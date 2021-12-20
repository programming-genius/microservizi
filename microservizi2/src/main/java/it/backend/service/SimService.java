package it.backend.service;

//import it.backend.repository.CSimRepository;
//import it.backend.repository.DSimRepository;
import it.backend.repository.custom.ISimRepository;
import it.backend.service.contract.ISimService;
import it.backend.model.SimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SimService implements ISimService {

    @Autowired
    private ISimRepository iSimRepository;

    @Override
    public SimDTO acquiredByUser(SimDTO sim, Integer userId) {
        return iSimRepository.acquiredByUser(sim, userId);
    }

    @Override
    public SimDTO activateProduct(Integer simId, Integer productId) {
        return iSimRepository.activateProduct(simId, productId);
    }

    @Override
    public SimDTO deActivateProduct(Integer simId, Integer productId) {
        return iSimRepository.deActivateProduct(simId, productId);
    }

    @Override
    public List<SimDTO> findSimByUser(Integer userId) {
        return iSimRepository.findSimByUser(userId);
    }
}
