package it.backend.repository.data;

import it.backend.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ISimEntityRepository extends JpaRepository<Sim, Integer> {

    @Transactional(readOnly = true)
    @Query("select c from Sim c left outer join fetch c.products where c.id=:simId")
    Sim findSimById(@Param("simId") Integer simId);

    @Transactional(readOnly = true)
    @Query("select c from Sim c join fetch c.businessUser u where u.id=:userId")
    List<Sim> findSimByUserId(@Param("userId") Integer userId);

}
