<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>晓园</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cleartype" content="on">
    <meta name="MobileOptimized" content="320">
    <meta name="HandheldFriendly" content="True">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=basePath%>website/scripts/layer-v2.4/layer/skin/layer.css">

    <script src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>    
    <script src="<%=basePath%>website/scripts/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
 	<script src="<%=basePath%>website/user/js/login.js"></script>
</head>
<body>

<main>
   
    <div>         
        <section class="box">
            <div class="login-div">
                <div class="login-tab login-selected" id="login-tab">登录</div>
              	<input type="hidden" id="basePath" value="<%=basePath%>">
            </div>
            <div class="login-info" id="login-div">            	
                <form class="form-horizontal" onsubmit="return false" >
                    <div>
                        <input type="text" id="loginName" class="form-control" placeholder="手机号" maxlength="11" name="loginName" required>
                    </div>
                    <div>
                        <input type="password" id="loginPassword" class="form-control" name="loginPassword" placeholder="密码" required>
                    </div>
                                        
                    <div id="validate-tip" class="validate-tip"style="padding-bottom:1rem;border:1px solid red;display:none;">					
					  	<span id="validate-info"></span>
					</div>
               
                    <div class="remember" style="height: 40px;margin-top:5px;">
                        <label>
                            <input type="checkbox" id="remember">记住
                        </label>
                        <a class="forget-pwd" href="<%=basePath%>user/forgetPwdPage.do">忘记密码</a>
                    </div>
                    <div class="login-btn-div" style="padding-bottom: 2rem;">
                        <button class="btn btn-login"  id="loginBtn" type="button" data-toggle="tooltip" data-placement="bottom">登录</button>
                    </div>                           
                </form>
               
            </div>
            
            
           
            
        </section>
     
    </div>

</main>

</body>

</html>