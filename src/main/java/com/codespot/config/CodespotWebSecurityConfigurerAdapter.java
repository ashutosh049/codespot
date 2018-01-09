package com.codespot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Spring Security Configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CodespotWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	//	CodespotUserDetailsService codespotUserDetailsService;
	
	@Autowired
	CodespotAuthenticationSuccessHandler successHandler;
	
	@Autowired
	CodespotAuthenticationFailureHandler failureHandler;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CsrfTokenRepository csrfTokenRepository;
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	DataSource dataSource;

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "resources","/unauth*").permitAll()
//				.antMatchers("/questions*").hasAuthority("ADMIN")
//				.antMatchers("/tables*").hasRole("ADMIN")
				.antMatchers("/questions/ask","/questions/create","/questionComment/**").hasRole("USER")
				//.anyRequest().authenticated()
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
					.successHandler(successHandler)
					.failureUrl("/login?failed=login-error")
					.failureHandler(failureHandler)
					.usernameParameter("userName")
					.passwordParameter("userPassword")
					.permitAll()
			.and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout=success")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.clearAuthentication(true)	 
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).invalidSessionUrl("/")
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/")
			.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//			.and()
//				.csrf().disable();
			.and()
				.csrf().csrfTokenRepository(csrfTokenRepository)
	        .and()
	        	.addFilterBefore(usernamePasswordAuthenticationFilter(), CodespotPasswordAuthenticationFilter.class);
		
		/*http
			.requiresChannel()
				.anyRequest().requiresSecure();*/
		/*http
			.portMapper()
				.http(8080).mapsTo(8443)
				.http(80).mapsTo(443);*/
		http
			.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/");
	        
	
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/* @Bean
	    public ProviderSignInController providerSignInController() {
	        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
	        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new FacebookSignInAdapter());
	    }*/
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

	@Bean 
    public CodespotPasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception { 
		CodespotPasswordAuthenticationFilter loginDecisionFilter = new CodespotPasswordAuthenticationFilter(); 
        loginDecisionFilter.setAuthenticationManager(authenticationManagerBean()); 
        loginDecisionFilter.setAuthenticationSuccessHandler(successHandler); 
        loginDecisionFilter.setAuthenticationFailureHandler(failureHandler); 
        return loginDecisionFilter; 
    }
	
	/**
	 * enabling the concurrent session-control support
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	
}