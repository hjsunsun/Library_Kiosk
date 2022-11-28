package library;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//패널사이즈 조정...

public class MemberPage extends JFrame{
	 MemberPage() {
		 super("회원정보"); //타이틀
		 //??????????????
		 setSize(300, 600); //창 크기 설정
	     JPanel jPanel_Member = new JPanel();
	     JPanel jPanel_MemberBTN = new JPanel();
	
	
	   
	     
	     //테이블
	     //1. 테이블 정보정의
	     String header[]= {"학번","비밀번호","이름","전공","전화번호","이메일","대출가능권수"};
	     String contents[][]= {
	    		 {"20221113","1234","홍길동","융합소프트웨어학과","010-1234-1234","2권"},
	    		 {"2015111133","1235","김영희","융합소프트웨어학과","010-4567-4567","1권"}		 
	     };
	     //2. jtable 클래스의 객체 생성
	     DefaultTableModel Member_model = new DefaultTableModel(contents, header);
	     JTable Member_table = new JTable(Member_model);
	     JScrollPane Member_scrollPane =new JScrollPane(Member_table);
	     //3.
	     jPanel_Member.add(Member_scrollPane);
	     add(jPanel_Member,BorderLayout.CENTER);
	     
	     //추가버튼 생성
	     JButton btn_add = new JButton("추가");
	     jPanel_MemberBTN.add(btn_add);
	
	     //삭제버튼 생성
	     JButton btn_delete = new JButton("삭제");
	     jPanel_MemberBTN.add(btn_delete);
	     
	   //뒤로가기버튼 생성
	     JButton btn_back = new JButton("뒤로가기");
	     jPanel_MemberBTN.add(btn_back);
	     add(jPanel_MemberBTN,BorderLayout.SOUTH );
	     
	    
	      //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      setVisible(true);
	       //추가버튼 누르면 추가하는 페이지로 이동  
	      btn_add.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new  member_add();
	            	 dispose();
	                
	            }
	        });
	      // 어떤 테이블을 선택하고 삭제를 누르면 삭제됨.
	      btn_delete.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(Member_table.getSelectedRow() == -1) {
						return;
					}
					else {
						Member_model.removeRow(Member_table.getSelectedRow());
						//실제 배열에 삭제하기
					}
	                
	               
	            }
	        });
	      
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
		new MemberPage();

	}
}
	

