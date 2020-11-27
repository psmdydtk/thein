<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="Header.jsp"%>
<meta charset="UTF-8">
<title>해시태그 인스타</title>
</head>
<body style="background-color:white;">
<%
String hash = request.getParameter("hash");
%>
<!------------------------------- instar  -->
	<div class="instar" style="margin-left:25px; margin-right:25px; background-color:white;" >
         <h4 style="text-align:center;">&nbsp;&nbsp; #<%=hash %>   인스타 게시물</h4>
         <div class="grid_instar" style="margin:10px;" >
         	<c:forEach var="list" items="${crawl}">
         		<div style="border:1px solid black ;max-height: 100%;">
         			<img alt="" src="${list.value.src }" style="height: 95%;width: 100%;"> 
         			 <c:forEach var="tag" items="${list.value.tags}">
         				<a>${tag[0]}${tag[1]}${tag[2]}</a>
         			</c:forEach> 
         		</div>
         		
         	</c:forEach>
            
         </div>
      </div>
</body>
</html>