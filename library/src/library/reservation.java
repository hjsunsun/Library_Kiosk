package library;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class reservation extends JFrame {
   Database db = new Database();
   private JPanel p1 = new JPanel();
   private JButton btn1 = new JButton("대출");
   private JButton btn2 = new JButton("예약");
   private JButton btn3 = new JButton("연장");
   private JPanel MPanel = new JPanel();
   private JTextField tf = new JTextField(18);
   private JButton keyborad_sn = new JButton("입력");
   private JButton search = new JButton("전체 검색");
   private JLabel lb = new JLabel("예약가능 여부가 1일 때 표를 클릭 후 예약하세요.");
   private JButton reserv = new JButton("예약");
   private JTextArea db_connect = new JTextArea("db연동");

   Connection con = null;
   Statement stmt = null;
   
   public reservation() {
        con = null;
        stmt = null;
	   String url = "jdbc:mysql://124.56.138.3:30/db2019110340";   //dbstudy 스키마
	    String username = "2019110340";
	    String password = "test1234!@#$QWER";      //본인이 설정한 root 계정의 비밀번호를 입력하면 된다.
	  
      setTitle("예약");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setLayout(null);

      

      // 버튼 설정
      btn1.setLocation(1, 0);
      btn2.setLocation(233, 0);
      btn3.setLocation(466, 0);

      btn1.setSize(233, 100);
      btn2.setSize(233, 100);
      btn3.setSize(233, 100);
      btn1.setBackground(Color.white);
      btn2.setBackground(new Color(255, 204, 153));
      btn3.setBackground(Color.white);

      Font big_font = new Font("NanumSquare", Font.BOLD, 30);
      btn1.setFont(big_font);
      btn2.setFont(big_font);
      btn3.setFont(big_font);

      // 예약할 도서 검색하는 패널 설정
      MPanel.add(tf);
      MPanel.add(keyborad_sn);
      MPanel.add(search);
      MPanel.setSize(696, 62);
      MPanel.setLocation(3, 150);

      Font plain_font = new Font("NanumSquare", Font.PLAIN, 25);
      lb.setFont(plain_font);
      tf.setFont(plain_font);
      keyborad_sn.setFont(plain_font);
      search.setFont(plain_font);
      keyborad_sn.setForeground(new Color(255, 255, 255));
      search.setForeground(new Color(255, 255, 255));

      // 예약 방법 알려주는 라벨 설정
      lb.setHorizontalAlignment(NORMAL); // 라벨 가운데 정렬
      lb.setLocation(40, 250);
      lb.setSize(600, 34);

      //테이블 넣을 패널 위치와 크기 설정
      JPanel jPanel_Book = new JPanel();
      jPanel_Book.setSize(690,300);
      jPanel_Book.setLocation(0,300);

      //테이블
      //1. 테이블 정보정의
      String header[]= {"도서ID", "청구기호", "도서명", "저자", "출판사", "예약가능여부"};
      String contents[][]= { };
      //2. jtable 클래스의 객체 생성
      DefaultTableModel Book_model = new DefaultTableModel(contents, header);
      JTable Book_table = new JTable(Book_model);
      Book_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      JScrollPane Book_scrollPane =new JScrollPane(Book_table);
      Book_scrollPane.setPreferredSize(new Dimension(527,300));
      //3.
      jPanel_Book.add(Book_scrollPane);
      c.add(jPanel_Book);
      
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();

			String sql = "select * from 도서;";

			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				Object data[] = { result.getString("도서 ID"), result.getString("청구기호"), result.getString("도서명"),
						result.getString("저자(역자)"), result.getString("출판사"), result.getString("예약가능여부") };

				Book_model.addRow(data);
			}

			// for (int i = 0; i < data.length; i++) {}

		} catch (Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}

		// 리스너 달기
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn3.addActionListener(new inActionListener());
		keyborad_sn.addActionListener(new keyboardActionListener());
		/*
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String value = tf.getText();
				if (Book_table.getSelectedRow() == -1) {
					return;
				} else {

					try {

						for(int i = 0; i < Book_model.getRowCount();i++ ) {
							Book_model.removeRow(i);
						}
						/*
						String header[]= {"도서ID", "청구기호", "도서명", "저자", "출판사", "예약가능여부"};
					      String contents[][]= { };
					      //2. jtable 클래스의 객체 생성
					      DefaultTableModel Book_model1 = new DefaultTableModel(contents, header);
					      JTable Book_table1 = new JTable(Book_model1);
					      Book_table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					      JScrollPane Book_scrollPane1 =new JScrollPane(Book_table1);
					      Book_scrollPane1.setPreferredSize(new Dimension(527,300));
					      //3.
					      jPanel_Book.add(Book_scrollPane1);
					      c.add(jPanel_Book);
					     */
					     /*
						DefaultTableModel dm = (DefaultTableModel) Book_table.getModel();
						int rowCount = dm.getRowCount();
						//Remove rows one by one from the end of the table
						for (int i = rowCount - 1; i >= 0; i--) {
						    dm.removeRow(i);
						}

						//Book_model.setNumRows(0);

						String temp = "select * from 도서 where lower(`도서명`) LIKE '%"+value+"%';";
						System.out.println(temp);
						ResultSet r = stmt.executeQuery(temp);
						r.next();

						while (r.next()) {
							Object data[] = { r.getString("도서 ID"), r.getString("청구기호"), r.getString("도서명"),
									r.getString("저자(역자)"), r.getString("출판사"), r.getString("예약가능여부") };

							Book_model.addRow(data);

						}


					} catch (Exception ex) {
						System.out.println("MySQL 서버 연동 실패 > " + ex.toString());
					}

				}

			}

		}); */


      // 예약버튼
      reserv.setFont(big_font);
      reserv.setForeground(new Color(255, 255, 255));
      reserv.setBackground(new Color(255, 153, 051));
      reserv.setSize(210, 100);
      reserv.setLocation(128, 670);
      reserv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value1 = 0;
				int value2 = 0;
				if (Book_table.getSelectedRow() == -1) {
					return;
				} else {
					int row = Book_table.getSelectedRow();
					
					System.out.println((Book_table.getValueAt(row, 5)).getClass().getTypeName());
					System.out.println((Book_table.getValueAt(row, 0)).getClass().getTypeName());
					value1 = Integer.valueOf((String) Book_table.getValueAt(row, 5));// 예약 가능 여부
					value2 = Integer.valueOf((String)Book_table.getValueAt(row, 0));
					//System.out.println(value1);
					//System.out.println(value2);
					if (value1 == 1) {
						try {

							String sql = "update 도서 set `예약가능여부` = 0 where `도서 ID`= "+value2;
							System.out.println(sql);
							int result = stmt.executeUpdate(sql);
							System.out.println(result);
							
							String temp = "select 회원아이디 FROM 학생 where loginYN = 1;";
							ResultSet r = stmt.executeQuery(temp);
							r.next();
							String userId = r.getString("회원아이디");
			
							String sql1 = "update 대여 set `예약회원ID` = "+userId+" where `도서ID` = "+value2;
							System.out.println(sql1);
							int result2 = stmt.executeUpdate(sql1);
							System.out.println(result2);
							
							JOptionPane.showMessageDialog(null, "예약되었습니다.", "확인 메세지", JOptionPane.INFORMATION_MESSAGE);
							
							new reservation();
							dispose();
							
						} catch (Exception ex) {
							System.out.println("MySQL 서버 연동 실패 > " + ex.toString());
						}
					} else {
						JOptionPane.showMessageDialog(null, "예약이 불가능합니다.", "오류 메세지", JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});

      // 홈버튼
      ImageIcon homeIcon = new ImageIcon("library/images/home.png");
      Image homeimg = homeIcon.getImage();
      homeimg = homeimg.getScaledInstance(52, 52, java.awt.Image.SCALE_SMOOTH);
      homeIcon = new ImageIcon(homeimg);

      JButton btn_home = new JButton("Home", homeIcon);
      btn_home.setBackground(Color.gray);

      btn_home.setFont(big_font);
      btn_home.setForeground(new Color(255, 255, 255));
      btn_home.addActionListener(new inActionListener());
      btn_home.setSize(210, 100);
      btn_home.setLocation(363, 670);

      c.add(reserv);
      c.add(btn1);
      c.add(btn2);
      c.add(btn3);
      c.add(MPanel);
      c.add(lb);
      c.add(db_connect);
      c.add(btn_home);
      

      keyborad_sn.setBackground(new Color(255, 153, 102));
      search.setBackground(new Color(255, 153, 051));


      setSize(700, 800);
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
         } else if (b.getText().equals("Home")) {
            new library_main();
            db.logout();
            dispose();
         }
      }
   }

   class sActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String all = tf.getText().trim();

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
         if (b.getText().equals("입력")) {
            new keyBorad();

         }
      }
   }

   class keyBorad extends JFrame {
      private JPanel p1 = new JPanel();
      private JPanel p2_KEY = new JPanel();
      public JTextArea input = new JTextArea();
      private JButton[] btn_key = new JButton[40];
      private String[] key = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u", "i",
            "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "shift_A", "특수기호",
            "<HTML><body><center>모두<br>지우기</center></body></HTML>", "입력"};
      private String[] shift_key = {" Q", "W", "E", "R", "T", "Y",
            "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
            "shift_a"};
      private String[] special_key = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "숫자"};

      public keyBorad() {
         setTitle("키보드");
         Container con = getContentPane();
         con.setBackground(Color.WHITE);
         con.setLayout(null);

         // 상단라벨
         JLabel label = new JLabel("키보드");
         label.setSize(700, 50);
         label.setLocation(0, 0);
         label.setHorizontalAlignment(JLabel.CENTER);
         label.setOpaque(true);
         label.setBackground(new Color(255, 204, 153));
         Font big_font = new Font("a옛날사진관4", Font.BOLD, 30);
         label.setFont(big_font);

         // 입력확인창
         p1.setLayout(new FlowLayout());
         p1.setBackground(Color.WHITE);
         p1.add(input);
         p1.setSize(700, 30);
         p1.setLocation(0, 55);

         // 입력 글씨 글꼴 및 글씨 크기
         Font font1 = new Font("a옛날사진관2", Font.BOLD, 20);
         input.setFont(font1);
         input.setSize(100, 50);

         // 키패드
         GridLayout grid = new GridLayout(5, 8);
         grid.setVgap(5);
         p2_KEY.setBackground(Color.WHITE);
         p2_KEY.setLayout(grid);
         p2_KEY.setSize(680, 200);
         p2_KEY.setLocation(4, 90);

         for (int i = 0; i < key.length; ++i) {
            btn_key[i] = new JButton(key[i]);
            btn_key[i].addActionListener(new inputkey_ActionListener());
            btn_key[i].setBackground(new Color(255, 229, 216));
            p2_KEY.add(btn_key[i]);
         }

         con.add(label);
         con.add(p1);
         con.add(p2_KEY);

         setSize(700, 330);
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
            } else if (b.getText().equals("<HTML><body><center>모두<br>지우기</center></body></HTML>")) {
               input.setText("");
            } else if (b.getText().equals("shift_A")) {
               int j = 0;
               for (int i = 10; i < 37; ++i) {
                  btn_key[i].setText(shift_key[j]);
                  ++j;
               }
            } else if (b.getText().equals("shift_a")) {
               for (int i = 10; i < 37; ++i) {
                  btn_key[i].setText(key[i]);
               }
               btn_key[36].setText(key[36]);
            } else if (b.getText().equals("특수기호")) {
               for (int i = 0; i < 10; ++i) {
                  btn_key[i].setText(special_key[i]);
               }
               btn_key[37].setText(special_key[10]);
            } else if (b.getText().equals("숫자")) {
               for (int i = 0; i < 10; ++i) {
                  btn_key[i].setText(key[i]);
               }
               btn_key[37].setText(key[37]);
            } else if (b.getText().equals("입력")) {
               tf.setText(input.getText());
               dispose();
            }
         }
      }
   }
}
