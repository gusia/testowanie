package pl.edu.uj.ii.goofy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PoczatkoweFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PoczatkoweFrame frame = new PoczatkoweFrame();
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
	public PoczatkoweFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][grow]", "[][grow][grow][]"));
		
		JLabel lblNewLabel = new JLabel("Startowe:");
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("Wszystkie:");
		contentPane.add(lblNewLabel_1, "cell 2 0");
		
		JLabel lblNewLabel_2 = new JLabel("Końcowe:");
		contentPane.add(lblNewLabel_2, "cell 4 0");
		
		JList list = new JList();
		contentPane.add(list, "cell 0 1 1 2,grow");
		
		JButton btnNewButton = new JButton("<-");
		contentPane.add(btnNewButton, "cell 1 1,growx,aligny bottom");
		
		JList list_1 = new JList();
		contentPane.add(list_1, "cell 2 1 1 2,grow");
		
		JButton btnNewButton_2 = new JButton("->");
		contentPane.add(btnNewButton_2, "cell 3 1,growx,aligny bottom");
		
		JList list_2 = new JList();
		contentPane.add(list_2, "cell 4 1 1 2,grow");
		
		JButton btnNewButton_1 = new JButton("->");
		contentPane.add(btnNewButton_1, "cell 1 2,growx,aligny top");
		
		JButton btnNewButton_3 = new JButton("<-");
		contentPane.add(btnNewButton_3, "cell 3 2,growx,aligny top");
		
		JButton btnZapisz = new JButton("Zapisz");
		contentPane.add(btnZapisz, "cell 0 3 2 1,growx");
		
		JButton btnAnuluj = new JButton("Anuluj");
		contentPane.add(btnAnuluj, "cell 3 3 2 1,growx");
	}

}
