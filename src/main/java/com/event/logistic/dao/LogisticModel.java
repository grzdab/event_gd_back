package com.event.logistic.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogisticModel {

    private Integer id;
    private int distance;
    private String notes;
    private int requiredArea;
    private int requiredPower;
    private String placeType;
    private String powerType;

    public LogisticModel() {
    }

    public LogisticModel(Integer id, int distance, String notes, int requiredArea, int requiredPower, String placeType, String powerType) {
        this.id = id;
        this.distance = distance;
        this.notes = notes;
        this.requiredArea = requiredArea;
        this.requiredPower = requiredPower;
        this.placeType = placeType;
        this.powerType = powerType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRequiredArea() {
        return requiredArea;
    }

    public void setRequiredArea(int requiredArea) {
        this.requiredArea = requiredArea;
    }

    public int getRequiredPower() {
        return requiredPower;
    }

    public void setRequiredPower(int requiredPower) {
        this.requiredPower = requiredPower;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }
}
