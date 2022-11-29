package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import library.borrow.inActionListener;
import library.login.keyBorad1;
import library.login.keyBorad2;
import library.login.keyBorad1.inputkey_ActionListener;

public class reservation extends JFrame {
	Database db = new Database();
	private JPanel p1 = new JPanel();

	private JButton btn1 = new JButton("대출");
	private JButton btn2 = new JButton("예약");
	private JButton btn3 = new JButton("연장");
	private JButton btn_home = new JButton("홈");

	private JPanel MPanel = new JPanel();
	private JLabel all = new JLabel("전체 검색");
	private JTextField tfall = new JTextField(15);
	private JButton sBtn = new JButton("검색");
	private JButton keyborad_sn = new JButton("키보드");

	private JTextArea db_connect = new JTextArea("db연동");

	public reservation() {
		setTitle("예약");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();

		GridLayout grid = new GridLayout(5, 1);
		grid.setVgap(10);
		c.setLayout(grid);

		GridLayout grid1 = new GridLayout(1, 3);
		p1.setLayout(grid1);
		MPanel.setLayout(new FlowLayout());

		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn3.addActionListener(new inActionListener());
		keyborad_sn.addActionListener(new keyboardActionListener());
		btn_home.addActionListener(new inActionListener());

		p1.add(btn1);
		p1.add(btn2);
		p1.add(btn3);
		
		all.setHorizontalAlignment(NORMAL);

		MPanel.add(tfall);
		MPanel.add(keyborad_sn);
		MPanel.add(sBtn);
		MPanel.setSize(300, 300);

		c.add(p1);
		c.add(all);
		c.add(MPanel);
		c.add(db_connect);
		c.add(btn_home);

		setSize(300, 600);
		setVisible(true);
	}

	class inActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("대출")) {
				new borrow();
				dispose();
			} else if (b.getText().equals("예약")) {
				new reservation();
				setVisible(false);
			} else if (b.getText().equals("연장")) {
				new extension();
				setVisible(false);
			} else if (b.getText().equals("홈")) {
				new library_main();
				db.logout();
				dispose();
			}
		}
	}

	class sActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String all = tfall.getText().trim();

			if (all.equals("-")) { // 검색한 도서 db에 없는 경우(if문 조건 수정 필요)
				JOptionPane.showMessageDialog(null, "해당 도서가 없습니다.", "도서 검색", JOptionPane.ERROR_MESSAGE);
			} else {
				// DB 불러와서 해당 도서 검색 결과 띄우기
//            (DB)내용.addActionListener(new rActionListener());
			}
		}
	}

	class keyboardActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("키보드1"))	{
				new keyBorad();
				
			}
		}
	}
	
	class keyBorad extends JFrame {
		private JPanel p1 = new JPanel();
		public JTextArea input = new JTextArea();
		private JButton[] btn_key = new JButton[38];
		private String[] key = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u",
				"i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
				"shift_A", "입력" };
		private String[] shift_key = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", " Q", "W", "E", "R", "T", "Y",
				"U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
				"shift_a", "입력" };

		public keyBorad() {
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

			setSize(600, 300);
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
					tfall.setText(input.getText());
					dispose();
				}
			}
		}
	}
}