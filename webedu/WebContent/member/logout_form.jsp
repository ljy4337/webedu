<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	//기존 세션 데이터를 모두 삭제
	session.invalidate();
	
	// 로그인 페이지로 이동 
	response.sendRedirect("/webedu/member/login_view.do");
%>

	      

