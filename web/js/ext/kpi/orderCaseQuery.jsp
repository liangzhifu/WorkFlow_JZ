<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.ztesoft.test.UserControl"%> 
<%@ page import="com.ztesoft.oss.reportconifg.bl.BuzKpiManager"%> 
<%@ page import="java.lang.*"%> 
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>定单完成情况查询</title>
    <style type="text/css">
		.bg{
		background-color:#cbe9fd;
		 font-size: 13px;
   font-family:'Microsoft Yahei' arial, helvetica,tahoma,sans-serif;
   line-height:30px;}
   .list-font{
		background-color:#f1f1f1;
		 font-size: 13px;
   font-family:'Microsoft Yahei' arial, helvetica,tahoma,sans-serif;
   line-height:25px;}

    </style>
        <link href="../resources/css/ext-all.css" rel="stylesheet" type="text/css">
	    <link href="../../../public/ccbs/style/zh-CN/ext-zte.css" rel="stylesheet" type="text/css">
        <jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
	    <script language="javascript" src="../../ext/kpi/js/orderCase.js"></script>
  </head>
  <body>
  <table width="175%" border="0" cellpadding="0" cellspacing="1" class="table">
     <thead>
      <TR align="center" class="bg">
        <TD width=40  >序号</TD>
        <TD width=150 >定单流水号</TD>
        <TD width=150 >客户名称</TD>
        <TD width=100 >申请人</TD>
        <TD width=100 >申请日期</TD>
        <TD width=100 >申请部门</TD>
        <TD width=200 >调度文号</TD>
        <TD width=100 >调度人员</TD>
        <TD width=100 >调度日期</TD>
        <TD width=100 >要求完成时间</TD>
		<TD width=100 >完成时间</TD>
        <TD width=100 >要求反馈时间</TD>
        <TD width=100 >反馈时间</TD>
        <TD width=100 >要求完成数量</TD>
        <TD width=100 >按时完成数量</TD>
        <TD width=100 >未按时完成数量</TD>
        <TD width=120 >未按时完成百分比</TD>
        <TD width=130 >未按时完成的省公司</TD>
        <TD width=100 >方向</TD>
        <TD width=100 >速率</TD>
        <TD width=100 >资源类型</TD>
        <TD width=100 >数量</TD>
      </TR>
    </thead>
	
		<%  
            UserControl uesr=new UserControl();   
			String datemode = request.getParameter("datemode");
			Map mapinfo = new HashMap();
		   if(datemode.equals("1")){
			mapinfo.put("staticMonth",request.getParameter("staticMonth"));
		   }
		   if(datemode.equals("0")){
			mapinfo.put("startdate",request.getParameter("startdate"));
			mapinfo.put("enddate",request.getParameter("enddate"));
		  }
		    mapinfo.put("querymode",request.getParameter("querymode"));
		    mapinfo.put("datemode", datemode);
		    mapinfo.put("custspec", request.getParameter("custspec"));
		    mapinfo.put("busitype", request.getParameter("busitype"));
           	mapinfo.put("myOrgId", request.getParameter("myOrgId"));
		    mapinfo.put("curStaffArea", request.getParameter("curStaffArea"));
				mapinfo.put("node_id", request.getParameter("node_id"));
		    Map retMap=(Map)uesr.getWork(mapinfo);
			   List li=(List)retMap.get("retArr");			  
			   Map rMap=(Map)retMap.get("retCircuitMap");	
			   String tabXml = "" ;
               for(int i=0;i<li.size();i++){
				  Map ma=(Map)li.get(i);				 
				  String orderNo = ma.get("custorderno").toString();		
				  String dispatch="";
				 if(ma.get("dispatchno")!=null){
					 dispatch= ma.get("dispatchno").toString();	
				 } 		
				 String serira_no_dispatchno=orderNo+dispatch;
                  List list2= (List)rMap.get(serira_no_dispatchno);
                  int h=list2.size();		  
                  Object custname=ma.get("custname");
                  if(custname==null||"".equals(custname)){
				      custname="&nbsp;";
				  }
				    Object custorderno=ma.get("custorderno");
				  if(custorderno==null||"".equals(custorderno)){
				      custorderno="&nbsp;";
				  }
				   Object draftername=ma.get("draftername");
				  if(draftername==null||"".equals(draftername)){
				      draftername="&nbsp;";
				  }
				
				    Object acceptorg= ma.get("acceptorg");
				   if(acceptorg==null||"".equals(acceptorg)){
				      acceptorg="&nbsp;";
				  }

			    	Object  dispatchno= ma.get("dispatchno");
				 if(dispatchno==null||"".equals(dispatchno)){
                     dispatchno="&nbsp;";
				  }
                       Object acceptdate =ma.get("acceptdate");
	              if(acceptdate==null||"".equals(acceptdate)){
                     acceptdate="&nbsp;";
				  }
				   Object dispatchdate=ma.get("dispatchdate");
				  if(dispatchdate==null || " ".equals(dispatchdate)){
				      dispatchdate="&nbsp;";
				  }
				   Object finishdate=ma.get("finishdate");
				   if(finishdate==null || " ".equals(finishdate)){
				      finishdate="&nbsp;";
				  }
				  
                   String dispatchfinishdate=(String)ma.get("dispatchfinishdate");
				  if(dispatchfinishdate!=null && !"".equals(dispatchfinishdate)){
					 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); 
					 dispatchfinishdate = fomat2.format(fomat2.parse(dispatchfinishdate));
					 }else{
                         dispatchfinishdate="&nbsp;";
				 }
				   Object reqfreebackdate=ma.get("reqfreebackdate");
				  if(reqfreebackdate==null||"".equals(reqfreebackdate)){
					 reqfreebackdate="&nbsp;";	 
				  }
				   Object freebackdate=ma.get("freebackdate");
				  if(freebackdate==null||"".equals(freebackdate)){
                     freebackdate="&nbsp;";
				  }
				   Object outtimeprovince=ma.get("outtimeprovince");
				   if(outtimeprovince==null||"".equals(outtimeprovince)){
                     outtimeprovince="&nbsp;";
				  }
                   Object direction=ma.get("direction");
  	  	          if(direction==null||"".equals(direction)){
                     direction="&nbsp;";
				  }
                  Object speed=ma.get("speed");
  	  	          if(speed==null||"".equals(speed)){
                     speed="&nbsp;";
				  }
				  Object restype= ma.get("restype");
  	  	          if(restype==null||"".equals(restype)){
                     restype="&nbsp;";
				  }
				  Object dispatchfname=ma.get("dispatchfname");
				  if(dispatchfname==null||"".equals(dispatchfname)){
                     dispatchfname="&nbsp;";
				  }
				   Object circuitnum=ma.get("circuitnum");
				  if(circuitnum==null||"".equals(circuitnum)){
                     circuitnum="&nbsp;";
				  }
				   Object intimenum=ma.get("intimenum");
				   if(intimenum==null||"".equals(intimenum)){
                     intimenum="&nbsp;";
				  }
				   Object outtimenum=ma.get("outtimenum");
				   if(outtimenum==null||"".equals(outtimenum)){
                     outtimenum="&nbsp;";
				  }
				  Object outtimepercent=ma.get("outtimepercent");
				   if(outtimepercent==null||"".equals(outtimepercent)){
                     outtimepercent="&nbsp;";
				  }
                  String val= "<TR class="+"list-font"+"><td  rowspan="+h+">"+(i+1)+"</td>";
				  String val1 = "<td  rowspan="+h+"> <a href=javascript:showorderinfo('"+ma.get("custorderid")+"','"+ma.get("custorderno")+"','"+ma.get("spectype")+"','"+ma.get("dispatchorderid")+"','"+ma.get("dispatchno")+"','"+"001"+"') style='text-decoration:none'>" +custorderno+"</a></td>";

				  String val2 = "<td  rowspan="+h+">"+custname+"</td>";
				  String val3 = "<td  rowspan="+h+">"+draftername+"</td>";
				  String val4 = "<td  rowspan="+h+">"+acceptdate+"</td>";
				  String val5 = "<td  rowspan="+h+">"+acceptorg+"</td>";
				  String val6 = "<td  rowspan="+h+"> <a href=javascript:showorderinfo('"+ma.get("custorderid")+"','"+ma.get("custorderno")+"','"+ma.get("spectype")+"','"+ma.get("dispatchorderid")+"','"+ma.get("dispatchno")+"','"+"002"+"') style='text-decoration:none'>"+dispatchno+"</a></td>";

				  String val7 = "<td  rowspan="+h+">"+dispatchfname+"</td>";
				  String val8 = "<td  rowspan="+h+">"+dispatchdate+"</td>";
				  String val9 = "<td  rowspan="+h+">"+dispatchfinishdate+"</td>";
                  String valda = "<td  rowspan="+h+">"+finishdate+"</td>";
				  String val10 = "<td  rowspan="+h+">"+reqfreebackdate+"</td>";
				  String va111 = "<td  rowspan="+h+">"+freebackdate+"</td>";
				  String val12 = "<td  rowspan="+h+">"+circuitnum+"</td>";
				  String val13 = "<td  rowspan="+h+">"+intimenum+"</td>";
				  String val14 = "<td  rowspan="+h+">"+outtimenum+"</td>";
				  String val15 = "<td  rowspan="+h+">"+outtimepercent+"</td>";
				  String val16 = "<td  rowspan="+h+">"+outtimeprovince+"</td>";
                  String val17 = "<td>"+direction+"</td>";
				  String val18 = "<td>"+speed+"</td>";
				  String val19 = "<td>"+restype+"</td>";
				  String val20 = "<td>"+ma.get("num")+"&nbsp;"+"</td></TR>";
				  String str=val+val1+val2+val3+val4+val5+val6+val7+val8+val9+valda+val10+va111+val12+val13+val14+val15+val16+val17+val18+val19+val20;
				  String str2="";
                  if(h>1){
                       for(int e=1;e<list2.size();e++){
                         Map  map3=(Map)list2.get(e);
                         Object  direction2=map3.get("direction");
						if(direction2==null||"".equals(direction2)){
                          direction2="&nbsp;";
				         }
                         String a = "<TR class="+"list-font"+"><td>"+direction2+"</td>";
				         String b = "<td>"+map3.get("speed")+"</td>";
				         String c = "<td>"+map3.get("restype")+"</td>";
				         String d = "<td>"+map3.get("num")+"&nbsp;"+"</td></TR>";
                         str2=str2+a+b+c+d;
					   }
				  }

				  tabXml = tabXml + str +  str2 ;
			  }
              out.write(tabXml);       			
			%>
  </table>
  </body>
</html>
