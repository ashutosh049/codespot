package com.codespot.service;

import com.codespot.dao.common.IOperations;
import com.codespot.model.User;

public interface IUserService extends IOperations<User>{

	User findByUserName(String username);
	User findByUserEmail(String username);
	
	/*
	public User save(User user);
	public User delete(long id) throws UserNotFound;
	public List<User> findAll();
	public User update(User user) throws UserNotFound;
	public User findById(long id);
	public boolean ifExist(String fieldName, String FieldValue);
	public List<User> findByEmail(String email);
	*/

}
