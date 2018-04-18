package com.dpcoi.rr.domain;/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author lzf
 * @create 2017-06-28
 **/

public class RRProblem {

    private Integer id;

    private String problemStatus;

    private String problemNo;

    private String problemType;

    private String engineering;

    private String problemProgress;

    private String trackingLevel;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date happenDate;

    private String happenDateStr;

    private String customer;

    private String vehicle;

    private String productNo;

    private String badContent;

    private String persionLiable;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date reportDate;

    private String reportDateStr;

    private String speedOfProgress;

    private String reasonForDelay;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date firstDate;

    private String firstDateStr;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date secondDate;

    private String secondDateStr;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date thirdDate;

    private String thirdDateStr;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fourthDate;

    private String fourthDateStr;

    private String closeConfirm;

    private Integer closeConfirmId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date closeConfirmTime;

    private String productLine;

    private String severity;

    private String occurrenceFrequency;

    private String badQuantity;

    private String batch;

    private String happenShift;

    private String responsibleDepartment;

    private String recordPpm;

    private String recordNum;

    private String temporary;

    private String rootCause;

    private String permanentGame;

    private String effectVerification;

    private String serialNumber;

    private Integer serialNumberFileId;

    private String qualityWarningCardNumber;

    private Integer qualityWarningCardNumberFileId;

    private String productScale;

    private Integer productScaleFileId;

    private String pfmea;

    private String cp;

    private String standardBook;

    private String equipmentChecklist;

    private Integer equipmentChecklistFileId;

    private String alwaysList;

    private String inspectionReferenceBook;

    private Integer inspectionReferenceBookFileId;

    private String inspectionBook;

    private Integer inspectionBookFileId;

    private String education;

    private Integer educationFileId;

    private String changePoint;

    private String expandTrace;

    private String artificial;

    private String materiel;

    private Integer state;

    private Integer isDelay;

    private Integer isHide;

    private String dpcoi4M;

    private String analyticReport;

    private Integer analyticReportFileId;

    private String layeredAudit;

    private Integer layeredAuditFileId;

    private String checkResult;

    private Integer checkResultFileId;

    private String naPending;

    private Integer naPendingFileId;

    private String otherInformation;

    private Integer otherInformationFileId;

    private Integer delayApplication;

    private Date createTime;

    private Integer createBy;

    private String containmentWorksheet;

    private Integer containmentWorksheetFileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus == null ? null : problemStatus.trim();
    }

    public String getProblemNo() {
        return problemNo;
    }

    public void setProblemNo(String problemNo) {
        this.problemNo = problemNo == null ? null : problemNo.trim();
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType == null ? null : problemType.trim();
    }

    public String getEngineering() {
        return engineering;
    }

    public void setEngineering(String engineering) {
        this.engineering = engineering == null ? null : engineering.trim();
    }

    public String getProblemProgress() {
        return problemProgress;
    }

    public void setProblemProgress(String problemProgress) {
        this.problemProgress = problemProgress == null ? null : problemProgress.trim();
    }

    public String getTrackingLevel() {
        return trackingLevel;
    }

    public void setTrackingLevel(String trackingLevel) {
        this.trackingLevel = trackingLevel == null ? null : trackingLevel.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle == null ? null : vehicle.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getBadContent() {
        return badContent;
    }

    public void setBadContent(String badContent) {
        this.badContent = badContent == null ? null : badContent.trim();
    }

    public String getPersionLiable() {
        return persionLiable;
    }

    public void setPersionLiable(String persionLiable) {
        this.persionLiable = persionLiable;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getSpeedOfProgress() {
        return speedOfProgress;
    }

    public void setSpeedOfProgress(String speedOfProgress) {
        this.speedOfProgress = speedOfProgress == null ? null : speedOfProgress.trim();
    }

    public String getReasonForDelay() {
        return reasonForDelay;
    }

    public void setReasonForDelay(String reasonForDelay) {
        this.reasonForDelay = reasonForDelay == null ? null : reasonForDelay.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getThirdDate() {
        return thirdDate;
    }

    public void setThirdDate(Date thirdDate) {
        this.thirdDate = thirdDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getFourthDate() {
        return fourthDate;
    }

    public void setFourthDate(Date fourthDate) {
        this.fourthDate = fourthDate;
    }

    public String getCloseConfirm() {
        return closeConfirm;
    }

    public void setCloseConfirm(String closeConfirm) {
        this.closeConfirm = closeConfirm == null ? null : closeConfirm.trim();
    }

    public Integer getCloseConfirmId() {
        return closeConfirmId;
    }

    public void setCloseConfirmId(Integer closeConfirmId) {
        this.closeConfirmId = closeConfirmId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCloseConfirmTime() {
        return closeConfirmTime;
    }

    public void setCloseConfirmTime(Date closeConfirmTime) {
        this.closeConfirmTime = closeConfirmTime;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine == null ? null : productLine.trim();
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity == null ? null : severity.trim();
    }

    public String getOccurrenceFrequency() {
        return occurrenceFrequency;
    }

    public void setOccurrenceFrequency(String occurrenceFrequency) {
        this.occurrenceFrequency = occurrenceFrequency == null ? null : occurrenceFrequency.trim();
    }

    public String getBadQuantity() {
        return badQuantity;
    }

    public void setBadQuantity(String badQuantity) {
        this.badQuantity = badQuantity == null ? null : badQuantity.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getHappenShift() {
        return happenShift;
    }

    public void setHappenShift(String happenShift) {
        this.happenShift = happenShift == null ? null : happenShift.trim();
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment == null ? null : responsibleDepartment.trim();
    }

    public String getRecordPpm() {
        return recordPpm;
    }

    public void setRecordPpm(String recordPpm) {
        this.recordPpm = recordPpm == null ? null : recordPpm.trim();
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum == null ? null : recordNum.trim();
    }

    public String getTemporary() {
        return temporary;
    }

    public void setTemporary(String temporary) {
        this.temporary = temporary == null ? null : temporary.trim();
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause == null ? null : rootCause.trim();
    }

    public String getPermanentGame() {
        return permanentGame;
    }

    public void setPermanentGame(String permanentGame) {
        this.permanentGame = permanentGame == null ? null : permanentGame.trim();
    }

    public String getEffectVerification() {
        return effectVerification;
    }

    public void setEffectVerification(String effectVerification) {
        this.effectVerification = effectVerification == null ? null : effectVerification.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getQualityWarningCardNumber() {
        return qualityWarningCardNumber;
    }

    public void setQualityWarningCardNumber(String qualityWarningCardNumber) {
        this.qualityWarningCardNumber = qualityWarningCardNumber == null ? null : qualityWarningCardNumber.trim();
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale == null ? null : productScale.trim();
    }

    public String getPfmea() {
        return pfmea;
    }

    public void setPfmea(String pfmea) {
        this.pfmea = pfmea == null ? null : pfmea.trim();
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp == null ? null : cp.trim();
    }

    public String getStandardBook() {
        return standardBook;
    }

    public void setStandardBook(String standardBook) {
        this.standardBook = standardBook == null ? null : standardBook.trim();
    }

    public String getEquipmentChecklist() {
        return equipmentChecklist;
    }

    public void setEquipmentChecklist(String equipmentChecklist) {
        this.equipmentChecklist = equipmentChecklist == null ? null : equipmentChecklist.trim();
    }

    public String getAlwaysList() {
        return alwaysList;
    }

    public void setAlwaysList(String alwaysList) {
        this.alwaysList = alwaysList == null ? null : alwaysList.trim();
    }

    public String getInspectionReferenceBook() {
        return inspectionReferenceBook;
    }

    public void setInspectionReferenceBook(String inspectionReferenceBook) {
        this.inspectionReferenceBook = inspectionReferenceBook == null ? null : inspectionReferenceBook.trim();
    }

    public String getInspectionBook() {
        return inspectionBook;
    }

    public void setInspectionBook(String inspectionBook) {
        this.inspectionBook = inspectionBook == null ? null : inspectionBook.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(String changePoint) {
        this.changePoint = changePoint == null ? null : changePoint.trim();
    }

    public String getExpandTrace() {
        return expandTrace;
    }

    public void setExpandTrace(String expandTrace) {
        this.expandTrace = expandTrace == null ? null : expandTrace.trim();
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial == null ? null : artificial.trim();
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel == null ? null : materiel.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getHappenDateStr() {
        return happenDateStr;
    }

    public void setHappenDateStr(String happenDateStr) {
        this.happenDateStr = happenDateStr;
    }

    public String getFirstDateStr() {
        return firstDateStr;
    }

    public void setFirstDateStr(String firstDateStr) {
        this.firstDateStr = firstDateStr;
    }

    public String getSecondDateStr() {
        return secondDateStr;
    }

    public void setSecondDateStr(String secondDateStr) {
        this.secondDateStr = secondDateStr;
    }

    public String getThirdDateStr() {
        return thirdDateStr;
    }

    public void setThirdDateStr(String thirdDateStr) {
        this.thirdDateStr = thirdDateStr;
    }

    public String getFourthDateStr() {
        return fourthDateStr;
    }

    public void setFourthDateStr(String fourthDateStr) {
        this.fourthDateStr = fourthDateStr;
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }

    public Integer getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Integer isDelay) {
        this.isDelay = isDelay;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getSerialNumberFileId() {
        return serialNumberFileId;
    }

    public void setSerialNumberFileId(Integer serialNumberFileId) {
        this.serialNumberFileId = serialNumberFileId;
    }

    public Integer getQualityWarningCardNumberFileId() {
        return qualityWarningCardNumberFileId;
    }

    public void setQualityWarningCardNumberFileId(Integer qualityWarningCardNumberFileId) {
        this.qualityWarningCardNumberFileId = qualityWarningCardNumberFileId;
    }

    public Integer getProductScaleFileId() {
        return productScaleFileId;
    }

    public void setProductScaleFileId(Integer productScaleFileId) {
        this.productScaleFileId = productScaleFileId;
    }

    public Integer getEquipmentChecklistFileId() {
        return equipmentChecklistFileId;
    }

    public void setEquipmentChecklistFileId(Integer equipmentChecklistFileId) {
        this.equipmentChecklistFileId = equipmentChecklistFileId;
    }

    public Integer getInspectionReferenceBookFileId() {
        return inspectionReferenceBookFileId;
    }

    public void setInspectionReferenceBookFileId(Integer inspectionReferenceBookFileId) {
        this.inspectionReferenceBookFileId = inspectionReferenceBookFileId;
    }

    public Integer getInspectionBookFileId() {
        return inspectionBookFileId;
    }

    public void setInspectionBookFileId(Integer inspectionBookFileId) {
        this.inspectionBookFileId = inspectionBookFileId;
    }

    public Integer getEducationFileId() {
        return educationFileId;
    }

    public void setEducationFileId(Integer educationFileId) {
        this.educationFileId = educationFileId;
    }

    public String getDpcoi4M() {
        return dpcoi4M;
    }

    public void setDpcoi4M(String dpcoi4M) {
        this.dpcoi4M = dpcoi4M;
    }

    public String getAnalyticReport() {
        return analyticReport;
    }

    public void setAnalyticReport(String analyticReport) {
        this.analyticReport = analyticReport;
    }

    public Integer getAnalyticReportFileId() {
        return analyticReportFileId;
    }

    public void setAnalyticReportFileId(Integer analyticReportFileId) {
        this.analyticReportFileId = analyticReportFileId;
    }

    public String getLayeredAudit() {
        return layeredAudit;
    }

    public void setLayeredAudit(String layeredAudit) {
        this.layeredAudit = layeredAudit;
    }

    public Integer getLayeredAuditFileId() {
        return layeredAuditFileId;
    }

    public void setLayeredAuditFileId(Integer layeredAuditFileId) {
        this.layeredAuditFileId = layeredAuditFileId;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public Integer getCheckResultFileId() {
        return checkResultFileId;
    }

    public void setCheckResultFileId(Integer checkResultFileId) {
        this.checkResultFileId = checkResultFileId;
    }

    public String getNaPending() {
        return naPending;
    }

    public void setNaPending(String naPending) {
        this.naPending = naPending;
    }

    public Integer getNaPendingFileId() {
        return naPendingFileId;
    }

    public void setNaPendingFileId(Integer naPendingFileId) {
        this.naPendingFileId = naPendingFileId;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public Integer getOtherInformationFileId() {
        return otherInformationFileId;
    }

    public void setOtherInformationFileId(Integer otherInformationFileId) {
        this.otherInformationFileId = otherInformationFileId;
    }

    public Integer getDelayApplication() {
        return delayApplication;
    }

    public void setDelayApplication(Integer delayApplication) {
        this.delayApplication = delayApplication;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContainmentWorksheet() {
        return containmentWorksheet;
    }

    public void setContainmentWorksheet(String containmentWorksheet) {
        this.containmentWorksheet = containmentWorksheet;
    }

    public Integer getContainmentWorksheetFileId() {
        return containmentWorksheetFileId;
    }

    public void setContainmentWorksheetFileId(Integer containmentWorksheetFileId) {
        this.containmentWorksheetFileId = containmentWorksheetFileId;
    }
}
