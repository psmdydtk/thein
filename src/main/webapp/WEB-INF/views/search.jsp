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
		<H3>리스트 페이지</H3>
<!------------------------------ 검색  -->
		<form class="search" action="/thein/search.do" method="POST">
			<label for="location">위치 : </label>
			<select name="loc" id="loc">
				<option value="gangnam">강남구</option>
				<option value="jung">중구</option>
				<option value="mapo">마포구</option>
				<option value="seodaemon">서대문구</option>
			</select>
			<input type="date" name="datepick">
			<input type="submit" value="검색">
		</form>
<!------------------------------ list  -->
		<div class="list">
		<form action="/thein/detail.do" method="POST">
			<H3>검색 결과 목록</H3>
			<c:if test="${!empty list}">
				<p>${list}</p>
				<label>for each로 돌리기 - 식당1</label>
				<input type="text" name="cooker">
				<input type="submit" name="detail" value="검색">
			
			</c:if>
		</form>
		</div>
	</div>
</div>
</body>
<%@ include file="Footer.jsp"%>
</html>