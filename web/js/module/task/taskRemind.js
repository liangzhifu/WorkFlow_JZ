var pageSize = 15;

var orderStateStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['orderStateCode', 'orderStateName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskDetail/getOrderState.do"
   })
});
orderStateStore.load();

var isDelayStore = new Ext.data.JsonStore({
	remoteSort: true,
	fields : ['isDelayCode', 'isDelayName'],
	proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskDetail/getIsDelay.do"
   })
});
isDelayStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = taskRemind.initSearchPanel(); 
	var mainPanel = taskRemind.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var taskRemind = (function() {

	return {
		initSearchPanel : function(){
	        var searchPanel = new Ext.FormPanel({
	        	region:'north',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,  
	            title: '查询条件',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:120,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: [{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'发行编号',
	                        name: 'search_publish_code',
	                        id: 'search_publish_code',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'datefield',
	                        fieldLabel:'变更时间',
	                        name: 'search_change_time',
	                        id: 'search_change_time',         
	                        anchor:'95%',
	                        format: 'Y-m-d'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'column',
			            items:[{    
			            	columnWidth:.7,
				            layout:'form',
				            items:[{ 
				            	xtype:'textfield',
					            fieldLabel:'所属部门',
					            name: 'search_org_name',
					            id: 'search_org_name',  
					            readOnly: true,  
					            anchor:'100%'
				            }]
			            },{
			            	columnWidth:.01,
				            layout:'form',
				            items:[{ 
				            	xtype:'textfield',
					            readOnly: true,   
					            fieldLabel:'所属部门ID',
					            name: 'search_org_id',
					            id: 'search_org_id', 
					            hidden:true,//默认是false
					            hideLabel:true//默认是false
				            }]
				        },{
				        	columnWidth:.25,
				            layout:'form',
				            items:[{ 
				            xtype:'button',
				               text:'请选择',
				               name: 'button_org',
				               id: 'button_org',
				               anchor:'95%',
				               listeners:{
	                               "click":doOrgQry                                                           
	                           }
				            }]
				        }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'创建人',
	                        name: 'search_create_user',
	                        id: 'search_create_user',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.5,
			            layout:'column',
			            items:[{    
			            	columnWidth:.5,
				            layout:'form',
				            items:[{ 
				            	xtype:'datetimefield',
					            fieldLabel:'创建时间',
					            name: 'create_time_begin',
					            id: 'create_time_begin',  
					            format: 'Y-m-d h:i:s', 
					            anchor:'99%'
				            }]
			            },{
			            	columnWidth:.45,
				            layout:'form',
				            labelWidth: 20,
				            items:[{ 
				            	xtype:'datetimefield',  
					            fieldLabel:'至',
					            name: 'create_time_end',
					            id: 'create_time_end',  
					            format: 'Y-m-d h:i:s',
					            labelWidth :20,
					            anchor:'99%'
				            }]
				        }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'定单状态',
		                    name: 'search_order_state_code',
		                    id: 'search_order_state_code',
		                    valueField: 'orderStateCode',
		                    displayField: 'orderStateName', 
		                    triggerAction:'all',     
		                    hideTrigger:false,
		                    allowBlank:true,
		                    editable: false, 
		                    store: orderStateStore,
		                    anchor:'95%',
		                    emptyText:'-----请选择-----',
		                    showSelectAll:true
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'是否延时',
		                    name: 'search_is_delay',
		                    id: 'search_is_delay',
		                    valueField: 'isDelayCode',
		                    displayField: 'isDelayName', 
		                    triggerAction:'all',     
		                    hideTrigger:false,
		                    allowBlank:true,
		                    editable: false, 
		                    store: isDelayStore,
		                    anchor:'95%',
		                    emptyText:'-----请选择-----',
		                    showSelectAll:true
			            }] 
	            	},{ 
			            columnWidth:1,
			            layout:'form',
			            items:[{               
			            	layout:'column',
			            	items:[{
								xtype:'label',
								columnWidth:0.4,
								html: '&nbsp;&nbsp;'
							},{
			            		 xtype: 'button',                       
	                             text:'查询',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/search.png',
	                             listeners:{
	                               "click":doQry                                                           
	                             }
			            	},{
								xtype:'label',
								columnWidth:0.05,
								html: '&nbsp;&nbsp;'
							},{
			            		 xtype: 'button',                       
	                             text:'修改',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/modify.gif',
	                             listeners:{
	                               "click":doEdit                                                           
	                             }
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
	    initMainPanel : function(){
	    	var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/taskDetail/queryTaskListJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orderId' },
	                { name: 'changeContent' },
	                { name: 'publishCode' },
	                { name: 'changeTime' },
	                { name: 'orderStateCode' },
	                { name: 'createOrg' },
	                { name: 'createUser' },
	                { name: 'createTime' },
	                { name: 'completeTime' },
	                { name: 'isDelay' },
	                { name: 'cycleId' },
	                { name: 'noticeCycle' },
	                { name: 'alarmTime' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			sm, {
				header : "order_id",
				dataIndex : "orderId",
				hidden : true
			}, {
				header : "变更内容",
				dataIndex : "changeContent",
				width : Ext.getBody().getSize().width * 0.2,
			    renderer: function(value, meta, record) {    
			         meta.attr = 'style="white-space:normal;"';     
			         return value;     
			    }
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "变更时间",
				dataIndex : "changeTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "定单状态",
				dataIndex : "orderStateCode",
				width : Ext.getBody().getSize().width * 0.05,
				renderer:function(value){
					if(value=="10A"){
						return "初始化";
					}else if(value=="10B"){
						return "处理中";
					}else if(value=="10C"){
						return "完成";
					}else {
						return "未知";
					}
				}
			}, {
				header : "邮件通知周期（小时）",
				dataIndex : "noticeCycle",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "变更单使用通知时限（分钟）",
				dataIndex : "alarmTime",
				width : Ext.getBody().getSize().width * 0.13
			}, {
				header : "所属部门",
				dataIndex : "createOrg",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "创建人",
				dataIndex : "createUser",
				width : Ext.getBody().getSize().width * 0.04
			}, {
				header : "创建时间",
				dataIndex : "createTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "完成时间",
				dataIndex : "completeTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "是否延期",
				dataIndex : "isDelay",
				width : Ext.getBody().getSize().width * 0.05,
				renderer:function(value){
					if(value == 1){
						return "是";
					}else {
						return "否";
					}
				}
			}, {
				header : "cycle_id",
				dataIndex : "cycleId",
				hidden : true
			}]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "mainDataGrid",
				region : 'center',
				title : "详细数据",
				cm : cm,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				store : store,
				bbar: new Ext.PagingToolbar({
	                pageSize: pageSize,//每页显示的记录值
	                store: store,
	                displayInfo: true,
	                displayMsg: '总记录数 {0} - {1} of {2}',
	                emptyMsg: "没有记录"
	            })
			});       
	        store.load({ params: { start: 0, limit: pageSize} });

			return mainGrid;
		}
	};
})();

function doQry(){
	Ext.getCmp('mainDataGrid').store.removeAll();
	var parms = {
		start : 0,
		limit : pageSize,
		publishCode : Ext.getCmp('search_publish_code').getValue(),
		changeTime : Ext.getCmp('search_change_time').value,
		orderStateCode : Ext.getCmp('search_order_state_code').getValue(),
		createUser : Ext.getCmp('search_create_user').getValue(),
		isDelay : Ext.getCmp('search_is_delay').getValue(),
		createTimeBegin : Ext.getCmp('create_time_begin').getValue(),
		createTimeEnd : Ext.getCmp('create_time_end').getValue(),
		orgId : Ext.getCmp('search_org_id').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doEdit(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能修改变更单通知周期！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择变更单！');
		return;
	}
	var returnValue = window.showModalDialog(contextPath + "/cycle/getCycleEditDlg.do?orderId="+record.data.orderId+"&cycleId="+record.data.cycleId, null, "dialogHeight=150px;dialogwidth=350px;help=no;scrollbars=no;");
	if(returnValue) doQry();
//	Ext.MessageBox.prompt("输入框","请输入通知周期（小时）：",function(bu,txt){
//		if("cancel" == bu) return;
//		var noticeCyle = txt;  
//		if(noticeCyle == undefined || noticeCyle == null || noticeCyle == ""){
//			alert("通知周期不能为空！");
//			return;
//		}
//		if(noticeCyle == "0"){
//			alert("通知周期不能为0！");
//			return;
//		}
//		Ext.Ajax.request({
//			url : contextPath+'/taskDetail/taskCycleEdit.do?orderId='+record.data.orderId+'&cycleId='+record.data.cycleId,
//			waitTitle : '提示', 
//	 		waitMsg: '请稍后,正在提交数据...',
//			params : {
//				notice_cycle: noticeCyle
//			},
//			success : function(response, action) {
//				var responseText = Ext.decode(response.responseText);
//				if(responseText.success){
//					alert("通知周期修改成功！");
//					doQry();
//				}else {
//					alert(responseText.message);
//				}
//			},
//			failure : function(a) {
//				Ext.Msg.alert('提示', '通知周期修改失败！');
//			}
//		});
//	})
}

function doOrgQry(){
	var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectOrg.jsp", null, "dialogHeight=400px;dialogwidth=300px;help=no;scrollbars=no;");
	if(returnValue){
		Ext.getCmp('search_org_id').setValue(returnValue.id);
		Ext.getCmp('search_org_name').setValue(returnValue.name);
	}
}