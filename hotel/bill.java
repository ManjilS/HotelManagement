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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class bill extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bill frame = new bill();
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
	public bill() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bill No");
		lblNewLabel.setBounds(10, 10, 123, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setBounds(10, 43, 123, 29);
		contentPane.add(lblRoomNo);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(10, 79, 123, 29);
		contentPane.add(lblUserId);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 118, 123, 29);
		contentPane.add(lblStatus);
		
		JLabel lblRoomCategory = new JLabel("Room Category");
		lblRoomCategory.setBounds(10, 157, 123, 29);
		contentPane.add(lblRoomCategory);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 196, 123, 29);
		contentPane.add(lblAmount);
		
		JComboBox comboBox = new JComboBox();
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
		comboBox.setBounds(99, 157, 163, 21);
		contentPane.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Century", Font.BOLD, 19));
		textArea.setBounds(35, 283, 338, 213);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection con = new connection();
				int rowsInserted = 0;

				try {
				    int bill_no = Integer.parseInt(textField.getText());
				    int room_no = Integer.parseInt(textField_1.getText());
				    int user_id = Integer.parseInt(textField_2.getText());
				    String status = textField_3.getText();
				    int amount = Integer.parseInt(textField_4.getText());
				    String room_cat = comboBox.getSelectedItem().toString();

				    String sql = "INSERT INTO bill (bill_no, room_no, user_id, status, room_category, amount) VALUES (?, ?, ?, ?, ?, ?)";
				    PreparedStatement s = connection.con.prepareStatement(sql);

				    s.setInt(1, bill_no);
				    s.setInt(2, room_no);
				    s.setInt(3, user_id);
				    s.setString(4, status);
				    s.setString(5, room_cat);
				    s.setInt(6, amount);

				    rowsInserted = s.executeUpdate();

				    if (rowsInserted > 0) {
				        JOptionPane.showMessageDialog(btnNewButton, "Row inserted");
				    }

				    s.close(); // Close the statement
				} catch (SQLException ent) {
				    ent.printStackTrace();
				} catch (NumberFormatException ent) {
				    JOptionPane.showMessageDialog(btnNewButton, "Invalid number format.");
				}

				try {
				    int bill_no1 = Integer.parseInt(textField.getText());
				    String sql1 = "SELECT bill_no, room_no, user_id, room_category, amount FROM bill WHERE bill_no = ?";
				    PreparedStatement s1 = connection.con.prepareStatement(sql1);
				    s1.setInt(1, bill_no1);
				    ResultSet rs = s1.executeQuery();

				    while (rs.next()) {
				        int bill_no = rs.getInt("bill_no");
				        int room_no = rs.getInt("room_no");
				        int user_id = rs.getInt("user_id");
				        String category = rs.getString("room_category");
				        int amount = rs.getInt("amount");

				        textArea.setText("Your bill is \n" + "Bill no : " + bill_no + "\n Room no :" + room_no + "\n User Id: " + user_id +
				                "\n category :" + category + "\n Amount :" + amount);
				    }

				    rs.close(); // Close the result set
				    s1.close(); // Close the statement
				} catch (SQLException evt) {
				    evt.printStackTrace();
				} catch (NumberFormatException ent) {
				    JOptionPane.showMessageDialog(btnNewButton, "Invalid number format.");
				}
			}
		});
		btnNewButton.setBounds(10, 252, 123, 21);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(99, 15, 163, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 48, 163, 19);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(99, 84, 163, 19);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(99, 123, 163, 19);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(99, 201, 163, 19);
		contentPane.add(textField_4);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				user u = new user();
				u.show();
				bill.this.setVisible(false);
				}catch(Exception en) {
					en.getMessage();
				}
			}
		});
		btnBack.setBounds(757, 252, 85, 21);
		contentPane.add(btnBack);
		
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
		btnNewButton_1.setBounds(587, 252, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View user");
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
				            rs.getDate("check_in_date"),
				            rs.getDate("check_out_date")
				        };
				        model.addRow(rowData);
				    }
				    
					}catch(SQLException ent) {
						ent.printStackTrace();
					}
			}
		});
		btnNewButton_2.setBounds(210, 252, 123, 21);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 17, 632, 208);
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
			        
			        
			        textField_2.setText(String.valueOf(user_id)); 
			        comboBox.setSelectedItem(rom_cat);
			        
			        
			    }
				
			}
		});
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(430, 293, 514, 202);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
		        int selectedRow = table_1.getSelectedRow();
		       
		        if (selectedRow != -1) { // Check if a row is selected
		            try {
		                int bill_no = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
		                int room_no = Integer.parseInt(tableModel.getValueAt(selectedRow, 1).toString());
		                int user_id = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
		                String status = tableModel.getValueAt(selectedRow, 3).toString();
		                String room_cat = tableModel.getValueAt(selectedRow, 4).toString();
		                int amount = Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString());
		                
		                String billDetails = String.format(
		                    "Your bill is:\nBill No:"+bill_no+"\nRoom No: "+room_no+"\nUser ID: "+user_id+"\nStatus: "+status+
		                    "\nRoom Category: "+room_cat+"\nAmount: "+amount);
		                textArea.setText(billDetails);
		            } catch (NumberFormatException ex) {
		                textArea.setText("Error: Unable to parse bill details.");
		            } catch (Exception ex) {
		                textArea.setText("An unexpected error occurred: " + ex.getMessage());
		            }
		        }
		    }
			
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_3 = new JButton("View bill");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection con = new connection();
					
					String sql = "SELECT * FROM bill";
				    Statement s = connection.con.createStatement();
				    ResultSet rs = s.executeQuery(sql);
				    ResultSetMetaData rsmd = rs.getMetaData();
				    DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				    
				    int cols = rsmd.getColumnCount();
				    String[] colname = new String[cols];
				    for (int i = 0; i < cols; i++) {
				        colname[i] = rsmd.getColumnName(i + 1);
				    }
				    model.setColumnIdentifiers(colname);  
				    model.setRowCount(0);
				    while (rs.next()) {
				        Object[] rowData = {
				            rs.getInt("bill_no"),
				            rs.getInt("room_no"),
				            rs.getInt("user_id"),  
				            rs.getString("status"),  
				            rs.getString("room_category"),
				            rs.getInt("amount")
				            
				        };
				        model.addRow(rowData);
				    }
				    
					}catch(SQLException ent) {
						ent.printStackTrace();
					}
				
			}
		});
		btnNewButton_3.setBounds(414, 252, 123, 21);
		contentPane.add(btnNewButton_3);
		
		
		
	
	}
}
