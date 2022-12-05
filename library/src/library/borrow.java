package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import library.reservation.inActionListener;

public class borrow extends JFrame{
	Database db = new Database();
	private JPanel pn = new JPanel();
	private JButton btn1 = new JButton("대출"); // 상단 대출 버튼
	private JButton btn2 = new JButton("예약"); // 상단 예약 버튼
	private JButton btn3 = new JButton("연장"); // 상단 연장 버튼
	private JLabel lb = new JLabel("대출할 도서ID를 입력해주세요."); // 안내 라벨 추가
	private JLabel bookId = new JLabel("도서ID"); // 도서 ID 라벨 추가
	private JTextField bookIdtf = new JTextField(18); // 도서 ID 입력하는 TextField 추가
	private JButton borrow = new JButton("대출"); // 대출하기 버튼

	public borrow() {
		setTitle("대출");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		// 리스너 달기
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn3.addActionListener(new inActionListener());

		// 버튼 설정
		btn1.setLocation(1,0);
		btn2.setLocation(233,0);
		btn3.setLocation(466,0);
		btn1.setSize(233,100);
		btn2.setSize(233,100);
		btn3.setSize(233,100);
		btn1.setBackground(new Color(255, 204, 153));
		btn2.setBackground(Color.white);
		btn3.setBackground(Color.white);

		Font big_font = new Font("NanumSquare", Font.BOLD, 30); // 폰트 객체 생성
		btn1.setFont(big_font);
		btn2.setFont(big_font);
		btn3.setFont(big_font);

		// 대출 방법 알려주는 라벨 설정
		lb.setHorizontalAlignment(NORMAL); // 라벨 가운데 정렬
		lb.setLocation(130,200);
		lb.setSize(449,34);

		// 도서ID 입력 패널 설정
		pn.add(bookId);
		pn.add(bookIdtf);
		pn.setLocation(59, 300);
		pn.setSize(560, 61);

		Font plain_font = new Font("NanumSquare", Font.PLAIN, 25);
		bookId.setFont(plain_font);
		bookIdtf.setFont(plain_font);
		lb.setFont(plain_font);

		// 대출버튼
		borrow.setFont(big_font);
		borrow.setForeground(new Color(255,255,255));
		borrow.setBackground(new Color(255,153,051));
		//borrow.setSize(210,100);
		borrow.setSize(193, 96);
		borrow.setLocation(250, 400);

		borrow.addActionListener(new search_ActionListener());

		// 홈버튼
		ImageIcon homeIcon = new ImageIcon("library/images/home.png");
		Image homeimg = homeIcon.getImage();
		homeimg = homeimg.getScaledInstance(52, 52,  java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(homeimg);

		// 홈버튼 생성 및 설정
		JButton btn_home = new JButton("Home",homeIcon);
		btn_home.setBackground(Color.gray);
		btn_home.addActionListener(new inActionListener());
		btn_home.setFont(big_font);
		btn_home.setForeground(new Color(255,255,255));

		btn_home.setSize(210, 100);
		btn_home.setLocation(240, 670);

		// 컨테이너에 컴포넌트들 추가
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		c.add(lb);
		c.add(pn);
		c.add(borrow);
		c.add(btn_home);
		c.add(btn_home);

		setSize(700,800);
		setVisible(true);
	}

	class inActionListener implements ActionListener { // 버튼 누를 때의 액션리스너 추가
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("대출"))   { // 버튼명이 대출이라면
				new borrow(); // 대출 페이지로 이동
				dispose();
			}
			else if (b.getText().equals("예약")){ // 버튼명이 예약이라면
				new reservation(); // 예약 페이지로 이동
				setVisible(false);
			}
			else if (b.getText().equals("연장")){ // 버튼명이 연장이라면
				new extension(); // 연장 페이지로 이동
				setVisible(false);
			}
			else if (b.getText().equals("Home"))  { // 버튼명이 Home이라면
				db.logout(); // 로그아웃
				new library_main(); // 메인 페이지로 이동
				dispose();
			}
		}
	}
	class search_ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String book = bookIdtf.getText().trim(); // 입력받은 도서ID
 			if(book.equals("-")){ // 입력받은 값이 없다면
				JOptionPane.showMessageDialog(null, "도서 ID를 입력해주세요.", "도서 대출 실패", JOptionPane.ERROR_MESSAGE);
			}
			else if(book != null) { // 입력받은 값이 있다면
				db.borrow(book); // db의 borrow메소드 호출

			}
		}
	}
}
