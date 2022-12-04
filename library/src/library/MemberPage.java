package library;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//패널사이즈 조정...

public class MemberPage extends JFrame {
	Connection con = null;
	Statement stmt = null;

	public MemberPage() {
		con = null;
		stmt = null;
		String url = "jdbc:mysql://124.56.138.3:30/db2019110340"; // dbstudy 스키마
		String username = "2019110340";
		String password = "test1234!@#$QWER"; // 본인이 설정한 root 계정의 비밀번호를 입력하면 된다.
		setTitle("회원정보"); // 타이틀

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245, 245, 245));

		JPanel jPanel_Member = new JPanel();
		jPanel_Member.setSize(700, 400);
		jPanel_Member.setLocation(0, 130);

		// 상단라벨 설정,크기지정,위치지정,라벨 가운데,라벨배경지정
		JLabel label = new JLabel("회원정보");
		label.setSize(700, 100);
		label.setLocation(0, 0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255, 204, 153));

		// 상단 폰트 생성, 폰트 라벨에 적용,
		Font big_font = new Font("NanumSquare", Font.BOLD, 30);
		label.setFont(big_font);

		// 테이블
		// 1. 테이블 정보정의
		String header[] = { "학번", "비밀번호", "이름", "전공", "전화번호", "이메일", "대출가능권수" };
		String contents[][] = {};

		// 2. jtable 클래스의 객체 생성
		DefaultTableModel Member_model = new DefaultTableModel(contents, header);
		JTable Member_table = new JTable(Member_model);
		JScrollPane Member_scrollPane = new JScrollPane(Member_table);
		Member_scrollPane.setSize(200, 200);
		// 3.
		jPanel_Member.add(Member_scrollPane);

		c.add(jPanel_Member);

		// db에 저장된 데이터-> 테이브로 보여주기
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();

			String sql = "select * from 학생;";

			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				Object data[] = { result.getString("회원아이디"), result.getString("비밀번호"), result.getString("이름"),
						result.getString("휴대전화"), result.getString("이메일"), result.getString("학과"),
						result.getString("대출가능권수") };

				Member_model.addRow(data);
			}

		} catch (Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}

		// 추가버튼 생성, 텍스트 변경
		JButton btn_add = new JButton("추가");
		btn_add.setSize(110, 62);
		btn_add.setLocation(137, 670);
		btn_add.setFont(big_font);
		btn_add.setForeground(new Color(255, 255, 255));
		btn_add.setBackground(new Color(255, 153, 051));
		btn_add.setBorderPainted(false);

		// 삭제버튼 생성
		JButton btn_delete = new JButton("삭제");
		btn_delete.setSize(110, 62);
		btn_delete.setLocation(272, 670);
		btn_delete.setFont(big_font);
		btn_delete.setForeground(Color.red);
		btn_delete.setBackground(new Color(255, 204, 153));
		btn_delete.setBorderPainted(false);

		// 뒤로가기버튼 생성
		JButton btn_back = new JButton("뒤로가기");
		btn_back.setSize(157, 62);
		btn_back.setLocation(407, 670);
		btn_back.setFont(big_font);
		btn_back.setForeground(new Color(255, 255, 255));
		btn_back.setBackground(new Color(255, 153, 051));
		btn_back.setBorderPainted(false);

		// 프레임에 추가
		c.add(label);
		c.add(btn_add);
		c.add(btn_delete);
		c.add(btn_back);

		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(700, 800);
		// 추가버튼 누르면 추가하는 페이지로 이동
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new member_add();
				dispose();

			}
		});
		// 어떤 테이블을 선택하고 삭제를 누르면 삭제됨.
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Member_table.getSelectedRow() == -1) {
					return;
				} else {
					try {
						int row = Member_table.getSelectedRow();
						String sql = "DELETE FROM 학생 where " + Member_table.getValueAt(row, 0) + "= 학생.회원아이디;";

						int result = stmt.executeUpdate(sql);
						System.out.println(result);

					} catch (Exception ex) {
						System.out.println("MySQL 서버 연동 실패 > " + ex.toString());
					}

					Member_model.removeRow(Member_table.getSelectedRow());

				}

			}
		});

		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminPage();
				dispose();
			}
		});
	}
}
