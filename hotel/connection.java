package hotel;

import java.sql.*;

public class connection {

	
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/hotel";
		String user="root";
		String password="";
		static Connection con;
		public connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection(url,user,password);
			
			
		}catch(Exception e) {
			System.out.println("Exception found");
		}
	}
		

}


