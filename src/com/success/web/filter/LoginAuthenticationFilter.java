package com.success.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.success.common.Constant;

/**
 * 登录过滤器，用户必须先登录后，才可访问资源
 * @author liang.zhifu
 *
 */
public class LoginAuthenticationFilter implements Filter {

	//排除的URL中，第一个为登录页面。
	private List<String> excludeURL = new ArrayList<String>();
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		//先排除不需过滤的URL集合
		if(this.excludeURL.contains(httpRequest.getRequestURI())){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		// 在Session中查找用户是否登录
		if (session.getAttribute(Constant.STAFF_KEY) != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;//执行doFilter方法后，必须执行 return语句，否则会产生多次请求。
		}
		httpResponse.sendRedirect(excludeURL.get(0));
		return;
	}

	@Override
	public void init(FilterConfig filterCfg) throws ServletException {
		// TODO Auto-generated method stub
		String contextPath = filterCfg.getServletContext().getContextPath();
		excludeURL.add(contextPath + "/login.do");
		excludeURL.add(contextPath + "/loginAuthenticate.do");
		excludeURL.add(contextPath + "/loginAuthenticateFromOA.do");
		excludeURL.add(contextPath + "/taskScreenShow/queryTaskScreenShowList.do");
		excludeURL.add(contextPath + "/taskScreenShow/queryTaskScreenCount.do");
		excludeURL.add(contextPath + "/jsp/screen/screenShow.jsp");
	}

}
