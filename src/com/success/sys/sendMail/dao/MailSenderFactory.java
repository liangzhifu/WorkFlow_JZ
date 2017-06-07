package com.success.sys.sendMail.dao;

/**
 * 发件箱工厂
 * 
 * @author Administrator
 * 
 */
public class MailSenderFactory {
	/**
	 * 服务邮箱
	 */
	private static SendMailDaoImpl serviceSms = null;

	/**
	 * 获取邮箱
	 * 
	 * @param type
	 *            邮箱类型
	 * @return 符合类型的邮箱
	 */
	public static SendMailDaoImpl getSender(String fromMailAddress,
			String fromMailPassword,String smtpHostName,String smtpPort) {

			if(smtpHostName==null||smtpHostName.equals("")){
				serviceSms = new SendMailDaoImpl(fromMailAddress, fromMailPassword,smtpPort);
			}else{
				serviceSms = new SendMailDaoImpl(fromMailAddress, fromMailPassword,smtpHostName,smtpPort);
			}
		
		return serviceSms;

	}
}
