var dpcoiUserApp = angular.module("dpcoiUser", []);
dpcoiUserApp.controller("dpcoiUserController", function ($scope) {
    $scope.dpcoiUser = {};
    $scope.dpcoiUser.dpcoiUserList = [{
        "dpcoiUserId" : "",
        "userName" : "",
        "userCode" : "",
        "email" : "",
        "mobileTel" : ""
    }];
    $scope.dpcoiUser.pageInfo = {"url":"/WorkFlow/dpcoiUser/getDpcoiUserListPage.do"};
    $scope.dpcoiUser.firstPage = function () {
        $scope.dpcoiUser.pageInfo.page = 1;
        $scope.dpcoiUser.Search();
    }
    $scope.dpcoiUser.prevPage = function () {
        $scope.dpcoiUser.pageInfo.page = $scope.dpcoiUser.pageInfo.page - 1;
        $scope.dpcoiUser.Search();
    }
    $scope.dpcoiUser.nextPage = function () {
        $scope.dpcoiUser.pageInfo.page = $scope.dpcoiUser.pageInfo.page + 1;
        $scope.dpcoiUser.Search();
    }
    $scope.dpcoiUser.lastPage = function () {
        $scope.dpcoiUser.pageInfo.page = $scope.dpcoiUser.pageInfo.totalPage;
        $scope.dpcoiUser.Search();
    }
    $scope.dpcoiUser.searchForm = {
        "userName": "",
        "userCode": ""
    }
    $scope.dpcoiUser.Search = function () {
        $scope.dpcoiUser.searchForm.pagenum = $scope.dpcoiUser.pageInfo.page-1;
        $scope.dpcoiUser.searchForm.pageCount = 10;
        $scope.dpcoiUser.searchForm.size = $scope.dpcoiUser.searchForm.pageCount;
        $scope.dpcoiUser.searchForm.start = $scope.dpcoiUser.searchForm.pagenum * $scope.dpcoiUser.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiUser.pageInfo.url,
            data:$scope.dpcoiUser.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiUser.dpcoiUserList = result.dpcoiUserList;
                    $scope.dpcoiUser.pageInfo.totalCount = result.dpcoiUserCount;
                    $scope.dpcoiUser.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiUser.pageInfo.page <= 1){
                        $scope.dpcoiUser.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiUser.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiUser.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiUser.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiUser.pageInfo.page >= $scope.dpcoiUser.pageInfo.totalPage){
                        $scope.dpcoiUser.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiUser.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiUser.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiUser.pageInfo.lastPageDisabled = false;
                    }
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#dpcoiUserSearch").click(function () {
        $scope.dpcoiUser.firstPage();
    });

    $("#dpcoiUserAdd").click(function () {
        $("#userListModal").modal("show");
        $scope.sysUser.Search();
    })

    $scope.dpcoiUser.deleteDpcoiUser = function (dpcoiUserId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUser/deleteDpcoiUser.do",
            data:{"dpcoiUserId":dpcoiUserId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiUser.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiUser.openDpcoiUser = function (dpcoiUserId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUser/openDpcoiUser.do",
            data:{"dpcoiUserId":dpcoiUserId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiUser.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiUser.closeDpcoiUser = function (dpcoiUserId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUser/closeDpcoiUser.do",
            data:{"dpcoiUserId":dpcoiUserId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiUser.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiUser.bindingAuthority = function (dpcoiUserId) {
        $("#dpcoiRoleListModal").modal("show");
        $("#dpcoiUserId").val(dpcoiUserId)
        $scope.dpcoiRole.Search();
    }

    $scope.sysUser = {};
    $scope.sysUser.sysUserList = [{
        "id" : "",
        "userName" : "",
        "userCode" : ""
    }];

    $scope.sysUser.searchForm={
        "userName": "",
        "userCode": ""
    }

    $scope.sysUser.Search = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUser/getNoDpcoiUserList.do",
            data:$scope.sysUser.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.sysUser.sysUserList = result.userList;
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#sysUserSearch").click(function () {
        $scope.sysUser.Search();
    });

    $("#sysUserConfirm").click(function () {
        var userIdStr = "";
        $("#userListModal input[name=\"sysUserCheckbox\"]:checked ").each(function () {
            userIdStr += "," + $(this).val();
        })
        if(userIdStr == ""){
            alert("请选择系统用户!");
            return ;
        }
        userIdStr = userIdStr.substring(1);
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUser/addDpcoiUser.do",
            data:{"userIdStr":userIdStr},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiUser.firstPage();
                    $("#userListModal").modal("hide");
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    });

    $scope.dpcoiRole = {};
    $scope.dpcoiRole.dpcoiRoleList = [{
        "dpcoiRoleId" : "",
        "dpcoiRoleName" : "",
        "id" : ""
    }];

    $scope.dpcoiRole.Search = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUserRole/getDpcoiRoleList.do",
            data:{"dpcoiUserId":$("#dpcoiUserId").val()},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiRole.dpcoiRoleList = result.roleList;
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#dpcoiRoleConfirm").click(function () {
        var dpcoiRoleIdStr = "";
        $("#dpcoiRoleListModal input[name=\"dpcoiRoleCheckbox\"]:checked ").each(function () {
            dpcoiRoleIdStr += "," + $(this).val();
        })
        if(dpcoiRoleIdStr != ""){
            dpcoiRoleIdStr = dpcoiRoleIdStr.substring(1);
        }
        var dpcoiUserId = $("#dpcoiUserId").val();
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiUserRole/editDpcoiUserRole.do",
            data:{"dpcoiRoleIdStr":dpcoiRoleIdStr,"dpcoiUserId":dpcoiUserId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $("#dpcoiRoleListModal").modal("hide");
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    });

    $(document).ready(function() {
        $scope.dpcoiUser.firstPage();
    });
});

angular.bootstrap(document, [ 'dpcoiUser' ]);