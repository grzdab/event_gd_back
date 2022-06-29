package com.event.equipment;

import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentStatus.EquipmentStatus;

import java.util.List;

public class Equipment {
    private int id;
    private int sortingId;
    private String name;
    private EquipmentCategory category;
    private String notes;
    private EquipmentData equipmentData;
    private List<EquipmentPhoto> photos;
    private EquipmentStatus status;
    private int bookingStatus;
    private List<EquipmentBookingStatus> equipmentBookingPeriods;
    private boolean inUse;

    public Equipment() {
    }

    public Equipment(int id, int sortingId, String name, EquipmentCategory category, String notes, EquipmentData equipmentData, List<EquipmentPhoto> photos, EquipmentStatus status, int bookingStatus, List<EquipmentBookingStatus> equipmentBookingPeriods, boolean inUse) {
        this.id = id;
        this.sortingId = sortingId;
        this.name = name;
        this.category = category;
        this.notes = notes;
        this.equipmentData = equipmentData;
        this.photos = photos;
        this.status = status;
        this.bookingStatus = bookingStatus;
        this.equipmentBookingPeriods = equipmentBookingPeriods;
        this.inUse = inUse;
    }

    public int getId() {
        return id;
    }

    public int getSortingId() {
        return sortingId;
    }

    public String getName() {
        return name;
    }

    public EquipmentCategory getCategory() {
        return category;
    }

    public String getNotes() {
        return notes;
    }

    public EquipmentData getEquipmentData() {
        return equipmentData;
    }

    public List<EquipmentPhoto> getPhotos() {
        return photos;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public List<EquipmentBookingStatus> getEquipmentBookingPeriods() {
        return equipmentBookingPeriods;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSortingId(int sortingId) {
        this.sortingId = sortingId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(EquipmentCategory category) {
        this.category = category;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEquipmentData(EquipmentData equipmentData) {
        this.equipmentData = equipmentData;
    }

    public void setPhotos(List<EquipmentPhoto> photos) {
        this.photos = photos;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setEquipmentBookingPeriods(List<EquipmentBookingStatus> equipmentBookingPeriods) {
        this.equipmentBookingPeriods = equipmentBookingPeriods;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
