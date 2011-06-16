package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.Hashtable;
import java.util.LinkedList;

import edu.uci.ics.jung.graph.Graph;

public class DuPathsCoverage<N, E> implements TestRequirementInt<N, E> {
	
	private Hashtable<N, LinkedList<DuDescription>> duNode;
	private Hashtable<E, LinkedList<DuDescription>> duEdge;
	private Graph<N, E> graph;
	private LinkedList<LinkedList<N>> paths;

	public DuPathsCoverage(Graph<N, E> graph, Hashtable<N, LinkedList<DuDescription>> duNode, Hashtable<E, LinkedList<DuDescription>> duEdge) {
		this.graph = graph;
		this.duNode = duNode;
		this.duEdge = duEdge;
		this.paths = new LinkedList<LinkedList<N>>();
	}
	
	@Override
	public LinkedList<LinkedList<N>> getRequirement() {
		for (N begin : duNode.keySet()) {
			int descriptionIndex = 0;
			for (DuDescription duDescription : duNode.get(begin)) {
				if (duDescription.getType() == DuDescriptionType.Definition) {
					
					// nodes
					for (N end : duNode.keySet()) {
						int duDescriptionEndIndex = 0;
						for (DuDescription duDescriptionEnd : duNode.get(begin)) {
							if (duDescriptionEnd.getName() == duDescription.getName()
								&& (begin != end || duDescriptionEndIndex > descriptionIndex)) {
								
								findDuPath(duDescription.getName(), begin, end);
							}
							
							++duDescriptionEndIndex;
						}
					}
					
					// edges
					for (E end : duEdge.keySet()) {
						for (DuDescription duDescriptionEnd : duNode.get(begin)) {
							if (duDescriptionEnd.getName() == duDescription.getName()) {
								findDuPath(duDescription.getName(), begin, graph.getEndpoints(end).getSecond());
							}
						}
					}
					
				}
				++descriptionIndex;
			}
		}
		
		return paths;
	}
	
	private void findDuPath(String name, N begin, N end) {
		LinkedList<N> beginPath = new LinkedList<N>();
		beginPath.add(begin);
		findSimplePaths(beginPath, end);
	}

	private void findSimplePaths(LinkedList<N> begin, N end) {

		for (N succ : graph.getSuccessors(begin.getLast())) {
			if (!begin.contains(succ) || succ == end) {
				LinkedList<N> newPath = new LinkedList<N>(begin);
				newPath.add(succ);
				findSimplePaths(newPath, end);
			}
		}
		
		if (end == begin.getLast()) {
			paths.add(begin);
		}
	}
}
