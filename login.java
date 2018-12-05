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


public class login implements ActionListener { // cus~는 고객전용, sta~는 직원전용이다.
	private JFrame cusloginframe = new JFrame();						// 고객 로그인창 프레임
	private JPanel cusloginpanel = new JPanel();						// 고객 로그인창 패널
	private JLabel cusloginLabel = new JLabel("LOG-IN");				// 고객 로그인창 제목(창의 이름)
	private JLabel cusidLabel = new JLabel("ID");						// 고객 로그인창 ID (텍스트)
	private JLabel cuspwdLabel = new JLabel("PASSWORD");				// 고객 로그인창 PW (텍스트)
	private JTextField cusidInput = new JTextField();					// 고객 로그인창 ID 입력필드
	private JPasswordField cuspwdInput = new JPasswordField();			// 고객 로그인창 PW 입력필드
	private JButton cusloginButton = new JButton("LOGIN");				// 고객 로그인창 로그인버튼
	private JButton cusjoinButton = new JButton("REGIST");				// 고객 로그인창 회원가입 버튼
	private JButton cusforgotButton = new JButton("LOST ID/PW?");		// 고객 로그인창 계정찾기 버튼
	
	// 이상 고객 로그인 //
	
	private JFrame cusjoinframe = new JFrame();							// 고객 회원가입창 플임
	private JPanel cusjoinpanel = new JPanel();							// 고객 회원가입창 패널
	private JLabel cusjoinLabel = new JLabel("REGISTRATION");			// 고객 회원가입창 제목
	private JLabel cusjoinidLabel = new JLabel("* ID");					// 이하 고객 회원가입창 텍스트
	private JLabel cusjoinpwdLabel = new JLabel("* PASSWORD");
	private JLabel cusjoinconfLabel = new JLabel("* CONFIRM");
	private JLabel cusjoinfnameLabel = new JLabel("* FIRST NAME");
	private JLabel cusjoinlnameLabel = new JLabel("* LAST NAME");
	private JLabel cusjoincphoneLabel = new JLabel("* CELL PHONE");
	private JLabel cusjointphoneLabel = new JLabel("TEMP PHONE");
	private JLabel cusjoinbirthLabel = new JLabel("* BIRTHDAY");
	private JLabel cusjoinmsgLabel = new JLabel("Must INPUT data on *."); // 여기서부터는 알림문구
	private JLabel cusjoinidcau = new JLabel("ID는 10자 이하");
	private JLabel cusjoinpwdcau = new JLabel("비밀번호는 20자 이하");
	private JLabel cusjoinconfcau = new JLabel("비밀번호와 일치시켜주세요.");
	private JLabel cusjoinfnamecau = new JLabel("성은 25자 이하");
	private JLabel cusjoinlnamecau = new JLabel("이름은 25자 이하");
	private JLabel cusjoinphonecau = new JLabel("전화번호는 - 없이 11자리");
	private JLabel cusjoinbirthcau = new JLabel("생년월일은 기호없이 6자리");
	
	private JTextField cusjoinidInput = new JTextField(); // 여기서부터는 고객 회원가입창 입력칸
	private JPasswordField cusjoinpwdInput = new JPasswordField();
	private JPasswordField cusjoinconfInput = new JPasswordField();
	private JTextField cusjoinfnameInput = new JTextField();
	private JTextField cusjoinlnameInput = new JTextField();
	private JTextField cusjoincphoneInput = new JTextField();
	private JTextField cusjointphoneInput = new JTextField();
	private JTextField cusjoinbirthInput = new JTextField();
	private JButton cusregistButton = new JButton("REGIST"); // 고객 회원가입창 가입하기 버튼
	private JButton cusjoincancelButton = new JButton("CANCEL"); // 고객 회원가입창 취소(닫기) 버튼
	// 이상 고객 회원가입 //
	
	private JFrame cusforgotframe = new JFrame(); 							// 고객 정보찾기창 프레임
	private JPanel cusforgotpanel = new JPanel();							// 고객 정보찾기창 패널
	private JLabel cusforgotlabel = new JLabel("FIND ID/PW");				// 고객 정보찾기창 제목
	private JLabel cusforgotfnamelabel = new JLabel("FIRST NAME");			// 고객 정보찾기창 라벨 (이하 2개 더)
	private JLabel cusforgotlnamelabel = new JLabel("LAST NAME");
	private JLabel cusforgotcphonelabel = new JLabel("CELL PHONE NUM");
	
	private JTextField cusforgotfnameInput = new JTextField();				// 고객 정보찾기창 입력칸(이하 2개 더)
	private JTextField cusforgotlnameInput = new JTextField();
	private JTextField cusforgotcphoneInput = new JTextField();
	private JButton cusfindButton = new JButton("FIND");					// 고객 정보찾기창 찾기버튼
	private JButton cusfindcancelButton = new JButton("CANCEL");			// 고객 정보찾기창 취소(닫기)버튼
	// 이상 ID/PW찾기 //
	
	// 이상 고객 //
	
	private JFrame mainframe = new JFrame();								// 첫 실행화면(고객, 직원 선택)
	private JPanel mainpanel = new JPanel();
	private JButton selcus = new JButton("CUSTOMER LOGIN");					// 누르면 고객로그인 열림
	private JButton selsta = new JButton("STAFF LOGIN");					// 누르면 직원로그인 열림
	
	// 이상 메인 //
	
	private JFrame staloginframe = new JFrame();							// 이하 구성은 고객과 동일함
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
	private JLabel staforgotlabel = new JLabel("FIND PW");					// 직원은 ID를 항상 앙고 있다고 가정하고(ID카드 등) 비밀번호만 검색
	private JLabel staforgotidlabel = new JLabel("ID");						// 이상의 이유와 보안상의 이유로 직원은 ID도 요구
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
	
	static String cusid;				// 받아온 고객id
	static String staid;				// 받아온 직원id
	static String status;				// 받아온 id의 상태(직원 id인가? 고객 id인가?)


	public login() { // 메인화면(스태프,고객구분)
		connectDB(); // 처음에 메인화면(고객, 직원선택) 창을 띄우고, 이 때 DB를 연결해둠
		
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
		new login();
		
	}
}
