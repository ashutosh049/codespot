package com.codespot.controller;

import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/auth/project")
public class AuthProjectController {

	@Autowired
	private IUserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private SecurityService securityService;
	

	private final Logger logger = LoggerFactory
			.getLogger(AuthProjectController.class);

	ObjectError error;
	FieldError fieldError;

	
	/*@RequestMapping({ "/reportBug" })
	public ModelAndView reportBug(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        model.addAttribute("userInContext", userInContext);
        List<Project>  projectList= projectRepository.findAllByUserId(userInContext.getId());
        model.addAttribute("projectList", projectList);
        model.addAttribute("project", new Project());
        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("bug", new Bug());
        model.addAttribute("bugForm", new BugForm());
        
		return new ModelAndView("report-bug-page");
	}*/
	
	/*@RequestMapping({ "/requestNewClient" })
	public ModelAndView requestNewClient(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        model.addAttribute("userInContext", userInContext);
        model.addAttribute("client", new Client());
        model.addAttribute("project", new Project());
        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("bug", new Bug());
        model.addAttribute("bugForm", new BugForm());
        
        List<Project>  projectList= projectRepository.findAllByUserId(userInContext.getId());
        model.addAttribute("projectList", projectList);
        
		return new ModelAndView("add-client");
	}*/
	

	/*@RequestMapping(value={ "/getClientList" }, method = RequestMethod.GET)
	public List<Client> getClientList(final Model model, final Locale locale, HttpSession session) {
        User userInContext = (User)session.getAttribute("userInContext");
        List<Client>  clientList=new ArrayList<Client>();
        clientList =  getClientList(userInContext.getId());
    	model.addAttribute("clientList", clientList);
		return clientList;
	}*/
	
	/*List<Client> getClientList(Long id){
		return clientRepository.findAllByUserId(id);
	}*/
	
	
	/*@RequestMapping({ "/addNewClient" })
	public ModelAndView addNewClient(final Model model, final Locale locale, HttpSession session, @ModelAttribute @Valid Client client) {
		User userInContext = (User)session.getAttribute("userInContext");
		client.setUser(userInContext);
		Client client_ = clientSevice.save(client);
		return new ModelAndView("redirect:/requestNewClient");
	}*/

	/*@RequestMapping(value = "/ceateProject", method = RequestMethod.POST)
	public ModelAndView ceateProject(ModelMap model, @ModelAttribute @Valid Project project, BindingResult result,
			final RedirectAttributes redirectAttributes,  HttpSession session) {
		    User userInContext = (User)session.getAttribute("userInContext");
	        project.setUser(userInContext);
	        projectService.save(project);
		return new ModelAndView("redirect:reportBug");
	}*/
	
	
	
	
	
	/*@RequestMapping(value = "/reportBug_", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public @ResponseBody Bug reportBugAjaxSubmit_(HttpServletRequest request,@RequestBody Bug bug, BindingResult result) throws IOException {
		
		Attachment attachment = bug.getAttachments().iterator().next();
		attachment.setAttachmentName(""+new Random().nextInt());
		attachment.setAttachmentType("BUG");
		
		Set<Attachment> attachmentSet = new HashSet<Attachment>();
		attachmentSet.add(attachment);
		
		bug.setAttachments(bug.getAttachments());
		
		bug = bugService.save(bug);
		return bug;
	}*/
	
	
	/* @RequestMapping(value = "upload-single", method = RequestMethod.POST)
	 public String uploadFileHandler(@RequestParam MultipartFile file, HttpServletRequest request) {
	        String url;
	        String storedFolderLocation = createStoredFolder(request);
	            String uploadedFileName = file.getOriginalFilename();
	            try {
	                byte[] bytes = file.getBytes();
	 
	                String storedFileLocation = storedFolderLocation + "/" + uploadedFileName;
	                // Create the file on server
	                File serverFile = new File(storedFileLocation);
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                url = getDomainName(request)
	                        + getRelativePath() + "/" + uploadedFileName;
	                if (isFileTypeImage(uploadedFileName)) {
	                   url= "<img src=\"" + url + "\" />";
	                } else {
	                    url= "<a href=\"" + url + "\">" + url + "</a>";
	                }
	 
	            } catch (Exception e) {
	                return e.getMessage();
	            }
	        return "Loaded File:"+url;
	    }*/

	
	 /*@RequestMapping(value = "/doUpload", method = RequestMethod.GET)
	    public String handleFileUpload(HttpServletRequest request,
	    		@RequestParam("fileUpload") CommonsMultipartFile[] fileUpload) throws Exception {
	          
	        if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                Attachment attachment = new Attachment();
	                attachment.setAttachmentName(aFile.getOriginalFilename());
	                attachment.setAttachmentType("BUG");
	                attachmentService.save(attachment);
	            }
	        }
	  
	        return "Success";
	    }*/
	
	 private String createStoredFolder(HttpServletRequest request) {
	        String realPath = request.getSession().getServletContext().getRealPath("/");
	        String relativePath = getRelativePath();
	        String storedFolderLocation = realPath + relativePath;
	        File dir = new File(storedFolderLocation);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        return storedFolderLocation;
	    }
	    private boolean isFileTypeImage(String fileName) {
	        String imagePattern =
	                "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
	        return Pattern.compile(imagePattern).matcher(fileName).matches();
	 
	    }
	    private String getRelativePath() {
	        String fileSeparator = "/";
	        return "/resources/uploads/"+new Random().nextInt(1000);
	    }
	    private String getDomainName(HttpServletRequest request) {
	        return request.getProtocol().toLowerCase().replaceAll("[0-9./]", "") + "://" +
	                request.getServerName() + ":" + request.getServerPort();
	    }
	    
	    
	
}
