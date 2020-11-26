 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!--------------------------------- Header -->
<%@ include file="Header.jsp"%>
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/8fed18a767.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="grid_menu">
<%@ include file="Menu.jsp"%>
<!------------------------------ main  -->
   <div class="container">
      <H3>밥상기록</H3>
<!------------------------------ info list  -->
         <div>
            <i class="fas fa-grin-hearts"> 찜 목록</i>
            <div style="overflow:auto; width:800px; height:150px;border:1px solid pink">
               <ul>
                  <c:forEach items="${heartList}" var="list">
                  <form id="form${list.shop_id}" action="detail.do?datepick=&shop_id=${list.shop_id}" method="POST">
                     <li>
                        <a href="#" id="link${list.shop_id}">${list.shop_name}</a>
                     </li>
                     <script type="text/javascript">
                        $( "#link${list.shop_id}" ).click(function() {
                        alert("${list.shop_id}");
                        $("#form${list.shop_id}").submit();
                        });
                     </script>
                     <input type="hidden" id= "shop_id_${list.shop_id}" name="shop_id_${list.shop_id}" value = "${list.shop_id}"/>
                  </form>
                 </c:forEach>
                </ul>
            </div>
               
         </div>
         <br>
         <div>
            <i class="far fa-calendar-alt"> 예약 목록</i>
            <div style="overflow:auto; width:800px; height:150px;border:1px solid pink">
            <c:choose>
               <c:when test="${!empty reservList}">
               <ul>
                  <c:forEach items="${reservList}" var="reser">
                  <li><a>${reser.shop_name}</a>
                  <a>${reser.reser_shop_date} ${reser.reser_shop_hour}시</a>
                  <a>${reser.reser_shop_person}명</a></li>
                  </c:forEach>
               </ul>
                  
               </c:when>
            </c:choose>
            </div>
               
         </div>
   </div>
</div>
</body>
<%@ include file="Footer.jsp"%>

</html>