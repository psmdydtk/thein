<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	insert form
	<div class="row">
		<div class="col">
			<label class="label">가게명</label><input type="text" name="shop_name">
		</div>
		<div class="col">
			<label class="label">골목</label><input type="text" name="shop_street">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 1</label><input type="text" name="shop_menu1">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu1_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 2</label><input type="text" name="shop_menu2">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu2_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 3</label><input type="text" name="shop_menu3">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu3_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">위치</label><input type="text"
				name="shop_location">
		</div>
		<div class="col">
			<label class="label">이미지</label><input type="text" name="shop_img">
		</div>
		<div class="col">
			<label class="label">별점</label><input type="number" name="shop_point">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash1">
		</div>
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash2">
		</div>
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash3">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">설명</label>
			<textarea style="width: 300px; padding: 5px;" name="shop_description"></textarea>
		</div>
	</div>
</body>
</html>