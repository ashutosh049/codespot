package com.codespot.dao.impl;

import org.springframework.stereotype.Repository;

import com.codespot.dao.IQuestionDao;
import com.codespot.dao.common.AbstractJpaDao;
import com.codespot.model.Question;
@Repository
public class QuestionDao extends AbstractJpaDao<Question>implements IQuestionDao {
	public QuestionDao() {
		super();
		setClazz(Question.class);
	}
}