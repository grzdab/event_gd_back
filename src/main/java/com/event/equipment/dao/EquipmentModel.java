package com.event.equipment.dao;

import javax.persistence.*;
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
    private int width;

    @Column(columnDefinition = "int default 0")
    private int length;

    @Column(columnDefinition = "int default 0")
    private int height;

    @Column(columnDefinition = "int default 0")
    private int weight;

    @Column(columnDefinition = "int default 0")
    private int powerRequired;

    @Column(columnDefinition = "int default 0")
    private int staffNeeded;

    @Column(columnDefinition = "int default 0")
    private int minimumAge;

    @Column(columnDefinition = "int default 0")
    private int maxParticipants;

    @Column(columnDefinition = "int default 0")
    private int equipmentCategoryId;

    @Column(columnDefinition = "boolean default true")
    private boolean inUse;

    public EquipmentModel() {
    }

    public EquipmentModel(int sortingId, String name, String notes, int width, int length, int height, int weight, int powerRequired, int staffNeeded, int minimumAge, int maxParticipants, int equipmentCategoryId, boolean inUse) {
        this.sortingId = sortingId;
        this.name = name;
        this.notes = notes;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.powerRequired = powerRequired;
        this.staffNeeded = staffNeeded;
        this.minimumAge = minimumAge;
        this.maxParticipants = maxParticipants;
        this.equipmentCategoryId = equipmentCategoryId;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPowerRequired(int powerRequired) {
        this.powerRequired = powerRequired;
    }

    public void setStaffNeeded(int staffNeeded) {
        this.staffNeeded = staffNeeded;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setEquipmentCategoryId(int equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
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

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getPowerRequired() {
        return powerRequired;
    }

    public int getStaffNeeded() {
        return staffNeeded;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getEquipmentCategoryId() {
        return equipmentCategoryId;
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


