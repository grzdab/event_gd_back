package com.event.equipmentBookingStatus;

import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-booking-status")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipmentBookingStatusController {
    private final EquipmentBookingStatusService service;

    public EquipmentBookingStatusController(EquipmentBookingStatusService service) {
        this.service = service;
    }


    @GetMapping
    public List<EquipmentBookingStatus> getAllEquipmentCategories() {
        return service.getEquipmentBookingStatuses();
    }

    @GetMapping(path = "/{id}")
    public EquipmentBookingStatus getEquipmentBookingStatusById(@PathVariable("id") int id) {
        return service.getEquipmentBookingStatusById(id);
    }

    @PostMapping
    public EquipmentBookingStatus addEquipmentBookingStatus(@RequestBody EquipmentBookingStatus equipmentBookingStatus) {
        return service.addEquipmentBookingStatus(equipmentBookingStatus);
    }

    @PutMapping("/{id}")
    public EquipmentBookingStatus updateEquipmentBookingStatus(
        @PathVariable("id") int id,
        @RequestBody EquipmentBookingStatus equipmentBookingStatus) {
        return service.updateEquipmentBookingStatus(id, equipmentBookingStatus);
    }

    @DeleteMapping(path = "{id}")
    public String deleteEquipmentBookingStatus(@PathVariable("id") int id) {
        return service.deleteEquipmentBookingStatus(id);
    }


}
