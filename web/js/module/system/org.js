var parentId = 50;
var queryOrgListUrl = contextPath + "/org/queryPageOrg.do";
var loadOrgTreeDataUrl = contextPath + "/org/loadOrgTreeData.do";

var dpcoiStore = new Ext.data.ArrayStore({
    fields: ['dpcoiAddJurisdictionId', 'dpcoiAddJurisdictionName'],
    data: [
        [0, '关闭'],
        [1, '开启']
    ]
});
var taskEditStore = new Ext.data.ArrayStore({
    fields: ['value', 'lable'],
    data: [
        [0, '关闭'],
        [1, '开启']
    ]
});
var dpcoiQuaisActStore = new Ext.data.ArrayStore({
    fields: ['value', 'lable'],
    data: [
        [0, '否'],
        [1, '是']
    ]
});
var agreemntStore = new Ext.data.ArrayStore({
    fields: ['value', 'lable'],
    data: [
        [0, '否'],
        [1, '是']
    ]
});

// 页面加载完成后，初始化页面组件
$(function() {
	orgManger.init();
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
var orgManger = (function() {
	var currentObj = null;
	var oldId = 50;
	return {
		init : function() {
			currentObj = this;
			currentObj.initLayout();
			currentObj.orgCenterLayout();
			currentObj.loadOrgTree();
			currentObj.initSearchPanel();
			currentObj.initOrgListGrid();
		},

		initLayout : function() {
			$('body').layout({
				fit : true,
				boder : true
			});
			$('body').layout('resize');
		},

		orgCenterLayout : function() {
			$('#orgCenterLayout').layout({
				fit : true,
				boder : false
			});

			$('#orgCenterLayout').layout('panel', 'north').panel('resize', {
				height : 102
			});
			$('#orgCenterLayout').layout('resize');
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
				var pageSize = $("#orgList").datagrid('options').pageSize;
				$("#orgList").datagrid(
						{
							url : queryOrgListUrl + "?parentId=" + id
									+ "&pageSize=" + pageSize
						});
				$("#orgList").datagrid('reload');
			}
			node.checked = 'true';
			oldId = node.id;
			parentId = oldId;
		},
		treeDbClickEvent : function(node) {
			alert(111);
		},

		initOrgListGrid : function() {
			var queryOrgListUrlFirst = queryOrgListUrl + "?parentId=50"
			$("#orgList").datagrid({
				fit : true,
				url : queryOrgListUrlFirst,
				singleSelect : true, // 只允许选择1条
				rownumbers : false, // 不显示列行数
				pagination : true, // 显示页码工具在dataGird下
				fitColumns : false, // 不自动展开收缩列的大小，使之可以水平滚动
				pageSize : 20,
				border : false,
				columns : [ [ {
					field : "orgId",
					checkbox : true
				}, {
					field : 'parentId',
					title : '父组织id',
					hidden : true,
					width : '20%'
				}, {
					field : 'orgName',
					title : '组织名称',
					width : '20%'
				}, {
					field : 'orgPathCode',
					title : '组织路径',
					hidden : true,
					width : '10%'
				}, {
                    field : 'dpcoiAddJurisdiction',
                    title : 'DPCOI增加权限',
                    width : '10%',
                    formatter: function(value, row, index){
                        if(value==0){
                            return "关闭";
                        }else if(value==1){
                            return "开启";
                        }else {
                            return "";
                        }
                    }
                }, {
                    field : 'agreementTrack',
                    title : '立合追踪者',
                    width : '10%',
                    formatter: function(value, row, index){
                        if(value==0){
                            return "否";
                        }else if(value==1){
                            return "是";
                        }else {
                            return "否";
                        }
                    }
                }, {
                    field : 'taskEditJurisdiction',
                    title : '4M修改权限',
                    width : '10%',
                    formatter: function(value, row, index){
                        if(value==0){
                            return "关闭";
                        }else if(value==1){
                            return "开启";
                        }else {
                            return "关闭";
                        }
                    }
                }, {
                    field : 'dpcoiQuaisAct',
                    title : 'DPCOI量准担当',
                    width : '10%',
                    formatter: function(value, row, index){
                        if(value==0){
                            return "否";
                        }else if(value==1){
                            return "是";
                        }else {
                            return "否";
                        }
                    }
                }, {
					field : 'orgCreateDate',
					title : '创建日期',
					width : '20%'
				}, {
					field : 'orgMark',
					title : '备注',
					width : '40%'
				} ] ],
				onClickRow : currentObj.onClickRow
			});
		},

		onClickRow : function(rowIndex, rowData) {

		},

		initSearchPanel : function() {
			var searchPanel = new Ext.FormPanel({
				renderTo : 'orgSearchPanel',
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
						columnWidth : .25,
						layout : 'form',
						items : [ {
							xtype : 'textfield',
							fieldLabel : '组织名称',
							name : 'orgNameQuery',
							id : 'orgNameQuery',
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
								html :'&nbsp;&nbsp;',
								text : ''
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
								html :'&nbsp;&nbsp;',
								text : ''
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
								html :'&nbsp;&nbsp;',
								text : ''
							}, {
								xtype : 'button',
								text : '新增',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doAddOrg
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html :'&nbsp;&nbsp;',
								text : ''
							}, {
								xtype : 'button',
								text : '修改',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doEditOrg
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								html :'&nbsp;&nbsp;',
								text : ''
							}, {
								id : 'delOrg',
								xtype : 'button',
								text : '删除',
								columnWidth : 0.05,
								icon : '/WorkFlow/images/icon/icon13.gif',
								listeners : {
									"click" : doDelOrg
								}
							}, {
								xtype : 'label',
								columnWidth : 0.05,
								text : ''
							} ]
						} ]
					} ]
				} ]
			});
		}
	};

})();

function doQry() {
	$("#orgList").datagrid('reload');
}
function doCloseWin() {
	var closeW = Ext.getCmp('orgFormWindow');
	if (closeW != null) {
		closeW.close();
	}
};
// 查询
function doSelectByName() {
	var orgName = (Ext.getCmp('orgNameQuery') != null ? Ext.getCmp(
			'orgNameQuery').getValue() : '');
	var pageSize = $("#orgList").datagrid('options').pageSize;
	$("#orgList").datagrid(
			{
				url : queryOrgListUrl + "?parentId=" + parentId + "&pageSize="
						+ pageSize + "&orgName=" + encodeURI(orgName)
			});
}
// 重置
function doReset() {
	Ext.getCmp('orgName').setValue('');
}
// 新增
function doAddOrg() {
	orgForm('add', 0, parentId);
}
// 修改
function doEditOrg() {
	var rows = $("#orgList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	orgForm('edit', 0, parentId);
}
// 删除
function doDelOrg() {
	var rows = $("#orgList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	Ext.get("delOrg").on("click", function() {
		Ext.MessageBox.confirm("确认框", "您确认删除组织吗？", function(btn, txt) {
			if (btn == "yes") {
				delOrgBack("ok");
			} else if (btn == "no") {
				// Ext.MessageBox.alert("系统提示","你放弃了删除操作！")
			}
		});
	});
}
// 删除操作
function delOrgBack(id) {
	var rows = $("#orgList").datagrid('getSelections');
	if (rows.length != 1) {
		Ext.MessageBox.alert("提示", "请选中一条数据进行操作", function(btn) {
		});
		return;
	}
	var orgId = rows[0].orgId;
	// alert(id);
	Ext.Ajax.request({
		url : contextPath + '/org/delOrg.do?orgId=' + orgId,
		async : false,
		success : function(response, action) {
			var result = Ext.decode(response.responseText).message;
			if (result == "2") {
				Ext.Msg.alert('提示', "请先删除此组织的子组织再进行操作");
			} else if (result == "1") {
				Ext.Msg.alert('提示', "删除成功");
			}
			$('#orgTree').tree('reload');
			doQry();
		},
		failure : function(response, action) {
			Ext.Msg.alert('错误', Ext.decode(response.responseText).message);
			doQry();
		}
	});
	doQry();
}
function orgForm(action, id, parentId) {
	var orgFormItem = orgFormPanel(action, id, parentId);
	var formWin = new Ext.Window({
		id : 'orgFormWindow',
		title : '组织管理',
		width : 350,
		height : 300,
		// closeAction : 'close',//释放窗体内存
		bodyStyle : {
			padding : '5px 0px 0px 0px',
			margins : '5px 0px 0px 0px'
		},
		// layout : 'fit',
		// modal : true, // 设置遮罩，即你要的效果
		// resiziable : false,
		items : [ orgFormItem ],
		buttonAlign : 'center',
		buttons : [
				{
					text : '保存',
					icon : '/IOMPROJ/public/image/确认.png',
					onClick : function() {
						Ext.getCmp('orgForm').getForm().submit(
								{
									url : contextPath + '/org/' + action
											+ 'Org.do?parentId=' + parentId,
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
						Ext.getCmp('orgFormWindow').close();
					}
				} ]

	});
	formWin.show();
}

function orgFormPanel(action, id, parentId) {
	var v_id = '';
	var v_orgName = '';
	var v_orgMark = '';
	var v_dpcoiAddJurisdiction = 0;
	var v_agreementTrack = 0;
	var rows = $("#orgList").datagrid('getSelections');
	if (action == 'edit') {
		v_id = rows[0].orgId;
		v_orgName = rows[0].orgName;
		v_orgMark = rows[0].orgMark;
        v_dpcoiAddJurisdiction = rows[0].dpcoiAddJurisdiction;
        v_agreementTrack = rows[0].agreementTrack;
        v_taskEditJurisdiction = rows[0].taskEditJurisdiction;
        v_dpcoiQuaisAct = rows[0].dpcoiQuaisAct;
	}
	var InitOrgForm = new Ext.FormPanel({
		id : 'orgForm',
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
				columnWidth : .90,
				xtype : 'textfield',
				fieldLabel : '组织名称',
				name : 'orgName',
				id : 'orgName',
				allowBlank : false,
				value : v_orgName,
				anchor : '95%'
			},{
                columnWidth:.90,
				xtype:'combo',
				fieldLabel:'DPCOI增加权限',
                hiddenName: 'dpcoiAddJurisdiction',
				id: 'dpcoiAddJurisdictionId',
				valueField: 'dpcoiAddJurisdictionId',
				displayField: 'dpcoiAddJurisdictionName',
				triggerAction:'all',
				store: dpcoiStore,
                allowBlank:true,
                mode : 'local',
                anchor:'95%',
				value : v_dpcoiAddJurisdiction,
				emptyText:'-----请选择-----'
            }, {
                columnWidth:.90,
                xtype:'combo',
                fieldLabel:'立合追踪者',
                hiddenName: 'agreementTrack',
                id: 'agreementTrackId',
                valueField: 'value',
                displayField: 'lable',
                triggerAction:'all',
                store: agreemntStore,
                allowBlank:true,
                mode : 'local',
                anchor:'95%',
                value : v_agreementTrack,
                emptyText:'-----请选择-----'
            }, {
                columnWidth:.90,
                xtype:'combo',
                fieldLabel:'4M修改权限',
                hiddenName: 'taskEditJurisdiction',
                id: 'taskEditJurisdictionId',
                valueField: 'value',
                displayField: 'lable',
                triggerAction:'all',
                store: taskEditStore,
                allowBlank:true,
                mode : 'local',
                anchor:'95%',
                value : v_taskEditJurisdiction,
                emptyText:'-----请选择-----'
            }, {
                columnWidth:.90,
                xtype:'combo',
                fieldLabel:'DPCOI量准担当',
                hiddenName: 'dpcoiQuaisAct',
                id: 'dpcoiQuaisActId',
                valueField: 'value',
                displayField: 'lable',
                triggerAction:'all',
                store: dpcoiQuaisActStore,
                allowBlank:true,
                mode : 'local',
                anchor:'95%',
                value : v_dpcoiQuaisAct,
                emptyText:'-----请选择-----'
            }, {
				columnWidth : .90,
				xtype : 'textarea',
				fieldLabel : '备注',
				name : 'orgMark',
				id : 'orgMark',
				allowBlank : true,
				value : v_orgMark,
				anchor : '95%'
			} ]
		} ]
	});
	return InitOrgForm;
}
