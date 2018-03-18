<<<<<<< HEAD
package com.codespot.config;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
//import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.codespot.model.ActiveUserStore;
import com.codespot.util.CodespotConstants;
import com.codespot.util.MailSender;
import com.codespot.util.MailSenderHelper;
import com.codespot.util.MailSenderImpl;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.codespot")
@PropertySource({ "classpath:codespot.properties" })
@EnableJpaRepositories("com.codespot.repository")
public class CodespotWebAppConfig {

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(CodespotConstants.DB_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(CodespotConstants.DB_URL));
		dataSource.setUsername(env.getRequiredProperty(CodespotConstants.DB_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(CodespotConstants.DB_PASSWORD));
		return dataSource;
	}

	/**
	 * @Deprecated.  org.hibernate.ejb.HibernatePersistence is deprecated.
	 * Using org.hibernate.jpa.HibernatePersistenceProvider instead.
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(CodespotConstants.ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(CodespotConstants.HIBERNATE_DIALECT,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_DIALECT));
		properties.put(CodespotConstants.HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_SHOW_SQL));
		properties.put(CodespotConstants.HIBERNATE_FORMAT_SQL,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_FORMAT_SQL));
		properties.put(CodespotConstants.HIBERNATE_HBM2DDL_AUTO,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_HBM2DDL_AUTO));
		properties.put(CodespotConstants.HIBERNATE_HBM2DDL_IMPORT_FILES,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_HBM2DDL_IMPORT_FILES));
		
		
		
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix(env
				.getRequiredProperty(CodespotConstants.SPRING_MVC_VIEW_PREFIX));
		resolver.setSuffix(env
				.getRequiredProperty(CodespotConstants.SPRING_MVC_VIEW_SUFFIX));
		resolver.setViewClass(JstlView.class);
		//resolver.setCache(false);
		/*resolver.setCacheable(false);*/
		return resolver;
	}

	@Bean
	public MailSenderHelper mailSenderHelper() {
		return new MailSenderHelper();
	}

	@Bean
	public MailSender mailsender() {
		return new MailSenderImpl();
	}

	/*@Bean
	public ModuleControllerHelper moduleControllerHelper() {
		return new ModuleControllerHelper();
	}*/

	@Bean
	public VelocityEngine velocityEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	@Bean
	CodespotAuthenticationSuccessHandler codespotAuthenticationSuccessHandler() {
		return new CodespotAuthenticationSuccessHandler();
	}
	
	@Bean
	CodespotLogoutSuccessHandler codespotLogoutSuccessHandler() {
		return new CodespotLogoutSuccessHandler();
	}

	@Bean
	CodespotAuthenticationFailureHandler codespotAuthenticationFailureHandler() {
		return new CodespotAuthenticationFailureHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/unauth/fail/restrictedaccess");
		return accessDeniedHandler;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	// SPRING BOOT
	/*
	 * @Bean public MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory();
	 * factory.setMaxFileSize("20MB"); factory.setMaxRequestSize("20MB"); return
	 * factory.createMultipartConfig(); }
	 */
	@Bean
	public ActiveUserStore activeUserStore(){
	    return new ActiveUserStore();
	}
	
}
=======
package com.codespot.config;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
//import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.codespot.model.ActiveUserStore;
import com.codespot.util.CodespotConstants;
import com.codespot.util.MailSender;
import com.codespot.util.MailSenderHelper;
import com.codespot.util.MailSenderImpl;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.codespot")
@PropertySource({ "classpath:codespot.properties" })
@EnableJpaRepositories("com.codespot.repository")
public class CodespotWebAppConfig {

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(CodespotConstants.DB_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(CodespotConstants.DB_URL));
		dataSource.setUsername(env.getRequiredProperty(CodespotConstants.DB_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(CodespotConstants.DB_PASSWORD));
		return dataSource;
	}

	/**
	 * @Deprecated.  org.hibernate.ejb.HibernatePersistence is deprecated.
	 * Using org.hibernate.jpa.HibernatePersistenceProvider instead.
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(CodespotConstants.ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(CodespotConstants.HIBERNATE_DIALECT,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_DIALECT));
		properties.put(CodespotConstants.HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_SHOW_SQL));
		properties.put(CodespotConstants.HIBERNATE_FORMAT_SQL,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_FORMAT_SQL));
		properties.put(CodespotConstants.HIBERNATE_HBM2DDL_AUTO,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_HBM2DDL_AUTO));
		properties.put(CodespotConstants.HIBERNATE_HBM2DDL_IMPORT_FILES,
				env.getRequiredProperty(CodespotConstants.HIBERNATE_HBM2DDL_IMPORT_FILES));
		
		
		
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix(env
				.getRequiredProperty(CodespotConstants.SPRING_MVC_VIEW_PREFIX));
		resolver.setSuffix(env
				.getRequiredProperty(CodespotConstants.SPRING_MVC_VIEW_SUFFIX));
		resolver.setViewClass(JstlView.class);
		//resolver.setCache(false);
		/*resolver.setCacheable(false);*/
		return resolver;
	}

	@Bean
	public MailSenderHelper mailSenderHelper() {
		return new MailSenderHelper();
	}

	@Bean
	public MailSender mailsender() {
		return new MailSenderImpl();
	}

	/*@Bean
	public ModuleControllerHelper moduleControllerHelper() {
		return new ModuleControllerHelper();
	}*/

	@Bean
	public VelocityEngine velocityEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	@Bean
	CodespotAuthenticationSuccessHandler codespotAuthenticationSuccessHandler() {
		return new CodespotAuthenticationSuccessHandler();
	}
	
	@Bean
	CodespotLogoutSuccessHandler codespotLogoutSuccessHandler() {
		return new CodespotLogoutSuccessHandler();
	}

	@Bean
	CodespotAuthenticationFailureHandler codespotAuthenticationFailureHandler() {
		return new CodespotAuthenticationFailureHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/unauth/fail/restrictedaccess");
		return accessDeniedHandler;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	// SPRING BOOT
	/*
	 * @Bean public MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory();
	 * factory.setMaxFileSize("20MB"); factory.setMaxRequestSize("20MB"); return
	 * factory.createMultipartConfig(); }
	 */
	@Bean
	public ActiveUserStore activeUserStore(){
	    return new ActiveUserStore();
	}
	
}
>>>>>>> post-chat
