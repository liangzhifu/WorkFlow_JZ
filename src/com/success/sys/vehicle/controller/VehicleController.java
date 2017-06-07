package com.success.sys.vehicle.controller;/**
 * Created by liangzhifu
 * DATE:2017/5/30.
 */

import com.success.sys.vehicle.domain.Vehicle;
import com.success.sys.vehicle.query.VehicleQuery;
import com.success.sys.vehicle.service.VehicleService;
import com.success.web.framework.util.AjaxUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lzf
 * @create 2017-05-30
 **/
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Resource(name = "vehicleService")
    private VehicleService vehicleService;

    @RequestMapping("/getVehicleListDlg.do")
    public String getVehicleListDlg(HttpServletRequest request, Map<String, Object> model) throws Exception{
        return "system/vehicleList";
    }

    /**
     * 获取车种列表--分页
     * @param response 参数
     * @param vehicleQuery 查询条件
     */
    @RequestMapping("getVehicleListPage.do")
    public void getVehicleListPage(HttpServletResponse response, VehicleQuery vehicleQuery){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<Map<String, Object>> vehicleList = this.vehicleService.queryVehicleListPage(vehicleQuery);
            Integer vehicleCount = this.vehicleService.queryVehicleCount(vehicleQuery);
            Integer pageCount = vehicleCount / vehicleQuery.getSize() + (vehicleCount % vehicleQuery.getSize() > 0 ? 1 : 0);
            map.put("vehicleList", vehicleList);
            map.put("vehicleCount", vehicleCount);
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
     * 新增车种
     * @param request 参数
     * @param response 参数
     * @param vehicle 车种
     */
    @RequestMapping("/addVehicle.do")
    public String addDpcoiOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Vehicle vehicle){
       this.vehicleService.addVehicle(vehicle);
        return "system/vehicleList";
    }

    /**
     * 删除车种
     * @param request 参数
     * @param response 参数
     * @param vehicle 车种
     */
    @RequestMapping("/deleteVehicle.do")
    public void deleteVehicle(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle){
       this.vehicleService.deleteVehicle(vehicle);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        AjaxUtil.ajaxResponse(response, new JSONObject(map).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
    }

    /**
     * 获取车种列表
     * @param response 参数
     * @param vehicleQuery 查询条件
     */
    @RequestMapping("getVehicleList.do")
    public void getVehicleList(HttpServletResponse response, VehicleQuery vehicleQuery){
        try{
            List<Map<String, Object>> vehicleList = this.vehicleService.queryVehicleList(vehicleQuery);
            AjaxUtil.ajaxResponse(response,  new JSONArray(vehicleList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
