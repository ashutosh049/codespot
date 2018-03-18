package com.codespot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
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
	CodespotAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	CodespotLogoutSuccessHandler codespotLogoutSuccessHandler;
	
	@Autowired
	CodespotAuthenticationFailureHandler authenticationFailureHandler;
	
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
				.antMatchers("/questions/ask","/questions/create","/questionComment/**","/searchusers/**").hasRole("USER")
				//.anyRequest().authenticated()
				.antMatchers("/invalidSession*","/session-expired*").anonymous()
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
					.successHandler(authenticationSuccessHandler)
					.failureUrl("/login?failed=login-error")
					.failureHandler(authenticationFailureHandler)
					.usernameParameter("userName")
					.passwordParameter("userPassword")
					.permitAll()
			.and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
			.and()
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout=success")
					.logoutSuccessHandler(codespotLogoutSuccessHandler)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.clearAuthentication(true)	 
			.and()
				.sessionManagement()
					.maximumSessions(1).sessionRegistry(sessionRegistry())
					.expiredUrl("/session-expired")	
				.and().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.invalidSessionUrl("/")
					.sessionFixation().none()
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
		/*http
			.sessionManagement()
				.maximumSessions(1).sessionRegistry(sessionRegistry())
				.expiredUrl("/session-expired");*/
	        
	
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
        loginDecisionFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler); 
        loginDecisionFilter.setAuthenticationFailureHandler(authenticationFailureHandler); 
        return loginDecisionFilter; 
    }

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
	
}