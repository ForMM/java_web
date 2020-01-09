<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>晓园</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cleartype" content="on">
    <meta name="MobileOptimized" content="320">
    <meta name="HandheldFriendly" content="True">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" href="<%=basePath%>website/user/css/account.css">
    <script src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>   
    <script src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script> 
	<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	<script src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script src="<%=basePath%>website/common/js/help.js"></script>
	<script src="<%=basePath%>website/user/js/orgTeacher.js"></script> 
</head>
<body>
    <div class="org-div">         
        <section class="box">
            <div class="account-div">            	
              	<div class="profile-info-list tab-content">              		
              		<div>
              			<div>
              				<h3>机构老师</h3>                     				       				
              			</div>
              			<div id="org-teacher-list"> 
	              	<!-- 	<div class="account-info-unit">
	              				<div class="avatar">
	              					<img src="http://xiaoyuan-so.oss-cn-shanghai.aliyuncs.com/portrait/988fcf8ea46f41fbb87f6c58e9f1140d.jpg">
	              				</div>
	              				<div class="org-info">                 				       				
	    							<div class="unit-info">
								        <label>名字:</label>
								        <span>汤清</span>
	    							</div>
	    							<div class="unit-info">
								        <label>学校:</label>
								        <span>中南林业科技大学</span>
	    							</div> 
	    							<div class="unit-info">
								        <label>介绍:</label>
								        <span>我来自湖南，我是一名信息工程大学印刷工程专业应届本科即将毕业的学员。 \n 我的性格偏于内向，为人坦率、热情、讲求原则；处事乐观、专心、细致、头脑清醒；富有责任心、乐于助人。</span>
	    							</div>       							 						
	              				</div>
	              				<div>
	              					<input type="button" value="删除"  id="delTeacher-btn">      
	              				</div>
	              			</div>  -->            			
              			</div>
              			<div class="input-div">
              				<div class="">
              					老师手机号：
              				</div>
              				<div class="">
              					<input type="text" id="phone-number" placeholder="手机号">
              					<input type="button" value="添加老师"  id="addTeacher-btn">              					
              				</div>
              			</div>
              			
              		</div>
              		
              	</div>               
       
              	              
            </div>            
        </section>
    </div>
</body>

</html>