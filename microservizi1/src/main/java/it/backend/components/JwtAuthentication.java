package it.backend.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthentication {

    private static final String SECRETKEY = Base64.getEncoder().encodeToString("abc1234".getBytes());
    private static final String PREFIX = "Bearer";
    private static final String EMPTY = "";
    private static final long EXPIRATIONTIME = 3600000;//milliseconds
    private static final String AUTHORIZATION = "Authorization";

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles",roles);
        Date now = new Date();
        Date validity = new Date(now.getTime()+EXPIRATIONTIME);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).
                signWith(SignatureAlgorithm.HS512, SECRETKEY).compact();
    }
}
