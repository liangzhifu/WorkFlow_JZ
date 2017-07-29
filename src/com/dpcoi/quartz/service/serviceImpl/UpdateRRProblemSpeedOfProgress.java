package com.dpcoi.quartz.service.serviceImpl;/**
 * Created by liangzhifu
 * DATE:2017/7/5.
 */

import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.service.RRProblemService;
import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.service.RRDelayStatisticsService;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lzf
 * @create 2017-07-05
 **/

@Component("updateRRProblemSpeedOfProgress")
public class UpdateRRProblemSpeedOfProgress {

    @Resource(name="timeTaskDao")
    private TimeTaskDao timeTaskDao;

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Resource(name = "rRDelayStatisticsService")
    private RRDelayStatisticsService rRDelayStatisticsService;

    @Scheduled(cron = "0 30 2 * * ?")
    public void job() {
        try {
            List<RRProblem> rrProblemList = this.rRProblemService.queryJobRRProblemList();
            for (RRProblem rrProblem : rrProblemList){
                String oldSpeedOfProgress = rrProblem.getSpeedOfProgress();
                this.rRProblemService.updateSpeedOfProgress(rrProblem);
                String speedOfProgress = rrProblem.getSpeedOfProgress();
                if("delayI".equals(speedOfProgress) || "delayII".equals(speedOfProgress) || "delayIII".equals(speedOfProgress) || "delayIV".equals(speedOfProgress)){
                    if("follow".equals(oldSpeedOfProgress)){
                        String persionLiable = rrProblem.getPersionLiable();
                        String[] persionLiableArray = persionLiable.split(",");
                        for(int i = 0; i < persionLiableArray.length; i++){
                            RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                            rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                            rrDelayStatistics.setDelayDate(new Date());
                            rrDelayStatistics.setDelayType(1);
                            rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                            rrDelayStatistics.setRrProblemId(rrProblem.getId());
                            rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                            rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                            this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                        }
                    }
                    TimeTask timeTask = new TimeTask();
                    timeTask.setNoticeType(38);
                    StringBuffer comment = new StringBuffer();
                    comment.append("状态:").append(rrProblem.getProblemStatus())
                            .append("<br>").append("问题编号:").append(rrProblem.getProblemNo())
                            .append("<br>").append("问题类型:").append(rrProblem.getProblemType())
                            .append("<br>").append("工程:").append(rrProblem.getEngineering())
                            .append("<br>").append("客户:").append(rrProblem.getCustomer())
                            .append("<br>").append("车型:").append(rrProblem.getVehicle())
                            .append("<br>").append("品名:").append(rrProblem.getProductNo())
                            .append("<br>").append("不良内容:").append(rrProblem.getBadContent())
                            .append("<br>").append("生产线:").append(rrProblem.getProductLine())
                            .append("<br>").append("严重度:").append(rrProblem.getSeverity())
                            .append("<br>").append("根本原因:").append(rrProblem.getRootCause())
                            .append("<br>").append("永久对策:").append(rrProblem.getPermanentGame())
                            .append("<br>").append("进度").append(speedOfProgress);
                    timeTask.setComment(comment.toString());
                    String emailUser = this.rRProblemService.queryDelayEmails(rrProblem);
                    timeTask.setUserEmail(emailUser);
                    timeTask.setDeleteState(0);
                    timeTask.setEmailTitle(rrProblem.getProblemNo());
                    this.timeTaskDao.insertTimeTask(timeTask);
                }
                this.rRProblemService.updateRRProblem(rrProblem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
