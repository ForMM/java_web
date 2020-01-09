$(function(){	
	$("#addTeacher-btn").click(function(){
		orgTeacherObj.addUserForOrg();
	});
	
	orgTeacherObj.getOrgUsers();
	
	
	
});

var orgTeacherObj = {
		getOrgUsers:function(){
			help.ajaxRequest("/user/getOrgUsers.do","",function(e){
//				console.log(JSON.stringify(e));	
				var orgUsers = e.data.dataList;
				for(var i=0;i<orgUsers.length;i++){
					var orgUser = orgUsers[i];
					$("#org-teacher-list").append(orgTeacherObj.createHtml(orgUser.headImg,orgUser.userName,orgUser.userPhone,orgUser.schoolName,orgUser.userIntro,orgUser.userOrgId));
				}
				$(".delTeacher-btn").click(function(){
					var that = this;
					var index = layer.confirm('你确定要删除', {
						  btn: ['确定','取消'] //按钮
						}, function(){
							var userOrgId=$(that).attr("userOrgId");
							orgTeacherObj.deleteOrgUser(userOrgId);
							layer.close(index);
						}, function(){
							layer.close(index);
						});
					
				
				});
			});	
		},
		createHtml:function(headImg,userName,phonenum,schoolName,intro,userOrgId){
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
			' <label>手机号:</label>'+
			' <span>'+phonenum+'</span>'+
		    '</div>'+
		    '<div class="unit-info">'+
		    '<label>学校:</label>'+
		    '<span>'+schoolName+'</span>'+
		    '</div>'+
		    '<div class="unit-info">'+
		    '<label>介绍:</label>'+
		    '<span>'+intro+'</span>'+
		    '</div>'+
		    '</div>'+
		    '<div><input type="button" value="删除" userOrgId="'+userOrgId+'"  class="delTeacher-btn"></div'+
		    '</div>';
			return unitOrgUser;
		},
		addUserForOrg:function(){
			var phoneNum = $("#phone-number").val();
			if(phoneNum == ""){
				layer.msg("请添加老师的手机号！");
			}else{
				help.ajaxRequest("/user/addUserForOrg.do",{"phoneNum":phoneNum},function(e){
					console.log(JSON.stringify(e));	
					location.href=getBasePath()+"/user/teacherPage.do";
				});	
			}
		},
		deleteOrgUser:function(id){
			help.ajaxRequest("/user/delOrgUser.do",{"userOrgId":id},function(e){
//				console.log(JSON.stringify(e));	
				location.href=getBasePath()+"/user/teacherPage.do";
			});	
		}
}
