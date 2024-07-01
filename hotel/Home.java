package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		btnUser.setBounds(221, 71, 120, 46);
		contentPane.add(btnUser);
		
		JButton btnRoom = new JButton(" Room Detials");
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				room r = new room();
				r.show();
				Home.this.setVisible(false);
			}
		});
		btnRoom.setBounds(423, 71, 120, 46);
		contentPane.add(btnRoom);
		
		JButton btnStaff = new JButton("Staff Details");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staff s = new staff();
				s.show();
				Home.this.setVisible(false);
			}
		});
		btnStaff.setBounds(41, 71, 120, 46);
		contentPane.add(btnStaff);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dash h = new Dash();
				h.show();
				Home.this.setVisible(false);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\undo.png"));
		lblNewLabel_8.setBounds(10, 10, 58, 51);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("View Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewReport v = new viewReport();
				v.show();
				Home.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(130, 143, 120, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("View Revenue");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revenue r = new revenue();
				r.show();
				Home.this.setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(331, 143, 120, 40);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\dash.jpg"));
		lblNewLabel.setBounds(0, 0, 573, 252);
		contentPane.add(lblNewLabel);
	}
}
