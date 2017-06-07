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
	var searchPanel = taskStatistics.initSearchPanel(); 
	var mainPanel = taskStatistics.initMainPanel();
	var orgPanel = taskStatistics.initOrgPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel,orgPanel]
    });
});

//页面配置对象
var taskStatistics = (function() {
	
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
		            	columnWidth:.18,
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
		            	columnWidth:.0025,
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
			        	columnWidth:.0625,
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
			            	xtype:'datefield',
				            fieldLabel:'创建时间',
				            name: 'create_time_begin',
				            id: 'create_time_begin',  
				            format: 'Y-m-d',
					        editable:true,  
				            anchor:'99%'
			            }]
		            },{
		            	columnWidth:0.25,
			            layout:'form',
			            items:[{ 
			            	xtype:'datefield',  
				            fieldLabel:'至',
				            name: 'create_time_end',
				            id: 'create_time_end',  
				            format: 'Y-m-d',
					        editable:true,  
				            labelWidth :20,
				            anchor:'99%'
			            }]
	            	},{ 
		            	columnWidth:0.25,
			            layout:'form',
			            items:[{ 
			            	xtype:'datefield',
				            fieldLabel:'变更时间',
				            name: 'change_time_begin',
				            id: 'change_time_begin',  
				            format: 'Y-m-d',
					        editable:true,  
				            anchor:'99%'
			            }]
		            },{
		            	columnWidth:0.25,
			            layout:'form',
			            items:[{ 
			            	xtype:'datefield',  
				            fieldLabel:'至',
				            name: 'change_time_end',
				            id: 'change_time_end',  
				            format: 'Y-m-d',
					        editable:true,  
				            labelWidth :20,
				            anchor:'99%'
			            }] 
	            	},{ 
		            	columnWidth:0.25,
			            layout:'form',
			            items:[{ 
			            	xtype:'datefield',
				            fieldLabel:'改善时间',
				            name: 'improve_time_begin',
				            id: 'improve_time_begin',  
				            format: 'Y-m-d',
					        editable:true,  
				            anchor:'99%'
			            }]
		            },{
		            	columnWidth:0.25,
			            layout:'form',
			            items:[{ 
			            	xtype:'datefield',  
				            fieldLabel:'至',
				            name: 'improve_time_end',
				            id: 'improve_time_end',  
				            format: 'Y-m-d',
					        editable:true,  
				            labelWidth :20,
				            anchor:'99%'
			            }] 
	            	},{ 
			            columnWidth:1,
			            layout:'form',
			            items:[{               
			            	layout:'column',
			            	items:[{
								xtype:'label',
								columnWidth:0.45,
								html:'&nbsp;&nbsp;'
							},{
			            		 xtype: 'button',                       
	                             text:'查询',
	                             columnWidth:0.05,
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
	            url: contextPath + "/statistics/queryStatisticsJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'totalNum' },
	                { name: 'completeNum' },
	                { name: 'delayNum' },
	                { name: 'allDelayNum' },
	                { name: 'completeDelayNum' },
	                { name: 'notCompleteNum' },
	                { name: 'noAgreementNum' },
	                { name: 'improveNum' },
	                { name: 'ngNum' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			{
				header : "总数",
				dataIndex : "totalNum",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "超时数量",
				dataIndex : "allDelayNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'超时数量\',\'\',\'1\',\'\');">'+value+'</a>';
				}
			}, {
				header : "完成数量",
				dataIndex : "completeNum",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "（完成）超时数量",
				dataIndex : "completeDelayNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'（完成）超时数量\',\'1\',\'1\',\'\');">'+value+'</a>';
				}
			}, {
				header : "未完成数量",
				dataIndex : "notCompleteNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'（完成）超时数量\',\'0\',\'\',\'\');">'+value+'</a>';
				}
			}, {
				header : "（未完成）超时数量",
				dataIndex : "delayNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'（未完成）超时数量\',\'0\',\'1\',\'\');">'+value+'</a>';
				}
			}, {
				header : "未立合(立合)",
				dataIndex : "noAgreementNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'未立合(立合)\',\'2\',\'\',\'\');">'+value+'</a>';
				}
			}, {
				header : "未完成(立合)",
				dataIndex : "improveNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'未完成(立合)\',\'3\',\'\',\'\');">'+value+'</a>';
				}
			}, {
				header : "NG(立合)",
				dataIndex : "ngNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'NG(立合)\',\'4\',\'\',\'\');">'+value+'</a>';
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
				store : store
			});       
	        store.load();

			return mainGrid;
		},
	    
	    initOrgPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/statistics/queryOrgStatisticsJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'name' },
	             	{ name: 'tacheId' },
	             	{ name: 'totalNum' },
	                { name: 'completeNum' },
	                { name: 'completeDelayNum' },
	                { name: 'notCompleteNum' },
	                { name: 'noShowNum'}
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			{
				header : "",
				dataIndex : "tacheId",
				hidden : true
			},{
				header : "部门/科室",
				dataIndex : "name",
				width : Ext.getBody().getSize().width * 0.2
			}, {
				header : "总数",
				dataIndex : "totalNum",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "完成数量",
				dataIndex : "completeNum",
				width : Ext.getBody().getSize().width * 0.1
			}, {
				header : "（完成）超时数量",
				dataIndex : "completeDelayNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'（完成）超时数量\',\'1\',\'1\',\''+record.data['tacheId']+'\');">'+value+'</a>';
				}
			}, {
				header : "未完成数量",
				dataIndex : "notCompleteNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'未完成数量\',\'0\',\'\',\''+record.data['tacheId']+'\');">'+value+'</a>';
				}
			}, {
				header : "未出席立合",
				dataIndex : "noShowNum",
				width : Ext.getBody().getSize().width * 0.1,
				renderer: function(value, meta, record) {    
					return '<a href="javascript:void(0)" onclick="showCreateWin(\'未出席立合数量\',\'5\',\'\',\''+record.data['tacheId']+'\');">'+value+'</a>';
				}
			}]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "orgDataGrid",
				region : 'south',
				title : "部门/科室详细数据",
				cm : cm,
				height: 300,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				store : store
			});       
	        store.load();

			return mainGrid;
		}
		
	};
})();

function doQry(){
	Ext.getCmp('mainDataGrid').store.removeAll();
	var parms = {
		createUser : Ext.getCmp('search_create_user').getValue(),
		createTimeBegin : Ext.util.Format.date(Ext.getCmp('create_time_begin').getValue(), 'Y-m-d'),
		createTimeEnd : Ext.util.Format.date(Ext.getCmp('create_time_end').getValue(), 'Y-m-d'),
		changeTimeBegin : Ext.util.Format.date(Ext.getCmp('change_time_begin').getValue(), 'Y-m-d'),
		changeTimeEnd : Ext.util.Format.date(Ext.getCmp('change_time_end').getValue(), 'Y-m-d'),
		improveTimeBegin : Ext.util.Format.date(Ext.getCmp('improve_time_begin').getValue(), 'Y-m-d'),
		improveTimeEnd : Ext.util.Format.date(Ext.getCmp('improve_time_end').getValue(), 'Y-m-d'),
		orgId : Ext.getCmp('search_org_id').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
	Ext.getCmp('orgDataGrid').getStore().load({ params: parms });
}

function doOrgQry(){
	var returnValue = window.showModalDialog(contextPath + "/jsp/system/selectOrg.jsp", null, "dialogHeight=400px;dialogwidth=300px;help=no;scrollbars=no;");
	if(returnValue){
		Ext.getCmp('search_org_id').setValue(returnValue.id);
		Ext.getCmp('search_org_name').setValue(returnValue.name);
	}
}

function showCreateWin(title,isComplete,isDelay,tacheId){
	var proxy = new Ext.data.HttpProxy({
        url: contextPath + "/statisticsOrder/queryStatisticsOrderJson.do",
        method: 'POST'
    });

    var reader = new Ext.data.JsonReader(
    { root: 'rows', totalProperty: 'total' },
        [
         	{ name: 'orderId' },
         	{ name: 'changeContent' },
            { name: 'issueDate' },
            { name: 'publishCode' },
            { name: 'productionLine' },
            { name: 'carType' },
            { name: 'mountingMat' },
            { name: 'changeTime' },
            { name: 'orderStateCode' }
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
	}]);
	var mainGrid = new Ext.grid.GridPanel({
		id : "dataGrid",
		region : 'south',
		cm : cm,
		height: 300,
		loadMask : true,
		// 超过长度带自动滚动条
		autoScroll : true,
		store : store
	});       
    store.load({ params: {
    	isComplete : isComplete,
    	isDelay : isDelay,
    	tacheId : tacheId,
    	createUser : Ext.getCmp('search_create_user').getValue(),
		createTimeBegin : Ext.util.Format.date(Ext.getCmp('create_time_begin').getValue(), 'Y-m-d'),
		createTimeEnd : Ext.util.Format.date(Ext.getCmp('create_time_end').getValue(), 'Y-m-d'),
		changeTimeBegin : Ext.util.Format.date(Ext.getCmp('change_time_begin').getValue(), 'Y-m-d'),
		changeTimeEnd : Ext.util.Format.date(Ext.getCmp('change_time_end').getValue(), 'Y-m-d'),
		improveTimeBegin : Ext.util.Format.date(Ext.getCmp('improve_time_begin').getValue(), 'Y-m-d'),
		improveTimeEnd : Ext.util.Format.date(Ext.getCmp('improve_time_end').getValue(), 'Y-m-d'),
		orgId : Ext.getCmp('search_org_id').getValue()
    }});
    
	win = new Ext.Window({
	    id: "myWin",
	    title: title,
	    width: document.body.clientWidth-100,
	    height: 450,
	    layout: "fit",
	    modal : true,
	    autoShow: true,
	    closeAction: 'close',
	    items:mainGrid
	});
	win.show();
}
		