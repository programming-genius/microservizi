package it.html;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.ws.rs.core.Application;

//Microprofile JWT protection
// authMethod e realName sono prescritti da Microprofile
// e devono essee MP-JWT e TCK-MP-JWT
//The authMethod is used to configure the authentication mechanism for the JAX-RS application.
// As a prerequisite to gaining access to any web resources which are protected by an authorization constraint,
// a user must have authenticated using the configured mechanism. Supported values include
// "BASIC", "DIGEST", "FORM", "CLIENT-CERT", "MP-JWT
//Note the the MP-JWT TCK currently only validates that a deployment with MP-JWT authentication follows the specification
@LoginConfig(authMethod = "MP-JWT", realmName = "TCK-MP-JWT")
public class AppConfig extends Application {
}
