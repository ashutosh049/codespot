package com.codespot.config;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import com.codespot.util.CodespotConstants;

/**
 * Registers the springSecurityFilterChain Filter for every URL in the application. 
 *
 */
@Configuration
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	
	
	@Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, multipartFilter());
    }
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() throws IOException {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(CodespotConstants.MAX_UPLOAD_SIZE);   // 20MB
	    multipartResolver.setMaxInMemorySize(1048576);  // 1MB
	    multipartResolver.setDefaultEncoding(CodespotConstants.MULTIPART_RESOLVER_DEFAULT_ENCODING);
//	    multipartResolver.setUploadTempDir(new FileSystemResource(CodespotConstants.ULOAD_DIRECTORY));
	    /*multipartResolver.setUploadTempDir(new ClassPathResource(CodespotConstants.ULOAD_DIRECTORY));*/
	    return multipartResolver;
	}

	@Bean
	@Order(0)
	public MultipartFilter multipartFilter() {
		MultipartFilter multipartFilter =new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartResolver");
	    return multipartFilter;
	}
}
