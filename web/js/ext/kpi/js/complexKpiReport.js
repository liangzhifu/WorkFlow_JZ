var complexKpiReport = function(){
   var treePanelMap = new Ext.util.MixedCollection();
   var dynaicFormMap = new Ext.util.MixedCollection();
   var dynaicGridMap = new Ext.util.MixedCollection();
   var groupNodeId = [54];
   var hashMap = new Ext.util.MixedCollection();
   hashMap.addAll(groupNodeId);
   var dynaicGridStoreMap = new Ext.util.MixedCollection();
   var _nodeId = null;
   var _templateName = null;
   var _retGrid = null; 
   var session = GetSession();
   
   return {
      init:function(){
	      var formPanel = new Ext.Panel({
	      		renderTo: Ext.getBody(),
				labelAlgin:'right',
				labelWidth:80,
				border:false,
				width:'100%',
				frame:false,
				layout:'auto',
				items:[{
				        title: node_name,
						collapsible: false,
						border:false,
						id:'queryParam',
						width:'100%',
						layout:'form',
						html:'<div id="queryParam"></div>'
				   },{
						collapsible: false,
						id:'queryResult',
						border:false,
						layout:'auto',
						html:'<div id="queryResult1"></div>'
				   }
				   
				]
			});
			
			var dynaicForm = new Ext.form.DynicForm({
											id: 'queryParam_Form',
											//autoScroll:true,
											//autoHeight:true,
											width:'100%',
											
											border:false,
											frame : false,
											labelWidth:100,
											menuId:node_id,
											menuType:'',
											labelAlign:'right',
											buttonAlign : "center",
											buttons:[{text:'查询',listeners:{click:complexKpiReport.doQuery}},{text:'导出',listeners:{click:complexKpiReport.exportExcel}},{text:'说明',listeners:{click:complexKpiReport.lookComments}}]
			                           	});
			 dynaicForm.render("queryParam");
			
			 
			 
			 var gridPanel = new Ext.Panel({
	       		id:'resultGrid',
				//width:'100%',
				autoScroll:true,
				autoHeight:true,
				layout: 'auto',
				items:[]
			});
			
			
			
			
			 for(var i=0; i<_comboItemMap.length; i++){
				
				var reprotName = _comboItemMap[i].alisText;           //子菜单名称
				var menu_id = _comboItemMap[i].id ;                     //代表子菜单ID
				var gridHeight = (screen.availHeight)/_comboItemMap.length; // 菜单个数
				if(menu_id == 162||menu_id ==1019){
					gridHeight = 200;
				}else if(menu_id == 163||menu_id ==1020){
					gridHeight = 300;
				}else if(menu_id == 160||menu_id ==1022){
					gridHeight = 300;
				}else if(menu_id == 161||menu_id ==1023){
					gridHeight = 200;
				}else if(menu_id == 196||menu_id ==1362){
					gridHeight = 200;
				}else if(menu_id =  199||menu_id ==1365){
					gridHeight = 400;
				}
				
			
				var item = new Ext.Panel({
				   		xtype:'panel',
                        title: reprotName,
						width:'100%',
						height:gridHeight,
						autoScroll:true,
						layout:'fit',
						items:[complexKpiReport.initContentPanel(menu_id)]
						
						
				   });
				gridPanel.add(item);
				
			}
			
			Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
			gridPanel.render("queryResult1");
			
			
			
			
			pageMaskAll.hide();
			//return formPanel;
			
			
		 
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
	  
	  lookComments:function(){
	    var url = "reportComments.jsp" ;
	  	window.open(url+"?nodeId="+node_id);
	  },
	  
	  exportExcel:function(){
		var isNull =  Ext.getCmp("queryParam_Form").form.validateNotNull();
			 if (!isNull) {
    			return;
    	}
		dataMask.show();
   		var url = '/IOMPROJ/ExcelExportServlet';
   		var param_json = Ext.getCmp("queryParam_Form").form.getValues();
		Ext.getCmp("queryParam_Form").form.items.each(function(obj){
			 if(obj.xtype == "combo" || obj.xtype == "MultiSelect"){
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
				if(nodeId == 199||nodeId ==1365){
				  	var row1 = [{},{header:'运营商及客户电路',colspan:4,align:'center'},{header:'非客户电路',colspan:8,align:'center'},{},{},{}];
				  	var row2 = [{},{header:'传输',colspan:2,align:'center'},{header:'数据(含互联网转接<br/>不带传输)',colspan:2,align:'center'},{header:'传输通道',colspan:2,align:'center'},{header:'交换',colspan:2,align:'center'},{header:'数据(chinanet、<br/>cn2、atm)',colspan:2,align:'center'},{header:'其它(应急、临时、<br/>保密、公免)',colspan:2,align:'center'},{},{},{}];
				  	group = new Ext.extendColumnHeaderGroup({rows: [row1,row2]});
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
						id:node_id+'grid1',
						plugins: group
				 });  
		  }else{

			 var temp = kpiMain.getFieldsAndColumns(nodeId);
			 dynaicGrid = dynaicGridMap.get(nodeId);
			 
			 var group ;
			 if(nodeId == 199||nodeId ==1365){
				var row1 = [{},{header:'运营商及客户电路',colspan:4,align:'center'},{header:'非客户电路',colspan:8,align:'center'},{},{},{}];
				var row2 = [{},{header:'传输',colspan:2,align:'center'},{header:'数据(含互联网转接<br/>不带传输)',colspan:2,align:'center'},{header:'传输通道',colspan:2,align:'center'},{header:'交换',colspan:2,align:'center'},{header:'数据(chinanet、<br/>cn2、atm)',colspan:2,align:'center'},{header:'其它(应急、临时、<br/>保密、公免)',colspan:2,align:'center'},{},{},{}];
				group = new Ext.extendColumnHeaderGroup({rows: [row1,row2]});
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
						id:node_id+'grid1',
						plugins: group
				 });  
		  }
		return dynaicGrid;
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
					//enableHdMenu:false,
					//autoScroll :true,
					//loadMask: new Ext.LoadMask("queryResult",{msg:'数据正在加载...请稍后...'}),
					//height:document.body.clientHeight/3,
					height:'90%',
					//width:document.body.clientWidth-210,
					//width:'600',
					//autoWidth:true,
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
		
		if(node_id == 199||node_id ==1365){
		
			var retGrid = complexKpiReport.generateGroupGrid(node_id);
			_retGrid = retGrid ;						 
			//Ext.getDom("queryResult1").innerHTML = "<div id='queryResult1'></div>";
			//retGrid.render("queryResult1");
			retGrid.getStore().loadData([]);
			return retGrid;
		}else{
			return complexKpiReport.generateCommonGrid(node_id);
		}
		
		
		
	  },


	  doQuery:function(){
	  	
	  	var isNull =  Ext.getCmp("queryParam_Form").form.validateNotNull();
			 if (!isNull) {
    			return;
    		}
		dataMask.show();
		Ext.defer(function(){
				 var param_json = Ext.getCmp("queryParam_Form").form.getValues();
				 Ext.getCmp("queryParam_Form").form.items.each(function(obj){
					   if(obj.xtype == "combo" || obj.xtype == "MultiSelect"){
						  param_json[obj.name] = obj.getValue();
					   }
				 });
				 
				 
				 
				 for(var i=0; i<_comboItemMap.length; i++){
	    	
	    			var param = param_json;
	    			//alert(_comboItemMap[i].src);
	    			param.templateName = _comboItemMap[i].src;   //子菜单查询模板
					param.myOrgId = session.org.orgId;
					param.curStaffArea = session.area.areaId ;
	    			param = "[" + Ext.encode(param) + "]";
					var returnValue = callRemoteFunction("com.ztesoft.oss.reportconifg.bl.BuzKpiManager","generateQuerySql",false,param);
					if(returnValue != undefined){
						if(_comboItemMap[i].id == 199||_comboItemMap[i].id==1365){
							_retGrid.getStore().loadData(returnValue);
						}else{
							Ext.getCmp(_comboItemMap[i].id+'grid1').getStore().loadData(returnValue);
						}
						
					}
	    
	    		}
				 
				 
				 dataMask.hide();
			 },10);
		
	  	
	    
	  
	    
	 }
	  
	};
	
	
   
}();