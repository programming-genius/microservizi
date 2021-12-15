package it.backend.service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import it.backend.entity.Role;
import it.backend.entity.User;
import it.backend.model.CustomUserDetails;
import it.backend.repository.data.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserEntityRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		User user = userRepository.findUserByUsername(username).orElseThrow(s);
        /*User user = new User();
        user.setUsername(username);
        user.setPassword("{bcrypt}$2a$10$Z75X0yl1xLq9cyIAtKABN.L1p6LQDG.BxEFkC.sy.eud54akAF7Yu");
        Set<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setName("READ");
        roles.add(role);
        user.setRoles(roles);*/
		return new CustomUserDetails(user);
	}
}
