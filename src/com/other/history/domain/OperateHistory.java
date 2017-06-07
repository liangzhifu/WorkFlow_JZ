package com.other.history.domain;/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-05-12
 **/

public class OperateHistory {

    private Integer id;

    private Integer SystermType;

    private Integer operateType;

    private Integer operateBy;

    private Date operateDate;

    private Integer businessType;

    private Integer businessId;

    public Integer getSystermType() {
        return SystermType;
    }

    public void setSystermType(Integer systermType) {
        SystermType = systermType;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(Integer operateBy) {
        this.operateBy = operateBy;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
