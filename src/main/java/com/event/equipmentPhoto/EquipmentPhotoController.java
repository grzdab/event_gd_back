package com.event.equipmentPhoto;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentPhotoController {
    private final EquipmentPhotoService service;

    public EquipmentPhotoController(EquipmentPhotoService service) {
        this.service = service;
    }

    //TODO adding photos and list of photos
//    @PostMapping("equipment-photo")
//    EquipmentPhoto addEquipmentPhotoPath(@RequestBody EquipmentPhoto equipmentPhoto) {
//        return service.add
//    }

    @GetMapping("/equipment-photo/{photoId}")
    EquipmentPhoto getPhotoById(@PathVariable int photoId) {
        return service.getEquipmentPhoto(photoId);
    }

    @GetMapping("/equipment-photo-by-equipment")
    List<EquipmentPhoto> getPhotoByEquipment(EquipmentModel equipmentModel) {
        return service.createListOfEquipmentPhoto(equipmentModel);
    }
}
