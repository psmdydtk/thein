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
		<form class="grid_info">
			<c:if test="${!empty info}">
				<div>
				<label>아이디</label>
				<input type="text" name="id" value="${info}" readonly="readonly" style="color: gray;">
				</div>
				<div>
				<label>이름</label>
				<input type="text" name="id" value="${info}">
				</div>
				<div>
				<label>email</label>
				<input type="text" name="id" value="${info}">
				</div>
				<div>
				<label>SNS</label>
				<input type="text" name="id" value="${info}">
				</div>
			</c:if>
		</form>
	</div>
</div>
</body>
<%@ include file="Footer.jsp"%>
</html>