
$(function(){
	var articleId = $("#articleId").val();
	articleInfoObj.getArticleInfo(articleId);
	
	articleInfoObj.getCommentList(articleId);	
	
	articleInfoObj.scrollList(articleId);
	
});

var page=1;
var pageSize=10;
var pageCount=1;
var articleInfoObj = {			
		getArticleInfo:function(articleId){
			
			help.ajaxRequest("/article/articleDetail.do",{"articleId":articleId},function(e){
//				console.log(JSON.stringify(e));
				
				$("#article-title").html(e.data.articleDetail.title);
				$("#article-time").html(e.data.articleDetail.createDate);
				$("#article-tag").html(e.data.articleDetail.tagName);
				$("#article-user").html(e.data.articleDetail.grade+"*"+e.data.articleDetail.major+"*"+e.data.articleDetail.userName);
				
				var content = JSON.parse(e.data.articleDetail.content); 
				for(var j = 0 ;j<content.length;j++){
					var imgUrl = content[j].imgUrl;
					var con = content[j].content;
					var img=$('<img  src="'+imgUrl+'"/>');
					var p = $('<p>'+con+'</p>');
					var htmlStr=$('<div> </div>');
				
					if(typeof imgUrl == 'undefined'){
						img=$('');
					}
					if(typeof con == 'undefined'){
						p = $('<p></p>');
					}
					htmlStr.append(img);
					htmlStr.append(p);
					$(htmlStr).appendTo($("#article-content"));					
				}			
				if(e.data.articleDetail.submitStatus == 1 && e.data.articleDetail.articleType == 1){
					$("#contribute-div").append('<input type="button" value="通过审核" id="approveArticle" class="contribute"><input type="button" value="不通过审核" id="notApproveArticle" class="contribute">').show();
					
					$("#approveArticle").click(function(){
						var articleId = $("#articleId").val();
						help.ajaxRequest("/article/approveArticle.do",{"articleId":articleId},function(e){
//							console.log(JSON.stringify(e));
							 layer.msg("操作成功！");						
						});	
					});	
					
					$("#notApproveArticle").click(function(){
						var articleId = $("#articleId").val();
						help.ajaxRequest("/article/notApproveArticle.do",{"articleId":articleId},function(e){
//							console.log(JSON.stringify(e));
							 layer.msg("操作成功！");						
						});	
					});	
				}
			
			});	
		},
		getCommentList:function(articleId){
			
			var param = {};
			param.articleId = articleId;
			param.page = page;
			param.pageSize = pageSize;
			
			help.ajaxRequest("/article/commentList.do",param,function(e){
//				console.log(JSON.stringify(e));
				$("#comment-count").html("<em>"+e.data.total+"&nbsp;</em>条评论");
				var commentList = e.data.dataList;
				pageCount = e.data.pageCount;				
				for(var i=0;i<commentList.length;i++){					
					articleInfoObj.commentHtml(commentList[i].headImg,commentList[i].userName,commentList[i].commentTime,commentList[i].content);
				}
						
			});	
		},
		commentHtml:function(headerImg,userName,creatreTime,content){
			var commentLi = '<li class="c-item">'+
	        	'<a target="_blank" class="avatar-wrap" href="">'+
	        	'<img alt="" src="'+headerImg+'">' +
	        	'</a>'+
	        	'<div class="c-content">'+
	        	'<div class="c-user-info">'+
	        	'<a class="c-user-name" target="_blank" href="">'+userName+'</a>'+
	        	'<span class="c-create-time">'+creatreTime+'</span>'+
	        	'</div>'+
	        	'<p>'+content+'</p>'+
	        	'</div>'+
	        	'</li>';
			$("#comment-ul").append(commentLi);
		},	
		scrollList:function(articleId){
			$(window).scroll(function(){                
		        var scrollh = $(document).height();  
		        var scrollTop=Math.max(document.documentElement.scrollTop||document.body.scrollTop);  
		        if((scrollTop + $(window).height()) >= scrollh){  
		        	page=page+1;
		        	if(page<=pageCount){
		        		articleInfoObj.getCommentList(articleId);
		        	}else{
		        		layer.msg("没有更多内容了");
		        	}
		        	
		        }  
		    });  
		}
}




