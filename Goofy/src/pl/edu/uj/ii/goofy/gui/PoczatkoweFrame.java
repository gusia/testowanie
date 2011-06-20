package pl.edu.uj.ii.goofy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class PoczatkoweFrame extends JDialog {

	private JPanel contentPane;
	MainFrame mFrame;
	DirectedSparseGraph<Node, Integer> graf;
	private JList list_2;
	private JList list_1;
	private JList list;

	/**
	 * Create the frame.
	 */
	public PoczatkoweFrame(MainFrame _mFrame) {
		mFrame = _mFrame;
		graf = mFrame.getGraf();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][grow]",
				"[][grow][grow][]"));

		JLabel lblNewLabel = new JLabel("Startowe:");
		contentPane.add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Wszystkie:");
		contentPane.add(lblNewLabel_1, "cell 2 0");

		JLabel lblNewLabel_2 = new JLabel("Końcowe:");
		contentPane.add(lblNewLabel_2, "cell 4 0");

		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajDoPocztakowych();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 1 2,grow");

		list = new JList();
		scrollPane.setViewportView(list);
		contentPane.add(btnNewButton, "cell 1 1,growx,aligny bottom");
		list.setModel(new DefaultListModel());
		for (Node wierzcholek : mFrame.getWierzcholkiPoczatkowe()) {
			((DefaultListModel) list.getModel()).addElement(wierzcholek);
		}

		JButton btnNewButton_2 = new JButton("->");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajDoKoncowych();
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 1 1 2,grow");

		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		contentPane.add(btnNewButton_2, "cell 3 1,growx,aligny bottom");
		list_1.setModel(new DefaultListModel());
		for (Node wierzcholek : graf.getVertices()) {
			((DefaultListModel) list_1.getModel()).addElement(wierzcholek);
		}

		JButton btnNewButton_1 = new JButton("->");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunZPoczatkowych();
			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();
		contentPane.add(scrollPane_2, "cell 4 1 1 2,grow");

		list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		contentPane.add(btnNewButton_1, "cell 1 2,growx,aligny top");
		list_2.setModel(new DefaultListModel());
		for (Node wierzcholek : mFrame.getWierzcholkiKoncowe()) {
			((DefaultListModel) list_2.getModel()).addElement(wierzcholek);
		}

		JButton btnNewButton_3 = new JButton("<-");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunZKoncowych();
			}
		});
		contentPane.add(btnNewButton_3, "cell 3 2,growx,aligny top");

		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zapiszZmiany();
			}
		});
		contentPane.add(btnZapisz, "cell 0 3 2 1,growx");

		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnAnuluj, "cell 3 3 2 1,growx");
	}

	private void dodajDoKoncowych() {
		try {
			Integer index = list_1.getSelectedIndex();
			Node node = (Node) ((DefaultListModel) list_1.getModel())
					.getElementAt(index);
			if (!((DefaultListModel) list_2.getModel()).contains(node)) {
				((DefaultListModel) list_2.getModel()).addElement(node);
			}
		} catch (Exception e) {
		}

	}

	private void usunZKoncowych() {
		try {
			Integer index = list_2.getSelectedIndex();
			((DefaultListModel) list_2.getModel()).removeElementAt(index);
		} catch (Exception e) {
		}
	}

	private void dodajDoPocztakowych() {
		try {
			Integer index = list_1.getSelectedIndex();
			Node node = (Node) ((DefaultListModel) list_1.getModel())
					.getElementAt(index);
			if (!((DefaultListModel) list.getModel()).contains(node)) {
				((DefaultListModel) list.getModel()).addElement(node);
			}
		} catch (Exception e) {
		}
	}

	private void usunZPoczatkowych() {
		try {
			Integer index = list.getSelectedIndex();
			((DefaultListModel) list.getModel()).removeElementAt(index);
		} catch (Exception e) {
		}
	}

	private void zapiszZmiany() {
		Enumeration <Node> enumer = (Enumeration<Node>) ((DefaultListModel) list.getModel()).elements();
		mFrame.getWierzcholkiPoczatkowe().clear();
		mFrame.getWierzcholkiKoncowe().clear();
		while (enumer.hasMoreElements()) {
			mFrame.getWierzcholkiPoczatkowe().add(enumer.nextElement());
		}
		enumer = (Enumeration<Node>) ((DefaultListModel) list_2.getModel()).elements();
		while (enumer.hasMoreElements()){
			mFrame.getWierzcholkiKoncowe().add(enumer.nextElement());
		}
		System.out.println("Początkowe: \n");
		for (Node s : mFrame.getWierzcholkiPoczatkowe()){
			System.out.print(s +", ");
		}
		System.out.println("Końcowe: \n");
		for (Node s : mFrame.getWierzcholkiKoncowe()){
			System.out.print(s +", ");
		}
		
		dispose();


	}

}
