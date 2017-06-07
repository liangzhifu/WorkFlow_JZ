package com.dpcoi.woOrder.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/5.
 */

import com.dpcoi.woOrder.domain.DpcoiWoOrderFile;
import com.dpcoi.woOrder.query.DpcoiWoOrderFileQuery;
import com.dpcoi.woOrder.query.DpcoiWoOrderQuery;
import com.dpcoi.woOrder.service.DpcoiWoOrderFileService;
import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-05
 **/
@Controller
@RequestMapping("/dpcoiWoOrderFile")
public class DpcoiWoOrderFileController {

    @Resource(name = "dpcoiWoOrderFileService")
    private DpcoiWoOrderFileService dpcoiWoOrderFileService;

    @RequestMapping("/getDpcoiWoOrderFileListDlg.do")
    public String getDpcoiWoOrderFileListDlg(){
        return "dpcoi/dpcoiWoOrderFileList";
    }

    /**
     *获取文件列表--分页
     * @param response 参数
     * @param dpcoiWoOrderFileQuery 查询条件
     */
    @RequestMapping("getDpcoiWoOrderFileListPage.do")
    public void getDpcoiWoOrderFileListPage(HttpServletResponse response, DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiWoOrderFileList = this.dpcoiWoOrderFileService.queryDpcoiWoOrderFileListPage(dpcoiWoOrderFileQuery);
            Integer dpcoiWoOrderFileCount = this.dpcoiWoOrderFileService.queryDpcoiWoOrderFileCount(dpcoiWoOrderFileQuery);
            Integer pageCount = dpcoiWoOrderFileCount / dpcoiWoOrderFileQuery.getSize() + (dpcoiWoOrderFileCount % dpcoiWoOrderFileQuery.getSize() > 0 ? 1 : 0);
            map.put("dpcoiWoOrderFileList", dpcoiWoOrderFileList);
            map.put("dpcoiWoOrderFileCount", dpcoiWoOrderFileCount);
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
     * 上传dpcoi工单文件
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrderId 工单ID
     * @param file 文件
     */
    @RequestMapping(value="uploadDpcoiWoOrderFile.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void uploadDpcoiWoOrderFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("dpcoiWoOrderId") Integer dpcoiWoOrderId, @RequestParam("uploadFile") MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            String path = request.getSession().getServletContext().getRealPath("/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            if(!file.isEmpty()){
                this.dpcoiWoOrderFileService.editWoOrderUploadFile(dpcoiWoOrderId, file, path, user);
                map.put("success", true);
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
     * 获取dpcoi工单的文件列表
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrderFileQuery 查询条件
     */
    @RequestMapping("getDpcoiWoOrderFileList.do")
    public void getDpcoiWoOrderFileList(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiWoOrderFileList = this.dpcoiWoOrderFileService.queryDpcoiWoOrderFileList(dpcoiWoOrderFileQuery);
            map.put("dpcoiWoOrderFileList", dpcoiWoOrderFileList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取dpcoi定单的审核通过的文件列表
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrderFileQuery 查询条件
     */
    @RequestMapping("getDpcoiOrderFileList.do")
    public void getDpcoiOrderFileList(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrderFileQuery dpcoiWoOrderFileQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> dpcoiOrderFileList = this.dpcoiWoOrderFileService.queryDpcoiOrderFileList(dpcoiWoOrderFileQuery);
            map.put("dpcoiOrderFileList", dpcoiOrderFileList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 删除dpcoi工单文件
     * @param request 参数
     * @param response 参数
     * @param dpcoiWoOrderFile 工单文件ID
     */
    @RequestMapping("deleteDpcoiWoOrderFile.do")
    public void deleteWoOrderFile(HttpServletRequest request, HttpServletResponse response, DpcoiWoOrderFile dpcoiWoOrderFile){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            User user = (User)request.getSession().getAttribute(Constant.STAFF_KEY);
            dpcoiWoOrderFile.setUpdateDate(new Date());
            dpcoiWoOrderFile.setUpdateBy(user.getUserId());
            dpcoiWoOrderFile.setDelFlag("1");
            this.dpcoiWoOrderFileService.updateDpcoiWoOrderFile(dpcoiWoOrderFile);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
