package com.event.equipmentBookingStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentBookingStatusController {
    private final EquipmentBookingStatusService service;

    public EquipmentBookingStatusController(EquipmentBookingStatusService service) {
        this.service = service;
    }

    @GetMapping("/equipment/booking-status/{bookingStatusId}")
    public EquipmentBookingStatus getEquipmentBookingStatusById(int bookingStatusId) {
        return service.getEquipmentBookingStatusById(bookingStatusId);
    }
}
