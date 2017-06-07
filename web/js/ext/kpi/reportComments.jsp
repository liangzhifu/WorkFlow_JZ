<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../public/I18N.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>报表备注说明</title>
    <jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
	<style type="text/css">
		
		 td.ux-grid-hd-group-cell {
				background: url(/IOMPROJ/ext/resources/images/default/grid/grid3-hrow.gif) repeat-x bottom;
         }
	</style>
  </head>
  <body> 
  </body>
  <script type="text/javascript">
  	  var params ;
      var session  = GetSession();
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
	  
	  params = GetRequest();
	  
	  var pageMaskAll = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.loadingpage")%>'});
	  var dataMask = new Ext.LoadMask(Ext.getBody(), {msg:'<%=getI18NResource("bandCommon.dataLoading")%>'});
      Ext.onReady(function(){
		    Ext.BLANK_IMAGE_URL = "/IOMPROJ/ext/resources/images/default/s.gif";
			pageMaskAll.show();
           	var viewPort = new Ext.Viewport({
           		layout: 'border',
           		renderTo: Ext.getBody(),
           		items: [{
           				region: 'center', 
						id:"contentPanel",
						xtype: 'panel', 
						split: false,
						margins: '5 5 5 0',
						minWidth: 400,
						minHeight: 400,
						layout: 'fit' ,
           				items:[
								{
								xtype:'panel',
								layout:'fit',
								frame:true,
								buttons:[{text:'保存',id:'isSave',listeners:{click:saveComments}},{text:'关闭',listeners:{click:cancel}}],
								buttonAlign : 'center',
								items:[{xtype: "textarea",style:"font-size:18px",layout:'fit', id:'comments' ,name: "intro", readOnly:false }]
								
								}
							  ]
           			  }]
           	
           	});
           	
           	Ext.defer(function(){
           		var nodeId = params['nodeId'] ;
           		var str = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","getReportComments",false,nodeId);
           		Ext.getCmp("comments").setRawValue(str);
           		
           		if (!session.hasPriv("canEditReportComment")){
                    Ext.getCmp("isSave").hide();
				}
           		
			},10);
           	
           	pageMaskAll.hide();
           	
			
	         
	  });
	  function cancel(){
	  	window.close();
	  }
	  function saveComments(){
	    var str = Ext.getCmp("comments").getRawValue();
	    
	    var nodeId = params['nodeId'];
	    var obj = new Object() ;
	    obj['nodeId'] = nodeId;
	    obj['XML'] = str ;
	    obj = "[" + Ext.encode(obj) + "]";
	    callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","modifyReportComments",false,obj);
		
	  };
  </script>
</html>
