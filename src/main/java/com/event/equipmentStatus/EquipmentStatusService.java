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
        EquipmentStatusModel model = equipmentStatusRepository.getById(id);
        return createEquipmentStatus(model);
    }

    public EquipmentStatus createEquipmentStatus(EquipmentStatusModel model) {
        return new EquipmentStatus(model.getId(), model.getStatus());
    }
}
