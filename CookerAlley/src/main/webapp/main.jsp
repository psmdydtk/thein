<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@page import="com.project.thein.vo.CookerVO"%> --%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!----------------------------- Header  -->
<head>
<%@ include file="../WEB-INF/views/Header.jsp"%>

<meta charset="UTF-8">
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<title>Insert title here</title>
</head>
   <script type="text/javascript">
$('document').ready(function() {
 var area0 = ["시/도 선택","서울특별시","경기도","인천광역시","대전광역시","강원도","충청남도","전라남도","경상북도","경상남도","제주도"];
  var area1 = ["마포구","노원구","도봉구","강동구","성동구","서대문구","동대문구","용산구","성북구","광진구","중구"];
   var area2 = ["군포시","부천시","수원시","평택시"];
   var area3 = ["중구","서구"];
   var area4 = ["동구"];
   var area5 = ["서산"];
   var area6 = ["여수"];
   var area7 = ["원주시"];
   var area8 = ["포항시"];
   var area9 = ["제주시"];

 

 // 시/도 선택 박스 초기화

 $("select[name^=sido]").each(function() {
  $selsido = $(this);
  $.each(eval(area0), function() {
   $selsido.append("<option value='"+this+"'>"+this+"</option>");
  });
  $selsido.next().append("<option value=''>구/군 선택</option>");
 });

 

 // 시/도 선택시 구/군 설정

 $("select[name^=sido]").change(function() {
  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
  var $gugun = $(this).next(); // 선택영역 군구 객체
  $("option",$gugun).remove(); // 구군 초기화

  if(area == "area0")
   $gugun.append("<option value='0'>구/군 선택</option>");
  else {
   $.each(eval(area), function() {
    $gugun.append("<option value='"+this+"'>"+this+"</option>");
   });
  }
 });


});
</script>
<body>
<!------------------------------ main  -->
   <div class="main">
      <H3>메인 페이지</H3>
<!------------------------------ 검색  -->
      <form class="search" action="/thein/search.do" method="GET">
         <label for="location">위치 : </label>
         <select name="sido1" id="sido1"></select>
         <select name="gugun1" id="gugun1" ></select>
         <input type="date" name="datepick">
         <input type="submit" value="검색">
      </form>
<!------------------------------- instar  -->
      <div class="instar">
         <h3>INSTAR 부분 (3x3)</h3>
         <div class="grid_instar">
            <div style="border:1px solid red;">1</div>
            <div style="border:1px solid red;">2</div>
            <div style="border:1px solid red;">3</div>
            
            <div style="border:1px solid red;">4</div>
            <div style="border:1px solid red;">5</div>
            <div style="border:1px solid red;">6</div>
            
            <div style="border:1px solid red;">7</div>
            <div style="border:1px solid red;">8</div>
            <div style="border:1px solid red;">9</div>
         </div>
      </div>
<!------------------------------- Footer  -->
   </div>






</body>

</html>