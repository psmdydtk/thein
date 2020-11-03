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
<div class="grid">
<%@ include file="Menu.jsp"%>
<!------------------------------ main  -->
	<div class="container">
		<H3>상세 페이지</H3>
<!------------------------------ 검색	  -->
<!------------------------------ list  -->
		<div class="list">
		<form>
			<H3>식당 정보</H3>
			<c:if test="${!empty detail}">
				<p>${detail}</p>
			</c:if>
		</form>
		</div>
<!------------------------------ hash/button  -->
		<div class="hash">
		<form>
			<input type="button" value="에약">
			<a href="" class="link">###1</a>
			<a href="" class="link">###2</a>
			<a href="" class="link">###3</a>
		</form>
		</div>
<!------------------------------ keyword  -->
		<div class="keyword">
			<H3>키워드</H3>
		</div>
	</div>
</div>
</body>
<%@ include file="Footer.jsp"%>
</html>