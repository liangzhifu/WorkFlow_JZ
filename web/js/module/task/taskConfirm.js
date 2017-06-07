//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskConfirm.initBasicPanel(); 
	var mainPanel = taskConfirm.initMainPanel();
	var buttonPanel = taskConfirm.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskConfirm = (function() {
	 
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
	    		fields : [ "taskTacheId", "orgName", "detail", "staffName", "completeDate", "refuse" ],
	    		data : dataArray
	    	});
	    	var sm = new Ext.grid.CheckboxSelectionModel();
	    	var cm = null;
	    	if(runCode=="confirm_confirm"||runCode=="quality2_confirm"){
	    		cm = new Ext.grid.ColumnModel([ 
           			sm,{
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
           				width : Ext.getBody().getSize().width * 0.65,
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
           			}, {
           				header : "是否拒绝",
           				dataIndex : "refuse",
           				width : Ext.getBody().getSize().width * 0.05
           			} ]);
	    	}else {
	    		cm = new Ext.grid.ColumnModel([ 
           			{
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
           				width : Ext.getBody().getSize().width * 0.69,
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
           			}, {
           				header : "是否拒绝",
           				dataIndex : "refuse",
           				width : Ext.getBody().getSize().width * 0.05
           			} ]);
	    	}
	    	
	    	var mainGrid = null;
			if(runCode=="confirm_confirm"||runCode=="quality2_confirm"){
				mainGrid = new Ext.grid.GridPanel({
					id : "mainDataGrid",
					region : 'center',
					title : "详细数据",
					sm : sm,
					cm : cm,
					loadMask : true,
					// 超过长度带自动滚动条
					autoScroll : true,
					border : false,
					sortable : false,
					store : dataStore
				});
	    	}else {
	    		mainGrid = new Ext.grid.GridPanel({
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
	    	}
			if(!(runCode=="confirm_confirm"||runCode=="quality2_confirm")){
				mainGrid.getColumnModel().setHidden(5,true);
			}
			return mainGrid;
	    },
	                                 
 		initButtonPanel : function(){
 			var buttonPanel = null;
 			if(runCode=="confirm_confirm"||runCode=="quality2_confirm"){
 				 buttonPanel = new Ext.FormPanel({
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
 	                         text:'拒绝',
 	                         columnWidth:0.05,
 	                         icon: '/WorkFlow/images/refuse.gif',
 	                         listeners:{
 	                        	 "click":doRefuse2                        
 	                         }
 		            	},{
 							xtype:'label',
 							columnWidth:0.05,
 							html:'&nbsp;&nbsp;'
 						},{
 		            		 xtype: 'button',                       
 	                         text:'作废',
 	                         columnWidth:0.05,
 	                         icon: '/WorkFlow/images/delete.gif',
 	                         listeners:{
 	                        	 "click":doDelTask                        
 	                         }
 		            	},{
 							xtype:'label',
 							columnWidth:0.05,
 							html:'&nbsp;&nbsp;'
 						},{
 		            		 xtype: 'button',                       
 	                         text:'确定',
 	                         columnWidth:0.05,
 	                         icon: '/WorkFlow/images/submit.png',
 	                         listeners:{
 	                        	 "click":doConfirm                        
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
 			}else {
 				 buttonPanel = new Ext.FormPanel({
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
 	                         text:'作废',
 	                         columnWidth:0.05,
 	                         icon: '/WorkFlow/images/delete.gif',
 	                         listeners:{
 	                        	 "click":doDelTask                        
 	                         }
 		            	},{
 							xtype:'label',
 							columnWidth:0.05,
 							html:'&nbsp;&nbsp;'
 						},{
 		            		 xtype: 'button',                       
 	                         text:'确定',
 	                         columnWidth:0.05,
 	                         icon: '/WorkFlow/images/submit.png',
 	                         listeners:{
 	                        	 "click":doConfirm                        
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
 			}
	       
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
	obj[0] = taskWoOrder.woOrderId;
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
	obj[5] = '<input type="button" value="拒绝" onclick="doRefuse('+taskWoOrder.woOrderId+')"/></span>';
	return obj;
}

function doEditConfirmConfirm(){
	var flag = true;
	Ext.Msg.confirm("确认框", "是否需要重新选择项目管理部确认人："+confrimConfirmUser, function (btn) {
		if(btn=='yes'){
			while(flag){
				var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectUser.jsp", null, "dialogHeight=500px;dialogwidth=400px;help=no;scrollbars=no;");
				if(returnValue){
					var userId = returnValue.id;
					if(userId != undefined && userId!=""){
						flag = false;
						Ext.Ajax.request( {
							url : contextPath+'/taskConfirmOrder/editConfirmOrderUser.do',
							async: false,
							waitTitle : '提示', 
					 		waitMsg: '请稍后,正在提交数据...',
							params : {
								userId : userId,
								orderId : taskOrderId,
								runCode : "confirm_confirm"
							},
							success : function(response, action) {
								var responseText = Ext.decode(response.responseText);
								if(responseText.success){
									alert("修改项目管理部确认人成功！");
								}else {
									alert(responseText.message);
								}
								doEditQuality2Confirm();
							},
							failure : function(e) {
								Ext.Msg.alert('提示', '修改项目管理部确认人失败！');
							}
						});
					}
				}
			}  				
		}else {
			doEditQuality2Confirm();
		}
	});
}

function doEditQuality2Confirm(){
    flag = true;
    Ext.Msg.confirm("确认框", "是否需要重新选择品质部长承认人："+quality2ConfirmUser, function (btn) {
        if(btn=='yes'){
            while(flag){
                var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectUser.jsp", null, "dialogHeight=500px;dialogwidth=400px;help=no;scrollbars=no;");
                if(returnValue){
                    var userId = returnValue.id;
                    if(userId != undefined && userId!=""){
                        flag = false;
                        Ext.Ajax.request( {
                            url : contextPath+'/taskConfirmOrder/editConfirmOrderUser.do',
                            async: false,
                            waitTitle : '提示',
                            waitMsg: '请稍后,正在提交数据...',
                            params : {
                                userId : userId,
                                orderId : taskOrderId,
                                runCode : "quality2_confirm"
                            },
                            success : function(response, action) {
                                var responseText = Ext.decode(response.responseText);
                                if(responseText.success){
                                    alert("修改品质部长承认人成功！");
                                }else {
                                    alert(responseText.message);
                                }
                                doSubmit();
                            },
                            failure : function(e) {
                                Ext.Msg.alert('提示', '修改品质部长承认人失败！');
                            }
                        });
                    }
                }
            }
        }else{
            doEditManagerConfirm();
        }
    });
}

function doEditManagerConfirm(){
	flag = true;
	Ext.Msg.confirm("确认框", "是否需要重新选择项目管理部部长承认人："+quality2ConfirmUser, function (btn) {
		if(btn=='yes'){
			while(flag){
				var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectUser.jsp", null, "dialogHeight=500px;dialogwidth=400px;help=no;scrollbars=no;");
				if(returnValue){
					var userId = returnValue.id;
					if(userId != undefined && userId!=""){
						flag = false;
						Ext.Ajax.request( {
							url : contextPath+'/taskConfirmOrder/editConfirmOrderUser.do',
							async: false,
							waitTitle : '提示', 
					 		waitMsg: '请稍后,正在提交数据...',
							params : {
								userId : userId,
								orderId : taskOrderId,
								runCode : "manager_confirm"
							},
							success : function(response, action) {
								var responseText = Ext.decode(response.responseText);
								if(responseText.success){
									alert("修改项目管理部部长承认人成功！");
								}else {
									alert(responseText.message);
								}
								doSubmit();
							},
							failure : function(e) {
								Ext.Msg.alert('提示', '修改项目管理部部长承认人失败！');
							}
						});
					}
				}
			}  				
		}else{
			doSubmit();
		}
	});
}

function doSubmit(){
	Ext.Ajax.request( {
		url : contextPath+'/taskConfirmOrder/confirmTaskConfirmOrder.do',
		async: false,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			confirmOrderId : confirmOrderId
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			if(responseText.success){
				alert("确认成功！");
				parent.doQry();
				parent.closeCreateWin();
			}else {
				alert(responseText.message);
			}
		},
		failure : function(e) {
			Ext.Msg.alert('提示', '确认失败！');
		}
	});
}

function doConfirm(){
	if(runCode=="accept2_confirm"){
		//选择变化点管理确认人
		doEditConfirmConfirm();
		//选择品质部长承认人
	}else {
		doSubmit();
	}	
}

function doDelTask(){
	Ext.MessageBox.prompt("输入框","请输入作废原因：",function(bu,txt){
		if("cancel" == bu) return;
		var invalidateText = txt;
		if(invalidateText == undefined || invalidateText == null || invalidateText == ""){
			Ext.Msg.alert('提示','作废原因不能为空！');
			return;
		}
		Ext.Ajax.request( {
			url : contextPath+'/taskDetail/delTask.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				orderId : taskOrderId,
				invalidateText : invalidateText
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				alert(responseText.message);
				parent.doQry();
				parent.closeCreateWin();
			},
			failure : function(a) {
				Ext.Msg.alert('错误', '作废变更单失败！');
			}
		});
	},this,60);
}

function doClose(){
	parent.closeCreateWin();
}

function doRefuse(id){
	var refuseReason = "";
	Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
		if("cancel"==bu) return;
		refuseReason= txt;  
		if(refuseReason == undefined || refuseReason == null || refuseReason == ""){
			Ext.Msg.alert('提示','拒绝原因不能为空！');
			return;
		}
		Ext.Ajax.request( {
			url : contextPath+'/woOrder/refuseWoOrder2.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				refuseReason: refuseReason,
				woOrderId: id
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
	},this,50);
}

function doRefuse2(){
	var rows = Ext.getCmp('mainDataGrid').getSelectionModel().getSelections();//根据选择列获取到所有的行
    if (rows.length == 0) {
    	Ext.Msg.alert('提示', '必须选择一个要拒绝的科室！');
    	return;
    }
    var ids = "";
    for (var i = 0; i < rows.length; i++) {
        //循环迭代所有的选择的row
        var row = rows[i];
        ids = ids + ','+ row.get('taskTacheId');//  这里将数据组织成类似，1，2，3这样的数据
    }
    ids = ids.substring(1);
	var refuseReason = "";
	Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
		if("cancel"==bu) return;
		refuseReason= txt;  
		if(refuseReason == undefined || refuseReason == null || refuseReason == ""){
			Ext.Msg.alert('提示','拒绝原因不能为空！');
			return;
		}
		Ext.Ajax.request( {
			url : contextPath+'/woOrder/refuseWoOrder3.do',
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
	},this,50);
}
