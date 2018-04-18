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
    <title>RR问题点Delay统计</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="rrDelayStatisticsController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="dateBegin">开始日期：</label>
                        <input type="text" name="dateBegin" id="dateBegin" ng-model="rrDelayStatistics.searchForm.dateBegin"
                               class="form-control-order form-control" data-type="date" placeholder="开始日期)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="dateEnd">结束日期：</label>
                        <input type="text" name="dateEnd" id="dateEnd" ng-model="rrDelayStatistics.searchForm.dateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="结束日期" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-6">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrDelayStatisticsSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <label  class="control-label">delay总数：{{rrDelayStatistics.statisticsCount}}</label>
            </div>
            <div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="x-grid3-header" width="50%">责任人</th>
                        <th class="x-grid3-header" width="50%">delay次数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="statisticsDate in rrDelayStatistics.persionLiable.mapList">
                        <td>{{statisticsDate.userName}}</td>
                        <td>
                            <a href="javascript:void(0)" ng-click="showRRDelayStatisticsList(statisticsDate.userName);" style="color: red">
                                &nbsp;&nbsp;&nbsp;{{statisticsDate.num}}&nbsp;&nbsp;&nbsp;
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- RR问题点延时列表 -->
<div id="rrDelayListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="rrDelayListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="rrDelayListModalLabel">RR问题点Delay列表</h3>
    </div>
    <form id="rrDelayListModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div style="height: 400px; overflow: scroll;">
                    <table id="fileListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="20%">问题编码</th>
                            <th width="20%">状态</th>
                            <th width="20%">延迟日期</th>
                            <th width="20%">问题进展</th>
                            <th width="20%">追踪等级</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="delayDate in rrDelayStatistics.rrDelay.mapList">
                            <td>{{delayDate.problemNo}}</td>
                            <td>{{delayDate.problemStatus}}</td>
                            <td>{{delayDate.delayDate}}</td>
                            <td>{{delayDate.problemProgress}}</td>
                            <td>{{delayDate.trackingLevel}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrDelayStatistics.js"></script>
</html>
