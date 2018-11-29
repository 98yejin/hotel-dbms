package ex181121;

import java.awt.Frame;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mypage {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel idLabel = new JLabel("My page");
	private JLabel pwdLabel = new JLabel("비밀번호");
    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginButton = new JButton("로그인");
    
    public mypage() {
    	panel.setLayout(null);
    	idLabel.setBounds(360,10,300,300);
    	idLabel.setFont(new Font ("Courier", Font.BOLD, 18));
    	pwdLabel.setBounds(300, 270, 60, 30);
        pwdInput.setBounds(350, 270, 80, 30);
        loginButton.setBounds(450, 268, 80, 35);
        
        panel.add(pwdLabel);
        panel.add(idLabel);
        panel.add(pwdInput);
        panel.add(loginButton);
        
        frame.add(panel);
        
        frame.setTitle("mypage");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	new mypage();
    }

}