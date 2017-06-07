package com.success.web.framework.exception;

/**
 * 业务逻辑异常
 * @author liang.zhifu
 *
 */
public class ServiceException extends Exception {

	public ServiceException(){
		super();
	}
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ServiceException(Throwable cause) {
        super(cause);
    }
}
