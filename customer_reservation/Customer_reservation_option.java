package HDMS2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customer_reservation_option implements ActionListener {
	static Connection dbTest;
	PreparedStatement stmt;
	int rs;
	Connect connect;
	
	public JFrame frame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	private JLabel titleLabel = new JLabel("Option");
	private JLabel breakfastLabel = new JLabel("조식");
	private JLabel extraBedLabel = new JLabel("extra bed");
	private JLabel etcLabel = new JLabel("etc");
	private JTextField breakfastInput = new JTextField();
	private JTextField extraBedInput = new JTextField();
	private JTextField etcInput = new JTextField();
	private JButton plusButton = new JButton("+");
	private JButton plusButton2 = new JButton("+");
	private JButton minusButton = new JButton("-");
	private JButton minusButton2 = new JButton("-");
	private JButton reservationButton = new JButton("예약하기");
	int breakfastCount, extraBedCount;
	static String checkInDate = "181127";
	static String checkOutDate = "181129";
	static String roomNumber = "311";
	static int adultNumber = 1;
	static int childNumber = 0; 
	String etc;
	int customerId = 9;
	
	
	public Customer_reservation_option() {
		connect = new Connect();
		titlePanel.setLayout(null);
		titlePanel.setBounds(0, 0, 800, 80);
		
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 80, 800, 520);
		
		titleLabel.setBounds(40, 20, 150, 50);
		titleLabel.setFont(new Font("Courier", Font.BOLD, 30));
		breakfastLabel.setBounds(200, 200, 60, 30);
		minusButton.setBounds(300, 200, 30, 30);
		breakfastInput.setBounds(350, 200, 30, 30);
		breakfastInput.setText("0");
		breakfastInput.setEditable(false);
		plusButton.setBounds(400, 200, 30, 30);
		extraBedLabel.setBounds(200, 250, 60, 30);
		minusButton2.setBounds(300, 250, 30, 30);
		extraBedInput.setBounds(350, 250, 30, 30);
		extraBedInput.setText("0");
		extraBedInput.setEditable(false);
		plusButton2.setBounds(400, 250, 30, 30);
		etcLabel.setBounds(200, 300, 60, 30);
		etcInput.setBounds(300, 300, 200, 30);
		reservationButton.setBounds(600, 380, 130, 40);
		
		titlePanel.add(titleLabel);
		contentPanel.add(breakfastLabel);
		contentPanel.add(breakfastInput);
		contentPanel.add(plusButton);
		contentPanel.add(minusButton);
		contentPanel.add(plusButton2);
		contentPanel.add(minusButton2);
		contentPanel.add(extraBedLabel);
		contentPanel.add(extraBedInput);
		contentPanel.add(etcLabel);
		contentPanel.add(etcInput);
		contentPanel.add(reservationButton);
		
		frame.setTitle("My Page - Customer - Reservation - Option");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(titlePanel);
		frame.add(contentPanel);
		frame.setVisible(true);
		
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
				String sqlStr = "Insert INTO room_reservation("
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
