<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Register Page</title>
<h1>회원가입 페이지입니다 !</h1>
</head>
<body>

   <form class="view" id="registerForm">
      <div>
         <table border="1">
            <tr>
               <td><label style="display: inline"> ID : </label></td>
               <td><input type="text" name="user_id"   width="30sp"></td>
            </tr>
            <tr>
               <td><label> PASSWORD </label></td>
               <td><input type="password" name="user_pwd" width="30sp"></td>
            </tr>
            <tr>
               <td><label style="display: inline"> 이름 : </label></td>
               <td><input type="text" name="user_name"   width="30sp"></td>
            </tr>
            <tr>
               <td><label style="display: inline"> 핸드폰번호 : </label></td>
               <td><input type="text" name="user_phone"   width="30sp"></td>
            </tr>
            <tr>
               <td><label style="display: inline"> E-Mail : </label></td>
               <td><input type="text" name="user_email"   width="30sp"></td>
            </tr>
            <tr>
               <td><label style="display: inline"> SNS ID : </label></td>
               <td><input type="text" name="user_sns_id"   width="30sp"></td>
            </tr>
         </table>
          
          <br> 
         
          <input type="button" id="register" name="register" value="회원가입" disable="">

      </div>

   </form>



</body>
<script type="text/javascript">

var pwd = $("#user_pwd");
$('#register').click(function(){
	insertRegister();
});
   function insertRegister(){
         var sendData = $('#registerForm').serialize();
            $.ajax({
               type:"POST",
               url:"Register.do",
               data:sendData,
               success : function(data){
                  if(data=="false"){
                     alert('가입 Failed'+data);
                  }else{
                     alert('가입  Success!!'+data);
                     document.location.href="main.do"
                  	}  
                  },
               error:function(data){
                  alert("이미 가입된 아이디거나 \n정보를 잘못 입력하셨습니다. \n다시 시도해주세요 !");
                  $('#registerForm').each(function(){
                	 this.reset(); 
                  });
               }
            });    
   }
</script>
</html>