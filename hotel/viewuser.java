package hotel;

import java.awt.EventQueue;
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

public class viewuser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 803, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 757, 230);
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
		btnNewButton.setBounds(682, 265, 85, 21);
		contentPane.add(btnNewButton);
		
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
