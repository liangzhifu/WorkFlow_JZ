var dpcoiOrderEditApp = angular.module("dpcoiOrderEdit", []);
dpcoiOrderEditApp.controller("dpcoiOrderEditController", function ($scope) {
    $scope.action = action;
    $scope.dpcoiOrderEdit = {};
    $scope.dpcoiOrderEdit.dpcoiOrder = {
        "issuedNo" : "",
        "designChangeNo" : "",
        "vehicleName" : "",
        "productNo" : "",
        "changeContent" : "",
        "designAct" : "",
        "hopeCuttingDate" : "",
        "realCuttingDate" : "",
        "releaseDate" : "",
        "returnDate" : "",
        "quasiAct" : "",
        "quasiActName" : "",
        "remark" : "",
        "dpcoiOrderType" : "",
        "productLine" : ""
    }

    $("#dpcoiOrderEditConfirm").click(function () {
        if(!($scope.action != "add" && $scope.dpcoiOrderEdit.dpcoiOrder.dpcoiOrderType == 2)){
            if(!$.html5Validate.isAllpass($("#issuedNo"))){
                return;
            }
            if(!$.html5Validate.isAllpass($("#designChangeNo"))){
                return;
            }
            if(!$.html5Validate.isAllpass($("#hopeCuttingDate"))){
                return;
            }
            if(!$.html5Validate.isAllpass($("#designAct"))){
                return;
            }
            if(!$.html5Validate.isAllpass($("#quasiAct"))){
                return;
            }
        }

        if(!$.html5Validate.isAllpass($("#vehicleName"))){
            return;
        }
        if(!$.html5Validate.isAllpass($("#productNo"))){
            return;
        }
        if(!$.html5Validate.isAllpass($("#changeContent"))){
            return;
        }
        if(!$.html5Validate.isAllpass($("#releaseDate"))){
            return;
        }
        $scope.dpcoiOrderEdit.dpcoiOrder.hopeCuttingDate = $("#hopeCuttingDate").val();
        $scope.dpcoiOrderEdit.dpcoiOrder.realCuttingDate = $("#realCuttingDate").val();
        $scope.dpcoiOrderEdit.dpcoiOrder.returnDate = $("#returnDate").val();
        $scope.dpcoiOrderEdit.dpcoiOrder.releaseDate = $("#releaseDate").val();
        $scope.dpcoiOrderEdit.dpcoiOrder.quasiAct = $("#quasiAct").val();
        var url = "";
        if($scope.action == "add"){
            url = "/WorkFlow/dpcoiOrder/addDpcoiOrder.do";
        }else {
            url = "/WorkFlow/dpcoiOrder/editDpcoiOrder.do";
        }
        $.ajax({
            method:'post',
            url:url,
            data:$scope.dpcoiOrderEdit.dpcoiOrder,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    window.location.href = "/WorkFlow/dpcoiOrder/getDpcoiOrderListDlg.do";
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    });

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
            minDate:new Date(),
            format: 'Y-m-d'
        });

        $.ajax({
            type : "POST",
            url : "/WorkFlow/dpcoiUser/getAutocompleteDpcoiUserList.do",
            success : function(result) {
                $('#quasiActName').autocomplete({
                    minLength : 0,
                    source : result.dpcoiUserList,
                    focus : function(event,	ui) {
                        $("#quasiActName").val(ui.item.label);
                        return false;
                    },
                    select : function(event, ui) {
                        $('#quasiActName').val(ui.item.label);
                        $('#quasiAct').val(ui.item.value);
                        return false;
                    },
                    // 当智能提示框关闭后会触发此事件,ui是空对象
                    close : function(event,	ui) {

                    }
                });
            },
            error : function(jqXHR, textStatus,
                             errorThrown) {
                alert("系统出现异常!!");
            }
        });

        if($scope.action != "add"){
            $scope.dpcoiOrderEdit.dpcoiOrder = angular.fromJson(dpcoiOrder);
            $scope.dpcoiOrderEdit.dpcoiOrder.returnDate = $scope.dpcoiOrderEdit.dpcoiOrder.returnDateStr;
            $scope.dpcoiOrderEdit.dpcoiOrder.releaseDate = $scope.dpcoiOrderEdit.dpcoiOrder.releaseDateStr;
            $scope.dpcoiOrderEdit.dpcoiOrder.realCuttingDate = $scope.dpcoiOrderEdit.dpcoiOrder.realCuttingDateStr;
            $scope.$apply();
        }else {
            var newDate = new Date();
            var year = newDate.getFullYear();
            var month = newDate.getMonth() + 1;
            var day = newDate.getDate();
            var monthStr = "" + month;
            if(month < 10){
                monthStr = "0" + month;
            }
            var dayStr = "" + day;
            if(day < 10){
                dayStr = "0" + day;
            }
            $scope.dpcoiOrderEdit.dpcoiOrder.releaseDate = year + "-" + monthStr + "-" + dayStr;
            var user = angular.fromJson(userJson);
            $scope.dpcoiOrderEdit.dpcoiOrder.quasiAct = user.userId;
            $scope.dpcoiOrderEdit.dpcoiOrder.quasiActName = user.userName;
            $scope.$apply();
        }
    });
});

angular.bootstrap(document, [ 'dpcoiOrderEdit' ]);