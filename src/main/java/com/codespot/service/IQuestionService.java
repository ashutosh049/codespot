package com.codespot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.codespot.dao.common.IOperations;
import com.codespot.model.Question;

public interface IQuestionService extends IOperations<Question>{

	Page<Question> getPage(int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField);

}
