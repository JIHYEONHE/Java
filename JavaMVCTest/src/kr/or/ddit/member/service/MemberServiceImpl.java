package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
 
	private IMemberDAO memDao;
	public MemberServiceImpl() {
		memDao = new MemberDAOImpl(); 
	}
	
	@Override
	public int registerMember(MemberVO mv) {
	
		int cnt = memDao.insertMember(mv);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = memDao.checkMember(memId);
		return chk;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		
		int cnt = memDao.deleteMember(memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
	
		List<MemberVO>memList = memDao.getAllMemberList();
		
		return memList;
	}
	

//	public void accountTransfer() {
//		try {
//		트랜잭션 시작
//		계죄DAO.update(); 현석계정 백만원인출
//		계죄DAO.update(); 재경계정 백만원 입금 
//		트랜잭션 종료 (commit)
//		}catch(Exception ex) {
//			롤백처리
//		}
//	}

	/**
	 * 검색할 회원 정보를 담은 MemberVO객체를 이용하여 회원정보를 조회하는 메서드
	 * @param mv 검색할 회원 정보를 담은 MemberVO객체
	 * @return 검색된 회원정보를 담은 List객체 
	 */
	public List<MemberVO> searchMemberList(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMemberList(mv);
		
		return memList;
	}
	
	
	
}
