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
<link rel="stylesheet" href="<%=basePath%>website/scripts/layui/css/layui.css"  media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>直播管理</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script src="<%=basePath%>website/scripts/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/course/js/course.js"></script>
<script type="text/javascript" src="<%=basePath%>website/course/js/courseList.js"></script>

</head>
<body>
<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">

	<div class="article-div">
		<div class="container">
			 <div class="row">
			 	<table class="layui-table">
					  <colgroup>
					   	<col width="150">
					    <col width="120">
					    <col width="120">
					    <col width="80">
					    <col width="80">
					    <col width="200">
					  </colgroup>
					  <thead>
					    <tr>
					      <th>课程名称</th>
					      <th>创建时间</th>
					      <th>开始时间</th>
					      <th>讲师</th>
					      <th>课程状态</th>
					      <th>课程PPT</th>
					    </tr> 
					  </thead>
					  <tbody id="course-div">
					   
					  </tbody>
					  
					  <script id="courseListTemplate" type="text/x-jquery-tmpl"> 
						{{each(i,course) dataList}}
 						<tr>
					      <td>{{= courseName}}</td>
					      <td>{{= createTime}}</td>
					      <td>{{= beginTime}}</td>
					      <td>{{= teacherName}}</td>
					      <td>
							{{if statu==1}}
								<span>即将开始</span>
							{{/if}}
							{{if statu==2}}
								<span>直播中</span>
							{{/if}}
							{{if statu==3}}
								<span>已结束</span>
							{{/if}}
							</td>
					      <td>
							<input type="file" id="file_{{= courseId}}" name="courseFile" lay-type="file" class="layui-upload-file"> 
							<button class="layui-btn layui-btn-normal courseImg-btn" id="photos_{{= courseId}}">查看PPT</button>							
						  </td>
					    </tr>
						{{/each}}
					</script> 
					  
					</table>
					
					<div id="coursepage"></div>
					
					
             	</div>              
			 </div>		
			
	</div>
</body>
<script type="text/javascript">
	courseObj.getUserCourseList(coursePage,coursePageSize);
</script>
</html>