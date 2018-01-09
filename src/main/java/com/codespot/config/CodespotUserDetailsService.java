package com.codespot.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codespot.model.Role;
import com.codespot.model.User;
import com.codespot.repository.UserRepository;

@Service
public class CodespotUserDetailsService  implements UserDetailsService {

	private final Log logger = LogFactory.getLog(this.getClass());

	private final Map<String, User> availableUsers = new HashMap<String, User>();

	@Autowired 
    private HttpSession session;
	
	 @Autowired
	 private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user!=null){
        	//session.setAttribute("certHolder", user);
        }else{
        	throw new UsernameNotFoundException(username + " not found");
        }
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        
        
        /*return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);*/
        return new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getUserPassword(), user.getUserActive(), true,
				true, true, grantedAuthorities);
    }
    
    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
    
    
    
	
}