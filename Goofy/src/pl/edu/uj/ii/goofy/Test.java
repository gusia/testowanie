package pl.edu.uj.ii.goofy;

import java.util.LinkedList;
import java.util.List;

import pl.edu.uj.ii.goofy.algorithm.coverage.EdgeCoverage;
import pl.edu.uj.ii.goofy.algorithm.testpaths.TestPathGenerator;
import pl.edu.uj.ii.goofy.algorithm.testpaths.Touring;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class Test {
	
	public static void main(String[] args) {
		Graph<Integer, Integer> graph = new DirectedSparseGraph<Integer, Integer>();
		LinkedList<Integer> startNodes = new LinkedList<Integer>();
		LinkedList<Integer> endNodes = new LinkedList<Integer>();
		
//		graph.addVertex(0);
//		graph.addVertex(1);
//		graph.addVertex(2);
//		graph.addVertex(3);
//		graph.addVertex(4);
//		graph.addVertex(5);
//		graph.addVertex(6);
//		
//		graph.addEdge(0, 0, 1);
//		graph.addEdge(1, 0, 4);
//		graph.addEdge(2, 1, 2);
//		graph.addEdge(3, 1, 5);
//		graph.addEdge(5, 2, 3);
//		graph.addEdge(6, 3, 1);
//		graph.addEdge(7, 4, 4);
//		graph.addEdge(8, 4, 6);
//		graph.addEdge(9, 5, 6);
//		startNodes.add(0);
//		endNodes.add(6);
		
// ------------------------------------------------------------------
		
//		graph.addVertex(0);
//		graph.addVertex(1);
//		graph.addVertex(2);
//		graph.addVertex(3);
//		graph.addVertex(4);
//		
//		graph.addEdge(0, 0, 1);
//		graph.addEdge(1, 1, 3);
//		graph.addEdge(2, 3, 2);
//		graph.addEdge(5, 2, 1);
//		graph.addEdge(3, 2, 4);
//		graph.addEdge(4, 4, 3);
//		
//		startNodes.add(0);
//		endNodes.add(4);
		
// ---------------------------------------------------------------------
		
//		graph.addVertex(1);
//		graph.addVertex(2);
//		graph.addVertex(3);
//		graph.addVertex(4);
//		graph.addVertex(5);
//		
//		graph.addEdge(0, 1, 2);
//		graph.addEdge(1, 2, 3);
//		graph.addEdge(2, 3, 4);
//		graph.addEdge(3, 2, 5);
//		graph.addEdge(4, 5, 2);
//		graph.addEdge(5, 5, 3);
//		
//		startNodes.add(1);
//		endNodes.add(5);
		
// ---------------------------------------------------------------------
		
//		graph.addVertex(1);
//		graph.addVertex(2);
//		graph.addVertex(3);
//		graph.addVertex(4);
//		
//		graph.addEdge(0, 1, 1);
//		graph.addEdge(1, 1, 2);
//		graph.addEdge(2, 1, 3);
//		graph.addEdge(3, 1, 4);
//		
//		graph.addEdge(4, 2, 1);
//		graph.addEdge(5, 2, 2);
//		graph.addEdge(6, 2, 3);
//		graph.addEdge(7, 2, 3);
//	
//		graph.addEdge(8, 3, 1);
//		graph.addEdge(9, 3, 2);
//		graph.addEdge(10, 3, 3);
//		graph.addEdge(11, 3, 4);
//		
//		graph.addEdge(12, 3, 1);
//		graph.addEdge(13, 3, 2);
//		graph.addEdge(14, 3, 3);
//		graph.addEdge(15, 3, 4);
		
//		startNodes.add(1);
//		endNodes.add(4);
		
// ---------------------------------------------------
		
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		
		graph.addEdge(0, 1, 2);
		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 4);
		graph.addEdge(3, 4, 2);
		graph.addEdge(4, 2, 5);
		
		startNodes.add(1);
		endNodes.add(5);
		
// ---------------------------------------------------
		
		System.out.println(graph);
		
		LinkedList<LinkedList<Integer>> requirements = new EdgeCoverage<Integer, Integer>(graph).getRequirement();

		
		TestPathGenerator<Integer, Integer> tpg = new TestPathGenerator<Integer, Integer>(graph, startNodes, endNodes, Touring.OnlyTouring);
		LinkedList<LinkedList<Integer>> testPaths = tpg.getAllPaths();
		
		System.out.println("requirements:");
		for (List<Integer> path : requirements) {
			System.out.println(path);
		}
		
		System.out.println("testPaths:");
		for (List<Integer> path : testPaths) {
			System.out.println(path);
		}
		
		MultiMap<LinkedList<Integer>, LinkedList<Integer>> reqPath = tpg.reducePaths(requirements);
		
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
