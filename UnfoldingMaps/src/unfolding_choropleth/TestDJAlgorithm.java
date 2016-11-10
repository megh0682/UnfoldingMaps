package unfolding_choropleth;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import unfolding_choropleth.DJAlgorithm;
import unfolding_choropleth.Edge;
import unfolding_choropleth.Graph;
import unfolding_choropleth.Vertex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDJAlgorithm {


  private List<Vertex> nodes;
  private List<Edge> edges;

  @Test
  public void testExcute() {
    nodes = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
    for (int i = 0; i < 11; i++) {
      Vertex location = new Vertex("Node_" + i, "Node_" + i);
      nodes.add(location);
    }

    addLane("Edge_0", 0, 1, 85);
    addLane("Edge_1", 0, 2, 217);
    addLane("Edge_2", 0, 4, 173);
    addLane("Edge_3", 2, 6, 186);
    addLane("Edge_4", 2, 7, 103);
    addLane("Edge_5", 3, 7, 183);
    addLane("Edge_6", 5, 8, 250);
    addLane("Edge_7", 8, 9, 84);
    addLane("Edge_8", 7, 9, 167);
    addLane("Edge_9", 4, 9, 502);
    addLane("Edge_10", 9, 10, 40);
    addLane("Edge_11", 1, 10, 600);

    // Lets check from location Loc_1 to Loc_10
    Graph graph = new Graph(nodes, edges);
    DJAlgorithm dijkstra = new DJAlgorithm(graph);
    dijkstra.execute(nodes.get(0));
    
    
    LinkedList<Vertex> path0to10 = dijkstra.getPath(nodes.get(10));
    LinkedList<Vertex> path0to3 = dijkstra.getPath(nodes.get(3));
    LinkedList<Vertex> path0to7 = dijkstra.getPath(nodes.get(7));
    LinkedList<Vertex> path0to9= dijkstra.getPath(nodes.get(9));
    
    
    assertNotNull(path0to9);
    assertTrue(path0to9.size() > 0);
    
    for (Vertex vertex : path0to9) {
      System.out.println(vertex);
    }
    
  }

  private void addLane(String laneId, int sourceLocNo, int destLocNo,
      int duration) {
    Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
    edges.add(lane);
  }
} 