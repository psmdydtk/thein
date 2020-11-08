<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
	div[class^="col"]{padding:5px;}
</style>
<%@ include file="Header.jsp"%>
<script async src="//www.google-analytics.com/analytics.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>

<body>
<%
String sido1=request.getParameter("sido1");
String gugun1 = request.getParameter("gugun1");
String datepick = request.getParameter("datepick");
%>

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
      <div class="list" >
      <table data-text-content="true">
         <tbody>
            <tr><th>검색 결과</th></tr>
            <c:forEach items="${viewAll }" var="list">
               <tr>
                  <td>
                     <form id="form${list.shop_id}" action="detail.do?datepick=<%=datepick %>" method="POST">
                        <table border="1" style="width:900px;margin:3px;padding:10px;">
						<tr>
							<td rowspan="6" colspan="4"><img height="300" width="300" src= "https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/116337329_652158698743520_2370389466736758097_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=108&_nc_ohc=piCNR4iaLvIAX84XpuQ&_nc_tp=15&oh=143d256b54ba12948884137c9f22b87d&oe=5FCF3F5A"></td>
							<th colspan="3"><a href="#" id="link${list.shop_id}" style="color:blue">${list.shop_name}</a></th>
							<td rowspan="2" style="text-align: center;"><img src="./resources/heart.png" height="40" width="40"></td>
						</tr>
						<tr>
							<th colspan="3">별점 :      ${list.shop_point}</th>
						</tr>
						<tr>
							<td colspan="4">골목 :      ${list.shop_street}</td>
						</tr>
						<tr>
							<td colspan="4">대표메뉴 :       ${list.shop_menu1}  가격 : ${list.shop_menu1_price}</td>
						</tr>
<tr>
							<td colspan="4">설명 :  ${list.shop_description}</td>
						</tr>
						<tr>
							<td colspan="4">
							<input type="button" name="#" value="자세히보기" style="float: right">
							<c:if test="${sessionScope.uType eq '9999'}">
								<input type="button" value="수정" onclick="window.location.href='/thein/shopUpdate.do?shop_id=${list.shop_id}'" style="float: right;margin-right:5px;">
								<a href="/thein/shopUpdate.do?shop_id=${list.shop_id}">이동</a>
							</c:if>
							</td>
						</tr>
						</table>
                        <input type="hidden" id= "shop_id_${list.shop_id}" name="shop_id_${list.shop_id}" value = "${list.shop_id}"/>
                     </form>
                  </td>
                  <script type="text/javascript">
                  $( "#link${list.shop_id}" ).click(function() {
                     $("#form${list.shop_id}").submit();
                     });
                  </script>
               </tr>
            </c:forEach>
         </tbody>
      </table>
      </div>
      <div style="display: block; text-align: center;">   
      <c:if test="${paging.startPage != 1 }">         
         <a href="search.do?sido1=<%=sido1 %>&gugun1=<%=gugun1 %>&datepick=<%=datepick %>&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
      </c:if>
      <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
         <c:choose>
            <c:when test="${p == paging.nowPage }">
               <b>${p }</b>
            </c:when>
            <c:when test="${p != paging.nowPage }">
               <a href="search.do?sido1=<%=sido1 %>&gugun1=<%=gugun1 %>&datepick=<%=datepick %>&nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
            </c:when>
         </c:choose>
      </c:forEach>
      <c:if test="${paging.endPage != paging.lastPage}">
         <a href="search.do?sido1=<%=sido1 %>&gugun1=<%=gugun1 %>&datepick=<%=datepick %>&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
      </c:if>
   </div>
   </div>
</div>
</body>
<%@ include file="Footer.jsp"%>
</html>