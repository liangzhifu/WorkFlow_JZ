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
    <title>RR问题点定单列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="rrProblemOrderListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="badContent">不良内容：</label>
                        <input type="text" name="badContent" id="badContent" ng-model="rrProblemOrderList.searchForm.badContent"
                               class="form-control-order form-control" placeholder="不良内容" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemProgress">问题进展：</label>
                        <select id="problemProgress" name="problemProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.problemProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:4"
                                    value="{{dpcoiConfigDate.configValue}}"
                                    >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="speedOfProgress">进度：</label>
                        <select id="speedOfProgress" name="speedOfProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.speedOfProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
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
                                ng-model="rrProblemOrderList.searchForm.problemStatus" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:1"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemType">问题类型：</label>
                        <select id="problemType" name="problemType" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.problemType" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:2"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="engineering">工程：</label>
                        <select id="engineering" name="engineering" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.engineering" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:3"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 4px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="customer">客户：</label>
                        <select id="customer" name="customer" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.customer" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:11"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>

                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicle">车型：</label>
                        <input type="text" name="vehicle" id="vehicle" ng-model="rrProblemOrderList.searchForm.vehicle"
                               class="form-control-order form-control" placeholder="车型" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品名：</label>
                        <select id="productNo" name="productNo" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.productNo" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:5"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateBegin">发生日期：</label>
                        <input type="text" name="happenDateBegin" id="happenDateBegin" ng-model="rrProblemOrderList.searchForm.happenDateBegin"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateEnd">至：</label>
                        <input type="text" name="happenDateEnd" id="happenDateEnd" ng-model="rrProblemOrderList.searchForm.happenDateEnd"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="persionLiable">责任人：</label>
                        <input type="text" name="persionLiable" id="persionLiable" ng-model="rrProblemOrderList.searchForm.persionLiable"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="rrProblemOrderList.searchForm.productLine"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="severity">严重度：</label>
                        <select id="severity" name="severity" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.severity" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:6"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="responsibleDepartment">责任部门：</label>
                        <select id="responsibleDepartment" name="responsibleDepartment" class="form-control-order form-control" required="required"
                                ng-model="rrProblemOrderList.searchForm.responsibleDepartment" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemOrderList.dpcoiConfigList | myFilter:9"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-4"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemOrderSearch">
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
                        <th width="2%" class="x-grid3-header" style="padding: 0px">状态</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">问题编号</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">问题类型</th>
                        <th width="2%" class="x-grid3-header" style="padding: 0px">工程</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">客户</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">车型</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">品名</th>
                        <th width="18%" class="x-grid3-header" style="padding: 0px">不良内容</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="3%" class="x-grid3-header" style="padding: 0px">严重度</th>
                        <th width="12%" class="x-grid3-header" style="padding: 0px">根本原因</th>
                        <th width="12%" class="x-grid3-header" style="padding: 0px">永久对策</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">订单状态</th>
                        <th width="8%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrProblemOrderDate in rrProblemOrderList.rrProblemOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.problemStatus}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.problemNo}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.problemType}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.engineering}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.customer}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.vehicle}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.productNo}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 4px 4px;text-align: left;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.badContent}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.productLine}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.severity}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.rootCause}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.permanentGame}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrProblemOrderDate.rrProblemOrderState==1">
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==1">PFMEA</span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==2">CP</span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==3">作业标准书</span>
                            </span>
                            <span ng-show="rrProblemOrderDate.rrProblemOrderState==2">已完成</span>
                            <span ng-show="rrProblemOrderDate.rrProblemOrderState==3">作废</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <span ng-hide="rrProblemOrderList.dpcoiAddJurisdiction == 1">
                                <a href="/WorkFlow/rrProblemOrder/getRRProblemOrderDetailDlg.do?rrProblemOrderId={{rrProblemOrderDate.rrProblemOrderId}}"
                                   class="btn  btn-mini btn-info btn-purple" style="margin-left: 4px">详情</a>
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="rrProblemOrderList.pageInfo.firstPageDisabled"
                            ng-click="rrProblemOrderList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemOrderList.pageInfo.prevPageDisabled"
                            ng-click="rrProblemOrderList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrProblemOrderList.pageInfo.page}}&nbsp;/&nbsp;{{rrProblemOrderList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrProblemOrderList.pageInfo.nextPageDisabled"
                            ng-click="rrProblemOrderList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemOrderList.pageInfo.lastPageDisabled"
                            ng-click="rrProblemOrderList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrProblemOrderList.pageInfo.totalCount}}条<input
                        id="rrProblemOrderList.pageInfo.totalCount" value="{{rrProblemOrderList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrProblemOrderList.js"></script>
</html>
