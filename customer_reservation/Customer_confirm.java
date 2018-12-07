package HDMS2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Customer_confirm implements ActionListener {
	
	public JFrame main_frame = new JFrame();
	public JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel idddLabel = new JLabel();
    private JLabel login = new JLabel();
    private JLabel login2 = new JLabel();
    private JButton admitButton = new JButton("");
    
    static int reservationId;
    static String checkInDate = "181127";
    static String checkOutDate = "181127";
    static int customerId;
    
    JLabel backImage = new JLabel();
    
    public Customer_confirm() {
		backImage.setIcon(new ImageIcon("/Users/koserim/Desktop/confirm_back.png"));
		backImage.setBounds(0,0,800,600);
		
    	panel.setLayout(null);
        idddLabel.setBounds(400,289,200,100);
        login.setBounds(260,369,80,35);
        login2.setBounds(525,369,80,35);
        admitButton.setBounds(355,470,80,35);
		admitButton.setBorderPainted(false);
		admitButton.setFocusPainted(false);
		admitButton.setContentAreaFilled(false);

        idddLabel.setText(Integer.toString(reservationId));
        login.setText(checkInDate);
        login2.setText(checkOutDate);
 
        panel.add(idddLabel);
        panel.add(login);
        panel.add(login2);
        panel.add(admitButton);
        panel.add(backImage);
        
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