<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="dbfound-tags" prefix="d"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<d:includeLibrary />
	</head>
	<script type="text/javascript" src="/IOMPROJ/public/script/helper.js"></script>
	<script type="text/javascript">
     var session = GetSession();
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
   function exportExcel(){
		var _excelTemplateName="Export_circuitfinishdetail";
		var _nodeName= "定单完成情况查询";
		var json_data=operationForm.getData();
		var json_parme={};  
		if(json_data==null){
           return ;
		}
		     json_parme.datemode =json_data.datemode;  
			 if(json_parme.datemode=="1"){
			 json_parme.staticMonth =json_data.staticMonth; 
	         } 
			 if(json_parme.datemode=="0"){
			 json_parme.startdate =json_data.startdate;
			 json_parme.enddate =json_data.enddate;
			 }
			 json_parme.querymode =json_data.querymode_id; 		
			 json_parme.busitype = json_data.busitype_id; 
			 json_parme.custspec =json_data.custspec_id; 
			 json_parme.myOrgId= session.org.orgId;
             json_parme.curStaffArea = session.area.areaId;

	
        var params = new Object();
		params['myOrgId'] = session.org.orgId;
		params['curStaffArea'] = session.area.areaId ;
         $D.request("/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=findTopAreaId",params,function(res){
         // debugger;

	          var retVal =res.outParam.retVal;              
				retVal=Ext.decode(retVal);
				// alert(retVal);
		    if(retVal[0]==false&&node_id==1358){
			    json_parme.curStaffArea = retVal[1];
			    json_parme.myAreaId= retVal[1];
				var url = '/IOMPROJ/ExcelExportServlet';
				var sqlVal =Ext.encode(json_parme);
				sqlVal = encodeURI(encodeURI(sqlVal));
				var paramstr = 'templateCode='+_excelTemplateName+'_prov'+'&fileName='+encodeURI(encodeURI(_nodeName))+'&sqlParams='+sqlVal;
				window.open(url+'?'+paramstr); 	
			}else{
				var url = '/IOMPROJ/ExcelExportServlet';
				var sqlVal =Ext.encode(json_parme);
				sqlVal = encodeURI(encodeURI(sqlVal));
				var paramstr = 'templateCode='+_excelTemplateName+'&fileName='+encodeURI(encodeURI(_nodeName))+'&sqlParams='+sqlVal;
				window.open(url+'?'+paramstr); 	
				}
          });
		
	  }


       function explain(){
	    var url = "reportComments.jsp" ;
	  	window.open(url+"?nodeId="+103);
	  }
	    function query() {
			 var data=operationForm.getData();
		     var datemode=data.datemode;
			 var querymode =data.querymode_id; 		
			 var busitype = data.busitype_id; 
			 var custspec =data.custspec_id; 
			 var startdate = data.startdate; 
		     var enddate = data.enddate; 		
			 var staticMonth = data.staticMonth; 
            var w=900;
	        var h=600;
            var myOrgId= session.org.orgId;
            var curStaffArea = session.area.areaId ;
		//	alert(myOrgId+""+curStaffArea);
	   var sFeatures="fullscreen=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=1,width="+w+",height="+h+"";
       window.open("../kpi/orderCaseQuery.jsp?querymode="+querymode+"&datemode="+datemode+"&startdate="+startdate+"&enddate="+enddate+"&staticMonth="+staticMonth+ "&custspec="+custspec+"&busitype="+busitype+"&myOrgId="+myOrgId+"&curStaffArea="+curStaffArea+"&node_id="+node_id+"" ,"work",sFeatures);
		}

		var sexItmes = [ {
			boxLabel : '按月份',
			checked : true,
			name : 'datemode',
			inputValue : '1'
		},{
			boxLabel : '按时间',
			name : 'datemode',
			inputValue : '0'
		} ];

	</script>
	     <d:dataStore fields="busitype_id,busitype_name" autoLoad="true" id="busitypeStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=busitype" />
		<d:dataStore fields="querymode_id,querymode_name" autoLoad="true" id="querymodeStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=querymode" />
        <d:dataStore fields="custspec_id,custspec_name" autoLoad="true" id="custspecStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=custspec" />

	<body >
		<d:form  style="margin:0px"  id="operationForm" title="定单完成情况查询" labelWidth="80">
			<d:line columnWidth="0.22">
			<d:field name="enable_flag" editor="label" />
				<d:field id="querymode" name="querymode_name" hiddenName="querymode_id" options="querymodeStore"
					displayField="querymode_name" valueField="querymode_id" editor="combo" prompt="查询类型" anchor="100%"/>
				<d:field id="busitype" name="busitype_name" hiddenName="busitype_id" options="busitypeStore"
					displayField="busitype_name" valueField="busitype_id" editor="combo" prompt="受理性质" anchor="100%"/>
			
			</d:line>
			<d:line columnWidth="0.22">
			<d:field name="enable_flag" editor="label" />
				<d:field id="datemode" name="datemode" editor="radiogroup" anchor="100%"  items="sexItmes" prompt="查询方式" />
				<d:field id="staticMonth" name="staticMonth" editor="monthfield" prompt="考核月份" anchor="100%" />		

			</d:line>
			<d:line columnWidth="0.22">
			<d:field name="enable_flag" editor="label" />
				<d:field id="startdate" name="startdate" editor="datefield"  prompt="考核时间" anchor="100%"/>
				<d:field id="enddate" name="enddate"  editor="datefield"   prompt="到" anchor="100%"/>
			</d:line>
			<d:line columnWidth="0.22">
			<d:field name="enable_flag" editor="label" />
				<d:field id="custspec" name="custspec_name" hiddenName="custspec_id" options="custspecStore"
				displayField="custspec_name" valueField="custspec_id" editor="combo" prompt="调度专业" anchor="100%"/>
			</d:line>
		</d:form>
		<d:buttonGroup  align="center">
			<d:button title="查询" click="query" />
			<d:button title="导出" click="exportExcel" />
            <d:button title="说明" click="explain"/>				
		</d:buttonGroup>
           
    <script type="text/javascript">
		     querymodeStore.on("load",function(){
			   Ext.getCmp('querymode').setValue(this.getAt(0).get('querymode_id'));		 
			 })
			 custspecStore.on("load",function(){
			   Ext.getCmp('custspec').setValue(this.getAt(0).get('custspec_id'));		 
			 })
			 busitypeStore.on("load",function(){
			   Ext.getCmp('busitype').setValue(this.getAt(0).get('busitype_id'));		 
			 })

			   var value = new Date().format('Y-m');
			   var val = new Date().format('Y-m-d');
			   var ymd = new Date();
               var year =ymd.getYear();
               var month =ymd.getMonth();
               var day =ymd.getDate();
               if(month<10)month = "0"+month;
               if(day<10)day = "0"+day;
               var ym=year+"-"+month+"-"+day;
			  Ext.getCmp("staticMonth").setValue(value);
			  Ext.getCmp("startdate").setValue(ym);
			  Ext.getCmp("enddate").setValue(val);
	</script>
</html>
