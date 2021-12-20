package it.backend.service;

import java.util.function.Supplier;
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
		return new CustomUserDetails(user);
	}
}
