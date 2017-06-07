<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>确认</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
		</script>
		<script type="text/javascript" src="<c:url value='/js/module/system/newPassword.js?version=23'/>"></script>
	</head>
	<body>
		<div id="changepasswordPanel" ></div>
	</body>
</html>