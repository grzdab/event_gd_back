package com.event.representative;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class    RepresentativeController {

    private final RepresentativeService service;

    public RepresentativeController(RepresentativeService service) {
        this.service = service;
    }

    @GetMapping("/representative/{representativeId}")
    public Representative getRepresentative(@PathVariable int representativeId) {
        return service.getRepresentative(representativeId);
    }

    @GetMapping("/representative")
    public List<Representative> getAllRepresentative() {
        return service.getAllRepresentative();
    }

    @PostMapping("/representative")
    public Representative addRepresentative(@RequestBody Representative representative) {
        return service.addRepresentative(representative);
    }

    @PutMapping("/representative/{representativeId}")
    public Representative updateRepresentative(@PathVariable int representativeId, @RequestBody Representative newRepresentative) {
        return service.updateRepresentative(representativeId, newRepresentative);
    }

    @DeleteMapping("/representative/{representativeId}")
    public String deleteRepresentative(@PathVariable int representativeId) {
        return service.deleteRepresentative(representativeId);
    }

    @GetMapping("/representative/client/{clientId}")
    public List<Representative> getAllRepresentativesForClient(@PathVariable String clientId) {
        return service.getAllRepresentativesForClient(clientId);
    }
}
