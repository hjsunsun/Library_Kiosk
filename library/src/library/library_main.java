package library;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class library_main extends JFrame {
	private ImageIcon adminIcon = new ImageIcon("library/images/dgurogo.png"); // 관리자 모드 이미지 아이콘
	private ImageIcon backIcon = new ImageIcon("library/images/dgurogo2.png"); // 배경 이미지 아이콘
	private JLabel title = new JLabel("<HTML><body><center>동국대학교 중앙도서관<br>키오스크</center></body></HTML>"); // 제목 
	private MyDialog password; // 다이얼로그 선언
	static Database db = new Database(); // db 연동

	// 생성자

	public library_main() {
		setTitle("동국대학교 중앙도서관 키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);

		// 이미지 불러오기(이미지 크기 재설정): 관리자 버튼 이미지
		Image adminimg = adminIcon.getImage();
		adminimg = adminimg.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		adminIcon = new ImageIcon(adminimg);

		// 이미지 불러오기(이미지 크기 재설정): 배경 
		Image backimg = backIcon.getImage();
		backimg = backimg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(backimg);
		// 라벨에 이미지를 넣고, 라벨의 위치를 설정하여 화면 중간에 배치한다.
		JLabel back_img = new JLabel(backIcon); 
		back_img.setSize(400, 400);
		back_img.setLocation(150, 50);

		// 버튼 생성
		JButton btn1 = new JButton("<HTML><body><center>로그인<br>(대출/연장/예약)</center></body></HTML>");
		btn1.setBackground(new Color(255, 153, 51));
		JButton btn2 = new JButton("반납");
		btn2.setBackground(new Color(255, 153, 102));
		JButton btn_manager = new JButton(adminIcon);

		// 버튼 크기 설정
		btn1.setSize(225, 225);
		btn2.setSize(225, 225);
		btn_manager.setSize(48, 48);

		// 버튼 레이아웃 설정
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		btn_manager.setBorderPainted(false);

		// 버튼배경 투명 설정
		btn_manager.setContentAreaFilled(false);
		btn_manager.setOpaque(false);

		// 버튼 위치설정
		btn1.setLocation(97, 498);
		btn2.setLocation(378, 498);
		btn_manager.setLocation(603, 32);

		// 리스너 추가
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn_manager.addActionListener(new inActionListener());

		// 폰트 설정
		Font t_font = new Font("a옛날사진관5", Font.BOLD, 40);
		Font btn1_font = new Font("NanumSquare", Font.BOLD, 25);
		Font btn2_font = new Font("NanumSquare", Font.BOLD, 25);

		// 제목 폰트 및 사이즈, 위치 설정 
		title.setFont(t_font);
		title.setLocation(150, 200);
		title.setSize(410, 100);
		
		// 버튼 폰트 설정
		btn1.setFont(btn1_font);
		btn2.setFont(btn2_font);

		// 창에 컴포넌트 
		c.add(title);
		c.add(back_img);
		c.add(btn1);
		c.add(btn2);
		c.add(btn_manager);

		// 다이얼로그 생성 후 관리자 버튼에 리스너 추가
		password = new MyDialog(this, "관리자 비밀번호");
		btn_manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password.setVisible(true);
			}
		});

		setSize(700, 800);
		setVisible(true);

	}

	// 배경 이미지 설정
	class background extends JPanel {
		private ImageIcon back_Image = new ImageIcon("library/images/back_img.png");
		private Image img = back_Image.getImage(); // 이미지 객체

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지의 크기를 들어가는 공간의 크기에 맞추고, 들어가는 공간의 x좌표 0, y좌표0 위치시킨다. 
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// 다이얼로그 창 생성(관리자 모드에 들어가기전 거쳐야 작업)
	class MyDialog extends JDialog {
		private JLabel la = new JLabel("비밀번호를 입력하세요.");
		private JTextField tf = new JTextField(15); // 비밀 번호 입력 공간
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
					// textfield에 입력된 값의 정해진 비밀번호와 일치하면, 관리자 모드 창을 연다.
					if (input.equals(T_password)) {
						new AdminPage();
						dispose();
					} else { // 틀리면, 오류 메세지 뜸
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

	// 눌리 버튼의 이름을 인식하여 같은 이름의 창 열기
	class inActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("<HTML><body><center>로그인<br>(대출/연장/예약)</center></body></HTML>")) {

				new login();
				dispose();	// 기존 창 닫기
			} else if (b.getText().equals("반납")) {
				new returnBook();
				setVisible(false);	// 기존 창 닫기: dispose();와 같은 역할을 함
			}
		}
	}

	// 이곳에서 윈도우창 실행
	public static void main(String[] args) {
		db.logout();
		new library_main();
		new Database();
	}
}
