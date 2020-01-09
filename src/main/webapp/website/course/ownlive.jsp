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
    <script src="<%=basePath %>website/common/js/jquery.tmpl.min.js"></script> 
	<script src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
	<script src="<%=basePath%>website/common/js/filterAjax.js"></script>
	<script src="<%=basePath%>website/common/js/help.js"></script>
	<script src="<%=basePath%>website/common/js/ajaxfileupload.js"></script>
	<script src="<%=basePath%>website/course/js/ownlive.js"></script>
	
	<style>
		.row{
			margin-left:0;
			margin-right:0;
		}
	</style>
	
</head>
<body>
 
    <div class="org-div">         
        <section class="box">
            <div class="header">
               <div class="xiaoyuan-inner">
               		<h1>个人讲师申请</h1>
               		
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
               		
               		<div class="user">
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
					</div>
               </div>
            </div>
            <div class="common-live-div">  
            
            <c:choose>
  		 	<c:when test="${currentUser.authStatus== 1}">  
		          <div class="row" >
  				<div class="col-sm-3 col-md-4">
	  				<div class="anth-div">
	  					<div>
	  						<span>1.专业技能证书照片</span>
	  						<span>2.名校高学历证书照片</span>
	  						<span>3.本人认证微博号截图</span>
	  						<span>(三选一)</span>
	  					</div>
	              		<img id="authImg" src="${currentUser.authImgs}">
	              		
	              	</div>    
  				</div>
  				<div class="col-sm-9 col-md-8">
	  				<div class="user-man-info">                 				       				
						<%-- <div class="unit-info">
							 <label>真实姓名:</label>							         
							 <input id="userRealName" class="formcontrl" type="text" value="${currentUser.userName}"/>
						</div> --%>
						<div class="unit-info">
							 <label>身份证:</label>
							 <p class="live-info">${currentUser.userCode}</p>
						</div>       							
						<div class="unit-info">
							 <label>开课类:</label>
							 <p class="live-info">${currentUser.courseType}</p>							 			       
						</div>
						<div class="unit-info">
							 <label>个人简介:</label>
							 <p class="live-info" style="padding-left:0px;">${currentUser.userIntro}</p>									 						        
						</div> 					
													
	          		</div> 
  				</div>
          	</div> 
          	
		   </c:when>
		   <c:otherwise> 
		  <div class="row">
  				<div class="col-sm-3 col-md-4">
	  				<div class="anth-div">
	  					<div>
	  						<span>1.专业技能证书照片</span>
	  						<span>2.名校高学历证书照片</span>
	  						<span>3.本人认证微博号截图</span>
	  						<span>(三选一)</span>
	  					</div>
	              		<img id="authImg">
	              		<span class="">
	              			<a href="javascript:void();">上传认证资料</a>
	              			<input type="file" id="auth-Img" multiple name="authImg" class="input-file" onchange="changeAuth(this)">
	              		</span>
	              	</div>    
  				</div>
  				<div class="col-sm-9 col-md-8">
	  				<div class="user-man-info">                 				       				
						<%-- <div class="unit-info">
							 <label>真实姓名:</label>							         
							 <input id="userRealName" class="formcontrl" type="text" value="${currentUser.userName}"/>
						</div> --%>
						<div class="unit-info">
							 <label>身份证:</label>
							 <input class="formcontrl" type="text" id="IDNum">
						</div>       							
						<div class="unit-info">
							 <label>开课类:</label>
							 <select class="formcontrl" id="courseTypeName">						      
						      </select>	
						       <script id="courseTypeTemplate" type="text/x-jquery-tmpl">
									{{each(i,course) dataList}}
										<option value="{{= course}}">{{= course}}</option>
									{{/each}}							
								</script>				        
						</div>
						<div class="unit-info">
							 <label style="position: relative;top: -85px;">个人简介:</label>
							 <textarea id="userIntro" class="formcontrl" style="height:150px;line-height:18px;"></textarea>							        
						</div> 					
						<div class="buttons" style="width:450px;">
				             <button class="btn btn-save btn-common" type="submit" id="ownlive-save">提交</button>				             
				        </div>   							
	          		</div> 
  				</div>
          	</div> 
           <script type="text/javascript">
            ownliveObj.getCourseType();
           </script>
		   </c:otherwise>
			</c:choose>
            
          	
          		
          		
            </div>       
        </section>     
    </div>


</body>
</html>