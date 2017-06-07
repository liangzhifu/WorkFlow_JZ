<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>定单详情</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
			var taskOrderJSON = '${taskOrderJSON}';
			var taskOrderId = '${orderId}';
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/task/taskDetail.js?version=34'/>"></script>
	</head>
	<body>
		<div id="mainDiv" ></div>
	</body>
</html>