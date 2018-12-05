package TaskPersonalPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;



public class TaskPersonalPage implements ActionListener{
	
	Connection connection=null;
	int staff_id = 18030002; //���߿� �α��� ���� �޾ƿ;� ��
	
	private JFrame frame = new JFrame();
	private JLabel hello = new JLabel();
	private JLabel title = new JLabel("My TaskPage");
	private JLabel category1 = new JLabel("Attendence");
	private JLabel category2 = new JLabel("Late Count");
	private JLabel category3 = new JLabel("Work Days");
	private JLabel category4 = new JLabel("Vacation");
	private JPanel panel = new JPanel();
	private JTextField text1 = new JTextField();
	private JTextField vacation_start = new JTextField();
	private JLabel to = new JLabel("~");
	private JTextField vacation_end = new JTextField();
	private JButton attend = new JButton("Attend");
	private JButton leave = new JButton("Leave");
	private JButton showAttendance = new JButton("Attendance of this week");
	private JButton Update = new JButton("Update");
	String contents1[][] = new String[2][8];
	String contents2[][] = new String[1][7];
	
	JTable table1 ;
	JTable table2 ;
	String days1[] = {" ", "1", "2", "3", "4", "5", "6", "7"};
	String days2[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
	
	
	public TaskPersonalPage() {
		
		connection = mysqlConnection.dbconnector();
		
		panel.setLayout(null);
		
		title.setBounds(50, 30, 100, 30);
		hello.setBounds(500, 30, 300, 30);
		category1.setBounds(50, 100, 100, 30);
		category2.setBounds(50, 200, 200, 30);
		category3.setBounds(50, 300, 200, 30);
		category4.setBounds(50, 400, 200, 30);
		text1.setBounds(150, 200, 50, 30);
		text1.setEnabled(false);
		attend.setBounds(150, 100, 75, 30);
		leave.setBounds(225, 100, 75, 30);
		showAttendance.setBounds(500, 100, 200, 30);
		vacation_start.setBounds(150, 400, 70, 30);
		to.setBounds(230, 400, 10, 30);
		vacation_end.setBounds(250, 400, 70, 30);
		Update.setBounds(650, 500, 100, 30);
		
		attend.addActionListener(this);
		leave.addActionListener(this);
//		Update.addActionListener(this);
		
		
		contents1[0][0] = "Attend";
		contents1[1][0] = "Leave";
		
		try {
			FindStaff();
			//makeTable1();
			showCount();
			makeTable2();
			vacation();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DefaultTableModel model1 = new DefaultTableModel(contents1, days1);
		table1 = new JTable(model1);
		table2 = new JTable(contents2, days2);
		
		table1.setEnabled(false);
		table2.setEnabled(false);
		JScrollPane scrollpane1 = new JScrollPane(table1);
		scrollpane1.setBounds(150,93,470,57);
		JScrollPane scrollpane2 = new JScrollPane(table2);
		scrollpane2.setBounds(150,300,470,41);
		
		
		panel.add(title);
		panel.add(hello);
		panel.add(category1);
		panel.add(category2);
		panel.add(category3);
		panel.add(category4);
		panel.add(text1);
//		panel.add(scrollpane1);
		panel.add(scrollpane2);
		panel.add(attend);
		panel.add(leave);
		panel.add(vacation_start);
		panel.add(to);
		panel.add(vacation_end);		
		panel.add(Update);
		panel.add(showAttendance);
		
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void FindStaff() throws SQLException{
		String department_id = "";
		String first_name = "";
		String last_name = "";
		
		String sqlStr = "SELECT department_id, first_name, last_name FROM staff WHERE id='"+staff_id+"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			department_id += rs.getString("department_id");
			first_name += rs.getString("first_name");
			last_name += rs.getString("last_name");
		}
		if (Integer.parseInt(department_id)==1) {
			hello.setText("Hello, CEO "+ first_name+ " " + last_name +"!");
		}else if (Integer.parseInt(department_id)==2) {
			hello.setText("Hello, "+ first_name+ " " + last_name +" of Front Desk.");
		}else if (Integer.parseInt(department_id)==3) {
			hello.setText("Hello, "+ first_name+ " " + last_name +" of Room Charge.");
		}else if (Integer.parseInt(department_id)==4) {
			hello.setText("Hello, "+ first_name+ " " + last_name +" of Cleaning.");
		}
		rs.next();		
	}
	
	public void makeTable1() throws SQLException{
		int day2 = 1;
		for(int i=1; i<=7; i++) {
			String attend_time = "";
			String leave_time = "";
				String sqlStr = "SELECT daily_attend_time, daily_leave_time FROM daily_status"
				+ " WHERE staff_id='"+staff_id+"' and daily_date='2018-12-"+ day2 +"'";	
				PreparedStatement stmt = connection.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				System.out.println(sqlStr);
				
				while(rs.next()) {
					attend_time += rs.getString("daily_attend_time");
					leave_time += rs.getString("daily_leave_time");
				}
				System.out.println("attend_time : " + attend_time);
				
				contents1[0][i] = attend_time;
				contents1[1][i] = leave_time;
				rs.next();	
				
				day2=day2+1;
			}
	}
	
	public void InputAttendTime() throws SQLException{
		long time_s = System.currentTimeMillis();
		SimpleDateFormat tmp_date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tmp_time = new SimpleDateFormat("hh:mm:ss");
		String date = tmp_date.format(time_s);
		String time = tmp_time.format(time_s);	
		System.out.println(date);
		System.out.println(time);
		
		String sqlStr = "INSERT INTO daily_status VALUES ("
		+staff_id+", '"+ date +"', '"+ time +"', NULL)";
		System.out.println(sqlStr);
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		//insert data ��� 
		stmt.executeUpdate(sqlStr);
		
		sqlStr = "select * from daily_status where staff_id='"+staff_id+"'";
		stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString("staff_id")+" ");
			System.out.print(rs.getString("daily_date")+" ");
			System.out.print(rs.getString("daily_attend_time")+" ");
			System.out.println(rs.getString("daily_leave_time")+" ");
		}	
	}
	
	public void InputLeaveTime() throws SQLException{
		long time_s = System.currentTimeMillis();
		SimpleDateFormat tmp_date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tmp_time = new SimpleDateFormat("hh:mm:ss");
		String date = tmp_date.format(time_s);
		String time = tmp_time.format(time_s);
		
		String sqlStr = "UPDATE daily_status SET daily_leave_time='"+ time 
				+"' WHERE staff_id='"+staff_id+"' and daily_date='"+ date +"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		stmt.executeUpdate(sqlStr);
		//System.out.println(sqlStr);
		
		//������ data ���
		sqlStr = "select * from daily_status where staff_id='"+staff_id+"'";
		stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString("staff_id")+" ");
			System.out.print(rs.getString("daily_date")+" ");
			System.out.print(rs.getString("daily_attend_time")+" ");
			System.out.println(rs.getString("daily_leave_time")+" ");
		}	
		
	}

	
	public void showCount() throws SQLException{
		
		String specification = "";
		
		String sqlStr = "SELECT late_count FROM status WHERE staff_id='"+staff_id+"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			specification += rs.getString("late_count");
		}
		
		text1.setText(specification);
		
		rs.next();
		
	}
	
	public void makeTable2() throws SQLException{
		String specification = "";
		
		String sqlStr = "select days from work where staff_id='"+staff_id+"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			specification += rs.getString("days");
		}
		
		String days = specification;
		if (days.equals("allday")) {
			for (int i=0; i<=6; i++) {
				contents2[0][i] = "O";	
			}
		}else if (days.equals("weekday")) {
			for (int i=0; i<=6; i++) {
				if (i<=4) {
					contents2[0][i] = "O";
				}else {
					contents2[0][i] = " ";
				}	
			}
		}else {
			for (int i=0; i<=6; i++) {
				if (i>4) {
					contents2[0][i] = "O";
				}else {
					contents2[0][i] = " ";
				}	
			}
		}
		rs.next();		
	}
	
	public void vacation() throws SQLException{
		String start_date = "";
		String end_date = "";
		
		String sqlStr = "SELECT start_date, end_date"
		+" FROM vacation WHERE staff_id='"+staff_id+"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		System.out.println(sqlStr);
		
		while(rs.next()) {
			start_date += rs.getString("start_date");
			end_date += rs.getString("end_date");
		}
		
		vacation_start.setText(start_date);
		vacation_end.setText(end_date);
		
		rs.next();
		
	}
	
	public void makenewTable() throws SQLException{
		int day2 = 1;
		for(int i=1; i<=7; i++) {
			String attend_time = "";
			String leave_time = "";
				String sqlStr = "SELECT daily_attend_time, daily_leave_time FROM daily_status"
				+ " WHERE staff_id='"+staff_id+"' and daily_date='2018-12-"+ day2 +"'";	
				PreparedStatement stmt = connection.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				System.out.println(sqlStr);
				
				while(rs.next()) {
					attend_time += rs.getString("daily_attend_time");
					leave_time += rs.getString("daily_leave_time");
				}
				System.out.println("attend_time : " + attend_time);
				
				contents1[0][i] = attend_time;
				contents1[1][i] = leave_time;
				rs.next();	
				
				day2=day2+1;
			}
	}

	public static void main(String[] args) {
		new TaskPersonalPage();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==attend) {
			try {
				InputAttendTime();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		else if(e.getSource()==leave){
			try {
				InputLeaveTime();
			}catch(SQLException se){
				se.printStackTrace();
			}			
		}
		else if(e.getSource()==Update) {
			try {
				System.out.println("Update");
				makenewTable();
				//makeTable1();	
				//table1 = new JTable(contents1, days1);
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
}

