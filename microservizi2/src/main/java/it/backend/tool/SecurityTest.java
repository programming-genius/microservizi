package it.backend.tool;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecurityTest {

	public static void main(String[] args) throws NoResultException {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("1234"));
	}
}
