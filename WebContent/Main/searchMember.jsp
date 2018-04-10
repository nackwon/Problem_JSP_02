<%@page import="kr.co.jimmy.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		MemberVO vo = (MemberVO)request.getAttribute("searchMember");
		out.print(vo.getId()+" "+ vo.getPw()+" "+vo.getName()+ " "+vo.getEmail()+" "+vo.getZipcode()+" "+vo.getAddr1()+" "+vo.getAddr2());
	%>
</body>
</html>