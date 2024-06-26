package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceptionDash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionDash frame = new ReceptionDash();
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
	public ReceptionDash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("View Staff");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewstaff v = new viewstaff();
				v.show();
				ReceptionDash.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(71, 25, 103, 35);
		contentPane.add(btnNewButton);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				user u =new user();
				u.show();
				ReceptionDash.this.setVisible(false);
				}catch(Exception ent) {
					ent.getMessage();
				}
			}
		});
		btnUser.setBounds(276, 25, 103, 35);
		contentPane.add(btnUser);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDash m = new MainDash();
				m.show();
				ReceptionDash.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(384, 122, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
