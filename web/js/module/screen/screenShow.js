var pageSize = 4;
var page = 0;

//查询总共记录数
var storeCount = 100;

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	Ext.Ajax.request({
		url : contextPath+'/taskScreenShow/queryTaskScreenCount.do',
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				storeCount = obj.pageCount;
			}
		},
		failure : function() {
		}
	});
	
	var searchPanel = screenShow.initSearchPanel(); 
	var mainPanel = screenShow.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[searchPanel, mainPanel]
    });
    
    Ext.TaskMgr.start(task);
});

//页面配置对象
var screenShow = (function() {

	return {
		initSearchPanel : function(){
	        var searchPanel = new Ext.FormPanel({
	            region:'north',
	            id:'basicForm',
	            labelAlign: 'left',
	            labelWidth :70,
	            frame:true,      
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:30,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: [{ 
			            columnWidth:.7,
			            layout:'form',
			            items:[{   
			            	xtype:'label',
			            	html: '&nbsp;&nbsp;'
			            }] 
	            	},{ 
			            columnWidth:.3,
			            layout:'form',
			            items:[{   
			            	xtype:'label',
			            	html: '<span id="taskCount" style="font-size:25;">超时未完成变更合计： xx</span>'
			            }] 
	            	}]
	        });
	        return searchPanel;
	    },
	    
		initMainPanel : function(){
			var proxy = new Ext.data.HttpProxy({
	            url: contextPath + "/taskScreenShow/queryTaskScreenShowList.do",
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
				width : Ext.getBody().getSize().width * 0.09,
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
				hidden : true
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
				width : Ext.getBody().getSize().width * 0.255
			}, {
				header : "确认人",
				dataIndex : "confirmName",
				width : Ext.getBody().getSize().width * 0.09
			}, {
				header : "状态",
				dataIndex : "contractResult",
				width : Ext.getBody().getSize().width * 0.085
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
			return mainGrid;
		}
	};
})();

var task = { 
    run : function() {  
    	if(page * pageSize >= storeCount){
    		//进行挑战页面
    		var getTimestamp=new Date().getTime();
    		window.location.href = "http://10.234.11.21:9007/pub/problem1.aspx"+"?timestamp="+getTimestamp;
    		return;
    	}
    	Ext.Ajax.request({
    		url : contextPath+'/taskScreenShow/queryTaskScreenCount.do',
    		success : function(response, action) {
    			var obj = Ext.decode(response.responseText);
    			if(obj.success){
    				document.getElementById("taskCount").innerHTML="超时未完成变更合计： "+obj.pageCount+"";
    			}
    		},
    		failure : function() {
    		}
    	});
    	page = page + 1;
    	if(page > 10000) page = 1;
    	Ext.getCmp('mainDataGrid').store.load({ params: { page: page, 
        	pageSize : pageSize
    	} });
    },  
    interval : 1000 * 15  
    // 2 second  
}



