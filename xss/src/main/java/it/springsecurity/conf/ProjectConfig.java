package it.springsecurity.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@ComponentScan({ "it.springsecurity.conf.comp", "it.springsecurity.controller" })
@EnableAsync
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		http
        .headers()
        .xssProtection()
        .and()
        .contentSecurityPolicy("script-src 'self'");
        
		 // Tutti gli utenti autenticati possono accedere
		http.authorizeRequests().mvcMatchers("/403").hasAnyRole("READER","GUEST","CREATOR");
		http.authorizeRequests().anyRequest().hasAnyRole("READER", "GUEST");
	}

}