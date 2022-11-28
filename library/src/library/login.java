package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends JFrame {
	private JLabel title = new JLabel("동국대학교 중앙도서관 키오스크");
	private JLabel StudentNumber = new JLabel("학번");
	private JTextField input_sn = new JTextField(15);
	private JLabel StudentPassword = new JLabel("비밀번호");
	private JTextField input_sp = new JTextField(15);
	private JButton btn_rogin = new JButton("로그인");
	private JButton btn_home = new JButton("홈");
	private JButton keyborad_sn = new JButton("키보드1");
	private JButton keyborad_sp = new JButton("키보드2");
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	
	public login() {
		
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane(); 
		
		GridLayout grid = new GridLayout(7,1);
		grid.setVgap(10);
		con.setLayout(grid);
		
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		
		title.setHorizontalAlignment(NORMAL);
		StudentNumber.setHorizontalAlignment(NORMAL);
		StudentPassword.setHorizontalAlignment(NORMAL);
		
		keyborad_sn.setSize(10,50);
		keyborad_sp.setSize(10,50);
		
		btn_rogin.addActionListener(new rogin_ActionListener());
		btn_home.addActionListener(new inActionListener());
		keyborad_sn.addActionListener(new keyboardActionListener());
		keyborad_sp.addActionListener(new keyboardActionListener());
		
		p1.add(input_sn);
		p1.add(keyborad_sn);
		
		p2.add(input_sp);
		p2.add(keyborad_sp);
		
		con.add(title);
		con.add(StudentNumber);
		con.add(p1);
		con.add(StudentPassword);
		con.add(p2);
		con.add(btn_rogin);
		con.add(btn_home);	

		setSize(300, 600);
		setVisible(true);
	}
	
	class rogin_ActionListener implements ActionListener {		
		Database db = new Database();
		public void actionPerformed(ActionEvent e) {
			
			String id = input_sn.getText().trim();
			String pw = input_sp.getText().trim();
			
			if(id.equals("") || pw.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				System.out.println("로그인 실패 > 로그인 정보 미입력");
			}
			
			else if(id != null && pw != null) {
				if(id.equals("test") && pw.equals("test1")) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
					new borrow();
					dispose();
					return;
					}
				else
					JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
				/*
				if(db.logincheck(id, pw)) {	//이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
					System.out.println("로그인 성공");
					JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
					// 대출, 예약, 연장 총 화면으로 넘어가기
					
				} else {
					System.out.println("로그인 실패 > 로그인 정보 불일치");
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
				}
				*/
			}
		}
	}
	
	class inActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();				
			if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}
		}
	}
	
	class keyboardActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("키보드1"))	{
				new keyBorad1();
				
			}
			else if (b.getText().equals("키보드2"))	{
				new keyBorad2();
			}
		}
	}
	
	class keyBorad1 extends JFrame {
	      private JPanel p1 = new JPanel();
	      public JTextArea input = new JTextArea();
	      private JButton[] btn_key = new JButton[38];
	      private String[] key = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u",
	            "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
	            "shift_A", "입력" };
	      private String[] shift_key = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", " Q", "W", "E", "R", "T", "Y",
	            "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
	            "shift_a", "입력" };

	      public keyBorad1() {
	         setTitle("키보드");
	         Container con = getContentPane();
	         con.setLayout(new FlowLayout());

	         GridLayout grid = new GridLayout(6, 7);
	         grid.setVgap(2);
	         p1.setLayout(grid);

	         for (int i = 0; i < key.length; ++i) {
	            btn_key[i] = new JButton(key[i]);
	            btn_key[i].addActionListener(new inputkey_ActionListener());
	            p1.add(btn_key[i]);
	         }

	         con.add(input);
	         con.add(p1);

	         setSize(500, 300);
	         setVisible(true);
	      }

	      class inputkey_ActionListener implements ActionListener {
	         public void actionPerformed(ActionEvent e) {
	            JButton b = (JButton) e.getSource();
	            if (b.getText().equals("1")) {
	                input.append("1");
	            } else if (b.getText().equals("2")) {
	               input.append("2");
	            } else if (b.getText().equals("3")) {
	               input.append("3");
	            } else if (b.getText().equals("4")) {
	               input.append("4");
	            } else if (b.getText().equals("5")) {
	               input.append("5");
	            } else if (b.getText().equals("6")) {
	               input.append("6");
	            } else if (b.getText().equals("7")) {
	               input.append("7");
	            } else if (b.getText().equals("8")) {
	               input.append("8");
	            } else if (b.getText().equals("9")) {
	               input.append("9");
	            } else if (b.getText().equals("0")) {
	               input.append("0");
	            } else if (b.getText().equals("q")) {
	               input.append("q");
	            } else if (b.getText().equals("w")) {
	               input.append("w");
	            } else if (b.getText().equals("e")) {
	               input.append("e");
	            } else if (b.getText().equals("r")) {
	               input.append("r");
	            } else if (b.getText().equals("t")) {
	               input.append("t");
	            } else if (b.getText().equals("y")) {
	               input.append("y");
	            } else if (b.getText().equals("u")) {
	               input.append("u");
	            } else if (b.getText().equals("i")) {
	               input.append("i");
	            } else if (b.getText().equals("o")) {
	               input.append("o");
	            } else if (b.getText().equals("p")) {
	               input.append("p");
	            } else if (b.getText().equals("a")) {
	               input.append("a");
	            } else if (b.getText().equals("s")) {
	               input.append("s");
	            } else if (b.getText().equals("d")) {
	               input.append("d");
	            } else if (b.getText().equals("f")) {
	               input.append("f");
	            } else if (b.getText().equals("g")) {
	               input.append("g");
	            } else if (b.getText().equals("h")) {
	               input.append("h");
	            } else if (b.getText().equals("j")) {
	               input.append("j");
	            } else if (b.getText().equals("k")) {
	               input.append("k");
	            } else if (b.getText().equals("l")) {
	               input.append("l");
	            } else if (b.getText().equals("z")) {
	               input.append("z");
	            } else if (b.getText().equals("x")) {
	               input.append("x");
	            } else if (b.getText().equals("c")) {
	               input.append("c");
	            } else if (b.getText().equals("v")) {
	               input.append("v");
	            } else if (b.getText().equals("b")) {
	               input.append("b");
	            } else if (b.getText().equals("n")) {
	               input.append("n");
	            } else if (b.getText().equals("m")) {
	               input.append("m");
	            }

	            // shift_A
	            else if (b.getText().equals("!")) {
	               input.append("!");
	            } else if (b.getText().equals("@")) {
	               input.append("@");
	            } else if (b.getText().equals("#")) {
	               input.append("#");
	            } else if (b.getText().equals("$")) {
	               input.append("$");
	            } else if (b.getText().equals("%")) {
	               input.append("%");
	            } else if (b.getText().equals("^")) {
	               input.append("^");
	            } else if (b.getText().equals("&")) {
	               input.append("&");
	            } else if (b.getText().equals("*")) {
	               input.append("*");
	            } else if (b.getText().equals("(")) {
	               input.append("(");
	            } else if (b.getText().equals(")")) {
	               input.append(")");
	            } else if (b.getText().equals("Q")) {
	               input.append("Q");
	            } else if (b.getText().equals("W")) {
	               input.append("W");
	            } else if (b.getText().equals("E")) {
	               input.append("E");
	            } else if (b.getText().equals("R")) {
	               input.append("R");
	            } else if (b.getText().equals("T")) {
	               input.append("T");
	            } else if (b.getText().equals("Y")) {
	               input.append("Y");
	            } else if (b.getText().equals("U")) {
	               input.append("U");
	            } else if (b.getText().equals("I")) {
	               input.append("I");
	            } else if (b.getText().equals("O")) {
	               input.append("O");
	            } else if (b.getText().equals("P")) {
	               input.append("P");
	            } else if (b.getText().equals("A")) {
	               input.append("A");
	            } else if (b.getText().equals("S")) {
	               input.append("S");
	            } else if (b.getText().equals("D")) {
	               input.append("D");
	            } else if (b.getText().equals("F")) {
	               input.append("F");
	            } else if (b.getText().equals("G")) {
	               input.append("G");
	            } else if (b.getText().equals("H")) {
	               input.append("H");
	            } else if (b.getText().equals("J")) {
	               input.append("J");
	            } else if (b.getText().equals("K")) {
	               input.append("K");
	            } else if (b.getText().equals("L")) {
	               input.append("L");
	            } else if (b.getText().equals("Z")) {
	               input.append("Z");
	            } else if (b.getText().equals("X")) {
	               input.append("X");
	            } else if (b.getText().equals("C")) {
	               input.append("C");
	            } else if (b.getText().equals("V")) {
	               input.append("V");
	            } else if (b.getText().equals("B")) {
	               input.append("B");
	            } else if (b.getText().equals("N")) {
	               input.append("N");
	            } else if (b.getText().equals("M")) {
	               input.append("M");
	            } else if (b.getText().equals("shift_A")) {
	               for (int i = 0; i < btn_key.length; ++i) {
	                  btn_key[i].setText(shift_key[i]);
	               }
	            } else if (b.getText().equals("shift_a")) {
	               for (int i = 0; i < btn_key.length; ++i) {
	                  btn_key[i].setText(key[i]);
	               }
	            } else if (b.getText().equals("입력")) {
	               input_sn.setText(input.getText());
	               dispose();
	               }
	         }
	      }
	   }

	   class keyBorad2 extends JFrame {
	      private JPanel p1 = new JPanel();
	      public JTextArea input = new JTextArea();
	      private JButton[] btn_key = new JButton[38];
	      private String[] key = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u",
	            "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
	            "shift_A", "입력" };
	      private String[] shift_key = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", " Q", "W", "E", "R", "T", "Y",
	            "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
	            "shift_a", "입력" };

	      public keyBorad2() {
	         setTitle("키보드");
	         Container con = getContentPane();
	         con.setLayout(new FlowLayout());

	         GridLayout grid = new GridLayout(6, 7);
	         grid.setVgap(2);
	         p1.setLayout(grid);

	         for (int i = 0; i < key.length; ++i) {
	            btn_key[i] = new JButton(key[i]);
	            btn_key[i].addActionListener(new inputkey_ActionListener());
	            p1.add(btn_key[i]);
	         }

	         con.add(input);
	         con.add(p1);

	         setSize(500, 300);
	         setVisible(true);
	      }

	      class inputkey_ActionListener implements ActionListener {
	         public void actionPerformed(ActionEvent e) {
	            JButton b = (JButton) e.getSource();
	            if (b.getText().equals("1")) {
	                input.append("1");
	            } else if (b.getText().equals("2")) {
	               input.append("2");
	            } else if (b.getText().equals("3")) {
	               input.append("3");
	            } else if (b.getText().equals("4")) {
	               input.append("4");
	            } else if (b.getText().equals("5")) {
	               input.append("5");
	            } else if (b.getText().equals("6")) {
	               input.append("6");
	            } else if (b.getText().equals("7")) {
	               input.append("7");
	            } else if (b.getText().equals("8")) {
	               input.append("8");
	            } else if (b.getText().equals("9")) {
	               input.append("9");
	            } else if (b.getText().equals("0")) {
	               input.append("0");
	            } else if (b.getText().equals("q")) {
	               input.append("q");
	            } else if (b.getText().equals("w")) {
	               input.append("w");
	            } else if (b.getText().equals("e")) {
	               input.append("e");
	            } else if (b.getText().equals("r")) {
	               input.append("r");
	            } else if (b.getText().equals("t")) {
	               input.append("t");
	            } else if (b.getText().equals("y")) {
	               input.append("y");
	            } else if (b.getText().equals("u")) {
	               input.append("u");
	            } else if (b.getText().equals("i")) {
	               input.append("i");
	            } else if (b.getText().equals("o")) {
	               input.append("o");
	            } else if (b.getText().equals("p")) {
	               input.append("p");
	            } else if (b.getText().equals("a")) {
	               input.append("a");
	            } else if (b.getText().equals("s")) {
	               input.append("s");
	            } else if (b.getText().equals("d")) {
	               input.append("d");
	            } else if (b.getText().equals("f")) {
	               input.append("f");
	            } else if (b.getText().equals("g")) {
	               input.append("g");
	            } else if (b.getText().equals("h")) {
	               input.append("h");
	            } else if (b.getText().equals("j")) {
	               input.append("j");
	            } else if (b.getText().equals("k")) {
	               input.append("k");
	            } else if (b.getText().equals("l")) {
	               input.append("l");
	            } else if (b.getText().equals("z")) {
	               input.append("z");
	            } else if (b.getText().equals("x")) {
	               input.append("x");
	            } else if (b.getText().equals("c")) {
	               input.append("c");
	            } else if (b.getText().equals("v")) {
	               input.append("v");
	            } else if (b.getText().equals("b")) {
	               input.append("b");
	            } else if (b.getText().equals("n")) {
	               input.append("n");
	            } else if (b.getText().equals("m")) {
	               input.append("m");
	            }

	            // shift_A
	            else if (b.getText().equals("!")) {
	               input.append("!");
	            } else if (b.getText().equals("@")) {
	               input.append("@");
	            } else if (b.getText().equals("#")) {
	               input.append("#");
	            } else if (b.getText().equals("$")) {
	               input.append("$");
	            } else if (b.getText().equals("%")) {
	               input.append("%");
	            } else if (b.getText().equals("^")) {
	               input.append("^");
	            } else if (b.getText().equals("&")) {
	               input.append("&");
	            } else if (b.getText().equals("*")) {
	               input.append("*");
	            } else if (b.getText().equals("(")) {
	               input.append("(");
	            } else if (b.getText().equals(")")) {
	               input.append(")");
	            } else if (b.getText().equals("Q")) {
	               input.append("Q");
	            } else if (b.getText().equals("W")) {
	               input.append("W");
	            } else if (b.getText().equals("E")) {
	               input.append("E");
	            } else if (b.getText().equals("R")) {
	               input.append("R");
	            } else if (b.getText().equals("T")) {
	               input.append("T");
	            } else if (b.getText().equals("Y")) {
	               input.append("Y");
	            } else if (b.getText().equals("U")) {
	               input.append("U");
	            } else if (b.getText().equals("I")) {
	               input.append("I");
	            } else if (b.getText().equals("O")) {
	               input.append("O");
	            } else if (b.getText().equals("P")) {
	               input.append("P");
	            } else if (b.getText().equals("A")) {
	               input.append("A");
	            } else if (b.getText().equals("S")) {
	               input.append("S");
	            } else if (b.getText().equals("D")) {
	               input.append("D");
	            } else if (b.getText().equals("F")) {
	               input.append("F");
	            } else if (b.getText().equals("G")) {
	               input.append("G");
	            } else if (b.getText().equals("H")) {
	               input.append("H");
	            } else if (b.getText().equals("J")) {
	               input.append("J");
	            } else if (b.getText().equals("K")) {
	               input.append("K");
	            } else if (b.getText().equals("L")) {
	               input.append("L");
	            } else if (b.getText().equals("Z")) {
	               input.append("Z");
	            } else if (b.getText().equals("X")) {
	               input.append("X");
	            } else if (b.getText().equals("C")) {
	               input.append("C");
	            } else if (b.getText().equals("V")) {
	               input.append("V");
	            } else if (b.getText().equals("B")) {
	               input.append("B");
	            } else if (b.getText().equals("N")) {
	               input.append("N");
	            } else if (b.getText().equals("M")) {
	               input.append("M");
	            } else if (b.getText().equals("shift_A")) {
	               for (int i = 0; i < btn_key.length; ++i) {
	                  btn_key[i].setText(shift_key[i]);
	               }
	            } else if (b.getText().equals("shift_a")) {
	               for (int i = 0; i < btn_key.length; ++i) {
	                  btn_key[i].setText(key[i]);
	               }
	            } else if (b.getText().equals("입력")) {
	               input_sp.setText(input.getText());
	               dispose();
	               }
	         }
	      }
	   }
	}