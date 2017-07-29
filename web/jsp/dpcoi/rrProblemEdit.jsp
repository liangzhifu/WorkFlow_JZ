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
                                        ng-model="rrProblemEdit.rrProblem.problemType" style="width: 60%">
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
                                <select id="trackingLevel" name="trackingLevel" class="form-control-order form-control"
                                        ng-model="rrProblemEdit.rrProblem.trackingLevel" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:12"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.trackingLevel"
                                            >{{dpcoiConfigDate.configValue}}</option>
                                </select>
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
                                <input class="form-control-order form-control clean" required="required" style="width: 60%"
                                       id="vehicle" name="vehicle" ng-value="rrProblemEdit.rrProblem.vehicle">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="productNo"><span style="color:red;">*</span>品名：</label>
                                <select id="productNo" name="productNo" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.productNo" style="width: 60%">
                                    <option value="">请选择</option>
                                    <option ng-repeat="dpcoiConfigDate in rrProblemEdit.dpcoiConfigList | myFilter:5"
                                            value="{{dpcoiConfigDate.configValue}}" ng-selected="dpcoiConfigDate.configValue==rrProblemEdit.rrProblem.productNo"
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
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <select id="persionLiable" name="persionLiable" class="form-control-order form-control" required="required"
                                        ng-model="rrProblemEdit.rrProblem.persionLiable" multiple="multiple" style="width: 60%">
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
                                <label  class="control-label" for="speedOfProgress">进度：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" ng-disabled="true"
                                       id="speedOfProgress" name="speedOfProgress" ng-model="rrProblemEdit.rrProblem.speedOfProgress">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="reasonForDelay">延期原因及进展：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
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
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
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
                                <label  class="control-label" for="batch"><span style="color:red;">*</span>批次：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" required="required"
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
                                <label  class="control-label" for="serialNumber">品情联编号：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="serialNumber" name="serialNumber" ng-model="rrProblemEdit.rrProblem.serialNumber">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="qualityWarningCardNumber">质量警示卡编号：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="qualityWarningCardNumber" name="qualityWarningCardNumber" ng-model="rrProblemEdit.rrProblem.qualityWarningCardNumber">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="productScale">品推表编号：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="productScale" name="productScale" ng-model="rrProblemEdit.rrProblem.productScale">
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
                                <label  class="control-label" for="equipmentChecklist">设备点检表：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" data-type="dateType3"
                                       id="equipmentChecklist" name="equipmentChecklist" ng-model="rrProblemEdit.rrProblem.equipmentChecklist">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="alwaysList">始终件表：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" data-type="dateType3"
                                       id="alwaysList" name="alwaysList" ng-model="rrProblemEdit.rrProblem.alwaysList">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="inspectionReferenceBook">检查基准书：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" data-type="dateType3"
                                       id="inspectionReferenceBook" name="inspectionReferenceBook" ng-model="rrProblemEdit.rrProblem.inspectionReferenceBook">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="inspectionBook">检查手顺书：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" data-type="dateType3"
                                       id="inspectionBook" name="inspectionBook" ng-model="rrProblemEdit.rrProblem.inspectionBook">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="education">教育议事录：</label>
                                <input class="form-control-order form-control clean" style="width: 60%" data-type="dateType3"
                                       id="education" name="education" ng-model="rrProblemEdit.rrProblem.education">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <label  class="control-label" for="changePoint">变化点管理：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="changePoint" name="changePoint" ng-model="rrProblemEdit.rrProblem.changePoint">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="expandTrace">展开追踪是否完成：</label>
                                <input class="form-control-order form-control clean" style="width: 50%"
                                       id="expandTrace" name="expandTrace" ng-model="rrProblemEdit.rrProblem.expandTrace">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="artificial">人工：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="artificial" name="artificial" ng-model="rrProblemEdit.rrProblem.artificial">
                            </div>
                            <div class="col-md-3">
                                <label  class="control-label" for="materiel">物料：</label>
                                <input class="form-control-order form-control clean" style="width: 60%"
                                       id="materiel" name="materiel" ng-model="rrProblemEdit.rrProblem.materiel">
                            </div>
                        </div>
                        <hr>
                        <div class="modal-footer">
                            <button type="button" id="rrProblemEditConfirm"
                                    class="btn btn-small btn-primary">确定
                            </button>
                            &nbsp;&nbsp;
                            <a href="/WorkFlow/rrProblem/getRRProblemListDlg.do"
                               class="btn  btn-mini btn-info">取消</a>
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
<script src="/WorkFlow/js/module/dpcoi/rrProblemEdit.js"></script>
</html>
