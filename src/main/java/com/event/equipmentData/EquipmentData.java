package com.event.equipmentData;

public class EquipmentData {
    private int id;
    private int width;
    private int length;
    private int height;
    private int weight;
    private int powerRequired;
    private int staffNeeded;
    private int minimumAge;
    private int maxParticipants;

    public EquipmentData() {
    }

    public EquipmentData(int width, int length, int height, int weight, int powerRequired, int staffNeeded, int minimumAge, int maxParticipants) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.powerRequired = powerRequired;
        this.staffNeeded = staffNeeded;
        this.minimumAge = minimumAge;
        this.maxParticipants = maxParticipants;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
}
