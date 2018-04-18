package com.dpcoi.rr.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */

import com.dpcoi.drived.service.ExportExcelService;
import com.dpcoi.file.domain.FileUpload;
import com.dpcoi.order.domain.DpcoiOrder;
import com.dpcoi.order.service.DpcoiOrderService;
import com.dpcoi.rr.domain.RRProblem;
import com.dpcoi.rr.query.RRProblemQuery;
import com.dpcoi.rr.service.RRProblemService;
import com.dpcoi.statistics.domain.RRDelayStatistics;
import com.dpcoi.statistics.service.RRDelayStatisticsService;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author lzf
 * @create 2017-06-28
 **/
@Controller
@RequestMapping("/rrProblem")
public class RRProblemController {

    @Resource(name = "rRProblemService")
    private RRProblemService rRProblemService;

    @Resource(name = "dpcoiOrderService")
    private DpcoiOrderService dpcoiOrderService;

    @Resource(name = "exportExcelService")
    private ExportExcelService exportExcelService;

    @Resource(name = "dpcoiWoOrderService")
    private DpcoiWoOrderService dpcoiWoOrderService;

    @Resource(name = "rRDelayStatisticsService")
    private RRDelayStatisticsService rRDelayStatisticsService;

    @RequestMapping("/getRRProblemAddDlg.do")
    public String getRRProblemAddDlg(Map<String, Object> model) throws Exception{
        model.put("action", "add");
        Map<String, Object> map = this.rRProblemService.getHappenDateRandom();
        model.put("startDate", map.get("startDate"));
        model.put("endDate", map.get("endDate"));
        return "dpcoi/rrProblemEdit";
    }

    @RequestMapping("/getRRProblemEditDlg.do")
    public String getRRProblemEditDlg(Map<String, Object> model, RRProblem rrProblem, RRProblemQuery rrProblemQuery) throws Exception{
        model.put("action", "edit");
        Map<String, Object> map = this.rRProblemService.getHappenDateRandom();
        model.put("startDate", map.get("startDate"));
        model.put("endDate", map.get("endDate"));
        model.put("rrProblemId", rrProblem.getId());
        model.put("rrProblemQuery", rrProblemQuery);
        return "dpcoi/rrProblemEdit";
    }

    @RequestMapping("/getRRProblemListDlg.do")
    public String getRRProblemListDlg(HttpServletRequest request, Map<String, Object> model, RRProblemQuery rrProblemQuery) throws Exception{
        User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
        Integer ministerJurisdiction = this.rRProblemService.queryMinisterJurisdiction(user);
        model.put("ministerJurisdiction", ministerJurisdiction);
        model.put("userName", user.getUserName());
        model.put("rrProblemQuery", rrProblemQuery);
        return "dpcoi/rrProblemList";
    }

    @RequestMapping("/getRRProblemViewListDlg.do")
    public String getRRProblemViewListDlg() throws Exception{
        return "dpcoi/rrProblemViewList";
    }

    @RequestMapping("/getRRProblemScreenShowDlg.do")
    public String getRRProblemScreenShowDlg(Map<String, Object> model) throws Exception{
        List<Map<String, Object>> newMapList = new LinkedList<Map<String, Object>>();
        List<Map<String, Object>> mapList = this.rRProblemService.queryRRProblemScreenShowList();
        for(Map<String, Object> map : mapList){
            String trackingLevel = (String)map.get("trackingLevel");
            if(trackingLevel == null || "".equals(trackingLevel) || "V".equals(trackingLevel)){
                map.put("backgroundColor", "background-color : #808080;color: #FFFFFF;!important;");
            }else if("I".equals(trackingLevel)){
                map.put("backgroundColor", "background-color : red;color: #FFFFFF;!important;");
            }else if("II".equals(trackingLevel)){
                map.put("backgroundColor", "background-color : red;color: #FFFFFF;!important;");
            }else if("III".equals(trackingLevel)){
                map.put("backgroundColor", "background-color : GoldenRod;color: #000000;!important;");
            }else if("IV".equals(trackingLevel)){
                map.put("backgroundColor", "background-color : yellow;color: #000000;!important;");
            }else {
                map.put("backgroundColor", "background-color : #808080;color: #FFFFFF;!important;");
            }

//            String speedOfProgress = (String)map.get("speedOfProgress");
//            if(speedOfProgress == null || "".equals(speedOfProgress)){
//                map.put("backgroundColor", "background-color : #808080;color: #FFFFFF;!important;");
//            }else if("delayI".equals(speedOfProgress)){
//                if(isDelay.intValue() == 1){
//                    map.put("backgroundColor", "background-color : deepskyblue;color: #000000;!important;");
//                }else {
//                    map.put("backgroundColor", "background-color : red;color: #FFFFFF;!important;");
//                }
//            }else if("delayII".equals(speedOfProgress)){
//                if(isDelay.intValue() == 1){
//                    map.put("backgroundColor", "background-color : deepskyblue;color: #000000;!important;");
//                }else {
//                    map.put("backgroundColor", "background-color : red;color: #FFFFFF;!important;");
//                }
//            }else if("delayIII".equals(speedOfProgress)){
//                if(isDelay.intValue() == 1){
//                    map.put("backgroundColor", "background-color : deepskyblue;color: #000000;!important;");
//                }else {
//                    map.put("backgroundColor", "background-color : GoldenRod;color: #000000;!important;");
//                }
//            }else if("delayIV".equals(speedOfProgress)){
//                if(isDelay.intValue() == 1){
//                    map.put("backgroundColor", "background-color : deepskyblue;color: #000000;!important;");
//                }else {
//                    map.put("backgroundColor", "background-color : yellow;color: #000000;!important;");
//                }
//            }else{
//                map.put("backgroundColor", "background-color : #808080;color: #FFFFFF;!important;");
//            }
            String persionLiable = (String)map.get("persionLiable");
            persionLiable = persionLiable.replace(",", "<br>");
            map.put("persionLiable", persionLiable);
            newMapList.add(map);
        }
        model.put("mapList", newMapList);
        return "dpcoi/rrProblemScreenShow";
    }

    /**
     * 导出RR问题点EXCEL
     * @param request 参数
     * @param response 参数
     * @param rrProblemQuery 查询条件
     */
    @RequestMapping("/doExportExcel.do")
    public void doDown(HttpServletRequest request, HttpServletResponse response, RRProblemQuery rrProblemQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            Integer ministerJurisdiction = this.rRProblemService.queryMinisterJurisdiction(user);
            if(ministerJurisdiction == 0){
                rrProblemQuery.setIsHide(0);
            }
            String speedOfProgress = rrProblemQuery.getSpeedOfProgress();
            if(speedOfProgress == null || "".equals(speedOfProgress)){
                speedOfProgress = "delayI,delayII,delayIII,delayIV,follow";
                rrProblemQuery.setSpeedOfProgress(speedOfProgress);
            }
            String path = request.getSession().getServletContext().getRealPath("/");
            String fileName = this.exportExcelService.excelRRProblem(path, rrProblemQuery);
            map.put("success", true);
            map.put("path", "/stdout/" + fileName);
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 查询RR问题点选项列表--分页
     * @param response 参数
     * @param rrProblemQuery 查询条件
     */
    @RequestMapping("/getRRProblemListPage.do")
    public void getRRProblemListPage(HttpServletRequest request, HttpServletResponse response, RRProblemQuery rrProblemQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            Integer ministerJurisdiction = this.rRProblemService.queryMinisterJurisdiction(user);
            if(ministerJurisdiction == 0){
                rrProblemQuery.setIsHide(0);
            }
            String speedOfProgress = rrProblemQuery.getSpeedOfProgress();
            if(speedOfProgress == null || "".equals(speedOfProgress)){
                speedOfProgress = "delayI,delayII,delayIII,delayIV,follow";
                rrProblemQuery.setSpeedOfProgress(speedOfProgress);
            }
            List<Map<String, Object>> rrProblemList = this.rRProblemService.queryRRProblemPageList(rrProblemQuery);
            Integer rrProblemCount = this.rRProblemService.queryRRProblemCount(rrProblemQuery);
            Integer pageCount = rrProblemCount / rrProblemQuery.getSize() + (rrProblemCount % rrProblemQuery.getSize() > 0 ? 1 : 0);
            map.put("rrProblemList", rrProblemList);
            map.put("rrProblemCount", rrProblemCount);
            map.put("pageCount", pageCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 查询RR问题点
     * @param response 参数
     * @param rrProblem 查询条件
     */
    @RequestMapping("/getRRProblem.do")
    public void getRRProblemListPage(HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            map.put("rrProblem", rrProblem);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 新增RR问题点选项
     * @param response 参数
     * @param rrProblem RR问题点选项
     */
    @RequestMapping("/addRRProblem.do")
    public void addRRProblem(HttpServletRequest request, HttpServletResponse response, @ModelAttribute RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            User rrUser = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            rrProblem.setCreateTime(new Date());
            rrProblem.setCreateBy(rrUser.getUserId());

            rrProblem.setIsHide(0);
            this.validSpeedOfProgress(rrProblem);
            rrProblem = this.validUploadFile(rrProblem);

            map.put("success", true);
            String changePoint = rrProblem.getChangePoint();
            if(changePoint != null){
                changePoint = changePoint.toUpperCase();
            }
            rrProblem.setChangePoint(changePoint);
            if(changePoint == null || "".equals(changePoint)){
                this.rRProblemService.addRRProblem(rrProblem);
            }else if("N/A".equals(changePoint)){
                String problemType = rrProblem.getProblemType();
                if ("部品".equals(problemType)){
                    rrProblem.setPfmea("N/A");
                    rrProblem.setCp("N/A");
                    rrProblem.setStandardBook("N/A");
                    rrProblem.setAlwaysList("N/A");
                    this.rRProblemService.addRRProblem(rrProblem);
                }else {
                    this.rRProblemService.addRRProblem(rrProblem);
                    User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
                    this.dpcoiOrderService.addDpcoiOrder(rrProblem, user);
                }
            }else {
                DpcoiOrder dpcoiOrder = this.dpcoiOrderService.quereyDpcoiOrderOfTaskOrderNo(changePoint);
                if(dpcoiOrder == null){
                    map.put("success", false);
                    map.put("message", "变化点管理不存在！");
                }else {
                    //同步数据
                    DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
                    dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
                    List<Map<String, Object>> mapList = this.dpcoiWoOrderService.queryDpcoiWoOrderList(dpcoiWoOrderQuery);
                    String pfmea = "";
                    String cp = "";
                    String standardBook = "";
                    for(Map<String, Object> objectMap : mapList){
                        Integer dpcoiWoOrderType = (Integer)objectMap.get("dpcoiWoOrderType");
                        Integer dpcoiWoOrderState = (Integer)objectMap.get("dpcoiWoOrderState");
                        if(1==dpcoiWoOrderType){
                            if(4==dpcoiWoOrderState){
                                Date pfmeaCompleteDate = (Date)objectMap.get("pfmeaCompleteDate");
                                if(pfmeaCompleteDate != null){
                                    pfmea = formatter.format(pfmeaCompleteDate);
                                }
                            }else if(7==dpcoiWoOrderState){
                                pfmea = "N/A";
                            }
                        }else if(2==dpcoiWoOrderType){
                            if(4==dpcoiWoOrderState){
                                Date cpCompleteDate = (Date)objectMap.get("cpCompleteDate");
                                if(cpCompleteDate != null){
                                    cp = formatter.format(cpCompleteDate);
                                }
                            }else if(7==dpcoiWoOrderState){
                                cp = "N/A";
                            }
                        }else if(3==dpcoiWoOrderType){
                            if(4==dpcoiWoOrderState){
                                Date standardBookCompleteDate = (Date)objectMap.get("standardBookCompleteDate");
                                if(standardBookCompleteDate != null){
                                    standardBook = formatter.format(standardBookCompleteDate);
                                }
                            }else if(7==dpcoiWoOrderState){
                                standardBook = "N/A";
                            }
                        }
                    }
                    rrProblem.setPfmea(pfmea);
                    rrProblem.setCp(cp);
                    rrProblem.setStandardBook(standardBook);
                    this.rRProblemService.addRRProblem(rrProblem);
                    dpcoiOrder.setRrProblemId(rrProblem.getId());
                    this.dpcoiOrderService.updateDpcoiOrder(dpcoiOrder);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 更新RR问题点选项
     * @param response 参数
     * @param rrProblem RR问题点选项
     */
    @RequestMapping("updateRRProblem.do")
    public void updateRRProblem(HttpServletRequest request, HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            rrProblem = this.validUploadFile(rrProblem);
            validSpeedOfProgress(rrProblem);
            RRProblem oldRRProblem = new RRProblem();
            oldRRProblem.setId(rrProblem.getId());
            oldRRProblem = this.rRProblemService.queryRRProblem(oldRRProblem);
            String oldChangePoint = oldRRProblem.getChangePoint();
            String oldTrackingLevel = oldRRProblem.getTrackingLevel();
            String oldSpeedOfProgress = rrProblem.getSpeedOfProgress();
            String changePoint = rrProblem.getChangePoint();
            if(changePoint != null){
                changePoint = changePoint.toUpperCase();
            }
            rrProblem.setChangePoint(changePoint);
            if(changePoint == null || "".equals(changePoint)){
                if(!(oldChangePoint == null || "".equals(oldChangePoint))){
                    throw new Exception("变化点管理已有值，不可修改为空！");
                }
            }else if("N/A".equals(changePoint)){
                if(oldChangePoint == null || "".equals(oldChangePoint)){
                    User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
                    this.dpcoiOrderService.addDpcoiOrder(rrProblem, user);
                }else {
                    if(!("N/A".equals(oldChangePoint))){
                        throw new Exception("变化点管理原有值不是N/A，不能修改为N/A！");
                    }
                }
            }else {
                if(oldChangePoint == null || "".equals(oldChangePoint)){
                    DpcoiOrder dpcoiOrder = this.dpcoiOrderService.quereyDpcoiOrderOfTaskOrderNo(changePoint);
                    if(dpcoiOrder == null){
                        throw new Exception("变化点管理不存在！");
                    }else {
                        //同步数据
                        DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
                        dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
                        List<Map<String, Object>> mapList = this.dpcoiWoOrderService.queryDpcoiWoOrderList(dpcoiWoOrderQuery);
                        String pfmea = "";
                        String cp = "";
                        String standardBook = "";
                        for (Map<String, Object> objectMap : mapList) {
                            Integer dpcoiWoOrderType = (Integer) objectMap.get("dpcoiWoOrderType");
                            Integer dpcoiWoOrderState = (Integer) objectMap.get("dpcoiWoOrderState");
                            if (1==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date pfmeaCompleteDate = (Date) objectMap.get("pfmeaCompleteDate");
                                    if (pfmeaCompleteDate != null) {
                                        pfmea = formatter.format(pfmeaCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    pfmea = "N/A";
                                }
                            } else if (2==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date cpCompleteDate = (Date) objectMap.get("cpCompleteDate");
                                    if (cpCompleteDate != null) {
                                        cp = formatter.format(cpCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    cp = "N/A";
                                }
                            } else if (3==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date standardBookCompleteDate = (Date) objectMap.get("standardBookCompleteDate");
                                    if (standardBookCompleteDate != null) {
                                        standardBook = formatter.format(standardBookCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    standardBook = "N/A";
                                }
                            }
                        }
                        rrProblem.setPfmea(pfmea);
                        rrProblem.setCp(cp);
                        rrProblem.setStandardBook(standardBook);
                        dpcoiOrder.setRrProblemId(rrProblem.getId());
                        this.dpcoiOrderService.updateDpcoiOrder(dpcoiOrder);
                    }
                }else if(!(oldChangePoint.equals(changePoint))){
                    throw new Exception("变化点管理已有值，不可修改！");
                }
            }
            this.rRProblemService.updateSpeedOfProgress(rrProblem);
            this.rRProblemService.updateTrackingLevel(rrProblem);
            if(oldTrackingLevel == null || "".equals(oldTrackingLevel) || "V".equals(oldTrackingLevel)){
                String trackingLevel = rrProblem.getTrackingLevel();
                String speedOfProgress = rrProblem.getSpeedOfProgress();
                if("I".equals(trackingLevel) || "II".equals(trackingLevel) || "III".equals(trackingLevel) || "IV".equals(trackingLevel)) {
                    String persionLiable = rrProblem.getPersionLiable();
                    String[] persionLiableArray = persionLiable.split(",");
                    for (int i = 0; i < persionLiableArray.length; i++) {
                        RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                        rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                        rrDelayStatistics.setDelayDate(new Date());
                        rrDelayStatistics.setDelayType(2);
                        rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                        rrDelayStatistics.setRrProblemId(rrProblem.getId());
                        rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                        rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                        rrDelayStatistics.setTrackingLevel(rrProblem.getTrackingLevel());
                        this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                    }
                }
            }
            this.rRProblemService.updateRRProblem(rrProblem);
            String trackingLevel = rrProblem.getTrackingLevel();
            if(!"V".equals(trackingLevel)){
                map.put("message", "已超期！");
            }else {
                map.put("message", "");
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 更新并申请延期RR问题点选项
     * @param response 参数
     * @param rrProblem RR问题点选项
     */
    @RequestMapping("updateDelayRRProblem.do")
    public void updateDelayRRProblem(HttpServletRequest request, HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            rrProblem.setDelayApplication(1);
            rrProblem = this.validUploadFile(rrProblem);
            validSpeedOfProgress(rrProblem);
            RRProblem oldRRProblem = new RRProblem();
            oldRRProblem.setId(rrProblem.getId());
            oldRRProblem = this.rRProblemService.queryRRProblem(oldRRProblem);
            String oldChangePoint = oldRRProblem.getChangePoint();
            String oldTrackingLevel = oldRRProblem.getTrackingLevel();
            String oldSpeedOfProgress = rrProblem.getSpeedOfProgress();
            String changePoint = rrProblem.getChangePoint();
            changePoint = changePoint.toUpperCase();
            rrProblem.setChangePoint(changePoint);
            if(changePoint == null || "".equals(changePoint)){
                if(!(oldChangePoint == null || "".equals(oldChangePoint))){
                    throw new Exception("变化点管理已有值，不可修改为空！");
                }
            }else if("N/A".equals(changePoint)){
                if(oldChangePoint == null || "".equals(oldChangePoint)){
                    User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
                    this.dpcoiOrderService.addDpcoiOrder(rrProblem, user);
                }else {
                    if(!("N/A".equals(oldChangePoint))){
                        throw new Exception("变化点管理原有值不是N/A，不能修改为N/A！");
                    }
                }
            }else {
                if(oldChangePoint == null || "".equals(oldChangePoint)){
                    DpcoiOrder dpcoiOrder = this.dpcoiOrderService.quereyDpcoiOrderOfTaskOrderNo(changePoint);
                    if(dpcoiOrder == null){
                        throw new Exception("变化点管理不存在！");
                    }else {
                        //同步数据
                        DpcoiWoOrderQuery dpcoiWoOrderQuery = new DpcoiWoOrderQuery();
                        dpcoiWoOrderQuery.setDpcoiOrderId(dpcoiOrder.getDpcoiOrderId());
                        List<Map<String, Object>> mapList = this.dpcoiWoOrderService.queryDpcoiWoOrderList(dpcoiWoOrderQuery);
                        String pfmea = "";
                        String cp = "";
                        String standardBook = "";
                        for (Map<String, Object> objectMap : mapList) {
                            Integer dpcoiWoOrderType = (Integer) objectMap.get("dpcoiWoOrderType");
                            Integer dpcoiWoOrderState = (Integer) objectMap.get("dpcoiWoOrderState");
                            if (1==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date pfmeaCompleteDate = (Date) objectMap.get("pfmeaCompleteDate");
                                    if (pfmeaCompleteDate != null) {
                                        pfmea = formatter.format(pfmeaCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    pfmea = "N/A";
                                }
                            } else if (2==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date cpCompleteDate = (Date) objectMap.get("cpCompleteDate");
                                    if (cpCompleteDate != null) {
                                        cp = formatter.format(cpCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    cp = "N/A";
                                }
                            } else if (3==dpcoiWoOrderType) {
                                if (4==dpcoiWoOrderState) {
                                    Date standardBookCompleteDate = (Date) objectMap.get("standardBookCompleteDate");
                                    if (standardBookCompleteDate != null) {
                                        standardBook = formatter.format(standardBookCompleteDate);
                                    }
                                } else if (7==dpcoiWoOrderState) {
                                    standardBook = "N/A";
                                }
                            }
                        }
                        rrProblem.setPfmea(pfmea);
                        rrProblem.setCp(cp);
                        rrProblem.setStandardBook(standardBook);
                        dpcoiOrder.setRrProblemId(rrProblem.getId());
                        this.dpcoiOrderService.updateDpcoiOrder(dpcoiOrder);
                    }
                }else if(!(oldChangePoint.equals(changePoint))){
                    throw new Exception("变化点管理已有值，不可修改！");
                }
            }
            this.rRProblemService.updateSpeedOfProgress(rrProblem);
            this.rRProblemService.updateTrackingLevel(rrProblem);
            if(oldTrackingLevel == null || "".equals(oldTrackingLevel) || "V".equals(oldTrackingLevel)){
                String speedOfProgress = rrProblem.getSpeedOfProgress();
                String trackingLevel = rrProblem.getTrackingLevel();
                if("I".equals(trackingLevel) || "II".equals(trackingLevel) || "III".equals(trackingLevel) || "IV".equals(trackingLevel)) {
                    String persionLiable = rrProblem.getPersionLiable();
                    String[] persionLiableArray = persionLiable.split(",");
                    for (int i = 0; i < persionLiableArray.length; i++) {
                        RRDelayStatistics rrDelayStatistics = new RRDelayStatistics();
                        rrDelayStatistics.setSpeedOfProgress(speedOfProgress);
                        rrDelayStatistics.setDelayDate(new Date());
                        rrDelayStatistics.setDelayType(2);
                        rrDelayStatistics.setPersionLiable(persionLiableArray[i]);
                        rrDelayStatistics.setRrProblemId(rrProblem.getId());
                        rrDelayStatistics.setProblemStatus(rrProblem.getProblemStatus());
                        rrDelayStatistics.setProblemProgress(rrProblem.getProblemProgress());
                        rrDelayStatistics.setTrackingLevel(rrProblem.getTrackingLevel());
                        this.rRDelayStatisticsService.addRRDelayStatistics(rrDelayStatistics);
                    }
                }
            }
            this.rRProblemService.updateRRProblem(rrProblem);
            this.rRProblemService.addSendMinisterEmail(rrProblem);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 查询责任人列表
     * @param response 参数
     */
    @RequestMapping("/getPersionLiableList.do")
    public void getPersionLiableList(HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> persionLiableList = this.rRProblemService.queryPersionLiableList();
            map.put("persionLiableList", persionLiableList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 关闭RR问题点
     * @param response 参数
     * @param rrProblem 参数
     */
    @RequestMapping("closeRRProblem.do")
    public void closeRRProblem(HttpServletRequest request, HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            String problemProgress = rrProblem.getProblemProgress();
            if("5/5".equals(problemProgress)){
                Integer state = rrProblem.getState();
                if(state == 2){
                    throw new Exception("RR问题点已关闭，不能再次关闭！");
                }else if(state == 3){
                    throw new Exception("RR问题点已作废，不能关闭！");
                }else {
                    RRProblem newRRProblem = new RRProblem();
                    newRRProblem.setId(rrProblem.getId());
                    newRRProblem.setState(2);
                    newRRProblem.setCloseConfirm("已关闭");
                    newRRProblem.setCloseConfirmId(user.getUserId());
                    newRRProblem.setCloseConfirmTime(new Date());
                    newRRProblem.setSpeedOfProgress("close");
                    newRRProblem.setTrackingLevel("V");
                    this.rRProblemService.updateRRProblem(newRRProblem);
                }
            }else {
                throw new Exception("问题进展不是5/5！");
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 作废RR问题点
     * @param response 参数
     * @param rrProblem 参数
     */
    @RequestMapping("toVoidRRProblem.do")
    public void toVoidRRProblem(HttpServletRequest request, HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            Integer state = rrProblem.getState();
            if(state == 2){
                throw new Exception("RR问题点已关闭，不能作废！");
            }else if(state == 3){
                throw new Exception("RR问题点已作废，不能再次作废！");
            }else {
                RRProblem newRRProblem = new RRProblem();
                newRRProblem.setId(rrProblem.getId());
                newRRProblem.setState(3);
                newRRProblem.setCloseConfirm("已作废");
                newRRProblem.setSpeedOfProgress("deleted");
                this.rRProblemService.updateRRProblem(newRRProblem);
                DpcoiOrder dpcoiOrder = this.dpcoiOrderService.queryDpcoiOrderByRRProblem(rrProblem.getId());
                if(dpcoiOrder != null){
                    this.dpcoiOrderService.editDpcoiOrderToVoid(dpcoiOrder, user);
                }
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * RR问题点延期
     * @param response 参数
     * @param rrProblem 参数
     */
    @RequestMapping("delayRRProblem.do")
    public void delayRRProblem(HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            Integer state = rrProblem.getState();
            if(state == 2){
                throw new Exception("RR问题点已关闭，不能延期！");
            }else if(state == 3){
                throw new Exception("RR问题点已作废，不能延期！");
            }else{
                rrProblem.setCloseConfirm("延期");
                rrProblem.setIsDelay(1);
                this.rRProblemService.updateTrackingLevel(rrProblem);
                this.rRProblemService.updateRRProblem(rrProblem);
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * RR问题点隐藏
     * @param response 参数
     * @param rrProblem 参数
     */
    @RequestMapping("hideRRProblem.do")
    public void hideRRProblem(HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            Integer isHide = rrProblem.getIsHide();
            if(isHide == 1){
                throw new Exception("RR问题点已隐藏，不能再次隐藏！");
            }else{
                rrProblem.setIsHide(1);
                this.rRProblemService.updateRRProblem(rrProblem);
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * RR问题点显示
     * @param response 参数
     * @param rrProblem 参数
     */
    @RequestMapping("cancleHideRRProblem.do")
    public void cancleHideRRProblem(HttpServletResponse response, RRProblem rrProblem){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rrProblem = this.rRProblemService.queryRRProblem(rrProblem);
            Integer isHide = rrProblem.getIsHide();
            if(isHide == 0){
                throw new Exception("RR问题点已显示，不能再次显示！");
            }else{
                rrProblem.setIsHide(0);
                this.rRProblemService.updateRRProblem(rrProblem);
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 验证5/5
     * @param rrProblem rr问题点
     * @throws Exception 异常
     */
    private void validSpeedOfProgress(RRProblem rrProblem) throws Exception{
        String problemProgress = rrProblem.getProblemProgress();
        if("5/5".equals(problemProgress)){
//                String trackingLevel = rrProblem.getTrackingLevel();
//                if(trackingLevel == null || "".equals(trackingLevel)){
//                    throw new Exception("追踪等级不能为空！");
//                }
            String temporary = rrProblem.getTemporary();
            if(temporary == null || "".equals(temporary)){
                throw new Exception("临时对策不能为空！");
            }
            String rootCause = rrProblem.getRootCause();
            if(rootCause == null || "".equals(rootCause)){
                throw new Exception("根本原因不能为空！");
            }
            String permanentGame = rrProblem.getPermanentGame();
            if(permanentGame == null || "".equals(permanentGame)){
                throw new Exception("永久对策不能为空！");
            }
            String effectVerification = rrProblem.getEffectVerification();
            if(effectVerification == null || "".equals(effectVerification)){
                throw new Exception("效果校验不能为空！");
            }
            String serialNumber = rrProblem.getSerialNumber();
            if(serialNumber == null || "".equals(serialNumber)){
                throw new Exception("品情联编号不能为空！");
            }
            String qualityWarningCardNumber = rrProblem.getQualityWarningCardNumber();
            if(qualityWarningCardNumber == null || "".equals(qualityWarningCardNumber)){
                throw new Exception("质量警示卡编号不能为空！");
            }
            String productScale = rrProblem.getProductScale();
            if(productScale == null || "".equals(productScale)){
                throw new Exception("品推表编号不能为空！");
            }
            String pfmea = rrProblem.getPfmea();
            if(pfmea == null || "".equals(pfmea)){
                throw new Exception("PFMEA不能为空！");
            }
            String cp = rrProblem.getCp();
            if(cp == null || "".equals(cp)){
                throw new Exception("CP不能为空！");
            }
            String standardBook = rrProblem.getStandardBook();
            if(standardBook == null || "".equals(standardBook)){
                throw new Exception("WI不能为空！");
            }
            String equipmentChecklist = rrProblem.getEquipmentChecklist();
            if(equipmentChecklist == null || "".equals(equipmentChecklist)){
                throw new Exception("设备点检表不能为空！");
            }
            String alwaysList = rrProblem.getAlwaysList();
            if(alwaysList == null || "".equals(alwaysList)){
                throw new Exception("始终件表不能为空！");
            }
            String inspectionReferenceBook = rrProblem.getInspectionReferenceBook();
            if(inspectionReferenceBook == null || "".equals(inspectionReferenceBook)){
                throw new Exception("检查基准书不能为空！");
            }
            String inspectionBook = rrProblem.getInspectionBook();
            if(inspectionBook == null || "".equals(inspectionBook)){
                throw new Exception("检查手顺书不能为空！");
            }
            String education = rrProblem.getEducation();
            if(education == null || "".equals(education)){
                throw new Exception("教育议事录不能为空！");
            }
//            String expandTrace = rrProblem.getExpandTrace();
//            if(expandTrace == null || "".equals(expandTrace)){
//                throw new Exception("展开追踪是否完成不能为空！");
//            }
//            String artificial = rrProblem.getArtificial();
//            if(artificial == null || "".equals(artificial)){
//                throw new Exception("人工不能为空！");
//            }
//            String materiel = rrProblem.getMateriel();
//            if(materiel == null || "".equals(materiel)){
//                throw new Exception("物料等级不能为空！");
//            }
            String dpcoi4M = rrProblem.getDpcoi4M();
            if(dpcoi4M == null || "".equals(dpcoi4M)){
                throw new Exception("4M不能为空！");
            }
            String analyticReport = rrProblem.getAnalyticReport();
            if(analyticReport == null || "".equals(analyticReport)){
                throw new Exception("解析报告不能为空！");
            }
            String layeredAudit = rrProblem.getLayeredAudit();
            if(layeredAudit == null || "".equals(layeredAudit)){
                throw new Exception("分层审核不能为空！");
            }
            String checkResult = rrProblem.getCheckResult();
            if(checkResult == null || "".equals(checkResult)){
                throw new Exception("验岗结果不能为空！");
            }
//            String naPending = rrProblem.getNaPending();
//            if(naPending == null || "".equals(naPending)){
//                throw new Exception("NA待定不能为空！");
//            }
//            String otherInformation = rrProblem.getOtherInformation();
//            if(otherInformation == null || "".equals(otherInformation)){
//                throw new Exception("其他资料不能为空！");
//            }
        }
    }

    /**
     * 上传文件
     * @param request 参数
     * @param response 参数
     * @param rrProblemId RR问题点ID
     * @param fileAttr 文件类型
     * @param file 文件
     */
    @RequestMapping(value="uploadFile.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("rrProblemId") Integer rrProblemId, @RequestParam("fileAttr") String fileAttr, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                FileUpload fileUpload = this.rRProblemService.addUploadFile(rrProblemId, fileAttr, file, path, user);
                map.put("success", true);
                map.put("fileId", fileUpload.getFileId());
                map.put("fileDate", formatter.format(new Date()));
                map.put("message", "上传成功");
            }else {
                map.put("success", false);
                map.put("message", "上传失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 验证上传文件
     * @param rrProblem RR问题点数据
     */
    private RRProblem validUploadFile(RRProblem rrProblem){
        String serialNumber = rrProblem.getSerialNumber();
        if(serialNumber == null || "".equals(serialNumber) || "N/A".equals(serialNumber.toUpperCase())){
            rrProblem.setSerialNumberFileId(0);
        }
        String qualityWarningCardNumber = rrProblem.getQualityWarningCardNumber();
        if(qualityWarningCardNumber == null || "".equals(qualityWarningCardNumber) || "N/A".equals(qualityWarningCardNumber.toUpperCase())){
            rrProblem.setQualityWarningCardNumberFileId(0);
        }
        String productScale = rrProblem.getProductScale();
        if(productScale == null || "".equals(productScale) || "N/A".equals(productScale.toUpperCase())){
            rrProblem.setProductScaleFileId(0);
        }
        String equipmentChecklist = rrProblem.getEquipmentChecklist();
        if(equipmentChecklist == null || "".equals(equipmentChecklist) || "N/A".equals(equipmentChecklist.toUpperCase())){
            rrProblem.setEquipmentChecklistFileId(0);
        }
        String inspectionReferenceBook =  rrProblem.getInspectionReferenceBook();
        if(inspectionReferenceBook == null || "".equals(inspectionReferenceBook) || "N/A".equals(inspectionReferenceBook.toUpperCase())){
            rrProblem.setInspectionReferenceBookFileId(0);
        }
        String inspectionBook = rrProblem.getInspectionBook();
        if(inspectionBook == null || "".equals(inspectionBook) || "N/A".equals(inspectionBook.toUpperCase())){
            rrProblem.setInspectionBookFileId(0);
        }
        String education = rrProblem.getEducation();
        if(education == null || "".equals(education) || "N/A".equals(education.toUpperCase())){
            rrProblem.setEducationFileId(0);
        }
        String analyticReport = rrProblem.getAnalyticReport();
        if(analyticReport == null || "".equals(analyticReport) || "N/A".equals(analyticReport.toUpperCase())){
            rrProblem.setAnalyticReportFileId(0);
        }
        String layeredAudit = rrProblem.getLayeredAudit();
        if(layeredAudit == null || "".equals(layeredAudit) || "N/A".equals(layeredAudit.toUpperCase())){
            rrProblem.setLayeredAuditFileId(0);
        }
        String checkResult = rrProblem.getCheckResult();
        if(checkResult == null || "".equals(checkResult) || "N/A".equals(checkResult.toUpperCase())){
            rrProblem.setCheckResultFileId(0);
        }
        String naPending = rrProblem.getNaPending();
        if(naPending == null || "".equals(naPending) || "N/A".equals(naPending.toUpperCase())){
            rrProblem.setNaPendingFileId(0);
        }
        String otherInformation = rrProblem.getOtherInformation();
        if(otherInformation == null || "".equals(otherInformation) || "N/A".equals(otherInformation.toUpperCase())){
            rrProblem.setOtherInformationFileId(0);
        }
        String containmentWorksheet = rrProblem.getContainmentWorksheet();
        if(containmentWorksheet == null || "".equals(containmentWorksheet) || "N/A".equals(containmentWorksheet.toUpperCase())){
            rrProblem.setContainmentWorksheetFileId(0);
        }
        return rrProblem;
    }

    @RequestMapping("/getFourDate.do")
    public void getFourDate(HttpServletResponse response, String happenDate){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map = this.rRProblemService.getFourDate(happenDate);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
