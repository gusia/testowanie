package pl.edu.uj.ii.goofy;

import java.util.LinkedList;
import java.util.List;

import pl.edu.uj.ii.goofy.algorithm.coverage.PrimePathsCoverage;
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
		
		graph.addEdge(0, 0, 1);
		graph.addEdge(1, 0, 4);
		graph.addEdge(2, 1, 2);
		graph.addEdge(3, 1, 5);
		graph.addEdge(5, 2, 3);
		graph.addEdge(6, 3, 1);
		graph.addEdge(7, 4, 4);
		graph.addEdge(8, 4, 6);
		graph.addEdge(9, 5, 6);
		
//		graph.addVertex(0);
//		graph.addVertex(1);
//		graph.addVertex(2);
//		
//		graph.addEdge(0, 0, 1);
//		graph.addEdge(1, 1, 2);
//		graph.addEdge(2, 2, 0);
		
		List<List<Integer>> requirements = new PrimePathsCoverage<Integer, Integer>().getRequirement(graph);
		LinkedList<Integer> startNodes = new LinkedList<Integer>();
		LinkedList<Integer> endNodes = new LinkedList<Integer>();
		startNodes.add(0);
		endNodes.add(6);
		
		TestPathGenerator<Integer, Integer> tpg = new TestPathGenerator<Integer, Integer>(graph, startNodes, endNodes, true);
		LinkedList<LinkedList<Integer>> testPaths = tpg.getAllPaths();
		
		System.out.println("requirements:");
		for (List<Integer> path : requirements) {
			for (Integer node : path) {
				System.out.print(String.format("%d, ", node));
			}
			System.out.println();
		}
		
		System.out.println("testPaths:");
		for (List<Integer> path : testPaths) {
			for (Integer node : path) {
				System.out.print(String.format("%d, ", node));
			}
			System.out.println();
		}
		
		MultiMap<LinkedList<Integer>, List<Integer>> reqPath = tpg.reducePaths(requirements);
		
		System.out.println("reqPaths:");
		for (LinkedList<Integer> key : reqPath.keySet()) {
			System.out.print(key);
			System.out.println(": ");
			
			
			for (List<Integer> path : reqPath.getValues(key)) {
				System.out.print("\t");
				System.out.println(path);
			}
		}
	}
}
