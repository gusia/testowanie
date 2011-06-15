package pl.edu.uj.ii.goofy.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[142px][285px,grow]", "[][][197.00px,grow]"));
		
		JButton btnNacinijMnie = new JButton("Naciśnij mnie");
		btnNacinijMnie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nacisnijMniePressed();
			}
		});
		contentPane.add(btnNacinijMnie, "cell 0 0");
		
		JLabel lblWowJestemLabelk = new JLabel("wow, jestem labelką");
		contentPane.add(lblWowJestemLabelk, "cell 0 1,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 0 1 3,grow");
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}

	private void nacisnijMniePressed() {

		JOptionPane.showMessageDialog(this, "Kocham Cię Jadzia!");
		
		StringBuilder sb = new StringBuilder();
		
		
		for (int i = 0; i < 1000; ++i) {
			sb.append("Kocham Cię! ");
		}
		
		textPane.setText(sb.toString());
	}
}
