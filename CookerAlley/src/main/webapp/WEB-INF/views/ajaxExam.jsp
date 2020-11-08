<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script async src="//www.google-analytics.com/analytics.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Insert title here</title>
<%
String reser_shop_id = request.getParameter("reser_shop_id");
String reser_shop_date = request.getParameter("reser_shop_date");
String reser_user_id = request.getParameter("reser_user_id");
%>
</head>
<body >
<a>뭐좀보여주세요</a>
<form id="form">
<input type="button" id="search" value="버튼이다">
<a>뭔가잇긴잇어 ㅎㅇ</a>
</form>
<div id="tableList">
뭐좀그려봐라
</div>
<script type="text/javascript">
	$('#search').click(function(){
		doSearch();
	});


function doSearch(){
	var reser_shop_id = '<%= reser_shop_id%>';
	var reser_user_id = '<%= reser_user_id%>';
	var reser_shop_date = '<%= reser_shop_date%>';
	var html = "";
	$.ajax({
		type:"POST",
		url:"reservation.do",
		data:{"reser_shop_id":reser_shop_id,"reser,user_id":reser_user_id,"reser_shop_date":reser_shop_date},
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
				html+="<td>"+result"</td>";
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


</body>
</html>