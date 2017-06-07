var ids = new Array();
var idIndex = 0;

//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var basicPanel = agreementConfirm.initBasicPanel(); 
	var mainPanel = agreementConfirm.initMainPanel();
	var buttonPanel = agreementConfirm.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[basicPanel,mainPanel,buttonPanel]
    });
});

//页面配置对象
var agreementConfirm = (function() {
	 
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
	    		fields : [ "orderId", "agreementId", "id", "seq", "problem", "improve", "responsible", "term", "state", "confirm", "action" ],
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
				header : "操作",
				dataIndex : "action",
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
                         text:'接受',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/submit.png',
                         listeners:{
                        	 "click":doAcceptAll                        
                         }
	            	},{
						xtype:'label',
						columnWidth:0.05,
						html:'&nbsp;&nbsp;'
					},{
	            		 xtype: 'button',                       
                         text:'拒绝',
                         columnWidth:0.05,
                         icon: '/WorkFlow/images/refuse.gif',
                         listeners:{
                        	 "click":doRefuseAll                        
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
	var improve = "";
	if(agreementContent.improve == undefined){
		
	}else {
		improve = agreementContent.improve.replace(/<\/br>/g, "<br>");
	}
	obj[5] = improve;
	obj[6] = agreementContent.responsibleName;
	obj[7] = agreementContent.termStr;
	var str = "<select id='data_state_" + agreementContent.id + "'>";
	if(agreementContent.state == "NG"){
		str += "<option value='NG' checked=true>NG</option>";
		str += "<option value='OK'>OK</option>";
	}else {
		str += "<option value='NG'>NG</option>";
		str += "<option value='OK' checked=true>OK</option>";
	}
	str += "</select>";
	obj[8] = str;
	obj[9] = agreementContent.confirmName;
	obj[10] = '<span id="span_input_hidden_'+agreementContent.id+'"><input type="hidden" value="'+agreementContent.id+'" class="content_input_hidden"><input type="button" value="拒绝" onclick="doRefuse('+agreementContent.id+')"/>&nbsp;&nbsp;<input type="button" value="接受" onclick="doAccept('+agreementContent.id+')"/></span>';;
	ids[idIndex] = agreementContent.id;
	idIndex ++;
	return obj;
}

function doAccept(id){
	Ext.Ajax.request( {
		url : contextPath+'/agreement/acceptAgreementContent.do',
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			orderId : orderId,
			agreementId : agreementId,
			id : id,
			state : document.getElementById("data_state_"+id).value
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			alert(responseText.message);
			document.getElementById("span_input_hidden_"+id).innerHTML='';
			var tempId = 0;
			for(var i = 0; i < idIndex; i++){
				if(id == ids[i]){
					tempId = i;
				}
			}
			ids.splice(tempId, 1);
			idIndex --;
		},
		failure : function(a) {
			Ext.Msg.alert('提示', '接受失败！');
		}
	});
}

function doRefuse(id){
	var refuseReason = "";
	Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
		if("cancel"==bu) return;
		refuseReason= txt; 
		if(refuseReason == ""){
			alert("拒绝原因必须填写！");
			return ;
		}
		Ext.Ajax.request( {
			url : contextPath+'/agreement/refuseAgreementContent.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				orderId : orderId,
				agreementId : agreementId,
				id : id,
				refuseReason: refuseReason
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				alert(responseText.message);
				document.getElementById("span_input_hidden_"+id).innerHTML='';
				var tempId = 0;
				for(var i = 0; i < idIndex; i++){
					if(id == ids[i]){
						tempId = i;
					}
				}
				ids.splice(tempId, 1);
				idIndex --;
			},
			failure : function(a) {
				Ext.Msg.alert('提示', '拒绝失败！');
			}
		});
	},this,50)
}

function doAcceptAll(){
	if(idIndex==0){
		parent.doQry();
		parent.closeCreateWin();
	}else {
		var idsStr = "";
		var stateStr = "";
		for(var i = 0; i < idIndex; i++){
			idsStr += "," + ids[i];
			stateStr += "," + document.getElementById("data_state_"+ids[i]).value;
		}
		idsStr = idsStr.substring(1);
		stateStr = stateStr.substring(1);
		Ext.Ajax.request( {
			url : contextPath+'/agreement/acceptAgreementContents.do',
			waitTitle : '提示', 
	 		waitMsg: '请稍后,正在提交数据...',
			params : {
				orderId : orderId,
				agreementId : agreementId,
				idsStr : idsStr,
				stateStr : stateStr
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				alert(responseText.message);
				parent.doQry();
				parent.closeCreateWin();
			},
			failure : function(a) {
				Ext.Msg.alert('提示', '接受所有失败！');
			}
		});
	}
}

function doRefuseAll(){
	if(idIndex==0){
		parent.doQry();
		parent.closeCreateWin();
	}else {
		var idsStr = "";
		for(var i = 0; i < idIndex; i++){
			idsStr += "," + ids[i];
		}
		idsStr = idsStr.substring(1);
		var refuseReason = "";
		Ext.MessageBox.prompt("输入框","请输入拒绝原因：",function(bu,txt){
			if("cancel"==bu) return;
			refuseReason= txt; 
			if(refuseReason == ""){
				alert("拒绝原因必须填写！");
				return ;
			}
			Ext.Ajax.request( {
				url : contextPath+'/agreement/refuseAgreementContents.do',
				waitTitle : '提示', 
		 		waitMsg: '请稍后,正在提交数据...',
				params : {
					orderId : orderId,
					agreementId : agreementId,
					idsStr : idsStr,
					refuseReason: refuseReason
				},
				success : function(response, action) {
					var responseText = Ext.decode(response.responseText);
					alert(responseText.message);
					parent.doQry();
					parent.closeCreateWin();
				},
				failure : function(a) {
					Ext.Msg.alert('提示', '拒绝所有失败！');
				}
			});
		},this,50)
	}
}

function doClose(){
	parent.closeCreateWin();
}
