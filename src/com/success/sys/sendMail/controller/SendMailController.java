package com.success.sys.sendMail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.sendMail.dao.MailSenderFactory;
import com.success.sys.sendMail.dao.SendMailDao;

public class SendMailController {

	public static String fromMailAddress = null;
	public static String fromMailPassword = null;
	public static String smtpHostName = null;
	public static String smtpPort = null;

	static {
		Properties prp = new Properties();
		InputStream is = SendMailController.class
				.getResourceAsStream("sendMail.properties");

		try {
			prp.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fromMailAddress = prp.getProperty("fromMailAddress");
		fromMailPassword = prp.getProperty("fromMailPassword");
		smtpHostName = prp.getProperty("smtpHostName");
		smtpPort = prp.getProperty("smtpPort");

	}

	public void queryPageOrg(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		SendMailDao sendMailDao = MailSenderFactory.getSender(fromMailAddress,
				fromMailPassword, smtpHostName, smtpPort);
		//收件人地址
		String toMailAddress = request.getParameter("toMailAddress");
		//主题
		String subject = request.getParameter("subject");
		//内容
		String content = request.getParameter("content");
		boolean b = true;
		try {
			sendMailDao.send(fromMailAddress, toMailAddress, subject, content);
		} catch (AddressException e) {
			b = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			b = false;
			e.printStackTrace();
		}
		if (b) {
			System.out.println("发送成功");
		}
	}
	public static void main(String[] args) {
		SendMailDao sendMailDao = MailSenderFactory.getSender(fromMailAddress,
				fromMailPassword, smtpHostName, smtpPort);
		//收件人
//		String toMailAddress = "382007293@qq.com";
//		String toMailAddress = "fubin.fan@takata.com.cn";
//		String toMailAddress = "jie.huajian@zte.com.cn,takata@ttdmg.com,382007293@qq.com";
		String toMailAddress = "g000tianxia0@outlook.com";
		//主题
		String subject = "测试（赛克赛斯）";
		//内容
		String content = "测\\r\\n试\r\n邮\\n\\r箱\\n发\\b送/n功\n\r能<br>我们都有一个家";
		boolean b = true;
		try {
			sendMailDao.send(fromMailAddress, toMailAddress, subject, content);
		} catch (AddressException e) {
			b = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			b = false;
			e.printStackTrace();
		}
		if (b) {
			System.out.println("发送成功");
		}
	}
}
