package it.html;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Path("/service")
public class Service {

    @ConfigProperty(name="mp.jwt.verify.issuer", defaultValue = "my-issuer-name")
    String jwtIssuer;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("auth/{username}/{password}/jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJWT(@PathParam(value = "username") String username, @PathParam(value = "password") String password) {
        String jwt = null;
        try {
            // The roleMappings claim can be used to map from a role defined in the groups claim to an application
            // level role defined in a @RolesAllowed annotation.
            // it can be useful when the IDM providing the token has roles that do not directly
            // align with those defined by the application.
            // https://jwt.io/
            Map<String, Object> rolesMap = new HashMap<>();
            rolesMap.put("role 1", "ROLE1");
            rolesMap.put("role 2", "ROLE2");
            rolesMap.put("role 3", "ROLE3");
            List<String> groups = new ArrayList<>();
            groups.add("role 1");
            jwt = generateJwt(username, password, groups, rolesMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return  Response.ok(jwt).build();
    }

    private PrivateKey loadPrivateKey() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException{
        byte[] keyfile = Service.class.
                getResourceAsStream("/META-INF/privatekey.pem").readAllBytes();
        String key = new String(keyfile, 0, keyfile.length).
                replaceAll("-----BEGIN (.*)-----","")
                .replaceAll("-----END (.*)-----","")
                .replaceAll("\r\n","")
                .replaceAll("\n","").trim();
        return KeyFactory.getInstance("RSA").
                generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)));
    }

    private String generateJwt(String username, String password, List groups, Map roles)
            throws InvalidKeySpecException, NoSuchAlgorithmException, IOException{
            Map<String, Object> claimsMap = new HashMap<>();
            claimsMap.put("username", username);
            claimsMap.put("password", password);
            JwtClaimsBuilder claims = Jwt.claims(claimsMap).
                    subject(username + " " + password).
                    claim("roleMappings", roles).
                    claim("groups", groups).
                    issuer(jwtIssuer).
                    issuedAt(Instant.now().toEpochMilli()).
                    expiresAt(Instant.now().plus(2, ChronoUnit.DAYS).toEpochMilli());
            PrivateKey privatekey = loadPrivateKey();
            return claims.jws().sign(privatekey);
    }

    @GET
    @Path("method1")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ROLE1")
    public Response method1() {
        return  Response.ok(jwt).status(200).build();
    }

    @GET
    @Path("method2")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response method2() {
        return Response.status(200).build();
    }

}