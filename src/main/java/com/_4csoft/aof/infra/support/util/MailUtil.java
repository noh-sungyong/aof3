/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : MailUtil.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2013. 6. 3.
 * @author : 김종규
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public class MailUtil {
	protected final LogUtil logger = new LogUtil("MAIL_LOGGER");

	private String host;
	private String port;
	private String protocol;
	private String authUserid;
	private String authUserpw;
	private String secureYn;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAuthUserid() {
		return authUserid;
	}

	public void setAuthUserid(String authUserid) {
		this.authUserid = authUserid;
	}

	public String getAuthUserpw() {
		return authUserpw;
	}

	public void setAuthUserpw(String authUserpw) {
		this.authUserpw = authUserpw;
	}

	public String getSecureYn() {
		return secureYn;
	}

	public void setSecureYn(String secureYn) {
		this.secureYn = secureYn;
	}

	public boolean send(String toAddress, String toName, String fromName, String subject, String content, String contentType, File file) {
		StringBuffer log = new StringBuffer();
		try {
			log.append(DateUtil.getFormatString(System.currentTimeMillis(), "yyyy.MM.dd HH:mm:ss.SSS"));
			log.append("|" + getAuthUserid());
			log.append("|" + fromName);
			log.append("|" + toAddress);
			log.append("|" + toName);
			log.append("|" + subject);
			log.append("|" + content);
			if (file != null && file.isFile()) {
				log.append("|" + file.getName());
			}

			Properties props = new Properties();
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.host", getHost());
			if (StringUtil.isNotEmpty(getPort())) {
				props.put("mail.smtp.port", String.valueOf(getPort()));
			}
			if ("Y".equals(getSecureYn())) {
				props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}

			Authenticator authenticator = null;

			if (StringUtil.isNotEmpty(getAuthUserid())) {
				props.put("mail.smtp.auth", "true");
				authenticator = new SMTPAuthenticator(getAuthUserid(), getAuthUserpw());

			}
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, authenticator);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getAuthUserid(), fromName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress, toName));
			message.setSubject(subject);

			Multipart multiPart = new MimeMultipart();
			MimeBodyPart textPart = new MimeBodyPart();
			if ("html".equalsIgnoreCase(contentType)) {
				textPart.setContent(content, "text/html;charset=utf-8");
			} else {
				textPart.setText(content);
			}
			multiPart.addBodyPart(textPart);

			if (file != null && file.isFile()) {
				MimeBodyPart filePart = new MimeBodyPart();
				FileDataSource fileData = new FileDataSource(file);

				filePart.setDataHandler(new DataHandler(fileData));
				filePart.setFileName(new String(file.getName().getBytes(), "utf-8"));
				multiPart.addBodyPart(filePart);
			}
			message.setContent(multiPart);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.trace("FAIL:" + log.toString() + ":" + e.getMessage());
			return false;
		}
		logger.trace("SUCCESS:" + log.toString());
		return true;
	}

	private class SMTPAuthenticator extends Authenticator {
		private String userid;
		private String userpw;

		public SMTPAuthenticator(String id, String pw) {
			this.userid = id;
			this.userpw = pw;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userid, userpw);
		}
	}

}
