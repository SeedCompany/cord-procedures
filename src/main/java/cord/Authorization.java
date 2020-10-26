package cord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import cord.model.Permission;
import cord.roles.*;

public class Authorization {

    @Context
    public Log log;

    @Context
    public GraphDatabaseService db;

    @Procedure(value = "cord.processNewBaseNode", mode = Mode.WRITE)
    @Description("Do the thing.")
    public Stream<ProcessNewBaseNodeResponse> processNewBaseNode(
      @Name("baseNodeId") String baseNodeId,
      @Name("baseNodeLabel") String baseNodeLabel,
      @Name("creatorUserId") String creatorUserId
    ) throws RuntimeException {

      try {

        this.log.info("cord.processNewBaseNode start");

        this.log.info("creatorUserId: " + creatorUserId);

        Long baseNodeNeoId = Utility.getNode(db, baseNodeId, baseNodeLabel);
        
        // get the base node's labels and model
        BaseNodeLabels label = Utility.baseNodeClassStringToEnum(baseNodeLabel);
        ArrayList<String> model = Utility.getBaseNodePropertiesByLabel(label);

        // create permission nodes for each property
        HashMap<String, Long> permMap = this.createAllPermissionNodes(baseNodeNeoId, label, model);

        // create SGs for all the global roles      
        HashMap<RoleNames, Long> sgMap = new HashMap<RoleNames, Long>();

        BaseRole[] globalRoles = {
           new Administrator(),
           new ConsultantManager(),
           new Controller(),
           new Leadership(),
           new FieldOperationsDirector(),
           new FinancialAnalystGlobal(),
           new Fundraising(),
           new Marketing(),
           new ProjectManagerGlobal(),
           new RegionalDirectorGlobal(),
           new StaffMember()
        };

        this.log.info("a1");

        for (BaseRole role: globalRoles){
          Long sgNodeNeoId = this.mergeSecurityGroupForRole(role, baseNodeId, baseNodeNeoId, label, model, permMap);
          sgMap.put(role.roleName, sgNodeNeoId);

          // global role users
          this.addRoleMembersToSg(role, sgNodeNeoId);
        }

        this.log.info("a2");

        // determine if the creator should be added to the admin group for this node
        // and if to create project role SGs
        Boolean isProjectNode = Utility.isProjectChildNode(label);

        if (isProjectNode) {

          this.log.info("a3");

        // get project members
        Long projectNodeNeoId = Utility.getProjectNode(db, baseNodeNeoId, label);
        ArrayList<Long> members = Utility.getProjectMembers(db, projectNodeNeoId);

          BaseRole[] projectRoles = {
            new Consultant(),
            new FinancialAnalystOnProject(),
            new Intern(),
            new Liason(),
            new ProjectManagerOnProject(),
            new RegionalCommunicationCoordinator(),
            new RegionalDirectorOnProject(),
            new Translator()
          };
    
          this.log.info("a4");
          for (BaseRole role: projectRoles){
            this.log.info("a4.5");
            Long sgNodeNeoId = this.mergeSecurityGroupForRole(role, baseNodeId, baseNodeNeoId, label, model, permMap);
            sgMap.put(role.roleName, sgNodeNeoId);
          }

          this.log.info("a5");

          this.processProjectMember(members, sgMap);

          this.log.info("a6");
        } else {
          this.log.info("a7");
          // add creator to admin group
          this.addMemberToSg(creatorUserId, sgMap.get(RoleNames.AdministratorRole));
        }

        this.log.info("cord.processNewBaseNode stop");

        return Stream.of(new ProcessNewBaseNodeResponse(true));


      } catch (Exception e){
        e.printStackTrace();
        this.log.error(e.getMessage());
      }
      return Stream.of(new ProcessNewBaseNodeResponse(false));
    }

    public void processProjectMember(ArrayList<Long> members, HashMap<RoleNames, Long> sgMap) throws RuntimeException {
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
              String[] roles = (String[]) rolesNode.getProperty(AllProperties.value.name());
              for (String role: roles){
                // map role string to a role object
                BaseRole roleObj = AllRoles.getRoleByName(role);
                
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
            }
          });

        });
        tx.commit();
      } catch(Exception e){
        this.log.error(e.getMessage());
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
        sgNode.setProperty(AllProperties.createdAt.name(), LocalDateTime.now());
        sgNode.setProperty(AllProperties.role.name(), role.roleName.name() );
        sgNode.createRelationshipTo(baseNode, 
          RelationshipType.withName(NonPropertyRelationshipTypes.baseNode.name()));
        
        // add all permissions to the SG according to the role and base node class
        // cycle through properties of base node
        model.forEach(property -> {
          // determine if the role grants the prop
          // static access not possible without gloriously large switch, ignore yellow squiggles
          Permission grant = role.permission(label, property); 

          // get permision nodes and connect them if permitted
          Long readPermNeoId = permMap.get(property+"Read");
          Long editPermNeoId = permMap.get(property+"Edit");

          Node readPerm = tx.getNodeById(readPermNeoId);
          Node editPerm = tx.getNodeById(editPermNeoId);

          if (readPerm != null && editPerm != null){
            
            switch (grant){
              case Read: 
                sgNode.createRelationshipTo(readPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
              return;
              case Write: 
                sgNode.createRelationshipTo(readPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
                sgNode.createRelationshipTo(editPerm, 
                  RelationshipType.withName(NonPropertyRelationshipTypes.permission.name()));
              return;
              case ReadWrite: 
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
      try ( Transaction tx = db.beginTx() )
      {
          Node sgNode = tx.getNodeById(sgNodeNeoId);
          Iterator<Node> iter = tx.findNodes(Label.label(role.roleName.name()));
          while(iter.hasNext()){
            Node userNode = iter.next();
            sgNode.createRelationshipTo(userNode, 
              RelationshipType.withName(NonPropertyRelationshipTypes.member.name()));
          }
          tx.commit();
      } catch(Exception e){
        this.log.error(e.getMessage());
        throw new RuntimeException("error in adding role members to SG: " + sgNodeNeoId + " " + role.roleName.name());
      }
    }

    

  }
