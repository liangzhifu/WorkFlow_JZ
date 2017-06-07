var agrementProjectStore = new Ext.data.ArrayStore({  
    fields: ['agrementProjectCode', 'agrementProjectName'],  
    data: [  
        ['AB', 'AB'], 
        ['SB', 'SB'],
        ['SW', 'SW']  
    ]  
}); 

var agreementResultStore = new Ext.data.ArrayStore({  
    fields: ['resultCode', 'resultName'],  
    data: [  
        ['NG', 'NG'], 
        ['OK', 'OK']
    ]  
});

var trackStore = new Ext.data.JsonStore({
    remoteSort: true,
    fields: ['userId', 'userName'],
    proxy: new Ext.data.HttpProxy({
        url: contextPath + "/agreement/getTrackUserList.do"
    })
});
trackStore.load();

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
    	            	xtype:'combo',
    	                fieldLabel:'工程',
    	                name: 'agreement_project_code',
    	                id: 'agreement_project_code',
    	                valueField: 'agrementProjectCode',
    	                displayField: 'agrementProjectName', 
    	                triggerAction:'all',     
    	                store: agrementProjectStore,
    	                allowBlank : true,
    					mode : 'local',
    					value : agreement.project,
    	                anchor:'95%',
    	                emptyText:'-----请选择-----'
    	            }] 
    	    	},{ 
    	            columnWidth:.25,
    	            layout:'form',
    	            items:[{   
    	            	xtype:'textfield',
                        fieldLabel:'切换LOT',
                        name: 'agreement_cut_lot',
                        id: 'agreement_cut_lot',  
                        value:agreement.cutLOT,
                        anchor:'95%',
                        emptyText:'-----请输入-----'
    	            }] 
            	},{ 
    	            columnWidth:.25,
    	            layout:'form',
    	            items:[{   
    	            	xtype:'textfield',
                        fieldLabel:'数量',
                        name: 'agreement_num',
                        id: 'agreement_num',  
                        value:agreement.num,
                        anchor:'95%',
                        emptyText:'-----请输入-----'
    	            }] 
            	},{ 
    	            columnWidth:.25,
    	            layout:'form',
    	            items:[{                 
    	            	xtype:'combo',
    	                fieldLabel:'结论',
    	                name: 'agreement_project_result',
    	                id: 'agreement_project_result',
    	                valueField: 'resultCode',
    	                displayField: 'resultName', 
    	                triggerAction:'all',     
    	                store: agreementResultStore,
    	                allowBlank : true,
    					mode : 'local',
    	                anchor:'95%',
    	                value: agreement.conclusionState,
    	                emptyText:'-----请选择-----'
    	            }] 
    	    	},{ 
    	            columnWidth:.25,
    	            layout:'form',
    	            items:[{   
    	            	xtype:'textfield',
    	                fieldLabel:'详细说明',
    	                name: 'agreement_conclusion_message',
    	                id: 'agreement_conclusion_message',         
    	                anchor:'95%',
    	                value: agreement.conclusionMessage,
    	                emptyText:'-----请输入-----'
    	            }] 
    	    	},{ 
    	            columnWidth:.25,
    	            layout:'form',
    	            items:[{               
    	            	xtype:'combo',
                        fieldLabel:'追踪者',
                        name: 'agreement_track_name',
                        id: 'agreement_track_id',
                        valueField: 'userId',
                        displayField: 'userName', 
                        triggerAction:'all',     
                        hideTrigger:false,
                        allowBlank:true,
                        editable: false, 
                        store: trackStore,
                        value: agreement.trackName,
                        anchor:'95%',
                        emptyText:'-----请选择-----',
                        showSelectAll:true
    	            }] 
            	}];
			var agreementTacheArray = agreement.agreementTacheList;
			for(var i = (0 + 10); i < (agreementTacheArray.length + 10); i ++){
				items[i] =  generBasicObj(agreementTacheArray[i - 10].tacheName, agreementTacheArray[i - 10].userName);
			}
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
	    		fields : [ "operation", "orderId", "agreementId", "id", "seq", "problem", "improve", "responsible", "term", "state", "confirm" ],
	    		data : dataArray
	    	});
			var cm = new Ext.grid.ColumnModel([ {
				header : "操作",
				dataIndex : "operation",
				width : Ext.getBody().getSize().width * 0.05
			},{
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
	var obj = new Array();
	obj[0] = '<input type="button" value="-" onclick="delRow('+agreementContent.seq+');">',
	obj[1] = orderId;
	obj[2] = agreementContent.agreementId;
	obj[3] = agreementContent.id;
	obj[4] = agreementContent.seq;
	var problemText = "";
	if(agreementContent.problem == undefined){
		
	}else {
		problemText = agreementContent.problem.replace(/<\/br>/g, "\r\n");
	}
	obj[5] = '<textarea style="height:40px;width:'+(Ext.getBody().getSize().width * 0.35-20)+'px;" id="data_problem_'+agreementContent.id+'">'+problemText+'</textarea>';
	var imporveText = "";
	if(agreementContent.improve == undefined){
		
	}else {
		imporveText = agreementContent.improve.replace(/<\/br>/g, "<br>");
	}
	obj[6] = imporveText;
	var responsible = "<select id='data_responsible_" + agreementContent.id + "'>";
	var userList = Ext.decode(userListJson);
    for (var i = 0; i < userList.length; i++) {
    	if(agreementContent.responsible == userList[i].userId){
    		responsible += "<option value='" + userList[i].userId + "' selected=true>" + userList[i].userName + "</option>";
    	}else {
    		responsible += "<option value='" + userList[i].userId + "'>" + userList[i].userName + "</option>";
    	}
    }
    responsible += "</select>";
	obj[7] = responsible;
	obj[8] = agreementContent.termStr;
	var state = "<select id='data_state_" + agreementContent.id + "'>";
	if(agreementContent.state == undefined || agreementContent.state == ""){
		state += "<option value=''></option>";
		state += "<option value='NG'>NG</option>";
		state += "<option value='OK'>OK</option>";
	}else {
		if(agreementContent.state == "NG"){
			state += "<option value='NG' checked=true>NG</option>";
			state += "<option value='OK'>OK</option>";
		}else {
			state += "<option value='NG'>NG</option>";
			state += "<option value='OK' checked=true>OK</option>";
		}
	}
	state += "</select>";
	obj[9] = state;
	var confirm = "";
	if(agreementContent.confirmName == undefined || agreementContent.confirmName == ""){
		confirm = "<select id='data_confirm_" + agreementContent.id + "'>";
		confirm += "<option value='0'></option>";
		for (var i = 0; i < userList.length; i++) {
	    	confirm += "<option value='" + userList[i].userId + "'>" + userList[i].userName + "</option>";
	    }
	    confirm += "</select>";
	}else {
		confirm = "<select id='data_confirm_" + agreementContent.id + "'>";
		for (var i = 0; i < userList.length; i++) {
	    	if(agreementContent.confirm == userList[i].userId){
	    		confirm += "<option value='" + userList[i].userId + "' selected=true>" + userList[i].userName + "</option>";
	    	}else {
	    		confirm += "<option value='" + userList[i].userId + "'>" + userList[i].userName + "</option>";
	    	}
	    }
	    confirm += "</select>";
	}
	obj[10] = confirm;
	return obj;
}

function delRow(seq){
	Ext.getCmp('mainDataGrid').getStore().each(function(record) {
		if(record.data.seq == seq){
			Ext.getCmp('mainDataGrid').getStore().remove(record);
		}                                   
	});
}

function doSubmit(){
	var agreement_project_code = Ext.getCmp('agreement_project_code').getRawValue();
	if(agreement_project_code == null || agreement_project_code == "" || agreement_project_code == "undefined"){
		Ext.MessageBox.alert("提示", "请选择工程！");
		return;
	}
	var agreement_cut_lot = Ext.getCmp('agreement_cut_lot').getValue();
	if(agreement_cut_lot == null || agreement_cut_lot == "" || agreement_cut_lot == "undefined"){
		Ext.MessageBox.alert("提示", "切换LOT不能为空！");
		return;
	}
	var agreement_project_result = Ext.getCmp('agreement_project_result').getRawValue();
	if(agreement_project_result == null || agreement_project_result == "" || agreement_project_result == "undefined"){
		Ext.MessageBox.alert("提示", "请选择结论！");
		return;
	}else {
		if(agreement_project_result == "NG"){
			var agreement_conclusion_message = Ext.getCmp('agreement_conclusion_message').getValue();
			if(agreement_conclusion_message == null || agreement_conclusion_message == "" || agreement_conclusion_message == "undefined"){
				Ext.MessageBox.alert("提示", "NG原因不能为空！");
				return;
			}
		}
	}
	var idArray = new Array();
	var problemArray = new Array();
	var responsibleArray = new Array();
	var stateArray = new Array();
	var confirmArray = new Array();
	var i = 0;
	Ext.getCmp('mainDataGrid').getStore().each(function(record) {
		var id = record.data.id;
		idArray[i] = id;
		problemArray[i] = document.getElementById("data_problem_"+record.data.id).value;
		responsibleArray[i] = document.getElementById("data_responsible_"+record.data.id).value;
		stateArray[i] = document.getElementById("data_state_"+record.data.id).value;
		confirmArray[i] = document.getElementById("data_confirm_"+record.data.id).value;
		i ++;
	});
	
	Ext.Ajax.request( {
		url : contextPath+'/agreement/eidtAgreement2.do',
		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
		params : {
			orderId : orderId,
			agreementId : agreementId,
			agreement_cut_lot : agreement_cut_lot,
			agreement_project_code : agreement_project_code,
			agreement_num : Ext.getCmp('agreement_num').getValue(),
			agreement_project_result : agreement_project_result,
			agreement_conclusion_message : Ext.getCmp('agreement_conclusion_message').getValue(),
			idJson : Ext.encode(idArray),
			problemJson : Ext.encode(problemArray),
			responsibleJson : Ext.encode(responsibleArray),
			stateJson : Ext.encode(stateArray),
			confirmJson : Ext.encode(confirmArray),
			agreement_track_id : Ext.getCmp('agreement_track_id').getValue()
		},
		success : function(response, action) {
			var responseText = Ext.decode(response.responseText);
			if(responseText.success){
				alert("修改信息成功！");
				window.close();
			}else {
				alert(responseText.message);
			}
		},
		failure : function(a) {
			Ext.Msg.alert('提示', '修改信息失败！');
		}
	});

}

function doClose(){
	window.close();
}
