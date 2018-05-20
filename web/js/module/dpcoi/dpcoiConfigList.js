var dpcoiConfigListApp = angular.module("dpcoiConfigList", []);
dpcoiConfigListApp.controller("dpcoiConfigListController", function ($scope) {
    $scope.dpcoiConfigList = {};
    $scope.dpcoiConfigList.dpcoiConfigCodeList = [{
        "dpcoiConfigCodeId" : "",
        "dpcoiConfigCodeName" : ""
    }];
    $scope.dpcoiConfigList.dpcoiConfig = {
        "configValue" : "",
        "configCodeId" : ""
    };
    $scope.dpcoiConfigList.dpcoiConfigList = [{
        "configId" : "",
        "configValue" : "",
        "configCodeId" : "",
        "configCodeName" : ""
    }];
    $scope.dpcoiConfigList.pageInfo = {"url":"/WorkFlow/dpcoiConfig/getDpcoiConfigListPage.do"};
    $scope.dpcoiConfigList.firstPage = function () {
        $scope.dpcoiConfigList.pageInfo.page = 1;
        $scope.dpcoiConfigList.Search();
    };
    $scope.dpcoiConfigList.prevPage = function () {
        $scope.dpcoiConfigList.pageInfo.page = $scope.dpcoiConfigList.pageInfo.page - 1;
        $scope.dpcoiConfigList.Search();
    };
    $scope.dpcoiConfigList.nextPage = function () {
        $scope.dpcoiConfigList.pageInfo.page = $scope.dpcoiConfigList.pageInfo.page + 1;
        $scope.dpcoiConfigList.Search();
    };
    $scope.dpcoiConfigList.lastPage = function () {
        $scope.dpcoiConfigList.pageInfo.page = $scope.dpcoiConfigList.pageInfo.totalPage;
        $scope.dpcoiConfigList.Search();
    };
    $scope.dpcoiConfigList.searchForm = {
        "configCodeId": ""
    };
    $scope.dpcoiConfigList.Search = function () {
        $scope.dpcoiConfigList.searchForm.pagenum = $scope.dpcoiConfigList.pageInfo.page-1;
        $scope.dpcoiConfigList.searchForm.pageCount = 10;
        $scope.dpcoiConfigList.searchForm.size = $scope.dpcoiConfigList.searchForm.pageCount;
        $scope.dpcoiConfigList.searchForm.start = $scope.dpcoiConfigList.searchForm.pagenum * $scope.dpcoiConfigList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiConfigList.pageInfo.url,
            data:$scope.dpcoiConfigList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiConfigList.dpcoiConfigList = result.dpcoiConfigList;
                    $scope.dpcoiConfigList.pageInfo.totalCount = result.dpcoiConfigCount;
                    $scope.dpcoiConfigList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiConfigList.pageInfo.page <= 1){
                        $scope.dpcoiConfigList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiConfigList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiConfigList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiConfigList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiConfigList.pageInfo.page >= $scope.dpcoiConfigList.pageInfo.totalPage){
                        $scope.dpcoiConfigList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiConfigList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiConfigList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiConfigList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiConfigSearch").click(function () {
        $scope.dpcoiConfigList.firstPage();
    });

    $scope.dpcoiConfigList.deleteDpcoiConfig = function (configId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiConfig/deleteDpcoiConfig.do",
            data:{"configId":configId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $scope.dpcoiConfigList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $("#dpcoiConfigAdd").click(function () {
        $("#dpcoiConfigAddModal").modal("show");
    });

    $scope.dpcoiConfigList.dpcoiConfigAdd = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiConfig/addDpcoiConfig.do",
            data:$scope.dpcoiConfigList.dpcoiConfig,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {

                }else {
                    alert(result.message);
                }
                $("#dpcoiConfigAddModal").modal("hide");
                $scope.dpcoiConfigList.firstPage();
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

    $scope.downDataExcel = function () {
        $.ajax({
            method: 'post',
            url: "/WorkFlow/dpcoiConfig/downExcel.do",
            data: $scope.dpcoiConfigList.searchForm,
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    var fileUrl = contextPath + result.path;
                    window.open(fileUrl);
                } else {
                    alert('导出Excel失败！');
                }
            }
        });
    };

    $(document).ready(function() {
        $.ajax({
            method: 'post',
            url: "/WorkFlow/dpcoiConfigCode/getDpcoiConfigCodeList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiConfigList.dpcoiConfigCodeList = result.dpcoiConfigCodeList;
                    $scope.$apply();
                }
            }
        });

        $scope.dpcoiConfigList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiConfigList' ]);