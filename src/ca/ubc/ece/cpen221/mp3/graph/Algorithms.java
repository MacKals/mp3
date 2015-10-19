package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

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
		// TODO: Implement this method and others
	    
		return 0;
	}
	
	/**
	 * 
	 */
	public static int BFS(Graph graph, Vertex startingVertex) {
	    
	    
	   Queue<Vertex> BFSQueue = new LinkedList<Vertex>();
	    
	   int distance = 0;
	   
	   
	   
      /* useful methods of graph 
       graph.edgeExists(v1, v2);
       graph.getDownstreamNeighbors(v);
       graph.getUpstreamNeighbors(v);
	   */
	    
	    return -1;
	    
	}
	
	/**
	 * 
	 */
	public static TreeMap<Vertex, Vertex> DFS(Graph graph, Vertex a) {
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

	    // compare all neighbours of a to neighbours of b using teh .edgeEgist method of the graph
	    for (Vertex aNeighbour : aNeighbours) {

	        if (graph.edgeExists(b, aNeighbour)) {
	        
	            commonUpstreamVertices.add(b);

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
	    
	    //see which vertices are shared within both lists
	    
	    for (Vertex elementInA : downstreamFromA){
	        if (graph.edgeExists(vertB, elementInA)){
	                commonDownstreamList.add(elementInA);
	        }
	        
	    }
	
	    return Collections.unmodifiableList(commonDownstreamList);
	}

}
