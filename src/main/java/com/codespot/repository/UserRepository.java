package com.codespot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

/*	@Transactional
	@Query("SELECT u FROM User u WHERE u.userEmail =  ?1")
	public List<User> findByEmail(@Param("userEmail") String userEmail);
*/
	
/*	@Transactional
	@Query("SELECT u FROM User u WHERE u.userName =  ?1")
	public List<User> findAllByUserName(@Param("userName") String userName);

*/	
	User findByUserEmail(String userEmail);
	User findByUserName(String userName);
	
}
