<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제성공</title>
</head>
<%
String result = request.getParameter("result");
%>
<script type="text/javascript">
function doExit() {
		alert("결제 성공");
		window.close();
	
}
</script>
<body onload="doExit();">

kakaoPaySuccess

결제에 성공하셨습니다.
</body>

</html>