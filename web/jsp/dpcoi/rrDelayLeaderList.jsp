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
    <title>RR责任人领导配置</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="rrDelayLeaderListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="userName">姓名：</label>
                        <input type="text" name="userName" id="userName" ng-model="rrDelayLeaderList.searchForm.userName"
                               class="form-control-order form-control" placeholder="姓名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="leaderName">领导：</label>
                        <input type="text" name="leaderName" id="leaderName" ng-model="rrDelayLeaderList.searchForm.leaderName"
                               class="form-control-order form-control" placeholder="领导" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="leaderType">领导类型：</label>
                        <select id="leaderType" name="leaderType" ng-model="rrDelayLeaderList.searchForm.leaderType"
                                class="form-control-order form-control" style="width: 50%;margin-left: 0%;padding: 1px 1px;font-size: 12px;height: 25px;">
                            <option value=""></option>
                            <option value="1">科长</option>
                            <option value="2">部长</option>
                            <option value="3">总经理</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrDelayLeaderSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>

                        <button class="btn btn-small btn-purple" type="button" id="rrDelayLeaderAdd">
                            <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
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
                        <th width="25%" class="x-grid3-header" style="padding: 0px">姓名</th>
                        <th width="25%" class="x-grid3-header" style="padding: 0px">领导</th>
                        <th width="25%" class="x-grid3-header" style="padding: 0px">领导类型</th>
                        <th width="25%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrDelayLeaderDate in rrDelayLeaderList.rrDelayLeaderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrDelayLeaderDate.userName}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrDelayLeaderDate.leaderName}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrDelayLeaderDate.leaderType == '1'">科长</span>
                            <span ng-show="rrDelayLeaderDate.leaderType == '2'">部长</span>
                            <span ng-show="rrDelayLeaderDate.leaderType == '3'">总经理</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <button class="btn btn-small btn-purple" type="button" ng-click="rrDelayLeaderList.deleteRRDelayLeader(rrDelayLeaderDate.id);" style="margin-left: 4px">
                                <i class="bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="rrDelayLeaderList.pageInfo.firstPageDisabled"
                            ng-click="rrDelayLeaderList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrDelayLeaderList.pageInfo.prevPageDisabled"
                            ng-click="rrDelayLeaderList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrDelayLeaderList.pageInfo.page}}&nbsp;/&nbsp;{{rrDelayLeaderList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrDelayLeaderList.pageInfo.nextPageDisabled"
                            ng-click="rrDelayLeaderList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrDelayLeaderList.pageInfo.lastPageDisabled"
                            ng-click="rrDelayLeaderList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrDelayLeaderList.pageInfo.totalCount}}条<input
                        id="rrDelayLeaderList.pageInfo.totalCount" value="{{rrDelayLeaderList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增RR责任人领导 -->
<div id="rrDelayLeaderAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="rrDelayLeaderAddModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="rrDelayLeaderAddModalLabel">新增RR责任人领导 </h3>
    </div>
    <form id="rrDelayLeaderAddModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-4">
                    <label  class="control-label" for="rrUserId">姓名：</label>
                    <input type="hidden" id="rrUserId" name="rrUserId" required="required" formtarget="rrUserName">
                    <input class="form-control-order form-control clean"  style="width: 70%;"
                           id="rrUserName" name="rrUserName">
                </div>
                <div class="col-md-4">
                    <label  class="control-label" for="rrLeaderId">领导：</label>
                    <input type="hidden" id="rrLeaderId" name="rrLeaderId" required="required" formtarget="rrLeaderName">
                    <input class="form-control-order form-control clean"  style="width: 70%;"
                           id="rrLeaderName" name="rrLeaderName">
                </div>
                <div class="col-md-4">
                    <label  class="control-label" for="rrLeaderType">领导类型：</label>
                    <select id="rrLeaderType" name="rrLeaderType" required="required"
                            class="form-control-order form-control" style="width: 70%;">
                        <option value=""></option>
                        <option value="1">科长</option>
                        <option value="2">部长</option>
                        <option value="3">总经理</option>
                    </select>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="delayLeaderAdd" ng-click="rrDelayLeaderList.rrDelayLeaderAdd();"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="delayLeaderCancle"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrDelayLeaderList.js"></script>
</html>
