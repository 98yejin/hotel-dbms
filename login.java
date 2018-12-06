import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class login implements ActionListener { // cus~는 고객전용, sta~는 직원전용이다.
	private JFrame cusloginframe = new JFrame();						// 고객 로그인창 프레임
	private JPanel cusloginpanel = new JPanel();						// 고객 로그인창 패널
	private JTextField cusidInput = new JTextField();					// 고객 로그인창 ID 입력필드
	private JPasswordField cuspwdInput = new JPasswordField();			// 고객 로그인창 PW 입력필드
	private JButton cusloginButton = new JButton();				// 고객 로그인창 로그인버튼
	private JButton cusjoinButton = new JButton();				// 고객 로그인창 회원가입 버튼
	private JButton cusforgotButton = new JButton();		// 고객 로그인창 계정찾기 버튼
	
	// 이상 고객 로그인 //
	
	private JFrame cusjoinframe = new JFrame();							// 고객 회원가입창 플임
	private JPanel cusjoinpanel = new JPanel();							// 고객 회원가입창 패널
	
	
	private JTextField cusjoinidInput = new JTextField(); // 여기서부터는 고객 회원가입창 입력칸
	private JPasswordField cusjoinpwdInput = new JPasswordField();
	private JPasswordField cusjoinconfInput = new JPasswordField();
	private JTextField cusjoinfnameInput = new JTextField();
	private JTextField cusjoinlnameInput = new JTextField();
	private JTextField cusjoincphoneInput = new JTextField();
	private JTextField cusjointphoneInput = new JTextField();
	private JTextField cusjoinbirthInput = new JTextField();
	private JButton cusregistButton = new JButton(); // 고객 회원가입창 가입하기 버튼
	private JButton cusjoincancelButton = new JButton(); // 고객 회원가입창 취소(닫기) 버튼
	// 이상 고객 회원가입 //
	
	private JFrame cusforgotframe = new JFrame(); 							// 고객 정보찾기창 프레임
	private JPanel cusforgotpanel = new JPanel();							// 고객 정보찾기창 패널

	
	private JTextField cusforgotfnameInput = new JTextField();				// 고객 정보찾기창 입력칸(이하 2개 더)
	private JTextField cusforgotlnameInput = new JTextField();
	private JTextField cusforgotcphoneInput = new JTextField();
	private JButton cusfindButton = new JButton();					// 고객 정보찾기창 찾기버튼
	private JButton cusfindcancelButton = new JButton();			// 고객 정보찾기창 취소(닫기)버튼
	// 이상 ID/PW찾기 //
	
	// 이상 고객 //
	
	private JFrame mainframe = new JFrame();								// 첫 실행화면(고객, 직원 선택)
	private JPanel mainpanel = new JPanel();
	private JButton selcus = new JButton();					// 누르면 고객로그인 열림
	private JButton selsta = new JButton();					// 누르면 직원로그인 열림
	
	// 이상 메인 //
	
	private JFrame staloginframe = new JFrame();							// 이하 구성은 고객과 동일함
	private JPanel staloginpanel = new JPanel();
	
	private JTextField staidInput = new JTextField();
	private JPasswordField stapwdInput = new JPasswordField();
	private JButton staloginButton = new JButton();
	private JButton staforgotButton = new JButton();
	
	// 이상 로그인 //
	
	private JFrame staforgotframe = new JFrame();
	private JPanel staforgotpanel = new JPanel();

	private JTextField staforgotidInput = new JTextField();
	private JTextField staforgotfnameInput = new JTextField();
	private JTextField staforgotlnameInput = new JTextField();
	private JTextField staforgotcphoneInput = new JTextField();
	private JButton stafindButton = new JButton();
	private JButton stafindcancelButton = new JButton();
	// 이상 PW찾기 //
	
	// 이상 직원 //
	
	static String cusid;				// 받아온 고객id
	static String staid;				// 받아온 직원id
	static String status;				// 받아온 id의 상태(직원 id인가? 고객 id인가?)


	public ImageTest() { // 메인화면(스태프,고객구분)
		connectDB(); // 처음에 메인화면(고객, 직원선택) 창을 띄우고, 이 때 DB를 연결해둠
		Image img = null;
		mainpanel.setLayout(null);
		selcus.setBounds(69,100,300,430);
		selsta.setBounds(429,100,300,430);
		selcus.setBorderPainted(false);
		selcus.setFocusPainted(false);
		selcus.setContentAreaFilled(false);
		selsta.setBorderPainted(false);
		selsta.setFocusPainted(false);
		selsta.setContentAreaFilled(false);
		
		
		mainpanel.add(selcus);
		mainpanel.add(selsta);
		selcus.addActionListener(this);
		selsta.addActionListener(this);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_185919117.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		mainpanel.add(imgLabel); // 이미지 집어넣기
		
		mainframe.add(mainpanel);
		
		mainframe.setTitle("SELECT POSITION");  					// 프레임 상단이름
		mainframe.setSize(815,638);  								// 프레임 크기
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 종료시 System.exit() 호출
		mainframe.setVisible(true);
		
	}
	
	private void customerlogin() { // 고객로그인
		
		Image img = null;
		Color color1 = new Color(0xD2958B);
		cusloginpanel.setLayout(null);	
		// component위치지정
		
		cusidInput.setBounds(338,191,87,20);
		cuspwdInput.setBounds(338,239,87,20);
		cusloginButton.setBounds(448,185,74,72);
		cusjoinButton.setBounds(273,290,98,20);
		cusforgotButton.setBounds(413,290,98,20);
		cusidInput.setBackground(color1); // 투명하니 선택이 불가능해져서 색상을 입힘
		cusidInput.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // 테두리 투명하게
		cuspwdInput.setBackground(color1);
		cuspwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusloginButton.setBorderPainted(false);
		cusloginButton.setFocusPainted(false);
		cusloginButton.setContentAreaFilled(false);
		cusjoinButton.setBorderPainted(false);
		cusjoinButton.setFocusPainted(false);
		cusjoinButton.setContentAreaFilled(false);
		cusforgotButton.setBorderPainted(false);
		cusforgotButton.setFocusPainted(false);
		cusforgotButton.setContentAreaFilled(false);
		
		// 버튼에 ActionListener 연결
		cusloginButton.addActionListener(this);
		cusjoinButton.addActionListener(this);
		cusforgotButton.addActionListener(this);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_185919105.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		cusloginpanel.add(imgLabel); // 이미지 집어넣기
		
		// component panel에추가
	
		cusloginpanel.add(cusidInput);
		cusloginpanel.add(cuspwdInput);
		cusloginpanel.add(cusloginButton);
		cusloginpanel.add(cusjoinButton);
		cusloginpanel.add(cusforgotButton);
		//Panel Frame에 추가		
		cusloginframe.add(cusloginpanel);
		
		cusloginframe.setTitle("CUSTOMER LOGIN");  					// 프레임 상단이름
		cusloginframe.setSize(815,638);  								// 프레임 크기
		cusloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // 종료시 고객 로그인 창만 닫음.
		cusloginframe.setVisible(true);	// frame 화면에 표시
		
	}
	
	private String customerloginact() { // 고객 로그인 버튼 누를때
		String logid = cusidInput.getText();
		String logpw = new String(cuspwdInput.getPassword()); // 검사를 위해 입력값을 받아서 저장함
		if (logid.equals("") || logpw.equals("")) { // 하나라도 비어있으면 메시지
			JOptionPane.showMessageDialog(null,"ID나 비밀번호가 입력되지 않았습니다.");
		} else { // 둘 다 입력이 있으면
			String sql1 = "select count(id) from customer where login_id = '" + logid + "'"; // login_id가 일치하는 ID 개수를 센다(존재하는 ID인가)
			try{
				PreparedStatement stmt1 = Con.prepareStatement(sql1);
				ResultSet rs1 = stmt1.executeQuery();
				
				while(rs1.next()){ // 쿼리가 실행되었을 때
					String idcount = rs1.getString("COUNT(ID)"); // login_id가 일치하는 ID 개수를 받아서 저장
					if (idcount.equals("0")){ // login_ID가 없으면
						JOptionPane.showMessageDialog(null,"해당 ID가 존재하지 않습니다");
					} else {			// login_ID가 있으면
						String sql2 = "select login_pw from customer where login_id = '" + logid + "'"; // 입력과 login_ID가 일치하는 튜플의 login_pw를 받아온다
						try{
							PreparedStatement stmt2 = Con.prepareStatement(sql2);
							ResultSet rs2 = stmt2.executeQuery();
							
							while(rs2.next()){
								String pwtest = rs2.getString("LOGIN_PW"); // 여기서 받아서 저장
								if (pwtest.equals(logpw)){ // 입력한 비밀번호와 DB의 비밀번호가 일치하는지 검사
									String sql3 = "select id from customer where login_id = '" + logid + "'"; // login_id가 일치하는 튜플의 ID를 받아온다
									try{
										PreparedStatement stmt3 = Con.prepareStatement(sql3);
										ResultSet rs3 = stmt3.executeQuery();
										while (rs3.next()){
											cusid = rs3.getString(("ID")); // 받아온 id를 저장
										}
										while (!cusid.isEmpty()){ // null이 아닌지 확인
											cusloginframe.dispose(); // 로그인에 성공하면 로그인창을 닫는다.
											return cusid; 
										}
									} catch (SQLException se) {
										se.printStackTrace();
									}
									
								} else { // 비밀번호가 일치하지 않을때
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
		return null;  // 창을 닫기 전엔 아무것도 return하지 않는다.
	}

	private void customerJoin() { // 고객회원가입
		Image img = null;
		Color color1 = new Color(0xDDC6C3);
		cusjoinpanel.setLayout(null);
	
		cusjoinidInput.setBounds(312,86,176,25);
		cusjoinpwdInput.setBounds(312,132,176,25);
		cusjoinconfInput.setBounds(312,185,176,25);
		cusjoinfnameInput.setBounds(312,237,176,25);
		cusjoinlnameInput.setBounds(312,289,176,25);
		cusjoincphoneInput.setBounds(312,336,176,25);
		cusjointphoneInput.setBounds(312,385,176,25);
		cusjoinbirthInput.setBounds(312,435,176,25);
		
		cusregistButton.setBounds(305,530,80,30);
		cusjoincancelButton.setBounds(414,530,80,30);
		cusregistButton.addActionListener(this);
		cusjoincancelButton.addActionListener(this);
		
		cusjoinidInput.setBackground(color1); // 투명하니 선택이 불가능해져서 색상을 입힘
		cusjoinidInput.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // 테두리 투명하게
		cusjoinpwdInput.setBackground(color1);
		cusjoinpwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjoinconfInput.setBackground(color1);
		cusjoinconfInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjoinfnameInput.setBackground(color1);
		cusjoinfnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjoinlnameInput.setBackground(color1);
		cusjoinlnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjoincphoneInput.setBackground(color1);
		cusjoincphoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjointphoneInput.setBackground(color1);
		cusjointphoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusjoinbirthInput.setBackground(color1);
		cusjoinbirthInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusregistButton.setBorderPainted(false);
		cusregistButton.setFocusPainted(false);
		cusregistButton.setContentAreaFilled(false);
		cusjoincancelButton.setBorderPainted(false);
		cusjoincancelButton.setFocusPainted(false);
		cusjoincancelButton.setContentAreaFilled(false);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_192522806.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		cusjoinpanel.add(imgLabel); // 이미지 집어넣기
		
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
		cusjoinframe.setSize(815,638);
		cusjoinframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cusjoinframe.setVisible(true);	
		
	}

	private void customerJoinact() { // 가입창에서 가입누를때 (기본적 틀은 같음)
		String regiid = cusjoinidInput.getText();
		String regipw = new String(cusjoinpwdInput.getPassword());
		String confpw = new String(cusjoinconfInput.getPassword());
		String regifname = cusjoinfnameInput.getText();
		String regilname = cusjoinlnameInput.getText();
		String regicphone = cusjoincphoneInput.getText();
		String regitphone = cusjointphoneInput.getText();
		String regibirth = cusjoinbirthInput.getText();
		int numcphone = Integer.parseInt(regicphone); // SQL문으로 입력하기 위해 숫자로 바꿈 
		int numtphone = 0;							  // Temp-phone이 입력되지 않은 경우를 대비해 0으로 저장.
		if (!regitphone.equals("")) { // 그런데 뭔가 입력을 했다면
			numtphone = Integer.parseInt(regitphone); // 숫자로 바꾼다.
		}
		
		if (regiid.equals("") || regipw.equals("") ||confpw.equals("") ||regifname.equals("") ||regilname.equals("") ||regicphone.equals("") ||regibirth.equals("")){ // 필수항목(temp-phone 이외)중 하나라도 입력이 없으면
			JOptionPane.showMessageDialog(null, new JTextArea("입력하지 않은 필수항목이 존재합니다."));
		} else if (regiid.length() > 10) { 																	// 이하 유효성검사를 어겼을 경우(길이제한)
			JOptionPane.showMessageDialog(null, new JTextArea("사용할 ID가 너무 깁니다. 10자 이하로 만들어주세요."));
		} else if (regipw.length() > 20) {
			JOptionPane.showMessageDialog(null, new JTextArea("사용할 비밀번호가 너무 깁니다. 20자 이하로 만들어주세요."));
		} else if (regifname.length() > 25) {
			JOptionPane.showMessageDialog(null, new JTextArea("입력한 성의 길이가 너무 깁니다. 25자 이하로 입력해주세요."));
		} else if (regilname.length() > 25) {
			JOptionPane.showMessageDialog(null, new JTextArea("입력한 이름의 길이가 너무 깁니다. 25자 이하로 입력해주세요."));
		} else if (regicphone.length() != 11 || numcphone > 1099999999 || numcphone < 1000000000 ||         // 이쪽은 숫자의 범위를 제한하였음.
				(regitphone.length() != 11 & regitphone.length() != 0)) {
			JOptionPane.showMessageDialog(null, new JTextArea("전화번호가 올바르게 입력되지 않았습니다."));
		}  else if (regibirth.length() != 6) {																// 이쪽은 여섯자리로 입력하도록 만들었음.
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
						} else if (!regipw.equals(confpw)){													// 회원가입시 비밀번호, 비밀번호 확인이 일치하지 않을 경우
								JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
						} else {
							try{
								if (numtphone == 0) {														// 회원가입시 temp-phone을 입력하지 않았을 경우
									String sql2 = "insert into customer(first_name, last_name, cell_phone_number, temp_phone_number, birthday, login_id, login_pw)  values ('"
											+ regifname + "','" + regilname + "'," + numcphone + "," + null + ",date(" + regibirth + "),'" + regiid + "','" + regipw + "')";
									PreparedStatement stmt2 = Con.prepareStatement(sql2);
									stmt2.executeUpdate(sql2);
								} else {																	// 이쪽은 입력햇을 경우다.
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
	
	private void customerForgot() { // 고객정보찾기
		Image img = null;
		Color color1 = new Color(0xD2958B);
		
		cusforgotpanel.setLayout(null);
		
		cusforgotfnameInput.setBounds(378,199,92,21);
		cusforgotlnameInput.setBounds(378,244,92,21);
		cusforgotcphoneInput.setBounds(378,289,92,21);
		
		cusfindButton.setBounds(275,344,75,24);
		cusfindcancelButton.setBounds(411,344,74,24);
		cusfindButton.addActionListener(this);
		cusfindcancelButton.addActionListener(this);
		
		cusforgotfnameInput.setBackground(color1);
		cusforgotfnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusforgotlnameInput.setBackground(color1);
		cusforgotlnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusforgotcphoneInput.setBackground(color1);
		cusforgotcphoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cusfindButton.setBorderPainted(false);
		cusfindButton.setFocusPainted(false);
		cusfindButton.setContentAreaFilled(false);
		cusfindcancelButton.setBorderPainted(false);
		cusfindcancelButton.setFocusPainted(false);
		cusfindcancelButton.setContentAreaFilled(false);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_201111181.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		cusforgotpanel.add(imgLabel); // 이미지 집어넣기
		
		cusforgotpanel.add(cusforgotfnameInput);
		cusforgotpanel.add(cusforgotlnameInput);
		cusforgotpanel.add(cusforgotcphoneInput);
		cusforgotpanel.add(cusfindButton);
		cusforgotpanel.add(cusfindcancelButton);
		
		cusforgotframe.add(cusforgotpanel);
		cusforgotframe.setTitle("FIND ID/PW");
		cusforgotframe.setSize(815,638);
		cusforgotframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cusforgotframe.setVisible(true);	
	}
	
	private void customerForgotact() { // 고객 찾기창에서 찾기누를때
		String findfname = cusforgotfnameInput.getText();
		String findlname = cusforgotlnameInput.getText();
		String findcphone = cusforgotcphoneInput.getText();
		if (findfname.equals("") || findlname.equals("") || findcphone.equals("")) { // 하나라도 비어있으면 메시지
			JOptionPane.showMessageDialog(null,"입력되지 않은 항목이 있습니다.");
		} else if(findcphone.length() != 11){										// 전화번호 입력양식을 어겼을 경우
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
								+ findlname + "' and cell_phone_number = '" + findcphone + "'";;		// ID와 PW를 한번에 알려주도록 만들었다.
						try{
							PreparedStatement stmt2 = Con.prepareStatement(sql2);
							ResultSet rs2 = stmt2.executeQuery();
							
							String [] find = new String[2];
							while(rs2.next()){										// SQL 실행문 결과를 Array로 저장.
								for (int i = 0; i < 2; i++) {
									find[i] = rs2.getString(i+1);
								}
							}
							JOptionPane.showMessageDialog(null,"검색 결과 ...\n ID : " + find[0] + "\n PW : " + find[1]);	// 저장된 Array를 이용하여 메시지창을 출력해줌.
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
	
	private void stafflogin() { // 직원로그인
		
		Image img = null;
		Color color1 = new Color(0xD2958B);
		staloginpanel.setLayout(null);	
		// component위치지정
		
		staidInput.setBounds(338,191,88,20);
		stapwdInput.setBounds(338,239,88,20);
		staloginButton.setBounds(461,187,70,28);
		staforgotButton.setBounds(461,234,70,28);
		staidInput.setBackground(color1); // Textinput가 투명하면 선택이 불가능해져서 색상을 입힘
		staidInput.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // 테두리 투명하게
		stapwdInput.setBackground(color1);
		stapwdInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		staloginButton.setBorderPainted(false); // 이하 3개가 한개 버튼 투명하게만듬 
		staloginButton.setFocusPainted(false);
		staloginButton.setContentAreaFilled(false);
		staforgotButton.setBorderPainted(false);
		staforgotButton.setFocusPainted(false);
		staforgotButton.setContentAreaFilled(false);
		
		// 버튼에 ActionListener 연결
		staloginButton.addActionListener(this);
		staforgotButton.addActionListener(this);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_185919118.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		staloginpanel.add(imgLabel); // 이미지 집어넣기
		
		// component panel에추가
		
		staloginpanel.add(staidInput);
		staloginpanel.add(stapwdInput);
		staloginpanel.add(staloginButton);
		staloginpanel.add(staforgotButton);
		//Panel Frame에 추가		
		staloginframe.add(staloginpanel);
		
		staloginframe.setTitle("STAFF LOGIN");  					// 프레임 상단이름
		staloginframe.setSize(815,638);  								// 프레임 크기
		staloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // 
		staloginframe.setVisible(true);
		
	}
	
	private String staffloginact() {
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
									String sql3 = "select id from staff where id = '" + logid + "'";
									try{
										PreparedStatement stmt3 = Con.prepareStatement(sql3);
										ResultSet rs3 = stmt3.executeQuery();
										while (rs3.next()){
											staid = rs3.getString("ID");			// 작동 원리는 같으나 cusid 대신 staid에 저장.
										}
										while (!staid.isEmpty()){
											staloginframe.dispose();
											return staid;
										}
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
		return null;
	}
	
	private void staffForgot() { // 직원정보찾기
		Image img = null;
		Color color1 = new Color(0xD2958B);
		
		staforgotpanel.setLayout(null);
		
		staforgotidInput.setBounds(380,192,92,20);
		staforgotfnameInput.setBounds(380,244,92,20);
		staforgotlnameInput.setBounds(380,289,92,20);
		staforgotcphoneInput.setBounds(380,334,92,20);
		
		stafindButton.setBounds(282,397,73,22);
		stafindcancelButton.setBounds(422,397,73,22);
		stafindButton.addActionListener(this);
		stafindcancelButton.addActionListener(this);
		
		staforgotidInput.setBackground(color1);
		staforgotidInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		staforgotfnameInput.setBackground(color1);
		staforgotfnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		staforgotlnameInput.setBackground(color1);
		staforgotlnameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		staforgotcphoneInput.setBackground(color1);
		staforgotcphoneInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		stafindButton.setBorderPainted(false);
		stafindButton.setFocusPainted(false);
		stafindButton.setContentAreaFilled(false);
		stafindcancelButton.setBorderPainted(false);
		stafindcancelButton.setFocusPainted(false);
		stafindcancelButton.setContentAreaFilled(false);
		
		try{ // URL로 이미지 받아오기
			File sourceimage = new File("c:\\KakaoTalk_20181206_192522782.png");
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			System.out.println("Image Loading Fail");
		}
		
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(0,0,800,600);
		staforgotpanel.add(imgLabel); // 이미지 집어넣기
		
		staforgotpanel.add(staforgotidInput);
		staforgotpanel.add(staforgotfnameInput);
		staforgotpanel.add(staforgotlnameInput);
		staforgotpanel.add(staforgotcphoneInput);
		staforgotpanel.add(stafindButton);
		staforgotpanel.add(stafindcancelButton);
		
		
		staforgotframe.add(staforgotpanel);
		staforgotframe.setTitle("FIND PW");
		staforgotframe.setSize(815,638);
		staforgotframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		staforgotframe.setVisible(true);
	}

	private void staffForgotact() {
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
	
	public void actionPerformed(ActionEvent e) { // 버튼이벤트
		if (e.getSource() == selcus){ // 고객선택시
			customerlogin();
			status = "customer";
		}
		if (e.getSource() == selsta){ // 직원선택시
			stafflogin();
			status = "staff";
		}
		if (e.getSource() == cusloginButton) { // Customer에서 로그인 눌렀을때 
			cusid = customerloginact();
		}
		if (e.getSource() == cusforgotButton) { // 분실 눌렀을 때
			customerForgot();
		}
		if (e.getSource() == cusfindButton) { // 찾기에서 찾기버튼 누를때
			customerForgotact();
		}
		if (e.getSource() == cusjoinButton) { // 로그인 창에서 회원가입 눌럿을 때
			customerJoin();
		}
		if (e.getSource() == cusregistButton) { // 가입하기 눌렀을 때
			customerJoinact();			
		}
		if (e.getSource() == cusjoincancelButton) { // 회원가입 창에서 취소 눌렀을 때
			cusjoinframe.dispose();
		}
		if(e.getSource() == cusfindcancelButton) { // 찾기창에서 취소누를때
			cusforgotframe.dispose();
		}
		
		///////////위로는 고객, 아래는 스태프 ////////////
		
		if (e.getSource() == staloginButton) { // STAFF에서 로그인 눌렀을때 
			staid = staffloginact();
		}
		if (e.getSource() == staforgotButton) { // 분실 눌렀을 때
			staffForgot();
		}
		if (e.getSource() == stafindButton) { // 찾기창에서 찾기버튼 누를때
			staffForgotact();			
		}
		if(e.getSource() == stafindcancelButton) { // 찾기창에서 취소누를때
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
		new ImageTest();
		
	}
}
