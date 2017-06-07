var holidayListApp = angular.module("holidayList", []);
holidayListApp.controller("holidayListController", function ($scope) {
    $scope.holidayList = {};
    $scope.holidayList.holidayList = [{
        "id" : "",
        "holiday" : ""
    }];
    $scope.holidayList.pageInfo = {"url":"/WorkFlow/holiday/getHolidayListPage.do"};
    $scope.holidayList.firstPage = function () {
        $scope.holidayList.pageInfo.page = 1;
        $scope.holidayList.Search();
    }
    $scope.holidayList.prevPage = function () {
        $scope.holidayList.pageInfo.page = $scope.holidayList.pageInfo.page - 1;
        $scope.holidayList.Search();
    }
    $scope.holidayList.nextPage = function () {
        $scope.holidayList.pageInfo.page = $scope.holidayList.pageInfo.page + 1;
        $scope.holidayList.Search();
    }
    $scope.holidayList.lastPage = function () {
        $scope.holidayList.pageInfo.page = $scope.holidayList.pageInfo.totalPage;
        $scope.holidayList.Search();
    }
    $scope.holidayList.searchForm = {
        "holidayStart": "",
        "holidayEnd": ""
    }
    $scope.holidayList.Search = function () {
        $scope.holidayList.searchForm.holidayStart = $("#holidayStart").val();
        $scope.holidayList.searchForm.holidayEnd = $("#holidayEnd").val();
        $scope.holidayList.searchForm.pagenum = $scope.holidayList.pageInfo.page-1;
        $scope.holidayList.searchForm.pageCount = 10;
        $scope.holidayList.searchForm.size = $scope.holidayList.searchForm.pageCount;
        $scope.holidayList.searchForm.start = $scope.holidayList.searchForm.pagenum * $scope.holidayList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.holidayList.pageInfo.url,
            data:$scope.holidayList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.holidayList.holidayList = result.holidayList;
                    $scope.holidayList.pageInfo.totalCount = result.holidayCount;
                    $scope.holidayList.pageInfo.totalPage =  result.pageCount;
                    if($scope.holidayList.pageInfo.page <= 1){
                        $scope.holidayList.pageInfo.firstPageDisabled = true;
                        $scope.holidayList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.holidayList.pageInfo.firstPageDisabled = false;
                        $scope.holidayList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.holidayList.pageInfo.page >= $scope.holidayList.pageInfo.totalPage){
                        $scope.holidayList.pageInfo.nextPageDisabled = true;
                        $scope.holidayList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.holidayList.pageInfo.nextPageDisabled = false;
                        $scope.holidayList.pageInfo.lastPageDisabled = false;
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

    $("#holidaySearch").click(function () {
        $scope.holidayList.firstPage();
    });

    $("#holidayAdd").click(function () {
        $("#holidayAddModal").modal("show");
    });

    $scope.holidayList.deleteHoliday = function (id) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/holiday/deleteHoliday.do",
            data:{"id":id},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.holidayList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $("#holidayAddConfirm").click(function () {
        $("#holidayAddModalForm").submit();
    });

    $(document).ready(function() {
        $("input[data-type='date']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                format: 'Y-m-d'
            });
        });

        $scope.holidayList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'holidayList' ]);