package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class book_add extends JFrame{
	public book_add() {
		setTitle("도서정보"); //타이틀
		setSize(700, 800); //창 크기 설정

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245, 245, 245));


		JLabel label = new JLabel("도서정보 추가");
		label.setSize(700, 100);
		label.setLocation(0,0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255,204,153));

		//상단 폰트 생성, 폰트 라벨에 적용,
		Font big_font =new Font("NanumSquare", Font.BOLD, 30);
		label.setFont(big_font);



		//패널 만들고 사이즈와 위치선정
		JPanel jPanel_book_add = new JPanel();
		add(jPanel_book_add);
		jPanel_book_add.setLayout(new GridLayout(8, 2,5,20));
		jPanel_book_add.setPreferredSize(new Dimension(300,500));
		jPanel_book_add.setSize(600, 500);
		jPanel_book_add.setLocation(50,150);
		jPanel_book_add.setBackground(new Color(245, 245, 245));

		//패널 안에 컨포넌트 넣기
		jPanel_book_add.add(new JLabel("도서id"));
		JTextField BookID=new JTextField("");
		jPanel_book_add.add(BookID);
		jPanel_book_add.add(new JLabel(" 청구기호"));
		JTextField Booknum=new JTextField("");
		jPanel_book_add.add(Booknum);
		jPanel_book_add.add(new JLabel(" 도서명"));
		JTextField Bookname=new JTextField("");
		jPanel_book_add.add(Bookname);
		jPanel_book_add.add(new JLabel(" 저자"));
		JTextField author=new JTextField("");
		jPanel_book_add.add(author);
		jPanel_book_add.add(new JLabel(" 출판사"));
		JTextField BookPubl=new JTextField("");
		jPanel_book_add.add(BookPubl);
		jPanel_book_add.add(new JLabel("출판연도"));
		JTextField BookYear=new JTextField("");
		jPanel_book_add.add(BookYear);
		jPanel_book_add.add(new JLabel("구분"));
		JTextField Booksort=new JTextField("");
		jPanel_book_add.add(Booksort);
		jPanel_book_add.add(new JLabel(" 언어"));
		JTextField Booklan=new JTextField("");
		jPanel_book_add.add(Booklan);
		/*
		jPanel_book_add.add(new JLabel(" 대출가능여부"));
		JTextField Bookborr=new JTextField("");
		jPanel_book_add.add(Bookborr);
		jPanel_book_add.add(new JLabel(" 예약가능여부"));
		JTextField Bookreser=new JTextField("");
		jPanel_book_add.add(Bookreser);
		*/


		//확인버튼 생성
		JButton btn_plus = new JButton("확인");
		btn_plus.setSize(110, 62);
		btn_plus.setLocation(204, 670);
		btn_plus.setFont(big_font);
		btn_plus.setForeground(new Color(255,255,255));
		btn_plus.setBackground(new Color(255,153,102));
		btn_plus.setBorderPainted(false);

		//뒤로가기버튼 생성
		JButton btn_back = new JButton("뒤로가기");
		btn_back.setSize(157, 62);
		btn_back.setLocation(339, 670);
		btn_back.setFont(big_font);
		btn_back.setForeground(new Color(255,255,255));
		btn_back.setBackground(new Color(255,153,102));
		btn_back.setBorderPainted(false);

		//프레임에 추가
		c.setBackground(new Color(245, 245, 245));
		c.add(jPanel_book_add);
		c.add(label);
		c.add(btn_back);
		c.add(btn_plus);

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		//저장버튼 누르면 db저장, 창 내려감.
		btn_plus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((Booknum.getText()).equals(""))||((Bookname.getText()).equals(""))||
						((author.getText()).equals(""))||((BookPubl.getText()).equals(""))||((BookYear.getText()).equals(""))
						||(( Booksort.getText()).equals(""))||((Booklan.getText()).equals("")))
				{JOptionPane.showMessageDialog(null, "모든항목을 입력해주세요", "오류 메세지",
						JOptionPane.ERROR_MESSAGE);}
				//else {//모든 항목이 입력된 경우
		            		/*for(DB에 저장된 행 돌리기 EX)(int i=0;i<model.getRowCount();i++){
		            		 * 		???select * from 도서???
		            		 * 		//DB에 저장된 청구기호 비교
		            				if((Textnum.getText()).equals(DB청구기호)){
		            					JOptionPane.showMessageDialog(null, "이미 존재하는 청구기호입니다.", "오류 메세지",
		            					JOptionPane.ERROR_MESSAGE);}

		            				// 청구기호 비교했을 때 다르면 이제 DB에 저장
		            			 	else{
		            			 	??INSERT INTO 도서 (도서ID,청구기호,도서명,저자(역자),출판사,출판연도,구분, 언어)
		            			 	VALUES ( 도서ID, Booknum.getText(), Bookname.getText(),author.getText(),BookPubl.getText(),BookYear.getText(),Booksort.getText(),Booklan.getText() );??

		            			 		new MemberPage();
		            					dispose();
		            			 	}


		            					}
		            				}
		            		 */



			}
		});
		//뒤로가기 버튼 이벤트처리
		btn_back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookPage();
				dispose();
			}
		});


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new book_add();

	}

}
