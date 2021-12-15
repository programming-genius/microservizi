package it.backend.repository;

import it.backend.entity.BusinessUser;
import it.backend.model.BusinessUserDTO;
import it.backend.model.BusinessUserFilterDTO;
import it.backend.model.BusinessUserPageDTO;
import it.backend.repository.data.IBusinessUserEntityRepository;
import it.backend.specifications.BusinessUserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBusinessUserRepository implements it.backend.repository.custom.IBusinessUserRepository {

    @Autowired
    private IBusinessUserEntityRepository iBusinessUserRepository;

    @Transactional
    public BusinessUserDTO createUser(BusinessUserDTO user) {
        BusinessUser entity = new BusinessUser();
        entity.setFirstName(user.getFirstName());
        entity.setFiscalCode(user.getFiscalCode());
        entity.setLastName(user.getLastName());
        iBusinessUserRepository.save(entity);
        user.setId(entity.getId());
        return user;
    }

    @Transactional
    public BusinessUserDTO updateUser(BusinessUserDTO user) {
        BusinessUser entity = iBusinessUserRepository.findById(user.getId()).get();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setFiscalCode(user.getFiscalCode());
        return user;
    }

    public BusinessUserDTO mergeUser(BusinessUserDTO user) { return null; }

    @Transactional
    public void deleteUser(Integer id) {
        BusinessUser entity = iBusinessUserRepository.getById(id);
        iBusinessUserRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    public BusinessUserDTO findById(Integer id) {
        return iBusinessUserRepository.findUserById(id);
    }

    @Override
    public BusinessUserPageDTO findUsers(int start, int max) {
        Page<BusinessUser> page = iBusinessUserRepository.findUsers(PageRequest.of(start, max));
        return makeDTO(page, start, max);
    }

    @Override
    public BusinessUserPageDTO findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) {
        Specification<BusinessUser> spec = BusinessUserSpec.getUsersByFilter(filter);
        Page<BusinessUser> page = iBusinessUserRepository.findAll(spec, PageRequest.of(start,max));
        return makeDTO(page,start,max);
    }

    private BusinessUserPageDTO makeDTO(Page<BusinessUser> page,int start, int max){
        List<BusinessUserDTO> dtoList = new ArrayList<>();
        for(BusinessUser businessUser: page.getContent()){
            dtoList.add(new BusinessUserDTO(businessUser.getId(),businessUser.getFirstName(),
                    businessUser.getLastName(),businessUser.getFiscalCode()));
        }
        return new BusinessUserPageDTO(page.getTotalElements(),start,max, dtoList);
    }
}
