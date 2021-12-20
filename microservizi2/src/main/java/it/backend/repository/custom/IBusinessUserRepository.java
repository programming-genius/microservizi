package it.backend.repository.custom;

import it.backend.model.BusinessUserDTO;
import it.backend.model.BusinessUserFilterDTO;
import it.backend.model.BusinessUserPageDTO;

public interface IBusinessUserRepository {
    BusinessUserDTO createUser(BusinessUserDTO user);
    BusinessUserDTO updateUser(BusinessUserDTO user);
    BusinessUserDTO mergeUser(BusinessUserDTO user);
    void deleteUser(Integer id);
    BusinessUserDTO findById(Integer id);
    BusinessUserPageDTO findUsers(int start, int max);
    BusinessUserPageDTO findUsersByFilter(int start, int max, BusinessUserFilterDTO filter);
}
