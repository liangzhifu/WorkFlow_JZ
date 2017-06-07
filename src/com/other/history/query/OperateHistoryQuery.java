package com.other.history.query;/**
 * Created by liangzhifu
 * DATE:2017/5/12.
 */

/**
 *
 * @author lzf
 * @create 2017-05-12
 **/

public class OperateHistoryQuery {

    private Integer SystermType;

    private Integer businessType;

    private Integer businessId;

    public Integer getSystermType() {
        return SystermType;
    }

    public void setSystermType(Integer systermType) {
        SystermType = systermType;
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
}
