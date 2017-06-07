<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>提示</title>
	<jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
  </head>
  <body>
  </body>
  <script type="text/javascript">
		var paramObj = window.dialogArguments;
        Ext.onReady(function(){
            var ErrorPanel = new Ext.Panel({
					 id:'errorPanel',
					 width:window.screen.width/2,
				     height:window.screen.height/8,
					 layout:'column',
					 autoScroll :true,
					 frame:true,
					 items:[
					    {columnWidth:1,border:false,layout:'column',items:[{columnWidth:.12},{columnWidth:.4,items:[{html:'<span style="font-size:14px;color:red;">抱歉!功能异常了,请联系管理员修复!</span>'}]}]},
						{columnWidth:1,border:false,layout:'column'},
                        {columnWidth:1,border:false,layout:'column',items:[{columnWidth:.25},{columnWidth:.06,items:[{xtype:'button',text:'确定',listeners:{click:function(){window.close();returnValue="";}}}]},
						 {columnWidth:.25,items:[
							{xtype:'button',text:'详情',listeners:{click:function(){window.returnValue= paramObj[0].Error[0].detail.replace(new RegExp("at ", "g"),"<br/>at ");window.close();}}}]}]}
					 ],
					 renderTo:Ext.getBody()
			   });
		});
  
  </script>
</html>
