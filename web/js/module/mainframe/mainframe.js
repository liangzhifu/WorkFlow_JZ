//页面加载完成后，初始化页面组件
$(function() {
	mainframeConfig.init();
});

//页面配置对象
var mainframeConfig = (function() {
	var currentObj = null;
	var preModuleId = 1; //保存上一次选择的菜单模块ID
	var subModeleId = 0;
	var loadSubCatalogUrl = contextPath + "/mainframe/getSubCatalog.do";
	return {
		init : function() {
			currentObj = this;
			mainframeConfig.layout();
			$("#catalog1").attr("class", "m01");
			mainframeConfig.initCatalogEvent();
		},
		
		layout:function(){
			var winHeight = $(window).height() - 86; //基于屏幕的高度， 54是头部导航栏的高度
			$("#mainIframe").height(winHeight - 10);
		},
		
		initCatalogEvent:function(){
			$("div[id^='catalog']").bind("click",function(){
				var moduleId = Number($(this).attr("moduleId"));
				$("div[id^='catalog']").attr("class", "m02");
				$(this).attr("class", "m01");
				if(moduleId == preModuleId){
					return;
				}else {//重新加载二级菜单
					preModuleId = moduleId;
					$.post(loadSubCatalogUrl+"?catalogId="+moduleId, function(data){
						$("#divSubCatalog").empty();
						var dataHtml = "";
						for(var i=0;i<data.length;i++){
							var catalog = data[i];
							dataHtml += '<div class="subM02 subc2" id="subCatalog'+catalog.menuId+'" title="'+catalog.menuName+'" moduleId="'+catalog.menuId+'" moduleUrl="'+catalog.menuUrl+'">'+catalog.menuName+'</div>';
						}
						$("#divSubCatalog").append(dataHtml);
						//加载事件
						$("div[id^='subCatalog']").bind("click",function(){
							var moduleId = Number($(this).attr("moduleId"));
							var moduleUrl = $(this).attr("moduleUrl");
							$("div[id^='subCatalog']").attr("class", "subM02 subc2");
							$(this).attr("class", "subM02 subc2 round");
							if(moduleId == subModeleId){
								return;
							}else {//重新加载页面
								subModeleId = moduleId;
								$("#mainIframe").attr("src",moduleUrl);
							}
						});
					});
				}
			});
			$("div[id^='subCatalog']").bind("click",function(){
				var moduleId = Number($(this).attr("moduleId"));
				var moduleUrl = $(this).attr("moduleUrl");
				$("div[id^='subCatalog']").attr("class", "subM02 subc2");
				$(this).attr("class", "subM02 subc2 round");
				if(moduleId == subModeleId){
					return;
				}else {//重新加载页面
					subModeleId = moduleId;
					$("#mainIframe").attr("src",moduleUrl);
				}
			});
			$("div[id='div_exit']").bind("click",function(){
				$.post("loginOut.do",null,function(result){
					window.location.href = contextPath + "/login.do";;
				});
			});
			$("div[id='div_password']").bind("click",function(){
				window.showModalDialog(contextPath + "/jsp/system/newPassword.jsp", null, "dialogHeight=170px;dialogwidth=402px;help=no;scrollbars=no;");
			});
		}
	};
})();