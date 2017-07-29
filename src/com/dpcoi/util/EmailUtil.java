package com.dpcoi.util;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.success.sys.email.domain.TimeTask;
import com.success.web.framework.exception.ServiceException;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

public class EmailUtil {

    public static TimeTask generateTimeTask(DpcoiOrder dpcoiOrder, RRProblem rrProblem, Integer noticeType, String emailUser) throws ServiceException{
        TimeTask timeTask = new TimeTask();
        timeTask.setNoticeType(noticeType);
        StringBuffer comment = new StringBuffer();
        Integer dpcoiOrderType = dpcoiOrder.getDpcoiOrderType();
        if(dpcoiOrderType.intValue() == 3){
            comment.append("状态:").append(rrProblem.getProblemStatus())
                    .append("<br>").append("问题编号:").append(rrProblem.getProblemNo())
                    .append("<br>").append("问题类型:").append(rrProblem.getProblemType())
                    .append("<br>").append("工程:").append(rrProblem.getEngineering())
                    .append("<br>").append("客户:").append(rrProblem.getCustomer())
                    .append("<br>").append("车型:").append(rrProblem.getVehicle())
                    .append("<br>").append("品名:").append(rrProblem.getProductNo())
                    .append("<br>").append("不良内容:").append(rrProblem.getBadContent())
                    .append("<br>").append("生产线:").append(rrProblem.getProductLine())
                    .append("<br>").append("严重度:").append(rrProblem.getSeverity())
                    .append("<br>").append("根本原因:").append(rrProblem.getRootCause())
                    .append("<br>").append("永久对策:").append(rrProblem.getPermanentGame());
            timeTask.setEmailTitle(rrProblem.getProblemNo());
        }else {
            comment.append("《设变通知书》编号:").append(dpcoiOrder.getIssuedNo())
                    .append("<br>").append("设变号:").append(dpcoiOrder.getDesignChangeNo())
                    .append("<br>").append("车种:").append(dpcoiOrder.getVehicleName())
                    .append("<br>").append("品号:").append(dpcoiOrder.getProductNo())
                    .append("<br>").append("希望切替日:").append(dpcoiOrder.getHopeCuttingDate())
                    .append("<br>").append("实际切替日:").append(dpcoiOrder.getRealCuttingDateStr())
                    .append("<br>").append("变更内容:").append(dpcoiOrder.getChangeContent())
                    .append("<br>").append("发行日期:").append(dpcoiOrder.getReleaseDateStr())
                    .append("<br>").append("《设变切替手配书》返回日:").append(dpcoiOrder.getReturnDateStr())
                    .append("<br>").append("设计担当:").append(dpcoiOrder.getDesignAct())
                    .append("<br>").append("量准担当:").append(dpcoiOrder.getQuasiActName())
                    .append("<br>").append("备注:").append(dpcoiOrder.getRemark())
                    .append("<br>").append("4M发行编号:").append(dpcoiOrder.getTaskOrderNo())
                    .append("<br>").append("生产线:").append(dpcoiOrder.getProductLine());
            if(dpcoiOrder.getIssuedNo() == null || "".equals(dpcoiOrder.getIssuedNo())){
                timeTask.setEmailTitle(dpcoiOrder.getTaskOrderNo());
            }else{
                timeTask.setEmailTitle(dpcoiOrder.getIssuedNo());
            }
        }
        timeTask.setComment(comment.toString());
        timeTask.setUserEmail(emailUser);
        timeTask.setDeleteState(0);
        return timeTask;
    }
}
