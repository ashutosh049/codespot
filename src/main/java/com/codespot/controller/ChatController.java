<<<<<<< HEAD
package com.codespot.controller;

import java.security.Principal;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.codespot.model.ChatMessage;
import com.codespot.model.Comment;
import com.codespot.model.Question;
import com.codespot.service.ICommentService;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;

@RestController
public class ChatController {


	private static final Logger logger = Logger.getLogger(ChatController.class);
			
	@Autowired private IUserService userService;
	@Autowired private IQuestionService questionService;
	@Autowired private ICommentService commentService;
	@Autowired private SimpMessagingTemplate simpMessagingTemplate;
	
	@SubscribeMapping("/codespotQEP/{questionId}")
	@SendTo("/topic/postComment/{questionId}")
    public Comment subscribeMapping(@DestinationVariable Long questionId,Comment comment) throws Exception {
		
		logger.info("@SubscribeMapping : /codespotQEP/"+questionId);
		logger.info("@SendTo : /topic/postComment/"+questionId);
		logger.info("Comment : [content="+comment.getCommentDescription()+"][username="+comment.getUser().getUserName()+"]");

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
				}else logger.error("Saved comment is null....!!");
			}else logger.error("question is null....!!");
		}else logger.error("User is not authentic.. maybe different....!!");
	 return null;
    }
	
	@MessageMapping("/chat")
	public void personalMessage2(SimpMessageHeaderAccessor headerAccessor, ChatMessage message) {
		 Principal principal = headerAccessor.getUser();
		 String authedSender = principal.getName();
	    SimpMessageHeaderAccessor ha = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	    ha.setSessionId(headerAccessor.getSessionId());
	    ha.setLeaveMutable(true);
	    ChatMessage reply = new ChatMessage(message.getFrom(), message.getTo(),"Reply..." + message.getMessage());
	    simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/messages", reply, ha.getMessageHeaders());
	}
	
	/**
     * If there are any exceptions thrown by any of the messaging infrastructure
     * then they can be sent to the end user on the <code>/queue/errors</code>
     * destination.
     * 
     * @param exception
     * @return
     */
    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
	
}
=======
package com.codespot.controller;

import java.security.Principal;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.codespot.model.ChatMessage;
import com.codespot.model.Comment;
import com.codespot.model.Question;
import com.codespot.service.ICommentService;
import com.codespot.service.IQuestionService;
import com.codespot.service.IUserService;

@RestController
public class ChatController {


	private static final Logger logger = Logger.getLogger(ChatController.class);
			
	@Autowired private IUserService userService;
	@Autowired private IQuestionService questionService;
	@Autowired private ICommentService commentService;
	@Autowired private SimpMessagingTemplate simpMessagingTemplate;
	
	@SubscribeMapping("/codespotQEP/{questionId}")
	@SendTo("/topic/postComment/{questionId}")
    public Comment subscribeMapping(@DestinationVariable Long questionId,Comment comment) throws Exception {
		
		logger.info("@SubscribeMapping : /codespotQEP/"+questionId);
		logger.info("@SendTo : /topic/postComment/"+questionId);
		logger.info("Comment : [content="+comment.getCommentDescription()+"][username="+comment.getUser().getUserName()+"]");

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
				}else logger.error("Saved comment is null....!!");
			}else logger.error("question is null....!!");
		}else logger.error("User is not authentic.. maybe different....!!");
	 return null;
    }
	
	@MessageMapping("/chat")
	public void personalMessage2(SimpMessageHeaderAccessor headerAccessor, ChatMessage message) {
		 Principal principal = headerAccessor.getUser();
		 String authedSender = principal.getName();
	    SimpMessageHeaderAccessor ha = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	    ha.setSessionId(headerAccessor.getSessionId());
	    ha.setLeaveMutable(true);
	    ChatMessage reply = new ChatMessage(message.getFrom(), message.getTo(),"Reply..." + message.getMessage());
	    simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/messages", reply, ha.getMessageHeaders());
	}
	
	/**
     * If there are any exceptions thrown by any of the messaging infrastructure
     * then they can be sent to the end user on the <code>/queue/errors</code>
     * destination.
     * 
     * @param exception
     * @return
     */
    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
	
}
>>>>>>> post-chat
