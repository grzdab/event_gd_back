package com.event.equipmentCategory;

import com.event.equipmentCategory.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryService {

    private final EquipmentCategoryRepository repository;

    public List<EquipmentCategory> getEquipmentCategories() {
        Collection<EquipmentCategoryModel> models = repository.findAll();
        return models
                .stream()
                .map(model -> new EquipmentCategory(model.getId(), model.getSortingGroup(), model.getName(), model.getDescription()))
                .collect(Collectors.toList());
    }

    public EquipmentCategory addEquipmentCategory(EquipmentCategory equipmentCategory) {
        if (getEquipmentCategoryByName(equipmentCategory.getName()) == null) {
            EquipmentCategoryModel model = new EquipmentCategoryModel(
                    equipmentCategory.getId(),
                    equipmentCategory.getName(),
                    equipmentCategory.getSortingGroup(),
                    equipmentCategory.getDescription());
            repository.save(model);
            equipmentCategory.setId(model.getId());
            return equipmentCategory;
        }
        return null;
    }

    public EquipmentCategory updateEquipmentCategory(int id, EquipmentCategory equipmentCategory) {
        EquipmentCategoryModel model = repository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find equipment category with specified ID"));
        model.setName(equipmentCategory.getName());
        model.setDescription(equipmentCategory.getDescription());
        repository.save(model);
        return equipmentCategory;
    }

    public EquipmentCategory getEquipmentCategoryById(int id) {
        EquipmentCategoryModel model = repository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find equipment category with specified ID"));
        return createEquipmentCategory(model);
    }

    public String deleteEquipmentCategory(int id) {
        String categoryName = getEquipmentCategoryById(id).getName();
        // TODO check if entry exists
        repository.deleteById(id);
        return String.format("Category #%d (%s) has been deleted from the database.", id, categoryName);
    }

    private EquipmentCategory createEquipmentCategory(EquipmentCategoryModel model) {
        return new EquipmentCategory(
                model.getId(),
                model.getSortingGroup(),
                model.getName(),
                model.getDescription()
        );
    }

    private EquipmentCategory getEquipmentCategoryByName(String name) {
        EquipmentCategoryModel model = repository.findByName(name);
        if (model != null) {
            return new EquipmentCategory(model.getId(), model.getSortingGroup(), model.getName(), model.getDescription());
        }
        return null;
    }
}
