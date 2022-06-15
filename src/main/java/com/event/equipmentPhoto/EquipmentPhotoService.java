package com.event.equipmentPhoto;

import com.event.equipmentPhoto.dao.EquipmentPhotoModel;
import com.event.equipmentPhoto.dao.EquipmentPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public record EquipmentPhotoService (EquipmentPhotoRepository equipmentPhotoRepository) {

    @Autowired
    public EquipmentPhotoService(EquipmentPhotoRepository equipmentPhotoRepository) {
        this.equipmentPhotoRepository = equipmentPhotoRepository;
    }

    public EquipmentPhoto getEquipmentPhoto(String equipmentPhotoId) {
        EquipmentPhotoModel model = equipmentPhotoRepository.findById(equipmentPhotoId).get();
        return createEquipmentPhoto(model);
    }

    private EquipmentPhoto createEquipmentPhoto(EquipmentPhotoModel model) {
        return new EquipmentPhoto(model.getId(), model.getPhotoURI());
    }
}
