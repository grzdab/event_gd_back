package com.event.equipmentCategory;

import com.event.equipmentCategory.dao.EquipmentCategoryModel;
import com.event.equipmentCategory.dao.EquipmentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public record EquipmentCategoryService (EquipmentCategoryRepository equipmentCategoryRepository) {

    @Autowired
    public EquipmentCategoryService(EquipmentCategoryRepository equipmentCategoryRepository) {
        this.equipmentCategoryRepository = equipmentCategoryRepository;
    }

    public EquipmentCategory getEquipmentCategory(int equipmentCategoryId) {
        EquipmentCategoryModel model = equipmentCategoryRepository.findById(String.valueOf(equipmentCategoryId)).get();
        return createEquipmentCategory(model);
    }

    private EquipmentCategory createEquipmentCategory(EquipmentCategoryModel model) {
        return new EquipmentCategory(model.getId(), model.getName(), model.getSortingGroup(), model.getDescription());
    }
}
