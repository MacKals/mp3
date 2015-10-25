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
        adjacencyList.getLast().add(v);
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        for (int i = 0; i < adjacencyList.size(); i++){
           
           //we found the vertex v1, and we don't have this edge already, then add it
            if (adjacencyList.get(i).get(0).equals(v1)){ 
                
                adjacencyList.get(i).add(v2); //add the edge
                break;
            }
        }
        
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {
        
        for (int i = 0; i < adjacencyList.size(); i++){
            
            if (adjacencyList.get(i).get(0).equals(v1)){
                
                for (int i2 = 1; i2 < adjacencyList.get(i).size(); i2++){
                    
                    if ( adjacencyList.get(i).get(i2).equals(v2)){
                        return true;
                    }
                }
            }
        }
        
        return false; 
        
    }


    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        LinkedList<Vertex> downstreamNeighboursList = new LinkedList<Vertex>();
        
        for (int i = 0; i < adjacencyList.size(); i++){
            if (adjacencyList.get(i).get(0).equals(v)){
                
                
                for (int i2 = 1; i2 < adjacencyList.get(i).size(); i2++){
                    //defensive copy
                    downstreamNeighboursList.add(new Vertex(adjacencyList.get(i).get(i2).toString()));
                }
                
                break;
            }
        }
        
        return Collections.unmodifiableList(downstreamNeighboursList);
    }


    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        LinkedList<Vertex> upstreamNeighboursList = new LinkedList<Vertex>();
        
        for (int i = 0; i < adjacencyList.size(); i++){
            
            for (int i2 = 1; i2 < adjacencyList.get(i).size(); i2++){
                
                if (adjacencyList.get(i).get(i2).equals(v)){
                  //defensive copy
                    upstreamNeighboursList.add(new Vertex(adjacencyList.get(i).get(0).toString()));
                }
                
            }
                
        }
        
        return upstreamNeighboursList;
    }


    public List<Vertex> getVertices() {
        LinkedList<Vertex> verticesList = new LinkedList<Vertex>();
        
        for (int i = 0; i < adjacencyList.size(); i++){
          //defensive copy
            verticesList.add(new Vertex(adjacencyList.get(i).get(0).toString())); 
        }
        return verticesList;
    }
}
