var index = 1;
var agreementProjectStore = new Ext.data.ArrayStore({  
    fields: ['agreementProjectCode', 'agreementProjectName'],  
    data: [  
        ['AB', 'AB'], 
        ['SB', 'SB'],
        ['SW', 'SW']  
    ]  
}); 

var agreementResultStore = new Ext.data.ArrayStore({  
    fields: ['resultCode', 'resultName'],  
    data: [  
        ['NG', 'NG'], 
        ['OK', 'OK']
    ]  
}); 

var userStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getUser.do"
   })
});
userStore.load();

var taskTach12Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=12"
   })
});
taskTach12Store.load();

var taskTach13Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=13"
   })
});
taskTach13Store.load();

var taskTach14Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=14"
   })
});
taskTach14Store.load();

var taskTach15Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=15"
   })
});
taskTach15Store.load();

var taskTach16Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=16"
   })
});
taskTach16Store.load();

var taskTach17Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=17"
   })
});
taskTach17Store.load();

var taskTach18Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=18"
   })
});
taskTach18Store.load();

var taskTach19Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=38"
   })
});
taskTach19Store.load();

var taskTach20Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=20"
   })
});
taskTach20Store.load();

var taskTach21Store = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getTacheUser.do?tacheId=21"
   })
});
taskTach21Store.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = agreementAdd.initBasicPanel(); 
	var mainPanel = agreementAdd.initMainPanel();
	var buttonPanel = agreementAdd.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var agreementAdd = (function() {
	 
	return {		
		initBasicPanel : function(){
		var	items= [{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
	                fieldLabel:'工程',
	                name: 'agreement_project_code',
	                id: 'agreement_project_code',
	                valueField: 'agreementProjectCode',
	                displayField: 'agreementProjectName', 
	                triggerAction:'all',     
	                store: agreementProjectStore,
	                allowBlank : true,
					mode : 'local',
	                anchor:'95%',
	                emptyText:'-----请选择-----'
	            }] 
	    	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{   
	            	xtype:'textfield',
                    fieldLabel:'切换LOT',
                    name: 'agreement_cut_lot',
                    id: 'agreement_cut_lot',         
                    anchor:'95%',
                    emptyText:'-----请输入-----'
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{   
	            	xtype:'textfield',
                    fieldLabel:'数量',
                    name: 'agreement_num',
                    id: 'agreement_num',         
                    anchor:'95%',
                    emptyText:'-----请输入-----'
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{                 
	            	xtype:'combo',
	                fieldLabel:'结论',
	                name: 'agreement_project_result',
	                id: 'agreement_project_result',
	                valueField: 'resultCode',
	                displayField: 'resultName', 
	                triggerAction:'all',     
	                store: agreementResultStore,
	                allowBlank : true,
					mode : 'local',
	                anchor:'95%',
	                emptyText:'-----请选择-----'
	            }] 
	    	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{   
	            	xtype:'textfield',
	                fieldLabel:'详细说明',
	                name: 'agreement_conclusion_message',
	                id: 'agreement_conclusion_message',         
	                anchor:'95%',
	                emptyText:'-----请输入-----'
	            }] 
	    	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'制造科',
                    name: 'agreement_tache_12_name',
                    id: 'agreement_tache_12_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach12Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'制造管理科',
                    name: 'agreement_tache_13_name',
                    id: 'agreement_tache_13_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach13Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'设备管理（AB）',
                    name: 'agreement_tache_14_name',
                    id: 'agreement_tache_14_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach14Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'设备管理(SB&SW）',
                    name: 'agreement_tache_15_name',
                    id: 'agreement_tache_15_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach15Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'计划物流科',
                    name: 'agreement_tache_16_name',
                    id: 'agreement_tache_16_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach16Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'客户品质科',
                    name: 'agreement_tache_17_name',
                    id: 'agreement_tache_17_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach17Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true,
                    listeners : {
                        afterRender : function(combo) {
                        	combo.setValue(userId);
                        	combo.setRawValue(userName);
                        }
                     }
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'品质检查科',
                    name: 'agreement_tache_18_name',
                    id: 'agreement_tache_18_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach18Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'计划物流（仓库）',
                    name: 'agreement_tache_19_name',
                    id: 'agreement_tache_19_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach19Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'系统评价科',
                    name: 'agreement_tache_20_name',
                    id: 'agreement_tache_20_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach20Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'管理（IT/总务）',
                    name: 'agreement_tache_21_name',
                    id: 'agreement_tache_21_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach21Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	},{ 
	            columnWidth:.33,
	            layout:'form',
	            items:[{               
	            	xtype:'combo',
                    fieldLabel:'追踪者',
                    name: 'agreement_track_name',
                    id: 'agreement_track_id',
                    valueField: 'userId',
                    displayField: 'userName', 
                    triggerAction:'all',     
                    hideTrigger:false,
                    allowBlank:true,
                    editable: false, 
                    store: taskTach17Store,
                    anchor:'95%',
                    emptyText:'-----请选择-----',
                    showSelectAll:true
	            }] 
        	}];
	        var basicPanel = new Ext.FormPanel({
	        	region:'north',
	        	id:'basicForm',
	            labelAlign: 'right',
	            labelWidth :100,
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
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "index","operation", "problem", "improve", "responsible", "term", "state", "confirm" ],
	    		data : []
	    	});
			var cm = new Ext.grid.ColumnModel([ {
				header : "",
				dataIndex : "index",
				hidden : true
			},{
				header : "操作<input type='button' value='+' onclick='addRow();'>",
				dataIndex : "operation",
				width : Ext.getBody().getSize().width * 0.05
			},{
				header : "问题点",
				dataIndex : "problem",
				width : Ext.getBody().getSize().width * 0.35
			}, {
				header : "改善对策",
				dataIndex : "improve",
				width : Ext.getBody().getSize().width * 0.35
			}, {
				header : "责任人",
				dataIndex : "responsible",
				width : Ext.getBody().getSize().width * 0.08
			}, {
				header : "改善期限",
				dataIndex : "term",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "状态",
				dataIndex : "state",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "确认者",
				dataIndex : "confirm",
				width : Ext.getBody().getSize().width * 0.06
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

function addRow(){
	var str = "<select id='data_responsible_" + index + "'>";
	var records = userStore.getRange();
    for (var i = 0; i < records.length; i++) {
    	if(userId == records[i].data.userId){
    		str += "<option value='" + records[i].data.userId + "' selected=true>" + records[i].data.userName + "</option>";
    	}else {
    		str += "<option value='" + records[i].data.userId + "'>" + records[i].data.userName + "</option>";
    	}
    }
	str += "</select>";
	var record = new Ext.data.Record({
		index : index,
		operation:'<input type="button" value="-" onclick="delRow('+index+');">',
		problem:'<textarea style="height:40px;width:'+(Ext.getBody().getSize().width * 0.35-20)+'px;" id="data_problem_'+index+'"></textarea>',
		improve:'',
		responsible:str,
		term:'',
		state:'',
		confirm:''
		});
	Ext.getCmp('mainDataGrid').getStore().add(record);
	index ++;
}

function delRow(seq){
	Ext.getCmp('mainDataGrid').getStore().each(function(record) {
		if(record.data.index == seq){
			Ext.getCmp('mainDataGrid').getStore().remove(record);
		}                                   
	});
}

function doSubmit(){
	var agreement_project_code = Ext.getCmp('agreement_project_code').getRawValue();
	if(agreement_project_code == null || agreement_project_code == "" || agreement_project_code == "undefined"){
		Ext.MessageBox.alert("提示", "请选择工程！");
		return;
	}
	var agreement_cut_lot = Ext.getCmp('agreement_cut_lot').getValue();
	if(agreement_cut_lot == null || agreement_cut_lot == "" || agreement_cut_lot == "undefined"){
		Ext.MessageBox.alert("提示", "切换LOT不能为空！");
		return;
	}
	
	var agreement_project_result = Ext.getCmp('agreement_project_result').getRawValue();
	if(agreement_project_result == null || agreement_project_result == "" || agreement_project_result == "undefined"){
		Ext.MessageBox.alert("提示", "请选择结论！");
		return;
	}else {
		if(agreement_project_result == "NG"){
			var agreement_conclusion_message = Ext.getCmp('agreement_conclusion_message').getValue();
			if(agreement_conclusion_message == null || agreement_conclusion_message == "" || agreement_conclusion_message == "undefined"){
				Ext.MessageBox.alert("提示", "NG原因不能为空！");
				return;
			}
		}
	}
	
	var problemArray = new Array();
	var responsibleArray = new Array();
	var i = 0;
	Ext.getCmp('mainDataGrid').getStore().each(function(record) {
		problemArray[i] = document.getElementById("data_problem_"+record.data.index).value;
		responsibleArray[i] = document.getElementById("data_responsible_"+record.data.index).value;
		i++;
	});
	var problemJson = Ext.encode(problemArray);  
	var responsibleJson = Ext.encode(responsibleArray);  
	var submitBasicUrl = contextPath + '/agreement/addAgreement.do?orderId='+orderId+"&userId="+userId;
	Ext.getCmp('basicForm').getForm().submit({
		url : submitBasicUrl,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			problemJson : problemJson,
			responsibleJson : responsibleJson,
			agreement_cut_lot : agreement_cut_lot,
			agreement_project_code : agreement_project_code,
			agreement_num : Ext.getCmp('agreement_num').getValue(),
			agreement_project_result : agreement_project_result,
			agreement_conclusion_message :  Ext.getCmp('agreement_conclusion_message').getValue(),
			agreement_tache_12_id : Ext.getCmp('agreement_tache_12_id').getValue(),
			agreement_tache_13_id : Ext.getCmp('agreement_tache_13_id').getValue(),
			agreement_tache_14_id : Ext.getCmp('agreement_tache_14_id').getValue(),
			agreement_tache_15_id : Ext.getCmp('agreement_tache_15_id').getValue(),
			agreement_tache_16_id : Ext.getCmp('agreement_tache_16_id').getValue(),
			agreement_tache_17_id : Ext.getCmp('agreement_tache_17_id').getValue(),
			agreement_tache_18_id : Ext.getCmp('agreement_tache_18_id').getValue(),
			agreement_tache_19_id : Ext.getCmp('agreement_tache_19_id').getValue(),
			agreement_tache_20_id : Ext.getCmp('agreement_tache_20_id').getValue(),
			agreement_tache_21_id : Ext.getCmp('agreement_tache_21_id').getValue(),
			agreement_track_id : Ext.getCmp('agreement_track_id').getValue()
		},
		async: false,
		success : function(form, action) {
			alert('新增立合成功！');
			window.close();
		},
		failure : function(form, action) {
			var responseText = Ext.decode(action.response.responseText);
			Ext.Msg.alert('提示', responseText.message);
		}
	});
}

function doClose(){
	window.close();
}