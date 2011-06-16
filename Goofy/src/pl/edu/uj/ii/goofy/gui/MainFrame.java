package pl.edu.uj.ii.goofy.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.edu.uj.ii.goofy.EdgeIdGenerator;
import pl.edu.uj.ii.goofy.MultiMap;
import pl.edu.uj.ii.goofy.algorithm.coverage.EdgeCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.EdgePairCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.NodeCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.PrimePathsCoverage;
import pl.edu.uj.ii.goofy.algorithm.coverage.TestRequirementInt;
import pl.edu.uj.ii.goofy.algorithm.testpaths.TestPathGenerator;
import pl.edu.uj.ii.goofy.algorithm.testpaths.Touring;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationServer;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 740477122217644965L;
	private List<String> wierzcholkiPoczatkowe = new LinkedList<String>();
	private List<String> wierzcholkiKoncowe = new LinkedList<String>();
	private JPanel contentPane;
	private DirectedSparseGraph<String, Integer> graf;
	private Layout<String, Integer> layout;
	BasicVisualizationServer<String,Integer> vv;
	private JComboBox comboBox;
	private JList list;
	private JList list_1;
	
	
	public List<String> getWierzcholkiPoczatkowe() {
		return wierzcholkiPoczatkowe;
	}


	public List<String> getWierzcholkiKoncowe() {
		return wierzcholkiKoncowe;
	}


	public DirectedSparseGraph<String, Integer> getGraf() {
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
		graf = new DirectedSparseGraph<String, Integer>();
		layout = new CircleLayout<String, Integer>(graf);
		//layout.setSize(new Dimension(300, 400));
		vv = new BasicVisualizationServer<String, Integer>(layout);
		//this.pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[::150,fill][::150,fill][fill][::200,fill][::700,grow]", "[fill][][][][][grow][]"));
		test();
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
		
		JPanel panel = new JPanel();
		contentPane.add(btnWierzchokiPocztkowekocowe, "cell 0 1 4 1");
		contentPane.add(vv,"cell 4 0 1 7,grow");

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
		
		JLabel lblNewLabel_1 = new JLabel("Wymagania testowe:");
		contentPane.add(lblNewLabel_1, "cell 0 4 2 1");
		
		JLabel lblNewLabel_2 = new JLabel("Ścieżki testowe:");
		contentPane.add(lblNewLabel_2, "cell 2 4 2 1");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 5 2 1,grow");
		
		list = new JList();
		list.setModel(new DefaultListModel());
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 5 2 1,grow");
		
		list_1 = new JList();
		list_1.setModel(new DefaultListModel());
		scrollPane_1.setViewportView(list_1);
		
		JButton btnNewButton_1 = new JButton("Pokaż wymagania na grafie");
		contentPane.add(btnNewButton_1, "cell 0 6 2 1");
		
		JButton btnNewButton_2 = new JButton("Pokaż ścieżki na grafie");
		contentPane.add(btnNewButton_2, "cell 2 6 2 1,growx");
	}

	void dodajWierzcholki() {
		WierzcholkiFrame wf = new WierzcholkiFrame(this);
		wf.setModal(true);
		wf.setVisible(true);
		((VisualizationServer<String, Integer>) graf).repaint();
	}

	void dodajKrawedzie() {
		KrawedzieFrame kf = new KrawedzieFrame(this);
		kf.setVisible(true);
		kf.setModal(true);
	}
	
	void oznaczPoczatkoweKoncoweWierzcholki(){
		PoczatkoweFrame pf = new PoczatkoweFrame(this);
		pf.setVisible(true);
		pf.setModal(true);				
	}
	
	void test (){
		graf.addVertex("a");
		graf.addVertex("b");
		graf.addVertex("c");
		graf.addVertex("d");
		
		EdgeIdGenerator eig = EdgeIdGenerator.getInstance();
		
		graf.addEdge(eig.getId(), "a","b");
		graf.addEdge(eig.getId(), "d","c");
		graf.addEdge(eig.getId(), "b","c");
		graf.addEdge(eig.getId(), "d","a");
		graf.addEdge(eig.getId(), "d","d");
		vv.repaint();
		vv.setVisible(true);
		
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
		
		TestRequirementInt<String, Integer> testRequirement;
		if (selectedItem == "Wierzchołkowe") {
			testRequirement = new NodeCoverage<String, Integer>(graf);
		} else if (selectedItem == "Krawędziowe") {
			testRequirement = new EdgeCoverage<String, Integer>(graf);
		} else if (selectedItem == "Par krawędzi") {
			testRequirement = new EdgePairCoverage<String, Integer>(graf);
		} else if (selectedItem == "Ścieżki doskonałe") {
			testRequirement = new PrimePathsCoverage<String, Integer>(graf);
		} else {
			return;
		}
		
		LinkedList<LinkedList<String>> paths = testRequirement.getRequirement();

		DefaultListModel model = (DefaultListModel)list.getModel();
		model.clear();
		for (LinkedList<String> path : paths) {
			model.addElement(path);
		}
		
		TestPathGenerator<String, Integer> gen = new TestPathGenerator<String, Integer>(graf, wierzcholkiPoczatkowe, wierzcholkiKoncowe, Touring.SidetripsAndDetours);
		gen.getAllPaths();
		MultiMap<LinkedList<String>, LinkedList<String>> map = gen.reducePaths(paths);
		
		model = (DefaultListModel)list_1.getModel();
		model.clear();
		for (LinkedList<String> path : map.keySet()) {
			model.addElement(path);
		}
	}

}
