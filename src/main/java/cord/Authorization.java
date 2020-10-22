package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.neo4j.graphdb.*;
import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import static org.neo4j.internal.helpers.collection.MapUtil.stringMap;

public class Authorization {

    @Context
    public Log log;

    /**
     * @param node  The node to get the relationships for
     * @return  A RelationshipTypes instance with the relations (incoming and outgoing) for a given node.
     */
    @Procedure(value = "cord.asdf")
    @Description("Do the thing.")
    public Stream<RelationshipTypes> getRelationshipTypes(@Name("node") Node node) {
        List<String> outgoing = new ArrayList<>();
        node.getRelationships(Direction.OUTGOING).iterator()
            .forEachRemaining(rel -> AddDistinct(outgoing, rel));

        List<String> incoming = new ArrayList<>();
        node.getRelationships(Direction.INCOMING).iterator()
                .forEachRemaining(rel -> AddDistinct(incoming, rel));

        return Stream.of(new RelationshipTypes(incoming, outgoing));
    }

    private void AddDistinct(List<String> list, Relationship relationship){
      AddDistinct(list, relationship.getType().name());
  }

  /**
   * Adds an item to a List only if the item is not already in the List
   *
   * @param list  the list to add the distinct item to
   * @param item  the item to add to the list
   */
  private <T> void AddDistinct(List<T> list, T item){
      if(!list.contains(item))
          list.add(item);
  }
    public static class RelationshipTypes {
      // These records contain two lists of distinct relationship types going in and out of a Node.
      public List<String> outgoing;
      public List<String> incoming;

      public RelationshipTypes(List<String> incoming, List<String> outgoing) {
          this.outgoing = outgoing;
          this.incoming = incoming;
      }
  }
}
