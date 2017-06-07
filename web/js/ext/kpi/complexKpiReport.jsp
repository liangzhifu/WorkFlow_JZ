<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../public/I18N.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>KPI</title>
    <jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
	<style type="text/css">
		
		 td.ux-grid-hd-group-cell {
				background: url(/IOMPROJ/ext/resources/images/default/grid/grid3-hrow.gif) repeat-x bottom;
         }
	</style>
  </head>
  <body> 
  </body>
  <script type="text/javascript" src="/IOMPROJ/ext/kpi/js/default.js"></script>
  <script type="text/javascript" src="/IOMPROJ/ext/kpi/js/complexKpiReport.js"></script>
  <script type="text/javascript">
      var session  = GetSession();
      var _comboItemMap ;
      var _excelTemplateName = GetUrlParameter('excelTemplate');
      var _nodeName = GetUrlParameter('menu_name');
      function GetRequest() { 
   		var url = location.search; 
   		var theRequest = new Object(); 
   		if (url.indexOf("?") != -1) { 
      		var str = url.substr(1); 
      		strs = str.split("&"); 
      		for(var i = 0; i < strs.length; i ++) { 
         		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
      		} 
   		} 
   		return theRequest; 
	  }; 
	  
	  var paramArr = GetRequest();
	  //alert(paramArr);
	  var node_id = paramArr['node_id'];
	  var node_name = paramArr['menu_name'];
	  var excelTemplate = paramArr['excelTemplate'];
	  
      function qryComboItemInfo(){
      		 var object = new Object();  
			 object.parent_node_id = node_id;
			 var params = "[" + Ext.encode(object) + "]";
			 //alert(params);
			 var returnValue = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","getSubReportItem",false,params);
			 //alert('hi!!');
			 _comboItemMap =  returnValue ;
      }
      
      qryComboItemInfo();
      
      
	  var pageMaskAll = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.loadingpage")%>'});
	  var dataMask = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.dataLoading")%>'});
      Ext.onReady(function(){
		     Ext.BLANK_IMAGE_URL = "/IOMPROJ/ext/resources/images/default/s.gif";
			 pageMaskAll.show();
           	Ext.defer(function(){
           		complexKpiReport.init();
			},10);
	         
	  });
	
  </script>
</html>
