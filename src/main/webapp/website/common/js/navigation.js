$(function(){
	navObject.initSignBtn();
	navObject.initGradeList($("#userSignup").children("div").children("div").children(".grade"));
	
})	

var navObject = {
	initSignBtn:function(){		
		$("#userSignup").children("div").children("div").children(".signup").click(function(){
			navObject.signupUser();
		});
		
		$("#schoolSignup").children("div").children("div").children(".signup").click(function(){
			navObject.signupSchool();
		});
		
		$("#orgSignup").children("div").children("div").children(".signup").click(function(){
			navObject.signupOrg();
		});
		
		$("#userSignup").children("div").children("div").children(".school").bind('input propertychange keyup', function() {
			$("#userSignup").children("div").children("div").children(".school-ul").html("");
			navObject.getSchoolsByName();
		});
		
		$("#schoolSignup").children("div").children("div").children(".school").bind('input propertychange keyup', function() {
			$("#schoolSignup").children("div").children("div").children(".school-ul").html("");
			navObject.schoolUserByName();
		});
		
		
		$("#resetPsd").unbind('click').click(function(){	
		 	$('#loginModal').modal('hide');
		 	$('#resetPsdModal').modal('show');
		});
		
	},
	initGradeList:function(ele){
		 var year = new Date().getFullYear();
		 var num = 30;		 
		 for(var i=0;i<=num;i++){
			 var value = (year-i)+"级";
			 var htmlStr = '<option value="'+value+'">'+value+'</option>';
			 $(ele).append(htmlStr);
		 }
	},
	initSchoolSelect: function(school,schoolist){
		$(schoolist).children("li").click(function(){			
			var that = $(this);
			$(school).attr("schoolId",$(that).attr("schoolId"));
			$(school).val($(that).html());
			$(that).parent().hide();				
		})			
	},
	getSchoolsByName:function(){
		var school = $("#userSignup").children("div").children("div").children(".school");
		var schoolist = $("#userSignup").children("div").children("div").children(".school-ul");
		var schoolName = school.val();			
		
		help.ajaxRequest("/user/schoolList.do",{"schoolName":schoolName},function(e){
//			console.log(JSON.stringify(e));	
			$("#userSignup").children("div").children("div").children(".schoolListTemplate").tmpl( e.data.dataList ).appendTo(schoolist); 
		  	$(schoolist).show();
			navObject.initSchoolSelect(school,schoolist);
		});		
	},
	schoolUserByName:function(){
		var school = $("#schoolSignup").children("div").children("div").children(".school");
		var schoolist = $("#schoolSignup").children("div").children("div").children(".school-ul");
		var schoolName = school.val();			
		
		help.ajaxRequest("/user/schoolList.do",{"schoolName":schoolName},function(e){
//			console.log(JSON.stringify(e));	
			$("#schoolSignup").children("div").children("div").children(".schoolListTemplate").tmpl( e.data.dataList ).appendTo(schoolist); 
		  	$(schoolist).show();
			navObject.initSchoolSelect(school,schoolist);
		});		
	},
	getFormElement:function(formId,ele){
		var id = "#"+formId;
		var eleclass = "."+ele;
		var element = $(id).children("div").children("div").children(eleclass);
		return element;
	},
	signupUser:function(){
		var param = {};
		
		var password=navObject.getFormElement("userSignup","password").val();
		if(password.length<8 || password.length>16){
			layer.msg("密码长度必须为8-16位！");
			return false;
		}
		
		param.mobilePhone = navObject.getFormElement("userSignup","phoneNum").val();
		param.checkCode = navObject.getFormElement("userSignup","validCode").val();
		param.password = password;
		param.userName = navObject.getFormElement("userSignup","userName").val();
		param.schoolId = navObject.getFormElement("userSignup","school").attr("schoolid");	
		param.major = navObject.getFormElement("userSignup","major").val();
		param.grade = navObject.getFormElement("userSignup","grade").val();	
		help.ajaxRequest("/user/regist.do",param,function(e){
			location.href=getBasePath()+"/course/ownlivePage";
		});	
	},
	signupSchool:function(){
		var param = {};
		
		var password=navObject.getFormElement("schoolSignup","password").val();
		if(password.length<8 || password.length>16){
			layer.msg("密码长度必须为8-16位！");
			return false;
		}
		
		param.mobilePhone = navObject.getFormElement("schoolSignup","phoneNum").val();
		param.checkCode = navObject.getFormElement("schoolSignup","validCode").val();
		param.password = password;
		param.schoolId = navObject.getFormElement("schoolSignup","school").attr("schoolid");	
		help.ajaxRequest("/user/registSchool.do",param,function(e){
			location.href=getBasePath()+"/course/ownlivePage";
		});	
	},
	signupOrg:function(){
		var param = {};
		
		var password=navObject.getFormElement("orgSignup","password").val();
		if(password.length<8 || password.length>16){
			layer.msg("密码长度必须为8-16位！");
			return false;
		}
		
		param.mobilePhone = navObject.getFormElement("orgSignup","phoneNum").val();
		param.checkCode = navObject.getFormElement("orgSignup","validCode").val();
		param.password = password;
		help.ajaxRequest("/user/registOrg.do",param,function(e){
			location.href=getBasePath()+"/course/ownlivePage";
		});	
	},
}