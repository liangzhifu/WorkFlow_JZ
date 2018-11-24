<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>人员管理</title>
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
		<script type="text/javascript" src="<c:url value='/js/module/system/user.js?version=36'/>"></script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'west',collapsible:true, width:250, border:false, title:'组织树'" style="background:#F3F5F8;">
			<div class="easyui-layout" data-options="fit:true">  
		    	<div id="orgTree"  data-options="region:'center'"></div>  
		   </div>    
		</div>
		<div data-options="region:'center',iconCls:'icon-grid', border:false">
			<div id = "userCenterLayout">
				<div data-options="region:'north',border:true">
					<div id="userSearchPanel"></div>
				</div>
				<div data-options="region:'center',iconCls:'icon-grid' ,border : false">
					<table id="userList"></table>
				</div>
			</div>
		</div>
	</body>
</html>