package com.vsm.devcase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
    public void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		MeuPasswordEncoder password = new MeuPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("admin")
          .password(password.encode("admin"))
          .roles("ROLE");
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests().antMatchers("/oauth/token").permitAll()
          .anyRequest()
          .authenticated()
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
	
}
