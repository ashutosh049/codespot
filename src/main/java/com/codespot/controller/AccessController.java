<<<<<<< HEAD
package com.codespot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codespot.helper.SessionIdentifierGenerator;
import com.codespot.model.ActiveUserStore;
import com.codespot.model.Question;
import com.codespot.model.User;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;
import com.codespot.util.CodespotConstants;

//import com.codespot.util.FileUtils;

@RestController
public class AccessController {

	@Value("${ldap.enabled:false}") 
	private boolean isLdapConfigured; 
    @Value("${tempLock.timeout:0}") 
    private int templockTimeout; 
	
	@Autowired private IUserService userService;
	@Autowired private HttpSession session;
	@Autowired private ServletContext servletContext;
	@Autowired private IQuestionService questionService;

	private final Logger logger = LoggerFactory.getLogger(AccessController.class);

	ObjectError error;
	FieldError fieldError;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
    ActiveUserStore activeUserStore;

	@GET
	@RequestMapping({ "/"})
	public ModelAndView defaultHome(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,
			HttpSession argHttpSession, @RequestParam(value="pageNo", required=false) Integer pageNo) {
		
		List<String> activeUsersList = new ArrayList<String>();
		for (String userName: activeUserStore.getUsers()) {
		    	activeUsersList.add(userName);
		}
		model.addAttribute("activeUsersList", activeUsersList);
		return new ModelAndView("redirect:/questions");
	}
	
	@GET
	@RequestMapping({"/questions","/questionComment/info"})
	public ModelAndView questionHome(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,
			HttpSession argHttpSession, @RequestParam(value="sd", required=false) String sd, @RequestParam(value="pageNo", required=false) Integer pageNo) {
		
		List<String> activeUsersList = new ArrayList<String>();
		for (String userName: activeUserStore.getUsers()) {
		    	activeUsersList.add(userName);
		}
		
		if(sd==null)
			sd = CodespotConstants.SortDirection.ASC.name();
		
		if(sd.equals(CodespotConstants.SortDirection.ASC.name())){
			
		}
		else{
		}
		
		if(pageNo==null)
			pageNo = 1;
		
		Page<Question> questionPage = questionService.getPage(pageNo, 10, Sort.Direction.DESC , "createTimestamp");
		
		List<Question> questionList = questionPage.getContent(); 
		 int totalElm = (int) questionPage.getTotalElements();
		 int current = questionPage.getNumber() + 1;
		 int begin = Math.max(1, current - 5);
		 int end = Math.min(begin + 10, questionPage.getTotalPages());
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("totalPages", questionPage.getTotalPages());
		model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalElm", totalElm);
	    
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(User user,final Model model, final Locale locale,final RedirectAttributes redirectAttributes,HttpServletRequest request, HttpSession argHttpSession) {
		System.out.println(model.toString());
		logger.info("Context path : " + servletContext.getContextPath()+", "+RequestMethod.GET);
		return new ModelAndView("login");
	}
	
	@RequestMapping(value={ "/users/signup"}, method=RequestMethod.GET)
	public ModelAndView signupUsersPage(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("user", new User());	
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value={ "/users/signup"}, method=RequestMethod.POST)
	public ModelAndView signupUsersAction(ModelMap model,
			@ModelAttribute @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		logger.info("user : "+user.toString());
		
		if (userService.findByUserEmail(user.getUserEmail())==null) {
			if (userService.findByUserEmail(user.getUserName())==null) {
//				if (ModuleControllerHelper.isValidPassword(user.getPassword())) {
//					if (ModuleControllerHelper.isPasswordConfirmed(user.getPassword(), user.getConfirmPassword())) {
						String activationToken = new SessionIdentifierGenerator()
								.generatePasswordResetToken();
						
						user.setUserActive(true);
						user.setActivationTokem(activationToken);
						userService.create(user);
						logger.info("User created with activationToken : "+activationToken);
//						mailSenderHelper.sendUserActivaionMail(user,activationToken);
						model.addAttribute("pagestatus", "registration-success");
						model.addAttribute("registration-success-url", "index");
						logger.info("New user " + user.getUserName()
								+ " was successfully created.");
//					} else {
//						fieldError = new FieldError("user", "confirmPassword",
//								"Passowrd do not match. ");
//						result.addError(fieldError);
//					}
//				} else {
//					fieldError = new FieldError("user", "password",
//							"Invalid passowrd.");
//					result.addError(fieldError);
//				}
			} else {
				fieldError = new FieldError("user", "userName",
						"Username already registered.");
				result.addError(fieldError);
			}
		} else {
			fieldError = new FieldError("user", "email",
					"Email Id already registered.");
			result.addError(fieldError);
		}

		model.addAttribute("user", user);
		redirectAttributes.addFlashAttribute("user", user);

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
			return new ModelAndView("redirect:/users/signup-success");
		}
		
		return new ModelAndView("redirect:/users/signup-success");
		
	}
	
	@RequestMapping(value={ "/users/signup-success"}, method=RequestMethod.GET)
	public ModelAndView signupSuccess(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) {
		//model.addAttribute("user", new User());	
		return new ModelAndView("signup-success");
	}
	
	/*@RequestMapping({ "/", "/login" })
	public ModelAndView welcome_(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "action", required = false) String action) {
		model.addAttribute("user", new User());
		
		if(argHttpSession==null){
			model.addAttribute("sessionexpired", true);
			return new ModelAndView("login");
		}else{

			SecurityContextImpl impl = 	(SecurityContextImpl)argHttpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			if(impl!=null){
				Authentication authentication = impl.getAuthentication();
				User certUser =userService.findByUsername(authentication.getName());
				argHttpSession.setAttribute("certUser", certUser);
				
				WebAuthenticationDetails details = (WebAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
				String ip = details.getRemoteAddress();
			}
		
		}
		
		String message = (String)argHttpSession.getAttribute("Message");
		

		User userInContext = (User)argHttpSession.getAttribute("userInContext");
        boolean clockedin = argHttpSession.getAttribute("clockedin")==null ? false:(Boolean)argHttpSession.getAttribute("clockedin");
        Date clockedindateandtime = (Date)argHttpSession.getAttribute("clockedindateandtime");
        
        if(userInContext!=null && clockedin){
        	message = "You are clocked in now";
        	model.addAttribute("clockedindateandtime", clockedindateandtime);
        	model.addAttribute("userInContext", userInContext.getUserName());
        }
        
		model.addAttribute("message", message);
		return new ModelAndView("login");
	}*/
	
	@RequestMapping({"/clockin" })
	public ModelAndView clockout(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("user", new User());
		
		if(argHttpSession==null){
			model.addAttribute("sessionexpired", true);
			return new ModelAndView("login");
		}
		
		argHttpSession.setAttribute("action", "clockout");
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){    
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		    }
		    model.addAttribute("logout-sucess", "logout-sucess");
		    return new ModelAndView("redirect:/login?logout=cuccess");
		
	}

	@RequestMapping({ "/codespot-index" })
	public ModelAndView index(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUserName(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
       /* Integer pageNumber = 1;
        int resultSize=10;
        String sortByParam="bugId";*/
		
		model.addAttribute("userInContext",userInContext);
        
		return new ModelAndView("codespot-index");
	}
	
	/*@PreAuthorize("hasAuthority('ROLE_EMPL')")
	@RequestMapping({ "/calendar" })
	public ModelAndView calendar(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		User userInContext = (User)session.getAttribute("userInContext");
		if(userInContext!=null){
			model.addAttribute("userInContext",userInContext);
			model.addAttribute("userInContextId",String.valueOf(userInContext.getId()));
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
			return new ModelAndView("calendar");
		}
		return new ModelAndView("login");
	}*/
	
	/*@RequestMapping(value = "/useraction", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String Submit(@RequestParam("action") String action,@RequestParam("userid") String userid) throws UserNotFound {
		User userInContext = (User)session.getAttribute("userInContext");
		System.out.println("action :"+action+"\nuserid : "+userid);

		Date userClockActionTime = new Date();
		
		if(action!=null && userid!=null && userid.equals(String.valueOf(userInContext.getId()))){
			TimeClock timeClock = new TimeClock();
			TimeClock savedTimeCLock = null;
			
			if(!userInContext.getClockedInFlag()){
				timeClock.setUser(userInContext);
				timeClock.setBussinessdate(userClockActionTime);
				timeClock.setIntime(userClockActionTime);
				savedTimeCLock = clockService.createTimeClockEntry(timeClock);
			}else{
				timeClock = clockService.findById(userInContext.getTimeClockId());
				if(timeClock!=null){
					savedTimeCLock = clockService.updateTimeClockEntry(timeClock);
				}
			}
			
			if(savedTimeCLock!=null){
				if(action.equals("Clock-In")){
					userInContext.setClockedInFlag(true);
					userInContext.setTimeClockId(savedTimeCLock.getId());
					logger.info(userInContext.getUserName()+" clocked-in @"+savedTimeCLock.getIntime());
				}else if(action.equals("Clock-Out")){
					userInContext.setClockedInFlag(false);	
					userInContext.setTimeClockId(null);
					logger.info(userInContext.getUserName()+" clocked-out @"+savedTimeCLock.getOuttime());
				}
				userService.update(userInContext);
				return CodespotConstants.fmt.format(userClockActionTime);
			}
		}
		return null;
	}*/
	
	/*@RequestMapping(value = "/fetchUserAction", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String fetchUserAction(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) throws UserNotFound {
		User userInContext = (User)session.getAttribute("userInContext");
		if(userInContext.getClockedInFlag()){
			model.addAttribute("clockaction", "Clock-Out");
			model.addAttribute("btncolor", "btn-yellow");
			return "Clock-Out";
		}else{
			model.addAttribute("clockaction", "Clock-In");
			model.addAttribute("btncolor", "btn-success");
			return "Clock-In";
		}
	}*/
	
	/*@RequestMapping(value = "/userClockTimes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<TimeClockUiBean> userClockTimes(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		User userInContext = (User)session.getAttribute("userInContext");
		model.addAttribute("userInContext",userInContext);
		model.addAttribute("userInContextId",String.valueOf(userInContext.getId()));
		List<TimeClockUiBean> userUiClockList = new ArrayList<TimeClockUiBean>();
		if(userInContext!=null){
			
			List<TimeClock> userClockList = clockService.findAllUserTimeClock(userInContext.getId());
			for (TimeClock timeClock : userClockList) {
				TimeClockUiBean  uiBean= new TimeClockUiBean();
				uiBean.setTimeClockIdId(String.valueOf(timeClock.getId()));
				uiBean.setUserId(String.valueOf(timeClock.getUser().getId()));
				uiBean.setBussinessDate(DateUtil.getDateInString(timeClock.getBussinessdate()));
				
				uiBean.setInTime(CodespotConstants.fmt.format(timeClock.getIntime()));
				if(timeClock.getOuttime()!=null){
					uiBean.setOutTime(CodespotConstants.fmt.format(timeClock.getOuttime()));
					uiBean.setDuration(DateUtil.getTimeElapsed(timeClock.getDuration()));
				}
				userUiClockList.add(uiBean);
			}
			model.addAttribute("userUiClockList", userUiClockList);
			
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
		}
		return userUiClockList;
		
	}*/
	
	
	//---------------------ADMIN MODULE ------------------------------------------------
	
	/*@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping({ "/tables" })
	public ModelAndView tables(final Model model, final Locale locale, HttpSession session) throws ParseException {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
		
		model.addAttribute("userInContext",userInContext);
		
		List<TimeClockUiBean> userUiClockList = new ArrayList<TimeClockUiBean>();
		if(userInContext!=null){
			
			List<TimeClock> userClockList = clockService.displayTodaysEmployeeStatus(new Date());
			for (TimeClock timeClock : userClockList) {
				
				User user = timeClock.getUser();
				TimeClockUiBean  uiBean= new TimeClockUiBean();

				uiBean.setEmpId(String.valueOf(user.getId()));
				uiBean.setEmpEmail(user.getUserEmail());
				uiBean.setEmpName(user.getUserName());
				uiBean.setEmpClockedInFlag(user.getClockedInFlag());
				uiBean.setTimeClockIdId((String.valueOf(timeClock.getId())));
				uiBean.setUserId(user.getUserEmail());
				uiBean.setBussinessDate(DateUtil.getDateInString(timeClock.getBussinessdate()));
				uiBean.setInTime(new SimpleDateFormat("hh:mm a").format(timeClock.getIntime()));
				if(timeClock.getOuttime()!=null){
					try {
						uiBean.setOutTime(new SimpleDateFormat("hh:mm a").format(timeClock.getOuttime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					uiBean.setDuration(DateUtil.getTimeElapsed(timeClock.getDuration()));
				}
				userUiClockList.add(uiBean);
			}
			model.addAttribute("userUiClockList", userUiClockList);
			
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
		}
        
		return new ModelAndView("tables");
	}*/
	
	
	/*private void createCodespotUsers(User user){
		String activationToken = new SessionIdentifierGenerator().generatePasswordResetToken();
		UserPassword userPassword = new UserPassword();
		userPassword.setEmail(user.getUserEmail());
		userPassword.setActivationToken(activationToken);
		userPassword.setActivationTokenTimestamp(new Date());
		user.setUserPassword(userPassword);
		userService.save(user);
	}*/
	
	
	
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	/*@RequestMapping({ "/index" })
	public ModelAndView index(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
        Integer pageNumber = 1;
        int resultSize=10;
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
	
	/*@RequestMapping({ "/manager-index" })
	public ModelAndView managerIndex(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
        
        session.setAttribute("clientInContext", new Client());
        
        Integer pageNumber = 1;
        int resultSize=10;
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
        
		return new ModelAndView("manager-index");
	}*/
	
	
	@RequestMapping("/error") 
    public String loginError(Model model, Exception e) { 
        model.addAttribute("loginError", true); 
        model.addAttribute("isLdapConfigured", isLdapConfigured); 
 
        if (userIsLocked()) { 
            model.addAttribute("errorKey", "login.lock"); 
            model.addAttribute("templockTimeout", templockTimeout); 
        } else { 
            model.addAttribute("errorKey", "login.error"); 
        } 
 
        return "login"; 
    } 
	
	private boolean userIsLocked() { 
        return session.getAttribute("IS_LOCKED") != null && session.getAttribute("IS_LOCKED") instanceof Boolean 
                && (Boolean) session.getAttribute("IS_LOCKED"); 
 
    } 
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage (final Model model, final Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    model.addAttribute("logout-sucess", "logout-sucess");
	    return new ModelAndView("redirect:/login?logout=cuccess");
	}
	
}
=======
package com.codespot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codespot.helper.SessionIdentifierGenerator;
import com.codespot.model.ActiveUserStore;
import com.codespot.model.Question;
import com.codespot.model.User;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;
import com.codespot.util.CodespotConstants;

//import com.codespot.util.FileUtils;

@RestController
public class AccessController {

	@Value("${ldap.enabled:false}") 
	private boolean isLdapConfigured; 
    @Value("${tempLock.timeout:0}") 
    private int templockTimeout; 
	
	@Autowired private IUserService userService;
	@Autowired private HttpSession session;
	@Autowired private ServletContext servletContext;
	@Autowired private IQuestionService questionService;

	private final Logger logger = LoggerFactory.getLogger(AccessController.class);

	ObjectError error;
	FieldError fieldError;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Autowired
    ActiveUserStore activeUserStore;

	@GET
	@RequestMapping({ "/"})
	public ModelAndView defaultHome(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,
			HttpSession argHttpSession, @RequestParam(value="pageNo", required=false) Integer pageNo) {
		
		List<String> activeUsersList = new ArrayList<String>();
		for (String userName: activeUserStore.getUsers()) {
		    	activeUsersList.add(userName);
		}
		model.addAttribute("activeUsersList", activeUsersList);
		return new ModelAndView("redirect:/questions");
	}
	
	@GET
	@RequestMapping({"/questions","/questionComment/info"})
	public ModelAndView questionHome(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,
			HttpSession argHttpSession, @RequestParam(value="sd", required=false) String sd, @RequestParam(value="pageNo", required=false) Integer pageNo) {
		
		List<String> activeUsersList = new ArrayList<String>();
		for (String userName: activeUserStore.getUsers()) {
		    	activeUsersList.add(userName);
		}
		
		if(sd==null)
			sd = CodespotConstants.SortDirection.ASC.name();
		
		if(sd.equals(CodespotConstants.SortDirection.ASC.name())){
			
		}
		else{
		}
		
		if(pageNo==null)
			pageNo = 1;
		
		Page<Question> questionPage = questionService.getPage(pageNo, 10, Sort.Direction.DESC , "createTimestamp");
		
		List<Question> questionList = questionPage.getContent(); 
		 int totalElm = (int) questionPage.getTotalElements();
		 int current = questionPage.getNumber() + 1;
		 int begin = Math.max(1, current - 5);
		 int end = Math.min(begin + 10, questionPage.getTotalPages());
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("totalPages", questionPage.getTotalPages());
		model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalElm", totalElm);
	    
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(User user,final Model model, final Locale locale,final RedirectAttributes redirectAttributes,HttpServletRequest request, HttpSession argHttpSession) {
		System.out.println(model.toString());
		logger.info("Context path : " + servletContext.getContextPath()+", "+RequestMethod.GET);
		return new ModelAndView("login");
	}
	
	@RequestMapping(value={ "/users/signup"}, method=RequestMethod.GET)
	public ModelAndView signupUsersPage(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("user", new User());	
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value={ "/users/signup"}, method=RequestMethod.POST)
	public ModelAndView signupUsersAction(ModelMap model,
			@ModelAttribute @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		logger.info("user : "+user.toString());
		
		if (userService.findByUserEmail(user.getUserEmail())==null) {
			if (userService.findByUserEmail(user.getUserName())==null) {
//				if (ModuleControllerHelper.isValidPassword(user.getPassword())) {
//					if (ModuleControllerHelper.isPasswordConfirmed(user.getPassword(), user.getConfirmPassword())) {
						String activationToken = new SessionIdentifierGenerator()
								.generatePasswordResetToken();
						
						user.setUserActive(true);
						user.setActivationTokem(activationToken);
						userService.create(user);
						logger.info("User created with activationToken : "+activationToken);
//						mailSenderHelper.sendUserActivaionMail(user,activationToken);
						model.addAttribute("pagestatus", "registration-success");
						model.addAttribute("registration-success-url", "index");
						logger.info("New user " + user.getUserName()
								+ " was successfully created.");
//					} else {
//						fieldError = new FieldError("user", "confirmPassword",
//								"Passowrd do not match. ");
//						result.addError(fieldError);
//					}
//				} else {
//					fieldError = new FieldError("user", "password",
//							"Invalid passowrd.");
//					result.addError(fieldError);
//				}
			} else {
				fieldError = new FieldError("user", "userName",
						"Username already registered.");
				result.addError(fieldError);
			}
		} else {
			fieldError = new FieldError("user", "email",
					"Email Id already registered.");
			result.addError(fieldError);
		}

		model.addAttribute("user", user);
		redirectAttributes.addFlashAttribute("user", user);

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
			return new ModelAndView("redirect:/users/signup-success");
		}
		
		return new ModelAndView("redirect:/users/signup-success");
		
	}
	
	@RequestMapping(value={ "/users/signup-success"}, method=RequestMethod.GET)
	public ModelAndView signupSuccess(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) {
		//model.addAttribute("user", new User());	
		return new ModelAndView("signup-success");
	}
	
	/*@RequestMapping({ "/", "/login" })
	public ModelAndView welcome_(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "action", required = false) String action) {
		model.addAttribute("user", new User());
		
		if(argHttpSession==null){
			model.addAttribute("sessionexpired", true);
			return new ModelAndView("login");
		}else{

			SecurityContextImpl impl = 	(SecurityContextImpl)argHttpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			if(impl!=null){
				Authentication authentication = impl.getAuthentication();
				User certUser =userService.findByUsername(authentication.getName());
				argHttpSession.setAttribute("certUser", certUser);
				
				WebAuthenticationDetails details = (WebAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
				String ip = details.getRemoteAddress();
			}
		
		}
		
		String message = (String)argHttpSession.getAttribute("Message");
		

		User userInContext = (User)argHttpSession.getAttribute("userInContext");
        boolean clockedin = argHttpSession.getAttribute("clockedin")==null ? false:(Boolean)argHttpSession.getAttribute("clockedin");
        Date clockedindateandtime = (Date)argHttpSession.getAttribute("clockedindateandtime");
        
        if(userInContext!=null && clockedin){
        	message = "You are clocked in now";
        	model.addAttribute("clockedindateandtime", clockedindateandtime);
        	model.addAttribute("userInContext", userInContext.getUserName());
        }
        
		model.addAttribute("message", message);
		return new ModelAndView("login");
	}*/
	
	@RequestMapping({"/clockin" })
	public ModelAndView clockout(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("user", new User());
		
		if(argHttpSession==null){
			model.addAttribute("sessionexpired", true);
			return new ModelAndView("login");
		}
		
		argHttpSession.setAttribute("action", "clockout");
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){    
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		    }
		    model.addAttribute("logout-sucess", "logout-sucess");
		    return new ModelAndView("redirect:/login?logout=cuccess");
		
	}

	@RequestMapping({ "/codespot-index" })
	public ModelAndView index(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUserName(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
       /* Integer pageNumber = 1;
        int resultSize=10;
        String sortByParam="bugId";*/
		
		model.addAttribute("userInContext",userInContext);
        
		return new ModelAndView("codespot-index");
	}
	
	/*@PreAuthorize("hasAuthority('ROLE_EMPL')")
	@RequestMapping({ "/calendar" })
	public ModelAndView calendar(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		User userInContext = (User)session.getAttribute("userInContext");
		if(userInContext!=null){
			model.addAttribute("userInContext",userInContext);
			model.addAttribute("userInContextId",String.valueOf(userInContext.getId()));
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
			return new ModelAndView("calendar");
		}
		return new ModelAndView("login");
	}*/
	
	/*@RequestMapping(value = "/useraction", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String Submit(@RequestParam("action") String action,@RequestParam("userid") String userid) throws UserNotFound {
		User userInContext = (User)session.getAttribute("userInContext");
		System.out.println("action :"+action+"\nuserid : "+userid);

		Date userClockActionTime = new Date();
		
		if(action!=null && userid!=null && userid.equals(String.valueOf(userInContext.getId()))){
			TimeClock timeClock = new TimeClock();
			TimeClock savedTimeCLock = null;
			
			if(!userInContext.getClockedInFlag()){
				timeClock.setUser(userInContext);
				timeClock.setBussinessdate(userClockActionTime);
				timeClock.setIntime(userClockActionTime);
				savedTimeCLock = clockService.createTimeClockEntry(timeClock);
			}else{
				timeClock = clockService.findById(userInContext.getTimeClockId());
				if(timeClock!=null){
					savedTimeCLock = clockService.updateTimeClockEntry(timeClock);
				}
			}
			
			if(savedTimeCLock!=null){
				if(action.equals("Clock-In")){
					userInContext.setClockedInFlag(true);
					userInContext.setTimeClockId(savedTimeCLock.getId());
					logger.info(userInContext.getUserName()+" clocked-in @"+savedTimeCLock.getIntime());
				}else if(action.equals("Clock-Out")){
					userInContext.setClockedInFlag(false);	
					userInContext.setTimeClockId(null);
					logger.info(userInContext.getUserName()+" clocked-out @"+savedTimeCLock.getOuttime());
				}
				userService.update(userInContext);
				return CodespotConstants.fmt.format(userClockActionTime);
			}
		}
		return null;
	}*/
	
	/*@RequestMapping(value = "/fetchUserAction", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String fetchUserAction(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) throws UserNotFound {
		User userInContext = (User)session.getAttribute("userInContext");
		if(userInContext.getClockedInFlag()){
			model.addAttribute("clockaction", "Clock-Out");
			model.addAttribute("btncolor", "btn-yellow");
			return "Clock-Out";
		}else{
			model.addAttribute("clockaction", "Clock-In");
			model.addAttribute("btncolor", "btn-success");
			return "Clock-In";
		}
	}*/
	
	/*@RequestMapping(value = "/userClockTimes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<TimeClockUiBean> userClockTimes(final Model model, final Locale locale,final RedirectAttributes redirectAttributes, HttpSession argHttpSession,  HttpServletRequest request, HttpServletResponse response) {
		User userInContext = (User)session.getAttribute("userInContext");
		model.addAttribute("userInContext",userInContext);
		model.addAttribute("userInContextId",String.valueOf(userInContext.getId()));
		List<TimeClockUiBean> userUiClockList = new ArrayList<TimeClockUiBean>();
		if(userInContext!=null){
			
			List<TimeClock> userClockList = clockService.findAllUserTimeClock(userInContext.getId());
			for (TimeClock timeClock : userClockList) {
				TimeClockUiBean  uiBean= new TimeClockUiBean();
				uiBean.setTimeClockIdId(String.valueOf(timeClock.getId()));
				uiBean.setUserId(String.valueOf(timeClock.getUser().getId()));
				uiBean.setBussinessDate(DateUtil.getDateInString(timeClock.getBussinessdate()));
				
				uiBean.setInTime(CodespotConstants.fmt.format(timeClock.getIntime()));
				if(timeClock.getOuttime()!=null){
					uiBean.setOutTime(CodespotConstants.fmt.format(timeClock.getOuttime()));
					uiBean.setDuration(DateUtil.getTimeElapsed(timeClock.getDuration()));
				}
				userUiClockList.add(uiBean);
			}
			model.addAttribute("userUiClockList", userUiClockList);
			
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
		}
		return userUiClockList;
		
	}*/
	
	
	//---------------------ADMIN MODULE ------------------------------------------------
	
	/*@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping({ "/tables" })
	public ModelAndView tables(final Model model, final Locale locale, HttpSession session) throws ParseException {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
		
		model.addAttribute("userInContext",userInContext);
		
		List<TimeClockUiBean> userUiClockList = new ArrayList<TimeClockUiBean>();
		if(userInContext!=null){
			
			List<TimeClock> userClockList = clockService.displayTodaysEmployeeStatus(new Date());
			for (TimeClock timeClock : userClockList) {
				
				User user = timeClock.getUser();
				TimeClockUiBean  uiBean= new TimeClockUiBean();

				uiBean.setEmpId(String.valueOf(user.getId()));
				uiBean.setEmpEmail(user.getUserEmail());
				uiBean.setEmpName(user.getUserName());
				uiBean.setEmpClockedInFlag(user.getClockedInFlag());
				uiBean.setTimeClockIdId((String.valueOf(timeClock.getId())));
				uiBean.setUserId(user.getUserEmail());
				uiBean.setBussinessDate(DateUtil.getDateInString(timeClock.getBussinessdate()));
				uiBean.setInTime(new SimpleDateFormat("hh:mm a").format(timeClock.getIntime()));
				if(timeClock.getOuttime()!=null){
					try {
						uiBean.setOutTime(new SimpleDateFormat("hh:mm a").format(timeClock.getOuttime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					uiBean.setDuration(DateUtil.getTimeElapsed(timeClock.getDuration()));
				}
				userUiClockList.add(uiBean);
			}
			model.addAttribute("userUiClockList", userUiClockList);
			
			if(userInContext.getClockedInFlag()){
				model.addAttribute("clockaction", "Clock-Out");
				model.addAttribute("btncolor", "btn-yellow");
			}else{
				model.addAttribute("clockaction", "Clock-In");
				model.addAttribute("btncolor", "btn-success");
			}
		}
        
		return new ModelAndView("tables");
	}*/
	
	
	/*private void createCodespotUsers(User user){
		String activationToken = new SessionIdentifierGenerator().generatePasswordResetToken();
		UserPassword userPassword = new UserPassword();
		userPassword.setEmail(user.getUserEmail());
		userPassword.setActivationToken(activationToken);
		userPassword.setActivationTokenTimestamp(new Date());
		user.setUserPassword(userPassword);
		userService.save(user);
	}*/
	
	
	
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	//----------------------UN USED---------------------------------------------------------------------------
	/*@RequestMapping({ "/index" })
	public ModelAndView index(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
        Integer pageNumber = 1;
        int resultSize=10;
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
	
	/*@RequestMapping({ "/manager-index" })
	public ModelAndView managerIndex(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        SecurityContextImpl securityContextImpl = null;
        if(userInContext==null){
        	if(session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null){
        		securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        		org.springframework.security.core.Authentication authentication= securityContextImpl.getAuthentication();
        		String name = authentication.getName();
        		session.setAttribute("userInContext", userService.findByUsername(name));
        		model.addAttribute("userInContext", userInContext);
        	}
        }
        
        session.setAttribute("clientInContext", new Client());
        
        Integer pageNumber = 1;
        int resultSize=10;
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
        
		return new ModelAndView("manager-index");
	}*/
	
	
	@RequestMapping("/error") 
    public String loginError(Model model, Exception e) { 
        model.addAttribute("loginError", true); 
        model.addAttribute("isLdapConfigured", isLdapConfigured); 
 
        if (userIsLocked()) { 
            model.addAttribute("errorKey", "login.lock"); 
            model.addAttribute("templockTimeout", templockTimeout); 
        } else { 
            model.addAttribute("errorKey", "login.error"); 
        } 
 
        return "login"; 
    } 
	
	private boolean userIsLocked() { 
        return session.getAttribute("IS_LOCKED") != null && session.getAttribute("IS_LOCKED") instanceof Boolean 
                && (Boolean) session.getAttribute("IS_LOCKED"); 
 
    } 
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage (final Model model, final Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    model.addAttribute("logout-sucess", "logout-sucess");
	    return new ModelAndView("redirect:/login?logout=cuccess");
	}
	
}
>>>>>>> post-chat
