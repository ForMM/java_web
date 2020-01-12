<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
 <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>

<body>
<jsp:include page="../website/header.jsp"></jsp:include>


  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>移动设备、桌面端的组合响应式展现</legend>
  </fieldset>
   
  <div class="layui-row">
    <div class="layui-col-xs12 layui-col-md8">
      <div class="grid-demo grid-demo-bg1">移动：12/12、桌面：8/12</div>
    </div>
    <div class="layui-col-xs6 layui-col-md4">
      <div class="grid-demo">移动：6/12、桌面：4/12</div>
    </div>
    <div class="layui-col-xs6 layui-col-md12">
      <div class="grid-demo grid-demo-bg2">移动：6/12、桌面：12/12</div>
    </div>
  </div>
</body>
</html>