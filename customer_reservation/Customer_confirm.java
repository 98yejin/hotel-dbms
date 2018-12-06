package HDMS2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Customer_confirm implements ActionListener {
	
	public JFrame main_frame = new JFrame();
	public JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel idLabel = new JLabel("예약이 완료되었습니다.");
	private JLabel iddLabel = new JLabel("예약번호");
	private JLabel idddLabel = new JLabel();
    private JLabel loginButton = new JLabel("Check In");
    private JLabel login = new JLabel();
    private JLabel loginButton2 = new JLabel("Check Out");
    private JLabel login2 = new JLabel();
    private JButton admitButton = new JButton("확인");
    
    static int reservationId;
    static String checkInDate;
    static String checkOutDate;
    static int customerId;
    
    public Customer_confirm() {
    	panel.setLayout(null);
    	idLabel.setBounds(300,100,400,200);
    	idLabel.setFont(new Font("Courier", Font.BOLD, 20));
        iddLabel.setBounds(340,270,200,100);
        idddLabel.setBounds(400,270,200,100);
        loginButton.setBounds(190, 350, 80, 35);
        login.setBounds(260,350,80,35);
        loginButton2.setBounds(450,350,80,35);
        login2.setBounds(525,350,80,35);
        admitButton.setBounds(355,450,80,35);
        System.out.println("hi");
        idddLabel.setText(Integer.toString(reservationId));
        login.setText(checkInDate);
        login2.setText(checkOutDate);
 
        panel.add(idLabel);
        panel.add(iddLabel);
        panel.add(idddLabel);
        panel.add(loginButton);
        panel.add(login);
        panel.add(loginButton2);
        panel.add(login2);
        panel.add(admitButton);
        
        admitButton.addActionListener(this);
        
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
    }
    
    public static void main(String[] args) {
    	new Customer_confirm();
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == admitButton) {
			try {
				frame.setVisible(false);
				Customer_main customer_main = new Customer_main();
		    	main_frame = customer_main.frame;
				main_frame.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}