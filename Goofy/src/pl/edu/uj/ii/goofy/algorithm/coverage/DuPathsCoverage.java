package pl.edu.uj.ii.goofy.algorithm.coverage;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.DuDescriptionType;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.Graph;

public class DuPathsCoverage implements TestRequirementInt<Node, Edge> {

	private Graph<Node, Edge> graph;
	private HashSet<LinkedList<Node>> paths;
	private Hashtable<Short, LinkedList<DuDescription>> nodeUsages;
	
	public DuPathsCoverage(Graph<Node, Edge> graph) {
		this.graph = graph;
		this.paths = new HashSet<LinkedList<Node>>();
		
		nodeUsages = new Hashtable<Short, LinkedList<DuDescription>>();
		for (Node node : graph.getVertices()) {
			nodeUsages.put(node.getId(), node.getUsages());
		}
	}
	
	@Override
	public LinkedList<LinkedList<Node>> getRequirement() {
		for (Node begin : graph.getVertices()) {
			int descriptionIndex = 0;
			for (DuDescription duDescription : begin.getUsages()) {
				if (duDescription.getType() == DuDescriptionType.Definition) {
					
					// nodes
					for (Node end : graph.getVertices()) {
						int duDescriptionEndIndex = 0;
						for (DuDescription duDescriptionEnd : end.getUsages()) {
							if (duDescriptionEnd.getName() == duDescription.getName()) {
								if (begin != end && duDescriptionEnd.getType() == DuDescriptionType.Definition) {
									break;
								}
								
								if (duDescriptionEnd.getType() == DuDescriptionType.Usage
									&& ((begin != end) || (begin == end && duDescriptionEndIndex < descriptionIndex))) {
									findDuPath(duDescription.getName(), begin, end, false);
								}
							}
							
							++duDescriptionEndIndex;
						}
					}
					
					// edges
					for (Edge end : graph.getEdges()) {
						for (DuDescription duDescriptionEnd : end.getUsages()) {
							if (duDescriptionEnd.getName() == duDescription.getName()) {
								findDuPath(duDescription.getName(), begin, graph.getEndpoints(end).getSecond(), true);
							}
						}
					}
					
				}
				++descriptionIndex;
			}
		}
		
		return new LinkedList<LinkedList<Node>>(paths);
	}
	
	private void findDuPath(String name, Node begin, Node end, boolean edge) {
		LinkedList<Node> beginPath = new LinkedList<Node>();
		beginPath.add(begin);
		findSimplePaths(name, beginPath, end, edge);
	}

	private void findSimplePaths(String name, LinkedList<Node> begin, Node end, boolean edge) {
		for (Node succ : graph.getSuccessors(begin.getLast())) {
			if ((!begin.contains(succ) || end.equals(succ))
					&& 
					(end.equals(begin.getFirst()) || !containsDefinitionBeforeUse(nodeUsages.get(succ.getId()), name)
							|| (edge && succ.equals(end))
					)
				) {
				LinkedList<Node> newPath = new LinkedList<Node>(begin);
				newPath.add(succ);
				findSimplePaths(name, newPath, end, edge);
			}
		}
		
		if (end.equals(begin.getLast()) && begin.size() > 1) {
			paths.add(begin);
		}
	}
	
	private boolean containsDefinitionBeforeUse(LinkedList<DuDescription> list, String name) { 
		for (DuDescription desc : list) {
			if (desc.getName() == name) {
				if (desc.getType() == DuDescriptionType.Definition) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}
}
