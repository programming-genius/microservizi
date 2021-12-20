package it.backend.conf;

import it.backend.components.CustomAuthenticationProvider;
import it.backend.components.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Autowired
    private JwtAuthentication jwtAuthentication;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider);
	}

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
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/auth/login").permitAll().
                anyRequest().authenticated().and().
                csrf().disable().httpBasic().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String secret = keyGenerator.generateKey();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(secret, 30000, 256));
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
	    return super.authenticationManagerBean();
    }


    @Bean
    public HttpTraceRepository httpTraceRepository() {
	    return new InMemoryHttpTraceRepository();
	}

}