<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 검사</title>
<style type="text/css">
	#fail{
		color: red;
	}
</style>
<script type="text/javascript">
	function work_close(){
		//id의 값을 가져오기
		var userid = document.getElementById("id").value;
		opener.document.getElementById("userid").value = userid;
		opener.document.getElementById("isIdCheck").value = true;
		self.close();
	}
</script>
</head>
<body>
<%	
	String result = (String)request.getAttribute("result");
	String id = (String)request.getParameter("id");
	if(id == null)
		id ="";
	if(result == null)
		result ="";
%>
ID check
<form action="./command?cmd=search" method="post">
	<table>
		<tr>
			<td>
				아이디<input type="text" name="id" id="id" value="<%=id%>">
			</td>
			<td>
				<input type="submit" value="아이디 중복 체크">
			</td>
		</tr>
	</table>
</form>
<%
	if("true".equals(result)){
		out.print("<span id='success'>사용할 수 있는 아이디 입니다.</span>");
		out.print("<button onclick='work_close()'>아이디 사용하기</button>");
	} else if("false".equals(result)){
		out.print("<sapn id='fail'>아이디를 다시 입력해주세요</span>");
	}
%>
</body>
</html>