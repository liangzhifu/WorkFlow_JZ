var pageSize = 4;
var page = 0;

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = screenShow2.initSearchPanel(); 
	var mainPanel = screenShow2.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel, mainPanel]
    });
});

//页面配置对象
var screenShow2 = (function() {

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
								columnWidth:0.45,
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
	                             text:'导出EXCEL',
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
	            url: contextPath + "/taskScreenShow/queryTaskScreenShowJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orderId' },
	                { name: 'changeDate' },
	                { name: 'changeTime' },
	                { name: 'publishCode' },
	                { name: 'productionLine' },
	                { name: 'carType' },
	                { name: 'mountingMat' },
	                { name: 'tacheName' },
	                { name: 'confirmName' },
	                { name: 'contractResult' },
	                { name: 'changeContent' },
	                { name: 'colorId' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
			var cm = new Ext.grid.ColumnModel([ 
			{
				header : "order_id",
				dataIndex : "orderId",
				hidden : true
			}, {
				header : "变更日",
				dataIndex : "changeDate",
				width : Ext.getBody().getSize().width * 0.04,
			    renderer: function(value, meta, record) {         
			         return value.substring(5);     
			    }
			}, {
				header : "变更时间",
				dataIndex : "changeTime",
				hidden : true
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.09
			}, {
				header : "变更内容",
				dataIndex : "changeContent",
				width : Ext.getBody().getSize().width * 0.36
			}, {
				header : "生产线",
				dataIndex : "productionLine",
				width : Ext.getBody().getSize().width * 0.11
			}, {
				header : "车种名",
				dataIndex : "carType",
				hidden : true
			}, {
				header : "安装席",
				dataIndex : "mountingMat",
				hidden : true
			}, {
				header : "未确认部门",
				dataIndex : "tacheName",
				width : Ext.getBody().getSize().width * 0.3
			}, {
				header : "确认人",
				dataIndex : "confirmName",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "状态",
				dataIndex : "contractResult",
				width : Ext.getBody().getSize().width * 0.04
			}, {
				header : "行颜色",
				dataIndex : "colorId",
				hidden : true
			} ]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "mainDataGrid",
				region : 'center',
				cm : cm,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				store : store,
				viewConfig:{
	                stripeRows: false,//是否隔行换色
	                getRowClass : function(record,rowIndex,rowParams,store){
	                    var type = record.get('colorId');
	                    switch (type){
	                    case 0:
	                        return '';
	                    case 1:
	                        return '';
	                    case 2:
	                        return 'x-grid-row-yellow x-grid3-row-checker';
	                    case 3:
	                        return 'x-grid-row-blue x-grid3-row-checker';
	                    case 4:
	                        return 'x-grid-row-red x-grid3-row-checker';
	                    case 5:
	                        return 'x-grid-row-orange x-grid3-row-checker';
	                    }
	                }
	            }
			});    
			store.load();
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
		publishCode : Ext.getCmp('search_publish_code').getValue(),
		changeTimeBegin : Ext.getCmp('search_change_time_begin').value,
		changeTimeEnd : Ext.getCmp('search_change_time_end').value,		
		issueDate : Ext.getCmp('search_issue_date').value,
		changeContent : Ext.getCmp('search_change_content').getValue(),
		productionLine : Ext.getCmp('search_production_line').getValue(),
		carType : Ext.getCmp('search_car_type').getValue(),
		mountingMat : Ext.getCmp('search_mounting_mat').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doDown(){
	var fileUrl = "";
	Ext.Ajax.request( {
		url : contextPath+'/taskScreenShow/doExportExcel.do',
 		async: false,
		params : {
			publishCode : Ext.getCmp('search_publish_code').getValue(),
			changeTimeBegin : Ext.getCmp('search_change_time_begin').value,
			changeTimeEnd : Ext.getCmp('search_change_time_end').value,		
			issueDate : Ext.getCmp('search_issue_date').value,
			changeContent : Ext.getCmp('search_change_content').getValue(),
			productionLine : Ext.getCmp('search_production_line').getValue(),
			carType : Ext.getCmp('search_car_type').getValue(),
			mountingMat : Ext.getCmp('search_mounting_mat').getValue()
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
			alert('导出Excel失败！');
			return;
		}
	});
	if(fileUrl != ""){
		window.open(fileUrl);
	}
}
