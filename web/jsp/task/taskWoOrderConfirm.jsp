<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>部门确认</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/javascript" src="<c:url value='/js/laydate/laydate.js'/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
			var taskOrderJSON = '${taskOrderJSON}';
			var orderId = '${orderId}';
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/task/taskWoOrderConfirm.js?version=53'/>"></script>
	</head>
	<body>
		<div id="mainDiv" ></div>
	</body>
</html>