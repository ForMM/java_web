<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="<%=basePath%>website/scripts/layui/css/layui.css"  media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>发布文章</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script src="<%=basePath%>website/scripts/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>website/article/js/updateArticle.js"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="articleId" type="hidden" value="${articleId}">
	<div class="article-div">
		<div>
			<div class="type-name">文章标题</div>
			<div>
				<input type="text" id="article-title" class="form-control" placeholder="标题" value="">				
			</div>
		</div>
		<div class="article-content-div">
			<div class="type-name">文章内容</div>
			<div class="article-content">							
				<textarea class="layui-textarea" id="article-content" style="display: none">
				
				</textarea>
			</div>
		</div>
		<div>
			<div class="type-name">类别</div>
			<div>
				<div class="radioBox type">
					<c:forEach items="${dataList}" var="item">
					 <input type="radio" name="radChl" value="${item }" ><label for="radChl1">${item }</label>
					</c:forEach>
				</div>
			</div>
		</div>	
		
		
		<c:if test="${userType==3}">
			<div>
				<div class="type-name">置顶</div>
				<div>
					<div class="radioBox type">
						 <input id="topCheck" type="checkbox" value="1"/>
						 <label for="topCheck">是</label>
					</div>
				</div>
			</div>	
		</c:if>
		
		<div class="save-div">
			<!-- <input type="button" value="预览" id="previewArticle" class="previewBtn"> -->
			<input type="button" value="保存" id="saveArticle" class="saveBtn">
			<input type="button" value="发布" id="pubArticle" class="pubBtn">
		</div>
	</div>
</body>

</html>