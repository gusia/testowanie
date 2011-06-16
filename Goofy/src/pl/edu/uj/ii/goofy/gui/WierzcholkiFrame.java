package pl.edu.uj.ii.goofy.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

import net.miginfocom.swing.MigLayout;

public class WierzcholkiFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	MainFrame mFrame;
	DirectedSparseGraph<String, Integer> graf;
	HashSet<String> do_dodania;
	HashSet<String> do_usuniecia;

	

	/**
	 * Create the frame.
	 */
	public WierzcholkiFrame(MainFrame _mFrame) {
		mFrame = _mFrame;
		graf = mFrame.getGraf();
		do_dodania = new HashSet<String>();
		do_usuniecia = new HashSet<String>();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[210px][6px,grow]", "[15px][19px][][15px][121px,fill][][25px]"));
		
		JLabel lblWpiszDodawaneusuwaneWierzchoki = new JLabel("Wpisz nazwy dodawanych wierzchołków:");
		contentPane.add(lblWpiszDodawaneusuwaneWierzchoki, "cell 0 0 2 1,growx,aligny top");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dodajWierzcholek();
				}
			}
		});
		contentPane.add(textField, "cell 0 1 2 1,growx,aligny top");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajWierzcholek();
			}
		});
		contentPane.add(btnNewButton, "cell 0 2 2 1,growx,aligny top");
		
		JLabel lblWierzchokiTwojegoGrafu = new JLabel("Wierzchołki Twojego grafu:");
		contentPane.add(lblWierzchokiTwojegoGrafu, "cell 0 3 2 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 4 2 1,grow");
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE){
					usunWierzcholek();
				}
			}
		});
		list.setValueIsAdjusting(true);
		list.setModel(new DefaultListModel());
		System.out.println(graf.getVertexCount());
		
		
		for (String wierzcholek : graf.getVertices()) {
			((DefaultListModel)list.getModel()).addElement(wierzcholek);
		}
		
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Usuń zaznaczony");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunWierzcholek();
			}
		});
		contentPane.add(btnNewButton_1, "cell 0 5 2 1,growx,aligny top");
		
		JButton btnNewButton_2 = new JButton("Zapisz");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zapiszWierzcholkiDoGrafu();
			}
		});
		contentPane.add(btnNewButton_2, "cell 0 6,growx,aligny top");
		
		JButton btnNewButton_3 = new JButton("Anuluj");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnNewButton_3, "cell 1 6,growx,aligny top");
	}
	void dodajWierzcholek(){
		String el = textField.getText();
		if (el.equals("")|| el ==null || el.charAt(0) == ' ') return;
		if (!((DefaultListModel)list.getModel()).contains(el)){
			((DefaultListModel)list.getModel()).addElement(el);
			if (do_usuniecia.contains(el)){
				do_usuniecia.remove(el);
			}
			else {
				do_dodania.add(el);
			}
			
			textField.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "Wierzchołek już istnieje!");
		}
	}
	void usunWierzcholek(){
		try {
			Integer index = list.getSelectedIndex();
			String el = (String)((DefaultListModel)list.getModel()).getElementAt(index);
			((DefaultListModel)list.getModel()).removeElementAt(index);
			if (do_dodania.contains(el)){
				do_dodania.remove(el);
			}
			else {
				do_usuniecia.add(el);
			}
		} catch (Exception e){
			
		}
	}
	
	void zapiszWierzcholkiDoGrafu (){
		Iterator<String> iter = do_usuniecia.iterator();
		while (iter.hasNext()){
			String vertex = iter.next();
			graf.removeVertex(vertex);
			System.out.print("a ");
		}
		iter = do_dodania.iterator();
		while (iter.hasNext()){
			String vertex = iter.next();
			graf.addVertex(vertex);
			System.out.print("b ");
		}
		dispose();
	}
}
