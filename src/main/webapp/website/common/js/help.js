var help={};
function getBasePath(){
	 //获取当前网址，如： http://localhost:8083/proj/meun.jsp  
   var curWwwPath = window.document.location.href;  
   //获取主机地址之后的目录，如： proj/meun.jsp  
   var pathName = window.document.location.pathname;  
   var pos = curWwwPath.indexOf(pathName);  
   //获取主机地址，如： http://localhost:8083  
   var localhostPath = curWwwPath.substring(0, pos);  
   //获取带"/"的项目名，如：/proj  
   var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
   return localhostPath + projectName;
};
help.ajaxRequest=function(requestPath,param,fn){
	 var load=layer.load();
	$.ajax({
		url:getBasePath()+requestPath,
		type:"post",
		dataType:"json",			
		data:param,
		success:function(e){			
			layer.close(load);
			if(e.status==10000)
			fn(e);
		},
		error:function(){
			/*alert("服务器异常");*/
			layer.close(load);
		}
		
	});
};
