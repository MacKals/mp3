package ca.ubc.ece.cpen221.mp3.graph;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
    
    private List<LinkedList<String>> adjacencyList = new LinkedList<LinkedList<String>>();
    private List<String> vertexList = new LinkedList<String>();
    
    
    public void addVertex(Vertex v) {
        
        if (!vertexList.contains(v)){
            adjacencyList.add(new LinkedList<String>());
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
        
        List<Vertex> downstreamNeighboursList = new LinkedList<Vertex>();
        
        System.out.print("List of vertices in AdjacencyList:" + vertexList);
        
        int index = vertexList.indexOf(v.getLabel());
                 
        for (String vertex : adjacencyList.get(index)){
            downstreamNeighboursList.add(new Vertex(vertex));    //defensive copy
        }
        System.out.println("List of downstream neighbours to " + v +  " in AdjacencyList:" + downstreamNeighboursList);
        return Collections.unmodifiableList(downstreamNeighboursList);
    }


    public List<Vertex> getUpstreamNeighbors(Vertex vertex) {
        
        List<Vertex> upstreamNeighboursList = new LinkedList<Vertex>();
        
        for (int i = 0; i < vertexList.size(); i++) {
            if (adjacencyList.contains(vertex)) {
                upstreamNeighboursList.add( new Vertex(vertexList.get(i)));
            }
        }
        System.out.println("List of upstream neighbours to " + vertex + " in AdjacencyList: " + upstreamNeighboursList);
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
