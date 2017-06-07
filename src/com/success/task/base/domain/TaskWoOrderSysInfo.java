package com.success.task.base.domain;/**
 * Created by liangzhifu
 * DATE:2017/5/8.
 */

/**
 *
 * @author lzf
 * @create 2017-05-08
 **/

public class TaskWoOrderSysInfo {

    private Integer Id;

    private Integer woOrderId;

    private Integer sysInfoId;

    private Integer woInfoValue;

    public Integer getWoInfoValue() {
        return woInfoValue;
    }

    public void setWoInfoValue(Integer woInfoValue) {
        this.woInfoValue = woInfoValue;
    }

    public Integer getSysInfoId() {
        return sysInfoId;
    }

    public void setSysInfoId(Integer sysInfoId) {
        this.sysInfoId = sysInfoId;
    }

    public Integer getWoOrderId() {
        return woOrderId;
    }

    public void setWoOrderId(Integer woOrderId) {
        this.woOrderId = woOrderId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
