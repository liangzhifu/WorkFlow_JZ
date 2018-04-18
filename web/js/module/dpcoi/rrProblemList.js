var rrProblemListApp = angular.module("rrProblemList", []);
rrProblemListApp.controller("rrProblemListController", function ($scope) {
    $scope.rrProblemList = {};
    $scope.rrProblemList.ministerJurisdiction = ministerJurisdiction;
    $scope.rrProblemList.dpcoiConfigList = [{
        "configId" : 0,
        "configCodeId" : "",
        "configValue" : ""
    }];
    $scope.rrProblemList.rrProblemList = [{
        "id":"",
        "problemStatus" : "",
        "problemNo" : "",
        "problemType" : "",
        "engineering" : "",
        "problemProgress" : "",
        "trackingLevel" : "",
        "happenDate" : "",
        "customer" : "",
        "vehicle" : "",
        "productNo" : "",
        "badContent" : "",
        "persionLiable" : "",
        "reportDate" : "",
        "speedOfProgress" : "",
        "reasonForDelay" : "",
        "firstDate" : "",
        "secondDate" : "",
        "thirdDate" : "",
        "fourthDate" : "",
        "closeConfirm" : "",
        "productLine" : "",
        "severity" : "",
        "occurrenceFrequency" : "",
        "badQuantity" : "",
        "batch" : "",
        "happenShift" : "白班",
        "responsibleDepartment" : "",
        "recordPpm" : "",
        "recordNum" : "N/A",
        "temporary" : "",
        "rootCause" : "",
        "permanentGame" : "",
        "effectVerification" : "",
        "serialNumber" : "N/A",
        "qualityWarningCardNumber" : "N/A",
        "productScale" : "N/A",
        "pfmea" : "",
        "cp" : "",
        "standardBook" : "",
        "equipmentChecklist" : "",
        "alwaysList" : "",
        "inspectionReferenceBook" : "",
        "inspectionBook" : "",
        "education" : "",
        "changePoint" : "",
        "expandTrace" : "N/A",
        "artificial" : "N/A",
        "materiel" : "N/A",
        "state" : "",
        "isDelay": "",
        isRed:false,
        isGoldenRod:false,
        isYellow:false,
        isDeepSkyBlue:false
    }];
    $scope.rrProblemList.pageInfo = {"url":"/WorkFlow/rrProblem/getRRProblemListPage.do"};
    $scope.rrProblemList.firstPage = function () {
        $scope.rrProblemList.pageInfo.page = 1;
        $scope.rrProblemList.Search();
    }
    $scope.rrProblemList.prevPage = function () {
        $scope.rrProblemList.pageInfo.page = $scope.rrProblemList.pageInfo.page - 1;
        $scope.rrProblemList.Search();
    }
    $scope.rrProblemList.nextPage = function () {
        $scope.rrProblemList.pageInfo.page = $scope.rrProblemList.pageInfo.page + 1;
        $scope.rrProblemList.Search();
    }
    $scope.rrProblemList.lastPage = function () {
        $scope.rrProblemList.pageInfo.page = $scope.rrProblemList.pageInfo.totalPage;
        $scope.rrProblemList.Search();
    }
    $scope.rrProblemList.searchForm = {
        "badContent": badContent,
        "problemProgress": problemProgress,
        "speedOfProgress": speedOfProgress,
        "problemStatus" : problemStatus,
        "problemType" : problemType,
        "engineering" : engineering,
        "customer" : customer,
        "vehicle" : vehicle,
        "productNo" : productNo,
        "happenDateBegin" : happenDateBegin,
        "happenDateEnd" : happenDateEnd,
        "persionLiable" : persionLiable,
        "productLine" : productLine,
        "severity" : severity,
        "responsibleDepartment" : responsibleDepartment,
        "trackingLevel" : trackingLevel,
        "dpcoi4M" : dpcoi4M,
        "ids":""
    }

    $scope.getMultiselectValue = function(id){
        var idArray = $("#"+id).val();
        var idStr = "";
        if(!(idArray == undefined || idArray == null || idArray == "")){
            for(var i = 0; i < idArray.length; i++){
                idStr += "," + idArray[i];
            }
            if(idStr != ""){
                idStr = idStr.substring(1);
            }
        }
        return idStr;
    }

    $scope.rrProblemList.Search = function () {
        $scope.rrProblemList.searchForm.problemType = $scope.getMultiselectValue("problemType");
        $scope.rrProblemList.searchForm.engineering = $scope.getMultiselectValue("engineering");
        $scope.rrProblemList.searchForm.problemProgress = $scope.getMultiselectValue("problemProgress");
        $scope.rrProblemList.searchForm.speedOfProgress = $scope.getMultiselectValue("speedOfProgress");
        $scope.rrProblemList.searchForm.trackingLevel = $scope.getMultiselectValue("trackingLevel");
        $scope.rrProblemList.searchForm.dpcoi4M = $scope.getMultiselectValue("dpcoi4M");
        $scope.rrProblemList.searchForm.happenDateBegin = $("#happenDateBegin").val();
        $scope.rrProblemList.searchForm.happenDateEnd = $("#happenDateEnd").val();
        $scope.rrProblemList.searchForm.pagenum = $scope.rrProblemList.pageInfo.page-1;
        $scope.rrProblemList.searchForm.pageCount = 10;
        $scope.rrProblemList.searchForm.size = $scope.rrProblemList.searchForm.pageCount;
        $scope.rrProblemList.searchForm.start = $scope.rrProblemList.searchForm.pagenum * $scope.rrProblemList.searchForm.size;
        $scope.rrProblemList.searchForm.productLine = $scope.rrProblemList.searchForm.productLine.toUpperCase();
        $.ajax({
            method:'post',
            url:$scope.rrProblemList.pageInfo.url,
            data:$scope.rrProblemList.searchForm,
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if(result.success){
                    $scope.rrProblemList.rrProblemList = result.rrProblemList;
                    $scope.rrProblemList.pageInfo.totalCount = result.rrProblemCount;
                    $scope.rrProblemList.pageInfo.totalPage =  result.pageCount;
                    if($scope.rrProblemList.pageInfo.page <= 1){
                        $scope.rrProblemList.pageInfo.firstPageDisabled = true;
                        $scope.rrProblemList.pageInfo.prevPageDisabled = true;
                    }else {
                        $scope.rrProblemList.pageInfo.firstPageDisabled = false;
                        $scope.rrProblemList.pageInfo.prevPageDisabled = false;
                    }
                    if($scope.rrProblemList.pageInfo.page >= $scope.rrProblemList.pageInfo.totalPage){
                        $scope.rrProblemList.pageInfo.nextPageDisabled = true;
                        $scope.rrProblemList.pageInfo.lastPageDisabled = true;
                    }else {
                        $scope.rrProblemList.pageInfo.nextPageDisabled = false;
                        $scope.rrProblemList.pageInfo.lastPageDisabled = false;
                    }
                    for(var i = 0; i < $scope.rrProblemList.rrProblemList.length; i++){
                        $scope.rrProblemList.rrProblemList[i].checkBoxValue="no";
                        $scope.rrProblemList.rrProblemList[i].isRed=false;
                        $scope.rrProblemList.rrProblemList[i].isGoldenRod=false;
                        $scope.rrProblemList.rrProblemList[i].isYellow=false;
                        var obj = $scope.rrProblemList.rrProblemList[i];
                        var trackingLevel = obj.trackingLevel;
                        if(trackingLevel == undefined || trackingLevel == null || trackingLevel == "" || trackingLevel == "V"){

                        }else if(trackingLevel == "I"){
                            $scope.rrProblemList.rrProblemList[i].isRed = true;
                        }else if(trackingLevel == "II"){
                            $scope.rrProblemList.rrProblemList[i].isRed = true;
                        }else if(trackingLevel == "III"){
                            $scope.rrProblemList.rrProblemList[i].isGoldenRod = true;
                        }else if(trackingLevel == "IV"){
                            $scope.rrProblemList.rrProblemList[i].isYellow = true;
                        }else{
                        }
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

    $("#rrProblemSearch").click(function () {
        $scope.rrProblemList.firstPage();
    });

    $("#rrProblemAdd").click(function () {
        window.location.href = "/WorkFlow/rrProblem/getRRProblemAddDlg.do";
    });

    $("#rrProblemEdit").click(function () {
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        var rrProblem = null;
        for(var i = 0; i < $scope.rrProblemList.rrProblemList.length; i++){
            var obj = $scope.rrProblemList.rrProblemList[i];
            var rrProblemId = obj.id;
            if(id == rrProblemId){
                rrProblem = obj;
            }
        }
        if(rrProblem != null){
            var state = rrProblem.state;
            if(state == 2){
                if($scope.rrProblemList.ministerJurisdiction != 1){
                    alert("已关闭，不能修改！");
                    return;
                }
            }else if(state == 3){
                alert("已作废，不能修改！");
                return;
            }
            var persionLiable = ","+rrProblem.persionLiable+",";
            if(persionLiable.indexOf(","+userName+",") < 0){
                alert("你不是责任人，不能修改此问题点。");
                return ;
            }
        }
        var searchStr = ""
        searchStr += "badContent=" + $scope.rrProblemList.searchForm.badContent;
        searchStr += "&problemProgress=" + $scope.getMultiselectValue("problemProgress");
        searchStr += "&problemStatus=" + $scope.rrProblemList.searchForm.problemStatus;
        searchStr += "&problemType=" + $scope.getMultiselectValue("problemType");
        searchStr += "&engineering=" + $scope.getMultiselectValue("engineering");
        searchStr += "&trackingLevel=" + $scope.getMultiselectValue("trackingLevel");
        searchStr += "&dpcoi4M=" + $scope.getMultiselectValue("dpcoi4M");
        searchStr += "&customer=" + $scope.rrProblemList.searchForm.customer;
        searchStr += "&vehicle=" + $scope.rrProblemList.searchForm.vehicle;
        searchStr += "&productNo=" + $scope.rrProblemList.searchForm.productNo;
        searchStr += "&happenDateBegin=" + $("#happenDateBegin").val();
        searchStr += "&happenDateEnd=" + $("#happenDateEnd").val();
        searchStr += "&persionLiable=" + $scope.rrProblemList.searchForm.persionLiable;
        searchStr += "&productLine=" + $scope.rrProblemList.searchForm.productLine;
        searchStr += "&severity=" + $scope.rrProblemList.searchForm.severity;
        searchStr += "&responsibleDepartment=" + $scope.rrProblemList.searchForm.responsibleDepartment;
        searchStr += "&size=" + $scope.rrProblemList.searchForm.pageCount;
        searchStr += "&start=" + $scope.rrProblemList.searchForm.pagenum * $scope.rrProblemList.searchForm.size;
        window.location.href = "/WorkFlow/rrProblem/getRRProblemEditDlg.do?id="+id+"&"+searchStr;
    });

    $("#rrProblemDown").click(function () {
        var ids = "";
        $("input[name='checkbox_records']:checked").each(function () {
            ids += "," + $(this).val();
        });
        if(ids != "") ids = ids.substring(1);
        $scope.rrProblemList.searchForm.ids = ids;
        var fileUrl = "";
        $.ajax({
            url : '/WorkFlow/rrProblem/doExportExcel.do',
            async: false,
            data : $scope.rrProblemList.searchForm,
            method: "post",
            success : function(resultJson) {
                var obj =  angular.fromJson(resultJson);
                if(obj.success){
                    fileUrl = '/WorkFlow' + obj.path;
                }else {
                    alert(obj.message);
                    return;
                }
            },
            failure : function() {
                alert('导出Excel失败！');
                return;
            }
        });
        if(fileUrl != ""){
            window.open(fileUrl);
        }
    });

    $("#rrProblemClose").click(function () {
        if($scope.rrProblemList.ministerJurisdiction == 0){
            alert("你没有部长权限！");
            return;
        }
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        $.ajax({
            url : '/WorkFlow/rrProblem/closeRRProblem.do?id='+id,
            method: "post",
            success : function(resultJson) {
                var obj =  angular.fromJson(resultJson);
                if(obj.success){
                    $scope.rrProblemList.firstPage();
                }else {
                    alert(obj.message);
                    return;
                }
            },
            failure : function() {
                alert('失败！');
                return;
            }
        });
    });

    $("#rrProblemDelay").click(function () {
        if($scope.rrProblemList.ministerJurisdiction == 0){
            alert("你没有部长权限！");
            return;
        }
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        $.ajax({
            url : '/WorkFlow/rrProblem/delayRRProblem.do?id='+id,
            method: "post",
            success : function(resultJson) {
                var obj =  angular.fromJson(resultJson);
                if(obj.success){
                    $scope.rrProblemList.firstPage();
                }else {
                    alert(obj.message);
                    return;
                }
            },
            failure : function() {
                alert('失败！');
                return;
            }
        });
    });
    $("#rrProblemHide").click(function () {
        if($scope.rrProblemList.ministerJurisdiction == 0){
            alert("你没有部长权限！");
            return;
        }
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        $.ajax({
            url : '/WorkFlow/rrProblem/hideRRProblem.do?id='+id,
            method: "post",
            success : function(resultJson) {
                var obj =  angular.fromJson(resultJson);
                if(obj.success){
                    $scope.rrProblemList.firstPage();
                }else {
                    alert(obj.message);
                    return;
                }
            },
            failure : function() {
                alert('失败！');
                return;
            }
        });
    });
    $("#rrProblemCancleHide").click(function () {
        if($scope.rrProblemList.ministerJurisdiction == 0){
            alert("你没有部长权限！");
            return;
        }
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        $.ajax({
            url : '/WorkFlow/rrProblem/cancleHideRRProblem.do?id='+id,
            method: "post",
            success : function(resultJson) {
                var obj =  angular.fromJson(resultJson);
                if(obj.success){
                    $scope.rrProblemList.firstPage();
                }else {
                    alert(obj.message);
                    return;
                }
            },
            failure : function() {
                alert('失败！');
                return;
            }
        });
    });

    $("#rrProblemToVoid").click(function () {
        var length = $("input[name='checkbox_records']:checked").length;
        if(length == 0 || length > 1){
            alert("请选择一条RR问题点！");
            return ;
        }
        var id;
        $("input[name='checkbox_records']:checked").each(function () {
            id = $(this).val();
        });
        var con = confirm("确认作废RR问题点！")
        if (con == true){
            $.ajax({
                url : '/WorkFlow/rrProblem/toVoidRRProblem.do?id='+id,
                method: "post",
                success : function(resultJson) {
                    var obj =  angular.fromJson(resultJson);
                    if(obj.success){
                        $scope.rrProblemList.firstPage();
                    }else {
                        alert(obj.message);
                        return;
                    }
                },
                failure : function() {
                    alert('失败！');
                    return;
                }
            });
        }
    });

    $scope.checkBoxChange = function(index){

        if($scope.rrProblemList.rrProblemList[index].checkBoxValue == "yes"){
            $scope.rrProblemList.rrProblemList[index].checkBoxValue = "no"
            var obj = $scope.rrProblemList.rrProblemList[index];
            var trackingLevel = obj.trackingLevel;
            if(trackingLevel == undefined || trackingLevel == null || trackingLevel == "" || trackingLevel == "V"){
                $("#table_tr_"+index).find("td").each(function () {
                    $(this).css("background-color","#FFFFFF");
                });
            }else if(trackingLevel == "I"){
                    $("#table_tr_"+index).find("td").each(function () {
                        $(this).css("background-color","red");
                    });
            }else if(trackingLevel == "II"){
                    $("#table_tr_"+index).find("td").each(function () {
                        $(this).css("background-color","red");
                    });
            }else if(trackingLevel == "III"){
                    $("#table_tr_"+index).find("td").each(function () {
                        $(this).css("background-color","GoldenRod");
                    });
            }else if(trackingLevel == "IV"){
                    $("#table_tr_"+index).find("td").each(function () {
                        $(this).css("background-color","yellow");
                    });
            }else{
                $("#table_tr_"+index).find("td").each(function () {
                    $(this).css("background-color","#FFFFFF");
                });
            }
        }else {
            $scope.rrProblemList.rrProblemList[index].checkBoxValue = "yes";
            $("#table_tr_"+index).find("td").each(function () {
                $(this).css("background-color","#fff5d2");
            })
        }
    };

    $scope.rrProblemList.downloadFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    };

    $scope.rrProblemList.dpcoiWoOrderFileList = [{
        "dpcoiWoOrdreFileId" : "",
        "fileId" : "",
        "fileName" : "",
        "fileType" : "",
        "fileAlias" : "",
        "excelPdfName" : ""
    }];

    $scope.rrProblemList.downloadDpcoiWoOrderFile = function (fileId) {
        window.open("/WorkFlow/fileUpload/download.do?fileId="+fileId);
    };

    $scope.rrProblemList.fileList = function (rrProblemId, dpcoiWoOrderType) {
        $.ajax({
            method:'post',
            url:"/WorkFlow/rrProblemOrder/getDpcoiWoOrderFileList.do",
            data:{"rrProblemId":rrProblemId, "dpcoiWoOrderType":dpcoiWoOrderType},
            success: function(resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemList.dpcoiWoOrderFileList = result.dpcoiWoOrderFileList;
                    $scope.$apply();
                    $("#fileListModal").modal("show");
                }else {
                    alert(result.message);
                }
            },
            error : function() {
                alert("系统出现异常!!");
            }
        });
    };

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
        if(speedOfProgress != ''){
            var sceneIdArr = speedOfProgress.split(",");
            $('#speedOfProgress option').each(function(i,content){
                //alert(i+"***"+content.value);
                if($.inArray($.trim(content.value),sceneIdArr)>=0){
                    this.selected=true;
                }
            });
            $("#speedOfProgress").multiselect('refresh');
        }

        $.ajax({
            method: 'post',
            url: "/WorkFlow/dpcoiConfig/getDpcoiConfigList.do",
            success: function (resultJson) {
                var result = angular.fromJson(resultJson);
                if (result.success) {
                    $scope.rrProblemList.dpcoiConfigList = result.dpcoiConfigList;
                    $scope.$apply();
                    $("#problemProgress").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if(problemProgress != ''){
                        var sceneIdArr = problemProgress.split(",");
                        $('#problemProgress option').each(function(i,content){
                            //alert(i+"***"+content.value);
                            if($.inArray($.trim(content.value),sceneIdArr)>=0){
                                this.selected=true;
                            }
                        });
                        $("#problemProgress").multiselect('refresh');
                    }
                    $("#engineering").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if(engineering != ''){
                        var sceneIdArr = engineering.split(",");
                        $('#engineering option').each(function(i,content){
                            //alert(i+"***"+content.value);
                            if($.inArray($.trim(content.value),sceneIdArr)>=0){
                                this.selected=true;
                            }
                        });
                        $("#engineering").multiselect('refresh');
                    }
                    $("#problemType").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if(problemType != ''){
                        var sceneIdArr = problemType.split(",");
                        $('#problemType option').each(function(i,content){
                            //alert(i+"***"+content.value);
                            if($.inArray($.trim(content.value),sceneIdArr)>=0){
                                this.selected=true;
                            }
                        });
                        $("#problemType").multiselect('refresh');
                    }
                    $("#trackingLevel").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if(trackingLevel != ''){
                        var sceneIdArr = trackingLevel.split(",");
                        $('#trackingLevel option').each(function(i,content){
                            //alert(i+"***"+content.value);
                            if($.inArray($.trim(content.value),sceneIdArr)>=0){
                                this.selected=true;
                            }
                        });
                        $("#trackingLevel").multiselect('refresh');
                    }
                    $("#dpcoi4M").multiselect({
                        checkAllText: "全选",
                        uncheckAllText: '全不选',
                        header: false,
                        selectedList:4
                    });
                    if(dpcoi4M != ''){
                        var sceneIdArr = dpcoi4M.split(",");
                        $('#dpcoi4M option').each(function(i,content){
                            //alert(i+"***"+content.value);
                            if($.inArray($.trim(content.value),sceneIdArr)>=0){
                                this.selected=true;
                            }
                        });
                        $("#dpcoi4M").multiselect('refresh');
                    }
                    $scope.rrProblemList.pageInfo.page = pageNum;
                    $scope.rrProblemList.Search();
                }
            }
        });

        var height = document.body.clientHeight;
        var searchTableHeight = $("#searchTable").height();
        var footTableHeight = $("#footTable").height();
        var height = height - searchTableHeight - footTableHeight - 40;
        $("#listTable").css("height", height);
    });
});

rrProblemListApp.filter('myFilter', function() {
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

angular.bootstrap(document, [ 'rrProblemList' ]);