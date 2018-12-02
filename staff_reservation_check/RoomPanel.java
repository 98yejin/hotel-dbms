import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;

public class RoomPanel extends JPanel {
    // 호텔 방 표시하는 버튼들
    String id = "custom", pw = "custom";
    Connection conn ;
    JButton[] hotelRooms ;
    String DateInRP = null;

    RoomPanel(int floorNum){
        connectDB();
        // 층을 표시하는 라벨 및 층에 맞는 방 생성
        hotelRooms = makeRoomNumber(floorNum);
        setBorder(new LineBorder(Color.lightGray,2));
        setLayout(new GridLayout(3,2));

        for(int i = 0 ; i < 6 ; i++){
            add(hotelRooms[i]);
        }

        setSize(200,150);
        setVisible(true);
    }

    // 층마다 맞는 방을 생성하는 함수
    JButton[] makeRoomNumber(int floorNum){
        JButton[] rooms = new JButton[6] ;
        int[] roomNumbers = {11,12,21,22,31,32};
        int roomNumber  = 0 ;
        for(int i = 0 ; i < 6 ; i++){
            roomNumber = floorNum * 100 + roomNumbers[i];
            rooms[i] = new JButton(Integer.toString(roomNumber));
            rooms[i].setSize(100,50);
            rooms[i].addActionListener(new RoomButtonListener());
        }

        return rooms ;
    }

    void connectDB(){
        conn = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldbms?serverTimezone=Asia/Seoul",id,pw);
            if(conn == null){
                System.out.println("RoomPanel : fail to connect");
            }else{
                System.out.println("RoomPanel : success");
            }
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 호텔 방 버튼을 눌렀을 때 새로운 창이 뜨게하는 listener
    class RoomButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            new RoomInformation(b.getText());
        }
    }
    
    class RoomInformation extends JFrame{
        JTextField customer = new JTextField(12);
        JTextField checkIn = new JTextField(12);
        JTextField checkOut = new JTextField(12);
        JTextArea specArea = new JTextArea(5,12);
        int roomNumInRInfo ;

        RoomInformation(String roomNum){
            roomNumInRInfo = Integer.parseInt(roomNum);
            setTitle("Room No."+roomNum+ " Information");
            Container c = getContentPane();
            setLayout(null);

            JLabel roomNumLabel = new JLabel("Room Number   ");
            JLabel customerLabel = new JLabel("Customer   ");
            JLabel checkInLabel = new JLabel("Check In Date   ");
            JLabel checkOutLabel = new JLabel("Check Out Date   ");
            JLabel requirement = new JLabel("Requirement");

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
            requirement.setBounds(40,180,120,30);
            specArea.setBounds(40,220,240,200);

            c.add(roomNumLabel); c.add(roomNumber);
            c.add(customerLabel); c.add(customer);
            c.add(checkInLabel); c.add(checkIn);
            c.add(checkOutLabel); c.add(checkOut);
            c.add(requirement); c.add(specArea);

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
            if(DateInRP != null) {
                String sqlStr = "select first_name, last_name, check_in, check_out, requirement"
                        + " from room_reservation natural join customer "
                        + "where check_in <= \'" + DateInRP + "\' and \'" + DateInRP + "\' < check_out "
                        + "and room_id = "+ roomNumInRInfo;
                PreparedStatement stmt = conn.prepareStatement(sqlStr);
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
                        specArea.setText(rs.getString("requirement"));
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
}
