package com.maxtrain.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends
   WebSecurityConfigurerAdapter {
	
	public static final int FRONTEND_SECURITY_ORDER
       = SecurityProperties.ACCESS_OVERRIDE_ORDER + 3;

	@Value("${security.enable-csrf}")
	private boolean csrfEnabled;	

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  if (!csrfEnabled) {	  
		  http
		  	.csrf().disable();
		  //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	  }
  }
}