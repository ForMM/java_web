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
 	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/fullpage/jquery.fullpage.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/fullpage/home.css" />
	<link rel="stylesheet" href="<%=basePath%>website/scripts/layui/css/layui.css"  media="all">
	<style>
		.main{
			width:80%;
			margin:8rem auto;
		}
		.left-nav{
			overflow: hidden;
    		display: inline-block;
			float: left;
		    width: 15%;
		    min-width: 200px;
		}
		.iframediv-main{
		    width: 82%;
			overflow: hidden;
    		display: inline-block;
			border:1px solid #E2E2E2;
		}
		.layui-nav-item{
			text-align:center;
		}
		
@media (max-width: 1600px) {
  .iframediv-main{
  		width: 880px; 
		min-height: 800px;
  }
}

@media (max-width: 1360px) {
   .iframediv-main{
  		width: 780px; 
		min-height: 800px;
  }
}

@media (max-width: 1240px) {
   .iframediv-main{
  		width: 680px; 
		min-height: 800px;
  }
}

@media (max-width: 1124px) {
    .iframediv-main{
  		width: 580px; 
		min-height: 800px;
  }
}

@media (max-width: 767px) {
     .iframediv-main{
  		width: 880px; 
		min-height: 800px;
  }
}

@media (max-width: 640px) {
    .iframediv-main{
  		width: 980px; 
		min-height: 800px;
  }
}

@media (max-width: 640px) {
   .iframediv-main{
  		width: 980px; 
		min-height: 800px;
  }
}

@media (max-width: 520px) {
    .iframediv-main{
  		width: 980px; 
		min-height: 800px;
  }
}
		
	</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="main">

	<div class="left-nav">
		<ul class="layui-nav layui-nav-tree" lay-filter="test">
		<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
		  <li class="layui-nav-item layui-this">
		    <a href="<%=basePath %>user/accountPage.do" target="myFrameName">我的账号</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="<%=basePath %>article/articlePage.do" target="myFrameName">发布动态</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="<%=basePath %>article/articleDraftListPage.do" target="myFrameName">草稿箱</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="<%=basePath %>article/articlePubListPage.do" target="myFrameName">已发管理</a>
		  </li>
		  <c:if test="${currentUser.userType == 1}">
		  	<c:if test="${currentUser.authStatus != 2}">
			  <li class="layui-nav-item">
			  	<a href="<%=basePath %>course/ownlivePage.do" target="myFrameName">直播申请</a>
			  </li>
		  	</c:if>
		  	<c:if test="${currentUser.authStatus == 2}">
		  	<li class="layui-nav-item">
			  	<a href="<%=basePath %>course/courseManagePage.do" target="myFrameName">直播管理</a>
			  </li>
		  	</c:if>
		  </c:if>
		  
		  <c:if test="${currentUser.userType == 2}">
			  <c:if test="${currentUser.authStatus != 2}">
				  <li class="layui-nav-item">
				  	<a href="<%=basePath %>user/orgSignupPage.do" target="myFrameName">直播申请</a>
				  </li>
			  	</c:if>
			  	<c:if test="${currentUser.authStatus == 2}">
			  	<li class="layui-nav-item">
				  	<a href="<%=basePath %>course/courseManagePage.do" target="myFrameName">直播管理</a>
				  </li>
			  	</c:if>
			   	<li class="layui-nav-item">
			  		<a href="<%=basePath %>user/teacherManagePage.do" target="myFrameName">老师管理</a>
			  	</li>
		  </c:if>
		  
		   <c:if test="${currentUser.userType == 3}">
		   	<li class="layui-nav-item">
		  		<a href="<%=basePath %>article/verifyArticleListPage.do" target="myFrameName">投稿审核</a>
		  	</li>
		  </c:if>
		  
		</ul>
	</div>
	<div id="iframediv" class="iframediv-main">
		<iframe id="content" class="iframe-content" name="myFrameName" scrolling="yes" frameborder="0" >
		</iframe>
	</div>
</div>

</body>
 
<script src="<%=basePath%>website/scripts/layui/layui.js" charset="utf-8"></script>
<script>
layui.use('element', function(){
  var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(test)', function(elem){
    //console.log(elem)
  });
});
$("#content").attr("src","<%=basePath %>user/accountPage.do");
</script>
</html>