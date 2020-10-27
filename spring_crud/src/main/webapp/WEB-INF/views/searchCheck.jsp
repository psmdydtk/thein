<%@page import="com.seoul.thein.vo.CrudVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>타이틀이야</title>
  <link rel='stylesheet' href='/stylesheets/style.css'>
</head>
<body>
<%
List<CrudVO> list =(List<CrudVO>)request.getAttribute("list");
if(list.size()!=0){
	

%>
  <h1>**검색된 고객의 정보**</h1>
      <a>아이디 : <%=list.get(0).getId() %></a><br>
      <a>비밀번호 : <%=list.get(0).getPassword() %></a><br>
      <a>이름 : <%=list.get(0).getName() %></a>
      <%}
else{
%>
      <h1>해당 아이디는 존재하지 않습니다.</h1>
      <%
}
      %>
</body>
</html>
