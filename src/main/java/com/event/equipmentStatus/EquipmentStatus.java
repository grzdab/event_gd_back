package com.event.equipmentStatus;

import javax.persistence.Id;
import java.util.UUID;

public class EquipmentStatus {
    private int id;
    private int status;

    public EquipmentStatus() {
    }

    public EquipmentStatus(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
