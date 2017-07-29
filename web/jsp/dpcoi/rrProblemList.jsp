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
    <title>RR问题点列表</title>
    <%@include file="../public/js.jsp"%>
    <link rel="stylesheet" href="/WorkFlow/style/multiselect/jquery.multiselect.css" >
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/ace-responsive.min.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/button.css"/>
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/ace-fonts.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/jquery-ui.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/uniform.default.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/bootstrap/datetimepicker-xdan/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/css/jBox/jBox.css" />
    <link rel="stylesheet" href="/WorkFlow/style/assets/js/treeTable/themes/vsStyle/treeTable.min.css"/>

    <link rel="stylesheet" href="/WorkFlow/style/back-end/style.css" >
    <link rel="stylesheet" href="/WorkFlow/style/common/common.css" />
    <link rel="stylesheet" href="/WorkFlow/js/jqueryui.datetimepicker/jquery.datetimepicker.css" />

    <link rel="stylesheet" type="text/css" href="/WorkFlow/js/ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="/WorkFlow/js/ext/resources/css/ext-zte.css">
    <script type="text/javascript">
        var ministerJurisdiction = '${ministerJurisdiction}';
    </script>
    <style type="text/css">
        .claasRed{
            background-color : red;
            color: #000000;!important;
        }

        .classGoldenRod{
            background-color : GoldenRod;
            color: #000000;!important;
        }

        .classYellow{
            background-color : yellow;
            color: #000000;!important;
        }

        .cassDeepSkyBlue{
            background-color : deepskyblue;
            color: #000000;!important;
        }
    </style>
</head>
<body ng-controller="rrProblemListController" ng-cloak>
<div class="main-container container-fluid" style="padding-right: 1px;padding-left: 1px;">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body"  style="padding: 3px;" id="searchTable">
                <div class="row" style="margin-bottom: 4px;margin-top: 15px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="badContent">不良内容：</label>
                        <input type="text" name="badContent" id="badContent" ng-model="rrProblemList.searchForm.badContent"
                               class="form-control-order form-control" placeholder="不良内容" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemProgress">问题进展：</label>
                        <select id="problemProgress" name="problemProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.problemProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:4"
                                    value="{{dpcoiConfigDate.configValue}}"
                                    >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="speedOfProgress">进度：</label>
                        <select id="speedOfProgress" name="speedOfProgress" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.speedOfProgress" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
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
                                ng-model="rrProblemList.searchForm.problemStatus" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:1"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="problemType">问题类型：</label>
                        <select id="problemType" name="problemType" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.problemType" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:2"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="engineering">工程：</label>
                        <select id="engineering" name="engineering" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.engineering" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:3"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 4px;">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="customer">客户：</label>
                        <select id="customer" name="customer" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.customer" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:11"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>

                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="vehicle">车型：</label>
                        <input type="text" name="vehicle" id="vehicle" ng-model="rrProblemList.searchForm.vehicle"
                               class="form-control-order form-control" placeholder="车型" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productNo">品名：</label>
                        <select id="productNo" name="productNo" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.productNo" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:5"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateBegin">发生日期：</label>
                        <input type="text" name="happenDateBegin" id="happenDateBegin" ng-model="rrProblemList.searchForm.happenDateBegin"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="happenDateEnd">至：</label>
                        <input type="text" name="happenDateEnd" id="happenDateEnd" ng-model="rrProblemList.searchForm.happenDateEnd"
                               class="form-control-order form-control" data-type="date" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="persionLiable">责任人：</label>
                        <input type="text" name="persionLiable" id="persionLiable" ng-model="rrProblemList.searchForm.persionLiable"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="productLine">生产线：</label>
                        <input type="text" name="productLine" id="productLine" ng-model="rrProblemList.searchForm.productLine"
                               class="form-control-order form-control" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="severity">严重度：</label>
                        <select id="severity" name="severity" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.severity" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:6"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-2" style="padding-right: 1px;padding-left: 1px;">
                        <label  class="control-label" for="responsibleDepartment">责任部门：</label>
                        <select id="responsibleDepartment" name="responsibleDepartment" class="form-control-order form-control" required="required"
                                ng-model="rrProblemList.searchForm.responsibleDepartment" style="width: 60%;margin-left: 0%;padding: 1px 1px;font-size: 12px;margin-right: 15px;height: 25px;">
                            <option value="">请选择</option>
                            <option ng-repeat="dpcoiConfigDate in rrProblemList.dpcoiConfigList | myFilter:9"
                                    value="{{dpcoiConfigDate.configValue}}"
                            >{{dpcoiConfigDate.configValue}}</option>
                        </select>
                    </div>
                    <div class="col-md-6" style="padding-right: 1px;padding-left: 1px;">
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemAdd">
                            <i class="icon-plus-sign icon-on-right bigger-110"></i>新增
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemEdit">
                            <i class="icon-edit icon-on-right bigger-110"></i>修改
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemToVoid">
                            <i class="icon-edit icon-on-right bigger-110"></i>作废
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemDown">
                            <i class="icon-save-file icon-on-right bigger-110"></i>导出
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemClose">
                            <i class="icon-save-file icon-on-right bigger-110"></i>关闭
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="rrProblemDelay">
                            <i class="icon-save-file icon-on-right bigger-110"></i>延期
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
                        <th width="40px" class="x-grid3-header" style="padding: 0px"></th>
                        <th width="35px" class="x-grid3-header" style="padding: 0px">状态</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">问题编号</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">问题类型</th>
                        <th width="35px" class="x-grid3-header" style="padding: 0px">工程</th>
                        <th width="35px" class="x-grid3-header" style="padding: 0px">进展</th>
                        <th width="60px" class="x-grid3-header" style="padding: 0px">追踪等级</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">发生日期</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">客户</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">车型</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">品名</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">不良内容</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">责任人</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">下次汇报时间</th>
                        <th width="50px" class="x-grid3-header" style="padding: 0px;">进度</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">延期原因及延期进展</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">第一次原因调查</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">第二次永久<br>对策制定</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">第三次永久<br>对策有效</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">第四次经验总结</th>
                        <th width="60px" class="x-grid3-header" style="padding: 0px">关闭确认</th>
                        <th width="45px" class="x-grid3-header" style="padding: 0px;">生产线</th>
                        <th width="45px" class="x-grid3-header" style="padding: 0px">严重度</th>
                        <th width="60px" class="x-grid3-header" style="padding: 0px">发生频次</th>
                        <th width="60px" class="x-grid3-header" style="padding: 0px">不良数量</th>
                        <th width="64px" class="x-grid3-header" style="padding: 0px">批次</th>
                        <th width="35px" class="x-grid3-header" style="padding: 0px">班次</th>
                        <th width="120px" class="x-grid3-header" style="padding: 0px">责任部门</th>
                        <th width="75px" class="x-grid3-header" style="padding: 0px;">客户处是否记录PPM</th>
                        <th width="60px" class="x-grid3-header" style="padding: 0px">记录数量</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">临时对策（4H）</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">根本原因（48H）</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">永久对策（14D）</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">效果验证（34D）</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">品清联编号</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px;">质量警示<br>卡编号</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">品推表</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">PFMEA</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">CPQC工程表</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">作业标准书</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">设备点检表</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">始终件表</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px;">检查基准书</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">检查收顺书</th>
                        <th width="80px" class="x-grid3-header" style="padding: 0px">教育议事录</th>
                        <th width="100px" class="x-grid3-header" style="padding: 0px">变化点管理</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">展开及追踪是否完成</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">人工</th>
                        <th width="400px" class="x-grid3-header" style="padding: 0px">物料</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="rrProblemDate in rrProblemList.rrProblemList">
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="vertical-align:middle;"><input title="" type="checkbox" name="checkbox_records" id="checkbox_records_{{rrProblemDate.id}}" value="{{rrProblemDate.id}}" class="form-control-order form-checkbox-mypage"></td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.problemStatus}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.problemNo}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.problemType}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.engineering}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.problemProgress}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.trackingLevel}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.happenDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.customer}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.vehicle}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.productNo}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.badContent}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.persionLiable}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.reportDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.speedOfProgress}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.reasonForDelay}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.firstDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.secondDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.thirdDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.fourthDate}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.closeConfirm}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.productLine}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.severity}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.occurrenceFrequency}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.badQuantity}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.batch}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.happenShift}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.responsibleDepartment}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.recordPpm}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.recordNum}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.temporary}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.rootCause}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.permanentGame}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;word-wrap:break-word;word-break:break-all;padding: 0px;display:table-cell; vertical-align:middle;text-align: left;">{{rrProblemDate.effectVerification}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.serialNumber}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.qualityWarningCardNumber}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.productScale}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.pfmea}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.cp}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.standardBook}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.equipmentChecklist}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.alwaysList}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.inspectionReferenceBook}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.inspectionBook}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.education}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.changePoint}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.expandTrace}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.artificial}}</td>
                        <td ng-class="{'claasRed': rrProblemDate.isRed, 'classGoldenRod': rrProblemDate.isGoldenRod, 'classYellow': rrProblemDate.isYellow, 'cassDeepSkyBlue': rrProblemDate.isDeepSkyBlue}" style="white-space:normal;padding: 0px;display:table-cell; vertical-align:middle;">{{rrProblemDate.materiel}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot" id="footTable">
                <div class="table-foot-center">
                    <button class="" ng-disabled="rrProblemList.pageInfo.firstPageDisabled"
                            ng-click="rrProblemList.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemList.pageInfo.prevPageDisabled"
                            ng-click="rrProblemList.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{rrProblemList.pageInfo.page}}&nbsp;/&nbsp;{{rrProblemList.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="rrProblemList.pageInfo.nextPageDisabled"
                            ng-click="rrProblemList.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="rrProblemList.pageInfo.lastPageDisabled"
                            ng-click="rrProblemList.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{rrProblemList.pageInfo.totalCount}}条<input
                        id="rrProblemList.pageInfo.totalCount" value="{{rrProblemList.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/WorkFlow/js/module/dpcoi/rrProblemList.js"></script>
</html>
