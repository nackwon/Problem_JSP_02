<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function open_post(){
		window.open("postal.jsp","","width=600px height=400px");
	}
</script>
</head>
<body>
<table>
		<tr>
			<td>우편번호</td>
			<td><input type="text" size="4" id="zip1" > -
			<input	type="text" size="4" id="zip2" ></td>
			<td><button onclick="open_post(); return false">우편번호 찾기</button></td>
		</tr>
		<tr>
			<td>주 소</td>
			<td><input type="text" size="50" id="addr1"><br></td>
		</tr>
		<tr>
			<td>상세 주소</td>
			<td><input type="text" size="50" id="addr2"></td>
		</tr>
	</table>
</body>
</html>