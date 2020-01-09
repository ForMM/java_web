<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta name = "format-detection" content = "telephone=no">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>website/index.css" />
</head>
<body>
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<div id="header" class="owl-header nav">
    <div class="wrap-header">
        <div class="logo">
            <a href="index.html" class="owl-logo-img"></a>
        </div>

        <ul id="menu" class="header-link">
            <li><a href="">首页</a></li>
         
            <li class="login-li" style="padding-right: 0; "><a data-toggle="modal" data-target="#loginModal">登录</a></li>
          	<li id="userInfoLi" style="display:none;">
            	<div class="dropdown" style="cursor:pointer;">
				  <div id="dLabel"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   	<span id="userAccount"></span>
				   	<span class="caret"></span>
				  </div>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" style="position:relative;min-width: 100px; ">
				   	<%-- <li><a  href="<%=basePath %>user/accountPage">个人中心</a></li>	 --%>			  
				    <li id="loginout">退出</li>
				  </ul>
				</div>   
            </li> 
            
            <li id="signupLi">
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

<div>
	<div id="sub-header" class="sub_nav">
	    <div class="wrap-sub-header">
	        <ul id="menu" class="sub-header-link">
	        
	        
	          	<li class="myArticles subnav-li selected"><a  href="<%=basePath %>user/accountPage.do" target="myFrameName">我的账号</a></li>
			   	<li class="myArticles subnav-li" id="notSchoolArticle" ><a href="<%=basePath %>article/articleListPage.do" target="myFrameName">我的文章</a></li>
			   	<li class="myArticles subnav-li" id="schoolArticle" ><a href="<%=basePath %>article/schoolArticleListPage.do" target="myFrameName">学校文章</a></li>
			   	<li class="myArticles subnav-li" id="authCourse" ><a href="<%=basePath %>course/liveApplicationPage.do" target="myFrameName">直播申请</a></li>
			   	<li class="myArticles subnav-li" id="myCourse" ><a href="<%=basePath %>course/courseListPage.do" target="myFrameName">我的直播</a></li>
	        
	           
	        </ul>
	
	    </div>
	</div>

	<div id="iframediv" class="iframediv-nav">
		<iframe id="content" class="iframe-content" name="myFrameName" scrolling="yes" frameborder="0" >
		
		</iframe>
	</div>
	
</div>







<a class="back-to-top is-active" href="#"></a>

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
			    <div class="col-sm-12">
			      <button type="button" class="btn loginBtn" id="loginBtn">登录</button>
			      <a class="password-forget" id="resetPsd">忘记密码</a>
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
			      <input class="form-control" type="text" id="phoneNumReset" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-7">
			      <input class="form-control" type="text" id="validCodeReset" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-5">
			      <input type="button" class="btn loginBtn" sendCheckCode="sendCheckCode" checkCodeType="resetpwd" value="获取验证码"></input>
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control" type="password" id="passwordReset" placeholder="新密码">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <button type="button" class="btn loginBtn" id="reset">提交</button>
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
			      <input class="form-control phoneNum" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-7">
			      <input class="form-control validCode" type="text" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-5">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn " checkCodeType="register" value="获取验证码">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password" type="password" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control userName" type="text" placeholder="姓名">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control school" type="text" placeholder="学校" schoolId="" >
			      <ul class="school-ul">
			      				 
			      </ul>
			      <script class="schoolListTemplate" type="text/x-jquery-tmpl">
						<li schoolId="{{= schoolId}}">{{= schoolName}}</li>
					</script>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control major" type="text"  placeholder="专业">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <select class="form-control grade">
			    
			      </select>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input type="button" class="btn loginBtn signup" value="注册">
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
			      <input class="form-control phoneNum" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-7">
			      <input class="form-control validCode" type="text" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-5">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn " checkCodeType="register" value="获取验证码">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password" type="password" placeholder="密码">
			    </div>
			  </div>			 
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control school" type="text" placeholder="学校" schoolId="" >
			      <ul class="school-ul">
			      				 
			      </ul>
			      <script id="schoolListTemplate" type="text/x-jquery-tmpl">
						<li schoolId="{{= schoolId}}">{{= schoolName}}</li>
					</script>
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <button type="button" class="btn loginBtn signup">注册</button>
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
			      <input class="form-control phoneNum" type="text" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-7">
			      <input class="form-control validCode" type="text" placeholder="验证码">
			  	</div>
			  	 <div class="col-sm-5">
			      <input type="button" sendCheckCode="sendCheckCode" class="btn loginBtn sendCheckCodeBtn" checkCodeType="register" value="获取验证码">
			  	</div>
			  </div>
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <input class="form-control password" type="password" placeholder="密码">
			    </div>
			  </div>			 			
			  <div class="form-group">			   
			    <div class="col-sm-12">
			      <button type="button" class="btn loginBtn signup">注册</button>
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
<script type="text/javascript">
$(function(){

   var beforeScrollTop = $(document).scrollTop();
   $(window).scroll(function () {
        var afterScrollTop = $(document).scrollTop();
        if (afterScrollTop > beforeScrollTop) {//往下
            if(afterScrollTop > 200){
                $(".nav").slideUp();
            }
           	$(".back-to-top").css("display","none"); 
        } else {//往上
            $(".nav").slideDown();
          	$(".back-to-top").css("display","block"); 
        }

        beforeScrollTop = $(document).scrollTop();
        
        if(afterScrollTop == 0){
        	$(".back-to-top").css("display","none");
        }
        
   });
    
   $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 700);
        return false;
   });
   
   indexObject.getUserInfo();
	
});
</script>
</html>