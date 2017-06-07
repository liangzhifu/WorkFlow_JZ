<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>立合修改</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/javascript" src="<c:url value='/js/laydate/laydate.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ext-basex.js'/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
			var userListJson = '${userList}';
			var agreementJSON = '${agreementJSON}';
			var orderId = '${orderId}';
			var agreementId = '${agreementId}';
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/agreement/agreementEdit2.js?version=25'/>"></script>
	</head>
	<body>
		<div id="mainDiv" ></div>
	</body>
</html>