<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<h1> 로그인</h1>

<c:choose>
	<c:when test="${empty sessionScope.id}">
	
		<div>
		<form method="post" name="form" id="form" action="login.do">
		<label> 		id : 		</label>
		<input type="text" name="id" id="id"><br>
		<a>password: </a>
		<input type="password" name="password" id="password">
		<br><br><br><br><br><br> 
		<input type="submit" value="뒤로가기" onclick="javascript: form.action='home';"/>
		<input type="button" id="login" value="LOGIN" onclick="javascript: form.action='login.do';"/>
		</form>
		</div>
	</c:when>
	<c:otherwise>
		<h1>${sessionScope.id}님 환연합니다.</h1>
	</c:otherwise>
</c:choose>

</body>
<script type="text/javascript">
	$(document).ready(function(e){
		$('#login').click(function(){

			// 입력 값 체크
			if($.trim($('#id').val()) == ''){
				alert("아이디를 입력해 주세요.");
				$('#id').focus();
				return;
			}else if($.trim($('#password').val()) == ''){
				alert("패스워드를 입력해 주세요.");
				$('#password').focus();
				return;
			}
			
			//전송
			dosubmit();
			//$('#form').submit();
		});
		
	});
	
	function dosubmit(){
		var sendData = $('#form').serialize();
		
		$.ajax({
			type:"POST",
			url:"login.do",
			data:sendData,
			success : function(data){
				if(data=="false"){
					alert('Login Failed');
				}else{
					alert('Login Success!!');
					console.log(data);
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