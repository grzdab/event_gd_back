package com.event.businessCategory;

import com.event.businessCategory.dao.BusinessCategoryModel;
import com.event.businessCategory.dao.BusinessCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record BusinessCategoryService(BusinessCategoryRepository businessCategoryRepository) {

    public BusinessCategoryService(BusinessCategoryRepository businessCategoryRepository){
        this.businessCategoryRepository = businessCategoryRepository;
    }

    public BusinessCategory addBusinessCategory(BusinessCategory businessCategory){
        BusinessCategoryModel model = new BusinessCategoryModel(businessCategory.getName());
        businessCategoryRepository.save(model);
        businessCategory.setId(model.getId());
        return businessCategory;
    }
    public BusinessCategory getBusinessCategory(Integer businessCategoryId){
        BusinessCategoryModel model = businessCategoryRepository.findById(businessCategoryId).get();
        return createBusinessCategory(model);
    }
    public String deleteBusinessCategory(Integer businessCategoryId){
        businessCategoryRepository.deleteById(businessCategoryId);
        return "delete";
    }
    public BusinessCategory updateBusinessCategory(Integer businessCategoryId, BusinessCategory newBusinessCategory){
        BusinessCategoryModel model = businessCategoryRepository.findById(businessCategoryId).get();
        model.setName(newBusinessCategory.getName());
        businessCategoryRepository.save(model);
        return newBusinessCategory;
    }
    public List<BusinessCategory> getAllBusinessCategories(){
        List<BusinessCategory> businessCategoryList = new ArrayList<>();
        Iterable<BusinessCategoryModel> businessCategoryModels = businessCategoryRepository.findAll();
        for (BusinessCategoryModel model: businessCategoryModels){
            businessCategoryList.add(createBusinessCategory(model));
        }
        return businessCategoryList;
    }

    private BusinessCategory createBusinessCategory(BusinessCategoryModel businessCategoryModel){
        return new BusinessCategory(businessCategoryModel.getId(), businessCategoryModel.getName());
    }
}
