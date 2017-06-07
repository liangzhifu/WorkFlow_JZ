<%--
  Created by IntelliJ IDEA.
  User: 梁志福
  Date: 2017/4/20
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Dpcoi定单新增修改</title>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
    <script type="text/javascript">
        var action = '${action}';
        var dpcoiOrder = '${dpcoiOrder}';
        var userJson = '${user}';
    </script>
</head>
<body ng-controller="dpcoiOrderEditController" ng-cloak>
<input type="text" style="display: none" id="dpcoiOrderId" name="dpcoiOrderId">
<div class="ain-content">
    <div class="page-content">
        <div class="row-fluid">
            <div class="span12">
                <div class="block">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <label class="control-label" for="issuedNo"><span style="color:red;">*</span>《设变通知书》编号：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'"
                                    id="issuedNo" name="issuedNo" ng-model="dpcoiOrderEdit.dpcoiOrder.issuedNo" style="width: 60%">
                            </div>
                            <div class="col-md-6">
                                <label  class="control-label" for="designChangeNo"><span style="color:red;">*</span>设变号：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'" style="width: 60%"
                                       id="designChangeNo" name="designChangeNo" ng-model="dpcoiOrderEdit.dpcoiOrder.designChangeNo">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="control-label" for="vehicleName"><span style="color:red;">*</span>车种：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'"
                                       id="vehicleName" name="vehicleName" ng-model="dpcoiOrderEdit.dpcoiOrder.vehicleName" style="width: 60%">
                            </div>

                            <div class="col-md-6">
                                <label class="control-label" for="productNo"><span style="color:red;">*</span>品号：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'"
                                       id="productNo" name="productNo" ng-model="dpcoiOrderEdit.dpcoiOrder.productNo" style="width: 60%">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label  class="control-label"><span style="color:red;">*</span>希望切替日：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'" style="width: 60%"
                                       id="hopeCuttingDate" name="hopeCuttingDate" ng-value="dpcoiOrderEdit.dpcoiOrder.hopeCuttingDate">
                            </div>
                            <div class="col-md-6">
                                <label  class="control-label" for="realCuttingDate">实际切替日：</label>
                                <input class="form-control-order form-control clean" data-type="date" ng-value="dpcoiOrderEdit.dpcoiOrder.realCuttingDate"
                                       id="realCuttingDate" name="realCuttingDate" style="width: 60%">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="control-label" for="returnDate">《设变切替手配书》返回日：</label>
                                <input class="form-control-order form-control clean" data-type="date" style="width: 60%"
                                       id="returnDate" name="returnDate" ng-value="dpcoiOrderEdit.dpcoiOrder.returnDate">
                            </div>
                            <div class="col-md-6">
                                <label  class="control-label" for="releaseDate"><span style="color:red;">*</span>发行日期：</label>
                                <input class="form-control-order form-control clean" data-type="date" required="required" style="width: 60%"
                                       id="releaseDate" name="releaseDate" ng-disabled="action=='edit'" ng-value="dpcoiOrderEdit.dpcoiOrder.releaseDate">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label  class="control-label" for="quasiActName"><span style="color:red;">*</span>量准担当：</label>
                                <input class="form-control-order form-control clean" ng-hide="true" required="required" data-target="quasiActName"
                                       id="quasiAct" name="quasiAct" type="number"ng-value="dpcoiOrderEdit.dpcoiOrder.quasiAct">
                                <input class="form-control-order form-control clean" style="width: 60%" ng-value="dpcoiOrderEdit.dpcoiOrder.quasiActName"
                                       id="quasiActName" name="quasiActName" ng-disabled="action=='edit'">
                            </div>
                            <div class="col-md-6">
                                <label class="control-label" for="designAct"><span style="color:red;">*</span>设计担当：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'"
                                       id="designAct" name="designAct" ng-model="dpcoiOrderEdit.dpcoiOrder.designAct" style="width: 60%">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="control-label" for="changeContent"><span style="color:red;">*</span>变更内容：</label>
                                <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'" style="width: 60%"
                                       id="changeContent" name="changeContent" ng-model="dpcoiOrderEdit.dpcoiOrder.changeContent">
                            </div>
                            <div class="col-md-6">
                                <label class="control-label" for="remark">备注：</label>
                                <input class="form-control-order form-control clean" ng-disabled="action=='edit'"
                                       id="remark" name="remark" ng-model="dpcoiOrderEdit.dpcoiOrder.remark" style="width: 60%">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label class="control-label" for="productLine"><span style="color:red;">*</span>生产线：</label>
                            <input class="form-control-order form-control clean" required="required" ng-disabled="action=='edit'" style="width: 60%"
                                   id="productLine" name="productLine" ng-model="dpcoiOrderEdit.dpcoiOrder.productLine">
                        </div>
                        <hr>
                        <div class="modal-footer">
                            <button type="button" id="dpcoiOrderEditConfirm"
                                    class="btn btn-small btn-primary">确定
                            </button>
                            &nbsp;&nbsp;
                            <a href="/WorkFlow/dpcoiOrder/getDpcoiOrderListDlg.do"
                               class="btn  btn-mini btn-info">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
    <script src="/WorkFlow/js/module/dpcoi/dpcoiOrderEdit.js"></script>
</html>
