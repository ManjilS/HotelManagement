package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Dash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dash frame = new Dash();
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
	public Dash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Manager");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin a = new admin();
				a.show();
				Dash.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(30, 27, 152, 41);
		contentPane.add(btnNewButton);
		
		JButton btnReceptionist = new JButton("Receptionist");
		btnReceptionist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receptionist r = new Receptionist();
				r.show();
				Dash.this.setVisible(false);
			}
		});
		btnReceptionist.setBounds(256, 27, 152, 41);
		contentPane.add(btnReceptionist);
		
		JButton btnHousekeeping = new JButton("Housekeeping");
		btnHousekeeping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				housekeeping h = new housekeeping();
				h.show();
				Dash.this.setVisible(false);
			}
		});
		btnHousekeeping.setBounds(475, 27, 152, 41);
		contentPane.add(btnHousekeeping);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\dash.jpg"));
		lblNewLabel.setBounds(0, 0, 637, 147);
		contentPane.add(lblNewLabel);
	}
}
