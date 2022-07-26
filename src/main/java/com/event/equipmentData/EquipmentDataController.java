package com.event.equipmentData;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentDataController {
    private final EquipmentDataService service;

    public EquipmentDataController(EquipmentDataService service) {
        this.service = service;
    }

    @PostMapping("/equipment/data")
    EquipmentData addEquipmentData(@RequestBody EquipmentData equipmentData) {
        return service.addEquipmentData(equipmentData);
    }

    @GetMapping("/equipment-data/{dataId}")
    EquipmentData getEquipmentDataById(@PathVariable int dataId) {
        return service.getEquipmentData(dataId);
    }

    //TODO update
}
