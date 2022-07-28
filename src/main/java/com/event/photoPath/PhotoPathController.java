package com.event.photoPath;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PhotoPathController {
    private final PhotoPathService service;

    public PhotoPathController(PhotoPathService service) {
        this.service = service;
    }

    //TODO adding photos and list of photos

    @PostMapping("/equipment/photo-paths")
    String addEquipmentPhotoPaths(@RequestBody List<String> photoNames, Equipment equipment) {
        return service.addEquipmentPhotoPaths(photoNames, equipment);
    }

    @GetMapping("/photo-path/{photoId}")
    PhotoPath getPhotoPathById(@PathVariable int photoId) {
        return service.getPhotoPath(photoId);
    }

    @GetMapping("/photo-path-by-equipment")
    List<PhotoPath> getPhotoByEquipment(EquipmentModel equipmentModel) {
        return service.createListOfEquipmentPhotoPaths(equipmentModel);
    }

    @GetMapping("/photo-paths")
    List<PhotoPath> getAllPaths() {
        return service.getAllPhotoPaths();
    }
}
