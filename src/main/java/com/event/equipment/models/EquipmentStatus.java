package com.event.equipment.models;

import java.util.UUID;

public class EquipmentStatus {
    private UUID id;

    public EquipmentStatus() {
    }

    public EquipmentStatus(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
