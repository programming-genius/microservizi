package it.backend.repository.custom;

import it.backend.model.SimDTO;
import java.util.List;

public interface ISimRepository {
    SimDTO acquiredByUser(SimDTO sim, Integer userId);
    SimDTO activateProduct(Integer simId, Integer productId);
    SimDTO deActivateProduct(Integer simId, Integer productId);
    List<SimDTO> findSimByUser(Integer userId);
}
