package com.codespot.util;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


/**
 * DESCRIPTION GOES HERE<br>
 * <br>
 * Copyright (c) 2016 kumar.ashutosh@skillnetinc.com
 * 
 * @author ashu
 * @created Oct 22, 2016
 * @version $Revision$
 */
public interface MailSender {
	public void sendJunkMail(String SENDER_EMAIL, String SENDER_EMAIL_PASSWORD,
			String subject, String message, String sendTo)
			throws AddressException, MessagingException;

}
