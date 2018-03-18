<<<<<<< HEAD
package com.codespot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.codespot.config.interceptor.LoggerInterceptor;
import com.codespot.config.interceptor.LoginInterceptor;
import com.codespot.config.interceptor.UserInterceptor;
import com.codespot.socket.config.AppWebSocketConfig;

@Configuration
@EnableWebMvc
@Import({ AppWebSocketConfig.class })
public class CodespotWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{


	public CodespotWebMvcConfigurerAdapter() {
		super();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/").setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("locale");
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(new LoginInterceptor());
		
		/*
		 * To log out of the session after certain inactive interval.
		 * For this application we are not dealing with automatic log out due to inactivity.
		 * */
		//registry.addInterceptor(new SessionTimerInterceptor());
		registry.addInterceptor(new UserInterceptor());
		registry.addInterceptor(new LoggerInterceptor());
	}

	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}
	
	/*@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(30*1000L);
	}*/
	
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}*/

	/*public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mvc/chat").setViewName("commonchat");
	}*/
	
=======
package com.codespot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.codespot.config.interceptor.LoggerInterceptor;
import com.codespot.config.interceptor.LoginInterceptor;
import com.codespot.config.interceptor.UserInterceptor;
import com.codespot.socket.config.AppWebSocketConfig;

@Configuration
@EnableWebMvc
@Import({ AppWebSocketConfig.class })
public class CodespotWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{


	public CodespotWebMvcConfigurerAdapter() {
		super();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/").setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("locale");
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(new LoginInterceptor());
		
		/*
		 * To log out of the session after certain inactive interval.
		 * For this application we are not dealing with automatic log out due to inactivity.
		 * */
		//registry.addInterceptor(new SessionTimerInterceptor());
		registry.addInterceptor(new UserInterceptor());
		registry.addInterceptor(new LoggerInterceptor());
	}

	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}
	
	/*@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(30*1000L);
	}*/
	
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}*/

	/*public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mvc/chat").setViewName("commonchat");
	}*/
	
>>>>>>> post-chat
}