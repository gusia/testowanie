package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;

public class PrimePathsCoverage<N, E> implements TestRequirementInt<N, E> {

	private Graph<N, E> graph;

	public PrimePathsCoverage(Graph<N, E> graph) {
		this.graph = graph;
	}
	
	@Override
	public LinkedList<LinkedList<N>> getRequirement() {
		LinkedList<LinkedList<N>> tmpList = new LinkedList<LinkedList<N>>();
		LinkedList<LinkedList<N>> edgePaths = new NodeCoverage<N, E>(graph).getRequirement();
		LinkedList<LinkedList<N>> paths = new LinkedList<LinkedList<N>>();
				
		while (!edgePaths.isEmpty()) {
			for (LinkedList<N> path : edgePaths) {
				N first = path.getFirst();
				N last = path.getLast();
				Collection<N> successors = graph.getSuccessors(last);
				
				if (successors.size() == 0) {
					paths.add(path);
				}
				
				for (N succ : successors) {
					if (first == succ) {
						LinkedList<N> newPath = new LinkedList<N>(path);
						newPath.add(succ);
						paths.add(newPath);
					} else if (path.contains(succ)) {
						paths.add(path);
					} else {
						LinkedList<N> newPath = new LinkedList<N>(path);
						newPath.add(succ);
						tmpList.add(newPath);
					}
				}
				
			}
			
			edgePaths = tmpList;
			tmpList = new LinkedList<LinkedList<N>>();
		}
		
		LinkedList<LinkedList<N>> primePaths = new LinkedList<LinkedList<N>>();
		
		for (LinkedList<N> p1 : paths) {
			if (p1.size() == 1) {
				continue;
			}
			
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
