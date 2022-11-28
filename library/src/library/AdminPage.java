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
	    

		//이미지아이콘 -> 이미지로 변경 후 크기조정
		ImageIcon memberIcon = new ImageIcon("images/contact.png");
		ImageIcon bookIcon = new ImageIcon("images/book (1).png");
		Image memberimg = memberIcon.getImage();
		Image bookimg = bookIcon.getImage();
		memberimg = memberimg.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		bookimg = bookimg.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		memberIcon = new ImageIcon(memberimg);
		bookIcon = new ImageIcon(bookimg);
		
		

	    
	    //회원, 도서테이 확인,홈이동 할 수 있는 버튼 생성
	    JButton btn_member = new JButton("회원",memberIcon);
	    btn_member.setBackground(Color.orange);
	    JButton btn_book = new JButton("도서",bookIcon);
	    btn_book.setBackground(Color.orange);
	    JButton btn_home = new JButton("홈");
	    btn_home.setBackground(Color.gray);
	    //버튼 텍스트 아래로
	    btn_member.setVerticalTextPosition(JButton.BOTTOM);
	    btn_book.setVerticalTextPosition(JButton.BOTTOM);
	    
	   //버튼 크기 설정
	    btn_member.setSize(120, 150);
	    btn_book.setSize(120, 150);
	    btn_home.setSize(60, 60);
	    
	    
	    //버튼 위치설정
	    btn_member.setLocation(10, 200);
	    btn_book.setLocation(160, 200);
	    btn_home.setLocation(110, 400);
	    //버튼 추가
	    c.add(btn_member);
	    c.add(btn_book);
	    c.add(btn_home);
	    
	    
	
	    
	    //**컨포넌트 다 컨테이너에 붙인뒤에**
	    setSize(300, 600); 
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminPage();

	}
}
