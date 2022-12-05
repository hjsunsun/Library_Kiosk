package library;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.*;

public class member_add extends JFrame {
	Connection con = null;
	Statement stmt = null;

	public member_add() {
		con = null;
		stmt = null;
		String url = "jdbc:mysql://124.56.138.3:30/db2019110340"; // dbstudy 스키마
		String username = "2019110340";
		String password = "test1234!@#$QWER";

		setTitle("도서정보"); // 타이틀
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245, 245, 245));

		// 상단라벨 설정,크기지정,위치지정,라벨 가운데,라벨배경지정
		JLabel label = new JLabel("회원정보 추가");
		label.setSize(700, 100);
		label.setLocation(0, 0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255, 204, 153));

		// 상단 폰트 생성, 폰트 라벨에 적용,
		Font big_font = new Font("NanumSquare", Font.BOLD, 30);
		label.setFont(big_font);

		// 패널 만들고 사이즈와 위치선정
		JPanel jPanel_member_add = new JPanel();
		jPanel_member_add.setLayout(new GridLayout(7, 2, 5, 20));
		jPanel_member_add.setSize(600, 400);
		jPanel_member_add.setLocation(50, 150);
		jPanel_member_add.setBackground(new Color(245, 245, 245));

		// 패널 안에 컨포넌트 넣기
		jPanel_member_add.add(new JLabel(" 학번"));
		JTextField Textnum = new JTextField("");
		jPanel_member_add.add(Textnum);
		jPanel_member_add.add(new JLabel(" 비밀번호"));
		JTextField Textpassword = new JTextField("");
		jPanel_member_add.add(Textpassword);
		jPanel_member_add.add(new JLabel(" 이름"));
		JTextField name = new JTextField("");
		jPanel_member_add.add(name);
		jPanel_member_add.add(new JLabel(" 전공"));
		JTextField major = new JTextField("");
		jPanel_member_add.add(major);
		jPanel_member_add.add(new JLabel(" 전화번호"));
		JTextField phone = new JTextField("");
		jPanel_member_add.add(phone);
		jPanel_member_add.add(new JLabel("이메일"));
		JTextField email = new JTextField("");
		jPanel_member_add.add(email);
		/*
		 * jPanel_member_add.add(new JLabel(" 대출가능권수")); JTextField borrow=new
		 * JTextField(""); jPanel_member_add.add(borrow);
		 */

		// 확인버튼 생성
		JButton btn_plus = new JButton("확인");
		btn_plus.setSize(110, 62);
		btn_plus.setLocation(204, 670);
		btn_plus.setFont(big_font);
		btn_plus.setForeground(new Color(255, 255, 255));
		btn_plus.setBackground(new Color(255, 204, 153));
		btn_plus.setBorderPainted(false);

		// 뒤로가기버튼 생성
		JButton btn_back = new JButton("뒤로가기");
		btn_back.setSize(157, 62);
		btn_back.setLocation(339, 670);
		btn_back.setFont(big_font);
		btn_back.setForeground(new Color(255, 255, 255));
		btn_back.setBackground(new Color(255, 153, 051));
		btn_back.setBorderPainted(false);

		// 프레임에 추가
		c.add(jPanel_member_add);
		c.add(label);
		c.add(btn_back);
		c.add(btn_plus);

		setVisible(true);
		setSize(700, 800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// 저장버튼 누르면 db저장, 창 내려감.
		btn_plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result1 = 5;
				// 하나라도 입력값없으면 저장 못하게
				if (((Textnum.getText()).equals("")) || ((Textpassword.getText()).equals(""))
						|| ((name.getText()).equals("")) || ((major.getText()).equals(""))
						|| ((phone.getText()).equals("")) || ((email.getText()).equals(""))) {
					JOptionPane.showMessageDialog(null, "모든항목을 입력해주세요", "오류 메세지", JOptionPane.ERROR_MESSAGE);
				}

				else {// 모든 항목이 입력된 경우
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(url, username, password);
						stmt = con.createStatement();
						String sql = "select count(*) from 학생 where 회원아이디 = " + Textnum.getText(); // 학번(PK) 중복확인

						ResultSet result = stmt.executeQuery(sql);

						while (result.next()) {
							result1 = result.getInt(1);
							System.out.println(result1);

						}

					} catch (Exception ex) {
						System.out.println("MySQL 서버 연동 실패 > " + ex.toString());
					}

					if (result1 == 0) { // 중복된 학번이 없을 때
						
						// 회원정보 삽입
						String sql = "INSERT INTO 학생 (`회원아이디`,`비밀번호`, `이름`, `휴대전화`, `이메일`, `학과`, `대출가능권수`) VALUES ( "
								+ Textnum.getText() + ", \"" + Textpassword.getText() + "\", \"" + name.getText()
								+ "\", \"" + phone.getText() + "\", \"" + email.getText() + "\", \"" + major.getText()
								+ "\", 10 );";

						System.out.println(sql);
						new MemberPage();
						dispose();

						try {
							int result = stmt.executeUpdate(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "학번이 중복됩니다.", "오류 메세지", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

		// 뒤로가기 버튼 이벤트처리
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberPage();
				dispose();
			}
		});
	}
}
