package library;
//dispose();오케이 버튼에서 이거 안되는듯?
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class library_main extends JFrame {
	private JLabel title = new JLabel("동국대학교 중앙도서관 키오스크");
	private JPanel p1 = new JPanel();
	private MyDialog password;
	// 생성자
	public library_main() {
		setTitle("동국대학교 중앙도서관 키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane(); 
		
		GridLayout grid = new GridLayout(3,1);
		grid.setVgap(10);
		c.setLayout(grid); 	
		
		JButton btn1 = new JButton("로그인"); 	
		JButton btn2 = new JButton("반납");
		JButton btn_manager = new JButton("관리자"); 
		
		btn1.addActionListener(new inActionListener()); 
		btn2.addActionListener(new inActionListener());
		btn_manager.addActionListener(new inActionListener());
		
		//title.setHorizontalAlignment(NORMAL);
		p1.setLayout(null);
		
		title.setLocation(50, 50);
		title.setSize(200, 50);
		btn_manager.setLocation(230,10);
		btn_manager.setSize(50,20);
		
		p1.add(title);
		p1.add(btn_manager);
		
		c.add(p1);
		c.add(btn1);
		c.add(btn2);
		
		password = new MyDialog(this, "관리자 비밀번호");
		btn_manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				password.setVisible(true); 
			}
		});

		setSize(300, 600); 
		setVisible(true); 
		
	}

	class MyDialog extends JDialog {
		private JLabel la =new JLabel("비밀번호를 입력하세요.");
		private JTextField tf = new JTextField(15);
		private JButton okButton = new JButton("확인");

		public MyDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(new FlowLayout());
			add(la);
			add(tf);
			add(okButton);
			setSize(300, 100);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = tf.getText();
					// setVisible(false);
					String T_password = "admin";
					if (input.equals(T_password)) {
						new AdminPage();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}
	
	class inActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();				
			if (b.getText().equals("로그인"))	{
				new login();
				dispose();
			}
			else if (b.getText().equals("반납")){
				new returnBook();
				setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new library_main();
		new Database();
	}
}