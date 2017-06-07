$(function(){
	loginObj.init();
	if(error == "" || error == " "){
		
	}else {
		var errorStr = document.getElementById("errorMsg");
		var errorBox = document.getElementById('J_Message');
		errorStr.innerHTML = error;
		errorBox.style.display = "block";
	}
});

var loginObj = (function(){
	var currentObj;
	//URL
	var loginUrl = contextPath + "/loginAuthenticate.do";
	var mainframeUrl =contextPath + "/index.jsp";
	var imagesPath = contextPath + "/jsp/login/images";

	var userNameValid= false; //用户名是否有效,默认无效；

	return{
		
		init:function(){
			currentObj = this;
			$("#loginBtn").bind("click", function(){
				currentObj.login();
			});
	        $("#userName").bind("focusout", function(){
	        	currentObj.checkUsername();
	        });
		},
		
		//登录
		login:function(){		
			var userName = $("#userName").val();
			var password = $("#password").val();
			var loginBtn = document.getElementById("loginBtn");
			var errorStr = document.getElementById("errorMsg");
			var errorBox = document.getElementById('J_Message');
			var userNameInput = document.getElementById('userName');
			var passwordInput = document.getElementById('password');
			var userConfImg = document.getElementById('usernameConfirmImage');
			
			if (userName=='' && password=='') {
				errorStr.innerHTML = "请输入用户名和密码";
				errorBox.style.display = "block";
				userNameInput.focus();
				return;
			}else if (password=='') {
				errorStr.innerHTML = "请输入密码";
				errorBox.style.display = "block";
				passwordInput.focus();
				return;
			}
			
			if(!userNameValid){
				userNameInput.focus();
				return;
			}
			
			loginBtn.src = imagesPath+"/button_login.gif";
			loginBtn.disabled=true;
			errorBox.style.display = "none";

			//验证用户名和密码
			$.post(loginUrl, {userName:userName, password:password}, function(data){
				var jresult = $.parseJSON(data);
				if(jresult.loginState == 'success'){
					window.location.href = mainframeUrl;
				}else {
					loginBtn.disabled=false;
					loginBtn.src = imagesPath+"/button_login_n.png";
					var errorMsg = "";
					switch(jresult.loginState) 
					{ 
						case "UnknowUser":
							errorMsg="用户名不存在，请重新输入";
							userConfImg.src = "";
							passwordInput.value="";
							userNameInput.focus();
							break; 
						case "WrongPassword":
							errorMsg="密码错误";
							passwordInput.value="";
							passwordInput.focus();
							break; 
						default: 
							errorMsg="服务器错误，请联系管理员";
					}
					errorStr.innerHTML = errorMsg;
					errorBox.style.display = "block";
				}
			});
		},
		
		//验证用户名
		checkUsername:function() {
			var errorBox = document.getElementById('J_Message');
			errorBox.style.display = "none";
			var userName = $("#userName").val();
			if(!userName || userName.length < 0) return;
			$.post(loginUrl, {userName:userName, password:""}, function(data){
				var jresult = $.parseJSON(data);
				if(jresult.loginState == 'WrongPassword'){ 		//验证为WrongPassword时，用户名是正确的
					userNameValid = true;
					$("#userNameTip").removeClass("validateIconFault");
					$("#userNameTip").addClass("validateIconOk");
				}else {	//密码为空不可能为success，其他状态时，用户名均不正确
					userNameValid = false;
					$("#validateTip").removeClass("validateIconFault");
					$("#userNameTip").addClass("validateIconFault");
				}
			});
		}
		
	}
})();