<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./assets/css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar-left">
		<nav>
		<%if(session.getAttribute("uType")==null){ %>
			<p><a href="#">SNS</a></p>
			
			<p><a href="#">가입하기</a></p>
		<%}else if(session.getAttribute("id").equals("admin")){ %>
			<p><a href="/thein/">식당 추가</a></p>
			<p><a href="#">식당 수정</a></p>
			<p><a href="#">식당 삭제</a></p>
		<%}else{ %>
			<p><a href="#">SNS</a></p>
			
			<p>MY PAGE</p>
			<ul>
				<li><a href="/thein/UserInfo.do">회원정보</a></li>
				<li><a href="#">밥상기록</a></li>
			</ul>
		<%}%>

		</nav>
	</div>
</body>
</html>