package HDMS2;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Customer_enter_mypage implements ActionListener {
	static Connection dbTest;
	PreparedStatement stmt;
	ResultSet rs;
	Connect connect;
	
	public JFrame mypage_frame = new JFrame();
	public JFrame frame = new JFrame();
	private JPanel panel = new JPanel();

    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginButton = new JButton("");
    JLabel backImage = new JLabel();
    
    public Customer_enter_mypage() throws SQLException {
		backImage.setIcon(new ImageIcon("/Users/koserim/Desktop/enter_back.png"));
		backImage.setBounds(0,0,800,600);
		Color color1 = new Color(0xE6C4BF);
		
    	connect = new Connect();
    	Customer_mypage customer_mypage = new Customer_mypage();
    	mypage_frame = customer_mypage.frame;
    	
    	panel.setLayout(null);
        pwdInput.setBounds(330, 275, 120, 30);
		pwdInput.setBackground(color1);
		pwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        loginButton.setBounds(506, 262, 110, 50);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        
        loginButton.addActionListener(this);

        panel.add(pwdInput);
        panel.add(loginButton);
        panel.add(backImage);
        
        frame.add(panel);
        
        frame.setTitle("enter - mypage");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
    }
    
    public static void main(String[] args) throws SQLException {
    	new Customer_enter_mypage();
    }
    
    public void actionPerformed(ActionEvent e) {
		try {
			String sqlStr = "SELECT login_pw FROM customer WHERE login_id = 'kimjieun'";
			stmt = dbTest.prepareStatement(sqlStr);
			rs = stmt.executeQuery(sqlStr);
			if(e.getSource() == loginButton) {
	    		String pw1 = new String(pwdInput.getPassword());
	    		String srs = null;
				while(rs.next()) {
					srs = rs.getString("login_pw");
				}
				System.out.println(srs);
				StringBuffer pw2 = new StringBuffer(srs);
				if (pw1.contentEquals(pw2)) {	   
	    			frame.setVisible(false);
	    			mypage_frame.setVisible(true);
				}
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
    }

}