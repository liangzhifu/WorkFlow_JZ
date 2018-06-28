var dpcoiOrderRollBackListApp = angular.module("dpcoiOrderRollBackList", []);
dpcoiOrderRollBackListApp.controller("dpcoiOrderRollBackListController", function ($scope) {
    $scope.dpcoiOrderRollBackList = {};
    $scope.dpcoiOrderRollBackList.dpcoiOrderList = [{
        "dpcoiOrderId" : "",
        "designChangeNo" : "",
        "vehicleName" : "",
        "productNo" : "",
        "changeContent" : "",
        "dpcoiOrderState" : "",
        "dpcoiWoOrderType" : "",
        "taskOrderNo" : ""
    }];
    $scope.dpcoiOrderRollBackList.pageInfo = {"url":"/WorkFlow/dpcoiOrderRollBack/getDpcoiOrderRollBackListPage.do"};
    $scope.dpcoiOrderRollBackList.firstPage = function () {
        $scope.dpcoiOrderRollBackList.pageInfo.page = 1;
        $scope.dpcoiOrderRollBackList.Search();
    }
    $scope.dpcoiOrderRollBackList.prevPage = function () {
        $scope.dpcoiOrderRollBackList.pageInfo.page = $scope.dpcoiOrderRollBackList.pageInfo.page - 1;
        $scope.dpcoiOrderRollBackList.Search();
    }
    $scope.dpcoiOrderRollBackList.nextPage = function () {
        $scope.dpcoiOrderRollBackList.pageInfo.page = $scope.dpcoiOrderRollBackList.pageInfo.page + 1;
        $scope.dpcoiOrderRollBackList.Search();
    }
    $scope.dpcoiOrderRollBackList.lastPage = function () {
        $scope.dpcoiOrderRollBackList.pageInfo.page = $scope.dpcoiOrderRollBackList.pageInfo.totalPage;
        $scope.dpcoiOrderRollBackList.Search();
    }
    $scope.dpcoiOrderRollBackList.searchForm = {
        "vehicleName": "",
        "productNo": "",
        "designChangeNo": "",
        "productLine": ""
    }
    $scope.dpcoiOrderRollBackList.Search = function () {
        $scope.dpcoiOrderRollBackList.searchForm.pagenum = $scope.dpcoiOrderRollBackList.pageInfo.page-1;
        $scope.dpcoiOrderRollBackList.searchForm.pageCount = 10;
        $scope.dpcoiOrderRollBackList.searchForm.size = $scope.dpcoiOrderRollBackList.searchForm.pageCount;
        $scope.dpcoiOrderRollBackList.searchForm.start = $scope.dpcoiOrderRollBackList.searchForm.pagenum * $scope.dpcoiOrderRollBackList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiOrderRollBackList.pageInfo.url,
            data:$scope.dpcoiOrderRollBackList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiOrderRollBackList.dpcoiOrderList = result.dpcoiOrderRollBackList;
                    $scope.dpcoiOrderRollBackList.pageInfo.totalCount = result.dpcoiOrderRollBackCount;
                    $scope.dpcoiOrderRollBackList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiOrderRollBackList.pageInfo.page <= 1){
                        $scope.dpcoiOrderRollBackList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiOrderRollBackList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiOrderRollBackList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiOrderRollBackList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiOrderRollBackList.pageInfo.page >= $scope.dpcoiOrderRollBackList.pageInfo.totalPage){
                        $scope.dpcoiOrderRollBackList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiOrderRollBackList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiOrderRollBackList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiOrderRollBackList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiOrderRollBackSearch").click(function () {
        $scope.dpcoiOrderRollBackList.firstPage();
    });

    $scope.dpcoiOrderRollBackList.rollBack = function(dpcoiOrderId, dpcoiWoOrderType){
        var woOrderName = "";
        if(dpcoiWoOrderType == 1){
            woOrderName = "PFMEA";
        }else if(dpcoiWoOrderType == 2) {
            woOrderName = "CP";
        }else if(dpcoiWoOrderType == 3) {
            woOrderName = "作业标准书";
        }
        var con = confirm("确定回滚到："+woOrderName);
        if(con == true){
            $.ajax({
                method:'post',
                url:"/WorkFlow/dpcoiOrderRollBack/rollBackDpcoiOrder.do",
                data:{"dpcoiOrderId": dpcoiOrderId, "dpcoiWoOrderType": dpcoiWoOrderType},
                success: function(resultJson) {
                    var result = angular.fromJson(resultJson);
                    if(result.success){
                        $scope.dpcoiOrderRollBackList.firstPage();
                    }else {
                        alert(result.message);
                    }
                },
                error : function() {
                    alert("系统出现异常!!");
                }
            });
        }
    };

    $(document).ready(function() {
        $scope.dpcoiOrderRollBackList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiOrderRollBackList' ]);