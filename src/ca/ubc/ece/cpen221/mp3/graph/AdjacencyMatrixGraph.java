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
    List<Vertex> vertices = new ArrayList<Vertex>();
    
    public void addVertex(Vertex v) {
        
        //add one element to each 2. dimension ArrayList to get a (n,n+1) matrix 
        for (int i = 0; i < vertices.size(); i++) {
            matrix.get(i).add(false);
        }
        
        //add Vertex to vector
        vertices.add(v);
        
        //add final row to matrix to get a (n+1,n+1) square matrix
        matrix.add( new ArrayList<Boolean>( Collections.nCopies(vertices.size(), false) ) );
    }

    public void addEdge(Vertex v1, Vertex v2) {
        
        Vertex[] vertexArray= {v1, v2};
        int[] indecies = indexOfVertices( vertexArray );
        
        matrix.get(indecies[0]).set(indecies[1], true);
    }

    public boolean edgeExists(Vertex v1, Vertex v2) {

        Vertex[] vertexArray= {v1, v2};
        int[] indecies = indexOfVertices( vertexArray );
        
        return matrix.get(indecies[0]).get(indecies[1]);  
    }

    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        
        List<Vertex> down = new LinkedList<Vertex>();
        
        Vertex[] vertexArray = {v};
        int[] indices = indexOfVertices( vertexArray );
        int vertexIndex = indices[0];
        
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(vertexIndex).get(i)) {
                down.add(vertices.get(i));
            }
        }
        
        return down;
    }

    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        List<Vertex> up = new LinkedList<Vertex>();
        
        Vertex[] vertexArray = {v};
        int[] indices = indexOfVertices( vertexArray );
        int vertexIndex = indices[0];
        
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(i).get(vertexIndex)) {
                up.add(vertices.get(i));
            }
        }
        
        return up;
    }

    public List<Vertex> getVertices() {    
        
        return Collections.unmodifiableList(vertices);  
        
    }
    
    private int[] indexOfVertices( Vertex[] vertexArray ) {
        
        int[] indices = new int[ vertexArray.length ];
        
        for ( int i = 0; i < vertices.size(); i++ ) {
            for ( int j = 0; j < vertexArray.length; j++ ) {
                if (vertices.get(i).equals(vertexArray[j])) {
                    indices[j] = i;
                }
            }
            
        }        
        
        return indices;
        
    } 
}
