<%@page import="kr.co.jimmy.DAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	MemberDAO dao = new MemberDAO();
	String cmd1 = request.getParameter("cmd1");
	String id = request.getParameter("id");
	boolean flag = dao.id_check(id);
	
	if (cmd1.equals("id")) {
		if (flag) {
			cmd1 = "not";
		} else {
			cmd1 = "use";
		}
	} else {
		cmd1 = "post";
	}
	String json ="{\"user\":\"admin\",\"message\":\"success\"}";
	out.print(json);
	//out.print(cmd1);
%>