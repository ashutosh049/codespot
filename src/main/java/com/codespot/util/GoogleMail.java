package com.codespot.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * DESCRIPTION GOES HERE<br>
 * <br>
 * Copyright (c) 2016 kumar.ashutosh@skillnetinc.com
 * 
 * @author ashu
 * @created Oct 22, 2016
 * @version $Revision$
 */
@Service
public class GoogleMail {
	private static Logger logger = LoggerFactory
			.getLogger(GoogleMail.class);
	public static Boolean isNotificationEnable = false;


	private GoogleMail() {
	}

	public static void Send(final String username, final String password,
			String recipientEmail, String title, String message)
					throws AddressException, MessagingException {
		GoogleMail.send(username, password, recipientEmail, "", title,
				message);
	}

	public static void sendMail(String senderEmail, String senderPassword,
			String recipientEmail, String ccEmail, String title, String message)
					throws AddressException, MessagingException {

		send(senderEmail, senderPassword, recipientEmail, ccEmail, title,
				message);
	}

	@SuppressWarnings("restriction")
	public static void send(final String username, final String password,
			String recipientEmail, String ccEmail, String title, String message)
					throws AddressException, MessagingException {
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, null);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));

		logger.debug("sending to email address : {}", recipientEmail);
		msg.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(recipientEmail, false));

		if (ccEmail != null) {
			if (ccEmail.length() > 0) {
				msg.setRecipients(Message.RecipientType.CC,	InternetAddress.parse(recipientEmail, false));
			}
		}

		msg.setSubject(title);
		msg.setSentDate(new Date());
		msg.setDataHandler(new DataHandler(new HTMLDataSource(message)));
		
		// Images.....
		/*MimeMultipart multipart = new MimeMultipart("related");
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
		messageBodyPart.setContent(message, "text/html");
		multipart.addBodyPart(messageBodyPart);
		messageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource("F:/Templates/ace-master/ace-master/assets/images/avatars/bug-48-48.png.png");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);*/
		// Images.....
		
		SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
		
		transport.setLocalHost("localhost");
		logger.debug(" SMTP Transport ");

		transport.connect("smtp.gmail.com", Integer.parseInt("465"), username, password);

		logger.debug("Now sending Email");
		transport.sendMessage(msg, msg.getAllRecipients());
		logger.debug("Email sent successfully");
		transport.close();
	}

	public static void SendEmail(String senderemail, String senderPassword,
			String recipientEmail, String ccEmail, String title, String message)
					throws AddressException, MessagingException {
		send(senderemail, senderPassword, recipientEmail, ccEmail, title,
				message);
	}

	public static void main(String args[]) {

	}

	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		public InputStream getInputStream() throws IOException {
			if (html == null) {
				throw new IOException("Null HTML");
			}
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}

}
