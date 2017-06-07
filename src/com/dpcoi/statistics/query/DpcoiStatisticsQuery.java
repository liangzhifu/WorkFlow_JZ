package com.dpcoi.statistics.query;/**
 * Created by liangzhifu
 * DATE:2017/5/18.
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-05-18
 **/

public class DpcoiStatisticsQuery {

    private String vehicleName;

    private String productNo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateEnd;

    private String taskOrderNo;

    private Integer dpcoiWoOrderType;

    private String productLine;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Date getRealCuttingDateStart() {
        return realCuttingDateStart;
    }

    public void setRealCuttingDateStart(Date realCuttingDateStart) {
        this.realCuttingDateStart = realCuttingDateStart;
    }

    public Date getRealCuttingDateEnd() {
        return realCuttingDateEnd;
    }

    public void setRealCuttingDateEnd(Date realCuttingDateEnd) {
        this.realCuttingDateEnd = realCuttingDateEnd;
    }

    public Date getReleaseDateStart() {
        return releaseDateStart;
    }

    public void setReleaseDateStart(Date releaseDateStart) {
        this.releaseDateStart = releaseDateStart;
    }

    public Date getReleaseDateEnd() {
        return releaseDateEnd;
    }

    public void setReleaseDateEnd(Date releaseDateEnd) {
        this.releaseDateEnd = releaseDateEnd;
    }

    public String getTaskOrderNo() {
        return taskOrderNo;
    }

    public void setTaskOrderNo(String taskOrderNo) {
        this.taskOrderNo = taskOrderNo;
    }

    public Integer getDpcoiWoOrderType() {
        return dpcoiWoOrderType;
    }

    public void setDpcoiWoOrderType(Integer dpcoiWoOrderType) {
        this.dpcoiWoOrderType = dpcoiWoOrderType;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }
}
