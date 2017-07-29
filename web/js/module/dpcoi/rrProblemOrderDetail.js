var dpcoiOrderDetailApp = angular.module("dpcoiOrderDetail", []);
dpcoiOrderDetailApp.controller("dpcoiOrderDetailController", function ($scope) {
    $scope.dpcoiOrderDeatil = {};
    $scope.dpcoiOrderDeatil.dpcoiWoOrderFileList = [{
        "dpcoiWoOrdreFileId" : "",
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : "",
        "fileCreateDateStr" : "",
        "createByName" : ""
    }];

    $scope.dpcoiOrderDeatil.downloadFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    }

    $scope.dpcoiOrderDeatil.openFilePdf = function (fileAlias) {
        window.open("/WorkFlow/fileupload/"+fileAlias);
    }

    $scope.dpcoiOrderDeatil.openFileList = function (dpcoiWoOrderId) {
        $scope.dpcoiOrderDeatil.searchWoOrderFileList(dpcoiWoOrderId);
        $("#fileListModal").modal("show");
    }

    $scope.dpcoiOrderDeatil.searchWoOrderFileList = function (dpcoiWoOrderId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/getDpcoiWoOrderFileList.do",
            data:{"dpcoiWoOrderId":dpcoiWoOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiOrderDeatil.dpcoiWoOrderFileList = result.dpcoiWoOrderFileList;
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

    $scope.dpcoiOrderDeatil.dpcoiOrderFileList = [{
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : ""
    }];

    $scope.dpcoiOrderDeatil.openOrderFileList = function (dpcoiOrderId) {
        $scope.dpcoiOrderDeatil.searchOrderFileList(dpcoiOrderId);
        $("#fileListModal2").modal("show");
    }

    $scope.dpcoiOrderDeatil.searchOrderFileList = function (dpcoiOrderId) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/dpcoiWoOrderFile/getDpcoiOrderFileList.do",
            data:{"dpcoiOrderId":dpcoiOrderId},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.dpcoiOrderDeatil.dpcoiOrderFileList = result.dpcoiOrderFileList;
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
});

angular.bootstrap(document, [ 'dpcoiOrderDetail' ]);