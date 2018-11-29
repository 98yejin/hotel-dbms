package staff_reservation_check;

import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/*
*
* 필요한 것들
*
* 1. method
*
* - 정보 가져오기 method(고객의 신원)
*
* - 예약 정보를 색깔로 표시하는 method
*
* */

public class RoomPanel extends JPanel {
    // 호텔 방 표시하는 버튼들
    JButton[] hotelRooms ;

    RoomPanel(int floorNum){
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

    // 호텔 방 버튼을 눌렀을 때 새로운 창이 뜨게하는 listener
    class RoomButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            new RoomInformation(b.getText());
        }
    }


    /*
    *
    * 방정보를 표시하는 클래스
    *
    * [ 추가할만한 사항 ]
    *
    * - 변경 내용을 적고 수정한것을 저장하는 것이 있으면 괜찮을 것 같다
    * - 수정사항이나 disturb 같은 것들이 있으면 괜찮을 것 같다
    * */
    class RoomInformation extends JFrame{

        RoomInformation(String roomNum){
            setTitle("Room No."+roomNum+ " Information");
            Container c = getContentPane();
            setLayout(null);

            JLabel roomNumLabel = new JLabel("Room Number   ");
            JLabel customerLabel = new JLabel("Customer   ");
            JLabel checkInLabel = new JLabel("Check In Date   ");
            JLabel checkOutLabel = new JLabel("Check Out Date   ");
            JLabel specification = new JLabel("Specification");

            JTextField roomNumber = new JTextField(roomNum,5);
            JTextField customer = new JTextField(12);
            JTextField checkIn = new JTextField(12);
            JTextField checkOut = new JTextField(12);
            JTextArea specArea = new JTextArea(5,12);

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
            specification.setBounds(40,180,120,30);
            specArea.setBounds(40,220,240,200);

            c.add(roomNumLabel); c.add(roomNumber);
            c.add(customerLabel); c.add(customer);
            c.add(checkInLabel); c.add(checkIn);
            c.add(checkOutLabel); c.add(checkOut);
            c.add(specification); c.add(specArea);


            setSize(320,470);
            setVisible(true);
        }
    }
}
