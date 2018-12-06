package HDMS2;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
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
	private JLabel idLabel = new JLabel("My page");
	private JLabel pwdLabel = new JLabel("비밀번호");
    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginButton = new JButton("로그인");
    
    public Customer_enter_mypage() throws SQLException {
    	connect = new Connect();
    	Customer_mypage customer_mypage = new Customer_mypage();
    	mypage_frame = customer_mypage.frame;
    	
    	panel.setLayout(null);
    	idLabel.setBounds(360,10,300,300);
    	idLabel.setFont(new Font ("Courier", Font.BOLD, 18));
    	pwdLabel.setBounds(300, 270, 60, 30);
        pwdInput.setBounds(350, 270, 80, 30);
        loginButton.setBounds(450, 268, 80, 35);
        loginButton.addActionListener(this);
        panel.add(pwdLabel);
        panel.add(idLabel);
        panel.add(pwdInput);
        panel.add(loginButton);
        
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