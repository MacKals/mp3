package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		//THIS DOESN'T WORK
		int distance = 0;
		
		List<Vertex> listOnLevel = new ArrayList<Vertex>();
		
		listOnLevel.addAll(graph.getUpstreamNeighbors(a));
		listOnLevel.addAll(graph.getDownstreamNeighbors(a));
		
	    for (Vertex vertexToCheck : listOnLevel){
	//        if ( )
	        distance += shortestDistance(graph, vertexToCheck, b);
	        
	    }
	    return distance;
	}
	
	/**
	 * 
	 */
	public static Set<List<Vertex>> BFS(Graph graph, Vertex startingVertex) {
	   
	//   Queue<Vertex> BFSQueue = new LinkedList<Vertex>();
	   
      /* useful methods of graph 
       graph.edgeExists(v1, v2);
       graph.getDownstreamNeighbors(v);
       graph.getUpstreamNeighbors(v);
	   */
	    
	    return null;
	    
	}
	
	/**
	 * 
	 */
	public static Set<List<Vertex>> DFS(Graph graph, Vertex a) {
	//    Deque<Integer> stack = new ArrayDeque<Integer>();
	       
	    return null;
	}
	
	/**
	 * Get all upstream vertices with a direct edge to both vertex a and vertex b
	 * @param graph Graph to be analysed 
	 * @param a Vertex
	 * @param b Vertex 
	 * @param list of common downstream neighbours of a and b
	 */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
	    
	    List<Vertex> commonUpstreamVertices = new ArrayList<Vertex>();
	    
	    //get neighbour of a
	    List<Vertex> aNeighbours = graph.getDownstreamNeighbors(a);

	    // compare all neighbours of a to neighbours of b using teh .edgeEgist method of the graph
	    for (Vertex aNeighbour : aNeighbours) {

	        if (graph.edgeExists(b, aNeighbour)) {
	        
	            commonUpstreamVertices.add(b);
	        }

	        if (graph.edgeExists(b, aNeighbour)) {      
	            commonUpstreamVertices.add(b);

	        }
	    }
	    
	    return Collections.unmodifiableList(commonUpstreamVertices);
	}
	
    /**
     * Get all downstream vertices with a direct edge to both vertex a and vertex b
     * @param graph Graph to be analysed 
     * @param a Vertex
     * @param b Vertex 
     * @param list of common upstream neighbours of a and b
     */
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex vertA, Vertex vertB){
	    
	    List<Vertex> commonDownstreamList = new ArrayList<Vertex>();
	    
	    List<Vertex> downstreamFromA = graph.getDownstreamNeighbors(vertA);
	    
	    for (Vertex elementInA : downstreamFromA){
	        if (graph.edgeExists(vertB, elementInA)){
	                commonDownstreamList.add(elementInA);
	        }    
	    }
	
	    return Collections.unmodifiableList(commonDownstreamList);
	}
	
	 
	interface BredthFirstSearch {
	    int evaluate(Vertex v, boolean upstream);   
	}
	
    public static int shortestDistanceVersion(Graph graph, Vertex a, Vertex b) {
                
        /*
         * get vertices connected to a
         * stage vertices connected
         * evaluate first staged vertex if not checked before
         *  -   evaluate:
         *      -   check if equal 
         *      -   stage connected vertices 
         * 
         */
        
        BredthFirstSearch Bfs = new BredthFirstSearch() {

            public int evaluate(Vertex v, boolean upstream) {
                
                //ensure a != b
                if (a.equals(b)) {
                    return 0;
                }
                
                // using breath first search to determine shortest distance, enabling us to terminate at once connection is found.
                List<Vertex> checkedVertices = new LinkedList<Vertex>();
                Queue<Vertex> stagedVertices = new LinkedList<Vertex>();
                
                stagedVertices.add(a);

                int traversedDistance = 1;

                while (stagedVertices.size() != 0) {
                          
                    Vertex vertexUnderEvaluation = stagedVertices.poll();
                    
                    List<Vertex> directionalVerteciesOfVertex;
                    
                    //determine which direction to probe
                    if (upstream) {
                        directionalVerteciesOfVertex = graph.getUpstreamNeighbors(vertexUnderEvaluation);
                    } else {
                        directionalVerteciesOfVertex = graph.getDownstreamNeighbors(vertexUnderEvaluation);
                    }
                    
                    for (Vertex vertex : directionalVerteciesOfVertex) {
                        
                        if (vertex.equals(b)) {
                            return traversedDistance;
                        }
                        
                        if (!checkedVertices.contains(vertex)) {
                            stagedVertices.add(vertex);
                        }
                    }   
                   
                    traversedDistance++;
                }
                
                return -1;
            }
        };
        
        int distanceUpstream = Bfs.evaluate(a, true);
        int distsanceDownstram = Bfs.evaluate(a, false);
        
        if (distanceUpstream == distsanceDownstram && distanceUpstream == -1) return distanceUpstream;
        else if (distanceUpstream == -1) return distsanceDownstram;
        else return distanceUpstream; 
    }
}