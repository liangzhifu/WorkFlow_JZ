var vehicleListApp = angular.module("vehicleList", []);
vehicleListApp.controller("vehicleListController", function ($scope) {
    $scope.vehicleList = {};
    $scope.vehicleList.vehicleList = [{
        "id" : "",
        "vehicleName" : ""
    }];
    $scope.vehicleList.pageInfo = {"url":"/WorkFlow/vehicle/getVehicleListPage.do"};
    $scope.vehicleList.firstPage = function () {
        $scope.vehicleList.pageInfo.page = 1;
        $scope.vehicleList.Search();
    }
    $scope.vehicleList.prevPage = function () {
        $scope.vehicleList.pageInfo.page = $scope.vehicleList.pageInfo.page - 1;
        $scope.vehicleList.Search();
    }
    $scope.vehicleList.nextPage = function () {
        $scope.vehicleList.pageInfo.page = $scope.vehicleList.pageInfo.page + 1;
        $scope.vehicleList.Search();
    }
    $scope.vehicleList.lastPage = function () {
        $scope.vehicleList.pageInfo.page = $scope.vehicleList.pageInfo.totalPage;
        $scope.vehicleList.Search();
    }
    $scope.vehicleList.searchForm = {
        "vehicleName": ""
    }
    $scope.vehicleList.Search = function () {
        $scope.vehicleList.searchForm.pagenum = $scope.vehicleList.pageInfo.page-1;
        $scope.vehicleList.searchForm.pageCount = 10;
        $scope.vehicleList.searchForm.size = $scope.vehicleList.searchForm.pageCount;
        $scope.vehicleList.searchForm.start = $scope.vehicleList.searchForm.pagenum * $scope.vehicleList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.vehicleList.pageInfo.url,
            data:$scope.vehicleList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.vehicleList.vehicleList = result.vehicleList;
                    $scope.vehicleList.pageInfo.totalCount = result.vehicleCount;
                    $scope.vehicleList.pageInfo.totalPage =  result.pageCount;
                    if($scope.vehicleList.pageInfo.page <= 1){
                        $scope.vehicleList.pageInfo.firstPageDisabled = true;
                        $scope.vehicleList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.vehicleList.pageInfo.firstPageDisabled = false;
                        $scope.vehicleList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.vehicleList.pageInfo.page >= $scope.vehicleList.pageInfo.totalPage){
                        $scope.vehicleList.pageInfo.nextPageDisabled = true;
                        $scope.vehicleList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.vehicleList.pageInfo.nextPageDisabled = false;
                        $scope.vehicleList.pageInfo.lastPageDisabled = false;
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

    $("#vehicleSearch").click(function () {
        $scope.vehicleList.firstPage();
    });

    $("#vehicleAdd").click(function () {
        $("#vehicleAddModal").modal("show");
    });

    $scope.vehicleList.deleteVehicle = function (id) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/vehicle/deleteVehicle.do",
            data:{"id":id},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.vehicleList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $("#vehicleAddConfirm").click(function () {
       $("#vehicleAddModalForm").submit();
    });

    $(document).ready(function() {
        $scope.vehicleList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'vehicleList' ]);