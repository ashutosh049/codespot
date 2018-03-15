package com.codespot.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import com.codespot.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.codespot.model.Question;
import com.codespot.model.User;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;

//import freemarker.template.utility.StringUtil;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Value("${ldap.enabled:false}") 
	private boolean isLdapConfigured; 
    @Value("${tempLock.timeout:0}") 
    private int templockTimeout;
    
    @Value("${app.root.dir}") 
    private String app_root_dir;
    
	
	@Autowired private IUserService userService;
	@Autowired private IQuestionService questionService;
	@Autowired private HttpSession session;
	@Autowired private ServletContext servletContext;

	private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	ObjectError error;
	FieldError fieldError;
	
	@RequestMapping({ "/ask"})
	public ModelAndView editor(final Model model, final Locale locale,final RedirectAttributes redirectAttributes,	HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) {
		User userInContext = (User)session.getAttribute("userInContext");
		model.addAttribute("question",new Question());
		model.addAttribute("userInContext",userInContext);
		return new ModelAndView("question_ask");
	}
	
	@RequestMapping(value={ "/create"}, method=RequestMethod.POST)
	public ModelAndView createQuestion(ModelMap model,	@ModelAttribute @Valid Question question, BindingResult result,
			final RedirectAttributes redirectAttributes,HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Title : "+question.getQuestionTitle());
		System.out.println("Tags : "+question.getQuestionTags());
		//String escapedContent = StringEscapeUtils.escapeHtml4(question.getQuestionDescription());
		//String escapedContent = StringUtils.escape(question.getQuestionDescription());
//		String escapedContent = com.codespot.util.StringUtils.(question.getQuestionDescription(), false);
//		String escapedContent = HtmlUtils.htmlEscape(question.getQuestionDescription(), "UTF8");
//		String escapedContent = question.getQuestionDescription();//.replace("/<\/?[^>]+(>|$)/g", "");
		String content = question.getQuestionDescription();
		User userInContext = (User)session.getAttribute("userInContext");
		
		if(userInContext==null){
			userInContext = userService.findByUserName((String)session.getAttribute("username"));
		}
		
		question.setUser(userInContext);
		//question.setQuestionShortDescription(escapedContent.substring(0,150));
		question.setQuestionDescription("");
		
		long currentTimestamp = System.currentTimeMillis();
		
		question.setCreateTimestamp(new Timestamp(currentTimestamp));
		question.setLastModifiedTimestamp(new Timestamp(currentTimestamp));

		Question savedQuestion = questionService.create(question);
		
		//String rootDir = servletContext.getRealPath(request.getContextPath());
		String rootDir = app_root_dir;
		if(savedQuestion!=null){
			File dir = new File(rootDir+"/"+userInContext.getUserName()+"/"+savedQuestion.getQuestionId());
			if(dir.mkdirs()){
				File questionFile = new File(dir.getAbsolutePath()+"/"+savedQuestion.getQuestionTitle()+".html");
				try {
					FileUtils.writeStringToFile(questionFile, content, "UTF8");
					savedQuestion.setQuestionFilePath(questionFile.getAbsolutePath());
					questionService.update(savedQuestion);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}else{
				logger.error("Error creating dir structure: "+dir.getAbsolutePath());
			}
		}else{
			logger.error("Error creating Question entiy in table.");
		}
		return new ModelAndView("redirect:/questions/"+savedQuestion.getQuestionId());
	}

	@RequestMapping(value = { "/{questionId}" }, method = RequestMethod.GET)
	public ModelAndView showQuestionWithId(ModelMap model, @PathVariable("questionId") Long questionId,
			HttpSession argHttpSession, HttpServletRequest request, HttpServletResponse response,
			final RedirectAttributes redirectAttributes) throws Exception {
		Question questionFetched = questionService.findOne(questionId);
		String questionTitle = StringUtils.replace(questionFetched.getQuestionTitle().trim(), " ", "-");
		redirectAttributes.addFlashAttribute("questionFetched", questionFetched);
		return new ModelAndView("redirect:/questions/" + questionId + "/" + questionTitle);
	}
	
	@RequestMapping(value = { "/{questionId}/{questionTitle}" }, method = RequestMethod.GET)
	public ModelAndView showQuestionWithIdandTitle(ModelMap model, 
			@PathVariable("questionId") Long questionId,
			@PathVariable("questionTitle") String questionTitle,
			//@ModelAttribute("questionFetched") Question questionFetched, 
			HttpSession argHttpSession,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		Question question = null;

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if (flashMap != null) {
			question = (Question) flashMap.get("questionFetched");
		}

		if (question == null) {
			question = questionService.findOne(questionId);
		}
		
		if (question != null) {
			String content = null;
			File file = null;
			String questionFilePath = question.getQuestionFilePath();
			if(!StringUtils.isEmpty(questionFilePath)){
				file = new File(questionFilePath);
				if(!file.exists()){
					logger.error("File not exists : "+file.getAbsolutePath());
				}else{
					content = FileUtils.readFileToString(file, "UTF8");
				}
			}else{
				logger.error("File path is null.");
			}
			question.setQuestionDescription(content);
		}
		
		model.addAttribute("question", question);
		return new ModelAndView("/question_show");
	}

}
