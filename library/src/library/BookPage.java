package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookPage extends JFrame{
	public BookPage() {
		super("도서정보"); //타이틀
		 //??????????????
		 setSize(300, 600); //창 크기 설정
	     JPanel jPanel_Book = new JPanel();
	     JPanel jPanel_BookBTN = new JPanel();
	
	     
	   //테이블
	     //1. 테이블 정보정의
	     String Book_header[]= {"도서id","청구기호","도서명","저자","출판사","출판연도","구분","언어","대출가능여부","예약가능여부"};
	     String Book_contents[][]= {
	    		 {"202201","ㄱ3463-43","어린왕자","앙투안 드 생텍쥐페리","동서문화사","2022","문학","한국어","Y","N"},
	    		 {"202202","ㄱ3463-43","빨간머리앤","루시 모드 몽고메리","동서문화사","2022","문학","한국어","Y","N"}		 	 
	     };
	     //2. jtable 클래스의 객체 생성 
	     DefaultTableModel Book_model = new DefaultTableModel(Book_contents, Book_header);
	     JTable Book_table = new JTable(Book_model);
	     JScrollPane Book_scrollPane =new JScrollPane(Book_table);
	     
	     //3.
	     jPanel_Book.add(Book_scrollPane);
	     add(jPanel_Book,BorderLayout.CENTER);
	     
	   //추가버튼 생성
	     JButton btn_add = new JButton("추가");
	     jPanel_BookBTN.add(btn_add);
	
	     //삭제버튼 생성
	     JButton btn_delete = new JButton("삭제");
	     jPanel_BookBTN.add(btn_delete);
	     
	   //뒤로가기버튼 생성
	     JButton btn_back = new JButton("뒤로가기");
	     jPanel_BookBTN.add(btn_back);
	     add(jPanel_BookBTN,BorderLayout.SOUTH );
	      
	      
	      //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      setVisible(true);
	      
    //추가버튼 누르면 추가하는 페이지로 이동  
   btn_add.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
         	new  book_add();
         	 dispose();
             
         }
     });
   // 어떤 테이블을 선택하고 삭제를 누르면 삭제됨.
   btn_delete.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
        	 if(Book_table.getSelectedRow() == -1) {
					return;
				}
				else {
					Book_model.removeRow(Book_table.getSelectedRow());
					//실제 배열에 삭제하기
				}
         }
     });
   
   //뒤로가기 버튼 이벤트 생성
   btn_back.addActionListener(new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e) {
      	new AdminPage();
      	dispose();
				}
   });  
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BookPage();

	}
	}


