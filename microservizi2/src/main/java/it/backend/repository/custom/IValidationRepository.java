package it.backend.repository.custom;

import it.backend.model.ValidationDTO;

public interface IValidationRepository {
    ValidationDTO validateUser(Integer userId);
}
