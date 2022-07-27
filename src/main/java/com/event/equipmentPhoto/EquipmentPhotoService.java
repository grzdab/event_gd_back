package com.event.equipmentPhoto;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentPhoto.dao.EquipmentPhotoModel;
import com.event.equipmentPhoto.dao.EquipmentPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record EquipmentPhotoService (EquipmentPhotoRepository equipmentPhotoRepository) {

    @Autowired
    public EquipmentPhotoService(EquipmentPhotoRepository equipmentPhotoRepository) {
        this.equipmentPhotoRepository = equipmentPhotoRepository;
    }

    public EquipmentPhoto getEquipmentPhoto(Integer equipmentPhotoId) {
        EquipmentPhotoModel model = equipmentPhotoRepository.findById(equipmentPhotoId).get();
        return createEquipmentPhoto(model);
    }

    private EquipmentPhoto createEquipmentPhoto(EquipmentPhotoModel model) {
        return new EquipmentPhoto(model.getId(), model.getPhotoURI());
    }

    public List<Integer> createListOfPhotoId(List<EquipmentPhoto> photos) {
        if (photos == null) return null;
        List<Integer> photoIds = new ArrayList<>();
        for (EquipmentPhoto photo : photos) {
            photoIds.add(photo.getId());
        }
        return photoIds;
    }

    public List<EquipmentPhoto> createListOfEquipmentPhoto(EquipmentModel model) {
        List<Integer> photoId = model.getEquipmentPhotoId();
        List<EquipmentPhoto> photos = new ArrayList<>();
        for (Integer id : photoId) {
            photos.add(getEquipmentPhoto(id));
        }
        return photos;
    }
}
