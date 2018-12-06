package HDMS2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private JLabel idLabel = new JLabel("아이디");
	private JLabel iddLabel = new JLabel();
	private JLabel ppwdLabel = new JLabel("새 비밀번호");
	private JLabel pppwdLabel = new JLabel("새 비밀번호확인");
	private JLabel birthLabel = new JLabel("생년월일");
	private JLabel birthhLabel = new JLabel();
	private JLabel sexLabel = new JLabel("이름");
	private JLabel sexxLabel = new JLabel();
	private JLabel phoneLabel = new JLabel("연락처");
    private JLabel pphoneLabel = new JLabel("임시연락처");
    private JPasswordField pwdInput = new JPasswordField();
    private JTextField idInput = new JTextField();
    private JPasswordField ppwdInput = new JPasswordField();
    private JPasswordField pppwdInput = new JPasswordField();
    private JTextField birthInput = new JTextField();
    private JTextField sexInput = new JTextField();
    private JTextField phoneInput = new JTextField();
    private JTextField pphoneInput = new JTextField();
    private JLabel fail_Label = new JLabel("비밀번호가 일치하지 않습니다.");
    
    
    private JButton loginButton = new JButton("저장");
    private JButton loginButton2 = new JButton("취소");
    
    String login_id;
    String birthday;
    String name;

    public Customer_mypage() throws SQLException {
    	connect = new Connect();
    	
    	this.getName();
    	panel.setLayout(null);
    	idLabel.setBounds(100,100,80, 30);
    	iddLabel.setBounds(200,100,80,30);
    	
    	ppwdLabel.setBounds(100,150,80, 30);
    	pwdInput.setBounds(200, 150, 105, 30);
    	ppwdInput.setBounds(200,200,105,30);
    	pppwdLabel.setBounds(100, 200,100, 30 );
    	birthLabel.setBounds(100,250,80, 30);
    	birthhLabel.setBounds(200,250,80,30);
    	sexLabel.setBounds(100,300,80, 30);
    	sexxLabel.setBounds(200,300,80,30);
    	phoneLabel.setBounds(100,350,80, 30);
    	phoneInput.setBounds(200,350,105,30);
    	pphoneLabel.setBounds(100,400,80, 30);
    	pphoneInput.setBounds(200,400,105,30);
        loginButton.setBounds(300, 450, 80, 35);
        loginButton2.setBounds(500,450,80,35);
        
        iddLabel.setText(login_id);
        birthhLabel.setText(birthday);
        sexxLabel.setText(name);
        
        loginButton.addActionListener(this);
        loginButton2.addActionListener(this);
        
        panel.add(idLabel);
        panel.add(iddLabel);
        panel.add(ppwdLabel);
        panel.add(ppwdInput);
        panel.add(pppwdLabel);
        panel.add(pppwdInput);
        panel.add(birthLabel);
        panel.add(birthhLabel);
        panel.add(sexLabel);
        panel.add(sexxLabel);
        panel.add(phoneLabel);
        panel.add(phoneInput);
        panel.add(pphoneLabel);
        panel.add(pphoneInput);
        panel.add(pwdInput);
        panel.add(loginButton);
        panel.add(loginButton2);
       
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