package com.success.sys.config.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.config.domain.SysConfig;
import com.success.sys.config.query.SysConfigQuery;
import com.success.sys.config.service.SysConfigService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;

@Controller
@RequestMapping("/sysConfig")
public class SysConfigCotroller {

	@Resource(name = "sysConfigService")
	private SysConfigService sysConfigService;
	
	@RequestMapping("/querySysConfigJson.do")
	public void queryPageUser(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		String configCode = ServletAPIUtil.getStringParameter("configCode", request, "");
		try{
			SysConfigQuery query = new SysConfigQuery();
			query.setConfigCode(configCode);
			List<SysConfig> sysConfigList = this.sysConfigService.querySysConfigList(query);
			AjaxUtil.ajaxResponse(response, new JSONArray(sysConfigList).toString(), AjaxUtil.RESPONCE_TYPE_JSON);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
