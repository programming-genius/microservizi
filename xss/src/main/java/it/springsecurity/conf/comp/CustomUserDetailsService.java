package it.springsecurity.conf.comp;

import java.util.HashSet;
import java.util.Set;
//import java.util.function.Supplier;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//import it.springsecurity.repository.UserRepository;
import it.springsecurity.users.CustomUserDetails;
import it.springsecurity.users.Role;
import it.springsecurity.users.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	//@Autowired
	//private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) {
		//Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		//User u = userRepository.findUserByUsername(username).orElseThrow(s);
		User user = new User();
		user.setId(1);
		user.setUsername("John");
		user.setPassword("{bcrypt}$2a$10$rU8cizPKepsNs/QOQtR/M.mR/UnYd32pL0o8GUR8KzznuSA3j9f.2");
		
		Set<Role> roles = new HashSet<Role>();
		
		Role role = new Role();
		role.setId(1);
		role.setName("ROLE_GUEST");
		roles.add(role);
		
		user.setRoles(roles);
		
		return new CustomUserDetails(user);
	}
}
