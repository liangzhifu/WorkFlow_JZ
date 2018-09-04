function updateFileExcelChange(){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                var returnValue = {
                    fileId : data.fileId,
                    fileName : data.fileName
                };
                window.returnValue = returnValue;
                doClose();
            }
        });
    }
}

function doClose(){
    window.open('','_self','');
    window.close();
}