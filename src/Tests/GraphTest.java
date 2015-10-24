package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import TwitterAnalysis.TwitterAnalysis;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class GraphTest {

    private static String twitterFile = "datasets/twitter.txt";
    private static String simpleFile = "datasets/SimpleDataset.txt";
    private static String mediumFile = "datasets/MediumDataset.txt";
    
    @Test
    public void Test() { // want the list of commonInfluencers to contain 2, 3, 4 in this order.
        
        File file = new File(mediumFile);
        Graph graph = TwitterAnalysis.fileToGraph(file);
        
        System.out.println("File loaded");
        
        Vertex a = new Vertex("1"); //fill in string
        Vertex b = new Vertex("5"); //fill in string
        
        int retweets = Algorithms.shortestDistance(graph, a, b);
        
        assertEquals(retweets, 1);
        
        List<Vertex> commonInfluencers = Algorithms.commonDownstreamVertices(graph, a, b);
        
        assert(commonInfluencers.get(0).toString().equals("2"));
        assert(commonInfluencers.get(1).toString().equals("3"));
        assert(commonInfluencers.get(2).toString().equals("4"));
        
        // 
       
    }

}
