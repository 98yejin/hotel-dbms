import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class login implements ActionListener {
	private JFrame cusloginframe = new JFrame();
	private JPanel cusloginpanel = new JPanel();
	private JLabel cusloginLabel = new JLabel("LOG-IN");
	private JLabel cusidLabel = new JLabel("ID");
	private JLabel cuspwdLabel = new JLabel("PASSWORD");
	private JTextField cusidInput = new JTextField();
	private JPasswordField cuspwdInput = new JPasswordField();
	private JButton cusloginButton = new JButton("LOGIN");
	private JButton cusjoinButton = new JButton("REGIST");
	private JButton cusforgotButton = new JButton("LOST ID/PW?");
	// 이상 로그인 //
	private JFrame cusjoinframe = new JFrame();
	private JPanel cusjoinpanel = new JPanel();
	private JLabel cusjoinLabel = new JLabel("REGISTRATION");
	private JLabel cusjoinidLabel = new JLabel("* ID");
	private JLabel cusjoinpwdLabel = new JLabel("* PASSWORD");
	private JLabel cusjoinconfLabel = new JLabel("* CONFIRM");
	private JLabel cusjoinfnameLabel = new JLabel("* FIRST NAME");
	private JLabel cusjoinlnameLabel = new JLabel("* LAST NAME");
	private JLabel cusjoincphoneLabel = new JLabel("* CELL PHONE");
	private JLabel cusjointphoneLabel = new JLabel("TEMP PHONE");
	private JLabel cusjoinbirthLabel = new JLabel("* BIRTHDAY");
	private JLabel cusjoinmsgLabel = new JLabel("Must INPUT data on *.");
	private JLabel cusjoinidcau = new JLabel("ID는 10자 이하");
	private JLabel cusjoinpwdcau = new JLabel("비밀번호는 20자 이하");
	private JLabel cusjoinconfcau = new JLabel("비밀번호와 일치시켜주세요.");
	private JLabel cusjoinfnamecau = new JLabel("성은 25자 이하");
	private JLabel cusjoinlnamecau = new JLabel("이름은 25자 이하");
	private JLabel cusjoinphonecau = new JLabel("전화번호는 - 없이 11자리");
	private JLabel cusjoinbirthcau = new JLabel("생년월일은 기호없이 6자리");
	
	private JTextField cusjoinidInput = new JTextField();
	private JPasswordField cusjoinpwdInput = new JPasswordField();
	private JPasswordField cusjoinconfInput = new JPasswordField();
	private JTextField cusjoinfnameInput = new JTextField();
	private JTextField cusjoinlnameInput = new JTextField();
	private JTextField cusjoincphoneInput = new JTextField();
	private JTextField cusjointphoneInput = new JTextField();
	private JTextField cusjoinbirthInput = new JTextField();
	private JButton cusregistButton = new JButton("REGIST");
	private JButton cusjoincancelButton = new JButton("CANCEL");
	// 이상 회원가입 //
	
	private JFrame cusforgotframe = new JFrame();
	private JPanel cusforgotpanel = new JPanel();
	private JLabel cusforgotlabel = new JLabel("FIND ID/PW");
	private JLabel cusforgotfnamelabel = new JLabel("FIRST NAME");
	private JLabel cusforgotlnamelabel = new JLabel("LAST NAME");
	private JLabel cusforgotcphonelabel = new JLabel("CELL PHONE NUM");
	
	private JTextField cusforgotfnameInput = new JTextField();
	private JTextField cusforgotlnameInput = new JTextField();
	private JTextField cusforgotcphoneInput = new JTextField();
	private JButton cusfindButton = new JButton("FIND");
	private JButton cusfindcancelButton = new JButton("CANCEL");
	// 이상 ID/PW찾기 //
	
	// 이상 고객 //
	
	private JFrame mainframe = new JFrame();
	private JPanel mainpanel = new JPanel();
	private JButton selcus = new JButton("CUSTOMER LOGIN");
	private JButton selsta = new JButton("STAFF LOGIN");
	
	// 이상 메인 //
	
	private JFrame staloginframe = new JFrame();
	private JPanel staloginpanel = new JPanel();
	private JLabel staloginLabel = new JLabel("LOG-IN");
	private JLabel staidLabel = new JLabel("ID");
	private JLabel stapwdLabel = new JLabel("PASSWORD");
	private JTextField staidInput = new JTextField();
	private JPasswordField stapwdInput = new JPasswordField();
	private JButton staloginButton = new JButton("LOGIN");
	private JButton staforgotButton = new JButton("LOST PW?");
	
	// 이상 로그인 //
	
	private JFrame staforgotframe = new JFrame();
	private JPanel staforgotpanel = new JPanel();
	private JLabel staforgotlabel = new JLabel("FIND PW");
	private JLabel staforgotidlabel = new JLabel("ID");
	private JLabel staforgotfnamelabel = new JLabel("FIRST NAME");
	private JLabel staforgotlnamelabel = new JLabel("LAST NAME");
	private JLabel staforgotcphonelabel = new JLabel("CELL PHONE NUM");
	private JTextField staforgotidInput = new JTextField();
	private JTextField staforgotfnameInput = new JTextField();
	private JTextField staforgotlnameInput = new JTextField();
	private JTextField staforgotcphoneInput = new JTextField();
	private JButton stafindButton = new JButton("FIND");
	private JButton stafindcancelButton = new JButton("CANCEL");
	// 이상 PW찾기 //
	
	// 이상 직원 //
	
	public login() { // 메인화면(스태프,고객구분)
		connectDB();
		
		mainpanel.setLayout(null);
		selcus.setBounds(20,20,360,500);
		selsta.setBounds(400,20,360,500);
		
		mainpanel.add(selcus);
		mainpanel.add(selsta);
		selcus.addActionListener(this);
		selsta.addActionListener(this);
		
		mainframe.add(mainpanel);
		
		mainframe.setTitle("SELECT POSITION");  					// 프레임 상단이름
		mainframe.setSize(800,600);  								// 프레임 크기
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 종료시 System.exit() 호출
		mainframe.setVisible(true);	
		
	}
	
	private void customerlogin() { // 고객로그인
		
		cusloginpanel.setLayout(null);	
		// component위치지정
		cusloginLabel.setBounds(370,105,60,50);
		cusidLabel.setBounds(255,165,90,30);
		cuspwdLabel.setBounds(255,215,90,30);
		cusidInput.setBounds(335,165,90,30);
		cuspwdInput.setBounds(335,215,90,30);
		cusloginButton.setBounds(445,165,80,70);
		cusjoinButton.setBounds(265,265,110,30);
		cusforgotButton.setBounds(405,265,110,30);
		// 버튼에 ActionListener 연결
		cusloginButton.addActionListener(this);
		cusjoinButton.addActionListener(this);
		cusforgotButton.addActionListener(this);
		
		// component panel에추가
		cusloginpanel.add(cusloginLabel);
		cusloginpanel.add(cusidLabel);
		cusloginpanel.add(cuspwdLabel);
		cusloginpanel.add(cusidInput);
		cusloginpanel.add(cuspwdInput);
		cusloginpanel.add(cusloginButton);
		cusloginpanel.add(cusjoinButton);
		cusloginpanel.add(cusforgotButton);
		//Panel Frame에 추가		
		cusloginframe.add(cusloginpanel);
		
		cusloginframe.setTitle("CUSTOMER LOGIN");  					// 프레임 상단이름
		cusloginframe.setSize(800,600);  								// 프레임 크기
		cusloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // 종료시 System.exit() 호출
		cusloginframe.setVisible(true);								// frame 화면에 표시	
	}
		
	private void customerJoin() { // 고객회원가입
		
		cusjoinpanel.setLayout(null);
		cusjoinLabel.setBounds(350,0,100,40);
		cusjoinidLabel.setBounds(200,55,110,35);
		cusjoinpwdLabel.setBounds(200,105,110,35);
		cusjoinconfLabel.setBounds(200,155,110,35);
		cusjoinfnameLabel.setBounds(200,205,110,35);
		cusjoinlnameLabel.setBounds(200,255,110,35);
		cusjoincphoneLabel.setBounds(200,305,110,35);
		cusjointphoneLabel.setBounds(200,355,110,35);
		cusjoinbirthLabel.setBounds(200,405,110,35);
		cusjoinmsgLabel.setBounds(200,455,230,35);
		
		cusjoinidcau.setBounds(520,55,200,35);
		cusjoinpwdcau.setBounds(520,105,200,35);
		cusjoinconfcau.setBounds(520,155,200,35);
		cusjoinfnamecau.setBounds(520,205,200,35);
		cusjoinlnamecau.setBounds(520,255,200,35);
		cusjoinphonecau.setBounds(520,330,200,35);
		cusjoinbirthcau.setBounds(520,405,200,35);

		cusjoinidInput.setBounds(300,55,200,35);
		cusjoinpwdInput.setBounds(300,105,200,35);
		cusjoinconfInput.setBounds(300,155,200,35);
		cusjoinfnameInput.setBounds(300,205,200,35);
		cusjoinlnameInput.setBounds(300,255,200,35);
		cusjoincphoneInput.setBounds(300,305,200,35);
		cusjointphoneInput.setBounds(300,355,200,35);
		cusjoinbirthInput.setBounds(300,405,200,35);

		cusregistButton.setBounds(300,500,90,40);
		cusjoincancelButton.setBounds(410,500,90,40);
		cusregistButton.addActionListener(this);
		cusjoincancelButton.addActionListener(this);
		
		cusjoinpanel.add(cusjoinLabel);
		cusjoinpanel.add(cusjoinidLabel);
		cusjoinpanel.add(cusjoinpwdLabel);
		cusjoinpanel.add(cusjoinconfLabel);
		cusjoinpanel.add(cusjoinfnameLabel);
		cusjoinpanel.add(cusjoinlnameLabel);
		cusjoinpanel.add(cusjoincphoneLabel);
		cusjoinpanel.add(cusjointphoneLabel);
		cusjoinpanel.add(cusjoinbirthLabel);
		cusjoinpanel.add(cusjoinmsgLabel);
		
		cusjoinpanel.add(cusjoinidcau);
		cusjoinpanel.add(cusjoinpwdcau);
		cusjoinpanel.add(cusjoinconfcau);
		cusjoinpanel.add(cusjoinfnamecau);
		cusjoinpanel.add(cusjoinlnamecau);
		cusjoinpanel.add(cusjoinphonecau);
		cusjoinpanel.add(cusjoinbirthcau);
		
		cusjoinpanel.add(cusjoinidInput);
		cusjoinpanel.add(cusjoinpwdInput);
		cusjoinpanel.add(cusjoinconfInput);
		cusjoinpanel.add(cusjoinfnameInput);
		cusjoinpanel.add(cusjoinlnameInput);
		cusjoinpanel.add(cusjoincphoneInput);
		cusjoinpanel.add(cusjointphoneInput);
		cusjoinpanel.add(cusjoinbirthInput);
		
		cusjoinpanel.add(cusregistButton);
		cusjoinpanel.add(cusjoincancelButton);
		
		cusjoinframe.add(cusjoinpanel);
		cusjoinframe.setTitle("JOIN");
		cusjoinframe.setSize(800,600);
		cusjoinframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cusjoinframe.setVisible(true);	
		
	}

	private void customerForgot() { // 고객정보찾기
		cusforgotpanel.setLayout(null);
		cusforgotlabel.setBounds(370,105,120,50);
		cusforgotfnamelabel.setBounds(255,165,120,30);
		cusforgotlnamelabel.setBounds(255,215,120,30);
		cusforgotcphonelabel.setBounds(255,265,120,30);
		
		cusforgotfnameInput.setBounds(365,165,120,30);
		cusforgotlnameInput.setBounds(365,215,120,30);
		cusforgotcphoneInput.setBounds(365,265,120,30);
		
		cusfindButton.setBounds(265,315,110,30);
		cusfindcancelButton.setBounds(405,315,100,30);
		cusfindButton.addActionListener(this);
		cusfindcancelButton.addActionListener(this);
		
		cusforgotpanel.add(cusforgotlabel);
		cusforgotpanel.add(cusforgotfnamelabel);
		cusforgotpanel.add(cusforgotlnamelabel);
		cusforgotpanel.add(cusforgotcphonelabel);
		cusforgotpanel.add(cusforgotfnameInput);
		cusforgotpanel.add(cusforgotlnameInput);
		cusforgotpanel.add(cusforgotcphoneInput);
		cusforgotpanel.add(cusfindButton);
		cusforgotpanel.add(cusfindcancelButton);
		
		
		cusforgotframe.add(cusforgotpanel);
		cusforgotframe.setTitle("FIND ID/PW");
		cusforgotframe.setSize(800,600);
		cusforgotframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cusforgotframe.setVisible(true);	
	}
	
	private void stafflogin() { // 직원로그인
		staloginpanel.setLayout(null);	
		// component위치지정
		staloginLabel.setBounds(370,105,60,50);
		staidLabel.setBounds(255,165,90,30);
		stapwdLabel.setBounds(255,215,90,30);
		staidInput.setBounds(335,165,90,30);
		stapwdInput.setBounds(335,215,90,30);
		staloginButton.setBounds(445,165,100,30);
		staforgotButton.setBounds(445,215,100,30);
		// 버튼에 ActionListener 연결
		staloginButton.addActionListener(this);
		staforgotButton.addActionListener(this);
		
		// component panel에추가
		staloginpanel.add(staloginLabel);
		staloginpanel.add(staidLabel);
		staloginpanel.add(stapwdLabel);
		staloginpanel.add(staidInput);
		staloginpanel.add(stapwdInput);
		staloginpanel.add(staloginButton);
		staloginpanel.add(staforgotButton);
		//Panel Frame에 추가		
		staloginframe.add(staloginpanel);
		
		staloginframe.setTitle("STAFF LOGIN");  					// 프레임 상단이름
		staloginframe.setSize(800,600);  								// 프레임 크기
		staloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // 종료시 System.exit() 호출
		staloginframe.setVisible(true);	
	}
	
	private void staffForgot() { // 직원정보찾기
		staforgotpanel.setLayout(null);
		staforgotlabel.setBounds(370,105,120,50);
		staforgotidlabel.setBounds(255,165,120,30);
		staforgotfnamelabel.setBounds(255,215,120,30);
		staforgotlnamelabel.setBounds(255,265,120,30);
		staforgotcphonelabel.setBounds(255,315,120,30);
		
		staforgotidInput.setBounds(365,165,120,30);
		staforgotfnameInput.setBounds(365,215,120,30);
		staforgotlnameInput.setBounds(365,265,120,30);
		staforgotcphoneInput.setBounds(365,315,120,30);
		
		stafindButton.setBounds(265,365,110,30);
		stafindcancelButton.setBounds(405,365,100,30);
		stafindButton.addActionListener(this);
		stafindcancelButton.addActionListener(this);
		
		staforgotpanel.add(staforgotlabel);
		staforgotpanel.add(staforgotidlabel);
		staforgotpanel.add(staforgotfnamelabel);
		staforgotpanel.add(staforgotlnamelabel);
		staforgotpanel.add(staforgotcphonelabel);
		staforgotpanel.add(staforgotidInput);
		staforgotpanel.add(staforgotfnameInput);
		staforgotpanel.add(staforgotlnameInput);
		staforgotpanel.add(staforgotcphoneInput);
		staforgotpanel.add(stafindButton);
		staforgotpanel.add(stafindcancelButton);
		
		
		staforgotframe.add(staforgotpanel);
		staforgotframe.setTitle("FIND PW");
		staforgotframe.setSize(800,600);
		staforgotframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		staforgotframe.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { // 버튼이벤트
		if (e.getSource() == selcus){
			customerlogin();
		}
		
		if (e.getSource() == selsta){
			stafflogin();
		}
		
		if (e.getSource() == cusloginButton) { // Customer에서 로그인 눌렀을때 
			String logid = cusidInput.getText();
			String logpw = new String(cuspwdInput.getPassword());
			if (logid.equals("") || logpw.equals("")) { // 하나라도 비어있으면 메시지
				JOptionPane.showMessageDialog(null,"ID나 비밀번호가 입력되지 않았습니다.");
			} else { // 둘 다 입력이 있으면
				String sql1 = "select count(id) from customer where login_id = '" + logid + "'"; // login_id가 일치하는 ID 개수를 샌다
				try{
					PreparedStatement stmt1 = Con.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery();
					
					while(rs1.next()){
						String idcount = rs1.getString("COUNT(ID)");
						if (idcount.equals("0")){
							JOptionPane.showMessageDialog(null,"해당 ID가 존재하지 않습니다");
						} else {
							String sql2 = "select login_pw from customer where login_id = '" + logid + "'";
							try{
								PreparedStatement stmt2 = Con.prepareStatement(sql2);
								ResultSet rs2 = stmt2.executeQuery();
								
								while(rs2.next()){
									String pwtest = rs2.getString("LOGIN_PW");
									if (pwtest.equals(logpw)){
										String sql3 = "select * from customer where login_id = '" + logid + "'";
										try{
											PreparedStatement stmt3 = Con.prepareStatement(sql3);
											ResultSet rs3 = stmt3.executeQuery();
											String [] userinfo = new String[8];
											while (rs3.next()){
												for (int i = 0; i < 8; i++) {
													userinfo[i] = rs3.getString(i+1);
												}
											}
											// return //
										} catch (SQLException se) {
											se.printStackTrace();
										}
										
									} else {
										JOptionPane.showMessageDialog(null,"비밀번호가 틀렸습니다.");
									}
								}
							} catch (SQLException se){
								se.printStackTrace();
							}
						}
					}
				} catch (SQLException se){
					se.printStackTrace();
				}
			
			}
		}
		
		if (e.getSource() == cusforgotButton) { // 분실 눌렀을 때
			customerForgot();
		}
		
		if (e.getSource() == cusfindButton) {
			String findfname = cusforgotfnameInput.getText();
			String findlname = cusforgotlnameInput.getText();
			String findcphone = cusforgotcphoneInput.getText();
			if (findfname.equals("") || findlname.equals("") || findcphone.equals("")) { // 하나라도 비어있으면 메시지
				JOptionPane.showMessageDialog(null,"입력되지 않은 항목이 있습니다.");
			} else if(findcphone.length() != 11){
				JOptionPane.showMessageDialog(null,"전화번호는 \"-\" 없는 11자리의 숫자입니다.");
			} else {
				String sql1 = "select count(id) from customer where first_name = '" + findfname + "' and last_name = '"
						+ findlname + "' and cell_phone_number = '" + findcphone + "'"; // login_id가 일치하는 ID 개수를 샌다
				try{
					PreparedStatement stmt1 = Con.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery();
					
					while(rs1.next()){
						String idcount = rs1.getString("COUNT(ID)");
						if (idcount.equals("0")){
							JOptionPane.showMessageDialog(null,"해당 계정이 존재하지 않습니다");
						} else {
							String sql2 = "select login_id,login_pw from customer where first_name = '" + findfname + "' and last_name = '"
									+ findlname + "' and cell_phone_number = '" + findcphone + "'";;
							try{
								PreparedStatement stmt2 = Con.prepareStatement(sql2);
								ResultSet rs2 = stmt2.executeQuery();
								
								String [] find = new String[2];
								while(rs2.next()){
									for (int i = 0; i < 2; i++) {
										find[i] = rs2.getString(i+1);
									}
								}
								JOptionPane.showMessageDialog(null,"검색 결과 ...\n ID : " + find[0] + "\n PW : " + find[1]);
								cusforgotframe.dispose();
							} catch (SQLException se){
								se.printStackTrace();
							}
						}
					}
				} catch (SQLException se){
					se.printStackTrace();
				}
			
			}
		}
		
		if (e.getSource() == cusjoinButton) { // 로그인 창에서 회원가입 눌럿을 때
			customerJoin();
		}
		
		if (e.getSource() == cusregistButton) { // 가입하기 눌렀을 때
			String regiid = cusjoinidInput.getText();
			String regipw = new String(cusjoinpwdInput.getPassword());
			String confpw = new String(cusjoinconfInput.getPassword());
			String regifname = cusjoinfnameInput.getText();
			String regilname = cusjoinlnameInput.getText();
			String regicphone = cusjoincphoneInput.getText();
			String regitphone = cusjointphoneInput.getText();
			String regibirth = cusjoinbirthInput.getText();
			int numcphone = Integer.parseInt(regicphone);
			int numtphone = 0;
			if (!regitphone.equals("")) {
				numtphone = Integer.parseInt(regitphone);
			}
			
			if (regiid.equals("") || regipw.equals("") ||confpw.equals("") ||regifname.equals("") ||regilname.equals("") ||regicphone.equals("") ||regibirth.equals("")){ // 하나라도 공란
				JOptionPane.showMessageDialog(null, new JTextArea("입력하지 않은 필수항목이 존재합니다."));
			} else if (regiid.length() > 10) {
				JOptionPane.showMessageDialog(null, new JTextArea("사용할 ID가 너무 깁니다. 10자 이하로 만들어주세요."));
			} else if (regipw.length() > 20) {
				JOptionPane.showMessageDialog(null, new JTextArea("사용할 비밀번호가 너무 깁니다. 20자 이하로 만들어주세요."));
			} else if (regifname.length() > 25) {
				JOptionPane.showMessageDialog(null, new JTextArea("입력한 성의 길이가 너무 깁니다. 25자 이하로 입력해주세요."));
			} else if (regilname.length() > 25) {
				JOptionPane.showMessageDialog(null, new JTextArea("입력한 이름의 길이가 너무 깁니다. 25자 이하로 입력해주세요."));
			} else if (regicphone.length() != 11 || numcphone > 1099999999 || numcphone < 1000000000 ||
					(regitphone.length() != 11 & regitphone.length() != 0)) {
				JOptionPane.showMessageDialog(null, new JTextArea("전화번호가 올바르게 입력되지 않았습니다."));
			}  else if (regibirth.length() != 6) {
				JOptionPane.showMessageDialog(null, new JTextArea("생일이 올바르게 입력되지 않았습니다."));
			} else {
					String sql1 = "select count(id) from customer where login_id = '" + regiid + "'";
					try{
						PreparedStatement stmt1 = Con.prepareStatement(sql1);
						ResultSet rs1 = stmt1.executeQuery();
						while (rs1.next()){
							String chk = rs1.getString("COUNT(ID)");
							if (!chk.equals("0")){
								JOptionPane.showMessageDialog(null,"이미 사용중인 ID입니다!");
							} else if (!regipw.equals(confpw)){
									JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
							} else {
								try{
									if (numtphone == 0) {
										String sql2 = "insert into customer(first_name, last_name, cell_phone_number, temp_phone_number, birthday, login_id, login_pw)  values ('"
												+ regifname + "','" + regilname + "'," + numcphone + "," + null + ",date(" + regibirth + "),'" + regiid + "','" + regipw + "')";
										PreparedStatement stmt2 = Con.prepareStatement(sql2);
										stmt2.executeUpdate(sql2);
									} else {
										String sql2 = "insert into customer(first_name, last_name, cell_phone_number, temp_phone_number, birthday, login_id, login_pw)  values ('"
											+ regifname + "','" + regilname + "'," + numcphone + "," + numtphone + ",date(" + regibirth + "),'" + regiid + "','" + regipw + "')";
										PreparedStatement stmt2 = Con.prepareStatement(sql2);
										stmt2.executeUpdate(sql2);
									}	
										JOptionPane.showMessageDialog(null, "환영합니다! \n 로그인 ID : " + regiid + "\n확인을 누르면 창이 닫힙니다.");
										cusjoinframe.dispose();
									} catch (SQLException se){
										se.printStackTrace();
									}
									
								}
						}		
					} catch (SQLException se){
						se.printStackTrace();
					}
				}
			}
		
		if (e.getSource() == cusjoincancelButton) { // 회원가입 창에서 취소 눌렀을 때
			cusjoinframe.dispose();
		}
		
		if(e.getSource() == cusfindcancelButton) {
			cusforgotframe.dispose();
		}
		
		///////////위로는 고객, 아래는 스태프 ////////////
		
		
		if (e.getSource() == staloginButton) { // STAFF에서 로그인 눌렀을때 
			String logid = staidInput.getText();
			String logpw = new String(stapwdInput.getPassword());
			if (logid.equals("") || logpw.equals("")) { // 하나라도 비어있으면 메시지
				JOptionPane.showMessageDialog(null,"ID나 비밀번호가 입력되지 않았습니다.");
			} else { // 둘 다 입력이 있으면
				String sql1 = "select count(id) from staff where id = '" + logid + "'"; // login_id가 일치하는 ID 개수를 샌다
				try{
					PreparedStatement stmt1 = Con.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery();
					
					while(rs1.next()){
						String idcount = rs1.getString("COUNT(ID)");
						if (idcount.equals("0")){
							JOptionPane.showMessageDialog(null,"해당 ID가 존재하지 않습니다");
						} else {
							String sql2 = "select login_pw from staff where id = '" + logid + "'";
							try{
								PreparedStatement stmt2 = Con.prepareStatement(sql2);
								ResultSet rs2 = stmt2.executeQuery();
								
								while(rs2.next()){
									String pwtest = rs2.getString("LOGIN_PW");
									if (pwtest.equals(logpw)){
										String sql3 = "select * from staff where id = '" + logid + "'";
										try{
											PreparedStatement stmt3 = Con.prepareStatement(sql3);
											ResultSet rs3 = stmt3.executeQuery();
											String [] userinfo = new String[15];
											while (rs3.next()){
												for (int i = 0; i < 15; i++) {
													userinfo[i] = rs3.getString(i+1);
												}
											}
											// Return //
										} catch (SQLException se) {
											se.printStackTrace();
										}
										
									} else {
										JOptionPane.showMessageDialog(null,"비밀번호가 틀렸습니다.");										
									}
								}
							} catch (SQLException se){
								se.printStackTrace();
							}
						}
					}
				} catch (SQLException se){
					se.printStackTrace();
				}
			
			}
		}
		if (e.getSource() == staforgotButton) { // 분실 눌렀을 때
			staffForgot();
		}
		
		if (e.getSource() == stafindButton) {
			String findid = staforgotidInput.getText();
			String findfname = staforgotfnameInput.getText();
			String findlname = staforgotlnameInput.getText();
			String findcphone = staforgotcphoneInput.getText();
			if (findid.equals("") || findfname.equals("") || findlname.equals("") || findcphone.equals("")) { // 하나라도 비어있으면 메시지
				JOptionPane.showMessageDialog(null,"입력되지 않은 항목이 있습니다.");
			} else if(findcphone.length() != 11){
				JOptionPane.showMessageDialog(null,"전화번호는 \"-\" 없는 11자리의 숫자입니다.");
			} else {
				String sql1 = "select count(id) from staff where id = '" + findid + "' and first_name = '" + findfname + "' and last_name = '"
						+ findlname + "' and cell_phone_number = '" + findcphone + "'";
				try{
					PreparedStatement stmt1 = Con.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery();
					
					while(rs1.next()){
						String idcount = rs1.getString("COUNT(ID)");
						if (idcount.equals("0")){
							JOptionPane.showMessageDialog(null,"해당 계정이 존재하지 않습니다");
						} else {
							String sql2 = "select login_pw from staff where id = '" + findid + "' and first_name = '" + findfname + "' and last_name = '"
									+ findlname + "' and cell_phone_number = '" + findcphone + "'";;
							try{
								PreparedStatement stmt2 = Con.prepareStatement(sql2);
								ResultSet rs2 = stmt2.executeQuery();
								
								String [] find = new String[1];
								while(rs2.next()){
									for (int i = 0; i < 1; i++) {
										find[i] = rs2.getString(i+1);
									}
								}
								JOptionPane.showMessageDialog(null,"검색 결과 ...\n PW : " + find[0] + "\n 확인을 누르면 창이 닫힙니다.");
								staforgotframe.dispose();
							} catch (SQLException se){
								se.printStackTrace();
							}
						}
					}
				} catch (SQLException se){
					se.printStackTrace();
				}
			
			}
		}
		
		
		if(e.getSource() == stafindcancelButton) {
			staforgotframe.dispose();
		}
	}
	private static Connection Con;
	private void connectDB(){
		Con = null;
		
		String className = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/pbl?useSSL=false&useUnicode=true&characterEncoding=euckr";
		String username = "root";
		String password = "1234";
		
		try {
			Class.forName(className);
			Con = DriverManager.getConnection(url, username, password);
			System.out.println("Success");
		} catch(Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new login();
	}
}
