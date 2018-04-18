package com.dpcoi.rr.domain;/**
 * Created by liangzhifu
 * DATE:2017/7/28.
 */

/**
 *
 * @author lzf
 * @create 2017-07-28
 **/

public class RRDelayLeader {

    private Integer id;

    private Integer userId;

    private Integer leaderId;

    private Integer leaderType;

    private String deleteState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getLeaderType() {
        return leaderType;
    }

    public void setLeaderType(Integer leaderType) {
        this.leaderType = leaderType;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState == null ? null : deleteState.trim();
    }

}
