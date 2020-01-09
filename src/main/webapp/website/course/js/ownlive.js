$(function(){
	$('#avatar').hover(function(){
		 $("#info-user-bb").show();
	}, function(){
		$("#info-user-bb").hide();
	});
	
	$("#ownlive-save").click(function(){
		ownliveObj.saveData();
	});
})

//==================================================
function changeAuth(e) {
   var load =  layer.load();
   $.ajaxFileUpload ({
	   url:getBasePath()+"/course/uploadAuthImg.do", //你处理上传文件的服务端
	   secureuri:false, //与页面处理代码中file相对应的ID值
	   fileElementId:e.id,
	   dataType: 'json', //返回数据类型:text，xml，json，html,scritp,jsonp五种
	   success: function (data) {
		   if(data.status==10000){ 					  			 		  
			   $("#authImg").attr("src",data.data.filePath);
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


var ownliveObj = {		
		getCourseType:function(){
			help.ajaxRequest("/course/courseType.do","",function(e){
//				console.log(JSON.stringify(e));	
				$("#courseTypeName").empty();
				$("#courseTypeTemplate").tmpl( e.data ).appendTo("#courseTypeName"); 			  	
			});	
		},
		saveData:function(){
			var param = {};
			var authImg = $("#authImg").attr("src");
			if(authImg == "" || authImg == null || authImg == "undefined"){
				layer.msg("请上传认证资料！");
				return false;
			}else{
				param.authImg=authImg;	
			}
			var IDNum = $("#IDNum").val();
			if(IDNum == ""){
				layer.msg("身份证不能为空！");
				return false;
			}else{
				param.IDNum=IDNum;	
			}
			
			var courseTypeName = $("#courseTypeName").val();
			if(courseTypeName == ""){
				layer.msg("请选择开课类型！");
				return false;
			}else{
				param.courseTypeName=courseTypeName;	
			}
			
			var intro = $("#userIntro").val();
			if(intro == ""){
				layer.msg("请填写个人简介!");
				return false;
			}else{
				param.intro=intro;	
			}
//			console.log(JSON.stringify(param));		
			help.ajaxRequest("/course/lecturerApply.do",param,function(e){
				console.log(JSON.stringify(e));								
				location.href=getBasePath()+"/course/ownlivePage.do";
			});	
			
		}
		
}
