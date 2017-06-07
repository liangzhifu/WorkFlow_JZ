package com.dpcoi.woOrder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class DpcoiWoOrder {

    private Integer dpcoiWoOrderId;

    //定单ID
    private Integer dpcoiOrderId;

    //删除标志0--正常，1--删除
    private String delFlag;

    //定单类型1--PFMEA、2--CP、3--作业标准书
    private Integer dpcoiWoOrderType;

    //定单状态1--待确认、2--待上传文件、3--待领导确认、4--完成、5--拒绝、6--作废、7--不需要变更
    private Integer dpcoiWoOrderState;

    //创建时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    //确认时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date confirmDate;

    //上传文件完成时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fileCompleteDate;

    //领导确认时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date managerConfirmDate;

    //确认人
    private Integer confirmBy;

    //上传完成确认人
    private Integer fileCompleteBy;

    //领导确认人
    private Integer managerConfirmBy;

    private Date delayDate;

    public Integer getDpcoiWoOrderId() {
        return dpcoiWoOrderId;
    }

    public void setDpcoiWoOrderId(Integer dpcoiWoOrderId) {
        this.dpcoiWoOrderId = dpcoiWoOrderId;
    }

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getFileCompleteDate() {
        return fileCompleteDate;
    }

    public void setFileCompleteDate(Date fileCompleteDate) {
        this.fileCompleteDate = fileCompleteDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getManagerConfirmDate() {
        return managerConfirmDate;
    }

    public void setManagerConfirmDate(Date managerConfirmDate) {
        this.managerConfirmDate = managerConfirmDate;
    }

    public Integer getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(Integer confirmBy) {
        this.confirmBy = confirmBy;
    }

    public Integer getFileCompleteBy() {
        return fileCompleteBy;
    }

    public void setFileCompleteBy(Integer fileCompleteBy) {
        this.fileCompleteBy = fileCompleteBy;
    }

    public Integer getManagerConfirmBy() {
        return managerConfirmBy;
    }

    public void setManagerConfirmBy(Integer managerConfirmBy) {
        this.managerConfirmBy = managerConfirmBy;
    }

    public Date getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(Date delayDate) {
        this.delayDate = delayDate;
    }
}
