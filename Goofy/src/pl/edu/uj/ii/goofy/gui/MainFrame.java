package pl.edu.uj.ii.goofy.gui;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.collections15.Transformer;

import pl.edu.uj.ii.goofy.MultiMap;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import pl.edu.uj.ii.goofy.algorithm.coverage.EdgeCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.EdgePairCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.NodeCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.PrimePathsCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.TestRequirementInt;
import pl.edu.uj.ii.goofy.algorithm.testpaths.TestPathGenerator;
import pl.edu.uj.ii.goofy.algorithm.testpaths.Touring;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 740477122217644965L;
	private List<Node> wierzcholkiPoczatkowe = new LinkedList<Node>();
	private List<Node> wierzcholkiKoncowe = new LinkedList<Node>();
	private JPanel contentPane;
	private DirectedSparseGraph<Node, Edge> graf;
	//private Layout<Node, Edge> layout;
	//BasicVisualizationServer<String,Integer> vv;
	GraphZoomScrollPane panel;
	VisualizationViewer<Node, Edge> vv;
	private JComboBox comboBox;
	private JList list;
	private JList list_1;
	private JCheckBox chckbxNewCheckBox;
	
	
	public List<Node> getWierzcholkiPoczatkowe() {
		return wierzcholkiPoczatkowe;
	}


	public List<Node> getWierzcholkiKoncowe() {
		return wierzcholkiKoncowe;
	}


	public DirectedSparseGraph<Node, Edge> getGraf() {
		return graf;
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		graf = new DirectedSparseGraph<Node, Edge>();
		//layout = new CircleLayout<String, Integer>(graf);
		//layout.setSize(new Dimension(300, 400));
		vv = new VisualizationViewer<Node, Edge> (new KKLayout<Node, Edge>(graf));//new BasicVisualizationServer<String, Integer>(layout);
		//this.pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100:100:100,fill][100px:100px:100px,fill][100px:100px:100px,fill][100px:100px:100px,fill][::700,grow]", "[fill][][][][][][grow][]"));
		//test();
		JButton btnDodajWierzchoki = new JButton("Dodaj wierzchołki");
		btnDodajWierzchoki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajWierzcholki();
			}
		});
		contentPane.add(btnDodajWierzchoki, "cell 0 0 4 1");
		
		JButton btnWierzchokiPocztkowekocowe = new JButton(
				"Oznacz wierzchołki początkowe/końcowe");
		btnWierzchokiPocztkowekocowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oznaczPoczatkoweKoncoweWierzcholki();			
			}
		});
		
		panel = new GraphZoomScrollPane(vv);
		panel.setIgnoreRepaint(false);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Node>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	
		
		contentPane.add(btnWierzchokiPocztkowekocowe, "cell 0 1 4 1");
		contentPane.add(panel,"cell 4 0 1 8,grow");

		JButton btnDodajKrawdzie = new JButton("Dodaj krawędzie");
		btnDodajKrawdzie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajKrawedzie();
			}
		});
		contentPane.add(btnDodajKrawdzie, "cell 0 2 4 1");
		
		JLabel lblNewLabel = new JLabel("Kryterium:");
		contentPane.add(lblNewLabel, "cell 0 3,alignx trailing");
		
		comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 1 3 2 1,growx");
		comboBox.setModel(new DefaultComboBoxModel());
		dodajDoComboBox();
		
		JButton btnNewButton = new JButton("Pokaż");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pokazWymaganiaISciezki();
			}
		});
		contentPane.add(btnNewButton, "cell 3 3,growx");
		
		chckbxNewCheckBox = new JCheckBox("ścieżki poboczne/objazdy");
		contentPane.add(chckbxNewCheckBox, "cell 0 4 4 1");
		
		JLabel lblNewLabel_1 = new JLabel("Wymagania testowe:");
		contentPane.add(lblNewLabel_1, "cell 0 5 2 1");
		
		JLabel lblNewLabel_2 = new JLabel("Ścieżki testowe:");
		contentPane.add(lblNewLabel_2, "cell 2 5 2 1");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 6 2 1,grow");
		

		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				try {
					selectPath((LinkedList<Node>) ((DefaultListModel)list.getModel()).get(list.getSelectedIndex()));
				} catch (Exception ex) { }
			}
		});
		list.setModel(new DefaultListModel());

		scrollPane.setViewportView(list);
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 6 2 1,grow");
		

		list_1 = new JList();
		list_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				try {
					selectPath((LinkedList<Node>) ((DefaultListModel)list_1.getModel()).get(list_1.getSelectedIndex()));
				} catch (Exception ex) { }
			}
		});
		list_1.setModel(new DefaultListModel());
		scrollPane_1.setViewportView(list_1);
	}

	void selectPath(final LinkedList<Node> path) {
		Transformer<Node, Paint> vertexPaint = new Transformer<Node, Paint>() {

			@Override
			public Paint transform(Node node) {
				if (path.contains(node)) {
					return Color.GREEN;
				} else {
					return Color.RED;
				}
			}
			
		};
		
		Transformer<Edge, Paint> edgePaint = new Transformer<Edge, Paint>() {

			@Override
			public Paint transform(Edge edge) {
				Pair<Node> e = graf.getEndpoints(edge);
				Node n1 = e.getFirst();
				Node n2 = e.getSecond();
				
				if (path.contains(n1) && path.contains(n2)) {
					return Color.GREEN;
				} else {
					return Color.BLACK;
				}
			}
			
		};
		
		vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.repaint();
	}


	void dodajWierzcholki() {
		WierzcholkiFrame wf = new WierzcholkiFrame(this);
		wf.setModal(true);
		wf.setVisible(true);
		panel.doLayout();
		panel.repaint();
		
	}

	void dodajKrawedzie() {
		KrawedzieFrame kf = new KrawedzieFrame(this);
		kf.setModal(true);
		kf.setVisible(true);
		panel.doLayout();
		panel.repaint();
		
		//panel.setVisible(true);
	}
	
	void oznaczPoczatkoweKoncoweWierzcholki(){
		PoczatkoweFrame pf = new PoczatkoweFrame(this);
		pf.setVisible(true);
		pf.setModal(true);				
	}
	
	void test (){
//		graf.addVertex("a");
//		graf.addVertex("b");
//		graf.addVertex("c");
//		graf.addVertex("d");
//		
//		EdgeIdGenerator eig = EdgeIdGenerator.getInstance();
//		
//		graf.addEdge(eig.getId(), "a","b");
//		graf.addEdge(eig.getId(), "d","c");
//		graf.addEdge(eig.getId(), "b","c");
//		graf.addEdge(eig.getId(), "d","a");
//		graf.addEdge(eig.getId(), "d","d");
//		vv.repaint();
//		vv.setVisible(true);
	}
	
	void dodajDoComboBox(){
		((DefaultComboBoxModel)comboBox.getModel()).addElement("Wierzchołkowe");
		((DefaultComboBoxModel)comboBox.getModel()).addElement("Krawędziowe");
		((DefaultComboBoxModel)comboBox.getModel()).addElement("Par krawędzi");
		((DefaultComboBoxModel)comboBox.getModel()).addElement("Ścieżki doskonałe");
	}
	
	void pokazWymaganiaISciezki(){
		String selectedItem = (String)((DefaultComboBoxModel)comboBox.getModel()).getSelectedItem();
		if (selectedItem == null) return;
		
		TestRequirementInt<Node, Edge> testRequirement;
		if (selectedItem == "Wierzchołkowe") {
			testRequirement = new NodeCoverage<Node, Edge>(graf);
		} else if (selectedItem == "Krawędziowe") {
			testRequirement = new EdgeCoverage<Node, Edge>(graf);
		} else if (selectedItem == "Par krawędzi") {
			testRequirement = new EdgePairCoverage<Node, Edge>(graf);
		} else if (selectedItem == "Ścieżki doskonałe") {
			testRequirement = new PrimePathsCoverage<Node, Edge>(graf);
		} else {
			return;
		}
		
		LinkedList<LinkedList<Node>> paths = testRequirement.getRequirement();

		DefaultListModel model = (DefaultListModel)list.getModel();
		model.clear();
		for (LinkedList<Node> path : paths) {
			model.addElement(path);
		}
		
		Touring touring = chckbxNewCheckBox.isSelected() ? Touring.SidetripsAndDetours : Touring.OnlyTouring;
		TestPathGenerator<Node, Edge> gen = new TestPathGenerator<Node, Edge>(graf, wierzcholkiPoczatkowe, wierzcholkiKoncowe, touring);
		gen.getAllPaths();
		MultiMap<LinkedList<Node>, LinkedList<Node>> map = gen.reducePaths(paths);
		
		model = (DefaultListModel)list_1.getModel();
		model.clear();
		for (LinkedList<Node> path : map.keySet()) {
			model.addElement(path);
		}
	}

}
