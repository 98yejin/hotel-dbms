package HDMS2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Customer_main implements ActionListener {
	public JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton reservation_button = new JButton("예약");
	private JButton check_button = new JButton("예약 확인");
	private JButton mypage_button = new JButton("마이 페이지");
	
	public Customer_main() {
		panel.setLayout(null);
		frame.setTitle("My Page - Customer");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		reservation_button.setBounds(0, 5, 265, 570);
		check_button.setBounds(265, 5, 265, 570);
		mypage_button.setBounds(530, 5, 265, 570);
		
		reservation_button.addActionListener(this);
		
		panel.add(reservation_button);
		panel.add(check_button);
		panel.add(mypage_button);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Customer_main();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reservation_button) {

		}
	}
}
