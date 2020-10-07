<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="<%=basePath%>" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
    <!-- google font -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
    <link href="http://cdn.bootstrapmb.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/ionicons.css" rel="stylesheet" type="text/css">
	<link href="assets/css/simple-line-icons.css" rel="stylesheet" type="text/css">
	<link href="assets/css/themify-icons.css" rel="stylesheet" type="text/css">
    <link href="assets/css/jquery.mCustomScrollbar.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
	<link href="assets/css/header.css" rel="stylesheet">
	<link href="assets/css/menu.css" rel="stylesheet">
	<link href="assets/css/index.css" rel="stylesheet">
    <link href="assets/css/responsive.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#getphonecode').click(function(){
         $.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/CodePhoneDispatcher/Login",
             data: {tel:$("#tel").val()},
             success: function(data){
            	 obj = JSON.parse(data);
            	 alert(obj.message);
            	 document.getElementById("code_two").value=obj.code;
                      }
         });
    });
    $('#getmailcode').click(function(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/CodeMailDispatcher/Login",
            data: {mail:$("#mail").val()},
            success: function(data){
           	 obj = JSON.parse(data);
           	 alert(obj.message);
           	document.getElementById("code_two").value=obj.code;
                     }
        });
   });
});
</script>
<script>
	function tellogin(){
		document.getElementById("signin").disabled=false;
		document.getElementById("tellogin").style.display="inline";
		document.getElementById("codeform").style.display="inline";
		document.getElementById("passwordlogin").style.display="none";
	 	document.getElementById("maillogin").style.display="none";
	 	document.getElementById("getmailcode").style.display="none";
	 	document.getElementById("getphonecode").style.display="inline";
	}
	function maillogin(){
		document.getElementById("signin").disabled=false;
		document.getElementById("tellogin").style.display="none";
		document.getElementById("codeform").style.display="inline";
		document.getElementById("passwordlogin").style.display="none";
	 	document.getElementById("maillogin").style.display="inline";
	 	document.getElementById("getmailcode").style.display="inline";
	 	document.getElementById("getphonecode").style.display="none";
	}
	function passwordlogin(){
		document.getElementById("signin").disabled=false;
		document.getElementById("tellogin").style.display="none";
		document.getElementById("codeform").style.display="none";
		document.getElementById("passwordlogin").style.display="inline";
	 	document.getElementById("maillogin").style.display="none";
	}
</script>
<script>
	function signcheck(){
		document.getElementById("signin").disabled=true;
		
	}
</script>
<script type="text/javascript">
function check(){
	if(document.getElementById("code_two").value==document.getElementById("code").value){
		document.getElementById("signin").disabled=false;
	}
}
</script>
</head>

<body class="bg_darck">
    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="logo">
                    <a href="#">
                        <strong class="logo_icon">
                            <img alt="" src="assets/images/small-logo.png">
                        </strong>
                        <span class="logo-default">
                            <img alt="" src="assets/images/small-logo.png">
                        </span>
                    </a>
                </div>
                <div class="login-form">
                    <form action="${pageContext.request.contextPath}/user/Login" method="post">
                    	<div id="passwordlogin">
                        <div class="form-group" >
                            <label>Username</label>
                            <input type="text" class="form-control" name="username" placeholder="Username"  >
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Password"  > 
                        </div>
                        </div>
                         
                        <div class="form-group" id="tellogin" style="display: none;">
                            <label>Tel</label>
                            <input type="text" class="form-control" name="tel" id="tel" placeholder="Tel"   onblur="signcheck()"><br />
                        </div>
                        <div class="form-group" id="maillogin" style="display: none;">
                            <label>Mail</label>
                            <input type="email" class="form-control" name="mail" placeholder="Mail"   onblur="signcheck()"><br />
                        </div>
                        <div class="form-group" id="codeform" style="display: none;">
                            <label>Code</label>
                            <input type="text" name="code_two" id="code_two" style="display:none">
                            <div id="" style="display: flex;">
                            <input type="text" id="code" style="width: 75%;" name="code" class="form-control" placeholder="code" onblur="check()" >
                            <button  class="form-control" id="getphonecode" style="width: 25%;" onclick="settime(this)">获取验证码</button>
                            <button  class="form-control" id="getmailcode" style="width: 25%;display: none;" onclick="settime(this)">获取验证码</button>	
                            </div>
                        </div>
                        <button type="submit"  class="btn btn-success btn-flat m-b-30 m-t-30" id="signin">Sign in</button>
                        <div class="social-login-content">
                            <div class="social-button">
                                <button type="button" class="btn social btn-primary btn-flat btn-addon mt-2" onclick="tellogin()">
                                    <i class="fa fa-phone"></i>Sign in with Tel</button>
                                <button type="button" class="btn social twitter btn-flat btn-addon mt-2" onclick="maillogin()">
                                    <i class="fa fa-envelope"></i>Sign in with Mail</button>
                                <button type="button" class="btn social facebook btn-flat btn-addon mt-2" onclick="passwordlogin()">
                                    <i class="fa fa-address-book"></i>Sign in with User and Pssword</button>
                            </div>
                        </div>
                        
                   </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="assets/js/popper.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootstrapmb.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/custom.js" type="text/javascript"></script>
</body>
 <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript"> 
var wait = 60;
    document.getElementById("getmailcode").onclick = function() {
        time(this);
    }
    
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.innerHTML = "获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.innerHTML = wait ;
            wait--;
            setTimeout(function() {
                time(o)
            }, 1000)
        }
    }
</script>
<script type="text/javascript"> 
var wait = 60;
    document.getElementById("getphonecode").onclick = function() {
        time(this);
    }
    
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.innerHTML = "获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.innerHTML = wait ;
            wait--;
            setTimeout(function() {
                time(o)
            }, 1000)
        }
    }
</script>
</html>