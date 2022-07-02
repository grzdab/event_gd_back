package com.event.equipmentCategory;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentCategoryController {
    private final EquipmentCategoryService service;

    public EquipmentCategoryController(EquipmentCategoryService service) {
        this.service = service;
    }
    @GetMapping("/equipment-category/{categoryId}")
    public EquipmentCategory getCategoryServiceById(@PathVariable String categoryId) {
        return service.getEquipmentCategory(Integer.parseInt(categoryId));
    }
}
