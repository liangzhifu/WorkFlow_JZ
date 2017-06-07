package com.success.task.derived.service.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.success.templet.tache.query.TaskTacheQuery;
import com.success.templet.tache.service.TaskTacheService;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.success.agreement.base.domain.Agreement;
import com.success.agreement.base.domain.AgreementContent;
import com.success.agreement.base.domain.AgreementTache;
import com.success.agreement.base.service.AgreementService;
import com.success.common.info.domain.InfoValue;
import com.success.task.base.domain.TaskConfirmOrder;
import com.success.task.base.domain.TaskOrderInfo;
import com.success.task.base.domain.TaskWoOrderInfo;
import com.success.task.derived.service.ExportPDF;
import com.success.task.detail.domain.TaskOrder;
import com.success.task.detail.domain.TaskWoOrder;
import com.success.task.detail.query.TaskOrderQuery;
import com.success.task.detail.service.TaskOrderService;
import com.success.templet.content.domain.TaskTacheInfo;
import com.success.templet.content.domain.TaskTypeInfo;
import com.success.templet.tache.domain.TaskTache;
import com.success.templet.task.domain.TaskType;

@Service("exportPDF")
public class ExportPDFImpl implements ExportPDF {

	@Resource(name = "taskOrderService")
	private TaskOrderService taskOrderService;
	
	@Resource(name = "agreementService")
	private AgreementService agreementService;

	@Resource(name = "taskTacheService")
	private TaskTacheService taskTacheService;
	
	public String exportPDF(int orderId, String path) throws Exception{
		TaskOrderQuery taskOrderQuery = new TaskOrderQuery();
		taskOrderQuery.setOrderId(orderId);
		TaskOrder taskOrder = this.taskOrderService.getTaskOrderDetail(taskOrderQuery);
		TaskType taskType = taskOrder.getTaskType();
		
		String pdfName = "";
		String TemplatePDF = path + "templet/templet-" + taskOrder.getVersion() + ".pdf";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String randNum = this.getRandNum();
		pdfName = sdf.format(date)+"_"+randNum+".pdf";
		FileOutputStream fos = new FileOutputStream(path+"stdout/"+pdfName);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        PdfReader reader = new PdfReader(TemplatePDF);  
        PdfStamper stamp = new PdfStamper(reader, baos);  
        AcroFields form = stamp.getAcroFields();
        		
		int i = 0;
		int j = 0;
		int m = 0;
		//定单信息
		List<TaskOrderInfo> taskOrderInfoList = taskOrder.getTaskOrderInfoList();
		List<TaskTypeInfo> taskTypeInfoList = taskType.getTaskTypeInfo();
		for(i = 0; i < taskTypeInfoList.size(); i++){
			TaskTypeInfo taskTypeInfo = taskTypeInfoList.get(i);
			Integer taskTypeInfoId = taskTypeInfo.getTaskTypeInfoId();
			TaskOrderInfo taskOrderInfo = null;
			for(j = 0; j < taskOrderInfoList.size(); j++){
				TaskOrderInfo tempTaskOrderInfo = taskOrderInfoList.get(j);
				if(taskTypeInfoId.equals(tempTaskOrderInfo.getTaskTypeInfoId())){
					taskOrderInfo = tempTaskOrderInfo;
					break;
				}
			}
			Integer infoTypeId = taskTypeInfo.getInfoTypeId();
			if(infoTypeId == 3){
				List<InfoValue> infoValueList = taskTypeInfo.getInfoValueList();
				boolean flag = false;
				for(j = 0; j < infoValueList.size(); j++){
					InfoValue infoValue = infoValueList.get(j);
					Integer infoKey = infoValue.getInfoKey();
					if(infoKey != null && infoKey == 1){
						flag = true;
					}
				}
				String taskInfoValue = taskOrderInfo.getTaskInfoValue();
				if(flag){
					int index = taskInfoValue.indexOf("<<?><?>>");
					String checkedStr = taskInfoValue.substring(0, index);
					String inputValue = taskInfoValue.substring(index + 8);
					String[] checkedArray = new String[0];
					if(!("".equals(checkedStr))){
						checkedArray = checkedStr.split(",");
					}
					for(j = 0; j < infoValueList.size(); j++){
						InfoValue infoValue = infoValueList.get(j);
						Integer infoId = infoValue.getInfoId();
						boolean checkedFlag = false;
						for(m = 0; m < checkedArray.length; m++){
							String checkedValue = checkedArray[m];
							if((!"".equals(checkedValue)) && infoId.equals(Integer.valueOf(checkedValue))){
								checkedFlag = true;
							}
						}
						if(checkedFlag){
							form.setField("order-" + taskTypeInfoId + "-" + infoId, "true");
						}
						Integer infoKey = infoValue.getInfoKey();
						if(infoKey != null && infoKey == 1){
							form.setField("order-" + taskTypeInfoId + "-" + infoId + "-value", inputValue);
						}
					}
				}else {
					String[] checkedArray = taskInfoValue.split(",");
					for(j = 0; j < infoValueList.size(); j++){
						InfoValue infoValue = infoValueList.get(j);
						Integer infoId = infoValue.getInfoId();
						boolean checkedFlag = false;
						for(m = 0; m < checkedArray.length; m++){
							String checkedValue = checkedArray[m];
							if((!("".equals(checkedValue))) && infoId.equals(Integer.valueOf(checkedValue))){
								checkedFlag = true;
							}
						}
						if(checkedFlag){
							form.setField("order-" + taskTypeInfoId + "-" + infoId, "true");
						}
					}
				}
			}else {
				if(taskTypeInfoId != 12) {
					form.setField("order-" + taskTypeInfoId, taskOrderInfo.getTaskInfoValue());
				}
			}
			
		}
		
		//工单信息
		List<TaskWoOrder> taskWoOrderList = taskOrder.getTaskWoOrderList();
		List<TaskTache> taskTacheList = taskType.getTaskTache();
		for(i = 0; i < taskTacheList.size(); i++){
			TaskTache taskTache = taskTacheList.get(i);
			Integer tacheId = taskTache.getTacheId();
			TaskWoOrder taskWoOrder = null;
			for(j = 0; j < taskWoOrderList.size(); j++){
				TaskWoOrder tempTaskWoOrder = taskWoOrderList.get(j);
				Integer tempTacheId = tempTaskWoOrder.getTacheId();
				if(tempTacheId != null && (tempTacheId.equals(tacheId))){
					taskWoOrder = tempTaskWoOrder;
					break;
				}
			}
			List<TaskTacheInfo> taskTacheInfoList = taskTache.getTaskTacheInfo();
			for(j = 0; j < taskTacheInfoList.size(); j++){
				TaskTacheInfo taskTacheInfo = taskTacheInfoList.get(j);
				Integer taskTacheInfoId = taskTacheInfo.getTaskTacheInfoId();
				List<TaskWoOrderInfo> taskWoOrderInfoList = taskWoOrder.getTaskWoOrderInfoList();
				TaskWoOrderInfo taskWoOrderInfo = null;
				for(m = 0; m < taskWoOrderInfoList.size(); m ++){
					TaskWoOrderInfo tempTaskWoOrderInfo = taskWoOrderInfoList.get(m);
					Integer tempTaskTacheInfoId = tempTaskWoOrderInfo.getTaskTacheInfoId();
					if(tempTaskTacheInfoId != null && tempTaskTacheInfoId.equals(taskTacheInfoId)){
						taskWoOrderInfo = tempTaskWoOrderInfo;
						break;
					}
				}
				Integer infoTypeId = taskTacheInfo.getInfoTypeId();
				if(infoTypeId == 3){
					List<InfoValue> infoValueList = taskTacheInfo.getInfoValueList();
					boolean flag = false;
					for(m = 0; m < infoValueList.size(); m++){
						InfoValue infoValue = infoValueList.get(m);
						Integer infoKey = infoValue.getInfoKey();
						if(infoKey != null && infoKey == 1){
							flag = true;
						}
					}
					String woInfoValue = taskWoOrderInfo.getWoInfoValue();
					if(flag){
						int index = woInfoValue.indexOf("<<?><?>>");
						String checkedStr = woInfoValue.substring(0, index);
						String inputValue = woInfoValue.substring(index + 8);
						String[] checkedArray = new String[0];
						if(!("".equals(checkedStr))){
							checkedArray = checkedStr.split(",");
						}
						for(j = 0; j < infoValueList.size(); j++){
							InfoValue infoValue = infoValueList.get(j);
							Integer infoId = infoValue.getInfoId();
							boolean checkedFlag = false;
							for(m = 0; m < checkedArray.length; m++){
								String checkedValue = checkedArray[m];
								if((!"".equals(checkedValue)) && infoId.equals(Integer.valueOf(checkedValue))){
									checkedFlag = true;
								}
							}
							if(checkedFlag){
								form.setField("wo-" + tacheId + "-" + taskTacheInfoId + "-" + infoId, "true");
							}
							if(infoId == 54 || infoId == 364 || infoId == 192){
								if(checkedFlag){
									form.setField("other-1", "true");
								}else {
									form.setField("other-2", "true");
								}
							}
							Integer infoKey = infoValue.getInfoKey();
							if(infoKey != null && infoKey == 1){
								form.setField("wo-" + tacheId + "-" + taskTacheInfoId + "-" + infoId + "-value", inputValue);
							}
						}
					}else {
						String[] checkedArray = woInfoValue.split(",");
						for(j = 0; j < infoValueList.size(); j++){
							InfoValue infoValue = infoValueList.get(j);
							Integer infoId = infoValue.getInfoId();
							boolean checkedFlag = false;
							for(m = 0; m < checkedArray.length; m++){
								String checkedValue = checkedArray[m];
								if((!("".equals(checkedValue))) && infoId.equals(Integer.valueOf(checkedValue))){
									checkedFlag = true;
								}
							}
							if(checkedFlag){
								form.setField("wo-" + tacheId + "-" + taskTacheInfoId + "-" + infoId, "true");
							}
						}
					}
				}
			}
			String requireCompleteTime = taskWoOrder.getRequireCompleteTimeStr();
			form.setField("wo-" + tacheId + "-require-time", requireCompleteTime);
			String replyUserName = taskWoOrder.getReplyUserName();
			form.setField("wo-" + tacheId + "-reply", replyUserName);
		}

		//判断是否需要立合
		Integer agreementNum = this.taskOrderService.getAgreementNum(taskOrderQuery);
		if(agreementNum.intValue() > 0){
			form.setField("other-1", "true");
		}else {
			form.setField("other-2", "true");
		}
		
		//确认信息
		List<TaskConfirmOrder> taskConfirmOrderList = taskOrder.getTaskConfirmOrderList();
		for(i = 0; i < taskConfirmOrderList.size(); i++){
			TaskConfirmOrder taskConfirmOrder = taskConfirmOrderList.get(i);
			String confirmOrderStateCode = taskConfirmOrder.getConfirmOrderStateCode();
			if("10C".equals(confirmOrderStateCode)){
				String runType = taskConfirmOrder.getRunType();
				if("confirm".equals(runType)){
					String confirmUserName = taskConfirmOrder.getConfirmUserName();
					String runCode = taskConfirmOrder.getRunCode();
					if("group_confirm".equals(runCode) && (confirmUserName == null || "".equals(confirmUserName))){
						form.setField("order-" + runCode, "/");
					}else {
						form.setField("order-" + runCode, confirmUserName);
					}
				}
			}else {
				String runType = taskConfirmOrder.getRunType();
				String runCode = taskConfirmOrder.getRunCode();
				if("confirm".equals(runType) && "accept_confirm".equals(runCode)){
					String confirmUserName = taskConfirmOrder.getConfirmUserName();
					form.setField("order-" + runCode, confirmUserName);
				}
			}
		}
		
		form.setField("order-page-no", "TT-QR-PZ-002-01B");
		
		stamp.setFormFlattening(true); 
        stamp.close();  
	    Document doc = new Document();  
	    PdfCopy pdfCopy = new PdfCopy(doc, fos);  
	    doc.open();  
	    PdfImportedPage impPage = null;  
        impPage = pdfCopy.getImportedPage(new PdfReader(baos.toByteArray()), 1);  
        pdfCopy.addPage(impPage);  
        doc.close();
    
		return pdfName;
	}
	
	private String getRandNum(){
		 Random random = new Random();
         String result = "";
         for(int i = 0; i < 6; i++){
             result+=random.nextInt(10);    
         }
         return result;
	}
	

	@Override
	//导出立合单
	public String exportPDF2(Integer orderId, Integer agreementId, String path) throws Exception {
		// TODO Auto-generated method stub
		String pdfName;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String randNum = this.getRandNum();
		pdfName = sdf.format(date)+"_"+randNum+".pdf";
		
		Agreement agreement = this.agreementService.queryAgreementInfo3(orderId, agreementId);

		float marginLeft = 10f;
		float marginRight = 10f;
		float marginTop = 10f;
		float marginBottom = 10f;
		
		//问题+结论的高度
        float totalHeigth = 540f;
        
        //A4 宽595
		Document document = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);  
	    PdfWriter.getInstance(document, new FileOutputStream(path + "stdout/" + pdfName));   
	    document.open();  
	    
	    //标题
	    Paragraph titleParagraph = this.getTitleParagraph();
        document.add(titleParagraph);  
	    
        //基础信息
        PdfPTable baseTable = this.getBaseTable(agreement);
        document.add(baseTable);
        
        //结论
        PdfPTable conclusionTable = this.getConclusionTable(agreement, path);
        float conclusionHeigth = conclusionTable.getRowHeight(0);
        
        //添加问题表头
        PdfPTable problemTitalTable = this.getProblemTitleTable();
        document.add(problemTitalTable);
        
        List<AgreementContent> agreementContentList = agreement.getAgreementContentList();
        PdfPTable problemTable = this.getProblemTable(agreementContentList, 0, null);
        float problemHeight = 0f;
        int index = 0;
        for(; index < agreementContentList.size(); index++){
        	float height = problemTable.getRowHeight(index);
        	if((problemHeight + height) < (totalHeigth - conclusionHeigth)){
        		problemHeight += height;
        	}else {
        		break;
        	}
        }
        if(index == agreementContentList.size()){
        	//添加问题
	        document.add(problemTable);
        }else {
        	//添加问题
        	problemTable = this.getProblemTable(agreementContentList, 0, index);
        	document.add(problemTable);
        }
        //添加空白格
        float blankHeight = totalHeigth - conclusionHeigth - problemHeight;
        if(blankHeight > 10f){
        	PdfPTable blankTable = this.getBlankTable(blankHeight);
        	document.add(blankTable);
        }
        
        //添加结论
        document.add(conclusionTable);
        
        //添加会签段落
        Paragraph signParagraph = this.getSignParagraph();
        document.add(signParagraph); 
        
        //添加会签表格
        List<AgreementTache> agreementTacheList = agreement.getAgreementTacheList();
        PdfPTable SignTable = this.getSignTable(agreementTacheList, orderId);
        document.add(SignTable); 
        
        //添加备注
        this.AddRemarks(document);
	    
        //是否需要添加第二页
        if(index != agreementContentList.size()){
        	document.newPage();
        	PdfPTable problemTable2 = this.getProblemTable(agreementContentList, index, null);
        	document.add(problemTable2);
        }
         
	    document.close();  
	    
		return pdfName;
	}
	
	public PdfPTable getBlankTable(float height) throws Exception{
		 PdfPTable table = new PdfPTable(1);
	     table.setTotalWidth(575);
	     table.setLockedWidth(true);
	     
//	     BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//	     Font font = new Font(bfChinese, 8, Font.BOLD);
//	        
//	     Paragraph Paragraph = new Paragraph("", font);
//	     Paragraph.setAlignment(Element.ALIGN_CENTER); 
	     PdfPCell cell = new PdfPCell();
	     cell.setUseAscender(true);
	     cell.setUseDescender(true);
	     cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	     cell.setMinimumHeight(height);
//	     cell.addElement(Paragraph);
	     table.addCell(cell);
	     
	     return table;
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getMessageTable(String message) throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 12, Font.BOLD);
		PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(400f);
        table.setLockedWidth(true);
        Paragraph paragraph1 = new Paragraph("结论：" + message, font);
        paragraph1.setAlignment(Element.ALIGN_LEFT); 
        PdfPCell cell1 = new PdfPCell();
        cell1.setUseAscender(true);
        cell1.setUseDescender(true);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setMinimumHeight(50);
        cell1.addElement(paragraph1);
        table.addCell(cell1);
        return table;
	}
	
	/**
	 * 获取立合单结论表格
	 * @param agreement
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getConclusionTable(Agreement agreement, String path) throws Exception{
		float[] columnWidth = {400f, 45f, 62f, 68f};
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);
        
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 12, Font.BOLD);
        
        String message = "";
        if(agreement.getConclusionMessage() != null){
        	message = agreement.getConclusionMessage();
        }
        Paragraph paragraph1 = new Paragraph("结论：" + message, font);
        paragraph1.setAlignment(Element.ALIGN_LEFT); 
        PdfPCell cell1 = new PdfPCell();
        cell1.setUseAscender(true);
        cell1.setUseDescender(true);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setMinimumHeight(50);
        cell1.addElement(paragraph1);
        
        PdfPTable messageTable = this.getMessageTable("结论：" + message);
        float height = messageTable.getRowHeight(0);
        //OK、NG
        PdfPTable itmeTable = new PdfPTable(1);
        itmeTable.setTotalWidth(45f);
        itmeTable.setLockedWidth(true);
        
        Image imgOK1 = Image.getInstance(path + "images/OK1.png");
        Image imgOK2 = Image.getInstance(path + "images/OK2.png");
        Paragraph itemParagraph1 = new Paragraph("OK", font);
        itemParagraph1.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell itemCell1 = new PdfPCell();
        itemCell1.setUseAscender(true);
        itemCell1.setUseDescender(true);
        itemCell1.setMinimumHeight(height/2);
        itemCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if("OK".equals(agreement.getConclusionState())){
        	itemCell1.addElement(imgOK2);
        }else {
        	itemCell1.addElement(imgOK1);
        }
        
        Image imgNG1 = Image.getInstance(path + "images/NG1.png");
        Image imgNG2 = Image.getInstance(path + "images/NG2.png");
        Paragraph itemParagraph2 = new Paragraph("NG", font);
        itemParagraph2.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell itemCell2 = new PdfPCell();
        itemCell2.setUseAscender(true);
        itemCell2.setUseDescender(true);
        itemCell2.setMinimumHeight(height/2);
        itemCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if("NG".equals(agreement.getConclusionState())){
        	itemCell2.addElement(imgNG2);
        }else {
        	itemCell2.addElement(imgNG1);
        }
        
        itmeTable.addCell(itemCell1);
        itmeTable.addCell(itemCell2);
        
        PdfPCell cell2 = new PdfPCell();
        cell2.setUseAscender(true);
        cell2.setUseDescender(true);
        cell2.setPadding(0f);
        cell2.setPaddingBottom(0f);
        cell2.addElement(itmeTable);
        
        //确认者
        Paragraph paragraph3 = new Paragraph(agreement.getCreateUser(), font);
        paragraph3.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell3 = new PdfPCell();
        cell3.setUseAscender(true);
        cell3.setUseDescender(true);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setMinimumHeight(50);
        cell3.addElement(paragraph3);
        
        String userName = "";
        if(agreement.getTrackName() != null){
        	userName = agreement.getTrackName();
        }
//        List<AgreementTache> agreementTacheList = agreement.getAgreementTacheList();
//        for(int i = 0; i < agreementTacheList.size(); i++){
//        	AgreementTache agreementTache = agreementTacheList.get(i);
//        	Integer tacheId = agreementTache.getTacheId();
//        	if(tacheId == 17){
//        		if("/".endsWith(agreementTache.getUserName()) || "noshow".equals(agreementTache.getUserName())){
//        			
//        		}else {
//        			userName = agreementTache.getUserName();
//        		}
//        	}
//        }
        //跟踪者
        Paragraph paragraph4 = new Paragraph(userName, font);
        paragraph4.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell4 = new PdfPCell();
        cell4.setUseAscender(true);
        cell4.setUseDescender(true);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setMinimumHeight(50);
        cell4.addElement(paragraph4);
         
        table.addCell(cell1); 
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        return table;
	}
	
	/**
	 * 获取问题表头
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getProblemTitleTable() throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);
        
		float[] columnWidth = {20f, 190f, 190f, 45f, 62f, 68f};
        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(10f);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);
        
        Paragraph Paragraph1 = new Paragraph("序号", font);
        Paragraph1.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell1 = new PdfPCell();
        cell1.setUseAscender(true);
        cell1.setUseDescender(true);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.addElement(Paragraph1);
        table.addCell(cell1);
        
        Paragraph Paragraph2 = new Paragraph("问题点", font);
        Paragraph2.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell2 = new PdfPCell();
        cell2.setUseAscender(true);
        cell2.setUseDescender(true);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.addElement(Paragraph2);
        table.addCell(cell2);
        
        Paragraph Paragraph3 = new Paragraph("改善对策", font);
        Paragraph3.setAlignment(Element.ALIGN_CENTER);  
        PdfPCell cell3 = new PdfPCell();
        cell3.setUseAscender(true);
        cell3.setUseDescender(true);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.addElement(Paragraph3);
        table.addCell(cell3);
        
        Paragraph Paragraph4 = new Paragraph("责任人", font);
        Paragraph4.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell4 = new PdfPCell();
        cell4.setUseAscender(true);
        cell4.setUseDescender(true);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.addElement(Paragraph4);
        table.addCell(cell4);
        
        Paragraph Paragraph5 = new Paragraph("改善期限", font);
        Paragraph5.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell5 = new PdfPCell();
        cell5.setUseAscender(true);
        cell5.setUseDescender(true);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell5.addElement(Paragraph5);
        table.addCell(cell5);
        
        Paragraph Paragraph6 = new Paragraph("状态/确认者", font);
        Paragraph6.setAlignment(Element.ALIGN_CENTER); 
        PdfPCell cell6 = new PdfPCell();
        cell6.setUseAscender(true);
        cell6.setUseDescender(true);
        cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell6.addElement(Paragraph6);
        table.addCell(cell6); 

        return table;
	}
	
	/**
	 * 获取问题表格
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getProblemTable(List<AgreementContent> agreementContentList, Integer begin, Integer end) throws Exception{
		PdfPTable table = new PdfPTable(6);
		float[] columnWidth2 = {20f, 190f, 190f, 45f, 62f, 68f};
        table.setTotalWidth(columnWidth2);
        table.setLockedWidth(true);
        
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 12, Font.BOLD);
        
        Integer size = agreementContentList.size();
        Integer length = null;
        if(end == null){
        	length = size;
        }else {
        	length = end;
        }
        
        for(int i = begin; i < length; i++){
        	AgreementContent agreementContent = agreementContentList.get(i);
        	
        	Paragraph Paragraph1 = new Paragraph("" + agreementContent.getSeq(), font);
            Paragraph1.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell1 = new PdfPCell();
            cell1.setUseAscender(true);
            cell1.setUseDescender(true);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.addElement(Paragraph1);
            
            Paragraph Paragraph2 = new Paragraph(agreementContent.getProblem(), font);
            Paragraph2.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell2 = new PdfPCell();
            cell2.setUseAscender(true);
            cell2.setUseDescender(true);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.addElement(Paragraph2);
            
            Paragraph Paragraph3 = new Paragraph(agreementContent.getImprove(), font);
            Paragraph3.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell3 = new PdfPCell();
            cell3.setUseAscender(true);
            cell3.setUseDescender(true);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.addElement(Paragraph3);
            
            Paragraph Paragraph4 = new Paragraph(agreementContent.getResponsibleName(), font);
            Paragraph4.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell4 = new PdfPCell();
            cell4.setUseAscender(true);
            cell4.setUseDescender(true);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell4.addElement(Paragraph4);
            
            Paragraph Paragraph5 = new Paragraph(agreementContent.getTermStr(), font);
            Paragraph5.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell5 = new PdfPCell();
            cell5.setUseAscender(true);
            cell5.setUseDescender(true);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.addElement(Paragraph5);
            
            Paragraph Paragraph6 = new Paragraph(agreementContent.getState() + "/" + agreementContent.getConfirmName(), font);
            Paragraph6.setAlignment(Element.ALIGN_CENTER); 
            PdfPCell cell6 = new PdfPCell();
            cell6.setUseAscender(true);
            cell6.setUseDescender(true);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.addElement(Paragraph6);
            
            table.addCell(cell1); 
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4); 
            table.addCell(cell5);
            table.addCell(cell6);
    	}
        
		return table;
	}
	
	/**
	 * 获取会签段落
	 */
	public Paragraph getSignParagraph() throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese, 7, Font.BOLD);
	    Paragraph paragraph = new Paragraph("立合人员签名:", font);
	    paragraph.setAlignment(Element.ALIGN_LEFT); 
	    return paragraph;
	}
	
	/**
	 * 获取会签表格
	 * @param agreementTacheList
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getSignTable(List<AgreementTache> agreementTacheList, Integer orderId) throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese, 12, Font.BOLD);

		TaskTacheQuery taskTacheQuery = new TaskTacheQuery();
		taskTacheQuery.setOrderId(orderId);
		List<TaskTache> taskTacheList = this.taskTacheService.queryTaskTacheListOfOrder(taskTacheQuery);
		int size = taskTacheList.size();

		float[] columnWidth = new float[size];
		int i;
		for(i = 0; i < size; i++){
			columnWidth[i] = 575f / size;
		}
        PdfPTable table = new PdfPTable(size);
        table.setSpacingBefore(3f);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);

        for(TaskTache taskTache : taskTacheList){
        	Paragraph Paragraph = new Paragraph(taskTache.getTacheName(), font);
        	Paragraph.setAlignment(Element.ALIGN_CENTER);  
        	Paragraph.setSpacingBefore(15); 
        	PdfPCell cell = new PdfPCell();
        	cell.setUseAscender(true);
        	cell.setUseDescender(true);
        	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	cell.setPaddingBottom(10);
        	cell.setPaddingTop(10);
        	cell.addElement(Paragraph);
            table.addCell(cell); 
        }

        for(i = 0; i < agreementTacheList.size(); i++){
        	AgreementTache AgreementTache = agreementTacheList.get(i);
        	Paragraph Paragraph = new Paragraph(AgreementTache.getUserName(), font);
        	Paragraph.setAlignment(Element.ALIGN_CENTER);  
        	Paragraph.setSpacingBefore(15); 
        	PdfPCell cell = new PdfPCell();
        	cell.setUseAscender(true);
        	cell.setUseDescender(true);
        	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        	cell.setPaddingBottom(10);
        	cell.setPaddingTop(10);
        	cell.addElement(Paragraph);
            table.addCell(cell); 
        }

        return table;
	}
	
	/**
	 * 获取基础信息字符串
	 * @param agreement
	 * @return
	 * @throws Exception
	 */
	public String getBaseString(Agreement agreement) throws Exception{
		String str = "工程：";
		str += agreement.getProject();
		str += "      ";
		str += "切换LOT：";
		String cutLOT = agreement.getCutLOT();
		if(cutLOT.length() > 18){
			cutLOT = cutLOT.substring(0, 17);
		}else {
			for(int i = cutLOT.length(); i <= 18; i++){
				cutLOT += "    ";
			}
		}
		str += cutLOT;
		str += "   ";
		str += "数量：";
		String num = "";
		if(agreement.getNum() != null){
			num = agreement.getNum() + "";
		}
		str += num + "      ";
		str += "日期：";
		str += "  ";
		String createTime = agreement.getCreateTime();
		str += createTime.substring(0, 4) + "年   ";
		str += createTime.substring(5, 7) + "月   ";
		str += createTime.substring(8, 10) + "日";
		return str;
	}
	
	/**
	 * 获取基础信息表格
	 * @param agreement
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getBaseTable(Agreement agreement) throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		PdfPTable table = new PdfPTable(1);
        table.setSpacingBefore(20f);
        table.setTotalWidth(575);
        table.setLockedWidth(true);
        Font font = new Font(bfChinese, 12, Font.BOLD);
        String baseStr = this.getBaseString(agreement);
        Paragraph paragraph = new Paragraph(baseStr, font);
        paragraph.setAlignment(Element.ALIGN_LEFT); 
        PdfPCell cell = new PdfPCell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.addElement(paragraph);
        table.addCell(cell);  
        return table;
	}
	
	/**
	 * 添加备注
	 * @param document
	 * @throws Exception
	 */
	public void AddRemarks(Document document) throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font titleChinese3 = new Font(bfChinese, 10, Font.BOLD);
	    Paragraph Paragraph3 = new Paragraph("说明：", titleChinese3);
	    Paragraph3.setAlignment(Element.ALIGN_LEFT); // 居左设置  
        document.add(Paragraph3);
        
        Font titleChinese4 = new Font(bfChinese, 10, Font.BOLD);
	    Paragraph Paragraph4 = new Paragraph("1、变更内容需要现场立合时，使用此表格，并附于<<量产后变更确认表后>>", titleChinese4);
	    Paragraph4.setAlignment(Element.ALIGN_LEFT); // 居左设置  
	    Paragraph4.setIndentationLeft(20); 
        document.add(Paragraph4);
        
        Font titleChinese5 = new Font(bfChinese, 10, Font.BOLD);
	    Paragraph Paragraph5 = new Paragraph("2、变化点立合问题点需品证或客户品证判断OK后，批准关闭", titleChinese5);
	    Paragraph5.setAlignment(Element.ALIGN_LEFT); // 居左设置  
	    Paragraph5.setIndentationLeft(20); 
        document.add(Paragraph5);
        
        Font titleChinese6 = new Font(bfChinese, 10, Font.BOLD);
	    Paragraph Paragraph6 = new Paragraph("TT-QR-PZ-002-02A", titleChinese6);
	    Paragraph6.setSpacingBefore(15);  
	    Paragraph6.setAlignment(Element.ALIGN_LEFT); // 居左设置  
	    Paragraph6.setIndentationLeft(0);
        document.add(Paragraph6);
	}
	
	/**
	 * 获取标题
	 * @return
	 * @throws Exception
	 */
	public Paragraph getTitleParagraph() throws Exception{
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese, 20, Font.BOLD);
	    Paragraph paragraph = new Paragraph("变化点立合问题确认表", font);
	    paragraph.setAlignment(Element.ALIGN_CENTER); 
	    return paragraph;
	}

	@Override
	public String exportAllPDF(int orderId, String path) throws Exception {
		// TODO Auto-generated method stub
		String pdfName = "";
		String pdfName1 = this.exportPDF(orderId, path);
		
		Integer agreementId = this.agreementService.queryAgreementIdByOrderId(orderId);
		if(agreementId == null){
			pdfName = pdfName1;
		}else {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String randNum = this.getRandNum();
			pdfName = sdf.format(date)+"_"+randNum+".pdf";
			
			String pdfName2 = this.exportPDF2(orderId, agreementId, path);
		    PdfReader reader1 = new PdfReader(path+"stdout/"+pdfName1);  
		    PdfReader reader2 = new PdfReader(path+"stdout/"+pdfName2);  
		      
		    FileOutputStream out = new FileOutputStream(path+"stdout/"+pdfName);  
		      
		    Document document = new Document();  
		    PdfWriter writer = PdfWriter.getInstance(document, out);  
		      
		    document.open();  
		    PdfContentByte cb = writer.getDirectContent(); 
		      
		    java.util.List<PdfReader> readers = new ArrayList<PdfReader>();  
		    readers.add(reader1);  
		    readers.add(reader2);  
		      
		    int pageOfCurrentReaderPDF = 0;  
		    Iterator<PdfReader> iteratorPDFReader = readers.iterator();  
		      
		    // Loop through the PDF files and add to the output.  
		    while (iteratorPDFReader.hasNext()) {  
		        PdfReader pdfReader = iteratorPDFReader.next();  
		      
		        // Create a new page in the target for each source page.  
		        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {  
		            document.newPage();  
		            pageOfCurrentReaderPDF++;  
		            PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);  
		            cb.addTemplate(page, 0, 0);  
		        }  
		        pageOfCurrentReaderPDF = 0;  
		    }  
		    out.flush();  
		    document.close();  
		    out.close();  
		}
		return pdfName;
	}
	
}
