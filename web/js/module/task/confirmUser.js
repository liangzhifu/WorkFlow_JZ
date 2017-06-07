var oper = new Oper();
var PAGE_SIZE = 15;
var newConfirmOrderId = 0;
var taskInfoValue = null;
var userName = null;

var confirmUserStore = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'total',
	fields : [ "orderId", "taskTypeInfoId", "taskInfoValue", "confirmOrderId", "confirmUserId",
	           "confirmOrderStateCode","runCode","userId","userName"],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/taskConfirmOrder/queryPageTaskConfirmOrderUser.do"
	})

});
confirmUserStore.load({
	params : {
		start : 0,
		limit : PAGE_SIZE,
		taskInfoValue : taskInfoValue,
		userName : userName
	}
});
// 查询管理人员
var userStore3 = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userId", "userName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/tache/queryUserListJson.do?tacheIdIsNot=all"
	})

});
userStore3.load();
Ext.onReady(function() {
	var searchPanel = oper.initSearchPanel()
	var confirmPanel = oper.initConfirmPanel();
	var userPanel3 = oper.initUserPanel3();
	var viewport = new Ext.Viewport({
		el : 'mainDiv',
		layout : 'border',
		items : [ searchPanel,confirmPanel ]
	});
});
function Oper() {
	
	this.initSearchPanel = function() {
		var searchPanel = new Ext.FormPanel({
			region : 'north',
			labelAlign : 'right',
			labelWidth : 70,
			frame : true,
			// title : '查询条件',
			bodyStyle : {
				padding : '0px 0px 0px 0px',
				margins : '0px 0px 0px 0px'
			},
			height : 100,
			width : Ext.getBody().getSize().width,
			layout : 'column',
			items : [ {
				layout : 'column',
				title : '查询条件',
				bodyStyle : {
					padding : '1px 0px 0px 0px',
					margins : '1px 0px 0px 0px'
				},
				width : Ext.getBody().getSize().width,
				items : [ {
					columnWidth : .20,
					layout : 'form',
					items : [ {
						xtype : 'textfield',
						fieldLabel : '编码',
						name : 'taskInfoValue',
						id : 'taskInfoValue',
						anchor : '95%'
					} ]
				}, {
					columnWidth : .20,
					layout : 'form',
					items : [ {
						xtype : 'textfield',
						fieldLabel : '确认人姓名',
						name : 'userName',
						id : 'userName',
						anchor : '95%'
					} ]
				}, {
					columnWidth : 0.5,
					layout : 'form',
					items : [ {
						layout : 'column',
						items : [ {
							xtype : 'label',
							columnWidth : 0.3,
							html : '&nbsp;&nbsp;',
							text : '',
							anchor : '95%'
						}, {
							xtype : 'button',
							text : '查询',
							columnWidth : 0.05,
							icon : '/WorkFlow/images/icon/icon07.gif',
							listeners : {
								"click" : doQry
							}
						}, {
							xtype : 'label',
							columnWidth : 0.05,
							html : '&nbsp;&nbsp;',
							text : '',
							anchor : '95%'
						}, {
							xtype : 'button',
							text : '重置',
							columnWidth : 0.05,
							icon : '/WorkFlow/images/icon/icon07.gif',
							listeners : {
								"click" : deReset
							}
						}, {
							xtype : 'label',
							columnWidth : 0.05,
							html : '&nbsp;&nbsp;',
							text : '',
							anchor : '95%'
						}]
					} ]
				} ]
			} ]
		});
		return searchPanel;
	}	
	
	this.initConfirmPanel = function() {
		
		var toolbar = new Ext.Toolbar([ '工单确认人列表', {
			xtype : 'tbfill'
		}, {
			text : '修改确认人',
			id : 'editconfirmUser',
			onClick : function() {
				editConfirmUser();
			}
		}, {
			text : '&nbsp;&nbsp',
			id : 'test',
			onClick : function() {
				
			}
		} ]);
		
		 var pagebar = new Ext.PagingToolbar({
			 id: "pagingToolbar",
             pageSize: PAGE_SIZE,
             store: confirmUserStore,
             displayInfo: true,
             displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
             emptyMsg: "没有记录"
         });
		
		var sm = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				rowselect : function(sm, row, rec) {
// doQry();
				}
			}
		});
		var confirmUserPanel = new Ext.grid.GridPanel({
			region : 'center',
			id : 'confirmUserDataGrid',
			labelAlign : 'right',
			labelWidth : 30,
			frame : true,
			height : Ext.getBody().getSize().height * 0.85,
// width : Ext.getBody().getSize().width * 0.35,
			columns : [ sm, {
				header : '工单id',
				width : 60,
				hidden : true,				
				dataIndex : 'orderId'
			}, {
				header : '任务单id',
				width : 90,
				hidden : true,
				dataIndex : 'taskTypeInfoId'
			}, {
				header : '编号',
				width : Ext.getBody().getSize().width * 0.25,
				hidden : false,
				dataIndex : 'taskInfoValue'
			}, {
				header : '确认单id',
				width : 90,
				hidden : true,
				dataIndex : 'confirmOrderId'
			}, {
				header : '确认人id',
				width : 90,
				hidden : true,
				dataIndex : 'confirmUserId'
			}, {
				header : '确认人姓名',
				width : Ext.getBody().getSize().width * 0.25,
				hidden : false,
				dataIndex : 'userName'
			}, {
				header : '确认单状态',
				width : Ext.getBody().getSize().width * 0.25,
				hidden : false,
				dataIndex : 'confirmOrderStateCode'
			}, {
				header : '确认类型',
				width : Ext.getBody().getSize().width * 0.25,
				hidden : false,
				dataIndex : 'runCode',
				renderer:function(value){
					if(value=="accept_confirm"){
						return "接受人";
					}else if(value=="group_confirm"){
						return "确认人";
					}else if(value=="quality_confirm"){
						return "承认人";
					}else if(value=="accept2_confirm"){
						return "变化点管理接受人";
					}else if(value=="confirm_confirm"){
						return "变化点管理确认人";
					}else if(value=="quality2_confirm"){
						return "品质部长承认人";
					}else {
						return "";
					}
				}
			} ],
			autoScroll : true,
			border : false,
			sortable : false,
			store : confirmUserStore,
			sm : sm,
			tbar : toolbar,
			bbar: pagebar

		});
		/*
		 * 	extjs4 的写法
		 */
//		confirmUserStore.on('beforeload', function() {
//			 Ext.apply(confirmUserStore.proxy.extraParams, {
//				 start : 0,
//				 limit : PAGE_SIZE,
//				 taskInfoValue:Ext.getCmp('taskInfoValue').getValue(),
//				 userName:Ext.getCmp('userName').getValue()
//			});
//		});
		/*
		 * 	extjs3 的写法
		 */
		confirmUserStore.on('beforeload', function() {
			confirmUserStore.baseParams ={
				taskInfoValue:Ext.getCmp('taskInfoValue').getValue(),
				userName:Ext.getCmp('userName').getValue()	
			};
		});
//		Ext.getCmp('pagingToolbar') .on("beforechange", function(){
//			Ext.apply(confirmUserStore.proxy.extraParams, {
//				 taskInfoValue:Ext.getCmp('taskInfoValue').getValue(),
//				 userName:Ext.getCmp('userName').getValue()
//			});
//		}, this);
		
//		confirmUserStore.load({
//			params : {
//				start : 0,
//				limit : PAGE_SIZE,
//				taskInfoValue : Ext.getCmp('taskInfoValue').getValue(),
//			    userName : Ext.getCmp('userName').getValue()
//			}
//		});
		return confirmUserPanel;
	}

	this.initUserPanel3 = function() {
		var sm3 = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true
		});
		var userPanel3 = new Ext.grid.GridPanel({
			region : "center",
			id : "mainDataGrid3",
			height : 330,
			columns : [ sm3, {
				header : '人员id',
				hidden : false,
				dataIndex : 'userId'
			}, {
				header : '人员名字',
				hidden : false,
				dataIndex : 'userName'
			} ],
			autoScroll : true,
			store : userStore3,
			sm : sm3
		});
		return userPanel3;
	}
}
function doQry() {
	 taskInfoValue = Ext.getCmp('taskInfoValue').getValue();
	 userName = Ext.getCmp('userName').getValue();
Ext.getCmp("confirmUserDataGrid").getStore().reload({
	params : {
		start : 0,
		limit : PAGE_SIZE,
		taskInfoValue : taskInfoValue,
		userName : userName
	}
});
//	confirmUserStore.load({
//			params : {
//				start : 0,
//				limit : PAGE_SIZE,
//				taskInfoValue : taskInfoValue,
//				userName : userName
//			}
//	});
}
function deReset(){
	Ext.getCmp('taskInfoValue').setValue("");
	Ext.getCmp('userName').setValue("");

}
function editConfirmUser() {
	var selinfo = Ext.getCmp('confirmUserDataGrid').getSelectionModel()
			.getSelections();
	var confirmOrderStateCode = null;
	var sendConfirmUserId = null;
	if (selinfo.length > 0) {
		confirmOrderStateCode = selinfo[0].data.confirmOrderStateCode;
		if(!(confirmOrderStateCode==("处理中")||confirmOrderStateCode==("初始化"))){
			Ext.Msg.alert("提示", "只能修改状态为处理中或初始化的数据");
			return;
		}else{
			newConfirmOrderId = (selinfo[0].data.confirmOrderId);
		}	
	} else {
		Ext.Msg.alert("提示", "请至少选择一条数据");
		return;
	}
	userStore3.load({
		params : {
			tacheId : null,
			tacheIdIsNot : 'all'
		}
	});
	var userPanel3 = oper.initUserPanel3();
	
	var formWin2 = new Ext.Window(
			{
				id : 'menuFormWindow2',
				title : '修改确认人',
				width : 300,
				height : 400,
				// closeAction : 'close',//释放窗体内存
				bodyStyle : {
					padding : '5px 0px 0px 0px',
					margins : '5px 0px 0px 0px'
				},
				items : [ userPanel3 ],
				buttonAlign : 'center',
				buttons : [
						{
							text : '保存',
							icon : '/IOMPROJ/public/image/确认.png',
							onClick : function() {
								var selinfoUser = Ext.getCmp('mainDataGrid3')
										.getSelectionModel().getSelections();
								if (selinfoUser.length <= 0) {
									if(!(confirmOrderStateCode==("初始化"))){
										Ext.Msg.alert("提示", "只能将状态为初始化的数据的确认人设置为空");
										return;
									}
//									Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
//									return;
								}else{
									sendConfirmUserId = selinfoUser[0].data.userId;
								}
								Ext.Ajax
										.request({
											url : contextPath
													+ "/taskConfirmOrder/editTaskConfirmOrder.do",
											async : true,
											params : {
												confirmUserId : sendConfirmUserId,
												confirmOrderId : newConfirmOrderId
											},
											success : function(response, action) {
												Ext.Msg
														.alert(
																'成功',
																Ext
																		.decode(response.responseText).message);
												doQry();
												doCloseWin2();

											},
											failure : function(response, action) {
												Ext.Msg
														.alert(
																'错误',
																Ext
																		.decode(response.responseText).message);
												doQry();
												doCloseWin2();
											}
										});

							}
						}, {
							text : '关闭',
							icon : '/IOMPROJ/public/image/确认.png',
							onClick : function() {
								Ext.getCmp('menuFormWindow2').close();
							}
						} ]

			});
	formWin2.show();
}
function doCloseWin2() {
	var closeWMenu2 = Ext.getCmp('menuFormWindow2');
	if (closeWMenu2 != null) {
		closeWMenu2.close();
	}
};