function updateFileExcelChange(){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                doClose();
            }
        });
    }
}

function doClose(){
    window.open('','_self','');
    window.close();
}