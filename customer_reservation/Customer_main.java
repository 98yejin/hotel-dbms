package HDMS2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Customer_main implements ActionListener {
	
	public JFrame reservation_frame = new JFrame();
	public JFrame check_frame = new JFrame();
	public JFrame mypage_frame = new JFrame();
	
	public JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton reservation_button = new JButton("예약");
	private JButton check_button = new JButton("예약 확인");
	private JButton mypage_button = new JButton("마이 페이지");
	JLabel backImage = new JLabel();
	
	public Customer_main() throws SQLException {
		backImage.setIcon(new ImageIcon("/Users/koserim/Desktop/main_back.png"));
		backImage.setBounds(0,0,800,600);
		
		Customer_reservation customer_reservation = new Customer_reservation();
		Customer_check customer_check = new Customer_check();
		Customer_enter_mypage customer_enter_mypage = new Customer_enter_mypage();
		
		reservation_frame = customer_reservation.frame;
		check_frame = customer_check.frame;
		mypage_frame = customer_enter_mypage.frame;
		
		panel.setLayout(null);
		frame.setTitle("My Page - Customer");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		reservation_button.setBounds(40, 80, 230, 430);
		check_button.setBounds(285, 80, 230, 430);
		mypage_button.setBounds(530, 80, 230, 430);
		
		reservation_button.addActionListener(this);
		check_button.addActionListener(this);
		mypage_button.addActionListener(this);
		
		panel.add(reservation_button);
		panel.add(check_button);
		panel.add(mypage_button);
		panel.setOpaque(false);
		
		frame.add(backImage);
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws SQLException {
		new Customer_main();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reservation_button) {
			frame.setVisible(false);
			reservation_frame.setVisible(true);
		} else if(e.getSource() == check_button) {
			frame.setVisible(false);
			check_frame.setVisible(true);
		} else if(e.getSource() == mypage_button) {
			frame.setVisible(false);
			mypage_frame.setVisible(true);
		}
	}
}
