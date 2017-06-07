//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskWoOrderConfirm.initBasicPanel(); 
	var mainPanel = taskWoOrderConfirm.initMainPanel();
	var buttonPanel = taskWoOrderConfirm.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskWoOrderConfirm = (function() {
	 
	return {		
		initBasicPanel : function(){
			var items = new Array();
			var taskOrderJSON2 = taskOrderJSON.replace("\\", '\\\\');
			taskOrderJSON2 = taskOrderJSON2.replace(/\r\n/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n\r/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n/g,'</br>');
			var taskOrder = Ext.decode(taskOrderJSON2);
			var taskType  = taskOrder.taskType;
			var taskTypeInfoArray = taskType.taskTypeInfo;
			var len = taskTypeInfoArray.length;
			for (var i = 0; i < len; i++) {
				var taskTypeInfo = taskTypeInfoArray[i];
				var infoTypeId = taskTypeInfo.infoTypeId;
				var obj = generBasicObj(infoTypeId, taskTypeInfo, taskOrder.taskOrderInfoList);
				items[i] = obj;
			}
			var quality_confirm = "";
			var accept_confirm = "";
			var group_confirm = "";
			var taskConfirmOrderArray = taskOrder.taskConfirmOrderList;
			for(var i = 0; i < taskConfirmOrderArray.length; i++) {
				var taskConfirmOrder = taskConfirmOrderArray[i];
				if(taskConfirmOrder.runCode=="quality_confirm"){
					quality_confirm = taskConfirmOrder.confirmUserName;
				}else if(taskConfirmOrder.runCode=="accept_confirm"){
					accept_confirm = taskConfirmOrder.confirmUserName;
				}else if(taskConfirmOrder.runCode=="group_confirm"){
					group_confirm = taskConfirmOrder.confirmUserName;
				}
			}
			items[len] = { 
		            columnWidth:.33,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'承认',
			            value:quality_confirm,
			            anchor:'100%'
		            }]
            	};
			items[len+1] = { 
		            columnWidth:.33,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'确认',
			            value:group_confirm,
			            anchor:'100%'
		            }]
            	};
			items[len+2] = { 
		            columnWidth:.33,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'作成', 
			            value:accept_confirm,
			            anchor:'100%'
		            }]
            	};
	        var basicPanel = new Ext.FormPanel({
	        	region:'north',
	        	id:'basicForm',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,  
	            title: '基本信息',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:200,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: items,
	            autoScroll:true
	        });
	        return basicPanel;
	    },

	    initMainPanel : function(){
	    	var dataArray = new Array();
	    	var taskOrderJSON2 = taskOrderJSON.replace("\\", '\\\\');
			taskOrderJSON2 = taskOrderJSON2.replace(/\r\n/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n\r/g,'</br>');
			taskOrderJSON2 = taskOrderJSON2.replace(/\n/g,'</br>');
			var taskOrder = Ext.decode(taskOrderJSON2);
			var taskType  = taskOrder.taskType;
	    	var taskTachArray = taskType.taskTache;
			for(var i = 0; i < taskTachArray.length; i++){
				var taskTache = taskTachArray[i];
				var data = generMainObj(taskTache, taskOrder.taskWoOrderList);
				dataArray[i] = data;
			}
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "taskTacheId", "orgName", "detail", "completeDate", "woRefuseReason", "staffName" ],
	    		data : dataArray
	    	});
			var cm = new Ext.grid.ColumnModel([ {
				header : "部门/科室",
				dataIndex : "taskTacheId",
				hidden : true
			},{
				header : "部门/科室",
				dataIndex : "orgName",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "根据变化内容确认本部门/科室对应项目清单",
				dataIndex : "detail",
				width : Ext.getBody().getSize().width * 0.48,
			    renderer: function(value, meta, record) {    
			         meta.attr = 'style="white-space:normal;"';     
			         return value;     
			    }  
			}, {
				header : "预计完成日期",
				dataIndex : "completeDate",
				width : Ext.getBody().getSize().width * 0.14
			}, {
				header : "拒绝原因",
				dataIndex : "woRefuseReason",
				width : Ext.getBody().getSize().width * 0.2
			}, {
				header : "担当",
				dataIndex : "staffName",
				width : Ext.getBody().getSize().width * 0.06
			}]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "mainDataGrid",
				region : 'center',
				title : "详细数据",
				cm : cm,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				border : false,
				sortable : false,
				store : dataStore
			});
			return mainGrid;
	                                 },
	                                 
 		initButtonPanel : function(){
	        var buttonPanel = new Ext.FormPanel({
	        	region:'south',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:30,
	            width:Ext.getBody().getSize().width-2,
	            layout:'form',
	            items: [{               
	            	layout:'column',
	            	items:[{
						xtype:'label',
						columnWidth:0.4,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'确定',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/submit.png',
                         listeners:{
                        	 "click": doSubmit
                         }
	            	},{
						xtype:'label',
						columnWidth:0.05,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'取消',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/close.png',
                         listeners:{
                               "click": doClose                        
                         }
	            	}]
	            }]
	        });
	        return buttonPanel;
	    }
	};
})();

function generBasicObj(infoTypeId, taskTypeInfo, taskOrderInfoArray){
	var taskTypeInfoId = taskTypeInfo.taskTypeInfoId;
	var taskInfoValue = "";
	for(var i = 0; i < taskOrderInfoArray.length; i++){
		var taskOrderInfo = taskOrderInfoArray[i];
		if(taskTypeInfoId == taskOrderInfo.taskTypeInfoId){
			taskInfoValue = taskOrderInfo.taskInfoValue;
		}
	}
	var obj = null;
	if(infoTypeId == 1){
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'displayfield',
		           fieldLabel:taskTypeInfo.infoName,
		           value: taskInfoValue,
		           anchor:'95%'
		        }]
			};
	}else if(infoTypeId == 2){
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'displayfield', 
		           fieldLabel:taskTypeInfo.infoName,
		           value: taskInfoValue,
		           anchor:'95%'
		        }]
			};
	}else if(infoTypeId == 3){
		var items = new Array();
		var infoValueList = taskTypeInfo.infoValueList;
		var i = 0;
		var flag = false;
		for(i = 0; i < infoValueList.length; i++){
			var infoValue = infoValueList[i];
			var infoKey = infoValue.infoKey;
			if(infoKey == 1){
				flag = true;
			}
		}
		var checkedArray = new Array();
		var checkedInputValue = "";
		if(flag){
			var taskInfoValueArray = taskInfoValue.split("<<?><?>>");
			var checkedStr = taskInfoValueArray[0];
			if(!(checkedStr == undefined || checkedStr == null || checkedStr == "")){
				checkedArray = checkedStr.split(",");
			}
			checkedInputValue = taskInfoValueArray[1];
		}else {
			if(!(taskInfoValue == undefined || taskInfoValue == null || taskInfoValue == "")){
				checkedArray = taskInfoValue.split(",");
			}
		}
		for(i = 0; i < infoValueList.length; i++){
			var infoValue = infoValueList[i];
			var infoKey = infoValue.infoKey;
			var boxLabel = '';
			var checked = false;
			for(var j = 0; j < checkedArray.length; j++){
				if(infoValue.infoId == checkedArray[j]){
					checked = true;
				}
			}
			if(infoKey == 1){
				boxLabel = infoValue.infoName+'（'+checkedInputValue+'）';
			}else {
				boxLabel = infoValue.infoName;
			}
			var item = {
				boxLabel : boxLabel,
				inputValue : infoValue.infoId,
				checked : checked
			};
			items[i] = item;
		}
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'checkboxgroup', 
		           disabled : true,
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           items : items,
		           anchor:'95%'
		        }]
			};
	}else {
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'displayfield', 
		           fieldLabel:taskTypeInfo.infoName,
		           value: taskInfoValue,
		           anchor:'95%'
		        }]
			};
	} 
	return obj;
}

function generMainObj(taskTache, taskWoOrderArray){
	var taskWoOrder = null;
	var readOnly = true;
	for(var m = 0; m < taskWoOrderArray.length; m++){
		var temptaskWoOrder = taskWoOrderArray[m];
		if(taskTache.tacheId == temptaskWoOrder.tacheId){
			taskWoOrder = temptaskWoOrder;
		}
	}
	if(taskWoOrder.woOrderStateCode == "10B"){
		readOnly = false;
	}
	var obj = new Array();
	obj[0] = taskTache.tacheId;
	obj[1] = taskTache.orgName;
	var htmlStr = "";
	var taskTacheInfoArray = taskTache.taskTacheInfo;
	for(var i = 0; i < taskTacheInfoArray.length; i++){
		var taskTacheInfo = taskTacheInfoArray[i];
		var infoTypeId = taskTacheInfo.infoTypeId;
		
		var taskWoOrderInfoArray = taskWoOrder.taskWoOrderInfoList;
		var taskWoOrderInfo = null;
		for(var n = 0; n < taskWoOrderInfoArray.length; n++){
			var tempTaskWoOrderInfo = taskWoOrderInfoArray[n];
			if(taskTacheInfo.taskTacheInfoId == tempTaskWoOrderInfo.taskTacheInfoId){
				taskWoOrderInfo = tempTaskWoOrderInfo;
			}
		}
		
		if(infoTypeId == 1){
			
		}else if(infoTypeId == 2){
			
		}else if(infoTypeId == 3){
			var infoValueList = taskTacheInfo.infoValueList;
			var flag = false;
			var j = 0;
			for(j = 0; j < infoValueList.length; j++){
				var infoValue = infoValueList[j];
				var infoKey = infoValue.infoKey;
				if(infoKey == 1) flag = true;
			}
			var woInfoValue = taskWoOrderInfo.woInfoValue
			var checkedArray = new Array();
			var checkedInputValue = "";
			if(flag){
				var woInfoValueArray = woInfoValue.split("<<?><?>>");
				var checkedStr = woInfoValueArray[0];
				if(!(checkedStr == undefined || checkedStr == null || checkedStr == "")){
					checkedArray = checkedStr.split(",");
				}
				checkedInputValue = woInfoValueArray[1];
			}else {
				if(!(woInfoValue == undefined || woInfoValue == null || woInfoValue == "")){
					checkedArray = woInfoValue.split(",");
				}
			}
			for(j = 0; j < infoValueList.length; j++){
				var infoValue = infoValueList[j];
				var infoKey = infoValue.infoKey;
				var checked = "";
				for(var l = 0; l < checkedArray.length; l++){
					if(infoValue.infoId == checkedArray[l]){
						checked = "checked";
					}
				}
				if(readOnly){
					if(infoKey == 1){
						htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.infoId+'">'+infoValue.infoName+'&nbsp;';
						htmlStr += '（'+checkedInputValue+'）&nbsp;&nbsp;';
					}else {
						htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.infoId+'">'+infoValue.infoName+'&nbsp;&nbsp;';
					}
				}else {
					if(infoKey == 1){
						htmlStr += '<input type="checkbox" '+checked+' name="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'" value="'+infoValue.infoId+'">'+infoValue.infoName+'&nbsp;';
						htmlStr += '（<input type="text" id="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'_'+infoValue.infoId+'_value" name="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'_'+infoValue.infoId+'_value" value="'+checkedInputValue+'">）&nbsp;&nbsp;';
					}else {
						htmlStr += '<input type="checkbox" '+checked+' name="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'" value="'+infoValue.infoId+'">'+infoValue.infoName+'&nbsp;&nbsp;';
					}
				}
			}
		}else {
			
		}	
	}
	obj[2] = htmlStr;
	if(taskWoOrder.requireCompleteTimeStr == ""){
		obj[3] = '<input id="tache_require_time_'+taskTache.tacheId+'" name="tache_require_time_'+taskTache.tacheId+'" class="laydate-icon" onclick="laydate({istime: false, min: laydate.now(), isclear: false, istoday: false, issure: false, format: \'YYYY-MM-DD\'})">';
	}else {
		obj[3] = '<input id="tache_require_time_'+taskTache.tacheId+'" name="tache_require_time_'+taskTache.tacheId+'" class="laydate-icon" onclick="laydate({istime: false, min: laydate.now(), isclear: false, istoday: false, issure: false, format: \'YYYY-MM-DD\'})" value="'+taskWoOrder.requireCompleteTimeStr+'">';
	}	
	obj[4] = taskWoOrder.woRefuseReason;
	if(taskWoOrder.replyUserName){
		obj[5] = taskWoOrder.replyUserName
	}else {
		obj[5] = "";
	}
	return obj;
}

function doSubmit(){
	var index = 0;
	var pageParamArray = new Array();
	var taskOrderJSON2 = taskOrderJSON.replace("\\", '\\\\');
	taskOrderJSON2 = taskOrderJSON2.replace(/\r\n/g,'</br>');
	taskOrderJSON2 = taskOrderJSON2.replace(/\n\r/g,'</br>');
	taskOrderJSON2 = taskOrderJSON2.replace(/\n/g,'</br>');
	var taskOrder = Ext.decode(taskOrderJSON2);
	var taskType  = taskOrder.taskType;
	var taskTachArray = taskType.taskTache;
	for(var q = 0; q < taskTachArray.length; q++){
		var taskTache = taskTachArray[q];
		
		//预计完成日期
		var requireCompleteTime = document.getElementById("tache_require_time_"+taskTache.tacheId).value;
		if(requireCompleteTime == undefined || requireCompleteTime == null || requireCompleteTime == ""){
			alert("预计完成日期不能为空！");
			return;
		}
		pageParamArray[index] = {name:"tache_require_time_"+taskTache.tacheId, value:requireCompleteTime};
		index ++;
		
		var taskWoOrderArray = taskOrder.taskWoOrderList
		var taskWoOrder = null;
		for(var m = 0; m < taskWoOrderArray.length; m++){
			var temptaskWoOrder = taskWoOrderArray[m];
			if(taskTache.tacheId == temptaskWoOrder.tacheId){
				taskWoOrder = temptaskWoOrder;
			}
		}
		if(taskWoOrder.woOrderStateCode == "10B"){
			var taskTacheInfoArray = taskTache.taskTacheInfo;
			for(var i = 0; i < taskTacheInfoArray.length; i++){
				var taskTacheInfo = taskTacheInfoArray[i];
				
				//选择框
				var str = document.getElementsByName("wo_check_"+taskTache.tacheId+"_"+taskTacheInfo.taskTacheInfoId);
				var chestr="";
				for (u = 0; u < str.length; u++){
					if(str[u].checked == true){
						chestr+=","+str[u].value;
					}
				}
				if(chestr != ""){
					chestr = chestr.substring(1);
				}else {
					alert("必须选择一项！");
					return;
				}
				pageParamArray[index] = {name:"wo_check_"+taskTache.tacheId+"_"+taskTacheInfo.taskTacheInfoId, value:chestr};
				index ++;
				
				var infoTypeId = taskTacheInfo.infoTypeId;
				
				var taskWoOrderInfoArray = taskWoOrder.taskWoOrderInfoList;
				var taskWoOrderInfo = null;
				for(var n = 0; n < taskWoOrderInfoArray.length; n++){
					var tempTaskWoOrderInfo = taskWoOrderInfoArray[n];
					if(taskTacheInfo.taskTacheInfoId == tempTaskWoOrderInfo.taskTacheInfoId){
						taskWoOrderInfo = tempTaskWoOrderInfo;
					}
				}
				var infoValueList = taskTacheInfo.infoValueList;
				var flag = false;
				var keyInfoValue = null;
				var j = 0;
				for(j = 0; j < infoValueList.length; j++){
					var infoValue = infoValueList[j];
					var infoKey = infoValue.infoKey;
					if(infoKey == 1) {
						flag = true;
						keyInfoValue = infoValue;
					}
				}
				if(flag){
					var inputStr = document.getElementById("wo_check_"+taskTache.tacheId+"_"+taskTacheInfo.taskTacheInfoId+"_"+keyInfoValue.infoId+"_value").value;
					//判断是否有选中
					var inputChecked = false;
					for (var p = 0; p < str.length; p++){
						if(str[p].checked == true && str[p].value == keyInfoValue.infoId){
							inputChecked = true;
						}
					}
					if(inputChecked){
						if(inputStr == null || inputStr == ""){
							alert("你选择了其他，输入框不能为空！");
							return;
						}
					}
					pageParamArray[index] = {name:"wo_check_"+taskTache.tacheId+"_"+taskTacheInfo.taskTacheInfoId+"_"+keyInfoValue.infoId+"_value", value:inputStr};
					index ++;
				}
			}
		}
	}

	var pageParamJson = Ext.encode(pageParamArray); 

	Ext.Ajax.request( {
		url : contextPath+'/taskDetail/updateWoOrder.do?orderId='+orderId,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			pageParamJson : pageParamJson
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			if(responseText.success){
				alert("填写信息成功！");
				parent.doQry();
				parent.closeCreateWin();
			}else {
				alert(responseText.message);
			}
		},
		failure : function(a) {
			Ext.Msg.alert('提示', '填写信息失败！');
		}
	});

}

function doClose(){
	parent.closeCreateWin();
}
