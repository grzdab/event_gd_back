package com.event.event.client.dao;

import java.util.UUID;

public class ClientModel {

    String id;
    String fullName;
    String shortName;
    int contactId;
    boolean isActive;
    int clientTypeId;
    String notes;
    int taxInfoId;

    public ClientModel() {
    }

    public ClientModel(String id, String fullName, String shortName, int contactId, boolean isActive, int clientTypeId, String notes, int taxInfoId) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactId = contactId;
        this.isActive = isActive;
        this.clientTypeId = clientTypeId;
        this.notes = notes;
        this.taxInfoId = taxInfoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(int clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public int getTaxInfo() {
        return taxInfoId;
    }

    public void setTaxInfo(int taxInfoId) {
        this.taxInfoId = taxInfoId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
