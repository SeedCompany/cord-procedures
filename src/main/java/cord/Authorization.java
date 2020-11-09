package cord;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import org.neo4j.graphdb.*;
import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import cord.common.AllProperties;
import cord.common.AllRoles;
import cord.common.BaseNodeLabels;
import cord.common.NonBaseNodeLabels;
import cord.common.NonPropertyRelationshipTypes;
import cord.common.RoleNames;
import cord.model.Perm;
import cord.roles.*;

public class Authorization {

    @Context
    public Log log;

    @Context
    public GraphDatabaseService db;

    @Procedure(value = "cord.processNewBaseNode", mode = Mode.WRITE)
    @Description("Add security and grant access to new node. NOT IDEMPOTENT!")
    public Stream<ProcessNewBaseNodeResponse> processNewBaseNode(
      @Name("baseNodeId") String baseNodeId,
      @Name("baseNodeLabel") String baseNodeLabel,
      @Name("creatorUserId") String creatorUserId
    ) throws RuntimeException {

      try {

        AllRoles allRoles = new AllRoles();

        Long baseNodeNeoId = Utility.getNode(db, baseNodeId, baseNodeLabel);
        
        // get the base node's labels and model
        BaseNodeLabels label = Utility.baseNodeClassStringToEnum(baseNodeLabel);
        ArrayList<String> model = Utility.getBaseNodePropertiesByLabel(label);

        // create permission nodes for each property
        HashMap<String, Long> permMap = this.createAllPermissionNodes(baseNodeNeoId, label, model);

        // create SGs for all the global roles      
        HashMap<RoleNames, Long> sgMap = new HashMap<RoleNames, Long>();

        for (BaseRole role: allRoles.globalRolesList()){
          Long sgNodeNeoId = this.mergeSecurityGroupForRole(role, baseNodeId, baseNodeNeoId, label, model, permMap);
          sgMap.put(role.roleName, sgNodeNeoId);
          
          // global role users
          this.addRoleMembersToSg(role, sgNodeNeoId);
        }

        // determine if the creator should be added to the admin group for this node
        // and if to create project role SGs
        Boolean isProjectContextNode = Utility.isProjectContextNode(label);

        if (isProjectContextNode) {

          // get project members
          Long projectNodeNeoId = Utility.getProjectNode(db, baseNodeNeoId, label);
          if (projectNodeNeoId == null){
            this.log.error("project id not found. skipping adding project members to new node. baseNodeNeoId: " + baseNodeNeoId + " label: " + label);
          } else {    
            ArrayList<Long> members = Utility.getProjectMembers(db, projectNodeNeoId);

            for (BaseRole role: allRoles.projectRolesList()){
              Long sgNodeNeoId = this.mergeSecurityGroupForRole(role, baseNodeId, baseNodeNeoId, label, model, permMap);
              sgMap.put(role.roleName, sgNodeNeoId);
            }

            this.processProjectMember(members, sgMap, allRoles);

          }

        } else {

          // add creator to admin group
          this.addMemberToSg(creatorUserId, sgMap.get(RoleNames.AdministratorRole));
        }

        return Stream.of(new ProcessNewBaseNodeResponse(true));

      } catch (Exception e){
        e.printStackTrace();
        this.log.error(e.getMessage());
        throw new RuntimeException("error in processing new base node " + baseNodeId);
      } 
    }

    public void processProjectMember(ArrayList<Long> members, HashMap<RoleNames, Long> sgMap, AllRoles allRoles) throws RuntimeException {

      try ( Transaction tx = db.beginTx() )
      {
        // add all project members to this base node for their correct role
        members.forEach(memberNodeNeoId -> {

          Node memberNode = tx.getNodeById(memberNodeNeoId);
          // get member's roles
          Iterable<Relationship> toRolesIter = memberNode.getRelationships(Direction.OUTGOING, 
          RelationshipType.withName(AllProperties.roles.name()));
          toRolesIter.forEach(rel -> {
            if ((Boolean)rel.getProperty(AllProperties.active.name()) == true){
              Node rolesNode = rel.getEndNode();
              if (rolesNode.hasProperty(AllProperties.value.name())){

                String[] roles = (String[]) rolesNode.getProperty(AllProperties.value.name());
                if (roles != null){
                  for (String role: roles){

                    // map role string to a role object

                    RoleNames dbRole = AllRoles.getRoleNameEnumFromFeString(role, true);

                    if (dbRole == null){
                      
                      continue;
                    }
                    
                    BaseRole roleObj = allRoles.getRoleByStringName(dbRole.name());
                    
                    // get member's user node
                    Relationship toUser = memberNode.getSingleRelationship(
                      RelationshipType.withName(AllProperties.user.name()),
                      Direction.OUTGOING);
                      Node memberUserNode = toUser.getEndNode();
                      // get role SG node
                      Long roleNodeNeoId = sgMap.get(roleObj.roleName);
                      Node roleNode = tx.getNodeById(roleNodeNeoId);
                      
                      // attach user to SG of the role
                      roleNode.createRelationshipTo(memberUserNode, 
                      RelationshipType.withName(NonPropertyRelationshipTypes.member.name()));

                    }
                  } else {
                    this.log.error("roles is null");
                  }
                }
            }
          });

        });
        tx.commit();
      } catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException("error in processing project member");
      }
    }

    public static class ProcessNewBaseNodeResponse {
      public Boolean success;

      public ProcessNewBaseNodeResponse(Boolean success){
        this.success = success;
      }
    }

    private Long mergeSecurityGroupForRole( 
      BaseRole role, 
      String baseNodeId, 
      Long baseNodeNeoId, 
      BaseNodeLabels label, 
      ArrayList<String> model,
      HashMap<String, Long> permMap
    ){
      
      Long sgNeoId = Utility.getSecurityGroupNode(db, role, baseNodeId, label);
      if (sgNeoId == null){
        sgNeoId = this.createSecurityGroup(role, baseNodeNeoId, label, model, permMap);
      } 
      return sgNeoId;
    }

    private Long createSecurityGroup(
      BaseRole role, 
      Long baseNodeNeoId, 
      BaseNodeLabels label, 
      ArrayList<String> model, 
      HashMap<String, Long> permMap
    ){

      final Long sgNodeNeoId;
      try ( Transaction tx = db.beginTx() )
      {
        Node baseNode = tx.getNodeById(baseNodeNeoId);
        // create the security group node and connect it to the base node
        Node sgNode = tx.createNode(
          Label.label(NonBaseNodeLabels.SecurityGroup.name())
        );
        sgNodeNeoId = sgNode.getId();
        sgNode.setProperty(AllProperties.id.name(), this.getUniqueIdFromNeo4jId(sgNodeNeoId)); 
        sgNode.setProperty(AllProperties.createdAt.name(), ZonedDateTime.now() );
        sgNode.setProperty(AllProperties.role.name(), role.roleName.name());
        sgNode.createRelationshipTo(baseNode, 
          RelationshipType.withName(NonPropertyRelationshipTypes.baseNode.name()));
        
        // add all permissions to the SG according to the role and base node class
        // cycle through properties of base node
        model.forEach(property -> {

          // determine if the role grants the prop
          Perm grant = role.permission.permission(label, property); 

          // get permision nodes and connect them if permitted
          Long readPermNeoId = permMap.get(property+"Read");
          Long editPermNeoId = permMap.get(property+"Edit");

          Node readPerm = tx.getNodeById(readPermNeoId);
          Node editPerm = tx.getNodeById(editPermNeoId);

          if (readPerm != null && editPerm != null){
            
            switch (grant){
              case RO: 
                sgNode.createRelationshipTo(readPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
              return;
              case RW: 
                sgNode.createRelationshipTo(readPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
                sgNode.createRelationshipTo(editPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
              return;
              default: return;
            }
          }
        });

        tx.commit();
      } catch(Exception e){
        this.log.error(e.getMessage());
        throw new RuntimeException("failed to create security group");
      }

      return sgNodeNeoId;
    }

    private String getUniqueIdFromNeo4jId(Long id){
      return "i0_" + Long.valueOf(id); // todo, replace with nanoid like impl
    }

    private HashMap<String, Long> createAllPermissionNodes(Long baseNodeNeoId, BaseNodeLabels label, ArrayList<String> propertyList) throws RuntimeException {

      try ( Transaction tx = db.beginTx() )
      {
        HashMap<String, Long> map = new HashMap<String, Long>();

        Node baseNode = tx.getNodeById(baseNodeNeoId);

        propertyList.forEach(property -> {
  
          String propertyLabelForPerm = label.name() + property;
  
          Node permRead = tx.createNode(
            Label.label(NonBaseNodeLabels.Permission.name()), 
            Label.label(propertyLabelForPerm),
            Label.label(NonBaseNodeLabels.canRead.name())
            );
          permRead.setProperty(AllProperties.property.name(), property);
          permRead.setProperty(AllProperties.read.name(), true);
          permRead.createRelationshipTo(baseNode, 
            RelationshipType.withName(NonPropertyRelationshipTypes.baseNode.name()));

          map.put(property+"Read", permRead.getId());
  
          Node permEdit = tx.createNode(
            Label.label(NonBaseNodeLabels.Permission.name()), 
            Label.label(propertyLabelForPerm),
            Label.label(NonBaseNodeLabels.canRead.name()),
            Label.label(NonBaseNodeLabels.canEdit.name())
            );
          permEdit.setProperty(AllProperties.property.name(), property);
          permEdit.setProperty(AllProperties.read.name(), true);
          permEdit.setProperty(AllProperties.edit.name(), true);
          permEdit.createRelationshipTo(baseNode, 
            RelationshipType.withName(NonPropertyRelationshipTypes.baseNode.name()));

          map.put(property+"Edit", permEdit.getId());
            
        });
        
        tx.commit();
        
        return map;

      } catch(Exception e){
        this.log.error(e.getMessage());
        throw new RuntimeException("error in creating permission nodes");
      }
    }
  
    public void addMemberToSg(String userId, Long sgNodeNeoId) throws RuntimeException {
      try ( Transaction tx = this.db.beginTx() )
      {
        Node sgNode = tx.getNodeById(sgNodeNeoId);
        Node userNode = tx.findNode(Label.label(BaseNodeLabels.User.name()), AllProperties.id.name(), userId);
        sgNode.createRelationshipTo(userNode, 
          RelationshipType.withName(NonPropertyRelationshipTypes.member.name()));
        tx.commit();
      } catch(Exception e){
        this.log.error(e.getMessage());
        throw new RuntimeException("error in adding member to SG. userId, sgId: " + userId + " " + sgNodeNeoId);
      }
    }

    private void addRoleMembersToSg(BaseRole role, Long sgNodeNeoId){

      try ( Transaction tx = db.beginTx() ) {

          // get all the users with a specific role in their user object
          String feRoleName = AllRoles.getFrontendRoleNameFromApiRoleName(role.roleName);          
          
          tx.execute(
            "call apoc.periodic.iterate('MATCH (sg:SecurityGroup),(user:User)-[:roles {active: true}]->(roles:Property) "+
            "WHERE \""+feRoleName+"\" IN roles.value AND id(sg) = "+sgNodeNeoId+" AND NOT (user)<-[:member]-(sg) RETURN user, sg', "+
            "'MERGE (user)<-[:member]-(sg)', {batchSize:100}) yield batches, total return batches, total"
          );

          tx.commit();

      } catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException("error in adding an SG to all users with a specific role");
      }

    } 

  }



  