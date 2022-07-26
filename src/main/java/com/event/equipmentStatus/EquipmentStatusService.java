package com.event.equipmentStatus;

import com.event.equipmentStatus.dao.EquipmentStatusModel;
import com.event.equipmentStatus.dao.EquipmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public record EquipmentStatusService(EquipmentStatusRepository equipmentStatusRepository) {

    @Autowired
    public EquipmentStatusService(EquipmentStatusRepository equipmentStatusRepository) {
        this.equipmentStatusRepository = equipmentStatusRepository;
    }

    public EquipmentStatus getEquipmentStatus(int id) {
        try {
            EquipmentStatusModel model = equipmentStatusRepository.getById(id);
            return createEquipmentStatus(model);
        } catch (Exception e) {
            return null;
        }
    }

    public EquipmentStatus createEquipmentStatus(EquipmentStatusModel model) {
        return new EquipmentStatus(model.getId(), model.getName());
    }

    public EquipmentStatus addEquipmentStatus(EquipmentStatus status) {
        if (getEquipmentStatusByName(status.getName()) == null) {
            EquipmentStatusModel model = new EquipmentStatusModel(
                    status.getId(),
                    status.getName()
            );
            equipmentStatusRepository.save(model);
            status.setId(model.getId());
            return status;
        }
        return null;
    }


    //TODO propably error while more than one object
    private EquipmentStatus getEquipmentStatusByName(String name) {
        EquipmentStatusModel model = equipmentStatusRepository.findByName(name);
        if (model != null) {
            return new EquipmentStatus(model.getId(), model.getName());
        }
        return null;
    }

    public String deleteEquipmentStatus(int id) {
        return "null";
    }
}
