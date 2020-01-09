<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<script type="text/javascript">
  window.onload=function(){
	  var path=document.getElementById("path").value;
		top.location.href=path;
  }
	

</script>
<body>
<input type="hidden" id="path" value="<%=basePath%>"> 
</body>
</html>