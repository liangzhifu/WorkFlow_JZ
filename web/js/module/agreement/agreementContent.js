//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = agreementContent.initBasicPanel(); 
	var mainPanel = agreementContent.initMainPanel();
	var buttonPanel = agreementContent.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var agreementContent = (function() {
	 
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
	    	var agreementContentArray = agreement.agreementContentList;
	    	for(var i = 0; i < agreementContentArray.length; i++){
				var data = generMainObj(orderId, agreementContentArray[i]);
				dataArray[i] = data;
			}
	    	var dataStore = new Ext.data.ArrayStore({
	    		fields : [ "orderId", "agreementId", "id", "seq", "problem", "improve", "responsible", "term", "state", "confirm", "refuseReason" ],
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
			}, {
				header : "拒绝原因",
				dataIndex : "refuseReason",
				width : Ext.getBody().getSize().width * 0.1
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
	                                 },
	                                 
 		initButtonPanel : function(){
	        var buttonPanel = new Ext.FormPanel({
	        	region:'south',
	            labelAlign: 'right',
	            labelWidth :70,
	            frame:true,       
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            height:30,
	            width:Ext.getBody().getSize().width-2,
	            layout:'form',
	            items: [{               
	            	layout:'column',
	            	items:[{
						xtype:'label',
						columnWidth:0.4,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'确定',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/submit.png',
                         listeners:{
                        	 "click": doSubmit
                         }
	            	},{
						xtype:'label',
						columnWidth:0.05,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'取消',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/close.png',
                         listeners:{
                               "click": doClose                        
                         }
	            	}]
	            }]
	        });
	        return buttonPanel;
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
	var obj = new Array();;
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
	var imporveText = "";
	if(agreementContent.improve == undefined){
		
	}else {
		imporveText = agreementContent.improve.replace(/<\/br>/g, "\r\n");
	}
	obj[5] = '<textarea style="height:40px;width:'+(Ext.getBody().getSize().width * 0.35-20)+'px;" id="data_improve_'+agreementContent.id+'">'+imporveText+'</textarea>';
	obj[6] = agreementContent.responsibleName;
	if(agreementContent.termStr == undefined || agreementContent.termStr == ""){
		obj[7] = '<input id="data_term_'+agreementContent.id+'" name="data_term_'+agreementContent.id+'" class="laydate-icon" onclick="laydate({istime: false, min: laydate.now(), isclear: false, istoday: false, issure: false, format: \'YYYY-MM-DD\'})">';
	}else {
		obj[7] = '<input id="data_term_'+agreementContent.id+'" name="data_term_'+agreementContent.id+'" class="laydate-icon" onclick="laydate({istime: false, min: laydate.now(), isclear: false, istoday: false, issure: false, format: \'YYYY-MM-DD\'})" value="'+agreementContent.termStr+'">';
	}	
	obj[8] = agreementContent.state;
	var confirmId = null;
	if(agreementContent.confirm == undefined || agreementContent.confirm == null){
		confirmId = createUserId;
	}else {
		confirmId = agreementContent.confirm;
	}
	var str = "<select id='data_confirm_" + agreementContent.id + "'>";
	var userList = Ext.decode(userListJson);
    for (var i = 0; i < userList.length; i++) {
    	if(confirmId == userList[i].userId){
    		str += "<option value='" + userList[i].userId + "' selected=true>" + userList[i].userName + "</option>";
    	}else {
    		str += "<option value='" + userList[i].userId + "'>" + userList[i].userName + "</option>";
    	}
    }
	str += "</select>";
	obj[9] = str;
	obj[10] = agreementContent.refuseReason;
	return obj;
}

function doSubmit(){
	var idArray = new Array();
	var improveArray = new Array();
	var termArray = new Array();
	var confirmArray = new Array();
	var i = 0;
	Ext.getCmp('mainDataGrid').getStore().each(function(record) {
		var id = record.data.id;
		idArray[i] = id;
		improveArray[i] = document.getElementById("data_improve_"+record.data.id).value;
		confirmArray[i] = document.getElementById("data_confirm_"+record.data.id).value;
		termArray[i] = document.getElementById("data_term_"+record.data.id).value;
		i ++;
	});
	
	Ext.Ajax.request( {
		url : contextPath+'/agreement/updateAgreementContent.do',
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			orderId : orderId,
			agreementId : agreementId,
			idJson : Ext.encode(idArray),
			termJson : Ext.encode(termArray),
			confirmJson : Ext.encode(confirmArray),
			improveJson : Ext.encode(improveArray)
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			if(responseText.success){
				alert("填写信息成功！");
				parent.doQry();
				parent.closeCreateWin();
			}else {
				alert(responseText.message);
			}
		},
		failure : function(a) {
			Ext.Msg.alert('提示', '填写信息失败！');
		}
	});

}

function doClose(){
	parent.closeCreateWin();
}
