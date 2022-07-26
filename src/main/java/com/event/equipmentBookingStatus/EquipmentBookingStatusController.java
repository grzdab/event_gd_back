package com.event.equipmentBookingStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EquipmentBookingStatusController {
    private final EquipmentBookingStatusService service;

    public EquipmentBookingStatusController(EquipmentBookingStatusService service) {
        this.service = service;
    }

    @PostMapping("/equipment/booking-status")
    public EquipmentBookingStatus addEquipmentBookingStatus(@RequestBody EquipmentBookingStatus equipmentBookingStatus) {
        return service.addEquipmentBookingStatus(equipmentBookingStatus);
    }

    @GetMapping("/equipment/booking-status/{bookingStatusId}")
    public EquipmentBookingStatus getEquipmentBookingStatusById(int bookingStatusId) {
        return service.getEquipmentBookingStatusById(bookingStatusId);
    }

    //TODO delete

    @DeleteMapping("/equipment/booking-status/{bookingStatusId}")
    public String deleteEquipmentBookingStatus(@PathVariable int bookingStatusId) {
        return service.deleteEquipmentBookingStatus(bookingStatusId);
    }
}
