package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class room extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					room frame = new room();
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
	public room() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Room No");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 72, 105, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setForeground(new Color(255, 255, 255));
		lblStatus.setBounds(10, 110, 105, 25);
		contentPane.add(lblStatus);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setForeground(new Color(255, 255, 255));
		lblCategory.setBounds(10, 145, 105, 25);
		contentPane.add(lblCategory);
		
		JLabel lblRent = new JLabel("Rent");
		lblRent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRent.setForeground(new Color(255, 255, 255));
		lblRent.setBounds(10, 180, 105, 25);
		contentPane.add(lblRent);
		
		textField = new JTextField();
		textField.setBounds(127, 75, 180, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 113, 180, 19);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(127, 148, 180, 19);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				connection con = new connection();
				
				int room_no=Integer.parseInt(textField.getText());
				String status =textField_1.getText();
				String category=textField_2.getText();				
				int rent=Integer.parseInt(textField_3.getText());
				
				String sql = "INSERT INTO room (room_no,status,category,rent) VALUES (?, ?, ?, ?)";
				PreparedStatement s = connection.con.prepareStatement(sql);
				s.setInt(1, room_no);
				s.setString(2, status);
				s.setString(3, category);
				s.setInt(4, rent);
				
				int rowsInserted = s.executeUpdate();
			    if (rowsInserted > 0) {
			    	JOptionPane.showMessageDialog(btnNewButton, "Row inserted");
			        
			    }
			}catch(SQLException ent) {
				ent.printStackTrace();
			}
		}
	});
		btnNewButton.setBounds(10, 234, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
					if (table.getSelectedRowCount() == 1) {
					    try {
					        int row = table.getSelectedRow();
					        int value = (int) table.getValueAt(row, 0);
					        int room_no = Integer.parseInt(textField.getText()); 
					        String status = textField_1.getText();
					        String category = textField_2.getText();
					        int rent = Integer.parseInt(textField_3.getText());
					        
					       
					        

					        
					        String sql = "UPDATE room SET room_no = ?, status = ?, category = ?, rent = ? WHERE room_no = ?";
					        PreparedStatement s = connection.con.prepareStatement(sql);
					        s.setInt(1, room_no);
					        s.setString(2, status);
					        s.setString(3, category);
					        s.setInt(4, rent);
					        s.setInt(5, value);
					        int rowsUpdated = s.executeUpdate();
					        if (rowsUpdated > 0) {
					            JOptionPane.showMessageDialog(btnEdit, "Row updated successfully");
					            tblmodel.setValueAt(room_no, row, 0);
					            tblmodel.setValueAt(status, row, 1);
					            tblmodel.setValueAt(category, row, 2);
					            tblmodel.setValueAt(rent, row, 3);
					            
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
		btnEdit.setBounds(136, 234, 85, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection con = new connection();
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				if (table.getSelectedRowCount() == 1) {
				    try {
				        int row = table.getSelectedRow();
				        int value = (int) table.getValueAt(row, 0);
				        int room_no = Integer.parseInt(textField.getText());
				        String status = textField_1.getText();
				        String category = textField_2.getText();
				        int rent = Integer.parseInt(textField_3.getText());
				        

				        
				        String sql = "Delete from room where room_no=?";
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
		btnDelete.setBounds(302, 234, 85, 21);
		contentPane.add(btnDelete);
		
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
		btnView.setBounds(474, 234, 85, 21);
		contentPane.add(btnView);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(127, 183, 180, 19);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 77, 407, 142);
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
			        
			        
			        textField.setText(String.valueOf(room_no));
			        textField_1.setText(status);  
			        textField_2.setText(category);
			        textField_3.setText(String.valueOf(rent));
			        
			        
			    }
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.show();
				room.this.setVisible(false);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\undo.png"));
		lblNewLabel_8.setBounds(10, 10, 58, 51);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
			}
		});
		btnNewButton_1.setBounds(663, 234, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel room = new JLabel("New label");
		room.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\room.jpg"));
		room.setBounds(0, 0, 862, 418);
		contentPane.add(room);
	}
}
