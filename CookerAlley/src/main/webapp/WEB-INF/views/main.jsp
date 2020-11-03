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
<div>

<!------------------------------ main  -->
	<div class="container" style="width:80%;">
		<H3>메인 페이지</H3>
<!------------------------------ 검색  -->
		<form class="search" action="/thein/search.do" method="POST">
			<label for="location">위치 : </label>
			<select name="loc" id="loc">
				<option value="gangnam">강남구</option>
				<option value="jung">중구</option>
				<option value="mapo">마포구</option>
				<option value="seodaemon">서대문구</option>
			</select> <input type="date" name="datepick"> <input type="submit"
				value="검색">
		</form>
<!------------------------------- instar  -->
		<div class="instar">
			<h3>INSTAR 부분 (3x3)</h3>
		</div>
<!------------------------------- Footer  -->
	</div>
</div>
</body>

</html>