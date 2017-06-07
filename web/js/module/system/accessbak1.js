var parentId = 50;
var queryUserListUrl = contextPath + "/user/queryPageUser.do";
var loadOrgTreeDataUrl = contextPath + "/org/loadOrgTreeData.do";
var menuWindow = null;
var userIdCheckMenu = null;
//var data = [ [ 'a', 'a' ], [ 'b', 'b' ], [ 'c', 'c' ] ];
//
//var menuAccessStore = new Ext.data.SimpleStore({
//	fields : [ 'id', 'name' ],
//	data : data
//});
var menuAccessStore = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userMenuId", "menuId", "menuName" ],
	proxy : new Ext.data.HttpProxy({
		url : contextPath+"/menu/queryUserMenu.do?checkHaveAccess=''"
	})

});
menuAccessStore.load();
var menuAccessStore2 = new Ext.data.JsonStore({
	remoteSort : true,
	 root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "menuId", "menuName" ],
	proxy : new Ext.data.HttpProxy({
		url : contextPath+"/menu/queryMenuByUserId.do?checkHaveAccess=not"
	})

});
menuAccessStore2.load();
var menuAccessStore3 = new Ext.data.JsonStore({
	remoteSort : true,
	root : 'rows',
	totalProperty : 'totalCounts',
	fields : [ "userMenuId", "menuId", "menuName" ],
	proxy : new Ext.data.HttpProxy({
		url : contextPath+"/menu/queryUserMenu.do?checkHaveAccess=''"
	})

});
menuAccessStore3.load();
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
var currentObj = null;
// 页面配置对象
var userManger = (function() {
	
	var oldId = 50;
	return {
		init : function() {
			currentObj = this;
			currentObj.initLayout();
			currentObj.userCenterLayout();
			currentObj.loadOrgTree();
			currentObj.initSearchPanel();
			currentObj.initUserListGrid();
			currentObj.initAccessListGrid();
			currentObj.menuAccessPanel();
			currentObj.menuAccessPanel2();
			currentObj.menuAccessPanel3();
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
			$('#userCenterLayout').layout('panel', 'south').panel('resize', {
				height : 302
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

		initUserListGrid : function() {
			var queryUserListUrlFirst = queryUserListUrl+""
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
					field : 'userSex',
					title : '性别',
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
				} ] ],
//				onClickRow : currentObj.onClickRow
				onClickRow : onClickRow
			});
		},

//		onClickRow : function(rowIndex, rowData) {
//			doQryMenuAccess();
//		},

		initSearchPanel : function() {
			var searchPanel = new Ext.FormPanel({
				renderTo : 'userSearchPanel',
				labelAlign : 'right',
				labelWidth : 70,
				frame : true,
//				title : '查询条件',
				bodyStyle : {
					padding : '0px 0px 0px 0px',
					margins : '0px 0px 0px 0px'
				},
				height : 100,
				width : Ext.getBody().getSize().width - 252,
				layout : 'column',
				items : [{
					layout : 'column',
					title : '查询条件',
					bodyStyle : {
						padding : '1px 0px 0px 0px',
						margins : '1px 0px 0px 0px'
					},
					width : Ext.getBody().getSize().width - 252,
					items : [
				    {
					columnWidth : .20,
					layout : 'form',
					items : [ {
						xtype : 'textfield',
						fieldLabel : '姓名',
						name : 'userNameQuery',
						id : 'userNameQuery',
						anchor : '95%'
					} ]
				},{
					columnWidth : .20,
					layout : 'form',
					items : [ {
						xtype : 'textfield',
						fieldLabel : '工号',
						name : 'userWorkIdQuery',
						id : 'userWorkIdQuery',
						anchor : '95%'
					} ]
				},{
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
					columnWidth : 1,
					layout : 'column',
					items : [ {
						layout : 'column',
						items : [ {
							xtype : 'label',
							columnWidth : 0.3,
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
							columnWidth : 0.2,
							text : '',
							anchor : '95%'
						}, {
							xtype : 'label',
							columnWidth : 0.05,
							text : '',
							anchor : '95%'
						} ]
					} ]
				}] 
				} ]
			});
		},
		initAccessListGrid :function(){
			var menuAccessPanel1 = currentObj.menuAccessPanel();			
			var accessListPanel = new Ext.FormPanel({
				renderTo : 'accessList',
//				labelAlign : 'right',
//				bodyStyle : {
//					padding : '0px 0px 0px 0px',
//					margins : '0px 0px 0px 0px'
//				},
				height : 302,
				width : Ext.getBody().getSize().width - 252,
				layout :'column',
				items : [{
					title:'已有菜单权限',
					height: '100%',
					columnWidth: 0.5,
//					layout :'form',
					items:[menuAccessPanel1,{
						layout :'column',
						items:[{
							xtype:'label',
							witdh:0.3
						},{
							xtype : 'button',
							text :'增加权限',
							handler : function(){
								addMenuAccess();
							}
						},{
							xtype : 'button',
							text :'删除权限',
							handler : function(){
								delMenuAccess();
							}
						}]
					}]
				},{
					title:'已有功能权限',
					html:'功能权限',
					height: '100%',
					columnWidth: 0.5
				}]
			});
		},
		menuAccessPanel:function(){
			var menuAccessGrid = new Ext.grid.GridPanel({
				id:'menuAccessGridId',
				columns:[
				 {
				    header:'菜单权限id',
					hidden :true,
					dataIndex :'userMenuId'
				},{
				    header:'菜单id',
					hidden :true,
					dataIndex :'menuId'
				},{
					header:'权限名称',
					dataIndex:'menuName',
					width:200
				}],
				store:menuAccessStore,				
				height:200
			});
			var listGridStore = Ext.getCmp('menuAccessGridId').store;
			listGridStore.on('beforeload', function(store) {
				if (listGridStore.lastOptions != null) {
					var param = {};
					param.userId = userIdCheckMenu;
					listGridStore.baseParams = param;
				}
			});
			Ext.getCmp("menuAccessGridId").getStore().reload();
			return menuAccessGrid;
		},
		menuAccessPanel2:function(){
			var sm2 = new Ext.grid.CheckboxSelectionModel(
//					{singleSelect : false}
				);
			var menuAccessGrid2 = new Ext.grid.GridPanel({
				id:'menuAccessGridId2',
//				title:'权限数据',
//				mode:'local',
				sm:sm2,
				columns:[
				         sm2,
				{
				    header:'菜单id',
					hidden :true,
					dataIndex :'menuId'
				},{
					header:'菜单名称',
					dataIndex:'menuName',
					width:200
				}],
				store:menuAccessStore2,				
				height:200
			});
			Ext.getCmp("menuAccessGridId2").getStore().load();
			return menuAccessGrid2;
		},
		menuAccessPanel3:function(){
			var sm = new Ext.grid.CheckboxSelectionModel(
//					{singleSelect : true}
//					{dataIndex:'userMenuId'}
				);
			var menuAccessGrid3 = new Ext.grid.GridPanel({
				id:'menuAccessGridId3',
//				title:'权限数据',
//				mode:'local',
				sm:sm,

				columns:[
				         sm,
				 {
				    header:'菜单权限id',
					hidden :true,
					dataIndex :'userMenuId'
				},{
				    header:'菜单id',
					hidden :true,
					dataIndex :'menuId'
				},{
					header:'菜单名称',
					dataIndex:'menuName',
					width:200
				}],
				store:menuAccessStore3,				
				height:200
			});
			Ext.getCmp("menuAccessGridId3").getStore().load();
			return menuAccessGrid3;
		}
	};

})();

function doQry() {
	$("#userList").datagrid('reload');
}
function onClickRow() {
	doQryMenuAccess();
}
function doQryMenuAccess(){
	var rows=$("#userList").datagrid('getSelections');
	var userId=null;
	if(rows.length>0){
		userId = rows[0].userId;
	}
	userIdCheckMenu = userId;
	
//	Ext.getCmp("menuAccessGridId").store.removeAll();
//	Ext.getCmp("menuAccessGridId").getStore().load({params:{
//		userId:userId
//	}});
	Ext.getCmp("menuAccessGridId").getStore().reload();
}
function doCloseWin(){
	var closeW = Ext.getCmp('menuFormWindow');
	if(closeW!=null){
		closeW.close();
	}
};
// 查询
function doSelectByName() {
	var userName = (Ext.getCmp('userNameQuery') != null ? Ext.getCmp('userNameQuery')
			.getValue() : '');
	var userWorkId = (Ext.getCmp('userWorkIdQuery') != null ? Ext.getCmp('userWorkIdQuery')
			.getValue() : '');
	var mobileTel = (Ext.getCmp('mobileTelQuery') != null ? Ext.getCmp('mobileTelQuery')
			.getValue() : '');
	var pageSize = $("#userList").datagrid('options').pageSize;
	$("#userList").datagrid(
			{
				url : queryUserListUrl + "?orgId=" + parentId + "&pageSize="
						+ pageSize + "&userName=" + encodeURI(userName)
						+"&userWorkId="+userWorkId
						+"&mobileTel="+mobileTel
			});
}
// 重置
function doReset() {
	Ext.getCmp('userNameQuery').setValue('');
	Ext.getCmp('userWorkIdQuery').setValue('');
	Ext.getCmp('mobileTelQuery').setValue('');
}
function addMenuAccess(){

	var rows=$("#userList").datagrid('getSelections');
	var userId=null;
	if(rows.length>0){
		userId = rows[0].userId;
	}else{
		Ext.Msg.alert("提示","请至少选择一条数据");
		return;
	}
	menuAccessStore2.load({params:{
		userId:userId
	}});
	var menuAccessPanel2P = currentObj.menuAccessPanel2();
	var formWin = new Ext.Window({
		id :'menuFormWindow',
		title : '增加菜单权限',
		width : 300,
		height : 400,
//		closeAction : 'close',//释放窗体内存
		bodyStyle : {
			padding : '5px 0px 0px 0px',
			margins : '5px 0px 0px 0px'
		},
		items:[menuAccessPanel2P],
		buttonAlign : 'center',
		buttons : [
					{
						text : '保存',
						icon : '/IOMPROJ/public/image/确认.png',
						onClick : function() {
							var selinfo = Ext.getCmp('menuAccessGridId2').getSelectionModel()
							.getSelections();
							if(selinfo.length<=0){
								Ext.Msg.alert("提示","请至少选择一条数据");
								return;
							}
							var rows=$("#userList").datagrid('getSelections');
							var userId=null;
							if(rows.length<=0){
								Ext.Msg.alert("提示","未选择用户");
								return;
							}else{
								userId = rows[0].userId;
							}							
							var menuIdArray ='';
							for(var i=0;i<selinfo.length;i++){
								if(i==0){
									menuIdArray = selinfo[i].data.menuId;
								}else{
									menuIdArray += ","+selinfo[i].data.menuId;
								}
							}
							Ext.Ajax.request({
								url : contextPath+"/menu/addUserMenu.do",
								async : false,
								params:{  
									menuIdArray:menuIdArray,
									userId:userId
							    },
							    success : function(response, action) {
									Ext.Msg.alert('成功', Ext.decode(response.responseText).message);
									doQryMenuAccess();
									doCloseWin();
								},
								failure : function(response, action) {
									Ext.Msg.alert('错误', Ext.decode(response.responseText).message);
									doQryMenuAccess();
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
}
function delMenuAccess(){
	var rows=$("#userList").datagrid('getSelections');
	var userId=null;
	if(rows.length>0){
		userId = rows[0].userId;
	}else{
		Ext.Msg.alert("提示","请至少选择一条数据");
		return;
	}
	menuAccessStore3.load({params:{
		userId:userId
	}});
	var menuAccessPanel3P = currentObj.menuAccessPanel3();
	var formWin = new Ext.Window({
		id :'menuFormWindow',
		title : '册除菜单权限',
		width : 300,
		height : 400,
//		closeAction : 'close',//释放窗体内存
		bodyStyle : {
			padding : '5px 0px 0px 0px',
			margins : '5px 0px 0px 0px'
		},
		items:[menuAccessPanel3P],
		buttonAlign : 'center',
		buttons : [
					{
						text : '保存',
						icon : '/IOMPROJ/public/image/确认.png',
						onClick : function() {
							var selinfo = Ext.getCmp('menuAccessGridId3').getSelectionModel()
							.getSelections();
							if(selinfo.length<=0){
								Ext.Msg.alert("提示","请至少选择一条数据");
								return;
							}
							var rows=$("#userList").datagrid('getSelections');
							var userId=null;
							if(rows.length<=0){
								Ext.Msg.alert("提示","未选择用户");
								return;
							}else{
								userId = rows[0].userId;
							}							
							var menuIdArray ='';
							for(var i=0;i<selinfo.length;i++){
								if(i==0){
									menuIdArray = selinfo[i].data.menuId;
								}else{
									menuIdArray += ","+selinfo[i].data.menuId;
								}
							}
							Ext.Ajax.request({
								url : contextPath+"/menu/delUserMenu.do",
								async : false,
								params:{  
									menuIdArray:menuIdArray,
									userId:userId
							    },
							    success : function(response, action) {
									Ext.Msg.alert('成功', Ext.decode(response.responseText).message);
									doQryMenuAccess();
									doCloseWin();
								},
								failure : function(response, action) {
									Ext.Msg.alert('错误', Ext.decode(response.responseText).message);
									doQryMenuAccess();
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
}
function delMenuAccess2(){

//	var c = Ext.getCmp('menuAccessGridId').getSelectionModel();
//	var d = Ext.getCmp('menuAccessGridId').getSelectionModel().getSelected();
	var selinfoDel = Ext.getCmp('menuAccessGridId').getSelectionModel().getSelections();
//	alert(selinfoDel);
//	selinfoDel.ss();
	alert(selinfoDel.length);
	if(selinfoDel.length<=0){
		Ext.Msg.alert("提示","请至少选择一条数据");
		return;
	}
	var rows=$("#userList").datagrid('getSelections');
	var userId=null;
	if(rows.length<=0){
		Ext.Msg.alert("提示","未选择用户");
		return;
	}else{
		userId = rows[0].userId;
	}							
	var menuIdArray ='';
	for(var i=0;i<selinfoDel.length;i++){
		if(i==0){
			menuIdArray = selinfoDel[i].data.menuId;
		}else{
			menuIdArray += ","+selinfoDel[i].data.menuId;
		}
	}
	return;
}
