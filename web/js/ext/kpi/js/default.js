function setTempValue(){

	var myDate = new Date();
	
	var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	
	var defaultTime ;

	if( parseInt(month) < 10){
		defaultTime = year+'-0'+month;
	}else{
		defaultTime = year+'-'+month;
	}
		
	


	return defaultTime;
};

function setTempValueForDay(){

	var myDate = new Date();
	
	var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	var day = myDate.getDate();        //获取当前日(1-31)
	
	//myDate.toLocaleDateString();     //获取当前日期
	//var mytime=myDate.toLocaleTimeString();     //获取当前时间
	//myDate.toLocaleString( );        //获取日期与时间
	
	var defaultTime ;

	if( parseInt(month) < 10){
		defaultTime = year+'-0'+month+'-'+day;
	}else{
	
		defaultTime = year+'-'+month+'-'+day;
	}
	

	return defaultTime;
};

function getOrgSelect(){
   
   var currIdObj = arguments[1].dom;
   var id = currIdObj.id;//获取当前组件ID
   var returnValue = window.showModalDialog(arguments[0],id);
   if (returnValue==undefined) {
        if ( arguments[1].dom.value=='undefined' ){
            arguments[1].dom.value='';
        }
   } else {
       if (id=="proviceA"||id=="proviceZ"
            ||id=="cityA"||id=="cityZ"
                ||id=="countryA"||id=="countryZ"
                    ||id=="ceBelongCity"||id=="cn2PeBelongCity"){ 
           //区域选择框

           setAreaValues(returnValue,currIdObj);
       } 
	   try{textChgOper(id);}catch(e){}

   }
};

//设置区域名称
//参数：区域信息，当前所选组件对象

function setAreaValues(areaTreeNodeObj,currIdObj){
   var grade = areaTreeNodeObj.grade;
   var areaType = areaTreeNodeObj.areaType;
   var pathName = areaTreeNodeObj.pathName;
   if(currIdObj.id=="proviceA"){
       var areaValues = getAreaValues(areaTreeNodeObj,true);
       //设置父类区域
       currIdObj.value=areaValues.parentName;
       //设置子类区域
       Ext.getCmp("cityA").setValue(areaValues.subName);
   }else if (currIdObj.id=="proviceZ"){
       var areaValues = getAreaValues(areaTreeNodeObj,true);
       //设置父类区域
       currIdObj.value=areaValues.parentName;
       //设置子类区域
       Ext.getCmp("cityZ").setValue(areaValues.subName);
   }else if(currIdObj.id=="cityA"){
       var areaValues;
       var proviceObj = Ext.getCmp("proviceA");
       var countryObj = Ext.getCmp("countryA");
       if (proviceObj!=null && proviceObj!=undefined){
            //国际单

           areaValues = getAreaValues(areaTreeNodeObj,true);
           //设置父类区域
           proviceObj.setValue(areaValues.parentName);
           //设置子类区域
           currIdObj.value=areaValues.subName;
       } else if (countryObj!=null && countryObj!=undefined){
            //国内单

           areaValues = getAreaValues(areaTreeNodeObj,false);
           //设置子类区域
           countryObj.setValue(areaValues.subName);
           //设置父类区域
           currIdObj.value=areaValues.parentName;
       } else if ( (proviceObj==null || proviceObj==undefined)
            || (countryObj==null || countryObj==undefined)) {
            //国际没有省的选择
            areaValues = getAreaValues(areaTreeNodeObj,true);
            currIdObj.value=areaValues.subName;
       }
   }else if(currIdObj.id=="cityZ"){
       var areaValues;
       var proviceObj = Ext.getCmp("proviceZ");
       var countryObj = Ext.getCmp("countryZ");
       if (proviceObj!=null && proviceObj!=undefined){
            //国际单

           areaValues = getAreaValues(areaTreeNodeObj,true);
           //设置父类区域
           proviceObj.setValue(areaValues.parentName);
           //设置子类区域
           currIdObj.value=areaValues.subName;
       } else if (countryObj!=null && countryObj!=undefined){
            //国内单

           areaValues = getAreaValues(areaTreeNodeObj,false);
           //设置子类区域
           countryObj.setValue(areaValues.subName);
           //设置父类区域
           currIdObj.value=areaValues.parentName;
       } else if ( (proviceObj==null || proviceObj==undefined)
            || (countryObj==null || countryObj==undefined)) {
            //国际没有省的选择
            areaValues = getAreaValues(areaTreeNodeObj,true);
            currIdObj.value=areaValues.subName;
       }
   }else if (currIdObj.id=='countryA'){
       var areaValues = getAreaValues(areaTreeNodeObj,false);
       //设置子类区域
       currIdObj.value=areaValues.subName;
       //设置父类区域
       Ext.getCmp("cityA").setValue(areaValues.parentName);
   }else if (currIdObj.id=='countryZ'){
       var areaValues = getAreaValues(areaTreeNodeObj,false);
       //设置子类区域
       currIdObj.value=areaValues.subName;
       //设置父类区域
       Ext.getCmp("cityZ").setValue(areaValues.parentName);
   }else if (currIdObj.id=='ceBelongCity'){
       //以国际逻辑取值

       var areaValues = getAreaValues(areaTreeNodeObj,true);
       //设置子类区域
       currIdObj.value=areaValues.subName;
   }else if (currIdObj.id=='cn2PeBelongCity'){
       //以国际逻辑取值

       var areaValues = getAreaValues(areaTreeNodeObj,true);
       //设置子类区域
       currIdObj.value=areaValues.subName;
   }
};

//根据是否选择父类区域获取对象,不管是选择大或小区域都能相互填充对应区域名称

//参数：区域信息，是否为国际定单(否则为国内定单)
function getAreaValues(areaTreeNodeObj,isGjOrderType) {
    var grade = areaTreeNodeObj.grade;
    var areaType = areaTreeNodeObj.areaType;
    var pathName = areaTreeNodeObj.pathName; 
    var areaValues = new Object();
    areaValues.parentName='';
    areaValues.subName = '';
    var isGj;
    if ( pathName!=null ) {
        isGj = (pathName.indexOf('<%=getI18NResource("innerOrderAccept.gj")%>'))>0?true:false;
    }
    //国际定单
    if(isGjOrderType){
        if ( isGj ) {
            //选择了国际区域

            if(grade=='C2'){
                //国际两个字

            } else if (grade=='C3') {
                //国家名称
                areaValues.parentName = areaTreeNodeObj.name;
                areaValues.subName = '';
            } else if (grade=='C4') {
                //城市名称
                var pathName1 = pathName.substring(0,pathName.lastIndexOf ('/'));
                areaValues.parentName = pathName1.substring(pathName1.lastIndexOf ('/')+1,pathName1.length);;
                areaValues.subName = areaTreeNodeObj.name;
            }
        } else {
            //选择了国内区域

            if(grade=='C2'){
                //省

                areaValues.parentName = areaTreeNodeObj.name;
                areaValues.subName = '';
            } else if (grade=='C3') {
                //城市
                var pathName1 = pathName.substring(0,pathName.lastIndexOf ('/'));
                areaValues.parentName = pathName1.substring(pathName1.lastIndexOf ('/')+1,pathName1.length);
                areaValues.subName = areaTreeNodeObj.name;
            } else if (grade=='C4') {
                //县城
                var pathName1 = pathName.substring(0,pathName.lastIndexOf ('/'));
                var pathName2 = pathName1.substring(0,pathName1.lastIndexOf ('/'));
                areaValues.parentName = pathName2.substring(pathName2.lastIndexOf ('/')+1,pathName2.length);
                areaValues.subName = pathName1.substring(pathName1.lastIndexOf ('/')+1,pathName1.length);
            }
        }
    }else{
        //国内定单
        if ( isGj ) {
            //选择了国际区域

            if(grade=='C2'){
                //国际两个字

            } else if (grade=='C3') {
                //国家名称
            } else if (grade=='C4') {
                //城市名称
                areaValues.parentName = areaTreeNodeObj.name;
                areaValues.subName = '';
            }
        } else {
            //选择了国内区域

            if(grade=='C2'){
                //省

            } else if (grade=='C3') {
                //城市
                var pathName1 = pathName.substring(0,pathName.lastIndexOf ('/'));
                areaValues.parentName = areaTreeNodeObj.name;
                areaValues.subName = '';
            } else if (grade=='C4') {
                //县城
                var pathName1 = pathName.substring(0,pathName.lastIndexOf ('/'));
                areaValues.parentName = pathName1.substring(pathName1.lastIndexOf ('/')+1,pathName1.length);
                areaValues.subName = areaTreeNodeObj.name;
            }
        }
    }
    return areaValues;
};

function doClick(rowIndex,columnIndex,menuId){
   
   var record = Ext.getCmp("grid1").getStore().getAt(rowIndex);
   var columnModel = Ext.getCmp("grid1").getColumnModel();
   
   //alert(columnModel.getColumnCount()+"<>"+columnModel.getDataIndex(1));
   var columnVal = "";
   for(var i=0; i<columnModel.getColumnCount(); i++){
   	 var name = columnModel.getDataIndex(i);
   	 var val = record.get(name);
   	 columnVal = columnVal + name+"="+val+"&";
   	 //alert(i+"<>"+columnVal);
   }
   //alert(columnVal);
   
   var param_json = Ext.getCmp("queryParam_Form").form.getValuesOld();
   Ext.getCmp("queryParam_Form").form.items.each(function(obj){
	 if(obj.xtype == "combo"){
		 param_json[obj.name] = obj.getValue();
	 }
   });
	
   param_json.parent_node_id = menuId ;
   param_json.myOrgId = session.org.orgId;
   param_json.curStaffArea = session.area.areaId ;
   param_json = Ext.encode(param_json);
   param_json = param_json.replace(new RegExp(":","gm"),"=");
   param_json = param_json.replace(new RegExp("\"","gm"),"");
   param_json = param_json.replace("{","");
   param_json = param_json.replace("}","");
   var paramArr = param_json.split(",");
   var urlParam = "";
   for(var i=0; i<paramArr.length;i++){
   		urlParam = urlParam + paramArr[i]+"&" ;
   }
   
   urlParam = urlParam + columnVal ;
   urlParam = urlParam.substr(0,urlParam.length-1);
   
   if(menuId == 104 || menuId == 105 || menuId == 167 || menuId == 168 || menuId == 169 || menuId == 1017){
   		
   		//var str = "custOrderId=306962&custOrderNo=JT20130731gj0101-1&dispatchOrderId=18090&dispatchOrderNo=国网调[2013]2152号&specType=国际" ;
   		var str = urlParam ;
   		
   		var theRequest = new Object();
   		var strs = str.split("&"); 
      	for(var i = 0; i < strs.length; i ++) { 
         	theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
      	}
   		// 因大小写问题  重新赋值
   		theRequest['custOrderId'] = theRequest['custorderid'];
   		theRequest['custOrderNo'] = theRequest['custorderno'];
   		theRequest['dispatchOrderId'] = theRequest['dispatchorderid'];
   		theRequest['dispatchOrderNo'] = theRequest['dispatchorderno'];
   		theRequest['specType'] = theRequest['spectype'];
   		
   		if(theRequest['dispatchOrderNo'] == null || theRequest['dispatchOrderNo'] == 'null' || theRequest['dispatchOrderNo'] == undefined ||theRequest['dispatchOrderNo'] == ''){
   			Ext.Msg.alert('提示','该定单还未生成调度文号');
   			return;
   		}
   		var conditionObj = new Object();
   		var temp_value = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getTacheId",false,13);
		if(!temp_value){
			conditionObj.tacheIds = [];
		}else{
			conditionObj.tacheIds = temp_value.split(",");
		}
		conditionObj.staffId = session.staff.staffId;
		conditionObj.jobId = session.job.jobId;
		conditionObj.orgId = session.org.orgId;
		conditionObj.handleArea = session.area.areaId;
		conditionObj.menuId = menuId;
		conditionObj.menuType = '002';  // 001定单 002 调单
		
   		conditionObj["objData"] = theRequest;
        var classType =  callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getClassTypeById",true,conditionObj.objData.custOrderId);
		var prodCode =   callRemoteFunction("com.ztesoft.oss.reportconifg.bl.NetworkTaskManager","getProdTypeByCustOrderId",true,conditionObj.objData.custOrderId);
		conditionObj.objData['classType'] = classType;
	    var features="dialogHeight:"+document.body.clientHeight+"px;dialogWidth:"+document.body.clientWidth+"px;";
   		var orderType = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.InnerOrderManager","getGnOrderType",true,conditionObj.objData.custOrderId);
   		conditionObj.specialType = orderType;
   		
   		showModalDialog('/IOMPROJ/ext/bj/band/network/networkDispatchOrderDetail.jsp',conditionObj,features); //调单
   		
   		//showModalDialog('/IOMPROJ/ext/accept/gjCableApplyOrderAccept.jsp',conditionObj,features); //定单
   }else {
   	 	window.open('./subReportTemplate.jsp?'+urlParam);
   }
    
   
   
   
};