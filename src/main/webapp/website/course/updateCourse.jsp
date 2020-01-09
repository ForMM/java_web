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

<link href="<%=basePath%>website/scripts/bootstrap-datetimepicker/example/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="<%=basePath%>website/scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<link rel="stylesheet" type="text/css" href="<%=basePath%>website/article/css/article.css" />

<title>更新直播</title>
<script type="text/javascript" src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>
<script src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/filterAjax.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>

</head>
<body>

<input id="basePath" type="hidden" value="<%=basePath%>">
<input id="courseId" type="hidden" value="${courseId}">
	<div class="article-div">
		<div>
			<div class="type-name">课堂标题</div>
			<div>
				<input type="text" id="course-title" class="form-control" placeholder="标题" >				
			</div>
		</div>
		<div>
			<div class="type-name">课堂介绍</div>
			<div>
				<textarea id="course-intro" class="form-control course-info" placeholder="课堂介绍" style="height:100px;"></textarea>				
			</div>
		</div>
		<div class="course-content-div">
			<div class="type-name">课堂图片</div>
			<div class="article-content">							
				<div class="course-div" id="courseImg-div">
						
				</div>
				<div class="oprate-div">				
																	
					<div class="img-div"> 
						<span class="">
							<a href="javascript:void();">添加图片</a>
							<input type="file" id="contentImg" multiple name="contentImg" class="input-file"  onchange="change(this)">
						</span> 
					</div> 
					
				</div>
				
			</div>
		</div>
		<div>
			<div class="type-name">开始时间</div>
			<div>
				<div class="datetime-div">               
	                <div id="begin-time" class="input-group date form_datetime " data-date="" data-date-format="yyyy MM dd - HH:ii p" data-link-field="dtp_input1">
	                    <input class="form-control" size="16" type="text" value="" readonly>
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	                </div>
					<input type="hidden" id="dtp_input1" value="" /><br/>
	            </div>				
			</div>
		</div>
		<div>
			<div class="type-name">结束时间</div>
			<div>
				<div class="datetime-div">               
	                <div id="end-time" class="input-group date form_datetime " data-date="" data-date-format="yyyy MM dd - HH:ii p" data-link-field="dtp_input2">
	                    <input class="form-control" size="16" type="text" value="" readonly>
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	                </div>
					<input type="hidden" id="dtp_input2" value="" /><br/>
	            </div>				
			</div>
		</div>
		<div>
			<div class="type-name">类别</div>
			<div>
				<div class="radioBox type" id="courseTypeName">
				   
				</div>
				 <script id="courseTypeTemplate" type="text/x-jquery-tmpl">
						{{each(i,course) dataList}}
 							<input type="radio" name="radChl" id="radChl_{{= i}}" value="{{= course}}"><label for="radChl_{{= i}}">{{= course}}</label>										
						{{/each}}							
				</script>	
			</div>
		</div>	
		<div class="save-div">
			<input type="button" value="发布直播" id="updateCourse" class="">
		</div>	
	</div>
</body>

<script type="text/javascript" src="<%=basePath%>website/scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript" src="<%=basePath%>website/course/js/course.js"></script>
<script>
courseObj.getCourseType();
courseObj.initDataPicker();
courseObj.initUpdateData();
$("#updateCourse").click(function(){
	courseObj.updateCourse();
}); 
</script>
</html>