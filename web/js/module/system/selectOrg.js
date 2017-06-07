var pageSize = 15;

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = selectOrg.initSearchPanel(); 
	var mainPanel = selectOrg.initMainPanel();
	var buttonPanel = selectOrg.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var selectOrg = (function() {
	
	return {
		
		initSearchPanel : function(){
	        var searchPanel = new Ext.FormPanel({
	            region:'north',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,  
	            title: '查询条件',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:75,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: [{ 
			            columnWidth:.7,
			            layout:'form',
			            items:[{   
			            	xtype:'textfield',
	                        fieldLabel:'组织名',
	                        name: 'org_name',
	                        id: 'org_name',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	layout:'column',
			            	items:[{
								xtype:'label',
								columnWidth:0.45,
								text: ''
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
	            url: contextPath + "/org/queryOrgListJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'orgId' },
	                { name: 'orgName' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			sm, {
				header : "org_id",
				dataIndex : "orgId",
				hidden : true
			}, {
				header : "组织名",
				dataIndex : "orgName",
				width : Ext.getBody().getSize().width * 0.9
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
						columnWidth:0.375,
						html: '&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'确定',
                         columnWidth:0.1,
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
                         columnWidth:0.1,
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

function doQry(){
	Ext.getCmp('mainDataGrid').store.removeAll();
	var parms = {
		start : 0,
		limit : pageSize,
		orgName : Ext.getCmp('orgName').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}

function doSubmit(){
	var record = Ext.getCmp('mainDataGrid').getSelectionModel().getSelected();
	var value = null;
	if(record==null){
		value = {
			id : "",
			name :""
		}
	}else {
		value = {
			id : record.data.orgId,
			name :record.data.orgName
		}
	}
	window.returnValue = value;
	window.open('','_self','');
	window.close();
}

function doClose(){
	window.open('','_self','');
	window.close();
}