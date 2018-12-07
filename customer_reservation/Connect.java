package HDMS2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
	static Connection dbTest;
	Connect() {
		connectDB();
	}
	
	void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbTest = DriverManager.getConnection("jdbc:mysql://localhost:3306/hdms?serverTimezone=Asia/Seoul", "koserim", "3954");
			Customer_reservation.dbTest = dbTest;
			Customer_reservation_option.dbTest = dbTest;
			Customer_check.dbTest = dbTest;
			Customer_enter_mypage.dbTest = dbTest;
			Customer_mypage.dbTest = dbTest;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결에 실패하였습니다.");
			System.out.println("SQLException:" + e);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
	
	public static void main(String[] argv) {
		new Connect();
		try {
			dbTest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: + e");
		}
	}
	


	
	
}