package cord;

// import apoc.periodic.PeriodicExtended;

import org.junit.jupiter.api.*;
import org.neo4j.configuration.GraphDatabaseSettings;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.neo4j.logging.Log;

import cord.common.AllProperties;
import cord.common.AllRoles;
import cord.common.BaseNodeLabels;
import cord.common.FeRoleNames;
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

    // private static final Config driverConfig = Config.builder().withoutEncryption().build();
    // private Neo4j embeddedDatabaseServer;

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

    // @BeforeAll
    // void initializeNeo4j() throws URISyntaxException {

    //     var pluginDirContainingApocJar = new File(
	// 		ProcessBaseNodeTest.driverConfig.getClass().getProtectionDomain().getCodeSource().getLocation().toURI())
    //         .getParentFile().toPath();
            
    //     this.embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
    //             .withDisabledServer()
    //             .withConfig(GraphDatabaseSettings.plugin_dir, pluginDirContainingApocJar)
    //             .withConfig(GraphDatabaseSettings.procedure_unrestricted, List.of("apoc.*"))
    //             .withProcedure(apoc.periodic.PeriodicExtended.class)
    //             .withProcedure(Authorization.class)
    //             .build();

        

    //     ProcessBaseNodeTest.driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI(), driverConfig);
    // }

    // @AfterAll
    // void closeDriver(){
    //     ProcessBaseNodeTest.driver.close();
    //     this.embeddedDatabaseServer.close();
    // }

    @Test
    public void shouldCreateAllPermissionsForAllRoles() {

        try(Session session = driver.session()){
            // prepare the db
            Random random = new Random();

            // String adminId =            "adminId"               + random.nextInt(1000000);
            // String consultantId =       "consultantId"          + random.nextInt(1000000);
            // String cmId =               "cmId"                  + random.nextInt(1000000);
            // String ctrlId =             "ctrlId"                + random.nextInt(1000000);
            // String fieldOpsId =         "fieldOpsId"            + random.nextInt(1000000);
            // String faGlobalId =         "faGlobalId"            + random.nextInt(1000000);
            // String faProjectId =        "faProjectId"           + random.nextInt(1000000);
            // String funId =              "funId"                 + random.nextInt(1000000);
            // String internId =           "internId"              + random.nextInt(1000000);
            // String leadId =             "leadId"                + random.nextInt(1000000);
            // String liasonId =           "liasonId"              + random.nextInt(1000000);
            // String markId =             "markId"                + random.nextInt(1000000);
            // String pmGlobalId =         "pmGlobalId"            + random.nextInt(1000000);
            String pmOnProjectId =      "pmOnProjectId"         + random.nextInt(1000000);
            // String commId =             "commId"                + random.nextInt(1000000);
            // String rdGlobalId =         "rdGlobalId"            + random.nextInt(1000000);
            // String rdProjectId =        "rdProjectId"           + random.nextInt(1000000);
            // String staffId =            "staffId"               + random.nextInt(1000000);
            // String tranId =             "tranId"                + random.nextInt(1000000);

            // String adminIdNotOnProject =            "adminId"               + random.nextInt(1000000);
            // String consultantIdNotOnProject =       "consultantId"          + random.nextInt(1000000);
            // String cmIdNotOnProject =               "cmId"                  + random.nextInt(1000000);
            // String ctrlIdNotOnProject =             "ctrlId"                + random.nextInt(1000000);
            // String fieldOpsIdNotOnProject =         "fieldOpsId"            + random.nextInt(1000000);
            // String faGlobalIdNotOnProject =         "faGlobalId"            + random.nextInt(1000000);
            // String faProjectIdNotOnProject =        "faProjectId"           + random.nextInt(1000000);
            // String funIdNotOnProject =              "funId"                 + random.nextInt(1000000);
            // String internIdNotOnProject =           "internId"              + random.nextInt(1000000);
            // String leadIdNotOnProject =             "leadId"                + random.nextInt(1000000);
            // String liasonIdNotOnProject =           "liasonId"              + random.nextInt(1000000);
            // String markIdNotOnProject =             "markId"                + random.nextInt(1000000);
            // String pmGlobalIdNotOnProject =         "pmGlobalId"            + random.nextInt(1000000);
            // String pmOnProjectIdNotOnProject =      "pmOnProjectId"         + random.nextInt(1000000);
            // String commIdNotOnProject =             "commId"                + random.nextInt(1000000);
            // String rdGlobalIdNotOnProject =         "rdGlobalId"            + random.nextInt(1000000);
            // String rdProjectIdNotOnProject =        "rdProjectId"           + random.nextInt(1000000);
            // String staffIdNotOnProject =            "staffId"               + random.nextInt(1000000);
            // String tranIdNotOnProject =             "tranId"                + random.nextInt(1000000);
            
            // this.createUser(session, adminId,           AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.AdministratorRole));
            // this.createUser(session, consultantId,      AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ConsultantRole));
            // this.createUser(session, cmId,              AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ConsultantManagerRole));
            // this.createUser(session, ctrlId,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ControllerRole));
            // this.createUser(session, fieldOpsId,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FieldOperationsDirectorRole));
            // this.createUser(session, faProjectId,       AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FinancialAnalystOnProjectRole));
            // this.createUser(session, faGlobalId,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FinancialAnalystGlobalRole));
            // this.createUser(session, funId,             AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FundraisingRole));
            // this.createUser(session, internId,          AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.InternRole));
            // this.createUser(session, leadId,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.LeadershipRole));
            // this.createUser(session, liasonId,          AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.LiasonRole));
            // this.createUser(session, markId,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.MarketingRole));
            this.createUser(session, pmOnProjectId,     AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerOnProjectRole));
            // this.createUser(session, pmGlobalId,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerGlobalRole));
            // this.createUser(session, commId,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalCommunicationCoordinatorRole));
            // this.createUser(session, rdProjectId,       AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalDirectorOnProjectRole));
            // this.createUser(session, rdGlobalId,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalDirectorGlobalRole));
            // this.createUser(session, staffId,           AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.StaffMemberRole));
            // this.createUser(session, tranId,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.TranslatorRole));

            // this.createUser(session, adminIdNotOnProject,           AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.AdministratorRole));
            // this.createUser(session, consultantIdNotOnProject,      AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ConsultantRole));
            // this.createUser(session, cmIdNotOnProject,              AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ConsultantManagerRole));
            // this.createUser(session, ctrlIdNotOnProject,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ControllerRole));
            // this.createUser(session, fieldOpsIdNotOnProject,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FieldOperationsDirectorRole));
            // this.createUser(session, faGlobalIdNotOnProject,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FinancialAnalystOnProjectRole));
            // this.createUser(session, faProjectIdNotOnProject,       AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FinancialAnalystGlobalRole));
            // this.createUser(session, funIdNotOnProject,             AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.FundraisingRole));
            // this.createUser(session, internIdNotOnProject,          AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.InternRole));
            // this.createUser(session, leadIdNotOnProject,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.LeadershipRole));
            // this.createUser(session, liasonIdNotOnProject,          AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.LiasonRole));
            // this.createUser(session, markIdNotOnProject,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.MarketingRole));
            // this.createUser(session, pmGlobalIdNotOnProject,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerOnProjectRole));
            // this.createUser(session, pmOnProjectIdNotOnProject,     AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.ProjectManagerGlobalRole));
            // this.createUser(session, commIdNotOnProject,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalCommunicationCoordinatorRole));
            // this.createUser(session, rdGlobalIdNotOnProject,        AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalDirectorOnProjectRole));
            // this.createUser(session, rdProjectIdNotOnProject,       AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.RegionalDirectorGlobalRole));
            // this.createUser(session, staffIdNotOnProject,           AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.StaffMemberRole));
            // this.createUser(session, tranIdNotOnProject,            AllRoles.getFrontendRoleNameFromApiRoleName(RoleNames.TranslatorRole));
            
            // create project
            String projectId = "project" + random.nextInt(1000000);
            this.createBaseNode(session, "Project", projectId);

            // add project members
            // this.addProjectMembers(session, projectId, adminId,             FeRoleNames.Administrator);
            // this.addProjectMembers(session, projectId, consultantId,        FeRoleNames.Consultant);
            // this.addProjectMembers(session, projectId, cmId,                FeRoleNames.ConsultantManager);
            // this.addProjectMembers(session, projectId, ctrlId,              FeRoleNames.Controller);
            // this.addProjectMembers(session, projectId, fieldOpsId,          FeRoleNames.FieldOperationsDirector);
            // this.addProjectMembers(session, projectId, faProjectId,         FeRoleNames.FinancialAnalyst);
            // this.addProjectMembers(session, projectId, funId,               FeRoleNames.Fundraising);
            // this.addProjectMembers(session, projectId, internId,            FeRoleNames.Intern);
            // this.addProjectMembers(session, projectId, leadId,              FeRoleNames.Leadership);
            // this.addProjectMembers(session, projectId, liasonId,            FeRoleNames.Liason);
            // this.addProjectMembers(session, projectId, markId,              FeRoleNames.Marketing);
            this.addProjectMembers(session, projectId, pmOnProjectId,       FeRoleNames.ProjectManager);
            // this.addProjectMembers(session, projectId, commId,              FeRoleNames.RegionalCommunicationCoordinator);
            // this.addProjectMembers(session, projectId, rdProjectId,         FeRoleNames.RegionalDirector);
            // this.addProjectMembers(session, projectId, staffId,             FeRoleNames.StaffMember);
            // this.addProjectMembers(session, projectId, tranId,              FeRoleNames.Translator);

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
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.AdministratorRole,                     BaseNodeLabels.Project, projectId,      adminId);      
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.ConsultantRole,                        BaseNodeLabels.Project, projectId,      consultantId); 
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.ConsultantManagerRole,                 BaseNodeLabels.Project, projectId,      cmId);
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.ControllerRole,                        BaseNodeLabels.Project, projectId,      ctrlId);       
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.FieldOperationsDirectorRole,           BaseNodeLabels.Project, projectId,      fieldOpsId);   
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.FinancialAnalystOnProjectRole,         BaseNodeLabels.Project, projectId,      faProjectId);  
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.FundraisingRole,                       BaseNodeLabels.Project, projectId,      funId);        
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.InternRole,                            BaseNodeLabels.Project, projectId,      internId);     
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.LeadershipRole,                        BaseNodeLabels.Project, projectId,      leadId);       
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.LiasonRole,                            BaseNodeLabels.Project, projectId,      liasonId);     
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.MarketingRole,                         BaseNodeLabels.Project, projectId,      markId);       
            this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.ProjectManagerOnProjectRole,           BaseNodeLabels.Project, projectId,      pmOnProjectId);
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.RegionalCommunicationCoordinatorRole,  BaseNodeLabels.Project, projectId,      commId);       
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.RegionalDirectorOnProjectRole,         BaseNodeLabels.Project, projectId,      rdProjectId);  
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.StaffMemberRole,                       BaseNodeLabels.Project, projectId,      staffId);      
            // this.checkRoleAccess(session, Utility.getNames(Project.class), RoleNames.TranslatorRole,                        BaseNodeLabels.Project, projectId,      tranId);
            
            // todo: add tests for non-project members
        }
    }

    private void checkRoleAccess(
        Session session, 
        String[] properties, 
        RoleNames role, 
        BaseNodeLabels label, 
        String baseNodeId, 
        String userId
    ){

        for (String property : properties){

            BaseRole roleObj = allRoles.getRoleByStringName(role.name());
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

    private void addProjectMembers(Session session, String projectId, String userId, FeRoleNames projectRoleName){
        session.run(
            "MATCH (project:Project {id: $projectId}), (user:User {id:$userId}) "+
            "MERGE (user)<-[:user {active: true}]-(member:ProjectMember {id: $memberId, createdAt: datetime() })<-[:member {active: true}]-(project) "+
            "MERGE (member)-[:roles {active: true}]->(roles:Property{value:[$projectRoleName]})", 
            parameters(
                "projectId", projectId,
                "userId", userId,
                "memberId", projectId + ":" + userId,
                "projectRoleName", projectRoleName.name()
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