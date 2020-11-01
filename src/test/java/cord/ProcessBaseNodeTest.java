package cord;

import org.junit.jupiter.api.*;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.neo4j.logging.Log;

import cord.common.AllProperties;
import cord.common.AllRoles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.Perm;
import cord.model.Project;
import cord.roles.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.neo4j.driver.Values.parameters;

import java.util.Random;

// look in the debug console for logging statements

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProcessBaseNodeTest {

    private static Driver driver;

    private AllRoles allRoles;

    public ProcessBaseNodeTest(){
        this.allRoles = new AllRoles();
    }

    @BeforeAll
    void initializeNeo4j() {
        driver = GraphDatabase.driver( 
            "bolt://localhost:7687", 
            AuthTokens.basic( "neo4j", "reallysecurepassword" ) 
        );
    }

    @AfterAll
    void closeDriver(){
        ProcessBaseNodeTest.driver.close();
    }

    @Test
    public void shouldCreateAllPermissionsForAllRoles() {

        try(Session session = driver.session()){
            // prepare the db
            Random random = new Random();

            String adminId = "adminId" + random.nextInt(1000000);
            String pmOnProjectId = "pmOnProjectId" + random.nextInt(1000000);
            String pmGlobalId = "pmGlobalId" + random.nextInt(1000000);
            String consultantId = "consultantId" + random.nextInt(1000000);
            
            this.createUser(session, adminId, Utility.getFrontendRoleNameFromApiRoleName(RoleNames.AdministratorRole));
            this.createUser(session, pmOnProjectId, Utility.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerGlobalRole));
            this.createUser(session, pmGlobalId, Utility.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerOnProjectRole));
            this.createUser(session, consultantId, Utility.getFrontendRoleNameFromApiRoleName(RoleNames.ConsultantRole));
            
            // create project
            String projectId = "project" + random.nextInt(1000000);
            this.createBaseNode(session, "Project", projectId);

            // add project members
            this.addProjectMembers(session, projectId, pmOnProjectId, "ProjectManager");
            this.addProjectMembers(session, projectId, consultantId, "Consultant");

            // run the procedure
            session.run(
                "CALL cord.processNewBaseNode($baseNodeId, $label, $creatorId)", 
                parameters(
                    "baseNodeId", projectId, 
                    "label", "Project", 
                    "creatorId", pmOnProjectId
                )
            );

            // verify results
            
            // loop through properties for the creating user, who should have the admin role
            // for (Project property : Project.values()){

            //     Perm perm = Administrator.permission.permission(BaseNodeLabels.Project, property.name());

            //     this.checkProperty(
            //         session, 
            //         perm,
            //         BaseNodeLabels.Project, 
            //         property.name(), 
            //         RoleNames.AdministratorRole, 
            //         projectId,
            //         pmOnProjectId
            //     );
            // }

            // loop through properties for the pm on project user, who is also the creator, but we may change that later
            for (Project property : Project.values()){

                Perm perm = ProjectManagerOnProject.permission.permission(BaseNodeLabels.Project, property.name());

                this.checkProperty(
                    session, 
                    perm,
                    BaseNodeLabels.Project, 
                    property.name(), 
                    RoleNames.ProjectManagerOnProjectRole, 
                    projectId,
                    pmOnProjectId
                );
            }

            // loop through properties for the admin user
            for (Project property : Project.values()){

                Perm perm = Administrator.permission.permission(BaseNodeLabels.Project, property.name());

                this.checkProperty(
                    session, 
                    perm,
                    BaseNodeLabels.Project, 
                    property.name(), 
                    RoleNames.AdministratorRole, 
                    projectId,
                    adminId
                );
            }

            // loop through properties for the pm global role
            for (Project property : Project.values()){

                Perm perm = Consultant.permission.permission(BaseNodeLabels.Project, property.name());

                this.checkProperty(
                    session, 
                    perm,
                    BaseNodeLabels.Project, 
                    property.name(), 
                    RoleNames.ConsultantRole, 
                    projectId,
                    consultantId
                );
            }

            // loop through properties for the consultant
            for (Project property : Project.values()){

                Perm perm = ProjectManagerGlobal.permission.permission(BaseNodeLabels.Project, property.name());

                this.checkProperty(
                    session, 
                    perm,
                    BaseNodeLabels.Project, 
                    property.name(), 
                    RoleNames.ProjectManagerGlobalRole, 
                    projectId,
                    pmGlobalId
                );
            }

        }
    }

    private void loop(Session session, String[] properties, RoleNames role, BaseNodeLabels label, String baseNodeId, String userId){
        for (String property : properties){

            BaseRole roleObj = allRoles.getRoleByName(role.name());

            Perm perm = roleObj.permission.permission(label, property);

            Boolean read = false;
            Boolean edit = false;
            
            switch (perm) {
                case RO: read = true; break;
                case RW: read = true; edit = true; break;
                default: break;
            }
    
            this.verifyPropertyAccess(
                session, 
                label, 
                AllProperties.valueOf(property), 
                role, 
                read,
                edit, 
                userId, 
                baseNodeId
            );
        }
    }

    private void checkProperty(Session session, Perm perm, BaseNodeLabels label, String property, RoleNames role, String baseNodeId, String userId){
        
        Boolean read = false;
        Boolean edit = false;
        
        switch (perm) {
            case RO: read = true; break;
            case RW: read = true; edit = true; break;
            default: break;
        }

        this.verifyPropertyAccess(
            session, 
            label, 
            AllProperties.valueOf(property), 
            role, 
            read,
            edit, 
            userId, 
            baseNodeId
        );
        
    }

    private void createUser(Session session, String userId, String role){
        if (role == null){
            session.run("CREATE (:User {id:$userId, createdAt: datetime()})", parameters("userId", userId));
        } else {
            session.run("CREATE (:User {id:$userId, createdAt: datetime()})-[:roles {active: true}]->(:Property {value:[$role]})", 
            parameters(
                "userId", userId,
                "role", role
            ));        
        }
    }

    private void createBaseNode(Session session, String label, String baseNodeId){
        session.run("CREATE (:BaseNode:"+label+" {id:$baseNodeId, createdAt: datetime()})", parameters("baseNodeId", baseNodeId));
    }

    private void addProjectMembers(Session session, String projectId, String userId, String projectRoleName){
        session.run(
            "MATCH (project:Project {id: $projectId}), (user:User {id:$userId}) "+
            "MERGE (user)<-[:user {active: true}]-(member:ProjectMember {id: $memberId, createdAt: datetime() })<-[:member {active: true}]-(project) "+
            "MERGE (member)-[:roles {active: true}]->(roles:Property{value:[$projectRoleName]})", 
            parameters(
                "projectId", projectId,
                "userId", userId,
                "memberId", projectId + ":" + userId,
                "projectRoleName", projectRoleName
            )
        );
    }

    private void verifyPropertyAccess( // reads the SG by role. verifies each role works.
        Session session, 
        BaseNodeLabels label, 
        AllProperties property, 
        RoleNames role, 
        Boolean read,
        Boolean edit,
        String userId, 
        String baseNodeId
    ) {
        try {
            session.run(
                "MATCH (:User {id:$userId})<-[:member]-(:SecurityGroup {role:$role}) "+
                "-[:permission]->(:Permission {property:$property, read: $read})-[:baseNode]-> "+
                "(baseNode:"+label.name()+" {id:$baseNodeId}) "+
                "RETURN id(baseNode) as id LIMIT 1", 
                parameters(
                    "property", property.name(),
                    "role", role.name(),
                    "read", read,
                    "userId", userId,
                    "baseNodeId", baseNodeId
                    )
            ).single(); // calling single will throw if a record isn't returned

            if (!read){
                printError("illegal read access", label, property, role, read, edit, userId, baseNodeId);
            }
                    
        } catch (NoSuchRecordException e){
            if (read){
                printError("read access not granted", label, property, role, read, edit, userId, baseNodeId);
            }
        }

        try {
            session.run(
                "MATCH (:User {id:$userId})<-[:member]-(:SecurityGroup {role:$role}) "+
                "-[:permission]->(:Permission {property:$property, edit: $edit})-[:baseNode]-> "+
                "(baseNode:"+label.name()+" {id:$baseNodeId}) "+
                "RETURN id(baseNode) as id LIMIT 1", 
                parameters(
                    "property", property.name(),
                    "role", role.name(),
                    "edit", edit,
                    "userId", userId,
                    "baseNodeId", baseNodeId
                )
            ).single(); // calling single will throw if a record isn't returned

            if (!edit){
                printError("illegal edit access", label, property, role, read, edit, userId, baseNodeId);
            }
                    
        } catch (NoSuchRecordException e){
            if (edit){
                printError("edit access not granted", label, property, role, read, edit, userId, baseNodeId);
            }
        }
    }

    private void printError(
        String message,
        BaseNodeLabels label, 
        AllProperties property, 
        RoleNames role, 
        Boolean read,
        Boolean edit,
        String userId, 
        String baseNodeId
    ){
        String errorMessage = "\n" + 
        "-----------------------------------------------------\n" +
            "\tmessage: "         + message + "\n" + 
            "\tbase node: "       + label.name() + "\n" + 
            "\tproperty: "        + property.name() + "\n" + 
            "\trole: "            + role.name() + "\n" + 
            "\tread: "            + read + "\n" +
            "\tedit: "            + edit + "\n" +
            "\tuserId: "          + userId + "\n" +
            "\tbaseNodeId: "      + baseNodeId + "\n" +
        "-----------------------------------------------------\n"
        ;

        System.out.println(errorMessage);
        assertThat(errorMessage).isEmpty();
    }

}