<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<%@ include file="Header.jsp"%>
<script async src="//www.google-analytics.com/analytics.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="grid_menu">

<%
String datepick = request.getParameter("datepick");
String shop_id = request.getParameter("shop_id");
%>

<%@ include file="Menu.jsp"%>
<!------------------------------ main  -->
   <div class="container">
      <H3>상세 페이지</H3>
<!------------------------------ 검색     -->
<!------------------------------ list  -->
      <div class="list">
      <form>
         <H3>식당 정보</H3>
          <c:forEach items="${list }" var="list">
          		<form id="" action="reservation.do?datepick=<%=datepick %>&shop_id=<%=shop_id %>" method="POST">

                        <table border="1" style="width:500px;margin:3px;padding:10px;">
						<tr>
							<td rowspan="9" colspan="4">이미지가나올거지롱</td>
							<th colspan="3"><a href="#" id="link${list.shop_id}" style="color:blue">${list.shop_name}</a></th>
							<td rowspan="2" style="text-align: center;">
								<i class="heartClick far fa-heart" style="font-size:30px;margin-right:5px;"></i>
							</td>
						</tr>
						<tr>
							<th colspan="3">별점 :      ${list.shop_point}</th>
						</tr>
						<tr>
							<td colspan="4">골목 :      ${list.shop_street}</td>
						</tr>
						<tr>
							<td colspan="4">위치 :      ${list.shop_location}</td>
						</tr>
						<tr>
							<td colspan="4">대표메뉴 1:       ${list.shop_menu1}  가격 : ${list.shop_menu1_price}</td>
						</tr>
						<tr>
							<td colspan="4">대표메뉴 2:       ${list.shop_menu2}  가격 : ${list.shop_menu2_price}</td>
						</tr>
						<tr>
							<td colspan="4">대표메뉴 3:       ${list.shop_menu3}  가격 : ${list.shop_menu3_price}</td>
						</tr>
						<tr>
							<td colspan="4">설명 :  ${list.shop_description}</td>
						</tr>
						<tr>
							<td colspan="4">
							<input type="submit" name="#" value="검색" style="float: right">
							<input type="hidden" id="reser_user_id" value="${sessionScope.id}">
							<input type="hidden" id="reser_shop_date" value="${datepick }">
							<input type="hidden" id="reser_shop_id" value="${list.shop_id }">
							<c:if test="${sessionScope.uType eq '9999'}">
								<input type="button" value="수정" onclick="window.location.href='/thein/shopUpdate.do?shop_id=${list.shop_id}'" style="float: right;margin-right:5px;">
								<a href="/thein/shopUpdate.do?shop_id=${list.shop_id}">이동</a>
							</c:if>
							</td>
						</tr>
						</table>
                        <input type="hidden" id= "shop_id_${list.shop_id}" name="shop_id_${list.shop_id}" value = "${list.shop_id}"/>
                     </form>
          </c:forEach>
      </form>
      </div>

      
<!------------------------------ hash/button  -->
      <div class="hash">
      <form>
         <a href="" class="link">#${list.get(0).shop_hash1}</a>
         <a href="" class="link">#${list.get(0).shop_hash2}</a>
         <a href="" class="link">#${list.get(0).shop_hash3}</a>
         <input type="button" value="예약"  name="gotoReservation" id="gotoReservation">
      </form>
      </div>
<!------------------------------ keyword  -->
      <div class="keyword">
         <H3>키워드</H3>
         <a>#${keyword.get(0).kw_ajt0}  #${keyword.get(0).kw_ajt1}  #${keyword.get(0).kw_ajt2}</a>
         <a>#${keyword.get(0).kw_ajt3}  #${keyword.get(0).kw_ajt4}  #${keyword.get(0).kw_ajt5}</a>
         <a>#${keyword.get(0).kw_ajt6}  #${keyword.get(0).kw_ajt7}  #${keyword.get(0).kw_ajt8}</a>
         <a>#${keyword.get(0).kw_ajt9}  #${keyword.get(0).kw_ajt10}</a>
      </div>
   </div>
</div>
</body>
<%@ include file="Footer.jsp"%>
<script type="text/javascript">
$("#gotoReservation").click(function() {
    showPopup();
 });
function showPopup() {
    /* +$("#datepick").val() */
    window.open("goReser.do?datepick="+'<%=datepick %>'+"&shop_id="+'<%=shop_id%>', "a",
          "width=450px, height=1500px, right=150px, top=50"); 
 };
$('.heartClick').click(function() {
   $(this).toggleClass('far fas');
   if($(this).hasClass('fas')){
	   //db에 추가하는 기능 //채움 하트 클릭 시 
	   alert("뿅♥");
   }else{
	 //db에서 삭제 기능 //빈 하트 클릭 시
	   alert("ㅠㅠ");
   }
});
</script>
</html>