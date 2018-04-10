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
	int result = (int) request.getAttribute("number");
	if(result == 0){
		
	}
%>
ID check
<form action="./command?cmd=search" method="post">
	아이디<input type="text" name="id" id="id">
	<input type="submit" value="아이디확인">
	<span id="msg"></span>
	<button onclick="work_close()">아이디사용하기</button>
</form>
</body>
</html>