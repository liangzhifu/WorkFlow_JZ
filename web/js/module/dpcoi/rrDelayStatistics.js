var rrDelayStatisticsApp = angular.module("rrDelayStatistics", []);
rrDelayStatisticsApp.controller("rrDelayStatisticsController", function ($scope) {
    $scope.rrDelayStatistics = {};
    $scope.rrDelayStatistics.statisticsCount = 0;
    $scope.rrDelayStatistics.persionLiable = {};
    $scope.rrDelayStatistics.persionLiable.mapList = [{
        "userName" : "",
        "num" : 0
    }];
    $scope.rrDelayStatistics.searchForm = {
        "dateBegin": "",
        "dateEnd": "",
        "userName":""
    }

    $scope.rrDelayStatistics.Search = function () {
        $scope.rrDelayStatistics.searchForm.dateBegin = $("#dateBegin").val();
        $scope.rrDelayStatistics.searchForm.dateEnd = $("#dateEnd").val();
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrDelayStatistics/getStatisticsCount.do",
            data:$scope.rrDelayStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrDelayStatistics.statisticsCount = result.statisticsCount;
                    $scope.$apply();
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrDelayStatistics/getStatisticsList.do",
            data:$scope.rrDelayStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrDelayStatistics.persionLiable.mapList = result.mapList;
                    $scope.$apply();
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $("#rrDelayStatisticsSearch").click(function () {
        $scope.rrDelayStatistics.Search();
    });

    $(document).ready(function() {
        $scope.rrDelayStatistics.Search();
    });

    $scope.rrDelayStatistics.rrDelay = {};
    $scope.rrDelayStatistics.rrDelay.mapList = [{
        "problemNo" : "",
        "problemStatus" : "",
        "problemProgress" : "",
        "delayDate" : "",
        "speedOfPorgress" : ""
    }];

    $scope.showRRDelayStatisticsList = function(userName){
        $scope.rrDelayStatistics.searchForm.dateBegin = $("#dateBegin").val();
        $scope.rrDelayStatistics.searchForm.dateEnd = $("#dateEnd").val();
        $scope.rrDelayStatistics.searchForm.userName = userName;
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrDelayStatistics/getRRDelayStatisticsList.do",
            data:$scope.rrDelayStatistics.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrDelayStatistics.rrDelay.mapList = result.mapList;
                    $scope.$apply();
                    $("#rrDelayListModal").modal("show");
                }
            },error : function() {
                alert("系统出现异常!!");
            }
        });
    }

});

angular.bootstrap(document, [ 'rrDelayStatistics' ]);