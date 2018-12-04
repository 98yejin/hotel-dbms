import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DB_connect_test.*;

public class RoomManagePage extends JFrame {

    String staffID = "18030006";

    JLabel showToday;
    java.util.Date today = new java.util.Date();
    String Date ;

    JButton[] manageHotelRooms ;

    JLabel staffInformation = new JLabel("Name : ");

    ArrayList<String> reservedRooms ;

    DBconnect dbc = new DBconnect();

    RoomManagePage(){

        setTitle("Room Manage Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();
        setSize(800,600);

        bringStaffInfo();

        JButton update = new JButton("Update");
        update.addActionListener(new UpdateListener());
        update.setBounds(650,50,100,30);

        // 창 가운데 정렬
        Dimension frameSize = this.getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

        SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
        Date = todayDate.format(today);
        showToday = new JLabel("Today : "+Date);
        showToday.setBounds(50,50,150,20);

        ManageRoomPanel mrp = new ManageRoomPanel();
        Dimension panelSize = mrp.getSize();
        mrp.setLocation((frameSize.width - panelSize.width) / 2, (frameSize.height- panelSize.height)/2);

        initiallyUpdatedRooms(Date);

        staffInformation.setBounds(500,50,150,20);

        c.add(showToday);
        c.add(mrp);
        c.add(update);
        c.add(staffInformation);

        setVisible(true);
    }

    public int countRoomNumber() {
        int countRoom = 0 ;
        try {
            String sqlStr = "select count(room_number) from room where staff_id = " + staffID;
            PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();
            String count = "";
            while (rs.next()) {
                count = rs.getString("count(room_number)");
            }
            countRoom = Integer.parseInt(count);
        }catch(SQLException cse){
            cse.printStackTrace();
        }

        return countRoom ;
    }

    public JButton[] bringRoomnumber(int length){
        JButton[] roomNum = new JButton[length];
        try{
            String sqlStr = "select room_number from room where staff_id = "+staffID;
            PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();
            int i = 0 ;
            while(rs.next()){
                roomNum[i] = new JButton(rs.getString("room_number"));
                i++;
            }
        }catch(SQLException bse){
            bse.printStackTrace();
        }

        return roomNum ;
    }

    class ManageRoomPanel extends JPanel{
        ManageRoomPanel(){
            int numOfRooms = countRoomNumber() ;
            manageHotelRooms = bringRoomnumber(numOfRooms);

            setBorder(new LineBorder(Color.lightGray,2));
            setLayout(new GridLayout(1,numOfRooms));

            for(int i = 0 ; i < numOfRooms ; i++){
                add(manageHotelRooms[i]);
                manageHotelRooms[i].addActionListener(new ManageRoomListener());
            }

            setSize(100 * numOfRooms,50);
            setVisible(true);
        }
    }

    class ManageRoomListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            new RequirementFrame(b.getText());
        }
    }

    public void findRooms(JButton[] rooms, ArrayList<String> seletedRooms){
        for(int i = 0 ; i < rooms.length ; i ++){
            if(seletedRooms.contains(rooms[i].getText())){
                rooms[i].setForeground(Color.RED);
            }else{
                rooms[i].setForeground(Color.GREEN);
            }
        }
    }

    public void bringStaffInfo(){
        try{
            String sqlStr = "select first_name, last_name, task from staff as s join department as d on s.department_id = d.id where s.id ="
                    + staffID ;
            PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String str = staffInformation.getText() + rs.getString("first_name") + " "
                        +rs.getString("last_name");
                staffInformation.setText(str);
            }
        }catch(SQLException se3){
            se3.printStackTrace();
        }
    }

    public void initiallyUpdatedRooms(String Date) {
        try{

            // 그 및에는 예약된 방 가져오기
            reservedRooms = new ArrayList<>();
            String sqlStr = "select room_id from room_reservation "
                    + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out";
            PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservedRooms.add(rs.getString("room_id"));
            }

            stmt.close();
            rs.close();

            // 예약된 방 찾기
            findRooms(manageHotelRooms,reservedRooms);

        }catch(SQLException se3){
            se3.printStackTrace();
        }
    }

    class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            reservedRooms = new ArrayList<>();

            if(Date != null) {
                try {
                    String sqlStr = "select room_id from room_reservation "
                            + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out";
                    PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        reservedRooms.add(rs.getString("room_id"));
                    }

                    stmt.close();
                    rs.close();

                } catch (SQLException se) {
                    se.printStackTrace();
                }

                findRooms(manageHotelRooms, reservedRooms);
            }
        }
    }

    class RequirementFrame extends JFrame{
        JTextField customer = new JTextField(12);
        JTextField checkIn = new JTextField(12);
        JTextField checkOut = new JTextField(12);
        JTextArea requirement = new JTextArea(5,12);
        int roomNumInRF ;

        RequirementFrame(String roomNum){
            roomNumInRF = Integer.parseInt(roomNum);
            setTitle("Room No."+roomNum+ " Information");
            Container c = getContentPane();
            setLayout(null);

            JLabel roomNumLabel = new JLabel("Room Number   ");
            JLabel customerLabel = new JLabel("Customer   ");
            JLabel checkInLabel = new JLabel("Check In Date   ");
            JLabel checkOutLabel = new JLabel("Check Out Date   ");
            JLabel requirementLabel = new JLabel("Requirement");

            JTextField roomNumber = new JTextField(roomNum,5);

            roomNumber.setEnabled(false);
            customer.setEnabled(false);
            checkIn.setEnabled(false);
            checkOut.setEnabled(false);


            roomNumLabel.setBounds(40,20,120,30);
            roomNumber.setBounds(160,20,120,30);
            customerLabel.setBounds(40,60,120,30);
            customer.setBounds(160,60,120,30);
            checkInLabel.setBounds(40,100,120,30);
            checkIn.setBounds(160,100,120,30);
            checkOutLabel.setBounds(40,140,120,30);
            checkOut.setBounds(160,140,120,30);
            requirementLabel.setBounds(40,180,120,30);
            requirement.setBounds(40,220,240,200);

            c.add(roomNumLabel); c.add(roomNumber);
            c.add(customerLabel); c.add(customer);
            c.add(checkInLabel); c.add(checkIn);
            c.add(checkOutLabel); c.add(checkOut);
            c.add(requirementLabel); c.add(requirement);

            try {
                collectRoomInfo();
            }catch(SQLException se){
                se.printStackTrace();
            }

            setSize(320,470);

            // 창 가운데 정렬
            Dimension frameSize = this.getSize();
            Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

            setVisible(true);
        }

        public void collectRoomInfo() throws SQLException{
            if(Date != null) {
                String sqlStr = "select first_name, last_name, check_in, check_out, requirement"
                        + " from room_reservation natural join customer "
                        + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out "
                        + "and room_id = "+ roomNumInRF;
                PreparedStatement stmt = dbc.conn.prepareStatement(sqlStr);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    if(rs.getString("first_name") != null && rs.getString("last_name") != null) {
                        customer.setText(rs.getString("first_name") + " "+rs.getString("last_name"));
                    }
                    if(rs.getString("check_in") != null && rs.getString("check_out") != null) {
                        checkIn.setText(rs.getString("check_in"));
                        checkOut.setText(rs.getString("check_out"));
                    }
                    if(rs.getString("requirement") != null) {
                        requirement.setText(rs.getString("requirement"));
                    }
                }

                stmt.close();
                rs.close();
            }
            else{
                System.out.println("Date in RoomPanel is null");
            }
        }
    }
    public static void main(String args[]){
        new RoomManagePage();
    }
}
