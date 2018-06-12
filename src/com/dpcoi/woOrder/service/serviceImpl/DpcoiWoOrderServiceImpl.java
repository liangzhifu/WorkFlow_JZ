package com.dpcoi.woOrder.service.serviceImpl;

import com.dpcoi.rr.dao.RRProblemDao;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.util.EmailUtil;
import com.dpcoi.file.dao.FileUploadDao;
import com.dpcoi.order.dao.DpcoiOrderDao;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.woOrder.dao.DpcoiWoOrderDao;
import com.dpcoi.woOrder.dao.DpcoiWoOrderFileDao;
import com.dpcoi.woOrder.domain.DpcoiWoOrder;
import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderService;
import com.success.sys.email.dao.TimeTaskDao;
import com.success.sys.email.domain.TimeTask;
import com.success.sys.user.domain.User;
import com.success.web.framework.exception.ServiceException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Service("dpcoiWoOrderService")
public class DpcoiWoOrderServiceImpl implements DpcoiWoOrderService {

    @Resource(name="dpcoiOrderDao")
    private DpcoiOrderDao dpcoiOrderDao;

    @Resource(name="dpcoiWoOrderDao")
    private DpcoiWoOrderDao dpcoiWoOrderDao;

    @Resource(name="dpcoiWoOrderFileDao")
    private DpcoiWoOrderFileDao dpcoiWoOrderFileDao;

    @Resource(name="timeTaskDao")
    private TimeTaskDao timeTaskDao;

    @Resource(name="rRProblemDao")
    private RRProblemDao rRProblemDao;

    @Override
    public Integer addDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException {
        return this.dpcoiWoOrderDao.insertDpcoiWoOrder(dpcoiWoOrder);
    }

    @Override
    public Integer updateDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException {
        return this.dpcoiWoOrderDao.updateDpcoiWoOrder(dpcoiWoOrder);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderListPage(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException {
        return this.dpcoiWoOrderDao.selectDpcoiWoOrderListPage(dpcoiWoOrderQuery);
    }

    @Override
    public List<Map<String, Object>> queryDpcoiWoOrderList(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException {
        return this.dpcoiWoOrderDao.selectDpcoiWoOrderList(dpcoiWoOrderQuery);
    }

    @Override
    public Integer queryDpcoiWoOrderCount(DpcoiWoOrderQuery dpcoiWoOrderQuery) throws ServiceException {
        return this.dpcoiWoOrderDao.selectDpcoiWoOrderCount(dpcoiWoOrderQuery);
    }

    @Override
    public DpcoiWoOrder queryDpcoiWoOrder(DpcoiWoOrder dpcoiWoOrder) throws ServiceException {
        return this.dpcoiWoOrderDao.selectBySelf(dpcoiWoOrder);
    }

    @Override
    public void editWoOrderConfirm(DpcoiWoOrder dpcoiWoOrder, Integer confirmResult, User user) throws ServiceException {
        dpcoiWoOrder = this.queryDpcoiWoOrder(dpcoiWoOrder);
        int dpcoiWoOrderType = dpcoiWoOrder.getDpcoiWoOrderType().intValue();

        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
        dpcoiOrder = this.dpcoiOrderDao.selectBySelf(dpcoiOrder);
        //设置变更确认时间
        if(dpcoiWoOrderType == 1){
            dpcoiOrder.setPfmeaConfirmDate(new Date());
        }else if (dpcoiWoOrderType == 2){
            dpcoiOrder.setCpConfirmDate(new Date());
        }else if(dpcoiWoOrderType == 3){
            dpcoiOrder.setStandardBookConfirmDate(new Date());
        }

        if(confirmResult.intValue() == 0){//不需要变更
            dpcoiWoOrder.setDpcoiWoOrderState(7);

            //设置变更完成时间

            if(dpcoiWoOrderType == 1){
                dpcoiOrder.setPfmeaCompleteDate(new Date());
            }else if (dpcoiWoOrderType == 2){
                dpcoiOrder.setCpCompleteDate(new Date());
            }else if(dpcoiWoOrderType == 3){
                dpcoiOrder.setStandardBookCompleteDate(new Date());
            }
            Integer rrId = dpcoiOrder.getRrProblemId();
            if(rrId != null){
                RRProblem rrProblem = new RRProblem();
                rrProblem.setId(rrId);
                if(dpcoiWoOrderType == 1){
                    rrProblem.setPfmea("N/A");
                }else if (dpcoiWoOrderType == 2){
                    rrProblem.setCp("N/A");
                }else if(dpcoiWoOrderType == 3){
                    rrProblem.setStandardBook("N/A");
                    rrProblem.setAlwaysList("N/A");
                }
                this.rRProblemDao.updateRRProblem(rrProblem);
            }

            if(dpcoiWoOrderType == 3){
                //定单完成
                dpcoiOrder.setDpcoiOrderState(2);
            }else {
                DpcoiWoOrder newDpcoiWoOrder = new DpcoiWoOrder();
                newDpcoiWoOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
                newDpcoiWoOrder.setDelFlag("0");
                newDpcoiWoOrder.setDpcoiWoOrderState(1);
                newDpcoiWoOrder.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType()+1);
                newDpcoiWoOrder.setCreateDate(new Date());
                this.dpcoiWoOrderDao.insertDpcoiWoOrder(newDpcoiWoOrder);

                //发邮件通知人员--确认变更
                Integer noticeType = 18;
                if(dpcoiWoOrderType == 1){
                    noticeType = 35;
                    dpcoiOrder.setCpCreateDate(new Date());
                    dpcoiOrder.setCpEmailDate(new Date());
                }else if(dpcoiWoOrderType == 2){
                    noticeType = 25;
                    dpcoiOrder.setStandardBookCreateDate(new Date());
                    dpcoiOrder.setStandardBookEmailDate(new Date());
                }
                DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
                dpcoiWoOrderQery.setDpcoiWoOrderState(newDpcoiWoOrder.getDpcoiWoOrderState());
                dpcoiWoOrderQery.setDpcoiWoOrderType(newDpcoiWoOrder.getDpcoiWoOrderType());
                String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
                Integer rrProblemId = dpcoiOrder.getRrProblemId();
                RRProblem rrProblem;
                if(rrProblemId == null){
                    rrProblem = null;
                }else {
                    rrProblem = new RRProblem();
                    rrProblem.setId(rrProblemId);
                    rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
                }
                TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, noticeType, emaliUser);
                this.timeTaskDao.insertTimeTask(timeTask);
//功能不用暂时注解
//                //查找该工单对应的定单是否4M生成。如果是4M生成，查一下是否已经确认变更
//                List<Map<String, Object>> mapList = this.dpcoiWoOrderDao.selectDpcoiWoOrder4MConfrim(newDpcoiWoOrder);
//                for (Map<String, Object> map : mapList){
//                    Integer newConfrimResult = (Integer)map.get("confirmResult");
//                    Integer newUserId = (Integer)map.get("userId");
//                    User newUser = new User();
//                    newUser.setUserId(newUserId);
//                    this.editWoOrderConfirm(newDpcoiWoOrder, newConfrimResult, newUser);
//                    break;
//                }
            }
        } else {//需要变更
            //发邮件通知人员--上传文件
//            Integer noticeType = 19;
//            if(dpcoiWoOrderType == 1){
//                noticeType = 19;
//            }else if(dpcoiWoOrderType == 2){
//                noticeType = 22;
//            }else if(dpcoiWoOrderType == 3){
//                noticeType = 26;
//            }
            dpcoiWoOrder.setDpcoiWoOrderState(2);
//            DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
//            dpcoiWoOrderQery.setDpcoiWoOrderState(dpcoiWoOrder.getDpcoiWoOrderState());
//            dpcoiWoOrderQery.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType());
//            String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
//            TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, noticeType, emaliUser);
//            this.timeTaskDao.insertTimeTask(timeTask);
        }
        dpcoiWoOrder.setConfirmBy(user.getUserId());
        dpcoiWoOrder.setConfirmDate(new Date());
        //设置超时时间
        Date delayDate = this.getDealyDate(new Date());
        dpcoiWoOrder.setDelayDate(delayDate);
        this.dpcoiWoOrderDao.updateDpcoiWoOrder(dpcoiWoOrder);

        this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);
    }

    @Override
    public void editWoOrderFileComplete(DpcoiWoOrder dpcoiWoOrder, User user) throws ServiceException {
        dpcoiWoOrder = this.queryDpcoiWoOrder(dpcoiWoOrder);

        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
        dpcoiOrder = this.dpcoiOrderDao.selectBySelf(dpcoiOrder);

        dpcoiWoOrder.setDpcoiWoOrderState(3);
        dpcoiWoOrder.setFileCompleteBy(user.getUserId());
        dpcoiWoOrder.setFileCompleteDate(new Date());
        this.updateDpcoiWoOrder(dpcoiWoOrder);

        //将现有不通过的文件删除
        DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery = new DpcoiWoOrderFileQuery();
        dpcoiWoOrderFileQuery.setDpcoiWoOrderId(dpcoiWoOrder.getDpcoiWoOrderId());
        this.dpcoiWoOrderFileDao.deleteDpcoiWoOrderFile(dpcoiWoOrderFileQuery);

        //发邮件通知人员--审核
        int dpcoiWoOrderType = dpcoiWoOrder.getDpcoiWoOrderType().intValue();
        Integer noticeType = 20;
        if(dpcoiWoOrderType == 1){
            noticeType = 20;
        }else if(dpcoiWoOrderType == 2){
            noticeType = 23;
        }else if(dpcoiWoOrderType == 3){
            noticeType = 27;
        }
        DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
        dpcoiWoOrderQery.setDpcoiWoOrderState(dpcoiWoOrder.getDpcoiWoOrderState());
        dpcoiWoOrderQery.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType());
        String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
        Integer rrProblemId = dpcoiOrder.getRrProblemId();
        RRProblem rrProblem;
        if(rrProblemId == null){
            rrProblem = null;
        }else {
            rrProblem = new RRProblem();
            rrProblem.setId(rrProblemId);
            rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
        }
        TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, noticeType, emaliUser);
        this.timeTaskDao.insertTimeTask(timeTask);
    }

    @Override
    public void editWoOrderManagerConfirm(DpcoiWoOrder dpcoiWoOrder, Integer confirmResult, User user, String noPassedWoOrderFileIdStr) throws ServiceException {
        dpcoiWoOrder = this.queryDpcoiWoOrder(dpcoiWoOrder);

        dpcoiWoOrder.setManagerConfirmBy(user.getUserId());
        dpcoiWoOrder.setManagerConfirmDate(new Date());

        DpcoiOrder dpcoiOrder = new DpcoiOrder();
        dpcoiOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
        dpcoiOrder = this.dpcoiOrderDao.selectBySelf(dpcoiOrder);
        if(confirmResult.intValue() == 0){//审核不通过
            //将刚审核未不通过的文件更新状态
            if(!"".equals(noPassedWoOrderFileIdStr)){
                String[] woOrderFileIds = noPassedWoOrderFileIdStr.split(",");
                for(String woOrderFileId : woOrderFileIds){
                    DpcoiWoOrderFile dpcoiWoOrderFile = new DpcoiWoOrderFile();
                    dpcoiWoOrderFile.setId(Integer.valueOf(woOrderFileId));
                    dpcoiWoOrderFile.setWoFileState(2);
                    this.dpcoiWoOrderFileDao.updateDpcoiWoOrderFile(dpcoiWoOrderFile);
                }
            }

            //更新所有没有审核的文件为通过状态
            DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery = new DpcoiWoOrderFileQuery();
            dpcoiWoOrderFileQuery.setDpcoiWoOrderId(dpcoiWoOrder.getDpcoiWoOrderId());
            this.dpcoiWoOrderFileDao.updateDpcoiWoOrderFilePassed(dpcoiWoOrderFileQuery);

            dpcoiWoOrder.setDpcoiWoOrderState(5);

            DpcoiWoOrder newDpcoiWoOrder = new DpcoiWoOrder();
            newDpcoiWoOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
            newDpcoiWoOrder.setDelFlag("0");
            newDpcoiWoOrder.setDpcoiWoOrderState(2);
            newDpcoiWoOrder.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType());
            newDpcoiWoOrder.setCreateDate(dpcoiWoOrder.getCreateDate());
            newDpcoiWoOrder.setConfirmDate(dpcoiWoOrder.getConfirmDate());
            newDpcoiWoOrder.setConfirmBy(dpcoiWoOrder.getConfirmBy());
            newDpcoiWoOrder.setDelayDate(dpcoiWoOrder.getDelayDate());
            this.dpcoiWoOrderDao.insertDpcoiWoOrder(newDpcoiWoOrder);

            //将就工单的所有文件关联添加到新工单上，删除文件除外
            Map<String, Object> map = new HashedMap();
            map.put("oldDpcoiWoOrderId", dpcoiWoOrder.getDpcoiWoOrderId());
            map.put("newDpcoiWoOrderId", newDpcoiWoOrder.getDpcoiWoOrderId());
            this.dpcoiWoOrderFileDao.insertCopyDpcoiWoOrder(map);

            //发邮件通知人员--上传文件
            int dpcoiWoOrderType = newDpcoiWoOrder.getDpcoiWoOrderType().intValue();
            Integer noticeType = 21;
            if(dpcoiWoOrderType == 1){
                noticeType = 21;
            }else if(dpcoiWoOrderType == 2){
                noticeType = 24;
            }else if(dpcoiWoOrderType == 3){
                noticeType = 28;
            }
            DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
            dpcoiWoOrderQery.setDpcoiWoOrderState(newDpcoiWoOrder.getDpcoiWoOrderState());
            dpcoiWoOrderQery.setDpcoiWoOrderType(newDpcoiWoOrder.getDpcoiWoOrderType());
            String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
            Integer rrProblemId = dpcoiOrder.getRrProblemId();
            RRProblem rrProblem;
            if(rrProblemId == null){
                rrProblem = null;
            }else {
                rrProblem = new RRProblem();
                rrProblem.setId(rrProblemId);
                rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
            }
            TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, noticeType, emaliUser);
            this.timeTaskDao.insertTimeTask(timeTask);
        } else {//审核通过
            //更新所有没有审核的文件为通过状态
            DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery = new DpcoiWoOrderFileQuery();
            dpcoiWoOrderFileQuery.setDpcoiWoOrderId(dpcoiWoOrder.getDpcoiWoOrderId());
            this.dpcoiWoOrderFileDao.updateDpcoiWoOrderFilePassed(dpcoiWoOrderFileQuery);


            dpcoiWoOrder.setDpcoiWoOrderState(4);

            int dpcoiWoOrderType = dpcoiWoOrder.getDpcoiWoOrderType().intValue();

            //设置变更完成时间
            if(dpcoiWoOrderType == 1){
                dpcoiOrder.setPfmeaCompleteDate(new Date());
            }else if (dpcoiWoOrderType == 2){
                dpcoiOrder.setCpCompleteDate(new Date());
            }else if(dpcoiWoOrderType == 3){
                dpcoiOrder.setStandardBookCompleteDate(new Date());
            }
            Integer rrId = dpcoiOrder.getRrProblemId();
            if(rrId != null){
                RRProblem rrProblem = new RRProblem();
                rrProblem.setId(rrId);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                if(dpcoiWoOrderType == 1){
                    rrProblem.setPfmea(formatter.format(new Date()));
                }else if (dpcoiWoOrderType == 2){
                    rrProblem.setCp(formatter.format(new Date()));
                }else if(dpcoiWoOrderType == 3){
                    rrProblem.setStandardBook(formatter.format(new Date()));
                    rrProblem.setAlwaysList(formatter.format(new Date()));
                }
                this.rRProblemDao.updateRRProblem(rrProblem);
            }

            if(dpcoiWoOrderType == 3){
                dpcoiOrder.setDpcoiOrderState(2); ;
            }else {
                DpcoiWoOrder newDpcoiWoOrder = new DpcoiWoOrder();
                newDpcoiWoOrder.setDpcoiOrderId(dpcoiWoOrder.getDpcoiOrderId());
                newDpcoiWoOrder.setDelFlag("0");
                newDpcoiWoOrder.setDpcoiWoOrderState(1);
                newDpcoiWoOrder.setDpcoiWoOrderType(dpcoiWoOrder.getDpcoiWoOrderType()+1);
                newDpcoiWoOrder.setCreateDate(new Date());
                this.dpcoiWoOrderDao.insertDpcoiWoOrder(newDpcoiWoOrder);

                //发邮件通知人员--确认变更
                int newDpcoiWoOrderType = newDpcoiWoOrder.getDpcoiWoOrderType().intValue();
                Integer newNoticeType = 35;
                if(newDpcoiWoOrderType == 2){
                    newNoticeType = 35;
                    dpcoiOrder.setCpEmailDate(new Date());
                    dpcoiOrder.setCpCreateDate(new Date());
                }else if(newDpcoiWoOrderType == 3){
                    newNoticeType = 25;
                    dpcoiOrder.setStandardBookCreateDate(new Date());
                    dpcoiOrder.setStandardBookEmailDate(new Date());
                }
                DpcoiWoOrderQuery dpcoiWoOrderQery = new DpcoiWoOrderQuery();
                dpcoiWoOrderQery.setDpcoiWoOrderState(newDpcoiWoOrder.getDpcoiWoOrderState());
                dpcoiWoOrderQery.setDpcoiWoOrderType(newDpcoiWoOrder.getDpcoiWoOrderType());
                String emaliUser = this.dpcoiWoOrderDao.selectWoOrderEmailUsers(dpcoiWoOrderQery);
                Integer rrProblemId = dpcoiOrder.getRrProblemId();
                RRProblem rrProblem;
                if(rrProblemId == null){
                    rrProblem = null;
                }else {
                    rrProblem = new RRProblem();
                    rrProblem.setId(rrProblemId);
                    rrProblem = this.rRProblemDao.selectRRProblem(rrProblem);
                }
                TimeTask timeTask = EmailUtil.generateTimeTask(dpcoiOrder, rrProblem, newNoticeType, emaliUser);
                this.timeTaskDao.insertTimeTask(timeTask);
//功能不用暂时注解
//                //查找该工单对应的定单是否4M生成。如果是4M生成，查一下是否已经确认变更
//                List<Map<String, Object>> mapList = this.dpcoiWoOrderDao.selectDpcoiWoOrder4MConfrim(newDpcoiWoOrder);
//                for (Map<String, Object> map : mapList){
//                    Integer newConfrimResult = (Integer)map.get("confirmResult");
//                    Integer newUserId = (Integer)map.get("userId");
//                    User newUser = new User();
//                    newUser.setUserId(newUserId);
//                    this.editWoOrderConfirm(newDpcoiWoOrder, newConfrimResult, newUser);
//                    break;
//                }
            }

            this.dpcoiOrderDao.updateDpcoiOrder(dpcoiOrder);
        }

        this.dpcoiWoOrderDao.updateDpcoiWoOrder(dpcoiWoOrder);
    }

    /**
     * 判断日期是否节假日
     * @param date
     * @return
     */
    private boolean isHoliday(Date date){
        Integer num = this.dpcoiWoOrderDao.selectHoliday(date);
        if(num > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取超时时间
     * @param date
     * @return
     */
    private Date getDealyDate(Date date){
        Calendar src = Calendar.getInstance();
        src.setTime(date);
        boolean holidayFlag;
        for (int i = 0; i < 2; i++) {
            //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag = this.isHoliday(src.getTime());
            if(holidayFlag) {
                i--;
            }
        }
        return src.getTime();
    }
}
