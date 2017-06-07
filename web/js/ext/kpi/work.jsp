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
		var _excelTemplateName="Exportorderstatistics";
		var _nodeName= "定单统计及其完成情况";
		
	     var json_data=operationForm.getData();
		  if(json_data==null){
              return ;
		  }  
		    var json_parme={};         
             json_parme.datemode =json_data.datemode;  
			 if(json_parme.datemode=="1"){

			 json_parme.staticMonth =json_data.staticMonth; 
	         } 
			 if(json_parme.datemode=="0"){
			 json_parme.startdate =json_data.startdate;
			 json_parme.enddate =json_data.enddate;
			 }

			 var flag1 = Ext.getCmp("radio1").getValue();   
			 var flag2 = Ext.getCmp("radio2").getValue(); 
			 var flag3 = Ext.getCmp("radio3").getValue(); 
			 var flag4 = Ext.getCmp("radio4").getValue()
			 if(flag1==true){
			 json_parme.datemode1 ="1";
			 if(json_data.customername!=""){
			 json_parme.customername =json_data.customername;
			    }
			 }
			 if(flag2==true){
				 json_parme.datemode1 ="2";
				 if(json_data.ordersnumber!=""){
				 json_parme.ordersnumber =json_data.ordersnumber;
				 }
             }
			 if(flag3==true){
				 json_parme.datemode1 ="3";
				 if(json_data.tunesymbol!=""){
				 json_parme.tunesymbol =json_data.tunesymbol;
				 }
			 }
			 if(flag4==true){
				 json_parme.datemode1 ="4";
				 if(json_data.businesstype_id!=""){
			     json_parme.businesstype =json_data.businesstype_id; 
				}
			 }
			 json_parme.state =json_data.state_id; 		
			 json_parme.professional = json_data.professional_id; 
			 json_parme.acceptingnature =json_data.acceptingnature_id; 	
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
		    if(retVal[0]==false&&node_id==1357){
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
				} });
				}


       function explain(){
	    var url = "reportComments.jsp" ;
	  	window.open(url+"?nodeId="+100);
	  }
	    function query() {
             var flag = Ext.getCmp("datemode").getValue(); 
			 if(flag==null){	
				  $D.showMessage("请选择查询方式！",function(){});
                return;
			 } 
	         var date=operationGrid.getColumnModel();
	         var flag1 = Ext.getCmp("radio1").getValue();     
			 var flag2 = Ext.getCmp("radio2").getValue(); 
			 var flag3 = Ext.getCmp("radio3").getValue(); 
			 var flag4 = Ext.getCmp("radio4").getValue(); 
             var datemode1="";
			  if(flag1==true){
				 var code= date.columns[0].header;			 
				    datemode1=1;
				 if(code=="客户名称"){	           
		        	date.setHidden(0,false);
					date.setHidden(1,true);
					date.setHidden(2,true);
					date.setHidden(3,true);					
				 }
			  }
			 else if(flag2==true){
                 var code= date.columns[1].header;
				  datemode1=2;
				 if(code=="客户流水号"){	
				    date.setHidden(0,true);
				    date.setHidden(1,false);
		        	date.setHidden(2,true);		       	
					date.setHidden(3,true);				
				 }

			  }
			 else  if(flag3==true){            
				 var code= date.columns[2].header;
				   datemode1=3;
				 if(code=="调单文号"){	           	        			        					
					date.setHidden(0,true);
					date.setHidden(1,true);
				    date.setHidden(2,false);
					date.setHidden(3,true);
				 }
			  }
			 else if(flag4==true){               
			     var code= date.columns[3].header;
				   datemode1=4;
				 if(code=="业务类型"){	           
				    date.setHidden(0,true);
					date.setHidden(1,true);
					date.setHidden(2,true);
					date.setHidden(3,false);

				 }
			  }else{
                    date.setHidden(0,true);
					date.setHidden(1,true);
					date.setHidden(2,true);
					date.setHidden(3,true);
			  }
               var param_json  =operationForm.getData(); 
                   param_json.datemode1=datemode1;
                   param_json.node_id=node_id;
                   param_json.myOrgId=session.org.orgId;
                   param_json.curStaffArea=session.area.areaId;
 	            operationGrid.query();
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

	<body style="border:false">
		<d:dataStore fields="state_id,state_name" autoLoad="true" id="stateStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=state" />
		<d:query dataProvideClass="com.ztesoft.test.QueryData"
			dataProvideMethod="queryWork" rootPath="items"/>
        <d:dataStore fields="businesstype_id,businesstype_name" autoLoad="true" id="businesstypeStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=businesstype" />
        <d:dataStore fields="professional_id,professional_name" autoLoad="true" id="professionalStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=professional" />
        <d:dataStore fields="acceptingnature_id,acceptingnature_name" autoLoad="true" id="acceptingnatureStore"
			url="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=acceptingnature" />

		<d:form  style="margin:0px"  id="operationForm" title="定单统计及完成情况" labelWidth="80">
			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
				<d:field id="radio1" hideLabel="true" name="enable_flag" columnWidth="0.02" editor="radio" anchor="60%"/>
				<d:field id="customername" name="customername" editor="textfield" prompt="客户名称" />
				<d:field id="state" name="state_name" hiddenName="state_id" options="stateStore"
					displayField="state_name" valueField="state_id" editor="combo" prompt="状态" anchor="100%"/>
			</d:line>
			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
				<d:field id="radio2" hideLabel="true" name="enable_flag" columnWidth="0.02" editor="radio" anchor="60%" />
				<d:field id="ordersnumber" name="ordersnumber" editor="textfield" prompt="客户流水号" />
				<d:field id="professional" name="professional_name" hiddenName="professional_id" options="professionalStore"
					displayField="professional_name" valueField="professional_id" editor="combo" prompt="调度专业" anchor="100%"/>
			</d:line>
			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
				<d:field id="radio3" hideLabel="true" name="enable_flag" columnWidth="0.02" editor="radio" anchor="60%" />
				<d:field id="tunesymbol"name="tunesymbol" editor="textfield" prompt="调单文号" />
				<d:field id="acceptingnature" name="acceptingnature_name" hiddenName="acceptingnature_id" options="acceptingnatureStore"
					displayField="acceptingnature_name" valueField="acceptingnature_id" editor="combo" prompt="受理性质" anchor="100%"/>
			</d:line>
			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
				<d:field id="radio4" hideLabel="true" name="enable_flag" columnWidth="0.02" editor="radio" anchor="60%" />
				<d:field id="businesstype" name="businesstype" hiddenName="businesstype_id" options="businesstypeStore"
					displayField="businesstype_name" valueField="businesstype_id" editor="combo" prompt="业务类型" />
			</d:line>

			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
				<d:field id="radio9" hideLabel="true" name="_flag" columnWidth="0.02" editor="radio" anchor="60%" hidden="true"/>
				<d:field id="datemode" name="datemode" editor="radiogroup" anchor="100%"  items="sexItmes" prompt="查询方式" />
				<d:field id="staticMonth" name="staticMonth" editor="monthfield" prompt="按月份统计" anchor="100%" />
			</d:line>
			<d:line columnWidth="0.22">
				<d:field name="enable_flag12" editor="label" />
                <d:field id="radio10" hideLabel="true" name="_flag" columnWidth="0.02" editor="radio" anchor="60%" hidden="true"/>
				<d:field id="startdate" name="startdate" editor="datefield"  prompt="按时间段统计" />
				<d:field id="enddate" name="enddate"  editor="datefield"   prompt="到" anchor="100%"/>
			</d:line>
		</d:form>
		<d:buttonGroup  align="center">
			<d:button title="查询" click="query" />
            <d:button title="导出" click="exportExcel" />
			<d:button title="说明" click="explain"/>		
			
		</d:buttonGroup>
           
		<d:grid id="operationGrid" selectable="false" autoQuery="false"
			title="查询结果" height="284" queryForm="operationForm" forceFit="true"
			queryUrl="/IOMPROJ/dbfoundAction?className=com.ztesoft.test.UserControl&method=test" navBar="false" rowNumber="false"  style="margin:0px">
			<d:columns>
				<d:forEach items="${items}" var="item">
					<d:column name="${item.code}" prompt="${item.name}"
						width="${item.width}" />
				</d:forEach>
			</d:columns>
		</d:grid>
	</body>
    <script type="text/javascript">
	         stateStore.on("load",function(){
			   Ext.getCmp('state').setValue(this.getAt(0).get('state_id'));		 
			 })
			 businesstypeStore.on("load",function(){
			   Ext.getCmp('businesstype').setValue(this.getAt(0).get('businesstype_id'));		 
			 })

			 professionalStore.on("load",function(){
			   Ext.getCmp('professional').setValue(this.getAt(0).get('professional_id'));		 
			 })
			 acceptingnatureStore.on("load",function(){
			   Ext.getCmp('acceptingnature').setValue(this.getAt(0).get('acceptingnature_id'));		 
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
			

	         var date=operationGrid.getColumnModel();          
		        	date.setHidden(0,true);
					date.setHidden(1,true);
			        date.setHidden(2,true);
				    date.setHidden(3,true);
	</script>
</html>
