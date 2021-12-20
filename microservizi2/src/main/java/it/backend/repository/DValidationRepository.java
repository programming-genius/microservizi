package it.backend.repository;

import it.backend.model.ValidationDTO;
import it.backend.repository.custom.IValidationRepository;
import it.backend.repository.data.IBusinessUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DValidationRepository implements IValidationRepository {

    @Override
    public ValidationDTO validateUser(Integer userId) {
        //find con iBusinessUserEntityRepository ->BusinessUser u
        // Optional<BusinessUser> ouser = iBusinessUserEntityRepository.findById(userId); BusinessUser user = ouser.get();
        //Validation v = new Validation(); v.setDate(new Date()); v.setBusinessUser(user)
        //iValidationRepository.save(v);
        //BusinessUserDTO
        //ValidationDTO
        return null;
    }
}
