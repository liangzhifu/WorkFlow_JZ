package com.success.web.framework.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 获取页面传参
 * @author liang.zhifu
 *
 */
public class ServletAPIUtil {

	/**
	 * 获取整数型参数
	 * @param paramName
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static Integer getIntegerParameter(String paramName, HttpServletRequest request, Integer defaultValue) {
		String paramValue = request.getParameter(paramName);
		if(NumberUtils.isNumber(paramValue)){
			return NumberUtils.toInt(paramValue);
		}else{
			return defaultValue;
		}
	}
	
	public static Integer getIntegerParameter(String paramName, HttpServletRequest request) {
		return getIntegerParameter(paramName, request,  null);
	}
	
	/**
	 * 获取长整数型参数
	 * @param paramName
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static Long getLongParameter(String paramName, HttpServletRequest request, Long defaultValue) {
		String paramValue = request.getParameter(paramName);
		if(NumberUtils.isNumber(paramValue)){
			return NumberUtils.toLong(paramValue);
		}else{
			return defaultValue;
		}
	}
	
	public static Long getLongParameter(String paramName, HttpServletRequest request) {
		return getLongParameter(paramName, request,  new Long(0));
	}
	
	/**
	 * 获得字符串参数
	 * @param paramName
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static String getStringParameter(String paramName,HttpServletRequest request, String defaultValue ){
		String paramValue = request.getParameter(paramName);
		return StringUtils.isNotBlank(paramValue) ? paramValue:defaultValue;
	}
	
	public static String getStringParameter(String paramName, HttpServletRequest request) {
		return getStringParameter(paramName, request,  "");
	}
	
	public static Boolean getBooleanParameter(String paramName, HttpServletRequest request){
		String paramValue = request.getParameter(paramName);
		return Boolean.parseBoolean(paramValue);
	}
	
	/**
	 * 获取Boolean值参数
	 * @param paramName
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBooleanParameter(String paramName, HttpServletRequest request, Boolean defaultValue){
		String paramValue = request.getParameter(paramName);
		return StringUtils.isNotBlank(paramValue) ? Boolean.parseBoolean(paramValue) : defaultValue;
	}
	
	
	/**
	 * 将指定符号隔开的字符串request参数转成List<Long>类型的集合
	 * @param paramName:
	 * @param separator
	 * @param request
	 * @return
	 */
	public static List<Long> getParameterToListOfLong(String paramName, String separator, HttpServletRequest request){
		try{
			String paramValue = request.getParameter(paramName);
			if(paramValue != null && !paramValue.equals("")){
				String[] paramValueArray =  paramValue.split(separator);
				List<Long> result = new ArrayList<Long>();
				for(String e : paramValueArray){
					if(!NumberUtils.isNumber(e)) continue;
					result.add(Long.valueOf(e));
				}
				return result;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 将指定符号隔开的字符串request参数转成List<Integer>类型的集合
	 * @param paramName:
	 * @param separator
	 * @param request
	 * @return
	 */
	public static List<Integer> getParameterToListOfInteger(String paramName, String separator, HttpServletRequest request){
		try{
		String paramValue = request.getParameter(paramName);
		String[] paramValueArray =  paramValue.split(separator);
		List<Integer> result = new ArrayList<Integer>();
		for(String e : paramValueArray){
			result.add(Integer.valueOf(e));
		}
		return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
