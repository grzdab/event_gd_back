package com.event.equipmentData;

import com.event.equipmentData.dao.EquipmentDataModel;
import com.event.equipmentData.dao.EquipmentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record EquipmentDataService (EquipmentDataRepository equipmentDataRepository) {

    @Autowired
    public EquipmentDataService(EquipmentDataRepository equipmentDataRepository) {
        this.equipmentDataRepository = equipmentDataRepository;
    }

    public EquipmentData addEquipmentData(EquipmentData equipmentData) {
        if (equipmentData == null) return null;
        EquipmentDataModel equipmentDataModel = new EquipmentDataModel(
                equipmentData.getEquipmentId(),
                equipmentData.getWidth(),
                equipmentData.getLength(),
                equipmentData.getHeight(),
                equipmentData.getWeight(),
                equipmentData.getPowerRequired(),
                equipmentData.getStaffNeeded(),
                equipmentData.getMinimumAge(),
                equipmentData.getMaxParticipants());
        equipmentDataRepository.save(equipmentDataModel);
        equipmentData.setId(equipmentDataModel.getId());
        return equipmentData;
    }

    public EquipmentData getEquipmentData(Integer equipmentDataId) {
        try {
            EquipmentDataModel model = equipmentDataRepository.findById(equipmentDataId).get();
            return createEquipmentData(model);
        } catch (Exception e) {
            return null;
        }
    }

    private EquipmentData createEquipmentData(EquipmentDataModel model) {
        return new EquipmentData(
                model.getEquipmentId(),
                model.getWidth(),
                model.getLength(),
                model.getHeight(),
                model.getWeight(),
                model.getPowerRequired()
                , model.getStaffNeeded(),
                model.getMinimumAge(),
                model.getMaxParticipants());
    }
}
