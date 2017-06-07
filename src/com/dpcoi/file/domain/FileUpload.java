package com.dpcoi.file.domain;

import java.util.Date;

/**
 * Created by 梁志福 on 2017/4/20.
 */
public class FileUpload {

    private Integer fileId;

    private String fileName;

    private String fileAlias;

    private String fileType;

    private String excelPdfName;

    private Date createDate;

    private Integer createBy;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAlias() {
        return fileAlias;
    }

    public void setFileAlias(String fileAlias) {
        this.fileAlias = fileAlias;
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

    public String getExcelPdfName() {
        return excelPdfName;
    }

    public void setExcelPdfName(String excelPdfName) {
        this.excelPdfName = excelPdfName;
    }
}
