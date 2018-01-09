package com.codespot.dao.impl;

import org.springframework.stereotype.Repository;

import com.codespot.dao.ICommentDao;
import com.codespot.dao.common.AbstractJpaDao;
import com.codespot.model.Comment;

@Repository
public class CommentDao extends AbstractJpaDao<Comment>implements ICommentDao {
	public CommentDao() {
		super();
		setClazz(Comment.class);
	}
}