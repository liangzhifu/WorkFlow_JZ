var pageSize = 15;

var orderContractStateStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['orderContractStateCode', 'orderContractStateName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskScreen/getOrderContractState.do"
   })
});
orderContractStateStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = screenSearch.initSearchPanel(); 
	var mainPanel = screenSearch.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var screenSearch = (function() {
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
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'立合状态',
		                    name: 'search_order_contract_state_code',
		                    id: 'search_order_contract_state_code',
		                    valueField: 'orderContractStateCode',
		                    displayField: 'orderContractStateName', 
		                    triggerAction:'all',     
		                    hideTrigger:false,
		                    allowBlank:true,
		                    editable: false, 
		                    store: orderContractStateStore,
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
	                               "click":doEditTask                                                           
	                             }
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/taskScreen/queryTaskScreenShowList.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orderId' },
	                { name: 'changeTime' },
	                { name: 'publishCode' },
	                { name: 'productionLine' },
	                { name: 'carType' },
	                { name: 'mountingMat' },
	                { name: 'tacheName' },
	                { name: 'confirmName' },
	                { name: 'changeContent' },
	                { name: 'contractResult' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			{
				header : "order_id",
				dataIndex : "orderId",
				hidden : true
			}, {
				header : "变更时间",
				dataIndex : "changeTime",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "变更内容",
				dataIndex : "changeContent",
				width : Ext.getBody().getSize().width * 0.23
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
				header : "未确认部门",
				dataIndex : "tacheName",
				width : Ext.getBody().getSize().width * 0.2
			}, {
				header : "确认人",
				dataIndex : "confirmName",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "立合结果",
				dataIndex : "contractResult",
				width : Ext.getBody().getSize().width * 0.05
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
	    		changeTimeBegin : Ext.getCmp('search_change_time_begin').getValue() == "" ? "" : Ext.getCmp('search_change_time_begin').value,
	    		changeTimeEnd : Ext.getCmp('search_change_time_end').getValue() == "" ? "" : Ext.getCmp('search_change_time_end').value,
	    		orderContractStateCode : Ext.getCmp('search_order_contract_state_code').getValue()
	        	} });
	        
			return mainGrid;
		},
		
		onBeforechange2 : function(_p, _o) { 
            Ext.apply(_o, {
            	publishCode : Ext.getCmp('search_publish_code').getValue(),
            	changeTimeBegin : Ext.getCmp('search_change_time_begin').getValue() == "" ? "" : Ext.getCmp('search_change_time_begin').value,
	    		changeTimeEnd : Ext.getCmp('search_change_time_end').getValue() == "" ? "" : Ext.getCmp('search_change_time_end').value,
	    		orderContractStateCode : Ext.getCmp('search_order_contract_state_code').getValue()
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
		changeTimeBegin : Ext.getCmp('search_change_time_begin').getValue() == "" ? "" : Ext.getCmp('search_change_time_begin').value,
		changeTimeEnd : Ext.getCmp('search_change_time_end').getValue() == "" ? "" : Ext.getCmp('search_change_time_end').value,
		orderContractStateCode : Ext.getCmp('search_order_contract_state_code').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doEditTask(){
	if(parent.userOrgId != 237){
		alert("只有审核组织下的人员才能修改立合单！");
		return;
	}
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert('提示','请选择立合单！');
		return;
	}
	showCreateWin(record);
}

function showCreateWin(record){
	var url = contextPath+"/taskScreen/getScreenEditDlg.do?orderId="+record.data.orderId;
	win = new Ext.Window({
	    id: "myWin",
	    title: "修改立合结果",
	    width: 400,
	    height: 200,
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
