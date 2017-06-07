var pageSize = 15;

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = agreementOrderSearch.initSearchPanel(); 
	var mainPanel = agreementOrderSearch.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var agreementOrderSearch = (function() {
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
	            height:80,
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
	                        minValue:new Date(new Date()- 0 - 7*86400000).clearTime(),
	                        format: 'Y-m-d'
			            }] 
	            	},{ 
			            columnWidth:0.5,
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
	                             text:'立合',
	                             columnWidth:0.05,
	                             icon: '/WorkFlow/images/add.gif',
	                             listeners:{
	                               "click":doAddAgreement                                                           
	                             }
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/agreementOrder/queryAgreementOrderJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orderId' },
	                { name: 'changeContent' },
	                { name: 'issueDate' },
	                { name: 'productionLine' },
	                { name: 'carType' },
	                { name: 'mountingMat' },
	                { name: 'publishCode' },
	                { name: 'changeTime' },
	                { name: 'orderStateCode' },
	                { name: 'createUser' },
	                { name: 'createOrg' },
	                { name: 'createTime' },
	                { name: 'completeTime' },
	                { name: 'isDelay' }
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
				width : Ext.getBody().getSize().width * 0.1
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
	    		changeTime : Ext.getCmp('search_change_time').getValue() == "" ? "" : Ext.getCmp('search_change_time').value
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
            	changeTime : Ext.getCmp('search_change_time').getValue() == "" ? "" : Ext.getCmp('search_change_time').value
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
		changeTime : Ext.getCmp('search_change_time').getValue() == "" ? "" : Ext.getCmp('search_change_time').value
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doAddAgreement(){
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	if(record == null){
		Ext.Msg.alert('提示','请选择变更单！');
		return;
	}
	window.open(contextPath + "/agreement/getAgreementAddNewDlg.do?orderId="+record.data.orderId);
}
