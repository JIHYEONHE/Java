package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.util.MyBatisUtil;

public class BoardDAOImpl implements IBoardDAO{
	
	private static IBoardDAO boardDao;
	private SqlSession sqlSession;
	
	
	private BoardDAOImpl() {
		sqlSession = MyBatisUtil.getInstance(true);
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAOImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(BoardVO bv){
		
		return sqlSession.insert("board.insertBoard",bv);
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean chk = false;
		
		int cnt = sqlSession.selectOne("board.checkBoard",boardNo);
		if(cnt > 0) {
			chk = true;
		}
		return chk;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		
		return sqlSession.update("board.updateBoard",bv);
	}

	@Override
	public int deleteBoard(String boardNo) {
		
		return sqlSession.delete("board.deleteBoard",boardNo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		
		return sqlSession.selectList("board.BoardAllList");
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv) {
		
		return sqlSession.selectList("board.searchBoardList",bv);
	}

}
