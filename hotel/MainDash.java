package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainDash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDash frame = new MainDash();
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
	public MainDash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Manager");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin a = new admin();
				a.show();
				MainDash.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(27, 40, 152, 49);
		contentPane.add(btnNewButton);
		
		JButton btnReceptionist = new JButton("Receptionist");
		btnReceptionist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receptionist r = new Receptionist();
				r.show();
				MainDash.this.setVisible(false);
			}
		});
		btnReceptionist.setBounds(218, 40, 152, 49);
		contentPane.add(btnReceptionist);
		
		JButton btnHouseKeeper = new JButton("House Keeper");
		btnHouseKeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				housekeeping h = new housekeeping();
				h.show();
				MainDash.this.setVisible(false);
			}
		});
		btnHouseKeeper.setBounds(426, 40, 152, 49);
		contentPane.add(btnHouseKeeper);
	}

}
