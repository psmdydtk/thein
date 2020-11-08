<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<meta charset="UTF-8">
<%@ include file="Header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
<%@ include file="Menu.jsp"%>
<!------------------------------ main  -->
	<div class="container">
		<H3>식당관리페이지</H3>
	insert form
	<form id="form" name="form">
	<input type="text" name="shop_name1">
	<div class="row">
		<div class="col">
			<label class="label">가게명</label><input type="text" id="shop_name" name="shop_name" >
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
	<input type="button" id="submit" value="확인용">
	</form>
	</div>
</div>
</body>
<%@ include file="Footer.jsp"%>
<script>
$('#submit').click(function(){
	var sendData = $("#form").serialize();
	alert(sendData +"gg");
	console.log(sendData);
	//doInsert();
});

function doInsert(){
	$.ajax({
		type : "POST",
		url : "/thein/insert.do",
		data : $('#form').serialize(),
		success : function(response){
				if(response =="1"){
					//session?
					alert("저장 성공");
					$('#form')[0].reset();
				}else{
					alert("저장 실패");
					$('#form')[0].reset();
				}
			},
		//error:function(request,status,error){
		//	    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}

	});
}
</script>
</html>