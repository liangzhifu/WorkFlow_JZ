<%--
  Created by IntelliJ IDEA.
  User: liangzhifu
  Date: 2017/7/7
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>RR问题点屏幕显示</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <style type="text/css">
        .claasRed{
            background-color : red;
            color: #000000;!important;
        }

        .classGoldenRod{
            background-color : GoldenRod;
            color: #000000;!important;
        }

        .classYellow{
            background-color : yellow;
            color: #000000;!important;
        }

        .cassDeepSkyBlue{
            background-color : deepskyblue;
            color: #000000;!important;
        }
    </style>
</head>
<body>
    <div class="main-container container-fluid" style="padding-right: 0px;padding-left: 0px;">
        <div class="main-content">
            <div class="page-content" style="padding-right: 0px;padding-left: 0px;">
                <div id="headTable" style="overflow:hidden;">
                    <table class="table table-striped table-bordered table-hover">
                        <tbody>
                            <tr>
                                <td style="width:12%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">问题类型</td>
                                <td style="width:7%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">进展</td>
                                <td style="width:8%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">发生<br>日期</td>
                                <td style="width:10%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">车型</td>
                                <td style="width:10%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">品名</td>
                                <td style="width:23%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">不良内容</td>
                                <td style="width:10%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">责任人</td>
                                <td style="width:8%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">下次<br>汇报</td>
                                <td style="width:10%;font-size:25px;background-color : #808080;color: #FFFFFF;!important;">等级</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="listTable" style="overflow:hidden;">
                    <table class="table table-striped table-bordered table-hover">
                        <tbody id="tbodyList">
                            <c:forEach items="${mapList}" var="item">
                                <tr style="${item.backgroundColor}">
                                    <td style="width:12%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.problemType}</td>
                                    <td style="width:7%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.problemProgress}</td>
                                    <td style="width:8%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">
                                        <fmt:formatDate value="${item.happenDate}" pattern="yy-MM-dd"/>
                                    </td>
                                    <td style="width:10%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.vehicle}</td>
                                    <td style="width:10%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.productNo}</td>
                                    <td style="width:23%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;text-align: left;">${item.badContent}</td>
                                    <td style="width:10%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.persionLiable}</td>
                                    <td style="width:8%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">
                                        <fmt:formatDate value="${item.reportDate}" pattern="yy-MM-dd"/>
                                    </td>
                                    <td style="width:10%;font-size:25px;white-space:normal;word-wrap:break-word;word-break:break-all;padding: 5px;display:table-cell; vertical-align:middle;">${item.trackingLevel}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    var clientHeight = document.body.clientHeight;
    var headHeight = $("#headTable").height();
    $("#listTable").css("height", clientHeight-headHeight);

    $(document).ready(function() {
        setTimeout(autoScroll,15000);
    });

    function autoScroll() {
        var obj = $("#listTable").find("table:first");
        var height = obj.height();
        height = height - clientHeight + headHeight;
        if(height < 0){
            toOtherJsp();
        }else {
            var speed = height * 100;
            obj.animate({marginTop:"-"+height+"px"},speed, function () {
                setTimeout(toOtherJsp,15000);
            });
        }
    }

    function toOtherJsp(){
        var getTimestamp=new Date().getTime();
        window.location.href = "http://10.234.11.21:9007/pub/problem1.aspx"+"?timestamp="+getTimestamp;
    }
</script>
</html>
