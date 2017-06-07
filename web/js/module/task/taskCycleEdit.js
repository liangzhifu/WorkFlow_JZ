//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	Ext.QuickTips.init();
	
	var mainPanel = taskCycleEdit.initMainPanel();
	var buttonPanel = taskCycleEdit.initButtonPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[mainPanel, buttonPanel]
    });
});

//页面配置对象
var taskCycleEdit = (function() {

	return {
		initMainPanel : function(){
			var taskNoticeCycle  = Ext.decode(taskNoticeCycleJSON);
	        var mainPanel = new Ext.FormPanel({
	        	id:'mainForm',
	        	region:'center',
	            labelAlign: 'right',
	            labelWidth :160,
	            frame:true,      
	            bodyStyle:{padding:'0px 0px 0px 0px',margins:'0px 0px 0px 0px'},
	            width:Ext.getBody().getSize().width-2,
	            layout:'form',
	            items: [{   
		            	xtype:'textfield',
                        fieldLabel:'邮件通知周期（小时）',
                        name: 'notice_cycle',
                        id: 'notice_cycle',
                        regexText:"请输入正确的整数",
                        regex:/^[-+]?[\d]+$/,
                        value:taskNoticeCycle.noticeCycle
		            },{   
		            	xtype:'textfield',
                        fieldLabel:'变更单使用通知时限（分钟）',
                        name: 'alarm_time',
                        id: 'alarm_time',
                        regexText:"请输入正确的整数",
                        regex:/^[-+]?[\d]+$/,
                        value:taskNoticeCycle.alarmTime
		            }]
	        });
	        return mainPanel;
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
						columnWidth:0.3,
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
						columnWidth:0.1,
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

function doSubmit(){
	if (!Ext.getCmp('mainForm').getForm().isValid()) {
		return;
	}
	Ext.getCmp('mainForm').getForm().submit({
		url : contextPath+'/taskDetail/taskCycleEdit.do?orderId='+orderId+'&cycleId='+cycleId,
 		waitTitle : '提示', 
 		waitMsg: '请稍后,正在提交数据...',
        method : 'post', 
        // 如果有表单以外的其它参数，可以加在这里。我这里暂时为空，也可以将下面这句省略      
        baseParams : {},   
        // 第一个参数是传入该表单，第二个是Ext.form.Action对象用来取得服务器端传过来的json数据      
        success : function(form, action) {  
        	window.returnValue = true;
        	alert(action.result.message);
        	window.open('','_self','');
        	window.close();
        },      
        failure : function(form, action) {  
             Ext.Msg.alert('错误', action.result.message);      
        }      
 	});
}

function doClose(){
	window.returnValue = false;
	window.open('','_self','');
	window.close();
}