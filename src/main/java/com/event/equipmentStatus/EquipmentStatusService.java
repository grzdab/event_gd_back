package com.event.equipmentStatus;

import com.event.equipmentStatus.dao.EquipmentStatusModel;
import com.event.equipmentStatus.dao.EquipmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record EquipmentStatusService(EquipmentStatusRepository repository) {

    @Autowired
    public EquipmentStatusService(EquipmentStatusRepository repository) {
        this.repository = repository;
    }

    public List<EquipmentStatus> getEquipmentStatuses() {
        Collection<EquipmentStatusModel> models = repository.findAll();
        return models
            .stream()
            .map(model -> new EquipmentStatus(model.getId(), model.getName()))
            .collect(Collectors.toList());
    }


    public EquipmentStatus getEquipmentStatusById(int id) {
        if (id == 0) return null;
        EquipmentStatusModel model = repository.getById(id);
        return createEquipmentStatus(model);
    }

    public EquipmentStatus createEquipmentStatus(EquipmentStatusModel model) {
        return new EquipmentStatus(model.getId(), model.getName());
    }


    public EquipmentStatus addEquipmentStatus(EquipmentStatus equipmentStatus) {
        if (getEquipmentStatusByName(equipmentStatus.getName()) == null) {
            EquipmentStatusModel model = new EquipmentStatusModel(
                equipmentStatus.getId(),
                equipmentStatus.getName());
            repository.save(model);
            equipmentStatus.setId(model.getId());
            return equipmentStatus;
        }
        return null;
    }

    private EquipmentStatus getEquipmentStatusByName(String name) {
        EquipmentStatusModel model = repository.findByName(name);
        if (model != null) {
            return new EquipmentStatus(model.getId(), model.getName());
        }
        return null;
    }

    public EquipmentStatus updateEquipmentStatus(int id, EquipmentStatus equipmentStatus) {
        EquipmentStatusModel model = repository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find equipment status with specified ID"));
        model.setName(equipmentStatus.getName());
        repository.save(model);
        return createEquipmentStatus(model);
    }

    public String deleteEquipmentStatus(int id) {
        String statusName = getEquipmentStatusById(id).getName();
        repository.deleteById(id);
        return String.format("Status #%d (%s) has been deleted from the database.", id, statusName);
    }

}
