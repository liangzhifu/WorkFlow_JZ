package com.dpcoi.order.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class DpcoiOrderQuery {

    private String vehicleName;

    private String productNo;

    private String hopeCuttingDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateEnd;

    private Integer pfmeaDelay;

    private Integer cpDelay;

    private Integer standardBookDealy;

    private String taskOrderNo;

    private Integer start;

    private Integer size;

    private String issuedNo;

    private String designChangeNo;

    private Integer dpcoiOrderId;

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

    public String getHopeCuttingDate() {
        return hopeCuttingDate;
    }

    public void setHopeCuttingDate(String hopeCuttingDate) {
        this.hopeCuttingDate = hopeCuttingDate;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPfmeaDelay() {
        return pfmeaDelay;
    }

    public void setPfmeaDelay(Integer pfmeaDelay) {
        this.pfmeaDelay = pfmeaDelay;
    }

    public Integer getCpDelay() {
        return cpDelay;
    }

    public void setCpDelay(Integer cpDelay) {
        this.cpDelay = cpDelay;
    }

    public Integer getStandardBookDealy() {
        return standardBookDealy;
    }

    public void setStandardBookDealy(Integer standardBookDealy) {
        this.standardBookDealy = standardBookDealy;
    }

    public String getTaskOrderNo() {
        return taskOrderNo;
    }

    public void setTaskOrderNo(String taskOrderNo) {
        this.taskOrderNo = taskOrderNo;
    }

    public String getIssuedNo() {
        return issuedNo;
    }

    public void setIssuedNo(String issuedNo) {
        this.issuedNo = issuedNo;
    }

    public String getDesignChangeNo() {
        return designChangeNo;
    }

    public void setDesignChangeNo(String designChangeNo) {
        this.designChangeNo = designChangeNo;
    }

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
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

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }
}
