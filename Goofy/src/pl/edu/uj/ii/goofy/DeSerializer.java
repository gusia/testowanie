package pl.edu.uj.ii.goofy;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.DuDescriptionType;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class DeSerializer {
	private String path;
	private Graph<Node, Edge> graph;
	private LinkedList<Node> startNodes;
	private LinkedList<Node> endNodes;

	public DeSerializer(String path) {
		this.path = path;
		this.graph = new DirectedSparseGraph<Node, Edge>();
		this.startNodes = new LinkedList<Node>();
		this.endNodes = new LinkedList<Node>();
	}

	public void load() throws Exception {
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList graphNodes = doc.getElementsByTagName("graph");
		if (graphNodes.getLength() != 1) {
			throw new Exception();
		}

		org.w3c.dom.Node graphNode = graphNodes.item(0);
		NodeList graphChildNodes = graphNode.getChildNodes();
		for (int i = 0; i < graphChildNodes.getLength(); ++i) {
			org.w3c.dom.Node node = graphChildNodes.item(i);
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element element = (Element) node;

				String name = element.getNodeName();
				if (name == "nodes") {
					readNodes(element);
				} else if (name == "edges") {
					readEdges(element);
				}
			}
		}
	}

	public Graph<Node, Edge> getGraph() {
		return graph;
	}
	
	public LinkedList<Node> getStartNodes() {
		return startNodes;
	}
	
	public LinkedList<Node> getEndNodes() {
		return endNodes;
	}

	private void readNodes(Element e) {
		NodeList childNodes = e.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			org.w3c.dom.Node node = childNodes.item(i);
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element element = (Element)node;
				short id = Short.parseShort(element.getAttribute("id"));
				LinkedList<DuDescription> list = readDuDesc(element);
				Node n = new Node(id, list);
				graph.addVertex(n);
				
				if (element.hasAttribute("start")) {
					startNodes.add(n);
				}
				
				if (element.hasAttribute("end")) {
					endNodes.add(n);
				}
			}
		}
	}

	private void readEdges(Element e) {
		NodeList childNodes = e.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			org.w3c.dom.Node node = childNodes.item(i);
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element element = (Element)node;
				short from = Short.parseShort(element.getAttribute("from"));
				short to = Short.parseShort(element.getAttribute("to"));
				LinkedList<DuDescription> list = readDuDesc(element);
				graph.addEdge(new Edge(from, to, list), new Node(from), new Node(to));
			}
		}
	}

	private LinkedList<DuDescription> readDuDesc(Element e) {
		LinkedList<DuDescription> list = new LinkedList<DuDescription>();

		NodeList childNodes = e.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			org.w3c.dom.Node node = childNodes.item(i);
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.getNodeName() == "dudesc") {
					String name = element.getChildNodes().item(0).toString();
					String typeName = element.getAttribute("type");
					DuDescriptionType type = Enum.valueOf(
							DuDescriptionType.class, typeName);
					list.add(new DuDescription(name, type));
				}
			}
		}

		return list;
	}
}
