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
    <title>RR问题点定单回滚列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="rrProblemOrderRollBackListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemNo">问题编号：</label>
                        <input type="text" name="problemNo" id="problemNo" ng-model="rrProblemOrderRollBackList.searchForm.problemNo"
                               class="form-control-order form-control" placeholder="问题编号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-8"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemOrderRollBackSearch">
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
                        <th width="33%" class="x-grid3-header" style="padding: 0px">问题编号</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px">订单状态</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrProblemOrderDate in rrProblemOrderRollBackList.rrProblemOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemOrderDate.problemNo}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrProblemOrderDate.dpcoiOrderState==1">
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==1">PFMEA</span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==2">CP</span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==3">作业标准书</span>
                            </span>
                            <span ng-show="rrProblemOrderDate.dpcoiOrderState==2">已完成</span>
                            <span ng-show="rrProblemOrderDate.dpcoiOrderState==3">作废</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <span ng-show="rrProblemOrderDate.dpcoiOrderState==1">
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==1">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                </span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==2">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 2)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚CP
                                    </button>
                                </span>
                                <span ng-show="rrProblemOrderDate.dpcoiWoOrderType==3">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 2)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚CP
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemOrderRollBackList.rollBack(rrProblemOrderDate.dpcoiOrderId, 3)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚作业标准书
                                    </button>
                                </span>
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="rrProblemOrderRollBackList.pageInfo.firstPageDisabled"
                            ng-click="rrProblemOrderRollBackList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemOrderRollBackList.pageInfo.prevPageDisabled"
                            ng-click="rrProblemOrderRollBackList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrProblemOrderRollBackList.pageInfo.page}}&nbsp;/&nbsp;{{rrProblemOrderRollBackList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrProblemOrderRollBackList.pageInfo.nextPageDisabled"
                            ng-click="rrProblemOrderRollBackList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemOrderRollBackList.pageInfo.lastPageDisabled"
                            ng-click="rrProblemOrderRollBackList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrProblemOrderRollBackList.pageInfo.totalCount}}条<input
                        id="rrProblemOrderRollBackList.pageInfo.totalCount" value="{{rrProblemOrderRollBackList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrProblemOrderRollBackList.js"></script>
</html>
