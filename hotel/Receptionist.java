package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Receptionist extends JFrame {

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
					Receptionist frame = new Receptionist();
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
	public Receptionist() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setBounds(27, 26, 100, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 65, 100, 29);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(131, 31, 150, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 65, 150, 24);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();					
					
					String username=textField.getText();
					String password1=passwordField.getText();
					String sql="Select * from reception where username='"+username+"'and password='"+password1+"'";
					Statement s = connection.con.createStatement();
					ResultSet rs = s.executeQuery(sql);
					if(rs.next()) {
						dispose();
						ReceptionDash h = new ReceptionDash();
						h.show();
					}else {
						JOptionPane.showMessageDialog(btnNewButton, "Invalid username or password");
					}
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
			
		});
		btnNewButton.setBounds(142, 120, 100, 29);
		contentPane.add(btnNewButton);
	}
}
