package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class viewroom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewroom frame = new viewroom();
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
	public viewroom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 28, 567, 277);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
			    int selectedRow = table.getSelectedRow();
			    if (selectedRow != -1) { // Check if a row is selected
			        
			        int room_no = Integer.parseInt(tblmodel.getValueAt(selectedRow, 0).toString());
			        int rent  = Integer.parseInt(tblmodel.getValueAt(selectedRow, 3).toString());
			        String status = tblmodel.getValueAt(selectedRow, 1).toString();
			        String category = tblmodel.getValueAt(selectedRow, 2).toString();
			        String Cleaning = tblmodel.getValueAt(selectedRow, 4).toString();
			        
			        textField.setText(String.valueOf(room_no));
			        textField_1.setText(Cleaning);  
			       
			        
			        
			    }
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Room No");
		lblNewLabel.setBounds(20, 28, 119, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(149, 28, 189, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCleaning = new JLabel("Cleaning");
		lblCleaning.setBounds(20, 67, 119, 29);
		contentPane.add(lblCleaning);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 62, 189, 24);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
					if (table.getSelectedRowCount() == 1) {
					    try {
					        int row = table.getSelectedRow();
					        int value = (int) table.getValueAt(row, 0);
					        int room_no = Integer.parseInt(textField.getText()); 
					        String Cleaning = textField_1.getText();
					        String sql = "UPDATE room SET Cleaning = ? WHERE room_no = ?";
					        PreparedStatement s = connection.con.prepareStatement(sql);
					        s.setString(1, Cleaning);
					        s.setInt(2, room_no);
					        int rowsUpdated = s.executeUpdate();
					        if (rowsUpdated > 0) {
					            JOptionPane.showMessageDialog(btnNewButton, "Data updated successfully");
					            tblmodel.setValueAt(Cleaning, row, 4);
					        } else {
					            JOptionPane.showMessageDialog(btnNewButton, "Data update failed");
					        }
					        
					    } catch (SQLException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnNewButton, "Error updating row: " + ex.getMessage());
					    } catch (NumberFormatException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnNewButton, "Invalid entered data");
					    }
					} else {
					    JOptionPane.showMessageDialog(btnNewButton, "No row selected or multiple rows selected");
					}


					//connection.con.close();
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(67, 125, 85, 38);
		contentPane.add(btnNewButton);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					
					String sql = "SELECT * FROM room";
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
				            rs.getInt("room_no"),
				            rs.getString("status"),  
				            rs.getString("category"),  
				            rs.getInt("rent"),
				            rs.getString("Cleaning")
				        };
				        model.addRow(rowData);
				    }
				    
					}catch(SQLException ent) {
						ent.printStackTrace();
					}
			}
		});
		btnView.setBounds(278, 125, 85, 38);
		contentPane.add(btnView);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				housekeepDash d = new housekeepDash();
				d.show();
				viewroom.this.setVisible(false);
			}

			
		});
		btnNewButton_1.setBounds(290, 257, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
