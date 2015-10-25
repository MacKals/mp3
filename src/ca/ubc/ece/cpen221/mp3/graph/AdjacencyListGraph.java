package ca.ubc.ece.cpen221.mp3.graph;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    
    private List<LinkedList<Integer>> adjacencyList = new LinkedList<LinkedList<Integer>>();
    private List<Integer> vertexList = new LinkedList<Integer>();
    
    
    public void addVertex(Vertex v) {
        
        adjacencyList.add(new LinkedList<Integer>());
        vertexList.add(v.hashCode());
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        adjacencyList.get(vertexList.indexOf(v1.hashCode())).add(v2.hashCode());
        
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
        
        if (adjacencyList.get(vertexList.indexOf(v1.hashCode())).contains(v2.hashCode())){
            return true;
        }
        return false;
        
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        List<Vertex> downstreamNeighboursList = new LinkedList<Vertex>();
        
        int index = vertexList.indexOf(v.hashCode());
                 
        for (Integer vertex : adjacencyList.get(index)){
            
            downstreamNeighboursList.add(new Vertex(vertex.toString()));    //defensive copy
        }
        
        return Collections.unmodifiableList(downstreamNeighboursList);
    }


    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        LinkedList<Vertex> upstreamNeighboursList = new LinkedList<Vertex>();
        
        for (List<Integer> vertices : adjacencyList) {
            for (Integer vertex : vertices) {
                if (v.equals(vertex)) {
                    upstreamNeighboursList.add(v);
                }
            }
        }
        
        return upstreamNeighboursList;
    }


    public List<Vertex> getVertices() {
        
        List<Vertex> returnList = new LinkedList<Vertex>();
        
        for (Integer entry : vertexList){
            returnList.add(new Vertex(entry.toString()));
        }
        return returnList; //TODO: defensive copying? 
    }
}
