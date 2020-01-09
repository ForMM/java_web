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
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>发布文章</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>website/article/js/schoolArticleInfo.js"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="articleId" type="hidden" value="${articleId}">
	<div class="article-div">	
		
	
  <div id="article-main" class="article-main"> 
	   <h1 class="article-title" id="article-title"></h1> 
	   <div class="articleInfo">     
	   	<span class="time" id="article-tag"></span> 
	    <span class="time" id="article-time"></span> 
	    
	    <span class="time" id="article-user"></span> 
	   </div> 
	   <div class="article-content" id="article-content">
	    
	   </div>   
	   <div class="contribute-div" id="contribute-div"></div>
  </div>
  
  <div class="comment article-main" id="comment" style="margin-top:30px;">
    <div class="c-header" id="comment-count"></div>
   
     <ul id="comment-ul">
        <!-- <li class="c-item">
        	<a target="_blank" class="avatar-wrap" href="/user/5724118641/"> 
        		<img alt="" src="http://p3.pstatp.com/thumb/12009/6319075023">
        	</a>
            <div class="c-content">
                <div class="c-user-info">
                	<a class="c-user-name" target="_blank" href="/user/5724118641/">可靠的马小拐</a>
                    <span class="c-create-time">6小时前</span>
                </div>
                <p>报复社会可以去砍城管，对孩子下手，懦夫！</p>
			</div>
        </li> -->
     </ul>
    
  </div>
 
		
	</div>
</body>

</html>