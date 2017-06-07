var subReport = function(){
   var treePanelMap = new Ext.util.MixedCollection();
   var dynaicFormMap = new Ext.util.MixedCollection();
   var dynaicGridMap = new Ext.util.MixedCollection();
   var groupNodeId = [54];
   var hashMap = new Ext.util.MixedCollection();
   hashMap.addAll(groupNodeId);
   var dynaicGridStoreMap = new Ext.util.MixedCollection();
   var _nodeId = null;
   var _templateName = null;
   var session = GetSession();
   return {
      init:function(){
	      var viewport = new Ext.Viewport({
		        layout: 'border',
				items: [
					{
					region: 'center', 
					xtype: 'panel', 
					//split: false,
					margins: '5 5 5 0',
					minWidth: 400,
					minHeight: 400,
					//layout: 'border',
					autoScroll:true,
					//autoHeight:true,
					items:[
						subReport.initQueryParam()
					
						]
				}]
		  });
		  pageMaskAll.hide();
	  },
	  getStore : function(menuId){
		   var temp_obj = dynaicGridStoreMap.get(menuId);
		   if(!temp_obj){
		         temp_obj = new Ext.data.JsonStore({
						url:'/IOMPROJ/iomextservlet',
						idProperty: "id",
						menuId:menuId,
						isAutoConfigColumn:true
		         });	
				 dynaicGridStoreMap.add(menuId,temp_obj);
		   }
		   return temp_obj;
	  },
	  getGroupFieldsAndColumns:function(nodeId){
	  },
	  queryKpi:function(){
		     var isNull =  Ext.getCmp("queryParam_Form").form.validateNotNull();
			 if (!isNull) {
    			return;
    		}
			dataMask.show();
           	Ext.defer(function(){
				 var param_json = Ext.getCmp("queryParam_Form").form.getValuesOld();
				 Ext.getCmp("queryParam_Form").form.items.each(function(obj){
					   if(obj.xtype == "combo"){
						  param_json[obj.name] = obj.getValue();
					   }
				 });
				 param_json.nodeId = _nodeId;
				 param_json.templateName = _templateName;
				 param_json = "[" + Ext.encode(param_json) + "]";
				 var returnValue = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","generateQuerySql",false,param_json);
				 if(_nodeId==54){
					  if(returnValue != undefined){
							Ext.getCmp("grid2").getStore().loadData(returnValue);
					  }
				 }else{
					  if(returnValue != undefined){
							Ext.getCmp("grid1").getStore().loadData(returnValue);
					  }
				 }
				 dataMask.hide();
			 },10);
	  },
	  resetForm:function(){
	      Ext.getCmp("queryParam_Form").form.reset();
	  },
	  getFieldsAndColumns : function(){
			 var columns = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BandBuzManager","getColumns",false,arguments[0]); 
		     var obj = new Object();
			 var fields = [];
		     var gridColumn = [];
		     for(var i=0;i<columns.length;i++){
		    	 var temp = {};
		    	 var temp2 = {};
				 var fn = null;
		    	 temp.name = columns[i]["code"];
		    	 temp2.hidden  = columns[i]["isHide"]==0?false:true;
				 temp2.dataIndex = columns[i]["code"];
                 temp2.width = columns[i]["width"];
				 temp2.sortable = true;
		    	 temp2.header = columns[i]["name"];
				 temp2.align = "center";
				 temp2.id = columns[i]["id"];
				 if(columns[i]["renderer"]){
				      fn = new Function("value","metadata","css","attr","record","rowIndex","colIndex","store",columns[i]["renderer"]);
					  
					  temp2.renderer  = fn;
				 }
				 if(columns[i]["property"]){
				      var propertys = columns[i]["property"].split(";");
					  var temp_property = "";
					  for (var k = 0; k < propertys.length; k++) {
							if (propertys[k]) {
								temp_property = propertys[k].split(":");
								temp2[temp_property[0]] = temp_property[1];
							}
					  }
				 }
				 gridColumn.push(temp2);
		    	 fields.push(temp);
		     }
			obj.fields = fields;
			obj.column = gridColumn;
			return obj;
		},

	  generateCommonGrid :function(node_id){
		 
		 var dynaicGrid = null;
		 var temp_store =  new Ext.data.JsonStore({
									url:'/IOMPROJ/iomextservlet',
									idProperty: "circuitId",
									menuId:node_id,
									isAutoConfigColumn:true
				});
		 
		  dynaicGrid = new Ext.grid.GridPanel({
					store: temp_store,
					menuId:node_id,
					isAutoConfigColumn:true,
					border:false,
					enableHdMenu:false,
					autoScroll :true,
					//loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
					//height:document.body.clientHeight/3,
					height:'90%',
					//width:document.body.clientWidth-210,
					width:'100%',
					viewConfig: {
							//forceFit: true,
							//autoFill:true,
							sortAscText:'升序',
							sortDescText : '降序',
							columnsText : '列名',
							scrollOffset: 0
					},
					id:node_id+'grid1'
		  });

		 return dynaicGrid ;
	  },
	  
	  initContentPanel:function(node_id){

		return subReport.generateCommonGrid(node_id);
		
	  },

	  initQueryParam:function(){
	       var formPanel = new Ext.Panel({
	       		id:'resultGrid',
				width:'100%',
				autoScroll:true,
				autoHeight:true,
				layout: 'auto',
				items:[]
			});
			
			
			for(var i=0; i<_subItemMap.length; i++){
				
				var reprotName = _subItemMap[i].alisText;           //子菜单名称
				var menu_id = _subItemMap[i].id ;                     //代表子菜单ID
				var gridHeight = screen.availHeight/_subItemMap.length; // 菜单个数
			
				var item = {
				   		xtype:'panel',
                        title: reprotName,
						width:'100%',
						height:gridHeight,
						layout:'fit',
						items:[subReport.initContentPanel(menu_id)]
				   };
				formPanel.add(item);
				
			}
			
			
			return formPanel;
	  },
	  doQuery:function(){
	  	
	  	
	    for(var i=0; i<_subItemMap.length; i++){
	    	
	    	var param = param_json;
	    	param.templateName = _subItemMap[i].src;   //子菜单查询模板
			param.myOrgId = session.org.orgId;
			param.curStaffArea = session.area.areaId ;
	    	param = "[" + Ext.encode(param) + "]";
			var returnValue = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","generateQuerySql",false,param);
			if(returnValue != undefined){
				Ext.getCmp(_subItemMap[i].id+'grid1').getStore().loadData(returnValue);
			}
	    
	    }
	  
	    
	 }
	  
	};
	
	
   
}();