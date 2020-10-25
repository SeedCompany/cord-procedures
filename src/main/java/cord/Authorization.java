package cord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import javax.management.relation.Relation;

import org.neo4j.graphdb.*;
import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import cord.common.AllBaseNodes;
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

      this.log.info("cord.processNewBaseNode start");

      Node baseNode = Utility.getNode(db, baseNodeId, baseNodeLabel);
      
      // get the base node's labels and model
      BaseNodeLabels label = Utility.baseNodeClassStringToEnum(baseNodeLabel);
      ArrayList<String> model = Utility.getBaseNodePropertiesByLabel(label);

      // create permission nodes for each property
      HashMap<String, Node> permMap = this.createAllPermissionNodes(baseNode, label, model);

      // create SGs for all the global roles      
      BaseRole[] globalRoles = {
        AllRoles.Administrator,
        AllRoles.ConsultantManager,
        AllRoles.Controller,
        AllRoles.Leadership,
        AllRoles.FieldOperationsDirector,
        AllRoles.FinancialAnalystGlobal,
        AllRoles.Fundraising,
        AllRoles.Marketing,
        AllRoles.ProjectManagerGlobal,
        AllRoles.RegionalDirectorGlobal,
        AllRoles.StaffMember,
      };

      for (BaseRole role: globalRoles){
        this.mergeSecurityGroupForRole(role, baseNodeId, baseNode, label, model, permMap);
      }

      // determine if the creator should be added to the admin group for this node
      // and if to create project role SGs
      Boolean isProjectNode = Utility.isProjectChildNode(label);

      if (isProjectNode) {

        BaseRole[] projectRoles = {
          AllRoles.Consultant,
          AllRoles.FinancialAnalystOnProject,
          AllRoles.Intern,
          AllRoles.Liason,
          AllRoles.ProjectManagerOnProject,
          AllRoles.RegionalCommunicationCoordinator,
          AllRoles.RegionalDirectorOnProject,
          AllRoles.Translator
        };
  
        for (BaseRole role: projectRoles){
          this.mergeSecurityGroupForRole(role, baseNodeId, baseNode, label, model, permMap);
        }

      } else {
        // add creator to admin group
      }

      
      return Stream.of(new ProcessNewBaseNodeResponse(true));
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
      Node baseNode, 
      BaseNodeLabels label, 
      ArrayList<String> model,
      HashMap<String, Node> permMap
    ){
      
      Long sgId = Utility.getSecurityGroupNode(db, role, baseNodeId, label);
      if (sgId == null){
        sgId = this.createSecurityGroup(role, baseNode, label, model, permMap);
      } 
      return sgId;
    }

    private Long createSecurityGroup(
      BaseRole role, 
      Node baseNode, 
      BaseNodeLabels label, 
      ArrayList<String> model, 
      HashMap<String, Node> permMap
    ){

      Long sgId = null;
      try ( Transaction tx = db.beginTx() )
      {
        // create the security group node and connect it to the base node
        Node sgNode = tx.createNode(Label.label(label.name()));
        sgId = sgNode.getId();
        sgNode.setProperty(AllProperties.id.name(), this.getUniqueIdFromNeo4jId(sgId)); // todo, replace with nanoid like impl
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
          Node readPerm = permMap.get(property+"Read");
          Node editPerm = permMap.get(property+"Edit");

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
        throw new RuntimeException("failed to create security group");
      }

      return sgId;
    }

    private String getUniqueIdFromNeo4jId(Long id){
      return "n" + Long.valueOf(id);
    }

    private HashMap<String, Node> createAllPermissionNodes(Node baseNode, BaseNodeLabels label, ArrayList<String> propertyList) throws RuntimeException {

      try ( Transaction tx = db.beginTx() )
      {
        HashMap<String, Node> map = new HashMap<String, Node>();

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

          map.put(property+"Read", permRead);
  
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

          map.put(property+"Edit", permEdit);
            
        });
        
        tx.commit();
        
        return map;

      } catch(Exception e){
        throw new RuntimeException("error in creating permission nodes");
      }
    }
  
  }
