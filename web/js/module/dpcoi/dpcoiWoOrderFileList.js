var dpcoiWoOrderFileListApp = angular.module("dpcoiWoOrderFileList", []);
dpcoiWoOrderFileListApp.controller("dpcoiWoOrderFileListController", function ($scope) {
    $scope.dpcoiWoOrderFileList = {};
    $scope.dpcoiWoOrderFileList.dpcoiWoOrderFileList = [{
        "dpcoiOrderId" : "",
        "dpcoiOrderState" : "",
        "dpcoiWoOrderId" : "",
        "dpcoiWoOrderType" : "",
        "dpcoiWoOrderState" : "",
        "taskOrderNo" : "",
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : ""
    }];
    $scope.dpcoiWoOrderFileList.pageInfo = {"url":"/WorkFlow/dpcoiWoOrderFile/getDpcoiWoOrderFileListPage.do"};
    $scope.dpcoiWoOrderFileList.firstPage = function () {
        $scope.dpcoiWoOrderFileList.pageInfo.page = 1;
        $scope.dpcoiWoOrderFileList.Search();
    }
    $scope.dpcoiWoOrderFileList.prevPage = function () {
        $scope.dpcoiWoOrderFileList.pageInfo.page = $scope.dpcoiWoOrderFileList.pageInfo.page - 1;
        $scope.dpcoiWoOrderFileList.Search();
    }
    $scope.dpcoiWoOrderFileList.nextPage = function () {
        $scope.dpcoiWoOrderFileList.pageInfo.page = $scope.dpcoiWoOrderFileList.pageInfo.page + 1;
        $scope.dpcoiWoOrderFileList.Search();
    }
    $scope.dpcoiWoOrderFileList.lastPage = function () {
        $scope.dpcoiWoOrderFileList.pageInfo.page = $scope.dpcoiWoOrderFileList.pageInfo.totalPage;
        $scope.dpcoiWoOrderFileList.Search();
    }
    $scope.dpcoiWoOrderFileList.searchForm = {
        "vehicleName": "",
        "releaseDateStart": "",
        "releaseDateEnd": "",
        "productNo": "",
        "realCuttingDateStart": "",
        "realCuttingDateEnd": "",
        "designChangeNo": "",
        "fileName": "",
        "dpcoiWoOrderType": "",
        "productLine": ""
    }
    $scope.dpcoiWoOrderFileList.Search = function () {
        $scope.dpcoiWoOrderFileList.searchForm.realCuttingDateStart = $("#realCuttingDateStart").val();
        $scope.dpcoiWoOrderFileList.searchForm.realCuttingDateEnd = $("#realCuttingDateEnd").val();
        $scope.dpcoiWoOrderFileList.searchForm.releaseDateStart = $("#releaseDateStart").val();
        $scope.dpcoiWoOrderFileList.searchForm.releaseDateEnd = $("#releaseDateEnd").val();
        $scope.dpcoiWoOrderFileList.searchForm.pagenum = $scope.dpcoiWoOrderFileList.pageInfo.page-1;
        $scope.dpcoiWoOrderFileList.searchForm.pageCount = 10;
        $scope.dpcoiWoOrderFileList.searchForm.size = $scope.dpcoiWoOrderFileList.searchForm.pageCount;
        $scope.dpcoiWoOrderFileList.searchForm.start = $scope.dpcoiWoOrderFileList.searchForm.pagenum * $scope.dpcoiWoOrderFileList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiWoOrderFileList.pageInfo.url,
            data:$scope.dpcoiWoOrderFileList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiWoOrderFileList.dpcoiWoOrderFileList = result.dpcoiWoOrderFileList;
                    $scope.dpcoiWoOrderFileList.pageInfo.totalCount = result.dpcoiWoOrderFileCount;
                    $scope.dpcoiWoOrderFileList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiWoOrderFileList.pageInfo.page <= 1){
                        $scope.dpcoiWoOrderFileList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiWoOrderFileList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiWoOrderFileList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiWoOrderFileList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiWoOrderFileList.pageInfo.page >= $scope.dpcoiWoOrderFileList.pageInfo.totalPage){
                        $scope.dpcoiWoOrderFileList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiWoOrderFileList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiWoOrderFileList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiWoOrderFileList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiWoOrderFileSearch").click(function () {
        $scope.dpcoiWoOrderFileList.firstPage();
    });


    $scope.dpcoiWoOrderFileList.downloadDpcoiWoOrderFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    }

    $scope.dpcoiWoOrderFileList.openDpcoiWoOrderFilePdf = function (fileAlias) {
        window.open("/WorkFlow/fileupload/"+fileAlias);
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

        $scope.dpcoiWoOrderFileList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiWoOrderFileList' ]);