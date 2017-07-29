package com.dpcoi.config.domain;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

/**
 * DPCOI下拉列表配置
 * @author lzf
 * @create 2017-06-17
 **/

public class DpcoiConfig {

    private Integer configId;

    private Integer configCodeId;

    private String configValue;

    private String deleteState;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getConfigCodeId() {
        return configCodeId;
    }

    public void setConfigCodeId(Integer configCodeId) {
        this.configCodeId = configCodeId;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }
}
