package com.dpcoi.statistics.domain;/**
 * Created by liangzhifu
 * DATE:2017/7/20.
 */

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-07-20
 **/

public class RRDelayStatistics {

    private Integer id;

    private Integer rrProblemId;

    private String persionLiable;

    private Date delayDate;

    private String problemProgress;

    private Integer delayType;

    private String speedOfProgress;

    private String problemStatus;

    private String trackingLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRrProblemId() {
        return rrProblemId;
    }

    public void setRrProblemId(Integer rrProblemId) {
        this.rrProblemId = rrProblemId;
    }

    public String getPersionLiable() {
        return persionLiable;
    }

    public void setPersionLiable(String persionLiable) {
        this.persionLiable = persionLiable == null ? null : persionLiable.trim();
    }

    public Date getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(Date delayDate) {
        this.delayDate = delayDate;
    }

    public String getProblemProgress() {
        return problemProgress;
    }

    public void setProblemProgress(String problemProgress) {
        this.problemProgress = problemProgress == null ? null : problemProgress.trim();
    }

    public Integer getDelayType() {
        return delayType;
    }

    public void setDelayType(Integer delayType) {
        this.delayType = delayType;
    }

    public String getSpeedOfProgress() {
        return speedOfProgress;
    }

    public void setSpeedOfProgress(String speedOfProgress) {
        this.speedOfProgress = speedOfProgress == null ? null : speedOfProgress.trim();
    }

    public String getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getTrackingLevel() {
        return trackingLevel;
    }

    public void setTrackingLevel(String trackingLevel) {
        this.trackingLevel = trackingLevel;
    }
}
