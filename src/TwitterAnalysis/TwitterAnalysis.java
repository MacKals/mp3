package TwitterAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
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
    
    public static void main(String[] args){
        
        //get files from input
        File queriesFile = new File(args[0]);
        File outputFile = new File(args[1]);
        
        //get input queries
        List<Query> queries = Query.getQueriesFromFile(queriesFile);
        
        System.out.println("queries read"); 
       
        //generate graph
        File file = new File(twitterFile);
        Graph graph = fileToGraph(file, true);
        
        System.out.println("graph generated"); 

        //generate output
        String output = new String();
        
        for (Query query : queries) {
            
            try {

                switch (query.getQuery()) {
                
                case CommonInfluencers:
                    List<Vertex> commonInfluencers = Algorithms.commonDownstreamVertices(graph, query.getA(), query.getB());
                    output += printCommonInfluencers(commonInfluencers, query.getA(), query.getB());
                    break;
                
                case NumRetweets: 
                    int retweets = Algorithms.shortestDistance(graph, query.getA(), query.getB(), true);
                    output += printNumRetweets(retweets, query.getA(), query.getB());
                    break;
                }
            } finally {
                if (query.hasQuestinmark()) {
                    continue;
                }              
            }
        }
        
        System.out.println("queries performed with result: " + output); 

        
        //print file
        try {
            PrintWriter out = new PrintWriter(outputFile);
            out.println(output);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
    }
    
    private static class Query {
        
        public enum QueryType {
            CommonInfluencers("commonInfluencers"), NumRetweets("numRetweets");
            
            private String description;
            QueryType(String description) {
                this.description=description;
            }

            public String getDescription() {
                return description;
            }
                    
        }
        
        private QueryType query;
        private Vertex vertexA;
        private Vertex vertexB;
        private boolean hasQuestionmark;
        
        public Vertex getA() {
            return vertexA;
        }
        
        public Vertex getB() {
            return vertexB;
        }
        
        public QueryType getQuery() {
            return query;
        }
        
        public boolean hasQuestinmark() {
            if (hasQuestionmark) return true;
            return false;
        }
        
        private static List<Query> getQueriesFromFile(File file) {
            
            //<query type> <user a> <user b>
            
            List<Query> returnList = new ArrayList<Query>();
            
            try{
                sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    
                    //get indices of string
                    String url = sc.nextLine().trim();
                    int firstSpace = url.indexOf(" ");
                    int secondSpace = url.indexOf(" ", firstSpace + 1);
                    int questionMark = url.indexOf("?");
                    
                    Query queryInstnace = new Query();
                    
                    //get substrings and store to queryInstance as appropriate data-types
                    String queryString = url.substring(0, firstSpace);
                    if (queryString.equals( Query.QueryType.CommonInfluencers.getDescription() )) {
                        queryInstnace.query = Query.QueryType.CommonInfluencers;
                    } else {
                        queryInstnace.query = Query.QueryType.NumRetweets;
                    }
                    
                    queryInstnace.vertexA = new Vertex(url.substring(firstSpace, secondSpace));
                    
                    if (questionMark == -1) {
                        queryInstnace.hasQuestionmark = false;
                        queryInstnace.vertexA = new Vertex(url.substring(secondSpace));
                    } else {
                        queryInstnace.hasQuestionmark = true;
                        //-1 to get to last index, -2 due to ending question-mark and space
                        queryInstnace.vertexB = new Vertex(url.substring(secondSpace, url.length() - 3));  
                    }
                    
                    returnList.add(queryInstnace);
                    
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            return returnList;            
                 
        }
        
    }
    
    
    
    /** Prints to the console each of the users that are followed by two specified users.
     *  Prints each user identifier on a new line, surrounded by html "result" tags
     * @param result a list of all common influencers. Each user is represented by a vertex object
     * @param v1 a vertex corresponding to a Twitter user.
     * @param v2 another vertex corresponding to a Twitter user.
     */
    private static String printCommonInfluencers(List<Vertex> result, Vertex v1, Vertex v2) {
       
        String returnString = new String();
        returnString += "query: commonInfluencers " + v1.toString() + " " + v2.toString() + "\n";
        returnString += openingToken + "\n";
               
        for (Vertex element : result) {
            returnString += element.toString() + "\n";
        }

        returnString += closingToken + "\n";
        
        return returnString;
    }
    
    /** Prints to the console the minimum number of retweets needed before v1's tweet appears in v2's feed.
     * This integer is surrounded by html "result" tags.
     * @param result an integer representing the minimum number of retweets
     * @param v1 first user.    
     * @param v2 second user.
     */
    private static String printNumRetweets(int result, Vertex v1, Vertex v2) {
        
        String returnString = new String();
        
        returnString = "query: numRetweets " + v1.toString() + " " + v2.toString() + "\n"; 
        returnString = openingToken + "\n";
        returnString = result + "\n";
        returnString = closingToken + "\n";
        
        return returnString;
    }
    
    /** Reads data from a file and returns a Graph object representation that represents a Directed Graph.
     * The file must be contain two user identifiers on each line, separated by an "->".
     * @param file the file to read from.
     * @return A graph that represents the data from the file as a Directed Graph.
     */
    public static Graph fileToGraph(File file, boolean adjacencyList){
        
        //determine whether to use list or matrix for internal representation
        Graph buildGraph;
        if (adjacencyList) buildGraph = new AdjacencyListGraph();
        else buildGraph = new AdjacencyMatrixGraph();
        
        /*
         * In order to determine whether the vertex has been added to graph previously we here 
         * use an external representation of the added vertices
         * A better solution would be to handle this internally, but the interface makes this impossible. 
         */
        Set<Integer> addedVertices = new HashSet<Integer>();
        
        int j = 0;
        
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                
                if (j % 1000 == 0) {
                    System.out.println(j + ", "); 
                    
                }
                
                j++;
                
                String url = sc.nextLine();//.trim();
                int i = url.indexOf(" -> ");
                
                //generate substrings from line in file
                String string1 = url.substring(0,i);
                String string2 = url.substring(i+4);
                                
                //generate vertices from strings
                Vertex vertex1 = new Vertex(string1);
                Vertex vertex2 = new Vertex(string2);
                
                //adds the vertices to the graph if they don't exist already
                if (! addedVertices.contains(string1.hashCode()) ) {
                    buildGraph.addVertex(vertex1);
                    addedVertices.add(string1.hashCode());
                }
                if (! addedVertices.contains(string2.hashCode()) ){
                    buildGraph.addVertex(vertex2);
                    addedVertices.add(string2.hashCode());
                }
                
                buildGraph.addEdge(vertex1, vertex2);
                
            }
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        return buildGraph;
       
    }
}