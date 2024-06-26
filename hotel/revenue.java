package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class revenue extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					revenue frame = new revenue();
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
	public revenue() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Daily", "Weekly", "Monthly"}));
		comboBox.setBounds(158, 27, 168, 21);
		contentPane.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 22));
		textArea.setBounds(31, 92, 390, 233);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection con = new connection();
				String report = comboBox.getSelectedItem().toString();
				java.sql.Date startDate = null;
				java.sql.Date endDate = null;
				LocalDate today = LocalDate.now();
				int amount = 0;

				switch (report) {
				    case "Daily":
				        startDate = java.sql.Date.valueOf(today);
				        endDate = java.sql.Date.valueOf(today);
				        break;
				    case "Weekly":
				        startDate = java.sql.Date.valueOf(today.minusDays(7));
				        endDate = java.sql.Date.valueOf(today);
				        break;
				    case "Monthly":
				        startDate = java.sql.Date.valueOf(today.minusDays(30));
				        endDate = java.sql.Date.valueOf(today);
				        break;
				}

				try {
				    String sql1 = "SELECT amount FROM user JOIN bill ON user.user_id = bill.user_id WHERE check_in_date BETWEEN ? AND ?";
				    PreparedStatement s1 = connection.con.prepareStatement(sql1);
				    s1.setDate(1, startDate);
				    s1.setDate(2, endDate);
				    ResultSet rs = s1.executeQuery();
				    
				    while (rs.next()) {
				        amount += rs.getInt("amount");
				    }

				    textArea.setText("TOTAL REVENUE \n" + "Amount : " + amount);
				    
				    rs.close(); // Close the ResultSet
				    s1.close(); // Close the PreparedStatement
				} catch (SQLException evt) {
				    evt.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(336, 27, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Revenue");
		lblNewLabel.setBounds(42, 24, 94, 26);
		contentPane.add(lblNewLabel);
	}
}
