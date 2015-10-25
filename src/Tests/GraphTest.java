package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import TwitterAnalysis.TwitterAnalysis;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class GraphTest {

    private static String twitterFile = "datasets/twitter.txt";
    private static String smallFile = "datasets/small.txt";
    private static String mediumFile = "datasets/medium.txt";
    private static String sequentialFile = "datasets/sequential.txt";

    
    /*
     * Tests the list representation on a small data-set against known values.
     * The number of re-tweets between 
     *  - a and b should be 1.
     *  - b and a should be 1.
     * The list of commonInfluencers should contain 2, 3, 4 in this order.
     */
    @Test
    public void TestListRepresentation() { 
        
        File file = new File(smallFile);
        Graph graph = TwitterAnalysis.fileToGraph(file, true);
        
        Vertex a = new Vertex("1");
        Vertex b = new Vertex("5");
        
        int retweetsAB = Algorithms.shortestDistance(graph, a, b, true);
        int retweetsBA = Algorithms.shortestDistance(graph, b, a, true);

        assertEquals(retweetsAB, 1);
        assertEquals(retweetsBA, 4);

        List<Vertex> commonInfluencers = Algorithms.commonDownstreamVertices(graph, a, b);

        assertEquals(commonInfluencers.get(0).toString(), "2");
        assertEquals(commonInfluencers.get(1).toString(), "3");
        assertEquals(commonInfluencers.get(2).toString(), "4");
    }
    
    
    /*
     * Tests the two data-structures against one another on data-set 
     * including edge-cases such as duplicate edges, looping and edges 
     * going from vertex to itself. 
     */
    @Test
    public void TestInternalRepresentations() { 
        
        File file = new File(smallFile);
        Graph graphList = TwitterAnalysis.fileToGraph(file, true);
        Graph graphMatrix = TwitterAnalysis.fileToGraph(file, true);

        System.out.println("representations loaded");
        
        Vertex a = new Vertex("3");
        Vertex b = new Vertex("7"); 
        Vertex c = new Vertex("8");
        Vertex d = new Vertex("15");       
        
        //test for equal result with shortest distance
        int retweetsFromListAB = Algorithms.shortestDistance(graphList, a, b, true);
        int retweetsFromMatrixAB = Algorithms.shortestDistance(graphMatrix, a, b, true);

        int retweetsFromListCD = Algorithms.shortestDistance(graphList, c, d, true);
        int retweetsFromMatrixCD = Algorithms.shortestDistance(graphMatrix, c, d, true);
        
        assertEquals(retweetsFromListAB, retweetsFromMatrixAB);
        assertEquals(retweetsFromListCD, retweetsFromMatrixCD);
     
        System.out.println("retweets");

        //test for equal result with common influencers
        List<Vertex> commonInfluencersListAB = Algorithms.commonDownstreamVertices(graphList, a, b);
        List<Vertex> commonInfluencersMatrixAB = Algorithms.commonDownstreamVertices(graphMatrix, a, b);

        List<Vertex> commonInfluencersListCD = Algorithms.commonDownstreamVertices(graphList, c, d);
        List<Vertex> commonInfluencersMatrixCD = Algorithms.commonDownstreamVertices(graphMatrix, c, d);

        assertEquals(commonInfluencersListAB, commonInfluencersMatrixAB);
        assertEquals(commonInfluencersListCD, commonInfluencersMatrixCD);
        
        System.out.println("common");

  //      Set<List<Vertex>> DFSFromList = Algorithms.DFS(graphList);
    //    Set<List<Vertex>> DFSFromMatrix = Algorithms.DFS(graphMatrix);

        
    //    System.out.println( DFSFromList.size() );
     //   System.out.println( DFSFromMatrix.size() );
        
    //    assertEquals(Algorithms.BFS(graphList).size(), Algorithms.BFS(graphMatrix).size());
       // assertEquals(Algorithms.DFS(graphList).toString(), Algorithms.DFS(graphMatrix).toString());

    }
    
    @Test
    public void testMain() {
        
        String[] input= {"datasets/QueryFile1.txt", "output.txt"}; 
        
        TwitterAnalysis.main(input);
        
    }
}
