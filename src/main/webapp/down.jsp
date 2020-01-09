<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<script type="text/javascript">
  window.onload=function(){
	  if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		    //alert(navigator.userAgent);  
		    window.location.href ="https://www.pgyer.com/xiaoyuan_ios";
		} else if (/(Android)/i.test(navigator.userAgent)) {
		    //alert(navigator.userAgent); 
		    window.location.href ="Android.html";
		};
  }
</script>
<body>
</body>
</html>