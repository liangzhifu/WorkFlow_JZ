package com.success.sys.sendMail.dao;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 1、构建一个继承自javax.mail.Authenticator的具体类， 并重写里面的getPasswordAuthentication()方法。
 * 此类是用作登录校验的，以确保你对该邮箱有发送邮件的权利。
 */
public class MailAuthenticator extends Authenticator {
	/**
	 * 登陆邮箱
	 */
	private String fromMailAddress;
	/**
	 * 登陆邮箱密码
	 */
	private String fromMailPassword;

	/**
	 * 初始化邮箱和密码
	 * 
	 * @param fromMailAddress
	 *            邮箱
	 * @param fromMailPassword
	 *            密码
	 */
	public MailAuthenticator(String fromMailAddress, String fromMailPassword) {
		this.fromMailAddress = fromMailAddress;
		this.fromMailPassword = fromMailPassword;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(fromMailAddress, fromMailPassword);
	}

	public String getFromMailAddress() {
		return fromMailAddress;
	}

	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}

	public String getFromMailPassword() {
		return fromMailPassword;
	}

	public void setFromMailPassword(String fromMailPassword) {
		this.fromMailPassword = fromMailPassword;
	}

}
