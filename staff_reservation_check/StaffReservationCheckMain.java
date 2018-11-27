package staff_reservation_check;

import java.sql.*;
import java.awt.* ;
import java.awt.event.* ;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.swing.* ;
import javax.swing.event.*;

import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.*;
import org.jdatepicker.util.*;
import org.jdatepicker.impl.*;


/*
 *
 * 필요한 것들
 *
 * 1. method
 *
 * - 예약 정보 가져오기 method (취소될 때 어떻게 작동하는 지...)
 * - 시간
 * - 총 예약 건수
 *
 * 2. 디자인
 *
 * - 제목 글
 *
 * 3. 예약 정보 가져오기 method
 *
 * 1) 일단 날짜를 선택해서 해당하는 날짜에 있는 것만 가져오기
 * 2) 그리고 해당 날짜에 대한 예약을 실시간으로 접수하기
 * 3) 시간을 표시할 수 있도록 하기
 * 4) 색깔.. -> update를 눌렀을 때 바꾸어야하나...??
 *
 *
 * */

public class StaffReservationCheckMain extends JFrame {
    // 날짜를 반환하기 위한 select 버튼
    JButton select ;
    // calender
    JDatePickerImpl datePicker;

    StaffReservationCheckMain(){

        // Frame 정보 표시
        setTitle("Staff Reservation Check");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        // 층 마다 방 표시하는 클래스 및 update 버튼 초기화
        RoomPanel f2 = new RoomPanel(2);
        RoomPanel f3 = new RoomPanel(3);
        RoomPanel f4 = new RoomPanel(4);
        JButton update = new JButton("Update");

        // calender를 위한 속성 추가
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // 날짜 선택에 calender로 선택하기 추가
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter() );

        // 선택한 날짜를 반환하기 위한 select 버튼 추가
        select = new JButton("select");

        // calender 창과 select 버튼 크기 조정
        datePicker.setBounds(10,10,150,50);
        select.setBounds(160,10,100,30);

        // select 버튼에 액션 추가
        select.addActionListener(new DateListener());

        // 층을 나타내는 버튼, update 버튼 boundary 설정
        f2.setBounds(50,250,200,150);
        f3.setBounds(300,250,200,150);
        f4.setBounds(550,250,200,150);
        update.setBounds(600,100,100,50);

        // update 버튼, calender, select 버튼, 층 수를 선택할 수 있는 거 추가
        c.add(update);
        c.add(f2) ; c.add(f3) ; c.add(f4);
        c.add(select);c.add(datePicker);

        setSize(800,600);
        setVisible(true);
    }

    class DateListener implements ActionListener{

        // 버튼을 눌렀을 시에 선택한 날짜를 가져오도록 하기 (NullPointerException을 배제하기 위해 선택)
        @Override
        public void actionPerformed(ActionEvent e) {
            if(datePicker.getModel().getValue() != null) {
                Date selectedDate = (Date) datePicker.getModel().getValue();

                // 선택이 잘 되었는 지 보기 위한 출력용 코드 -> 나중에 사라지고 이 자리에 sql이 들어갈 것이다
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(selectedDate);
                System.out.println(date);
            }
        }
    }

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
