var myModal;

updateFileExcelChange = function (){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                alert(data.message);
                myModal.destroy();
            }
        });
        $("#uploadFile").val('');
    }
}

var dpcoiWoOrderListApp = angular.module("dpcoiWoOrderList", []);
dpcoiWoOrderListApp.controller("dpcoiWoOrderListController", function ($scope) {
    $scope.dpcoiWoOrderList = {};
    $scope.dpcoiWoOrderList.dpcoiWoOrderList = [{
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
        "dpcoiWoOrderState" : "",
        "taskOrderNo" : "",
        "remark" : ""
    }];
    $scope.dpcoiWoOrderList.pageInfo = {"url":"/WorkFlow/dpcoiWoOrder/getDpcoiWoOrderListPage.do"};
    $scope.dpcoiWoOrderList.firstPage = function () {
        $scope.dpcoiWoOrderList.pageInfo.page = 1;
        $scope.dpcoiWoOrderList.Search();
    }
    $scope.dpcoiWoOrderList.prevPage = function () {
        $scope.dpcoiWoOrderList.pageInfo.page = $scope.dpcoiWoOrderList.pageInfo.page - 1;
        $scope.dpcoiWoOrderList.Search();
    }
    $scope.dpcoiWoOrderList.nextPage = function () {
        $scope.dpcoiWoOrderList.pageInfo.page = $scope.dpcoiWoOrderList.pageInfo.page + 1;
        $scope.dpcoiWoOrderList.Search();
    }
    $scope.dpcoiWoOrderList.lastPage = function () {
        $scope.dpcoiWoOrderList.pageInfo.page = $scope.dpcoiWoOrderList.pageInfo.totalPage;
        $scope.dpcoiWoOrderList.Search();
    }
    $scope.dpcoiWoOrderList.searchForm = {
        "vehicleName": "",
        "releaseDateStart": "",
        "releaseDateEnd": "",
        "hopeCuttingDate": "",
        "productNo": "",
        "realCuttingDateStart": "",
        "realCuttingDateEnd": "",
        "productLine": ""
    }
    $scope.dpcoiWoOrderList.Search = function () {
        $scope.dpcoiWoOrderList.searchForm.realCuttingDateStart = $("#realCuttingDateStart").val();
        $scope.dpcoiWoOrderList.searchForm.realCuttingDateEnd = $("#realCuttingDateEnd").val();
        $scope.dpcoiWoOrderList.searchForm.releaseDateStart = $("#releaseDateStart").val();
        $scope.dpcoiWoOrderList.searchForm.releaseDateEnd = $("#releaseDateEnd").val();
        $scope.dpcoiWoOrderList.searchForm.pagenum = $scope.dpcoiWoOrderList.pageInfo.page-1;
        $scope.dpcoiWoOrderList.searchForm.pageCount = 10;
        $scope.dpcoiWoOrderList.searchForm.size = $scope.dpcoiWoOrderList.searchForm.pageCount;
        $scope.dpcoiWoOrderList.searchForm.start = $scope.dpcoiWoOrderList.searchForm.pagenum * $scope.dpcoiWoOrderList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.dpcoiWoOrderList.pageInfo.url,
            data:$scope.dpcoiWoOrderList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.dpcoiWoOrderList.dpcoiWoOrderList = result.dpcoiWoOrderList;
                    $scope.dpcoiWoOrderList.pageInfo.totalCount = result.dpcoiWoOrderCount;
                    $scope.dpcoiWoOrderList.pageInfo.totalPage =  result.pageCount;
                    if($scope.dpcoiWoOrderList.pageInfo.page <= 1){
                        $scope.dpcoiWoOrderList.pageInfo.firstPageDisabled = true;
                        $scope.dpcoiWoOrderList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.dpcoiWoOrderList.pageInfo.firstPageDisabled = false;
                        $scope.dpcoiWoOrderList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.dpcoiWoOrderList.pageInfo.page >= $scope.dpcoiWoOrderList.pageInfo.totalPage){
                        $scope.dpcoiWoOrderList.pageInfo.nextPageDisabled = true;
                        $scope.dpcoiWoOrderList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.dpcoiWoOrderList.pageInfo.nextPageDisabled = false;
                        $scope.dpcoiWoOrderList.pageInfo.lastPageDisabled = false;
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

    $("#dpcoiWoOrderSearch").click(function () {
        $scope.dpcoiWoOrderList.firstPage();
    });

    $scope.dpcoiWoOrderList.confirmWoOrder = function (dpcoiWoOrderId, confirmResult) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/confirmDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":dpcoiWoOrderId, "confirmResult":confirmResult},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiWoOrderList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiWoOrderList.uploadFile = function (dpcoiWoOrderId) {
        var path=$("#path").val();
        var html="<form method='post' id='excelForm' enctype='multipart/form-data' action='/WorkFlow/dpcoiWoOrderFile/uploadDpcoiWoOrderFile.do'>"+
            "<a class='uploadFile button button-primary button-rounded button-small' href='#'>" +
            "<input type='file' accept='application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/pdf, application/octet-stream' onchange='updateFileExcelChange()' name='uploadFile' id='uploadFile'/><i class='glyphicon glyphicon-search'></i>浏览" +
            "</a>"+
            "<input type='hidden' name='dpcoiWoOrderId' value='"+dpcoiWoOrderId+"'/>"
        "</form>";
        myModal = new jBox('Modal', {
            width: 150,
            title: '上传文件',
            content: html,
            onCloseComplete:function(){
                myModal.destroy();
            }
        }).open();
    }

    $scope.dpcoiWoOrderList.fileList = function (dpcoiWoOrderId, confirmResult) {
        if(confirmResult == 1){
            $scope.dpcoiWoOrderFile.action = "uploadFile";
        }else {
            $scope.dpcoiWoOrderFile.action = "managerConfirm";
        }
        $scope.dpcoiWoOrderFile.dpcoiWoOrderId = dpcoiWoOrderId;

        $scope.dpcoiWoOrderFile.search();

        $("#fileListModal").modal("show");
    }

    $scope.dpcoiWoOrderList.fileCompleteWoOrder = function (dpcoiWoOrderId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/fileCompleteDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":dpcoiWoOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiWoOrderList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiWoOrderList.managerConfirmWoOrder = function (confirmResult) {
        var woOrderFileIdStr = "";
        $("#fileListModal input[name=\"dpcoiWoOrderFileCheckbox\"]:checked ").each(function () {
            woOrderFileIdStr += "," + $(this).val();
        })
        if(woOrderFileIdStr != ""){
            woOrderFileIdStr = woOrderFileIdStr.substring(1);
        }
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/managerConfirmDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":$scope.dpcoiWoOrderFile.dpcoiWoOrderId, "confirmResult":confirmResult, "noPassedWoOrderFileIdStr":woOrderFileIdStr},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiWoOrderList.firstPage();
                    $("#fileListModal").modal("hide");
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiWoOrderFile = {};
    $scope.dpcoiWoOrderFile.action = "";
    $scope.dpcoiWoOrderFile.dpcoiWoOrderId = "";
    $scope.dpcoiWoOrderFile.dpcoiWoOrderFileList = [{
        "dpcoiWoOrdreFileId" : "",
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : ""
    }];
    $scope.dpcoiWoOrderFile.search = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/getDpcoiWoOrderFileList.do",
            data:{"dpcoiWoOrderId":$scope.dpcoiWoOrderFile.dpcoiWoOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiWoOrderFile.dpcoiWoOrderFileList = result.dpcoiWoOrderFileList;
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }
    $scope.dpcoiWoOrderFile.deleteDpcoiWoOrderFile = function (dpcoiWoOrderFileId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/deleteDpcoiWoOrderFile.do",
            data:{"id":dpcoiWoOrderFileId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    var newDpcoiWoOrderFileList = new Array();
                    var j = 0;
                    for(var i = 0; i < $scope.dpcoiWoOrderFile.dpcoiWoOrderFileList.length; i++){
                        var dpcoiWoOrderFile = $scope.dpcoiWoOrderFile.dpcoiWoOrderFileList[i];
                        if(dpcoiWoOrderFileId != dpcoiWoOrderFile.dpcoiWoOrderFileId){
                            newDpcoiWoOrderFileList[j] = dpcoiWoOrderFile;
                            j++;
                        }
                    }
                    $scope.dpcoiWoOrderFile.dpcoiWoOrderFileList = newDpcoiWoOrderFileList;
                    $scope.$apply();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.dpcoiWoOrderFile.downloadDpcoiWoOrderFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    }

    $scope.dpcoiWoOrderFile.openDpcoiWoOrderFilePdf = function (fileAlias) {
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

        $scope.dpcoiWoOrderList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

angular.bootstrap(document, [ 'dpcoiWoOrderList' ]);