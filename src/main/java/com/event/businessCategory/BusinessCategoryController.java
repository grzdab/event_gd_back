package com.event.businessCategory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessCategoryController {

    private final BusinessCategoryService service;

    @Autowired
    public BusinessCategoryController(BusinessCategoryService service) {
        this.service = service;
    }

    @PostMapping("/admin/businessCategory")
    public BusinessCategory addBusinessCategory(@RequestBody BusinessCategory businessCategory){
        return service.addBusinessCategory(businessCategory);
    }

    @PutMapping("/admin/businessCategory/{businessCategoryId}")
    public ResponseEntity<Object> updateBusinessCategory(@PathVariable String businessCategoryId, @RequestBody BusinessCategory newBusinessCategory) {
        BusinessCategory updateBusinessCategory = service.updateBusinessCategory(businessCategoryId, newBusinessCategory);
        if (updateBusinessCategory == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateBusinessCategory);
        }
    }

    @GetMapping("/admin/businessCategory/{businessCategoryId}")
    public BusinessCategory getBusinessCategory(@PathVariable String businessCategoryId){
        return service.getBusinessCategory(businessCategoryId);
    }

    @GetMapping("/admin/businessCategory")
    public List<BusinessCategory> getAllBusinessCategories(){
        return service.getAllBusinessCategories();
    }

    @DeleteMapping("/admin/businessCategory/{businessCategoryId}")
    public String deleteBusinessCategory(@PathVariable String businessCategoryId){
        return service.deleteBusinessCategory(businessCategoryId);
    }

}
