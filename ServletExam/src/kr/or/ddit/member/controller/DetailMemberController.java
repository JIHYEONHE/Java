package kr.or.ddit.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import kr.or.ddit.comm.service.*;
import kr.or.ddit.comm.vo.*;
import kr.or.ddit.member.service.*;
import kr.or.ddit.member.vo.*;

@WebServlet("/member/detail.do")
public class DetailMemberController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String memId = req.getParameter("memId");
    
       //1.서비스 객체 생성하기 
       IMemberService memService = MemberServiceImpl.getInstance();
       MemberVO mv = memService.getMember(memId);
       
       IAtchFileService fileService = AtchFileServiceImpl.getInstance();
       
       if(mv.getAtchFileId() > 0) { // 첨부파일 존재하면...
          // 1-2. 첨부파일 정보 조회
          AtchFileVO fileVO = new AtchFileVO();
          fileVO.setAtchFileId(mv.getAtchFileId());
          
          List<AtchFileVO> atchFileList = fileService.getAtchFileList(fileVO);
          
          req.setAttribute("atchFileList", atchFileList);
       }
       req.setAttribute("mv", mv);
       
       req.getRequestDispatcher("/view/member/detail.jsp").forward(req,resp);
    }
 
 @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
 
}