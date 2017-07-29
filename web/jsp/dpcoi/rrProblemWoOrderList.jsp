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
<body ng-controller="rrProblemWoOrderListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="badContent">不良内容：</label>
                        <input type="text" name="badContent" id="badContent" ng-model="rrProblemWoOrderList.searchForm.badContent"
                               class="form-control-order form-control" placeholder="不良内容" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemProgress">问题进展：</label>
                        <select id="problemProgress" name="problemProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.problemProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:4"
                                    value="{{dpcoiConfigDate.configValue}}"
                                    >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="speedOfProgress">进度：</label>
                        <select id="speedOfProgress" name="speedOfProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.speedOfProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option value="delayI">delayI</option>
                            <option value="delayII">delayII</option>
                            <option value="delayIII">delayIII</option>
                            <option value="delayIV">delayIV</option>
                            <option value="close">close</option>
                            <option value="follow">follow</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemStatus">状态：</label>
                        <select id="problemStatus" name="problemStatus" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.problemStatus" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:1"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemType">问题类型：</label>
                        <select id="problemType" name="problemType" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.problemType" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:2"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="engineering">工程：</label>
                        <select id="engineering" name="engineering" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.engineering" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:3"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 4px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="customer">客户：</label>
                        <select id="customer" name="customer" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.customer" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:11"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>

                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicle">车型：</label>
                        <input type="text" name="vehicle" id="vehicle" ng-model="rrProblemWoOrderList.searchForm.vehicle"
                               class="form-control-order form-control" placeholder="车型" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品名：</label>
                        <select id="productNo" name="productNo" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.productNo" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:5"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateBegin">发生日期：</label>
                        <input type="text" name="happenDateBegin" id="happenDateBegin" ng-model="rrProblemWoOrderList.searchForm.happenDateBegin"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateEnd">至：</label>
                        <input type="text" name="happenDateEnd" id="happenDateEnd" ng-model="rrProblemWoOrderList.searchForm.happenDateEnd"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="persionLiable">责任人：</label>
                        <input type="text" name="persionLiable" id="persionLiable" ng-model="rrProblemWoOrderList.searchForm.persionLiable"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="rrProblemWoOrderList.searchForm.productLine"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="severity">严重度：</label>
                        <select id="severity" name="severity" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.severity" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:6"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="responsibleDepartment">责任部门：</label>
                        <select id="responsibleDepartment" name="responsibleDepartment" class="form-control-order form-control" required="required"
                                ng-model="rrProblemWoOrderList.searchForm.responsibleDepartment" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemWoOrderList.dpcoiConfigList | myFilter:9"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemWoOrderSearch">
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
                        <th width="2%" class="x-grid3-header" style="padding: 0px">状态</th>
                        <th width="7%" class="x-grid3-header" style="padding: 0px">问题编号</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">问题类型</th>
                        <th width="2%" class="x-grid3-header" style="padding: 0px">工程</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">客户</th>
                        <th width="4%" class="x-grid3-header" style="padding: 0px">车型</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">品名</th>
                        <th width="14%" class="x-grid3-header" style="padding: 0px">不良内容</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">生产线</th>
                        <th width="3%" class="x-grid3-header" style="padding: 0px">严重度</th>
                        <th width="9%" class="x-grid3-header" style="padding: 0px">根本原因</th>
                        <th width="9%" class="x-grid3-header" style="padding: 0px">永久对策</th>
                        <th width="6%" class="x-grid3-header" style="padding: 0px">工单类型</th>
                        <th width="18%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrProblemWoOrderDate in rrProblemWoOrderList.rrProblemWoOrderList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.problemStatus}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.problemNo}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.problemType}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.engineering}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.customer}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.vehicle}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.productNo}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 4px 4px;text-align: left;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.badContent}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.productLine}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.severity}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.rootCause}}
                        </td>
                        <td style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{rrProblemWoOrderDate.permanentGame}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderType==1">PFMEA</span>
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderType==2">CP</span>
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderType==3">作业标准书</span>
                        </td>
                        <!--<td style="white-space:normal;">{{rrProblemWoOrderDate.remark}}</td>-->
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderState == 1">
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.confirmWoOrder(rrProblemWoOrderDate.rrProblemWoOrderId, 1); " style="margin-left: 4px">
                                    <i class="bigger-110"></i>需要变更
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.confirmWoOrder(rrProblemWoOrderDate.rrProblemWoOrderId, 0);">
                                    <i class="bigger-110"></i>不需要变更
                                </button>
                            </span>
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderState == 2">
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.uploadFile(rrProblemWoOrderDate.rrProblemWoOrderId);" style="margin-left: 4px">
                                    <i class="bigger-110"></i>上传文件
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.fileList(rrProblemWoOrderDate.rrProblemWoOrderId, 1);">
                                    <i class="bigger-110"></i>文件列表
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.fileCompleteWoOrder(rrProblemWoOrderDate.rrProblemWoOrderId);">
                                    <i class="bigger-110"></i>变更完成
                                </button>
                            </span>
                            <span ng-show="rrProblemWoOrderDate.rrProblemWoOrderState == 3">
                                <button class="btn btn-small btn-purple" type="button" ng-click="rrProblemWoOrderList.fileList(rrProblemWoOrderDate.rrProblemWoOrderId, 2);" style="margin-left: 4px">
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
                    <button class="" ng-disabled="rrProblemWoOrderList.pageInfo.firstPageDisabled"
                            ng-click="rrProblemWoOrderList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemWoOrderList.pageInfo.prevPageDisabled"
                            ng-click="rrProblemWoOrderList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrProblemWoOrderList.pageInfo.page}}&nbsp;/&nbsp;{{rrProblemWoOrderList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrProblemWoOrderList.pageInfo.nextPageDisabled"
                            ng-click="rrProblemWoOrderList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemWoOrderList.pageInfo.lastPageDisabled"
                            ng-click="rrProblemWoOrderList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrProblemWoOrderList.pageInfo.totalCount}}条<input
                        id="rrProblemWoOrderList.pageInfo.totalCount" value="{{rrProblemWoOrderList.pageInfo.totalCount}}" type="hidden"></span>
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
                        <tr ng-repeat="rrProblemWoOrderFileData in rrProblemWoOrderFile.dpcoiWoOrderFileList">
                            <td>
                                <span ng-show="rrProblemWoOrderFileData.woFileState==0">
                                    <input title="" type='checkbox' class='form-control-order form-checkbox-mypage' name='rrProblemWoOrderFileCheckbox' value='{{rrProblemWoOrderFileData.dpcoiWoOrderFileId}}'>
                                </span>
                            </td>
                            <td>{{rrProblemWoOrderFileData.fileName}}</td>
                            <td>{{rrProblemWoOrderFileData.createByName}}</td>
                            <td>{{rrProblemWoOrderFileData.fileCreateDateStr}}</td>
                            <td>
                                <span ng-show="rrProblemWoOrderFileData.woFileState==0">未审核</span>
                                <span ng-show="rrProblemWoOrderFileData.woFileState==1">通过</span>
                                <span ng-show="rrProblemWoOrderFileData.woFileState==2">不通过</span>
                            </td>
                            <td>
                                <button class="btn btn-small btn-purple" type="button"
                                        ng-click="rrProblemWoOrderFile.downloadDpcoiWoOrderFile(rrProblemWoOrderFileData.fileId)">
                                    <i class="bigger-110"></i>查看
                                </button>
                                <span ng-show="rrProblemWoOrderFileData.woFileState==0">
                                    &nbsp;&nbsp;
                                    <button class="btn btn-small btn-purple" type="button"
                                            ng-show="rrProblemWoOrderFile.action == 'uploadFile'"
                                            ng-click="rrProblemWoOrderFile.deleteDpcoiWoOrderFile(rrProblemWoOrderFileData.dpcoiWoOrderFileId)">
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
                <button type="button" id="fliePass" ng-click="rrProblemWoOrderList.managerConfirmWoOrder(1);"
                        ng-show="rrProblemWoOrderFile.action == 'managerConfirm'"
                        class="btn btn-small btn-primary">通过
                </button>
                <button type="button" id="flieNoPass" ng-click="rrProblemWoOrderList.managerConfirmWoOrder(0);"
                        ng-show="rrProblemWoOrderFile.action == 'managerConfirm'"
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
<script src="/WorkFlow/js/module/dpcoi/rrProblemWoOrderList.js"></script>
</html>
