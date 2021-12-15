package it.springsecurity.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("1234"));
	}

}
