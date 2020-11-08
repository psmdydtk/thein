<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%
String datepick = request.getParameter("datepick");
String reser_shop_id = request.getParameter("shop_id");
%>
   <div>

      <!------------------------------ 달력 -->
      <div class="container" style="width: 400%;">
         <H2>예약 달력 페이지입니다</H2>
         <!------------------------------ 상세예약  -->
         <form class="view" id="form" action="/thein/Reservation.do" method="POST">

            <input type="date" name="reser_shop_date" value="<%=datepick%>">

            <input type="hidden" id="reser_shop_id" value="${list.shop_id }">
            
            <!------------------------------- time  -->
            <div class="container" style="width: 600px;">
               <h1>시간선택</h1>
               <table>
                  <tr>
                     <td>12:00<input type="radio" name="reser_shop_hour" value="12"></td>
                     <td>13:00<input type="radio" name="reser_shop_hour" value="13"></td>
                     <td>14:00<input type="radio" name="reser_shop_hour" value="14"></td>
                  </tr>
                  <tr>
                     <td>15:00<input type="radio" name="reser_shop_hour" value="15"></td>
                     <td>16:00<input type="radio" name="reser_shop_hour" value="16"></td>
                     <td>17:00<input type="radio" name="reser_shop_hour" value="17"></td>
                  </tr>
                  <tr>
                     <td>18:00<input type="radio" name="reser_shop_hour" value="18"></td>
                     <td>19:00<input type="radio" name="reser_shop_hour" value="19"></td>
                     <td>20:00<input type="radio" name="reser_shop_hour" value="20"></td>
                  </tr>

               </table>

               <h1>인원선택</h1>
               <select name="reser_shop_person">
                  <optgroup label="인원">
                     <option value=1>1명</option>
                     <option value=2>2명</option>
                     <option value=3>3명</option>
                     <option value=4>4명</option>
                     <option value=5>5명</option>
                     <option value=6>6명</option>
                     <option value=7>7명</option>
                     <option value=8>8명</option>
                  </optgroup>
               </select>

               <h1>예약금</h1>
               <label>메인메뉴가격 /2</label><br> 결제 금액 동의 <input type="checkbox"
                  id="agree" name="agree"> 동의 안함<input type="checkbox"
                  id="disAgree" name="disAgree"> <br>
               <br> <input type="submit" id="gotoPay" sname="gotoPay"
                  value="결제하러 가기" disable="">

            </div>
            </form>
      </div>
      
   </div>
   
   <br><br>
   <%--여긴아닙니다 
   <c:if test="${!empty list}">
      <table border="1">
         <tr>
            <td>가게id</td>
            <td>날짜</td>
            <td>시간</td>
         </tr>

         <c:forEach items="${list}" var="ob">
            <tr>
               <td>${ob.name}</td>  <!-- getter가 있어야함 -->
               <td>${ob.age}</td>
               <td>${ob.hobby}</td>
            </tr>
            </c:forEach>
      </table>

      
   </c:if> --%>

</body>
<script>
   var button_gotoPay = document.getElementById('gotoPay');
   button_gotoPay.disabled = true;
</script>
<script type="text/javascript">
   var radio_agree = document.getElementById('agree');
   var button_gotoPay = document.getElementById('gotoPay');
   $("#agree").change(function() {
      alert("agree 선택!!!");
      button_gotoPay.disabled = false;
   }

   );
   $("#disAgree").change(function() {
      alert("동의해야만 결제단계로 넘어갈 수 있습니다.");
      button_gotoPay.disabled = true;
   });
</script>
<script type="text/javascript">
   
   
   function insertReservation(){
      var sendData = $('#form').serialize();
      
      $.ajax({
         type:"POST",
         url:"Reservation.do",
         data:sendData,
         success : function(data){
            if(data=="false"){
               alert('예약 Failed');
            }else{
               alert('예약  Success!!');
               console.log(data);
               location.href="detail";
            }
               
            },
         error:function(data){
            alert("Error");
         }
      });
   }
</script>


</html>