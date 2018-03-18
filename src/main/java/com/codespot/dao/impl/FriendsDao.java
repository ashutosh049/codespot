package com.codespot.dao.impl;

import org.springframework.stereotype.Repository;

import com.codespot.dao.IFriendsDao;
import com.codespot.dao.common.AbstractJpaDao;
import com.codespot.model.Friends;

@Repository
public class FriendsDao extends AbstractJpaDao<Friends>implements IFriendsDao {
	public FriendsDao() {
		super();
		setClazz(Friends.class);
	}
}
