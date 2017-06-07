var oper = new Oper();
var PAGE_SIZE = 1000;
var tacheId = null;
var tacheIdIsNot = '';
var tacheUserStore = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "orgId", "tacheId", "orgName", "userId", "userName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/tache/queryUserTacheListJson.do?tacheIdIsNot="
				+ tacheIdIsNot
	})

});
tacheUserStore.load();
var userStore = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userId", "userName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/tache/queryUserListJson.do?tacheIdIsNot="
				+ tacheIdIsNot
	})

});
userStore.load();
var userStore2 = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userId", "userName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/tache/queryUserListJson.do?tacheIdIsNot=not"
	})

});
userStore2.load();
// 查询管理人员
var userStore3 = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userId", "userName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/tache/queryTacheManagerUserList.do"
	})

});
userStore3.load();

Ext.onReady(function() {
	var tachePanel = oper.initTachePanel();
	var userPanel = oper.initUserPanel();
	var userPanel2 = oper.initUserPanel2();
	var userPanel3 = oper.initUserPanel3();
	var viewport = new Ext.Viewport({
		el : 'mainDiv',
		layout : 'border',
		items : [ tachePanel, userPanel ]
	});
});
function Oper() {
	this.initTachePanel = function() {
		var toolbar = new Ext.Toolbar([ '权限组织列表', {
			xtype : 'tbfill'
		}, {
            text : '添加管理员',
            id : 'addManager',
            onClick : function() {
                addManager();
            }
        },{
            text : '删除管理员',
            id : 'deleteManager',
            onClick : function() {
                deleteManager();
            }
        },{
			text : '&nbsp;&nbsp',
			id : 'test',
			onClick : function() {
				doQry2();
			}
		} ]);
		var sm = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true,
			listeners : {
				rowselect : function(sm, row, rec) {
					doQry();
				}
			}
		});
		var tacheUserPanel = new Ext.grid.GridPanel({
			region : 'west',
			id : 'tacheUserDataGrid',
			labelAlign : 'right',
			labelWidth : 30,
			frame : true,
			height : Ext.getBody().getSize().height * 0.85,
			width : Ext.getBody().getSize().width * 0.35,
			columns : [ sm, {
				header : 'id',
				hidden : true,
				dataIndex : 'tacheId'
			}, {
				header : '权限组织名称',
				width : 200,
				hidden : false,
				dataIndex : 'orgName'
			}, {
				header : '管理员id',
				width : 200,
				hidden : true,
				dataIndex : 'userId'
			}, {
				header : '管理员名称',
				width : 200,
				hidden : false,
				dataIndex : 'userName'
			} ],
			autoScroll : true,
			border : false,
			sortable : false,
			store : tacheUserStore,
			sm : sm,
			tbar : toolbar

		});
		// var listGridStore = Ext.getCmp('tacheUserDataGrid').store;
		// listGridStore.on('beforeload', function(store) {
		// if (listGridStore.lastOptions != null) {
		// var param = {};
		// param.tacheId = tacheId;
		// listGridStore.baseParams = param;
		// }
		// Ext.getCmp("tacheUserDataGrid").getStore().reload();
		// });

		return tacheUserPanel;
	}

	this.initUserPanel = function() {
		var toolbar2 = new Ext.Toolbar([ '人员列表', {
			xtype : 'tbfill'
		}, {
			text : '增加员工',
			id : 'insertUser',
			onClick : function() {
				addUser();
			}
		}, {
			text : '删除人员',
			id : 'deleteUser',
			onClick : function() {
				delUser();
			}
		}, {
			text : '&nbsp;&nbsp'
		}  ]);
		var sm2 = new Ext.grid.CheckboxSelectionModel(
		// {singleSelect : false}
		);
		var userPanel = new Ext.grid.GridPanel({
			region : "center",
			id : "mainDataGrid",
			height : Ext.getBody().getSize().height * 0.85,
			width : Ext.getBody().getSize().width * 0.65,
			columns : [ sm2, {
				header : '人员id',
				hidden : false,
				dataIndex : 'userId'
			}, {
				header : '人员名字',
				hidden : false,
				dataIndex : 'userName'
			} ],
			autoScroll : true,
			border : false,
			sortable : false,
			store : userStore,
			sm : sm2,
			tbar : toolbar2
		});
		var listGridStore2 = Ext.getCmp('mainDataGrid').store;
		listGridStore2.on('beforeload', function(store) {
			if (listGridStore2.lastOptions != null) {
				var param = {};
				param.tacheId = tacheId;
				param.tacheIdIsNot = tacheIdIsNot;
				listGridStore2.baseParams = param;
			}
		});
		Ext.getCmp("mainDataGrid").getStore().reload();
		return userPanel;
	}
	this.initUserPanel2 = function() {
		var sm2 = new Ext.grid.CheckboxSelectionModel(
		// {singleSelect : false}
		);
		var userPanel2 = new Ext.grid.GridPanel({
			region : "center",
			id : "mainDataGrid2",
			height : 330,
			columns : [ sm2, {
				header : '人员id',
				hidden : false,
				dataIndex : 'userId'
			}, {
				header : '人员名字',
				hidden : false,
				dataIndex : 'userName'
			} ],
			autoScroll : true,

			store : userStore2,
			sm : sm2
		});
		return userPanel2;
	}
	this.initUserPanel3 = function() {
		var sm3 = new Ext.grid.CheckboxSelectionModel({
			singleSelect : false
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
	var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
			.getSelections();
	tacheId = (selinfo[0].data.tacheId);
	Ext.getCmp("mainDataGrid").getStore().reload();
}
function doQry2() {
	Ext.getCmp("tacheUserDataGrid").getStore().reload();
	// var b = Ext.getCmp("tacheUserDataGrid").store;
	// b.load();
}

function addManager() {
    var newTacheId = 0;
    var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
        .getSelections();
    if (selinfo.length > 0) {
        newTacheId = (selinfo[0].data.tacheId);
    } else {
        Ext.Msg.alert("提示", "请至少选择一条权限组织数据");
        return;
    }
    userStore3.load({
        params : {
            tacheId : tacheId,
            managerFlag : '1'
        },
		callback: function(records, options, success){
            var userPanel3 = oper.initUserPanel3();
            var formWin2 = new Ext.Window(
                {
                    id : 'menuFormWindow2',
                    title : '添加管理员',
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
                                    Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
                                    return;
                                }
                                var userIdStr = "";
                                for (var i = 0; i < selinfoUser.length; i++){
                                    userIdStr += "," + selinfoUser[i].data.userId;
                                }
                                userIdStr = userIdStr.substring(1);
                                Ext.Ajax
                                    .request({
                                        url : contextPath
                                        + "/tache/addManager.do",
                                        async : true,
                                        params : {
                                            userIdStr : userIdStr,
                                            tacheId : newTacheId
                                        },
                                        success : function(response, action) {
                                            Ext.Msg
                                                .alert(
                                                    '成功',
                                                    Ext
                                                        .decode(response.responseText).message);
                                            doQry2();
                                            doCloseWin2();

                                        },
                                        failure : function(response, action) {
                                            Ext.Msg
                                                .alert(
                                                    '错误',
                                                    Ext
                                                        .decode(response.responseText).message);
                                            doQry2();
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
    });

}
function deleteManager() {
    var newTacheId = 0;
    var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
        .getSelections();
    if (selinfo.length > 0) {
        newTacheId = (selinfo[0].data.tacheId);
    } else {
        Ext.Msg.alert("提示", "请至少选择一条权限组织数据");
        return;
    }
    userStore3.load({
        params : {
            tacheId : tacheId,
            managerFlag : '0'
        },
        callback: function(records, options, success){
            var userPanel3 = oper.initUserPanel3();
            var formWin2 = new Ext.Window(
                {
                    id : 'menuFormWindow2',
                    title : '删除管理员',
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
                                    Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
                                    return;
                                }
                                var userIdStr = "";
                                for (var i = 0; i < selinfoUser.length; i++){
                                    userIdStr += "," + selinfoUser[i].data.userId;
                                }
                                userIdStr = userIdStr.substring(1);
                                Ext.Ajax
                                    .request({
                                        url : contextPath
                                        + "/tache/deleteManager.do",
                                        async : true,
                                        params : {
                                            userIdStr : userIdStr,
                                            tacheId : newTacheId
                                        },
                                        success : function(response, action) {
                                            Ext.Msg
                                                .alert(
                                                    '成功',
                                                    Ext
                                                        .decode(response.responseText).message);
                                            doQry2();
                                            doCloseWin2();

                                        },
                                        failure : function(response, action) {
                                            Ext.Msg
                                                .alert(
                                                    '错误',
                                                    Ext
                                                        .decode(response.responseText).message);
                                            doQry2();
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
    });

//	doQry2();
}
function editManager() {
	var newTacheId = 0;
	var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
			.getSelections();
	if (selinfo.length > 0) {
		newTacheId = (selinfo[0].data.tacheId);
	} else {
		Ext.Msg.alert("提示", "请至少选择一条权限组织数据");
		return;
	}
	userStore3.load({
		params : {
			tacheId : tacheId,
			tacheIdIsNot : 'all'
		}
	});
	var userPanel3 = oper.initUserPanel3();
	var formWin2 = new Ext.Window(
			{
				id : 'menuFormWindow2',
				title : '修改管理员',
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
									Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
									return;
								}
								Ext.Ajax
										.request({
											url : contextPath
													+ "/tache/editManager.do",
											async : true,
											params : {
												userId : selinfoUser[0].data.userId,
												userName : selinfoUser[0].data.userName,
												tacheId : newTacheId
											},
											success : function(response, action) {
												Ext.Msg
														.alert(
																'成功',
																Ext
																		.decode(response.responseText).message);
												doQry2();
												doCloseWin2();

											},
											failure : function(response, action) {
												Ext.Msg
														.alert(
																'错误',
																Ext
																		.decode(response.responseText).message);
												doQry2();
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
//	doQry2();
}
function addUser() {
	var newTacheId = 0;
	var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
			.getSelections();
	if (selinfo.length > 0) {
		newTacheId = (selinfo[0].data.tacheId);
	} else {
		Ext.Msg.alert("提示", "请至少选择一条权限组织数据");
		return;
	}
	userStore2.load({
		params : {
			tacheId : tacheId,
			tacheIdIsNot : 'not'
		}
	});
	var userPanel2 = oper.initUserPanel2();
	var formWin = new Ext.Window(
			{
				id : 'menuFormWindow',
				title : '增加员工',
				width : 300,
				height : 400,
				// closeAction : 'close',//释放窗体内存
				bodyStyle : {
					padding : '5px 0px 0px 0px',
					margins : '5px 0px 0px 0px'
				},
				items : [ userPanel2 ],
				buttonAlign : 'center',
				buttons : [
						{
							text : '保存',
							icon : '/IOMPROJ/public/image/确认.png',
							onClick : function() {
								var selinfoUser = Ext.getCmp('mainDataGrid2')
										.getSelectionModel().getSelections();
								if (selinfoUser.length <= 0) {
									Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
									return;
								}
								var userIdArray = '';
								for ( var i = 0; i < selinfoUser.length; i++) {
									if (i == 0) {
										userIdArray = selinfoUser[i].data.userId;
									} else {
										userIdArray += ","
												+ selinfoUser[i].data.userId;
									}
								}
								Ext.Ajax
										.request({
											url : contextPath
													+ "/tache/addTacheUser.do",
											async : true,
											params : {
												userIdArray : userIdArray,
												tacheId : newTacheId
											},
											success : function(response, action) {
												Ext.Msg
														.alert(
																'成功',
																Ext
																		.decode(response.responseText).message);
												doQry();
												doCloseWin();
											},
											failure : function(response, action) {
												Ext.Msg
														.alert(
																'错误',
																Ext
																		.decode(response.responseText).message);
												doQry();
												doCloseWin();
											}
										});

							}
						}, {
							text : '关闭',
							icon : '/IOMPROJ/public/image/确认.png',
							onClick : function() {
								Ext.getCmp('menuFormWindow').close();
							}
						} ]

			});
	formWin.show();
	doQry();
}
function delUser() {
	var newTacheId = 0;
	var selinfo = Ext.getCmp('tacheUserDataGrid').getSelectionModel()
			.getSelections();
	if (selinfo.length > 0) {
		newTacheId = (selinfo[0].data.tacheId);
	} else {
		Ext.Msg.alert("提示", "请至少选择一条权限组织数据");
		return;
	}
	var selinfoUser = Ext.getCmp('mainDataGrid').getSelectionModel()
			.getSelections();
	if (selinfoUser.length <= 0) {
		Ext.Msg.alert("提示", "请至少选择一条员工信息数据");
		return;
	}
	var userIdArray = '';
	for ( var i = 0; i < selinfoUser.length; i++) {
		if (i == 0) {
			userIdArray = selinfoUser[i].data.userId;
		} else {
			userIdArray += "," + selinfoUser[i].data.userId;
		}
	}
	Ext.Ajax.request({
		url : contextPath + "/tache/delTacheUser.do",
		async : true,
		params : {
			userIdArray : userIdArray,
			tacheId : newTacheId
		},
		success : function(response, action) {
			Ext.Msg.alert('成功', Ext.decode(response.responseText).message);
			doQry();
		},
		failure : function(response, action) {
			Ext.Msg.alert('错误', Ext.decode(response.responseText).message);
			doQry();
		}
	});
}
function doCloseWin() {
	var closeWMenu = Ext.getCmp('menuFormWindow');
	if (closeWMenu != null) {
		closeWMenu.close();
	}
};
function doCloseWin2() {
	var closeWMenu2 = Ext.getCmp('menuFormWindow2');
	if (closeWMenu2 != null) {
		closeWMenu2.close();
	}
};