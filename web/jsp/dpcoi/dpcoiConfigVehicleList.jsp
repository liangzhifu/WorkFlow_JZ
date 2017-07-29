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
    <title>车型清单</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="dpcoiConfigVehicleListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="dpcoiConfigCodeId">客户：</label>
                        <select id="dpcoiConfigCodeId" ng-model="dpcoiConfigVehicleList.searchForm.configId"
                                class="form-control-order form-control" style="width: 70%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                            <option ng-repeat="dpcoiConfigDate in dpcoiConfigVehicleList.dpcoiConfigList" value="{{dpcoiConfigDate.configId}}">{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="configVehicleValue">车型：</label>
                        <input type="text" id="configVehicleValue" ng-model="dpcoiConfigVehicleList.searchForm.configVehicleValue"
                               class="form-control-order form-control" placeholder="车型" style="width: 70%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-6">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiConfigVehicleSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>

                        <button class="btn btn-small btn-purple" type="button" id="dpcoiConfigVehicleAdd">
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
                        <th width="33%" class="x-grid3-header" style="padding: 0px">车型</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px">客户</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiConfigVehicleDate in dpcoiConfigVehicleList.dpcoiConfigVehicleList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{dpcoiConfigVehicleDate.configVehicleValue}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{dpcoiConfigVehicleDate.configValue}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiConfigVehicleList.deleteDpcoiConfigVehicle(dpcoiConfigVehicleDate.configVehicleId);" style="margin-left: 4px">
                                <i class="bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="dpcoiConfigVehicleList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiConfigVehicleList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiConfigVehicleList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiConfigVehicleList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiConfigVehicleList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiConfigVehicleList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiConfigVehicleList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiConfigVehicleList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiConfigVehicleList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiConfigVehicleList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiConfigVehicleList.pageInfo.totalCount}}条<input
                        id="dpcoiConfigVehicleList.pageInfo.totalCount" value="{{dpcoiConfigVehicleList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增下拉菜单选项 -->
<div id="dpcoiConfigVehicleAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="dpcoiConfigVehicleAddModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="dpcoiConfigVehicleAddModalLabel">新增下拉菜单选项</h3>
    </div>
    <form id="dpcoiConfigVehicleAddModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <label  class="control-label" for="dpcoiConfigId">客户：</label>
                    <select id="dpcoiConfigId" class="form-control-order form-control" ng-model="dpcoiConfigVehicleList.dpcoiConfigVehicle.configId">
                        <option ng-repeat="dpcoiConfigDate in dpcoiConfigVehicleList.dpcoiConfigList" value="{{dpcoiConfigDate.configId}}">{{dpcoiConfigDate.configValue}}</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label  class="control-label" for="dpcoiConfigVehicleValue">车型：</label>
                    <input class="form-control-order form-control clean" id="dpcoiConfigVehicleValue" ng-model="dpcoiConfigVehicleList.dpcoiConfigVehicle.configVehicleValue">
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="fliePass" ng-click="dpcoiConfigVehicleList.dpcoiConfigVehicleAdd();"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="flieListCost"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiConfigVehicleList.js"></script>
</html>
