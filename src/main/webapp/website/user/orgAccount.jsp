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
    <script src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>   
    <script src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script> 
	<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	<script src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script src="<%=basePath%>website/common/js/help.js"></script>
	<script src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
	<script src="<%=basePath%>website/index.js"></script>
	<script src="<%=basePath%>website/user/js/orgAccount.js"></script>
</head>
<body>
   <input id="basePath" type="hidden" value="<%=basePath%>">
<input id="userId" type="hidden" value="${userId}">
    <div class="org-div">         
        <section class="box">
            <div class="account-div">            	
              	<div class="profile-info tab-content" >
              		<div class="account-info" id="info-see">
              			<div>
              				<h3>基本资料</h3>
              				<input type="button" value="编辑资料" class="modify-btn" id="modify-btn">              				
              			</div>
              			<div class="account-info">
              				<div class="avatar">
              					<img src="${currentUser.headImg}">
              				</div>
              				<div class="man-info">                 				       				
    							<div class="unit-info">
							        <label>机构名称:</label>
							        <span>${currentUser.userName}</span>
    							</div>
    							<div class="unit-info">
							        <label class="intro-org">机构简介:</label>
							        <div class="word-intro">${currentUser.userIntro}</div>
    							</div>    
    							<div class="unit-info">
							        <label>联系地址:</label>
							        <span>${currentUser.userAddr}</span>
    							</div>
    							<div class="unit-info">
							        <label>联系电话:</label>
							        <span>${currentUser.userPhone}</span>
    							</div> 
    							
    							<div class="unit-info">
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
    							</div>   
    							   							
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
              						<a href="javascript:void();">修改logo</a>
              						<input type="file" id="headerImg" multiple name="headerImg" class="input-file" onchange="change(this)">
              					</span>
              				</div>
              				<div class="man-info">                 				       				
    							<div class="unit-info">
							        <label>机构名称:</label>							         
							        <input id="orgName" class="formcontrl" type="text" value="${currentUser.userName}"/>
    							</div>
    							<div class="unit-info">
							        <label>机构简介:</label>
							        <textarea id="orgIntro" rows="8" cols="">${currentUser.userIntro}</textarea>						      					     
    							</div>       							
    							<div class="unit-info">
							        <label>联系地址:</label>
							        <input id="userAddr" class="formcontrl" type="text" value="${currentUser.userAddr}"/>							        
    							</div>
    							<div class="unit-info">
							        <label>联系电话:</label>
							        <input id="userPhoneNum" class="formcontrl" type="text" value="${currentUser.userPhone}"/>							        
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