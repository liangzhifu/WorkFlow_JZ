var pageSize = 15;

var agrementProjectStore = new Ext.data.ArrayStore({  
    fields: ['agrementProjectCode', 'agrementProjectName'],  
    data: [  
        ['AB', 'AB'], 
        ['SB', 'SB'],
        ['SW', 'SW']  
    ]  
}); 

var agreementStateStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['agreementStateCode', 'agreementStateName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/agreement/getAgreementState.do"
   })
});
agreementStateStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = agreementSearch.initSearchPanel(); 
	var mainPanel = agreementSearch.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var agreementSearch = (function() {
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
	    	            	xtype:'combo',
	    	                fieldLabel:'工程',
	    	                name: 'search_agreement_project',
	    	                id: 'search_agreement_project',
	    	                valueField: 'agrementProjectCode',
	    	                displayField: 'agrementProjectName', 
	    	                triggerAction:'all',     
	    	                store: agrementProjectStore,
	    	                allowBlank : true,
	    					mode : 'local',
	    					valueField : 'id',
	    	                anchor:'95%',
	    	                emptyText:'-----请选择-----'
	    	            }] 
	    	    	},{ 
	    	            columnWidth:.25,
	    	            layout:'form',
	    	            items:[{   
	    	            	xtype:'textfield',
	                        fieldLabel:'切换LOT',
	                        name: 'search_agreement_cut_lot',
	                        id: 'search_agreement_cut_lot',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
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
				            	xtype:'datefield',
					            fieldLabel:'创建时间',
					            name: 'search_create_time',
					            id: 'search_create_time',  
					            format: 'Y-m-d', 
					            anchor:'99%'
				            }]
			            },{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'变更单状态',
		                    name: 'search_agreement_state_code',
		                    id: 'search_agreement_state_code',
		                    valueField: 'agreementStateCode',
		                    displayField: 'agreementStateName', 
		                    triggerAction:'all',     
		                    hideTrigger:false,
		                    allowBlank:true,
		                    editable: false, 
		                    store: agreementStateStore,
		                    anchor:'95%',
		                    emptyText:'-----请选择-----',
		                    showSelectAll:true
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
	                             text:'修改',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/modify.gif',
	                             listeners:{
	                               "click":doEditAgreement                                                           
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
	            url: contextPath + "/agreement/queryAgreementListJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'agreementId' },
	             	{ name: 'orderId' },
	             	{ name: 'isClose' },
	                { name: 'agreementName' },
	                { name: 'publishCode' },
	                { name: 'project' },
	                { name: 'cutLOT' },
	                { name: 'num' },
	                { name: 'createTime' },
	                { name: 'createUser' },
	                { name: 'agreementState' },
	                { name: 'conclusionState' },
	                { name: 'conclusionMessage' },
	                { name: 'invalidateText'}
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			sm, {
				header : "agreement_id",
				dataIndex : "agreementId",
				hidden : true
			}, {
				header : "order_id",
				dataIndex : "orderId",
				hidden : true
			}, {
				header : "is_close",
				dataIndex : "isClose",
				hidden : true
			}, {
				header : "立合编号",
				dataIndex : "agreementName",
				width : Ext.getBody().getSize().width * 0.1,
			    renderer: function(value, meta, record) {    
			    	return '<a href="'+contextPath+'/agreement/getAgreementDetailDlg.do?orderId='+record.data.orderId+'&agreementId='+record.data.agreementId+'" target="_blank">'+value+'</a>';    
			    }
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.1,
			    renderer: function(value, meta, record) {    
			    	return '<a href="'+contextPath+'/taskDetail/getTaskDetailDlg.do?orderId='+record.data.orderId+'" target="_blank">'+value+'</a>';  
			    }
			}, {
				header : "工程",
				dataIndex : "project",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "切换LOT",
				dataIndex : "cutLOT",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "数量",
				dataIndex : "num",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "创建时间",
				dataIndex : "createTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "立合状态",
				dataIndex : "agreementState",
				width : Ext.getBody().getSize().width * 0.1,
				renderer:function(value){
					if(value=="10B"){
						return "处理中";
					}else if(value=="10R"){
						return "待确认";
					}else if(value=="10C"){
						return "完成";
					}else if(value=="10N"){
						return "NG";
					}else if(value=="10X"){
						return "作废";
					}else {
						return "未知";
					}
				}
			}, {
				header : "创建人",
				dataIndex : "createUser",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "结论",
				dataIndex : "conclusionState",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "详细说明",
				dataIndex : "conclusionMessage",
				width : Ext.getBody().getSize().width * 0.1
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
	    		agreementProject : Ext.getCmp('search_agreement_project').getValue(),
	    		agreementCutLOT : Ext.getCmp('search_agreement_cut_lot').getValue(),
	    		createUser : Ext.getCmp('search_create_user').getValue(),
	    		createTime : Ext.getCmp('search_create_time').value,
	    		agreementState : Ext.getCmp('search_agreement_state_code').getValue()
	        	} });
	        
	        mainGrid.addListener('rowdblclick', rowdblclick);     
	        
	        function rowdblclick(grid, rowindex, e){     
	            grid.getSelectionModel().each(function(rec){  
	            	if(rec.get("orderStateCode")=="10X"){
	            		Ext.Msg.alert('提示','此变更单已作废！');
	            		return;
	            	}
	            	window.open(contextPath+"/taskDetail/getTaskDetailDlg.do?orderId="+rec.get("orderId"));
	            });     
	        }
	        
			return mainGrid;
		},
		
		onBeforechange2 : function(_p, _o) { 
            Ext.apply(_o, {
            	publishCode : Ext.getCmp('search_publish_code').getValue(),
        		agreementProject : Ext.getCmp('search_agreement_project').getValue(),
        		agreementCutLOT : Ext.getCmp('search_agreement_cut_lot').getValue(),
        		createUser : Ext.getCmp('search_create_user').getValue(),
        		createTime : Ext.getCmp('search_create_time').value,
        		agreementState : Ext.getCmp('search_agreement_state_code').getValue()
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
		agreementProject : Ext.getCmp('search_agreement_project').getValue(),
		agreementCutLOT : Ext.getCmp('search_agreement_cut_lot').getValue(),
		createUser : Ext.getCmp('search_create_user').getValue(),
		createTime : Ext.getCmp('search_create_time').value,
		agreementState : Ext.getCmp('search_agreement_state_code').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doDown(){
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择立合单！');
		return;
	}
	if(record.data.agreementState=="10X"){
		Ext.Msg.alert('提示','作废立合单不能导出！！');
		return;
	}
	var fileUrl = "";
	Ext.Ajax.request( {
		url : contextPath+'/agreement/doExportPDF.do',
 		async: false,
		params : {
			orderId : record.data.orderId,
			agreementId : record.data.agreementId			
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

function doEditAgreement(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能修改立合单！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择立合单！');
		return;
	}
	if(record.data.agreementSate=="10X"){
		Ext.Msg.alert('提示','作废立合单不能修改！');
		return;
	}
	if(record.data.isClose==1){
		if(parent.userId != 1202){
			alert("只有李亚楠才能修改已经关闭的立合单！");
			return;
		}
	}
	if(parent.userOrgId != 237){
		window.open(contextPath + "/agreement/getAgrementEditDlg.do?orderId="+record.data.orderId+"&agreementId="+record.data.agreementId);
	}else {
		window.open(contextPath + "/agreement/getAgrementEditDlg2.do?orderId="+record.data.orderId+"&agreementId="+record.data.agreementId);
	}
}

function doDelTask(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能作废立合单！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择立合单！');
		return;
	}
	if(record.data.orderStateCode=="10X"){
		Ext.Msg.alert('提示','作废立合单不能再次作废！');
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
			url : contextPath+'/agreement/delAgreement.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				orderId : record.data.orderId,
				agreementId : record.data.agreementId,
				invalidateText : invalidateText
			},
			success : function(response, action) {
				doQry();
				alert(response.reponseText.message);
			},
			failure : function(a) {
				Ext.Msg.alert('错误', '作废立合单失败！');
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