<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page import="kr.or.ddit.comm.vo.AtchFileVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   MemberVO mv = (MemberVO) request.getAttribute("mv");
   List<AtchFileVO> atchFileList =
         (List<AtchFileVO>) request.getAttribute("atchFileList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기존 회원 수정</title>
</head>
<body>
   <table border="1">
      <tr>
         <th>I D :</th>
         <td><%= mv.getMemId() %></td>
      </tr>
      <tr>
         <th>이름 :</th>
         <td><%= mv.getMemName() %></td>
      </tr>
      <tr>
         <th>전화번호 :</th>
         <td><%= mv.getMemTel() %></td>
      </tr>
      <tr>
         <th>주소 :</th>
         <td><%= mv.getMemAddr() %></td>
      </tr>
      <tr>
         <th>첨부파일 :</th>
         <td>
            <%
               if(atchFileList != null) {
                  for(AtchFileVO fileVO : atchFileList) {
            %>
       <div><a href="<%=request.getContextPath()%>/filedown.do?fileId=<%=fileVO.getAtchFileId() %>&fileSn=<%=fileVO.getFileSn()%>"><%=fileVO.getOrignlFileNm() %></a></div>
            <%   
                  }
               }
            %>
         </td>
      </tr>
      <tr>
         <td colspan="2">
            <a href="list.do">[목록]</a>
            <a href="update.do?memId=<%= mv.getMemId() %>">[회원정보 수정]</a>
            <a href="delete.do?memId=<%= mv.getMemId() %>">[회원정보 삭제]</a>
         </td>
   </table>
</body>
</html>