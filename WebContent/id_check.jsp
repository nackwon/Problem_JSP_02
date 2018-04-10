<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 검사</title>
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
	if(result == null){
		result ="";
	} else if(result.equals(id) ){
		System.out.print("중복된 아이디 입니다.");
	} else{
		System.out.print("사용가능한 아이디 입니다.");
	}
%>
ID check
<form action="./command?cmd=search" method="post">
	아이디<input type="text" name="id" id="id">
	<input type="submit" value="아이디 중복 체크">
	<span id="msg"></span>
	<button onclick="work_close()">아이디사용하기</button>
</form>
</body>
</html>