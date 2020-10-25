package cord;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.neo4j.graphdb.*;
import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import cord.common.AllBaseNodes;
import cord.common.AllRoles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
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

      // create permission nodes for each property
      

      // create SGs for all the global roles
      this.mergeSecurityGroupForRole(AllRoles.Administrator, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.ProjectManagerGlobal, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.RegionalDirectorGlobal, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.FieldOperationsDirector, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.FinancialAnalystGlobal, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.Controller, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.ConsultantManager, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.Fundraising, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.Marketing, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.StaffMember, baseNodeId, label);
      this.mergeSecurityGroupForRole(AllRoles.Leadership, baseNodeId, label);
      
      // determine if the creator should be added to the admin group for this node
      Boolean addToAdmin = Utility.isProjectChildNode(label);

      // if (addToAdmin) 

      
      return Stream.of(new ProcessNewBaseNodeResponse(true));
    }

    public static class ProcessNewBaseNodeResponse {
      public Boolean success;

      public ProcessNewBaseNodeResponse(Boolean success){
        this.success = success;
      }
    }

    private Long mergeSecurityGroupForRole( BaseRole role, String baseNodeId, BaseNodeLabels label){
      // role.permission(label, property)
      
      Long sgId = Utility.getSecurityGroupNode(db, role, baseNodeId, label);
      if (sgId == null){
        sgId = this.createSecurityGroup(RoleNames.Administrator, baseNodeId, label);
      } 
      return sgId;
    }


    private Long createSecurityGroup(RoleNames role, String baseNodeId, BaseNodeLabels label){

      Long sgId = null;
      try ( Transaction tx = db.beginTx() )
      {
        // create the security group node and connect it to the base node
        Node sgNode = tx.createNode(Label.label(label.name()));
        sgId = sgNode.getId();
        sgNode.setProperty("id", "n" + Long.valueOf(sgId)); // todo, replace with nanoid like impl
        sgNode.setProperty("createdAt", LocalDateTime.now());
        sgNode.setProperty("role", role.name());

        Node baseNode = tx.findNode(Label.label(label.name()), "id", baseNodeId);
        if (baseNode == null) throw new RuntimeException("did not find base node when creating SG");

        sgNode.createRelationshipTo(baseNode, RelationshipType.withName("baseNode"));
        
        // add all permissions to the SG according to the role and base node class


        tx.commit();
      } catch(Exception e){
        throw new RuntimeException("failed to create security group");
      }

      return sgId;
    }

    private void addPermsToSg(Node sgNode, Node baseNode){

    }

  }
