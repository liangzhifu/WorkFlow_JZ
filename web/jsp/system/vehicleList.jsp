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
    <title>Dpcoi定单列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="vehicleListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="vehicleList.searchForm.vehicleName"
                               class="form-control-order form-control" data-type="date" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-8"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="vehicleSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="vehicleAdd">
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
                        <th width="50%" class="x-grid3-header" style="padding: 0px">车种名</th>
                        <th width="50%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="vehicleDate in vehicleList.vehicleList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{vehicleDate.vehicleName}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <button class="btn btn-small btn-purple" type="button"
                                    ng-click="vehicleList.deleteVehicle(vehicleDate.id);" style="margin-left: 4px">
                                <i class="bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="vehicleList.pageInfo.firstPageDisabled"
                            ng-click="vehicleList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="vehicleList.pageInfo.prevPageDisabled"
                            ng-click="vehicleList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{vehicleList.pageInfo.page}}&nbsp;/&nbsp;{{vehicleList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="vehicleList.pageInfo.nextPageDisabled"
                            ng-click="vehicleList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="vehicleList.pageInfo.lastPageDisabled"
                            ng-click="vehicleList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{vehicleList.pageInfo.totalCount}}条<input
                        id="vehicleList.pageInfo.totalCount" value="{{vehicleList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增车种-->
<div id="vehicleAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="vehicleAddModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="vehicleAddModalLabel">添加车种</h3>
    </div>
    <form id="vehicleAddModalForm" method="post" class="form-inline" action="/WorkFlow/vehicle/addVehicle.do">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <label  class="control-label" for="vehicleName">车种名：</label>
                    <input type="text" name="vehicleName"
                           class="form-control-order form-control" data-type="date">
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="vehicleAddConfirm"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="vehicleAddReturn"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/system/vehicleList.js"></script>
</html>
