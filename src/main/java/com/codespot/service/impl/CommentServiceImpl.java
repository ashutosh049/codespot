package com.codespot.service.impl;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespot.dao.ICommentDao;
import com.codespot.dao.common.IOperations;
import com.codespot.model.Comment;
import com.codespot.repository.UserRepository;
import com.codespot.service.ICommentService;
import com.codespot.service.common.AbstractJpaService;

@Service
public class CommentServiceImpl extends AbstractJpaService<Comment>implements ICommentService {

	@Resource
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ICommentDao dao;

	public CommentServiceImpl() {
		super();
	}

	@Override
	protected IOperations<Comment> getDao() {
		return dao;
	}
	
}
