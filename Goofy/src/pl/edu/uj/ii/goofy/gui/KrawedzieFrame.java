package pl.edu.uj.ii.goofy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.edu.uj.ii.goofy.EdgeIdGenerator;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;

public class KrawedzieFrame extends JDialog {
	EdgeIdGenerator eig = EdgeIdGenerator.getInstance();
	private JPanel contentPane;
	MainFrame mFrame;
	DirectedSparseGraph<Node, Integer> graf;
	HashSet<Pair<Node>> do_dodania;
	HashSet<Pair<Node>> do_usuniecia;
	private JList list;
	private JList list_1;
	private JList list_2;

	/**
	 * Create the frame.
	 */
	public KrawedzieFrame(MainFrame _mFrame) {
		mFrame = _mFrame;
		graf = mFrame.getGraf();
		do_dodania = new HashSet<Pair<Node>>();
		do_usuniecia = new HashSet<Pair<Node>>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[140.00,grow,fill][-43.00][182.00,grow]",
				"[][grow][fill][][][grow][][]"));

		JLabel lblWierzchoekrdowy = new JLabel("Wierzchołek źródłowy:");
		contentPane.add(lblWierzchoekrdowy, "cell 0 0");

		JLabel label_1 = new JLabel("Wierzchołek docelowy:");
		contentPane.add(label_1, "cell 2 0");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1,grow");

		list = new JList();
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dodajKrawedz();
				}
			}
		});
		list.setModel(new DefaultListModel());
		scrollPane.setViewportView(list);

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 1,grow");

		list_1 = new JList();
		list_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dodajKrawedz();
				}
			}
		});
		scrollPane_1.setViewportView(list_1);
		list_1.setModel(new DefaultListModel());
		for (Node wierzcholek : graf.getVertices()) {
			((DefaultListModel) list.getModel()).addElement(wierzcholek);
			((DefaultListModel) list_1.getModel()).addElement(wierzcholek);
		}
		JLabel label = new JLabel("->");
		contentPane.add(label, "cell 1 1");

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajKrawedz();
			}
		});

		contentPane.add(btnDodaj, "cell 0 2 3 1");

		JLabel lblListaKrawdzi = new JLabel("Lista Krawędzi:");
		contentPane.add(lblListaKrawdzi, "cell 0 3");

		JScrollPane scrollPane_2 = new JScrollPane();
		contentPane.add(scrollPane_2, "cell 0 5 3 1,grow");

		list_2 = new JList();
		list_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE){
					usunKrawedz();
				}
			}
		});
		scrollPane_2.setViewportView(list_2);
		list_2.setModel(new DefaultListModel());
		for (Integer krawedz : graf.getEdges()) {
			Pair<Node> el = graf.getEndpoints(krawedz);
			((DefaultListModel) list_2.getModel()).addElement(el);

		}

		JButton btnUsun = new JButton("Usuń zaznaczoną krawędz");
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunKrawedz();
			}
		});
		contentPane.add(btnUsun, "cell 0 6 3 1");

		JButton btnNewButton = new JButton("Zapisz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zapiszKrawedzieDoGrafu();
			}
		});
		contentPane.add(btnNewButton, "cell 0 7");

		JButton btnNewButton_1 = new JButton("Anuluj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnNewButton_1, "cell 2 7,growx");
	}

	private void dodajKrawedz() {
		try {
			Pair<Node> el;
			Integer index1 = list.getSelectedIndex();
			Node str1 = (Node) ((DefaultListModel) list.getModel())
					.getElementAt(index1);
			Integer index2 = list_1.getSelectedIndex();
			Node str2 = (Node) ((DefaultListModel) list_1.getModel())
					.getElementAt(index2);
			el = new Pair<Node>(str1, str2);
			if (!((DefaultListModel) list_2.getModel()).contains(el)) {
				((DefaultListModel) list_2.getModel()).addElement(el);
				if (do_usuniecia.contains(el)) {
					do_usuniecia.remove(el);
				} else {
					do_dodania.add(el);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Krawędź już istnieje!");
			}
		} catch (Exception e) {
		}
	}

	private void usunKrawedz() {
		try {
			Pair<Node> el;
			Integer index = list_2.getSelectedIndex();
			el = (Pair<Node>) ((DefaultListModel) list_2.getModel())
					.getElementAt(index);
			((DefaultListModel) list_2.getModel()).removeElementAt(index);
			if (do_dodania.contains(el)) {
				do_dodania.remove(el);
			} else {
				do_usuniecia.add(el);
			}

		} catch (Exception e) {
		}

	}

	private void zapiszKrawedzieDoGrafu() {
		for (Pair<Node> iter : do_usuniecia) {
			Integer id = graf.findEdge(iter.getFirst(), iter.getSecond());
			graf.removeEdge(id);
		}

		for (Pair<Node> iter : do_dodania) {
			graf.addEdge(eig.getId(), iter);
		}
		dispose();
	}
}
