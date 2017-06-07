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
    <title>节假日列表</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="holidayListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="holidayStart">节假日日期：</label>
                        <input type="text" id="holidayStart" ng-model="holidayList.searchForm.holidayStart"
                               class="form-control-order form-control" data-type="date" placeholder="节假日日期(开始)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 5px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="holidayEnd">至：</label>
                        <input type="text" id="holidayEnd" ng-model="holidayList.searchForm.holidayEnd"
                               class="form-control-order form-control" data-type="date" placeholder="节假日日期(结束)" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 45px;height: 25px;">
                    </div>
                    <div class="col-md-6"></div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="holidaySearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="holidayAdd">
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
                        <th width="50%" class="x-grid3-header" style="padding: 0px">节假日</th>
                        <th width="50%" class="x-grid3-header" style="padding: 0px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="holidayDate in holidayList.holidayList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{holidayDate.holiday}}
                        </td>


                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <button class="btn btn-small btn-purple" type="button"
                                    ng-click="holidayList.deleteHoliday(holidayDate.id);" style="margin-left: 4px">
                                <i class="bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="holidayList.pageInfo.firstPageDisabled"
                            ng-click="holidayList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="holidayList.pageInfo.prevPageDisabled"
                            ng-click="holidayList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{holidayList.pageInfo.page}}&nbsp;/&nbsp;{{holidayList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="holidayList.pageInfo.nextPageDisabled"
                            ng-click="holidayList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="holidayList.pageInfo.lastPageDisabled"
                            ng-click="holidayList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{holidayList.pageInfo.totalCount}}条<input
                        id="holidayList.pageInfo.totalCount" value="{{holidayList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增节假日-->
<div id="holidayAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="holidayAddModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="holidayAddModalLabel">添加节假日</h3>
    </div>
    <form id="holidayAddModalForm" method="post" class="form-inline" action="/WorkFlow/holiday/addHoliday.do">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <label  class="control-label" for="holiday">节假日日期：</label>
                    <input type="text" name="holiday" data-type="date"
                           class="form-control-order form-control" data-type="date">
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="holidayAddConfirm"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="holidayAddReturn"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/holidayList.js"></script>
</html>
