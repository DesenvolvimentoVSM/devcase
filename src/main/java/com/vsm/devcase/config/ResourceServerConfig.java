package com.vsm.devcase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
    public void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		MeuPasswordEncoder meuEncoder = new MeuPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(meuEncoder);
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests().antMatchers("/oauth/token").permitAll()
          //.anyRequest().authenticated()
          .and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
          .csrf().disable();
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    	resources.stateless(true);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new MeuPasswordEncoder();
    }
    
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    	
    	@Bean
    	@Override
    	public AuthenticationManager authenticationManagerBean() throws Exception {
    		return super.authenticationManagerBean();
    	}
    }
    
    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
    	return new OAuth2MethodSecurityExpressionHandler();
    }
	
}
