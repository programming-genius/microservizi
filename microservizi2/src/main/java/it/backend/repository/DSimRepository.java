package it.backend.repository;

import it.backend.entity.BusinessUser;
import it.backend.entity.Product;
import it.backend.entity.Sim;
import it.backend.model.ProductDTO;
import it.backend.model.SimDTO;
import it.backend.repository.custom.ISimRepository;
import it.backend.repository.data.IBusinessUserEntityRepository;
import it.backend.repository.data.IProductEntityRepository;
import it.backend.repository.data.ISimEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DSimRepository implements ISimRepository {

    @Autowired
    private ISimEntityRepository iSimRepository;
    @Autowired
    private IBusinessUserEntityRepository iBusinessUserRepository;
    @Autowired
    private IProductEntityRepository iProductRepository;

    @Transactional
    public SimDTO acquiredByUser(SimDTO sim, Integer userId) {
        BusinessUser businessUserEntity = iBusinessUserRepository.findById(userId).get();
        Sim simEntity = new Sim();
        simEntity.setImsi(sim.getImsi());
        simEntity.setMsisdn(sim.getMsisdn());
        iSimRepository.save(simEntity);
        businessUserEntity.addSim(simEntity);
        sim.setId(simEntity.getId());
        return sim;
    }

    @Transactional
    public SimDTO activateProduct(Integer simId, Integer productId) {
        Product productEntity = iProductRepository.getById(productId);
        Sim simEntity = iSimRepository.findSimById(simId);
        simEntity.addProduct(productEntity);
        List<ProductDTO> productDTO = new ArrayList<>();
        for(Product product: simEntity.getProducts()){
            productDTO.add(new ProductDTO(product.getId(),product.getName()));
        }
        SimDTO simDTO = new SimDTO(simEntity.getId(),simEntity.getMsisdn(), simEntity.getImsi());
        simDTO.setProducts(productDTO);
        return simDTO;
    }

    @Transactional
    public SimDTO deActivateProduct(Integer simId, Integer productId) {
        Product productEntity = iProductRepository.getById(productId);
        Sim simEntity = iSimRepository.findSimById(simId);
        simEntity.removeProduct(productEntity);
        List<ProductDTO> productDTO = new ArrayList<>();
        for(Product product: simEntity.getProducts()){
            productDTO.add(new ProductDTO(product.getId(),product.getName()));
        }
        SimDTO simDTO = new SimDTO(simEntity.getId(),simEntity.getMsisdn(), simEntity.getImsi());
        simDTO.setProducts(productDTO);
        return simDTO;
    }

    @Transactional(readOnly=true)
    public List<SimDTO> findSimByUser(Integer userId) {
        List<Sim> simResult = iSimRepository.findSimByUserId(userId);
        List<SimDTO> simDTO = new ArrayList<>();
        for(Sim sim: simResult){
            simDTO.add(new SimDTO(sim.getId(),sim.getMsisdn(),sim.getImsi()));
        }
        return simDTO;
    }
}
