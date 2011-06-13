package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.List;

import edu.uci.ics.jung.graph.Graph;

public interface TestRequirementInt<N, E> {
	public List<List<N>> getRequirement(Graph<N, E> graph);
}
