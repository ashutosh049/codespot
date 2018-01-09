package com.codespot.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Set session timeout units..
 * @author Ashutosh
 *
 */
public class CodespotSessionListener implements HttpSessionListener {

	private static final Logger logger_= Logger.getLogger(CodespotSessionListener.class);
	
	public void sessionCreated(HttpSessionEvent event) {
		logger_.info("Session created");
		event.getSession().setMaxInactiveInterval(0);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		event.getSession().setAttribute("Message", "Session Expired");
		logger_.info("Session destroyed");
	}
	
}
