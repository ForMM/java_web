
$(function(){
	var articleId = $("#articleId").val();
	articleInfoObj.getArticleInfo();
	
	articleInfoObj.getCommentList(articleId);
	
	articleInfoObj.scrollList(articleId);
	
});

var countText = "";
var page=1;
var pageSize=10;
var pageCount=1;
var articleInfoObj = {			
		getArticleInfo:function(){
			var articleId = $("#articleId").val();
			
			help.ajaxRequest("/article/articleDetail.do",{"articleId":articleId},function(e){
//				console.log(JSON.stringify(e));
				
				$("#article-title").html(e.data.articleDetail.title);
				$("#article-time").html(e.data.articleDetail.createDate);
				$("#article-tag").html(e.data.articleDetail.tagName);
				
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
				if(e.data.articleDetail.articleType != 4){
					$("#contributeArticle").unbind().bind("click",function(){
						articleInfoObj.contributeArticle(articleId);
					})
					$("#contribute-div").unbind().show();
				}
				
				if(e.data.articleDetail.submitStatus == 1){
					$("#contributeArticle").val("已投稿").unbind();
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
					var commentDto = commentList[i];
					articleInfoObj.commentHtml(commentDto);
				}
						
			});	
		},
		commentHtml:function(commentDto){
			
			var commentLi = '<li class="c-item">'+
	        	'<a target="_blank" class="avatar-wrap" href="">'+
	        	'<img alt="" src="'+commentDto.headImg+'"></a>' +
	        	'<div class="c-content">'+
	        	'<div class="c-user-info">'+
	        	'<a class="c-user-name" target="_blank" href="">'+commentDto.userName+'</a>'+
	        	'<span class="c-create-time">'+commentDto.commentTime+'</span>'+
	        	'</div>'+
	        	'<p>'+commentDto.content+'</p>'+
	        	'<span class="replay" targetId="'+commentDto.targetId+'" commentId="'+commentDto.commentId+'" style="color:#406599;cursor: pointer;">回复</span>';
			
			commentLi += '<span class="count" targetId="'+commentDto.targetId+'" commentId="'+commentDto.commentId+'">'+"&nbsp;"+commentDto.subComments+'条回复<i class="layui-icon" style="font-size: 14px; color: #1E9FFF;">&#xe61a;</i></span>';
			commentLi = commentLi+'</div></li>';
			$("#comment-ul").append(commentLi);
			
			$("span.count").unbind().click(function(){
				var that = this;
				var targetId = $(that).attr("targetId");
				var commentId = $(that).attr("commentId");
				
				var param = {};
				param.articleId=targetId;
				param.commentId=commentId;
				
				if($(that).hasClass("count")){
					countText=$(that).html();
					help.ajaxRequest("/article/subCommentList.do",param,function(e){
//						console.log(JSON.stringify(e));
						var datalist = e.data.dataList;
						for(var i=datalist.length-1;i>=0;i--){
							var subcomment = datalist[i];
							
							var htmlstr = articleInfoObj.replyCommentList(subcomment);
							$(that).after(htmlstr).html("&nbsp;收起回复").removeClass("count").addClass("hideCount");
						}
						
						$("span.c-reply").unbind().click(function(){
							$(".textarea-div").remove();
							var that = this;
							var targetId = $(that).attr("targetId");
							var commentId = $(that).attr("commentId");
							var htmlStr = '<div class="textarea-div"><textarea name="desc" placeholder="写下您的回复" class="layui-textarea"></textarea><button class="layui-btn submitBtn " commentId="'+commentId+'" targetId="'+targetId+'">回复</button></div>';
							$(that).after(htmlStr);
							
							$(".submitBtn").unbind().click(function(){
								var targetId = $(this).attr("targetId");
								var commentId = $(this).attr("commentId");
								var commentContent = $(this).prev().val();
								
								if(commentContent == ""){
									layer.msg("请填写你的评论!");
									return false;
								}
								
								var that = this;
								var param = {};
								param.targetId = targetId;
								param.commentId = commentId;
								param.commentContent = commentContent;
								
								help.ajaxRequest("/article/replyComment.do",param,function(e){
									console.log(JSON.stringify(e));
									layer.msg("回复成功!");
									$(that).parent().slideToggle("slow");
								});	
							});
						});
						
					});	
				}else{
					$(that).empty().html(countText).removeClass("hideCount").addClass("count");
					$("div.c-reply-comment").hide();
				}
				
			});
			
			$("span.replay").unbind().click(function(){
				$(".textarea-div").remove();
				var that = this;
				var targetId = $(that).attr("targetId");
				var commentId = $(that).attr("commentId");
				var htmlStr = '<div class="textarea-div"><textarea name="desc" placeholder="写下您的回复" class="layui-textarea"></textarea><button class="layui-btn submitBtn " commentId="'+commentId+'" targetId="'+targetId+'">回复</button></div>';
				$(that).after(htmlStr);
				
				$(".submitBtn").unbind().click(function(){
					var targetId = $(this).attr("targetId");
					var commentId = $(this).attr("commentId");
					var commentContent = $(this).prev().val();
					
					if(commentContent == ""){
						layer.msg("请填写你的评论!");
						return false;
					}
					
					var that = this;
					var param = {};
					param.targetId = targetId;
					param.commentId = commentId;
					param.commentContent = commentContent;
					help.ajaxRequest("/article/replyComment.do",param,function(e){
						console.log(JSON.stringify(e));
						layer.msg("回复成功!");
						$(that).parent().slideToggle("slow");
					});	
				});
			});
		},
		replyCommentList:function(subcomment){
			
			var htmlStr = 
			'<div class="c-reply-comment">'+
			'<a target="_blank" class="avatar-wrap" href="/user/3604461507/">'+
			'<img alt="" src="'+subcomment.headImg+'">'+
			' </a><div class="c-content">'+
			' <div class="c-user-info">'+
			'<a target="_blank" class="c-user-name" href="/user/3604461507/">'+subcomment.userName+'</a>'+
			'<span class="c-create-time">'+subcomment.commentTime+'</span></div>'+
			'  <p>'+subcomment.content+'</p>'+
			'  <div class="c-footer-action">'+
			'<span class="c-reply" targetId="'+subcomment.targetId+'" commentId="'+subcomment.commentId+'">回复</span>'+
			' </div></div></div>';
		    
			return htmlStr;
		},
		contributeArticle:function(articleId){
			help.ajaxRequest("/article/submitArticle.do",{"articleId":articleId},function(e){
//				console.log(JSON.stringify(e));
				$("#contributeArticle").val("已投稿").attr("disabled","disabled");
				 layer.msg("文章投稿成功，等待审核！");						
			});	
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




