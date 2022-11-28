package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class book_add extends JFrame{
	public book_add() {
		super("도서추가");
		setSize(300, 600);
		
		JPanel jPanel_book_add = new JPanel();
		add(jPanel_book_add);
		jPanel_book_add.setLayout(new GridLayout(10, 2,5,5));
		jPanel_book_add.setPreferredSize(new Dimension(300,500));

		
		jPanel_book_add.add(new JLabel("도서id"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 청구기호"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 도서명"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 저자"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 출판사"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel("출판연도"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel("구분"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 언어"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 대출가능여부"));
		jPanel_book_add.add(new JTextField(""));
		jPanel_book_add.add(new JLabel(" 예약가능여부"));
		jPanel_book_add.add(new JTextField(""));
		
		//저장버튼-> 누르면 테이블에 입력값 저장
				JPanel jPanel_book_puls = new JPanel();
				
				JButton btn_plus = new JButton("저장");
				jPanel_book_puls.add(btn_plus);
				
				//뒤로가기버튼
				JButton btn_back = new JButton("뒤로가기");
				jPanel_book_puls.add(btn_back);
				
				add(jPanel_book_puls,BorderLayout.SOUTH);
				
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setVisible(true);
				
				btn_plus.addActionListener(new ActionListener(){
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	new BookPage();
		                dispose(); // 창 안보이게 하기 
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
