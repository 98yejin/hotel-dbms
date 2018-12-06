package cleaning;

import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CleaningPage extends JFrame {

    static Connection conn = null;

    // String staffID = "18030006";

    // 오늘 날짜 표시를 위한 것
    JLabel showToday;
    java.util.Date today = new java.util.Date();
    String Date ;

    JButton[] manageHotelRooms ;

    JTextArea cleaningYText = new JTextArea();


    // 색깔 표시
    JButton[] f2Rooms = makeRoomNumber(2) ;
    JButton[] f3Rooms = makeRoomNumber(3) ;
    JButton[] f4Rooms = makeRoomNumber(4) ;

    ArrayList<String> reservedRooms ;
    ArrayList<String> doNotDisturbRooms ;
    ArrayList<String> cleaningY = new ArrayList<>();
    /*

    do not disturb : setEnabled(false);
    cleaningY : lightGray

    사용중인 아닌 방 : setEnabled(false);

    */

    // DBconnect dbc = new DBconnect(); -> DB연결을 위한 것 아직은 쓸모가 없을 듯

    CleaningPage(){
    	
    	TestConnect tc = new TestConnect();
        setTitle("Cleaning Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();
        setSize(800,600);

        cleaningYText.setBounds(630,50,120,100);
        cleaningYText.setEnabled(false);

        // JButton update = new JButton("Update");
        // update.addActionListener(new UpdateListener());
        // update.setBounds(650,50,100,30);

        // 창 가운데 정렬
        Dimension frameSize = this.getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

        SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
        Date = todayDate.format(today);
        showToday = new JLabel("Today : "+Date);
        showToday.setBounds(50,50,150,20);

        ManageRoomPanel mrp2 = new ManageRoomPanel(f2Rooms);
        ManageRoomPanel mrp3 = new ManageRoomPanel(f3Rooms);
        ManageRoomPanel mrp4 = new ManageRoomPanel(f4Rooms);

        mrp2.setBounds(50,250,200,150);
        mrp3.setBounds(300,250,200,150);
        mrp4.setBounds(550,250,200,150);

        initiallyUpdatedRooms(Date);

        c.add(showToday);
        // c.add(mrp);
        // c.add(update);
        c.add(mrp2); c.add(mrp3); c.add(mrp4);
        c.add(cleaningYText);

        setVisible(true);
    }

    /*
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
    */

    JButton[] makeRoomNumber(int floorNum){
        JButton[] rooms = new JButton[6] ;
        int[] roomNumbers = {11,12,21,22,31,32};
        int roomNumber  = 0 ;
        for(int i = 0 ; i < 6 ; i++){
            roomNumber = floorNum * 100 + roomNumbers[i];
            rooms[i] = new JButton(Integer.toString(roomNumber));
            rooms[i].setSize(100,50);
            rooms[i].setForeground(Color.RED);
        }

        return rooms ;
    }

    /*
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
    */


    class ManageRoomPanel extends JPanel{
        ManageRoomPanel(JButton[] fRooms){
            setBorder(new LineBorder(Color.lightGray,2));
            setLayout(new GridLayout(3,2));

            for(int i = 0 ; i < fRooms.length ; i++){
                add(fRooms[i]);
                fRooms[i].addActionListener(new CleaningRoomListener());
            }

            setSize(200,150);
            setVisible(true);
        }
    }


    /*

    최초상태 창 : 바로 색깔

    색깔 :

    버튼을 눌렀을 때 : 색깔을 변경한다 -> update




    */

    class CleaningRoomListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if(reservedRooms.contains(b.getText()) && !doNotDisturbRooms.contains(b.getText())) {
                b.setForeground(Color.GREEN);
                if(!cleaningY.contains(b.getText())) {
                    cleaningY.add(b.getText());
                    String str = cleaningYText.getText();
                    str += b.getText();
                    cleaningYText.setText(str);
                }
            }
        }
        /*
        public void addCleaningY(){
            try{
            }
            catch(SQLException crse){
                crse.printStackTrace();
            }
        }
        */
    }


    public void findNotResevRooms(JButton[] rooms, ArrayList<String> seletedRooms){
        for(int i = 0 ; i < rooms.length ; i ++){
            if(!reservedRooms.contains(rooms[i].getText())){
                rooms[i].setEnabled(false);
            }
        }
    }

    public void findDoNotDisturbRooms(JButton[] rooms, ArrayList<String> seletedRooms){
        for(int i = 0 ; i < rooms.length ; i ++){
            if(doNotDisturbRooms.contains(rooms[i].getText())){
                rooms[i].setForeground(Color.lightGray);
            }
        }
    }

    public void initiallyUpdatedRooms(String Date) {
        reservedRooms = new ArrayList<>();
        doNotDisturbRooms = new ArrayList<>();
        try{
            // 그 및에는 예약된 방 가져오기
            String sqlStr = "select distinct room_id from room_reservation natural join daily_room_manage "
                    + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out";

            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();

            String num ;

            System.out.println("reserved------------");
            while (rs.next()) {
                num = rs.getString("room_id");
                reservedRooms.add(num);
                System.out.println(num);
                ;
            }


            sqlStr = "select room_id from room_reservation as rr join daily_room_manage as drm on rr.id = drm.room_reservation_id"
                    + " where date = " + "\'" + Date + "\'" + " and disturbYN = 1";

            System.out.println(sqlStr);
            PreparedStatement stmt2 = conn.prepareStatement(sqlStr);
            ResultSet rs2 = stmt2.executeQuery();

            System.out.println("not disturb------------");
            while(rs2.next()){
                num = rs2.getString("room_id");
                doNotDisturbRooms.add(num);
            }

            stmt.close();
            rs.close();

            // 예약된 방 찾기
            findNotResevRooms(f2Rooms,reservedRooms);
            findNotResevRooms(f3Rooms,reservedRooms);
            findNotResevRooms(f4Rooms,reservedRooms);
            findDoNotDisturbRooms(f2Rooms,doNotDisturbRooms);
            findDoNotDisturbRooms(f3Rooms,doNotDisturbRooms);
            findDoNotDisturbRooms(f4Rooms,doNotDisturbRooms);

        }catch(SQLException se3){
            se3.printStackTrace();
        }
    }



    /*
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
    */

    /*
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
    */

    // 방번호 가져오기

    public static void main(String args[]){
        new CleaningPage();
    }
}