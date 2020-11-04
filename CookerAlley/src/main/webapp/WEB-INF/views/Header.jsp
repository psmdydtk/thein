<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% Random r = new Random(); int i =r.nextInt(1000000); %>
    
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<meta charset="UTF-8">
<link href="./assets/css/style.css?ver=<%=i %>" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div class="header">
	<form class="nav" id="form">
	<a class="link" href="/thein/main.do">메인</a>
	<%if(session.getAttribute("id")==null){ %>
		아이디<input type = "text" name="id">
		패스워드 <input type = "password" name="password">
		<input type="button" value="로그인" id="login">
	<%}else{ %>
	<a class="link" href="/thein/UserInfo.do">
	${sessionScope.id}님 환영</a>
	<a class="link" href="/thein/logout.do">로그아웃</a>
	<%} %>
	</form>
</div>
</body>
<script>
$('#login').click(function(){
	$.ajax({
		type : "POST",
		url : "/thein/login.do",
		data : $('#form').serialize(),
		success : function(response){
				if(response =="1"){
					//session?
					alert("로그인 성공");
					location.href="/thein/main.do";
				}else{
					alert("로그인 실패");
					$('#form')[0].reset();
				}
			},
		//error:function(request,status,error){
		//	    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}

	});
});
</script>
</html>