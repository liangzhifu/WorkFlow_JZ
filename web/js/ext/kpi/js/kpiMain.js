var treePanelArray=new Array();
var kpiMain = function(){
   var treePanelMap = new Ext.util.MixedCollection();
   var dynaicFormMap = new Ext.util.MixedCollection();
   var dynaicGridMap = new Ext.util.MixedCollection();
   var groupNodeId = [54];
   var hashMap = new Ext.util.MixedCollection();
   hashMap.addAll(groupNodeId);
   var dynaicGridStoreMap = new Ext.util.MixedCollection();
   var _nodeId = null;
   var _templateName = null;
   var _nodeName = null;
   var _excelTemplateName = null;
   var _requestObj = null;
   var session = GetSession();
   return {
      init:function(requestObj){
		  _requestObj = requestObj;
	      var viewport = new Ext.Viewport({
		        layout: 'border',
				items: [{
					region: 'center', 
					id:"contentPanel",
					xtype: 'panel', 
					split: false,
					margins: '5 5 5 0',
					layout: 'fit'//,
					//items:[kpiMain.initQueryParam()]
				}]
		  });
		  var obj = {};
		  obj.staffId = session.staff.staffId;
		  obj.privateCodes = _requestObj["privCode"];
		  obj.jobId = session.job.jobId;
		  obj.orgId = session.org.orgId;
		  var node = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BandBuzManager","getTreeNodeByPriv",false,obj);
		  if(node.length>0){
			kpiMain.loadPage(node[0]);
		  }
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
				 param_json.myOrgId = session.org.orgId;
				 param_json.curStaffArea = session.area.areaId ;
				 param_json = "[" + Ext.encode(param_json) + "]";
				 //alert(param_json);
				 var returnValue = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","generateQuerySql",false,param_json);
				 if(_nodeId==156 || _nodeId==155 || _nodeId==165){
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
	  
	  lookComments:function(){
	    var url = "reportComments.jsp" ;
	  	window.open(url+"?nodeId="+_nodeId);
	  },
	  
	  
	  resetForm:function(){
	      Ext.getCmp("queryParam_Form").form.reset();
	  },
	  exportExcel:function(){
		var isNull =  Ext.getCmp("queryParam_Form").form.validateNotNull();
			 if (!isNull) {
    			return;
    	}
		dataMask.show();
   		var url = '/IOMPROJ/ExcelExportServlet';
   		var param_json = Ext.getCmp("queryParam_Form").form.getValuesOld();
		Ext.getCmp("queryParam_Form").form.items.each(function(obj){
			if(obj.xtype == "combo"){
				param_json[obj.name] = obj.getValue();
			}
		});
		
		
		//判断是否省分工号 省分工号进入特殊处理
		var params = new Object();
		params['myOrgId'] = session.org.orgId;
		params['curStaffArea'] = session.area.areaId ;
		params = "[" + Ext.encode(params) + "]";
		var retVal = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","findTopAreaId",false,params);
		
		
		if(retVal[0] == false){
			
			param_json.myOrgId = session.org.orgId;
			param_json.myAreaId = retVal[1] ;
			param_json.curStaffArea = retVal[1] ;
			var sqlVal =  Ext.encode(param_json);
			sqlVal = encodeURI(encodeURI(sqlVal));
			//var paramstr = 'templateCode='+_excelTemplateName+'_prov&fileName='+_nodeName+'&sqlParams='+sqlVal;
			var paramstr = 'templateCode='+_excelTemplateName+'&fileName='+encodeURI(encodeURI(_nodeName))+'&sqlParams='+sqlVal;
	    	window.open(url+'?'+paramstr);
	    	dataMask.hide();
		
		}else{
			
			param_json.myOrgId = session.org.orgId;
			param_json.curStaffArea = session.area.areaId ;
			var sqlVal =  Ext.encode(param_json);
			sqlVal = encodeURI(encodeURI(sqlVal));
			//sqlVal = sqlVal.replace(new RegExp("\"","gm"),"");
			var paramstr = 'templateCode='+_excelTemplateName+'&fileName='+encodeURI(encodeURI(_nodeName))+'&sqlParams='+sqlVal;
	    	window.open(url+'?'+paramstr);
	    	dataMask.hide();
		}
		
		
	    
	   
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
	  generateGroupGrid :function(nodeId){
		  var temp_store = null;
		  var dynaicGrid = null;
		  if(!dynaicGridStoreMap.get(nodeId)){
		       temp_store = new Ext.data.JsonStore({
					url:'/IOMPROJ/iomextservlet',
					idProperty: "id",
					menuId:nodeId,
					isAutoConfigColumn:true
				});
			    dynaicGridStoreMap.add(nodeId,temp_store);
		  }else{
		      temp_store =  dynaicGridStoreMap.get(nodeId);
			  dynaicGridStoreMap.remove(temp_store);
			   temp_store = new Ext.data.JsonStore({
					url:'/IOMPROJ/iomextservlet',
					idProperty: "id",
					menuId:nodeId,
					isAutoConfigColumn:true
				});
				dynaicGridStoreMap.add(nodeId,temp_store);
		  }
		  
		  if(!dynaicGridMap.get(nodeId)){
		  		var group ;
				if(nodeId == 156){
					var row1 = [{},{},{header:'开放电路数量',colspan:3,align:'center'},{header:'超时电路数量',colspan:3,align:'center'}];
				  	group = new Ext.extendColumnHeaderGroup({rows: [row1]});
				}else if(nodeId == 155){
					var row1 = [{},{header:'新开',colspan:3,align:'center'},{header:'调整',colspan:3,align:'center'},{header:'关闭',colspan:3,align:'center'}];
				  	group = new Ext.extendColumnHeaderGroup({rows: [row1]});
				}else if(nodeId == 165){
					var row1 = [{},{},{header:'正常调单',colspan:10,align:'center'},{header:'异常单',colspan:10,align:'center'}];
				  	group = new Ext.extendColumnHeaderGroup({rows: [row1]});
				}
			      
				  dynaicGrid = new Ext.grid.GridPanel({
						store: temp_store,
						menuId:nodeId,
						isAutoConfigColumn:true,
						autoScroll :true,
						border:false,
						enableHdMenu:false,
						loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
						//height:document.body.clientHeight*0.76,
						height:document.body.clientHeight-Ext.getCmp("queryParam").getHeight()-45,
						width:document.body.clientWidth-210,
						viewConfig: {
									sortAscText:'升序',
									sortDescText : '降序',
									columnsText : '列名',
									scrollOffset: 0
						},
						id:'grid2',
						plugins: group
				 });  
		  }else{

			 var temp = kpiMain.getFieldsAndColumns(nodeId);
			 dynaicGrid = dynaicGridMap.get(nodeId);
			 
			 var group ;
			 if(nodeId == 156){
				var row1 = [{},{},{header:'开放电路数量',colspan:3,align:'center'},{header:'超时电路数量',colspan:3,align:'center'}];
				group = new Ext.extendColumnHeaderGroup({rows: [row1]});
			 }else if(nodeId == 155){
				var row1 = [{},{header:'新开',colspan:3,align:'center'},{header:'调整',colspan:3,align:'center'},{header:'关闭',colspan:3,align:'center'}];
				group = new Ext.extendColumnHeaderGroup({rows: [row1]});
			 }else if(nodeId == 165){
				var row1 = [{},{},{header:'正常调单',colspan:10,align:'center'},{header:'异常单',colspan:10,align:'center'}];
				group = new Ext.extendColumnHeaderGroup({rows: [row1]});
			 }
			
			
			 dynaicGridMap.remove(dynaicGrid);

				  dynaicGrid = new Ext.grid.GridPanel({
						store: temp_store,
						menuId:nodeId,
						isAutoConfigColumn:true,
						border:false,
						enableHdMenu:false,
						autoScroll :true,
						loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
						//height:document.body.clientHeight*0.76,
						height:document.body.clientHeight-Ext.getCmp("queryParam").getHeight()-45,
						width:document.body.clientWidth-210,
						viewConfig: {
									sortAscText:'升序',
									sortDescText : '降序',
									columnsText : '列名',
									scrollOffset: 0
						},
						id:'grid2',
						plugins: group
				 });  
		  }
		return dynaicGrid;
	  },
	  generateCommonGrid :function(node_id){
		  var temp_store = null;
		  var dynaicGrid = null;
	      if(!dynaicGridStoreMap.get(node_id)){
				temp_store =  new Ext.data.JsonStore({
									url:'/IOMPROJ/iomextservlet',
									idProperty: "circuitId",
									menuId:node_id,
									isAutoConfigColumn:true
				});
				dynaicGridStoreMap.add(node_id,temp_store);
		  }else{
		       temp_store = dynaicGridStoreMap.get(node_id);
				dynaicGridStoreMap.remove(temp_store);
			   temp_store =  new Ext.data.JsonStore({
									url:'/IOMPROJ/iomextservlet',
									idProperty: "circuitId",
									menuId:node_id,
									isAutoConfigColumn:true
				});
				dynaicGridStoreMap.add(node_id,temp_store);
		  }
          if(!dynaicGridMap.get(node_id)){
			 dynaicGrid = new Ext.grid.GridPanel({
						store: temp_store,
						menuId:node_id,
						isAutoConfigColumn:true,
						border:false,
						autoScroll :true,
						enableHdMenu:false,
						loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
						//height:document.body.clientHeight*0.76,
						height:document.body.clientHeight-Ext.getCmp("queryParam").getHeight()-45,
						width:document.body.clientWidth-10,
						viewConfig: {
								//forceFit: true,
								//autoFill:true,
								sortAscText:'升序',
								sortDescText : '降序',
								columnsText : '列名',
								scrollOffset: 0
						},
						id:'grid1'
			  });
			  dynaicGridMap.add(node_id,dynaicGrid);
			  Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
			  dynaicGrid.render("queryResult1");
		 }else{
			 var data_temp = Ext.decode("{results:0,rows:[]}");
		     dynaicGrid = dynaicGridMap.get(node_id);
			 var temp = kpiMain.getFieldsAndColumns(node_id);
			 temp_store = dynaicGridStoreMap.get(node_id);
			 //alert(Ext.encode(dynaicGrid));
			 //temp_store.loadData(data_temp);
             //dynaicGrid.reconfigure(temp_store,new Ext.grid.ColumnModel({columns:temp.column}));
			 //dynaicGrid.doLayout(true);
			 //temp_store.reload();
			 dynaicGridMap.remove(dynaicGrid);
			  dynaicGrid = new Ext.grid.GridPanel({
						store: temp_store,
						menuId:node_id,
						isAutoConfigColumn:true,
						border:false,
						enableHdMenu:false,
						autoScroll :true,
						loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
						//height:document.body.clientHeight*0.76,
						height:document.body.clientHeight-Ext.getCmp("queryParam").getHeight()-45,
						width:document.body.clientWidth-210,
						viewConfig: {
								//forceFit: true,
								//autoFill:true,
								sortAscText:'升序',
								sortDescText : '降序',
								columnsText : '列名',
								scrollOffset: 0
						},
						id:'grid1'
			  });
			  dynaicGridMap.add(node_id,dynaicGrid);			  
			  Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
			  dynaicGrid.render("queryResult1");
			  dynaicGrid.getStore().loadData([]);
		 }
		 //return dynaicGrid;
	  },
		initTreePanel:function(i){
			var treePanel = treePanelMap.get(i);
			if(!treePanel){
				treePanel = new Ext.tree.TreePanel({
				    animate:true,
					autoScroll:true,
					loader: new Ext.tree.TreeLoader({
							url:'/IOMPROJ/iomextservlet',
							listeners:{
							    load:function(load,treeNode,response){
									   //load default tree node
								       /**var node_array = treeNode.childNodes;
									   for(var i=0;i<node_array.length;i++){
									        if(node_array[i].attributes.isDefault=="Y"){
											       node_array[i].select();
												   node_array[i].fireEvent("click",node_array[i]);
											}
									   }**/
								}
							}
					}),
					containerScroll: true,
					border: false,
					height: document.body.clientHeight-100, 
					width: 200,
					lines : true,
					id:'treePanelItem'+i,
					rootVisible: false,
					root: {
						expanded: false,
						nodeType: 'async',
						text: i,
						draggable: false,
						id: "id"+i
					},
					listeners:{
						beforeload:function(node){
							   var obj = new Object();
							   obj.id = node.id.substr(2);
							   obj.text = node.text;
							   obj.staffId = session.staff.staffId;
							   obj.jobId = session.job.jobId;
							   obj.orgId = session.org.orgId;
							   var loader = this.getLoader();
							   var xmlValue = toXml("com.ztesoft.oss.reportconifg.bl.BandBuzManager","getTreeNode",false,obj);
							   loader.baseParams = {xml:xmlValue};
						},
						
						click:function(node,event){
							var src = node.attributes.src;
							var id = node.attributes.id;
							var menu_name = node.attributes.alisText ;
							_nodeId = id;
							_nodeName = menu_name;
							_templateName = node.attributes.src;
							_excelTemplateName = node.attributes.excelTemplate;
							
							if(src.indexOf('.jsp')>0){
								var pnNorth=new Ext.Panel({
									id:'pnNorth',
									width:'100%',
									heigth:'100%',
									html:'<iframe src=\"'+ src +'\" width="100%" height="100%" marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>'
								});
								Ext.getCmp("contentPanel").removeAll();
								Ext.getCmp("contentPanel").add(pnNorth) ;
								Ext.getCmp("contentPanel").doLayout(true);
							}else{
								Ext.getCmp("contentPanel").removeAll();
								var panel = kpiMain.initQueryParam();
								Ext.getCmp("contentPanel").add(panel) ;
								Ext.getCmp("contentPanel").doLayout(true);
								Ext.getCmp("queryParam").setTitle(menu_name);

								var dynaicForm ;
								var dynaicGrid = dynaicGridMap.get(_nodeId);
								var dynaicStore = dynaicGridStoreMap.get(_nodeId);

								dynaicForm = new Ext.form.DynicForm({
											id: 'queryParam_Form',
											autoScroll:true,
											autoHeight:true,
											border:false,
											frame : false,
											labelWidth:100,
											menuId:_nodeId,
											menuType:'',
											labelAlign:'right',
											buttonAlign : "center",
											buttons:[{text:'查询',listeners:{click:kpiMain.queryKpi}},{text:'导出',listeners:{click:kpiMain.exportExcel}},{text:'说明',listeners:{click:kpiMain.lookComments}}]
										});

								dynaicForm.render("queryParam");
								pageMaskAll.show();
           						Ext.defer(function(){
									if(_nodeId == 165 || _nodeId == 156 || _nodeId == 155){				
										dynaicGrid = kpiMain.generateGroupGrid(_nodeId);
										Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
										dynaicGrid.render("queryResult1");
										dynaicGrid.getStore().loadData([]);
									}else{
										//dynaicGrid = kpiMain.generateCommonGrid(node_id);
										//Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
										//dynaicGrid.render("queryResult1");
										kpiMain.generateCommonGrid(_nodeId);
									}
									pageMaskAll.hide();
								},10);
							}
						}
					}
				});
			  treePanelMap.add(i,treePanel);
			  treePanelArray.push('treePanelItem'+i);
		   }
		   return treePanel;
	  },
	  TabPanelClick:function(panel){
	      //alert(_nodeId);
	  },
	  initTabPanel:function(){
	          var temp_array = [];
			  var temp_obj;
			  var temp_value = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BandBuzManager","getTabPanel",false,932);
			  for(var i=0;i<temp_value.length;i++){
				  temp_obj = {};
				  var catalogId = parseInt(temp_value[i]["id"].substr(5));
				  temp_obj["title"] = temp_value[i]["title"];
				  temp_obj["items"] = kpiMain.initTreePanel(catalogId);
				  temp_obj["border"] = false;
				  temp_obj["style"] = "padding-top:1px;";
				  temp_obj["iconCls"] = "panelHeader";
				  temp_obj["listeners"] = {'expand':kpiMain.TabPanelClick};
				  temp_array.push(temp_obj);
			  }
			  return temp_array;
	  },
	  initPanel:function(){
	        var accordion = new Ext.Panel({
				id: 'treePanel',
				region: 'west',
				margins:'5 0 5 5',
                width:200,
                layout:'accordion',
                items:[kpiMain.initTabPanel()]
            });
			return accordion;
	  },
	  initMenu:function(){
	     
	  },
	  initQueryParam:function(){
	       var formPanel = new Ext.Panel({
				labelAlgin:'right',
				labelWidth:80,
				border:false,
				frame:false,
				layout:'column',
				items:[{
				        title: '统计条件',
						collapsible: false,
						border:false,
						id:'queryParam',
						layout:'column',
						html:'<div id="queryParam"></div>'
				   },{
                        title: '查询结果',
						collapsible: false,
						id:'queryResult',
						border:false,
						layout:'fit',
						html:'<div id="queryResult1"></div>'
				   }
				   
				]
			});
			return formPanel;
	  },
	  loadPage:function(node){
			var src = node.src;
			var id = node.id;
			var menu_name = node.alisText ;
			_nodeId = id;
			_nodeName = menu_name;
			_templateName = node.src;
			_excelTemplateName = node.excelTemplate;
			var srcType = _requestObj["srcType"];
			if(typeof(srcType)!='undefined'&&srcType!=null&&srcType=='url'){
				src = _requestObj["src"];
				if(!src){
					alert('菜单参数需要设置URL！');
				}
				var pnNorth=new Ext.Panel({
					id:'pnNorth',
					width:'100%',
					heigth:'100%',
					html:'<iframe src=\"'+ src +'\" width="100%" height="100%" marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>'
				});
				Ext.getCmp("contentPanel").removeAll();
				Ext.getCmp("contentPanel").add(pnNorth) ;
				Ext.getCmp("contentPanel").doLayout(true);
			}else{
				Ext.getCmp("contentPanel").removeAll();
				var panel = kpiMain.initQueryParam();
				Ext.getCmp("contentPanel").add(panel) ;
				Ext.getCmp("contentPanel").doLayout(true);
				Ext.getCmp("queryParam").setTitle(menu_name);

				var dynaicForm ;
				var dynaicGrid = dynaicGridMap.get(_nodeId);
				var dynaicStore = dynaicGridStoreMap.get(_nodeId);

				dynaicForm = new Ext.form.DynicForm({
					id: 'queryParam_Form',
					autoScroll:true,
					autoHeight:true,
					border:false,
					frame : false,
					labelWidth:100,
					menuId:_nodeId,
					menuType:'',
					labelAlign:'right',
					buttonAlign : "center",
					buttons:[{text:'查询',listeners:{click:kpiMain.queryKpi}},{text:'导出',listeners:{click:kpiMain.exportExcel}},{text:'说明',listeners:{click:kpiMain.lookComments}}]
				});

				dynaicForm.render("queryParam");
				pageMaskAll.show();
				Ext.defer(function(){
					if(_nodeId == 165 || _nodeId == 156 || _nodeId == 155){				
						dynaicGrid = kpiMain.generateGroupGrid(_nodeId);
						Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
						dynaicGrid.render("queryResult1");
						dynaicGrid.getStore().loadData([]);
					}else{
						kpiMain.generateCommonGrid(_nodeId);
					}
					pageMaskAll.hide();
				},10);
			}			
	  }
   }
}();

Ext.EventManager.onWindowResize(function(){  
	for(var i=0;i<treePanelArray.length;i++){	 
	    Ext.getCmp(treePanelArray[i]).setHeight(document.body.clientHeight-100); 
	}
});

