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
<body ng-controller="dpcoiWoOrderListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateStart">发行日期：</label>
                        <input type="text" name="releaseDateStart" id="releaseDateStart" ng-model="dpcoiWoOrderList.searchForm.releaseDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="releaseDateEnd">至：</label>
                        <input type="text" name="releaseDateEnd" id="releaseDateEnd" ng-model="dpcoiWoOrderList.searchForm.releaseDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="发行日期(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateStart">实际切替日：</label>
                        <input type="text" name="realCuttingDateStart" id="realCuttingDateStart" ng-model="dpcoiWoOrderList.searchForm.realCuttingDateStart"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 10px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="realCuttingDateEnd">至：</label>
                        <input type="text" name="realCuttingDateEnd" id="realCuttingDateEnd" ng-model="dpcoiWoOrderList.searchForm.realCuttingDateEnd"
                               class="form-control-order form-control" data-type="date" placeholder="实际切替日(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品号：</label>
                        <input type="text" name="productNo" id="productNo" ng-model="dpcoiWoOrderList.searchForm.productNo"
                               class="form-control-order form-control" placeholder="品号" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 35px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicleName">车种名：</label>
                        <input type="text" name="vehicleName" id="vehicleName" ng-model="dpcoiWoOrderList.searchForm.vehicleName"
                               class="form-control-order form-control" placeholder="车种名" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="dpcoiWoOrderList.searchForm.productLine"
                               class="form-control-order form-control" placeholder="生产线" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-8">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiWoOrderSearch">
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
                        <th width="7%" class="x-grid3-header" style="padding: 0px">《设变通知书》编号</th>
                        <th width="8%" class="x-grid3-header" style="padding: 0px">发行编码</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">车种</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">品号</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">希望切替日</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">实际切替日</th>
                        <th width="9%" class="x-grid3-header" style="padding: 0px">变更内容</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">发行日期</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">返回日</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">设计担当</th>
                        <th width="5%" class="x-grid3-header" style="padding: 0px">量准担当</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">工单类型</th>
                        <!--<th width="20%">备注</th>-->
                        <th width="18%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiWoOrderDate in dpcoiWoOrderList.dpcoiWoOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.issuedNo}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.designChangeNo}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">{{dpcoiWoOrderDate.taskOrderNo}}</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.vehicleName}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.productNo}}</td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.productLine}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.hopeCuttingDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.realCuttingDate}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;padding: 4px 4px;text-align: left;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.changeContent}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{dpcoiWoOrderDate.releaseDate}}</td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.returnDate}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.designAct}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 1">{{dpcoiWoOrderDate.quasiActName}}</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiOrderType == 2">/</span>
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderType==1">PFMEA</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderType==2">CP</span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderType==3">作业标准书</span>
                        </td>
                        <!--<td style="white-space:normal;">{{dpcoiWoOrderDate.remark}}</td>-->
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderState == 1">
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.confirmWoOrder(dpcoiWoOrderDate.dpcoiWoOrderId, 1); " style="margin-left: 4px">
                                    <i class="bigger-110"></i>需要变更
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.confirmWoOrder(dpcoiWoOrderDate.dpcoiWoOrderId, 0);">
                                    <i class="bigger-110"></i>不需要变更
                                </button>
                            </span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderState == 2">
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.uploadFile(dpcoiWoOrderDate.dpcoiWoOrderId);" style="margin-left: 4px">
                                    <i class="bigger-110"></i>上传文件
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.fileList(dpcoiWoOrderDate.dpcoiWoOrderId, 1);">
                                    <i class="bigger-110"></i>文件列表
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.fileCompleteWoOrder(dpcoiWoOrderDate.dpcoiWoOrderId);">
                                    <i class="bigger-110"></i>变更完成
                                </button>
                            </span>
                            <span ng-show="dpcoiWoOrderDate.dpcoiWoOrderState == 3">
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiWoOrderList.fileList(dpcoiWoOrderDate.dpcoiWoOrderId, 2);" style="margin-left: 4px">
                                    <i class="bigger-110"></i>文件列表
                                </button>
                            </span>

                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="dpcoiWoOrderList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiWoOrderList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiWoOrderList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiWoOrderList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiWoOrderList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiWoOrderList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiWoOrderList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiWoOrderList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiWoOrderList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiWoOrderList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiWoOrderList.pageInfo.totalCount}}条<input
                        id="dpcoiWoOrderList.pageInfo.totalCount" value="{{dpcoiWoOrderList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 文件列表 -->
<div id="fileListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="fileListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="fileListModalLabel">文件列表</h3>
    </div>
    <form id="fileListModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div style="height: 200px; overflow: scroll;">
                    <table id="fileListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="8%">多选框</th>
                            <th width="42%">文件名称</th>
                            <th width="10%">创建人</th>
                            <th width="15%">创建时间</th>
                            <th width="10%">审核状态</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="dpcoiWoOrderFileData in dpcoiWoOrderFile.dpcoiWoOrderFileList">
                            <td>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==0">
                                    <input title="" type='checkbox' class='form-control-order form-checkbox-mypage' name='dpcoiWoOrderFileCheckbox' value='{{dpcoiWoOrderFileData.dpcoiWoOrderFileId}}'>
                                </span>
                            </td>
                            <td>{{dpcoiWoOrderFileData.fileName}}</td>
                            <td>{{dpcoiWoOrderFileData.createByName}}</td>
                            <td>{{dpcoiWoOrderFileData.fileCreateDateStr}}</td>
                            <td>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==0">未审核</span>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==1">通过</span>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==2">不通过</span>
                            </td>
                            <td>
                                <button class="btn btn-small btn-purple" type="button"
                                        ng-click="dpcoiWoOrderFile.downloadDpcoiWoOrderFile(dpcoiWoOrderFileData.fileId)">
                                    <i class="bigger-110"></i>查看
                                </button>
                                <span ng-show="dpcoiWoOrderFileData.woFileState==0">
                                    &nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button"
                                            ng-show="dpcoiWoOrderFile.action == 'uploadFile'"
                                            ng-click="dpcoiWoOrderFile.deleteDpcoiWoOrderFile(dpcoiWoOrderFileData.dpcoiWoOrderFileId)">
                                        <i class="bigger-110"></i>删除
                                    </button>
                                </span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="fliePass" ng-click="dpcoiWoOrderList.managerConfirmWoOrder(1);"
                        ng-show="dpcoiWoOrderFile.action == 'managerConfirm'"
                        class="btn btn-small btn-primary">通过
                </button>
                <button type="button" id="flieNoPass" ng-click="dpcoiWoOrderList.managerConfirmWoOrder(0);"
                        ng-show="dpcoiWoOrderFile.action == 'managerConfirm'"
                        class="btn btn-small btn-primary">不通过
                </button>
                <button type="button" id="flieListCost"
                        class="btn btn-small btn-primary" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/dpcoiWoOrderList.js"></script>
</html>
