package com.event.equipment.dao;

import io.swagger.models.auth.In;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class EquipmentModel {

    private UUID id;

    @Column(columnDefinition = "int default 1")
    private int sortingId;

    @Column(columnDefinition = "varchar default unnamed")
    private String name;

    @Column(columnDefinition = "varchar default null")
    private String notes;

    @Column(columnDefinition = "int default 0")
    private int equipmentDataId;

    @Column(columnDefinition = "int default 0")
    private int equipmentCategoryId;

    private List<Integer> equipmentPhotoId;

    @Column(columnDefinition = "boolean default true")
    private boolean inUse;

    public EquipmentModel() {
    }

    public EquipmentModel(int sortingId, String name, String notes, int equipmentDataId, int equipmentCategoryId, List<Integer> equipmentPhotoId, boolean inUse) {
        this.sortingId = sortingId;
        this.name = name;
        this.notes = notes;
        this.equipmentDataId = equipmentDataId;
        this.equipmentCategoryId = equipmentCategoryId;
        this.equipmentPhotoId = equipmentPhotoId;
        this.inUse = inUse;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSortingId(int sortingId) {
        this.sortingId = sortingId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEquipmentDataId(int equipmentDataId) {
        this.equipmentDataId = equipmentDataId;
    }

    public void setEquipmentCategoryId(int equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public void setEquipmentPhotoId(List<Integer> equipmentPhotoId) {
        this.equipmentPhotoId = equipmentPhotoId;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getSortingId() {
        return sortingId;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public int getEquipmentDataId() {
        return equipmentDataId;
    }

    public int getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    @ElementCollection
    public List<Integer> getEquipmentPhotoId() {
        return equipmentPhotoId;
    }

    public boolean isInUse() {
        return inUse;
    }

    @Id
    @GeneratedValue
    public UUID getId() {
        return id;
    }
}


