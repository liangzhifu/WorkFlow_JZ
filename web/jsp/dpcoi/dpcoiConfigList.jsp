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
    <title>Dpcoi下拉菜单配置</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
    <script type="text/javascript">
        var contextPath = "${pageContext.request.contextPath}";

        function impExcel(){
            var html="<form method='post' id='excelForm' enctype='multipart/form-data' action='/WorkFlow/dpcoiConfig/updateFileExcel.do'>" +
                "<a class='uploadFile button button-primary button-rounded button-small' href='#'>" +
                "<input type='file' accept='application/vnd.ms-excel' onchange='updateFileExcelChange()' name='excelFile' id='excelFile'/><i class='glyphicon glyphicon-search'></i>浏览" +
                "</a>"+
                "</form>";
            var myModal = new jBox('Modal', {
                width: 150,
                title: 'Excel导入数据',
                content: html,
                onCloseComplete:function(){
                    myModal.destroy();
                }
            }).open();
        }

        function updateFileExcelChange(){
            if($("#excelFile").val()){
                $("#excelForm").ajaxSubmit({
                    success:function(data){
                        if(data&&data.success){
                            alert(data.success);
                        }else if(data&&data.error){
                            alert(data.error);
                        }
                        location.reload();
                    }
                });
                $("#excelFile").val('');
            }
        }

        function downExcel(){
            window.open("/WorkFlow/templet/DpcoiConfig.xls");
        }
    </script>
</head>
<body ng-controller="dpcoiConfigListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body" style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="dpcoiConfigCodeId">下拉菜单类型：</label>
                        <select id="dpcoiConfigCodeId" ng-model="dpcoiConfigList.searchForm.configCodeId"
                                class="form-control-order form-control" style="width: 50%;margin-left: 0%;padding: 1px 1px;font-size: 12px;height: 25px;">
                            <option ng-repeat="dpcoiConfigCodeDate in dpcoiConfigList.dpcoiConfigCodeList" value="{{dpcoiConfigCodeDate.configCodeId}}">{{dpcoiConfigCodeDate.configCodeName}}</option>
                        </select>
                    </div>
                    <div class="col-md-8">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiConfigSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>

                        <button class="btn btn-small btn-purple" type="button" id="dpcoiConfigAdd">
                            <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
                        </button>

                        <button class="btn btn-small btn-purple" type="button" onclick="impExcel()">
                            <i class="icon-plus-sign icon-on-right bigger-110"></i>导入
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
                        <th width="33%" class="x-grid3-header" style="padding: 0px">下拉菜单类型</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px">下拉菜单选项</th>
                        <th width="33%" class="x-grid3-header" style="padding: 0px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="dpcoiConfigDate in dpcoiConfigList.dpcoiConfigList">
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{dpcoiConfigDate.configCodeName}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">
                            {{dpcoiConfigDate.configValue}}
                        </td>
                        <td style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">
                            <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiConfigList.deleteDpcoiConfig(dpcoiConfigDate.configId);" style="margin-left: 4px">
                                <i class="bigger-110"></i>删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-left">
                    <button onclick="downExcel();" class=""><i class="glyphicon glyphicon-open-file"></i>模板下载</button>
                    <span class="separator"></span>
                    <button ng-click="downDataExcel();" class=""><i class="glyphicon glyphicon-open-file"></i>数据下载</button>
                    <span class="separator"></span>
                </div>
                <div class="table-foot-center">
                    <button class="" ng-disabled="dpcoiConfigList.pageInfo.firstPageDisabled"
                            ng-click="dpcoiConfigList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiConfigList.pageInfo.prevPageDisabled"
                            ng-click="dpcoiConfigList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiConfigList.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiConfigList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiConfigList.pageInfo.nextPageDisabled"
                            ng-click="dpcoiConfigList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiConfigList.pageInfo.lastPageDisabled"
                            ng-click="dpcoiConfigList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiConfigList.pageInfo.totalCount}}条<input
                        id="dpcoiConfigList.pageInfo.totalCount" value="{{dpcoiConfigList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增下拉菜单选项 -->
<div id="dpcoiConfigAddModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="dpcoiConfigAddModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="dpcoiConfigAddModalLabel">新增下拉菜单选项</h3>
    </div>
    <form id="dpcoiConfigAddModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <label  class="control-label" for="dpcoiConfigCodeId">下拉菜单类型：</label>
                    <select name="dpcoiConfigCodeId" class="form-control-order form-control" ng-model="dpcoiConfigList.dpcoiConfig.configCodeId">
                        <option ng-repeat="dpcoiConfigCodeDate in dpcoiConfigList.dpcoiConfigCodeList" value="{{dpcoiConfigCodeDate.configCodeId}}">{{dpcoiConfigCodeDate.configCodeName}}</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label  class="control-label" for="dpcoiConfigValue">下拉菜单选项：</label>
                    <input class="form-control-order form-control clean" id="dpcoiConfigValue" name="dpcoiConfigValue" ng-model="dpcoiConfigList.dpcoiConfig.configValue">
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="fliePass" ng-click="dpcoiConfigList.dpcoiConfigAdd();"
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
<script src="/WorkFlow/js/module/dpcoi/dpcoiConfigList.js"></script>
</html>
