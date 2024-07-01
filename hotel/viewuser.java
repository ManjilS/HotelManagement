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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

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
		setBounds(100, 100, 803, 363);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 71, 757, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.show();
				viewuser.this.setVisible(false);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\undo.png"));
		lblNewLabel_8.setBounds(10, 10, 58, 51);
		contentPane.add(lblNewLabel_8);
		
				
		txtSearch = new JTextField();
		txtSearch.setBounds(519, 42, 154, 19);
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
		btnNewButton_1.setBounds(683, 40, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Search for");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(393, 48, 104, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\refesh.png"));
		lblNewLabel_1.setBounds(285, 10, 48, 51);
		contentPane.add(lblNewLabel_1);
		
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
