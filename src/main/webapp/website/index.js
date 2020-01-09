


var indexObject = {
		initSchoolSelect: function(){
			$("#schoolList").children("li").click(function(){
				var that = $(this);
				$("#school").attr("schoolId",$(that).attr("schoolId"));
				$("#school").val($(that).html());
				$(that).parent().hide();				
			})			
		},
		getSchoolsByName:function(){
			var schoolName = $("#school").val();			
			
			help.ajaxRequest("/user/schoolList.do",{"schoolName":schoolName},function(e){
//				console.log(JSON.stringify(e));								
			  	$("#schoolListTemplate").tmpl( e.data.dataList ).appendTo("#schoolList"); 
			  	$("#schoolList").show();
			  	indexObject.initSchoolSelect();
			  	
			});		
		},
		getUserInfo:function(){
			help.ajaxRequest("/user/getUserInfo.do","",function(e){
				//console.log(JSON.stringify(e));					
				var currentUser = e.data.user;
			  	if(currentUser != null && currentUser != ""){
			  		$('#userAccount').html(currentUser.userAccount);				  					  		
			  		$("#userInfoLi").show();	
			  		$("#signupLi,.login-li").hide();
			  		var str = '<a href="'+getBasePath()+'/user/userCenterPage.do">个人管理中心</a>';
			  		$("#userCenter").empty().append(str);
			  		
			  	}else{			  		
			  		$(".login-li").show();
			  	}
			  	
			});	
		},
		subnavSelect:function(){
			$(".subnav-li").click(function(){
				var that = this;
				$(".subnav-li").removeClass("selected");
				$(that).addClass("selected");
			});
		},
		showTips:function(){
	        //{"backdrop":"static"}点击背景不会消失  
	        $('#loginModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
	            // 是弹出框居中。。。  
	            var $modal_dialog = $(this.$element[0]).find('.modal-dialog');  
	            //获取可视窗口的高度  
	            var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
	            //得到dialog的高度  
	            var dialogHeight = $modal_dialog.height();  
	            //计算出距离顶部的高度  
	            var m_top = (clientHeight - dialogHeight)/2;  
//	            console.log("clientHeight : " + clientHeight);  
//	            console.log("dialogHeight : " + dialogHeight);  
//	            console.log("m_top : " + m_top);  
	            $modal_dialog.css({'margin': m_top + 'px auto'});  
	        });  
		}
}
