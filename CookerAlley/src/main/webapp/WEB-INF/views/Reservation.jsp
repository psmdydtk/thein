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
if(reser_shop_price<5000){
	reser_shop_price = 5000;
}
%>
<script type="text/javascript">
let result = <%=reser_shop_price%>;


function searchTime(){
   $.ajax({
      type : "POST",
      url : "/thein/searchTime.do?datepick="+'<%=reser_shop_date%>'+"&shop_id="+'${reservationVO.reser_shop_id }',
      data : "",
      success : function(response){
         //alert("searchTime");
         for(var i in response){
            //console.log(response[i].reser_shop_hour);
            $("input[name=reser_shop_hour]").each(function() {
               var a = $(this).val();
               if(a==response[i].reser_shop_hour){
                  $(this).attr('disabled',true);
                  //console.log(response[i].reser_shop_hour+"임마 바꾼다잉");
               }
            });
         }   
         },
      error:function(request,status,error){
          alert("error 다 ");
      }

   });
   
   changeSelect();
}
</script>

<body onload="searchTime();" style="width: 300px;">
   <div>
      <!------------------------------ 달력 -->
      <div class="container" >
         <H2>예약 페이지</H2>
         <!------------------------------ 상세예약  -->
         <form class="view" id="form" action="/thein/Reservation.do" method="POST">
            <input type="hidden" name="reser_shop_id" value="${reservationVO.reser_shop_id }">
            <input type="hidden" id="reser_user_id" name="reser_user_id" value="${sessionScope.id}">
            <input type="date" id="reser_shop_date" name="reser_shop_date" value="<%=reser_shop_date%>">
            <input type="hidden" name="reser_shop_price" value ="<%=reser_shop_price %>">
            <!------------------------------- time  -->
            <div class="container">
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
               <select name="reser_shop_person" id="reser_shop_person" onchange="changeSelect()">
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
				<script>
					function changeSelect(){
   					var person = $('#reser_shop_person').val();
   					result = result*person ;
     				$('#price').text(result);	
     				result = result/person;
					}
				</script>
               <h1>예약금</h1>
               <label>예약금은 <h2 id="price" name="price" style="display: inline"></h2>원입니다</label>
                <br>
               <br> 
               결제 금액 동의 <input type="checkbox" id="agree" name="agree">
                동의 안함<input type="checkbox" id="disAgree" name="disAgree">
               <br>
               <br> 
               <input type="submit" id="gotoPay" sname="gotoPay" value="결제하러 가기" disable="">

            </div>
         </form>    
      </div>
   </div>
   <br><br>
</body>

<script type="text/javascript">
   var button_gotoPay = document.getElementById('gotoPay');
   button_gotoPay.disabled = true;
   var radio_agree = document.getElementById('agree');
   var button_gotoPay = document.getElementById('gotoPay');
   $("#agree").change(function() {
      //alert("agree 선택!!!");
      button_gotoPay.disabled = false;
   }

   );
   $("#disAgree").change(function() {
      alert("동의해야만 결제단계로 넘어갈 수 있습니다.");
      button_gotoPay.disabled = true;
   });

	$("#reser_shop_date").change(function(){
		var changeDate = $('#reser_shop_date').val();
		//alert("값이바뀌엇다" + changeDate);
		$("input[name=reser_shop_hour]").each(function() {
           $(this).attr('disabled',false);//date초기화작업
        });
		 $.ajax({
		      type : "POST",
		      url : "/thein/searchTime.do?datepick="+changeDate+"&shop_id="+encodeURI(${reservationVO.reser_shop_id }),
		      data : "",
		      success : function(response){
		         //alert("searchTime" + response);
		         for(var i in response){
		            //console.log(response[i].reser_shop_hour);
		            $("input[name=reser_shop_hour]").each(function() {
		               var a = $(this).val();
		               if(a==response[i].reser_shop_hour){
		                  $(this).attr('disabled',true);
		                  //console.log(response[i].reser_shop_hour+"임마 바꾼다잉");
		               }
		            });
		         }   
		         },
		      error:function(request,status,error){
		          alert("error 다 ");
		      }
		   });
		});
   
   function insertReservation(){
      var sendData = $('#form').serialize();
      //alert(sendData);
      $.ajax({
         type:"POST",
         url:"Reservation.do",
         data:sendData,
         success : function(data){
            if(data=="false"){
               alert('예약 Failed');
            }else{
               alert('예약  Success!!');
              // console.log(data);
            }
               
            },
         error:function(data){
            alert("예약실패");
         }
      });
   }
   
   function checkDate(){
	   var checkDatas = $('#reser_user_id').val();
	   var checkTimes = $('input[name="reser_shop_hour"]:checked').val();
	   if(checkDatas==""){
		   alert("로그인을 해주세요");
		   return false;
	   }else if (checkTimes==null) {
		    alert("시간을 선택해 주세요");
			return false;
			}
	   else{
		   //alert(checkDatas + "승인이된거야" + checkTimes);
		   return true;
	   }
	   
   	}
   
   $('#form').on('submit',function(event){
      //this.submit();
      event.preventDefault();
      if(checkDate()){
    	  this.submit(); 
       //insertReservation();
      //alert("예약이 완료되었습니다 !");
     // window.close(); 
      }
   })
</script>
</html>