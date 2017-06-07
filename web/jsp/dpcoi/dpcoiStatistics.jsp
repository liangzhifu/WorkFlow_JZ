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
    <title>Dpcoi统计</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="dpcoiStatisticsController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateStart">发行日期：</label>
                        <input type="text" name="releaseDateStart" id="releaseDateStart" ng-model="dpcoiStatistics.searchForm.releaseDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateEnd">至：</label>
                        <input type="text" name="releaseDateEnd" id="releaseDateEnd" ng-model="dpcoiStatistics.searchForm.releaseDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateStart">实际切替日：</label>
                        <input type="text" name="realCuttingDateStart" id="realCuttingDateStart" ng-model="dpcoiStatistics.searchForm.realCuttingDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateEnd">至：</label>
                        <input type="text" name="realCuttingDateEnd" id="realCuttingDateEnd" ng-model="dpcoiStatistics.searchForm.realCuttingDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品号：</label>
                        <input type="text" name="productNo" id="productNo" ng-model="dpcoiStatistics.searchForm.productNo"
                               class="form-control-order form-control" placeholder="品号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="taskOrderNo">4M发行编号：</label>
                        <input type="text" name="taskOrderNo" id="taskOrderNo" ng-model="dpcoiStatistics.searchForm.taskOrderNo"
                               class="form-control-order form-control" placeholder="4M发行编号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 5px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="dpcoiStatistics.searchForm.vehicleName"
                               class="form-control-order form-control" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="dpcoiStatistics.searchForm.productLine"
                               class="form-control-order form-control" placeholder="生产线" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-6">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiStatisticsSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                    </div>
                </div>
            </div>
                <div class="col-md-12">
                    <label  class="control-label">变更总量：{{dpcoiStatistics.dpcoiOrderCount}}</label>
                </div>
            <div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="x-grid3-header" width="20%">类型</th>
                        <th class="x-grid3-header" width="20%">变更总数</th>
                        <th class="x-grid3-header" width="20%">完成数量</th>
                        <th class="x-grid3-header" width="20%">完成(超时)数量</th>
                        <th class="x-grid3-header" width="20%">未完成数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="statisticsDate in dpcoiStatistics.dpcoiWoOrderCount">
                        <td>
                            <span ng-show="statisticsDate.dpcoiWoOrderType==1">PFMEA</span>
                            <span ng-show="statisticsDate.dpcoiWoOrderType==2">CP</span>
                            <span ng-show="statisticsDate.dpcoiWoOrderType==3">作业标准书</span>
                        </td>
                        <td>{{statisticsDate.dpcoiWoOrderCount}}</td>
                        <td>{{statisticsDate.dpcoiWoOrderComplete}}</td>
                        <td>
                            <a href="javascript:void(0)" ng-click="showWoOrderCompleteDelay(statisticsDate.dpcoiWoOrderType);" style="color: red">
                                &nbsp;&nbsp;&nbsp;{{statisticsDate.dpcoiWoOrderCompleteDelay}}&nbsp;&nbsp;&nbsp;
                            </a>
                        </td>
                        <td>
                            <a href="javascript:void(0)" ng-click="showWoOrderNoComplete(statisticsDate.dpcoiWoOrderType);" style="color: red">
                                &nbsp;&nbsp;&nbsp;{{statisticsDate.dpcoiWoOrderNoComplete}}&nbsp;&nbsp;&nbsp;
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 定单列表 -->
<div id="orderListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="orderListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="orderListModalLabel">定单列表</h3>
    </div>
    <form id="orderListModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div style="height: 400px; overflow: scroll;">
                    <table id="fileListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="20%">《设变通知书》编号</th>
                            <th width="20%">设变号</th>
                            <th width="20%">车种</th>
                            <th width="20%">品号</th>
                            <th width="20%">生产线</th>
                            <th width="20%">希望切替日</th>
                            <th width="20%">实际切替日</th>
                            <th width="40%">变更内容</th>
                            <th width="20%">发行日期</th>
                            <th width="20%">返回日</th>
                            <th width="15%">设计担当</th>
                            <th width="15%">量准担当</th>
                            <th width="20%">4M发行编号</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="dpcoiOrderDate in dpcoiStatistics.dpcoiOrderList">
                            <td>{{dpcoiOrderDate.issuedNo}}</td>
                            <td>{{dpcoiOrderDate.designChangeNo}}</td>
                            <td>{{dpcoiOrderDate.vehicleName}}</td>
                            <td>{{dpcoiOrderDate.productNo}}</td>
                            <td>{{dpcoiOrderDate.productLine}}</td>
                            <td>{{dpcoiOrderDate.hopeCuttingDate}}</td>
                            <td>{{dpcoiOrderDate.realCuttingDate}}</td>
                            <td>{{dpcoiOrderDate.changeContent}}</td>
                            <td>{{dpcoiOrderDate.releaseDate}}</td>
                            <td>{{dpcoiOrderDate.returnDate}}</td>
                            <td>{{dpcoiOrderDate.designAct}}</td>
                            <td>{{dpcoiOrderDate.quasiActName}}</td>
                            <td>{{dpcoiOrderDate.taskOrderNo}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiStatistics.js"></script>
</html>
