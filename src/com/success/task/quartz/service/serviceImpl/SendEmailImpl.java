package com.success.task.quartz.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.email.query.TimeTaskQuery;
import com.success.sys.sendMail.dao.MailSenderFactory;
import com.success.sys.sendMail.dao.SendMailDao;

@Component("sendEmailImpl")
public class SendEmailImpl {

	@Resource(name = "timeTaskDao")
	private TimeTaskDao timeTaskDao;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void job(){
		TimeTaskQuery timeTaskQuery = new TimeTaskQuery();
		timeTaskQuery.setDeleteState(0);
//		String[][] mailSender = {
//			{"takata@ttdmg.com","Abcd12345"},
//			{"support1@ttdmg.com","Abcd12345"},
//			{"support2@ttdmg.com","Abcd12345"},
//			{"support3@ttdmg.com","Abcd12345"},
//			{"support4@ttdmg.com","Abcd12345"},
//			{"support5@ttdmg.com","Abcd12345"},
//			{"support6@ttdmg.com","Abcd12345"},
//			{"support7@ttdmg.com","Abcd12345"},
//			{"support8@ttdmg.com","Abcd12345"},
//			{"support9@ttdmg.com","Abcd12345"},
//			{"support10@ttdmg.com","Abcd12345"}
//		};
		java.util.Random random=new java.util.Random();// 定义随机类
		int index = random.nextInt(11);// 返回[0,10)集合中的整数，注意不包括10
		
		SendMailDao sendMailDao = null;
		try{
			List<TimeTask> timeTaskList = this.timeTaskDao.selectTimeTask(timeTaskQuery);
			if(timeTaskList == null){
				
			}else {
				for(int i = 0; i < timeTaskList.size(); i++){
					index = index % 11;
					sendMailDao = null;
					sendMailDao = MailSenderFactory.getSender("404_4M@cn.takata.com", "", "10.235.12.102", "25");
					TimeTask timeTask = timeTaskList.get(i);
					int failedNum = timeTask.getFailedNum();
					String toMailAddress = timeTask.getUserEmail();//"1009568392@qq.com";
					if(toMailAddress == null || "".equals(toMailAddress)){
						timeTask.setDeleteState(1);
						this.timeTaskDao.deleteTimeTask(timeTask);
						continue;
					}
					while(toMailAddress.indexOf(",,") >= 0){
						toMailAddress = toMailAddress.replaceAll(",,", ",");
					}
					try{
						//主题
						String subject = "";
						Integer noticeType = timeTask.getNoticeType();
						if(noticeType == 1){
							subject = "变更提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 2){
							subject = "超时提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 3){
							subject = "拒绝提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 4){
							subject = "变更实施提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 5){
							subject = "超时提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 6){
							subject = "变更时间修改提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 7){
							subject = "变更确认提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 8){
							subject = "审核提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 9){
							subject = "作成提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 10){
							subject = "作废提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 11){
							subject = "变更确认人提醒：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 12){
							subject = "新建立合：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 13){
							subject = "立合确认：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 14){
							subject = "立合结论：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 15){
							subject = "立合修改：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 16){
							subject = "立合作废：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 17){
							subject = "立合拒绝：发行编号--"+timeTask.getPublishCode();
						}else if(noticeType == 18){
							subject = "PFMEA变更确认";
						}else if(noticeType == 19){
							subject = "PFMEA变更上传文件";
						}else if(noticeType == 20){
							subject = "PFMEA变更审核";
						}else if(noticeType == 21){
							subject = "PFMEA变更审核拒绝，重新上传文件";
						}else if(noticeType == 35){
							subject = "CP变更确认";
						}else if(noticeType == 22){
							subject = "CP变更上传文件";
						}else if(noticeType == 23){
							subject = "CP变更审核";
						}else if(noticeType == 24){
							subject = "CP变更审核拒绝，重新上传文件";
						}else if(noticeType == 25){
							subject = "作业标准书变更确认";
						}else if(noticeType == 26){
							subject = "作业标准书变更上传文件";
						}else if(noticeType == 27){
							subject = "作业标准书变更审核";
						}else if(noticeType == 28){
							subject = "作业标准书变更审核拒绝，重新上传文件";
						}else if(noticeType == 29){
							subject = "PFMEA变更确认超时";
						}else if(noticeType == 30){
							subject = "CP变更确认超时";
						}else if(noticeType == 31){
							subject = "作业标准书变更确认超时";
						}else if(noticeType == 32){
							subject = "PFMEA变更超时";
						}else if(noticeType == 33){
							subject = "CP变更超时";
						}else if(noticeType == 34){
							subject = "作业标准书变更超时";
						}else if(noticeType == 36){
							subject = "DPCOI作废-设变废除";
						}else if(noticeType == 37){
							subject = "DPCOI作废-4M变更废除";
						}else {
							
						}
						//内容
						String content = timeTask.getComment() + "<br>系统URL：http://s401app09.cn.takatacorp.com";
						sendMailDao.send("404_4M@cn.takata.com", toMailAddress, subject, content);
						timeTask.setDeleteState(1);
						this.timeTaskDao.deleteTimeTask(timeTask);
					}catch(Exception e1){
						e1.printStackTrace();
						if(failedNum > 2){
							timeTask.setDeleteState(2);
						}else {
							timeTask.setFailedNum(failedNum+1);
						}
						this.timeTaskDao.updateTimeTask(timeTask);
					}	
					index ++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
