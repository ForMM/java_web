
$(function(){	
	var status = $(".sub-selected").attr("submittype");
	var articleType = $(".sub-selected").attr("articleType");
	schoolArticleListObj.initSubTab();
	schoolArticleListObj.getDataList(page,pageSize,status,articleType);
	schoolArticleListObj.scrollList();
	
});

var page=1;
var pageSize=10;
var pageCount=1;
var schoolArticleListObj = {	
		initSubTab:function(){
			
			$(".sub-item").unbind().click(function(){
				$(".sub-item").removeClass("sub-selected");
				$(this).addClass("sub-selected");
				var status = $(this).attr("submittype");
				var articleType = $(this).attr("articleType");				
				$("#articleList").empty();
				page=1;
				pageSize=10;
				pageCount=1;
				schoolArticleListObj.getDataList(page,pageSize,status,articleType);
			});
		},
		getDataList:function(page,pageSize,submitStatus,articleType){
			var param = {};		
			param.page = page;
			param.pageSize = pageSize;
			param.submitStatus = submitStatus;
			param.articleType = articleType;
		
			help.ajaxRequest("/article/mySchoolAricleList.do",param,function(e){
				 console.log(JSON.stringify(e));
				 pageCount=e.data.pageCount;
				 $("#articleListTemplate").tmpl( e.data ).appendTo("#articleList");
				 schoolArticleListObj.articleInfoPage();	
				 schoolArticleListObj.articleEditClick();
				 schoolArticleListObj.articleTopClick();
			});	
		},
		articleInfoPage:function(){
			 $(".intro,.article-intro").click(function(){		
					var articleId = $(this).attr("articleId");
					location.href=getBasePath()+"/article/articleInfoPage.do?articleId="+articleId;
				});
		},
		articleEditClick:function(){
			$(".article-edit").unbind().click(function(event){
				event.preventDefault();
				event.stopPropagation();
				var articleId = $(this).attr("articleId");
				location.href=getBasePath()+"/article/updateArticlePage.do?articleId="+articleId;
			});
		},	
		articleTopClick:function(){
			$(".article-top").unbind().click(function(event){
				event.preventDefault();
				event.stopPropagation();
				var articleId = $(this).attr("articleId");
				
				var confirmIndex = layer.confirm('您确定要置顶此文章？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						
						help.ajaxRequest("/article/articleTop.do",{"articleId":articleId},function(e){
//							 console.log(JSON.stringify(e));
							 layer.msg("置顶成功!");
							 layer.close(confirmIndex);
						});

					}, function(){
						layer.close(confirmIndex);
					});
				
					
				
			});
		},
		scrollList:function(){
			$(window).scroll(function(){                
		        var scrollh = $(document).height();  
		        var scrollTop=Math.max(document.documentElement.scrollTop||document.body.scrollTop);  
		        if((scrollTop + $(window).height()) >= (scrollh-1)){  
		        	page=page+1;
		        	if(page<=pageCount){
		        		schoolArticleListObj.getDataList(page,pageSize);
		        	}else{
		        		layer.msg("没有更多内容了");
		        	}
		        	
		        }  
		    });  
		}
}




