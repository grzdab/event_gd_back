package com.event.equipmentData.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EquipmentDataModel {

    private Integer id;

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

    public EquipmentDataModel() {
    }

    public EquipmentDataModel(int width, int length, int height, int weight, int powerRequired, int staffNeeded, int minimumAge, int maxParticipants) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.powerRequired = powerRequired;
        this.staffNeeded = staffNeeded;
        this.minimumAge = minimumAge;
        this.maxParticipants = maxParticipants;
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

    @Id
    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }
}
