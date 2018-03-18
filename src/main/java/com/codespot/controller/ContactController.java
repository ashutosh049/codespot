package com.codespot.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codespot.helper.ModuleControllerHelper;
import com.codespot.model.ContactType;
import com.codespot.model.Friends;
import com.codespot.model.User;
import com.codespot.model.ui.ContactCard;
import com.codespot.service.IFriendsService;
import com.codespot.service.IUserService;

@RestController
@RequestMapping({ "/searchusers" })
public class ContactController {

	@Autowired
	private IUserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private IFriendsService friendsService;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	private final Logger logger = LoggerFactory.getLogger(ContactController.class);

	ObjectError error;
	FieldError fieldError;

	@GET
	@RequestMapping({ "" })
	public ModelAndView searchusers(final Model model, final Locale locale, HttpSession argHttpSession) {
		return new ModelAndView("search-users");
	}

//	@RequestMapping({ "/{getAllAddableFriends}", "/{getAllAddableFriends}", "/{getAllAddableFriends}" })
	@GET
	@RequestMapping({ "/{contacttype}"})
	public ContactCard getAllAddableFriends(@PathVariable("contacttype") String contacttype ,@RequestParam(value = "sd", required = false) String sd,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		if (pageNo == null)
			pageNo = 1;

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();

		User userInContext = (User) session.getAttribute("userInContext");

		if (!userInContext.getUserName().equals(name)) {
			logger.error("Malicious user... " + userInContext.getUserName() + " and " + name);
			return null;
		}
		
		ContactType type = ModuleControllerHelper.getContactType(contacttype);

		Page<User> userSearchListPage = userService.findAllContactType(type, userInContext.getUserId(), pageNo, 6,
				Sort.Direction.DESC, "userName");

		List<User> userList = userSearchListPage.getContent();
		int totalElm = (int) userSearchListPage.getTotalElements();
		int current = userSearchListPage.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, userSearchListPage.getTotalPages());

		ContactCard card = new ContactCard();
		card.setUserList(userList);
		card.setTotalPages(userSearchListPage.getTotalPages());
		card.setBeginIndex(begin);
		card.setEndIndex(end);
		card.setCurrentIndex(current);
		card.setTotalElm(totalElm);
		return card;
	}

/*	String data = "<div class='col-md-4 list-results list-results-underlined' id='contact_data'> "
			        + "<div class='card' style='padding-top: 0px; padding-bottom: 0px;'> "
			        	+ "<div class='card-head'> "
			        		+ "<header> "
			        			+ "<div class='hbox-column' style='width: 60px;'> "
			        				+ "<img class='img-circle img-responsive pull-left' src='${contextPath}/resources/img/avtars/a ("+key+").jpg' alt='' /> "
        						+ "</div> "
        						+ "<a class='text-sm text-faded' href='#'>"+value[3]+"</a> "
							+ "</header> "
							+ "<div class='tools'> "
								+ "<div class='btn-group'> "
									+ "<a class='btn btn-icon-toggle btn-close'> "
										+ "<i class='md md-close'> </i> "
									+ "</a> "
								+ "</div> "
							+ "</div> "
						+ "</div> "
						+ "<div class='card-body'> "
							+ "<div class='clearfix opacity-75'> "
								+ "<span class='glyphicon glyphicon-envelope text-sm'></span> "
									+ "&nbsp;"+value[2]+" <p>Etiam porta sem malesuada magna mollis euismod</p> "
								+ "</div> "
							+ "</div> "
							+ "<div class='card-actionbar'> "
								+ "<div class='card-actionbar-row'> "
									+ "<button type='button' class='btn btn-default-light ink-reaction' onClick='sendFR(event,"+value[0]+");'> "
											+ "<i class='fa fa-user-plus text-success'></i> "
											+ "connect "
									+ "</button> "
								+ "</div> "
							+ "</div> "
						+ "</div> "
					+ "</div>";
*/	
	/*
	 * @GET
	 * 
	 * @RequestMapping({"/getPendingFRList"}) public ContactCard
	 * getPendingFRList(final Model model, final Locale locale, HttpSession
	 * argHttpSession, @RequestParam(value="sd", required=false) String
	 * sd, @RequestParam(value="pageNo", required=false) Integer pageNo) {
	 * 
	 * org.springframework.security.core.userdetails.User user =
	 * (org.springframework.security.core.userdetails.User)SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); String name =
	 * user.getUsername();
	 * 
	 * User userInContext = (User)session.getAttribute("userInContext");
	 * 
	 * if(!userInContext.getUserName().equals(name)){ logger.error(
	 * "Malicious user... "+userInContext.getUserName() +" and "+ name); return
	 * null; }
	 * 
	 * List<Friends> pendingFRList =
	 * friendsService.findAllPendingFR(userInContext); return pendingFRListl }
	 */

	@MessageMapping("/frEP")
	public boolean frendrequest(SimpMessageHeaderAccessor headerAccessor, @Payload String addUserId) {

		Principal principal = headerAccessor.getUser();
		String sender = principal.getName();
		User userInContext = userService.findByUserName(sender);
		String to = null;
		if (sender.equals(userInContext.getUserName())) {
			User userToAdd = userService.findOne(Long.valueOf(addUserId));
			if (userToAdd != null && userToAdd.getUserActive()) {
				to = userToAdd.getUserName();

				Friends friends = new Friends(userInContext, userToAdd, 0);
				friendsService.create(friends);

				friends = new Friends(userToAdd, userInContext, 0);
				friendsService.create(friends);

				SimpMessageHeaderAccessor ha = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
				ha.setSessionId(headerAccessor.getSessionId());
				ha.setLeaveMutable(true);
				userInContext.setActivationTokem(null);
				userInContext.setUserPassword(null);
				simpMessagingTemplate.convertAndSendToUser(to, "/queue/addFriend", userInContext,
						ha.getMessageHeaders());
				return true;
			}
		}
		return false;
	}

	/*
	 * @GET
	 * 
	 * @RequestMapping({"/sendUsersRequest"}) public ModelAndView
	 * sendUsersRequest(final Model model, final Locale locale, HttpSession
	 * argHttpSession, @RequestParam(value="adduser") Long adduser) { User
	 * userInContext = (User)session.getAttribute("userInContext"); User
	 * userToAdd = userService.findOne(adduser); if(userToAdd!=null &&
	 * userToAdd.getUserActive()){ Friends friends = new
	 * Friends(userInContext,userToAdd,0); friendsService.create(friends); }
	 * return new ModelAndView("search-users"); }
	 */

}
