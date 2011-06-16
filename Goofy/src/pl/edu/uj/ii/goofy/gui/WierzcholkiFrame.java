package pl.edu.uj.ii.goofy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ScrollPaneConstants;

public class WierzcholkiFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WierzcholkiFrame frame = new WierzcholkiFrame();
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
	public WierzcholkiFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[210px][6px][204px]", "[15px][19px][25px][15px][121px][25px]"));
		
		JLabel lblWpiszDodawaneusuwaneWierzchoki = new JLabel("Wpisz nazwy dodawanych/usuwanych wierzchołków:");
		contentPane.add(lblWpiszDodawaneusuwaneWierzchoki, "cell 0 0 3 1,growx,aligny top");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 1 3 1,growx,aligny top");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajWierzcholek();
			}
		});
		contentPane.add(btnNewButton, "cell 0 2,alignx right,aligny top");
		
		JButton btnNewButton_1 = new JButton("Usuń");
		contentPane.add(btnNewButton_1, "cell 2 2,alignx left,aligny top");
		
		JLabel lblWierzchokiTwojegoGrafu = new JLabel("Wierzchołki Twojego grafu:");
		contentPane.add(lblWierzchokiTwojegoGrafu, "cell 0 3 3 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, "cell 0 4 3 1,grow");
		
		list = new JList();
		list.setValueIsAdjusting(true);
		list.setModel(new DefaultListModel());
		scrollPane.setColumnHeaderView(list);
		
		JButton btnNewButton_2 = new JButton("Zapisz");
		contentPane.add(btnNewButton_2, "cell 0 5,alignx right,aligny top");
		
		JButton btnNewButton_3 = new JButton("Anuluj");
		contentPane.add(btnNewButton_3, "cell 2 5,alignx left,aligny top");
	}
	void dodajWierzcholek(){
		((DefaultListModel)list.getModel()).addElement(textField.getText());
	}
	public JList getList() {
		return list;
	}
}
