var dpcoiConfigVehicleListApp = angular.module("dpcoiConfigVehicleList", []);
dpcoiConfigVehicleListApp.controller("dpcoiConfigVehicleListController", function ($scope) {
    $scope.dpcoiConfigVehicleList = {};
    $scope.dpcoiConfigVehicleList.dpcoiConfigList = [{
        "dpcoiConfigId" : "",
        "dpcoiConfigValue" : ""
    }];
    $scope.dpcoiConfigVehicleList.dpcoiConfigVehicle = {
        "configId" : "",
        "configVehicleValue" : ""
    };
    $scope.dpcoiConfigVehicleList.dpcoiConfigVehicleList = [{
        "configId" : "",
        "configValue" : "",
        "configVehicleId" : "",
        "configVehicleValue" : ""
    }];
    $scope.dpcoiConfigVehicleList.pageInfo = {"url":"/WorkFlow/dpcoiConfigVehicle/getDpcoiConfigVehicleListPage.do"};
    $scope.dpcoiConfigVehicleList.firstPage = function () {
        $scope.dpcoiConfigVehicleList.pageInfo.page = 1;
        $scope.dpcoiConfigVehicleList.Search();
    };
    $scope.dpcoiConfigVehicleList.prevPage = function () {
        $scope.dpcoiConfigVehicleList.pageInfo.page = $scope.dpcoiConfigVehicleList.pageInfo.page - 1;
        $scope.dpcoiConfigVehicleList.Search();
    };
    $scope.dpcoiConfigVehicleList.nextPage = function () {
        $scope.dpcoiConfigVehicleList.pageInfo.page = $scope.dpcoiConfigVehicleList.pageInfo.page + 1;
        $scope.dpcoiConfigVehicleList.Search();
    };
    $scope.dpcoiConfigVehicleList.lastPage = function () {
        $scope.dpcoiConfigVehicleList.pageInfo.page = $scope.dpcoiConfigVehicleList.pageInfo.totalPage;
        $scope.dpcoiConfigVehicleList.Search();
    };
    $scope.dpcoiConfigVehicleList.searchForm = {
        "configId": "",
        "configVehicleValue": ""
    };
    $scope.dpcoiConfigVehicleList.Search = function () {
        $scope.dpcoiConfigVehicleList.searchForm.pagenum = $scope.dpcoiConfigVehicleList.pageInfo.page-1;
        $scope.dpcoiConfigVehicleList.searchForm.pageCount = 10;
        $scope.dpcoiConfigVehicleList.searchForm.size = $scope.dpcoiConfigVehicleList.searchForm.pageCount;
        $scope.dpcoiConfigVehicleList.searchForm.start = $scope.dpcoiConfigVehicleList.searchForm.pagenum * $scope.dpcoiConfigVehicleList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiConfigVehicleList.pageInfo.url,
            data:$scope.dpcoiConfigVehicleList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiConfigVehicleList.dpcoiConfigVehicleList = result.dpcoiConfigVehicleList;
                    $scope.dpcoiConfigVehicleList.pageInfo.totalCount = result.dpcoiConfigVehicleCount;
                    $scope.dpcoiConfigVehicleList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiConfigVehicleList.pageInfo.page <= 1){
                        $scope.dpcoiConfigVehicleList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiConfigVehicleList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiConfigVehicleList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiConfigVehicleList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiConfigVehicleList.pageInfo.page >= $scope.dpcoiConfigVehicleList.pageInfo.totalPage){
                        $scope.dpcoiConfigVehicleList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiConfigVehicleList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiConfigVehicleList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiConfigVehicleList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiConfigVehicleSearch").click(function () {
        $scope.dpcoiConfigVehicleList.firstPage();
    });

    $scope.dpcoiConfigVehicleList.deleteDpcoiConfigVehicle = function (configVehicleId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiConfigVehicle/deleteDpcoiConfigVehicle.do",
            data:{"configVehicleId":configVehicleId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $scope.dpcoiConfigVehicleList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#dpcoiConfigVehicleAdd").click(function () {
        $("#dpcoiConfigVehicleAddModal").modal("show");
    });

    $scope.dpcoiConfigVehicleList.dpcoiConfigVehicleAdd = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiConfigVehicle/addDpcoiConfigVehicle.do",
            data:$scope.dpcoiConfigVehicleList.dpcoiConfigVehicle,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $("#dpcoiConfigVehicleAddModal").modal("hide");
                $scope.dpcoiConfigVehicleList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $(document).ready(function() {
        $.ajax({
            method: 'post',
            url: "/WorkFlow/dpcoiConfig/getDpcoiConfigList.do?configCodeId=11",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiConfigVehicleList.dpcoiConfigList = result.dpcoiConfigList;
                    $scope.$apply();
                }
            }
        });

        $scope.dpcoiConfigVehicleList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiConfigVehicleList' ]);