package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * https://shs2810.tistory.com/18
 * 
 * 1)java.sql.Driver (옵션, 드라이버 체크)
 * 2)java.sql.Connection (객체 가져오기)
 * 3)java.sql.Statement (객체 생성)
 * 4)java.sql.ResultSet 
 * 5)java.sql.PreparedStatement (쿼리 날리기)
 */

public class JDBCUtil {
//필요한 기능 몰아놓고 호출해서 쓸려고 만듬 ( 자 바 소 스 파일 임)
	
	static { //JDBCUtill 은 클래스가 로딩되면서  딱 한번!!! 자동으로 실행됨
		try { //클래스 있는지 체크 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
			
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!");
		}
	}
	
	 //커넥션 객체 생성하기 /sql connection 사용
	 // @return
	public static Connection getConnection() {
		try { //정상일때는 connection 아니면 null return
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"pc03",
					"java");
		}catch(SQLException ex) {
			System.out.println("DB 연결 실패!!");
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 자원반납 하는 메소드 close
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn,
			Statement stmt, 
			PreparedStatement pstmt, 
			ResultSet rs) {
		
		if(rs != null) try {rs.close();}catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
	}
}
