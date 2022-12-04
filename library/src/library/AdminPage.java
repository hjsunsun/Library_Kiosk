package library;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminPage extends JFrame {
	public AdminPage() {
		setTitle("관리자페이지");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//배치관리자 없이(버튼크기랑 위치 설정때문)
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245, 245, 245));

		//상단라벨 설정,크기지정,위치지정,라벨 가운데,라벨배경지정
		JLabel label = new JLabel("관리자페이지");
		label.setSize(700, 100);
		label.setLocation(0,0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255,204,153));

		//상단 폰트 생성, 폰트 라벨에 적용,
		Font big_font =new Font("a옛날사진관4", Font.BOLD, 30);
		label.setFont(big_font);


		//서브라벨 설정,크기지정,위치지정,라벨 가운데,라벨배경지정
		JLabel sublabel = new JLabel("원하는 메뉴를 선택해주세요.");
		sublabel.setSize(307, 25);
		sublabel.setLocation(196,247);
		sublabel.setHorizontalAlignment(JLabel.CENTER);

		//상단 폰트 생성, 폰트 라벨에 적용,
		Font sub_font =new Font("NanumSquare", Font.PLAIN, 25);
		sublabel.setFont(sub_font);


		//이미지아이콘 -> 이미지로 변경 후 크기조정
		ImageIcon memberIcon = new ImageIcon("images/contact.png");
		ImageIcon bookIcon = new ImageIcon("images/book (1).png");
		Image memberimg = memberIcon.getImage();
		Image bookimg = bookIcon.getImage();
		memberimg = memberimg.getScaledInstance(96, 96,  java.awt.Image.SCALE_SMOOTH);
		bookimg = bookimg.getScaledInstance(96, 96,  java.awt.Image.SCALE_SMOOTH);
		memberIcon = new ImageIcon(memberimg);
		bookIcon = new ImageIcon(bookimg);




		//회원, 도서테이 확인,홈이동 할 수 있는 버튼 생성
		JButton btn_member = new JButton("회원",memberIcon);
		btn_member.setBackground(new Color(255,153,051));
		JButton btn_book = new JButton("도서",bookIcon);
		btn_book.setBackground(new Color(255,153,102));

		//버튼 텍스트 아래로
		btn_member.setVerticalTextPosition(JButton.BOTTOM);
		btn_book.setVerticalTextPosition(JButton.BOTTOM);

		//버튼 텍스트 색 변경(기존 big_font 사용)
		btn_member.setFont(big_font);
		btn_book.setFont(big_font);
		btn_member.setForeground(new Color(255,255,255));
		btn_book.setForeground(new Color(255,255,255));


		//버튼 크기 설정
		btn_member.setSize(225, 225);
		btn_book.setSize(225, 225);
		//버튼 위치설정
		btn_member.setLocation(97, 336);
		btn_book.setLocation(378, 336);

		//버튼 테두리 삭제
		btn_member.setBorderPainted(false);
		btn_book.setBorderPainted(false);

		//(공통)홈버튼
		//코드순서 이미지 사이즈 변경/ 홈버튼 생성, 배경변경/ 글꼴 변경, 글꼴 색변경/버튼크기, 위치변경/
		ImageIcon homeIcon = new ImageIcon("images/home.png");
		Image homeimg = homeIcon.getImage();
		homeimg = homeimg.getScaledInstance(52, 52,  java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(homeimg);

		JButton btn_home = new JButton("Home",homeIcon);
		btn_home.setBackground(Color.gray);

		btn_home.setFont(big_font);
		btn_home.setForeground(new Color(255,255,255));

		btn_home.setSize(210, 100);
		btn_home.setLocation(240, 670);
		btn_home.setBorderPainted(false);

		//버튼,라벨 추가
		c.add(btn_member);
		c.add(btn_book);
		c.add(btn_home);
		c.add(label);
		c.add(sublabel);




		//**컨포넌트 다 컨테이너에 붙인뒤에**
		setSize(700, 800);
		setVisible(true);


		//버튼에 이벤트(새로운 창 보이게) 추가
		btn_member.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberPage();//회원테이블 있는 페이지 보이게
				dispose();

			}
		});

		btn_book.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookPage();//회원테이블 있는 페이지 보이게
				dispose();

			}
		});
		//홈이동 이벤트
		btn_home.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new library_main();
				dispose();


			}
		});
	}
}
