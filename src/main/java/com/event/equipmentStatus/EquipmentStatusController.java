package com.event.equipmentStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentStatusController {
    private final EquipmentStatusService service;

    public EquipmentStatusController(EquipmentStatusService service) {
        this.service = service;
    }

    @GetMapping("/equipment-status/{statusId}")
    EquipmentStatus getEquipmentStatusById(@PathVariable int statusId) {
        return service.getEquipmentStatus(statusId);
    }
}
