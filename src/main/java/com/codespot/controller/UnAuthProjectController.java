package com.codespot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespot.repository.UserRepository;
import com.codespot.service.IUserService;
import com.codespot.service.SecurityService;

@RestController
@RequestMapping("/unauth/bug")
public class UnAuthProjectController {

	@Autowired
	private IUserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	private final Logger logger = LoggerFactory
			.getLogger(UnAuthProjectController.class);

	ObjectError error;
	FieldError fieldError;

/*	@RequestMapping(value = "/show-bug", method = RequestMethod.GET)
	public ModelAndView reportBugByAdmin(final Model model, final Locale locale, HttpSession session,@RequestParam("Id") Long Id) {
		Bug searchedBug = bugService.findById(Id);
		List<Bug> searchBugList = bugRepository.findAllLike(String.valueOf(Id));
		User userInContext = (User)session.getAttribute("userInContext");
		model.addAttribute("userInContext",userInContext);
		if(searchedBug==null){
			if(!ArrayUtils.isEmpty(searchBugList)){
				model.addAttribute("searchBugList",searchBugList);
				return new ModelAndView("show-bug-list");
			}else if(ArrayUtils.isOfSize1(searchBugList)){
				model.addAttribute("searchedBug",searchBugList.get(0));
				return new ModelAndView("show-bug");
			}
		}else{
			
			Map<Long,String> attachmentList = new HashMap<Long,String>();
			for (Attachment attachment : searchedBug.getAttachments()) {
				attachmentList.put(attachment.getAttachmentId(), attachment.getAttachmentName());
			}
			
			List<Comment> commentList = new ArrayList<Comment>(searchedBug.getComments());
			
			model.addAttribute("searchedBug",searchedBug);
			model.addAttribute("attachmentList",attachmentList);
			model.addAttribute("commentForm",new CommentForm());
			model.addAttribute("commentList",commentList);
			return new ModelAndView("show-bug");
		}
		
		return new ModelAndView("show-bug-not-found");
	}*/
	
	/*@RequestMapping(value = "/show-bug-all", method = RequestMethod.GET)
	public ModelAndView showBugAll(final Model model, final Locale locale, HttpSession session) {
		
		User userInContext = (User)session.getAttribute("userInContext");

		Page<Bug> searchPage = bugService.findAllByUserId(0);
		
		 int current = searchPage.getNumber() + 1;
		 int begin = Math.max(1, current - 5);
		 int end = Math.min(begin + 10, searchPage.getTotalPages());
		
		model.addAttribute("userInContext",userInContext);
		return new ModelAndView("show-bug");
		
	}*/
	
	/*@RequestMapping(value = "/download/attachment/{attachment_id}", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,  HttpServletResponse response,@PathVariable("attachment_id") long attachment_id) throws IOException {
        Attachment att = attachmentService.findById(attachment_id);
        response.setContentType(att.getAttachmentType());
        response.setContentLength(att.getAttachmentData().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + att.getAttachmentName() +"\"");
        FileCopyUtils.copy(att.getAttachmentData(), response.getOutputStream());
    }*/

	
	/*@RequestMapping(value = "/postcomment/{bugId}", method = RequestMethod.POST)
    public @ResponseBody ModelAndView myFormUpload(@ModelAttribute CommentForm commentForm,@PathVariable("bugId") long bugId, HttpSession session) throws IOException {
    	
		User userInContext = (User)session.getAttribute("userInContext");
		
		if(userInContext!=null && commentForm!=null){
    		Bug bug = bugService.findById(bugId);
    		Set<Attachment> attachmentSet = new HashSet<Attachment>();
    		
    		for (MultipartFile file : commentForm.getMyfile()) {
    			Attachment attachment = new Attachment();
    			attachment.setAttachmentData(file.getBytes());
				attachment.setAttachmentName(file.getOriginalFilename());
				attachment.setAttachmentType("COMMENT");
				attachmentSet.add(attachment);
			}
    		
    		Comment comment = new Comment();
    		comment.setCommentedBy(userInContext.getUserName());
    		comment.setUserId(userInContext.getId());
    		comment.setCreateDate(new Date());
    		comment.setDescription(commentForm.getDescription());
    		comment.setBug(bug);
    		comment.setAttachments(attachmentSet);
    		
    		commentService.save(comment);
    		return new ModelAndView("redirect:/unauth/bug/show-bug?Id="+bug.getBugId());
    	}
    	return new ModelAndView("error");
    }*/
	
	/*@RequestMapping(value = "/update/status/{bugId}/{currentStatus}/{newStatus}", method = RequestMethod.POST)
    public String updateStatus(HttpServletRequest request,  HttpServletResponse response,@PathVariable("bugId") long bugId,
    		@PathVariable("currentStatus") String currentStatus,@PathVariable("newStatus") String newStatus) throws IOException {
		if(currentStatus!=null && newStatus!=null){
			Bug bug = bugService.findById(bugId);
			if(bug!=null){
				if(bug.getStatus().equals(BugStatus.valueOf(currentStatus))){
					bug.setStatus(BugStatus.valueOf(newStatus));
					int updated = bugRepository.updateBugStatus(bug.getStatus(),bug.getBugId());
					if(updated==1){
						return "SUCCESS";
					}
					return "ERROR";
				}
				return "COLLISION";
			}
		}
		return "ERROR";
    }*/
}
