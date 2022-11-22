package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class rogin extends JFrame {
	private JLabel title = new JLabel("동국대학교 중앙도서관 키오스크");
	private JLabel StudentNumber = new JLabel("학번");
	private JTextField input_sn = new JTextField("");
	private JLabel StudentPassword = new JLabel("비밀번호");
	private JTextField input_sp = new JTextField("");
	private JButton btn_rogin = new JButton("로그인");
	private JButton btn_home = new JButton("홈");
	
	public rogin() {
		
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane(); 
		
		GridLayout grid = new GridLayout(7,1);
		grid.setVgap(10);
		con.setLayout(grid);
		
		title.setHorizontalAlignment(NORMAL);
		StudentNumber.setHorizontalAlignment(NORMAL);
		StudentPassword.setHorizontalAlignment(NORMAL);
		
		btn_rogin.addActionListener(new rogin_ActionListener());
		btn_home.addActionListener(new inActionListener());
		
		con.add(title);
		con.add(StudentNumber);
		con.add(input_sn);
		con.add(StudentPassword);
		con.add(input_sp);
		con.add(btn_rogin);
		con.add(btn_home);	

		setSize(300, 600);
		setVisible(true);
	}
	
	class rogin_ActionListener implements ActionListener {		
		Database db = new Database();
		public void actionPerformed(ActionEvent e) {
			
			String id = input_sn.getText().trim();
			String pw = input_sp.getText().trim();
			
			if(id.equals("") || pw.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				System.out.println("로그인 실패 > 로그인 정보 미입력");
			}
			
			else if(id != null && pw != null) {
				if(id.equals("test") && pw.equals("test1")) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
					new borrow();
					dispose();
					return;
					}
				else
					JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
				/*
				if(db.logincheck(id, pw)) {	//이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
					System.out.println("로그인 성공");
					JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
					// 대출, 예약, 연장 총 화면으로 넘어가기
					
				} else {
					System.out.println("로그인 실패 > 로그인 정보 불일치");
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
				}
				*/
			}
		}
	}
	
	class inActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();				
			if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}
		}
	}
}