package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * https://shs2810.tistory.com/18
 * 
 * 1)java.sql.Driver (옵션, 드라이버 체크)
 * 2)java.sql.Connection (객체 가져오기)
 * 3)java.sql.Statement (객체 생성)
 * 4)java.sql.ResultSet 
 * 5)java.sql.PreparedStatement (쿼리 날리기)
 */
/**
 * db.Properties파일의 내용으로 db정보를 설정하는 방법
 * 방법1) Properties 객체 이용하기
 * @author PC-03
 *
 */
public class JDBCUtil2 {
//필요한 기능 몰아놓고 호출해서 쓸려고 만듬 ( 자 바 소 스 파일 임)

	static Properties prop; // Properties 객체변수 선언
	
	static { //JDBCUtill 은 클래스가 로딩되면서  딱 한번!!! 자동으로 실행됨
	
		prop = new Properties();
			
		
		try { //클래스 있는지 체크 
			prop.load(new FileInputStream("res/db.properties"));

			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 완료!");
			
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!");
		}catch(IOException ex) {
			System.out.println("해당 파일이 없거나 입출력 오류입니다.");
			ex.printStackTrace();
		}
	}
	
	 //커넥션 객체 생성하기 /sql connection 사용
	 // @return
	public static Connection getConnection() {
		try { //정상일때는 connection 아니면 null return
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
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
