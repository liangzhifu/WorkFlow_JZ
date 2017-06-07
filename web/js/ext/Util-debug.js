Ext.MessageBox.buttonText={ok:'<%=getI18NResource("bandCommon.sure")%>',cancel:'<%=getI18NResource("bandCommon.cancel")%>',yes:'<%=getI18NResource("bandCommon.yes")%>',no:'<%=getI18NResource("bandCommon.no")%>'};
function IsInt(num){
  return ((num%1)==0)
}

function makeDOM(){
  var o,suffixs=[".4.0", ".3.0", ".2.0", ""];
  for(var i=0;i<suffixs.length;i++){
    try{
       if(window.ActiveXObject){
    	   o = new ActiveXObject("msxml2.DOMDocument"+ suffixs[i]);
       }else if(document.implementation && document.implementation.createDocument){
    	   o = document.implementation.createDocument('', '', null);
       }
      break;
    }catch(ex){};
  }
  o.async = false;o.validateOnParse = false;o.resolveExternals = false;return o;
}


function getObjectType(o){
  if(o==null) return 'n';
  switch(o.constructor){
    case Number:
      if(IsInt(o)) return 'i';
      else return 'f';
    case Boolean:
      return 'b';
    case String:
      return 's';
    case Date:
      return 'd';
    case Array:
      return 'a';
    default:
      if(o.constructor.toString().indexOf("Array")>0)
        return 'a';
      else if(o.constructor.toString().indexOf("Date")>0)
        return 'd'
      else return 'o';
  }
}

function packageObject(elm,type,arg){
   switch(type){
    case 'n':
      break;
    case 'b':
    case 'i':
    case 'l':
    case 'f':
    case 's':
      elm.text = StrEnCode(arg.toString());break;
    case 'd':
      elm.text = DateToString(new Date(arg.valueOf()), true);break;
    case 'o':
      for(var key in arg){
        var child =arg[key];
        var subtype = getObjectType(child);
        var childElm = elm.ownerDocument.createElement(subtype+key);
        elm.appendChild(childElm);
        packageObject(childElm,subtype,child);
      }
      break;
    case 'a':
      for(var i=0;i<arg.length;i++){
        var child =arg[i];
        var subtype = getObjectType(child);
        var childElm = elm.ownerDocument.createElement(subtype+"Item");
        elm.appendChild(childElm);
        packageObject(childElm,subtype,child);
      }
      break;
  }
}

function StrEnCode(s){
    //return (s)? s.replace(/'/g, "��").replace(/"/g, "��").replace(/</g, "��").replace(/>/g, "��").replace(/&/g, "��") : "";
	return s;
}

function toXml(){
    	if(arguments.length == 0){
    		return "";
    	}
    	var doc = makeDOM();
  		var node = doc.createProcessingInstruction("xml","version='1.0'");
  		doc.appendChild(node);
  		node = doc.appendChild(doc.createElement("Function"));
  		node.setAttribute("name",arguments[1]);
  		node.setAttribute("serviceName",arguments[0]);
  		if(arguments[2]){
		  node.setAttribute("userTransaction","true");
		}else{
		  node.setAttribute("userTransaction","false");
		}
  		node.setAttribute("generatedTime",new Date());
       
		for(var i=3;i<arguments.length;i++){
		    var elm = doc.createElement("Param");
		    var type = getObjectType(arguments[i]);
		    elm.setAttribute("type", type);
		    packageObject(elm, type, arguments[i]);
		    node.appendChild(elm);
		}
		
		 return doc.xml;
}

function packageError(oN){
  var e1 =new Error(0,oN.message);
  e1.code = oN.code;
  e1.resolve = oN.resolve;
  e1.detail = oN.detail;
  return e1;
}

function callRemoteFunction(){

	var arg = [];
	var feature = "dialogHeight:4;dialogWidth:25;status:no;center:yes;scroll:no;";
	var returnValue = null;
	if(arguments.length == 0){
		return false;
	}
	for(var i=0;i<arguments.length;i++){
	    arg.push(arguments[i]);
	}
	var xmlValue = toXml.apply(this,arg);
	var axajRequest = Ext.Ajax.request({
									url:'/IOMPROJ/iomextservlet',
									success: function(){},
									failure: function(){},
									params: { xml:xmlValue },
									listeners:{
									  requestexception:function(){
									  }
									}
	});
   if(axajRequest.conn.responseText == "null"){
		 returnValue = window.showModalDialog("/IOMPROJ/ext/public/Error.jsp",null,feature);
   }
   
   if(axajRequest.conn.responseText.indexOf("[") != -1 && axajRequest.conn.responseText.lastIndexOf("[") != 1){
	   try{
			/*returnValue = Ext.decode(axajRequest.conn.responseText.replace(new RegExp("\n", "g"),""));*/
			returnValue = Ext.decode(axajRequest.conn.responseText);
			if(returnValue.length>0){
				 if(returnValue[0].hasOwnProperty("Error")){
					  returnValue = window.showModalDialog("/IOMPROJ/ext/public/Error.jsp",returnValue,feature);
					  if(returnValue != null && returnValue != undefined && returnValue != 'undefined' && returnValue != 'null' && returnValue != ''){
						 //Ext.Msg.show({title:'<%=getI18NResource("common.systemDetailsError")%>',msg:'<textarea    value="' + returnValue + '" rows = "700" height = "500" > </textarea>'});
						 var errorMsgWin = new Ext.Window({
						   // applyTo:'search-More-win',
							layout:'fit',
							title:'    <%=getI18NResource("common.systemDetailsError")%>',
							width:700,
							height:500,
							closeAction:'hide',
							plain: true,
							modal:true,
							html:'<div contentEditable="true" style = "width:680px; height:465px; overflow:auto;">' + returnValue + '</div>'
						});
							errorMsgWin.show();
					  }
				 }
			}
	   }catch(e){
			 returnValue = axajRequest.conn.responseText;
	   }
   }else{
	    returnValue = axajRequest.conn.responseText;
   }
   axajRequest = null;
   return returnValue;
}

function callRemoteFunctionJson(){

	var arg = [];
	var feature = "dialogHeight:4;dialogWidth:25;status:no;center:yes;scroll:no;";
	var returnValue = null;
	if(arguments.length == 0){
		return false;
	}
	for(var i=0;i<arguments.length;i++){
	    arg.push(arguments[i]);
	}
	var xmlValue = toXml.apply(this,arg);
	var axajRequest = Ext.Ajax.request({
									url:'/IOMPROJ/iomextservlet',
									success: function(){},
									failure: function(){},
									params: { xml:xmlValue },
									listeners:{
									  requestexception:function(){
									  }
									}
	});
   if(axajRequest.conn.responseText == "null"){
		 returnValue = window.showModalDialog("/IOMPROJ/ext/public/Error.jsp",null,feature);
   }
   
	   try{
			/*returnValue = Ext.decode(axajRequest.conn.responseText.replace(new RegExp("\n", "g"),""));*/
			returnValue = Ext.decode(axajRequest.conn.responseText);
			if(returnValue.length>0){
				 if(returnValue[0] && returnValue[0].hasOwnProperty && returnValue[0].hasOwnProperty("Error")){
					  returnValue = window.showModalDialog("/IOMPROJ/ext/public/Error.jsp",returnValue,feature);
					  if(returnValue != null && returnValue != undefined && returnValue != 'undefined' && returnValue != 'null' && returnValue != ''){
						 //Ext.Msg.show({title:'<%=getI18NResource("common.systemDetailsError")%>',msg:'<textarea    value="' + returnValue + '" rows = "700" height = "500" > </textarea>'});
						 var errorMsgWin = new Ext.Window({
						   // applyTo:'search-More-win',
							layout:'fit',
							title:'    <%=getI18NResource("common.systemDetailsError")%>',
							width:700,
							height:500,
							closeAction:'hide',
							plain: true,
							modal:true,
							html:'<div contentEditable="true" style = "width:680px; height:465px; overflow:auto;">' + returnValue + '</div>'
						});
							errorMsgWin.show();
					  }
				 }
			}
	   }catch(e){
			 returnValue = axajRequest.conn.responseText;
	   }
   axajRequest = null;
   return returnValue;
}

function callRemoteFunctionAysn(){
	var arg = [];
	if(arguments.length == 0){
		return false;
	}
	for(var i=1;i<arguments.length;i++){
	    arg.push(arguments[i]);
	}
	var xmlValue = toXml.apply(this,arg);
	xmlValue=encodeURI(encodeURI(xmlValue));
	var paramStr;
	if(window.ActiveXObject){
		//ajax=new ActiveXObject("Microsoft.XMLHTTP");
		ajax = new ActiveXObject("MSXML2.XmlHttp");
	}
	else{
		ajax = new XMLHttpRequest();
	}
	try{
		ajax.onreadystatechange=arguments[0];
		paramStr = "xml=" + xmlValue;
		//设置servlet,访问后台
		ajax.open("POST","/IOMPROJ/iomextservlet",true);
		//向后台传参数必须加这条语句，否则参数无法传到后台
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajax.send(paramStr);
	}catch(e){
		alert(e.message);
	}
}

function callRemoteFunctionAysnCom(){
	var arg = [];
	var taskQueryajax;
	if(arguments.length == 0){
		return false;
	}
	for(var i=1;i<arguments.length;i++){
	    arg.push(arguments[i]);
	}
	var xmlValue = toXml.apply(this,arg);
	xmlValue=encodeURI(encodeURI(xmlValue));
	var paramStr;
	if(window.ActiveXObject){
		//ajax=new ActiveXObject("Microsoft.XMLHTTP");
		taskQueryajax = new ActiveXObject("MSXML2.XmlHttp");
	}
	else{
		taskQueryajax = new XMLHttpRequest();
	}
	try{
		taskQueryajax.onreadystatechange=arguments[0];
		paramStr = "xml=" + xmlValue;
		//设置servlet,访问后台
		taskQueryajax.open("POST","/IOMPROJ/iomextservlet",true);
		//向后台传参数必须加这条语句，否则参数无法传到后台
		taskQueryajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		taskQueryajax.send(paramStr);
		return taskQueryajax;
	}catch(e){
		alert(e.message);
	}
}