
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javafx.scene.text.Font;

public class front_taskInfo extends JFrame {

	private JFrame frame = new JFrame();
	private JPanel InsertValuable = new JPanel();
	private JPanel InsertParking = new JPanel();
	private JPanel pageButtonPanel = new JPanel();
	private JLabel valuableLabel = new JLabel("귀중품보관");
	private JLabel parkingLabel = new JLabel("파킹관리");
	private JTextField valuableInput1 = new JTextField(); //맡긴물건 
	private JTextField valuableInput2 = new JTextField(); //맡긴손님 로그인아이디  
	private JTextField valuableInput3 = new JTextField(); //맡긴손님 이름(first_name)
	private JTextField valuableInput4 = new JTextField(); //직원사원번호 
	private JTextField valuableInput5 = new JTextField(); //맡긴날짜 
	private JTextField valuableInput6 = new JTextField(); //맡긴시간 
	private JTextField valuableInput7 = new JTextField(); //돌려준날짜 
	private JTextField valuableInput8 = new JTextField(); //돌려준시간 
	private JTextField parkingInput1 = new JTextField(); //맡긴차량  
	private JTextField parkingInput2 = new JTextField(); //맡긴손님 로그인아이디 
	private JTextField parkingInput3 = new JTextField(); //맡긴손님 이름(first_name)
	private JTextField parkingInput4 = new JTextField(); //직원사원번호 
	private JTextField parkingInput5 = new JTextField(); //맡긴날짜 
	private JTextField parkingInput6 = new JTextField(); //맡긴시간 
	private JTextField parkingInput7 = new JTextField(); //돌려준날짜 
	private JTextField parkingInput8 = new JTextField(); //돌려준시간 
	private JButton valuableButton = new JButton("보관");
	private JButton parkingButton = new JButton("파킹");
	private JButton valuablepageButton = new JButton("Valuable");
	private JButton parkingpageButton = new JButton("Parking");
	private JButton staffCheckButton = new JButton("Staff Check");
	private JButton billButton = new JButton("   Bill  ");
	private JTable table;
	
	Connection conn= null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		new front_taskInfo();
	}

	public front_taskInfo() {
		conn = mysqlConnection.dbconnector();
		InsertValuable.setLayout(null);
		InsertValuable.setBounds(0, 0, 800, 160);
		//InsertParking.setLayout(null);
		//InsertParking.setBounds(0, 180, 800, 160);
		//pageButtonPanel.setLayout(null);
		//pageButtonPanel.setBounds(0, 360, 800, 100);
		
		valuableLabel.setBounds(105, 70, 60, 30);
		valuableInput1.setBounds(100, 100, 100, 30);
		valuableInput2.setBounds(210, 100, 100, 30);
		valuableInput3.setBounds(320, 100, 100, 30);
		valuableInput4.setBounds(430, 100, 100, 30);
		valuableInput5.setBounds(100, 130, 100, 30);
		valuableInput6.setBounds(210, 130, 100, 30);
		valuableInput7.setBounds(320, 130, 100, 30);
		valuableInput8.setBounds(430, 130, 100, 30);
		valuableButton.setBounds(540, 110, 80, 40);
		
		valuableInput1.setText("맡긴물건");
		valuableInput2.setText("손님로그인ID");
		valuableInput3.setText("손님이름");
		valuableInput4.setText("사원번호");
		valuableInput5.setText("맡긴날짜");
		valuableInput6.setText("맡긴시간");
		valuableInput7.setText("돌려준날짜");
		valuableInput8.setText("돌려준시간");
		
		InsertValuable.add(valuableLabel);
		InsertValuable.add(valuableInput1);
		InsertValuable.add(valuableInput2);
		InsertValuable.add(valuableInput3);
		InsertValuable.add(valuableInput4);
		InsertValuable.add(valuableInput5);
		InsertValuable.add(valuableInput6);
		InsertValuable.add(valuableInput7);
		InsertValuable.add(valuableInput8);
		InsertValuable.add(valuableButton);
		
		parkingLabel.setBounds(105, 180, 60, 30);
		parkingInput1.setBounds(100, 210, 100, 30);
		parkingInput2.setBounds(210, 210, 100, 30);
		parkingInput3.setBounds(320, 210, 100, 30);
		parkingInput4.setBounds(430, 210, 100, 30);
		parkingInput5.setBounds(100, 240, 100, 30);
		parkingInput6.setBounds(210, 240, 100, 30);
		parkingInput7.setBounds(320, 240, 100, 30);
		parkingInput8.setBounds(430, 240, 100, 30);
		parkingButton.setBounds(540, 220, 80, 40);
		
		parkingInput1.setText("맡긴차량번호");
		parkingInput2.setText("손님로그인ID");
		parkingInput3.setText("손님이름");
		parkingInput4.setText("사원번호");
		parkingInput5.setText("맡긴날짜");
		parkingInput6.setText("맡긴시간");
		parkingInput7.setText("돌려준날짜");
		parkingInput8.setText("돌려준시간");
		
		InsertValuable.add(parkingLabel);
		InsertValuable.add(parkingInput1);
		InsertValuable.add(parkingInput2);
		InsertValuable.add(parkingInput3);
		InsertValuable.add(parkingInput4);
		InsertValuable.add(parkingInput5);
		InsertValuable.add(parkingInput6);
		InsertValuable.add(parkingInput7);
		InsertValuable.add(parkingInput8);
		InsertValuable.add(parkingButton);
		
		valuablepageButton.setBounds(185, 370, 100, 30);
		parkingpageButton.setBounds(295, 370, 100, 30);
		staffCheckButton.setBounds(405, 370, 100, 30);
		billButton.setBounds(515, 370, 100, 30);
		
		InsertValuable.add(valuablepageButton);
		valuablepageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ValuablenewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		InsertValuable.add(parkingpageButton);
		parkingpageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ParkingnewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		InsertValuable.add(staffCheckButton);
		
		InsertValuable.add(billButton);
		billButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new BillnewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		frame.add(InsertValuable);
		//frame.add(InsertParking);
		//frame.add(pageButtonPanel);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	//귀중품보관창 
	class ValuablenewWindow extends JFrame{
		String[][] value;
		String valuable;
		String customerID;
		String customerName;
		String staffID;
		String receiveDate;
		String receiveTime;
		String giveDate;
		String giveTime;
		
		private JPanel NewWindowContainer = new JPanel();
		
		ValuablenewWindow() throws SQLException{
			setTitle("Valuable");
			this.makeTable();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);

		}
		
		public void getValuables() throws SQLException {
			try {
				conn = mysqlConnection.dbconnector();
				String query = "SELECT valuable.content, customer.login_id, customer.first_name, "
						+ "staff.id, valuable_staff.receive_date, valuable_staff.receive_time, "
						+ "valuable_staff.give_date, valuable_staff.give_time "
						+ "from (((valuable_staff "
						+ "inner join valuable on valuable.id = valuable_staff.valuable_id) "
						+ "inner join customer on valuable.custom_id = customer.id) "
						+ "inner join staff on valuable_staff.staff_id = staff.id) ";
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				int i = 1;
				while(rs.next()) {
					i++;
				}
				rs.close();
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				value = new String[i][8];
				i = 0;
				while(rs.next()) {
					valuable = rs.getString("content");
					customerID = rs.getString("login_id");
					customerName = rs.getString("first_name");
					staffID = rs.getString("id");
					receiveDate = rs.getString("receive_date");
					receiveTime = rs.getString("receive_time");
					giveDate = rs.getString("give_date");
					giveTime = rs.getString("give_time");
					value[i][0] = valuable;
					value[i][1] = customerID;
					value[i][2] = customerName;
					value[i][3] = staffID;
					value[i][4] = receiveDate;
					value[i][5] = receiveTime;
					value[i][6] = giveDate;
					value[i][7] = giveTime;
					i++;			
				}
				rs.close();
				stmt.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		public void makeTable() throws SQLException {
			this.getValuables();
			String[] menu = {"맡긴물건", "손님아이디", "손님이름", "사원번호", "맡긴날짜", "맡긴시간", "돌려준날짜", "돌려준시간"};
			DefaultTableModel model = new DefaultTableModel(value, menu) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			JTable Table = new JTable(model);
			JScrollPane scroll = new JScrollPane(Table);
			NewWindowContainer.add(scroll);
		}
		
	}
	
	//파킹창 
	class ParkingnewWindow extends JFrame{
		String[][] value;
		String car_num;
		String customerID;
		String customerName;
		String staffID;
		String receiveDate;
		String receiveTime;
		String giveDate;
		String giveTime;
		
		private JPanel NewWindowContainer = new JPanel();
		
		ParkingnewWindow() throws SQLException{
			setTitle("Parking");
			this.makeTable();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);

		}
		
		public void getValuables() throws SQLException {
			try {
				conn = mysqlConnection.dbconnector();
				String query = "SELECT parking.car_num, customer.login_id, customer.first_name, "
						+ "staff.id, parking_staff.receive_date, parking_staff.receive_time, "
						+ "parking_staff.give_date, parking_staff.give_time "
						+ "from (((parking_staff "
						+ "inner join parking on parking.id = parking_staff.parking_id) "
						+ "inner join customer on parking.customer_id = customer.id) "
						+ "inner join staff on parking_staff.staff_id = staff.id) ";
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				int i = 1;
				while(rs.next()) {
					i++;
				}
				rs.close();
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				value = new String[i][8];
				i = 0;
				while(rs.next()) {
					car_num = rs.getString("car_num");
					customerID = rs.getString("login_id");
					customerName = rs.getString("first_name");
					staffID = rs.getString("id");
					receiveDate = rs.getString("receive_date");
					receiveTime = rs.getString("receive_time");
					giveDate = rs.getString("give_date");
					giveTime = rs.getString("give_time");
					value[i][0] = car_num;
					value[i][1] = customerID;
					value[i][2] = customerName;
					value[i][3] = staffID;
					value[i][4] = receiveDate;
					value[i][5] = receiveTime;
					value[i][6] = giveDate;
					value[i][7] = giveTime;
					i++;			
				}
				rs.close();
				stmt.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		public void makeTable() throws SQLException {
			this.getValuables();
			String[] menu = {"맡긴차량번호", "손님아이디", "손님이름", "사원번호", "맡긴날짜", "맡긴시간", "돌려준날짜", "돌려준시간"};
			DefaultTableModel model = new DefaultTableModel(value, menu) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			JTable Table = new JTable(model);
			JScrollPane scroll = new JScrollPane(Table);
			NewWindowContainer.add(scroll);
		}
		
	}
	
	//총가격확인창 
	class BillnewWindow extends JFrame{
		String[][] value;
		String customerID;
		String customerName;
		String price;
		
		private JPanel NewWindowContainer = new JPanel();
		
		BillnewWindow() throws SQLException{
			setTitle("Bill");
			this.makeTable();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);

		}
		
		public void getValuables() throws SQLException {
			try {
				conn = mysqlConnection.dbconnector();
				String query = "select customer.login_id, customer.first_name, "
						+ "(room.price + (0.5*room_reservation.breakfast)) as price "
						+ "from ((room_reservation "
						+ "inner join customer on room_reservation.customer_id = customer.id) "
						+ "inner join room on room_reservation.room_id = room.room_number)";
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				int i = 1;
				while(rs.next()) {
					i++;
				}
				rs.close();
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				value = new String[i][3];
				i = 0;
				while(rs.next()) {
					customerID = rs.getString("login_id");
					customerName = rs.getString("first_name");
					price = rs.getString("price");
					value[i][0] = customerID;
					value[i][1] = customerName;
					value[i][2] = price;
					i++;			
				}
				rs.close();
				stmt.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		public void makeTable() throws SQLException {
			this.getValuables();
			String[] menu = {"손님아이디", "손님이름", "총가격"};
			DefaultTableModel model = new DefaultTableModel(value, menu) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			JTable Table = new JTable(model);
			JScrollPane scroll = new JScrollPane(Table);
			NewWindowContainer.add(scroll);
		}
		
	}

}
