package com.codespot.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.dao.IFriendsDao;
import com.codespot.dao.common.IOperations;
import com.codespot.model.Friends;
import com.codespot.model.User;
import com.codespot.repository.FriendsRepository;
import com.codespot.repository.UserRepository;
import com.codespot.service.IFriendsService;
import com.codespot.service.common.AbstractJpaService;

@Service
public class FriendsServiceImpl extends AbstractJpaService<Friends> implements IFriendsService {

	
	@Resource
	private UserRepository userRepository;
	@Resource
	private FriendsRepository friendsRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IFriendsDao dao;
	
	public FriendsServiceImpl() {
        super();
    }
	
	@Override
	protected IOperations<Friends> getDao() {
		return dao;
	}
	
	@Transactional
	public Friends create(Friends friends) {
		return getDao().create(friends);
	}

	public List<Friends> findAllPendingFR(User userInContext) {
		return friendsRepository.findAllPendingFR(userInContext);
	}

	public Page<Friends> findAllPendingFR(long userid, int pageNumber, int fetchSize, Direction sortParameter,
			String sortByField) {
		return null;
	}

}
