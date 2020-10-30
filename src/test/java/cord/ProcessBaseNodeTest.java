package cord;

import org.junit.jupiter.api.*;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import cord.common.AllProperties;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.Perm;
import cord.model.Project;
import cord.roles.Administrator;

import static org.assertj.core.api.Assertions.assertThat;

import static org.neo4j.driver.Values.parameters;

import java.util.Random;

// look in the debug console for logging statements

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProcessBaseNodeTest {

    private static Driver driver;

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
    public void shouldCreateAllAdministratorPermissionsOnProjectNode() {
        try(Session session = driver.session()){
            // prepare the db
            Random random = new Random();
            String creatorId = "user" + random.nextInt(1000000);
            String adminId = "admin" + random.nextInt(1000000);

            this.createUser(session, creatorId, null);
            this.createUser(session, adminId, "Administrator");

            String projectId = "project" + random.nextInt(1000000);

            this.createBaseNode(session, "Project", projectId);

            // run the procedure
            session.run(
                "CALL cord.processNewBaseNode($baseNodeId, $label, $creatorId)", 
                parameters(
                    "baseNodeId", projectId, 
                    "label", "Project", 
                    "creatorId", creatorId
                )
            );

            // verify results
            // AllRoles allRoles = new AllRoles();
            
            // loop through properties for the creating user, who should have the admin role
            for (Project property : Project.values()){

                Perm perm = Administrator.permission.permission(BaseNodeLabels.Project, property.name());

                Boolean read = false;
                Boolean edit = false;
                
                switch (perm) {
                    case RO: read = true; break;
                    case RW: read = true; edit = true; break;
                    default: break;
                }

                this.verifyPropertyAccess(
                    session, 
                    BaseNodeLabels.Project, 
                    AllProperties.valueOf(property.name()), 
                    RoleNames.AdministratorRole, 
                    read,
                    edit, 
                    creatorId, 
                    projectId
                );
            }

            // loop through properties for the admin user
            for (Project property : Project.values()){

                Perm perm = Administrator.permission.permission(BaseNodeLabels.Project, property.name());

                Boolean read = false;
                Boolean edit = false;
                
                switch (perm) {
                    case RO: read = true; break;
                    case RW: read = true; edit = true; break;
                    default: break;
                }

                this.verifyPropertyAccess(
                    session, 
                    BaseNodeLabels.Project, 
                    AllProperties.valueOf(property.name()), 
                    RoleNames.AdministratorRole, 
                    read,
                    edit, 
                    adminId, 
                    projectId
                );
            }


        }
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

    private void verifyPropertyAccess(
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
                printError("access not granted", label, property, role, read, edit, userId, baseNodeId);
            }
                    
        } catch (NoSuchRecordException e){
            if (read){
                printError("access not granted", label, property, role, read, edit, userId, baseNodeId);
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
                printError("access grant error", label, property, role, read, edit, userId, baseNodeId);
            }
                    
        } catch (NoSuchRecordException e){
            if (edit){
                printError("access not granted", label, property, role, read, edit, userId, baseNodeId);
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