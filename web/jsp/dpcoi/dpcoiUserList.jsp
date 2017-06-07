<%--
  Created by IntelliJ IDEA.
  User: liangzhifu
  Date: 2017/4/23
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="../public/js.jsp"%>
    <%@include file="../public/css.jsp"%>
</head>
<body ng-controller="dpcoiUserController" ng-cloak>
<div class="main-container container-fluid">
    <div class="main-content" >
        <div class="page-content" >

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4">
                        <label  class="control-label" for="dpcoiUserName">姓名：</label>
                        <input type="text" name="dpcoiUserName" id="dpcoiUserName" ng-model="dpcoiUser.searchForm.userName"
                               class="form-control-order form-control" placeholder="姓名">
                    </div>
                    <div class="col-md-4">
                        <label  class="control-label" for="dpcoiUserCode">工号：</label>
                        <input type="text" name="dpcoiUserCode" id="dpcoiUserCode" ng-model="dpcoiUser.searchForm.userCode"
                               class="form-control-order form-control" placeholder="工号：">
                    </div>
                    <div class="col-md-4">
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiUserSearch">
                            <i class="icon-search icon-on-right bigger-110"></i>查找
                        </button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-small btn-purple" type="button" id="dpcoiUserAdd">
                            <i class="icon-user icon-on-right bigger-110"></i>增加
                        </button>
                    </div>
                </div>
            </div>
            <div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th width="20%">姓名</th>
                        <th width="20%">工号</th>
                        <th width="20%">邮箱</th>
                        <th width="20%">手机</th>
                        <th width="20%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="dpcoiUserData in dpcoiUser.dpcoiUserList">
                            <td>{{dpcoiUserData.userName}}</td>
                            <td>{{dpcoiUserData.userCode}}</td>
                            <td>{{dpcoiUserData.email}}</td>
                            <td>{{dpcoiUserData.mobileTel}}</td>
                            <td>
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiUser.deleteDpcoiUser(dpcoiUserData.dpcoiUserId);">
                                    <i class="bigger-110"></i>删除
                                </button>
                                &nbsp;&nbsp;
                                <button class="btn btn-small btn-purple" type="button" ng-show="dpcoiUserData.dpcoiUserState==0" ng-click="dpcoiUser.openDpcoiUser(dpcoiUserData.dpcoiUserId);">
                                    <i class="bigger-110"></i>开启
                                </button>
                                <button class="btn btn-small btn-purple" type="button" ng-show="dpcoiUserData.dpcoiUserState==1" ng-click="dpcoiUser.closeDpcoiUser(dpcoiUserData.dpcoiUserId);">
                                    <i class="bigger-110"></i>关闭
                                </button>
                                &nbsp;&nbsp;
                                <button class="btn btn-small btn-purple" type="button" ng-click="dpcoiUser.bindingAuthority(dpcoiUserData.dpcoiUserId);">
                                    <i class="bigger-110"></i>绑定权限
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class = "tfoot">
                <div class="table-foot-center">
                    <button class="" ng-disabled="dpcoiUser.pageInfo.firstPageDisabled"
                            ng-click="dpcoiUser.firstPage();">
                        <span class="glyphicon glyphicon-step-backward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiUser.pageInfo.prevPageDisabled"
                            ng-click="dpcoiUser.prevPage();">
                        <span class="glyphicon glyphicon-backward "></span>
                    </button>
                    <span class="separator"></span> <span class="page">
								{{dpcoiUser.pageInfo.page}}&nbsp;/&nbsp;{{dpcoiUser.pageInfo.totalPage}}&nbsp; </span> <span
                        class="separator"></span>
                    <button class="" ng-disabled="dpcoiUser.pageInfo.nextPageDisabled"
                            ng-click="dpcoiUser.nextPage();">
                        <span class="glyphicon glyphicon-forward "></span>
                    </button>
                    <button class="" ng-disabled="dpcoiUser.pageInfo.lastPageDisabled"
                            ng-click="dpcoiUser.lastPage();">
                        <span class="glyphicon glyphicon-step-forward  "></span>
                    </button>
                    <span class="separator"></span> <span>共{{dpcoiUser.pageInfo.totalCount}}条<input
                        id="dpcoiUser.pageInfo.totalCount" value="{{dpcoiUser.pageInfo.totalCount}}" type="hidden"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 系统用户列表 -->
<div id="userListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="userListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="userListModalLabel">选择系统用户</h3>
    </div>
    <form id="userListModalForm" method="post" class="form-inline">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-4">
                    <label  class="control-label" for="userName">姓名：</label>
                    <input type="text" name="userName" id="userName" ng-model="sysUser.searchForm.userName"
                           class="form-control-order form-control" placeholder="姓名">
                </div>
                <div class="col-md-4">
                    <label  class="control-label" for="userCode">工号：</label>
                    <input type="text" name="userCode" id="userCode" ng-model="sysUser.searchForm.userCode"
                           class="form-control-order form-control" placeholder="工号：">
                </div>
                <div class="col-md-4">
                    <button class="btn btn-small btn-purple" type="button" id="sysUserSearch">
                        <i class="icon-search icon-on-right bigger-110"></i>查找
                    </button>
                </div>
            </div>
            <div class="row">
                <div style="height: 200px; overflow: scroll;">
                    <table id="sysUserListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="15%">多选框</th>
                            <th>用户名</th>
                            <th>工号</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="sysUser in sysUser.sysUserList">
                            <td><input title="" type='checkbox' class='form-control-order form-checkbox-mypage' name='sysUserCheckbox' value='{{sysUser.userId}}'></td>
                            <td>{{sysUser.userName}}</td>
                            <td>{{sysUser.userCode}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="sysUserConfirm"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="selectReturn"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>

<!-- 权限列表 -->
<div id="dpcoiRoleListModal" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="userListModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">×
        </button>
        <h3 id="dpcoiRoleListModalLabel">选择权限</h3>
    </div>
    <form id="dpcoiRoleListModalForm" method="post" class="form-inline">
        <input type="hidden" id="dpcoiUserId" name="dpcoiUserId">
        <div class="modal-body">
            <div class="row">
                <div style="height: 200px; overflow: scroll;">
                    <table id="dpcoiRoleListTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="15%">多选框</th>
                            <th>权限</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="dpcoiRole in dpcoiRole.dpcoiRoleList">
                            <td>
                                <input title="" type='checkbox' class='form-control-order form-checkbox-mypage' name='dpcoiRoleCheckbox' value='{{dpcoiRole.dpcoiRoleId}}' ng-checked="dpcoiRole.id != null">
                            </td>
                            <td>{{dpcoiRole.dpcoiRoleName}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" id="dpcoiRoleConfirm"
                        class="btn btn-small btn-primary">确定
                </button>
                <button type="button" id="dpcoiRoleReturn"
                        class="btn btn-small btn-primary" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </form>
</div>
</body>
    <script src="/WorkFlow/js/module/dpcoi/dpcoiUserList.js"></script>
</html>
