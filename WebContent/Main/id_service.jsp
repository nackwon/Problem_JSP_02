<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String cmd = request.getParameter("cmd");
	if(cmd.equals("id")){
		cmd = "id";	
	} else {
		cmd = "post";
	}
	out.print(cmd);
%>