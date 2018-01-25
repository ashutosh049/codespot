package com.codespot.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.codespot.model.Comment;
import com.codespot.model.Question;
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
	

	
	/*@MessageMapping("/add" )
    @SendTo("/topic/showResult")
    public Result addNum(CalcInput input) throws Exception {
        Thread.sleep(2000);
        Result result = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2())); 
        return result;
    }*/
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	/*@MessageMapping("/questionComment")
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
