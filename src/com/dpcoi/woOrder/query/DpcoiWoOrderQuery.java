package com.dpcoi.woOrder.query;/**
 * Created by liangzhifu
 * DATE:2017/5/4.
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-05-04
 **/

public class DpcoiWoOrderQuery {

    private Integer dpcoiOrderId;

    private Integer start;

    private Integer size;

    private Integer dpcoiUserId;

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

    private Integer dpcoiWoOrderState;

    private Integer dpcoiWoOrderType;

    public Integer getDpcoiUserId() {
        return dpcoiUserId;
    }

    public void setDpcoiUserId(Integer dpcoiUserId) {
        this.dpcoiUserId = dpcoiUserId;
    }

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
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

    public Integer getDpcoiWoOrderType() {
        return dpcoiWoOrderType;
    }

    public void setDpcoiWoOrderType(Integer dpcoiWoOrderType) {
        this.dpcoiWoOrderType = dpcoiWoOrderType;
    }

    public Integer getDpcoiWoOrderState() {
        return dpcoiWoOrderState;
    }

    public void setDpcoiWoOrderState(Integer dpcoiWoOrderState) {
        this.dpcoiWoOrderState = dpcoiWoOrderState;
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
