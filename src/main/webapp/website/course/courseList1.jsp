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

<title>发布文章</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/course/js/course.js"></script>
<script type="text/javascript" src="<%=basePath%>website/course/js/courseList.js"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">

	<div class="addTopBtn">
		<input value="添加课堂" type="button" id="addCourseBtn" >
	</div> 

	<div class="article-div">
		<div class="container">
			 <div class="row" id="course-div">
			 	
             </div>              
			 </div>		
			 <script id="articleListTemplate" type="text/x-jquery-tmpl"> 
						{{each(i,article) dataList}}
 						
 				<div class="post-masonry col-md-3 col-sm-3">
                    <div class="post-thumb">
                        <img src="{{= img}}" alt="">
                        <div class="title-over">
                            <h4><a href="#">{{= courseName}}</a></h4>
                        </div>
                        <div class="post-hover ">
                            <div class="inside">
                                <i class="fa fa-plus"></i>                                
                                <h5>{{= courseName}}</h5>                                  
					         	<a href="<%=basePath %>course/updateCoursePage?courseId={{= courseId}}"><p>{{= courseIntro}}</p></a>                                                              
                            </div>
                        </div>
                    </div>
                </div>

                 
						{{/each}}
				</script>
	</div>
</body>
<script type="text/javascript">
	courseObj.getUserCourseList(coursePage,coursePageSize);
	courseObj.scrollList();
</script>
</html>