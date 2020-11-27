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
<title>골목밥상</title>
</head>
<body>
<div class="header" style="background-color:white">
	<form class="nav" id="form" style="height: 40px; line-height:40px;margin:0;padding:0;">
		<div style="display: inline-block; float: left; height: 40px;" >
		<a href="/thein/main.do">
			<img src="./resources/main.png" height="40" width="150">
		</a>
		</div>
		<div style="display: inline-block; float:right; height: 40px;">
		<c:choose>
<%-- 		<%if(session.getAttribute("uType")==null){ %> --%>
		<c:when test="${empty sessionScope.uType}">
			ID <input type = "text" name="user_id" id="user_id">
			PWD <input type = "password" id="user_pwd" name="user_pwd">
			<input type="button" value="로그인" id="login">
			<input type="button" value="회원가입" id="register" onclick="location.href='goRegister.do'">
		</c:when>
		<c:when test="${sessionScope.uType eq '9999'}">
<%-- 		<%}else if(session.getAttribute("uType").equals("9999")){ %> --%>
			<a class="link" href="/thein/UserInfo.do">
			${sessionScope.id}님 환영</a>
			<a class="link" href="/thein/shopMgt.jsp">식당 관리</a>
			<a class="link" href="/thein/logout.do">로그아웃</a>
		</c:when>
		<c:otherwise>
<%-- 		<%}else{ %> --%>
			<a class="link" href="/thein/UserInfo.do">
			${sessionScope.id}님 환영</a>
			<a class="link" href="/thein/logout.do">로그아웃</a>
<%-- 		<%}%> --%>
		</c:otherwise>
		</c:choose>
		</div>
	</form>
</div>
</body>
<script>
//login버튼 클릭시
$('#login').click(function(){
	loginCheck();
	//doLogin();
});
//비밀번호에서 엔터키누를때 펑션
$(document).ready(function(){
    $("#user_pwd").keypress(function (e) {
     if (e.which == 13){
    	 		loginCheck();//id&pwd null Check
                doLogin();  // 실행할 이벤트
     }
 });
});

//id,pwd null체크
function loginCheck() {
	var id = document.getElementById('user_id');
	var pwd = document.getElementById('user_pwd');
	if(id.value==""){
		alert("아이디를 확인하세요");
		id.focus();
		return false;
	}
	if(pwd.value==""){
		alert("비밀번호를 확인하세요");
		pwd.focus();
		return false;
	}
	doLogin();
}

//login function 
function doLogin() {
	$.ajax({
		type : "POST",
		url : "/thein/login.do",
		data : $('#form').serialize(),
		success : function(response){
				if(response =="1"){
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
}

</script>
</html>