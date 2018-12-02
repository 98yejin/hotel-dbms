import java.sql.*;
import java.awt.* ;
import java.awt.event.* ;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.swing.* ;
import javax.swing.event.*;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.*;
import org.jdatepicker.util.*;
import org.jdatepicker.impl.*;

public class StaffReservationCheckMain extends JFrame {
    // DB Connection -> 이걸 바꾸자 ... 외부에 있는
    Connection conn ;

    // update 버튼
    JButton update ;

    // DB 접속 아이디와 비밀번호
    String id = "custom";
    String pw = "custom";

    // 오늘 날짜를 보여주는 라벨
    JLabel showToday ;

    // 최신 업데이트 시간을 표시하기 위한 Label, 문자열, Format
    JLabel updateTimeLabel ;
    String updateTimeStr ;
    SimpleDateFormat updateTimeFormat = new SimpleDateFormat("HH:mm:ss");

    // 오늘 날짜, 시간 표시
    Date today = new Date();

    // 선택된 방을 담는 ArrayList
    ArrayList<String> selectedRoom = new ArrayList<>();

    // calender
    JDatePickerImpl datePicker;

    // calender에서 선택한 날짜
    java.util.Date selectedDate ;

    // sql 형식으로 변환 및 string으로 추출
    java.sql.Date selectedDate2 = new java.sql.Date(today.getTime());
    String Date  = selectedDate2.toString();

    // RoomPanel
    RoomPanel f2,f3,f4;

    StaffReservationCheckMain(){
        connectDB();

        // Frame 정보 표시
        setTitle("Staff Reservation Check");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        // 층 마다 방 표시하는 클래스 및 update 버튼 초기화
        f2 = new RoomPanel(2);
        f3 = new RoomPanel(3);
        f4 = new RoomPanel(4);

        update = new JButton("Update");
        updateTimeLabel = new JLabel("The Latest Check Time : ");

        // calender를 위한 속성 추가
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // 오늘 날짜 표시를 위한 것들
        SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
        Date = todayDate.format(today);
        showToday = new JLabel("Today : "+Date);

        // 오늘 날짜 처음 예약 바로 표시
        initiallyUpdatedRooms(Date);

        // 날짜 선택에 calender로 선택하기 추가
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter() );


        // calender 창과 오늘 날짜 보여주는 label 조정
        datePicker.setBounds(500,50,150,50);
        showToday.setBounds(50,50,150,20);


        // 층을 나타내는 버튼
        f2.setBounds(50,250,200,150);
        f3.setBounds(300,250,200,150);
        f4.setBounds(550,250,200,150);

        // update 버튼 생성 및 listener 등록
        update.setBounds(650,50,100,30);
        update.addActionListener(new UpdateListener());

        // 최신 업데이트 시간을 보여주는 label 정보 표시
        updateTimeLabel.setBounds(500,500,250,20);

        // update 버튼, calender, 방 선택할 수 있는 거 추가 ( 모든 컴포넌트 추가 )
        c.add(update);
        c.add(f2) ; c.add(f3) ; c.add(f4);
        c.add(datePicker); c.add(showToday);
        c.add(updateTimeLabel);

        setSize(800,600);

        // 창 가운데 정렬
        Dimension frameSize = this.getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);

        setVisible(true);
    }

    // 처음 켜자마자 오늘 예약 뜨기
    public void initiallyUpdatedRooms(String Date) {
        try{
            // 패널에 오늘 날짜 설정
            f2.DateInRP = Date ;
            f3.DateInRP = Date ;
            f4.DateInRP = Date ;

            // 그 및에는 예약된 방 가져오기
            String sqlStr = "select room_id from room_reservation "
                    + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                selectedRoom.add(rs.getString("room_id"));
            }

            stmt.close();
            rs.close();

            // 예약된 방 찾기
            findRooms(f2.hotelRooms,selectedRoom);
            findRooms(f3.hotelRooms,selectedRoom);
            findRooms(f4.hotelRooms,selectedRoom);

        }catch(SQLException se3){
            se3.printStackTrace();
        }
    }

    // DB 연결
    void connectDB(){
        conn = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldbms?serverTimezone=Asia/Seoul",id,pw);
            if(conn == null){
                System.out.println("fail to connect");
            }else{
                System.out.println("success");
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

    // update 버튼에 쓰이는 ActionListener
    class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Date = null ;
            selectedRoom = new ArrayList<>();

            if (datePicker.getModel().getValue() != null) {
                selectedDate = (java.util.Date) datePicker.getModel().getValue();
                selectedDate2 = new java.sql.Date(selectedDate.getTime());
                Date = selectedDate2.toString();

                f2.DateInRP = Date ;
                f3.DateInRP = Date ;
                f4.DateInRP = Date ;

                long updatetime = System.currentTimeMillis();
                updateTimeStr = updateTimeFormat.format(new Date(updatetime));
                updateTimeLabel.setText("The Latest Check Time : " + updateTimeStr);
            }

            if(Date != null) {
                try {
                    String sqlStr = "select room_id from room_reservation "
                            + "where check_in <= \'" + Date + "\' and \'" + Date + "\' < check_out";
                    PreparedStatement stmt = conn.prepareStatement(sqlStr);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        selectedRoom.add(rs.getString("room_id"));
                    }

                    stmt.close();
                    rs.close();

                } catch (SQLException se) {
                    se.printStackTrace();
                }

                findRooms(f2.hotelRooms, selectedRoom);
                findRooms(f3.hotelRooms, selectedRoom);
                findRooms(f4.hotelRooms, selectedRoom);
            }
        }
    }

    // 예약된 방을 찾기 위한 method
    public void findRooms(JButton[] rooms, ArrayList<String> seletedRooms){
        for(int i = 0 ; i < rooms.length ; i ++){
            if(seletedRooms.contains(rooms[i].getText())){
                rooms[i].setForeground(Color.RED);
            }else{
                rooms[i].setForeground(Color.GREEN);
            }
        }
    }

    //jDatePicker 를 위한 Date 표시하는 Formatter
    class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }

    public static void main(String args[]){
        new StaffReservationCheckMain();
    }
}
