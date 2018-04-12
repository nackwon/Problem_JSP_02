<%@page import="java.util.Arrays"%>
<%@page import="kr.co.jimmy.VO.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border: 1px solid black;
	text-align: center;
}
tr, td{
	border : 1px solid black;
}
</style>
</head>
<a href="./command?cmd=">홈으로</a>
<body>
	<%
	
		ArrayList<MemberVO> list = null;
		list = (ArrayList<MemberVO>) request.getAttribute("member");
		
		if (list != null) {
			StringBuffer sb = new StringBuffer(
					"<table><tr><td>아이디</td><td>비밀번호</td><td>이메일</td><td>이름</td><td>우편번호</td><td>주소</td><td>상세 주소</td><td>프로젝트경험</td><td>사용 가능 툴</td><td>사용 가능 언어</td></tr>");
			for (MemberVO vo : list) {
				sb.append("<tr><td><a href='./command?cmd=updateReady&id='+vo.getId()/>" + vo.getId() + "</td>");
				sb.append("<td>" + vo.getPw() + "</td> ");
				sb.append("<td>" + vo.getName() + "</td> ");
				sb.append("<td>" + vo.getEmail() + "</td> ");
				sb.append("<td>" + vo.getZipcode() + "</td> ");
				sb.append("<td>" + vo.getAddr1() + "</td> ");
				sb.append("<td>" + vo.getAddr2() + "</td> ");
				sb.append("<td>" + vo.getProject() + "</td> ");
				sb.append("<td>" + vo.getTool() + "</td> ");
				sb.append("<td>" + Arrays.toString(vo.getLangs()) + "</td></tr>");
			}
			sb.append("</table>");
			out.print(sb.toString());
		} else {
			out.print("가입된 회원이 없습니다.");
		}
	%>
</body>
</html>