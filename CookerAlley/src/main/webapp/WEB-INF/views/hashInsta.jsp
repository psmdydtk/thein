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
<body>
<!------------------------------- instar  -->
           <div class="instar">
         <h4>&nbsp;&nbsp; 골목식당 인기게시물</h4>
         <div class="grid_instar" style="grid-gap: 30px; margin:100px;">
            <c:forEach items="${crawl}" var="crawl">
               <div style="border:1px solid gray; width:400px">
               <img src="${crawl.value.src }" width="100%" height="330px"></img>
               <div style="width: 100%; text-align:center; font-size:14px;">
                  <c:forEach items="${crawl.value.tags }" var="tag">
                     <a>${tag[0]}</a>
                     <a>${tag[1]}</a>
                     <a>${tag[2]}</a>
                  </c:forEach>
               </div>
               </div>
            </c:forEach> 
         </div>
      </div>

</body>
</html>