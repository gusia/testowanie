package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;

import edu.uci.ics.jung.graph.Graph;

public class EdgePairCoverage<N, E> implements TestRequirementInt<N, E> {

	@Override
	public List<List<N>> getRequirement(Graph<N, E> graph) {
		List<List<N>> paths = new LinkedList<List<N>>();
		HashSet<List<N>> passedPaths = new HashSet<List<N>>();
		
		for (N node : graph.getVertices()) {
			for (N n1 : graph.getSuccessors(node)) {
				for (N n2 : graph.getSuccessors(n1)) {
					LinkedList<N> path = new LinkedList<N>();
					path.add(node);
					path.add(n1);
					path.add(n2);
					paths.add(path);
					
					passedPaths.add(list(node, n1));
					passedPaths.add(list(n1, n2));
					passedPaths.add(list(node));
					passedPaths.add(list(n1));
					passedPaths.add(list(n2));
				}
			}
		}
		
		for (List<N> path : new EdgeCoverage<N, E>().getRequirement(graph)) {
			if (!passedPaths.contains(path)) {
				paths.add(path);
			}
		}
		
		return paths;
	}

	private List<N> list(N n1, N n2) {
		LinkedList<N> list = new LinkedList<N>();
		list.add(n1);
		
		if (n2 != null) {
			list.add(n2);
		
		}
		return list;
	}
	
	private List<N> list(N n) {
		return list(n, null);
	}
}
