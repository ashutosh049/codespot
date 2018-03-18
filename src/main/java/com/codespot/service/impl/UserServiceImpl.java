<<<<<<< HEAD
package com.codespot.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.Exception.UserNotFound;
import com.codespot.dao.IUserDao;
import com.codespot.dao.common.IOperations;
import com.codespot.model.ContactType;
import com.codespot.model.Role;
import com.codespot.model.User;
import com.codespot.repository.RoleRepository;
import com.codespot.repository.UserRepository;
import com.codespot.service.IUserService;
import com.codespot.service.common.AbstractJpaService;

@Service
public class UserServiceImpl extends AbstractJpaService<User> implements IUserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Resource
	private UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IUserDao dao;
	
	public UserServiceImpl() {
        super();
    }
	
	@Override
	protected IOperations<User> getDao() {
		return dao;
	}
	

	@Transactional
	public User create(User user) {
		user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));

		Set<Role> roleSet = new HashSet<Role>();
		
		/**For setting USER access level**/
		Role roleUser = roleRepository.findOne(2L);
		roleSet.add(roleUser);
		
		/**For setting ALL access levels**/
		/*for (Role role : roleRepository.findAll()) {
			if (role.getRoleName().equals(SecurityRole.ROLE_USER.name())) {
				roleSet.add(role);
			}
		}*/
		user.setRoles(roleSet);
		return getDao().create(user);
	}

	@Transactional(rollbackFor = UserNotFound.class)
	public User delete(long id) throws UserNotFound {
		User deletedUser = userRepository.findOne(id);
		if (deletedUser == null)
			throw new UserNotFound();
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(rollbackFor = UserNotFound.class)
	public User update(User user) throws UserNotFound {
		User updatedUser = userRepository.findOne(user.getUserId());

		if (updatedUser == null)
			throw new UserNotFound();

		entityManager.merge(user);
		updatedUser.setUserEmail(user.getUserEmail());
		updatedUser.setUserPassword(user.getUserPassword());
		updatedUser.setUserActive(user.getUserActive());
//		updatedUser.setConfirmPassword(user.getUserPassword());
		return updatedUser;
	}


	/*public boolean ifExist(String fieldName, String fieldValue) {
		boolean exists = false;
		if (fieldName.equalsIgnoreCase("userEmail")) {
			exists = userRepository.findByEmail(fieldValue).size() >= 1;
		} else if (fieldName.equalsIgnoreCase("userName")) {
			exists = userRepository.findAllByUserName(fieldValue).size() >= 1;
		}
		return exists;
	}*/

	/*public List<User> findByEmail(String email) {
		return (List<User>) userRepository.findByEmail(email);
	}*/

	@Transactional
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	public boolean ifExist(String fieldName, String FieldValue) {
		return false;
	}

	public User findByUserEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
	
	public Page<User> findAllContactType(ContactType contactType, long id,int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField) {
		PageRequest request = new PageRequest(pageNumber - 1, fetchSize, sortParameter, sortByField);
		
		Page<User>  page = null;
		
		if (contactType.equals(ContactType.AllAddable)) {
			page = userRepository.findAllAddableFriends(id, request);
		} else if (contactType.equals(ContactType.AllAddableSent)) {
			page = userRepository.findAllAddableSentFriends(id, request);
		} else if (contactType.equals(ContactType.AllAdded)) {
			page = userRepository.findAllAddedFriends(id, request);
		}

		return page;
	}

}
=======
package com.codespot.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.Exception.UserNotFound;
import com.codespot.dao.IUserDao;
import com.codespot.dao.common.IOperations;
import com.codespot.model.ContactType;
import com.codespot.model.Role;
import com.codespot.model.User;
import com.codespot.repository.RoleRepository;
import com.codespot.repository.UserRepository;
import com.codespot.service.IUserService;
import com.codespot.service.common.AbstractJpaService;

@Service
public class UserServiceImpl extends AbstractJpaService<User> implements IUserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Resource
	private UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IUserDao dao;
	
	public UserServiceImpl() {
        super();
    }
	
	@Override
	protected IOperations<User> getDao() {
		return dao;
	}
	

	@Transactional
	public User create(User user) {
		user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));

		Set<Role> roleSet = new HashSet<Role>();
		
		/**For setting USER access level**/
		Role roleUser = roleRepository.findOne(2L);
		roleSet.add(roleUser);
		
		/**For setting ALL access levels**/
		/*for (Role role : roleRepository.findAll()) {
			if (role.getRoleName().equals(SecurityRole.ROLE_USER.name())) {
				roleSet.add(role);
			}
		}*/
		user.setRoles(roleSet);
		return getDao().create(user);
	}

	@Transactional(rollbackFor = UserNotFound.class)
	public User delete(long id) throws UserNotFound {
		User deletedUser = userRepository.findOne(id);
		if (deletedUser == null)
			throw new UserNotFound();
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(rollbackFor = UserNotFound.class)
	public User update(User user) throws UserNotFound {
		User updatedUser = userRepository.findOne(user.getUserId());

		if (updatedUser == null)
			throw new UserNotFound();

		entityManager.merge(user);
		updatedUser.setUserEmail(user.getUserEmail());
		updatedUser.setUserPassword(user.getUserPassword());
		updatedUser.setUserActive(user.getUserActive());
//		updatedUser.setConfirmPassword(user.getUserPassword());
		return updatedUser;
	}


	/*public boolean ifExist(String fieldName, String fieldValue) {
		boolean exists = false;
		if (fieldName.equalsIgnoreCase("userEmail")) {
			exists = userRepository.findByEmail(fieldValue).size() >= 1;
		} else if (fieldName.equalsIgnoreCase("userName")) {
			exists = userRepository.findAllByUserName(fieldValue).size() >= 1;
		}
		return exists;
	}*/

	/*public List<User> findByEmail(String email) {
		return (List<User>) userRepository.findByEmail(email);
	}*/

	@Transactional
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	public boolean ifExist(String fieldName, String FieldValue) {
		return false;
	}

	public User findByUserEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
	
	public Page<User> findAllContactType(ContactType contactType, long id,int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField) {
		PageRequest request = new PageRequest(pageNumber - 1, fetchSize, sortParameter, sortByField);
		
		Page<User>  page = null;
		
		if (contactType.equals(ContactType.AllAddable)) {
			page = userRepository.findAllAddableFriends(id, request);
		} else if (contactType.equals(ContactType.AllAddableSent)) {
			page = userRepository.findAllAddableSentFriends(id, request);
		} else if (contactType.equals(ContactType.AllAdded)) {
			page = userRepository.findAllAddedFriends(id, request);
		}

		return page;
	}

}
>>>>>>> post-chat
