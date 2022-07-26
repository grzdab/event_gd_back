package com.event.equipmentStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentStatusController {
    private final EquipmentStatusService service;

    public EquipmentStatusController(EquipmentStatusService service) {
        this.service = service;
    }

    @PostMapping("equipment-status")
    EquipmentStatus addEquipmentStatus(@RequestBody EquipmentStatus equipmentStatus) {
        return service.addEquipmentStatus(equipmentStatus);
    }

    @GetMapping("/equipment-status/{statusId}")
    EquipmentStatus getEquipmentStatusById(@PathVariable int statusId) {
        return service.getEquipmentStatus(statusId);
    }

    //TODO all other controllers
    @DeleteMapping("equipment-status/{statusId}")
    String deleteEquipmentById(@PathVariable int statusId) {
        return service.deleteEquipmentStatus(statusId);
    }
}
