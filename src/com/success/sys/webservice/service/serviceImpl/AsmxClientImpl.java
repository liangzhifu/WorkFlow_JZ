package com.success.sys.webservice.service.serviceImpl;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.success.sys.webservice.service.AsmxClient;

public class AsmxClientImpl implements AsmxClient {

	private String url = "http://s401app09.cn.takatacorp.com/TKWS/Service.asmx";//提供接口的地址  
	private String soapaction = "http://s401app09.cn.takatacorp.com/";   //域名，这是在server定义的 
    //private String TOKEN = "96ce90a5-0980-4708-8250-df0cd4ac67f1";//天津
    private String TOKEN = "A1CFE4CF-209C-479B-B013-953EAB88D4C4";//荆州
    
    public String createAccountService(String userName)throws Exception{
    	Service service = new Service();  
    	Call call = (Call)service.createCall();              
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName(this.soapaction,"createAccount"));
        call.addParameter(new QName(this.soapaction,"account_staffid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName(this.soapaction,"app_uid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName(this.soapaction,"app_status"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        call.setUseSOAPAction(true);  
        call.setSOAPActionURI(this.soapaction + "createAccount");
        String returnValue = (String)call.invoke(new Object[]{userName, this.TOKEN, "0"});
        return returnValue;
    }
    
    public String updateAccountService(String userName, String appStatus)throws Exception{
    	Service service = new Service();  
    	Call call = (Call)service.createCall();              
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName(this.soapaction,"updateAccount"));
        call.addParameter(new QName(this.soapaction,"account_staffid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName(this.soapaction,"app_uid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName(this.soapaction,"app_status"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        call.setUseSOAPAction(true);  
        call.setSOAPActionURI(this.soapaction + "updateAccount");
        String returnValue = (String)call.invoke(new Object[]{userName, this.TOKEN, appStatus});
        return returnValue;
    }
    
    public String staffInfoService(String userName)throws Exception{
    	Service service = new Service();  
    	Call call = (Call)service.createCall();              
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName(this.soapaction,"staffInfo"));
        call.addParameter(new QName(this.soapaction,"account_staffid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName(this.soapaction,"app_uid"), //设置要传递的参数  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
               javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        call.setUseSOAPAction(true);  
        call.setSOAPActionURI(this.soapaction + "staffInfo");
        String returnValue = (String)call.invoke(new Object[]{userName, this.TOKEN});
        return returnValue;
    }
    
}
