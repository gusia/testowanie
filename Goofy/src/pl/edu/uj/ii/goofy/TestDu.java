package pl.edu.uj.ii.goofy;

import java.util.Hashtable;
import java.util.LinkedList;

import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.DuDescriptionType;
import pl.edu.uj.ii.goofy.algorithm.coverage.DuPathsCoverage;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class TestDu {

	public static void main(String[] args) {
		Graph<String, Integer> graph = new DirectedSparseGraph<String, Integer>();
		
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addVertex("5");
		graph.addVertex("6");
		graph.addVertex("7");
		graph.addVertex("8");
		
		graph.addEdge(0, "1", "2");
		graph.addEdge(1, "2", "3");
		graph.addEdge(2, "3", "4");
		graph.addEdge(3, "3", "5");
		graph.addEdge(4, "4", "3");
		graph.addEdge(5, "3", "5");
		graph.addEdge(6, "5", "6");
		graph.addEdge(7, "6", "7");
		graph.addEdge(8, "6", "8");
		graph.addEdge(9, "7", "6");
		
		Hashtable<String, LinkedList<DuDescription>> nodes = new Hashtable<String, LinkedList<DuDescription>>();
		DuDescription description;
		LinkedList<DuDescription> list;
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("numbers", DuDescriptionType.Definition);
		description = new DuDescription("sum", DuDescriptionType.Definition);
		description = new DuDescription("length", DuDescriptionType.Definition);
		nodes.put("1", list);
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("i", DuDescriptionType.Definition);
		nodes.put("2", list);
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("i", DuDescriptionType.Definition);
		description = new DuDescription("sum", DuDescriptionType.Definition);
		description = new DuDescription("sum", DuDescriptionType.Usage);
		description = new DuDescription("numbers", DuDescriptionType.Usage);
		description = new DuDescription("i", DuDescriptionType.Usage);
		nodes.put("4", list);
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("med", DuDescriptionType.Definition);
		description = new DuDescription("mead", DuDescriptionType.Definition);
		description = new DuDescription("varsum", DuDescriptionType.Definition);
		description = new DuDescription("i", DuDescriptionType.Definition);
		description = new DuDescription("sum", DuDescriptionType.Usage);
		description = new DuDescription("numbers", DuDescriptionType.Usage);
		description = new DuDescription("length", DuDescriptionType.Usage);
		nodes.put("5", list);
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("varsum", DuDescriptionType.Definition);
		description = new DuDescription("i", DuDescriptionType.Definition);
		description = new DuDescription("varsum", DuDescriptionType.Usage);
		description = new DuDescription("numbers", DuDescriptionType.Usage);
		description = new DuDescription("i", DuDescriptionType.Usage);
		description = new DuDescription("mean", DuDescriptionType.Usage);
		nodes.put("7", list);
		
		list = new LinkedList<DuDescription>();
		description = new DuDescription("var", DuDescriptionType.Definition);
		description = new DuDescription("sd", DuDescriptionType.Definition);
		description = new DuDescription("varsum", DuDescriptionType.Usage);
		description = new DuDescription("length", DuDescriptionType.Usage);
		description = new DuDescription("mean", DuDescriptionType.Usage);
		description = new DuDescription("med", DuDescriptionType.Usage);
		description = new DuDescription("var", DuDescriptionType.Usage);
		description = new DuDescription("sd", DuDescriptionType.Usage);
		nodes.put("8", list);
		
		LinkedList<LinkedList<String>> paths = new DuPathsCoverage<String, Integer>(graph, nodes, new Hashtable<Integer, LinkedList<DuDescription>>()).getRequirement();
		
		for (LinkedList<String> path : paths) {
			System.out.println(path);
		}
	}

}
