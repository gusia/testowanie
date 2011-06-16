package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.HashSet;
import java.util.LinkedList;

import edu.uci.ics.jung.graph.Graph;

public class EdgeCoverage<N, E> implements TestRequirementInt<N, E> {

	public EdgeCoverage(Graph<N, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public LinkedList<LinkedList<N>> getRequirement() {
		LinkedList<LinkedList<N>> paths = new LinkedList<LinkedList<N>>();
		HashSet<N> passedNodes = new HashSet<N>();
		
		for (N node : graph.getVertices()) {
			for (N neighbour : graph.getSuccessors(node)) {
				LinkedList<N> path = new LinkedList<N>();
				path.add(node);
				path.add(neighbour);
				paths.add(path);
				
				passedNodes.add(node);
				passedNodes.add(neighbour);
			}
		}
		
		for (N node : graph.getVertices()) {
			if (!passedNodes.contains(node)) {
				LinkedList<N> path = new LinkedList<N>();
				path.add(node);
				paths.add(path);
			}
		}
		
		return paths;
	}

	private Graph<N, E> graph;
}
