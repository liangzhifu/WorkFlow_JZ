var woOrderIds = new Array();
var woOrderIdIndex = 0;
//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskRefuse.initBasicPanel(); 
	var mainPanel = taskRefuse.initMainPanel();
	var buttonPanel = taskRefuse.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskRefuse = (function() {
	 
	return {		
		initBasicPanel : function(){
			var items = new Array();
			var taskOrder = Ext.decode(taskOrderJSON);
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
	            height:220,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: items,
	            autoScroll:true
	        });
	        return basicPanel;
	    },

	    initMainPanel : function(){
	    	var dataArray = new Array();
	    	var taskOrder = Ext.decode(taskOrderJSON);
			var taskType  = taskOrder.taskType;
	    	var taskTachArray = taskType.taskTache;
			for(var i = 0; i < taskTachArray.length; i++){
				var taskTache = taskTachArray[i];
				var data = generMainObj(taskTache, taskOrder.taskWoOrderList);
				dataArray[i] = data;
			}
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "taskTacheId", "orgName", "detail", "staffName", "completeDate", "refuse"],
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
				width : Ext.getBody().getSize().width * 0.62,
			    renderer: function(value, meta, record) {    
			         meta.attr = 'style="white-space:normal;"';     
			         return value;     
			    }  
			}, {
				header : "担当",
				dataIndex : "staffName",
				width : Ext.getBody().getSize().width * 0.06
			}, {
				header : "预计完成日期",
				dataIndex : "completeDate",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "是否拒绝",
				dataIndex : "refuse",
				width : Ext.getBody().getSize().width * 0.1
			} ]);
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
	            height:40,
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
                         text:'接受',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/submit.png',
                         listeners:{
                        	 "click":doAcceptAll                        
                         }
	            	},{
						xtype:'label',
						columnWidth:0.05,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'拒绝',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/refuse.gif',
                         listeners:{
                        	 "click":doRefuseAll                        
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
                        	 "click":doClose                       
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
				if(infoKey == 1){
					htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.id+'">'+infoValue.infoName+'&nbsp;';
					htmlStr += '（'+checkedInputValue+'）&nbsp;&nbsp;';
				}else {
					htmlStr += '<input type="checkbox" disabled '+checked+' value="'+infoValue.id+'">'+infoValue.infoName+'&nbsp;&nbsp;';
				}
			}
		}else {
			
		}	
	}
	obj[2] = htmlStr;
	if(taskWoOrder.replyUserName){
		obj[3] = taskWoOrder.replyUserName;
	}else {
		obj[3] = "";
	}
	obj[4] = taskWoOrder.requireCompleteTimeStr;
	if(taskWoOrder.woOrderStateCode == "10R"){
		woOrderIds[woOrderIdIndex] = taskWoOrder.woOrderId;
		woOrderIdIndex ++;
		obj[5] = '<span id="span_input_hidden_'+taskWoOrder.woOrderId+'"><input type="hidden" value="'+taskWoOrder.woOrderId+'" class="tache_input_hidden"><input type="button" value="拒绝" onclick="doRefuse('+taskWoOrder.woOrderId+')"/>&nbsp;&nbsp;<input type="button" value="接受" onclick="doAccept('+taskWoOrder.woOrderId+')"/></span>';
	}else{
		obj[5] = '';
	}
	
	return obj;
}

function doAccept(id){
	Ext.Ajax.request( {
		url : contextPath+'/woOrder/acceptWoOrder.do',
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			woOrderId: id,
			orderId : orderId
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			alert(responseText.message);
			document.getElementById("span_input_hidden_"+id).innerHTML='';
			var tempId = 0;
			for(var i = 0; i < woOrderIdIndex; i++){
				if(id == woOrderIds[i]){
					tempId = i;
				}
			}
			woOrderIds.splice(tempId, 1);
			woOrderIdIndex --;
		},
		failure : function(a) {
			Ext.Msg.alert('提示', '确认回填信息失败！');
		}
	});
}

function doRefuse(id){
	var refuseReason = "";
	Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
		if("cancel"==bu) return;
		refuseReason= txt;  
		Ext.Ajax.request( {
			url : contextPath+'/woOrder/refuseWoOrder.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				refuseReason: refuseReason,
				woOrderId: id
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				alert(responseText.message);
				document.getElementById("span_input_hidden_"+id).innerHTML='';
				var tempId = 0;
				for(var i = 0; i < woOrderIdIndex; i++){
					if(id == woOrderIds[i]){
						tempId = i;
					}
				}
				woOrderIds.splice(tempId, 1);
				woOrderIdIndex --;
			},
			failure : function(a) {
				Ext.Msg.alert('提示', '拒绝失败！');
			}
		});
	},this,50)
}

function doAcceptAll(){
	if(woOrderIdIndex==0){
		parent.doQry();
		parent.closeCreateWin();
	}else {
		var ids = "";
		for(var i = 0; i < woOrderIdIndex; i++){
			ids += "," + woOrderIds[i];
		}
		ids = ids.substring(1);
		Ext.Ajax.request( {
			url : contextPath+'/woOrder/acceptWoOrders.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				woOrderIds: ids,
				orderId : orderId
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				alert(responseText.message);
				parent.doQry();
				parent.closeCreateWin();
			},
			failure : function(a) {
				Ext.Msg.alert('提示', '确认回填信息失败！');
			}
		});
	}
}

function doRefuseAll(){
	if(woOrderIdIndex==0){
		parent.doQry();
		parent.closeCreateWin();
	}else {
		var ids = "";
		for(var i = 0; i < woOrderIdIndex; i++){
			ids += "," + woOrderIds[i];
		}
		ids = ids.substring(1);
		var refuseReason = "";
		Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
			if("cancel"==bu) return;
			refuseReason= txt;  
			Ext.Ajax.request( {
				url : contextPath+'/woOrder/refuseWoOrders.do',
				waitTitle : '提示', 
		 		waitMsg: '请稍后,正在提交数据...',
				params : {
					refuseReason: refuseReason,
					woOrderIds: ids
				},
				success : function(response, action) {
					var responseText = Ext.decode(response.responseText);
					alert(responseText.message);
					parent.doQry();
					parent.closeCreateWin();
				},
				failure : function(a) {
					Ext.Msg.alert('提示', '拒绝失败！');
				}
			});
		},this,50)
	}
}

function doClose(){
	parent.doQry();
	parent.closeCreateWin();
}
