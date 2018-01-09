package com.codespot.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codespot.service.IUserService;

/**
 * Basic controller class for redirection to home or index.<br>
 * <br>
 * Copyright (c) 2016 kumar.ashutosh@skillnetinc.com
 * 
 * @author ashu
 * @created Oct 10, 2016
 * @version $Revision$
 */

//@EnableGlobalMethodSecurity(securedEnabled = true)
@RestController
public class NavController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired 
    private HttpSession session;
	
/*	@RequestMapping({ "/pages/{pageNumber}" })
	public ModelAndView indexPageNumber(@PathVariable Integer pageNumber, final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        model.addAttribute("userInContext", userInContext);
        

        int resultSize=4;
        String sortByParam="bugId";
		Page<Bug> searchPageByUser = bugService.findAllByUserId(userInContext.getId() , pageNumber,resultSize,sortByParam);
		
		List<Bug> bugList =  searchPageByUser.getContent();
		
		 int current = searchPageByUser.getNumber() + 1;
		 int begin = Math.max(1, current - 5);
		 int end = Math.min(begin + 10, searchPageByUser.getTotalPages());
		
		model.addAttribute("userInContext",userInContext);
		model.addAttribute("bugList",bugList);
		model.addAttribute("searchPage", searchPageByUser);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
        
		return new ModelAndView("index");
	}*/
	
	/*@Secured("ROLE_USER")
	@RequestMapping({ "/blank" })
	public ModelAndView blank(final Model model, final Locale locale, HttpSession argHttpSession) {
		model.addAttribute("uploadForm", new UploadForm());
		return new ModelAndView("blank");
	}*/

	@RequestMapping({ "/buttons" })
	public ModelAndView buttons(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("buttons");
	}

/*	@RequestMapping({ "/calendar" })
	public ModelAndView calendar(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("calendar");
	}*/

	@RequestMapping({ "/content-slider" })
	public ModelAndView contentSlider(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("content-slider");
	}

	@RequestMapping({ "/dropzone" })
	public ModelAndView dropzone(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("dropzone");
	}

	@RequestMapping({ "/elements" })
	public ModelAndView elements(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("elements");
	}

	@RequestMapping({ "/email-confirmation" })
	public ModelAndView emailConfirmation(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("email-confirmation");
	}

	@RequestMapping({ "/email-contrast" })
	public ModelAndView emailContrast(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("email-contrast");
	}

	@RequestMapping({ "/email-navbar" })
	public ModelAndView emailNavbar(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("email-navbar");
	}

	@RequestMapping({ "/email-newsletter" })
	public ModelAndView emailNewsLetter(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("email-newsletter");
	}

	@RequestMapping({ "/email" })
	public ModelAndView email(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("email");
	}

	@RequestMapping({ "/error-404" })
	public ModelAndView errorFourHundred(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("error-404");
	}

	@RequestMapping({ "/error-500" })
	public ModelAndView errorFiveHundred(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("error-500");
	}

	@RequestMapping({ "/faq" })
	public ModelAndView faq(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("faq");
	}

	@RequestMapping({ "/form-elements-2" })
	public ModelAndView formElementsTwo(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("form-elements-2");
	}

	@RequestMapping({ "/form-elements" })
	public ModelAndView formElements(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("form-elements");
	}

	@RequestMapping({ "/form-wizard" })
	public ModelAndView formWizard(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("form-wizard");
	}

	@RequestMapping({ "/gallery" })
	public ModelAndView gallery(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("gallery");
	}

	@RequestMapping({ "/grid" })
	public ModelAndView grid(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("grid");
	}

	@RequestMapping({ "/inbox" })
	public ModelAndView inbox(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("inbox");
	}

	@RequestMapping({ "/invoice" })
	public ModelAndView invoice(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("invoice");
	}

	@RequestMapping({ "/jqgrid" })
	public ModelAndView jqgrid(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("jqgrid");
	}

	@RequestMapping({ "/jquery-ui" })
	public ModelAndView jqueryUi(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("jquery-ui");
	}

	@RequestMapping({ "/mobile-menu-1" })
	public ModelAndView mobileMenuOne(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("mobile-menu-1");
	}

	@RequestMapping({ "/mobile-menu-2" })
	public ModelAndView mobileMenuTwo(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("mobile-menu-2");
	}

	@RequestMapping({ "/mobile-menu-3" })
	public ModelAndView mobileMenuThree(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("mobile-menu-3");
	}

	@RequestMapping({ "/nestable-list" })
	public ModelAndView nestableList(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("nestable-list");
	}

	@RequestMapping({ "/pricing" })
	public ModelAndView pricing(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("pricing");
	}

	@RequestMapping({ "/profile" })
	public ModelAndView profile(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("profile");
	}

	@RequestMapping({ "/search" })
	public ModelAndView search(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("search");
	}

/*	@RequestMapping({ "/tables" })
	public ModelAndView tables(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("tables");
	}*/

	@RequestMapping({ "/timeline" })
	public ModelAndView timeline(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("timeline");
	}

	@RequestMapping({ "/top-menu" })
	public ModelAndView topMenu(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("top-menu");
	}

	@RequestMapping({ "/treeview" })
	public ModelAndView treeview(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("treeview");
	}

	@RequestMapping({ "/two-menu-1" })
	public ModelAndView twoMenuOne(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("two-menu-1");
	}

	@RequestMapping({ "/two-menu-2" })
	public ModelAndView twoMenuTwo(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("two-menu-2");
	}

	@RequestMapping({ "/typography" })
	public ModelAndView typography(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("typography");
	}

	@RequestMapping({ "/widgets" })
	public ModelAndView widgets(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("widgets");
	}

	@RequestMapping({ "/wysiwyg" })
	public ModelAndView wysiwyg(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("wysiwyg");
	}

}
