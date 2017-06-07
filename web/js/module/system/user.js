var oper = new Oper();
var parentId = 50;
var queryUserListUrl = contextPath + "/user/queryPageUser.do";
var loadOrgTreeDataUrl = contextPath + "/org/loadOrgTreeData.do";
var isCheckAllStore = new Ext.data.ArrayStore({
	fields : [ 'id', 'value' ],
	data : [ [ "1", "仅查询当前组织" ], [ "2", "查询当前及下属组织" ] ]
});
// var isHeaderStore = new Ext.data.ArrayStore({
// fields : [ 'id', 'value' ],
// data : [ { display:'是', value:'1' }, { display:'否', value:'0' } ]
// });
var isHeaderStore = new Ext.data.ArrayStore({
	fields : [ 'id', 'value' ],
	data : [ [ "1", "是" ], [ "0", "否" ] ]
});
var orgStore = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "orgId", "orgName" ],
	proxy : new Ext.data.HttpProxy({
		sync : true,
		url : contextPath + "/org/queryPageOrg.do"
	})

});
orgStore.load();
function Oper() {
	this.initOrgPanel = function() {
		var sm = new Ext.grid.CheckboxSelectionModel({
			singleSelect : true
		});
		var orgPanel = new Ext.grid.GridPanel({
			region : "center",
			id : "orgDataGrid",
			height : 330,
			columns : [ sm, {
				header : '组织id',
				hidden : true,
				dataIndex : 'orgId'
			}, {
				header : '组织称',
				hidden : false,
				dataIndex : 'orgName'
			} ],
			autoScroll : true,
			store : orgStore,
			sm : sm
		});
		return orgPanel;
	}
}
// 页面加载完成后，初始化页面组件
$(function() {
	userManger.init();
});
if ((typeof Range !== "undefined") && !Range.prototype.createContextualFragment) {
	Range.prototype.createContextualFragment = function(html) {
		var frag = document.createDocumentFragment(), div = document
				.createElement("div");
		frag.appendChild(div);
		div.outerHTML = html;
		return frag;
	};
}
// 页面配置对象
var userManger = (function() {
	var currentObj = null;
	var oldId = 50;
	return {
		init : function() {
			currentObj = this;
			currentObj.initLayout();
			currentObj.userCenterLayout();
			currentObj.loadOrgTree();
			currentObj.initSearchPanel();
			currentObj.initUserListGrid();
		},

		initLayout : function() {
			$('body').layout({
				fit : true,
				boder : true
			});
			$('body').layout('resize');
		},

		userCenterLayout : function() {
			$('#userCenterLayout').layout({
				fit : true,
				boder : false
			});

			$('#userCenterLayout').layout('panel', 'north').panel('resize', {
				height : 102
			});
			$('#userCenterLayout').layout('resize');
		},

		loadOrgTree : function() {
			$('#orgTree').tree({
				onBeforeLoad : currentObj.onBeforeLoad,
				onClick : currentObj.treeClickEvent,
				onDbClick : currentObj.treeDbClickEvent,
				onLoadSuccess : function() {
					var currentNode = $('#orgTree').tree('getRoot');
					oldId = currentNode.id;
				}
			});
		},

		// 异步加载 子节数据
		onBeforeLoad : function(node, param) {
			if (node) {
				var id = node.id;
				$(this).tree('options').url = loadOrgTreeDataUrl + "?parentId="
						+ id + "&random=" + new Date().getTime();
			} else {
				$(this).tree('options').url = loadOrgTreeDataUrl
						+ "?parentId=-1&random=" + new Date().getTime();
			}
		},

		treeClickEvent : function(node) {
			var id = node.id;
			if (oldId == id) { // 如果点击的是同一行则不做任何操作
				return;
			} else {// 刷新页面右方列表数据
				var pageSize = $("#userList").datagrid('options').pageSize;
				$("#userList").datagrid(
						{
							url : queryUserListUrl + "?orgId=" + id
									+ "&pageSize=" + pageSize
						});
				$("#userList").datagrid('reload');
			}
			node.checked = 'true';
			oldId = node.id;
			parentId = oldId;
		},
		treeDbClickEvent : function(node) {
			alert(111);
		},

		initUserListGrid : function() {
			var queryUserListUrlFirst = queryUserListUrl + ""
			$("#userList").datagrid({
				fit : true,
				url : queryUserListUrlFirst,
				singleSelect : true, // 只允许选择1条
				rownumbers : false, // 不显示列行数
				pagination : true, // 显示页码工具在dataGird下
				fitColumns : false, // 不自动展开收缩列的大小，使之可以水平滚动
				pageSize : 20,
				border : false,
				columns : [ [ {
					field : "userId",
					checkbox : true
				}, {
					field : 'userName',
					title : '姓名',
					width : '20%'
				}, {
					field : 'userWorkId',
					title : '工号',
					width : '10%'
				}, {
					field : 'orgName',
					title : '组织',
					width : '10%'
				}, {
					field : 'userCode',
					title : '登陆名',
					width : '10%'
				}, {
					field : 'password',
					title : '密码',
					hidden : true,
					width : '10%'
				}, {
					field : 'orgId',
					title : '组织id',
					hidden : true,
					width : '10%'
				}, {
					field : 'email',
					title : '邮箱',
					width : '20%'
				}, {
					field : 'mobileTel',
					title : '手机',
					width : '20%'
				}, {
					field : 'isHeader',
					title : '是否领导',
					width : '20%'
				} ] ],
				onClickRow : currentObj.onClickRow
			});
		},

		onClickRow : function(rowIndex, rowData) {

		},

		initSearchPanel : function() {
			var searchPanel = new Ext.FormPanel({
				renderTo : 'userSearchPanel',
				labelAlign : 'right',
				labelWidth : 70,
				frame : true,
				// title : '查询条件',
				bodyStyle : {
					padding : '0px 0px 0px 0px',
					margins : '0px 0px 0px 0px'
				},
				height : 100,
				width : Ext.getBody().getSize().width - 252,
				layout : 'column',
				items : [ {
					layout : 'column',
					title : '查询条件',
					bodyStyle : {
						padding : '1px 0px 0px 0px',
						margins : '1px 0px 0px 0px'
					},
					width : Ext.getBody().getSize().width - 252,
					items : [ {
						columnWidth : .20,
						layout : 'form',
						items : [ {
							xtype : 'textfield',
							fieldLabel : '姓名',
							name : 'userNameQuery',
							id : 'userNameQuery',
							anchor : '95%'
						} ]
					}, {
						columnWidth : .20,
						layout : 'form',
						items : [ {
							xtype : 'textfield',
							fieldLabel : '工号',
							name : 'userWorkIdQuery',
							id : 'userWorkIdQuery',
							anchor : '95%'
						} ]
					}, {
						columnWidth : .20,
						layout : 'form',
						items : [ {
							xtype : 'textfield',
							fieldLabel : '手机号',
							name : 'mobileTelQuery',
							id : 'mobileTelQuery',
							anchor : '95%'
						} ]
					}, {
						columnWidth : .25,
						layout : 'form',
						items : [ {
							xtype : 'combo',
							fieldLabel : '选择',
							name : 'isCheckAllQuery',
							id : 'isCheckAllQuery',
							allowBlank : true,
							mode : 'local',
							valueField : 'id',
							displayField : 'value',
							triggerAction : 'all',
							store : isCheckAllStore,
							// emptyText: '查询当前及下属组织',
							value : '2',
							anchor : '95%'
						} ]
					}, {
						columnWidth : 1,
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
									"click" : doSelectByName
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
									"click" : doReset
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html : '&nbsp;&nbsp;',
								text : '',
								anchor : '95%'
							}, {
								xtype : 'button',
								text : '新增',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doAddUser
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html : '&nbsp;&nbsp;',
								text : '',
								hidden : false,
								anchor : '95%'
							}, {
								xtype : 'button',
								text : '修改',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doEditUser
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html : '&nbsp;&nbsp;',
								text : '',
								anchor : '95%'
							}, {
								id : 'delUser',
								xtype : 'button',
								text : '删除',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doDelUser
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html : '&nbsp;&nbsp;',
								text : '',
								anchor : '95%'
							}, {
								id : 'updateOrg',
								xtype : 'button',
								text : '调整组织',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doUpdateOrg
								}
							} ]
						} ]
					} ]
				} ]
			});
		}
	};

})();

function doQry() {
	$("#userList").datagrid('reload');
}

function doCloseWin() {
	var closeW = Ext.getCmp('userFormWindow');
	if (closeW != null) {
		closeW.close();
	}
	var closeWMenu = Ext.getCmp('orgFormWindow');
	if (closeWMenu != null) {
		closeWMenu.close();
	}
};
// 查询
function doSelectByName() {
	var userName = (Ext.getCmp('userNameQuery') != null ? Ext.getCmp(
			'userNameQuery').getValue() : '');
	var userWorkId = (Ext.getCmp('userWorkIdQuery') != null ? Ext.getCmp(
			'userWorkIdQuery').getValue() : '');
	var mobileTel = (Ext.getCmp('mobileTelQuery') != null ? Ext.getCmp(
			'mobileTelQuery').getValue() : '');
	var isCheckAll = (Ext.getCmp('isCheckAllQuery') != null ? Ext.getCmp(
			'isCheckAllQuery').getValue() : '1');
	// alert(isCheckAll);
	var pageSize = $("#userList").datagrid('options').pageSize;
//	$("#userList").datagrid('reload', {
//		orgId : parentId,
//		pageSize : pageSize,
//		userName : userName,
//		userWorkId : userWorkId,
//		mobileTel : mobileTel,
//		isCheckAll : isCheckAll
//	});
	// $("#userList").datagrid(
	// {
	// url : queryUserListUrl + "?orgId=" + parentId + "&pageSize="
	// + pageSize + "&userName=" + encodeURI(userName)
	// + "&userWorkId=" + userWorkId + "&mobileTel="
	// + mobileTel + "&isCheckAll=" + isCheckAll
	// });
	 $("#userList").datagrid(
			{
				url : queryUserListUrl + "?orgId=" + parentId + "&pageSize="
						+ pageSize + "&userWorkId=" + userWorkId
						+ "&mobileTel=" + mobileTel + "&isCheckAll="
						+ isCheckAll,
				queryParams : {
					userName : userName
				}
			});
}
// 重置
function doReset() {
	Ext.getCmp('userNameQuery').setValue('');
	Ext.getCmp('userWorkIdQuery').setValue('');
	Ext.getCmp('mobileTelQuery').setValue('');
	// Ext.getCmp('isCheckAllQuery').setValue('');
}
// 新增
function doAddUser() {
	// userForm('add', 0, parentId);
	Ext.MessageBox.prompt("输入框", "请输入工号：", function(bu, txt) {
		if ("cancel" == bu)
			return;
		var userCode = txt;
		if (userCode == undefined || userCode == null || userCode == "") {
			alert("工号不能为空！");
			return;
		}
		Ext.Ajax.request({
			url : contextPath + '/user/addUser2.do',
			waitTitle : '提示',
			waitMsg : '请稍后,正在提交数据...',
			params : {
				userCode : userCode,
				orgId : parentId
			},
			success : function(response, action) {
				var responseText = Ext.decode(response.responseText);
				if (responseText.success) {
					doQry();
					alert(responseText.message);
				} else {
					alert(responseText.message);
				}
			},
			failure : function(a) {
				Ext.Msg.alert('提示', '添加用户失败！');
			}
		});
	})
}
// 修改
function doEditUser() {
	var rows = $("#userList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	userForm('edit', 0, parentId);
}
// 删除
function doDelUser() {
	var rows = $("#userList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	Ext.get("delUser").on("click", function() {
		Ext.MessageBox.confirm("确认框", "您确认删除员工吗？", function(btn, txt) {
			if (btn == "yes") {
				delUserBack("ok");
			} else if (btn == "no") {
				// Ext.MessageBox.alert("系统提示","你放弃了删除操作！")
			}
		});
	});
}
// 删除操作
function delUserBack(id) {
	var rows = $("#userList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	var userId = rows[0].userId;
	// alert(userId);
	Ext.Ajax.request({
		url : contextPath + '/user/delUser.do?userId=' + userId,
		async : false,
		success : function(response, action) {
			Ext.Msg.alert('成功', Ext.decode(response.responseText).message);
			doQry();
		},
		failure : function(response, action) {
			Ext.Msg.alert('错误', Ext.decode(response.responseText).message);
			doQry();
		}
	});
	doQry();
}
// 调整组织
function doUpdateOrg() {
	var rows = $("#userList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条员工数据进行操作", function(btn) {
		});
		return;
	}
	var userId = rows[0].userId;
	var orgPanel = oper.initOrgPanel();
	var formWin = new Ext.Window(
			{
				id : 'orgFormWindow',
				title : '调整组织',
				width : 300,
				height : 400,
				// closeAction : 'close',//释放窗体内存
				bodyStyle : {
					padding : '5px 0px 0px 0px',
					margins : '5px 0px 0px 0px'
				},
				items : [ orgPanel ],
				buttonAlign : 'center',
				buttons : [
						{
							text : '保存',
							icon : '/IOMPROJ/public/image/确认.png',
							onClick : function() {
								var selinfoUser = Ext.getCmp('orgDataGrid')
										.getSelectionModel().getSelections();
								if (selinfoUser.length <= 0) {
									Ext.Msg.alert("提示", "请至少选择一条组织数据");
									return;
								}
								var orgId = selinfoUser[0].data.orgId;
								Ext.Ajax
										.request({
											url : contextPath
													+ "/user/editUserOrg.do",
											async : true,
											params : {
												orgId : orgId,
												id : userId
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
								Ext.getCmp('orgFormWindow').close();
							}
						} ]

			});
	formWin.show();
	doQry();
}
function userForm(action, id, parentId) {
	var userFormItem = userFormPanel(action, id, parentId);
	var formWin = new Ext.Window({
		id : 'userFormWindow',
		title : '人员管理',
		width : 300,
		height : 400,
		// closeAction : 'close',//释放窗体内存
		bodyStyle : {
			padding : '5px 0px 0px 0px',
			margins : '5px 0px 0px 0px'
		},
		// layout : 'fit',
		// modal : true, // 设置遮罩，即你要的效果
		// resiziable : false,
		items : [ userFormItem ],
		buttonAlign : 'center',
		buttons : [
				{
					text : '保存',
					icon : '/IOMPROJ/public/image/确认.png',
					onClick : function() {
						Ext.getCmp('userForm').getForm().submit(
								{
									url : contextPath + '/user/' + action
											+ 'User.do?orgId=' + parentId,
									waitTitle : '提示',
									waitMsg : '请稍后,正在提交数据...',
									// method : 'post',
									// async : false,//同步请求
									// 如果有表单以外的其它参数，可以加在这里。我这里暂时为空，也可以将下面这句省略
									baseParams : {},
									// 第一个参数是传入该表单，第二个是Ext.form.Action对象用来取得服务器端传过来的json数据
									success : function(response, action) {
										// var a =
										// Ext.decode(response.responseText);
										Ext.Msg.alert('提示',
												action.result.message);
										doCloseWin();
										doQry();
									},
									failure : function(response, action) {
										Ext.Msg.alert('错误',
												action.result.message);
										doCloseWin();
										doQry();
									}
								});
					}
				}, {
					text : '关闭',
					icon : '/IOMPROJ/public/image/确认.png',
					onClick : function() {
						Ext.getCmp('userFormWindow').close();
					}
				} ]

	});
	formWin.show();
}

function userFormPanel(action, id, parentId) {
	var v_id = '';
	var v_userCode = '';
	var v_userName = '';
	var v_userWorkId = '';
	var v_userSex = '';
	var v_password = '';
	var v_orgId = '';
	var v_email = '';
	var v_mobileTel = '';
	var v_userMark = '';
	var v_isHdeaer = '';
	var rows = $("#userList").datagrid('getSelections');
	if (action == 'edit') {
		var v_id = rows[0].userId;
		var v_userName = rows[0].userName;
		var v_userCode = rows[0].userCode;
		var v_userWorkId = rows[0].userWorkId;
		var v_userSex = rows[0].userSex;
		var v_password = rows[0].password;
		var v_orgId = rows[0].orgId;
		var v_email = rows[0].email;
		var v_mobileTel = rows[0].mobileTel;
		var v_userMark = rows[0].userMark;
		var v_isHeader = rows[0].isHeader;

	}
	var InituserForm = new Ext.FormPanel({
		id : 'userForm',
		region : 'center',
		// labelAlign : 'left',
		// labelWidth : 130,
		frame : true,
		items : [ {
			layout : 'form',
			items : [ {
				columnWidth : .01,
				xtype : 'textfield',
				fieldLabel : '主键ID',
				name : 'id',
				id : 'id',
				allowBlank : true,
				value : v_id,
				hidden : true,
				hideLabel : true,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '姓名',
				name : 'userName',
				id : 'userName',
				allowBlank : false,
				value : v_userName,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '工号',
				name : 'userWorkId',
				id : 'userWorkId',
				allowBlank : false,
				value : v_userCode,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'radiogroup',
				fieldLabel : '性别',
				items : [ {
					boxLabel : '男',
					name : 'userSex',
					inputValue : '男',
					id : 'userSex1'
				}, {
					boxLabel : '女',
					name : 'userSex',
					inputValue : '女',
					id : 'userSex2'
				} ]
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '登陆名',
				name : 'userCode',
				id : 'userCode',
				allowBlank : false,
				value : v_userCode,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '密码',
				name : 'password',
				id : 'password',
				allowBlank : true,
				// hidden :true,
				inputType : 'password',
				value : v_password,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '邮箱',
				name : 'email',
				id : 'email',
				vtype : "email",
				regexText : '必须是正确的邮件地址',
				allowBlank : false,
				value : v_email,
				anchor : '95%'
			}, {
				columnWidth : .40,
				xtype : 'textfield',
				fieldLabel : '手机',
				name : 'mobileTel',
				id : 'mobileTel',
				regex : /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
				regexText : '必须是11位手机号',
				allowBlank : false,
				value : v_mobileTel,
				anchor : '95%'
			}, {
				columnWidth : .90,
				xtype : 'textarea',
				fieldLabel : '备注',
				name : 'userMark',
				id : 'userMark',
				allowBlank : true,
				value : v_userMark,
				anchor : '95%'
			}, {
				xtype : 'combo',
				fieldLabel : '是否领导',
				hiddenName : 'isHeader',// 提交时后台获取的值
				// id : 'isHeader',
				allowBlank : true,
				mode : 'local',
				valueField : 'id',
				displayField : 'value',
				triggerAction : 'all',
				store : isHeaderStore,
				value : v_isHeader,
				// emptyText : '请选择',
				anchor : '95%'
			} ]
		} ]
	});
	return InituserForm;
}
