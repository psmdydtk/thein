<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="Header.jsp"%>
</head>
<body>
	update form
<c:if test="${!empty list}">
	<p>${list.shop_name}</p>
	<form>
<div class="row">
		<div class="col">
			<label class="label">가게명</label>
			<input type="text" name="shop_name" value="${list.shop_name}">
		</div>
		<div class="col">
			<label class="label">골목</label>
			<input type="text" name="shop_street" value="${list.shop_street}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 1</label>
			<input type="text" name="shop_menu1" value="${list.shop_menu1}">
		</div>
		<div class="col">
			<label class="label">가격</label>
			<input type="number" name="shop_menu1_price" value="${list.shop_menu1_price}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 2</label>
			<input type="text" name="shop_menu2" value="${list.shop_menu2}">
		</div>
		<div class="col">
			<label class="label">가격</label>
			<input type="number" name="shop_menu2_price" value="${list.shop_menu2_price}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 3</label>
			<input type="text" name="shop_menu3" value="${list.shop_menu3}">
		</div>
		<div class="col">
			<label class="label">가격</label>
			<input type="number" name="shop_menu3_price" value="${list.shop_menu3_price}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">위치</label>
			<input type="text" name="shop_location" value="${list.shop_location}">
		</div>
		<div class="col">
			<label class="label">이미지</label>
			<input type="text" name="shop_img" value="${list.shop_img}">
		</div>
		<div class="col">
			<label class="label">별점</label>
			<input type="number" name="shop_point" value="${list.shop_point}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">#</label>
			<input type="text" name="shop_hash1" value="${list.shop_hash1}">
		</div>
		<div class="col">
			<label class="label">#</label>
			<input type="text" name="shop_hash2" value="${list.shop_hash2}">
		</div>
		<div class="col">
			<label class="label">#</label>
			<input type="text" name="shop_hash3" value="${list.shop_hash3}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">설명</label>
			<textarea style="width: 300px; padding: 5px;" name="shop_description">${list.shop_description}</textarea>
		</div>
	</div>
	</form>
</c:if>
	
	
</body>
<script>

</script>
</html>