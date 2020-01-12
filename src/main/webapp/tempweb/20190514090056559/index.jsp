<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>地方政府机构网站模板</title>
<link href="./css/slimize.css" rel="stylesheet">
<link href="./css/tsd-ui.css" rel="stylesheet">
<link href="./css/common.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<script src="./js/jquery.1.71.min.js"></script>
<script src="./js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=basePath%>website/scripts/layer-v2.4/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>website/common/js/help.js"></script>
<script type="text/javascript" src="<%=basePath%>tempweb/20190514090056559/index.js"></script>
<!--[if lt IE 9]>
<script src="./images/html5shiv.min.js"></script>
<![endif]-->

</head>
<style type="text/css">
  .slideBox .prev,.slideBox .next{
    position: absolute; 
    display: block; 
  }
  .slideBox .prev:hover,.slideBox .next:hover{
    filter: alpha(opacity=80);
    opacity: 0.8;
  }
  .slideBox .prevStop{
    display: none;
  }
  .slideBox .nextStop{
    display: none;
  } 
 .slideBox1 .prev,.slideBox1 .next{
    left: 0; 
    top: 50%; 
    width: 32px; 
    height: 64px; 
    margin-top: -32px; 
    background-image: url(./images/mb3-slider-arrow1.png); 
    background-repeat: no-repeat;
  }
  .slideBox1 .next{
    left: auto; 
    right: 0; 
    background-image: url(./images/mb3-slider-arrow3.png); 
  }
.slideBox1 .prev:hover{
   background-image: url(./images/mb3-slider-arrow2.png); 
  }
.slideBox1 .next:hover{
   background-image: url(./images/mb3-slider-arrow4.png); 
}
.news-list .date {
  color: #999;
}
img:hover {
  filter: alpha(opacity=100) !important;
  opacity: 1 !important;
}
</style>
<style>
.slideBox-common { width:1200px !important;}
.slideBox-common .bd li{height:120px !important;width:1200px !important;}
.slideBox-common li img{width:1200px !important;}
.slideBox-common .hd ul { height:12px !important;}
.slideBox-common .hd { bottom:15px !important; }
</style>
<body>
<!-- 通用页头 -->
<!-- <header class="header" >
	<nav class="nav-center clear">
    	
    	<nav class="weather">
    		<iframe name="sinaWeatherTool" src="http://i.tianqi.com/index.php?c=code&amp;id=34&amp;icon=1&amp;num=3&amp;py=zhijiang" width="260" height="28" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no" allowtransparency="true" style="padding-top:7px"></iframe>
    	</nav>
    	
    	
	</nav>
</header> -->

<figure class="banner">
<figure class="center">
		<img src="./images/a1.gif">
	</figure>
	
</figure>

<nav class="nav">
	<ul class="center clear">
		<li class="on">
			<a href="#""><img src="./images/mb23-nav-icon-1.png" class="icon-off" /></a><a href="#""><img src="./images/mb23-nav-icon-1-2.png" class="icon-on" /></a>
			<a href="#">首页</a>
		</li>
		<li>
			<a href="#zsdt/"><img src="./images/mb23-nav-icon-2.png" class="icon-off" /></a><a href="#zsdt/"><img src="./images/mb23-nav-icon-2-2.png"class="icon-on" /></a>
			<a href="#zsdt/">职位投递</a>
		</li>
		
		<li>
			<a href="#tzzn/"><img src="./images/mb23-nav-icon-4.png" class="icon-off" /></a><a href="#tzzn/"><img src="./images/mb23-nav-icon-4-2.png" class="icon-on" /></a>
			</a><a href="#tzzn/">xxxx</a>
		</li>
		<li>
			<a href="#qyxz/"><img src="./images/mb23-nav-icon-5.png"class="icon-off" /></a><a href="#qyxz/"><img src="./images/mb23-nav-icon-5-2.png"class="icon-on" /></a>
			</a><a href="#qyxz/">关于简聘</a>
		</li>
	</ul>
</nav>

<style>
.w1 li a {
    width: 448px !important;
}
.w2 li a {
    width: 192px !important;
}
.w3 li a {
    width: 298px  !important;
}
.nav{    margin-bottom: 0px;
}</style>
<article class="main-news clear" style="margin-top: 15px;">
	<figure id="slideBox" class="slideBox slideBox1">
	    <div class="hd">
	        <ul class="clear"><li></li><li></li><li></li><li></li><li></li></ul>
	    </div>
	    <figure class="bd">
	        <ul>
	            <li class="pic-box pic-box1">
	                <a href="./zsdt/tpxw/201904/t20190411_1621654.shtml" target="_blank"><img src="./images/W020190411343359131715.jpg" height='320' width='500' border='0'/></a>
	                <p><a href="./zsdt/tpxw/201904/t20190411_1621654.shtml" target="_blank" title="一季度项目拉练活动启动">一季度项目拉练活动启动</a></p>
	            </li>
	     
	            <li class="pic-box pic-box1">
	                <a href="./zsdt/tpxw/201904/t20190411_1621636.shtml" target="_blank"><img src="./images/W020190411336517917514.jpg" height='320' width='500' border='0'/></a>
	                <p><a href="./zsdt/tpxw/201904/t20190411_1621636.shtml" target="_blank" title="宜城一季度招商引资成果丰硕">宜城一季度招商引资成果丰硕</a></p>
	            </li>
	     
	            <li class="pic-box pic-box1">
	                <a href="./zsdt/tpxw/201904/t20190403_1618055.shtml" target="_blank"><img src="./images/W020190403332324762313.jpg" height='320' width='500' border='0'/></a>
	                <p><a href="./zsdt/tpxw/201904/t20190403_1618055.shtml" target="_blank" title="无人驾驶汽车来了">无人驾驶汽车来了</a></p>
	            </li>
	     
	            <li class="pic-box pic-box1">
	                <a href="./zsdt/tpxw/201903/t20190327_1613544.shtml" target="_blank"><img src="./images/W020190327563646059427.jpg" height='320' width='500' border='0'/></a>
	                <p><a href="./zsdt/tpxw/201903/t20190327_1613544.shtml" target="_blank" title="高新区赴深圳招商引资硕果累累">高新区赴深圳招商引资硕果累累</a></p>
	            </li>
	     
	            <li class="pic-box pic-box1">
	                <a href="./zsdt/tpxw/201902/t20190227_1584053.shtml" target="_blank"><img src="./images/W020190227371057206499.jpg" height='320' width='500' border='0'/></a>
	                <p><a href="./zsdt/tpxw/201902/t20190227_1584053.shtml" target="_blank" title="项目集中签约开竣工">项目集中签约开竣工</a></p>
	            </li>
	         
	        </ul>
	    </figure>
	    <a class="prev" href="javascript:void(0)"></a>
	    <a class="next" href="javascript:void(0)"></a>
	</figure><!-- slideBox end -->

	<script>
	    jQuery(".slideBox1").slide({mainCell:".bd ul",effect:"left",autoPlay:true,easing:"swing",delayTime:500,mouseOverStop:true,pnLoop:true});
	</script>

	<article class="slideTxtBox slideTxtBox1">
<a class="prev" href="javascript:void(0)"></a>
                            <a class="next" href="javascript:void(0)"></a>
	    <article class="hd">
	        <ul class="clear">
                    <li class="on"><a href="http://www.gov.cn/pushinfo/v150203/index.htm" target="_blank">最新动态</a></li>
		   
	        </ul>
	    </article>
	    <article class="bd">
                
	        <ul class="news-list news-list1 w1" id="articleList">
	        
	        <script id="articleListTemplate" type="text/x-jquery-tmpl"> 
						{{each(i,article) dataList}}

<li><time class="date">2019-05-09</time><a href="./zsdt/gzdt/201905/t20190509_1639650.shtml" target="_blank" title="统一思想 找准方向 凝聚合力 用优质项目引领樊城高质量发展">{{= articleTitle}}</a></li>
                 
						{{/each}}
				</script>
	        
	        	
				           

	        </ul>
	    </article><!-- bd end -->
	</article><!-- slideTxtBox end -->
</article><!-- main-news end -->
<article class="main-col clear">
<figure class="center">
		<img src="./images/jg_1.jpg">
	</figure>
</article>
<article class="main-col clear">
<figure class="center">
		<img src="./images/jg_1.jpg">
	</figure>
</article>
<article class="main-col clear">
<figure class="center">
		<img src="./images/jg_1.jpg">
	</figure>
</article>
<article class="main-col clear">
<figure class="center">
		<img src="./images/jg_1.jpg">
	</figure>
</article>

<footer class="footer">
	<address>
			
	<p>地方政府机构网站模板     </p>
	<p>  电话：0755-132456  党办电话:011-2342045　</p>
	<p>技术支持：某某公司  </p>
	 
	</address>
</footer>




</body>
</html>