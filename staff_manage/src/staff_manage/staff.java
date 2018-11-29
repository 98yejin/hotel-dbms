package staff_manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class staff extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
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
	Connection connection=null;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public staff() {
		connection = mysqlConnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String query = "SELECT DISTINCT staff.position as 직급, staff.first_name as 이름, staff.id as 사원번호, staff.cell_phone_number as 폰번호," 
									+ "staff.contract_start as 입사일, staff.contract_end as 계약종료일, status.late_count as 지각횟수, status.absence_count as 결석횟수," 
									+ "status.trouble_count as 사고횟수 FROM staff, status WHERE staff.id = status.staff_id and id = ?";
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
		
		table = new JTable();
		JScrollPane scrollPane_1 = new JScrollPane(table);
		contentPane.add(scrollPane_1, BorderLayout.CENTER);
		
		JButton btnLoadTable = new JButton("Load Employee Data");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "SELECT DISTINCT staff.position as 직급, staff.first_name as 이름, staff.id as 사원번호, staff.cell_phone_number as 폰번호, "
							+ "staff.contract_start as 입사일, staff.contract_end as 계약종료일, status.late_count as 지각횟수, status.absence_count as 결석횟수, "
							+ "status.trouble_count as 사고횟수 FROM staff, status WHERE staff.id = status.staff_id";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLoadTable, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.EAST);
	}

}
