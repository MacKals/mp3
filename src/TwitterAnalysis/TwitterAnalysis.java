package TwitterAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;


/** Performs an analysis on a dataset from Twitter.
 * @author erikmaclennan, mkals
 */
public class TwitterAnalysis {

    private static Scanner sc;
    
    private static String openingToken = "<result>";
    private static String closingToken = "</result>";
    
    private static String twitterFile = "datasets/twitter.txt";
    private static String simpleFile = "datasets/SimpleDataset.txt";
    private static String mediumFile = "datasets/MediumDataset.txt";

    
    public static void main(String[] args){
        
        File file = new File(mediumFile);
        Graph graph = fileToGraph(file);
        
        System.out.println("File loaded");
        
        //Vertex a = new Vertex("10416"); //fill in string
        //Vertex b = new Vertex("37722200"); //fill in string

        
        Vertex a = new Vertex("1"); //fill in string
        Vertex b = new Vertex("5"); //fill in string
        
        int retweets = Algorithms.shortestDistance(graph, a, b);
        printnumRetweets(retweets, a, b);
        
        List<Vertex> commonInfluencers = Algorithms.commonDownstreamVertices(graph, a, b);
        printCommonInfluencers(commonInfluencers, a, b);
        
    }
    
    /** Prints to the console each of the users that are followed by two specified users.
     *  Prints each user identifier on a new line, surrounded by html "result" tags
     * @param result a list of all common influencers. Each user is represented by a vertex object
     * @param v1 a vertex corresponding to a Twitter user.
     * @param v2 another vertex corresponding to a Twitter user.
     */
    private static void printCommonInfluencers(List<Vertex> result, Vertex v1, Vertex v2) {
        
        System.out.println("query: commonInfluencers " + v1.toString() + " " + v2.toString()); 
        System.out.println(openingToken);
               
        for (Vertex element : result) {
            System.out.println(element.toString());
        }

        System.out.println(closingToken);
        
    }
    
    /** Prints to the console the minimum number of retweets needed before v1's tweet appears in v2's feed.
     * This integer is surrounded by html "result" tags.
     * @param result an integer representing the minimum number of retweets
     * @param v1 first user.    
     * @param v2 second user.
     */
    private static void printnumRetweets(int result, Vertex v1, Vertex v2) {
        
        System.out.println("query: numRetweets " + v1.toString() + " " + v2.toString()); 
        System.out.println(openingToken);
        System.out.println(result);
        System.out.println(closingToken);
        
    }
    
    /** Reads data from a file and returns a Graph object representation that represents a Directed Graph.
     * The file must be contain two user identifiers on each line, separated by an "->".
     * @param file the file to read from.
     * @return A graph that represents the data from the file as a Directed Graph.
     */
    private static Graph fileToGraph(File file){
        
        Graph buildGraph = new AdjacencyListGraph();
        
        /*
         * In order to determine whether the vertex has been added to graph previously we here 
         * use an external representation of the added vertices
         * A better solution would be to handle this internally, but the interface makes this impossible. 
         */
        Set<String> addedVertices = new TreeSet<String>();
                
        int j = 0;
        
        try{
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                
                if ( j % 100 == 0 ) {
                    System.out.println(j);
                }
                
                j++;
                
                String url = sc.nextLine().trim();
                int i = url.indexOf(" -> ");
                
                //generate substrings from line in file
                String string1 = url.substring(0,i);
                String string2 = url.substring(i+4);
                                
                //generate vertices from strings
                Vertex vertex1 = new Vertex(string1);
                Vertex vertex2 = new Vertex(string2);
                
                //adds the vertices to the graph if they don't exist already
                if ( !addedVertices.contains(string1) ) {
                    buildGraph.addVertex(vertex1);
                    addedVertices.add(string1);
                }
                if ( !addedVertices.contains(string2) ){
                    buildGraph.addVertex(vertex2);
                    addedVertices.add(string2);
                }
                
                buildGraph.addEdge(vertex1, vertex2);
                
            }
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        return buildGraph;
       
    }
}