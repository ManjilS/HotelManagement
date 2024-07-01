package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class user extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user frame = new user();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public user() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1231, 466);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblNewLabel.setBounds(20, 61, 93, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User_Id");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(20, 99, 93, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Age");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(20, 137, 93, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(20, 179, 93, 28);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Status");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(20, 217, 93, 28);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Citizenship_no");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(20, 255, 93, 28);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Room_Category");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(20, 293, 93, 28);
		contentPane.add(lblNewLabel_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		
	       connection con = new connection();
	        Statement s = connection.con.createStatement();
	        String Query = "SELECT category FROM room";
	        ResultSet rs = s.executeQuery(Query);
	        Set<String> typeset=new HashSet<>();
	        while (rs.next()) {
	            String type = rs.getString(1);
	            if (typeset.add(type)) { 
	                comboBox.addItem(type);
	            }
	        }
	   
		comboBox.setBounds(123, 293, 195, 21);
		contentPane.add(comboBox);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					String name=textField.getText();
					int id=Integer.parseInt(textField_1.getText());
					int age=Integer.parseInt(textField_2.getText());
					String address=textField_3.getText();
					String status =textField_4.getText();
					int ctz_no=Integer.parseInt(textField_5.getText());
					String room_cat=comboBox.getSelectedItem().toString();
					String check_in_date =textField_6.getText();
					String check_out_date=textField_7.getText();
					String sql = "INSERT INTO user (name,user_id,age,address,status,citizenship_no,room_category,check_in_date) VALUES (?,?,?, ?, ?, ?, ?,?)";
					PreparedStatement s = connection.con.prepareStatement(sql);
					s.setString(1, name);
					s.setInt(2, id);
					s.setInt(3, age);
					s.setString(4, address);
					s.setString(5, status);
					s.setInt(6, ctz_no);
					s.setString(7, room_cat);
					s.setString(8, check_in_date);
					
					int rowsInserted = s.executeUpdate();
				    if (rowsInserted > 0) {
				    	JOptionPane.showMessageDialog(btnNewButton, "Row inserted");
				        
				    }
				}catch(SQLException ent) {
					ent.printStackTrace();
				}
			}
		});
			
		
		btnNewButton.setBounds(86, 400, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
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
					        int age = Integer.parseInt(textField_2.getText());
					        String address = textField_3.getText();
					        String status = textField_4.getText();
					        int ctz_no = Integer.parseInt(textField_5.getText());
					        String room_cat = comboBox.getSelectedItem().toString();
					        String check_in_date =textField_6.getText();
							String check_out_date=textField_7.getText();
					        
					        String sql = "UPDATE user SET name = ?, user_id = ?, age = ?, address = ?, status = ?, citizenship_no = ?, room_category = ?,check_in_date=?,check_out_date=? WHERE user_id = ?";
					        PreparedStatement s = connection.con.prepareStatement(sql);
					        s.setString(1, name);
					        s.setInt(2, id);
					        s.setInt(3, age);
					        s.setString(4, address);
					        s.setString(5, status);
					        s.setInt(6, ctz_no);
					        s.setString(7, room_cat);
					        s.setString(8, check_in_date);	
					        s.setString(9, check_out_date);
					        s.setInt(10, value);
					        int rowsUpdated = s.executeUpdate();
					        if (rowsUpdated > 0) {
					            JOptionPane.showMessageDialog(btnNewButton_1, "Row updated successfully");
					            tblmodel.setValueAt(name, row, 0);
					            tblmodel.setValueAt(id, row, 1);
					            tblmodel.setValueAt(age, row, 2);
					            tblmodel.setValueAt(address, row, 3);
					            tblmodel.setValueAt(status, row, 4);
					            tblmodel.setValueAt(ctz_no, row, 5);
					            tblmodel.setValueAt(room_cat, row, 6);
					            tblmodel.setValueAt(check_in_date, row, 7);
					            tblmodel.setValueAt(check_out_date, row, 8);
					        } else {
					            JOptionPane.showMessageDialog(btnNewButton_1, "Row update failed");
					        }
					        
					    } catch (SQLException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnNewButton_1, "Error updating row: " + ex.getMessage());
					    } catch (NumberFormatException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(btnNewButton_1, "Invalid entered data");
					    }
					} else {
					    JOptionPane.showMessageDialog(btnNewButton_1, "No row selected or multiple rows selected");
					}


					//connection.con.close();
					
				}catch(Exception evt) {
					evt.printStackTrace();
				}
				
			}

			
		});
		btnNewButton_1.setBounds(214, 400, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection con = new connection();
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				if (table.getSelectedRowCount() == 1) {
				    try {
				        int row = table.getSelectedRow();
				        int value = (int) table.getValueAt(row, 1);
				        String name = textField.getText();
				        int id = Integer.parseInt(textField_1.getText());
				        int age = Integer.parseInt(textField_2.getText());
				        String address = textField_3.getText();
				        String status = textField_4.getText();
				        int ctz_no = Integer.parseInt(textField_5.getText());
				        String room_cat = comboBox.getSelectedItem().toString();
				        String date=textField_6.getText();
				        String check_in_date =textField_6.getText();
						String check_out_date=textField_7.getText();
				        
				        String sql = "Delete from user where user_id=?";
				        PreparedStatement s = connection.con.prepareStatement(sql);
				        s.setInt(1, value);
				        
				        int rowsdeleted = s.executeUpdate();
				        if (rowsdeleted > 0) {
				            JOptionPane.showMessageDialog(btnNewButton_1, "Data deleted successfully");
				            
				        } else {
				            JOptionPane.showMessageDialog(btnNewButton_1, "data deleted failed");
				        }
				        
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(btnNewButton_1, "Error deleteing row: " + ex.getMessage());
				    }
				}
			}
		});
		btnNewButton_2.setBounds(338, 400, 85, 21);
		contentPane.add(btnNewButton_2);
		
		
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(123, 67, 195, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(123, 104, 195, 28);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_2.setColumns(10);
		textField_2.setBounds(123, 142, 195, 28);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(255, 255, 255));
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_3.setColumns(10);
		textField_3.setBounds(123, 184, 195, 28);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(255, 255, 255));
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_4.setColumns(10);
		textField_4.setBounds(123, 222, 195, 28);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setForeground(new Color(255, 255, 255));
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_5.setColumns(10);
		textField_5.setBounds(123, 260, 195, 28);
		contentPane.add(textField_5);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(372, 75, 798, 303);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
			    int selectedRow = table.getSelectedRow();
			    if (selectedRow != -1) { // Check if a row is selected
			        String name = tblmodel.getValueAt(selectedRow, 0).toString();
			        int user_id = Integer.parseInt(tblmodel.getValueAt(selectedRow, 1).toString());
			        int age = Integer.parseInt(tblmodel.getValueAt(selectedRow, 2).toString());
			        String address = tblmodel.getValueAt(selectedRow, 3).toString();
			        String status = tblmodel.getValueAt(selectedRow, 4).toString();
			        int ctz_no = Integer.parseInt(tblmodel.getValueAt(selectedRow, 5).toString());
			        String rom_cat = tblmodel.getValueAt(selectedRow, 6).toString();
			        String check_in_date = tblmodel.getValueAt(selectedRow, 7).toString();
			        String check_out_date = tblmodel.getValueAt(selectedRow, 7).toString();
			        textField.setText(name);
			        textField_1.setText(String.valueOf(user_id));  
			        textField_2.setText(String.valueOf(age));
			        textField_3.setText(address);  
			        textField_4.setText(status);
			        textField_5.setText(String.valueOf(ctz_no));
			        comboBox.setSelectedItem(rom_cat);
			        textField_6.setText(String.valueOf(check_in_date));
			        if(check_out_date==""){
			        		textField_7.setText("Not checked out");
			        }else {
			        textField_7.setText(String.valueOf(check_out_date));
			        }
			    }
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_4 = new JButton("View");
		btnNewButton_4.addActionListener(new ActionListener() {
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
		btnNewButton_4.setBounds(449, 400, 85, 21);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Bill");
		btnNewButton_5.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectedRowCount()==1) {
				bill b = new bill();
				b.show();
				user.this.setVisible(false);
					}
				}catch(SQLException ent) {
					ent.getMessage();				
					}
			}
		});
		btnNewButton_5.setBounds(597, 400, 85, 21);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Clear");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
			}
		});
		btnNewButton_6.setBounds(755, 400, 85, 21);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Check in date");
		lblNewLabel_6_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6_1.setBounds(20, 324, 93, 28);
		contentPane.add(lblNewLabel_6_1);
		
		textField_6 = new JTextField();
		textField_6.setForeground(new Color(255, 255, 255));
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_6.setColumns(10);
		textField_6.setBounds(123, 324, 195, 28);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Check out date");
		lblNewLabel_6_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6_1_1.setBounds(20, 362, 93, 28);
		contentPane.add(lblNewLabel_6_1_1);
		
		textField_7 = new JTextField();
		textField_7.setForeground(new Color(255, 255, 255));
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_7.setColumns(10);
		textField_7.setBounds(123, 362, 195, 28);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBounds(906, 32, 163, 19);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("Search");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = textField_8.getText();
				
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
		btnNewButton_7.setBounds(1079, 31, 85, 21);
		contentPane.add(btnNewButton_7);
		
		JLabel lblNewLabel_7 = new JLabel("Search for");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(794, 27, 102, 21);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton_8 = new JButton("Undo");
		btnNewButton_8.addActionListener(new ActionListener() {
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
		btnNewButton_8.setBounds(893, 400, 85, 21);
		contentPane.add(btnNewButton_8);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.show();
				user.this.setVisible(false);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\undo.png"));
		lblNewLabel_8.setBounds(10, 10, 58, 51);
		contentPane.add(lblNewLabel_8);
		
	}
}
