package pl.edu.uj.ii.goofy;

import java.util.LinkedList;
import java.util.List;

import pl.edu.uj.ii.goofy.algorithm.testpaths.TestPathGenerator;
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
		
		graph.addEdge(0, 0, 1);
		graph.addEdge(1, 0, 4);
		graph.addEdge(2, 1, 2);
		graph.addEdge(3, 1, 5);
		graph.addEdge(4, 2, 3);
		graph.addEdge(5, 3, 6);
		graph.addEdge(6, 4, 4);
		graph.addEdge(7, 4, 6);
		graph.addEdge(8, 5, 6);
		
		
//		List<List<Integer>> paths = new PrimePathsCoverage<Integer, Integer>().getRequirement(graph);
		LinkedList<Integer> startNodes = new LinkedList<Integer>();
		LinkedList<Integer> endNodes = new LinkedList<Integer>();
		startNodes.add(0);
		startNodes.add(1);
		endNodes.add(6);
		
		LinkedList<LinkedList<Integer>> paths = new TestPathGenerator<Integer, Integer>(graph, startNodes, endNodes).getPaths();
		
		for (List<Integer> path : paths) {
			for (Integer node : path) {
				System.out.print(String.format("%d, ", node));
			}
			System.out.println();
		}
	}

}
