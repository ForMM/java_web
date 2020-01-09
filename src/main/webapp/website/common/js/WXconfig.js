var wechat={
		initConfig:function(basePath){
			 var curWwwPath = location.href.split('#')[0];  
			  $.ajax({
					url:basePath+"user/wechatConfig.do",
					type:"post",
					dataType:"json",
					data:{"url":curWwwPath},
					success:function(e){
						if(e.status==10000){
							wx.config({
							    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
							    appId: e.data.appId, // 必填，公众号的唯一标识
							    timestamp: e.data.timestamp, // 必填，生成签名的时间戳
							    nonceStr: e.data.noncestr, // 必填，生成签名的随机串
							    signature: e.data.signature,// 必填，签名，见附录1
							    jsApiList: ["onMenuShareTimeline",
							                "onMenuShareAppMessage",
							                "onMenuShareQQ",
							                "onMenuShareWeibo",
							               " onMenuShareQZone"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
							});
						}else{
							layer.open({
							    content: e.info,
							    style: 'background-color:#09C1FF; color:#fff; border:none;',
							    time: 2
							});
						}
					},
					error:function(){
						/*alert("服务器异常");*/
					}
					
				});
		},
		onMenuShareAppMessage:function(title,desc,link,imgUrl,type,dataUrl){
			wx.ready(function(){
				wx.onMenuShareAppMessage({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    type: type, // 分享类型,music、video或link，不填默认为link
				    dataUrl:dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
				    success: function () { 
				        // 用户确认分享后执行的回调函数
				    	//alert("分享成功");
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    	//alert("分享失败");
				    }
				});
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			});
			wx.error(function(res){
			    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			});
		},
		onMenuShareTimeline:function(title,link,imgUrl){
			wx.ready(function(){
				wx.onMenuShareTimeline({
				    title: title, // 分享标题
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    success: function () { 
				        // 用户确认分享后执行的回调函数
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    }
				});
			});
			wx.error(function(res){
			});
		},
		onMenuShareQQ:function(title,desc,link,imgUrl){
			wx.ready(function(){
				wx.onMenuShareQQ({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    success: function () { 
				       // 用户确认分享后执行的回调函数
				    },
				    cancel: function () { 
				       // 用户取消分享后执行的回调函数
				    }
				});
			});
			wx.error(function(res){
			});
		},
		onMenuShareWeibo:function(title,desc,link,imgUrl){
			wx.ready(function(){
				wx.onMenuShareWeibo({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    success: function () { 
				       // 用户确认分享后执行的回调函数
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    }
				});
			});
			wx.error(function(res){
			});
		},
		onMenuShareQZone:function(title,desc,link,imgUrl){
			wx.ready(function(){
				wx.onMenuShareQZone({
				    title: title, // 分享标题
				    desc: desc, // 分享描述
				    link: link, // 分享链接
				    imgUrl: imgUrl, // 分享图标
				    success: function () { 
				       // 用户确认分享后执行的回调函数
				    },
				    cancel: function () { 
				        // 用户取消分享后执行的回调函数
				    }
				});
			});
			wx.error(function(res){
			});
		}
};
