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

var rrProblemWoOrderListApp = angular.module("rrProblemWoOrderList", []);
rrProblemWoOrderListApp.controller("rrProblemWoOrderListController", function ($scope) {
    $scope.rrProblemWoOrderList = {};
    $scope.rrProblemWoOrderList.dpcoiConfigList = [{
        "configId" : "",
        "configCodeId" : "",
        "configValue" : ""
    }];
    $scope.rrProblemWoOrderList.rrProblemWoOrderList = [{
        "problemStatus" : "",
        "problemNo" : "",
        "problemType" : "",
        "engineering" : "",
        "problemProgress" : "",
        "customer" : "",
        "vehicle" : "",
        "productNo" : "",
        "badContent" : "",
        "productLine" : "",
        "severity" : "",
        "reasonForDelay" : "",
        "speedOfProgress" : "",
        "rrProblemOrderId" : "",
        "rrProblemOrderState" : "",
        "rrProblemWoOrderId" : "",
        "rrProblemWoOrderState" : "",
        "rrProblemWoOrderType" : ""
    }];
    $scope.rrProblemWoOrderList.pageInfo = {"url":"/WorkFlow/rrProblemWoOrder/getRRProblemWoOrderListPage.do"};
    $scope.rrProblemWoOrderList.firstPage = function () {
        $scope.rrProblemWoOrderList.pageInfo.page = 1;
        $scope.rrProblemWoOrderList.Search();
    }
    $scope.rrProblemWoOrderList.prevPage = function () {
        $scope.rrProblemWoOrderList.pageInfo.page = $scope.rrProblemWoOrderList.pageInfo.page - 1;
        $scope.rrProblemWoOrderList.Search();
    }
    $scope.rrProblemWoOrderList.nextPage = function () {
        $scope.rrProblemWoOrderList.pageInfo.page = $scope.rrProblemWoOrderList.pageInfo.page + 1;
        $scope.rrProblemWoOrderList.Search();
    }
    $scope.rrProblemWoOrderList.lastPage = function () {
        $scope.rrProblemWoOrderList.pageInfo.page = $scope.rrProblemWoOrderList.pageInfo.totalPage;
        $scope.rrProblemWoOrderList.Search();
    }
    $scope.rrProblemWoOrderList.searchForm = {
        "badContent": "",
        "problemProgress": "",
        "speedOfProgress": "",
        "problemStatus" : "",
        "problemType" : "",
        "engineering" : "",
        "customer" : "",
        "vehicle" : "",
        "productNo" : "",
        "happenDateBegin" : "",
        "happenDateEnd" : "",
        "persionLiable" : "",
        "productLine" : "",
        "severity" : "",
        "responsibleDepartment" : ""
    }
    $scope.rrProblemWoOrderList.Search = function () {
        $scope.rrProblemWoOrderList.searchForm.speedOfProgress = $("#speedOfProgress").multiselect("MyValues");
        $scope.rrProblemWoOrderList.searchForm.happenDateBegin = $("#happenDateBegin").val();
        $scope.rrProblemWoOrderList.searchForm.happenDateEnd = $("#happenDateEnd").val();
        $scope.rrProblemWoOrderList.searchForm.pagenum = $scope.rrProblemWoOrderList.pageInfo.page-1;
        $scope.rrProblemWoOrderList.searchForm.pageCount = 10;
        $scope.rrProblemWoOrderList.searchForm.size = $scope.rrProblemWoOrderList.searchForm.pageCount;
        $scope.rrProblemWoOrderList.searchForm.start = $scope.rrProblemWoOrderList.searchForm.pagenum * $scope.rrProblemWoOrderList.searchForm.size;
        $.ajax({
            method:'post',
            url:$scope.rrProblemWoOrderList.pageInfo.url,
            data:$scope.rrProblemWoOrderList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.rrProblemWoOrderList.rrProblemWoOrderList = result.rrProblemWoOrderList;
                    $scope.rrProblemWoOrderList.pageInfo.totalCount = result.rrProblemWoOrderCount;
                    $scope.rrProblemWoOrderList.pageInfo.totalPage =  result.pageCount;
                    if($scope.rrProblemWoOrderList.pageInfo.page <= 1){
                        $scope.rrProblemWoOrderList.pageInfo.firstPageDisabled = true;
                        $scope.rrProblemWoOrderList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.rrProblemWoOrderList.pageInfo.firstPageDisabled = false;
                        $scope.rrProblemWoOrderList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.rrProblemWoOrderList.pageInfo.page >= $scope.rrProblemWoOrderList.pageInfo.totalPage){
                        $scope.rrProblemWoOrderList.pageInfo.nextPageDisabled = true;
                        $scope.rrProblemWoOrderList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.rrProblemWoOrderList.pageInfo.nextPageDisabled = false;
                        $scope.rrProblemWoOrderList.pageInfo.lastPageDisabled = false;
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

    $("#rrProblemWoOrderSearch").click(function () {
        $scope.rrProblemWoOrderList.firstPage();
    });

    $scope.rrProblemWoOrderList.confirmWoOrder = function (dpcoiWoOrderId, confirmResult) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/confirmDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":dpcoiWoOrderId, "confirmResult":confirmResult},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemWoOrderList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.rrProblemWoOrderList.uploadFile = function (dpcoiWoOrderId) {
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

    $scope.rrProblemWoOrderList.fileList = function (dpcoiWoOrderId, confirmResult) {
        if(confirmResult == 1){
            $scope.rrProblemWoOrderFile.action = "uploadFile";
        }else {
            $scope.rrProblemWoOrderFile.action = "managerConfirm";
        }
        $scope.rrProblemWoOrderFile.dpcoiWoOrderId = dpcoiWoOrderId;

        $scope.rrProblemWoOrderFile.search();

        $("#fileListModal").modal("show");
    }

    $scope.rrProblemWoOrderList.fileCompleteWoOrder = function (dpcoiWoOrderId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/fileCompleteDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":dpcoiWoOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemWoOrderList.firstPage();
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    }

    $scope.rrProblemWoOrderList.managerConfirmWoOrder = function (confirmResult) {
        var woOrderFileIdStr = "";
        $("#fileListModal input[name=\"rrProblemWoOrderFileCheckbox\"]:checked ").each(function () {
            woOrderFileIdStr += "," + $(this).val();
        })
        if(woOrderFileIdStr != ""){
            woOrderFileIdStr = woOrderFileIdStr.substring(1);
        }
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrder/managerConfirmDpcoiWoOrder.do",
            data:{"dpcoiWoOrderId":$scope.rrProblemWoOrderFile.dpcoiWoOrderId, "confirmResult":confirmResult, "noPassedWoOrderFileIdStr":woOrderFileIdStr},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemWoOrderList.firstPage();
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

    $scope.rrProblemWoOrderFile = {};
    $scope.rrProblemWoOrderFile.action = "";
    $scope.rrProblemWoOrderFile.dpcoiWoOrderId = "";
    $scope.rrProblemWoOrderFile.dpcoiWoOrderFileList = [{
        "dpcoiWoOrderFileId" : "",
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : ""
    }];
    $scope.rrProblemWoOrderFile.search = function () {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/getDpcoiWoOrderFileList.do",
            data:{"dpcoiWoOrderId":$scope.rrProblemWoOrderFile.dpcoiWoOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemWoOrderFile.dpcoiWoOrderFileList = result.dpcoiWoOrderFileList;
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
    $scope.rrProblemWoOrderFile.deleteDpcoiWoOrderFile = function (dpcoiWoOrderFileId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/deleteDpcoiWoOrderFile.do",
            data:{"id":dpcoiWoOrderFileId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    var newDpcoiWoOrderFileList = new Array();
                    var j = 0;
                    for(var i = 0; i < $scope.rrProblemWoOrderFile.dpcoiWoOrderFileList.length; i++){
                        var dpcoiWoOrderFile = $scope.rrProblemWoOrderFile.dpcoiWoOrderFileList[i];
                        if(dpcoiWoOrderFileId != dpcoiWoOrderFile.dpcoiWoOrderFileId){
                            newDpcoiWoOrderFileList[j] = dpcoiWoOrderFile;
                            j++;
                        }
                    }
                    $scope.rrProblemWoOrderFile.dpcoiWoOrderFileList = newDpcoiWoOrderFileList;
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

    $scope.rrProblemWoOrderFile.downloadDpcoiWoOrderFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    }

    $scope.rrProblemWoOrderFile.openDpcoiWoOrderFilePdf = function (fileAlias) {
        window.open("/WorkFlow/fileupload/"+fileAlias);
    }

    $(document).ready(function() {
        $("input[data-type='date']").each(function () {
            $(this).datetimepicker({
                timepicker: false,
                format: 'Y-m-d'
            });
        });

        $("#speedOfProgress").multiselect({
            checkAllText: "全选",
            uncheckAllText: '全不选',
            header: false,
            selectedList:4
        });

        $.ajax({
            method: 'post',
            url: "/WorkFlow/dpcoiConfig/getDpcoiConfigList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemWoOrderList.dpcoiConfigList = result.dpcoiConfigList;
                    $scope.$apply();
                }
            }
        });

        $scope.rrProblemWoOrderList.firstPage();

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

rrProblemWoOrderListApp.filter('myFilter', function() {
    return function(inputArray, configCodeId) {
        var array = [];
        for(var i = 0; i < inputArray.length ; i++){
            var obj = inputArray[i];
            var id = obj.configCodeId;
            if(id == configCodeId){
                array.push(obj);
            }
        }
        return array;
    };
});

angular.bootstrap(document, [ 'rrProblemWoOrderList' ]);