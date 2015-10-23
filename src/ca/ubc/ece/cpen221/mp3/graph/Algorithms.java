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
	    
	    /*
	     * Operation:
	     * get vertex a
	     * add vertecies to stack (evaluates the most recent first)
	     * evaluate uppermost entry in stack 
	     * 
	     * evaluate: 
	     *  - check if evaluated before
	     *  - get associated vertices and add to set
	     *  - 
	     */
	    
	    
	    
	    Deque<Integer> stack = new ArrayDeque<Integer>();
	       
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

	    // compare all neighbours of a to neighbours of b using the .edgeExists method of the graph
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
	
	 
	interface BreadthFirstSearch {
	    int evaluate(Vertex v, boolean upstream);   
	}
	
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
                        
        int NotFound = -1;

        /*
         * get vertices connected to a
         * stage vertices connected
         * evaluate first staged vertex if not checked before
         *  -   evaluate:
         *      -   check if equal 
         *      -   stage connected vertices 
         * 
         */
        
        BreadthFirstSearch Bfs = new BreadthFirstSearch() {

            public int evaluate(Vertex v, boolean upstream) {
                
                //ensure a != b
                if (a.equals(b)) {
                    return 0;
                }
                
                // using breadth first search to determine shortest distance, enabling us to terminate once connection is found.
                List<Vertex> checkedVertices = new LinkedList<Vertex>();
                Queue<Vertex> stagedVertices = new LinkedList<Vertex>();
                
                stagedVertices.add(a);

                int traversedDistance = 1;

                while (stagedVertices.size() != 0) {
                          
                    Vertex vertexUnderEvaluation = stagedVertices.poll();
                    
                    List<Vertex> directionalVerticesOfVertex;
                    
                    //probe in specified direction, upstream or downstream
                    if (upstream) {
                        directionalVerticesOfVertex = graph.getUpstreamNeighbors(vertexUnderEvaluation);
                    } else {
                        directionalVerticesOfVertex = graph.getDownstreamNeighbors(vertexUnderEvaluation);
                    }
                    
                    for (Vertex vertex : directionalVerticesOfVertex) {
                        
                        if (vertex.equals(b)) {
                            return traversedDistance;
                        }
                        
                        if (!checkedVertices.contains(vertex)) {
                            stagedVertices.add(vertex);
                        }
                        
                    }   
                   
                    traversedDistance++;
                }
                
                return NotFound;
            }
        };
                
        int distanceUpstream = Bfs.evaluate(a, true);
        int distanceDownstream = Bfs.evaluate(a, false);
        
        if (distanceDownstream == NotFound && distanceUpstream == NotFound) return NotFound;
        else if (distanceUpstream == NotFound) return distanceDownstream;
        else if (distanceDownstream == NotFound) return distanceUpstream;
        else return distanceUpstream < distanceDownstream ? distanceUpstream : distanceDownstream; 
    }
}