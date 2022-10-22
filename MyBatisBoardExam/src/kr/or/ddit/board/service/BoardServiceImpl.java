package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.vo.BoardVO;


public class BoardServiceImpl implements IBoardService{
	private static IBoardService boardService;
	private IBoardDAO boardDAO;
	
	private BoardServiceImpl() {
		boardDAO = BoardDAOImpl.getInstance();
	}

	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	@Override
	public int registerBoard(BoardVO bv) {
		int cnt = boardDAO.insertBoard(bv);
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean chk = boardDAO.checkBoard(boardNo);
		return chk;
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		int cnt = boardDAO.updateBoard(bv);
		return cnt;
	}

	@Override
	public int removeBoard(String boardNo) {
		int cnt = boardDAO.deleteBoard(boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> memList = boardDAO.getAllBoardList();
		return memList;
	}
	
	public void accountTransfer() {
		try {
		// 트랜잭션 시작
		// 계좌DAO.update();
		//계좌DAO.update();
		
		//트랜잭션 정상종료(commit)
		}catch(Exception e) {
			//롤백
		}
	}

	@Override
	public List<BoardVO> searchBoardList(BoardVO bv) {
		List<BoardVO> memList = boardDAO.searchBoardList(bv);
		return memList;
	}
	
}
