$(function(){
	
	var basePath=getBasePath();
	var checkurl=basePath+"/user/sendCheckCode.do";
	var sendCheckCodeBtnValue = $("#sendCheckCodeBtn").val();
	 var mobilephone="";
	 var checkCodeType="";
	 var geetestFlag=true;
	$("input[sendCheckCode='sendCheckCode']").unbind('click').click(function () {
		 initTimeout=60;
		 mobilephone=$(this).parent("div").parent("div").siblings("div").eq(0).children("div").children("input").val();
		 checkCodeType = $(this).attr("checkCodeType");
		 ele=this;
		if(!$(this).hasClass("sendCheckCodefalse")){
			if(geetestFlag){
				var load = layer.load();
				$.ajax({//此请求页面只能请求一次
			        // 获取id，challenge，success（是否启用failback）
			        url: basePath+"/pc-geetest/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
			        type: "get",
			        dataType: "json",
			        success: function (data) {
			        	layer.close(load);
			            // 使用initGeetest接口
			            // 参数1：配置参数
			            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
			            initGeetest({
			                gt: data.gt,
			                challenge: data.challenge,
			                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
			                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
			            }, handlerPopupMobile);
			            $("#mask, #popup-captcha-mobile").show();
			            geetestFlag=false;
			        }
			    });	
			}else{
				 $("#mask, #popup-captcha-mobile").show();
			}
        
		}
   });
    var handlerPopupMobile = function (captchaObj) {
        // 将验证码加到id为captcha的元素里
        captchaObj.appendTo("#popup-captcha-mobile");
       // captchaObj.bindOn("#sendCheckCodeBtn");
        //拖动验证成功后两秒(可自行设置时间)自动发生跳转等行为
        captchaObj.onSuccess(function () {
            var validate = captchaObj.getValidate();
    		
            $.ajax({
                url: checkurl, // 进行二次验证
                type: "post",
                dataType: "json",
                data: {
                    // 二次验证所需的三个值
                	mobilePhone:mobilephone,
                	type:checkCodeType,
                    geetest_challenge: validate.geetest_challenge,
                    geetest_validate: validate.geetest_validate,
                    geetest_seccode: validate.geetest_seccode
                },
                success: function (e) {
                	$("#mask, #popup-captcha-mobile").hide();
                	if(e.status==10000){
                		sendCheckCodeTimeout();	
                		 
                    } else {
                    	 layer.msg(e.info);
                    }
                }
            });
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    
	var sendCheckCodeFlag=true;
	var sendCheckCodeBtnValue = $("#sendCheckCodeBtn").val();
	var ele=null;
    
	var initTimeout=60;
	var timeout=initTimeout;
	function sendCheckCodeTimeout(){			
		timeout=timeout-1;
		if(timeout>0){	
			$(ele).val(timeout+"秒后重新获取").addClass("sendCheckCodefalse");
			setTimeout(sendCheckCodeTimeout,1000);
		}else{
			timeout=initTimeout;
			$(ele).val(sendCheckCodeBtnValue).removeClass("sendCheckCodefalse");	
			sendCheckCodeFlag=true;			
		}		
	}
});