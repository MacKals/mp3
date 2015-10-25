package ca.ubc.ece.cpen221.mp3.graph;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    
    private List<LinkedList<Vertex>> adjacencyList = new LinkedList<LinkedList<Vertex>>();
    private List<Vertex> vertexList = new LinkedList<Vertex>();
    
    
    public void addVertex(Vertex v) {
        
        adjacencyList.add(new LinkedList<Vertex>());
        vertexList.add(v);
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        adjacencyList.get(vertexList.indexOf(v1)).add(v2);
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
        
        if (adjacencyList.get(vertexList.indexOf(v1)).contains(v2)){
            return true;
        }
        return false;
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        List<Vertex> downstreamNeighboursList = new LinkedList<Vertex>();
        
        int index = vertexList.indexOf(v);
                 
        for (Vertex vertex : adjacencyList.get(index)){
            
            downstreamNeighboursList.add(new Vertex(vertex.toString()));    //defensive copy
        }
        
        return Collections.unmodifiableList(downstreamNeighboursList);
    }


    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        LinkedList<Vertex> upstreamNeighboursList = new LinkedList<Vertex>();
        
        for (List<Vertex> vertices : adjacencyList) {
            for (Vertex vertex : vertices) {
                if (v.equals(vertex)) {
                    upstreamNeighboursList.add(v);
                }
            }
        }
        
        return upstreamNeighboursList;
    }


    public List<Vertex> getVertices() {
        
        return vertexList; //TODO: defensive copying? 
    }
}
