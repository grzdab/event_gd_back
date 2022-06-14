package com.event.businessBranch;

import com.event.businessCategory.BusinessCategory;
import com.event.businessCategory.BusinessCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessBranchController {
    private final BusinessBranchService service;

    @Autowired
    public BusinessBranchController(BusinessBranchService service) {
        this.service = service;
    }

    @PostMapping("/admin/businessBranch")
    public BusinessBranch addBusinessBranch(@RequestBody BusinessBranch businessBranch){
        return service.addBusinessBranch(businessBranch);
    }

    @PutMapping("/admin/businessBranch/{businessBranchId}")
    public ResponseEntity<Object> updateBusinessBranch(@PathVariable Integer businessBranchId, @RequestBody BusinessBranch newBusinessBranch) {
        BusinessBranch updateBusinessBranch = service.updateBusinessBranch(businessBranchId, newBusinessBranch);
        if (updateBusinessBranch == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateBusinessBranch);
        }
    }

    @GetMapping("/admin/businessBranch/{businessBranchId}")
    public BusinessBranch getBusinessBranch(@PathVariable Integer businessBranchId){
        return service.getBusinessBranch(businessBranchId);
    }

    @GetMapping("/admin/businessBranch")
    public List<BusinessBranch> getAllBusinessBranch(){
        return service.getAllBusinessBranches();
    }

    @DeleteMapping("/admin/businessBranch/{businessBranchId}")
    public String deleteBusinessBranch(@PathVariable Integer businessBranchId){
        return service.deleteBusinessBranch(businessBranchId);
    }
}
