var kpiConfig = function(){
	var session = GetSession();
	return {
		init:function(){
			var viewport = new Ext.Viewport({
				layout: 'border',
				items: [{
					region: 'center', 
					id:"contentPanel",
					xtype: 'panel', 
					split: false,
					margins: '5 5 5 0',
					layout: 'fit',
					items:[kpiConfig.initPage()]
				}]
			});
			kpiConfig.loadPage();
		},
		initPage:function(){
			var formPanel = new Ext.Panel({
				labelAlgin:'right',
				labelWidth:80,
				border:false,
				frame:false,
				layout:'column',
				items:[{
					title: '系统Runtime信息设置',
					collapsible: false,
					border:false,
					id:'paramConfig',
					layout:'column',
					html:'<div id="paramConfig"></div>'
				},{
					title: '执行结果',
					collapsible: false,
					id:'configResult',
					border:false,
					layout:'fit',
					html:'<div id="configResult"></div>'
				}]
			});
			return formPanel;
		},
		loadPage:function(){
			var form = new Ext.FormPanel({
				id: 'paramConfigForm',
				autoScroll:true,
				autoHeight:true,
				border:false,
				frame : false,
				labelWidth:100,
				labelAlign:'right',
				buttonAlign : "center",
				layout:'form',
				items:[{
					xtype: 'button',
					text: '刷新uos_config数据',
					listeners:{
						"click":function(){
							kpiConfig.initUosConfig();
						}
					}
				},{
					fieldLabel: 'UosConfigName',
					items: [{
						xtype: 'textfield',
						name: 'uosConfigName',
						id: 'uosConfigName',
						width:200,
						anchor:'95%'
					}]
				},{
					xtype: 'button',
					text: '获取uos_config的value信息',
					listeners:{
						"click":function(){
							kpiConfig.getUosConfigValue();
						}
					}
				},{
					fieldLabel: 'UosConfigValue',
					items: [{
						xtype: 'textfield',
						name: 'uosConfigValue',
						id: 'uosConfigValue',
						width:200,
						anchor:'95%',
						readOnly: true
					}]
				}]
			});
			form.render("paramConfig");

			var resultForm = new Ext.FormPanel({
				id: 'configResultForm',
				autoScroll:true,
				autoHeight:true,
				border:false,
				frame : false,
				labelWidth:100,
				labelAlign:'right',
				buttonAlign : "center",
				layout:'form',
				items:[{
					items: [{
						xtype: 'textarea',
						name: 'resultText',
						id: 'resultText',
						width:500,
							height:200,
						anchor:'95%'
					}]
				}]
			});
			resultForm.render("configResult");
			
			/*var resultForm = new Ext.FormPanel({
				id: 'configResultForm',
				html:'Hello,easyjf open source'
			});
			resultForm.render("configResult");*/
			pageMaskAll.show();
			Ext.defer(function(){
				pageMaskAll.hide();
			},10);
		},
		getUosConfigValue:function (){//获取uos_config的value信息.
			var sName = Ext.getCmp("uosConfigName").getValue();
			if (sName == ""){
			    alert("请填写UosConfigName值!");
				Ext.getCmp("uosConfigName").focus();
			    return ;
			}
			var s = callRemoteFunctionNoTrans("com.ztesoft.iom.web.StaticDataServlet", "getUosConfigValue",sName);

			Ext.getCmp("uosConfigValue").setValue(s);
		},
		initUosConfig:function(){//初始化uos_config表和报表定义信息.
			var s = callRemoteFunctionNoTrans("com.ztesoft.iom.web.StaticDataServlet", "initStaticData","");
			s += "\n" + callRemoteFunctionNoTrans("com.ztesoft.oss.reportconifg.bl.BandBuzManager", "initConfiggedMap","");
			
			Ext.getCmp("resultText").setValue(s);
	    }
	}
}();

Ext.EventManager.onWindowResize(function(){ 
});

