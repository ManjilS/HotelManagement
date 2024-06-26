package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class housekeeping extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					housekeeping frame = new housekeeping();
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
	public housekeeping() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setBounds(10, 10, 95, 28);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 10, 178, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 57, 95, 28);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();					
					
					String username=textField.getText();
					String password1=passwordField.getText();
					String sql="Select * from housekeeping where h_name='"+username+"'and password='"+password1+"'";
					Statement s = connection.con.createStatement();
					ResultSet rs = s.executeQuery(sql);
					if(rs.next()) {
						dispose();
						housekeepDash d = new housekeepDash();
						d.show();
					}else {
						JOptionPane.showMessageDialog(btnNewButton, "Invalid username or password");
					}
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
			
		});
		btnNewButton.setBounds(147, 127, 85, 21);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 53, 178, 28);
		contentPane.add(passwordField);
	}
}