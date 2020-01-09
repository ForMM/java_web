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
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>文章列表</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/article/js/articleDraftList.js"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">

	<div class="article-div">
		<div>
			<ul id="articleList">
			
			</ul>
			
			<script id="articleListTemplate" type="text/x-jquery-tmpl"> 
						{{each(i,article) dataList}}
 							{{if imgs}}
                                   <li>
					<div class="article-unit">
						 <div class="img_box">
						 	<a target="_blank">

								{{each(i,img) imgs}}
														{{if i<1}}
														<img alt="{{= articleTitle}}" class="load-img fade" src="{{= img}}">
														{{/if}}
													{{/each}}
								
						 		
						 	</a>
						 </div>
						 <div class="intro" articleId="{{= articleId}}">
						 		<h3><a target="_blank" style="overflow: hidden; text-overflow: ellipsis; -webkit-box-orient: vertical; display: -webkit-box; -webkit-line-clamp: 2;">{{= articleTitle}}</a></h3>
						 		<div class="info">{{= shortContent}}</div>
						 		<div class="tag">
						 			<span>{{= tagName}}</span>
						 			<span class="pubtime">{{= publisTime}}</span>
									<span class="article-comment"> <a class="article-edit" articleId="{{= articleId}}">编辑</a> <a class="article-del" articleId="{{= articleId}}">删除</a></span>								
						 		</div>
						 		
						 </div>
					</div>
				</li>      
							{{else}}
									<li>
					<div class="article-unit">
						 
						 <div class="article-intro" articleId="{{= articleId}}">
						 		<h3><a  target="_blank" style="overflow: hidden; text-overflow: ellipsis; -webkit-box-orient: vertical; display: -webkit-box; -webkit-line-clamp: 2;">{{= articleTitle}}</a></h3>
						 		<div class="info">{{= shortContent}}</div>
						 		<div class="tag">
						 			<span>{{= tagName}}</span>
						 			<span class="pubtime">{{= publisTime}}</span>
									<span class="article-comment"><a class="article-edit" articleId="{{= articleId}}">编辑</a> <a class="article-del" articleId="{{= articleId}}">删除</a></span>
						 		</div>
						 		
						 </div>
					</div>
				</li>				
							{{/if}}	


                 
						{{/each}}
				</script>
			
		</div>
		
		
		
	</div>
</body>

</html>