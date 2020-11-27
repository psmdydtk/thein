<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<%@ include file="Header.jsp"%>
<script src="https://kit.fontawesome.com/8fed18a767.js" crossorigin="anonymous"></script>
<script async src="//www.google-analytics.com/analytics.js"></script>
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
h2 {
	font-size: 15px;
}

.star-rating {
	width: 304px;
}

.star-rating, .star-rating span {
	display: inline-block;
	height: 27px;
	overflow: hidden;
	background: url(resources/star.png) no-repeat;
}

.star-rating span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}
</style>
<%
Object suser = session.getAttribute("id");
String user = "";
if(suser!=null){
	 user = suser.toString();
}
%>
<body>
	<div class="grid_menu">
		<%
		String datepick = request.getParameter("datepick");
		String shop_id = request.getParameter("shop_id");
		String user_id = request.getParameter("user_id");
		String reser_shop_price = request.getParameter("reser_shop_price");
		%>
		<%@ include file="Menu.jsp"%>
		<!------------------------------ main  -->
		<div class="container">
			<H3>상세 페이지</H3>
			<!------------------------------ 검색     -->
			<!------------------------------ list  -->
			<div class="list">
				<H3>식당 정보</H3>
				<c:forEach items="${list }" var="list">
					<form id=""
						action="reservation.do?datepick=<%=datepick%>&shop_id=<%=shop_id%>"
						method="POST">
						<table style="margin: 3px; padding: 10px; border: 1px solid black">
							<tr>
								<td rowspan="9" colspan="4"><img height="300" width="300"
									src="${list.shop_img }"></td>
								<th colspan="3"><a href="#" id="link${list.shop_id}"
									style="color: blue; font-size: 18pt; float: left;">${list.shop_name}</a></th>
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
								<th colspan="3" style="text-align: left; font-size: 1.5em;">별점
									:
									<div class="wrap-star" style="display: inline-block;">
										<div class='star-rating'>
											<span id="star" style="width:${list.shop_point *10 }%"></span>
										</div>
									</div>
								</th>
							</tr>
							<tr>
								<td colspan="4">골목 : ${list.shop_street}</td>
							</tr>
							<tr>
								<td colspan="4">위치 : ${list.shop_location}</td>
							</tr>
							<tr>
								<td colspan="4">대표메뉴 1: ${list.shop_menu1} 가격 :
									${list.shop_menu1_price}</td>
							</tr>
							<tr>
								<td colspan="4">대표메뉴 2: ${list.shop_menu2} 가격 :
									${list.shop_menu2_price}</td>
							</tr>
							<tr>
								<td colspan="4">대표메뉴 3: ${list.shop_menu3} 가격 :
									${list.shop_menu3_price}</td>
							</tr>
							<tr>
								<td colspan="4">설명 : ${list.shop_description}</td>
							</tr>
							<tr>
								<td colspan="4"><input type="button" value="예약"
									name="gotoReservation" id="gotoReservation"
									style="float: right"> <input type="hidden"
									id="reser_user_id" value="${sessionScope.id}"> <input
									type="hidden" id="reser_shop_date" value="${datepick }">
									<input type="hidden" id="reser_shop_id"
									value="${list.shop_id }"> <input type="hidden"
									id="reser_shop_price" value="${list.shop_menu1_price }">
									<c:if test="${sessionScope.uType eq '9999'}">
										<input type="button" value="수정"
											onclick="window.location.href='/thein/shopUpdate.do?shop_id=${list.shop_id}'"
											style="float: right; margin-right: 5px;">
										<a href="/thein/shopUpdate.do?shop_id=${list.shop_id}">이동</a>
									</c:if></td>
							</tr>
						</table>
						<input type="hidden" id="shop_id_${list.shop_id}"
							name="shop_id_${list.shop_id}" value="${list.shop_id}" />

					</form>
				</c:forEach>
			</div>


			<!------------------------------ hash/button  -->
			<div class="hash">
				<form>
					<a style="font-weight: bold; font-size: 15pt; color: black"
						href="hashInsta.do?hash=${list.get(0).shop_hash1}&shop_id=${list.get(0).shop_id}"
						class="link">#${list.get(0).shop_hash1}</a> <a
						style="font-weight: bold; font-size: 15pt; color: black"
						href="hashInsta.do?hash=${list.get(0).shop_hash2}&shop_id=${list.get(0).shop_id}"
						class="link">#${list.get(0).shop_hash2}</a> <a
						style="font-weight: bold; font-size: 15pt; color: black"
						href="hashInsta.do?hash=${list.get(0).shop_hash3}&shop_id=${list.get(0).shop_id}"
						class="link">#${list.get(0).shop_hash3}</a>

				</form>
			</div>
			<!------------------------------ keyword  -->
			<div class="keyword">
				<H3>키워드</H3>
				<a>#${keyword.get(0).kw_ajt1}
					#${keyword.get(0).kw_ajt2}</a> <a>#${keyword.get(0).kw_ajt3}
					#${keyword.get(0).kw_ajt4} #${keyword.get(0).kw_ajt5}</a> <a>#${keyword.get(0).kw_ajt6}
					#${keyword.get(0).kw_ajt7} #${keyword.get(0).kw_ajt8}</a> <a>#${keyword.get(0).kw_ajt9}
					#${keyword.get(0).kw_ajt10}</a>
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
		   return true;
	   }
   }else{
	 //db에서 삭제 기능 //채움 하트 클릭 시
	 if(check !=""){
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
	 }else{
		 alert("로그인을 해야 사용 가능한 기능입니다");
	 }
   }
});

 $("#gotoReservation").click(function() {
	 if("${sessionScope.id}"==null ){
		 alert("로그인을 하시고 시도해주세요");
	 }else{
		 //alert("${sessionScope.id}");
		 showPopup();
	 }
 });
 function showPopup() {
/* +$("#datepick").val() */
window.open("goReser.do?reser_shop_date="+'<%=datepick%>'+"&reser_shop_id="+'<%=shop_id%>' + "&reser_user_id="
+ "${sessionScope.id}" + "&reser_shop_price="+ "${list[0].shop_menu1_price }", "a",				
"width=500px, height=700px, right=150px, top=50");
	}
</script>
</html>