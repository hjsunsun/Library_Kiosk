package library;

import java.sql.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://124.56.138.3:30/db2019110340";	//dbstudy 스키마
	String username = "2019110340";
	String password = "test1234!@#$QWER";		//본인이 설정한 root 계정의 비밀번호를 입력하면 된다.
	
	Database() {	//데이터베이스에 연결한다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
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

}