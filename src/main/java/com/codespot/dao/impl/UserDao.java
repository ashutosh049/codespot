package com.codespot.dao.impl;

import org.springframework.stereotype.Repository;

import com.codespot.dao.IUserDao;
import com.codespot.dao.common.AbstractJpaDao;
import com.codespot.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User>implements IUserDao {
	public UserDao() {
		super();
		setClazz(User.class);
	}
}
