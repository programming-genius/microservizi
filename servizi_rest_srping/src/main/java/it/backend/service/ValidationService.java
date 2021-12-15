package it.backend.service;

import it.backend.service.contract.IValidationService;
import it.backend.model.ValidationDTO;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {
    @Override
    public ValidationDTO validateUser(Integer userId) {
        return new ValidationDTO();
    }
}
