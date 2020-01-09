<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
<link rel="stylesheet" href="<%=basePath%>website/scripts/layui/css/layui.css"  media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />
<title>老师管理</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/user/js/addTeacher.js"></script>
</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
	<div class="article-div">
		<div>
			<div class="type-name">讲师姓名</div>
			<div>
				<input type="text" id="teacherName" name="title"  lay-verify="title" autocomplete="off"  class="layui-input" placeholder="姓名">				
			</div>
		</div>
		<div>
			<div class="type-name">手机号</div>
			<div>
			 	<input type="tel" id="phoneNumber" name="phone" lay-verify="phone" autocomplete="off" class="layui-input"  placeholder="手机号">
			</div>
		</div>
		<div>
			<div class="type-name">讲师介绍</div>
			<div>
				 <textarea placeholder="老师介绍" class="layui-textarea" id="teacherIntro"></textarea>
			</div>
		</div>
		<div class="save-div">
			<button class="layui-btn" id="addTeacher-btn">保存</button>
		</div>	
	</div>
</body>

</html>