package com.codespot.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/unauth")
public class UnAuthController {

	private final Logger logger = LoggerFactory
			.getLogger(UnAuthController.class);

	ObjectError error;
	FieldError fieldError;

	@RequestMapping(value = "/fail/restrictedaccess", method = RequestMethod.GET)
	public ModelAndView reportBugByAdmin(final Model model,
			final Locale locale, HttpSession session) {
		return new ModelAndView("access-denied");
	}

}
