package com.success.sys.sendMail.dao;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface SendMailDao {
	// 发送普通邮件
	public void send(String fromMailAddress, String toMailAddress, String subject, Object content)
			throws AddressException, MessagingException;

	// 发送带附件邮件
	public void send(String fromMailAddress, String toMailAddress, String subject, Object content,
			String attachment, String newAttachmentName)
			throws AddressException, MessagingException;
}
