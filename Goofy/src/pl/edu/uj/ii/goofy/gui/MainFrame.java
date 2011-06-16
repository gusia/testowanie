package pl.edu.uj.ii.goofy.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

import pl.edu.uj.ii.goofy.EdgeIdGenerator;
import java.awt.Panel;
import javax.swing.JCheckBox;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 740477122217644965L;
	private List<String> wierzcholkiPoczatkowe = new LinkedList<String>();
	private List<String> wierzcholkiKoncowe = new LinkedList<String>();
	private JPanel contentPane;
	private DirectedSparseGraph<String, Integer> graf;
	private Layout<String, Integer> layout;
	BasicVisualizationServer<String,Integer> vv;
	private JComboBox comboBox;
	
	
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
		contentPane.setLayout(new MigLayout("", "[100:100:100,fill][100px:100px:100px,fill][100px:100px:100px,fill][100px:100px:100px,fill][::700,grow]", "[fill][][][][][][grow][]"));
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
		
		GraphZoomScrollPane panel = new GraphZoomScrollPane(new VisualizationViewer<String, Integer>(layout));
		
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("ścieżki poboczne/objazdy");
		contentPane.add(chckbxNewCheckBox, "cell 0 4 4 1");
		
		JLabel lblNewLabel_1 = new JLabel("Wymagania testowe:");
		contentPane.add(lblNewLabel_1, "cell 0 5 2 1");
		
		JLabel lblNewLabel_2 = new JLabel("Ścieżki testowe:");
		contentPane.add(lblNewLabel_2, "cell 2 5 2 1");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 6 2 1,grow");
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 6 2 1,grow");
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
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
		
	}

}
