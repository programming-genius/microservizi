package it.backend.repository.data;

import it.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductEntityRepository extends JpaRepository<Product,Integer> {
}
