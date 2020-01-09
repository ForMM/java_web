$(function(){	
	
	$("#modify-btn").click(function(){
		$("#info-see").hide();
		$("#info-modify").show();
	});
	$("#info-close").click(function(){
		location.href=getBasePath()+"/user/accountPage.do";
	});
	$("#info-save").click(function(){
		accountObj.saveData();
	});
	
});

//==================================================
function change(e) {
   var load =  layer.load();
   $.ajaxFileUpload ({
	   url:getBasePath()+"/user/uploadHeaderImg.do", //你处理上传文件的服务端
	   secureuri:false, //与页面处理代码中file相对应的ID值
	   fileElementId:e.id,
	   dataType: 'json', //返回数据类型:text，xml，json，html,scritp,jsonp五种
	   success: function (data) {
		   if(data.status==10000){ 					  			 		  
			   $("#header-img").attr("src",data.data.filePath);
				  //  alert(JSON.stringify(data));
				    layer.msg("上传成功");
              }else if(data.status==10081){
              /* alert(data.info);*/
               	 layer.msg(data.info);
                 top.location.href=getBasePath();
              }else if(data.status==403){
                  /* alert(data.info);*/
             	 layer.msg(data.info);
              }else{
             	 layer.msg(data.info);
              }  
		 
	    layer.close(load);
	   },
	   error:function(){
		   layer.msg("上传失败!");
		   layer.close(load);
	   }
	 });
}

var accountObj = {
		saveData:function(){
			var param = {};			
			var headImg = $("#header-img").attr("src");
			if(headImg == "" || headImg == null || headImg == "undefined"){
				layer.msg("请上传机构logo！");
				return false;
			}else{
				param.headImg=headImg;	
			}
			var userName = $("#orgName").val();
			if(userName == ""){
				layer.msg("请填写机构名称！");
				return false;
			}else{
				param.userName=userName;	
			}
			var userIntro = $("#orgIntro").val();
			if(userIntro == ""){
				layer.msg("请简要写下机构简介！");
				return false;
			}else{
				param.userIntro=userIntro;	
			}
				
			var userAddr = $("#userAddr").val();
			if(userAddr == ""){
				layer.msg("请填写机构地址！");
				return false;
			}else{
				param.userAddr=userAddr;	
			}
			var phoneNum = $("#userPhoneNum").val();			
			if(phoneNum == ""){
				layer.msg("请填写联系方式！");
				return false;
			}else{
				param.phoneNum=phoneNum;	
			}
			param.orgLisenceFlag = false;
			help.ajaxRequest("/user/modifyUserOrg.do",param,function(e){
//				console.log(JSON.stringify(e));								
				location.href=getBasePath()+"/user/accountPage.do";
			});	
			
		},
		getOrgUsers:function(){
			help.ajaxRequest("/user/getOrgUsers.do","",function(e){
//				console.log(JSON.stringify(e));	
				var orgUsers = e.data.dataList;
				for(var i=0;i<orgUsers.length;i++){
					var orgUser = orgUsers[i];
					$("#org-teacher-list").append(accountObj.createHtml(orgUser.headImg,orgUser.userName,orgUser.schoolName,orgUser.userIntro));
				}
			});	
		},
		createHtml:function(headImg,userName,schoolName,intro){
			var unitOrgUser = '<div class="account-info-unit">'+
				'<div class="avatar">'+
			'<img src="'+headImg+'">'+
			'</div>'+
			'<div class="org-info"> '+
			'<div class="unit-info">'+
			' <label>名字:</label>'+
			' <span>'+userName+'</span>'+
		    '</div>'+
		    '<div class="unit-info">'+
		    '<label>学校:</label>'+
		    '<span>'+schoolName+'</span>'+
		    '</div>'+
		    '<div class="unit-info">'+
		    '<label>介绍:</label>'+
		    '<span>'+intro+'</span>'+
		    '</div>'+
		    '</div></div>';
			return unitOrgUser;
		}
}
