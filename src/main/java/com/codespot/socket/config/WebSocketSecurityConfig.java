package com.codespot.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		//messages.simpDestMatchers("/questionComment/*").authenticated();
		
		/**--https://docs.spring.io/spring-security/site/docs/current/reference/html/websocket.html--**/
		
		//messages
        //.nullDestMatcher().authenticated()
        //.simpSubscribeDestMatchers("/questionComment/**","/postQuestionComment/**").hasRole("USER") 
        //.simpDestMatchers("/postQuestionComment/questionComment/**").hasRole("USER") 
        //.simpSubscribeDestMatchers("/user/**", "/topic/friends/*").hasRole("USER") 
        //.simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll() 
        //.anyMessage().denyAll();
		//;
	}
	
	@Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}