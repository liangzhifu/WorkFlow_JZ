<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">

	var contextPath = "${pageContext.request.contextPath}";
	var url = contextPath + "/viewMainframe.do";
	
	var isIEBrower = false;//\sMSIE\s/.test(navigator.userAgent);//判断是否IE
	if(isIEBrower){
		var params = "fullscreen=yes,toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no,menubar=no,left=0,top=0,height="
			+ (window.screen.availHeight-30) +",width="+ (window.screen.availWidth-10);
		var newWin = window.open(url,"均胜工作流系统",params);
		closewin();
		newWin.focus();
	}else{
		window.location.href = url;
	}
	function closewin(){   
	    var browserName=navigator.appName;   
	    var ua=navigator.userAgent;
	    if (browserName=="Netscape") {   
	        var opened=window.open('about:blank','_self');  
	        opened.opener=null;  
	        opened.close();  
	    } else if(browserName=="Microsoft Internet Explorer") {  
	    	window.open('','_self');  
	        window.opener=null;
	        window.close();   
	    }  
	}
</script>
</head>
<body>
	<object id="min" type="application/x-oleobject"	classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11">
		<param name="Command" value="Minimize">
	</object>
	<object id="max" type="application/x-oleobject" classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11">
		<param name="Command" value="Maximize">
	</object>
</body>
</html>