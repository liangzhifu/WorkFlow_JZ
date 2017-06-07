package com.success.sys.fun.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.success.sys.fun.domain.UserFun;
import com.success.sys.fun.query.UserFunQuery;


public interface FunService {
	public List<UserFun> selectUserFun(UserFunQuery query) ;
	public List<UserFun> selectFunByUserId(UserFunQuery query) ;
	
	public UserFunQuery getUserFunQuery(HttpServletRequest request);
	
	public Integer insertUserFun(UserFun userFun);
	public Integer deleteUserFun(UserFun userFun);
}
