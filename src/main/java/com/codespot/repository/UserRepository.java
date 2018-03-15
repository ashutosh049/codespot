package com.codespot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserEmail(String userEmail);
	User findByUserName(String userName);
	

//	@Query("SELECT u FROM User as u "
//		 + "LEFT OUTER JOIN u.userfriends as f "
//		 + "WHERE u.userId !=:userId "
//		 + "AND f.relationFrom =:userId "
//		 + "AND f.status != 1")
	@Transactional
	@Query("SELECT u FROM User u WHERE u.userId <> :userId AND u.userId NOT IN ( SELECT f.relationWith FROM Friends f WHERE f.relationFrom =:userId)")
	Page<User> findAllAddableFriends(@Param("userId") long userId, Pageable request);
	
	/*@Transactional
	@Query("SELECT f FROM Friends f ")
	Page<User> findAllTobeAcceptedFriends(@Param("userId") long userId, Pageable request);*/
	
}
