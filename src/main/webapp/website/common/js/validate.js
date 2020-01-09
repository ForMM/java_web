var validateObject = {
		checkPhone: function(phoneNum){
			var phone = /^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/;
			if(!phone.test(phoneNum)){			  
			    return false;
			}
			return true;
		},
		checkPassword:function(pwd){
			var password = /^[\da-zA-Z!@#$%^&*()_+|{}?><\-\]\\[\/]{6,16}$/;
			if(!password.test(pwd)){			  
			    return false;
			}
			return true;
		}
		
}