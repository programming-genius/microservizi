package it.backend.service;

//import it.backend.repository.CBusinessUserRepository;
//import it.backend.repository.DBusinessUserRepository;
import it.backend.model.BusinessUserFilterDTO;
import it.backend.model.BusinessUserPageDTO;
import it.backend.repository.custom.IBusinessUserRepository;
import it.backend.service.contract.IBusinessUserService;
import it.backend.model.BusinessUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessUserService implements IBusinessUserService {

    @Autowired
    private IBusinessUserRepository iBusinessUserRepository;

    @Override
    public BusinessUserDTO createUser(BusinessUserDTO user) {
        return iBusinessUserRepository.createUser(user);
    }

    @Override
    public BusinessUserDTO updateUser(BusinessUserDTO user) {
        return iBusinessUserRepository.updateUser(user);
    }

    @Override
    public BusinessUserDTO mergeUser(BusinessUserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        iBusinessUserRepository.deleteUser(id);
    }

    @Override
    public BusinessUserDTO findById(Integer id) {
        return iBusinessUserRepository.findById(id);
    }

    @Override
    public BusinessUserPageDTO findUsers(int start, int max) {
        return iBusinessUserRepository.findUsers(start, max);
    }

    @Override
    public BusinessUserPageDTO findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) {
        return iBusinessUserRepository.findUsersByFilter(start, max, filter);
    }
}
