
$(function(){
	
	articleListObj.getDataList(page);
	articleListObj.scrollList();
	
});

var page=1;
var pageSize=10;
var pageCount=1;
var articleListObj = {	
		getDataList:function(page){
			var userId = $("#userId").val();
			var param = {};
			param.userId = userId;
			param.page = page;
			param.pageSize = pageSize;
			
			help.ajaxRequest("/article/userAricleDraftList.do",param,function(e){
//				 console.log(JSON.stringify(e));
				 pageCount=e.data.pageCount;				
				 $("#articleListTemplate").tmpl( e.data ).appendTo("#articleList");
				 articleListObj.articleInfoPage();
				 articleListObj.articleEditClick();
				 articleListObj.articleDelClick();
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
		articleDelClick:function(){
			$(".article-del").unbind().click(function(event){
				event.preventDefault();
				event.stopPropagation();
				var articleId = $(this).attr("articleId");
				
				var confirmIndex = layer.confirm('您确定要删除？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						help.ajaxRequest("/article/delArticle.do",{"articleId":articleId},function(e){
//							 console.log(JSON.stringify(e));
							location.href=getBasePath()+"/article/articleDraftListPage.do";
							layer.close(confirmIndex);
						});	

					}, function(){
						layer.close(confirmIndex);
					});
				
								
				
//				
			});
		},
		scrollList:function(){
			$(window).scroll(function(){                
		        var scrollh = $(document).height();  
		        var scrollTop=Math.max(document.documentElement.scrollTop||document.body.scrollTop);  
		        if((scrollTop + $(window).height()) >= scrollh){  
		        	page=page+1;
		        	if(page<=pageCount){
		        		articleListObj.getDataList(page);
		        	}else{
		        		layer.msg("没有更多内容了");
		        	}
		        	
		        }  
		    });  
		}
}




