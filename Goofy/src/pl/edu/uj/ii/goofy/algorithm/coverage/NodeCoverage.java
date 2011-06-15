package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;

public class NodeCoverage<N, E> implements TestRequirementInt<N, E> {

	@Override
	public List<List<N>> getRequirement(Graph<N, E> graph) {
		List<List<N>> paths = new LinkedList<List<N>>();
		
		for (N node : graph.getVertices()) {
			LinkedList<N> path = new LinkedList<N>();
			path.add(node);
			paths.add(path);
		}
		
		return paths;
	}
}
