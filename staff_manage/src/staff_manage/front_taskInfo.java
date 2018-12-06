package staff_manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class front_taskInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	Connection connection= null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public front_taskInfo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		connection = mysqlConnection.dbconnector();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{170, 115, 116, 110, 120, 0};
		gbl_contentPane.rowHeights = new int[]{568, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		contentPane.setLayout(gbl_contentPane);
		
		//귀중품 보관 
		JButton valuableButton = new JButton("Valuable");
		valuableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ValuablenewWindow();
			}
		});
		
		GridBagConstraints GBCvaluable = new GridBagConstraints();
		GBCvaluable.insets = new Insets(0, 0, 0, 5);
		GBCvaluable.gridx = 1;
		GBCvaluable.gridy = 0;
		
		contentPane.add(valuableButton, GBCvaluable);
		
		//발렛파킹 
		JButton parkingButton = new JButton("Parking");
		parkingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ParkingnewWindow();
			}
		});
		
		GridBagConstraints GBCparking = new GridBagConstraints();
		GBCparking.insets = new Insets(0, 0, 0, 5);
		GBCparking.gridx = 2;
		GBCparking.gridy = 0;
		
		contentPane.add(parkingButton, GBCparking);
			
		//객실전담직원 확인 
		JButton staffCheckButton = new JButton("Staff Check");
		staffCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints GBCstaffcheck = new GridBagConstraints();
		GBCstaffcheck.insets = new Insets(0, 0, 0, 5);
		GBCstaffcheck.gridx = 3;
		GBCstaffcheck.gridy = 0;
		
		contentPane.add(staffCheckButton, GBCstaffcheck);	
			
		//계산 
		JButton billButton = new JButton("   Bill  ");
		billButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BillnewWindow();
			}
		});
		GridBagConstraints GBCbill = new GridBagConstraints();
		GBCbill.gridx = 4;
		GBCbill.gridy = 0;
		contentPane.add(billButton, GBCbill);
	}
	
	class ValuablenewWindow extends JFrame{
		ValuablenewWindow(){
			setTitle("Valuable");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);
			
			table = new JTable();
			add(table); 
			try {
				connection = mysqlConnection.dbconnector();
				String query = "SELECT valuable.content as 맡긴귀중품, customer.first_name as 손님이름, "
						+ "staff.first_name as 직원이름, valuable_staff.receive_date as 맡긴날짜, "
						+ "valuable_staff.receive_time as 맡긴시간, valuable_staff.give_date as 받은날짜, valuable_staff.give_time as 받은시간 "
						+ "from (((valuable_staff "
						+ "inner join valuable on valuable.id = valuable_staff.valuable_id) "
						+ "inner join customer on valuable.custom_id = customer.id) "
						+ "inner join staff on valuable_staff.staff_id = staff.id)";
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			JScrollPane scrollPane = new JScrollPane(table);
			NewWindowContainer.add(scrollPane, BorderLayout.CENTER);
		}
	}
	
	class ParkingnewWindow extends JFrame{
		ParkingnewWindow(){
			setTitle("Parking");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);
			
			table = new JTable();
			add(table); 
			try {
				connection = mysqlConnection.dbconnector();
				String query = "SELECT parking.car_num as 맡긴차량번호, customer.first_name as 손님이름, "
						+ "staff.first_name as 직원이름, parking_staff.receive_date as 맡긴날짜, "
						+ "parking_staff.receive_time as 맡긴시간, parking_staff.give_date as 받은날짜, parking_staff.give_time as 받은시간 "
						+ "from (((parking_staff "
						+ "inner join parking on parking.id = parking_staff.parking_id) "
						+ "inner join customer on parking.customer_id = customer.id) "
						+ "inner join staff on parking_staff.staff_id = staff.id)";
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			JScrollPane scrollPane = new JScrollPane(table);
			NewWindowContainer.add(scrollPane, BorderLayout.CENTER);	
		}
	}
	
	class BillnewWindow extends JFrame{
		BillnewWindow(){
			setTitle("Bill");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);
			setSize(500,300);
			setResizable(false);
			setVisible(true);
			setLocation(400,300);
			
			table = new JTable();
			add(table); 
			try {
				connection = mysqlConnection.dbconnector();
				String query = "select customer.login_id as 고객아이디, customer.first_name as 고객이름, "
						+ "(room.price + (0.5*room_reservation.breakfast)) as 총가격 "
						+ "from ((room_reservation "
						+ "inner join customer on room_reservation.customer_id = customer.id) "
						+ "inner join room on room_reservation.room_id = room.room_number)";
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));			
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			JScrollPane scrollPane = new JScrollPane(table);
			NewWindowContainer.add(scrollPane, BorderLayout.CENTER);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					front_taskInfo frame = new front_taskInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}


