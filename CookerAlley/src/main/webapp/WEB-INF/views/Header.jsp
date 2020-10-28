<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% Random r = new Random(); int i =r.nextInt(1000000); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./assets/css/style.css?ver=<%=i %>" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="menu">
	<div>
	<a href="/thein/main.do" class="link">메인 </a>
	</div>
		<div>
			<a href="" class="link">회원가입 </a>
		</div>
		<div>
			<a href="" class="link">로그인</a>
		</div>
	</div>
</div>
</body>
</html>