package it.backend.repository;

import it.backend.entity.BusinessUser;
import it.backend.entity.Product;
import it.backend.entity.Sim;
import it.backend.model.ProductDTO;
import it.backend.model.SimDTO;
import it.backend.repository.custom.ISimRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CSimRepository /*implements ISimRepository*/ {

    @PersistenceContext
    private EntityManager em;

    //Override
    @Transactional
    public SimDTO acquiredByUser(SimDTO sim, Integer userId) {
        //BusinessUser businessUserEntity = em.find(BusinessUser.class, userId);
        TypedQuery<BusinessUser> query = em.createQuery("select c from BusinessUser c left outer join " +
                "fetch c.sim where c.id=:userId", BusinessUser.class);
        query.setParameter("userId",userId);
        BusinessUser businessUserEntity = query.getSingleResult();

        Sim simEntity = new Sim();
        simEntity.setImsi(sim.getImsi());
        simEntity.setMsisdn(sim.getMsisdn());
        em.persist(simEntity);

        businessUserEntity.addSim(simEntity);
        sim.setId(simEntity.getId());
        return sim;
    }

    //@Override
    @Transactional
    public SimDTO activateProduct(Integer simId, Integer productId) {
        Product productEntity = em.getReference(Product.class, productId);
        TypedQuery<Sim> query = em.createQuery("select c from Sim c left outer join fetch c.products where c.id=:simId", Sim.class);
        query.setParameter("simId",simId);
        Sim simEntity = query.getSingleResult();
        simEntity.addProduct(productEntity);
        List<ProductDTO> productDTO = new ArrayList<>();
        for(Product product: simEntity.getProducts()){
            productDTO.add(new ProductDTO(product.getId(),product.getName()));
        }
        SimDTO simDTO = new SimDTO(simEntity.getId(),simEntity.getMsisdn(), simEntity.getImsi());
        simDTO.setProducts(productDTO);
        return simDTO;
    }

    //@Override
    @Transactional
    public SimDTO deActivateProduct(Integer simId, Integer productId) {
        Product productEntity = em.getReference(Product.class, productId);
        TypedQuery<Sim> query = em.createQuery("select c from Sim c join fetch c.products where c.id=:simId", Sim.class);
        query.setParameter("simId",simId);
        Sim simEntity = query.getSingleResult();
        simEntity.removeProduct(productEntity);
        List<ProductDTO> productDTO = new ArrayList<>();
        for(Product product: simEntity.getProducts()){
            productDTO.add(new ProductDTO(product.getId(),product.getName()));
        }
        SimDTO simDTO = new SimDTO(simEntity.getId(),simEntity.getMsisdn(), simEntity.getImsi());
        simDTO.setProducts(productDTO);
        return simDTO;
    }

    //@Override
    @Transactional(readOnly = true)
    public List<SimDTO> findSimByUser(Integer userId) {
        TypedQuery<Sim> query = em.createQuery("select c from Sim c join fetch c.businessUser u where u.id=:userId", Sim.class);
        query.setParameter("userId", userId);
        List<Sim> simResult = query.getResultList();
        List<SimDTO> simDTO = new ArrayList<>();
        for(Sim sim: simResult){
            simDTO.add(new SimDTO(sim.getId(),sim.getMsisdn(),sim.getImsi()));
        }
        return simDTO;
    }
}
