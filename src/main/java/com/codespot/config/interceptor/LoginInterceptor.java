package com.codespot.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codespot.controller.AccessController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(AccessController.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		RequestMapping requestMapping = ((HandlerMethod) handler).getMethodAnnotation(RequestMapping.class);
		
		HttpSession session =  request.getSession();
		
		DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest != null) {
			logger.info("Requested url : "+savedRequest.getRedirectUrl());
		} 
		
		boolean loginRequested = requestMapping != null && requestMapping.value().length > 0 && "login".equals(requestMapping.value()[0]);
		boolean userInCtxt = request.getSession().getAttribute("userInContext") != null;

		if (loginRequested && userInCtxt) {
			logger.info("User already logged in.Requested url : "+savedRequest.getRedirectUrl());
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		} else if (loginRequested && !userInCtxt) {
			response.sendRedirect(request.getContextPath() + "/login");
			return true;
		}

		return true;
	}
}