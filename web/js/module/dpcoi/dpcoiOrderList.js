var dpcoiOrderListApp = angular.module("dpcoiOrderList", []);
dpcoiOrderListApp.controller("dpcoiOrderListController", function ($scope) {
    $scope.dpcoiOrderList = {};
    $scope.dpcoiOrderList.dpcoiAddJurisdiction = dpcoiAddJurisdiction;
    $scope.dpcoiOrderList.userId = userId;
    $scope.dpcoiOrderList.dpcoiOrderList = [{
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
    $scope.dpcoiOrderList.pageInfo = {"url":"/WorkFlow/dpcoiOrder/getDpcoiOrderListPage.do"};
    $scope.dpcoiOrderList.firstPage = function () {
        $scope.dpcoiOrderList.pageInfo.page = 1;
        $scope.dpcoiOrderList.Search();
    }
    $scope.dpcoiOrderList.prevPage = function () {
        $scope.dpcoiOrderList.pageInfo.page = $scope.dpcoiOrderList.pageInfo.page - 1;
        $scope.dpcoiOrderList.Search();
    }
    $scope.dpcoiOrderList.nextPage = function () {
        $scope.dpcoiOrderList.pageInfo.page = $scope.dpcoiOrderList.pageInfo.page + 1;
        $scope.dpcoiOrderList.Search();
    }
    $scope.dpcoiOrderList.lastPage = function () {
        $scope.dpcoiOrderList.pageInfo.page = $scope.dpcoiOrderList.pageInfo.totalPage;
        $scope.dpcoiOrderList.Search();
    }
    $scope.dpcoiOrderList.searchForm = {
        "vehicleName": "",
        "releaseDateStart": "",
        "releaseDateEnd": "",
        "hopeCuttingDate": "",
        "realCuttingDateStart": "",
        "realCuttingDateEnd": "",
        "productNo": "",
        "pfmeaDelay": "",
        "cpDelay": "",
        "standardBookDelay": "",
        "designChangeNo": "",
        "productLine": ""
    }
    $scope.dpcoiOrderList.Search = function () {
        var delay = $("#delay").val();
        if(delay == "1"){
            $scope.dpcoiOrderList.searchForm.pfmeaDelay = "1";
        }else if(delay == "2"){
            $scope.dpcoiOrderList.searchForm.cpDelay = "1";
        }else if(delay == "3"){
            $scope.dpcoiOrderList.searchForm.standardBookDelay = "1";
        }
        $scope.dpcoiOrderList.searchForm.realCuttingDateStart = $("#realCuttingDateStart").val();
        $scope.dpcoiOrderList.searchForm.realCuttingDateEnd = $("#realCuttingDateEnd").val();
        $scope.dpcoiOrderList.searchForm.releaseDateStart = $("#releaseDateStart").val();
        $scope.dpcoiOrderList.searchForm.releaseDateEnd = $("#releaseDateEnd").val();
        $scope.dpcoiOrderList.searchForm.pagenum = $scope.dpcoiOrderList.pageInfo.page-1;
        $scope.dpcoiOrderList.searchForm.pageCount = 10;
        $scope.dpcoiOrderList.searchForm.size = $scope.dpcoiOrderList.searchForm.pageCount;
        $scope.dpcoiOrderList.searchForm.start = $scope.dpcoiOrderList.searchForm.pagenum * $scope.dpcoiOrderList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiOrderList.pageInfo.url,
            data:$scope.dpcoiOrderList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiOrderList.dpcoiOrderList = result.dpcoiOrderList;
                    $scope.dpcoiOrderList.pageInfo.totalCount = result.dpcoiOrderCount;
                    $scope.dpcoiOrderList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiOrderList.pageInfo.page <= 1){
                        $scope.dpcoiOrderList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiOrderList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiOrderList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiOrderList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiOrderList.pageInfo.page >= $scope.dpcoiOrderList.pageInfo.totalPage){
                        $scope.dpcoiOrderList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiOrderList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiOrderList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiOrderList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiOrderSearch").click(function () {
        $scope.dpcoiOrderList.firstPage();
    });

    $("#dpcoiOrderAdd").click(function () {
        window.location.href = "/WorkFlow/dpcoiOrder/getDpcoiOrderAddDlg.do";
    });

    $scope.dpcoiOrderList.toVoidDpcoiOrder = function (dpcoiOrderId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiOrder/toVoidDpcoiOrder.do",
            data:{"dpcoiOrderId":dpcoiOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiOrderList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $(document).ready(function() {
        $("input[data-type='date']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                format: 'Y-m-d'
            });
        });

        $("#hopeCuttingDate").datetimepicker({
            timepicker: false,
            todayButton: true,
            showApplyButton: true,
            format: 'Y-m-d'
        });

        $scope.dpcoiOrderList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiOrderList' ]);