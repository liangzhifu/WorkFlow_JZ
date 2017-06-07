package com.dpcoi.woOrder.domain;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-05-05
 **/

public class DpcoiWoOrderFile {

    private Integer id;

    private Integer dpcoiWoOrderId;

    private Integer fileId;

    private Date createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private String delFlag;

    private Integer woFileState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDpcoiWoOrderId() {
        return dpcoiWoOrderId;
    }

    public void setDpcoiWoOrderId(Integer dpcoiWoOrderId) {
        this.dpcoiWoOrderId = dpcoiWoOrderId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getWoFileState() {
        return woFileState;
    }

    public void setWoFileState(Integer woFileState) {
        this.woFileState = woFileState;
    }
}
