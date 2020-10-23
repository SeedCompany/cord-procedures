package cord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.neo4j.graphdb.*;
import org.neo4j.internal.kernel.api.exceptions.ProcedureException;
import org.neo4j.kernel.api.exceptions.Status;
import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import cord.model.BaseNode;

public class Authorization {

    @Context
    public Log log;

    @Context
    public GraphDatabaseService db;

    @Procedure(value = "cord.processNewBaseNode", mode = Mode.WRITE)
    @Description("Do the thing.")
    public Stream<ProcessNewBaseNodeResponse> processNewBaseNode(
      @Name("baseNodeId") String baseNodeId,
      @Name("creatorUserId") String creatorUserId
    ) throws RuntimeException {

      this.log.info("cord.processNewBaseNode");
      
      // get the base node's labels
      ArrayList<String> labels = Utility.getBaseNodeLabels(db, baseNodeId);

      this.mergeSecurityGroupForRole();
      
      return Stream.of(new ProcessNewBaseNodeResponse(true));
    }

    public static class ProcessNewBaseNodeResponse {
      public Boolean success;

      public ProcessNewBaseNodeResponse(Boolean success){
        this.success = success;
      }
    }

    private void mergeSecurityGroupForRole(){

    }

  }
