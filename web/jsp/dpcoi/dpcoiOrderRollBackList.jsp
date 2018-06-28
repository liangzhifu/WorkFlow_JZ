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
<body ng-controller="dpcoiOrderRollBackListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="designChangeNo">发行编码：</label>
                        <input type="text" name="designChangeNo" id="designChangeNo" ng-model="dpcoiOrderRollBackList.searchForm.designChangeNo"
                               class="form-control-order form-control" placeholder="发行编码" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="dpcoiOrderRollBackList.searchForm.vehicleName"
                               class="form-control-order form-control" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="dpcoiOrderRollBackList.searchForm.productLine"
                               class="form-control-order form-control" placeholder="生产线" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品号：</label>
                        <input type="text" name="productNo" id="productNo" ng-model="dpcoiOrderRollBackList.searchForm.productNo"
                               class="form-control-order form-control" placeholder="品号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiOrderRollBackSearch">
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
                        <th width="8%" class="x-grid3-header" style="padding: 0px">发行编码</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">车种</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">品号</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="30%" class="x-grid3-header" style="padding: 0px">变更内容</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">订单状态</th>
                        <th width="30%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiOrderDate in dpcoiOrderRollBackList.dpcoiOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{dpcoiOrderDate.designChangeNo}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.vehicleName}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.productNo}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.productLine}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 4px 4px;text-align: left;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.changeContent}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderState==1">
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==1">PFMEA</span>
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==2">CP</span>
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==3">作业标准书</span>
                            </span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderState==2">已完成</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderState==3">作废</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderState==1">
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==1">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                </span>
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==2">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 2)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚CP
                                    </button>
                                </span>
                                <span ng-show="dpcoiOrderDate.dpcoiWoOrderType==3">
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 1)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚PFMEA
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 2)">
                                        <i class="icon-save-file icon-on-right bigger-110"></i>回滚CP
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiOrderRollBackList.rollBack(dpcoiOrderDate.dpcoiOrderId, 3)">
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
                    <button class="" ng-disabled="dpcoiOrderRollBackList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiOrderRollBackList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiOrderRollBackList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiOrderRollBackList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiOrderRollBackList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiOrderRollBackList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiOrderRollBackList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiOrderRollBackList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiOrderRollBackList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiOrderRollBackList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiOrderRollBackList.pageInfo.totalCount}}条<input
                        id="dpcoiOrderRollBackList.pageInfo.totalCount" value="{{dpcoiOrderRollBackList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiOrderRollBackList.js"></script>
</html>
