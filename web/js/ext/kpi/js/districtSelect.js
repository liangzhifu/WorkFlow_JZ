Ext.onReady(function() {
    Ext.BLANK_IMAGE_URL = "../resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    var Tree = Ext.tree;
    //组件ID
    var id = window.dialogArguments;
    
    var loader = new Ext.tree.TreeLoader({
        url:'/IOMPROJ/iomextservlet',
        listeners:{
            load:function(load,treeNode,response){
               var node_array = treeNode.childNodes;
            },
            click : function(node, event) {
            }
        }
    });
    
    var tree = new Tree.TreePanel({
        el : "dyntree",
        id : 'treePanel',
        title : '<%=getI18NResource("innerOrderAccept.districtSelect")%>',
        autoScroll : true,
        align : 'left',
        animate : true,
        frame : true,
        useArrows : true,
        width : '100%',
        height : '100%',
        lines : true,
        containerScroll: true,
        border: false,
        rootVisible: true,
        loader : loader,
        root : {
            expanded: true,
            nodeType: 'async',
            text: '<%=getI18NResource("innerOrderAccept.chinaTel")%>',
            draggable: false,
            id:'50135'
        },
        listeners : {
            beforeload:function(node){
                   var obj = new Object();
                   obj.id = node.id;
                   obj.text = node.text;
                   var loader = this.getLoader();
                   var xmlValue = toXml("com.ztesoft.oss.reportconifg.bl.BandBuzManager","getAreaTreeNode",false,obj);
                   loader.baseParams = {xml:xmlValue};
            },
            dblclick : function(node, event) {
                //先校验,是否选择了国际区域
                var isGj;
                var pathName = node.attributes.pathName;
                //为国际定单
                if (id!="proviceA" && id!="proviceZ"){
				    if ( pathName!=null && pathName!=undefined ) {
				        isGj = (pathName.indexOf('<%=getI18NResource("innerOrderAccept.gj")%>'))>0?true:false;
	                    if (isGj && node.attributes.grade!='C4'){
	                        //国际没选择C4城市级时
	                       Ext.Msg.alert('<%=getI18NResource("innerOrderAccept.titleTip")%>',
	                          '<%=getI18NResource("innerOrderAccept.pleaseSelectCity")%>');
	                          return;
	                    }else if (!isGj && (node.attributes.grade=='C1'||node.attributes.grade=='C2')){
	                        //国内没选择C3城市级时
	                        Ext.Msg.alert('<%=getI18NResource("innerOrderAccept.titleTip")%>',
	                          '<%=getI18NResource("innerOrderAccept.pleaseSelectCity")%>');
	                          return;
	                    }
				    } else {
	                       Ext.Msg.alert('<%=getI18NResource("innerOrderAccept.titleTip")%>',
	                          '<%=getI18NResource("innerOrderAccept.pleaseSelectCity")%>');
	                          return;
	                }
                }
                var obj = new Object();
                obj.id = node.attributes.id;
                obj.name = node.attributes.text;
                obj.grade = node.attributes.grade;
                obj.pathName = node.attributes.pathName;
                obj.pathCode = node.attributes.pathCode;
                obj.areaType = node.attributes.areaType;
                obj.grade = node.attributes.grade;
                window.returnValue=obj;
                window.close();
            }
        }
    });
            
    tree.render();
    tree.getRootNode().expand();

});
        
function closeWindow(){
    window.returnValue='';
}
  
