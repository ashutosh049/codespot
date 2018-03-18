package com.codespot.repository;

import org.springframework.data.domain.Page;
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

	@Transactional
	@Query("SELECT u.userId, u.userActive, u.userEmail, u.userName FROM User u WHERE u.userId <> :userId AND u.userId NOT IN ( SELECT f.relationWith FROM Friends f WHERE f.relationFrom =:userId)")
	Page<User> findAllAddableFriends(@Param("userId") long userId, Pageable request);
	
	@Transactional
	@Query("SELECT u.userId, u.userActive, u.userEmail, u.userName FROM User u WHERE u.userId <> :userId AND u.userId IN ( SELECT f.relationWith FROM Friends f WHERE f.relationFrom =:userId AND f.status=1)")
	Page<User> findAllAddedFriends(@Param("userId") long userId, Pageable request);

	
	@Transactional
	@Query("SELECT u.userId, u.userActive, u.userEmail, u.userName FROM User u WHERE u.userId <> :userId AND u.userId IN ( SELECT f.relationWith FROM Friends f WHERE f.relationFrom =:userId AND f.status=0)")
	Page<User> findAllAddableSentFriends(@Param("userId") long userId, Pageable request);
	
	
}
