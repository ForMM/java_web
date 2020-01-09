$(function(){	
	var basePath=$("#basePath").val();
	
	
	indexObject.getUserInfo();
	
	$('#selectSchool').bind('input propertychange keyup', function() {
		$("#selectSchoolList").html("");
		accountObj.getSchoolsByName();
	});
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
			param.userName=$("#userRealName").val();		
			param.schoolId=$("#selectSchool").attr("schoolId");
			param.grade=$("#userGrade").val();
			param.major=$("#userMajor").val();
			param.potrait=$("#header-img").attr("src");
			
			help.ajaxRequest("/user/modifyUserInfo.do",param,function(e){
//				console.log(JSON.stringify(e));								
				location.href=getBasePath()+"/user/accountPage.do";
			});	
			
		},
		initSchoolSelect: function(){
			$("#selectSchoolList").children("li").click(function(){
				var that = $(this);
				$("#selectSchool").attr("schoolId",$(that).attr("schoolId"));
				$("#selectSchool").val($(that).html());
				$(that).parent().hide();				
			})			
		},
		getSchoolsByName:function(){
			var schoolName = $("#selectSchool").val();			
			help.ajaxRequest("/user/schoolList.do",{"schoolName":schoolName},function(e){
//				console.log(JSON.stringify(e));								
			  	$("#selectSchoolListTemplate").tmpl( e.data.dataList ).appendTo("#selectSchoolList"); 
			  	$("#selectSchoolList").show();
			  	accountObj.initSchoolSelect();
			  	
			});		
		},
}
