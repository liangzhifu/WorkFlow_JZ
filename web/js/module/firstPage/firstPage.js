var firstPageManage = (function(){
	
	var currentObj;
	
	return {
		init : function (){
			currentObj = this;
			currentObj.initMainPanel();
		},
		
		initMainPanel:function(){
			var containerHeight = $("#bodyContent").height() - 2;
			$('#firstPageDiv').panel({
				width:'100%',
			    height:containerHeight,
			    title:"",
			    collapsible:true
			});
		},
	}
})();