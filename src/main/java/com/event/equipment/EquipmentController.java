package com.event.equipment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    Equipment getEquipmentById(@PathVariable int id) {
        return service.getEquipmentById(id);
    }

    @GetMapping()
    List<Equipment> getAllEquipment() {
        return service.getAllEquipment();
    }

    @PostMapping()
    Equipment addEquipment(@RequestBody Equipment equipment) {
        return service.addEquipment(equipment);
    }

    @PutMapping("/{id}")
    Equipment uploadEquipment(@PathVariable int id, @RequestBody Equipment newEquipment) {
        return service.updateEquipment(id, newEquipment);
    }

    @DeleteMapping("/{id}")
    String deleteEquipment(@PathVariable int id) {
        return service.deleteEquipment(id);
    }


    @GetMapping("/category/{id}")
    List<Equipment> getEquipmentByCategory(@PathVariable int id) {
        return service.getEquipmentByCategoryId(id);
    }

    @GetMapping("/status/{id}")
    List<Equipment> getEquipmentByStatus(@PathVariable int id) {
        return service.getEquipmentByStatusId(id);
    }

    @GetMapping("/ownership/{id}")
    List<Equipment> getEquipmentByOwnership(@PathVariable int id) {
        return service.getEquipmentByOwnershipId(id);
    }

    @GetMapping("/booking-status/{id}")
    List<Equipment> getEquipmentByBookingStatus(@PathVariable int id) {
        return service.getEquipmentByBookingStatusId(id);
    }

}
