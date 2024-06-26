package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUser = new JButton("View User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				viewuser u = new viewuser();
				u.show();
				Home.this.setVisible(false);
				}catch(Exception en) {
					en.getMessage();
				}
			}
		});
		btnUser.setBounds(213, 42, 120, 46);
		contentPane.add(btnUser);
		
		JButton btnRoom = new JButton(" Room Detials");
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				room r = new room();
				r.show();
				Home.this.setVisible(false);
			}
		});
		btnRoom.setBounds(415, 42, 120, 46);
		contentPane.add(btnRoom);
		
		JButton btnStaff = new JButton("Staff Details");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staff s = new staff();
				s.show();
				Home.this.setVisible(false);
			}
		});
		btnStaff.setBounds(33, 42, 120, 46);
		contentPane.add(btnStaff);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDash d = new MainDash();
				d.show();
				Home.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(452, 169, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewReport v = new viewReport();
				v.show();
				Home.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(213, 114, 120, 40);
		contentPane.add(btnNewButton_1);
	}
}
