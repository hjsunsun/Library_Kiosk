package library;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import library.extension.inActionListener;

public class returnBook extends JFrame  {
   JLabel labelTitle = new JLabel("반납");
   JLabel labelId = new JLabel("도서 ID");
   JTextField bookID = new JTextField();
   
   JButton enterBtn = new JButton("반납");
   JButton btn_home = new JButton("홈");
   
   public returnBook(){
      setTitle("동국대학교 중앙도서관 키오스크 반납");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      
      GridLayout grid = new GridLayout(5,1);
      grid.setVgap(10);
      c.setLayout(grid);
      enterBtn.addActionListener(new inActionListener()); 
      enterBtn.setPreferredSize(new Dimension(30, 30));
      btn_home.addActionListener(new inActionListener());
      
      c.add(labelTitle);
      c.add(labelId);
      c.add(bookID);
      c.add(enterBtn);
      c.add(btn_home);
       

      labelTitle.setHorizontalAlignment(JLabel.CENTER);
      labelId.setHorizontalAlignment(JLabel.CENTER);
      
      setSize(300,600);
      setVisible(true);
      
   }
   
   class inActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();   
         if (b.getText().equals("반납"))   {
            int result = JOptionPane.showConfirmDialog(null,"["+ bookID.getText()+"]\n반납하시겠습니까?" , "도서 반납", JOptionPane.YES_NO_OPTION );
            if(result == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.", "반납", JOptionPane.DEFAULT_OPTION);
               
               //반납 시 필요한 쿼리 작성
               //1. 도서테이블의 해당 도서 대출가능여부 Y로 변경 (대여테이블에서 땡겨오기)
               //2. 회원테이블의 대출가능권수 +1 (대여테이블에서 땡겨오기)
               //3. 대여테이블의 해당 대여 행 삭제? or 대출 종료 여부 추가 
               
            }
            else if(result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
               //No 혹은 창 종료 시 실행될 부분
            }
         }
         else if(b.getText().equals("")) {
            //메인화면 이동
         }
         else if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}
         
      }   
   }
}
