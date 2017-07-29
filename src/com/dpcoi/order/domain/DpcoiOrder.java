package com.dpcoi.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class DpcoiOrder {

    private Integer dpcoiOrderId;

    //定单状态1--处理中、2--完成、3--作废
    private Integer dpcoiOrderState;

    //删除标志0--正常，1--删除
    private String delFlag;

    //创建时间
    private Date createDate;

    private String createDateStr;

    //创建人
    private Integer createBy;

    private String createByName;

    //更新时间
    private Date updateDate;

    //更新人
    private Integer updateBy;

    //定单类型1--项目管理,2--4M
    private Integer dpcoiOrderType;

    //4M定单ID
    private Integer taskOrderId;

    //4M定单编码
    private String taskOrderNo;

    //《设变通知书》编号
    private String issuedNo;

    //设变号
    private String designChangeNo;

    //车种
    private String vehicleName;

    //品号
    private String productNo;

    //希望切替日
    private String hopeCuttingDate;

    //实际切替日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date realCuttingDate;

    private String realCuttingDateStr;

    //变更内容
    private String changeContent;

    //发行日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    private String releaseDateStr;

    //《设变切替手配书》返回日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date returnDate;

    private String returnDateStr;

    //备注
    private String remark;

    //设计担当
    private String designAct;

    //量准担当
    private Integer quasiAct;

    //量准担当人
    private String quasiActName;

    //PFMEA创建时间
    private Date pfmeaCreateDate;

    //PFMEA邮件通知时间
    private Date pfmeaEmailDate;

    //PFMEA确认变更时间
    private Date pfmeaConfirmDate;

    //PFMEA变更完成并审核通过时间
    private Date pfmeaCompleteDate;

    //PFMEA是否超时变更
    private Integer pfmeaDelay;

    //CP创建时间
    private Date cpCreateDate;

    //CP邮件通知时间
    private Date cpEmailDate;

    //CP确认变更时间
    private Date cpConfirmDate;

    //CP变更完成并审核通过时间
    private Date cpCompleteDate;

    //CP是否超时变更
    private Integer cpDelay;

    //StandardBook创建时间
    private Date standardBookCreateDate;

    //StandardBook邮件通知时间
    private Date standardBookEmailDate;

    //StandardBook确认变更时间
    private Date standardBookConfirmDate;

    //StandardBook变更完成并审核通过时间
    private Date standardBookCompleteDate;

    //StandardBook是否超时变更
    private Integer standardBookDelay;

    //生产线
    private String productLine;

    private Integer rrProblemId;

    public Integer getDpcoiOrderId() {
        return dpcoiOrderId;
    }

    public void setDpcoiOrderId(Integer dpcoiOrderId) {
        this.dpcoiOrderId = dpcoiOrderId;
    }

    public Integer getDpcoiOrderState() {
        return dpcoiOrderState;
    }

    public void setDpcoiOrderState(Integer dpcoiOrderState) {
        this.dpcoiOrderState = dpcoiOrderState;
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

    public Integer getDpcoiOrderType() {
        return dpcoiOrderType;
    }

    public void setDpcoiOrderType(Integer dpcoiOrderType) {
        this.dpcoiOrderType = dpcoiOrderType;
    }

    public Integer getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getRealCuttingDate() {
        return realCuttingDate;
    }

    public void setRealCuttingDate(Date realCuttingDate) {
        this.realCuttingDate = realCuttingDate;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDesignAct() {
        return designAct;
    }

    public void setDesignAct(String designAct) {
        this.designAct = designAct;
    }

    public Integer getQuasiAct() {
        return quasiAct;
    }

    public void setQuasiAct(Integer quasiAct) {
        this.quasiAct = quasiAct;
    }

    public Date getPfmeaCreateDate() {
        return pfmeaCreateDate;
    }

    public void setPfmeaCreateDate(Date pfmeaCreateDate) {
        this.pfmeaCreateDate = pfmeaCreateDate;
    }

    public Date getPfmeaEmailDate() {
        return pfmeaEmailDate;
    }

    public void setPfmeaEmailDate(Date pfmeaEmailDate) {
        this.pfmeaEmailDate = pfmeaEmailDate;
    }

    public Date getPfmeaConfirmDate() {
        return pfmeaConfirmDate;
    }

    public void setPfmeaConfirmDate(Date pfmeaConfirmDate) {
        this.pfmeaConfirmDate = pfmeaConfirmDate;
    }

    public Date getPfmeaCompleteDate() {
        return pfmeaCompleteDate;
    }

    public void setPfmeaCompleteDate(Date pfmeaCompleteDate) {
        this.pfmeaCompleteDate = pfmeaCompleteDate;
    }

    public Integer getPfmeaDelay() {
        return pfmeaDelay;
    }

    public void setPfmeaDelay(Integer pfmeaDelay) {
        this.pfmeaDelay = pfmeaDelay;
    }

    public Date getCpCreateDate() {
        return cpCreateDate;
    }

    public void setCpCreateDate(Date cpCreateDate) {
        this.cpCreateDate = cpCreateDate;
    }

    public Date getCpEmailDate() {
        return cpEmailDate;
    }

    public void setCpEmailDate(Date cpEmailDate) {
        this.cpEmailDate = cpEmailDate;
    }

    public Date getCpConfirmDate() {
        return cpConfirmDate;
    }

    public void setCpConfirmDate(Date cpConfirmDate) {
        this.cpConfirmDate = cpConfirmDate;
    }

    public Date getCpCompleteDate() {
        return cpCompleteDate;
    }

    public void setCpCompleteDate(Date cpCompleteDate) {
        this.cpCompleteDate = cpCompleteDate;
    }

    public Integer getCpDelay() {
        return cpDelay;
    }

    public void setCpDelay(Integer cpDelay) {
        this.cpDelay = cpDelay;
    }

    public Date getStandardBookCreateDate() {
        return standardBookCreateDate;
    }

    public void setStandardBookCreateDate(Date standardBookCreateDate) {
        this.standardBookCreateDate = standardBookCreateDate;
    }

    public Date getStandardBookEmailDate() {
        return standardBookEmailDate;
    }

    public void setStandardBookEmailDate(Date standardBookEmailDate) {
        this.standardBookEmailDate = standardBookEmailDate;
    }

    public Date getStandardBookConfirmDate() {
        return standardBookConfirmDate;
    }

    public void setStandardBookConfirmDate(Date standardBookConfirmDate) {
        this.standardBookConfirmDate = standardBookConfirmDate;
    }

    public Date getStandardBookCompleteDate() {
        return standardBookCompleteDate;
    }

    public void setStandardBookCompleteDate(Date standardBookCompleteDate) {
        this.standardBookCompleteDate = standardBookCompleteDate;
    }

    public Integer getStandardBookDelay() {
        return standardBookDelay;
    }

    public void setStandardBookDelay(Integer standardBookDelay) {
        this.standardBookDelay = standardBookDelay;
    }

    public String getRealCuttingDateStr() {
        return realCuttingDateStr;
    }

    public void setRealCuttingDateStr(String realCuttingDateStr) {
        this.realCuttingDateStr = realCuttingDateStr;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getReturnDateStr() {
        return returnDateStr;
    }

    public void setReturnDateStr(String returnDateStr) {
        this.returnDateStr = returnDateStr;
    }

    public String getTaskOrderNo() {
        return taskOrderNo;
    }

    public void setTaskOrderNo(String taskOrderNo) {
        this.taskOrderNo = taskOrderNo;
    }

    public String getQuasiActName() {
        return quasiActName;
    }

    public void setQuasiActName(String quasiActName) {
        this.quasiActName = quasiActName;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public Integer getRrProblemId() {
        return rrProblemId;
    }

    public void setRrProblemId(Integer rrProblemId) {
        this.rrProblemId = rrProblemId;
    }
}
