package com.event.equipmentOwnership;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipment-ownership")
@CrossOrigin("http://localhost:3000")
public class EquipmentOwnershipController {
    private final EquipmentOwnershipService service;

    @GetMapping("/{id}")
    EquipmentOwnership getEquipmentOwnershipById(@PathVariable int id) {
        return service.getEquipmentOwnershipById(id);
    }

    @GetMapping
    public List<EquipmentOwnership> getAllEquipmentOwnershipTypes() {
        return service.getEquipmentOwnershipTypes();
    }

    @PostMapping
    public EquipmentOwnership addEquipmentOwnership(@RequestBody EquipmentOwnership equipmentOwnership) {
        return service.addEquipmentOwnership(equipmentOwnership);
    }

    @PutMapping("/{id}")
    public EquipmentOwnership updateEquipmentOwnership(
        @PathVariable("id") int id,
        @RequestBody EquipmentOwnership equipmentOwnership) {
        return service.updateEquipmentOwnership(id, equipmentOwnership);
    }

    @DeleteMapping(path = "{id}")
    public String deleteEquipmentOwnership(@PathVariable("id") int id) {
        return service.deleteEquipmentOwnership(id);
    }

}
