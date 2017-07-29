<%--
  Created by IntelliJ IDEA.
  User: 梁志福
  Date: 2017/4/19
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>RR问题点订单详情</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body ng-controller="dpcoiOrderDetailController" ng-cloak>
<div class="main-container container-fluid">
    <div class="main-content" >
        <div class="page-content" >
            <div class="row-fluid" >
                <div class="span12" >
                    <div class="block" >
                        <div class="block-header" >
                            <div >
                                <i class="icon-edit"></i>基本信息
                            </div>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <label class="control-label">状态：${rrProblem.problemStatus}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">问题编号：${rrProblem.problemNo}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">问题类型：${rrProblem.problemType}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">工程：${rrProblem.engineering}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">客户：${rrProblem.customer}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-2">
                                    <label class="control-label">车型：${rrProblem.vehicle}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">品名：${rrProblem.productNo}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">生产线：${rrProblem.productLine}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">严重度：${rrProblem.severity}</label>
                                </div>
                                <div class="col-md-4">
                                    <label class="control-label">根本原因：${rrProblem.rootCause}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-2">
                                    <label class="control-label">进度：${rrProblem.speedOfProgress}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">创建人：${dpcoiOrder.createByName}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">创建时间：${dpcoiOrder.createDateStr}</label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">PFMEA变更超时：
                                        <c:if test="${dpcoiOrder.pfmeaDelay==0}">否</c:if>
                                        <c:if test="${dpcoiOrder.pfmeaDelay==1}">是</c:if>
                                    </label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">CP变更超时：
                                        <c:if test="${dpcoiOrder.cpDelay==0}">否</c:if>
                                        <c:if test="${dpcoiOrder.cpDelay==1}">是</c:if>
                                    </label>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">作业标准书变更超时：
                                        <c:if test="${dpcoiOrder.standardBookDelay==0}">否</c:if>
                                        <c:if test="${dpcoiOrder.standardBookDelay==1}">是</c:if>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <label class="control-label">不良内容：${rrProblem.badContent}</label>
                                </div>
                                <div class="col-md-4">
                                    <label class="control-label">备注：${dpcoiOrder.remark}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12" >
                    <div class="block" >
                        <div class="block-header" >
                            <div >
                                <i class="icon-edit"></i>工单列表
                                <button class="btn btn-small btn-purple" type="button"
                                        ng-click="dpcoiOrderDeatil.openOrderFileList('${dpcoiOrder.dpcoiOrderId}')">
                                    <i class="bigger-110"></i>整套文件
                                </button>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width="10%">工单类型</th>
                                <th width="10%">工单状态</th>
                                <th width="12%">创建时间</th>
                                <th width="8%">确认人</th>
                                <th width="12%">确认时间</th>
                                <th width="8%">完成变更人</th>
                                <th width="12%">完成变更时间</th>
                                <th width="8%">审核人</th>
                                <th width="10%">审核时间</th>
                                <th width="10%">上传文件</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dpcoiWoOrderDetailList}" var="item" >
                                <tr>
                                    <td>
                                        <c:if test="${item.dpcoiWoOrderType == 1}">PFMEA</c:if>
                                        <c:if test="${item.dpcoiWoOrderType == 2}">CP</c:if>
                                        <c:if test="${item.dpcoiWoOrderType == 3}">作业标准书</c:if>
                                    </td>
                                    <td>${item.dpcoiWoOrderState}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.dpcoiWoOrderType == 1}">
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 0}">
                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 2}">
                                                <c:if test="${dpcoiOrder.cpDelay == 0}">
                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.cpDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 3}">
                                                <c:if test="${dpcoiOrder.standardBookDelay == 0}">
                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.standardBookDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.createDateStr ? "/" : item.createDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>${empty item.userName1 ? "/" : item.userName1}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.dpcoiWoOrderType == 1}">
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 0}">
                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 2}">
                                                <c:if test="${dpcoiOrder.cpDelay == 0}">
                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.cpDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 3}">
                                                <c:if test="${dpcoiOrder.standardBookDelay == 0}">
                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.standardBookDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.confirmDateStr ? "/" : item.confirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>${empty item.userName2 ? "/" : item.userName2}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.dpcoiWoOrderType == 1}">
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 0}">
                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 2}">
                                                <c:if test="${dpcoiOrder.cpDelay == 0}">
                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.cpDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 3}">
                                                <c:if test="${dpcoiOrder.standardBookDelay == 0}">
                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.standardBookDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.fileCompleteDateStr ? "/" : item.fileCompleteDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>${empty item.userName3 ? "/" : item.userName3}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.dpcoiWoOrderType == 1}">
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 0}">
                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.pfmeaDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 2}">
                                                <c:if test="${dpcoiOrder.cpDelay == 0}">
                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.cpDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${item.dpcoiWoOrderType == 3}">
                                                <c:if test="${dpcoiOrder.standardBookDelay == 0}">
                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                </c:if>
                                                <c:if test="${dpcoiOrder.standardBookDelay == 1}">
                                                            <span style="color:red;">
                                                                    ${empty item.managerConfirmDateStr ? "/" : item.managerConfirmDateStr}
                                                            </span>
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <button class="btn btn-small btn-purple" type="button"
                                                ng-click="dpcoiOrderDeatil.openFileList('${item.dpcoiWoOrderId}')">
                                            <i class="bigger-110"></i>详情
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12" >
                    <div class="block" >
                        <div class="block-header" >
                            <div >
                                <i class="icon-edit"></i>操作记录
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>操作类型</th>
                                <th>操作人</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${operateHistoryList}" var="item" >
                                <tr>
                                    <td>
                                        <c:if test="${item.operateType == 1}">生成</c:if>
                                        <c:if test="${item.operateType == 2}">4M同步</c:if>
                                        <c:if test="${item.operateType == 3}">作废</c:if>
                                        <c:if test="${item.operateType == 4}">修改</c:if>
                                        <c:if test="${item.operateType == 5}">RR同步</c:if>
                                    </td>
                                    <td>${item.opeateByName}</td>
                                    <td>${item.operateDateStr}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <a href="/WorkFlow/rrProblemOrder/getRRProblemOrderListDlg.do"
                   class="btn  btn-mini btn-info">关闭</a>
            </div>
        </div>
    </div>
</div>

<!-- 文件列表 -->
<div id="fileListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="fileListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="fileListModalLabel">文件列表</h3>
    </div>
    <form id="fileListModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div style="height: 200px; overflow: scroll;">
                    <table id="fileListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="45%">文件名称</th>
                            <th width="10%">审核状态</th>
                            <th width="10%">创建人</th>
                            <th width="15%">创建时间</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="dpcoiWoOrderFileData in dpcoiOrderDeatil.dpcoiWoOrderFileList">
                            <td>{{dpcoiWoOrderFileData.fileName}}</td>
                            <td>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==0">未审核</span>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==1">通过</span>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==2">不通过</span>
                            </td>
                            <td>{{dpcoiWoOrderFileData.createByName}}</td>
                            <td>{{dpcoiWoOrderFileData.fileCreateDateStr}}</td>
                            <td>
                                <button class="btn btn-small btn-purple" type="button"
                                        ng-click="dpcoiOrderDeatil.downloadFile(dpcoiWoOrderFileData.fileId)">
                                    <i class="bigger-110"></i>查看
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="flieListClose"
                        class="btn btn-small btn-primary" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </form>
</div>

<!-- 文件列表 -->
<div id="fileListModal2" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="fileListModal2Label"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="fileListModal2Label">文件列表</h3>
    </div>
    <form id="fileListModal2Form" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div style="height: 200px; overflow: scroll;">
                    <table id="fileListTable2"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="40%">文件名称</th>
                            <th width="10%">工单类型</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="dpcoiOrderFileData in dpcoiOrderDeatil.dpcoiOrderFileList">
                            <td>{{dpcoiOrderFileData.fileName}}</td>
                            <td>
                                <span ng-show="dpcoiOrderFileData.dpcoiWoOrderType==1">PFMEA</span>
                                <span ng-show="dpcoiOrderFileData.dpcoiWoOrderType==2">CP</span>
                                <span ng-show="dpcoiOrderFileData.dpcoiWoOrderType==3">作业标准书</span>
                            </td>
                            <td>
                                <button class="btn btn-small btn-purple" type="button"
                                        ng-click="dpcoiOrderDeatil.downloadFile(dpcoiOrderFileData.fileId)">
                                    <i class="bigger-110"></i>查看
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="flieList2Close"
                        class="btn btn-small btn-primary" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiOrderDetail.js"></script>
</html>
