var pageSize = 15;

var orderContractStateStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['orderContractStateCode', 'orderContractStateName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskScreen/getOrderContractState2.do"
   })
});
orderContractStateStore.load();

var orderContractUserStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
       url: contextPath + "/taskScreen/getOrderContractUser.do"
   })
});
orderContractUserStore.load();

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = screenEdit.initBasicPanel(); 
	var buttonPanel = screenEdit.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,buttonPanel]
    });
});

//页面配置对象
var screenEdit = (function() {
	return {
		initBasicPanel : function(){
			var items = new Array();
			items[0] = { 
		            columnWidth:1,
		            layout:'form',
		            items:[{               
		            	xtype:'combo',
	                    fieldLabel:'立合状态',
	                    name: 'search_order_contract_state_code',
	                    id: 'search_order_contract_state_code',
	                    hiddenName: 'orderContractStateCode',
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
            	};
			items[1] = { 
		            columnWidth:1,
		            layout:'form',
		            items:[{               
		            	xtype:'combo',
	                    fieldLabel:'确认人',
	                    name: 'search_order_contract_user',
	                    id: 'search_order_contract_user',
	                    hiddenName: 'userId',
	                    valueField: 'userId',
	                    displayField: 'userName', 
	                    triggerAction:'all',     
	                    hideTrigger:false,
	                    allowBlank:true,
	                    editable: false, 
	                    store: orderContractUserStore,
	                    anchor:'95%',
	                    emptyText:'-----请选择-----',
	                    showSelectAll:true
		            }] 
            	};
//			var items2 = new Array();
//			var infoArray = new Array();
//			var checkedArray = new Array();
//			if(!""==contractOrgs){
//				checkedArray = contractOrgs.split(",");
//			}
//			infoArray[0] = {"id":1, "name":"制造科"};
//			infoArray[1] = {"id":2, "name":"生产技术"};
//			infoArray[2] = {"id":3, "name":"设备保全"};
//			infoArray[3] = {"id":4, "name":"工艺管理"};
//			infoArray[4] = {"id":5, "name":"计划"};
//			infoArray[5] = {"id":6, "name":"仓库"};
//			infoArray[6] = {"id":7, "name":"品质/客户品质"};
//			infoArray[7] = {"id":8, "name":"品质检查"};
//			infoArray[8] = {"id":9, "name":"过程评价"};
//			infoArray[9] = {"id":10, "name":"系统评价"};
//			infoArray[10] = {"id":11, "name":"IT/总务"};
//			for(var i = 0; i < infoArray.length; i++){
//				var checked = false;
//				var infoValue = infoArray[i];
//				var infoKey = infoValue.id;
//				var boxLabel = infoValue.name;
//				for(var j = 0; j < checkedArray.length; j++){
//					if(infoValue.id == checkedArray[j]){
//						checked = true;
//					}
//				}
//				var item = {
//					boxLabel : boxLabel,
//					name : 'order_contract_check',
//					inputValue : infoValue.id
//				};
//				items2[i] = item;
//			}
//			items[1] = { 
//			        columnWidth:1,
//			        layout:'form',
//			        items:[{               
//			           xtype:'checkboxgroup',
//			           readOnly: false,   
//			           fieldLabel:"未确认部门",
//			           name: 'order_contract',
//			           id: 'order_contract', 
//			           items : items2,
//			           anchor:'95%'
//			        }]
//				};
			 var basicPanel = new Ext.FormPanel({
		        	region:'center',
		        	id:'basicForm',
		            labelAlign: 'right',
		            labelWidth :70,
		            frame:true,      
		            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
		            height:240,
		            width:Ext.getBody().getSize().width-2,
		            layout:'column',
		            items: items,
		            autoScroll:true
		        });
		        return basicPanel;
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
						columnWidth:0.425,
						html: '&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'确定',
                         columnWidth:0.05,
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
                         columnWidth:0.05,
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

function doSubmit(){	
	//判断是否为空
	var orderContractStateCode = Ext.getCmp("search_order_contract_state_code").getValue();
	if(orderContractStateCode == undefined || orderContractStateCode == null || orderContractStateCode == ""){
		alert("请选择立合状态!");
		return;
	}
	var orderContractUser = Ext.getCmp("search_order_contract_user").getValue();
	if(orderContractUser == undefined || orderContractUser == null || orderContractUser == ""){
		alert("请选择确认人!");
		return;
	}
	 
	var submitBasicUrl = contextPath + '/taskScreen/editOrderContract.do';
	Ext.getCmp('basicForm').getForm().submit({
		url : submitBasicUrl,
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			orderId : orderId
		},
		async: false,
		success : function(form, action) {
			alert('修改立合成功！');
			parent.doQry();
			parent.closeCreateWin();
		},
		failure : function(form, action) {
			var responseText = Ext.decode(action.response.responseText);
			Ext.Msg.alert('提示', responseText.message);
		}
	});
}

function doClose(){
	parent.doQry();
	parent.closeCreateWin();
}



