package it.backend.repository.data;

import java.util.Optional;

import it.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserEntityRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUsername(String u);
}
