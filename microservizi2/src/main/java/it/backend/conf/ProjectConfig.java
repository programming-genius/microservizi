package it.backend.conf;

import it.backend.components.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtAuthentication jwtAuthentication;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    //Basic
		//http.httpBasic();
        /*http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost"));
                config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE"));
                return config;
            };
            c.configurationSource(source);
        });*/
        //http.csrf().disable();
		//http.authorizeRequests().anyRequest().authenticated();

		//TOKEN
        http.authorizeRequests().
                anyRequest().authenticated().and().apply(new JwtAuthenticationConfigurer(jwtAuthentication)).and().
                csrf().disable().httpBasic().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

    @Bean
    public HttpTraceRepository httpTraceRepository() {
	    return new InMemoryHttpTraceRepository();
	}

}