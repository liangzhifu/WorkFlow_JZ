<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>均胜工作流系统Web主页面</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/js/jquery/easyui/themes/bootstrap/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/js/jquery/easyui/themes/icon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/style/mainframe/mainframe.css?version=53'/>">
<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/easyui/jquery.easyui.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/js/jquery/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/plugin/jquery.helper.js'/>"></script>

<script type="text/javascript"> 
	var contextPath = "${pageContext.request.contextPath}";
	var userId = '${userId}';
	var userName = '${userName}';
    var userOrgId = '${userOrgId}';
</script>
<script type="text/javascript" src="<c:url value='/js/module/mainframe/mainframe.js?version=57'/>"></script>
</head>
  
<body>
	<div id="moduleNav" class="header">
	<div class="nav">
	<c:forEach items="${catalogList}" var="catalog" varStatus="status">
		<div class="m02" id="catalog${catalog.id}" title="${catalog.name}" moduleId="${catalog.id}">
			<table height="50">
			<tr height="30"><td><img alt="" src="${catalog.imagePath}"></td></tr>
			<tr height="20"><td style="text-align:center;"><div class="m33">${catalog.name}</div></td></tr>
			</table>
		</div>
	</c:forEach>
	</div>
	<div id="divSubCatalog" class="subNav subc2">
	<c:forEach items="${subCatalogList}" var="catalog" varStatus="status">
		<div class="subM02 subc2" id="subCatalog${catalog.menuId}" title="${catalog.menuName}" moduleId="${catalog.menuId}" moduleUrl="${catalog.menuUrl}">
		${catalog.menuName}
			<c:if test="${catalog.woOrderNumber > 0}">
				<span style="position:absolute;top: 0px;right: 0px;width: 20px;height: 18px;font-size: 14px;line-height: 14px;color:red;font-weight:bold;font-style:italic;background-color:blue;">${catalog.woOrderNumber}</span>
			</c:if>
		</div>
	</c:forEach>
	</div>
	<!--  
	<ul class="user_menu">
		<li>
           <dl id="loginUserInfo" class="li_3_content">
				<a>
					<div class="user_img"></div>
					<div class="user_name">
						您好！<br>${userName}
					</div>
				</a>
           </dl>
		</li>
	</ul>
	-->
	<ul class="password_menu">
		<li>
			<div class="m02" id="div_password">
				<table height="50">
					<tr height="30">
						<td><img alt="" src="/WorkFlow/images/per_icon02.png"></td>
					</tr>
					<tr height="20">
						<td style="text-align:center;"><div class="m33">修改密码</div></td>
					</tr>
				</table>
			</div>
		</li>
	</ul>
	<ul class="exit_menu">
		<li>
			<div class="m02" id="div_exit">
				<table height="50">
					<tr height="30">
						<td><img alt="" src="/WorkFlow/images/ext_icon02.png"></td>
					</tr>
					<tr height="20">
						<td style="text-align:center;"><div class="m33">退出</div></td>
					</tr>
				</table>
			</div>
		</li>
	</ul>
</div>

<div id="bodyContent" class="content">
	<iframe id="mainIframe" src="jsp/firstPage/firstPage.jsp" width="100%"></iframe>
</div>
</body>

</html>
