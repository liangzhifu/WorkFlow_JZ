var pageSize = 15;

var userStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/history/getAgreementUser.do"
   })
});
userStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var searchPanel = agreementHistory.initSearchPanel(); 
	var mainPanel = agreementHistory.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel,mainPanel]
    });
});

//页面配置对象
var agreementHistory = (function() {
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
			            	xtype:'textfield',
	                        fieldLabel:'立合编号',
	                        name: 'search_agreement_name',
	                        id: 'search_agreement_name',         
	                        anchor:'95%',
	                        emptyText:'-----请输入-----'
			            }] 
	            	},{ 
			            columnWidth:.25,
			            layout:'form',
			            items:[{               
			            	xtype:'combo',
		                    fieldLabel:'修改人',
		                    name: 'search_edit_user',
		                    id: 'search_edit_user',
		                    valueField: 'userId',
		                    displayField: 'userName', 
		                    triggerAction:'all',     
		                    hideTrigger:false,
		                    allowBlank:true,
		                    editable: false, 
		                    store: userStore,
		                    anchor:'95%',
		                    emptyText:'-----请选择-----',
		                    showSelectAll:true
			            }] 
	            	},{ 
			            columnWidth:0.25,
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
			            	}]
		            	}] 
	            	}]
	        });
	        return searchPanel;
	    },
		
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/history/queryHistoryAgreementListJson.do",
	            method: 'POST'
	        });

	        var reader = new Ext.data.JsonReader(
	        { root: 'rows', totalProperty: 'total' },
	            [
	             	{ name: 'id' },
	             	{ name: 'publishCode' },
	                { name: 'agreementName' },
	                { name: 'editUserName' },
	                { name: 'editTimeStr' },
	                { name: 'editType' }
	            ]
	        );

	        var store = new Ext.data.Store(
	            { proxy: proxy, reader: reader }
	        );
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
			var cm = new Ext.grid.ColumnModel([ 
			sm, {
				header : "id",
				dataIndex : "id",
				hidden : true
			}, {
				header : "发行编号",
				dataIndex : "publishCode",
				width : Ext.getBody().getSize().width * 0.19
			}, {
				header : "立合编号",
				dataIndex : "agreementName",
				width : Ext.getBody().getSize().width * 0.19,
				renderer: function(value, meta, record) {    
			    	return '<a href="'+contextPath+'/history/getHistoryAgreementDetailDlg.do?historyAgreementId='+record.data.id+'" target="_blank">'+value+'</a>';    
			    }
			}, {
				header : "修改人",
				dataIndex : "editUserName",
				width : Ext.getBody().getSize().width * 0.19
			}, {
				header : "修改时间",
				dataIndex : "editTimeStr",
				width : Ext.getBody().getSize().width * 0.19
			}, {
				header : "修改类型",
				dataIndex : "editType",
				width : Ext.getBody().getSize().width * 0.19
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
	    		agreementName : Ext.getCmp('search_agreement_name').getValue(),
	    		editUser : Ext.getCmp('search_edit_user').getValue()
	        } });

			return mainGrid;
		},
		
		onBeforechange2 : function(_p, _o) { 
            Ext.apply(_o, {
            	publishCode : Ext.getCmp('search_publish_code').getValue(),
	    		agreementName : Ext.getCmp('search_agreement_name').getValue(),
	    		editUser : Ext.getCmp('search_edit_user').getValue()
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
		agreementName : Ext.getCmp('search_agreement_name').getValue(),
		editUser : Ext.getCmp('search_edit_user').getValue()
	};
	Ext.getCmp('mainDataGrid').getStore().load({ params: parms });
}