package it.backend.conf;

import it.backend.components.JwtAuthentication;
import it.backend.filter.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtAuthentication jwtAuthentication;

    public JwtAuthenticationConfigurer(JwtAuthentication jwtAuthentication){
        this.jwtAuthentication = jwtAuthentication;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtAuthentication);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
