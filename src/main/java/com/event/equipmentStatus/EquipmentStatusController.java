package com.event.equipmentStatus;

import com.event.equipmentStatus.EquipmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipment-status")
@CrossOrigin("http://localhost:3000")
public class EquipmentStatusController {
    private final EquipmentStatusService service;

    @GetMapping("/{statusId}")
    EquipmentStatus getEquipmentStatusById(@PathVariable int statusId) {
        return service.getEquipmentStatusById(statusId);
    }

    @GetMapping
    public List<EquipmentStatus> getAllEquipmentStatuses() {
        return service.getEquipmentStatuses();
    }

    @PostMapping
    public EquipmentStatus addEquipmentStatus(@RequestBody EquipmentStatus equipmentStatus) {
        return service.addEquipmentStatus(equipmentStatus);
    }

    @PutMapping("/{id}")
    public EquipmentStatus updateEquipmentStatus(
        @PathVariable("id") int id,
        @RequestBody EquipmentStatus equipmentStatus) {
        return service.updateEquipmentStatus(id, equipmentStatus);
    }

    @DeleteMapping(path = "{id}")
    public String deleteEquipmentStatus(@PathVariable("id") int id) {
        return service.deleteEquipmentStatus(id);
    }

}
