 function showorderinfo(custorderid,custorderno,spectype,dispatchorderid,dispatchno,type){

	 if(custorderid !='null' && custorderno !='null' && spectype != 'null' && dispatchorderid != 'null' && dispatchno != 'null'){
        var theRequest = new Object();
	    theRequest['custOrderId'] = custorderid;
   		theRequest['custOrderNo'] = custorderno;
   		theRequest['dispatchOrderId'] = dispatchorderid;
   		theRequest['dispatchOrderNo'] = dispatchno;
   		theRequest['specType'] = spectype;
		 var session = GetSession();
		 var conditionObj = new Object();
		 var temp_value = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getTacheId",false,13);
		if(!temp_value){
			conditionObj.tacheIds = [];
		}else{
			conditionObj.tacheIds = temp_value.split(",");
		}	  		
 		conditionObj.staffId =session.staff.staffId;
		conditionObj.jobId = session.job.jobId;
		conditionObj.orgId = session.org.orgId;
		conditionObj.handleArea =session.area.areaId;
		conditionObj.menuId = 103;
		conditionObj.menuType = type; 
   		conditionObj["objData"] = theRequest;
        var classType =  callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getClassTypeById",true,conditionObj.objData.custOrderId);
		var prodCode =   callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getProdTypeByCustOrderId",true,conditionObj.objData.custOrderId);
		conditionObj.objData['classType'] = classType;
	    var features="dialogHeight:"+document.body.clientHeight+"px;dialogWidth:"+document.body.clientWidth+"px;";
   		var orderType = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.InnerOrderManager","getGnOrderType",true,conditionObj.objData.custOrderId);
   		conditionObj.specialType = orderType;	
		if(type=='002'){
   		window.showModalDialog('/IOMPROJ/ext/bj/band/network/networkDispatchOrderDetail.jsp',conditionObj,features); //调单
   		}else{
   		window.showModalDialog('/IOMPROJ/ext/accept/gjCableApplyOrderAccept.jsp',conditionObj,features); //定单
	  }
	}else{
         alert("请确定数据是否完整！");
	}
}

