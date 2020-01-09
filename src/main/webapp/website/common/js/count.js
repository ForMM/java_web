$(function(){
	
	var countStart=0;
    var countEnd=0;
    var options = {useEasing : true,useGrouping : true,separator : ',',decimal : '.',prefix : '',suffix : ''};  	
    function countLastProject(){
    	$.ajax({
			url:base.basePath()+"/project/countLastProjects.do",
			dataType:"json",
			type:"post",
			data:{},
			success:function(e){
				//alert(JSON.stringify(e));
				countEnd= e.data.count;
				 var demo = new CountUp("myTargetElement", countStart, countEnd, 0, 3, options);
				 demo.start();
				 countStart=countEnd;
			},
			error:function(){
				/*alert("服务器异常");*/
			}
		});
     }
    countLastProject();
});
