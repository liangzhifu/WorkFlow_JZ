package com.dpcoi.woOrder.query;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-05-05
 **/

public class DpcoiWoOrderFileQuery {

    private Integer dpcoiOrderId;

    private Integer dpcoiWoOrderId;

    private Integer start;

    private Integer size;

    private String vehicleName;

    private String productNo;

    private String hopeCuttingDate;

    private String productLine;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDateEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDateEnd;

    private String fileName;

    private String taskOrderNo;

    private Integer dpcoiWoOrderType;

    private String designChangeNo;

    public Integer getDpcoiWoOrderId() {
        return dpcoiWoOrderId;
    }

    public void setDpcoiWoOrderId(Integer dpcoiWoOrderId) {
        this.dpcoiWoOrderId = dpcoiWoOrderId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
    }

    public String getDesignChangeNo() {
        return designChangeNo;
    }

    public void setDesignChangeNo(String designChangeNo) {
        this.designChangeNo = designChangeNo;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }
}
