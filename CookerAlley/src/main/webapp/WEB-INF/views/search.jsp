<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/8fed18a767.js" crossorigin="anonymous"></script>
<!-- bootstrap -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 --><style>
	div[class^="col"]{padding:5px;}
</style>
<%@ include file="Header.jsp"%>
<script async src="//www.google-analytics.com/analytics.js"></script>
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 -->
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<!-- 별점표시하기위한스타일 -->
<style>
h2 {font-size:15px;}
.star-rating {width:304px; }
.star-rating,.star-rating span {display:inline-block; height:27px; overflow:hidden; background:url(resources/star.png)no-repeat;  }
.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>

<%
String sido1=request.getParameter("sido1");
String gugun1 = request.getParameter("gugun1");
String datepick = request.getParameter("datepick");
Object suser = session.getAttribute("id");
String user = "";
if(suser!=null){
	 user = suser.toString();
}
%>
  <script type="text/javascript">
$('document').ready(function() {
 var area0 = ["시/도 선택","서울특별시","경기도","인천광역시","대전광역시","강원도","충청남도","전라남도","경상북도","경상남도","제주도"];
  var area1 = [" ","마포구","노원구","도봉구","강동구","성동구","서대문구","동대문구","용산구","성북구","광진구","중구"];
   var area2 = ["군포시","부천시","수원시","평택시"];
   var area3 = ["중구","서구"];
   var area4 = ["동구"];
   var area5 = ["서산"];
   var area6 = ["여수"];
   var area7 = ["원주시"];
   var area8 = ["포항시"];
   var area9 = ["제주시"];

 // 시/도 선택 박스 초기화

 $("select[name^=sido]").each(function() {
  $selsido = $(this);
  $.each(eval(area0), function() {
   $selsido.append("<option value='"+this+"'>"+this+"</option>");
  });
  $selsido.next().append("<option value=''>구/군 선택</option>");
 });


 // 시/도 선택시 구/군 설정

 $("select[name^=sido]").change(function() {
  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
  var $gugun = $(this).next(); // 선택영역 군구 객체
  $("option",$gugun).remove(); // 구군 초기화

  if(area == "area0")
   $gugun.append("<option value='0'>구/군 선택</option>");
  else {
   $.each(eval(area), function() {
    $gugun.append("<option value='"+this+"'>"+this+"</option>");
   });
  }
 });


});
</script>
<script type="text/javascript">
/* onload시 초기 시도,군,날짜 셋팅 */
function doFirst(){
	$("select[name=sido1]").val("<%=sido1%>");
	$("select[name=gugun1]").val("<%=sido1%>");
	$("input[name=datepick]").val("<%=datepick%>");
}
</script>
<body onload="doFirst();">
<div class="grid_menu">
<%@ include file="Menu.jsp"%>

<!------------------------------ main  -->
   <div class="container">
      <H3>리스트 페이지</H3>
<!------------------------------ 검색  -->
	<div style="display: inline-block; width: 100%">
      <form class="search" action="/thein/search.do" method="GET">
         <div style="display: inline-block;">
        	 <label for="location">위치 : </label>
         	 <select name="sido1" id="sido1"></select>
        	 <select name="gugun1" id="gugun1" ></select>
        	 <input type="date" name="datepick" id="datepick">
         </div>
         <div style="display: inline-block;">
      		<input type="image" value="" src="./resources/search.png" name="submit" value="submit" height="20" width="20">
      	</div>
      </form>
     </div>
<!------------------------------ list  -->
      <div class="list" >
      <table data-text-content="true">
         <tbody>
            <tr><th>검색 결과</th></tr>
            <tr></tr>
            <c:forEach items="${viewAll }" var="list">
               <tr>
                  <td>
                     <form id="form${list.shop_id}" action="detail.do?datepick=<%=datepick %>&shop_id=${list.shop_id}" method="POST">
                        <table style="width:900px;margin:3px;padding:10px;border:1px solid black;">
						<tr>
							<td rowspan="6" style="width:300px"><img height="300" width="300" src= "${list.shop_img }"></td>
							<th colspan="3" style="text-align: left;">
								
							<a href="#" id="link${list.shop_id}" style="color:black;font-size:1.8em;">${list.shop_name}</a></th>
							<c:choose>
								<c:when test="${list.ones_id eq null}">
									<td rowspan="2" style="text-align: right;"><i class="heartClick far fa-heart" style="font-size:40px;margin-right:5px;" value="${list.shop_id }"></i></td>
								</c:when>
								<c:otherwise>
									<td rowspan="2" style="text-align: right;"><i class="heartClick fa-heart fas" style="font-size:40px;margin-right:5px;" value="${list.shop_id }"></i></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th colspan="3" style="text-align: left;font-size:1.2em;">별점 :     
								<div class="wrap-star" style="display: inline-block;">
								    <div class='star-rating'>
								        <span id="star"style ="width:${list.shop_point *10 }%"></span>
								    </div>
								</div>
							</th>
							
								
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
							<input type="hidden" name="#" value="검색" style="float: right">
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
<script type="text/javascript">
$('.heartClick').click(function() {
	var shop_id= $(this).attr('value');
	 var check = "<%=user%>";
   $(this).toggleClass('far fas');
   if($(this).hasClass('fas')){
	   //db에 추가하는 기능 //빈 하트 클릭 시 
	  
	   if(check !=""){
		    $.ajax({
			      type : "POST",
			      url : "/thein/insertHeart.do",
			      data : "ones_shop_id="+shop_id+"&ones_user_id="+check,
			      success : function(response){
			         if(response){
			        	 alert("찜목록에 추가되었습니다.");
			         }else{
			        	 alert("찜목록 등록을 실패하였습니다." + response);
			         }
			         },
			      error:function(request,status,error){
			          alert("error 발생 ");
			      }
			   }); 
	   }else{
		   alert("로그인을 해야 사용 가능한 기능입니다.");
	   }
   }else{
	 //db에서 삭제 기능 //채움 하트 클릭 시
	 $.ajax({
			      type : "POST",
			      url : "/thein/deleteHeart.do",
			      data : "ones_shop_id="+shop_id+"&ones_user_id="+check,
			      success : function(response){
			         if(response){
			        	 alert("찜목록에서 삭제되었습니다.");
			         }else{
			        	 alert("찜목록 삭제를 실패하였습니다." + response);
			         }
			         },
			      error:function(request,status,error){
			          alert("error 발생 ");
			      }
			   }); 
	   
   }
});
</script>
</html>