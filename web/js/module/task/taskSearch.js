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
	fields : ['isDelayCode', 'isDelayName'],
	proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskDetail/getIsDelay.do"
   })
});
isDelayStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = taskSearch.initSearchPanel(); 
	var mainPanel = taskSearch.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var taskSearch = (function() {
	var taskDetailUrl = contextPath + "/taskDetail/getTaskDetailDlg.do";
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
	            height:150,
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
	                        name: 'search_change_time_begin',
	                        id: 'search_change_time_begin',         
	                        anchor:'95%',
	                        format: 'Y-m-d'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'datefield',
	                        fieldLabel:'至',
	                        name: 'search_change_time_end',
	                        id: 'search_change_time_end',         
	                        anchor:'95%',
	                        format: 'Y-m-d'
			            }] 
	            	},{    
			            	columnWidth:.175,
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
				        	columnWidth:.055,
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
			            	columnWidth:0.25,
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
			            	columnWidth:0.25,
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
				        },{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'变更单状态',
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
		                    fieldLabel:'是否超时',
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
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'datefield',
	                        fieldLabel:'发行日期',
	                        name: 'search_issue_date',
	                        id: 'search_issue_date',         
	                        anchor:'95%',
	                        format: 'Y-m-d'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'生产线',
	                        name: 'search_production_line',
	                        id: 'search_production_line',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'车种名',
	                        name: 'search_car_type',
	                        id: 'search_car_type',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'安装席',
	                        name: 'search_mounting_mat',
	                        id: 'search_mounting_mat',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'变更内容',
	                        name: 'search_change_content',
	                        id: 'search_change_content',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:0.5,
			            layout:'form',
			            items:[{               
			            	layout:'column',
			            	items:[{
								xtype:'label',
								columnWidth:0.25,
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
	                             text:'新增',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/add.gif',
	                             listeners:{
	                               "click":doAddTask                                                           
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
	                               "click":doEditTask                                                           
	                             }
			            	},{
								xtype:'label',
								columnWidth:0.05,
								html: '&nbsp;&nbsp;'
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
								html: '&nbsp;&nbsp;'
							},{
			            		 xtype: 'button',                       
	                             text:'导出PDF',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/save.gif',
	                             listeners:{
	                               "click":doDown                                                           
	                             }
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/taskDetail/queryTaskListJson2.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orderId' },
	             	{ name: 'agreementId' },
	                { name: 'changeContent' },
	                { name: 'issueDate' },
	                { name: 'productionLine' },
	                { name: 'carType' },
	                { name: 'mountingMat' },
	                { name: 'publishCode' },
	                { name: 'agreementName' },
	                { name: 'changeTime' },
	                { name: 'orderStateCode' },
	                { name: 'createUser' },
	                { name: 'createOrg' },
	                { name: 'createTime' },
	                { name: 'completeTime' },
	                { name: 'isDelay' },
	                { name: 'agreementState'},
	                { name: 'invalidateText'}
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
				header : "agreement_id",
				dataIndex : "agreementId",
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
				header : "变更时间",
				dataIndex : "changeTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "发行日期",
				dataIndex : "issueDate",
				width : Ext.getBody().getSize().width * 0.06
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
			    	return '<a href="'+contextPath+'/taskDetail/getTaskDetailDlg.do?orderId='+record.data.orderId+'" target="_blank">'+value+'</a>';    
			    }
			}, {
				header : "立合编号",
				dataIndex : "agreementName",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
			    	return '<a href="'+contextPath+'/agreement/getAgreementDetailDlg.do?orderId='+record.data.orderId+'&agreementId='+record.data.agreementId+'" target="_blank">'+value+'</a>';    
			    }
			}, {
				header : "生产线",
				dataIndex : "productionLine",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "车种名",
				dataIndex : "carType",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "安装席",
				dataIndex : "mountingMat",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "定单状态",
				dataIndex : "orderStateCode",
				width : Ext.getBody().getSize().width * 0.1,
				renderer:function(value){
					if(value=="10A"){
						return "初始化";
					}else if(value=="10B"){
						return "处理中";
					}else if(value=="10C"){
						return "完成";
					}else if(value=="10X"){
						return "作废";
					}else {
						return "未知";
					}
				}
			}, {
				header : "立合状态",
				dataIndex : "agreementState",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "所属部门",
				dataIndex : "createOrg",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "创建人",
				dataIndex : "createUser",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "创建时间",
				dataIndex : "createTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "完成时间",
				dataIndex : "completeTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "是否超时",
				dataIndex : "isDelay",
				width : Ext.getBody().getSize().width * 0.1,
				renderer:function(value){
					if(value == 1){
						return "是";
					}else {
						return "否";
					}
				}
			}, {
				header : "作废原因",
				dataIndex : "invalidateText",
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
				store : store,
				bbar: new Ext.PagingToolbar({
					id: "pagingToolbar",
	                pageSize: pageSize,//每页显示的记录值
	                store: store,
	                displayInfo: true,
	                displayMsg: '总记录数 {0} - {1} of {2}',
	                emptyMsg: "没有记录"
	            })
			});    
			Ext.getCmp('pagingToolbar') .on("beforechange", this.onBeforechange2, this);
					    
	        store.load({ params: { start: 0, 
	        	limit : pageSize,
	    		publishCode : Ext.getCmp('search_publish_code').getValue(),
	    		changeTimeBegin : Ext.getCmp('search_change_time_begin').value,
	    		changeTimeEnd : Ext.getCmp('search_change_time_end').value,
	    		orderStateCode : Ext.getCmp('search_order_state_code').getValue(),
	    		createUser : Ext.getCmp('search_create_user').getValue(),
	    		isDelay : Ext.getCmp('search_is_delay').getValue(),
	    		createTimeBegin : Ext.getCmp('create_time_begin').getValue(),
	    		createTimeEnd : Ext.getCmp('create_time_end').getValue(),
	    		orgId : Ext.getCmp('search_org_id').getValue(),
	    		issueDate : Ext.getCmp('search_issue_date').value,
	    		changeContent : Ext.getCmp('search_change_content').getValue(),
	    		productionLine : Ext.getCmp('search_production_line').getValue(),
	    		carType : Ext.getCmp('search_car_type').getValue(),
	    		mountingMat : Ext.getCmp('search_mounting_mat').getValue()
	        	} });
	        
//	        mainGrid.addListener('rowdblclick', rowdblclick);     
//	        
//	        function rowdblclick(grid, rowindex, e){     
//	            grid.getSelectionModel().each(function(rec){  
//	            	if(rec.get("orderStateCode")=="10X"){
//	            		Ext.Msg.alert('提示','此变更单已作废！');
//	            		return;
//	            	}
//	            	window.open(contextPath+"/taskDetail/getTaskDetailDlg.do?orderId="+rec.get("orderId"));
//	            });     
//	        }
	        
			return mainGrid;
		},
		
		onBeforechange2 : function(_p, _o) { 
            Ext.apply(_o, {
            	publishCode : Ext.getCmp('search_publish_code').getValue(),
            	changeTimeBegin : Ext.getCmp('search_change_time_begin').value,
	    		changeTimeEnd : Ext.getCmp('search_change_time_end').value,
	    		orderStateCode : Ext.getCmp('search_order_state_code').getValue(),
	    		createUser : Ext.getCmp('search_create_user').getValue(),
	    		isDelay : Ext.getCmp('search_is_delay').getValue(),
	    		createTimeBegin : Ext.getCmp('create_time_begin').getValue(),
	    		createTimeEnd : Ext.getCmp('create_time_end').getValue(),
	    		orgId : Ext.getCmp('search_org_id').getValue(),
	    		issueDate : Ext.getCmp('search_issue_date').value,
	    		changeContent : Ext.getCmp('search_change_content').getValue(),
	    		productionLine : Ext.getCmp('search_production_line').getValue(),
	    		carType : Ext.getCmp('search_car_type').getValue(),
	    		mountingMat : Ext.getCmp('search_mounting_mat').getValue()
            });//增加自定义参数
	        return true;
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
		changeTimeBegin : Ext.getCmp('search_change_time_begin').value,
		changeTimeEnd : Ext.getCmp('search_change_time_end').value,
		orderStateCode : Ext.getCmp('search_order_state_code').getValue(),
		createUser : Ext.getCmp('search_create_user').getValue(),
		isDelay : Ext.getCmp('search_is_delay').getValue(),
		createTimeBegin : Ext.getCmp('create_time_begin').getValue(),
		createTimeEnd : Ext.getCmp('create_time_end').getValue(),
		orgId : Ext.getCmp('search_org_id').getValue(),
		issueDate : Ext.getCmp('search_issue_date').value,
		changeContent : Ext.getCmp('search_change_content').getValue(),
		productionLine : Ext.getCmp('search_production_line').getValue(),
		carType : Ext.getCmp('search_car_type').getValue(),
		mountingMat : Ext.getCmp('search_mounting_mat').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doDown(){
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择变更单！');
		return;
	}
	if(record.data.orderStateCode=="10X"){
		Ext.Msg.alert('提示','作废变更单不能导出！！');
		return;
	}
	var fileUrl = "";
	Ext.Ajax.request( {
		url : contextPath+'/taskDetail/doExportPDF.do',
 		async: false,
		params : {
			orderId : record.data.orderId
		},
 		method: "post",
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				fileUrl = contextPath + obj.path;
			}else {
				alert(obj.message);
				return;
			}
		},
		failure : function() {
			alert('导出PDF失败！');
			return;
		}
	});
	if(fileUrl != ""){
		window.open(fileUrl);
	}
}

function doAddTask(){
	window.open(contextPath + "/taskDetail/getTaskAddDlg.do?taskTypeId=1");
}

function doEditTask(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能修改变更单！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择变更单！');
		return;
	}
	if(record.data.orderStateCode=="10X"){
		Ext.Msg.alert('提示','作废变更单不能修改！！');
		return;
	}
	window.open(contextPath + "/taskDetail/getTaskEditDlg.do?orderId="+record.data.orderId);
}

function doDelTask(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能作废变更单！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择变更单！');
		return;
	}
	if(record.data.orderStateCode=="10X"){
		Ext.Msg.alert('提示','作废变更单不能再次作废！');
		return;
	}
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
				orderId : record.data.orderId,
				invalidateText : invalidateText
			},
			success : function(response, action) {
				doQry();
				alert(response.reponseText.message);
			},
			failure : function(a) {
				Ext.Msg.alert('错误', '作废变更单失败！');
			}
		});
	},this,60);
}

function doOrgQry(){
	var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectOrg.jsp", null, "dialogHeight=400px;dialogwidth=300px;help=no;scrollbars=no;");
	if(returnValue){
		Ext.getCmp('search_org_id').setValue(returnValue.id);
		Ext.getCmp('search_org_name').setValue(returnValue.name);
	}
}