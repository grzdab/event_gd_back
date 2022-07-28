package com.event.equipmentPhoto;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EquipmentPhotoController {
    private final EquipmentPhotoService service;

    public EquipmentPhotoController(EquipmentPhotoService service) {
        this.service = service;
    }

    @GetMapping("/equipment-photo/{photoId}")
    EquipmentPhoto getPhotoById(@PathVariable int photoId) {
        return service.getEquipmentPhoto(photoId);
    }

    @GetMapping("/equipment-photo-by-equipment")
    List<EquipmentPhoto> getPhotoByEquipment(EquipmentModel equipmentModel) {
//        return service.createListOfEquipmentPhoto(equipmentModel);
        return null;
    }
}
