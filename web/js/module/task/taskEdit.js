var carType = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['configCommit', 'configValue'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/sysConfig/querySysConfigJson.do?configCode=carType"
   })
});
carType.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskDetail.initBasicPanel(); 
	var mainPanel = taskDetail.initMainPanel();
	var buttonPanel = taskDetail.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskDetail = (function() {
	 
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
			var taskDetailOrderArray = taskOrder.taskConfirmOrderList;
			for(var i = 0; i < taskDetailOrderArray.length; i++) {
				var taskDetailOrder = taskDetailOrderArray[i];
				if(taskDetailOrder.runCode=="quality_confirm"){
					quality_confirm = taskDetailOrder.confirmUserName;
				}else if(taskDetailOrder.runCode=="accept_confirm"){
					accept_confirm = taskDetailOrder.confirmUserName;
				}else if(taskDetailOrder.runCode=="group_confirm"){
					group_confirm = taskDetailOrder.confirmUserName;
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
	    		fields : [ "taskTacheId", "orgName", "detail", "staffName", "completeDate" ],
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
				width : Ext.getBody().getSize().width * 0.74,
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
				width : Ext.getBody().getSize().width * 0.08
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
                         text:'确定',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/submit.png',
                         listeners:{
                        	 "click":doSubmit                       
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
	if(taskInfoValue == undefined){
		
	}else {
		taskInfoValue = taskInfoValue.replace(/<\/br>/g, "\r\n");
	}
	var obj = null;
	if(infoTypeId == 1){
			obj = { 
			        columnWidth:taskTypeInfo.infoLength,
			        layout:'form',
			        items:[{               
			           xtype:'textfield',
			           readOnly: false,   
			           fieldLabel:taskTypeInfo.infoName,
			           name: 'order_'+taskTypeInfo.taskTypeInfoId,
			           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
			           value: taskInfoValue,
			           anchor:'95%'
			        }]
				};
	}else if(infoTypeId == 2){
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'textarea',
		           readOnly: false,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
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
				boxLabel = infoValue.infoName+'（<input type="text" id="order_check_'+taskTypeInfo.taskTypeInfoId+'_'+infoValue.infoId+'_value" name="order_check_'+taskTypeInfo.taskTypeInfoId+'_'+infoValue.infoId+'_value" value="'+checkedInputValue+'" style="width:150px;" size="20">）';
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
        if(items.length > 1){
            obj = {
                columnWidth:taskTypeInfo.infoLength,
                layout:'form',
                items:[{
                    xtype:'checkboxgroup',
                    columns: 6,
                    readOnly: false,
                    fieldLabel:taskTypeInfo.infoName,
                    name: 'order_'+taskTypeInfo.taskTypeInfoId,
                    id: 'order_'+taskTypeInfo.taskTypeInfoId,
                    items : items,
                    anchor:'100%'
                }]
            };
        }else {
            obj = {
                columnWidth:taskTypeInfo.infoLength,
                layout:'form',
                items:[{
                    xtype:'checkboxgroup',
                    readOnly: false,
                    fieldLabel:taskTypeInfo.infoName,
                    name: 'order_'+taskTypeInfo.taskTypeInfoId,
                    id: 'order_'+taskTypeInfo.taskTypeInfoId,
                    items : items,
                    anchor:'100%'
                }]
            };
        }
	}else if(infoTypeId == 4 && taskTypeInfo.infoSeq == 7){
		obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'datetimefield',
		           readOnly: false,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           value:taskInfoValue,
		           minValue:taskInfoValue,
		           anchor:'95%'
		        }]
			};
	}else if(infoTypeId == 5){
		obj = { 
	            columnWidth:taskTypeInfo.infoLength,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:taskTypeInfo.infoName,
                    name: 'order_'+taskTypeInfo.taskTypeInfoId,
                    id: 'order_'+taskTypeInfo.taskTypeInfoId,
                    valueField: 'configCommit',
                    displayField: 'configValue', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: true, 
                    store: carType,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true,
                    listeners:{
                    	beforequery : function(e){
                    		var combo = e.combo;
                    		if(!e.forceAll){
                    			var value = e.query;
                    			combo.store.filterBy(function(record,id){
                    				var text = record.get(combo.displayField);
                    				return (text.indexOf(value)!=-1);
                    			});
                    			combo.expand();
                    			return false;
                    		}
                    	}
                    }
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
					if(infoValue.id = checkedArray[l]){
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
	return obj;
}

function doClose(){
	window.close();
}

function doSubmit(){
	if (!Ext.getCmp('basicForm').getForm().isValid()) {
		alert("请输入正确的格式！");
		return;
	}
	var order_11 = Ext.getCmp("order_11").value;
	var order_1 = Ext.getCmp("order_1").getValue();
	var order_2 = Ext.getCmp("order_2").getValue();
	var order_3 = Ext.getCmp("order_3").getValue();
	var order_4 = Ext.getCmp("order_4").getValue();
	var order_9 = Ext.getCmp("order_9").getValue();
    var order_12 = Ext.getCmp("order_12").getValue();
	var order_8_input = document.getElementById("order_check_8_13_value").value;
	var order_5_value = "";
	var order_5 = Ext.getCmp('order_5').getValue();
	Ext.each(order_5, function(item){
		order_5_value += ","+item.inputValue;
	});
	var order_6_value = "";
	var order_6 = Ext.getCmp('order_6').getValue();
	Ext.each(order_6, function(item){
		order_6_value += ","+item.inputValue;
	});
	var order_7_value = "";
	var order_7 = Ext.getCmp('order_7').getValue();
	Ext.each(order_7, function(item){
		order_7_value += ","+item.inputValue;
	});
	var order_8_value = "";
	var order_8 = Ext.getCmp('order_8').getValue();
	Ext.each(order_8, function(item){
		order_8_value += ","+item.inputValue;
	});
	Ext.Ajax.request( {
		url : contextPath+'/taskOrder/editChangeTime.do',
		async: false,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			orderId: orderId,
			order_11 : order_11,
			order_1 : order_1,
			order_2 : order_2,
			order_3 : order_3,
			order_4 : order_4,
			order_5 : order_5_value,
			order_6 : order_6_value,
			order_7 : order_7_value,
			order_8 : order_8_value,
			order_9 : order_9,
            order_12 : order_12,
			order_8_input : order_8_input
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			if(responseText.success){
				alert("修改成功！");
				window.close();
			}else {
				alert(responseText.message);
			}
		},
		failure : function(e) {
			Ext.Msg.alert('提示', '修改失败！');
		}
	});
}