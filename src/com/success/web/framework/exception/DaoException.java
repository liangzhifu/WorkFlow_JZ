package com.success.web.framework.exception;

/**
 * 数据库异常
 * @author liang.zhifu
 *
 */
public class DaoException extends ServiceException {

	public DaoException(){
		super();
	}
	
	public DaoException(String message){
		super(message);
	}
	
	public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public DaoException(Throwable cause) {
        super(cause);
    }
}
