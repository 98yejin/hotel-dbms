import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JDBC_Practice2 implements ActionListener {
	private JFrame mainframe = new JFrame();
	private JPanel mainpanel = new JPanel();
	private JLabel loginLabel = new JLabel("LOG-IN");
	private JLabel idLabel = new JLabel("ID");
	private JLabel pwdLabel = new JLabel("PASSWORD");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("LOGIN");
	private JButton joinButton = new JButton("REGIST");
	private JButton forgotButton = new JButton("LOST ID/PW?");
	// �̻� �α��� //
	private JFrame joinframe = new JFrame();
	private JPanel joinpanel = new JPanel();
	private JLabel joinLabel = new JLabel("REGISTRATION");
	private JLabel joinidLabel = new JLabel("* ID");
	private JButton joincheckButton = new JButton("CHECK");
	private JRadioButton joincheckRButton = new JRadioButton(""); // �ߺ�Ȯ�� �� �̻������ ����
	private JLabel joinpwdLabel = new JLabel("* PASSWORD");
	private JLabel joinconfLabel = new JLabel("* CONFIRM");
	private JLabel joinfnameLabel = new JLabel("* FIRST NAME");
	private JLabel joinlnameLabel = new JLabel("* LAST NAME");
	private JLabel joincphoneLabel = new JLabel("* CELL PHONE");
	private JLabel jointphoneLabel = new JLabel("TEMP PHONE");
	private JLabel joinbirthLabel = new JLabel("* BIRTHDAY");
	private JLabel joinmsgLabel = new JLabel("Must INPUT data on *.");
	private JTextField joinidInput = new JTextField();
	private JPasswordField joinpwdInput = new JPasswordField();
	private JPasswordField joinconfInput = new JPasswordField();
	private JTextField joinfnameInput = new JTextField();
	private JTextField joinlnameInput = new JTextField();
	private JTextField joincphoneInput = new JTextField();
	private JTextField jointphoneInput = new JTextField();
	private JTextField joinbirthInput = new JTextField();
	private JButton registButton = new JButton("REGIST");
	private JButton cancelButton = new JButton("CANCEL");
	// �̻� ȸ������ //
	
	public JDBC_Practice2() {
		mainpanel.setLayout(null);	
		// component��ġ����
		loginLabel.setBounds(370,105,60,50);
		idLabel.setBounds(255,165,90,30);
		pwdLabel.setBounds(255,215,90,30);
		idInput.setBounds(335,165,90,30);
		pwdInput.setBounds(335,215,90,30);
		loginButton.setBounds(445,165,80,70);
		joinButton.setBounds(265,265,110,30);
		forgotButton.setBounds(405,265,110,30);
		// ��ư�� ActionListener ����
		loginButton.addActionListener(this);
		joinButton.addActionListener(this);
		
		// component panel���߰�
		mainpanel.add(loginLabel);
		mainpanel.add(idLabel);
		mainpanel.add(pwdLabel);
		mainpanel.add(idInput);
		mainpanel.add(pwdInput);
		mainpanel.add(loginButton);
		mainpanel.add(joinButton);
		mainpanel.add(forgotButton);
		//Panel Frame�� �߰�		
		mainframe.add(mainpanel);
		
		mainframe.setTitle("LOGIN");  					// ������ ����̸�
		mainframe.setSize(800,600);  								// ������ ũ��
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // ����� System.exit() ȣ��
		mainframe.setVisible(true);								// frame ȭ�鿡 ǥ��	
	}
	
	private void Join() {
		
		joinpanel.setLayout(null);
		joinLabel.setBounds(350,0,100,40);
		joinidLabel.setBounds(200,55,110,35);
		joinpwdLabel.setBounds(200,105,110,35);
		joinconfLabel.setBounds(200,155,110,35);
		joinfnameLabel.setBounds(200,205,110,35);
		joinlnameLabel.setBounds(200,255,110,35);
		joincphoneLabel.setBounds(200,305,110,35);
		jointphoneLabel.setBounds(200,355,110,35);
		joinbirthLabel.setBounds(200,405,110,35);
		joinmsgLabel.setBounds(200,455,230,35);

		joinidInput.setBounds(300,55,200,35);
		joinpwdInput.setBounds(300,105,200,35);
		joinconfInput.setBounds(300,155,200,35);
		joinfnameInput.setBounds(300,205,200,35);
		joinlnameInput.setBounds(300,255,200,35);
		joincphoneInput.setBounds(300,305,200,35);
		jointphoneInput.setBounds(300,355,200,35);
		joinbirthInput.setBounds(300,405,200,35);

		joincheckButton.setBounds(530,55,90,35);
		joincheckRButton.setBounds(630,55,20,35);
		registButton.setBounds(300,500,90,40);
		cancelButton.setBounds(410,500,90,40);
		joincheckButton.addActionListener(this);
		registButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		joinpanel.add(joinLabel);
		joinpanel.add(joinidLabel);
		joinpanel.add(joinpwdLabel);
		joinpanel.add(joinconfLabel);
		joinpanel.add(joinfnameLabel);
		joinpanel.add(joinlnameLabel);
		joinpanel.add(joincphoneLabel);
		joinpanel.add(jointphoneLabel);
		joinpanel.add(joinbirthLabel);
		joinpanel.add(joinmsgLabel);
		
		joinpanel.add(joinidInput);
		joinpanel.add(joinpwdInput);
		joinpanel.add(joinconfInput);
		joinpanel.add(joinfnameInput);
		joinpanel.add(joinlnameInput);
		joinpanel.add(joincphoneInput);
		joinpanel.add(jointphoneInput);
		joinpanel.add(joinbirthInput);
		
		joinpanel.add(joincheckButton);
		joinpanel.add(joincheckRButton);
		joinpanel.add(registButton);
		joinpanel.add(cancelButton);
		
		joinframe.add(joinpanel);
		joinframe.setTitle("JOIN");
		joinframe.setSize(800,600);
		joinframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		joinframe.setVisible(true);	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) { // �α��� �������� 
			connectDB();
		}
		if (e.getSource() == joinButton) { // �α��� â���� ȸ������ ������ ��
			Join();
		}
		if (e.getSource() == forgotButton) { // �н� ������ ��
			
		}
		if (e.getSource() == joincheckButton) { // ȸ������ â���� ID �ߺ��˻� ������ ��
			String username = joinidInput.getText();
			String sqlStr1 = "select COUNT(ID) from Member where ID = '" + username + "'";
		}
//		if () { // ID �ߺ��˻�
//			joincheckRButton.setSelected(true); // RButton�� TRUE(üũ�ǵ���) ����
//		}
//			else {
//				JOptionPane.showMessageDialog(null, new JTextArea("���� ID�� �����մϴ�."));
//			}
		if (e.getSource() == registButton) {
			String password = new String(joinpwdInput.getPassword());
			String passwordconf = new String(joinconfInput.getPassword());
			String sqlStr2 = "insert into Member values (username,password)";
	
			JOptionPane.showMessageDialog(null, new JTextArea("ȯ���մϴ�!"));
		}
	
			
		
		if (e.getSource() == cancelButton) { // ȸ������ â���� ��� ������ ��
			joinframe.dispose();
		}
	}
	private static Connection Con;
	private void connectDB(){
		Connection con = null;
		
		String className = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/pbl?useSSL=false&useUnicode=true&characterEncoding=euckr";
		String username = idInput.getText();
		String password = new String(pwdInput.getPassword());
		
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Success");
		} catch(Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new JDBC_Practice2();
	}
}