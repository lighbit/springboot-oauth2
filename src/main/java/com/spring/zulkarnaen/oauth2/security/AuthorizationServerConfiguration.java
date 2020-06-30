package com.spring.zulkarnaen.oauth2.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
/**
 * 
 * @author zulkarnaen
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	/*
	 * @EnableAuthorizationServer enables an Authorization Server (i.e. an
	 * AuthorizationEndpoint and a TokenEndpoint) in the current application
	 * context.
	 */
	/*
	 * AuthorizationServerConfigurerAdapter implements AuthorizationServerConfigurer
	 * which provides all the necessary methods to configure an Authorization
	 * server.
	 */

	private static final Logger logger = Logger.getLogger(ClientDetailsServiceConfigurer.class);

	private static String REALM = "MY_OAUTH_REALM";

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		logger.info("username: my-trusted-client and password: secret");

		clients.inMemory().withClient("my-trusted-client")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust").secret("secret")
				.accessTokenValiditySeconds(120).// Access token is only valid for 2 minutes.
				refreshTokenValiditySeconds(600);// Refresh token is only valid for 10 minutes.
	}

	/*
	 * Above configuration
	 * 
	 * Registers a client with client-id ‘my-trusted-client’ and password ‘secret’
	 * and roles & scope he is allowed for.
	 */
	/*
	 * Specifies that any generated access token will be valid for only 120 seconds
	 */
	/*
	 * Specifies that any generated refresh token will be valid for only 600 seconds
	 */

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM + "/client");
	}

}
