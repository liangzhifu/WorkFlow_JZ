<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../../public/I18N.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><%=getI18NResource("innerOrderAccept.districtSelect")%></title>
    <jsp:include page="../../public/ExtJsStylesheet.jsp" flush="true"/>
    <script language="javascript" src="../../public/script/helper.js"></script>
    
  </head>
  <body>
		<div align="left" id="dyntree"></div>
  </body>
  <script language="JScript"><%@ include file="js/districtSelect.js"%></script>
</html>