package com.dpcoi.util;/**
 * Created by liangzhifu
 * DATE:2017/5/7.
 */

import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.success.sys.email.domain.TimeTask;
import com.success.web.framework.exception.ServiceException;

/**
 *
 * @author lzf
 * @create 2017-05-07
 **/

public class EmailUtil {

    public static TimeTask generateTimeTask(DpcoiOrder dpcoiOrder, Integer noticeType, String emailUser) throws ServiceException{
        TimeTask timeTask = new TimeTask();
        timeTask.setNoticeType(noticeType);
        StringBuffer comment = new StringBuffer();
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
                .append("<br>").append("4M发行编号:").append(dpcoiOrder.getTaskOrderNo());
        timeTask.setComment(comment.toString());
        timeTask.setUserEmail(emailUser);
        timeTask.setDeleteState(0);
        return timeTask;
    }
}
