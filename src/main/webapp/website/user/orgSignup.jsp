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
    <link rel="stylesheet" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>website/scripts/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=basePath%>website/scripts/layer-v2.4/layer/skin/layer.css">
	<link rel="stylesheet" href="<%=basePath%>website/user/css/account.css">
	
    <script src="<%=basePath%>website/scripts/jquery-1.11.1.min.js"></script>    
	<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	<script src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script src="<%=basePath%>website/common/js/help.js"></script>
	<script src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
 	<script src="<%=basePath%>website/user/js/orgSign.js"></script>

</head>
<body>
    <div class="org-div" >         
        <div class="validate-flow">
        	 <div class="header">
               <div class="xiaoyuan-inner">
               		<h1>机构认证</h1>
               		
               		<div class="renzheng-flow">
               			<div>
               			<c:choose>
  		 				<c:when test="${currentUser.authStatus ==0}">
  		 					<div class="flow-num flow-selected">1</div>   
               				<div class="flow-word">填写资料</div> 
  		 				</c:when>
  		 				<c:otherwise> 
		   					<div class="flow-num">1</div>   
               				<div class="flow-word">填写资料</div>    
					    </c:otherwise>
						</c:choose>                 				              			
               			</div>               	
               			<div>
               				<div class="flow-num">2</div>   
               				<div class="flow-word">提交</div>               			
               			</div>               			
               			<div>               			
               				<c:choose>
	  		 				<c:when test="${currentUser.authStatus ==1}">
	  		 					<div class="flow-num flow-selected">3</div>   
	               				<div class="flow-word">审核</div> 
	  		 				</c:when>
	  		 				<c:otherwise> 
			   					<div class="flow-num">3</div>   
	               				<div class="flow-word">审核</div>    
						    </c:otherwise>
							</c:choose>                			              		
               			</div>               			
               			<div>
               				<c:choose>
	  		 				<c:when test="${currentUser.authStatus ==2}">
	  		 					<div class="flow-num flow-selected">4</div>   
	               				<div class="flow-word">完成</div> 
	  		 				</c:when>
	  		 				<c:when test="${currentUser.authStatus ==3}">
	  		 					<div class="flow-num flow-failed">4</div>   
	               				<div class="flow-word">完成</div> 
	  		 				</c:when>
	  		 				<c:otherwise> 
			   					<div class="flow-num">4</div>   
	               				<div class="flow-word">完成</div>    
						    </c:otherwise>
							</c:choose>  
               				              			
               			</div>                			
               		</div>
               		
               		<%-- <div class="user">
					    <div class="avatar-vessel">
					        <img src="${currentUser.headImg}" class="avatar" id="avatar">
					    </div>
					    <div class="user-info-dropdown" id="info-user-bb">
						    <div class="info-cell">
						        <ul>
						            <li class="user-uid">${currentUser.userName}</li>
						            <li><span>${currentUser.schoolName}</span></li>
						            <li><span class="major-span">${currentUser.major}</span><span class="grade-span">${currentUser.grade}</span> </li>
						        </ul>
						    </div>
						</div>					   
					</div> --%>
               </div>
            </div>
        </div>
        
        
          <c:choose>
  		 	<c:when test="${currentUser.authStatus== 1}">  
        <div class="org-form-div">
        	<form>
        		<div class="unit-info">
					 <label>机构logo:</label>
					 <div class="org-logo-div">
					 	<img id="header-img" src="${currentUser.headImg}">	              		
					 </div>					
				</div>  
				<div class="unit-info">
					 <label>机构名称:</label>
					 <p class="live-info">${currentUser.userName}</p>					
				</div>  
				<div class="unit-info">
					 <label>机构简介:</label>
					 <p class="live-info" style="padding-left:0px;">${currentUser.userIntro}</p>					 				
				</div>  
				<div class="unit-info">
					 <label>营业执照:</label>
					 <div class="org-logo-div">
					 	<img id="authImg" src="${currentUser.authImgs}">	              		
					 </div>						
				</div>  
				
				<div class="unit-info">
					 <label>运营者姓名:</label>
					 <p class="live-info" style="padding-left:0px;">${currentUser.userRealName}</p>					 				
				</div> 
				<div class="unit-info">
					 <label>身份证号码:</label>
					 <p class="live-info" style="padding-left:0px;">${currentUser.userCode}</p>					 				
				</div> 
				<div class="unit-info">
					 <label>身份证图片:</label>
					 <div class="org-logo-div">
					 	<img src="${currentUser.userCodeImg}">	              		
					 </div>						
				</div> 				
				<div class="unit-info">
					 <label>联系地址:</label>
					  <p class="live-info">${currentUser.userAddr}</p>					 
				</div>  
				<div class="unit-info">
					 <label>联系方式:</label>
					  <p class="live-info">${currentUser.userPhone}</p>						 
				</div> 								
        	</form>
        </div>
        
        </c:when>
		   <c:otherwise> 
        <div class="org-form-div">
        	<form>
        		<div class="unit-info">
					 <label class="org-label-logo">机构logo:</label>
					 <div class="org-logo-div">
					 	<img id="header-img">
	              		<span class="">
       						<a href="javascript:void();">上传机构logo</a>
       						<input type="file" id="headerImg" multiple name="headerImg" class="org-file" onchange="change(this)" style="margin-left: 0px;">
       					</span>
					 </div>					
				</div>  
				<div class="unit-info">
					 <label>机构名称:</label>
					 <input class="formcontrl" type="text" id="orgName">
				</div>  
				<div class="unit-info">
					 <label class="org-label-intro">机构简介:</label>
					 <textarea id="orgIntro" rows="3" cols="50" placeholder="简单介绍" class="org-textarea" ></textarea>					 
				</div>  
				<div class="unit-info">
					 <label class="org-label-logo">营业执照:</label>
					 <div class="org-logo-div">
					 	<img id="authImg">
	              		<span class="">
       						<a href="javascript:void();">上传营业执照</a>
       						<input type="file" id="auth-Img" multiple name="authImg" class="org-file" onchange="changeAuth(this)" style="margin-left: 0px;">
       					</span>
					 </div>						
				</div>  
				
				<div class="unit-info">
					 <label>运营联络人:</label>
					 <input class="formcontrl" type="text" id="userRealName">
				</div>
				<div class="unit-info">
					 <label>身份证号码:</label>
					 <input class="formcontrl" type="text" id="userCode">
				</div>
				<div class="unit-info">
					 <label class="org-label-logo">身份证图片:</label>
					 <div class="org-logo-div">
					 	<img id="userCodeImg">
	              		<span class="">
       						<a href="javascript:void();">上传身份证</a>
       						<input type="file" id="userCodeImgUpload" multiple name="userCodeImg" class="org-file" onchange="changeUserCodeImg(this)" style="margin-left: 0px;">
       					</span>
					 </div>						
				</div>
								
				<div class="unit-info">
					 <label>机构地址:</label>
					 <input class="formcontrl" type="text" id="userAddr">
				</div>  
				<div class="unit-info">
					 <label>联系方式:</label>
					 <input class="formcontrl" type="text" id="userPhoneNum" value="${currentUser.userPhone}">
				</div> 				
				<div class="save-div">
					<input type="button" value="提交" id="submitOrg">
				</div>	 
        	</form>
        </div>
        
        </c:otherwise>
			</c:choose>
        
    </div>



</body>

</html>