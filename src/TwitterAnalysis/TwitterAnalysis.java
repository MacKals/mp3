package TwitterAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TwitterAnalysis {

    public static void main(String[] args){
        
        System.out.print("Started");

        File file = new File("datasets/twitter.txt");
        Graph ourGraph = fileToGraph(file);
        
        System.out.print("Hurra!");
    }
    
    private static Graph fileToGraph(File file){
        Graph buildGraph = new AdjacencyMatrixGraph();
        
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                
                String url = sc.nextLine().trim();
                int i = url.indexOf("->");
                
                String string1 = url.substring(0,i-1);
                String string2 = url.substring(i+2);
                
                Vertex vertex1 = new Vertex(string1);
                Vertex vertex2 = new Vertex(string2);
                
                //adds the vertices to the graph if they don't exist already
                if (!existsInGraph(buildGraph, vertex1)){
                    buildGraph.addVertex(vertex1);
                }
                if (!existsInGraph(buildGraph, vertex2)){
                    buildGraph.addVertex(vertex2);
                }
                
                buildGraph.addEdge(vertex1, vertex2);
               
            }
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        return buildGraph;
       
    }
    
    private static boolean existsInGraph(Graph graph, Vertex v) {
        
        for ( Vertex element : graph.getVertices() ) {
            
            if (element.equals(v)) {
                return true;
            }
        }
        
        return false;
    }
}
