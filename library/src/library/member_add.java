package library;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
//????클래스 멤버페이지에 있는 모델에 추가가 안된다....???////
public class member_add extends JFrame {
	public member_add(){
		super("회원추가");
		setSize(300, 600);
		
		
		JPanel jPanel_member_add = new JPanel();
		add(jPanel_member_add);
		jPanel_member_add.setLayout(new GridLayout(7, 2,5,5));
		jPanel_member_add.setPreferredSize(new Dimension(300,400));
		

		jPanel_member_add.add(new JLabel(" 학번"));
		JTextField Textnum=new JTextField("");
		jPanel_member_add.add(Textnum);
		jPanel_member_add.add(new JLabel(" 비밀번호"));
		JTextField Textpassword=new JTextField("");
		jPanel_member_add.add(Textpassword);
		jPanel_member_add.add(new JLabel(" 이름"));
		JTextField name=new JTextField("");
		jPanel_member_add.add(name);
		jPanel_member_add.add(new JLabel(" 전공"));
		JTextField major=new JTextField("");
		jPanel_member_add.add(major);
		jPanel_member_add.add(new JLabel(" 전화번호"));
		JTextField phone=new JTextField("");
		jPanel_member_add.add(phone);
		jPanel_member_add.add(new JLabel("이메일"));
		JTextField email=new JTextField("");
		jPanel_member_add.add(email);
		jPanel_member_add.add(new JLabel(" 대출가능권수"));
		JTextField borrow=new JTextField("");
		jPanel_member_add.add(borrow);
		
		
		//저장버튼-> 누르면 테이블에 입력값 저장
		JPanel jPanel_member_puls = new JPanel();
		JButton btn_plus = new JButton("저장");
		jPanel_member_puls.add(btn_plus);
		
		//뒤로가기버튼
		JButton btn_back = new JButton("뒤로가기");
		jPanel_member_puls.add(btn_back);
		
		add(jPanel_member_puls,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		//저장버튼 누르면 창 내려감.
		btn_plus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	

            	String inputmember[] = new String[6];
            	inputmember[0]=Textnum.getText();
            	inputmember[1]=Textpassword.getText();
            	inputmember[2]=name.getText();
            	inputmember[3]=major.getText();
            	inputmember[4]=phone.getText();
            	inputmember[5]=email.getText();
            	inputmember[6]=borrow.getText();
				
            	//Member_model.addRow(inputmember);
				
				//nameField.setText("");
				//sbj1.setText("");
				//sbj2.setText("");
				//sbj3.setText("");
            	new MemberPage();
            	dispose(); 
                
            }
        });
		
		//뒤로가기 버튼 이벤트처리
		 btn_back.addActionListener(new ActionListener(){
	          @Override
	          public void actionPerformed(ActionEvent e) {
	         	new  MemberPage();
	         	dispose();
	   				}
	      }); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new  member_add();

	}

}
