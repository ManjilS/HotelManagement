package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class staff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staff frame = new staff();
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
	public staff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Staff Name");
		lblNewLabel.setBounds(10, 23, 100, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblStaffId = new JLabel("Staff Id");
		lblStaffId.setBounds(10, 60, 100, 24);
		contentPane.add(lblStaffId);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 96, 100, 24);
		contentPane.add(lblStatus);
		
		JLabel lblJoinDate = new JLabel("Join Date");
		lblJoinDate.setBounds(10, 132, 100, 24);
		contentPane.add(lblJoinDate);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 166, 100, 24);
		contentPane.add(lblAddress);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				connection con = new connection();
				String name=textField.getText();
				int id=Integer.parseInt(textField_1.getText());
				String status=textField_2.getText();
				String join =textField_3.getText();
				String address=textField_4.getText();
				String category =textField_5.getText();
				String sql = "INSERT INTO staff (name, staff_id, status, join_date, address,category) VALUES (?, ?, ?, ?, ?,?)";
				PreparedStatement s = connection.con.prepareStatement(sql);
				s.setString(1, name);
				s.setInt(2, id);
				s.setString(3, status);
				s.setString(4, join);
				s.setString(5, address);
				s.setString(6, category);
				int rowsInserted = s.executeUpdate();
			    if (rowsInserted > 0) {
			    	JOptionPane.showMessageDialog(btnNewButton, "Row inserted");
			        
			    }

				//connection.con.close();
				
				}catch(SQLException evt) {
					evt.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 227, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
					if (table.getSelectedRowCount() == 1) {
					    try {
					        int row = table.getSelectedRow();
					        int value = (int) table.getValueAt(row, 1);
					        String name = textField.getText();
					        int id = Integer.parseInt(textField_1.getText());
					        String status = textField_2.getText();
					        String join_date = textField_3.getText();
					        String address = textField_4.getText();
					        String category=textField_5.getText();

					        
					        String sql = "UPDATE staff SET name = ?, staff_id = ?, status = ?, join_date = ?, address = ?,category=? WHERE staff_id = ?";
					        PreparedStatement s = connection.con.prepareStatement(sql);
					        s.setString(1, name);
					        s.setInt(2, id);
					        s.setString(3, status);
					        s.setString(4, join_date);
					        s.setString(5, address);
					        s.setString(6, category);
					        s.setInt(7, value);
					        int rowsUpdated = s.executeUpdate();
					        if (rowsUpdated > 0) {
					            JOptionPane.showMessageDialog(btnEdit, "Row updated successfully");
					            tblmodel.setValueAt(name, row, 0);
					            tblmodel.setValueAt(id, row, 1);
					            tblmodel.setValueAt(status, row, 2);
					            tblmodel.setValueAt(join_date, row, 3);
					            tblmodel.setValueAt(address, row, 4);
					            tblmodel.setValueAt(category, row, 5);
					            
					        } else {
					            JOptionPane.showMessageDialog(btnEdit, "Row update failed");
					        }
					        
					    } catch (SQLException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnEdit, "Error updating row: " + ex.getMessage());
					    } catch (NumberFormatException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnEdit, "Invalid entered data");
					    }
					} else {
					    JOptionPane.showMessageDialog(btnEdit, "No row selected or multiple rows selected");
					}


					//connection.con.close();
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
				
			}
		});
		btnEdit.setBounds(141, 227, 85, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection con = new connection();
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				if (table.getSelectedRowCount() == 1) {
				    try {
				        int row = table.getSelectedRow();
				        int value = (int) table.getValueAt(row, 1);
				        String name = textField.getText();
				        int id = Integer.parseInt(textField_1.getText());
				        String status = textField_2.getText();
				        String address = textField_4.getText();
				        String join_date = textField_3.getText();
				        String category = textField_5.getText();

				        
				        String sql = "Delete from staff where staff_id=?";
				        PreparedStatement s = connection.con.prepareStatement(sql);
				        s.setInt(1, value);
				        
				        int rowsdeleted = s.executeUpdate();
				        if (rowsdeleted > 0) {
				            JOptionPane.showMessageDialog(btnDelete, "Data deleted successfully");
				            
				        } else {
				            JOptionPane.showMessageDialog(btnDelete, "data deleted failed");
				        }
				        
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(btnDelete, "Error deleteing row: " + ex.getMessage());
				    }
				}
			}
		});
		btnDelete.setBounds(280, 227, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			            rs.getString("address"),
			            rs.getString("category")
			        };
			        model.addRow(rowData);
			    }
			    
				}catch(SQLException ent) {
					ent.printStackTrace();
				}
			}
		});
		btnView.setBounds(400, 227, 85, 21);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.show();
				staff.this.setVisible(false);
			}
		});
		btnBack.setBounds(525, 227, 85, 21);
		contentPane.add(btnBack);
		
		textField = new JTextField();
		textField.setBounds(74, 26, 174, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(74, 63, 174, 19);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(74, 99, 174, 19);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(74, 135, 174, 19);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(74, 169, 174, 19);
		contentPane.add(textField_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 28, 521, 189);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
			    int selectedRow = table.getSelectedRow();
			    if (selectedRow != -1) { // Check if a row is selected
			        String name = tblmodel.getValueAt(selectedRow, 0).toString();
			        int staff_id = Integer.parseInt(tblmodel.getValueAt(selectedRow, 1).toString());
			        String status = tblmodel.getValueAt(selectedRow, 2).toString();
			        String join_date = tblmodel.getValueAt(selectedRow, 3).toString();
			        String address = tblmodel.getValueAt(selectedRow, 4).toString();
			        String category =tblmodel.getValueAt(selectedRow, 5).toString();			        
			        textField.setText(name);
			        textField_1.setText(String.valueOf(staff_id));  
			        textField_2.setText(status);
			        textField_3.setText(join_date);  
			        textField_4.setText(address);
			        textField_5.setText(category);
			        
			    }
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton_1.setBounds(643, 227, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 193, 100, 24);
		contentPane.add(lblCategory);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(74, 198, 174, 19);
		contentPane.add(textField_5);
	}

}
