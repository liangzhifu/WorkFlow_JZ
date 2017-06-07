<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>高田天津汽配制造有限公司</title>
<link rel="stylesheet" href="<c:url value='/jsp/login/css/login.css?version=2'/>">
<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.min.js'/>"></script>
<script type="text/javascript"> 
	var contextPath = "${pageContext.request.contextPath}";
	var error = '${error}';
	(function(){
		if(window != top){
			top.location.reload();
		}
	})();
</script>
<script type="text/javascript" src="<c:url value='/js/module/login/login.js?version=6'/>"></script>
<c:url var="imagePath" value="/jsp/login/images" />
</head>
<body>

<div class="top">
	高田品质4M变化管理
</div>
<div class="middle">
	<div class="content">
		<div class="loginfram">
              <div class="login-title">系统登录
              </div>
              <div id="J_Message" style="display: none;" class="login-msg">
              	<p id="errorMsg" class="error">请输入用户名和密码</p>
              </div>
              <div class="input">
                    <div id="usernameArea" class="user">
                        <input class="TxtuserCssClass" id="userName" maxLength=33 name="userName"  placeholder="请输入账号" value="" />
                       	<div id="userNameTip" class="validateTip"></div>
                    </div>
                    
                    <div id="passwordArea" class="password">
                      <input class="TxtuserCssClass TxtpasswordCssClass" id="password" maxLength=33 name="password" type="password" placeholder="请输入密码" value="" />
                    </div>
              </div>
             
              <div class="button-login">
				<input id="loginBtn" type="image" src="${imagePath}/button_login_n.png" onMouseMove="src='${imagePath}/button_login_h.png'" onMouseOut="src='${imagePath}/button_login_n.png'"/>
              </div>
		</div>
	</div>
</div>

</body>
</html>
