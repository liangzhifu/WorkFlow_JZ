$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


var Debug = (function(){	
	return {
		alert:function(message){
			//console.info(message);
			alert(message);
		}
	}
})();

var Util = (function(){
	
	return {
		
		
		/**
		 * options.url
		 * options.title
		 * options.rate:弹出窗的比例，默认0.8
		 * options.height:窗口高度
		 * options.width:窗口宽度
		 * options.id: window对象ID
		 * options.iconCls: 图标样式
		 * options.closeEvent	窗口关闭事件
		 */
		openDialog:function(options){
			var dialogId = options && options.id ? options.id :"dialog_" + new Date().getTime();
			var url = options && options.url ? options.url:"";
			var title = options && options.title ? options.title:"系统窗口";
			var rate = options && options.rate ? options.rate:0.8;
			var iconCls = options && options.iconCls ? options.iconCls:'icon-grid';
			
			var dialogWidth= options && options.width ? options.width : $(window).width() * rate;
			var dialogHeight = options && options.height ? options.height : $(window).height() * rate;
			
			var iframe = $("<iframe></iframe>").attr("src", url).attr("height", "100%").attr("width","100%").attr("frameBorder", "0");
			
			 $("<div></div").attr("id", dialogId).append(iframe).window({
				 iconCls:iconCls,
				cache:false,
				width:dialogWidth,
				height:dialogHeight,
				title:title,
				modal: true,
				onClose:options.closeEvent
			});		
		}
		
		/**
		 * 弹出一个窗口，系统将创建一个EasyUI的dialog对象
		 * options:对话框的配置项，包括以下属性
		 * 		options.url:页面的一个地址
		 * 		options.rate:弹出窗在父窗口的比例，默认是0.8
		 * 		options.width: 弹出窗的宽度
		 * 		options.height: 弹出窗的高度
		 * 		options.iconCls: 图标样式
		 * 		options.id: 弹出窗的ID
		 * 		options.title: 弹出窗的标题
		 * 		options.buttons:弹出窗的按钮数组,每个元素的定义是：{iconCls:xxx, text:xxx, handler:fn},例如：buttons:[{iconCls:xxx, text:xxx, handler:fn}]
		 *		options.openDialogObj: 打开弹窗页面的JS对象，用于在弹窗子页面中回调“打开弹窗页面”定义的函数，
		 *								例如：在弹窗子页面的“确定”按钮时，将选中的数据设置回“打开弹窗的页面”。
		 */
		,dialog:function(options){
			var url = options && options.url ? options.url : "";
			var rate = options && options.rate ? options.rate:0.8;
			var dialogWidth= options && options.width ? options.width : $(window).width() * rate;
			var dialogHeight = options && options.height ? options.height : $(window).height() * rate;
			var iconCls = options && options.iconCls ? options.iconCls:'icon-grid';
			var dialogId = options && options.id ? options.id : "dialog_" + new Date().getTime();
			var title = options && options.title ? options.title : "系统窗口";
			var openDialogObj = options && options.openDialogObj ? options.openDialogObj : {};
			
			var dialogOpts = {
				title: title,
				width: dialogWidth,
				height: dialogHeight,
				closed: false,
				cache: false,
				iconCls:iconCls,
				modal: true
				,onClose:function(){
					$(this).dialog('destroy');		
				}
			};
			if(options && options.buttons && options.buttons instanceof Array && options.buttons.length > 0){
				dialogOpts.buttons = options.buttons;
			}
			var iframe = $("<iframe></iframe>").attr("src", url).attr("height", "100%").attr("width","100%").attr("frameBorder", "0");
			
			var dialog = $("<div></div>").attr("id", dialogId).append(iframe).dialog(dialogOpts);
			iframe[0].contentWindow.openDialogObj = openDialogObj;
			return dialog;
		}
	}
})();

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // 月
		"d+" : this.getDate(), // 天
		"h+" : this.getHours(), // 时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 刻
		"S" : this.getMilliseconds() //毫秒
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}