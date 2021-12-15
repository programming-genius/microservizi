package it.backend.repository.data;

import it.backend.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IValidationEntityRepository extends JpaRepository<Validation,Integer> {
}
