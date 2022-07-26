package com.event.photoPath;

import com.event.equipment.dao.EquipmentModel;
import com.event.photoPath.dao.PhotoPathModel;
import com.event.photoPath.dao.PhotoPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record PhotoPathService(PhotoPathRepository photoPathRepository) {

    @Autowired
    public PhotoPathService(PhotoPathRepository photoPathRepository) {
        this.photoPathRepository = photoPathRepository;
    }

    public PhotoPath addPhotoPath(PhotoPath photoPath) {
        return null;
    }

    public PhotoPath addPhotoPaths(List<PhotoPath> photoPaths) {
        return null;
    }

    public PhotoPath getPhotoPath(Integer equipmentPhotoId) {
        try {
            PhotoPathModel model = photoPathRepository.findById(equipmentPhotoId).get();
            return createPhotoPath(model);
        } catch (Exception e) {
            return null;
        }
    }

    private PhotoPath createPhotoPath(PhotoPathModel model) {
        if (model == null) return null;
        return new PhotoPath(model.getId(), model.getPhotoURI());
    }

    public List<Integer> createListOfPhotoId(List<PhotoPath> photos) {
        if (photos == null) return null;
        try {
            List<Integer> photoIds = new ArrayList<>();
            for (PhotoPath photo : photos) {
                photoIds.add(photo.getId());
            }
            return photoIds;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PhotoPath> createListOfEquipmentPhoto(EquipmentModel model) {
        List<Integer> photoId = model.getEquipmentPhotoId();
        List<PhotoPath> photos = new ArrayList<>();
        for (Integer id : photoId) {
            photos.add(getPhotoPath(id));
        }
        return photos;
    }
}
