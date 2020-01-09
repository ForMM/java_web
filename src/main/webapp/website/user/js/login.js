$(function(){	
	var basePath=$("#basePath").val();
	
	$("#loginBtn").unbind('click').click(function(){
		userObject.login();
	});
	$("#loginName,#loginPassword").bind('keyup',function(event){
		 var e = event || window.event || arguments.callee.caller.arguments[0];
		 if(e && e.keyCode==13){ 
			 userObject.login();
        }
	});
	
	$("#loginout").unbind('click').click(function(){	
		userObject.logout();
	});
	
	$("#resetPsd").unbind('click').click(function(){	
	 	$('#loginModal').modal('hide');
	 	$('#resetPsdModal').modal('show');
	});
	
	$("#reset").unbind('click').click(function(){	
		userObject.resetPsd();
	});
	indexObject.getUserInfo();
	indexObject.subnavSelect();
});


var userObject = {
	login:function(){
		var loginName=$("#loginName").val();
		var loginPassword = $("#loginPassword").val();
	
        var param = {};
        param.account=loginName;
        param.password=loginPassword;
    	help.ajaxRequest("/user/login.do",param,function(e){
    		
    		
    		
    		/*var userType=e.data.userType;
    		var authStatus=e.data.authStatus;
    		if(userType == 2){
    			if(authStatus ==0){
    				location.href=getBasePath()+"/user/orgSignupPage.do";
    			}else{
    				location.href=getBasePath()+"/user/userCenterPage.do";
    			}
    			
    		}else if(userType == 3){
    			//添加学校认证界面
    			if(authStatus ==0){
    				location.href=getBasePath()+"/course/ownlivePage.do";
    			}else{
    				location.href=getBasePath()+"/user/userCenterPage.do";
    			}
    		}else{
    			location.href=getBasePath()+"/user/userCenterPage.do";
    		}*/
    		
    		location.href=getBasePath()+"/user/userCenterPage.do";	
    		
    		
		});		
	},
	resetPsd:function(){
		var param = {};
		param.mobilePhone = $("#phoneNumReset").val();
		param.checkCode = $("#validCodeReset").val();
		param.newPwd = $("#passwordReset").val();	
//			console.log(JSON.stringify(e));										  
		help.ajaxRequest("/user/resetPwd.do",param,function(e){
		  	$('#resetPsdModal').modal('hide');
		});
	},
	logout:function(){
		//防止服务器没有响应，客户端删除sessionid保证退出
		delCookie("JSESSIONID");
		help.ajaxRequest("/user/loginOut.do","",function(e){
//			console.log(JSON.stringify(e));								
			location.href=getBasePath()+"/";
		});	
		$("#userInfoLi").show();
	}
}
