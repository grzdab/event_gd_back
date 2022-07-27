package com.event.equipmentData.dao;

import javax.persistence.*;

@Entity
@Table(name = "equipment_data_model")
public class EquipmentDataModel {

    @Id
    @GeneratedValue(generator = "equipment_data", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_data", sequenceName = "equipment_data",allocationSize=1)
    @Column
    private Integer id;

    @Column
    private Integer equipmentId;

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

    public EquipmentDataModel(int equipmentId,int width, int length, int height, int weight, int powerRequired, int staffNeeded, int minimumAge, int maxParticipants) {
        this.equipmentId =equipmentId;
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

    public Integer getId() {
        return id;
    }

    public Integer getEquipmentId() {
        return equipmentId;
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

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
