package staff_manage;

import java.sql.*;
import javax.swing.*;                                         

public class mysqlConnection {
	Connection conn=null;
	public static Connection dbconnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=false", "root", "12345");
			return conn;
		}catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
