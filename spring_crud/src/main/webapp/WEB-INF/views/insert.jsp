<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<h3>** 회원등록 **</h3>
<form id="form" name="form"  method="post">
아이디 : <input type="text" name="id" value=""><br>
패스워드 : <input type="password" name="password" value=""><br>
고객명 : <input type="text" name="name" value=""><br>
<input type="button" id="insert" name="insert" value="등록" >
<input type="reset" value="재작성">
</form>
</body>
<script type="text/javascript">
	$('#insert').click(function(){
		doInsert();
	});
	function doInsert(){
		var sendData = $('#form').serialize();
		$.ajax({
			type:"POST",
			url:"insert.do",
			data:sendData,
			success : function(result){
				console.log(result);
				if(result=="false"){
					alert('Error');
				}else{
					alert('회원가입 성공');
					location.href="home";
					
				}
					
				},
			error:function(data){
				alert("Error");
			}
		});
	}
	
</script>
</html>