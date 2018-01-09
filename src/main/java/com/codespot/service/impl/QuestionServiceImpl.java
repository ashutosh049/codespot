package com.codespot.service.impl;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codespot.dao.IQuestionDao;
import com.codespot.dao.common.IOperations;
import com.codespot.model.Question;
import com.codespot.repository.QuestionRepository;
import com.codespot.service.IQuestionService;
import com.codespot.service.common.AbstractJpaService;

@Service
public class QuestionServiceImpl extends AbstractJpaService<Question> implements IQuestionService {

	@Resource
	private QuestionRepository questionRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IQuestionDao dao;

	public QuestionServiceImpl() {
		super();
	}

	@Override
	protected IOperations<Question> getDao() {
		return dao;
	}

	public Page<Question> getPage(int pageNumber, int fetchSize, String sortByField) {
		PageRequest request = new PageRequest(pageNumber - 1, fetchSize, Sort.Direction.DESC, sortByField);
		Page<Question>  page = questionRepository.findAll(request);
		return page;
	}

}
