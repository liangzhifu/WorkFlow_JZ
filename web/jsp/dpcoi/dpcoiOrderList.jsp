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
    <script>
        var dpcoiAddJurisdiction = "${dpcoiAddJurisdiction}";
        var userId = "${userId}";
    </script>
</head>
<body ng-controller="dpcoiOrderListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateStart">发行日期：</label>
                        <input type="text" name="releaseDateStart" id="releaseDateStart" ng-model="dpcoiOrderList.searchForm.releaseDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateEnd">至：</label>
                        <input type="text" name="releaseDateEnd" id="releaseDateEnd" ng-model="dpcoiOrderList.searchForm.releaseDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateStart">实际切替日：</label>
                        <input type="text" name="realCuttingDateStart" id="realCuttingDateStart" ng-model="dpcoiOrderList.searchForm.realCuttingDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateEnd">至：</label>
                        <input type="text" name="realCuttingDateEnd" id="realCuttingDateEnd" ng-model="dpcoiOrderList.searchForm.realCuttingDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品号：</label>
                        <input type="text" name="productNo" id="productNo" ng-model="dpcoiOrderList.searchForm.productNo"
                               class="form-control-order form-control" placeholder="品号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="delay">超时变更：</label>
                        <select name="delay" id="delay"
                            class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="0"></option>
                            <option value="1">PFMEA</option>
                            <option value="2">CP</option>
                            <option value="3">作业标准书</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="designChangeNo">发行编码：</label>
                        <input type="text" name="designChangeNo" id="designChangeNo" ng-model="dpcoiOrderList.searchForm.designChangeNo"
                               class="form-control-order form-control" placeholder="发行编码" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="dpcoiOrderList.searchForm.vehicleName"
                               class="form-control-order form-control" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="dpcoiOrderList.searchForm.productLine"
                               class="form-control-order form-control" placeholder="生产线" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-4"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiOrderSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiOrderAdd"
                            ng-show="dpcoiOrderList.dpcoiAddJurisdiction == 1">
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
                        <th width="7%" class="x-grid3-header" style="padding: 0px">《设变通知书》编号</th>
                        <th width="8%" class="x-grid3-header" style="padding: 0px">发行编码</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">车种</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">品号</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">希望切替日</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">实际切替日</th>
                        <th width="18%" class="x-grid3-header" style="padding: 0px">变更内容</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">发行日期</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">返回日</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">设计担当</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">量准担当</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">订单状态</th>
                        <th width="8%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiOrderDate in dpcoiOrderList.dpcoiOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.issuedNo}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.designChangeNo}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">
                                <span ng-hide="dpcoiOrderDate.rrProblemId==null" style="background-color:mediumslateblue">{{dpcoiOrderDate.taskOrderNo}}</span>
                                <span ng-show="dpcoiOrderDate.rrProblemId==null">{{dpcoiOrderDate.taskOrderNo}}</span>

                            </span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.vehicleName}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.productNo}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.productLine}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.hopeCuttingDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.realCuttingDate}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 4px 4px;text-align: left;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.changeContent}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiOrderDate.releaseDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.returnDate}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.designAct}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 1">{{dpcoiOrderDate.quasiActName}}</span>
                            <span ng-show="dpcoiOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
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
                            <span ng-hide="dpcoiOrderDate.dpcoiOrderState==3">
                                <span ng-show="dpcoiOrderList.dpcoiAddJurisdiction == 1">
                                    <span ng-hide="dpcoiOrderDate.dpcoiOrderState==2">
                                        <span ng-show="dpcoiOrderList.userId == dpcoiOrderDate.createBy">
                                            <button class="btn btn-small btn-purple" type="button" ng-show="dpcoiOrderDate.dpcoiOrderType==1"
                                                    ng-click="dpcoiOrderList.toVoidDpcoiOrder(dpcoiOrderDate.dpcoiOrderId);" style="margin-left: 4px">
                                                <i class="bigger-110"></i>作废
                                            </button>
                                        </span>
                                    </span>
                                    <span ng-hide="dpcoiOrderDate.taskOrderId">
                                        <a href="/WorkFlow/dpcoiOrder/getDpcoiOrderEditDlg.do?dpcoiOrderId={{dpcoiOrderDate.dpcoiOrderId}}"
                                           class="btn  btn-mini btn-info btn-purple" style="margin-left: 4px">修改</a>
                                    </span>
                                </span>
                            </span>
                            <span ng-hide="dpcoiOrderList.dpcoiAddJurisdiction == 1">
                                <a href="/WorkFlow/dpcoiOrder/getDpcoiOrderDetailDlg.do?dpcoiOrderId={{dpcoiOrderDate.dpcoiOrderId}}"
                                   class="btn  btn-mini btn-info btn-purple" style="margin-left: 4px">详情</a>
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="dpcoiOrderList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiOrderList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiOrderList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiOrderList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiOrderList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiOrderList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiOrderList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiOrderList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiOrderList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiOrderList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiOrderList.pageInfo.totalCount}}条<input
                        id="dpcoiOrderList.pageInfo.totalCount" value="{{dpcoiOrderList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
    <script src="/WorkFlow/js/module/dpcoi/dpcoiOrderList.js"></script>
</html>
