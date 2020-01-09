

//==================================================
var n=0;
function change(e) {
   var load =  layer.load();
   $.ajaxFileUpload ({
	   url:getBasePath()+"/course/uploadCourseImg.do", //你处理上传文件的服务端
	   secureuri:false, //与页面处理代码中file相对应的ID值
	   fileElementId:e.id,
	   dataType: 'json', //返回数据类型:text，xml，json，html,scritp,jsonp五种
	   success: function (data) {
		   if(data.status==10000){ 
			   $("#courseImg-div").empty();
			   var imgHtml =  '<img id="courseImg_'+n+'" src="'+data.data.filePath+'"/>';
			   $("#courseImg-div").append(imgHtml);	
			   n++;
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

var coursePage=1;
var coursePageSize=10;
var coursePageCount=1;
var courseObj = {
		initDataPicker:function(){
			$('.form_datetime').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
		        showMeridian: 1
		    });
		},
		getCourseType:function(){
			help.ajaxRequest("/course/courseType.do","",function(e){
//				console.log(JSON.stringify(e));	
				$("#courseTypeName").empty();
				$("#courseTypeTemplate").tmpl( e.data ).appendTo("#courseTypeName"); 	
			});	
		},
		saveData:function(){
			var param = {};
			var title = $("#course-title").val();
			if(title == ""){
				layer.msg("课堂标题不能为空！");
				return false;
			}else{
				param.title=title;	
			}
			var intro = $("#course-intro").val();
			if(intro == ""){
				layer.msg("课堂介绍不能为空！");
				return false;
			}else{
				param.intro=intro;	
			}
			
			var authImgSize = $("#courseImg-div").children("img").size();
			if(authImgSize == 0){
				layer.msg("你还没有上传课堂图片!");
				return false;
			}else{
				var imgStr = "";
				var imgEleArr = $("#courseImg-div").children("img");
				for(var i=0;i<authImgSize;i++){
					imgStr = imgStr+ $(imgEleArr[i]).attr("src") + "#" ;					
				}
				imgStr = imgStr.substring(0,imgStr.length-1);
				param.imgs=imgStr;	
			}
	
			var beginTime = $("#dtp_input1").val();
			if(beginTime == ""){
				layer.msg("请填上课堂的开始时间！");
				return false;
			}else if(Date.parse(beginTime) < Date.parse(new Date())){
				layer.msg("课堂的开始时间不能比现在时间还小！");
				return false;
			}else{
				param.beginTime=beginTime;	
			}
			
			var endTime = $("#dtp_input2").val();
			if(endTime == ""){
				layer.msg("请填上课堂的结束时间！");
				return false;
			}else{
				param.endTime=endTime;	
			}
			
			if(Date.parse(endTime) < Date.parse(beginTime)){
				layer.msg("开始时间不能大于结束时间！");
				return false;
			}
		
			var type = $("input[name='radChl']:checked").val();
			if(type == "" || typeof type =="undefined"){
				layer.msg("请选择课堂类别！");
				return false;
			}else{
				param.type=type;	
			}
			
			var phoneEle = $("#phoneNum");
			if(typeof phoneEle != 'undefined'){
				if(phoneEle.val() == ""){
					layer.msg("请填写分配老师的手机号");
					return false;
				}else{
					param.phoneNum = phoneEle.val();
				}
			}
			
			var confirmIndex = layer.confirm('您确定要发布？一旦发布，不能修改！', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					help.ajaxRequest("/course/addCourse.do",param,function(e){
//						 console.log(JSON.stringify(e));
						window.parent.frames["courseaddIframe"].location=getBasePath()+"/course/addCoursePage.do";
						window.parent.frames["courseListIframe"].location=getBasePath()+"/course/courseListPage.do";
						
						layer.close(confirmIndex);
						parent.ccc();
					});	

				}, function(){
					layer.close(confirmIndex);
				});
		},
		pptImgs:{},
		getUserCourseList:function(page,pageSize){
			courseObj.pptImgs={};
			var param={};
			param.userId = $("#userId").val();	
			param.page=page;
			param.pageSize=pageSize;
			help.ajaxRequest("/course/userCourseList.do",param,function(e){
				console.log(e);
				coursePageCount = e.data.pageCount;
				$("#course-div").empty("");
				$("#courseListTemplate").tmpl( e.data ).appendTo("#course-div");	
				
				layui.use('upload', function(){
					var dataList = e.data.dataList;
					for(var i=0;dataList!=null&&i<dataList.length;i++){
						var course = dataList[i];
						var courseId = course.courseId;
						var eleid= "#file_"+courseId;
						var hisBckgroud = $("#photos_"+courseId).css("background-color");
						  layui.upload({
						    url: getBasePath()+"/course/uploadCourseFile.do?courseId="+courseId //上传接口
						    ,
						    elem: eleid,
						    title: '上传PPT'
						    ,ext: 'ppt|pptx',
						    before:function(){
						    	layerIndex=layer.load(0,{time: 10*1000}); 
						    },
						    success: function(res){ //上传成功后的回调
//						      console.log(res);
						    	layer.close(layerIndex);
						      if(res.status==10000){
						    	  layer.msg("PPT上传成功！");
						    	  var cId = res.data.courseId;
						    	  courseObj.pptImgs[cId]=res.data.pptImgs;
						    	  $("#photos_"+cId).css("background-color",hisBckgroud).click(function(){
						    		  layer.photos({
										    photos: courseObj.pptImgs[cId]
										    ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
										  });
						    	  });
						      }else{
						    	  layer.msg("PPT上传失败！");
						      }
						      
						    }
						  });
						  courseObj.pptImgs[courseId]=course.pptImgs;
//						  console.log(course.pptImgs);
						  if(course.pptImgs.data == undefined){
							  $("#photos_"+courseId).css("background-color","gray");
						  }
						}
					
					});
				
				  $(".courseImg-btn").click(function(){
					  var courseId=this.id.split("_")[1];
						layer.photos({
						    photos: courseObj.pptImgs[courseId]
						    ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
						  });
					});
				
				layui.use(['laypage', 'layer'], function(){
					  var laypage = layui.laypage
					  ,layer = layui.layer;
					  
					  laypage({
						    cont: 'coursepage'
						    ,pages: coursePageCount //总页数
						    ,groups: 5 //连续显示分页数
						    ,curr: page || 1, 
						    jump: function(e, first){ //触发分页后的回调			    	
						        if(!first){ //一定要加此判断，否则初始时会无限刷新
						        	var pageNow = e.curr;					        	
						        	courseObj.getUserCourseList(pageNow,coursePageSize);
						        }
						    }
						  });
					});
			});	
			
		},
		initUpdateData:function(courseId){
			var courseId = $("#courseId").val();
			help.ajaxRequest("/course/courseDetail.do",{"courseId":courseId},function(e){
//				console.log(JSON.stringify(e));		
				$("#course-title").val(e.data.courseDetail.courseName);	
				$("#course-intro").val(e.data.courseDetail.courseIntro);	
								
				var imgsArr = e.data.courseDetail.imgs;	
				var imgHtml = "";
				for(var i=0;i<imgsArr.length;i++){					
					imgHtml =  '<img id="courseImg_'+i+'" src="'+imgsArr[i]+'"/>';					   
				}
				$("#courseImg-div").append(imgHtml);	
				
				var radios = $("input[name='radChl']");
				for(var i=0;i<radios.size();i++){	
					if(e.data.courseDetail.courseType == $(radios[i]).val()){
						$(radios[i]).prop("checked","checked");
					}										   
				}
				
				$('#begin-time').datetimepicker('update', e.data.courseDetail.begin);
				$('#end-time').datetimepicker('update', e.data.courseDetail.end);
			});	
		},
		updateCourse:function(){
			var courseId = $("#courseId").val();
			var param = {};
			param.courseId = courseId;
			var title = $("#course-title").val();
			if(title == ""){
				layer.msg("课堂标题不能为空！");
				return false;
			}else{
				param.title=title;	
			}
			var intro = $("#course-intro").val();
			if(intro == ""){
				layer.msg("课堂介绍不能为空！");
				return false;
			}else{
				param.intro=intro;	
			}
			
			var authImgSize = $("#courseImg-div").children("img").size();
			if(authImgSize == 0){
				layer.msg("你还没有上传课堂图片!");
				return false;
			}else{
				var imgStr = "";
				var imgEleArr = $("#courseImg-div").children("img");
				for(var i=0;i<authImgSize;i++){
					imgStr = imgStr+ $(imgEleArr[i]).attr("src") + "#" ;					
				}
				imgStr = imgStr.substring(0,imgStr.length-1);
				param.imgs=imgStr;	
			}
	
			var beginTime = $("#dtp_input1").val();
			if(beginTime == ""){
				layer.msg("请填上课堂的开始时间！");
				return false;
			}else if(Date.parse(beginTime) < Date.parse(new Date())){
				layer.msg("课堂已经是过去式了，不允许修改！");
				return false;
			}else{
				param.beginTime=beginTime;	
			}
			
			var endTime = $("#dtp_input2").val();
			if(endTime == ""){
				layer.msg("请填上课堂的结束时间！");
				return false;
			}else{
				param.endTime=endTime;	
			}
			
			if(Date.parse(endTime) < Date.parse(beginTime)){
				layer.msg("开始时间不能大于结束时间！");
				return false;
			}
		
			var type = $("input[name='radChl']:checked").val();
			if(type == "" || typeof type =="undefined"){
				layer.msg("请选择课堂类别！");
				return false;
			}else{
				param.type=type;	
			}
						
			help.ajaxRequest("/course/modifyCourse.do",param,function(e){
//				console.log(JSON.stringify(e));		
				location.href=getBasePath()+"/course/courseListPage.do";
			});	
		},
		scrollList:function(){
			$(window).scroll(function(){                
		        var scrollh = $(document).height();  
		        var scrollTop=Math.max(document.documentElement.scrollTop||document.body.scrollTop);  
		        if((scrollTop + $(window).height()) >= scrollh){  
		        	coursePage=coursePage+1;		        	
		        	if(coursePage<=coursePageCount){
		        		courseObj.getUserCourseList(coursePage,coursePageSize);
		        	}else{
		        		layer.msg("没有更多内容了");
		        	}
		        	
		        }  
		    });  
		}
}
