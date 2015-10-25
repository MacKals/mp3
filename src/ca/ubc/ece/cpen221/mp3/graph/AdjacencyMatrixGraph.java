package ca.ubc.ece.cpen221.mp3.graph;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {

    
    //matrix of mappings from element in outer array to element in inner array (from first dimension to second)
    List<ArrayList<Boolean>> matrix = new ArrayList<ArrayList<Boolean>>();
    List<String> vertices = new ArrayList<String>();
    
    public void addVertex(Vertex v) {
        
        //add one element to each 2. dimension ArrayList to get a (n,n+1) matrix 
        for (int i = 0; i < vertices.size(); i++) {
            matrix.get(i).add(false);
        }
        
        //add Vertex to vector
        vertices.add(v.getLabel());
        
        //add final row to matrix to get a (n+1,n+1) square matrix
        matrix.add( new ArrayList<Boolean>( Collections.nCopies(vertices.size(), false) ) );
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        int v1Index = vertices.indexOf(v1.getLabel());
        int v2Index = vertices.indexOf(v2.getLabel());
        
        matrix.get(v1Index).set(v2Index, true);
        
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {

        int v1Index = vertices.indexOf(v1.getLabel());
        int v2Index = vertices.indexOf(v2.getLabel());
        
        return matrix.get(v1Index).get(v2Index);  
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        List<Vertex> down = new LinkedList<Vertex>();
        
        int vIndex = vertices.indexOf(v.getLabel());
        
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(vIndex).get(i)) {
                down.add(new Vertex( vertices.get(i) ));     //defensive copying                
            }
        }
        
        System.out.println("getDownstreamNeighbors from matrix: " + down);
        
        return down;
    }

    public List<Vertex> getUpstreamNeighbors(Vertex v) {
       
        List<Vertex> up = new LinkedList<Vertex>();
        
        int vIndex = vertices.indexOf(v.getLabel());
        
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(i).get(vIndex)) {
                up.add(new Vertex( vertices.get(i).toString() ));    //defensive copying
            }
        }
        
        System.out.println("getUpstreamNeighbors from matrix: " + up);
        
        return up;
    }

    public List<Vertex> getVertices() {    
        
        List<Vertex> returnList = new ArrayList<Vertex>();
        
        for (String label : vertices) {
            returnList.add(new Vertex(label));
        }
        
        return returnList;  
        
    }
    
}
