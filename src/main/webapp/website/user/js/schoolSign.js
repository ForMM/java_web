$(function(){	
	var basePath=$("#basePath").val();
	

	$("#submitSchool").click(function(){
		schoolSignObj.saveData();
	});
	
	$('#avatar').hover(function(){
		 $("#info-user-bb").show();
	}, function(){
		$("#info-user-bb").hide();
	});
	
});

//==================================================
function changeUserCodeImg(e) {
   var load =  layer.load();
   $.ajaxFileUpload ({
	   url:getBasePath()+"/user/uploadUserCodeImg.do", //你处理上传文件的服务端
	   secureuri:false, //与页面处理代码中file相对应的ID值
	   fileElementId:e.id,
	   dataType: 'json', //返回数据类型:text，xml，json，html,scritp,jsonp五种
	   success: function (data) {
		   if(data.status==10000){ 					  			 		  
			   $("#userCodeImg").attr("src",data.data.filePath);
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

var schoolSignObj = {
		saveData:function(){
			var param = {};			
			var userRealName = $("#userRealName").val();			
			if(userRealName == ""){
				layer.msg("请填写运营联络人！");
				return false;
			}else{
				param.userRealName=userRealName;	
			}
			
			var userCode = $("#userCode").val();			
			if(userCode == ""){
				layer.msg("请填写身份证号码！");
				return false;
			}else{
				param.userCode=userCode;	
			}
			
			var userCodeImg = $("#userCodeImg").attr("src");
			if(userCodeImg == "" || userCodeImg == null || userCodeImg == "undefined"){
				layer.msg("请上传身份证图片！");
				return false;
			}else{
				param.userCodeImg=userCodeImg;	
			}	
			
			var phoneNum = $("#userPhoneNum").val();			
			if(phoneNum == ""){
				layer.msg("请填写联系方式！");
				return false;
			}else{
				param.phoneNum=phoneNum;	
			}
			param.orgLisenceFlag = true;
			help.ajaxRequest("/user/modifyUserSchool.do",param,function(e){
//				console.log(JSON.stringify(e));								
				location.href=getBasePath()+"/user/schoolSignupPage.do";
			});	
			
		}
}
