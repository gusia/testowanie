package pl.edu.uj.ii.goofy;

import org.jgraph.JGraph;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;

public class EdgeColorTest implements JGraphTestInterface {

	@Override
	public void go() {
		// create a JGraphT graph
		ListenableGraph<String, DefaultWeightedEdge> g = new ListenableDirectedMultigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		// create a visualization using JGraph, via an adapter
		JGraphModelAdapter<String, DefaultWeightedEdge> jgAdapter = new JGraphModelAdapter<String, DefaultWeightedEdge>(g);

		JGraph jgraph = new JGraph(jgAdapter);

//		adjustDisplaySettings(jgraph);
//		getContentPane().add(jgraph);
//		resize(DEFAULT_SIZE);

		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";

		// add some sample data (graph manipulated via JGraphT)
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);

		g.addEdge(v1, v2);
		g.addEdge(v2, v3);
		g.addEdge(v3, v1);
		g.addEdge(v4, v3);
		
		System.out.println(g.toString());
	}

	private static class ListenableDirectedMultigraph<V, E> extends
			DefaultListenableGraph<V, E> implements DirectedGraph<V, E> {
		private static final long serialVersionUID = 1L;

		ListenableDirectedMultigraph(Class<E> edgeClass) {
			super(new DirectedMultigraph<V, E>(edgeClass));
		}
	}
}
