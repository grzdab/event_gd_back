package com.event.equipmentData;

import com.event.equipmentData.dao.EquipmentDataModel;
import com.event.equipmentData.dao.EquipmentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public record EquipmentDataService (EquipmentDataRepository equipmentDataRepository) {

    @Autowired
    public EquipmentDataService(EquipmentDataRepository equipmentDataRepository) {
        this.equipmentDataRepository = equipmentDataRepository;
    }

    // TODO add update


    public EquipmentData addEquipmentData(EquipmentData equipmentData) {
        EquipmentDataModel equipmentDataModel = new EquipmentDataModel(equipmentData);
        equipmentDataRepository.save(equipmentDataModel);
        equipmentData.setId(equipmentDataModel.getId());
        return equipmentData;
    }

    public EquipmentData getEquipmentData(String equipmentDataId) {
        if (Integer.parseInt(equipmentDataId) == 0) {
            return new EquipmentData(0,0,0,0,0,0,0,0,0);
        }
        EquipmentDataModel model = equipmentDataRepository.findById(Integer.parseInt(equipmentDataId)).get();
        return createEquipmentData(model);
    }

    public EquipmentData getEquipmentDataById(int id) {
        EquipmentDataModel dataModel = equipmentDataRepository.findById(id).get();
        return createEquipmentData(dataModel);
    }

    private EquipmentData createEquipmentData(EquipmentDataModel model) {
        return new EquipmentData(
            model.getEquipmentId(),
            model.getWidth(),
            model.getLength(),
            model.getHeight(),
            model.getWeight(),
            model.getPowerRequired(),
            model.getStaffNeeded(),
            model.getMinimumAge(),
            model.getMaxParticipants());
    }
}
