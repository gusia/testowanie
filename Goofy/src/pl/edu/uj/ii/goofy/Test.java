package pl.edu.uj.ii.goofy;

import java.util.List;

import pl.edu.uj.ii.goofy.algorithm.coverage.EdgeCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.EdgePairCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.PrimePathsCoverage;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class Test {
	
	public static void main(String[] args) {
		Graph<Integer, Integer> graph = new DirectedSparseGraph<Integer, Integer>();
		
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);
		
		graph.addEdge(1, 0, 1);
		graph.addEdge(2, 1, 2);
		graph.addEdge(3, 2, 1);
		graph.addEdge(4, 2, 0);
//		graph.addEdge(5, 4, 5);
//		graph.addEdge(6, 5, 6);
//		graph.addEdge(7, 6, 0);
//		graph.addEdge(8, 4, 6);
//		graph.addEdge(9, 5, 6);
		
		
		List<List<Integer>> paths = new PrimePathsCoverage<Integer, Integer>().getRequirement(graph);
		
		for (List<Integer> path : paths) {
			for (Integer node : path) {
				System.out.print(String.format("%d, ", node));
			}
			System.out.println();
		}
	}

}
