package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewstaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewstaff frame = new viewstaff();
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
	public viewstaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 25, 724, 241);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceptionDash r = new ReceptionDash();
				r.show();
				viewstaff.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(668, 276, 85, 21);
		contentPane.add(btnNewButton);
		try {
		connection con = new connection();
		
		String sql = "SELECT * FROM staff";
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
	            rs.getInt("staff_id"),  
	            rs.getString("status"),
	            rs.getDate("join_date"),  
	            rs.getString("address")
	        };
	        model.addRow(rowData);
	    }
	    
		}catch(SQLException ent) {
			ent.printStackTrace();
		}
	}
}
