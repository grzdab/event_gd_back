package com.event.equipmentStatus.dao;

import javax.persistence.*;

@Entity
public class EquipmentStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "default value 0")
    private int Status;

    public EquipmentStatusModel() {
    }

    public EquipmentStatusModel(int id, int status) {
        this.id = id;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return Status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
