package pl.edu.uj.ii.goofy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import pl.edu.uj.ii.goofy.algorithm.DuDescription;
import pl.edu.uj.ii.goofy.algorithm.DuDescriptionType;
import pl.edu.uj.ii.goofy.algorithm.Edge;
import pl.edu.uj.ii.goofy.algorithm.Node;
import edu.uci.ics.jung.graph.Graph;

public class DefinicjeUzyciaFrame extends JDialog {

	private static final long serialVersionUID = -6803935863095198249L;
	private JPanel contentPane;
	MainFrame mFrame;
	Graph<Node, Edge> graf;
	private JRadioButton rdbtnUzycie;
	private JRadioButton rdbtnDefinicja;
	private JLabel lblNazwaUycia;
	private JButton btnNewButton;
	private JList list_1;
	private JList list;
	private JButton btnNewButton_2;
	private JButton btnNewButton_4;
	private JButton btnNewButton_1;
	private JButton btnNewButton_3;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public DefinicjeUzyciaFrame(MainFrame _mf) {
		mFrame = _mf;
		graf = mFrame.getGraf();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[100px:110px:100px,grow][][47.00,grow][66.00][][grow][]",
				"[][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Wierzchołki/Krawędzie:");
		contentPane.add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Użycia/Definicje:");
		contentPane.add(lblNewLabel_1, "cell 4 0");

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 0 1 2 5,grow");

		list_1 = new JList();
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				zmianaWierzcholkaKrawedzi();
				System.out.println(list_1.getSelectedIndex());
			}
		});
		list_1.setToolTipText("Wybierz wierzchołek/krawędź.");
		scrollPane_1.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setValueIsAdjusting(true);
		list_1.setModel(new DefaultListModel());
		for (Node wierzcholek : graf.getVertices()) {
			((DefaultListModel) list_1.getModel()).addElement(wierzcholek);
		}
		for (Edge krawedz : graf.getEdges()) {
			// Pair<Node> el = graf.getEndpoints(krawedz);
			((DefaultListModel) list_1.getModel()).addElement(krawedz);

		}

		ButtonGroup wybor = new ButtonGroup();

		rdbtnUzycie = new JRadioButton("użycie");
		// rdbtnUzycie.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mousePressed(MouseEvent e) {
		// zaznaczenieUzycia();
		// }
		// });
		// rdbtnUzycie.setActionCommand("uzycie");
		contentPane.add(rdbtnUzycie, "cell 2 1 2 1");
		wybor.add(rdbtnUzycie);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 4 1 2 5,grow");

		list = new JList();
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					usunDefinicjeUzycie();
				} 
			}
		});
		list.setModel(new DefaultListModel());
		list.setToolTipText("Definicje/Użycia wybranej krawędzi/wierzchołka");
		scrollPane.setViewportView(list);

		rdbtnDefinicja = new JRadioButton("definicja");
		contentPane.add(rdbtnDefinicja, "cell 2 2 2 1");
		wybor.add(rdbtnDefinicja);

		btnNewButton_2 = new JButton("↑");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				przesunWGore();
			}
		});
		btnNewButton_2.setToolTipText("Przesuń w górę.");
		contentPane.add(btnNewButton_2, "cell 6 2,growx");

		lblNazwaUycia = new JLabel("Nazwa definicji:");
		contentPane.add(lblNazwaUycia, "cell 2 3 2 1,growx");

		btnNewButton_4 = new JButton("Usuń");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunDefinicjeUzycie();
			}
		});
		contentPane.add(btnNewButton_4, "flowx,cell 6 3,growx");

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dodajUzycieDefinicje();
				}
			}
		});
		contentPane.add(textField, "cell 2 4 2 1,growx");
		textField.setColumns(10);

		btnNewButton_1 = new JButton("↓");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				przesunWDol();
			}
		});
		btnNewButton_1.setToolTipText("Przesuń w dół.");
		contentPane.add(btnNewButton_1, "cell 6 4,growx");

		btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajUzycieDefinicje();
			}
		});
		contentPane.add(btnNewButton, "cell 2 5 2 1,growx");

		btnNewButton_3 = new JButton("Zapisz");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zapisz();
			}
		});
		contentPane.add(btnNewButton_3, "cell 6 5");
	}

	void dodajUzycieDefinicje() {
		try {
			String name = textField.getText();
			DuDescription dD;
			if (!((name == "") || (name.charAt(0) == ' '))) {
				if (rdbtnUzycie.isSelected()) {
					dD = new DuDescription(name, DuDescriptionType.Usage);
					if (!((DefaultListModel) list.getModel()).contains(dD)) {
						((DefaultListModel) list.getModel()).addElement(dD);
					}
				} else if (rdbtnDefinicja.isSelected()) {
					dD = new DuDescription(name, DuDescriptionType.Definition);
					if (!((DefaultListModel) list.getModel()).contains(dD)) {
						((DefaultListModel) list.getModel()).addElement(dD);
					}
				}
				textField.setText("");
			}
		} catch (Exception e) {
		}
	}

	void zmianaWierzcholkaKrawedzi() {
		try {
			((DefaultListModel) list.getModel()).clear();
			int id = list_1.getSelectedIndex();
			LinkedList<DuDescription> dDList;
			if (((DefaultListModel) list_1.getModel()).getElementAt(id) instanceof Edge) {
				dDList = ((Edge) (((DefaultListModel) list_1.getModel())
						.getElementAt(id))).getUsages();
				rdbtnDefinicja.setEnabled(false);
				rdbtnUzycie.setSelected(true);
			} else {
				dDList = ((Node) (((DefaultListModel) list_1.getModel())
						.getElementAt(id))).getUsages();
				rdbtnDefinicja.setEnabled(true);
			}
			for (DuDescription el : dDList) {
				((DefaultListModel) list.getModel()).addElement(el);
			}
		} catch (Exception e) {
		}
	}

	void przesunWGore() {
		try {
			int id = list.getSelectedIndex();
			if (id > 0) {
				DuDescription tmp = (DuDescription) ((DefaultListModel) list
						.getModel()).getElementAt(id - 1);
				DuDescription sel = (DuDescription) ((DefaultListModel) list
						.getModel()).getElementAt(id);
				((DefaultListModel) list.getModel()).remove(id - 1);
				((DefaultListModel) list.getModel()).add(id - 1, sel);
				((DefaultListModel) list.getModel()).remove(id);
				((DefaultListModel) list.getModel()).add(id, tmp);

			}
		} catch (Exception e) {
		}
	}

	void przesunWDol() {
		try {
			int id = list.getSelectedIndex();
			if (id < ((DefaultListModel) list.getModel()).getSize() - 1) {
				DuDescription tmp = (DuDescription) ((DefaultListModel) list
						.getModel()).getElementAt(id + 1);
				DuDescription sel = (DuDescription) ((DefaultListModel) list
						.getModel()).getElementAt(id);
				((DefaultListModel) list.getModel()).remove(id);
				((DefaultListModel) list.getModel()).add(id, tmp);
				((DefaultListModel) list.getModel()).remove(id + 1);
				((DefaultListModel) list.getModel()).add(id + 1, sel);

			}
		} catch (Exception e) {
		}
	}

	void usunDefinicjeUzycie() {
		try {
			int id = list.getSelectedIndex();
			((DefaultListModel) list.getModel()).remove(id);
		} catch (Exception e) {
		}
	}

	void zapisz() {
		try {
			int id = list_1.getSelectedIndex();
			Enumeration<DuDescription> enumer = (Enumeration<DuDescription>) ((DefaultListModel) list
					.getModel()).elements();
			if (!((DefaultListModel) list.getModel()).isEmpty()) {
				if (((DefaultListModel) list_1.getModel()).getElementAt(id) instanceof Node) {
					((Node) ((DefaultListModel) list_1.getModel())
							.getElementAt(id)).getUsages().clear();
					while (enumer.hasMoreElements()) {
						((Node) ((DefaultListModel) list_1.getModel())
								.getElementAt(id)).getUsages().add(
								enumer.nextElement());
					}
					JOptionPane.showMessageDialog(this,
							"Zapisano definicje/uzycia wierzchołka!");
				} else if (((DefaultListModel) list_1.getModel())
						.getElementAt(id) instanceof Edge) {
					((Edge) ((DefaultListModel) list_1.getModel())
							.getElementAt(id)).getUsages().clear();
					while (enumer.hasMoreElements()) {
						((Edge) ((DefaultListModel) list_1.getModel())
								.getElementAt(id)).addUsage(enumer
								.nextElement());
					}
					JOptionPane.showMessageDialog(this,
							"Zapisano użycia krawędzi!");
				}
			}
		} catch (Exception e) {
		}
	}
}
