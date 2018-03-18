package com.codespot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.codespot.dao.common.IOperations;
import com.codespot.model.ContactType;
import com.codespot.model.User;

public interface IUserService extends IOperations<User>{

	User findByUserName(String username);
	User findByUserEmail(String username);
	Page<User> findAllContactType(ContactType contactType, long userid, int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField);

}
