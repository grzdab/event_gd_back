package com.event.equipment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("/equipment/{id}")
    Equipment getEquipmentById(@PathVariable int id) {
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
    Equipment uploadEquipment(@PathVariable int id, @RequestBody Equipment newEquipment) {
        return service.updateEquipment(id, newEquipment);
    }

    @DeleteMapping("/equipment/{id}")
    String deleteEquipment(@PathVariable int id) {
        return service.deleteEquipment(id);
    }





    @GetMapping("/equipment/category/{id}")
    List<Equipment> getEquipmentByCategory(@PathVariable int id) {
        return service.getEquipmentByCategoryId(id);
    }

    @GetMapping("/equipment/status/{id}")
    List<Equipment> getEquipmentByStatus(@PathVariable int id) {
        return service.getEquipmentByStatusId(id);
    }

    @GetMapping("/equipment/ownership/{id}")
    List<Equipment> getEquipmentByOwnership(@PathVariable int id) {
        return service.getEquipmentByOwnershipId(id);
    }

    @GetMapping("/equipment/booking-status/{id}")
    List<Equipment> getEquipmentByBookingStatus(@PathVariable int id) {
        return service.getEquipmentByBookingStatusId(id);
    }

}
