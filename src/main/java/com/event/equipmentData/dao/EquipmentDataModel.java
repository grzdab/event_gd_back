package com.event.equipmentData.dao;

import com.event.equipmentData.EquipmentData;

import javax.persistence.*;

@Entity
@Table(name="equipment_data_model")
public class EquipmentDataModel {

    @Id
    @GeneratedValue(generator = "equipment_data", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_data", sequenceName = "equipment_data",allocationSize=1)
    @Column
    private Integer id;

    @Column
    private int equipmentId;

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

    public EquipmentDataModel(EquipmentData equipmentData) {
        this.equipmentId = equipmentData.getEquipmentId();
        this.width = equipmentData.getWidth();
        this.length = equipmentData.getLength();
        this.height = equipmentData.getHeight();
        this.weight = equipmentData.getWeight();
        this.powerRequired = equipmentData.getPowerRequired();
        this.staffNeeded = equipmentData.getStaffNeeded();
        this.minimumAge = equipmentData.getMinimumAge();
        this.maxParticipants = equipmentData.getMaxParticipants();
    }


    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int id) {
        this.equipmentId = id;
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
