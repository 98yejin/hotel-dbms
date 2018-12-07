package HDMS2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Customer_mypage implements ActionListener {
	static Connection dbTest;
	PreparedStatement stmt;
	Statement stmt2;
	ResultSet rs;
	Connect connect;
	
	public JFrame main_frame = new JFrame();
	
	public JFrame frame = new JFrame();
	public JFrame fail_frame = new JFrame();
	private JPanel panel = new JPanel();
	private JPanel fail_panel = new JPanel();
	private JLabel iddLabel = new JLabel();
	private JLabel birthhLabel = new JLabel();
	private JLabel sexxLabel = new JLabel();
    private JPasswordField pwdInput = new JPasswordField();
    private JTextField idInput = new JTextField();
    private JPasswordField ppwdInput = new JPasswordField();
    private JPasswordField pppwdInput = new JPasswordField();
    private JTextField birthInput = new JTextField();
    private JTextField sexInput = new JTextField();
    private JTextField phoneInput = new JTextField();
    private JTextField pphoneInput = new JTextField();
    private JLabel fail_Label = new JLabel("비밀번호가 일치하지 않습니다.");
    
    private JButton loginButton = new JButton("");
    private JButton loginButton2 = new JButton("");
    JLabel backImage = new JLabel();
    
    String login_id;
    String birthday;
    String name;

    public Customer_mypage() throws SQLException {
		backImage.setIcon(new ImageIcon("/Users/koserim/Desktop/mypage_back.png"));
		backImage.setBounds(0,0,800,600);
		Color color1 = new Color(0xE6C4BF);
    	connect = new Connect();
    	
    	this.getName();
    	panel.setLayout(null);
    	iddLabel.setBounds(320,128,100,30);
    	iddLabel.setFont(new Font("Courier", Font.PLAIN, 17));
    	iddLabel.setForeground(color1);
    	
    	pwdInput.setBounds(320, 175, 130, 30);
    	pwdInput.setBackground(color1);
    	pwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    	ppwdInput.setBounds(320, 220, 130, 30);
    	ppwdInput.setBackground(color1);
    	ppwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());

    	birthhLabel.setBounds(320,270,150,30);
    	birthhLabel.setFont(new Font("Courier", Font.PLAIN, 17));
    	birthhLabel.setForeground(color1);
 
    	sexxLabel.setBounds(320,315,150,30);
    	sexxLabel.setFont(new Font("Courier", Font.PLAIN, 17));
    	sexxLabel.setForeground(color1);
  
    	phoneInput.setBounds(320,360,105,30);
    	phoneInput.setBackground(color1);
    	phoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());

    	pphoneInput.setBounds(320,410,105,30);
    	pphoneInput.setBackground(color1);
    	pphoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        loginButton.setBounds(260, 480, 110, 50);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton2.setBounds(440, 480, 110, 50);
        loginButton2.setBorderPainted(false);
        loginButton2.setFocusPainted(false);
        loginButton2.setContentAreaFilled(false);
        
        iddLabel.setText(login_id);
        birthhLabel.setText(birthday);
        sexxLabel.setText(name);
        
        loginButton.addActionListener(this);
        loginButton2.addActionListener(this);
        

        panel.add(iddLabel);
        panel.add(ppwdInput);
        panel.add(pppwdInput);
        panel.add(birthhLabel);
        panel.add(sexxLabel);
        panel.add(phoneInput);
        panel.add(pphoneInput);
        panel.add(pwdInput);
        panel.add(loginButton);
        panel.add(loginButton2);
        panel.add(backImage);
       
        frame.add(panel);
        
        frame.setTitle("mypage");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
    }
    
    public static void main(String[] args) throws SQLException {
    	new Customer_mypage();
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == loginButton) {
    		// 저장 버튼 눌렀을 때 일어나는 거
    		String pw1 = new String(pwdInput.getPassword());
    		String pw2 = new String(ppwdInput.getPassword());
    		String cp1 = new String(phoneInput.getText());
    		String cp2 = new String(pphoneInput.getText());
    	   if (pw1.contentEquals(pw2)) {	   
    			try {				
    				String sqlStr = "update customer set login_pw = '"+pw1+ "', cell_phone_number = " + cp1 + ", temp_phone_number = " + cp2 + " WHERE login_id = 'kimjieun';";
    				stmt = dbTest.prepareStatement(sqlStr);
    				int rs1 = stmt.executeUpdate(sqlStr);
    				System.out.println(rs1 > 0 ? "success" : "fail");
    				stmt.close();
    				frame.setVisible(false);
    		    	Customer_main customer_main = new Customer_main();
    		    	main_frame = customer_main.frame;
    				main_frame.setVisible(true);
    			}
    			
    			catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    	   } else {
    		   fail_panel.setLayout(null);
    		   fail_Label.setBounds(150,60,200,30);
    		   fail_panel.add(fail_Label);
    		   fail_frame.add(fail_panel);
    		   fail_frame.setTitle("Login Fail");
    		   fail_frame.setSize(450, 200);
    		   fail_frame.setVisible(true);
    
    	   }
    	} else if (e.getSource() == loginButton2) {
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
    
    public void getName() throws SQLException {
		try {
			String sqlStr = "SELECT login_id, birthday, first_name, last_name FROM customer WHERE login_id = 'kimjieun'";
			stmt = dbTest.prepareStatement(sqlStr);
			rs = stmt.executeQuery(sqlStr);
			while(rs.next()) {
				login_id = rs.getString("login_id");
				birthday = rs.getString("birthday");
				name = rs.getString("first_name") + " " +rs.getString("last_name");
				
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}


}