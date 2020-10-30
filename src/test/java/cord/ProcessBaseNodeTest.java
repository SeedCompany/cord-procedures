package cord;

import org.junit.jupiter.api.*;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;

import cord.common.AllProperties;
import cord.common.AllRoles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.Perm;
import cord.model.Project;
import cord.roles.Administrator;

import static org.assertj.core.api.Assertions.assertThat;

import static org.neo4j.driver.Values.parameters;

// look in the debug console for logging statements

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProcessBaseNodeTest {

    private static final Config driverConfig = Config.builder().withoutEncryption().build();
    private static Driver driver;
    private Neo4j embeddedDatabaseServer;

    @BeforeAll
    void initializeNeo4j() {
        this.embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withProcedure(Authorization.class)
                .build();

        this.driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI(), driverConfig);
    }

    @AfterAll
    void closeDriver(){
        this.driver.close();
        this.embeddedDatabaseServer.close();
    }

    @AfterEach
    void cleanDb(){
        try(Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    public void shouldCreateAllAdministratorPermissionsOnProjectNode() {
        try(Session session = driver.session()){
            // prepare the db
            String creatorId = "user1";
            String adminId = "admin1";

            this.createUser(session, creatorId);
            this.createUser(session, adminId);

            this.createBaseNode(session, "Project", "project1");

            // run the procedure
            session.run(
                "CALL cord.processNewBaseNode($baseNodeId, $label, $creatorId)", 
                parameters(
                    "baseNodeId", "project1", 
                    "label", "Project", 
                    "creatorId", "user1"
                )
            );

            // verify results
            AllRoles allRoles = new AllRoles();
            
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
                    "project1"
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
                    "project1"
                );
            }


        }
    }

    private void createUser(Session session, String userId ){
        session.run("CREATE (:User {id:$userId})", parameters("userId", userId));        
    }

    private void createBaseNode(Session session, String label, String baseNodeId){
        session.run("CREATE (:"+label+" {id:$baseNodeId})", parameters("baseNodeId", baseNodeId));
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