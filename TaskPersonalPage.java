package TaskPersonalPage;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;



public class TaskPersonalPage implements ActionListener{
	
	Connection connection=null;
	int staff_id = 18020002; //���߿� �α��� ���� �޾ƿ;� ��
	
	private JFrame frame = new JFrame();
	private JLabel hello = new JLabel();
	private JLabel title = new JLabel("���� ��������");
	private JLabel category1 = new JLabel("���Ȯ��");
	private JLabel category2 = new JLabel("���Ƚ��(����,�Ἦ)");
	private JLabel category3 = new JLabel("�ٹ�����");
	private JLabel text = new JLabel("ȸ");
	private JPanel panel = new JPanel();
	private JTextField text1 = new JTextField();
	private JButton attend = new JButton("���");
	private JButton leave = new JButton("���");
	private JButton Update = new JButton("Update");
	String contents1[][] = new String[2][8];
	String contents2[][] = new String[1][7];
	
	
	public TaskPersonalPage() {
		
		connection = mysqlConnection.dbconnector();
		
		panel.setLayout(null);
		
		title.setBounds(50, 30, 100, 30);
		hello.setBounds(500, 30, 300, 30);
		category1.setBounds(50, 100, 100, 30);
		category2.setBounds(50, 200, 200, 30);
		category3.setBounds(50, 300, 200, 30);
		text1.setBounds(200, 200, 50, 30);
		text1.setEnabled(false);
		text.setBounds(255, 200, 100, 30);
		attend.setBounds(680, 90, 60, 30);
		leave.setBounds(680, 120, 60, 30);
		Update.setBounds(650, 500, 100, 30);
		
		attend.addActionListener(this);
		leave.addActionListener(this);
		
		String days1[] = {" ", "1", "2", "3", "4", "5", "6", "7"};
		String days2[] = {"��", "ȭ", "��", "��", "��", "��","��"};
		
		contents1[0][0] = "���";
		contents1[1][0] = "���";
		
		try {
			FindStaff();
			makeTable1();
			showCount();
			makeTable2();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		JTable table1 = new JTable(contents1, days1);
		JTable table2 = new JTable(contents2, days2);
		
		JScrollPane scrollpane1 = new JScrollPane(table1);
		scrollpane1.setBounds(200,93,470,57);
		JScrollPane scrollpane2 = new JScrollPane(table2);
		scrollpane2.setBounds(200,300,470,41);
		
		
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
		panel.add(Update);
		
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void FindStaff() throws SQLException{
		String department_id = "";
		String name = "";
		
		String sqlStr = "SELECT department_id, first_name, last_name FROM staff WHERE id='"+staff_id+"'";
		PreparedStatement stmt = connection.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			department_id += rs.getString("department_id");
			name += rs.getString("first_name");
			name += rs.getString("last_name");
		}
		if (Integer.parseInt(department_id)==1) {
			hello.setText("CEO "+ name +"�� �ȳ��ϼ���!");
		}else if (Integer.parseInt(department_id)==2) {
			hello.setText("Front desk �� "+ name +" �� �ȳ��ϼ���!");
		}else if (Integer.parseInt(department_id)==3) {
			hello.setText("Room Charge �� "+ name +" �� �ȳ��ϼ���!");
		}else if (Integer.parseInt(department_id)==4) {
			hello.setText("Cleaning �� "+ name +" �� �ȳ��ϼ���!");
		}
		rs.next();		
	}
	
	public void makeTable1() throws SQLException{
//		int day1 = 26;
		int day2 = 1;
		
		for(int i=1; i<=7; i++) {
			String attend_time = "";
			String leave_time = "";
//			if (i<=5) {				
//				String sqlStr = "SELECT daily_attend_time, daily_leave_time FROM daily_status"
//				+ " WHERE staff_id='"+staff_id+"' and daily_date='2018-11-"+ day1 +"'";	
//				PreparedStatement stmt = connection.prepareStatement(sqlStr);
//				ResultSet rs = stmt.executeQuery();
//				
//				while(rs.next()) {
//					attend_time += rs.getString("daily_attend_time");
//					leave_time += rs.getString("daily_leave_time");
//				}
//				
//				contents1[0][i] = attend_time;
//				contents1[1][i] = leave_time;
//				rs.next();	
//				
//				day1=day1+1;
//			}else {
				String sqlStr = "SELECT daily_attend_time, daily_leave_time FROM daily_status"
				+ " WHERE staff_id='"+staff_id+"' and daily_date='2018-12-"+ day2 +"'";	
				PreparedStatement stmt = connection.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					attend_time += rs.getString("daily_attend_time");
					leave_time += rs.getString("daily_leave_time");
				}
				
				contents1[0][i] = attend_time;
				contents1[1][i] = leave_time;
				rs.next();	
				
				day2=day2+1;
			}
//		}
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

	public static void main(String[] args) {
		new TaskPersonalPage();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==attend) {
			try {
				InputAttendTime();
				makeTable1();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		else if(e.getSource()==leave){
			try {
				InputLeaveTime();
				makeTable1();
			}catch(SQLException se){
				se.printStackTrace();
			}			
		}
		
	}
}

