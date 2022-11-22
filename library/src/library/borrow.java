package library;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import library.reservation.inActionListener;

public class borrow extends JFrame{
	private JPanel p1 = new JPanel();
	
	private JButton btn1 = new JButton("대출"); 	
	private JButton btn2 = new JButton("예약");
	private JButton btn3 = new JButton("연장");
	private JButton btn_home = new JButton("홈");
	
	public borrow() {		
		setTitle("대출");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane(); 
		
		GridLayout grid = new GridLayout(5,1);
		grid.setVgap(10);
		c.setLayout(grid);
		
		GridLayout grid1 = new GridLayout(1,3);
		p1.setLayout(grid1);
		
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn3.addActionListener(new inActionListener());
		btn_home.addActionListener(new inActionListener());
		
		p1.add(btn1);
		p1.add(btn2);
		p1.add(btn3);
		
		c.add(p1);
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(btn_home);
		
		setSize(300, 600); 
		setVisible(true); 
	}
	
	class inActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();				
			if (b.getText().equals("대출"))	{
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
			else if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}
		}
	}
}
