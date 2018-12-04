package HDMS2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorMessage implements ActionListener {
	public JFrame frame = new JFrame();
	private JPanel contentPanel = new JPanel();
	private JLabel descriptionLabel = new JLabel("조건에 해당하는 방이 없습니다.");
	private JButton confirmButton = new JButton("확인");
	
	public ErrorMessage() {
		contentPanel.setLayout(null);
		descriptionLabel.setBounds(100, 50, 230, 30);
		descriptionLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		confirmButton.setBounds(150, 100, 100, 30);
		
		contentPanel.add(descriptionLabel);
		contentPanel.add(confirmButton);
		
		confirmButton.addActionListener(this);
		
		frame.setTitle("Error Message");
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(contentPanel);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ErrorMessage();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			System.exit(0);
		}
	}
	
}
