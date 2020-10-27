<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.seoul.thein.vo.CrudVO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색을 하자</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<h3> 검색할 아이디를 입력하세요 </h3>
<form name="form" id="form" method="post" >
ID <input type="text" name="id">
<input type="button" id="search" name="search" value="검색" >
</form>
<br><br>
<div id="tableList">
	<h1>검색된 아이디입니다.</h1>
</div>
<br><br><br>
<input type="submit" value="뒤로가기" onclick="javascript: form.action='home';"/>
</body>
<script type="text/javascript">
	$('#search').click(function(){
		doSearch();
	});


function doSearch(){
	var sendData = $('#form').serialize();
	var html = "";
	$.ajax({
		type:"POST",
		url:"searchId.do",
		data:sendData,
		success : function(result){
			console.log(result);
			if(result=="false"){
				alert('Error');
			}else{
				alert('검색성공');
				html+= "<table border='1'>";
				html+="<tr>";
				html+="<td>id</td>";
				html+="<td>비밀번호</td>";
				html+="<td>이름</td>";
				html+="</tr>";
				html+="<tr>";
				html+="<td>"+result[0].id+"</td>";
				html+="<td>"+result[0].password+"</td>";
				html+="<td>"+result[0].name+"</td>";
				html+="</tr>";
				html+="</table>";
				$("#tableList").html(html);
			}
				
			},
		error:function(data){
			alert("Error");
		}
	});
}


</script>
</html>