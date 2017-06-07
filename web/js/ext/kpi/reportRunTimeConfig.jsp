<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../public/I18N.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ºþ±±´úÎ¬RunTimeConfig</title>
		<jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
		<style type="text/css">
			td.ux-grid-hd-group-cell {
				background: url(/IOMPROJ/ext/resources/images/default/grid/grid3-hrow.gif) repeat-x bottom;
			}
		</style>
	</head>
	<body> 
	</body>
	<script language="javascript" src="/IOMPROJ/public/script/helper.js"></script>
	<script language="javascript" src="/IOMPROJ/public/script/XmlInter.js"></script>
	<script type="text/javascript" src="/IOMPROJ/ext/kpi/js/reportRunTimeConfig.js"></script>
	<script type="text/javascript" src="/IOMPROJ/ext/kpi/js/default.js"></script>
	<script type="text/javascript">
		var session  = GetSession();
		var pageMaskAll = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.loadingpage")%>'});
		var dataMask = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.dataLoading")%>'});
		//var privCode = getQueryString("privCode");
		//var requestObj = GetRequest();
		Ext.onReady(function(){
			Ext.BLANK_IMAGE_URL = "/IOMPROJ/ext/resources/images/default/s.gif";
			pageMaskAll.show();
			Ext.defer(function(){
				kpiConfig.init();
			},10);
		});
	</script>
</html>
