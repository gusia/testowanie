package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.HashSet;
import java.util.LinkedList;

import edu.uci.ics.jung.graph.Graph;

public class EdgePairCoverage<N, E> implements TestRequirementInt<N, E> {

	private Graph<N, E> graph;

	public EdgePairCoverage(Graph<N, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public LinkedList<LinkedList<N>> getRequirement() {
		LinkedList<LinkedList<N>> paths = new LinkedList<LinkedList<N>>();
		HashSet<LinkedList<N>> passedPaths = new HashSet<LinkedList<N>>();
		
		for (N node : graph.getVertices()) {
			for (N n1 : graph.getSuccessors(node)) {
				for (N n2 : graph.getSuccessors(n1)) {
					LinkedList<N> path = new LinkedList<N>();
					path.add(node);
					path.add(n1);
					path.add(n2);
					paths.add(path);
					
					passedPaths.add(LinkedList(node, n1));
					passedPaths.add(LinkedList(n1, n2));
					passedPaths.add(LinkedList(node));
					passedPaths.add(LinkedList(n1));
					passedPaths.add(LinkedList(n2));
				}
			}
		}
		
		for (LinkedList<N> path : new EdgeCoverage<N, E>(graph).getRequirement()) {
			if (!passedPaths.contains(path)) {
				paths.add(path);
			}
		}
		
		return paths;
	}

	private LinkedList<N> LinkedList(N n1, N n2) {
		LinkedList<N> LinkedList = new LinkedList<N>();
		LinkedList.add(n1);
		
		if (n2 != null) {
			LinkedList.add(n2);
		
		}
		return LinkedList;
	}
	
	private LinkedList<N> LinkedList(N n) {
		return LinkedList(n, null);
	}
}
