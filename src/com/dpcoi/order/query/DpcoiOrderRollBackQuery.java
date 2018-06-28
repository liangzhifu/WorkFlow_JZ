package com.dpcoi.order.query;

/**
 * @author lzf
 * @create 2017-10-23
 **/

public class DpcoiOrderRollBackQuery {

    private Integer start;

    private Integer size;

    private String problemNo;

    private String productNo;

    private String designChangeNo;

    private String productLine;

    private String vehicleName;

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

    public String getProblemNo() {
        return problemNo;
    }

    public void setProblemNo(String problemNo) {
        this.problemNo = problemNo;
    }

    public String getDesignChangeNo() {
        return designChangeNo;
    }

    public void setDesignChangeNo(String designChangeNo) {
        this.designChangeNo = designChangeNo;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
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
}
