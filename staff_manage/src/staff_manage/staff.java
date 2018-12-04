package staff_manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class staff extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	Connection connection= null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public staff() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		connection = mysqlConnection.dbconnector();
		
		//관리직원 정보보기 
		table = new JTable();
		add(table);
		String query = "SELECT DISTINCT staff.position as 직급, staff.first_name as 이름, staff.id as 사원번호, "
				+ "staff.cell_phone_number as 폰번호, staff.contract_start as 입사일, staff.contract_end as 계약종료일, "
				+ "status.late_count as 지각횟수, status.absence_count as 결석횟수, status.trouble_count as 사고횟수 "
				+ "FROM staff, status "
				+ "WHERE staff.id = status.staff_id";
		stmt = connection.prepareStatement(query);
		rs = stmt.executeQuery(query);
		table.setModel(DbUtils.resultSetToTableModel(rs));	
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//직원 검색 
		textField = new JTextField();
		textField.setText("직원의 이름(First Name)을 입력해주세요");
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String query = "SELECT DISTINCT staff.position as 직급, staff.first_name as 이름, staff.id as 사원번호, staff.cell_phone_number as 폰번호," 
									+ "staff.contract_start as 입사일, staff.contract_end as 계약종료일, status.late_count as 지각횟수, status.absence_count as 결석횟수," 
									+ "status.trouble_count as 사고횟수 FROM staff, status WHERE staff.id = status.staff_id and first_name = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textField.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staff frame = new staff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
