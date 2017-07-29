package com.dpcoi.config.controller;/**
 * Created by liangzhifu
 * DATE:2017/6/17.
 */

import com.dpcoi.config.domain.DpcoiConfigCode;
import com.dpcoi.config.service.DpcoiConfigCodeService;
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
 * @create 2017-06-17
 **/
@Controller
@RequestMapping("/dpcoiConfigCode")
public class DpcoiConfigCodeController {

    @Resource(name = "dpcoiConfigCodeService")
    private DpcoiConfigCodeService dpcoiConfigCodeService;

    /**
     * 查询Dpcoi的所有（可配置）下拉菜单类型
     * @param response 参数
     */
    @RequestMapping("/getDpcoiConfigCodeList.do")
    public void getDpcoiConfigCodeList(HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<DpcoiConfigCode> dpcoiConfigCodeList = this.dpcoiConfigCodeService.queryDpcoiConfigCodeList();
            map.put("dpcoiConfigCodeList", dpcoiConfigCodeList);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }
}
