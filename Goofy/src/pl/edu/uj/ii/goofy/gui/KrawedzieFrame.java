package pl.edu.uj.ii.goofy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

public class KrawedzieFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KrawedzieFrame frame = new KrawedzieFrame();
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
	public KrawedzieFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow,fill][][grow,fill]", "[][grow][fill][][grow][]"));
		
		JLabel lblWierzchoekrdowy = new JLabel("Wierzchołek źródłowy:");
		contentPane.add(lblWierzchoekrdowy, "cell 0 0");
		
		JLabel lblWierzchoekDocelowy = new JLabel("Wierzchołek docelowy:");
		contentPane.add(lblWierzchoekDocelowy, "cell 2 0");
		
		JList list = new JList();
		contentPane.add(list, "cell 0 1,grow");
		
		JLabel label = new JLabel("->");
		contentPane.add(label, "cell 1 1");
		
		JList list_1 = new JList();
		contentPane.add(list_1, "cell 2 1,grow");
		
		JButton btnDodaj = new JButton("Dodaj");
		contentPane.add(btnDodaj, "cell 0 2");
		
		JButton btnUsu = new JButton("Usuń");
		contentPane.add(btnUsu, "cell 2 2");
		
		JLabel lblListaKrawdzi = new JLabel("Lista Krawędzi:");
		contentPane.add(lblListaKrawdzi, "cell 0 3");
		
		JList list_2 = new JList();
		contentPane.add(list_2, "cell 0 4 3 1,grow");
		
		JButton btnNewButton = new JButton("Zapisz");
		contentPane.add(btnNewButton, "cell 0 5");
		
		JButton btnNewButton_1 = new JButton("Anuluj");
		contentPane.add(btnNewButton_1, "cell 2 5");
	}

}
