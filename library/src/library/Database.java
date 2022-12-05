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
	String password = "test1234!@#$QWER";		//root 계정의 비밀번호 입력
	
	Database() {	//데이터베이스에 연결한다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 로딩
			con = DriverManager.getConnection(url, username, password); // 데이터베이스 연결
			stmt = con.createStatement(); // Statement (정적쿼리) 생성
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
		
		String id = _i; // 입력받은 id
		String pw = _p; // 입력받은 password
		PreparedStatement ps = null;
		
		try {
			String checkingStr = "SELECT 비밀번호 FROM 학생 WHERE 회원아이디='" + id + "'"; // 입력받은 id에 따라 학생 테이블의 비밀번호 select
			ResultSet result = stmt.executeQuery(checkingStr); // resultset으로 쿼리 생성
			String loginupdate = "update 학생 set loginYN = 1 WHERE 회원아이디 ='" + id + "'"; // 입력받은 id 로그인 여부 1로 update
			ps = con.prepareStatement(loginupdate); // 쿼리 생성

			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("비밀번호"))) { // 비밀번호 컬럼 데이터와 같다면
					flag = true;
					ps.executeUpdate(); // 쿼리 실행
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
			String logout = "update 학생 set loginYN = 0"; // 로그인 상태 0으로 update
			ps = con.prepareStatement(logout); // 쿼리 생성

			int count = 0;
			ps.executeUpdate(); // 쿼리 실행

		} catch(Exception e) {
			System.out.println("로그아웃 실패 > " + e.toString());
		}
	}

	/* 책 대출 */
	void borrow(String _bId){

		String bookId = _bId;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		// 대출 여부 dialog 생성
		int num = JOptionPane.showConfirmDialog(null, "대출하시겠습니까?", "대출", JOptionPane.YES_NO_OPTION);


		try {
			// 아이디 체크
			String checkId = "SELECT 회원아이디 FROM 학생 WHERE loginYN = 1"; // 로그인 상태 1인 학생 테이블의 회원 아이디 조회
			ResultSet resultId = stmt.executeQuery(checkId);

			while(resultId.next()) {
				// 대여 가능 권수 체크
				String checkBooknum = "select 대출가능권수 FROM 학생 where 회원아이디 = " + resultId.getString(1) + ";";
				ResultSet resultBooknum = stmt2.executeQuery(checkBooknum);

				while(resultBooknum.next()) {
					if (resultBooknum.getInt(1) <= 0) { // 대여 가능 권수가 0보다 같거나 작다면
						// 대여 불가 경고 dialog 생성
						JOptionPane.showMessageDialog(null, "대출 가능 권수가 0권입니다.", "대출 불가", JOptionPane.ERROR_MESSAGE);
						break;
					}
					else {
						// 학생 테이블 대여 가능 권수 감소 update문
						String bUpdate = "UPDATE\n" +
								"    학생 as A, 대여 as B\n" +
								"SET\n" +
								"    A.대출가능권수 = A.대출가능권수 - 1\n" +
								"WHERE\n" +
								"\tA.회원아이디 = B.`회원학번` and A.회원아이디 =" + resultId.getString(1) + ";";

						// 대여 테이블 정보 추가
						String borrow = "INSERT INTO 대여 (`회원학번`, `도서ID`, `대출일자`, `반납가능일자`) VALUES (" + resultId.getString(1) + "," + bookId + ", NOW(),(NOW()+21));";
						String infoupdate = "UPDATE 대여 set `반납가능일자`= DATE_ADD(NOW(), Interval 21 day)  where `도서ID` = " + bookId + ";";

						ps1 = con.prepareStatement(bUpdate); // 쿼리 생성
						ps2 = con.prepareStatement(borrow);
						int count = 0;
						ps1.executeUpdate(); // 쿼리 실행
						ps2.executeUpdate();


						if(num == JOptionPane.CLOSED_OPTION) { // 닫기 버튼 클릭시
							new borrow(); // 대여 페이지 보여주기
							return;
						}
						else if(num == JOptionPane.YES_OPTION) { // yes option 클릭시
							// 대출 완료 안내 dialog 생성
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
			String checkId = "SELECT 회원아이디 FROM 학생 WHERE 대출가능권수 < 10"; // 대출 가능 권수가 10보다 작은 회원 아이디 조회 (한번이라도 빌린 회원 아이디라면)
			ResultSet resultId = stmt.executeQuery(checkId);

			String checkingStr = "DELETE FROM 대여 WHERE 도서ID = "+bookId+";"; // 해당하는 bookID를 대여한 기록이 있는 대여 row 데이터 삭제문

			while(resultId.next()) {
				// 반납시 대여 가능 권수 1권 추가
				String checkingStr2 = "UPDATE\n" +
						"    학생 as A, 대여 as B\n" +
						"SET\n" +
						"    A.대출가능권수 = A.대출가능권수 + 1\n" +
						"WHERE\n" +
						"\tA.회원아이디 = B.`회원학번` and A.회원아이디 =" + resultId.getString(1) + ";";
				ps2 = con.prepareStatement(checkingStr); // 쿼리 생성
				ps3 = con.prepareStatement(checkingStr2);

				ps2.executeUpdate(); // 쿼리 실행
				ps3.executeUpdate();
			}


		} catch(Exception e) {

			System.out.println("반납 실패 > " + e.toString());
		}
	}



}