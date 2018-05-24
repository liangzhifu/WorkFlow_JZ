<%--
  Created by IntelliJ IDEA.
  User: 梁志福
  Date: 2017/4/20
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>RR问题点新增修改</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
    <script type="text/javascript">
        var action = '${action}';
        var startDate = '${startDate}';
        var endDate = '${endDate}';
        var rrProblemId = '${rrProblemId}';
        var searchStr = ""
        searchStr += "badContent=${rrProblemQuery.badContent}";
        searchStr += "&problemProgress=${rrProblemQuery.problemProgress}";
        searchStr += "&speedOfProgress=${rrProblemQuery.speedOfProgress}";
        searchStr += "&problemStatus=${rrProblemQuery.problemStatus}";
        searchStr += "&problemType=${rrProblemQuery.problemType}";
        searchStr += "&engineering=${rrProblemQuery.engineering}";
        searchStr += "&customer=${rrProblemQuery.customer}";
        searchStr += "&vehicle=${rrProblemQuery.vehicle}";
        searchStr += "&productNo=${rrProblemQuery.productNo}";
        searchStr += "&happenDateBegin=${rrProblemQuery.happenDateBegin}";
        searchStr += "&happenDateEnd=${rrProblemQuery.happenDateEnd}";
        searchStr += "&persionLiable=${rrProblemQuery.persionLiable}";
        searchStr += "&productLine=${rrProblemQuery.productLine}";
        searchStr += "&severity=${rrProblemQuery.severity}";
        searchStr += "&responsibleDepartment=${rrProblemQuery.responsibleDepartment}";
        searchStr += "&trackingLevel=${rrProblemQuery.trackingLevel}";
        searchStr += "&dpcoi4M=${rrProblemQuery.dpcoi4M}";
        searchStr += "&size=${rrProblemQuery.size}";
        searchStr += "&start=${rrProblemQuery.start}";
    </script>
</head>
<body ng-controller="rrProblemEditController" ng-cloak>
<input type="text" style="display: none" id="rrProblemId" name="rrProblemId">
<div class="ain-content">
    <div class="page-content">
        <div class="row-fluid">
            <div class="span12">
                <div class="block">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-3">
                                <label class="control-label" for="problemStatus"><span style="color:red;">*</span>状态：</label>
                                <select id="problemStatus" name="problemStatus" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.problemStatus" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:1"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.problemStatus"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="problemNo">问题编号：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="true" style="width: 60%"
                                       id="problemNo" name="problemNo" ng-model="rrProblemEdit.rrProblem.problemNo">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="problemType"><span style="color:red;">*</span>问题类型：</label>
                                <select id="problemType" name="problemType" class="form-control-order form-control" ng-disabled="action=='edit'" required="required"
                                        ng-model="rrProblemEdit.rrProblem.problemType" ng-change="problemTypeChange()" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:2"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.problemType"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="engineering"><span style="color:red;">*</span>工程：</label>
                                <select id="engineering" name="engineering" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.engineering" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:3"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.engineering"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="problemProgress"><span style="color:red;">*</span>问题进展：</label>
                                <select id="problemProgress" name="problemProgress" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.problemProgress" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:4"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.problemProgress"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="trackingLevel">追踪等级：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="trackingLevel" name="trackingLevel" ng-model="rrProblemEdit.rrProblem.trackingLevel">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="happenDate"><span style="color:red;">*</span>发生日期：</label>
                                <input class="form-control-order form-control clean" required="required" style="width: 60%" data-type="dateType1" ng-disabled="action=='edit'"
                                       id="happenDate" name="happenDate" ng-model="rrProblemEdit.rrProblem.happenDateStr">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="customer">客户：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="customer" name="customer" ng-model="rrProblemEdit.rrProblem.customer">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="vehicle"><span style="color:red;">*</span>车型：</label>
                                <select id="vehicle" name="vehicle" required="required" style="width: 60%"
                                        class="form-control-order form-control clean chosen-select chosen">
                                    <option value="">请选择</option>
                                    <option ng-repeat="data in dpcoiConfigVehicleList" value="{{data.value}}">{{data.value}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="productNo"><span style="color:red;">*</span>品名：</label>
                                <select id="productNo" name="productNo" class="form-control-order form-control clean chosen-select chosen" required="required">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:5"
                                            value="{{dpcoiConfigDate.configValue}}"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="badContent"><span style="color:red;">*</span>不良内容：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
                                       id="badContent" name="badContent" ng-model="rrProblemEdit.rrProblem.badContent"
                                       ng-dblclick="editInput('badContent')">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="persionLiable"><span style="color:red;">*</span>责任人：</label>
                                <select id="persionLiable" name="persionLiable" class="form-control-order form-control chosen-select"
                                        required="required" multiple="multiple">
                                    <option ng-repeat="data in rrProblemEdit.persionLiableList" value="{{data.userName}}">{{data.userName}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="reportDate"><span style="color:red;">*</span>下次汇报时间：</label>
                                <input class="form-control-order form-control clean" required="required" style="width: 60%" data-type="dateType2"
                                       id="reportDate" name="reportDate" ng-model="rrProblemEdit.rrProblem.reportDateStr">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="dpcoi4M">4M：</label>
                                <select id="dpcoi4M" name="dpcoi4M" class="form-control-order form-control chosen-select" required="required"
                                        ng-model="rrProblemEdit.rrProblem.dpcoi4M" style="width: 60%" multiple="multiple">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:13"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.severity"
                                    >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="reasonForDelay">延期原因及进展：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
                                       id="reasonForDelay" name="reasonForDelay" ng-model="rrProblemEdit.rrProblem.reasonForDelay"
                                       ng-dblclick="editInput('reasonForDelay')">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="firstDate">第一次原因调查：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="firstDate" name="firstDate" ng-model="rrProblemEdit.rrProblem.firstDateStr">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="secondDate">第二次永久对策制定：</label>
                                <input class="form-control-order form-control clean" style="width: 50%" ng-disabled="true"
                                       id="secondDate" name="secondDate" ng-model="rrProblemEdit.rrProblem.secondDateStr">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="thirdDate">第三次永久对策有效：</label>
                                <input class="form-control-order form-control clean" style="width: 50%" ng-disabled="true"
                                       id="thirdDate" name="thirdDate" ng-model="rrProblemEdit.rrProblem.thirdDateStr">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="fourthDate">第四次经验总结：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="fourthDate" name="fourthDate" ng-model="rrProblemEdit.rrProblem.fourthDateStr">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="closeConfirm">关闭确认：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="closeConfirm" name="closeConfirm" ng-model="rrProblemEdit.rrProblem.closeConfirm">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="productLine"><span style="color:red;">*</span>生产线：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required" pattern="^[J][a-zA-Z][a-zA-Z]\-[a-zA-Z0-9]{4}$"
                                       id="productLine" name="productLine" ng-model="rrProblemEdit.rrProblem.productLine">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="severity"><span style="color:red;">*</span>严重度：</label>
                                <select id="severity" name="severity" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.severity" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:6"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.severity"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="occurrenceFrequency"><span style="color:red;">*</span>发生频次：</label>
                                <select id="occurrenceFrequency" name="occurrenceFrequency" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.occurrenceFrequency" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:7"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.occurrenceFrequency"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="badQuantity"><span style="color:red;">*</span>不良数量：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
                                       id="badQuantity" name="badQuantity" ng-model="rrProblemEdit.rrProblem.badQuantity">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="batch">批次：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="batch" name="batch" ng-model="rrProblemEdit.rrProblem.batch">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="happenShift"><span style="color:red;">*</span>发生班次：</label>
                                <select id="happenShift" name="happenShift" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.happenShift" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:8"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="rrProblemEdit.rrProblem.happenShift==dpcoiConfigDate.configValue"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="responsibleDepartment"><span style="color:red;">*</span>责任部门：</label>
                                <select id="responsibleDepartment" name="responsibleDepartment" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.responsibleDepartment" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:9"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.responsibleDepartment"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="recordPpm"><span style="color:red;">*</span>客户是否记录PPM：</label>
                                <select id="recordPpm" name="recordPpm" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.recordPpm" style="width: 50%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:10"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.recordPpm"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="recordNum"><span style="color:red;">*</span>记录数量：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
                                       id="recordNum" name="recordNum" ng-model="rrProblemEdit.rrProblem.recordNum">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="temporary">临时对策：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="temporary" name="temporary" ng-model="rrProblemEdit.rrProblem.temporary"
                                       ng-dblclick="editInput('temporary')">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="rootCause">根本原因：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="rootCause" name="rootCause" ng-model="rrProblemEdit.rrProblem.rootCause"
                                       ng-dblclick="editInput('rootCause')">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="permanentGame">永久对策：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="permanentGame" name="permanentGame" ng-model="rrProblemEdit.rrProblem.permanentGame"
                                       ng-dblclick="editInput('permanentGame')">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="effectVerification">效果校验：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="effectVerification" name="effectVerification" ng-model="rrProblemEdit.rrProblem.effectVerification"
                                       ng-dblclick="editInput('effectVerification')">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="containmentWorksheet">遏制工作表：</label>
                                <input type="button" ng-click="uploadFile('containmentWorksheet','containmentWorksheetFileId')" value="上传文件">
                                <input type="hidden" id="containmentWorksheetFileId" name="containmentWorksheetFileId" ng-model="rrProblemEdit.rrProblem.containmentWorksheetFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="containmentWorksheet" name="containmentWorksheet" ng-model="rrProblemEdit.rrProblem.containmentWorksheet">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="qualityWarningCardNumber">质量警示卡：</label>
                                <input type="button" ng-click="uploadFile('qualityWarningCardNumber','qualityWarningCardNumberFileId')" value="上传文件">
                                <input type="hidden" id="qualityWarningCardNumberFileId" name="qualityWarningCardNumberFileId" ng-model="rrProblemEdit.rrProblem.qualityWarningCardNumberFileId">
                                <input class="form-control-order form-control clean" style="width: 38%"
                                       id="qualityWarningCardNumber" name="qualityWarningCardNumber" ng-model="rrProblemEdit.rrProblem.qualityWarningCardNumber">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="checkResult">验岗结果：</label>
                                <input type="button" ng-click="uploadFile('checkResult','checkResultFileId')" value="上传文件">
                                <input type="hidden" id="checkResultFileId" name="checkResultFileId" ng-model="rrProblemEdit.rrProblem.checkResultFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="checkResult" name="checkResult" ng-model="rrProblemEdit.rrProblem.checkResult">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="pfmea">PFMEA：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="pfmea" name="pfmea" ng-model="rrProblemEdit.rrProblem.pfmea">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="cp">CP：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="cp" name="cp" ng-model="rrProblemEdit.rrProblem.cp">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="standardBook">WI：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="standardBook" name="standardBook" ng-model="rrProblemEdit.rrProblem.standardBook">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="alwaysList">始终件表：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="alwaysList" name="alwaysList" ng-model="rrProblemEdit.rrProblem.alwaysList">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="inspectionReferenceBook">检查基准书：</label>
                                <input type="button" ng-click="uploadFile('inspectionReferenceBook','inspectionReferenceBookFileId')" value="上传文件">
                                <input type="hidden" id="inspectionReferenceBookFileId" name="inspectionReferenceBookFileId" ng-model="rrProblemEdit.rrProblem.inspectionReferenceBookFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="inspectionReferenceBook" name="inspectionReferenceBook" ng-model="rrProblemEdit.rrProblem.inspectionReferenceBook">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="analyticReport">解析报告：</label>
                                <input type="button" ng-click="uploadFile('analyticReport','analyticReportFileId')" value="上传文件">
                                <input type="hidden" id="analyticReportFileId" name="analyticReportFileId" ng-model="rrProblemEdit.rrProblem.analyticReportFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="analyticReport" name="analyticReport" ng-model="rrProblemEdit.rrProblem.analyticReport">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="layeredAudit">分层审核：</label>
                                <input type="button" ng-click="uploadFile('layeredAudit','layeredAuditFileId')" value="上传文件">
                                <input type="hidden" id="layeredAuditFileId" name="layeredAuditFileId" ng-model="rrProblemEdit.rrProblem.layeredAuditFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="layeredAudit" name="layeredAudit" ng-model="rrProblemEdit.rrProblem.layeredAudit">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="naPending">NA待定：</label>
                                <input type="button" ng-click="uploadFile('naPending','naPendingFileId')" value="上传文件">
                                <input type="hidden" id="naPendingFileId" name="naPendingFileId" ng-model="rrProblemEdit.rrProblem.naPendingFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="naPending" name="naPending" ng-model="rrProblemEdit.rrProblem.naPending">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="otherInformation">其他资料：</label>
                                <input type="button" ng-click="uploadFile('otherInformation','otherInformationFileId')" value="上传文件">
                                <input type="hidden" id="otherInformationFileId" name="otherInformationFileId" ng-model="rrProblemEdit.rrProblem.otherInformationFileId">
                                <input class="form-control-order form-control clean" style="width: 45%"
                                       id="otherInformation" name="otherInformation" ng-model="rrProblemEdit.rrProblem.otherInformation">
                            </div>
                        </div>
                        <div ng-hide="true">
                                <label  class="control-label" for="expandTrace">展开追踪是否完成：</label>
                                <input class="form-control-order form-control clean" style="width: 50%"
                                       id="expandTrace" name="expandTrace" ng-model="rrProblemEdit.rrProblem.expandTrace">
                                <label  class="control-label" for="artificial">人工：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="artificial" name="artificial" ng-model="rrProblemEdit.rrProblem.artificial">
                                <label  class="control-label" for="materiel">物料：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="materiel" name="materiel" ng-model="rrProblemEdit.rrProblem.materiel">
                                <label  class="control-label" for="speedOfProgress">进度：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="speedOfProgress" name="speedOfProgress" ng-model="rrProblemEdit.rrProblem.speedOfProgress">
                        </div>
                        <hr>
                        <div class="modal-footer">
                            <span ng-hide="action=='add'">
                                <span ng-hide="rrProblemEdit.rrProblem.trackingLevel=='V'">
                                    <button type="button" id="rrProblemDelayConfirm"
                                            class="btn btn-small btn-primary">申请延期
                                    </button>
                                    &nbsp;&nbsp;
                                </span>
                            </span>
                            <button type="button" id="rrProblemEditConfirm"
                                    class="btn btn-small btn-primary">确定
                            </button>
                            &nbsp;&nbsp;
                            <button type="button" id="rrProblemEditCancle"
                                    class="btn  btn-mini btn-info">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 文件列表 -->
<div id="inputModal" class="modal fade" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-body">
        <div class="row">
            <input type="hidden" id="textAreaId">
            <div class="col-md-12">
                <textarea class="form-control-order form-control clean" style="width: 100%"
                          rows="3" id="inputText" name="inputText"
                ng-dblclick="completTextArea()"></textarea>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrProblemEdit.js?version=5"></script>
</html>
