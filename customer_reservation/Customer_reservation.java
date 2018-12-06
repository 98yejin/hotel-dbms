package HDMS2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Customer_reservation implements ActionListener {
	
	static Connection dbTest;
	PreparedStatement stmt;
	ResultSet rs;
	Connect connect;
	
	public JFrame option_frame = new JFrame();
	
	public JFrame frame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JLabel mainLabel = new JLabel("예약");
	private JLabel checkInLabel = new JLabel("체크인");
	private JLabel checkOutLabel = new JLabel("체크아웃");
	private JLabel adultLabel = new JLabel("성인");
	private JLabel childLabel = new JLabel("어린이");
	private JButton searchButton = new JButton("검색");
	private JButton nextButton = new JButton("NEXT");
	private JTextField checkInInput = new JTextField();
	private JTextField checkOutInput = new JTextField();
	private JComboBox<String> adultBox = new JComboBox<String>();
	private JComboBox<String> childBox = new JComboBox<String>();
	
	int customerCount = 0;
	String checkInDate;
	String checkOutDate;
	String roomNumber;
	String grade;
	String bedType;
	String view;
	int capacity;
	int price;
	String[][] value;
	String tempRoomNumber;
	String tempAdult;
	String tempChild;
	
	public Customer_reservation() throws SQLException {
		connect = new Connect();
		
		titlePanel.setLayout(null);
		titlePanel.setBounds(0, 0, 800, 80);
		
		searchPanel.setLayout(null);
		searchPanel.setBounds(0, 80, 800, 140);
		
		listPanel.setLayout(null); 
		listPanel.setBounds(0, 220, 800, 600);
		
		mainLabel.setBounds(40, 20, 60, 50);
		mainLabel.setFont(new Font("Courier", Font.BOLD, 30));
		checkInLabel.setBounds(50, 50, 60, 30);
		checkInLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		checkInInput.setBounds(90,50,100,30);
		
		checkOutLabel.setBounds(200, 50, 60, 30);
		checkOutLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		checkOutInput.setBounds(255,50,100,30);
		
		adultLabel.setBounds(380, 50, 40, 30);    
		adultLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		adultBox.setBounds(430,50,60,30);
		childLabel.setBounds(530, 50, 60, 30);
		childLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		childBox.setBounds(580,50,60,30);
		searchButton.setBounds(670, 40, 80, 40);
		nextButton.setBounds(600, 480, 100, 40);
		
		adultBox.addItem("1");
		adultBox.addItem("2");
		childBox.addItem("0");
		childBox.addItem("1");
		childBox.addItem("2");

		searchButton.addActionListener(this);
		nextButton.addActionListener(this);
		
		titlePanel.add(mainLabel);
		searchPanel.add(checkInLabel);
		searchPanel.add(checkInInput);
		searchPanel.add(checkOutLabel);
		searchPanel.add(checkOutInput);
		searchPanel.add(adultLabel);
		searchPanel.add(childLabel);
		searchPanel.add(searchButton);
		searchPanel.add(adultBox);
		searchPanel.add(childBox);
		
		listPanel.add(nextButton);
		listPanel.setVisible(false);
		
		frame.setTitle("My Page - Customer - Reservation");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(titlePanel);
		frame.add(searchPanel);
		frame.add(listPanel);
		frame.setVisible(false);
	}
	
	public static void main(String[] args) throws SQLException {
		new Customer_reservation();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			listPanel.setVisible(true);			
			checkInDate = checkInInput.getText();
			checkOutDate = checkOutInput.getText();
			customerCount = Integer.parseInt((String) adultBox.getSelectedItem()) + 
					Integer.parseInt((String) childBox.getSelectedItem());
			try {
				this.makeTable();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == nextButton) {
			Customer_reservation_option customer_reservation_option = new Customer_reservation_option();
			Customer_reservation_option.roomNumber = tempRoomNumber;
			Customer_reservation_option.checkInDate = checkInDate;
			Customer_reservation_option.checkOutDate = checkOutDate;
			Customer_reservation_option.adultNumber = Integer.parseInt((String) adultBox.getSelectedItem());
			Customer_reservation_option.childNumber = Integer.parseInt((String) childBox.getSelectedItem());
			option_frame = customer_reservation_option.frame;
			frame.setVisible(false);
			option_frame.setVisible(true);
		}
	}
    
	public void getRooms() throws SQLException {
		try {
			String sqlStr = "SELECT room_number, grade, bed_type, view, capacity, price FROM room WHERE capacity >= "+ customerCount
					+ " and room_number NOT IN ( SELECT DISTINCT room_id FROM room_reservation "
					+ "WHERE ( date(check_in) <= date("+ checkInDate+ ") and date(check_out) > date("+ checkInDate+")) "
					+ "or ( date(check_in) < date("+ checkOutDate+ ") and date(check_out) >= date("+ checkOutDate+ ")) )";

			stmt = dbTest.prepareStatement(sqlStr);
			rs = stmt.executeQuery(sqlStr);
			if (rs.next() == false) {
				ErrorMessage er = new ErrorMessage();
			} else {
				int i = 1;
				while(rs.next()) {
					i++;
				}
				value = new String[i][6];
				rs.close();
				stmt = dbTest.prepareStatement(sqlStr);
				rs = stmt.executeQuery(sqlStr);
				i = 0;
				while(rs.next()) {
					roomNumber = rs.getString("room_number");
					grade = rs.getString("grade");
					bedType = rs.getString("bed_type");
					view = rs.getString("view");
					capacity = rs.getInt("capacity");
					price = rs.getInt("price") * 10000 ;
					value[i][0] = roomNumber;
					value[i][1] = grade;
					value[i][2] = bedType;
					value[i][3] = view;
					value[i][4] = Integer.toString(capacity);
					value[i][5] = Integer.toString(price);
					i++;			
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void makeTable() throws SQLException {
		this.getRooms();
		String[] menu = {"번호","객실 등급", "침대 타입", "view", "수용 인원", "가격(원)"};
		DefaultTableModel model = new DefaultTableModel(value, menu) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable roomTable = new JTable(model);
		JScrollPane scroll = new JScrollPane(roomTable);
		scroll.setBounds(100, 200, 600, 250);
		roomTable.setFont(new Font("Courier", Font.PLAIN, 15));
		roomTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					tempRoomNumber = roomTable.getValueAt(roomTable.getSelectedRow(), 0).toString();
				}
			}
		});
		listPanel.add(scroll);
	}

}
