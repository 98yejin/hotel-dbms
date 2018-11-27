package ex181121;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class reg {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel idLabel = new JLabel("예약이 완료되었습니다.");
	private JLabel iddLabel = new JLabel("예약번호");
	private JLabel idddLabel = new JLabel("263783");
    private JLabel loginButton = new JLabel("Check in");
    private JLabel login = new JLabel("2018/11/24");
    private JLabel loginButton2 = new JLabel("Check out");
    private JLabel login2 = new JLabel("2018/11/28");
    
    public reg() {
    	panel.setLayout(null);
    	idLabel.setBounds(300,100,400,200);
    	idLabel.setFont(new Font("Courier", Font.BOLD, 20));
        iddLabel.setBounds(340,270,200,100);
        idddLabel.setBounds(400,270,200,100);
        loginButton.setBounds(190, 450, 80, 35);
        login.setBounds(260,450,80,35);
        loginButton2.setBounds(450,450,80,35);
        login2.setBounds(525,450,80,35);
        
        
        
        panel.add(idLabel);
        panel.add(iddLabel);
        panel.add(idddLabel);
        panel.add(loginButton);
        panel.add(login);
        panel.add(loginButton2);
        panel.add(login2);
        
        frame.add(panel);
        
        
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	new reg();
    }

}