package TwitterAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TwitterAnalysis {

    private static Scanner sc;
    
    private static String openingTolken = "<result>";
    private static String closingTolken = "</result>";
    

    public static void main(String[] args){
        
        System.out.print("Started");

        File file = new File("datasets/twitter.txt");
        Graph graph = fileToGraph(file);
        
        
        Vertex a = new Vertex("14838508"); //fill in string
        Vertex b = new Vertex("98032178"); //fill in string
        
        
        List<Vertex> commonInfluencers = commonInfluencers(graph, a, b);
        printCommonInfluencers(commonInfluencers, a, b);
        
        int retweets = minRetweetsForAToAppearInB(graph, a, b);
        printnumRetweets(retweets, a, b);
        
    }
    

    private static void printCommonInfluencers(List<Vertex> result, Vertex v1, Vertex v2) {
        
        System.out.println("query: commonInfluencers " + v1.toString() + " " + v2.toString()); 
        System.out.println(openingTolken);
               
        for (Vertex element : result) {
            System.out.println(element.toString());
        }

        System.out.println(closingTolken);
        
    }
    
    private static void printnumRetweets(int result, Vertex v1, Vertex v2) {
        
        System.out.println("query: numRetweets " + v1.toString() + " " + v2.toString()); 
        System.out.println(openingTolken);
        System.out.println(result);
        System.out.println(closingTolken);
        
    }
    
    private static Graph fileToGraph(File file){
        Graph buildGraph = new AdjacencyMatrixGraph();
        
        try{
            sc = new Scanner(file);
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
    
    private static List<Vertex> commonInfluencers(Graph graph, Vertex a, Vertex b) {
        
        
        return null;
    }
    
    private static int minRetweetsForAToAppearInB(Graph graph, Vertex a, Vertex b) {
        
        return -1;
    }
}
