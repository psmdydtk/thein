<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<%@ include file="Header.jsp"%>
<title>Insert title here</title>
</head>

<body>
<div class="grid_menu">
<%@ include file="Menu.jsp"%>
<!------------------------------ main  -->
	<div class="container">
		<H3>회원정보페이지</H3>
<!------------------------------ info list  -->
		<div class="list">
		<form>
			<c:if test="${!empty info}">
				<label>아이디</label>
				<input type="text" name="id" value="${info}" readonly="readonly" style="color: gray;">
				<label>이름</label>
				<input type="text" name="id" value="${info}">
				<label>email</label>
				<input type="text" name="id" value="${info}">
				<label>SNS</label>
				<input type="text" name="id" value="${info}">
			
			</c:if>
		</form>
		</div>
	</div>
</div>
</body>
<%@ include file="Footer.jsp"%>
</html>