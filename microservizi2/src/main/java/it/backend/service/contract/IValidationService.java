package it.backend.service.contract;

import it.backend.model.ValidationDTO;

public interface IValidationService {
    ValidationDTO validateUser(Integer userId);
}
