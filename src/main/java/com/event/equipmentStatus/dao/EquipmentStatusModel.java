package com.event.equipmentStatus.dao;

import javax.persistence.*;

@Entity
public class EquipmentStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "int default '0'")
    private Integer Status;

    public EquipmentStatusModel() {
    }

    public EquipmentStatusModel(int id, int status) {
        this.id = id;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
