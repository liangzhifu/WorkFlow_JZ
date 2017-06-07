<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>确认人管理</title>
		<%@ include file="/jsp/public/common.jsp"%>	
		<link rel="stylesheet" type="text/css" href="<c:url value='/js/jquery/easyui/themes/bootstrap/easyui.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/js/jquery/easyui/themes/icon.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/style/resource/queryForm.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/style/resource/linkBearing.css'/>">
		<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.easyui.min.js'/>"></script> 
		<script type="text/javascript" src="<c:url value='/js/jquery/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/plugin/jquery.helper.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ext-basex.js'/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/task/confirmUser.js?version=25'/>"></script>
	</head>
	<body onContextMenu="return false;"
		style="width: 100%; height: 100%; overflow: hidden">
	<div id="mainDiv"></div>
	</body>
</html>