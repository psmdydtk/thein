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
		
		###
		<c:if test="${!empty param.type}">
		<%-- <%String type=request.getParameter("type");
		if(type.equals("insert")){ %> --%>
			<c:if test="${param.type eq 'insert'}">
		insert form
		<div class="row">
			<div class="col"><label class="label">가게명</label><input type="text" name="shop_name"></div>
			<div class="col"><label class="label">골목</label><input type="text" name="shop_street"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">메뉴 1</label><input type="text" name="shop_menu1"></div>
			<div class="col"><label class="label">가격</label><input type="number" name="shop_menu1_price"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">메뉴 2</label><input type="text" name="shop_menu2"></div>
			<div class="col"><label class="label">가격</label><input type="number" name="shop_menu2_price"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">메뉴 3</label><input type="text" name="shop_menu3"></div>
			<div class="col"><label class="label">가격</label><input type="number" name="shop_menu3_price"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">위치</label><input type="text" name="shop_location"></div>
			<div class="col"><label class="label">이미지</label><input type="text" name="shop_img"></div>
			<div class="col"><label class="label">별점</label><input type="number" name="shop_point"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">#</label><input type="text" name="shop_hash1"></div>
			<div class="col"><label class="label">#</label><input type="text" name="shop_hash2"></div>
			<div class="col"><label class="label">#</label><input type="text" name="shop_hash3"></div>
		</div>
		<div class="row">
			<div class="col"><label class="label">설명</label><textarea style="width:300px;padding:5px;" name="shop_description"></textarea></div>
		</div>
			</c:if>
		000
		jsp 분리시켜
		각 기능의jsp 분리하고 여기에는 jsp 부르는 기능만

		<%-- <%}else{ %>
		&&&&
		<%} %> --%>
		</c:if>
		444
	</div>
</div>
</body>
<%@ include file="../WEB-INF/views/Footer.jsp"%>
</html>