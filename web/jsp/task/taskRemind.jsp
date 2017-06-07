<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>任务提醒周期设置</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/css" src="<c:url value='/js/ext/ux/css/Spinner.css'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/DateTimeField.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/Spinner.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/SpinnerField.js'/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/task/taskRemind.js?version=27'/>"></script>
	</head>
	<body>
		<div id = "mainDiv"></div>
	</body>
</html>