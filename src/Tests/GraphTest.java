package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import TwitterAnalysis.TwitterAnalysis;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class GraphTest {

    private static String smallFile = "datasets/small.txt";
    private static String mediumFile = "datasets/medium.txt";

    /*
     * In general: 
     * Functionality for importing and interpreting vertices is collected from TwitterAnalysis, 
     * as is the methods assiciated with the graphs such as addVertex, get upstream and downstream 
     * neighbours etc. They are thus implicitly tested trough the various test cases.
     * 
     * The test cases below thus mainly focus on testing all the Algorythms methods
     *  -> if they evaluate to produce expected result, the other parts of the program 
     *  will also be working as expected.
     */
    

    
    /*
     * Tests all the Algorythms methods against known values on a small data-set.
     * Uses two sets of vertecies to test between, both of which should highlite edge-case behaviour
     * in the graph:
     *  - b is sparse branch in graph
     *  - a, c and d are all in dense part of graph
     */
    @Test
    public void TestAlgorythmsRepresentations() { 
        
        File file = new File(smallFile);
        Graph graph = TwitterAnalysis.fileToGraph(file, true);
        
        Vertex a = new Vertex("1");
        Vertex b = new Vertex("4");
        Vertex c = new Vertex("5");
        Vertex d = new Vertex("2");
        
        
        //test shortest distance
        int shortastDistanceAB = Algorithms.shortestDistance(graph, a, b, false);
        int shortastDistanceDownstreamOnlyAB = Algorithms.shortestDistance(graph, a, b, true);

        int shortastDistanceCD = Algorithms.shortestDistance(graph, c, d, false);
        int shortastDistanceDownstreamOnlyCD = Algorithms.shortestDistance(graph, c, d, true);
        
        assertEquals(shortastDistanceAB, 2);
        assertEquals(shortastDistanceDownstreamOnlyAB, -1);
        
        assertEquals(shortastDistanceCD, 1);
        assertEquals(shortastDistanceDownstreamOnlyCD, 1);
        
        
        //testing common upstream and downstream vertices
        List<Vertex> commonUpstreamAB = Algorithms.commonUpstreamVertices(graph, a, b);
        List<Vertex> commonUpstreamCD = Algorithms.commonUpstreamVertices(graph, c, d);
        
        List<Vertex> commonDownstreamAB = Algorithms.commonDownstreamVertices(graph, a, b);
        List<Vertex> commonDownstreamCD = Algorithms.commonDownstreamVertices(graph, c, d);
        
        List<Vertex> commonDownstreamABAnswer = new ArrayList<Vertex>();
        commonDownstreamABAnswer.add(new Vertex("5"));

        List<Vertex> commonDownstreamCDAnswer = new ArrayList<Vertex>();
        commonDownstreamCDAnswer.add(new Vertex("2"));
        commonDownstreamCDAnswer.add(new Vertex("3"));

        assertEquals(commonUpstreamAB.isEmpty(), true);
        assertEquals(commonUpstreamCD.get(0).getLabel(), "1");
        
        assertEquals(commonDownstreamAB, commonDownstreamABAnswer);
        assertEquals(commonDownstreamCD, commonDownstreamCDAnswer);

        
        //testing BFS
        //instead of making new test-set of vertecies, we make test-set of ints and perform element-wise comparison
        Set<List<Vertex>> BFSResult = Algorithms.BFS(graph);
        int[][] BFSAnswerArray = {{2, 5, 4, 3, 1}, {5, 4, 3, 2, 1}, {4, 5, 3, 2, 1}, {3, 5, 4, 1, 2}, {1, 3, 5, 4, 2}};
        List<List<Vertex>> BFSAnswer = convertToSet(BFSAnswerArray);
        
        //loops through set and removes matches, at end all should have been removed if all are found
        for (List<Vertex> list : BFSResult) {
            if (BFSAnswer.contains(list)) {                
                BFSAnswer.remove(list);
                continue;
            }
            assert(false); //should allways find match
        }
        assert(BFSAnswer.isEmpty());
        
        
        //testing DFS
        Set<List<Vertex>> DFSResult = Algorithms.DFS(graph);
        int[][] DFSAnswerArray = {{3, 1, 5, 2, 4}, {2, 3, 1, 5, 4}, {4, 5, 2, 3, 1}, {1, 2, 3, 5, 4}, {5, 2, 3, 1, 4}};
        List<List<Vertex>> DFSAnswer = convertToSet(DFSAnswerArray);
        
        //loops through set and removes matches, at end all should have been removed if all are found
        for (List<Vertex> list : DFSResult) {
            if (DFSAnswer.contains(list)) {                
                DFSAnswer.remove(list);
                continue;
            }
            assert(false); //should always find match
        }
        assert(DFSAnswer.isEmpty());
    }
    
    List<List<Vertex>> convertToSet(int[][] array) {
        
        List<List<Vertex>> returnSet = new ArrayList<List<Vertex>>();
        
        
        for (int i = 0; i < array.length; i++) {
            
            List<Vertex> list = new ArrayList<Vertex>();

            for (int j = 0; j < array[0].length; j++) {
                list.add(new Vertex( String.valueOf(array[i][j])) );
            }
            
            returnSet.add(list);
        }
        
        return returnSet;
        
    }
    
    /*
     * Tests the two graph-structures against one another on a data-set 
     * including edge-cases such as duplicate edges, looping and edges 
     * going from vertex to itself. All public methods are tested for equal result.
     */
    @Test
    public void TestInternalRepresentations() { 
        
        File file = new File(mediumFile);
        Graph graphList = TwitterAnalysis.fileToGraph(file, true);
        Graph graphMatrix = TwitterAnalysis.fileToGraph(file, false);
        
        Vertex a = new Vertex("1");
        Vertex b = new Vertex("3"); 
        Vertex c = new Vertex("4");
        Vertex d = new Vertex("5");       
  /*
        Vertex a = new Vertex("3");
        Vertex b = new Vertex("7"); 
        Vertex c = new Vertex("8");
        Vertex d = new Vertex("15");       
  */
        
        //test for equal result with shortest distance
        int retweetsFromListAB = Algorithms.shortestDistance(graphList, a, b, true);
        int retweetsFromMatrixAB = Algorithms.shortestDistance(graphMatrix, a, b, true);

        int retweetsFromListCD = Algorithms.shortestDistance(graphList, c, d, true);
        int retweetsFromMatrixCD = Algorithms.shortestDistance(graphMatrix, c, d, true);
        
        assertEquals(retweetsFromListAB, retweetsFromMatrixAB);
        assertEquals(retweetsFromListCD, retweetsFromMatrixCD);
        
        System.out.println("retweets");
        
        //test for equal result with common influences
        List<Vertex> commonInfluencersListAB = Algorithms.commonDownstreamVertices(graphList, a, b);
        List<Vertex> commonInfluencersMatrixAB = Algorithms.commonDownstreamVertices(graphMatrix, a, b);

        List<Vertex> commonInfluencersListCD = Algorithms.commonDownstreamVertices(graphList, c, d);
        List<Vertex> commonInfluencersMatrixCD = Algorithms.commonDownstreamVertices(graphMatrix, c, d);
        
        assertEquals(commonInfluencersListAB, commonInfluencersMatrixAB);
        assertEquals(commonInfluencersListCD, commonInfluencersMatrixCD);
        
        //compare BFS and DFS results
        assertEquals(Algorithms.BFS(graphList), Algorithms.BFS(graphMatrix));
        assertEquals(Algorithms.DFS(graphList), Algorithms.DFS(graphMatrix));
    }
    
    
    /*
     * Tests the list representation on a small data-set against known values.
     * If explicitly focuses on the directionality of the graph representation and assisted methods. 
     * The number of re-tweets between 
     *  - a and b should be 1.
     *  - b and a should be 2.
     */
    @Test
    public void TestListRepresentation() { 
        
        File file = new File(smallFile);
        Graph graph = TwitterAnalysis.fileToGraph(file, false);
        
        Vertex a = new Vertex("1");
        Vertex b = new Vertex("5");
        
        int retweetsAB = Algorithms.shortestDistance(graph, a, b, true);
        int retweetsBA = Algorithms.shortestDistance(graph, b, a, true);

        assertEquals(retweetsAB, 1);
        assertEquals(retweetsBA, 2);
    }
    
    /*
     * Tests main method and associated reading and generation of queries,
     * reading and interpretation of the twitter graph and output to file.
     * Twitter-file is so large we cannot be sure what the answer to our test-
     * query is supposed to be, so a visual inspection of the output file is 
     * performed to assure it looks reasonable. The real test here will be
     * performed by the CPEN team.
     */
    @Test
    public void testMain() {
        
        String[] input= {"datasets/QueryFile1.txt", "datasets/output.txt"}; 
        
        TwitterAnalysis.main(input);
        
    }
}
