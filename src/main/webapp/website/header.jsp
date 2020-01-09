<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html><html>
<head>
    <meta charset="utf-8">
    <title>晓园</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cleartype" content="on">
    <meta name="MobileOptimized" content="320">
    <meta name="HandheldFriendly" content="True">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta name = "format-detection" content = "telephone=no">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>website/index.css" />
    <script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/scripts/bootstrap/js/bootstrap.js"></script>
	
	
	<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	
	<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=basePath%>website/common/js/html5.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="<%=basePath%>website/common/js/jquery.tmpl.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/operaCookie.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/index.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/navigation.js"></script>
	<script src="http://static.geetest.com/static/tools/gt.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/index.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/user/js/login.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>website/user/js/sendCheckCode.js"></script>
	
<style>
	#mask {
            display: none;
            position: fixed;
            text-align: center;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            overflow: auto;
            z-index:99;
        }
        #popup-captcha-mobile {
            position: fixed;
            display: none;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
            z-index: 9999;
        }
    </style>
</head>
<body>


<div id="header" class="owl-header nav">
    <div class="wrap-header">
        <div class="logo">
            <a href="" class="owl-logo-img"></a>
        </div>

        <ul id="menu" class="header-link">
            <li><a href="<%=basePath%>">首页</a></li>
          	<li id="userCenter"></li>
            <li class="login-li" style="padding-right: 0; "><a data-toggle="modal" data-target="#loginModal">登录</a></li>
          	<li id="userInfoLi" style="display:none;">
            	<div class="dropdown" style="cursor:pointer;">
				  <div id="dLabelxx"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   	<span id="userAccount"></span>
				   	<span class="caret"></span>
				  </div>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabelxx" style="position:relative;min-width: 100px; ">
				   	<%-- <li><a  href="<%=basePath %>user/accountPage.do">个人中心</a></li>	 --%>			  
				    <li id="loginout">退出</li>
				  </ul>
				</div>   
            </li> 
            
            <li id="signupLi" style="display:block">
            	<div class="dropdown" style="cursor:pointer;">
				  <div id="dLabel"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   	<span>申请入驻</span>
				   	<span class="caret"></span>
				  </div>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" style="position:relative;min-width: 100px; ">
				   	<li><a data-toggle="modal" data-target="#signModal">个人申请</a></li>
				   	<li><a data-toggle="modal" data-target="#signSchoolModal">学校入驻</a></li>
				   	<li><a data-toggle="modal" data-target="#signOrgModal">机构入驻</a></li>		  				  
				  </ul>
				</div>   
            </li>
            
                       
        </ul>

    </div>
</div>

<!-- Modal -->
<div class="modal" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">登录</h4>
      </div>
      <div class="modal-body">
        	<!--内容体  -->
        	
        	<form class="form-horizontal" role="form" id="userLogin">
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control input-info" type="text" id="loginName" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control input-info" type="password" id="loginPassword" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			      <button type="button" class="btn loginBtn input-info" id="loginBtn">登录</button>
			    </div>
			  </div>
			   <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			    	<div class="forget-div">
			    		<a class="password-forget" id="resetPsd">忘记密码</a>
			    	</div>
			    </div>
			  </div>
			</form>
        	
      </div>
      <!-- <div class="modal-footer">       
        <button type="button" class="btn btn-primary">登录</button>
      </div> -->
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal" id="resetPsdModal" tabindex="-1" role="dialog" aria-labelledby="resetModelLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="resetModelLabel">重置密码</h4>
      </div>
      <div class="modal-body">
        	<!--内容体  -->
        	
        	<form class="form-horizontal" role="form">
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control input-info" type="text" id="phoneNumReset" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">	
			    <div class="col-sm-12">
				    <div class="forget-div">
				     	<input class="form-control valicode-info" type="text" id="validCodeReset" placeholder="验证码">
				      	<input type="button" class="btn loginBtn valicode-btn" sendCheckCode="sendCheckCode" checkCodeType="resetpwd" value="获取验证码"></input>
				    </div>
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control input-info" type="password" id="passwordReset" placeholder="新密码">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			      <button type="button" class="btn loginBtn input-info" id="reset">提交</button>
			    </div>
			  </div>
			</form>
        	
      </div>
      <!-- <div class="modal-footer">       
        <button type="button" class="btn btn-primary">登录</button>
      </div> -->
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">注册</h4>
      </div>
      <div class="modal-body">
        	<!--内容体  -->
        	<form class="form-horizontal" role="form" id="userSignup">
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control phoneNum input-info" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-6">
			      <input class="form-control validCode signup-code" type="text" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-6">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn " checkCodeType="register" value="获取验证码" style="padding:4px;">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password input-info" type="password" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control userName input-info" type="text" placeholder="姓名">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control school input-info" type="text" placeholder="学校" schoolId="" >
			      <ul class="school-ul">
			      				 
			      </ul>
			      <script class="schoolListTemplate" type="text/x-jquery-tmpl">
						<li schoolId="{{= schoolId}}">{{= schoolName}}</li>
					</script>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control major input-info" type="text"  placeholder="专业">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <select class="form-control grade input-info">
			    
			      </select>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			      <input type="button" class="btn loginBtn signup input-info" value="注册">
			    </div>
			  </div>
			</form>
      </div>
    <!--   <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div> -->
    </div>
  </div>
</div>


<!-- Modal -->
<div class="modal" id="signSchoolModal" tabindex="-1" role="dialog" aria-labelledby="schoolModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="schoolModalLabel">学校入驻</h4>
      </div>
      <div class="modal-body">
        	<!--内容体  -->
        	<form class="form-horizontal" role="form" id="schoolSignup">
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control phoneNum input-info" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-6">
			      <input class="form-control validCode signup-code" type="text" placeholder="验证码" style="padding:4px;">
			  	</div>
			  	 <div class="col-sm-6">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn " checkCodeType="register" value="获取验证码">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password input-info" type="password" placeholder="密码">
			    </div>
			  </div>			 
			   <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control school input-info" type="text" placeholder="学校" schoolId="" >
			      <ul class="school-ul">
			      				 
			      </ul>
			      <script class="schoolListTemplate" type="text/x-jquery-tmpl">
						<li schoolId="{{= schoolId}}">{{= schoolName}}</li>
					</script>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			      <button type="button" class="btn loginBtn signup input-info">注册</button>
			    </div>
			  </div>
			</form>
      </div>
    <!--   <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div> -->
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal" id="signOrgModal" tabindex="-1" role="dialog" aria-labelledby="signOrgModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="signOrgModal">机构入驻</h4>
      </div>
      <div class="modal-body">
        	<!--内容体  -->
        	<form class="form-horizontal" role="form" id="orgSignup">
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control phoneNum input-info" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-6">
			      <input class="form-control validCode signup-code" type="text" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-6">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn sendCheckCodeBtn" checkCodeType="register" value="获取验证码" style="padding:4px;">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password input-info" type="password" placeholder="密码">
			    </div>
			  </div>			 			
			  <div class="form-group">			   
			    <div class="col-sm-12 btn-div">
			      <button type="button" class="btn loginBtn signup input-info">注册</button>
			    </div>
			  </div>
			</form>
      </div>
    <!--   <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div> -->
    </div>
  </div>
</div>
<div id="mask"></div>
    <div id="popup-captcha-mobile"></div>
</body>
</html>