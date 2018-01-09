package com.codespot.service;

import org.springframework.data.domain.Page;

import com.codespot.dao.common.IOperations;
import com.codespot.model.Question;

public interface IQuestionService extends IOperations<Question>{

	Page<Question> getPage(int pageNo, int fetchSize, String sortByField);

}
