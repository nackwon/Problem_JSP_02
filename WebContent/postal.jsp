<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jimmy.VO.ZipCodeVO"%>
<%@page import="kr.co.jimmy.DAO.ZipcodeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function select() {
		var val = document.getElementById("select").value;
		var temp = val.split(" ");
		var zipcode = temp[0].split("-");
		var addr1 = val.substring(temp[0].length+1);
		document.getElementById("zip1").value = zipcode[0];
		document.getElementById("zip2").value = zipcode[1];
		document.getElementById("addr1").value = addr1;
	}
	
	function use_zipcode() {
		var zip1 = document.getElementById("zip1").value;
		var zip2 = document.getElementById("zip2").value;
		var addr1 = document.getElementById("addr1").value;
		var addr2 = document.getElementById("addr2").value;

		opener.document.getElementById("zip1").value = zip1;
		opener.document.getElementById("zip2").value = zip2;
		opener.document.getElementById("addr1").value = addr1;
		opener.document.getElementById("addr2").value = addr2;
		opener.document.getElementById("isZipCheck").value = true;
		self.close();
	}
</script>
</head>
<body>
	<form action="./command?cmd=searchpost" method="post">
		<input type="text" name="dong"> 
		<input type="submit" value="찾기">
	</form>
	<%
		ArrayList<ZipCodeVO> list = (ArrayList<ZipCodeVO>) request.getAttribute("vo");

		if (list == null)
			list = new ArrayList<ZipCodeVO>();

		StringBuffer sb = new StringBuffer("<select onchange='select()' id='select'> <option>주소 선택</option>");
		for (ZipCodeVO vo : list) {
			sb.append("<option value='" + vo.toAddress() + "'>");
			sb.append(vo.toString() + "</option>");
		}
		sb.append("</select>");
		out.print(sb.toString());
	%>
	<br>
	<br>
	<br>
	<table>
		<tr>
			<td>우편번호</td>
			<td><input type="text" size="4" id="zip1" disabled="disabled">
				- <input type="text" size="4" id="zip2" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td>주 소</td>
			<td><input type="text" size="50" id="addr1" disabled="disabled"><br></td>
		</tr>
		<tr>
			<td>상세 주소</td>
			<td><input type="text" size="50" id="addr2"></td>
		</tr>
	</table>
	<button onclick="use_zipcode()">사용하기</button>
</body>
</html>