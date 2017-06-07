package com.dpcoi.sys.domain;

import java.util.Date;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class DpcoiUserRole {

    private Integer id;

    //dpcoi用户ID
    private Integer dpcoiUserId;

    //dpcoi权限ID
    private Integer dpcoiRoleId;

    //删除标志0--正常，1--删除
    private String delFlag;

    //创建时间
    private Date createDate;

    //创建人
    private Integer createBy;

    //更新时间
    private Date updateDate;

    //更新人
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDpcoiUserId() {
        return dpcoiUserId;
    }

    public void setDpcoiUserId(Integer dpcoiUserId) {
        this.dpcoiUserId = dpcoiUserId;
    }

    public Integer getDpcoiRoleId() {
        return dpcoiRoleId;
    }

    public void setDpcoiRoleId(Integer dpcoiRoleId) {
        this.dpcoiRoleId = dpcoiRoleId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
}
