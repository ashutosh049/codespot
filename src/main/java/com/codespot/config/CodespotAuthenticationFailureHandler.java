package com.codespot.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CodespotAuthenticationFailureHandler  implements
AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	 public CodespotAuthenticationFailureHandler() {
	        super();
	    }
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
					throws IOException, ServletException {
		
		String message = "";

		if(exception instanceof UsernameNotFoundException) {
			message = "UsernameNotFound";
		} else if(exception instanceof AuthenticationCredentialsNotFoundException) {
			message = "AuthenticationCredentialsNotFound";
		}else if(exception instanceof InsufficientAuthenticationException) {
			message = "InsufficientAuthentication";
		}else if(exception instanceof AccountExpiredException) {
			message = "AccountExpired";
		}else if(exception instanceof CredentialsExpiredException) {
			message = "CredentialsExpired";
		}else if(exception instanceof DisabledException) {
			message = "Disabled";
		}else if(exception instanceof LockedException) {
			message = "Locked";
		}else if(exception instanceof BadCredentialsException) {
			message = "BadCredentials";
		}else{
			message = exception.getMessage();
		}
		final HttpSession session = request.getSession();
		session.setAttribute("errorMessage", message);
		redirectStrategy.sendRedirect(request, response, "/login?error="+message);
	}
	/**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
