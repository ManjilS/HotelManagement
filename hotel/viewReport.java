package hotel;

import java.awt.EventQueue;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class viewReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	JComboBox comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewReport frame = new viewReport();
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
	public viewReport() {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Daily", "Weekly", "Monthly"}));
		comboBox.setBounds(214, 22, 243, 21);
		contentPane.add(comboBox);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 856, 255);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("Report Type");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(90, 22, 101, 21);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Generate");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String report = comboBox.getSelectedItem().toString();
		        java.sql.Date startDate = null;
		        java.sql.Date endDate = null;
		        LocalDate today = LocalDate.now();

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
		        connection con = new connection();
		        
		        String sql = "SELECT * FROM user WHERE check_in_date BETWEEN ? AND ?";
		        
		        try (PreparedStatement s = connection.con.prepareStatement(sql)) {
		            s.setDate(1, startDate);
		            s.setDate(2, endDate);
		            ResultSet rs = s.executeQuery();

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
		                Object[] rowData = new Object[cols];
		                for (int i = 0; i < cols; i++) {
		                    rowData[i] = rs.getObject(i + 1);
		                    
		                }
		                model.addRow(rowData);
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(482, 22, 121, 21);
		contentPane.add(btnNewButton);
		
				
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.show();
				viewReport.this.setVisible(false);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\bhade\\Downloads\\undo.png"));
		lblNewLabel_8.setBounds(10, 10, 58, 51);
		contentPane.add(lblNewLabel_8);
	}
	

}
