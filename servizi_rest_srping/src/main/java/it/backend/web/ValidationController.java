package it.backend.web;

import it.backend.model.ValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ValidationController {

    @PostMapping(value="/validation/create/{userId}", consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ValidationDTO create(@PathVariable Integer user) {
        return new ValidationDTO();
    }

}
