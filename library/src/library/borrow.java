package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class borrow extends JFrame{
	   private JPanel p1 = new JPanel();
	   private JPanel p2 = new JPanel();
	   
	   private JButton btn1 = new JButton("대출");    
	   private JButton btn2 = new JButton("예약");
	   private JButton btn3 = new JButton("연장");
	   private JButton btn_home = new JButton("홈");
	   private JLabel bookId = new JLabel("도서 ID 검색");
	   private JTextField input_bookId = new JTextField("");
	   private JButton btn_search = new JButton("대출");
	   
	   public borrow() {      
	      setTitle("대출");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Container c = getContentPane(); 
	      
	      GridLayout grid = new GridLayout(5,1);
	      grid.setVgap(10);
	      c.setLayout(grid);
	      
	      GridLayout grid1 = new GridLayout(1,3);
	      p1.setLayout(grid1);
	      
	      GridLayout grid2 = new GridLayout(2, 1);
	      p2.setLayout(grid2);
	      
	      btn1.addActionListener(new inActionListener());
	      btn2.addActionListener(new inActionListener());
	      btn3.addActionListener(new inActionListener());
	      btn_home.addActionListener(new inActionListener());
	      
	      p1.add(btn1);
	      p1.add(btn2);
	      p1.add(btn3);
	      
	      p2.add(input_bookId);
	      p2.add(btn_search);
	      
	      btn_search.addActionListener(new search_ActionListener());
	      c.add(p1);
	      c.add(bookId);
	      c.add(p2);
	      c.add(new JLabel());
	      c.add(btn_home);
	      
	      
	      setSize(300, 600); 
	      setVisible(true); 
	   }
	   
	   class inActionListener implements ActionListener {      
	      public void actionPerformed(ActionEvent e) {
	         JButton b = (JButton) e.getSource();            
	         if (b.getText().equals("대출"))   {
	            new borrow();
	            dispose();
	         }
	         else if (b.getText().equals("예약")){
	            new reservation();
	            setVisible(false);
	         }
	         else if (b.getText().equals("연장")){
	            new extension();
	            setVisible(false);
	         }
	         else if (b.getText().equals("홈"))   {
	            new library_main();
	            dispose();
	         }
	      }
	   }
	   class search_ActionListener implements ActionListener {
	      public void actionPerformed(ActionEvent e) {
	         String bookId = input_bookId.getText().trim();
	         if(bookId.equals("")){
	            JOptionPane.showMessageDialog(null, "도서 ID를 입력해주세요.", "도서 대출 실패", JOptionPane.ERROR_MESSAGE);
	         }
	         else if(bookId != null) {
	            int num = JOptionPane.showConfirmDialog(null, "대출하시겠습니까?", "대출", JOptionPane.YES_NO_OPTION);
	            if(num == JOptionPane.CLOSED_OPTION) {
	               new borrow();
	               dispose();
	               return;
	            }
	            else if(num == JOptionPane.YES_OPTION) {
	               JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.", "대출 성공", JOptionPane.INFORMATION_MESSAGE);
	            }
	         }
	      }
	   }
	}
