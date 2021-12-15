package it.backend.web;

import static org.springframework.http.ResponseEntity.ok;
import it.backend.components.JwtAuthentication;
import it.backend.model.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthentication jwtAuthentication;
    
    @PostMapping(value="/auth/login")
    public ResponseEntity<Map<Object,Object>> login(@RequestBody UserLoginDTO userLogin) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(),userLogin.getPassword()));
            List<String> roles = authentication.getAuthorities().stream().map(a -> a.getAuthority())
                    .collect(Collectors.toList());
            String token = jwtAuthentication.createToken(userLogin.getUsername(),roles);
            Map<Object,Object> model = new HashMap<>();
            model.put("username",userLogin.getUsername());
            model.put("token",token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }
}
