package com.dpcoi.quartz.service.serviceImpl;

import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.service.RRProblemService;
import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.service.RRDelayStatisticsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 * @create 2017-11-02
 **/
@Component("updateRRproblemTrackingLevel")
public class UpdateRRproblemTrackingLevel {

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Resource(name = "rRDelayStatisticsService")
    private RRDelayStatisticsService rRDelayStatisticsService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void job() {
        try {
            List<RRProblem> rrProblemList = this.rRProblemService.queryJobRRProblemTrackingLevelList();
            for (RRProblem rrProblem : rrProblemList){
                String oldTrackingLevel = rrProblem.getTrackingLevel();
                this.rRProblemService.updateTrackingLevel(rrProblem);
                this.rRProblemService.updateRRProblem(rrProblem);
                String speedOfProgress = rrProblem.getSpeedOfProgress();
                String trackingLevel =  rrProblem.getTrackingLevel();
                Integer delayApplication = rrProblem.getDelayApplication();
                if("I".equals(trackingLevel) || "II".equals(trackingLevel) || "III".equals(trackingLevel) || "IV".equals(trackingLevel)) {
                    if (oldTrackingLevel == null || "".equals(oldTrackingLevel) || "V".equals(oldTrackingLevel)) {
                        String persionLiable = rrProblem.getPersionLiable();
                        String[] persionLiableArray = persionLiable.split(",");
                        for (int i = 0; i < persionLiableArray.length; i++) {
                            RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                            rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                            rrDelayStatistics.setDelayDate(new Date());
                            rrDelayStatistics.setDelayType(1);
                            rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                            rrDelayStatistics.setRrProblemId(rrProblem.getId());
                            rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                            rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                            rrDelayStatistics.setTrackingLevel(trackingLevel);
                            this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                        }
                    } else if ("IV".equals(oldTrackingLevel) && "III".equals(trackingLevel) && delayApplication.intValue() == 0) {
                        String persionLiable = rrProblem.getPersionLiable();
                        String[] persionLiableArray = persionLiable.split(",");
                        for (int i = 0; i < persionLiableArray.length; i++) {
                            RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                            rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                            rrDelayStatistics.setDelayDate(new Date());
                            rrDelayStatistics.setDelayType(1);
                            rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                            rrDelayStatistics.setRrProblemId(rrProblem.getId());
                            rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                            rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                            rrDelayStatistics.setTrackingLevel(trackingLevel);
                            this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                        }
                    } else if ("III".equals(oldTrackingLevel) && "II".equals(trackingLevel) && delayApplication.intValue() == 1) {
                        String persionLiable = rrProblem.getPersionLiable();
                        String[] persionLiableArray = persionLiable.split(",");
                        for (int i = 0; i < persionLiableArray.length; i++) {
                            RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                            rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                            rrDelayStatistics.setDelayDate(new Date());
                            rrDelayStatistics.setDelayType(1);
                            rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                            rrDelayStatistics.setRrProblemId(rrProblem.getId());
                            rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                            rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                            rrDelayStatistics.setTrackingLevel(trackingLevel);
                            this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
