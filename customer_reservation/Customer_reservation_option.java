package HDMS2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customer_reservation_option implements ActionListener {
	static Connection dbTest;
	PreparedStatement stmt;
	int rs;
	ResultSet rs2;
	Connect connect;
	
	public JFrame confirm_frame = new JFrame();
	public JFrame frame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	private JLabel titleLabel = new JLabel("Option");

	private JTextField breakfastInput = new JTextField();
	private JTextField extraBedInput = new JTextField();
	private JTextField etcInput = new JTextField();
	private JButton plusButton = new JButton("");
	private JButton plusButton2 = new JButton("");
	private JButton minusButton = new JButton("");
	private JButton minusButton2 = new JButton("");
	private JButton reservationButton = new JButton("");
	int breakfastCount, extraBedCount;
	static String checkInDate;
	static String checkOutDate;
	static String roomNumber;
	static int adultNumber;
	static int childNumber; 
	String etc;
	int customerId = 9;
	JLabel backImage = new JLabel();
	
	public Customer_reservation_option() {
		backImage.setIcon(new ImageIcon("/Users/koserim/Desktop/option_back.png"));
		backImage.setBounds(0,0,800,600);
		Color color1 = new Color(0xE6C4BF);
		frame.add(contentPanel);
		
		connect = new Connect();
		
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 00, 800, 600);
		
		titleLabel.setBounds(40, 20, 150, 50);
		titleLabel.setFont(new Font("Courier", Font.BOLD, 30));
		minusButton.setBounds(300, 225, 30, 30);
		minusButton.setBorderPainted(false);
		minusButton.setFocusPainted(false);
		minusButton.setContentAreaFilled(false);
		breakfastInput.setBounds(351, 229, 25, 24);
		breakfastInput.setText("0");
		breakfastInput.setEditable(false);
		breakfastInput.setBackground(color1);
		breakfastInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		plusButton.setBounds(400, 225, 30, 30);
		plusButton.setBorderPainted(false);
		plusButton.setFocusPainted(false);
		plusButton.setContentAreaFilled(false);
		minusButton2.setBounds(300, 275, 30, 30);
		minusButton2.setBorderPainted(false);
		minusButton2.setFocusPainted(false);
		minusButton2.setContentAreaFilled(false);
		extraBedInput.setBounds(351, 279, 25, 23);
		extraBedInput.setText("0");
		extraBedInput.setEditable(false);
		extraBedInput.setBackground(color1);
		extraBedInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		plusButton2.setBounds(400, 275, 30, 30);
		plusButton2.setBorderPainted(false);
		plusButton2.setFocusPainted(false);
		plusButton2.setContentAreaFilled(false);
		etcInput.setBounds(310, 327, 180, 20);
		etcInput.setBackground(color1);
		etcInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		reservationButton.setBounds(600, 400, 130, 40);
		reservationButton.setBorderPainted(false);
		reservationButton.setFocusPainted(false);
		reservationButton.setContentAreaFilled(false);
		
		contentPanel.add(breakfastInput);
		contentPanel.add(plusButton);
		contentPanel.add(minusButton);
		contentPanel.add(plusButton2);
		contentPanel.add(minusButton2);
		contentPanel.add(extraBedInput);
		contentPanel.add(etcInput);
		contentPanel.add(reservationButton);
		contentPanel.add(backImage);
		
		frame.setTitle("My Page - Customer - Reservation - Option");
		frame.setSize(800, 600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(false);
		
		plusButton.addActionListener(this);
		plusButton2.addActionListener(this);
		minusButton.addActionListener(this);
		minusButton2.addActionListener(this);
		reservationButton.addActionListener(this);
	}


	public static void main(String[] args) {
		new Customer_reservation_option();
		System.out.println(roomNumber);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == plusButton) {
			int temp = Integer.parseInt(breakfastInput.getText());
			temp = plus(temp);
			breakfastInput.setText(Integer.toString(temp));
		}
		if(e.getSource() == minusButton) {
			int temp = Integer.parseInt(breakfastInput.getText());
			if (temp == 0) {
				
			} else {
				temp = minus(temp);
				breakfastInput.setText(Integer.toString(temp));
			}
		}
		if(e.getSource() == plusButton2) {
			int temp = Integer.parseInt(extraBedInput.getText());
			temp = plus(temp);
			extraBedInput.setText(Integer.toString(temp));
		}
		if(e.getSource() == minusButton2) {
			int temp = Integer.parseInt(extraBedInput.getText());
			if (temp == 0) {
				
			} else {
				temp = minus(temp);
				extraBedInput.setText(Integer.toString(temp));
			}
		}
		if(e.getSource() == reservationButton) {
			breakfastCount = Integer.parseInt(breakfastInput.getText());
			extraBedCount = Integer.parseInt(extraBedInput.getText());
			etc = etcInput.getText();
			try {
				String sqlStr = "INSERT INTO room_reservation("
						+ "num_of_adult, num_of_children, check_in, check_out, "
						+ "breakfast, extra_bed, requirement, room_id, customer_id" 
						+ ") VALUES("
						+ adultNumber + ", " + childNumber + ", " + checkInDate + ", " + checkOutDate + ", "
						+ breakfastCount + ", "+ extraBedCount + ", '" + etc + "', "
						+ roomNumber + ", " + customerId + ")";
				System.out.println(sqlStr);
				stmt = dbTest.prepareStatement(sqlStr);
				rs = stmt.executeUpdate(sqlStr);
				System.out.println(rs>0? "성공" : "실패");
				stmt.close();
				
				String sqlStr2 = "SELECT max(id) FROM room_reservation";
				stmt = dbTest.prepareStatement(sqlStr2);
				rs2 = stmt.executeQuery(sqlStr2);
				int tempReservationId = 1;
				while (rs2.next()) {
					tempReservationId = rs2.getInt("max(id)");
				}
				stmt.close();
				rs2.close();
				
				Customer_confirm.reservationId = tempReservationId;
				System.out.println(tempReservationId);
				Customer_confirm.checkInDate = checkInDate;
				Customer_confirm.checkOutDate = checkOutDate;
				Customer_confirm customer_confirm = new Customer_confirm();
				confirm_frame = customer_confirm.frame;
				frame.setVisible(false);
				confirm_frame.setVisible(true);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
		}
	}
	
	public int plus(int num) {
		num = num + 1;
		return num;
	}
	
	public int minus(int num) {
		num = num - 1;
		return num;
	}
	
}
