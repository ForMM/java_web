<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
    
    <!--[if lt IE 9]>
	<script type="text/javascript" src="<%=basePath%>website/common/js/html5.js"></script>
	<script type="text/javascript" src="<%=basePath%>website/common/js/respond.min.js"></script>
	<![endif]-->
    
    <script src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>   
    <script src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script> 
	<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	<script src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script src="<%=basePath%>website/common/js/help.js"></script>
	<script src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
	<script src="<%=basePath%>website/index.js"></script>
	<script src="<%=basePath%>website/user/js/account.js"></script>
</head>
<body>
   <input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">
    <div class="org-div">         
        <section class="box">
            <div class="account-div">            	
              
              	<div class="profile-info" >
              		<div class="account-info" id="info-see">
              			<div>
              				<h3>个人资料</h3>
              				<input type="button" value="编辑资料" class="modify-btn" id="modify-btn">              			
              			</div>
              			<div class="account-info">
              				<div class="avatar">
              					<img src="${currentUser.headImg}">
              				</div>
              				<div class="man-info">                 				       				
    							<p class="unit-info">
							        <label>姓名:</label>
							        <span>${currentUser.userName}</span>
    							</p>
    							<p class="unit-info">
							        <label>学校:</label>
							        <span>${currentUser.schoolName}</span>
    							</p>    
    							<p class="unit-info">
							        <label>年级:</label>
							        <span>${currentUser.grade}</span>
    							</p>
    							<p class="unit-info">
							        <label>专业:</label>
							        <span>${currentUser.major}</span>
    							</p> 
    							<p class="unit-info">
							        <label>用户类型:</label>
							        <c:if test="${currentUser.userType==1}">
										<span>个人用户</span>
									</c:if>
							        <c:if test="${currentUser.userType==2}">
										<span>机构用户</span>
									</c:if>
									<c:if test="${currentUser.userType==3}">
										<span>学校用户</span>
									</c:if>
    							</p>    							
              				</div>
              			</div>
              		</div>
              		<div class="account-info-modify" style="display:none;" id="info-modify">
              			<div>
              				<h3>编辑资料</h3>              				
              			</div>
              			<div class="account-info">
              				<div class="avatar">
              					<img id="header-img" src="${currentUser.headImg}">
              					<span class="">
              						<a href="javascript:void();">修改头像</a>
              						<input type="file" id="headerImg" multiple name="headerImg" class="input-file" onchange="change(this)">
              					</span>
              				</div>
              				<div class="man-info">                 				       				
    							<div class="unit-info">
							        <label>姓名:</label>							         
							        <input id="userRealName" class="formcontrl" type="text" value="${currentUser.userName}"/>
    							</div>
    							<div class="unit-info">
							        <label>学校:</label>
							        <input class="formcontrl" type="text" id="selectSchool" autocomplete="off" schoolId="${currentUser.schoolId}" value="${currentUser.schoolName}">
							        <ul class="account-school-ul" id="selectSchoolList"></ul>
								    <script id="selectSchoolListTemplate" type="text/x-jquery-tmpl">
										<li schoolId="{{= schoolId}}">{{= schoolName}}</li>
									</script>							      					     
    							</div>       							
    							<div class="unit-info">
							        <label>年级:</label>
							        <input id="userGrade" class="formcontrl" type="text" value="${currentUser.grade}"/>							        
    							</div>
    							<div class="unit-info">
							        <label>专业:</label>
							        <input id="userMajor" class="formcontrl" type="text" value="${currentUser.major}"/>							        
    							</div> 
    							<div class="buttons">
				                    <button class="btn btn-save btn-common" type="submit" id="info-save">保存</button>
				                    <button class="btn btn-reset btn-common" type="reset" id="info-close">取消</button>
				                </div>   							
              				</div>              				
              			</div>
              			
              		</div>
              	</div>               
            </div>            
        </section>
    </div>
</body>

</html>