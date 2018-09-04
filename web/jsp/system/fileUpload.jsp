<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传文件</title>
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/jquery-ui.css" />
    <script src="/WorkFlow/js/jquery/easyui/jquery.min.js"></script>
    <script src="/WorkFlow/style/assets/js/jquery.form.js"></script>
    <script type="text/javascript">
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <script type="text/javascript" src="<c:url value='/js/module/system/fileUpload.js?version=9'/>"></script>
</head>
<body>
<form method='post' id='excelForm' enctype='multipart/form-data' action='/WorkFlow/fileUpload/uploadFile.do'>
    <a class='uploadFile button button-primary button-rounded button-small' href='#'>
        <input type='file' onchange='updateFileExcelChange()' name='uploadFile' id='uploadFile'/>
        <i class='glyphicon glyphicon-search'></i>
    </a>
</form>
</body>
</html>