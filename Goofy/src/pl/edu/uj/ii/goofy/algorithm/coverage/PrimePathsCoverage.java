package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;

public class PrimePathsCoverage<N, E> implements TestRequirementInt<N, E> {

	@Override
	public List<List<N>> getRequirement(Graph<N, E> graph) {
		List<List<N>> tmpList = new EdgeCoverage<N, E>().getRequirement(graph);
		List<List<N>> edgePaths = new LinkedList<List<N>>();
		LinkedList<List<N>> paths = new LinkedList<List<N>>();
		
		for (List<N> path : tmpList) {
			if (path.size() != 1) {
				edgePaths.add(path);
			}
		}
		tmpList.clear();
		
		while (!edgePaths.isEmpty()) {
			for (List<N> path : edgePaths) {
				N last = path.get(path.size() - 1);
				
				if (path.get(0) == last
					|| graph.getSuccessorCount(last) == 0) {
					
					paths.add(path);
				} else {
					for (N succ : graph.getSuccessors(last)) {
						if (!path.contains(succ) || path.get(0) == succ) {
							LinkedList<N> newPath = new LinkedList<N>(path);
							newPath.add(succ);
							tmpList.add(newPath);
						}
					}
				}
			}
			
			edgePaths = tmpList;
			tmpList = new LinkedList<List<N>>();
		}
		
		LinkedList<List<N>> primePaths = new LinkedList<List<N>>();
		
		for (List<N> p1 : paths) {
			boolean ok = true;
			
			for (List<N> p2 : paths) {
				if (p1 != p2 && isSubPath(p2, p1)) { 
					ok = false;
				}
			}
			
			if (ok) {
				primePaths.add(p1);
			}
		}
		
		return primePaths;
	}
	
	private boolean isSubPath(List<N> p1, List<N> p2) {
		if (p1.size() < p2.size()) {
			return false;
		}
		
		for (int i = 0; i < p1.size() - p2.size() + 1; ++i) {
			if (p1.subList(i, i + p2.size()).equals(p2)) {
				return true;
			}
		}
		
		return false;
	}
}
