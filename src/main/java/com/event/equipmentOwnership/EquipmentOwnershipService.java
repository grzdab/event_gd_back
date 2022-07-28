package com.event.equipmentOwnership;

import com.event.equipmentOwnership.dao.EquipmentOwnershipModel;
import com.event.equipmentOwnership.dao.EquipmentOwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record EquipmentOwnershipService(EquipmentOwnershipRepository repository) {

    @Autowired
    public EquipmentOwnershipService(EquipmentOwnershipRepository repository) {
        this.repository = repository;
    }

    public List<EquipmentOwnership> getEquipmentOwnershipTypes() {
        Collection<EquipmentOwnershipModel> models = repository.findAll();
        return models
            .stream()
            .map(model -> new EquipmentOwnership(model.getId(), model.getName()))
            .collect(Collectors.toList());
    }


    public EquipmentOwnership getEquipmentOwnershipById(int id) {
        if (id == 0) return null;
        EquipmentOwnershipModel model = repository.getById(id);
        return createEquipmentOwnership(model);
    }

    public EquipmentOwnership createEquipmentOwnership(EquipmentOwnershipModel model) {
        return new EquipmentOwnership(model.getId(), model.getName());
    }


    public EquipmentOwnership addEquipmentOwnership(EquipmentOwnership equipmentOwnership) {
        if (getEquipmentOwnershipByName(equipmentOwnership.getName()) == null) {
            EquipmentOwnershipModel model = new EquipmentOwnershipModel(
                equipmentOwnership.getName());
            repository.save(model);
            equipmentOwnership.setId(model.getId());
            return equipmentOwnership;
        }
        return null;
    }

    private EquipmentOwnership getEquipmentOwnershipByName(String name) {
        EquipmentOwnershipModel model = repository.findByName(name);
        if (model != null) {
            return new EquipmentOwnership(model.getId(), model.getName());
        }
        return null;
    }

    public EquipmentOwnership updateEquipmentOwnership(int id, EquipmentOwnership equipmentOwnership) {
        EquipmentOwnershipModel model = repository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find equipment ownership type with specified ID"));
        model.setName(equipmentOwnership.getName());
        repository.save(model);
        return equipmentOwnership;
    }

    public String deleteEquipmentOwnership(int id) {
        String categoryName = getEquipmentOwnershipById(id).getName();
        repository.deleteById(id);
        return String.format("Ownership #%d (%s) has been deleted from the database.", id, categoryName);
    }

}
