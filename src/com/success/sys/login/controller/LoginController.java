package com.success.sys.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.common.Constant;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.sys.user.service.UserService;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.ServletAPIUtil;

/**
 * 系统登录
 * @author liang.zhifu
 *
 */
@Controller
public class LoginController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/login.do")
	public String login(){
		return "login/login";
	}
	
	/**
	 * 登录认证
	 * @return
	 */
	@RequestMapping("/loginAuthenticate.do")
	public void loginAuthenticate(HttpServletRequest request, HttpServletResponse response){
		JSONObject jresult = new JSONObject();
		String userCode = ServletAPIUtil.getStringParameter("userName", request);
		String password = ServletAPIUtil.getStringParameter("password", request);
		String resultStr = "";
		try {
			resultStr = this.userService.loginSystem(userCode, password);
			if (resultStr.equals("success")) {
				UserQuery query = new UserQuery();
				query.setUserCode(userCode);
				User user = this.userService.queryUser(query);
				request.getSession().setAttribute(Constant.STAFF_KEY, user);
			}
		} catch (Exception e1) {
			resultStr = "error";
			e1.printStackTrace();
		}finally {
			try{jresult.put("loginState", resultStr);}catch(Exception e){};
			AjaxUtil.ajaxResponse(response, jresult.toString(), AjaxUtil.RESPONCE_TYPE_TEXT);
		}
	}
	
	@RequestMapping("/loginOut.do")
	public void loginOut(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute(Constant.STAFF_KEY);
	}
	
}
