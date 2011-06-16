package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.LinkedList;

import edu.uci.ics.jung.graph.Graph;

public class NodeCoverage<N, E> implements TestRequirementInt<N, E> {

	private Graph<N, E> graph;

	public NodeCoverage(Graph<N, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public LinkedList<LinkedList<N>> getRequirement() {
		LinkedList<LinkedList<N>> paths = new LinkedList<LinkedList<N>>();
		
		for (N node : graph.getVertices()) {
			LinkedList<N> path = new LinkedList<N>();
			path.add(node);
			paths.add(path);
		}
		
		return paths;
	}
}
