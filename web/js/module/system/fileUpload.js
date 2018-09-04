function updateFileExcelChange(){
    if($("#uploadFile").val()){
        $("#excelForm").ajaxSubmit({
            success:function(data){
                data = data.replace("<PRE>", "");
                data = data.replace("</PRE>", "");
                var jsonObj = {};
                if (typeof(JSON) == 'undefined'){
                    jsonObj = eval("("+data+")");
                }else{
                    jsonObj = JSON.parse(data);
                }
                var returnValue = {
                    fileId : jsonObj.fileId,
                    fileName : jsonObj.fileName
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