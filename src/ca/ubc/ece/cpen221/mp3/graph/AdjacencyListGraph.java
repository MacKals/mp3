package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    
    private List<HashSet<String>> adjacencyList = new LinkedList<HashSet<String>>();
    private List<String> vertexList = new LinkedList<String>();
    
    
    public void addVertex(Vertex v) {
        
        if (!vertexList.contains(v)){
            adjacencyList.add(new HashSet<String>());
            vertexList.add(v.getLabel());
        } 
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        adjacencyList.get(vertexList.indexOf(v1.getLabel())).add(v2.getLabel());
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
       
        if (adjacencyList.get(vertexList.indexOf(v1.getLabel())).contains(v2.getLabel())){

            return true;
        }
        return false;
        
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        List<Vertex> downstreamNeighboursList = new ArrayList<Vertex>();
        
        int index = vertexList.indexOf(v.getLabel());
                 
        for (String vertex : adjacencyList.get(index)){
            downstreamNeighboursList.add(new Vertex(vertex));    //defensive copy
        }
        return downstreamNeighboursList;
    }


    public List<Vertex> getUpstreamNeighbors(Vertex vertex) {
        
        List<Vertex> upstreamNeighboursList = new ArrayList<Vertex>();
        
        for (int i = 0; i < vertexList.size(); i++) {
            if (adjacencyList.get(i).contains(vertex.getLabel())) {
                upstreamNeighboursList.add( new Vertex(vertexList.get(i)));
            }
        }

        return upstreamNeighboursList;
    }


    public List<Vertex> getVertices() {
        
        List<Vertex> returnList = new LinkedList<Vertex>();
        
        for (String entry : vertexList){
            returnList.add(new Vertex(entry));
        }
        return returnList; 
    }
}
