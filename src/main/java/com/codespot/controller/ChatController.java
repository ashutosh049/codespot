package com.codespot.controller;

import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codespot.model.CalcInput;
import com.codespot.model.Comment;
import com.codespot.model.Question;
import com.codespot.model.Result;
import com.codespot.service.ICommentService;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;

@RestController
//@RequestMapping("/mvc")
public class ChatController {


	private static final Logger logger = Logger.getLogger(ChatController.class);
			
	@Autowired private IUserService userService;
	@Autowired private IQuestionService questionService;
	@Autowired private ICommentService commentService;
	@Autowired private HttpSession session;
	
//	private final ChatRepository chatRepository;
//
//	private final Map<DeferredResult<List<String>>, Integer> chatRequests =
//			new ConcurrentHashMap<DeferredResult<List<String>>, Integer>();


//	@Autowired
//	public ChatController(ChatRepository chatRepository) {
//		this.chatRepository = chatRepository;
//	}
	
//	@RequestMapping(value="/chat1",method=RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView editor(final Model model) {
//		return new ModelAndView("commonchat");
//	}
	
//	@RequestMapping(value="/chat",method=RequestMethod.GET)
//	@ResponseBody
//	public DeferredResult<List<String>> getMessages(@RequestParam(required=false) Object messageIndex) {
//		
//		int messageIndex_ = 0;
//		
//		if(messageIndex instanceof Integer){
//			messageIndex_ = Integer.valueOf((String)messageIndex);
//		}
//		
//		final DeferredResult<List<String>> deferredResult = new DeferredResult<List<String>>(null, Collections.emptyList());
//		
//		this.chatRequests.put(deferredResult, messageIndex_);
//
//		deferredResult.onCompletion(new Runnable() {
//			public void run() {
//				chatRequests.remove(deferredResult);
//			}
//		});
//
//		List<String> messages = this.chatRepository.getMessages(messageIndex_);
//		
//		if (!messages.isEmpty()) {
//			deferredResult.setResult(messages);
//		}
//
//		return deferredResult;
//		
//	}

//	@RequestMapping(value="/chat",method=RequestMethod.POST)
//	@ResponseBody
//	public void postMessage(@RequestParam String message) {
//
//		this.chatRepository.addMessage(message);
//
//		// Update all chat requests as part of the POST request
//		// See Redis branch for a more sophisticated, non-blocking approach
//
//		for (Entry<DeferredResult<List<String>>, Integer> entry : this.chatRequests.entrySet()) {
//			List<String> messages = this.chatRepository.getMessages(entry.getValue());
//			entry.getKey().setResult(messages);
//		}
//	}
	
	@MessageMapping("/add" )
    @SendTo("/topic/showResult")
    public Result addNum(CalcInput input) throws Exception {
        Thread.sleep(2000);
        Result result = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2())); 
        return result;
    }
	
	/*@PreAuthorize("hasRole('ROLE_USER')")
	@MessageMapping("/questionComment")
	@SendTo("/topic/postComment/{questionId}")
    public Comment postComment(@DestinationVariable Long questionId, Comment comment) throws Exception {
		logger.info("@MessageMapping : questionComment/"+questionId);
		logger.info("Comment : [content="+comment.getCommentDescription()+"][username="+comment.getUser().getUserName()+"]");
		if(comment.getUser().getUserName() == SecurityContextHolder.getContext().getAuthentication().getName()){
			comment.setUser(userService.findByUserName(comment.getUser().getUserName()));
			long currentTimestamp = System.currentTimeMillis();
			comment.setCreateTimestamp(new Timestamp(currentTimestamp));
			comment.setLastModifiedTimestamp(new Timestamp(currentTimestamp));
			comment.setQuestion(questionService.findOne(questionId));
			Comment savedComment = commentService.create(comment);
			if(savedComment!=null){
				logger.info("Comment persisted : "+savedComment.getCommentId());
				comment.setCommentId(savedComment.getCommentId());
			}
		}
        return comment;
    }*/
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@SubscribeMapping("/questionComment/{questionId}")
	@SendTo("/topic/postComment/{questionId}")
    public Comment subscribeMapping(@DestinationVariable Long questionId,Comment comment) throws Exception {
		logger.info("@SubscribeMapping : questionComment/"+questionId);
		logger.info("Comment : [content="+comment.getCommentDescription()+"][username="+comment.getUser().getUserName()+"]");
		
		//Check if comment by user is same as logged in user
		if(comment.getUser().getUserName().trim().equals(SecurityContextHolder.getContext().getAuthentication().getName()) ){
			
			Question question = questionService.findOne(questionId);
			
			if(question!=null){
				comment.setUser(userService.findByUserName(comment.getUser().getUserName()));
				long currentTimestamp = System.currentTimeMillis();
				comment.setCreateTimestamp(new Timestamp(currentTimestamp));
				comment.setLastModifiedTimestamp(new Timestamp(currentTimestamp));
				
				comment.setQuestion(question);
				Comment savedComment = commentService.create(comment);
				
				if(savedComment!=null){
					logger.info(">>>>>>>>>>Comment persisted [CommentId : "+savedComment.getCommentId()+"] <<<<<<<<<");
					logger.info(savedComment.toString());
					return savedComment;
				}
			}
		}
	 return null;
    }
	
    @RequestMapping("/start")
    public ModelAndView start() {
        return new ModelAndView("start");
    }
	
}
