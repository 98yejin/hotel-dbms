package hotel;
import javax.swing.*;
import java.awt.*;
import java.sql.*;



public class TaskPersonalPage {
	
	private Connection dbTest;
	

	private void connectDB() {
		try {
			//JDBC Driver Loading
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			dbTest = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=false", "root", "990225");
			System.out.println("Connect");
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fail");
			System.out.println("SQLException:" + e);			
		}catch(Exception e) {
			System.out.println("Exception:" + e);
		}
	}	
	
	private JFrame frame = new JFrame();
	private JLabel hello = new JLabel("팀 님 안녕하세요.");
	private JLabel title = new JLabel("나의 업무정보");
	private JLabel category1 = new JLabel("출결확인");
	private JLabel category2 = new JLabel("경고횟수(지각,결석)");
	private JLabel category3 = new JLabel("근무일정");
	private JLabel text = new JLabel("회");
	private JPanel panel = new JPanel();
	private JTextField text1 = new JTextField();
	private JButton attend = new JButton("출근");
	private JButton leave = new JButton("퇴근");
	
	
	public TaskPersonalPage() {
		
		connectDB();
		
		panel.setLayout(null);
		
		title.setBounds(50, 30, 100, 30);
		hello.setBounds(600, 30, 200, 30);
		category1.setBounds(50, 100, 100, 30);
		category2.setBounds(50, 200, 200, 30);
		category3.setBounds(50, 300, 200, 30);
		text1.setBounds(200, 200, 100, 30);
		text1.setEnabled(false);
		text.setBounds(310, 200, 100, 30);
		attend.setBounds(610, 90, 80, 30);
		leave.setBounds(610, 120, 80, 30);
		
		
		
		String days1[] = {" ", "일", "월", "화", "수", "목", "금", "토"};
		String days2[] = {"일", "월", "화", "수", "목", "금", "토"};
		
		
		String contents1[][] = {{"출근","8:00","8:00","8:00","8:00","8:00","8:00","8:00"},
		{"퇴근","6:00","6:00","6:00","6:00","6:00","6:00","6:00"}};
		String contents2[][] = {{"O","O","O","O","O","O","O"}};
		
		JTable table1 = new JTable(contents1, days1);
		JTable table2 = new JTable(contents2, days2);
		
		JScrollPane scrollpane1 = new JScrollPane(table1);
		scrollpane1.setBounds(200,93,400,57);
		JScrollPane scrollpane2 = new JScrollPane(table2);
		scrollpane2.setBounds(200,300,400,41);
		
		try {
			showCount();
		}catch(SQLException se3) {
			se3.printStackTrace();
		}
		
		panel.add(title);
		panel.add(hello);
		panel.add(category1);
		panel.add(category2);
		panel.add(category3);
		panel.add(text1);
		panel.add(scrollpane1);
		panel.add(scrollpane2);
		panel.add(text);
		panel.add(attend);
		panel.add(leave);
		
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		

	}
	
	public void makeTable1() throws SQLException{
		String specification = "";
		
		String sqlStr = "SELECT late_count FROM status WHERE staff_id='18010001'";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			specification += rs.getString("late_count");
		}
		
		text1.setText(specification);
		
		rs.next();
		
	}

	
	public void showCount() throws SQLException{
		String specification = "";
		
		String sqlStr = "SELECT late_count FROM status WHERE staff_id='18010001'";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			specification += rs.getString("late_count");
		}
		
		text1.setText(specification);
		
		rs.next();
		
	}
	
	public void makeTable2() throws SQLException{
		String specification = "";
		
		String sqlStr = "SELECT late_count FROM status WHERE staff_id='18010001'";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			specification += rs.getString("late_count");
		}
		
		text1.setText(specification);
		
		rs.next();
		
	}

	public static void main(String[] args) {
		new TestConnect();
		new TaskPersonalPage();
	}
}
