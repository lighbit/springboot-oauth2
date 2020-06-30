package com.spring.zulkarnaen.oauth2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
/**
 * 
 * @author zulkarnaen
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	/*
	 * @EnableResourceServer annotation, applied on OAuth2 Resource Servers, enables
	 * a Spring Security filter that authenticates requests using an incoming OAuth2
	 * token
	 */
	/*
	 * ResourceServerConfigurerAdapter implements ResourceServerConfigurer providing
	 * methods to adjust the access rules and paths that are protected by OAuth2
	 * security.
	 */

	private static final String RESOURCE_ID = "my_rest_api";

	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	/* DIGUNAKAN JIKA INGIN SET AUTH DENGAN API BERIKUT */
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().requestMatchers().antMatchers("/api/**").and().authorizeRequests()
				.antMatchers("/api/**").access("hasRole('ADMIN')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}
