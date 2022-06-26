package com.event.equipmentCategory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentCategoryController {
    private final EquipmentCategoryService service;

    public EquipmentCategoryController(EquipmentCategoryService service) {
        this.service = service;
    }

    @GetMapping("/equipment/category-service/{categoryServiceId}")
    public EquipmentCategory getCategoryServiceById(int categoryServiceId) {
        return service.getEquipmentCategory(categoryServiceId);
    }
}
