<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</head>

<body>
<jsp:include page="website/header.jsp"></jsp:include>

<div id="fullpage">
	<div class="section active" id="section0">
		<!-- <h1>学习成就梦想</h1>
		<h3>晓园打造高校最专业的学习平台</h3> -->
		
		<div class="qrCodeDiv">
			<img class="qrcode" alt="" src="<%=basePath%>website/common/img/qrCode.png">
		</div>
		<div class="iosDownload">
			<a href="">
				<img alt="" src="<%=basePath%>website/common/img/ios.png">
			</a>
		</div>
		<div class="andridoDownload">
			<a href="">
				<img alt="" src="<%=basePath%>website/common/img/andriod.png">
			</a>
		</div>
		
	</div>
	<div class="section" id="section1">
	    <div class="slide "><h1>Simple Demo</h1></div>
	    <div class="slide active"><h1>Only text</h1></div>
	    <div class="slide"><h1>And text</h1></div>
	    <div class="slide"><h1>And more text</h1></div>
	</div>
	<div class="section" id="section2"><h1>发布资讯，给学校学生创建最好的资讯分享平台</h1></div>
	<div class="section" id="section3"><h1>发布课堂，找到志同道合的同志一起线上线下学习，一起成长</h1></div>
</div>


	<script src="<%=basePath%>website/scripts/fullpage/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/scripts/fullpage/scrolloverflow.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/scripts/fullpage/jquery.fullpage.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#fullpage').fullpage({
				sectionsColor: ['#f2f2f2', '#4BBFC3', '#7BAABE', 'whitesmoke', '#ccddff']
			});
		});
	</script>

</body>
</html>