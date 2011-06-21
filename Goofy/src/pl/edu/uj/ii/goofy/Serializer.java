package pl.edu.uj.ii.goofy;

import java.io.File;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

public class Serializer {
	
	private Graph<Node, Edge> graph;
	private Collection<Node> startNodes;
	private Collection<Node> endNode;
	private String path;

	public Serializer(Graph<Node, Edge> graph, Collection<Node> startNodes, Collection<Node> endNodes, String path) {
		this.graph = graph;
		this.startNodes = startNodes;
		this.endNode = endNodes;
		this.path = path;
	}
	
	public void save() throws ParserConfigurationException, TransformerException {
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		  Document doc = docBuilder.newDocument();
		  
		  Element xmlGraph = doc.createElement("graph");
		  doc.appendChild(xmlGraph);
		  
		  Element xmlNodes = doc.createElement("nodes");
		  xmlGraph.appendChild(xmlNodes);
		  for (Node node : graph.getVertices()) {
			  addNode(node, doc, xmlNodes, true);
		  }
		  
		  Element xmlEdges = doc.createElement("edges");
		  xmlGraph.appendChild(xmlEdges);
		  for (Edge edge : graph.getEdges()) {
			  addEdge(edge, doc, xmlEdges);
		  }
		  
//		  Element xmlStartNodes = doc.createElement("startNodes");
//		  xmlGraph.appendChild(xmlStartNodes);
//		  for (Node node : startNodes) {
//			  addNode(node, doc, xmlStartNodes, false);
//		  }
//		  
//		  Element xmlEndNodes = doc.createElement("endNodes");
//		  xmlGraph.appendChild(xmlEndNodes);
//		  for (Node node : endNode) {
//			  addNode(node, doc, xmlEndNodes, false);
//		  }
		  
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		  DOMSource source = new DOMSource(doc);
		  StreamResult result =  new StreamResult(new File(path));
		  transformer.transform(source, result);
	}
	
	private void addNode(Node node, Document doc, Element parent, boolean withDuDesc) {
		Element xmlNode = doc.createElement("node");
		xmlNode.setAttribute("id", Short.toString(node.getId()));
		if (startNodes.contains(node)) {
			xmlNode.setAttribute("start", "1");
		}
		if (endNode.contains(node)) {
			xmlNode.setAttribute("end", "1");
		}
		
		if (withDuDesc) {
			for (DuDescription duDesc : node.getUsages()) {
				addDuDesc(duDesc, doc, xmlNode);
			}
		}
		
		parent.appendChild(xmlNode);
	}
	
	private void addEdge(Edge edge, Document doc, Element parent) {
		Element xmlNode = doc.createElement("edge");
		Pair<Node> endpoints = graph.getEndpoints(edge);
		xmlNode.setAttribute("from", Short.toString(endpoints.getFirst().getId()));
		xmlNode.setAttribute("to", Short.toString(endpoints.getSecond().getId()));
		
		for (DuDescription duDesc : edge.getUsages()) {
			addDuDesc(duDesc, doc, xmlNode);
		}
		
		parent.appendChild(xmlNode);
	}
	
	private void addDuDesc(DuDescription duDesc, Document doc, Element parent) {
		Element xmlDesc = doc.createElement("dudesc");
		xmlDesc.setAttribute("type", duDesc.getType().toString());
		xmlDesc.appendChild(doc.createTextNode(duDesc.getName()));
		parent.appendChild(xmlDesc);
	}
}
