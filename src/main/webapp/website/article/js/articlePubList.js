
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
			
			help.ajaxRequest("/article/userAriclePubList.do",param,function(e){
//				 console.log(JSON.stringify(e));
				 pageCount=e.data.pageCount;				
				 $("#articleListTemplate").tmpl( e.data ).appendTo("#articleList");
				 var userType = e.data.userType;
				 console.log(userType);
				 if(userType == 3){ 
					 articleListObj.articleTopClick();
				 }else{
					 $(".article-top").remove();
				 }
				 articleListObj.articleInfoPage();
				 articleListObj.articleEditClick();
				 
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
		        		articleListObj.getDataList(page);
		        	}else{
		        		layer.msg("没有更多内容了");
		        	}
		        }  
		    });  
		}
}




