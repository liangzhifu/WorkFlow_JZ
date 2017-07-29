package com.dpcoi.woOrder.controller;/**
 * Created by liangzhifu
 * DATE:2017/7/4.
 */

import com.dpcoi.woOrder.query.RRProblemWoOrderFileQuery;
import com.dpcoi.woOrder.service.RRProblemWoOrderFileService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-07-04
 **/
@Controller
@RequestMapping("/rrProblemWoOrderFile")
public class RRProblemWoOrderFileController {

    @Resource(name = "rRProblemWoOrderFileService")
    private RRProblemWoOrderFileService rRProblemWoOrderFileService;

    @RequestMapping("/getRRProblemWoOrderFileListDlg.do")
    public String getRRProblemWoOrderFileListDlg(){
        return "dpcoi/rrProblemWoOrderFileList";
    }

    /**
     * 获取RR问题点文件列表--分页
     * @param response 参数
     * @param rrProblemWoOrderFileQuery 查询条件
     */
    @RequestMapping("/getRRProblemWoOrderFileListPage.do")
    public void getRRProblemWoOrderFileListPage(HttpServletResponse response, RRProblemWoOrderFileQuery rrProblemWoOrderFileQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> rrProblemWoOrderFileList = this.rRProblemWoOrderFileService.queryRRProblemWoOrderFileListPage(rrProblemWoOrderFileQuery);
            Integer rrProblemWoOrderFileCount = this.rRProblemWoOrderFileService.queryRRProblemWoOrderFileCount(rrProblemWoOrderFileQuery);
            Integer pageCount = rrProblemWoOrderFileCount / rrProblemWoOrderFileQuery.getSize() + (rrProblemWoOrderFileCount % rrProblemWoOrderFileQuery.getSize() > 0 ? 1 : 0);
            map.put("rrProblemWoOrderFileList", rrProblemWoOrderFileList);
            map.put("dpcoiWoOrderFileCount", rrProblemWoOrderFileCount);
            map.put("pageCount", pageCount);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
