<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<h1>HOME</h1>
<a href="./command?cmd=make">주소록 5만개 등록하기</a><br><br>
<a href="./command?cmd=registView">등록</a>
<a href="./command?cmd=searchAll">전체 조회</a><br><br>
<a href="./command?cmd=update">멤버 수정하기</a>
<form action="./command?cmd=searchMember" method="post">
	<input type="text" name="id">
	<input type="submit" value="찾기">
</form><br>
<form action="./command?cmd=deleteMember" method="post">
	<input type="text" name="id">
	<input type="submit" value="삭제">
</form><br>
</body>
</html>