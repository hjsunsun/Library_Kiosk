package library;

import javax.swing.*;
import java.sql.*;


public class Database {
	Connection con = null;
	Statement stmt = null;
	Statement stmt2 = null;
	Statement stmt3 = null;
	String url = "jdbc:mysql://124.56.138.3:30/db2019110340";	//dbstudy 스키마
	String username = "2019110340";
	String password = "test1234!@#$QWER";		//본인이 설정한 root 계정의 비밀번호를 입력하면 된다.
	
	Database() {	//데이터베이스에 연결한다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			System.out.println("MySQL 서버 연동 성공");
		} catch(Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}
	}

	/* 로그인 정보를 확인 */
	boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		PreparedStatement ps = null;
		
		try {
			String checkingStr = "SELECT 비밀번호 FROM 학생 WHERE 회원아이디='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);
			String loginupdate = "update 학생 set loginYN = 1 WHERE 회원아이디 ='" + id + "'";
			ps = con.prepareStatement(loginupdate);

			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("비밀번호"))) {
					flag = true;
					ps.executeUpdate();
				}

				else {
					flag = false;
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}

	/* 로그아웃 */
	void logout(){
		PreparedStatement ps = null;

		try {
			String logout = "update 학생 set loginYN = 0";
			ps = con.prepareStatement(logout);

			int count = 0;
			ps.executeUpdate();

		} catch(Exception e) {
			System.out.println("로그아웃 실패 > " + e.toString());
		}
	}
	/* 책 보유 여부 확인 */
	boolean bookcheck(String _b) {
		boolean flag = false;

		String book = _b; // ex) 20001

		try {
			String checkingStr = "SELECT "+ book+" FROM 도서";
			ResultSet result = stmt.executeQuery(checkingStr);

			int count = 0;
			while(result.next()) {
				if(book.equals(result.getString("도서 ID"))) {
					flag = true;
				}

				else {
					flag = false;
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("도서 찾기 실패 > " + e.toString());
		}

		return flag;
	}

	/* 책 대출 */
	void borrow(String _bId){

		String bookId = _bId;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		int num = JOptionPane.showConfirmDialog(null, "대출하시겠습니까?", "대출", JOptionPane.YES_NO_OPTION);


		try {
			// 아이디 체크
			String checkId = "SELECT 회원아이디 FROM 학생 WHERE loginYN = 1";
			ResultSet resultId = stmt.executeQuery(checkId);

			while(resultId.next()) {
				// 대여 가능 권수 체크
				String checkBooknum = "select 대출가능권수 FROM 학생 where 회원아이디 = " + resultId.getString(1) + ";";
				ResultSet resultBooknum = stmt2.executeQuery(checkBooknum);

				while(resultBooknum.next()) {
					if (resultBooknum.getInt(1) <= 0) {
						JOptionPane.showMessageDialog(null, "대출 가능 권수가 0권입니다.", "대출 불가", JOptionPane.ERROR_MESSAGE);
						break;
					}
					else {
						// 학생 테이블 대여 가능 권수 감소
						String bUpdate = "UPDATE\n" +
								"    학생 as A, 대여 as B\n" +
								"SET\n" +
								"    A.대출가능권수 = A.대출가능권수 - 1\n" +
								"WHERE\n" +
								"\tA.회원아이디 = B.`회원학번` and A.회원아이디 =" + resultId.getString(1) + ";";

						// 대여 테이블 정보 추가
						String borrow = "INSERT INTO 대여 (`회원학번`, `도서ID`, `대출일자`, `반납가능일자`) VALUES (" + resultId.getString(1) + "," + bookId + ", NOW(),(NOW()+21));";
						String infoupdate = "UPDATE 대여 set `반납가능일자`= DATE_ADD(NOW(), Interval 21 day)  where `도서ID` = " + bookId + ";";

						ps1 = con.prepareStatement(bUpdate);
						ps2 = con.prepareStatement(borrow);
						int count = 0;
						ps1.executeUpdate();
						ps2.executeUpdate();


						if(num == JOptionPane.CLOSED_OPTION) {
							new borrow();
							return;
						}
						else if(num == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.", "대출 성공", JOptionPane.INFORMATION_MESSAGE);
						}

					}
						break;
					}
					break;
				}


		} catch(Exception e) {
			System.out.println("대여 실패 > " + e.toString());
		}
	}

	/* 반납 */
	void returnBook(String _bId){
		String bookId = _bId;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		try {
			String checkId = "SELECT 회원아이디 FROM 학생 WHERE 대출가능권수 < 10";
			ResultSet resultId = stmt.executeQuery(checkId);

			String checkingStr = "DELETE FROM 대여 WHERE 도서ID = "+bookId+";";

			while(resultId.next()) {
				String checkingStr2 = "UPDATE\n" +
						"    학생 as A, 대여 as B\n" +
						"SET\n" +
						"    A.대출가능권수 = A.대출가능권수 + 1\n" +
						"WHERE\n" +
						"\tA.회원아이디 = B.`회원학번` and A.회원아이디 =" + resultId.getString(1) + ";";
				ps2 = con.prepareStatement(checkingStr);
				ps3 = con.prepareStatement(checkingStr2);

				ps2.executeUpdate();
				ps3.executeUpdate();
			}


		} catch(Exception e) {
			System.out.println("반납 실패 > " + e.toString());
		}
	}



}