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
			<label class="label">골목</label><input type="text" name="shop_street" id="shop_street">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 1</label><input type="text" name="shop_menu1" id="shop_menu1">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu1_price" id="shop_menu1_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 2</label><input type="text" name="shop_menu2" id="shop_menu2">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu2_price" id="shop_menu2_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">메뉴 3</label><input type="text" name="shop_menu3" id="shop_menu3">
		</div>
		<div class="col">
			<label class="label">가격</label><input type="number"
				name="shop_menu3_price" id="shop_menu3_price">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">위치</label><input type="text"
				name="shop_location" id="shop_location">
		</div>
		<div class="col">
			<label class="label">이미지</label><input type="text" name="shop_img" id="shop_img">
		</div>
		<div class="col">
			<label class="label">별점</label><input type="number" name="shop_point" id="shop_point">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash1" id="shop_hash1">
		</div>
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash2" id="shop_hash2">
		</div>
		<div class="col">
			<label class="label">#</label><input type="text" name="shop_hash3" id="shop_hash3">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="label">설명</label>
			<textarea style="width: 300px; padding: 5px;" name="shop_description" id="shop_description"></textarea>
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
	doInsert();
});

	function doInsert(){
		var shop_name = $('#shop_name').val();
		var shop_street = $('#shop_street').val();
		var shop_menu1= $('#shop_menu1').val();
		var shop_menu1_price= $('#shop_menu1_price').val();
		var shop_menu2 =  $('#shop_menu2').val();
		var shop_menu2_price =  $('#shop_menu2_price').val();
		var shop_menu3 =  $('#shop_menu3').val();
		var shop_menu3_price =  $('#shop_menu3_price').val();
		var shop_location =  $('#shop_location').val();
		var shop_description =  $('#shop_description').val();
		var shop_img =  $('#shop_img').val();
		var shop_point =  $('#shop_point').val();
		var shop_hash1 =  $('#shop_hash1').val();
		var shop_hash2 =  $('#shop_hash2').val();
		var shop_hash3 =  $('#shop_hash3').val();
		   $.ajax({
		      type : "POST",
		      url : "insert.do",
		      data : {"shop_name":shop_name,"shop_street":shop_street,"shop_menu1":shop_menu1,
		    	  "shop_menu1_price":shop_menu1_price,"shop_menu2":shop_menu2,"shop_menu2_price":shop_menu2_price,
		    	  "shop_menu3":shop_menu3,"shop_location":shop_location,"shop_description":shop_description,
		    	  "shop_img":shop_img,"shop_point":shop_point,"shop_hash1":shop_hash1,"shop_hash2":shop_hash2,
		    	  "shop_hash3":shop_hash3},//{"shop_name":$('#shop_name').val()},//$("#form").serialize(),
		      success : function(result){
		            if(result =="1"){
		               //session?
		               alert("저장 성공");
		               $('#form')[0].reset();
		            }else{
		               alert("저장 실패");
		               $('#form')[0].reset();
		            }
		         }
		     /*  error : function(result){
		             alert("error");
		      } */
		   });
		}


</script>
</html>