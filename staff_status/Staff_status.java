import javax.swing.*;
import java.awt.*;

public class Staff_status {
	
	private JFrame frame = new JFrame();
	private JLabel hello = new JLabel("팀 님 안녕하세요.");
	private JLabel title = new JLabel("나의 업무정보");
	private JLabel category1 = new JLabel("출결확인");
	private JLabel category2 = new JLabel("경고횟수(지각,결석)");
	private JLabel category3 = new JLabel("근무일정");
	private JLabel text = new JLabel("회");
	private JPanel panel = new JPanel();
	private JTextField text1 = new JTextField();
	private JButton attend = new JButton("출근");
	private JButton leave = new JButton("퇴근");
	
	
	public Staff_status(){
		panel.setLayout(null);
		
		title.setBounds(50, 30, 100, 30);
		hello.setBounds(600, 30, 200, 30);
		category1.setBounds(50, 100, 100, 30);
		category2.setBounds(50, 200, 200, 30);
		category3.setBounds(50, 300, 200, 30);
		text1.setBounds(200, 200, 100, 30);
		text1.setEnabled(false);
		text.setBounds(310, 200, 100, 30);
		attend.setBounds(610, 90, 80, 30);
		leave.setBounds(610, 120, 80, 30);
		
		String days1[] = {" ", "일", "월", "화", "수", "목", "금", "토"};
		String days2[] = {"일", "월", "화", "수", "목", "금", "토"};
		String contents1[][] = {{"출근","8:00","8:00","8:00","8:00","8:00","8:00","8:00"},
		{"퇴근","6:00","6:00","6:00","6:00","6:00","6:00","6:00"}};
		String contents2[][] = {{"O","O","O","O","O","O","O"}};
		
		JTable table1 = new JTable(contents1, days1);
		JTable table2 = new JTable(contents2, days2);
		
		JScrollPane scrollpane1 = new JScrollPane(table1);
		scrollpane1.setBounds(200,93,400,57);
		JScrollPane scrollpane2 = new JScrollPane(table2);
		scrollpane2.setBounds(200,300,400,41);
		
		
		
		panel.add(title);
		panel.add(hello);
		panel.add(category1);
		panel.add(category2);
		panel.add(category3);
		panel.add(text1);
		panel.add(scrollpane1);
		panel.add(scrollpane2);
		panel.add(text);
		panel.add(attend);
		panel.add(leave);
		
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new Staff_status();
	}
}
