package cord;

import org.neo4j.internal.kernel.api.exceptions.ProcedureException;
import org.neo4j.kernel.api.exceptions.Status;

import java.util.ArrayList;

import org.neo4j.graphdb.*;
import cord.model.BaseNode;

public class Utility {

  public static BaseNode baseNodeClassStringToEnum(String className) throws RuntimeException {
    switch (className){
      case "Budget":
      return BaseNode.Budget;
      default:
      throw new RuntimeException("BaseNode class not found: " + className);
    }
  }

  public static ArrayList<String> getBaseNodeLabels(GraphDatabaseService db, String id) throws RuntimeException {
    try ( Transaction tx = db.beginTx() )
    {
        Node baseNode = tx.findNode(Label.label("BaseNode"), "id", id);
        Iterable<Label> labels = baseNode.getLabels();
        ArrayList<String> labelArray = new ArrayList<String>();
        labels.forEach((label) -> {
          labelArray.add(label.name());
        });
        tx.commit();
        return labelArray;
    } catch(Exception e){
      throw new RuntimeException("error in finding base node lables: " + id);
    }
  }

}
