package com.success.task.derived.service.serviceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.success.common.info.domain.InfoValue;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.derived.service.DerivedWord;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.TaskOrderService;
import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.task.domain.TaskType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("derivedWord")
public class DerivedWordImpl implements DerivedWord {

	private Configuration configuration = null;
	
	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	public DerivedWordImpl(){
		configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");
	}
	
	@Override
	public void doWord(int orderId) throws Exception {
		// TODO Auto-generated method stub
		this.createWord(orderId);
	}
	
	public void createWord(int orderId) throws Exception{  
        Map<String,Object> dataMap=new HashMap<String,Object>();  
        getData(dataMap, orderId);  
        
        Template t=null;  
        try {  
        	configuration.setDirectoryForTemplateLoading(new File("E:/MyEclipse-JDK1.6/WorkFlow/WebRoot/templet"));  //FTL文件所存在的位置  
            t = configuration.getTemplate("5.ftl","UTF-8"); //文件名  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(new Date());
        String fileName = "f:/" + str + "-" + Math.random()*10000 + ".doc";
        File outFile = new File(fileName);  
        Writer out = null; 
        FileOutputStream fos=null; 
        try {  
        	fos = new FileOutputStream(outFile);  
            OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
            out = new BufferedWriter(oWriter);  
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
        }  
           
        try {  
            t.process(dataMap, out);  
            out.close();
            fos.close();
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	
	private void getData(Map<String, Object> dataMap, int orderId) throws Exception{   
        StringBuffer checked = new StringBuffer();
        StringBuffer check = new StringBuffer();
        
        checked.append("0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAPgADAP7/CQAGAAAAAAAAAAAAAAABAAAAAQAAAAAAAAAA");
        checked.append("EAAAAgAAAAEAAAD+////AAAAAAAAAAD/////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("///////////////////////////////////////////////////////////////////////////9");
        checked.append("/////v////7////+////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("/////////////////////////////////////////////////////////////////////////1IA");
        checked.append("bwBvAHQAIABFAG4AdAByAHkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAWAAUA//////////8BAAAAQB3Si0LszhGeDQCqAGAC8wAAAAAAAAAAAAAAAJAhisZ+PdEB");
        checked.append("AwAAAAABAAAAAAAAYwBvAG4AdABlAG4AdABzAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAABIAAgECAAAA//////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAUAAAAAAAAAABAEMAbwBtAHAATwBiAGoAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEgACAP///////////////wAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAB0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA////////");
        checked.append("////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAA");
        checked.append("AP7///8DAAAA/v//////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("////////////////////////////////////////////////////////////////////////////");
        checked.append("//////////////////////////////////////////////////////////////////////8AAjQA");
        checked.append("RgHAgAAAAAD///8AAAAAAAQAAAABAACACQAAgHIBAABYAQAAMQD+VkNoZWNrQm94MQBhAAACFAA1");
        checked.append("AAAABAAAAKUAAACGIgAAi1tTTwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAEA/v8DCgAA/////0Ad0otC7M4Rng0AqgBgAvMbAAAATWljcm9zb2Z0IEZvcm1z");
        checked.append("IDIuMCC4tNGhv/IAEAAAAEVtYmVkZGVkIE9iamVjdAARAAAARm9ybXMuQ2hlY2tCb3guMQD0ObJx");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        checked.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==");
        
        
        check.append("0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAPgADAP7/CQAGAAAAAAAAAAAAAAABAAAAAQAAAAAAAAAA");
        check.append("EAAAAgAAAAEAAAD+////AAAAAAAAAAD/////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("///////////////////////////////////////////////////////////////////////////9");
        check.append("/////v////7////+////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("/////////////////////////////////////////////////////////////////////////1IA");
        check.append("bwBvAHQAIABFAG4AdAByAHkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAWAAUA//////////8BAAAAQB3Si0LszhGeDQCqAGAC8wAAAAAAAAAAAAAAABD0KVCAPdEB");
        check.append("AwAAAAABAAAAAAAAYwBvAG4AdABlAG4AdABzAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAABIAAgECAAAA//////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAUAAAAAAAAAABAEMAbwBtAHAATwBiAGoAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEgACAP///////////////wAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAB0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA////////");
        check.append("////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAA");
        check.append("AP7///8DAAAA/v//////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("////////////////////////////////////////////////////////////////////////////");
        check.append("//////////////////////////////////////////////////////////////////////8AAjQA");
        check.append("RgHAgAAAAAD///8AAAAAAAQAAAABAACACQAAgHIBAABYAQAAMP/tC0NoZWNrQm94MQBlAAACFAA1");
        check.append("AAAABAAAAKUAAACGIgAAi1tTTwAAAAAAAAAAMIGt6/4HAAAAAAAAAAAAAAAAAAAAAAAAIIKt6/4H");
        check.append("AAAAAAAAAAAAAAEA/v8DCgAA/////0Ad0otC7M4Rng0AqgBgAvMbAAAATWljcm9zb2Z0IEZvcm1z");
        check.append("IDIuMCC4tNGhv/IAEAAAAEVtYmVkZGVkIE9iamVjdAARAAAARm9ybXMuQ2hlY2tCb3guMQD0ObJx");
        check.append("AAAAAAAAAAAAAAAAAAAAADCBrev+BwAAAAAAAAAAAAAAAAAAAAAAAKCMd+v+BwAAAQAAAAEAAABY");
        check.append("ORQCAAAAAIjXLAgAAAAAAAAAAAEAAABgORQCAAAAAFNVnuv+BwAAAAAAAAAAAAAAAAAAAAAAAEBG");
        check.append("UQYAAAAAUBGj6/4HAAAAx0kCAAAAAAAAAAAAAAAAANwsCAAAAADg4EMCAAAAAAAAAAAAAAAAAQEB");
        check.append("AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQAAAAAAAAAggq3r/gcAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        check.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADCBrev+BwAAAAAAAAAAAAAAAAAAAAAAAA==");
        	

        
        dataMap.put("order_time", "order_time");
//        dataMap.put("order_1", "order_1");
//        dataMap.put("order_2", "order_2");
//        dataMap.put("order_3", "order_3");
//        dataMap.put("order_4", "order_4");
//        dataMap.put("order_9", "order_9");
        dataMap.put("order_10", "order_10");
//        dataMap.put("order_8_13_value", "order_8_13_value");
        
//        dataMap.put("order_5_1", check.toString());
//        dataMap.put("order_5_2", check.toString());
//        dataMap.put("order_5_3", check.toString());
//        dataMap.put("order_5_4", check.toString());        
//        dataMap.put("order_6_5", check.toString());
//        dataMap.put("order_6_6", check.toString());
//        dataMap.put("order_6_7", check.toString());
//        dataMap.put("order_6_8", check.toString());
//        dataMap.put("order_6_9", check.toString());
//        dataMap.put("order_6_10", check.toString());
//        dataMap.put("order_6_11", check.toString());        
//        dataMap.put("order_7_12", check.toString());        
//        dataMap.put("order_8_13", check.toString());
        
        
     
        TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
        taskOrderQuery.setOrderId(orderId);
        try{
        	TaskOrder taskOrder = this.taskOrderService.getTaskOrderDetail(taskOrderQuery);
        	TaskType taskTyep = taskOrder.getTaskType();
        	List<TaskTypeInfo> taskTypeInfoList = taskTyep.getTaskTypeInfo();
        	List<TaskOrderInfo> taskOrderInfoList = taskOrder.getTaskOrderInfoList();
        	int i = 0;
        	int j = 0;
        	int l = 0;
        	for(i = 0; i < taskTypeInfoList.size(); i++){
        		TaskTypeInfo taskTypeInfo = taskTypeInfoList.get(i);
        		int taskTypeInfoId = taskTypeInfo.getTaskTypeInfoId();
        		for(j = 0; j < taskOrderInfoList.size(); j++){
        			TaskOrderInfo taskOrderInfo = taskOrderInfoList.get(j);
        			if(taskTypeInfoId == taskOrderInfo.getTaskTypeInfoId()){
        				int infoTypeId = taskTypeInfo.getInfoTypeId();
        				if(infoTypeId == 3){
        					String taskInfoValue = taskOrderInfo.getTaskInfoValue();
        					List<InfoValue> infoValueList = taskTypeInfo.getInfoValueList();
        					boolean flag = false;
        					for(l = 0; l < infoValueList.size(); l++){
        						InfoValue infoValue = infoValueList.get(l);
        						if(infoValue.getInfoKey()!= null && infoValue.getInfoKey()==1){
        							flag = true;
        						}
        					}
        					String[] checkeArry = null;
        					String inputStr = "";
        					if(flag){
        						String[] taskInfoValueArray = taskInfoValue.split("<<\\?><\\?>>");
        						String checkeStr = taskInfoValueArray[0];
        						inputStr = taskInfoValueArray[1];
        						if(!(checkeStr == null || "".equals(checkeStr))){
        							checkeArry = checkeStr.split(",");
        						}
        					}else {
        						String checkeStr = taskInfoValue;
        						if(!(checkeStr == null || "".equals(checkeStr))){
        							checkeArry = checkeStr.split(",");
        						}
        					}
        					for(l = 0; l < infoValueList.size(); l++){
        						InfoValue infoValue = infoValueList.get(l);
        						int infoId = infoValue.getInfoId();
        						boolean isChecked = false;
        						if(checkeArry != null){
        							for(int m = 0; m < checkeArry.length; m++){
        								String checkeArryValue = checkeArry[m];
        								if(checkeArryValue != null && "".equals(checkeArryValue)){
        									if(infoId == Integer.parseInt(checkeArryValue)){
        										isChecked = true;
        										break;
        									}
        								}
        							}
        						}
        						if(isChecked){
        							dataMap.put("order_"+taskTypeInfoId+"_"+infoId, checked.toString());
        						}else {
        							dataMap.put("order_"+taskTypeInfoId+"_"+infoId, check.toString());
        						}
        						if(infoValue.getInfoKey()!=null && infoValue.getInfoKey()==1){
        							dataMap.put("order_"+taskTypeInfoId+"_"+infoId+"_value", inputStr);
        						}
        					}
        				}else {
        					dataMap.put("order_"+taskTypeInfoId, taskOrderInfo.getTaskInfoValue());
        				}
        				break;
        			}
        		}
        	}
        	
            dataMap.put("wo_1_1_22_value", "wo_1_1_22_value");
            dataMap.put("wo_2_2_28_value", "wo_2_2_28_value");
            dataMap.put("wo_3_3_36_value", "wo_3_3_36_value");
            dataMap.put("wo_4_4_40_value", "wo_4_4_40_value");
            dataMap.put("wo_5_5_46_value", "wo_5_5_46_value");
            dataMap.put("wo_6_6_52_value", "wo_6_6_52_value");
            dataMap.put("wo_7_7_58_value", "wo_7_7_58_value");
            dataMap.put("wo_8_8_65_value", "wo_8_8_65_value");
            dataMap.put("wo_9_9_67_value", "wo_9_9_67_value");
            dataMap.put("wo_10_10_72_value", "wo_10_10_72_value");
            dataMap.put("wo_11_11_76_value", "wo_11_11_76_value");
            
            dataMap.put("wo_1_1_14", check.toString());
            dataMap.put("wo_1_1_15", check.toString());
            dataMap.put("wo_1_1_16", check.toString());
            dataMap.put("wo_1_1_17", check.toString());
            dataMap.put("wo_1_1_18", check.toString());
            dataMap.put("wo_1_1_19", check.toString());
            dataMap.put("wo_1_1_20", check.toString());
            dataMap.put("wo_1_1_21", check.toString());
            dataMap.put("wo_1_1_22", check.toString());
            dataMap.put("wo_2_2_23", check.toString());
            dataMap.put("wo_2_2_24", check.toString());
            dataMap.put("wo_2_2_25", check.toString());
            dataMap.put("wo_2_2_26", check.toString());
            dataMap.put("wo_2_2_27", check.toString());
            dataMap.put("wo_2_2_28", check.toString());
            dataMap.put("wo_3_3_29", check.toString());
            dataMap.put("wo_3_3_30", check.toString());
            dataMap.put("wo_3_3_31", check.toString());
            dataMap.put("wo_3_3_32", check.toString());
            dataMap.put("wo_3_3_33", check.toString());
            dataMap.put("wo_3_3_34", check.toString());
            dataMap.put("wo_3_3_35", check.toString());
            dataMap.put("wo_3_3_36", check.toString());
            dataMap.put("wo_4_4_37", check.toString());
            dataMap.put("wo_4_4_38", check.toString());
            dataMap.put("wo_4_4_39", check.toString());
            dataMap.put("wo_4_4_40", check.toString());
            dataMap.put("wo_5_5_41", check.toString());
            dataMap.put("wo_5_5_42", check.toString());
            dataMap.put("wo_5_5_43", check.toString());
            dataMap.put("wo_5_5_44", check.toString());
            dataMap.put("wo_5_5_45", check.toString());
            dataMap.put("wo_5_5_46", check.toString());
            dataMap.put("wo_6_6_47", check.toString());
            dataMap.put("wo_6_6_48", check.toString());
            dataMap.put("wo_6_6_49", check.toString());
            dataMap.put("wo_6_6_50", check.toString());
            dataMap.put("wo_6_6_51", check.toString());
            dataMap.put("wo_6_6_52", check.toString());
            dataMap.put("wo_7_7_53", check.toString());
            dataMap.put("wo_7_7_54", check.toString());
            dataMap.put("wo_7_7_55", check.toString());
            dataMap.put("wo_7_7_56", check.toString());
            dataMap.put("wo_7_7_57", check.toString());
            dataMap.put("wo_7_7_58", check.toString());
            dataMap.put("wo_8_8_59", check.toString());
            dataMap.put("wo_8_8_60", check.toString());
            dataMap.put("wo_8_8_61", check.toString());
            dataMap.put("wo_8_8_62", check.toString());
            dataMap.put("wo_8_8_63", check.toString());
            dataMap.put("wo_8_8_64", check.toString());
            dataMap.put("wo_8_8_65", check.toString());
            dataMap.put("wo_9_9_66", check.toString());
            dataMap.put("wo_9_9_67", check.toString());
            dataMap.put("wo_9_9_68", check.toString());        
            dataMap.put("wo_10_10_69", check.toString());
            dataMap.put("wo_10_10_70", check.toString());
            dataMap.put("wo_10_10_71", check.toString());
            dataMap.put("wo_10_10_72", check.toString());
            dataMap.put("wo_11_11_73", check.toString());
            dataMap.put("wo_11_11_74", check.toString());
            dataMap.put("wo_11_11_75", check.toString());
            dataMap.put("wo_11_11_76", check.toString());
        	
        }catch(Exception e){
        	e.printStackTrace();
        	throw e;
        }
    }

	public static void main(String[] args){
		DerivedWordImpl imp = new DerivedWordImpl();
		try{
			imp.doWord(1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
