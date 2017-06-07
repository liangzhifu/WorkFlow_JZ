package com.success.sys.webservice.service.serviceImpl;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class AxisClientImpl {

	public String service() throws Exception{
		String result = "";
		try {
			String endpoint = "http://webservice.webxml.com.cn/webservices/qqOnlineWebService.asmx";
			
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(new QName(endpoint, "qqCheckOnline"));//WSDL里面描述的接口名称
			call.addParameter(new QName("http://WebXml.com.cn/","qqCode"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型

			String temp = "1009568392";
			call.setSOAPActionURI("http://WebXml.com.cn/qqCheckOnline");
			result = (String)call.invoke(new Object[]{temp});

			//给方法传递参数，并且调用方法

		}catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	public static void main(String[] args){
		AxisClientImpl impl = new AxisClientImpl();
		try{
			System.out.println(impl.service());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
