package com.event.equipmentCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipment-category")
@CrossOrigin("http://localhost:3000")
public class EquipmentCategoryController {

    private final EquipmentCategoryService service;

    @GetMapping
    public List<EquipmentCategory> getAllEquipmentCategories() {
        return service.getEquipmentCategories();
    }

    @GetMapping(path = "/{id}")
    public EquipmentCategory getEquipmentCategoryById(@PathVariable("id") int id) {
        return service.getEquipmentCategoryById(id);
    }

    @PostMapping
    public EquipmentCategory addEquipmentCategory(@RequestBody EquipmentCategory equipmentCategory) {
        return service.addEquipmentCategory(equipmentCategory);
    }

    @PutMapping("/{id}")
    public EquipmentCategory updateEquipmentCategory(
        @PathVariable("id") int id,
        @RequestBody EquipmentCategory equipmentCategory) {
        return service.updateEquipmentCategory(id, equipmentCategory);
    }

    @DeleteMapping(path = "{id}")
    public String deleteEquipmentCategory(@PathVariable("id") int id) {
        return service.deleteEquipmentCategory(id);
    }
}