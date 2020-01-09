<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="<%=basePath%>website/scripts/layui/css/layui.css"  media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>直播管理</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script src="<%=basePath%>website/scripts/layui/layui.js" charset="utf-8"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">


	<div class="article-div">
		<div class="layui-tab  layui-tab-card" lay-filter="demo">
		  <ul class="layui-tab-title">
		    <li class="layui-this">发布直播</li>
		    <li>课程管理</li>
		  </ul>
		  <div class="layui-tab-content">
		    <div class="layui-tab-item layui-show">
		     	<iframe class="course-iframe" name="courseaddIframe" scrolling="yes" frameborder="0" src="<%=basePath %>course/addCoursePage.do">
				</iframe>
		    </div>
		    <div class="layui-tab-item">
		    	<iframe id="courseListIframe" name="courseListIframe" class="course-iframe" scrolling="yes" frameborder="0" src="<%=basePath %>course/courseListPage.do">
				</iframe>
		    </div>
		  </div>
		</div>
	</div>
</body>
<script type="text/javascript">
var element=null;
	layui.use('element', function(){
	  var $ = layui.jquery;
	  element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
	});
	function ccc(){ 
		element.tabChange('demo', 1)
	}
</script>
</html>