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
    <title>Dpcoi工单列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="dpcoiWoOrderFileListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateStart">发行日期：</label>
                        <input type="text" name="releaseDateStart" id="releaseDateStart" ng-model="dpcoiWoOrderFileList.searchForm.releaseDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateEnd">至：</label>
                        <input type="text" name="releaseDateEnd" id="releaseDateEnd" ng-model="dpcoiWoOrderFileList.searchForm.releaseDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateStart">实际切替日：</label>
                        <input type="text" name="realCuttingDateStart" id="realCuttingDateStart" ng-model="dpcoiWoOrderFileList.searchForm.realCuttingDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateEnd">至：</label>
                        <input type="text" name="realCuttingDateEnd" id="realCuttingDateEnd" ng-model="dpcoiWoOrderFileList.searchForm.realCuttingDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品号：</label>
                        <input type="text" name="productNo" id="productNo" ng-model="dpcoiWoOrderFileList.searchForm.productNo"
                               class="form-control-order form-control" placeholder="品号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="designChangeNo">发行编码：</label>
                        <input type="text" name="designChangeNo" id="designChangeNo" ng-model="dpcoiWoOrderFileList.searchForm.designChangeNo"
                               class="form-control-order form-control" placeholder="发行编码" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="fileName">文件名称：</label>
                        <input type="text" name="fileName" id="fileName" ng-model="dpcoiWoOrderFileList.searchForm.fileName"
                               class="form-control-order form-control" placeholder="文件名称" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="dpcoiWoOrderType">工单类型：</label>
                        <select name="dpcoiWoOrderType" id="dpcoiWoOrderType" ng-model="dpcoiWoOrderFileList.searchForm.dpcoiWoOrderType"
                            class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value=""></option>
                            <option value="1">PFMEA</option>
                            <option value="2">CP</option>
                            <option value="2">作业标准书</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="dpcoiWoOrderFileList.searchForm.vehicleName"
                               class="form-control-order form-control" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="dpcoiWoOrderFileList.searchForm.productLine"
                               class="form-control-order form-control" placeholder="生产线" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 25px;height: 25px;">
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiWoOrderFileSearch">
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
                        <th width="10%" class="x-grid3-header" style="padding: 0px">《设变通知书》编号</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">发行编码</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">车种</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">品号</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">发行日期</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">实际切替日</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">工单类型</th>
                        <th width="10%" class="x-grid3-header" style="padding: 0px">工单状态</th>
                        <th width="22%" class="x-grid3-header" style="padding: 0px">文件名称</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiWoOrderFileDate in dpcoiWoOrderFileList.dpcoiWoOrderFileList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.issuedNo}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.designChangeNo}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.vehicleName}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.productNo}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.productLine}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.releaseDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.realCuttingDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderType==1">PFMEA</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderType==2">CP</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderType==3">作业标准书</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==1">待确认</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==2">待上传文件</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==3">待审核</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==4">已完成</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==5">审核不通过</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==6">作废</span>
                            <span ng-show="dpcoiWoOrderFileDate.dpcoiWoOrderState==7">不需要变更</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;word-wrap:break-word;word-break:break-all;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderFileDate.fileName}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-hide="dpcoiWoOrderFileDate.dpcoiOrderState == 3">
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderFileList.downloadDpcoiWoOrderFile(dpcoiWoOrderFileDate.fileId);">
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
                    <button class="" ng-disabled="dpcoiWoOrderFileList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiWoOrderFileList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiWoOrderFileList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiWoOrderFileList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiWoOrderFileList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiWoOrderFileList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiWoOrderFileList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiWoOrderFileList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiWoOrderFileList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiWoOrderFileList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiWoOrderFileList.pageInfo.totalCount}}条<input
                        id="dpcoiWoOrderFileList.pageInfo.totalCount" value="{{dpcoiWoOrderFileList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiWoOrderFileList.js"></script>
</html>
