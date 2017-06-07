var dpcoiStatisticsApp = angular.module("dpcoiStatistics", []);
dpcoiStatisticsApp.controller("dpcoiStatisticsController", function ($scope) {
    $scope.dpcoiStatistics = {};
    $scope.dpcoiStatistics.dpcoiOrderCount = 0;
    $scope.dpcoiStatistics.dpcoiWoOrderCount = [{
        "dpcoiWoOrderType" : 0,
        "dpcoiWoOrderCount" : 0,
        "dpcoiWoOrderComplete" : 0,
        "dpcoiWoOrderCompleteDealy" : 0,
        "dpcoiWoOrderNoComplete" : 0
    }];
    $scope.dpcoiStatistics.searchForm = {
        "vehicleName": "",
        "releaseDateStart": "",
        "releaseDateEnd": "",
        "hopeCuttingDate": "",
        "realCuttingDateStart": "",
        "realCuttingDateEnd": "",
        "productNo": "",
        "dpcoiWoOrderType" : "",
        "taskOrderNo": "",
        "productLine": ""
    }

    $scope.dpcoiStatistics.Search = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiStatistics/getDpcoiStatistics.do",
            data:$scope.dpcoiStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiStatistics.dpcoiOrderCount = result.dpcoiOrderCount;
                    $scope.dpcoiStatistics.dpcoiWoOrderCount = result.dpcoiWoOrderCount;
                    $scope.$apply();
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $("#dpcoiStatisticsSearch").click(function () {
        $scope.dpcoiStatistics.Search();
    });

    $(document).ready(function() {
        $scope.dpcoiStatistics.Search();
    });

    $scope.dpcoiStatistics.dpcoiOrderList = [{
        "dpcoiOrderId" : "",
        "issuedNo" : "",
        "designChangeNo" : "",
        "vehicleName" : "",
        "productNo" : "",
        "hopeCuttingDate" : "",
        "realCuttingDate" : "",
        "changeContent" : "",
        "releaseDate" : "",
        "returnDate" : "",
        "designAct" : "",
        "quasiAct" : "",
        "dpcoiOrderState" : "",
        "dpcoiOrderType" : "",
        "taskOrderNo" : "",
        "remark" : ""
    }];

    $scope.showWoOrderNoComplete = function(dpcoiWoOrderType){
        $scope.dpcoiStatistics.searchForm.dpcoiWoOrderType = dpcoiWoOrderType;
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiStatistics/getWoOrderNoComplete.do",
            data:$scope.dpcoiStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiStatistics.dpcoiOrderList = result.orderList;
                    $scope.$apply();
                    $("#orderListModal").modal("show");
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.showWoOrderCompleteDelay = function(dpcoiWoOrderType){
        $scope.dpcoiStatistics.searchForm.dpcoiWoOrderType = dpcoiWoOrderType;
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiStatistics/getWoOrderCompleteDelay.do",
            data:$scope.dpcoiStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiStatistics.dpcoiOrderList = result.orderList;
                    $scope.$apply();
                    $("#orderListModal").modal("show");
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
    }
});

angular.bootstrap(document, [ 'dpcoiStatistics' ]);