<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>立合展示</title>
		<%@ include file="/jsp/public/common.jsp"%>
		<script type="text/css" src="/WorkFlow/js/ext/ux/css/Spinner.css"></script>
		<style type="text/css"> 
			.x-grid-row-orange .x-grid3-cell{
			    background-color:orange;
			}
			.x-grid-row-red .x-grid3-cell{
			    background-color:red;
			    color:white;
			}
			.x-grid-row-yellow .x-grid3-cell{
			    background-color:yellow;
			}
			.x-grid-row-blue .x-grid3-cell{
			    background-color:rgb(59,190,255);
			}
			.x-grid3-row-checker, .x-grid3-hd-checker {
			    background-position:2px 2px;
			    background-repeat:no-repeat;
			    background-color:transparent;
			}
			
			.x-grid3-row-table {
				table-layout:fixed;
			}
			
			.x-grid3-row-checker, table {
			    overflow:hidden;
			}
			.x-grid3-row td, .x-grid3-summary-row td{ 
				text-align: left;
				word-break:break-all; word-wrap:break-word;
			}
			.x-grid3-row td div{
				word-break:break-all; word-wrap:break-word;
				white-space:normal;
			}
			.x-grid3-hd-row td{
				text-align: center;
				
			}
		</style>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/DateTimeField.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ext-basex.js?vesion=2'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/Spinner.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/ext/ux/SpinnerField.js'/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
		</script>
		
		<script type="text/javascript" src="<c:url value='/js/module/screen/screenShow2.js?version=13'/>"></script>
	</head>
	<body>
		<div id="mainDiv"></div>
	</body>
</html>