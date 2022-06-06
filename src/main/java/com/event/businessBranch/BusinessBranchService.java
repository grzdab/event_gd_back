package com.event.businessBranch;

import com.event.businessBranch.dao.BusinessBranchModel;
import com.event.businessBranch.dao.BusinessBranchRepository;
import com.event.businessCategory.BusinessCategory;
import com.event.businessCategory.dao.BusinessCategoryModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record BusinessBranchService(BusinessBranchRepository businessBranchRepository) {

    public BusinessBranchService(BusinessBranchRepository businessBranchRepository) {
        this.businessBranchRepository = businessBranchRepository;
    }


    public BusinessBranch addBusinessBranch(BusinessBranch businessBranch){
        BusinessBranchModel model = new BusinessBranchModel(businessBranch.getName());
        businessBranchRepository.save(model);
        businessBranch.setId(model.getId());
        return businessBranch;
    }
    public BusinessBranch getBusinessBranch(String businessBranchId){
        BusinessBranchModel model = businessBranchRepository.findById(businessBranchId).get();
        return createBusinessBranch(model);
    }
    public String deleteBusinessBranch(String businessBranchId){
        businessBranchRepository.deleteById(businessBranchId);
        return "delete";
    }
    public BusinessBranch updateBusinessBranch(String businessBranchId, BusinessBranch newBusinessBranch){
        BusinessBranchModel model = businessBranchRepository.findById(businessBranchId).get();
        model.setName(newBusinessBranch.getName());
        businessBranchRepository.save(model);
        return newBusinessBranch;
    }
    public List<BusinessBranch> getAllBusinessBranches(){
        List<BusinessBranch> businessBranchList = new ArrayList<>();
        Iterable<BusinessBranchModel> businessBranchModels = businessBranchRepository.findAll();
        for (BusinessBranchModel model: businessBranchModels){
            businessBranchList.add(createBusinessBranch(model));
        }
        return businessBranchList;
    }

    private BusinessBranch createBusinessBranch(BusinessBranchModel businessBranchModel){
        return new BusinessBranch(businessBranchModel.getId(), businessBranchModel.getName());
    }
}
