package com.success.sys.sendMail.dao;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 简单邮件发送器，可单发，群发。
 * 
 * @author guoj
 * 
 */
public class SendMailDaoImpl implements SendMailDao{
	/**
	 * 发送邮件的prop文件
	 */
	private final transient Properties props = System.getProperties();
	/**
	 * 邮件服务器登陆验证
	 */
	private transient MailAuthenticator authenticator;
	/**
	 * 邮箱session
	 */
	private transient Session session;

	/**
	 * 初始化邮件发送器(输入SMTP服务器地址)
	 * 
	 * @param smtpHostName
	 * @param fromMailAddress
	 * @param fromMailPassword
	 */
	public SendMailDaoImpl(final String fromMailAddress,
			final String fromMailPassword, final String smtpHostName,
			String smtpPort) {
		init(fromMailAddress, fromMailPassword, smtpHostName, smtpPort);
	}

	/**
	 * 初始化邮件发送器(解析SMTP服务器地址)
	 * 
	 * @param fromMailAddress
	 * @param fromMailPassword
	 */
	public SendMailDaoImpl(final String fromMailAddress,
			final String fromMailPassword, String smtpPort) {
		// 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName = "smtp." + fromMailAddress.split("@")[1];
		System.out.println("解析出的smtp服务器为：" + smtpHostName);
		init(fromMailAddress, fromMailPassword, smtpHostName, smtpPort);
	}

	/**
	 * 初始化
	 */
	/**
	 * 初始化
	 * 
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostName
	 *            SMTP主机地址
	 */
	private void init(String fromMailAddress, String fromMailPassword,
			String smtpHostName, String smtpPort) {
		// 初始化props
		if (smtpPort == null || smtpPort.equals("")) {
			smtpPort = "25";
		}
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.host", smtpHostName);
		// 验证
		//authenticator = new MailAuthenticator(fromMailAddress, fromMailPassword);
		// 创建session
		//Session.getInstance(props, null);
		session = Session.getDefaultInstance(props);
	}

	/**
	 * 发送邮件
	 * 
	 * @param toMailAddress
	 *            收件人地址，可多人中间以“,”分隔
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	@Override
	public void send(String fromMailAddress, String toMailAddress, String subject, Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		//message.setFrom(new InternetAddress(authenticator.getFromMailAddress()));
		// message.setFrom(new InternetAddress("guojin123"));
		// 设置收件人
		String[] toMailAddressArr = toMailAddress.split(",");
		InternetAddress[] addressesArr = new InternetAddress[toMailAddressArr.length];
		for (int i = 0; i < toMailAddressArr.length; i++) {
			addressesArr[i] = new InternetAddress(toMailAddressArr[i]);
		}
		message.setFrom(new InternetAddress(fromMailAddress));
		
		message.setRecipients(RecipientType.TO, addressesArr);
		// message.setRecipient(RecipientType.TO, new InternetAddress(
		// toMailAddress));
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}

	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人list列表
	 * @param subject
	 * @param content
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(List<String> recipients, String subject, Object content)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getFromMailAddress()));
		// 设置收信群
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);

	}

	/**
	 * 发送邮件带附件
	 * 
	 * @param toMailAddress
	 *            收件人地址
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件路径
	 * @param newAttachmentName
	 *            附件新名字
	 * @throws AddressException
	 * @throws MessagingException
	 */
	@Override
	public void send(String fromMailAddress, String toMailAddress, String subject, Object content,
			String attachment, String newAttachmentName)
			throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getFromMailAddress()));
		// 设置收件人
		String[] toMailAddressArr = toMailAddress.split(",");
		InternetAddress[] addressesArr = new InternetAddress[toMailAddressArr.length];
		for (int i = 0; i < toMailAddressArr.length; i++) {
			addressesArr[i] = new InternetAddress(toMailAddressArr[i]);
		}
		message.setRecipients(RecipientType.TO, addressesArr);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容带附件
		MimeMultipart mimeMultipart = new MimeMultipart();
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setText(content.toString(), "utf-8");
		MimeBodyPart attach1 = new MimeBodyPart();
		mimeMultipart.addBodyPart(mimeBodyPart);
		DataSource ds1 = new FileDataSource(attachment);
		DataHandler dh1 = new DataHandler(ds1);
		attach1.setDataHandler(dh1);
		attach1.setFileName(newAttachmentName);
		System.out.println("附件新名称：" + newAttachmentName);
		mimeMultipart.addBodyPart(attach1);
		// mimeMultipart.addBodyPart(attach1);
		message.setContent(mimeMultipart);
		message.saveChanges();

		// message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}


}
