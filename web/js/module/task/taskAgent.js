var pageSize = 15;
var win = null;

var orderStateStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['orderStateCode', 'orderStateName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskDetail/getOrderState.do"
   })
});
orderStateStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = taskAgent.initSearchPanel(); 
	var mainPanel = taskAgent.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var taskAgent = (function() {

	return {
		
		initSearchPanel : function(){
	        var searchPanel = new Ext.FormPanel({
	            region:'north',
	            id:'basicForm',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,  
	            title: '查询条件',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:100,
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
			            	layout:'column',
			            	items:[{
								xtype:'label',
								columnWidth:0.4,
								html:'&nbsp;&nbsp;'
							},{
			            		 xtype: 'button',                       
	                             text:'查询',
	                             columnWidth:0.1,
	                             icon: '/WorkFlow/images/search.png',
	                             listeners:{
	                               "click":doQry                                                           
	                             }
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/taskDetail/queryTaskAgentListJson.do",
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
	                { name: 'createUser' },
	                { name: 'createOrg' },
	                { name: 'createTime' },
	                { name: 'completeTime' },
	                { name: 'isDelay' },
	                { name: 'agentId' },
	                { name: 'agentType' }
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
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {
					return "<a href='javascript:void(0)' onclick='openWin("+record.data.agentType+","+record.data.orderId+","+record.data.agentId+")'>"+value+"</a>"
				}
			}, {
				header : "变更时间",
				dataIndex : "changeTime",
				width : Ext.getBody().getSize().width * 0.08
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
				header : "所属部门",
				dataIndex : "createOrg",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "创建人",
				dataIndex : "createUser",
				width : Ext.getBody().getSize().width * 0.05
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
				header : "agent_id",
				dataIndex : "agentId",
				hidden : true
			},{
				header : "待办类型",
				dataIndex : "agentType",
				width : Ext.getBody().getSize().width * 0.1,
				renderer:function(value){
					if(value == 1){
						return "待确认";
					}else if(value == 2){
						return "填写内容";
					}else if(value == 3){
						return "待回复";
					}else if(value == 5){
						return "立合待填写";
					}else if(value == 6){
						return "立合待确认";
					}else {
						return "未知";
					}
				}
			} ]);
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
	        
	        mainGrid.on('rowdblclick', function(grid, rowIndex, columnIndex, e) {
	            var record=grid.getSelectionModel().getSelected();//选择记录
	            if(record == null){
	            	return;
	            }
	            var returnValue = null;
	            if(record.data.agentType == 1){
	            	var title = "变更单确认";
	            	var url = contextPath+'/taskDetail/getTaskConfirmDlg.do?orderId='+record.data.orderId+'&confirmOrderId='+record.data.agentId;
	            	showCreateWin(title, url);
	            }else if(record.data.agentType == 2){
	            	var title = "内容填写";
	            	var url = contextPath+"/taskDetail/getTaskStaffDetailDlg.do?orderId="+record.data.orderId;
	            	showCreateWin(title, url);
	            }else if(record.data.agentType == 3){
	            	var title = "内容确认";
	            	var url = contextPath+"/taskDetail/getTaskRefuseDlg.do?orderId="+record.data.orderId;
	            	showCreateWin(title, url);
	            }else if(record.data.agentType == 5){
	            	var title = "内容填写";
	            	var url = contextPath+"/agreement/getAgreementContentDlg.do?orderId="+record.data.orderId+'&agreementId='+record.data.agentId;
	            	showCreateWin(title, url);
	            }else if(record.data.agentType == 6){
	            	var title = "内容确认";
	            	var url = contextPath+"/agreement/getAgreementConfirmDlg.do?orderId="+record.data.orderId+'&agreementId='+record.data.agentId;
	            	showCreateWin(title, url);
	            }else {
	            	alert("未知代办!");
	            	return;
	            }
	        }, this);
	        
			return mainGrid;
		}
	};
})();

function doQry(){
	if (!Ext.getCmp('basicForm').getForm().isValid()) {
		alert("请输入正确的格式！");
		return;
	}
	Ext.getCmp('mainDataGrid').store.removeAll();
	var parms = {
		start : 0,
		limit : pageSize,
		publishCode : Ext.getCmp('search_publish_code').getValue(),
		changeTime : Ext.getCmp('search_change_time').value,
		orderStateCode : Ext.getCmp('search_order_state_code').getValue(),
		createUser : Ext.getCmp('search_create_user').getValue(),
		createTimeBegin : Ext.getCmp('create_time_begin').getValue(),
		createTimeEnd : Ext.getCmp('create_time_end').getValue(),
		orgId : Ext.getCmp('search_org_id').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doOrgQry(){
	var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectOrg.jsp", null, "dialogHeight=400px;dialogwidth=300px;help=no;scrollbars=no;");
	if(returnValue){
		Ext.getCmp('search_org_id').setValue(returnValue.id);
		Ext.getCmp('search_org_name').setValue(returnValue.name);
	}
}

function showCreateWin(titile, url){
	win = new Ext.Window({
	    id: "myWin",
	    title: titile,
	    width: document.body.clientWidth-100,
	    height: 450,
	    layout: "fit",
	    modal : true,
	    autoShow: true,
	    closeAction: 'close',
	    html: '<iframe style="overflow:scroll;width:100%; height:100%;" src="'+url+'" frameborder="0"></iframe>'
	});
	win.show();
}

function closeCreateWin(){
	win.close();
}

function openWin(agentType, orderId, agentId){
	if(agentType == 1){
    	var title = "变更单确认";
    	var url = contextPath+'/taskDetail/getTaskConfirmDlg.do?orderId='+orderId+'&confirmOrderId='+agentId;
    	showCreateWin(title, url);
    }else if(agentType == 2){
    	var title = "内容填写";
    	var url = contextPath+"/taskDetail/getTaskStaffDetailDlg.do?orderId="+orderId;
    	showCreateWin(title, url);
    }else if(agentType == 3){
    	var title = "内容确认";
    	var url = contextPath+"/taskDetail/getTaskRefuseDlg.do?orderId="+orderId;
    	showCreateWin(title, url);
    }else if(agentType == 5){
    	var title = "内容填写";
    	var url = contextPath+"/agreement/getAgreementContentDlg.do?orderId="+orderId+'&agreementId='+agentId;
    	showCreateWin(title, url);
    }else if(agentType == 6){
    	var title = "内容确认";
    	var url = contextPath+"/agreement/getAgreementConfirmDlg.do?orderId="+orderId+'&agreementId='+agentId;
    	showCreateWin(title, url);
    }else {
    	alert("未知代办!");
    	return;
    }
}