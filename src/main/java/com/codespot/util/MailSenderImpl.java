package com.codespot.util;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;

/**
 * DESCRIPTION GOES HERE<br>
 * <br>
 * Copyright (c) 2016 kumar.ashutosh@skillnetinc.com
 *
 * @author ashu
 * @created Oct 22, 2016
 * @version $Revision$
 */
@Component("mailSender")
public class MailSenderImpl implements MailSender{

	/* (non-Javadoc)
	 * @see com.wrap.util.IWrapMailSender#sendJunkMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendJunkMail(String SENDER_EMAIL, String SENDER_EMAIL_PASSWORD,
			String subject, String message, String sendTo)
			throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		GoogleMail.SendEmail(SENDER_EMAIL, SENDER_EMAIL_PASSWORD,sendTo, "",subject,message);	
	}

}
