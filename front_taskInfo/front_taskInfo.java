
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class front_taskInfo implements ActionListener {

	private JFrame frame = new JFrame();
	private JLayeredPane layeredPane = new JLayeredPane();
	//private JPanel panel = new JPanel();
	//private JPanel InsertParking = new JPanel();
	//private JPanel pageButtonPanel = new JPanel();
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
	private JButton valuableButton = new JButton();
	private JButton parkingButton = new JButton();
	private JButton valuablepageButton = new JButton();
	private JButton parkingpageButton = new JButton();
	private JButton staffCheckButton = new JButton();
	private JButton billButton = new JButton();
	//private JTable table;
	
	Connection conn= null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	BufferedImage img = null;
	
	public static void main(String[] args) {
		new front_taskInfo();
	}

	public front_taskInfo() {			
		conn = mysqlConnection.dbconnector();
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		layeredPane.setBounds(0, 0, 800, 600);
		layeredPane.setLayout(null);
		
		//이미지 받아오기  
		try {
			img = ImageIO.read(new File("image/background.png"));
		}catch(IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}
		
		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 800, 600);
		
		//기능 구현 
		//BigPanel.setLayout(null);
		//BigPanel.setBounds(0, 0, 800, 600);
		
		//1. 귀중품 보관 입력 
		valuableLabel.setBounds(150, 100, 100, 30);
		valuableLabel.setFont(new Font("Courier", Font.BOLD, 15));
		valuableInput1.setBounds(150, 130, 100, 30);
		valuableInput2.setBounds(260, 130, 100, 30);
		valuableInput3.setBounds(370, 130, 100, 30);
		valuableInput4.setBounds(480, 130, 100, 30);
		valuableInput5.setBounds(150, 170, 100, 30);
		valuableInput6.setBounds(260, 170, 100, 30);
		valuableInput7.setBounds(370, 170, 100, 30);
		valuableInput8.setBounds(480, 170, 100, 30);
		valuableButton.setBounds(580, 140, 80, 50);
		
		valuableInput1.setText("맡긴물건");
		valuableInput2.setText("손님로그인ID");
		valuableInput3.setText("손님이름");
		valuableInput4.setText("사원번호");
		valuableInput5.setText("맡긴날짜");
		valuableInput6.setText("맡긴시간");
		valuableInput7.setText("돌려준날짜");
		valuableInput8.setText("돌려준시간");
		
		valuableInput1.setOpaque(false); //투명처리 
		valuableInput1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput2.setOpaque(false); //투명처리 
		valuableInput2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput3.setOpaque(false); //투명처리 
		valuableInput3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput4.setOpaque(false); //투명처리 
		valuableInput4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput5.setOpaque(false); //투명처리 
		valuableInput5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput6.setOpaque(false); //투명처리 
		valuableInput6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput7.setOpaque(false); //투명처리 
		valuableInput7.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableInput8.setOpaque(false); //투명처리 
		valuableInput8.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		valuableButton.setBorderPainted(false);
		valuableButton.setFocusPainted(false);
		valuableButton.setContentAreaFilled(false);
		
		
		layeredPane.add(valuableLabel);
		layeredPane.add(valuableInput1);
		layeredPane.add(valuableInput2);
		layeredPane.add(valuableInput3);
		layeredPane.add(valuableInput4);
		layeredPane.add(valuableInput5);
		layeredPane.add(valuableInput6);
		layeredPane.add(valuableInput7);
		layeredPane.add(valuableInput8);
		layeredPane.add(valuableButton);
		
		//2. 파킹 보관 입력 
		parkingLabel.setBounds(150, 230, 100, 30);
		parkingLabel.setFont(new Font("Courier", Font.BOLD, 15));
		parkingInput1.setBounds(150, 260, 100, 30);
		parkingInput2.setBounds(260, 260, 100, 30);
		parkingInput3.setBounds(370, 260, 100, 30);
		parkingInput4.setBounds(480, 260, 100, 30);
		parkingInput5.setBounds(150, 300, 100, 30);
		parkingInput6.setBounds(260, 300, 100, 30);
		parkingInput7.setBounds(370, 300, 100, 30);
		parkingInput8.setBounds(480, 300, 100, 30);
		parkingButton.setBounds(580, 270, 80, 50);
		
		parkingInput1.setText("맡긴차량번호");
		parkingInput2.setText("손님로그인ID");
		parkingInput3.setText("손님이름");
		parkingInput4.setText("사원번호");
		parkingInput5.setText("맡긴날짜");
		parkingInput6.setText("맡긴시간");
		parkingInput7.setText("돌려준날짜");
		parkingInput8.setText("돌려준시간");
		
		parkingInput1.setOpaque(false); //투명처리 
		parkingInput1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput2.setOpaque(false); //투명처리 
		parkingInput2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput3.setOpaque(false); //투명처리 
		parkingInput3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput4.setOpaque(false); //투명처리 
		parkingInput4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput5.setOpaque(false); //투명처리 
		parkingInput5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput6.setOpaque(false); //투명처리 
		parkingInput6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput7.setOpaque(false); //투명처리 
		parkingInput7.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingInput8.setOpaque(false); //투명처리 
		parkingInput8.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		parkingButton.setBorderPainted(false);
		parkingButton.setFocusPainted(false);
		parkingButton.setContentAreaFilled(false);
		
		layeredPane.add(parkingLabel);
		layeredPane.add(parkingInput1);
		layeredPane.add(parkingInput2);
		layeredPane.add(parkingInput3);
		layeredPane.add(parkingInput4);
		layeredPane.add(parkingInput5);
		layeredPane.add(parkingInput6);
		layeredPane.add(parkingInput7);
		layeredPane.add(parkingInput8);
		layeredPane.add(parkingButton);
		
		valuableButton.addActionListener(this);
		parkingButton.addActionListener(this);
		
		//3. 버튼 
		valuablepageButton.setBounds(185, 370, 100, 50);
		valuablepageButton.setBorderPainted(false);
		valuablepageButton.setFocusPainted(false);
		valuablepageButton.setContentAreaFilled(false);
		
		parkingpageButton.setBounds(295, 370, 100, 50);
		parkingpageButton.setBorderPainted(false);
		parkingpageButton.setFocusPainted(false);
		parkingpageButton.setContentAreaFilled(false);
		
		staffCheckButton.setBounds(405, 370, 120, 50);
		staffCheckButton.setBorderPainted(false);
		staffCheckButton.setFocusPainted(false);
		staffCheckButton.setContentAreaFilled(false);
		
		billButton.setBounds(515, 370, 100, 50);
		billButton.setBorderPainted(false);
		billButton.setFocusPainted(false);
		billButton.setContentAreaFilled(false);
		
		layeredPane.add(valuablepageButton);
		valuablepageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ValuablenewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		layeredPane.add(parkingpageButton);
		parkingpageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ParkingnewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		layeredPane.add(staffCheckButton);
		
		layeredPane.add(billButton);
		billButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new BillnewWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		layeredPane.add(panel);
		frame.add(layeredPane);
		frame.add(panel);
		
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
			getContentPane().setBackground(new java.awt.Color(164, 23, 28));

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
			getContentPane().setBackground(new java.awt.Color(164, 23, 28));

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
			getContentPane().setBackground(new java.awt.Color(164, 23, 28));

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
	
	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == valuableButton) {
			String content = valuableInput1.getText();
			String loginID = valuableInput2.getText();
			String customerName = valuableInput3.getText();
			int staffID = Integer.parseInt(valuableInput4.getText());
			String receiveDate = valuableInput5.getText();
			String receiveTime = valuableInput6.getText();
			String giveDate = valuableInput7.getText();
			String giveTime = valuableInput8.getText();
			
			try {
				conn = mysqlConnection.dbconnector();
				String query1 = "INSERT INTO valuable (content, custom_id) "
						+ "VALUES ("
						+ content + ", (SELECT customer_id from customer where id =" + loginID + "))";
				stmt = conn.prepareStatement(query1);
				rs = stmt.executeQuery(query1);
				stmt.close();
				rs.close();
				
				String query2 = "INSERT INTO valuable_staff VALUES ("
						+ "(SELECT max(id) FROM valuable), " + staffID + "," + receiveDate + "," 
						+ receiveTime + "," + giveDate + "," + giveTime + ")";
				stmt = conn.prepareStatement(query2);
				rs = stmt.executeQuery(query2);
				stmt.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == parkingButton) {
			String carNum = parkingInput1.getText();
			String loginID = parkingInput2.getText();
			String customerName = parkingInput3.getText();
			int staffID = Integer.parseInt(parkingInput4.getText());
			String receiveDate = parkingInput5.getText();
			String receiveTime = parkingInput6.getText();
			String giveDate = parkingInput7.getText();
			String giveTime = parkingInput8.getText();
			
			try {
				conn = mysqlConnection.dbconnector();
				String query1 = "INSERT INTO parking (content, customer_id) "
						+ "VALUES ("
						+ carNum + ", (SELECT customer_id from customer where id =" + loginID + "))";
				stmt = conn.prepareStatement(query1);
				rs = stmt.executeQuery(query1);
				stmt.close();
				rs.close();
				
				String query2 = "INSERT INTO valuable_staff VALUES ("
						+ "(SELECT max(id) FROM valuable), " + staffID + "," + receiveDate + "," 
						+ receiveTime + "," + giveDate + "," + giveTime + ")";
				stmt = conn.prepareStatement(query2);
				rs = stmt.executeQuery(query2);
				stmt.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
