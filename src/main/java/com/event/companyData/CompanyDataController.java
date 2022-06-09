package com.event.companyData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CompanyDataController {

    private final CompanyDataService service;

    @Autowired
    public CompanyDataController(CompanyDataService service){
        this.service = service;
    }


    @PostMapping("/companyData")
    public CompanyData addCompanyData(@RequestBody CompanyData companyData) {
        return service.addCompanyData(companyData);
    }

    @PutMapping("/companyData/{companyDataId}")
    public ResponseEntity<Object> updateCompanyData(@PathVariable UUID companyDataId, @RequestBody CompanyData newCompanyData) {
        CompanyData updateCompanyData = service.updateCompanyData(companyDataId, newCompanyData);
        if (updateCompanyData == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateCompanyData);
        }
    }

    @GetMapping("/companyData/{companyDataId}")
    public CompanyData getCompanyData(@PathVariable UUID companyDataId) {
        return service.getCompanyData(companyDataId);
    }

    @GetMapping("/companyData")
    public List<CompanyData> getAllCompanyData() {
        return service.getAllCompanyData();
    }

    @DeleteMapping("/companyData/{companyDataId}")
    public String deleteCompanyData(@PathVariable UUID companyDataId) {
        return service.deleteCompanyData(companyDataId);
    }

}

