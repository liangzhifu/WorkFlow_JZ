var rrDelayLeaderListApp = angular.module("rrDelayLeaderList", []);
rrDelayLeaderListApp.controller("rrDelayLeaderListController", function ($scope) {
    $scope.rrDelayLeaderList = {};
    $scope.rrDelayLeaderList.rrDelayLeaderList = [{
        "id" : "",
        "userId" : "",
        "userName" : "",
        "leaderId" : "",
        "leaderName" : "",
        "leaderType" : ""
    }];
    $scope.rrDelayLeaderList.rrDelayLeader = {
        "userId" : "",
        "leaderId" : "",
        "leaderType" : ""
    };
    $scope.rrDelayLeaderList.pageInfo = {"url":"/WorkFlow/rrDelayLeader/getRRDelayLeaderListPage.do"};
    $scope.rrDelayLeaderList.firstPage = function () {
        $scope.rrDelayLeaderList.pageInfo.page = 1;
        $scope.rrDelayLeaderList.Search();
    };
    $scope.rrDelayLeaderList.prevPage = function () {
        $scope.rrDelayLeaderList.pageInfo.page = $scope.rrDelayLeaderList.pageInfo.page - 1;
        $scope.rrDelayLeaderList.Search();
    };
    $scope.rrDelayLeaderList.nextPage = function () {
        $scope.rrDelayLeaderList.pageInfo.page = $scope.rrDelayLeaderList.pageInfo.page + 1;
        $scope.rrDelayLeaderList.Search();
    };
    $scope.rrDelayLeaderList.lastPage = function () {
        $scope.rrDelayLeaderList.pageInfo.page = $scope.rrDelayLeaderList.pageInfo.totalPage;
        $scope.rrDelayLeaderList.Search();
    };
    $scope.rrDelayLeaderList.searchForm = {
        "userName": "",
        "leaderName": "",
        "leaderType": ""
    };
    $scope.rrDelayLeaderList.Search = function () {
        $scope.rrDelayLeaderList.searchForm.pagenum = $scope.rrDelayLeaderList.pageInfo.page-1;
        $scope.rrDelayLeaderList.searchForm.pageCount = 10;
        $scope.rrDelayLeaderList.searchForm.size = $scope.rrDelayLeaderList.searchForm.pageCount;
        $scope.rrDelayLeaderList.searchForm.start = $scope.rrDelayLeaderList.searchForm.pagenum * $scope.rrDelayLeaderList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.rrDelayLeaderList.pageInfo.url,
            data:$scope.rrDelayLeaderList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.rrDelayLeaderList.rrDelayLeaderList = result.rrDelayLeaderList;
                    $scope.rrDelayLeaderList.pageInfo.totalCount = result.rrDelayLeaderCount;
                    $scope.rrDelayLeaderList.pageInfo.totalPage =  result.pageCount;
                    if($scope.rrDelayLeaderList.pageInfo.page <= 1){
                        $scope.rrDelayLeaderList.pageInfo.firstPageDisabled = true;
                        $scope.rrDelayLeaderList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.rrDelayLeaderList.pageInfo.firstPageDisabled = false;
                        $scope.rrDelayLeaderList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.rrDelayLeaderList.pageInfo.page >= $scope.rrDelayLeaderList.pageInfo.totalPage){
                        $scope.rrDelayLeaderList.pageInfo.nextPageDisabled = true;
                        $scope.rrDelayLeaderList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.rrDelayLeaderList.pageInfo.nextPageDisabled = false;
                        $scope.rrDelayLeaderList.pageInfo.lastPageDisabled = false;
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

    $("#rrDelayLeaderSearch").click(function () {
        $scope.rrDelayLeaderList.firstPage();
    });

    $scope.rrDelayLeaderList.deleteRRDelayLeader = function (id) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrDelayLeader/updateRRDelayLeader.do",
            data:{"id":id, "deleteState":"1"},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $scope.rrDelayLeaderList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#rrDelayLeaderAdd").click(function () {
        $("#rrUserId").val("");
        $("#rrUserName").val("");
        $("#rrLeaderId").val("");
        $("#rrLeaderName").val("");
        $("#rrLeaderType").val("");
        $("#delayLeaderAdd").attr("disabled",false);
        $("#rrDelayLeaderAddModal").modal("show");
    });

    $scope.rrDelayLeaderList.rrDelayLeaderAdd = function () {
        if(!$.html5Validate.isAllpass($("#rrUserId"))){
            return;
        }
        if(!$.html5Validate.isAllpass($("#rrLeaderId"))){
            return;
        }
        if(!$.html5Validate.isAllpass($("#rrLeaderType"))){
            return;
        }
        $scope.rrDelayLeaderList.rrDelayLeader.userId = $("#rrUserId").val();
        $scope.rrDelayLeaderList.rrDelayLeader.leaderId = $("#rrLeaderId").val();
        $scope.rrDelayLeaderList.rrDelayLeader.leaderType = $("#rrLeaderType").val();
        $("#delayLeaderAdd").attr("disabled","true");
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrDelayLeader/addRRDelayLeader.do",
            data:$scope.rrDelayLeaderList.rrDelayLeader,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $("#rrDelayLeaderAddModal").modal("hide");
                $scope.rrDelayLeaderList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $(document).ready(function() {

        $.ajax({
            type : "POST",
            url : "/WorkFlow/user/getUserList.do",
            success : function(result) {
                $('#rrUserName').autocomplete({
                    minLength : 0,
                    source : result.userList,
                    focus : function(event,	ui) {
                        $("#rrUserName").val(ui.item.label);
                        return false;
                    },
                    select : function(event, ui) {
                        $('#rrUserName').val(ui.item.label);
                        $('#rrUserId').val(ui.item.value);
                        return false;
                    },
                    // 当智能提示框关闭后会触发此事件,ui是空对象
                    close : function(event,	ui) {

                    }
                });
                $('#rrLeaderName').autocomplete({
                    minLength : 0,
                    source : result.userList,
                    focus : function(event,	ui) {
                        $("#rrLeaderName").val(ui.item.label);
                        return false;
                    },
                    select : function(event, ui) {
                        $('#rrLeaderName').val(ui.item.label);
                        $('#rrLeaderId').val(ui.item.value);
                        return false;
                    },
                    // 当智能提示框关闭后会触发此事件,ui是空对象
                    close : function(event,	ui) {

                    }
                });
            },
            error : function(jqXHR, textStatus,
                             errorThrown) {
                alert("系统出现异常!!");
            }
        });

        $scope.rrDelayLeaderList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'rrDelayLeaderList' ]);