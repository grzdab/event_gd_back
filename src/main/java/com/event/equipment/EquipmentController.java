package com.event.equipment;

import com.event.equipment.models.EquipmentCategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("/equipment/{id}")
    Equipment getEquipmentById(@PathVariable String id) {
        return service.getEquipmentById(id);
    }

    @GetMapping("/equipment")
    List<Equipment> getAllEquipment() {
       return service.getAllEquipment();
    }

    @PostMapping("/equipment")
    Equipment addEquipment(@RequestBody Equipment equipment) {
        return service.addEquipment(equipment);
    }

    @PutMapping("/equipment/{id}")
    Equipment uploadEquipment(@PathVariable String id, @RequestBody Equipment newEquipment) {
        return service.uploadEquipment(id, newEquipment);
    }

    @DeleteMapping("/equipment/{id}")
    String deleteEquipment(@PathVariable String id) {
        return service.deleteEquipment(id);
    }
}
