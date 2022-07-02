package com.event.logistic;

public class Logistic {

    private int id;
    private int distance;
    private String notes;
    private int requiredArea;
    private int requiredPower;
    private PlaceType placeType;
    private PowerType powerType;

    public Logistic() {
    }

    public Logistic(int id, int distance, String notes, int requiredArea, int requiredPower, PlaceType placeType, PowerType powerType) {
        this.id = id;
        this.distance = distance;
        this.notes = notes;
        this.requiredArea = requiredArea;
        this.requiredPower = requiredPower;
        this.placeType = placeType;
        this.powerType = powerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }
}
