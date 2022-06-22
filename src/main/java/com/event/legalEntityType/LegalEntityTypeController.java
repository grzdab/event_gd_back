package com.event.legalEntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LegalEntityTypeController{

    private final LegalEntityTypeService service;

    @Autowired
    public LegalEntityTypeController(LegalEntityTypeService service) {
        this.service = service;
    }

    @PostMapping("/admin/legalEntityType")
    public LegalEntityType addLegalEntityType(@RequestBody LegalEntityType legalEntityType){
        return service.addLegalEntityType(legalEntityType);
    }

    @PutMapping("/admin/legalEntityType/{legalEntityTypeId}")
    public ResponseEntity<Object> updateLegalEntityType(@PathVariable int legalEntityTypeId, @RequestBody LegalEntityType newLegalEntityType) {
        LegalEntityType updateLegalEntityType = service.updateLegalEntityType(legalEntityTypeId, newLegalEntityType);
        if (updateLegalEntityType == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateLegalEntityType);
        }
    }

    @GetMapping("/admin/legalEntityType/{legalEntityTypeId}")
    public LegalEntityType getLegalEntityType(@PathVariable int legalEntityTypeId){
        return service.getLegalEntityType(legalEntityTypeId);
    }

    @GetMapping("/admin/legalEntityType")
    public List<LegalEntityType> getAllLegalEntityTypes(){
        return service.getAllLegalEntityTypes();
    }

    @DeleteMapping("/admin/legalEntityType/{legalEntityTypeId}")
    public String deleteLegalEntityType(@PathVariable int legalEntityTypeId){
        return service.deleteLegalEntityType(legalEntityTypeId);
    }

}

