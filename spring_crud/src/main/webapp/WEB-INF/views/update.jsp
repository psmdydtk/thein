<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<h1> 회원 정보 수정</h1>
<br><br>
<form id="form" name="form" method="post">
	<a> id : 	</a><input type="text" name="id" value= "<%=request.getParameter("id") %>" readonly><br>
	<a> password : 	</a><input type="text" name="password" value= "<%=request.getParameter("password") %>"><br>
	<a> 이름 : 	</a><input type="text" name="name" value= "<%=request.getParameter("name") %>"><br>
	<br><br><br><br><br>
	
	<input type="button" id="update" name="update" value="회원수정"/>
	<input type="button" id="delete" name="delete" value="삭제하기"/>
	<input type="button" id="back" name="back" value="뒤로가기" onclick="javascript: form.action='home';"/>
</form>
</body>
<script type="text/javascript">
$('#update').click(function(){
	doUpdate();
});
$('#delete').click(function(){
	doDelete();
});
function doUpdate(){
	var sendData = $('#form').serialize();
	$.ajax({
		type:"POST",
		url:"update.do",
		data:sendData,
		success : function(result){
			console.log(result);
			if(result=="false"){
				alert('Error');
			}else{
				alert('회원정보 수정 성공');
				location.href="home";
			}
			},
		error:function(data){
			alert("Error");
		}
	});
}
function doDelete(){
	var sendData = $('#form').serialize();
	$.ajax({
		type:"POST",
		url:"delete.do",
		data:sendData,
		success : function(result){
			console.log(result);
			if(result=="false"){
				alert('Error');
			}else{
				alert('회원삭제 되었습니다.');
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