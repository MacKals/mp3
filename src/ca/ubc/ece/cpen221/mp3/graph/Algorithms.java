package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayDeque;
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
       graph.edgeExists(v1, v2);
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
	 * 
	 */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
	    
	    
	    
	    return null;
	}
	
	/**
	 * 
	 */
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex vertA, Vertex vertB){
	    
	    ArrayList<Vertex> downstreamFromA = graph.getDownstreamNeighbors(vertA);
	    ArrayList<Vertex> downstreamFromB = graph.getDownstreamNeighbors(vertB);
	    
	    
	    
	    return null;
	}

}
