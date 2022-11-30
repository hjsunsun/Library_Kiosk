package library;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.*;

public class member_add extends JFrame {
	public member_add(){
		setTitle("도서정보"); //타이틀
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(245, 245, 245));

		//상단라벨 설정,크기지정,위치지정,라벨 가운데,라벨배경지정
		JLabel label = new JLabel("회원정보 추가");
		label.setSize(700, 100);
		label.setLocation(0,0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255,204,153));

		//상단 폰트 생성, 폰트 라벨에 적용,
		Font big_font =new Font("NanumSquare", Font.BOLD, 30);
		label.setFont(big_font);

		//패널 만들고 사이즈와 위치선정
		JPanel jPanel_member_add = new JPanel();
		jPanel_member_add.setLayout(new GridLayout(7,2,5,20));
		jPanel_member_add.setSize(600, 400);
		jPanel_member_add.setLocation(50,150);
		jPanel_member_add.setBackground(new Color(245, 245, 245));



		//패널 안에 컨포넌트 넣기
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
		/*
		jPanel_member_add.add(new JLabel(" 대출가능권수"));
		JTextField borrow=new JTextField("");
		jPanel_member_add.add(borrow);
		*/


		//확인버튼 생성
		JButton btn_plus = new JButton("확인");
		btn_plus.setSize(110, 62);
		btn_plus.setLocation(204, 600);
		btn_plus.setFont(big_font);
		btn_plus.setForeground(new Color(255,255,255));
		btn_plus.setBackground(new Color(255,204,153));
		btn_plus.setBorderPainted(false);

		//뒤로가기버튼 생성
		JButton btn_back = new JButton("뒤로가기");
		btn_back.setSize(157, 62);
		btn_back.setLocation(339, 670);
		btn_back.setFont(big_font);
		btn_back.setForeground(new Color(255,255,255));
		btn_back.setBackground(new Color(255,153,051));
		btn_back.setBorderPainted(false);

		//프레임에 추가
		c.add(jPanel_member_add);
		c.add(label);
		c.add(btn_back);
		c.add(btn_plus);

		setVisible(true);
		setSize(700, 800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//저장버튼 누르면 db저장, 창 내려감.
		btn_plus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//하나라도 입력값없으면 저장 못하게
				if(((Textnum.getText()).equals(""))||((Textpassword.getText()).equals(""))||((name.getText()).equals(""))||
						((major.getText()).equals(""))||((phone.getText()).equals(""))||((email.getText()).equals("")))
				{JOptionPane.showMessageDialog(null, "모든항목을 입력해주세요", "오류 메세지",
						JOptionPane.ERROR_MESSAGE);}
				//else {//모든 항목이 입력된 경우
            		/*for(DB에 저장된 행 돌리기 EX)(int i=0;i<model.getRowCount();i++){
            		 * ??select * from 학생??
            		 *
            		 * 		//DB에 저장된 학번과 비밀번호 비교
            		 * 		if((Textnum.getText()).equals(DB학번) &&(Textpassword.getText()).equals(DB비밀번호)){
            		 * 			JOptionPane.showMessageDialog(null, "이미 존재하는 학번과 비밀번호입니다.", "오류 메세지",
            					JOptionPane.ERROR_MESSAGE);}

            				else if((Textnum.getText()).equals(DB학번)){
            					JOptionPane.showMessageDialog(null, "이미 존재하는 학번입니다.", "오류 메세지",
            					JOptionPane.ERROR_MESSAGE);}

            				else if((Textpassword.getText()).equals(DB비밀번호)){
            					JOptionPane.showMessageDialog(null, "이미 존재하는 비밀번호입니다.", "오류 메세지",
            					JOptionPane.ERROR_MESSAGE);}

            				// 학번, 비밀번호 비교했을 때 다르면 이제 DB에 저장
            			 	else{
            			 	??INSERT INTO 학생 (학번, 비밀번호, 이름, 전공, 전화번호, 이메일, 대출가능권수)
            			 	VALUES ( Textnum.getText(),Textpassword.getText(),name.getText(),major.getText(),phone.getText(),email.getText(), 10 );
            			 	??
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
