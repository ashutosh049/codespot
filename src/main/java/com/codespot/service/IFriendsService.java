<<<<<<< HEAD
package com.codespot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.codespot.dao.common.IOperations;
import com.codespot.model.Friends;
import com.codespot.model.User;

public interface IFriendsService extends IOperations<Friends>{

	Page<Friends> findAllPendingFR(long userid, int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField);
	List<Friends> findAllPendingFR(User userInContext);

}
=======
package com.codespot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.codespot.dao.common.IOperations;
import com.codespot.model.Friends;
import com.codespot.model.User;

public interface IFriendsService extends IOperations<Friends>{

	Page<Friends> findAllPendingFR(long userid, int pageNumber, int fetchSize, Sort.Direction sortParameter, String sortByField);
	List<Friends> findAllPendingFR(User userInContext);

}
>>>>>>> post-chat
