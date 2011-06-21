package pl.edu.uj.ii.goofy;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.DuDescriptionType;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import pl.edu.uj.ii.goofy.algorithm.coverage.DuPathsCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.PrimePathsCoverage;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class TestDu {

	private static Graph<Node, Edge> graph;
	
	public static void main(String[] args) {
		LinkedList<DuDescription> l = new LinkedList<DuDescription>();
		l.add(new DuDescription("i", DuDescriptionType.Definition));
		Node n1 = new Node((short)1, l);
		LinkedList<Node> pat = new LinkedList<Node>();
	pat.add(n1);
		Collection<Node> path2 = Collections.unmodifiableCollection(pat);
		
		graph = new DirectedSparseGraph<Node, Edge>();

		LinkedList<DuDescription> list;
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("numbers", DuDescriptionType.Definition));
		list.add(new DuDescription("sum", DuDescriptionType.Definition));
		list.add(new DuDescription("length", DuDescriptionType.Definition));
		addNode(1, list);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Definition));
		addNode(2, list);
		
		addNode(3, new LinkedList<DuDescription>());
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("sum", DuDescriptionType.Usage));
		list.add(new DuDescription("sum", DuDescriptionType.Definition));
		list.add(new DuDescription("numbers", DuDescriptionType.Usage));
		list.add(new DuDescription("i", DuDescriptionType.Definition));
		addNode(4, list);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("med", DuDescriptionType.Definition));
		list.add(new DuDescription("mean", DuDescriptionType.Definition));
		list.add(new DuDescription("varsum", DuDescriptionType.Definition));
		list.add(new DuDescription("i", DuDescriptionType.Definition));
		list.add(new DuDescription("sum", DuDescriptionType.Usage));
		list.add(new DuDescription("numbers", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		addNode(5, list);
		
		addNode(6, new LinkedList<DuDescription>());
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("varsum", DuDescriptionType.Usage));
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("varsum", DuDescriptionType.Definition));
		list.add(new DuDescription("i", DuDescriptionType.Definition));
		list.add(new DuDescription("numbers", DuDescriptionType.Usage));
		list.add(new DuDescription("mean", DuDescriptionType.Usage));
		addNode(7, list);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("var", DuDescriptionType.Definition));
		list.add(new DuDescription("sd", DuDescriptionType.Definition));
		list.add(new DuDescription("varsum", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		list.add(new DuDescription("mean", DuDescriptionType.Usage));
		list.add(new DuDescription("med", DuDescriptionType.Usage));
		list.add(new DuDescription("var", DuDescriptionType.Usage));
		list.add(new DuDescription("sd", DuDescriptionType.Usage));
		addNode(8, list);	
		
		addEdge(1, 2);
		addEdge(2, 3);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		addEdge(3, 4, list);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		addEdge(3, 5, list);
		
		addEdge(4, 3);
		addEdge(5, 6);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		addEdge(6, 7, list);
		
		list = new LinkedList<DuDescription>();
		list.add(new DuDescription("i", DuDescriptionType.Usage));
		list.add(new DuDescription("length", DuDescriptionType.Usage));
		addEdge(6, 8, list);
		
		addEdge(7, 6);
		
		LinkedList<LinkedList<Node>> paths = new DuPathsCoverage(graph).getRequirement();
		
		for (LinkedList<Node> path : paths) {
			System.out.println(path);
		}
	}
	
	private static void addNode(int n, Collection<DuDescription> desc) {
		graph.addVertex(new Node((short) n, desc));
	}
	
	private static void addEdge(int a, int b) {
		graph.addEdge(new Edge((short) a, (short) b), new Node((short)a), new Node((short)b));
	}
	
	private static void addEdge(int a, int b, LinkedList<DuDescription> list) {
		graph.addEdge(new Edge((short) a, (short) b, list), new Node((short)a), new Node((short)b));
	}

}
