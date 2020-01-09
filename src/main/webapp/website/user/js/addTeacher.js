$(function(){	
	$("#addTeacher-btn").click(function(){
		addTeacherObj.addUserForOrg();
	});
	
});

var addTeacherObj = {
		addUserForOrg:function(){
			var param = {};
			var teacherName = $("#teacherName").val();
			if(teacherName == ""){
				layer.msg("请添加讲师姓名！");
				return false;
			}
			var phoneNum = $("#phoneNumber").val();
			if(phoneNum == ""){
				layer.msg("请添加讲师手机号！");
				return false;
			}
			var teacherIntro = $("#teacherIntro").val();
			if(teacherIntro == ""){
				layer.msg("请添加讲师手机号！");
				return false;
			}
			param.teacherName = teacherName;
			param.phoneNum=phoneNum;
			param.teacherIntro = teacherIntro;
			
			help.ajaxRequest("/user/addUserForOrg.do",param,function(e){
				console.log(JSON.stringify(e));	
				window.parent.frames["teacherListIframe"].location=getBasePath()+"/user/teacherListPage.do";
				window.parent.frames["teacheraddIframe"].location=getBasePath()+"/user/addTeacherPage.do";
				
				parent.laytabchange();
				
			});	
			
		},
		deleteOrgUser:function(id){
			help.ajaxRequest("/user/delOrgUser.do",{"userOrgId":id},function(e){
//				console.log(JSON.stringify(e));	
				location.href=getBasePath()+"/user/teacherPage.do";
			});	
		}
}
