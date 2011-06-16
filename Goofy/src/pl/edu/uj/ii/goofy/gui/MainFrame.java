package pl.edu.uj.ii.goofy.gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import edu.uci.ics.jung.visualization.VisualizationServer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[fill][grow][grow][grow]", "[fill][][][][grow]"));
		
		JButton btnDodajWierzchoki = new JButton("Dodaj wierzchołki");
		btnDodajWierzchoki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WierzcholkiFrame wf = new WierzcholkiFrame();
				wf.setVisible(true);
				setEnabled(false);
			}
		});
		contentPane.add(btnDodajWierzchoki, "cell 0 0");
		
		panel = new JPanel();
		contentPane.add(panel, "cell 1 0 3 5,grow");
		
		JButton btnWierzchokiPocztkowekocowe = new JButton("Wierzchołki początkowe/końcowe");
		btnWierzchokiPocztkowekocowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PoczatkoweFrame pf = new PoczatkoweFrame();
				pf.setVisible(true);
				setEnabled(false);
			}
		});
		contentPane.add(btnWierzchokiPocztkowekocowe, "cell 0 1");
		
		JButton btnDodajKrawdzie = new JButton("Dodaj krawędzie");
		btnDodajKrawdzie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KrawedzieFrame kf = new KrawedzieFrame();
				kf.setVisible(true);
				setEnabled(false);
			}
		});
		contentPane.add(btnDodajKrawdzie, "cell 0 2");
	}

	public void setGraph(VisualizationServer<Integer, String> vs) {
		panel.add((Component) vs);
	}
}
