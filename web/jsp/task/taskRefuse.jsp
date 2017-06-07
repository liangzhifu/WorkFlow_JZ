<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>回复</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.easyui.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/plugin/jquery.json.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/plugin/jquery.helper.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/laydate/laydate.js'/>"></script>
		<script type="text/javascript" src="/WorkFlow/js/ext/ux/DateTimeField.js"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
			var taskOrderJSON = '${taskOrderJSON}';
			var orderId = '${orderId}';
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/task/taskRefuse.js?version=24'/>"></script>
	</head>
	<body>
		<div id="mainDiv" ></div>
	</body>
</html>