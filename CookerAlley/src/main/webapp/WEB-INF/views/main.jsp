<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.project.thein.vo.CookerVO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<%@ include file="Header.jsp"%>

<meta charset="UTF-8">
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<title>Insert title here</title>
</head>

<body>
<!------------------------------ main  -->
	<div class="main">
		<H3>메인 페이지</H3>
<!------------------------------ 검색  -->
		<form class="search" action="/thein/search.do" method="POST">
			<label for="location">위치 : </label>
			<select name="loc" id="loc">
				<option value="gangnam">강남구</option>
				<option value="jung">중구</option>
				<option value="mapo">마포구</option>
				<option value="seodaemon">서대문구</option>
			</select>
			<input type="date" name="datepick"> <input type="submit" value="검색">
		</form>
<!------------------------------- instar  -->
		<div class="instar">
			<h3>INSTAR 부분 (3x3)</h3>
			<div class="grid_instar">
				<div style="border:1px solid red;">1</div>
				<div style="border:1px solid red;">2</div>
				<div style="border:1px solid red;">3</div>
				
				<div style="border:1px solid red;">4</div>
				<div style="border:1px solid red;">5</div>
				<div style="border:1px solid red;">6</div>
				
				<div style="border:1px solid red;">7</div>
				<div style="border:1px solid red;">8</div>
				<div style="border:1px solid red;">9</div>
			</div>
		</div>
<!------------------------------- Footer  -->
	</div>
</body>

</html>