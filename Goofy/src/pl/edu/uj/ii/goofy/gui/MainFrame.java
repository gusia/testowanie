package pl.edu.uj.ii.goofy.gui;

import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private DirectedSparseGraph<String, Integer> graf;
	Layout<String, Integer> layout;
	BasicVisualizationServer<String, Integer> vv;

	public DirectedSparseGraph<String, Integer> getGraf() {
		return graf;
	}

	public void setGraf(DirectedSparseGraph<String, Integer> graf) {
		this.graf = graf;
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
		layout.setSize(new Dimension(300, 400));
		vv = new BasicVisualizationServer<String, Integer>(layout);
		vv.setPreferredSize(new Dimension(300, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[fill][grow][grow][grow]",
				"[fill][][][][grow]"));

		JButton btnDodajWierzchoki = new JButton("Dodaj wierzchołki");
		btnDodajWierzchoki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajWierzcholki();
			}
		});
		contentPane.add(btnDodajWierzchoki, "cell 0 0");

		panel = new JPanel();
		contentPane.add(panel, "cell 1 0 3 5,grow");
		panel.add(vv);
		
		JButton btnWierzchokiPocztkowekocowe = new JButton(
				"Wierzchołki początkowe/końcowe");
		btnWierzchokiPocztkowekocowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PoczatkoweFrame pf = new PoczatkoweFrame();
				System.out.println("asdf");
				pf.setVisible(true);
				System.out.println("asdfasdf");
			}
		});
		contentPane.add(btnWierzchokiPocztkowekocowe, "cell 0 1");

		JButton btnDodajKrawdzie = new JButton("Dodaj krawędzie");
		btnDodajKrawdzie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajKrawedzie();
			}
		});
		contentPane.add(btnDodajKrawdzie, "cell 0 2");
	}

	void dodajWierzcholki() {
		WierzcholkiFrame wf = new WierzcholkiFrame(this);
		wf.setModal(true);
		wf.setVisible(true);
		// layout.reset();
	}

	void dodajKrawedzie() {
		KrawedzieFrame kf = new KrawedzieFrame(this);
		kf.setVisible(true);
		kf.setModal(true);
	}

}
