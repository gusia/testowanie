package pl.edu.uj.ii.goofy.algorithm.testpaths;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;


public class TestPathGenerator<N,E> {
	public TestPathGenerator(Graph<N, E> graph, List<N> start, List<N> end) {
		this.graph = graph;
		this.startNodes = start;
		this.endNodes = end;
		paths = new LinkedList<LinkedList<N>>();
	}
	
	public LinkedList<LinkedList<N>> getPaths() {
		for (N startNode : startNodes) {
			LinkedList<N> startPath = new LinkedList<N>();
			startPath.add(startNode);
			findPaths(startPath);
		}
		
		return paths;
	}
	
	private void findPaths(LinkedList<N> begin) {

		for (N succ : graph.getSuccessors(begin.getLast())) {
			if (!begin.contains(succ)) {
				LinkedList<N> newPath = new LinkedList<N>(begin);
				newPath.add(succ);
				findPaths(newPath);
			}
		}
		
		if (endNodes.contains(begin.getLast())) {
			paths.add(begin);
		}
	}
	
	private LinkedList<LinkedList<N>> paths;
	private Graph<N, E> graph;
	private List<N> startNodes;
	private List<N> endNodes;
}
