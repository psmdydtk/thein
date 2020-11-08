<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<%@ include file="../WEB-INF/views/Header.jsp"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
	div[class^="col"]{padding:5px;}
	label[class^="label"]{width:50px;}
</style>
<title>Insert title here</title>
</head>

<body>
<div class="grid_menu">
<%-- <%@ include file="../WEB-INF/views/Menu.jsp"%> --%>
<%@ include file="../WEB-INF/views/Menu.jsp"%>
<!------------------------------ main  -->
	<div class="container">
		<H3>식당관리페이지</H3>
<!------------------------------ type별 관리 페이지 보이기  -->
		
		<c:choose>
		<c:when test="${!empty param.type}">
			<c:if test="${param.type eq 'insert'}">
				<%-- <%@include file="../WEB-INF/views/shopInsert.jsp" %> --%>
			</c:if>
			
			<c:if test="${param.type eq 'update'}">
				<%@include file="../WEB-INF/views/MgtSearchShop.jsp" %>
			</c:if>
			
			<c:if test="${param.type eq 'delete'}">
				<%@include file="../WEB-INF/views/shopDelete.jsp" %>
			</c:if>
		
		</c:when>
		<c:otherwise>
		식당 식당식다잇다식당
		</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
<%@ include file="../WEB-INF/views/Footer.jsp"%>
</html>