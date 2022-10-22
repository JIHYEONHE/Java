package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

//MODEL부분 
public class MemberDAOImplForJDBC implements IMemberDAO {

	// 나자신을 담기위한 static변수 생성
	private static IMemberDAO memDao;

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//2.private생성자
	private MemberDAOImplForJDBC() {
		
	}
	
	public static IMemberDAO getInstance() {
		if(memDao == null) {
			memDao = new MemberDAOImplForJDBC();
			
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();

			String sql = "INSERT INTO mymember (mem_id,mem_name,mem_tel, mem_addr,reg_dt)" + "VALUES (?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql); // preparedstatement 만들기
			pstmt.setNString(1, mv.getMemId());
			pstmt.setNString(2, mv.getMemName());
			pstmt.setNString(3, mv.getMemTel());
			pstmt.setNString(4, mv.getMemAddr());

			cnt = pstmt.executeUpdate(); // insert,update, delete -> executeUpdate

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원등록 과정중 예외발생!", ex);
		} finally { // close 하면 자원반납
			JDBCUtil3.close(conn, stmt, pstmt, rs); // JDBCUtil3 에 만든것 호출해서 사용ㅇ
		}

		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = false;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select count(*) as cnt from mymember" + " where mem_id = ? "; // count로 세는 쿼리

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery(); // rs 는 resultset

			int cnt = 0;

			if (rs.next()) {
				cnt = rs.getInt("cnt"); // cnt 컬럼이름을 줌 /숫자형이라서 getInt
			}

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs); // null이아닌것 알아서 close
		}

		return chk;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();

			String sql = "UPDATE mymember" + " SET mem_name = ? " + " ,mem_tel = ? " + " ,mem_addr = ? "
					+ "  WHERE mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			// 물음표값 세팅
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원정보 수정중 예외발생!", ex);
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection(); // 연결

			String sql = " delete from mymember where mem_id = ? "; // 쿼리

			pstmt = conn.prepareStatement(sql); // 위의 sql 넣어줌
			pstmt.setString(1, memId); // 쿼리 ?에 들어갈 것

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("회원삭제 과정중 예외발생!", ex);
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) { // 컬럼이름으로 한줄한줄 접근
				MemberVO mv = new MemberVO();
				mv.setMemId(rs.getNString("mem_id"));
				mv.setMemName(rs.getNString("mem_name"));
				mv.setMemTel(rs.getNString("mem_tel"));
				mv.setMemAddr(rs.getNString("mem_addr"));

				memList.add(mv);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("전체회원 조회 중  예외발생!", ex);
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMemberList(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select * from mymember where 1=1 ";

			if (mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if (mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if (mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if (mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr = '%' ||?|| '%' ";
			}

			pstmt = conn.prepareStatement(sql);

			int index = 1;

			if (mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if (mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if (mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if (mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}

			rs = pstmt.executeQuery(); // select 라서 query사용

			while (rs.next()) { // 컬럼이름으로 한줄한줄 접근
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(rs.getNString("mem_id"));
				mv2.setMemName(rs.getNString("mem_name"));
				mv2.setMemTel(rs.getNString("mem_tel"));
				mv2.setMemAddr(rs.getNString("mem_addr"));

				memList.add(mv2);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

}
