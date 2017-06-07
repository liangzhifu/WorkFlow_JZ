//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = agreementDetail.initBasicPanel(); 
	var mainPanel = agreementDetail.initMainPanel();
	
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel]
    });
});

//页面配置对象
var agreementDetail = (function() {
	 
	return {		
		initBasicPanel : function(){
			var items = new Array();
			var agreementJSON2 = agreementJSON.replace("\\", '\\\\');
			agreementJSON2 = agreementJSON2.replace(/\r\n/g,'</br>');
			agreementJSON2 = agreementJSON2.replace(/\n\r/g,'</br>');
			agreementJSON2 = agreementJSON2.replace(/\n/g,'</br>');
	    	var agreement = Ext.decode(agreementJSON2);
			items = [{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'发行编号',
			            value:'<a href="'+contextPath+'/taskDetail/getTaskDetailDlg.do?orderId='+agreement.orderId+'" target="_blank">'+agreement.publishCode+'</a>',
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'立合编号',
			            value:agreement.agreementName,
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'创建人',
			            value:agreement.createUser,
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'创建时间',
			            value:agreement.createTime,
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'工程',
			            value:agreement.project,
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'切换LOT',
			            value:agreement.cutLOT,
			            anchor:'100%'
		            }]
            	},{ 
		            columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'数量',
			            value:agreement.num,
			            anchor:'100%'
		            }]
            	}];
			var agreementTacheArray = agreement.agreementTacheList;
			for(var i = (0 + 7); i < (agreementTacheArray.length + 7); i ++){
				items[i] =  generBasicObj(agreementTacheArray[i - 7].tacheName, agreementTacheArray[i - 7].userName);
			}
			items[agreementTacheArray.length + 7] = {
					columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'结论',
			            value:agreement.conclusionState,
			            anchor:'100%'
		            }]
			};
			items[agreementTacheArray.length + 8] = {
					columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'详细说明',
			            value:agreement.conclusionMessage,
			            anchor:'100%'
		            }]
			};
			items[agreementTacheArray.length + 9] = {
					columnWidth:.25,
		            layout:'form',
		            items:[{ 
		            	xtype:'displayfield',
			            fieldLabel:'追踪者',
			            value:agreement.trackName,
			            anchor:'100%'
		            }]
			};
	        var basicPanel = new Ext.FormPanel({
	        	region:'north',
	        	id:'basicForm',
	            labelAlign: 'right',
	            labelWidth :120,
	            frame:true,  
	            title: '基本信息',       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:180,
	            width:Ext.getBody().getSize().width-2,
	            layout:'column',
	            items: items,
	            autoScroll:true
	        });
	        return basicPanel;
	    },

	    initMainPanel : function(){
	    	var dataArray = new Array();
	    	var agreementJSON2 = agreementJSON.replace("\\", '\\\\');
	    	agreementJSON2 = agreementJSON2.replace(/\r\n/g,'</br>');
			agreementJSON2 = agreementJSON2.replace(/\n\r/g,'</br>');
			agreementJSON2 = agreementJSON2.replace(/\n/g,'</br>');
	    	var agreement = Ext.decode(agreementJSON2);
	    	var agreementConfirmArray = agreement.agreementContentList;
	    	for(var i = 0; i < agreementConfirmArray.length; i++){
				var data = generMainObj(orderId, agreementConfirmArray[i]);
				dataArray[i] = data;
			}
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "orderId", "agreementId", "id", "seq", "problem", "improve", "responsible", "term", "state", "confirm" ],
	    		data : dataArray
	    	});
			var cm = new Ext.grid.ColumnModel([ {
				header : "",
				dataIndex : "orderId",
				hidden : true
			},{
				header : "",
				dataIndex : "agreementId",
				hidden : true
			},{
				header : "",
				dataIndex : "id",
				hidden : true
			},{
				header : "序号",
				dataIndex : "seq",
				width : Ext.getBody().getSize().width * 0.03
			}, {
				header : "问题点",
				dataIndex : "problem",
				width : Ext.getBody().getSize().width * 0.35
			}, {
				header : "改善对策",
				dataIndex : "improve",
				width : Ext.getBody().getSize().width * 0.35
			}, {
				header : "责任人",
				dataIndex : "responsible",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "改善期限",
				dataIndex : "term",
				width : Ext.getBody().getSize().width * 0.08
			}, {
				header : "状态",
				dataIndex : "state",
				width : Ext.getBody().getSize().width * 0.05
			}, {
				header : "确认者",
				dataIndex : "confirm",
				width : Ext.getBody().getSize().width * 0.08
			}]);
			var mainGrid = new Ext.grid.GridPanel({
				id : "mainDataGrid",
				region : 'center',
				title : "详细数据",
				cm : cm,
				loadMask : true,
				// 超过长度带自动滚动条
				autoScroll : true,
				border : false,
				sortable : false,
				store : dataStore
			});
			return mainGrid;
	    }
	};
})();

function generBasicObj(tacheName, userName){
	var obj = { 
            columnWidth:.25,
            layout:'form',
            items:[{ 
            	xtype:'displayfield',
	            fieldLabel:tacheName,
	            value:userName,
	            anchor:'100%'
            }]
    	};
	return obj;
}

function generMainObj(orderId, agreementContent){
	var obj = new Array();
	obj[0] = orderId;
	obj[1] = agreementContent.agreementId;
	obj[2] = agreementContent.id;
	obj[3] = agreementContent.seq;
	var problem = "";
	if(agreementContent.problem == undefined){
		
	}else {
		problem = agreementContent.problem.replace(/<\/br>/g, "<br>");
	}
	obj[4] = problem;
	var improve = "";
	if(agreementContent.improve == undefined){
		
	}else {
		improve = agreementContent.improve.replace(/<\/br>/g, "<br>");
	}
	obj[5] = improve;
	obj[6] = agreementContent.responsibleName;
	obj[7] = agreementContent.termStr;
	obj[8] = agreementContent.state;
	obj[9] = agreementContent.confirmName;
	return obj;
}
