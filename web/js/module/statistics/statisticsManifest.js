//页面加载完成后，初始化页面组件
Ext.onReady(function() {
	var mainPanel = statisticsManifest.initMainPanel();
	    
    var viewport = new Ext.Viewport({
        el:'mainDiv',
        layout: 'border',
        items:[mainPanel]
    });
    doQry();
});

//页面配置对象
var statisticsManifest = (function() {
	
	return {
		
		initMainPanel : function(){
			var mainPanel = new Ext.Panel({
				id : "mainDataGrid",
				region : 'center',
	            layout:'table',
	            height : Ext.getBody().getSize().height,
 	            width : Ext.getBody().getSize().width-2,
 	            buttonAlign: "center",
 	            layoutConfig: {   
 	            	columns: 3   
 	            }, 
	            defaults: {
	            	frame : true,
	            	height : 30,
	            	width : (Ext.getBody().getSize().width-2) / 3,
	            	style: "border:1px solid gray;border-collapse:collapse;margin:0 auto;text-align:center;"
	            },
	            items: [
	                    {html:"<font style='font-size:45px'>科室</font>",colspan : 1, rowspan : 1, height : 60}, 
	                    {html:"<font style='font-size:45px'>确认项目</font>", height : 60},
	                    {html:"<font style='font-size:45px'>数量</font>", height : 60},
	                    {html:"<div style='line-height:85px;'><font style='font-size:20px'>制造管理科</font></div>", rowspan : 3, height : 90},
	                    {html:"<font style='font-size:20px'>始终件更改</font>"},
	                    {html:"<span id='param_86'></span>"},
	                    {html:"<font style='font-size:20px'>作业指导书更改</font>"},
	                    {html:"<span id='param_87'></span>"},
	                    {html:"<font style='font-size:20px'>零件搬运表更改</font>"},
	                    {html:"<span id='param_88'></span>"},
	                    {html:"<div style='line-height:85px;'><font style='font-size:20px'>设备管理（AB）</font></div>", rowspan : 3, height : 90},
	                    {html:"<font style='font-size:20px'>PFMEA作成/更新</font>"},
	                    {html:"<span id='param_95'></span>"},
	                    {html:"<font style='font-size:20px'>设备参数/程序更改</font>"},
	                    {html:"<span id='param_96'></span>"},
	                    {html:"<font style='font-size:20px'>流程图作成/更新</font>"},
	                    {html:"<span id='param_97'></span>"},
	                    {html:"<div style='line-height:85px;'><font style='font-size:20px'>设备管理（SB&SW）</font></div>", rowspan : 3, height : 90},
	                    {html:"<font style='font-size:20px'>PFMEA作成/更新</font>"},
	                    {html:"<span id='param_104'></span>"},
	                    {html:"<font style='font-size:20px'>设备参数/程序更改</font>"},
	                    {html:"<span id='param_105'></span>"},
	                    {html:"<font style='font-size:20px'>流程图作成/更新</font>"},
	                    {html:"<span id='param_106'></span>"},
	                    {html:"<div style='line-height:55px;'><font style='font-size:20px'>客户品质科</font></div>", rowspan : 2, height : 60},
	                    {html:"<font style='font-size:20px'>C/P作成/更新</font>"},
	                    {html:"<span id='param_122'></span>"},
	                    {html:"<font style='font-size:20px'>检查基准书作成/更新</font>"},
	                    {html:"<span id='param_123'></span>"},
	                    {html:"<div style='line-height:55px;'><font style='font-size:20px'>品质检查科</font></div>", rowspan : 2, height : 60},
	                    {html:"<font style='font-size:20px'>检查要领书作成/更新</font>"},
	                    {html:"<span id='param_130'></span>"},
	                    {html:"<font style='font-size:20px'>检查成绩书作成/更新</font>"},
	                    {html:"<span id='param_131'></span>"}
	                    ]
			});       

			return mainPanel;
		}
		
	};
})();

function doQry(){
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",86,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_86").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(86);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",87,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_87").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(87);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",88,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_88").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(88);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",95,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_95").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(95);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",96,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_96").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(96);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",97,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_97").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(97);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",104,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_104").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(104);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",105,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_105").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(105);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",106,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_106").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(106);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",122,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_122").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(122);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",123,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_123").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(123);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",130,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_130").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(130);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
	Ext.Ajax.request({
		url : contextPath+'/statisticsManifest/queryStatisticsManifestCount.do',
		params : {
			param : ",131,"
		},
		success : function(response, action) {
			var obj = Ext.decode(response.responseText);
			if(obj.success){
				document.getElementById("param_131").innerHTML='<font style="font-size:20px"><a href="javascript:void(0)" onclick="showCreateWin(131);">&nbsp;&nbsp;&nbsp;'+obj.count+'&nbsp;&nbsp;&nbsp;</a></font>';
			}
		},
		failure : function() {
		}
	});
}

function showCreateWin(param){
	var proxy = new Ext.data.HttpProxy({
        url: contextPath + "/statisticsManifest/queryManifestOrderJson.do",
        method: 'POST'
    });

    var reader = new Ext.data.JsonReader(
    { root: 'rows', totalProperty: 'total' },
        [
         	{ name: 'orderId' },
         	{ name: 'changeContent' },
            { name: 'issueDate' },
            { name: 'publishCode' },
            { name: 'productionLine' },
            { name: 'carType' },
            { name: 'mountingMat' },
            { name: 'changeTime' },
            { name: 'orderStateCode' }
        ]
    );

    var store = new Ext.data.Store(
        { proxy: proxy, reader: reader }
    );
    
    var sm = new Ext.grid.CheckboxSelectionModel();
    
	var cm = new Ext.grid.ColumnModel([ 
	{
		header : "order_id",
		dataIndex : "orderId",
		hidden : true
	}, {
		header : "变更内容",
		dataIndex : "changeContent",
		width : Ext.getBody().getSize().width * 0.2,
	    renderer: function(value, meta, record) {    
	         meta.attr = 'style="white-space:normal;"';     
	         return value;     
	    }
	}, {
		header : "变更时间",
		dataIndex : "changeTime",
		width : Ext.getBody().getSize().width * 0.1
	}, {
		header : "发行日期",
		dataIndex : "issueDate",
		width : Ext.getBody().getSize().width * 0.06
	}, {
		header : "发行编号",
		dataIndex : "publishCode",
		width : Ext.getBody().getSize().width * 0.1
	}, {
		header : "生产线",
		dataIndex : "productionLine",
		width : Ext.getBody().getSize().width * 0.1
	}, {
		header : "车种名",
		dataIndex : "carType",
		width : Ext.getBody().getSize().width * 0.1
	}, {
		header : "安装席",
		dataIndex : "mountingMat",
		width : Ext.getBody().getSize().width * 0.1
	}, {
		header : "定单状态",
		dataIndex : "orderStateCode",
		width : Ext.getBody().getSize().width * 0.1,
		renderer:function(value){
			if(value=="10A"){
				return "初始化";
			}else if(value=="10B"){
				return "处理中";
			}else if(value=="10C"){
				return "完成";
			}else if(value=="10X"){
				return "作废";
			}else {
				return "未知";
			}
		}
	}]);
	var mainGrid = new Ext.grid.GridPanel({
		id : "dataGrid",
		region : 'south',
		cm : cm,
		height: 300,
		loadMask : true,
		// 超过长度带自动滚动条
		autoScroll : true,
		store : store
	});       
    store.load({ params: {
		param : param
    }});
    
	win = new Ext.Window({
	    id: "myWin",
	    title: "统计清单",
	    width: document.body.clientWidth-100,
	    height: 450,
	    layout: "fit",
	    modal : true,
	    autoShow: true,
	    closeAction: 'close',
	    items:mainGrid
	});
	win.show();
}
		