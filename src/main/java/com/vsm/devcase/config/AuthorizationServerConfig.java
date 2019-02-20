package com.vsm.devcase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("angular")
			.secret("b9c7f1c5f184cf2c640b253e18427bb135dbc51c3a15a6c7bf8ec0052a46e6d6")
			.scopes("write", "read", "openid")
			.authorizedGrantTypes("password", "refresh_token")
			.autoApprove(true) 
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 * 24);
	}
	
	@Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("angular");
		return accessTokenConverter;
	}

	private TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
}
