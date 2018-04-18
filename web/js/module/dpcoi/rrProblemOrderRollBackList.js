var rrProblemOrderRollBackListApp = angular.module("rrProblemOrderRollBackList", []);
rrProblemOrderRollBackListApp.controller("rrProblemOrderRollBackListController", function ($scope) {
    $scope.rrProblemOrderRollBackList = {};
    $scope.rrProblemOrderRollBackList.rrProblemOrderList = [{
        "problemNo" : "",
        "rrProblemOrderState" : "",
        "dpcoiWoOrderType" : "",
        "rrProblemOrderId" : ""
    }];
    $scope.rrProblemOrderRollBackList.pageInfo = {"url":"/WorkFlow/dpcoiOrderRollBack/getRRProblemOrderRollBackListPage.do"};
    $scope.rrProblemOrderRollBackList.firstPage = function () {
        $scope.rrProblemOrderRollBackList.pageInfo.page = 1;
        $scope.rrProblemOrderRollBackList.Search();
    }
    $scope.rrProblemOrderRollBackList.prevPage = function () {
        $scope.rrProblemOrderRollBackList.pageInfo.page = $scope.rrProblemOrderRollBackList.pageInfo.page - 1;
        $scope.rrProblemOrderRollBackList.Search();
    }
    $scope.rrProblemOrderRollBackList.nextPage = function () {
        $scope.rrProblemOrderRollBackList.pageInfo.page = $scope.rrProblemOrderRollBackList.pageInfo.page + 1;
        $scope.rrProblemOrderRollBackList.Search();
    }
    $scope.rrProblemOrderRollBackList.lastPage = function () {
        $scope.rrProblemOrderRollBackList.pageInfo.page = $scope.rrProblemOrderRollBackList.pageInfo.totalPage;
        $scope.rrProblemOrderRollBackList.Search();
    }
    $scope.rrProblemOrderRollBackList.searchForm = {
        "problemNo" : ""
    }
    $scope.rrProblemOrderRollBackList.Search = function () {
        $scope.rrProblemOrderRollBackList.searchForm.pagenum = $scope.rrProblemOrderRollBackList.pageInfo.page-1;
        $scope.rrProblemOrderRollBackList.searchForm.pageCount = 10;
        $scope.rrProblemOrderRollBackList.searchForm.size = $scope.rrProblemOrderRollBackList.searchForm.pageCount;
        $scope.rrProblemOrderRollBackList.searchForm.start = $scope.rrProblemOrderRollBackList.searchForm.pagenum * $scope.rrProblemOrderRollBackList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.rrProblemOrderRollBackList.pageInfo.url,
            data:$scope.rrProblemOrderRollBackList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.rrProblemOrderRollBackList.rrProblemOrderList = result.rrProblemOrderRollBackList;
                    $scope.rrProblemOrderRollBackList.pageInfo.totalCount = result.rrProblemOrderRollBackCount;
                    $scope.rrProblemOrderRollBackList.pageInfo.totalPage =  result.pageCount;
                    if($scope.rrProblemOrderRollBackList.pageInfo.page <= 1){
                        $scope.rrProblemOrderRollBackList.pageInfo.firstPageDisabled = true;
                        $scope.rrProblemOrderRollBackList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.rrProblemOrderRollBackList.pageInfo.firstPageDisabled = false;
                        $scope.rrProblemOrderRollBackList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.rrProblemOrderRollBackList.pageInfo.page >= $scope.rrProblemOrderRollBackList.pageInfo.totalPage){
                        $scope.rrProblemOrderRollBackList.pageInfo.nextPageDisabled = true;
                        $scope.rrProblemOrderRollBackList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.rrProblemOrderRollBackList.pageInfo.nextPageDisabled = false;
                        $scope.rrProblemOrderRollBackList.pageInfo.lastPageDisabled = false;
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

    $scope.rrProblemOrderRollBackList.rollBack = function(dpcoiOrderId, dpcoiWoOrderType){
        var woOrderName = "";
        if(dpcoiWoOrderType == 1){
            woOrderName = "PFMEA";
        }else if(dpcoiWoOrderType == 2) {
            woOrderName = "CP";
        }else if(dpcoiWoOrderType == 3) {
            woOrderName = "作业标准书";
        }
        var con = confirm("确定回滚到："+woOrderName);
        if(con == true) {
            $.ajax({
                method: 'post',
                url: "/WorkFlow/dpcoiOrderRollBack/rollBackDpcoiOrder.do",
                data: {"dpcoiOrderId": dpcoiOrderId, "dpcoiWoOrderType": dpcoiWoOrderType},
                success: function (resultJson) {
                    var result = angular.fromJson(resultJson);
                    if (result.success) {
                        $scope.rrProblemOrderRollBackList.firstPage();
                    } else {
                        alert(result.message);
                    }
                },
                error: function () {
                    alert("系统出现异常!!");
                }
            });
        }
    };

    $("#rrProblemOrderRollBackSearch").click(function () {
        $scope.rrProblemOrderRollBackList.firstPage();
    });

    $(document).ready(function() {
        $scope.rrProblemOrderRollBackList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'rrProblemOrderRollBackList' ]);