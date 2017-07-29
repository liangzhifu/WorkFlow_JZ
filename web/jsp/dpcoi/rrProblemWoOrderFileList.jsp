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
    <title>RR问题点Dpcoi工单列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="rrProblemWoOrderFileListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="badContent">不良内容：</label>
                        <input type="text" name="badContent" id="badContent" ng-model="rrProblemWoOrderFileList.searchForm.badContent"
                               class="form-control-order form-control" placeholder="不良内容" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemProgress">问题进展：</label>
                        <select id="problemProgress" name="problemProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.problemProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:4"
                                    value="{{dpcoiConfigDate.configValue}}"
                                    >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="speedOfProgress">进度：</label>
                        <select id="speedOfProgress" name="speedOfProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.speedOfProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option value="delayI">delayI</option>
                            <option value="delayII">delayII</option>
                            <option value="delayIII">delayIII</option>
                            <option value="delayIV">delayIV</option>
                            <option value="close">close</option>
                            <option value="follow">follow</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemStatus">状态：</label>
                        <select id="problemStatus" name="problemStatus" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.problemStatus" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:1"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemType">问题类型：</label>
                        <select id="problemType" name="problemType" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.problemType" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:2"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="engineering">工程：</label>
                        <select id="engineering" name="engineering" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.engineering" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:3"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 4px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="customer">客户：</label>
                        <select id="customer" name="customer" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.customer" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:11"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>

                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicle">车型：</label>
                        <input type="text" name="vehicle" id="vehicle" ng-model="rrProblemWoOrderFileList.searchForm.vehicle"
                               class="form-control-order form-control" placeholder="车型" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品名：</label>
                        <select id="productNo" name="productNo" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.productNo" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:5"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateBegin">发生日期：</label>
                        <input type="text" name="happenDateBegin" id="happenDateBegin" ng-model="rrProblemWoOrderFileList.searchForm.happenDateBegin"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateEnd">至：</label>
                        <input type="text" name="happenDateEnd" id="happenDateEnd" ng-model="rrProblemWoOrderFileList.searchForm.happenDateEnd"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="persionLiable">责任人：</label>
                        <input type="text" name="persionLiable" id="persionLiable" ng-model="rrProblemWoOrderFileList.searchForm.persionLiable"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="rrProblemWoOrderFileList.searchForm.productLine"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="severity">严重度：</label>
                        <select id="severity" name="severity" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.severity" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:6"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="responsibleDepartment">责任部门：</label>
                        <select id="responsibleDepartment" name="responsibleDepartment" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderFileList.searchForm.responsibleDepartment" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderFileList.dpcoiConfigList | myFilter:9"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemWoOrderFileSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                    </div>
                </div>
            </div>
            <div class="x-panel-header x-unselectable" id="ext-gen12" style="-moz-user-select: none;">
                <span class="x-panel-header-text" id="ext-gen31">详细数据</span>
            </div>
            <div id="listTable" style="overflow:auto;">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th width="3%" class="x-grid3-header" style="padding: 0px">状态</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">问题编号</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">问题类型</th>
                        <th width="3%" class="x-grid3-header" style="padding: 0px">工程</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">客户</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">车型</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">品名</th>
                        <th width="12%" class="x-grid3-header" style="padding: 0px">不良内容</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">严重度</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">工单类型</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">工单状态</th>
                        <th width="15%" class="x-grid3-header" style="padding: 0px">文件名称</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrProblemWoOrderFileDate in rrProblemWoOrderFileList.rrProblemWoOrderFileList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.problemStatus}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.problemNo}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.problemType}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.engineering}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.customer}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.vehicle}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.productNo}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemWoOrderFileDate.badContent}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.productLine}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.severity}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderType==1">PFMEA</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderType==2">CP</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderType==3">作业标准书</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==1">待确认</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==2">待上传文件</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==3">待审核</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==4">已完成</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==5">审核不通过</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==6">作废</span>
                            <span ng-show="rrProblemWoOrderFileDate.dpcoiWoOrderState==7">不需要变更</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;word-wrap:break-word;word-break:break-all;display:table-cell; vertical-align:middle;">{{rrProblemWoOrderFileDate.fileName}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-hide="rrProblemWoOrderFileDate.dpcoiOrderState == 3">
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderFileList.downloadDpcoiWoOrderFile(rrProblemWoOrderFileDate.fileId);">
                                    <i class="bigger-110"></i>查看
                                </button>
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="rrProblemWoOrderFileList.pageInfo.firstPageDisabled"
                            ng-click="rrProblemWoOrderFileList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemWoOrderFileList.pageInfo.prevPageDisabled"
                            ng-click="rrProblemWoOrderFileList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrProblemWoOrderFileList.pageInfo.page}}&nbsp;/&nbsp;{{rrProblemWoOrderFileList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrProblemWoOrderFileList.pageInfo.nextPageDisabled"
                            ng-click="rrProblemWoOrderFileList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemWoOrderFileList.pageInfo.lastPageDisabled"
                            ng-click="rrProblemWoOrderFileList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrProblemWoOrderFileList.pageInfo.totalCount}}条<input
                        id="rrProblemWoOrderFileList.pageInfo.totalCount" value="{{rrProblemWoOrderFileList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="/WorkFlow/js/module/dpcoi/rrProblemWoOrderFileList.js"></script>
</html>
