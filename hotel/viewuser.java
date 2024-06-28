package hotel;

import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class viewuser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewuser frame = new viewuser();
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
	public viewuser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 757, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.show();
				viewuser.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(682, 289, 85, 21);
		contentPane.add(btnNewButton);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(525, 20, 154, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String search = txtSearch.getText();
				
				try {
		           connection con = new connection();
		            String sql = "SELECT * FROM user WHERE name = '"+search+"'";
		            PreparedStatement s = connection.con.prepareStatement(sql);
		            ResultSet rs = s.executeQuery(sql);
				    ResultSetMetaData rsmd = rs.getMetaData();
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    
				    int cols = rsmd.getColumnCount();
				    String[] colname = new String[cols];
				    for (int i = 0; i < cols; i++) {
				        colname[i] = rsmd.getColumnName(i + 1);
				    }
				    model.setColumnIdentifiers(colname);  
				    model.setRowCount(0);
				    while (rs.next()) {
				        Object[] rowData = {
				            rs.getString("name"),
				            rs.getInt("user_id"),  
				            rs.getInt("age"),
				            rs.getString("address"),  
				            rs.getString("status"),
				            rs.getInt("citizenship_no"),
				            rs.getString("room_category"),
				            rs.getString("check_in_date"),
				            rs.getString("check_out_date")
				        };
				        model.addRow(rowData);
				    }
		        } catch (SQLException en) {
		            en.printStackTrace();
		        	}
				
			}
		});
		btnNewButton_1.setBounds(682, 19, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Search for");
		lblNewLabel.setBounds(411, 23, 104, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Undo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					
					String sql = "SELECT * FROM user";
				    Statement s = connection.con.createStatement();
				    ResultSet rs = s.executeQuery(sql);
				    ResultSetMetaData rsmd = rs.getMetaData();
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    
				    int cols = rsmd.getColumnCount();
				    String[] colname = new String[cols];
				    for (int i = 0; i < cols; i++) {
				        colname[i] = rsmd.getColumnName(i + 1);
				    }
				    model.setColumnIdentifiers(colname);  
				    model.setRowCount(0);
				    while (rs.next()) {
				        Object[] rowData = {
				            rs.getString("name"),
				            rs.getInt("user_id"),  
				            rs.getInt("age"),
				            rs.getString("address"),  
				            rs.getString("status"),
				            rs.getInt("citizenship_no"),
				            rs.getString("room_category"),
				            rs.getString("check_in_date"),
				            rs.getString("check_out_date")
				        };
				        model.addRow(rowData);
				    }
				    
					}catch(SQLException ent) {
						ent.printStackTrace();
					}
			}
		});
		btnNewButton_2.setBounds(587, 289, 85, 21);
		contentPane.add(btnNewButton_2);
		
		try {
			connection con = new connection();
			
			String sql = "SELECT * FROM user";
		    Statement s = connection.con.createStatement();
		    ResultSet rs = s.executeQuery(sql);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    
		    int cols = rsmd.getColumnCount();
		    String[] colname = new String[cols];
		    for (int i = 0; i < cols; i++) {
		        colname[i] = rsmd.getColumnName(i + 1);
		    }
		    model.setColumnIdentifiers(colname);  
		    model.setRowCount(0);
		    while (rs.next()) {
		        Object[] rowData = {
		            rs.getString("name"),
		            rs.getInt("user_id"),  
		            rs.getInt("age"),
		            rs.getString("address"),  
		            rs.getString("status"),
		            rs.getInt("citizenship_no"),
		            rs.getString("room_category"),
		            rs.getString("check_in_date"),
		            rs.getString("check_out_date")
		        };
		        model.addRow(rowData);
		    }
		    
			}catch(SQLException ent) {
				ent.printStackTrace();
			}
	}
}
