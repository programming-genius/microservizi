package it.backend.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtAuthentication {

    private static final String SECRETKEY = Base64.getEncoder().encodeToString("abc1234".getBytes());
    private static final String PREFIX = "Bearer";
    private static final String EMPTY = "";
    private static final String AUTHORIZATION = "Authorization";

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);
        if(token!=null && validateToken(token)) {
            String username = getUserName(token);
            if(username!=null){
                UserDetails userDetails = new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public String getPassword() {
                        return null;
                    }

                    @Override
                    public String getUsername() {
                        return username;
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return false;
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isEnabled() {
                        return false;
                    }
                };
                return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
            }
        }
        return null;
    }

    private String getUserName(String token){
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody().getSubject();
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith(PREFIX)) {
            return bearerToken.replace(PREFIX, EMPTY);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        }catch(Exception e) {
            //throw new IllegalArgumentException("Expired or invalid JWT token");
            e.printStackTrace();
            return false;
        }
    }
}
