package it.backend.web;

import it.backend.service.contract.ISimService;
import it.backend.model.SimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimController {

    @Autowired
    private ISimService simService;

    @PostMapping(value="/sim/user/activate/{userId}", consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public SimDTO acquiredByUser(@RequestBody SimDTO sim, @PathVariable Integer userId) {
        return simService.acquiredByUser(sim, userId);
    }

    @PutMapping(value="/sim/activate/{productId}/{simId}")
    @ResponseStatus(HttpStatus.OK)
    public SimDTO activateProduct(@PathVariable Integer simId, @PathVariable  Integer productId) {
        return simService.activateProduct(simId, productId);
    }

    @PutMapping(value="/sim/deactivate/{productId}/{simId}")
    @ResponseStatus(HttpStatus.OK)
    public SimDTO deactivateProduct(@PathVariable Integer simId, @PathVariable  Integer productId) {
        return simService.deActivateProduct(simId, productId);
    }

    @GetMapping(value = "/sim/user/{userId}")
    public ResponseEntity<List<SimDTO>> findSimByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(simService.findSimByUser(userId),HttpStatus.OK);
    }
}
