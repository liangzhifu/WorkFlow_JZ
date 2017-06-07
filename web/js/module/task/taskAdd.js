var myDate = new Date();
var nextDate1 = new Date(myDate.getTime() + 24*60*60*1000);  //后一天
while (nextDate1.getDay() == 0 || nextDate1.getDay() == 6)
{
	nextDate1 = new Date(nextDate1.getTime() + 24*60*60*1000);
} 
var nextDate2 = new Date(nextDate1.getTime() + 24*60*60*1000);  //后一天
while (nextDate2.getDay() == 0 || nextDate2.getDay() == 6)
{
	nextDate2 = new Date(nextDate2.getTime() + 24*60*60*1000);
} 

var carType = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['configCommit', 'configValue'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/vehicle/getVehicleList.do"
   })
});
carType.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = taskAdd.initBasicPanel(); 
	var mainPanel = taskAdd.initMainPanel();
	var buttonPanel = taskAdd.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var taskAdd = (function() {
	 
	return {		
		initBasicPanel : function(){
			var items = new Array();
			var taskType  = Ext.decode(taskTypeJSON);
			var taskTypeInfoArray = taskType.taskTypeInfo;
			var len = taskTypeInfoArray.length;
			for (var i = 0; i < len; i++) {
				var taskTypeInfo = taskTypeInfoArray[i];
				var infoTypeId = taskTypeInfo.infoTypeId;
				var obj = generBasicObj(infoTypeId, taskTypeInfo);
				items[i] = obj;
			}
			items[len] = { 
		            columnWidth:.33,
		            layout:'column',
		            items:[{    
		            	columnWidth:.7,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            fieldLabel:'承认',
				            name: 'order_quality_confirm_staff_name',
				            id: 'order_quality_confirm_staff_name',  
				            readOnly: true,  
				            anchor:'100%'
			            }]
		            },{
		            	columnWidth:.01,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            readOnly: true,   
				            fieldLabel:'承认ID',
				            name: 'order_quality_confirm_staff',
				            id: 'order_quality_confirm_staff', 
				            hidden:true,//默认是false
				            hideLabel:true//默认是false
			            }]
			        },{
			        	columnWidth:.25,
			            layout:'form',
			            items:[{ 
			            xtype:'button',
			               text:'请选择',
			               name: 'button_order_quality_confirm_staff',
			               id: 'button_order_quality_confirm_staff',
			               anchor:'95%',
			               listeners:{
                               "click":function(){doQryStaff('order_quality_confirm_staff', 1);}                                                          
                           }
			            }]
			        }] 
            	};
			items[len+1] = { 
		            columnWidth:.33,
		            layout:'column',
		            items:[{    
		            	columnWidth:.7,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            fieldLabel:'确认',
				            name: 'order_group_confirm_staff_name',
				            id: 'order_group_confirm_staff_name',  
				            readOnly: true,  
				            anchor:'100%'
			            }]
		            },{
		            	columnWidth:.01,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            readOnly: true,   
				            fieldLabel:'确认ID',
				            name: 'order_group_confirm_staff',
				            id: 'order_group_confirm_staff', 
				            hidden:true,//默认是false
				            hideLabel:true//默认是false
			            }]
			        },{
			        	columnWidth:.25,
			            layout:'form',
			            items:[{ 
			            xtype:'button',
			               text:'请选择',
			               name: 'button_order_group_confirm_staff',
			               id: 'button_order_group_confirm_staff',
			               anchor:'95%',
			               listeners:{
                               "click":function(){doQryStaff('order_group_confirm_staff', 1);}                                                   
                           }
			            }]
			        }] 
            	};
			items[len+2] = { 
		            columnWidth:.33,
		            layout:'column',
		            items:[{    
		            	columnWidth:.7,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            fieldLabel:'作成',
				            name: 'order_accept_confirm_staff_name',
				            id: 'order_accept_confirm_staff_name', 
				            readOnly: true,  
				            anchor:'100%'
			            }]
		            },{
		            	columnWidth:.01,
			            layout:'form',
			            items:[{ 
			            	xtype:'textfield',
				            readOnly: true,   
				            fieldLabel:'作成ID',
				            name: 'order_accept_confirm_staff',
				            id: 'order_accept_confirm_staff', 
				            hidden:true,//默认是false
				            hideLabel:true//默认是false
			            }]
			        },{
			        	columnWidth:.25,
			            layout:'form',
			            items:[{ 
			            xtype:'button',
			               text:'请选择',
			               name: 'button_order_accept_confirm_staff',
			               id: 'button_order_accept_confirm_staff',
			               anchor:'95%',
			               listeners:{
                               "click":function(){doQryStaff('order_accept_confirm_staff', 1);}                                           
                           }
			            }]
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
	            height:240,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: items,
	            autoScroll:true
	        });
	        return basicPanel;
	    },

	    initMainPanel : function(){
	    	var dataArray = new Array();
	    	var taskType  = Ext.decode(taskTypeJSON);
	    	var taskTachArray = taskType.taskTache;
			for(var i = 0; i < taskTachArray.length; i++){
				var taskTache = taskTachArray[i];
				var data = generMainObj(taskTache);
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
				width : Ext.getBody().getSize().width * 0.72
			}, {
				header : "担当",
				dataIndex : "staffName",
				width : Ext.getBody().getSize().width * 0.08
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
						columnWidth:0.425,
						html: '&nbsp;&nbsp;'
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
						html: '&nbsp;&nbsp;'
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

function generBasicObj(infoTypeId, taskTypeInfo){
	var obj = null;
	if(infoTypeId == 1){
		if(taskTypeInfo.taskTypeInfoId == 1){
			obj = { 
			        columnWidth:taskTypeInfo.infoLength,
			        layout:'form',
			        items:[{               
			           xtype:'textfield',
			           readOnly: true,   
			           fieldLabel:taskTypeInfo.infoName,
			           name: 'order_'+taskTypeInfo.taskTypeInfoId,
			           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
			           anchor:'95%'
			        }]
				};
		}else {
			obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'textfield',
		           readOnly: false,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           anchor:'95%'
		        }]
			};
		}
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
		           anchor:'95%'
		        }]
			};
	}else if(infoTypeId == 3){
		var items = new Array();
		var infoValueList = taskTypeInfo.infoValueList;
		for(var i = 0; i < infoValueList.length; i++){
			var infoValue = infoValueList[i];
			var infoKey = infoValue.infoKey;
			var boxLabel = '';
			if(infoKey == 1){
				boxLabel = infoValue.infoName+'（<input type="text" id="order_check_'+taskTypeInfo.taskTypeInfoId+'_'+infoValue.infoId+'_value" name="order_check_'+taskTypeInfo.taskTypeInfoId+'_'+infoValue.infoId+'_value" style="width:150px;" size="20">）';
			}else {
				boxLabel = infoValue.infoName;
			}
			var item = {
				boxLabel : boxLabel,
				name : 'order_check_'+taskTypeInfo.taskTypeInfoId,
				inputValue : infoValue.infoId
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
	}else if(infoTypeId == 4){
		if(taskTypeInfo.taskTypeInfoId == 11){
			obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'datetimefield',
		           readOnly: false,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           minValue:nextDate2,
		           anchor:'95%'
		        }]
			};
		}else {
			obj = { 
		        columnWidth:taskTypeInfo.infoLength,
		        layout:'form',
		        items:[{               
		           xtype:'datefield',
		           readOnly: true,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           format: 'Y-m-d', 
		           value:new Date(),
		           anchor:'95%'
		        }]
			};
		}
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
                    editable: false, 
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
		           xtype:'textfield',
		           readOnly: false,   
		           fieldLabel:taskTypeInfo.infoName,
		           name: 'order_'+taskTypeInfo.taskTypeInfoId,
		           id: 'order_'+taskTypeInfo.taskTypeInfoId, 
		           vtypeText: '请输入正确的格式',
		           anchor:'95%'
		        }]
			};
	} 
	return obj;
}

function generMainObj(taskTache){
	var obj = new Array();
	obj[0] = taskTache.tacheId;
	obj[1] = taskTache.orgName;
	var htmlStr = "";
	var taskTacheInfoArray = taskTache.taskTacheInfo;
	for(var i = 0; i < taskTacheInfoArray.length; i++){
		var taskTacheInfo = taskTacheInfoArray[i];
		var infoTypeId = taskTacheInfo.infoTypeId;
		if(infoTypeId == 1){
			htmlStr += '<input type="text" class="wo_input_hidden" value="">';
		}else if(infoTypeId == 2){
			
		}else if(infoTypeId == 3){
			var infoValueList = taskTacheInfo.infoValueList;
			for(var j = 0; j < infoValueList.length; j++){
				var infoValue = infoValueList[j];
				var infoKey = infoValue.infoKey;
				if(infoKey == 1){
					htmlStr += '<input type="checkbox" class="wo_input_check" disabled=true name="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'" value="'+infoValue.infoName+'">'+infoValue.infoName+'&nbsp;';
					htmlStr += '（）&nbsp;&nbsp;';
				}else {
					htmlStr += '<input type="checkbox" class="wo_input_check" disabled=true name="wo_check_'+taskTache.tacheId+'_'+taskTacheInfo.taskTacheInfoId+'" value="'+infoValue.infoName+'">'+infoValue.infoName+'&nbsp;&nbsp;';
				}
			}
		}else {
			
		}	
	}
	obj[2] = htmlStr;
	obj[3] = "";
	obj[4] = "";
	return obj;
}

function doSubmit(){
	var order_quality_confirm_staff = Ext.getCmp('order_quality_confirm_staff').getValue();
	if(order_quality_confirm_staff == null || order_quality_confirm_staff == "" || order_quality_confirm_staff == "undefined"){
		Ext.MessageBox.alert("提示", "请选择承认人！");
		return;
	}
	var order_accept_confirm_staff = Ext.getCmp('order_accept_confirm_staff').getValue();
	if(order_accept_confirm_staff == null || order_accept_confirm_staff == "" || order_accept_confirm_staff == "undefined"){
		Ext.MessageBox.alert("提示", "请选择做成人！");
		return;
	}
	
	var checkFlag = false;
	var order_5 = Ext.getCmp('order_5').getValue();
	Ext.each(order_5, function(item){
		checkFlag = true;
	});
	var order_6 = Ext.getCmp('order_6').getValue();
	Ext.each(order_6, function(item){
		checkFlag = true;
	});
	var order_7 = Ext.getCmp('order_7').getValue();
	Ext.each(order_7, function(item){
		checkFlag = true;
	});
	var order_8 = Ext.getCmp('order_8').getValue();
	Ext.each(order_8, function(item){
		checkFlag = true;
	});
	if(!checkFlag){
		Ext.MessageBox.alert("提示", "必须选择一个复选框！");
		return;
	}
	
	var order_2 = Ext.getCmp('order_2').getValue();
	if(order_2 == null || order_2 == "" || order_2 == "undefined"){
		Ext.MessageBox.alert("提示", "生产线不能为空！");
		return;
	}
	var order_3 = Ext.getCmp('order_3').getValue();
	if(order_3 == null || order_3 == "" || order_3 == "undefined"){
		Ext.MessageBox.alert("提示", "车种名不能为空！");
		return;
	}
	var order_4 = Ext.getCmp('order_4').getValue();
	if(order_4 == null || order_4 == "" || order_4 == "undefined"){
		Ext.MessageBox.alert("提示", "安装席不能为空！");
		return;
	}
	var order_9 = Ext.getCmp('order_9').getValue();
	if(order_9 == null || order_9 == "" || order_9 == "undefined"){
		Ext.MessageBox.alert("提示", "变更内容不能为空！");
		return;
	}
	var order_change_time = Ext.getCmp('order_11').getValue();
	if(order_change_time == null || order_change_time == "" || order_change_time == "undefined"){
		Ext.MessageBox.alert("提示", "请选择变更时间！");
		return;
	}
	var myDate = new Date(order_change_time);
	var nowDate = new Date();
	if(nowDate > myDate){
		alert("变更时间不能小于当前时间！");
		return;
	}
	if (!Ext.getCmp('basicForm').getForm().isValid()) {
		alert("请输入正确的格式！");
		return;
	}
	
	var inputChecked = false;
	var str = document.getElementsByName("order_check_8");
	for (var u = 0; u < str.length; u++){
		if(str[u].checked == true){
			inputChecked = true;
		}
	}
	var inputStr = document.getElementById("order_check_8_13_value").value;
	if(inputChecked){
		if(inputStr == null || inputStr == ""){
			alert("你选择了其他，输入框不能为空！");
			return;
		}
	}
	
	var submitBasicUrl = contextPath + '/taskDetail/addTask.do?taskTypeId='+taskTypeId+"&userId="+userId;
	Ext.getCmp('basicForm').getForm().submit({
		url : submitBasicUrl,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			
		},
		async: false,
		success : function(form, action) {
			alert('新增变更单成功！');
			window.close();
		},
		failure : function(form, action) {
			var responseText = Ext.decode(action.response.responseText);
			Ext.Msg.alert('提示', responseText.message);
		}
	});
}

function doQryStaff(id, type){
	var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectUser.jsp", null, "dialogHeight=500px;dialogwidth=400px;help=no;scrollbars=no;");
	if(returnValue){
		if(type == 1){
			Ext.getCmp(id).setValue(returnValue.id);
			Ext.getCmp(id+'_name').setValue(returnValue.name);
		}else if(type == 2){
			document.getElementById("tache_staff_"+id).value = returnValue.id;
			document.getElementById("tache_staff_name_"+id).innerHTML = returnValue.name;
		}
	}
}

function doClose(){
	window.close();
}