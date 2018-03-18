package com.codespot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.model.Friends;
import com.codespot.model.User;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long>{

	@Transactional
	@Query("SELECT f FROM Friends f WHERE f.relationFrom =:userInContext AND f.status = 0")
	List<Friends> findAllPendingFR(@Param("userInContext") User userInContext);

}