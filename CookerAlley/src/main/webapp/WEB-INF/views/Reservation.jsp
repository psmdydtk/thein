<%@page import="java.sql.Date"%>
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
<%
String reser_shop_date = request.getParameter("reser_shop_date");
String reser_shop_id = request.getParameter("shop_id");
int reser_shop_price =Integer.parseInt(request.getParameter("reser_shop_price"))/2;

%>
<script type="text/javascript">

function searchTime(){
   $.ajax({
      type : "POST",
      url : "/thein/searchTime.do?datepick="+'<%=reser_shop_date%>'+"&shop_id="+'${reservationVO.reser_shop_id}',
      data : "",
      success : function(response){
         for(var i in response){
            //console.log(response[i].reser_shop_hour);
            $("input[name=reser_shop_hour]").each(function() {
               var a = $(this).val();
               if(a==response[i].reser_shop_hour){
                  $(this).attr('disabled',true);
                  console.log(response[i].reser_shop_hour+"임마 바꾼다잉");
               }
               
            });
            
         }   
         },
      error:function(request,status,error){
          alert("error 다 ");
      }

   });
}
</script>

<body onload="searchTime();">


   <div>

      <!------------------------------ 달력 -->
      <div class="container" style="width: 400%;">
         <H2>예약 달력 페이지입니다</H2>
         <!------------------------------ 상세예약  -->
         <form class="view" id="form" action="/thein/Reservation.do" method="POST">

            </select> 
            <input type="hidden" name="reser_shop_id" value="${reservationVO.reser_shop_id }">
            <input type="hidden" name="reser_user_id" value="${reservationVO.reser_user_id }">
             <input type="date" name="reser_shop_date" value="<%=reser_shop_date%>">
            <input type="hidden" name="reser_shop_price" value ="<%=reser_shop_price %>">
         <%--    <input type="hidden" name="reser_shop_regi" value="<%=time1 %>"> --%>
            
            
            
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
               <label>예약금은 <h2 style="display: inline"><%=reser_shop_price %></h2>원입니다</label>
                <br>
               <br> 
               결제 금액 동의 <input type="checkbox" id="agree" name="agree">
                동의 안함<input type="checkbox" id="disAgree" name="disAgree">
               <br>
               <br> 
               <input type="submit" id="gotoPay" sname="gotoPay" value="결제하러 가기" disable="">

            </div>
      </div>
      </form>
   </div>
   
   <br><br>

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